package com.briantanabe.fd.ap.tests.unit;

import com.briantanabe.fd.ap.actionProcessor.ActionProcessor;
import com.briantanabe.fd.ap.tests.spy.RequestAllPlayerIdsObserver;
import com.briantanabe.fd.dp.fantasy.player.NflPlayer;
import com.briantanabe.fd.dp.tests.fixtures.MockWebRequest;
import com.briantanabe.fd.dp.tests.fixtures.WebConstants;
import com.briantanabe.fd.du.log.LoggingUtility;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by Brian on 10/12/14.
 */
public class ActionProcessorTests {
    private static ActionProcessor actionProcessor;

    @Before
    @Transactional
    public void setup(){
        LoggingUtility.turnLoggingOff();

        try {
            actionProcessor = new ActionProcessor(MockWebRequest.getMockWebRequestForPlayerIdProviderTests());

            Thread actionProcessorThread = new Thread(actionProcessor);
            actionProcessorThread.start();
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Failed to create the ActionProcessor");
        }
    }

    @Test
    public void shouldBeAbleToRequestGetAllPlayerIdEvents(){
        try {
            ActionProcessor testableActionProcessor = new ActionProcessor(MockWebRequest.getMockSecureWebRequestForEspnLeaguePlayerOwnershipProviderTests(WebConstants.TEST_ESPN_LEAGUE_ID));

            RequestAllPlayerIdsObserver eventSource = new RequestAllPlayerIdsObserver();
            actionProcessor.addObserver(eventSource);
            eventSource.addObserver(actionProcessor);

            eventSource.requestAllPlayerIds();
            List<NflPlayer> allPlayerIds = eventSource.getAllPlayerIds();
            assertEquals("The number of playerIds returned by the event was not correct.", 549, allPlayerIds.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

package com.briantanabe.fd.ap.tests.unit;

import com.briantanabe.fd.ap.actionProcessor.ActionProcessor;
import com.briantanabe.fd.ap.tests.spy.RequestAllPlayerIdsObserver;
import com.briantanabe.fd.dp.tests.fixtures.MockWebRequest;
import com.briantanabe.fd.du.log.LoggingUtility;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.fail;

/**
 * Created by Brian on 10/12/14.
 */
public class ActionProcessorTests {
    private static ActionProcessor actionProcessor;

    @BeforeClass
    public static void setup(){
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
        RequestAllPlayerIdsObserver eventSource = new RequestAllPlayerIdsObserver();
        actionProcessor.addObserver(eventSource);

        eventSource.requestAllPlayerIds();

    }
}

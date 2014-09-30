package com.briantanabe.fd.fixtures;

import com.briantanabe.fd.web.SecureWebRequest;
import com.briantanabe.fd.web.WebRequest;
import com.briantanabe.fd.web.auth.TestableCredentialProvider;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.briantanabe.fd.fixtures.FileDocumentor.getDocumentFromFileHtml;
import static com.briantanabe.fd.web.WebPage.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by BTanabe on 9/30/2014.
 */
public class MockWebRequest {

    public static SecureWebRequest getMockSecureWebRequestForEspnLeaguePlayerOwnershipProviderTests(int leagueId) throws IOException {
        Map<String, Document> urlToDocumentMap = new LinkedHashMap<String, Document>();
        for(int index = 1; index < 40; index++){
            String requestUrl = String.format("http://games.espn.go.com/ffl/tools/projections?&leagueId=%d&seasonTotals=true&seasonId=2014&startIndex=%d", leagueId, (index - 1) * 40);
            String htmlDocumentPath = String.format("./DataProvider/src/test/resources/WebPages/espnProjectionsPage/espnPlayersSeasonProjectionPage%d.html", index);
            Document testPage = getDocumentFromFileHtml(htmlDocumentPath);
            urlToDocumentMap.put(requestUrl, testPage);
        }

        return MockWebRequest.getMockSecureWebRequest(urlToDocumentMap);
    }

    public static WebRequest getMockWebRequestForNumberFireRemainingSeasonProjectionsProvider() throws IOException {
        Map<String, Document> urlToDocumentMap = new LinkedHashMap<String, Document>();
        urlToDocumentMap.put(NUMBER_FIRE_REMAINING_SEASON_QUARTERBACK_PROJECTIONS_URL, NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonQuarterbackProjectionDocument());
        urlToDocumentMap.put(NUMBER_FIRE_REMAINING_SEASON_RUNNING_BACK_PROJECTIONS_URL, NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonRunningBackProjectionDocument());
        urlToDocumentMap.put(NUMBER_FIRE_REMAINING_SEASON_WIDE_RECEIVER_PROJECTIONS_URL, NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonWideReceiverProjectionDocument());
        urlToDocumentMap.put(NUMBER_FIRE_REMAINING_SEASON_TIGHT_END_PROJECTIONS_URL, NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonTightEndProjectionDocument());
        urlToDocumentMap.put(NUMBER_FIRE_REMAINING_SEASON_KICKER_PROJECTIONS_URL, NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonKickerProjectionDocument());
        urlToDocumentMap.put(NUMBER_FIRE_REMAINING_SEASON_DEFENSE_PROJECTIONS_URL, NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonDefenseProjectionDocument());

        return MockWebRequest.getMockWebRequest(urlToDocumentMap);
    }

    public static WebRequest getMockWebRequestForPlayerIdProviderTests() throws IOException {
        Map<String, Document> urlToDocumentMap = new LinkedHashMap<String, Document>();
        urlToDocumentMap.put(NUMBER_FIRE_REMAINING_SEASON_QUARTERBACK_PROJECTIONS_URL, NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonQuarterbackProjectionDocument());
        urlToDocumentMap.put(NUMBER_FIRE_REMAINING_SEASON_RUNNING_BACK_PROJECTIONS_URL, NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonRunningBackProjectionDocument());
        urlToDocumentMap.put(NUMBER_FIRE_REMAINING_SEASON_WIDE_RECEIVER_PROJECTIONS_URL, NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonWideReceiverProjectionDocument());
        urlToDocumentMap.put(NUMBER_FIRE_REMAINING_SEASON_TIGHT_END_PROJECTIONS_URL, NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonTightEndProjectionDocument());
        urlToDocumentMap.put(NUMBER_FIRE_REMAINING_SEASON_KICKER_PROJECTIONS_URL, NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonKickerProjectionDocument());
        urlToDocumentMap.put(NUMBER_FIRE_REMAINING_SEASON_DEFENSE_PROJECTIONS_URL, NumberFireRemainingSeasonProjectionFixture.getRemainingSeasonDefenseProjectionDocument());

        return MockWebRequest.getMockWebRequest(urlToDocumentMap);
    }

    public static WebRequest getMockWebRequestForNumberFireCurrentWeekProjectionsProvider() throws IOException {
        Map<String, Document> urlToDocumentMap = new LinkedHashMap<String, Document>();
        urlToDocumentMap.put(NUMBER_FIRE_CURRENT_WEEK_QUARTERBACK_PROJECTIONS_URL, NumberFireCurrentWeekProjectionFixture.getCurrentWeekQuarterbackProjectionDocument());
        urlToDocumentMap.put(NUMBER_FIRE_CURRENT_WEEK_RUNNING_BACK_PROJECTIONS_URL, NumberFireCurrentWeekProjectionFixture.getCurrentWeekRunningBackProjectionDocument());
        urlToDocumentMap.put(NUMBER_FIRE_CURRENT_WEEK_WIDE_RECEIVER_PROJECTIONS_URL, NumberFireCurrentWeekProjectionFixture.getCurrentWeekWideReceiverProjectionDocument());
        urlToDocumentMap.put(NUMBER_FIRE_CURRENT_WEEK_TIGHT_END_PROJECTIONS_URL, NumberFireCurrentWeekProjectionFixture.getCurrentWeekTightEndProjectionDocument());
        urlToDocumentMap.put(NUMBER_FIRE_CURRENT_WEEK_KICKER_PROJECTIONS_URL, NumberFireCurrentWeekProjectionFixture.getCurrentWeekKickerProjectionDocument());
        urlToDocumentMap.put(NUMBER_FIRE_CURRENT_WEEK_DEFENSE_PROJECTIONS_URL, NumberFireCurrentWeekProjectionFixture.getCurrentWeekDefenseProjectionDocument());

        return MockWebRequest.getMockWebRequest(urlToDocumentMap);
    }

    private static WebRequest getMockWebRequest(Map<String, Document> urlToHtmlDocumentMap) throws IOException {
        WebRequest webRequest = mock(WebRequest.class);
        for(String url : urlToHtmlDocumentMap.keySet().toArray(new String[urlToHtmlDocumentMap.size()])){
            when(webRequest.getPageAsDocument(url)).thenReturn(urlToHtmlDocumentMap.get(url));
        }

        return webRequest;
    }

    private static SecureWebRequest getMockSecureWebRequest(Map<String, Document> urlToHtmlDocumentMap) throws IOException {
        SecureWebRequest webRequest = mock(SecureWebRequest.class);
        for(String url : urlToHtmlDocumentMap.keySet().toArray(new String[urlToHtmlDocumentMap.size()])){
            when(webRequest.getPageAsDocument(url)).thenReturn(urlToHtmlDocumentMap.get(url));
        }

        when(webRequest.login(new TestableCredentialProvider())).thenReturn(webRequest);

        return webRequest;
    }
}

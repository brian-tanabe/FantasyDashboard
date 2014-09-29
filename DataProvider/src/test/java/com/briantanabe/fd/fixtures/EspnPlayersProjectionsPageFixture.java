package com.briantanabe.fd.fixtures;

import org.jsoup.nodes.Document;

/**
 * Created by BTanabe on 9/26/2014.
 */
public class EspnPlayersProjectionsPageFixture {

    public static Document getAllPlayersProjectionPageOneAsDocument(){
        return FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/WebPages/espnProjectionsPage/espnPlayersSeasonProjectionPage1.html");
    }

    public static Document getAllPlayersProjectionPageTwoAsDocument(){
        return FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/WebPages/espnProjectionsPage/espnPlayersSeasonProjectionPage2.html");
    }
}

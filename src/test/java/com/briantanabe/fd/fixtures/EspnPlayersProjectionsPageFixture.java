package com.briantanabe.fd.fixtures;

import org.jsoup.nodes.Document;

import static com.briantanabe.fd.fixtures.FileDocumentor.getDocumentFromFileHtml;

/**
 * Created by BTanabe on 9/26/2014.
 */
public class EspnPlayersProjectionsPageFixture {

    public static Document getAllPlayersProjectionPageOneAsDocument(){
        return getDocumentFromFileHtml("./src/test/resources/WebPages/espnPlayersSeasonProjectionPageOne.html");
    }

    public static Document getAllPlayersProjectionPageTwoAsDocument(){
        return getDocumentFromFileHtml("./src/test/resources/WebPages/espnPlayersSeasonProjectionPageTwo.html");
    }
}

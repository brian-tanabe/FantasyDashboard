package com.briantanabe.fd.fixtures;

import org.jsoup.nodes.Document;

import static com.briantanabe.fd.fixtures.FileDocumentor.getDocumentFromFileHtml;

/**
 * Created by BTanabe on 9/26/2014.
 */
public class EspnPlayersPageFixture {

    public static Document getAllPlayersPageOneAsDocument(){
        return getDocumentFromFileHtml("./src/test/resources/WebPages/espn/espnPlayersPageOne.html");
    }

    public static Document getAllPlayersPageTwoAsDocument(){
        return getDocumentFromFileHtml("./src/test/resources/WebPages/espn/espnPlayersPageTwo.html");
    }
}

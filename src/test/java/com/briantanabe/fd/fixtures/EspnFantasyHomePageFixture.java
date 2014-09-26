package com.briantanabe.fd.fixtures;

import org.jsoup.nodes.Document;

import static com.briantanabe.fd.fixtures.FileDocumentor.getDocumentFromFileHtml;


/**
 * Created by Brian on 9/25/14.
 */
public class EspnFantasyHomePageFixture {

    public static Document getAuthenticatedEspnFantasyHomePageDocument(){
        return getDocumentFromFileHtml("./src/test/resources/WebPages/espnAuthenticatedFantasyHomePage.html");
    }

    public static Document getUnauthenticatedEspnFantasyHomePageDocument(){
        return getDocumentFromFileHtml("./src/test/resources/WebPages/espnUnAuthenticatedFantasyHomePage.html");
    }

}

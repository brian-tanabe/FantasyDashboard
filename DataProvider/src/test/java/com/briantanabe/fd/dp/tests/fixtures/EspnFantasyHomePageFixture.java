package com.briantanabe.fd.dp.tests.fixtures;

import org.jsoup.nodes.Document;


/**
 * Created by Brian on 9/25/14.
 */
public class EspnFantasyHomePageFixture {

    public static Document getAuthenticatedEspnFantasyHomePageDocument(){
        return FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/WebPages/espnAuthenticatedFantasyHomePage.html");
    }

    public static Document getUnauthenticatedEspnFantasyHomePageDocument(){
        return FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/WebPages/espnUnAuthenticatedFantasyHomePage.html");
    }

}

package com.briantanabe.fd.fixtures;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;

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

    private static Document getDocumentFromFileHtml(String path){
        try {
            return Jsoup.parse(new File(path), "UTF8");
        } catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}

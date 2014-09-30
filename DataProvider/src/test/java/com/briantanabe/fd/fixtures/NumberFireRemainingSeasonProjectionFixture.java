package com.briantanabe.fd.fixtures;

import org.jsoup.nodes.Document;

import static com.briantanabe.fd.fixtures.FileDocumentor.getDocumentFromFileHtml;

/**
 * Created by BTanabe on 9/30/2014.
 */
public class NumberFireRemainingSeasonProjectionFixture {

    public static Document getRemainingSeasonQuarterbackProjectionDocument(){
        return getDocumentFromFileHtml("./DataProvider/src/test/resources/WebPages/nfRemainingSeasonQbProjections.html");
    }

    public static Document getRemainingSeasonRunningBackProjectionDocument(){
        return getDocumentFromFileHtml("./DataProvider/src/test/resources/WebPages/nfRemainingSeasonRbProjections.html");
    }

    public static Document getRemainingSeasonWideReceiverProjectionDocument(){
        return getDocumentFromFileHtml("./DataProvider/src/test/resources/WebPages/nfRemainingSeasonWrProjections.html");
    }

    public static Document getRemainingSeasonTightEndProjectionDocument(){
        return getDocumentFromFileHtml("./DataProvider/src/test/resources/WebPages/nfRemainingSeasonTeProjections.html");
    }

    public static Document getRemainingSeasonKickerProjectionDocument(){
        return getDocumentFromFileHtml("./DataProvider/src/test/resources/WebPages/nfRemainingSeasonKProjections.html");
    }

    public static Document getRemainingSeasonDefenseProjectionDocument(){
        return getDocumentFromFileHtml("./DataProvider/src/test/resources/WebPages/nfRemainingSeasonDefProjections.html");
    }
}

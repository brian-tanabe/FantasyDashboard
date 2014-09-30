package com.briantanabe.fd.fixtures;

import org.jsoup.nodes.Document;

import static com.briantanabe.fd.fixtures.FileDocumentor.getDocumentFromFileHtml;

/**
 * Created by BTanabe on 9/30/2014.
 */
public class NumberFireCurrentWeekProjectionFixture {
    public static Document getCurrentWeekQuarterbackProjectionDocument(){
        return getDocumentFromFileHtml("./DataProvider/src/test/resources/WebPages/nfCurrentWeekQbProjections.html");
    }

    public static Document getCurrentWeekRunningBackProjectionDocument(){
        return getDocumentFromFileHtml("./DataProvider/src/test/resources/WebPages/nfCurrentWeekRbProjections.html");
    }

    public static Document getCurrentWeekWideReceiverProjectionDocument(){
        return getDocumentFromFileHtml("./DataProvider/src/test/resources/WebPages/nfCurrentWeekWrProjections.html");
    }

    public static Document getCurrentWeekTightEndProjectionDocument(){
        return getDocumentFromFileHtml("./DataProvider/src/test/resources/WebPages/nfCurrentWeekTeProjections.html");
    }

    public static Document getCurrentWeekKickerProjectionDocument(){
        return getDocumentFromFileHtml("./DataProvider/src/test/resources/WebPages/nfCurrentWeekKProjections.html");
    }

    public static Document getCurrentWeekDefenseProjectionDocument(){
        return getDocumentFromFileHtml("./DataProvider/src/test/resources/WebPages/nfCurrentWeekDefProjections.html");
    }
}

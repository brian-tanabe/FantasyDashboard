package com.briantanabe.fd.dp.tests.fixtures;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;

/**
 * Created by BTanabe on 9/26/2014.
 */
public class FileDocumentor {
    public static Document getDocumentFromFileHtml(String path){
        try {
            return Jsoup.parse(new File(path), "UTF8");
        } catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}

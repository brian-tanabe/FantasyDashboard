package com.briantanabe.fd.fixtures;

import com.briantanabe.fd.web.SecureWebRequest;
import com.briantanabe.fd.web.WebRequest;
import com.briantanabe.fd.web.auth.TestableCredentialProvider;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by BTanabe on 9/30/2014.
 */
public class MockWebRequest {

    public static WebRequest getMockWebRequest(Map<String, Document> urlToHtmlDocumentMap) throws IOException {
        WebRequest webRequest = mock(WebRequest.class);
        for(String url : urlToHtmlDocumentMap.keySet().toArray(new String[urlToHtmlDocumentMap.size()])){
            when(webRequest.getPageAsDocument(url)).thenReturn(urlToHtmlDocumentMap.get(url));
        }

        return webRequest;
    }

    public static SecureWebRequest getMockSecureWebRequest(Map<String, Document> urlToHtmlDocumentMap) throws IOException {
        SecureWebRequest webRequest = mock(SecureWebRequest.class);
        for(String url : urlToHtmlDocumentMap.keySet().toArray(new String[urlToHtmlDocumentMap.size()])){
            when(webRequest.getPageAsDocument(url)).thenReturn(urlToHtmlDocumentMap.get(url));
        }

        when(webRequest.login(new TestableCredentialProvider())).thenReturn(webRequest);

        return webRequest;
    }
}

package com.briantanabe.fd.tests;

import com.briantanabe.fd.web.WebRequest;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Brian on 9/24/14.
 */
public class WebRequestTests {

    @Test
    public void shouldBeAbleToDownloadWebsites(){
        try {
            String url = "http://date.jsontest.com/";
            String goldHtml = "{\\n   \"time\": \"12:07:08 AM\",\\n   \"milliseconds_since_epoch\": 1411603628820,\\n   \"date\": \"09-25-2014\"\\n}";

            WebRequest webRequest = mock(WebRequest.class);
            when(webRequest.getPage(url)).thenReturn(goldHtml);

            assertEquals("Page HTML does not match", goldHtml, webRequest.getPage(url));
        } catch(Exception ex){
            fail("FAILED to make a WebRequest::getPage");
        }
    }

    @Test
    public void shouldBeAbleToGetSomeHtmlWhenMakingRequests(){
        try {
            String url = "http://date.jsontest.com/";
            WebRequest webRequest = new WebRequest();
            String pageHtml = webRequest.getPage(url);
            assertTrue("HTML request returned nothing", pageHtml.length() > 0);
        } catch(Exception ex){
            fail("FAILED to download any HTML");
        }
    }
}

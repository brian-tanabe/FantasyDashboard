package com.briantanabe.fd.web;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Brian on 9/24/14.
 */
public class WebRequest {
    private final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:32.0) Gecko/20100101 Firefox/32.0";
    private HttpClient client;

    public WebRequest(){
        client = HttpClientBuilder.create().build();
    }

    public String getPage(String url) throws ClientProtocolException, IOException {
        HttpGet request = new HttpGet(url);
        request.addHeader("User-agent", USER_AGENT);
        HttpResponse response = client.execute(request);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer stringBuffer = new StringBuffer();
        String line = "";
        while((line = bufferedReader.readLine()) != null){
            stringBuffer.append(line);
        }

        return stringBuffer.toString();
    }
}

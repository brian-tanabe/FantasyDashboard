package com.briantanabe.fd.web;

import com.briantanabe.fd.web.auth.CredentialProviderI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;

import static com.briantanabe.fd.web.WebPage.*;

/**
 * Created by BTanabe on 9/25/2014.
 */
public class SecureWebRequest extends WebRequest {
    private HttpClient client = HttpClientBuilder.create().build();
    private String cookies;

    public SecureWebRequest login(CredentialProviderI credentialProvider) throws IOException {
        cookies = getLoginCookiesFromServer(credentialProvider);
        return this;
    }

    private String getLoginCookiesFromServer(CredentialProviderI credentialProvider) throws IOException {
        HttpPost post = new HttpPost(credentialProvider.getLoginUrl());

        post.setHeader("Host", "r.espn.go.com");
        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        post.setHeader("Accept-Language", "en-US,en;q=0.8");
        post.setHeader("Cookie", cookies);
        post.setHeader("Connection", "keep-alive");
        post.setHeader("Accept-Encoding", "gzip,deflate");

        HttpResponse response = client.execute(post);

        return response.getFirstHeader("Set-Cookie") == null ? "" : response.getFirstHeader("Set-Cookie").toString();
    }

    public Document getPageAsDocument(String url) throws IOException {
        HttpGet request = new HttpGet(url);
        request.setHeader("User-Agent", USER_AGENT);
        request.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        request.setHeader("Accept-Language", "en-US,en;q=0.8");
        request.setHeader("Cookie", cookies);

        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String pageHtml = "";
        while ((pageHtml = rd.readLine()) != null) {
            result.append(pageHtml);
        }


        return Jsoup.parse(result.toString());
    }
}

package com.briantanabe.fd.tests.web;

import com.briantanabe.fd.web.SecureWebRequest;
import com.briantanabe.fd.web.auth.EspnCredentialProvider;
import org.jsoup.nodes.Document;
import org.junit.Test;


import static org.junit.Assert.fail;

/**
 * Created by BTanabe on 9/25/2014.
 */
public class EspnWebRequestTests {

    @Test
    public void shouldBeAbleToAuthenticateWithEspnAndGetLoginCookie(){
        try {
            new SecureWebRequest().login(new EspnCredentialProvider());
        } catch(Exception ex){
            fail("Failed to log into ESPN");
        }
    }

    @Test
    public void shouldBeAbleToFindLogoutButtonOnEspnsHomePageIfLoggedIn(){
        try {
            SecureWebRequest secureWebRequest = new SecureWebRequest().login(new EspnCredentialProvider());
            Document espnHomePage = secureWebRequest.getPageAsDocument("http://www.espn.com");

            fail("NOT SURE HOW TO DETERMINE IF YOU'RE LOGGED IN...");
            System.out.println("");
        } catch(Exception ex){
            fail("Failed to log into ESPN");
        }
    }
}

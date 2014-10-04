package com.briantanabe.fd.dp.web.auth;

/**
 * Created by BTanabe on 9/30/2014.
 */
public class TestableCredentialProvider implements CredentialProviderI {

    public String getUserName() {
        return "";
    }

    public String getPassword() {
        return "";
    }

    public String getLoginUrl() {
        return "http://www.brian-tanabe.com";
    }
}

package com.briantanabe.fd.web.auth;

import com.briantanabe.fd.web.WebPage;

/**
 * Created by BTanabe on 9/25/2014.
 */
public class EspnCredentialProvider implements CredentialProviderI {
    private static String username = "Dusty1955";
    private static String password = "snickers";

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLoginUrl() {
        return WebPage.getEspnLoginUrl(username, password);
    }
}

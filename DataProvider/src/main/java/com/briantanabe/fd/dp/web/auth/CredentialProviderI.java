package com.briantanabe.fd.dp.web.auth;

/**
 * Created by BTanabe on 9/25/2014.
 */
public interface CredentialProviderI {
    public String getUserName();
    public String getPassword();
    public String getLoginUrl();
}

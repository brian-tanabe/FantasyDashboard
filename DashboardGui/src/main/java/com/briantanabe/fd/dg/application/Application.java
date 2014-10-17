package com.briantanabe.fd.dg.application;

import com.briantanabe.fd.ap.actionProcessor.ActionProcessor;
import com.briantanabe.fd.dg.gui.MainGui;
import com.briantanabe.fd.dp.web.SecureWebRequest;
import com.briantanabe.fd.dp.web.auth.EspnCredentialProvider;

/**
 * Created by Brian on 10/3/2014.
 */
public class Application {

    public static void main(String[] args) {
        try {
            final MainGui mainGui = new MainGui();
            mainGui.createGui();

            // Replace this with logged on information:
            SecureWebRequest secureWebRequest = new SecureWebRequest();
            secureWebRequest.login(new EspnCredentialProvider());

            ActionProcessor actionProcessor = new ActionProcessor(secureWebRequest);
            Thread actionProcessorThread = new Thread(actionProcessor);

            actionProcessor.addObserver(mainGui);
            mainGui.addObserver(actionProcessor);

            actionProcessorThread.start();
            mainGui.open();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

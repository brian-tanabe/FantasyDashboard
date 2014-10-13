package com.briantanabe.fd.dg.application;

import com.briantanabe.fd.ap.actionProcessor.ActionProcessor;
import com.briantanabe.fd.dg.gui.MainGui;

/**
 * Created by Brian on 10/3/2014.
 */
public class Application {

    public static void main(String[] args) {
        try {
            final MainGui mainGui = new MainGui();

            ActionProcessor actionProcessor = new ActionProcessor();
            Thread actionProcessorThread = new Thread(actionProcessor);
            actionProcessorThread.start();

            System.out.println("Reached past gui thread");
            mainGui.open();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

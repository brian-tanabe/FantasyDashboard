package com.briantanabe.fd.du.log;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;

/**
 * Created by btanabe on 10/6/2014.
 */
public class LoggingUtility {

    public static void turnLoggingOff(){
        setLoggingLevel(Level.OFF);
    }

    public static void setLoggingLevel(Level level){
        List<String> loggerNames = Collections.list(LogManager.getLogManager().getLoggerNames());
        for(String logName : loggerNames){
            LogManager.getLogManager().getLogger(logName).setLevel(level);
        }
    }
}

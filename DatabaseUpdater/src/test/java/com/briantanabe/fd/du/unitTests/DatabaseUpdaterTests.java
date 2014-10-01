package com.briantanabe.fd.du.unitTests;

import com.briantanabe.fd.du.updater.DatabaseUpdater;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by BTanabe on 9/30/2014.
 */
public class DatabaseUpdaterTests {


    // http://docs.jboss.org/hibernate/orm/4.2/quickstart/en-US/html/ch03.html
    // http://www.tutorialspoint.com/hibernate/hibernate_configuration.htm
    @Test
    public void testCanReachDatabase(){
        DatabaseUpdater databaseUpdater = DatabaseUpdater.getInstance();
        assertTrue("Failed to isConnected to our database", databaseUpdater.isConnected());
    }
}

package com.briantanabe.fd.dp.tests.unit.providers;

import com.briantanabe.fd.dp.providers.PlayerSearchTableDataProvider;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by btanabe on 10/13/2014.
 */
public class PlayerSearchTableDataProviderTests {

    @Test
    public void shouldBeAbleToFindFiveHundredPlayers(){
        PlayerSearchTableDataProvider dataProvider = new PlayerSearchTableDataProvider();
        assertEquals("Did not find the proper number of players", 549, dataProvider.getAllPlayerSearchTableRows().size());
    }
}

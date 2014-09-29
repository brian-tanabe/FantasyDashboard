package com.briantanabe.fd.providers;

import com.briantanabe.fd.fantasy.player.EspnNflPlayer;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Brian on 9/28/14.
 */
public class EspnLeaguePlayerOwnershipProvider {
    private ArrayList<EspnNflPlayer> ownershipInfo = new ArrayList<EspnNflPlayer>();

    public void scrapeForOwnershipInfo() throws IOException {

    }

    public ArrayList<EspnNflPlayer> getPlayerOwnershipInfo(){
        return ownershipInfo;
    }
}

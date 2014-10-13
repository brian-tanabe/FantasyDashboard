package com.briantanabe.fd.ap.events;

import com.briantanabe.fd.dp.fantasy.player.NflPlayer;

import java.util.List;

/**
 * Created by Brian on 10/12/14.
 */
public class RequestAllPlayerIdsEvent {
    private List<NflPlayer> allPlayers;

    public void setAllPlayers(List<NflPlayer> allPlayers) {
        this.allPlayers = allPlayers;
    }

    public List<NflPlayer> getAllPlayers() {
        return allPlayers;
    }
}

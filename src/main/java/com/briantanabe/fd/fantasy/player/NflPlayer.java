package com.briantanabe.fd.fantasy.player;

/**
 * Created by BTanabe on 9/26/2014.
 */
public class NflPlayer {
    private int espnPlayerId;
    private String name;

    public NflPlayer(int espnPlayerId, String name){
        this.espnPlayerId = espnPlayerId;
        this.name = name;
    }

    public int getEspnPlayerId(){
        return espnPlayerId;
    }

    public String getName(){
        return name;
    }
}

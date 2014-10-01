package com.briantanabe.fd.fantasy.player;

/**
 * Created by BTanabe on 9/26/2014.
 */
public class NflPlayer {
    private int espnPlayerId;
    private int numberFireId;
    private String name;

    protected NflPlayer(){} // for hibernate

    public NflPlayer(int espnPlayerId, int numberFireId, String name){
        this.espnPlayerId = espnPlayerId;
        this.numberFireId = numberFireId;
        this.name = name;
    }

    public int getEspnPlayerId(){
        return espnPlayerId;
    }

    public int getNumberFireId(){
        return numberFireId;
    }

    public String getName(){
        return name;
    }

    public void setEspnPlayerId(int espnPlayerId) {
        this.espnPlayerId = espnPlayerId;
    }

    public void setNumberFireId(int numberFireId) {
        this.numberFireId = numberFireId;
    }

    public void setName(String name) {
        this.name = name;
    }
}

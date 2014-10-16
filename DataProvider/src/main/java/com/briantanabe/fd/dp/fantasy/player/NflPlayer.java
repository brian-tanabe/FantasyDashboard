package com.briantanabe.fd.dp.fantasy.player;

/**
 * Created by BTanabe on 9/26/2014.
 */
public class NflPlayer {
    private int id;
    private int espnPlayerId;
    private int numberFireId;
    private String name;

    protected NflPlayer(){} // for hibernate

    public NflPlayer(int espnPlayerId, int numberFireId, String name){
        this.espnPlayerId = espnPlayerId;
        this.numberFireId = numberFireId;
        this.name = name;
    }

    protected int getId(){ return id; }     // for hibernate

    public int getEspnPlayerId(){
        return espnPlayerId;
    }

    public int getNumberFireId(){
        return numberFireId;
    }

    public String getName(){
        return name;
    }

    protected void setId(int id){ this.id = id; }   // for hibernate

    public void setEspnPlayerId(int espnPlayerId) {
        this.espnPlayerId = espnPlayerId;
    }

    public void setNumberFireId(int numberFireId) {
        this.numberFireId = numberFireId;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("name=[%s]; espnId=[%d]; nfId=[%d]", name, espnPlayerId, numberFireId);
    }
}

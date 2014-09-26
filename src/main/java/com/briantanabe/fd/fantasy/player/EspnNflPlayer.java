package com.briantanabe.fd.fantasy.player;

/**
 * Created by BTanabe on 9/26/2014.
 */
public class EspnNflPlayer extends NflPlayer {
    private int id;
    private int owner;

    public EspnNflPlayer(int espnId, String name, int owner){
        super(name);
        this.id = espnId;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}

package com.briantanabe.fd.fantasy.player;

/**
 * Created by BTanabe on 9/26/2014.
 */
public class EspnNflPlayer extends NflPlayer {
    private int leagueId;
    private int owner;

    public EspnNflPlayer(int leagueId, int espnId, String name, int owner){
        super(espnId, - 1, name);
        this.leagueId = leagueId;
        this.owner = owner;
    }

    public int getLeagueId(){ return leagueId; }

    public void setLeagueId(int leagueId){ this.leagueId = leagueId; }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}

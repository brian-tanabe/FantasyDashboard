package com.briantanabe.fd.fantasy.player;

/**
 * Created by Brian on 9/24/14.
 */
public class NumberFireRanking extends NflPlayer {
    private int ranking;
    private double firePoints;

    protected NumberFireRanking(int numberFireId, int espnPlayerId, String name, int ranking, double firePoints){
        super(espnPlayerId, name);
        this.ranking = ranking;
        this.firePoints = firePoints;
    }

    // GETTERS:
    public int getRanking() {
        return ranking;
    }

    public double getFirePoints() {
        return firePoints;
    }

    // SETTERS:
    public void setRanking(int ranking){
        this.ranking = ranking;
    }

    public void setFirePoints(double firePoints){
        this.firePoints = firePoints;
    }
}

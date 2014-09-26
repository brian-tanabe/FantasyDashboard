package com.briantanabe.fd.fantasy.player;

/**
 * Created by Brian on 9/24/14.
 */
public class NumberFireRanking extends NflPlayer {
    private int numberFireId;
    private int ranking;
    private double firePoints;

    public NumberFireRanking(int numberFireId, int espnPlayerId, String name){
        super(espnPlayerId, name);
        this.numberFireId = numberFireId;
    }

    public NumberFireRanking(int numberFireId, int espnPlayerId, String name, int ranking, double firePoints){
        super(espnPlayerId, name);
        this.numberFireId = numberFireId;
        this.ranking = ranking;
        this.firePoints = firePoints;
    }

    // GETTERS:
    public int getNumberFireId(){
        return numberFireId;
    }

    public int getRanking() {
        return ranking;
    }

    public double getFirePoints() {
        return firePoints;
    }

    // SETTERS:
    public void setNumberFireId(int numberFireId){
        this.numberFireId = numberFireId;
    }

    public void setRanking(int ranking){
        this.ranking = ranking;
    }

    public void setFirePoints(double firePoints){
        this.firePoints = firePoints;
    }
}

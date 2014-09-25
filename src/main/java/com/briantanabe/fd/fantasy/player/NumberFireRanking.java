package com.briantanabe.fd.fantasy.player;

/**
 * Created by Brian on 9/24/14.
 */
public class NumberFireRanking {
    private String name;
    private int ranking;
    private double firePoints;

    public NumberFireRanking(){}

    public NumberFireRanking(String name, int ranking, double firePoints){
        this.name = name;
        this.ranking = ranking;
        this.firePoints = firePoints;
    }

    // GETTERS:
    public String getName() {
        return name;
    }

    public int getRanking() {
        return ranking;
    }

    public double getFirePoints() {
        return firePoints;
    }

    // SETTERS:
    public void setName(String name){
        this.name = name;
    }

    public void setRanking(int ranking){
        this.ranking = ranking;
    }

    public void setFirePoints(double firePoints){
        this.firePoints = firePoints;
    }
}

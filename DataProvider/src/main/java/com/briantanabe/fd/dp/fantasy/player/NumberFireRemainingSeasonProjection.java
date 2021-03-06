package com.briantanabe.fd.dp.fantasy.player;

/**
 * Created by BTanabe on 9/26/2014.
 */
public class NumberFireRemainingSeasonProjection extends NumberFireProjection {

    protected NumberFireRemainingSeasonProjection(){} // for hibernate

    public NumberFireRemainingSeasonProjection(int numberFireId, int espnPlayerId, String name){
        super(numberFireId, espnPlayerId, name, -1, -1);
    }

    public NumberFireRemainingSeasonProjection(int numberFireId, int espnPlayerId, String name, int ranking, double firePoints){
        super(numberFireId, espnPlayerId, name, ranking, firePoints);
    }
}

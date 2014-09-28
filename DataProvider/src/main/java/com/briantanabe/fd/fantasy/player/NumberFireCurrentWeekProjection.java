package com.briantanabe.fd.fantasy.player;

/**
 * Created by BTanabe on 9/26/2014.
 */
public class NumberFireCurrentWeekProjection extends NumberFireProjection {

    public NumberFireCurrentWeekProjection(int numberFireId, String name, int ranking, double firePoints){
        super(numberFireId, -1, name, ranking, firePoints);
    }
}

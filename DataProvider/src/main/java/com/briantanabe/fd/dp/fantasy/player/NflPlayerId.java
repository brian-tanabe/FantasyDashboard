package com.briantanabe.fd.dp.fantasy.player;

/**
 * Created by Brian on 10/14/14.
 */
public class NflPlayerId extends NflPlayer {

    protected NflPlayerId() {}      // for hibernate

    protected NflPlayerId(int espnId, int numberFireId, String name){
        super(espnId, numberFireId, name);
    }
}

package com.briantanabe.fd.dm.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Brian on 10/15/2014.
 */
public class PlayerIdsEntityPK implements Serializable {
    private int espnId;
    private int numberFireId;

    @Column(name = "ESPN_ID")
    @Id
    public int getEspnId() {
        return espnId;
    }

    public void setEspnId(int espnId) {
        this.espnId = espnId;
    }

    @Column(name = "NUMBER_FIRE_ID")
    @Id
    public int getNumberFireId() {
        return numberFireId;
    }

    public void setNumberFireId(int numberFireId) {
        this.numberFireId = numberFireId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerIdsEntityPK that = (PlayerIdsEntityPK) o;

        if (espnId != that.espnId) return false;
        if (numberFireId != that.numberFireId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = espnId;
        result = 31 * result + numberFireId;
        return result;
    }
}

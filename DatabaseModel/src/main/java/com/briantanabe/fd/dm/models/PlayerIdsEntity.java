package com.briantanabe.fd.dm.models;

import javax.persistence.*;

/**
 * Created by Brian on 10/17/2014.
 */
@Entity
@Table(name = "PLAYER_IDS", schema = "PUBLIC", catalog = "FANTAYDB")
@IdClass(PlayerIdsEntityPK.class)
public class PlayerIdsEntity {
    private int espnId;
    private int numberFireId;
    private String name;

    @Id
    @Column(name = "ESPN_ID")
    public int getEspnId() {
        return espnId;
    }

    public void setEspnId(int espnId) {
        this.espnId = espnId;
    }

    @Id
    @Column(name = "NUMBER_FIRE_ID")
    public int getNumberFireId() {
        return numberFireId;
    }

    public void setNumberFireId(int numberFireId) {
        this.numberFireId = numberFireId;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerIdsEntity that = (PlayerIdsEntity) o;

        if (espnId != that.espnId) return false;
        if (numberFireId != that.numberFireId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = espnId;
        result = 31 * result + numberFireId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}

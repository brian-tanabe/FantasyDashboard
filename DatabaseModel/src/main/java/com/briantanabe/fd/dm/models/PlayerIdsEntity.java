package com.briantanabe.fd.dm.models;

import javax.persistence.*;

/**
 * Created by Brian on 10/18/2014.
 */
@Entity
@Table(name = "PLAYER_IDS", schema = "PUBLIC", catalog = "FANTAYDB")
public class PlayerIdsEntity {
    private int id;
    private int espnId;
    private int numberFireId;
    private String name;

    protected PlayerIdsEntity(){}   // for hibernate:

    public PlayerIdsEntity(int espnId, int numberFireId, String name){
        this.espnId = espnId;
        this.numberFireId = numberFireId;
        this.name = name;
    }

    @Basic
    @Column(name = "ESPN_ID")
    public int getEspnId() {
        return espnId;
    }

    public void setEspnId(int espnId) {
        this.espnId = espnId;
    }

    @Basic
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

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerIdsEntity that = (PlayerIdsEntity) o;

        if (espnId != that.espnId) return false;
        if (id != that.id) return false;
        if (numberFireId != that.numberFireId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = espnId;
        result = 31 * result + numberFireId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}

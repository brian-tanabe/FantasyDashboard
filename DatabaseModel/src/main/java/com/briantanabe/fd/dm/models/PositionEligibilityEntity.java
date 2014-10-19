package com.briantanabe.fd.dm.models;

import javax.persistence.*;

/**
 * Created by Brian on 10/18/2014.
 */
@Entity
@Table(name = "POSITION_ELIGIBILITY", schema = "PUBLIC", catalog = "FANTAYDB")
public class PositionEligibilityEntity {
    private int id;
    private int playerId;
    private String position;

    protected PositionEligibilityEntity(){}     // for hibernate

    public PositionEligibilityEntity(int playerId, String positionString){
        this.playerId = playerId;
        this.position = positionString;
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

    @Basic
    @Column(name = "PLAYER_ID")
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    @Basic
    @Column(name = "POSITION")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PositionEligibilityEntity that = (PositionEligibilityEntity) o;

        if (id != that.id) return false;
        if (playerId != that.playerId) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + playerId;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }
}

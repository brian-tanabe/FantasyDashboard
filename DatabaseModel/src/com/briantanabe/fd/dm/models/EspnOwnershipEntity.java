package com.briantanabe.fd.dm.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Brian on 10/15/2014.
 */
@Entity
@Table(name = "ESPN_OWNERSHIP", schema = "PUBLIC", catalog = "FANTASYDATABASE")
public class EspnOwnershipEntity {
    private int espnId;
    private int leagueId;
    private int ownerId;

    @Basic
    @Column(name = "ESPN_ID")
    public int getEspnId() {
        return espnId;
    }

    public void setEspnId(int espnId) {
        this.espnId = espnId;
    }

    @Basic
    @Column(name = "LEAGUE_ID")
    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    @Basic
    @Column(name = "OWNER_ID")
    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EspnOwnershipEntity that = (EspnOwnershipEntity) o;

        if (espnId != that.espnId) return false;
        if (leagueId != that.leagueId) return false;
        if (ownerId != that.ownerId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = espnId;
        result = 31 * result + leagueId;
        result = 31 * result + ownerId;
        return result;
    }
}

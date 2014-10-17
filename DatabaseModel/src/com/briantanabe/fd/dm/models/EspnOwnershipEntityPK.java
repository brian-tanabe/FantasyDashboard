package com.briantanabe.fd.dm.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Brian on 10/16/2014.
 */
public class EspnOwnershipEntityPK implements Serializable {
    private int espnId;
    private int leagueId;
    private int teamId;

    @Column(name = "ESPN_ID")
    @Id
    public int getEspnId() {
        return espnId;
    }

    public void setEspnId(int espnId) {
        this.espnId = espnId;
    }

    @Column(name = "LEAGUE_ID")
    @Id
    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    @Column(name = "TEAM_ID")
    @Id
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EspnOwnershipEntityPK that = (EspnOwnershipEntityPK) o;

        if (espnId != that.espnId) return false;
        if (leagueId != that.leagueId) return false;
        if (teamId != that.teamId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = espnId;
        result = 31 * result + leagueId;
        result = 31 * result + teamId;
        return result;
    }
}

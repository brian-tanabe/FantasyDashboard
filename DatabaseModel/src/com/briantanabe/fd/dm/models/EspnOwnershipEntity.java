package com.briantanabe.fd.dm.models;

import javax.persistence.*;

/**
 * Created by Brian on 10/16/2014.
 */
@Entity
@Table(name = "ESPN_OWNERSHIP", schema = "PUBLIC", catalog = "FANTASYDATABASE")
@IdClass(EspnOwnershipEntityPK.class)
public class EspnOwnershipEntity {
    private int espnId;
    private int leagueId;
    private int teamId;

    protected EspnOwnershipEntity(){}

    public EspnOwnershipEntity(int espnId, int leagueId, int teamId){
        this.espnId = espnId;
        this.leagueId = leagueId;
        this.teamId = teamId;
    }

    @Id
    @Column(name = "ESPN_ID")
    public int getEspnId() {
        return espnId;
    }

    public void setEspnId(int espnId) {
        this.espnId = espnId;
    }

    @Id
    @Column(name = "LEAGUE_ID")
    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    @Id
    @Column(name = "TEAM_ID")
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

        EspnOwnershipEntity that = (EspnOwnershipEntity) o;

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

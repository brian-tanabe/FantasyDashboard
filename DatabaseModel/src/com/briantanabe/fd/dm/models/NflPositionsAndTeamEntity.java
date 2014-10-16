package com.briantanabe.fd.dm.models;

import javax.persistence.*;

/**
 * Created by Brian on 10/15/2014.
 */
@Entity
@Table(name = "NFL_POSITIONS_AND_TEAM", schema = "PUBLIC", catalog = "FANTASYDATABASE")
public class NflPositionsAndTeamEntity {
    private int espnId;
    private String nflTeam;
    private String position;

    @Id
    @Column(name = "ESPN_ID")
    public int getEspnId() {
        return espnId;
    }

    public void setEspnId(int espnId) {
        this.espnId = espnId;
    }

    @Basic
    @Column(name = "NFL_TEAM")
    public String getNflTeam() {
        return nflTeam;
    }

    public void setNflTeam(String nflTeam) {
        this.nflTeam = nflTeam;
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

        NflPositionsAndTeamEntity that = (NflPositionsAndTeamEntity) o;

        if (espnId != that.espnId) return false;
        if (nflTeam != null ? !nflTeam.equals(that.nflTeam) : that.nflTeam != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = espnId;
        result = 31 * result + (nflTeam != null ? nflTeam.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }
}

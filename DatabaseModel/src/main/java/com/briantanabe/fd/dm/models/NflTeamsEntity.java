package com.briantanabe.fd.dm.models;

import javax.persistence.*;

/**
 * Created by Brian on 10/17/2014.
 */
@Entity
@Table(name = "NFL_TEAMS", schema = "PUBLIC", catalog = "FANTAYDB")
public class NflTeamsEntity {
    private int id;
    private String abbreviation;
    private String location;
    private String teamName;

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
    @Column(name = "ABBREVIATION")
    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Basic
    @Column(name = "LOCATION")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "TEAM_NAME")
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NflTeamsEntity that = (NflTeamsEntity) o;

        if (id != that.id) return false;
        if (abbreviation != null ? !abbreviation.equals(that.abbreviation) : that.abbreviation != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (teamName != null ? !teamName.equals(that.teamName) : that.teamName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (abbreviation != null ? abbreviation.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (teamName != null ? teamName.hashCode() : 0);
        return result;
    }
}

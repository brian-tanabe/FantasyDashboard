package com.briantanabe.fd.dm.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Created by Brian on 10/18/2014.
 */
@Entity
@Table(name = "PLAYER_PROFILE", schema = "PUBLIC", catalog = "FANTAYDB")
public class PlayerProfileEntity {
    private int playerId;
    private int nflTeamId;
    private Date birthday;
    private Integer experience;

    @OneToMany
    @JoinTable(name = "POSITION_ELIGIBILITY", joinColumns = @JoinColumn(name = "PLAYER_ID"))
    private Set<String> positionEligibility;

    protected PlayerProfileEntity() {}  // for hibernate

    public PlayerProfileEntity(int playerId, int nflTeamId){
        this.playerId = playerId;
        this.nflTeamId = nflTeamId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAYER_ID")
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    @Basic
    @Column(name = "NFL_TEAM_ID")
    public int getNflTeamId() {
        return nflTeamId;
    }

    public void setNflTeamId(int nflTeamId) {
        this.nflTeamId = nflTeamId;
    }

    @Basic
    @Column(name = "BIRTHDAY")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "EXPERIENCE")
    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }


    public Set<String> getEligiblePositions(){
        return positionEligibility;
    }

    public void getEligiblePositions(Set<String> positionEligibility){
        this.positionEligibility = positionEligibility;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerProfileEntity that = (PlayerProfileEntity) o;

        if (nflTeamId != that.nflTeamId) return false;
        if (playerId != that.playerId) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (experience != null ? !experience.equals(that.experience) : that.experience != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = playerId;
        result = 31 * result + nflTeamId;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (experience != null ? experience.hashCode() : 0);
        return result;
    }
}

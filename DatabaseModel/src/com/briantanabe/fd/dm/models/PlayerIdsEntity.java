package com.briantanabe.fd.dm.models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Brian on 10/15/2014.
 */
@Entity
@Table(name = "PLAYER_IDS", schema = "PUBLIC", catalog = "FANTASYDATABASE")
@IdClass(PlayerIdsEntityPK.class)
public class PlayerIdsEntity {
    private String name;
    private int espnId;
    private int numberFireId;

    private NumberFireCurrentWeekProjectionsEntity playersNumberFireCurrentWeekProjection;
    private NumberFireRemainingSeasonProjectionsEntity playersNumberFireRemainingSeasonProjection;

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "NUMBER_FIRE_ID")
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

        PlayerIdsEntity that = (PlayerIdsEntity) o;

        if (espnId != that.espnId) return false;
        if (numberFireId != that.numberFireId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + espnId;
        result = 31 * result + numberFireId;
        return result;
    }

    @OneToOne
    public NumberFireCurrentWeekProjectionsEntity getPlayersNumberFireCurrentWeekProjection() {
        return playersNumberFireCurrentWeekProjection;
    }

    public void setPlayersNumberFireCurrentWeekProjection(NumberFireCurrentWeekProjectionsEntity nfRemainingSeasonOtO) {
        this.playersNumberFireCurrentWeekProjection = nfRemainingSeasonOtO;
    }

    @OneToOne
    public NumberFireRemainingSeasonProjectionsEntity getPlayersNumberFireRemainingSeasonProjection() {
        return playersNumberFireRemainingSeasonProjection;
    }

    public void setPlayersNumberFireRemainingSeasonProjection(NumberFireRemainingSeasonProjectionsEntity getNumberFireRemainingSeasonProjection) {
        this.playersNumberFireRemainingSeasonProjection = getNumberFireRemainingSeasonProjection;
    }

    private List<EspnOwnershipEntity> playersEspnOwnershipInfo;

    @OneToMany
    public List<EspnOwnershipEntity> getPlayersEspnOwnershipInfo() {
        return playersEspnOwnershipInfo;
    }

    public void setPlayersEspnOwnershipInfo(List<EspnOwnershipEntity> playersEspnOwnershipInfo) {
        this.playersEspnOwnershipInfo = playersEspnOwnershipInfo;
    }

    private NflPositionsAndTeamEntity playersPositionAndTeam;

    @OneToOne
    public NflPositionsAndTeamEntity getPlayersPositionAndTeam() {
        return playersPositionAndTeam;
    }

    public void setPlayersPositionAndTeam(NflPositionsAndTeamEntity playersPositionAndTeam) {
        this.playersPositionAndTeam = playersPositionAndTeam;
    }
}

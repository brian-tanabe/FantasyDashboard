package com.briantanabe.fd.dp.fantasy.player;

import com.briantanabe.fd.dp.nfl.position.Position;
import com.briantanabe.fd.dp.nfl.team.NflTeam;

import javax.persistence.Entity;
import java.util.Set;

/**
 * Created by btanabe on 10/13/2014.
 */
@Entity
public class PlayerSearchTableRow {
    protected int id;   // for hibernate
    protected NflPlayerPositionAndTeam nflPlayerPositionAndTeam;
    protected NumberFireCurrentWeekProjection numberFireCurrentWeekProjection;
    protected NumberFireRemainingSeasonProjection numberFireRemainingSeasonProjection;

    public String getName(){
        return nflPlayerPositionAndTeam.getName();
    }

    public NflTeam getNflTeam(){
        return nflPlayerPositionAndTeam.getNflTeam();
    }

    public Set<Position> getPositionEligibility(){
        return nflPlayerPositionAndTeam.getPositionEligibility();
    }

    public double getNumberFireCurrentWeekFirePoints(){
        return numberFireCurrentWeekProjection.getFirePoints();
    }

    public double getNumberFireRemainingSeasonFirePoints(){
        return numberFireRemainingSeasonProjection.getFirePoints();
    }

    protected NflPlayerPositionAndTeam getNflPlayerPositionAndTeam() {
        return nflPlayerPositionAndTeam;
    }

    // for hibernate
    protected void setId(int id){
        this.id = id;
    }

    // for hibernate
    protected int getId(){
        return id;
    }

    protected void setNflPlayerPositionAndTeam(NflPlayerPositionAndTeam nflPlayerPositionAndTeam) {
        this.nflPlayerPositionAndTeam = nflPlayerPositionAndTeam;
    }

    protected NumberFireCurrentWeekProjection getNumberFireCurrentWeekProjection() {
        return numberFireCurrentWeekProjection;
    }

    protected void setNumberFireCurrentWeekProjection(NumberFireCurrentWeekProjection numberFireCurrentWeekProjection) {
        this.numberFireCurrentWeekProjection = numberFireCurrentWeekProjection;
    }

    protected NumberFireRemainingSeasonProjection getNumberFireRemainingSeasonProjection() {
        return numberFireRemainingSeasonProjection;
    }

    protected void setNumberFireRemainingSeasonProjection(NumberFireRemainingSeasonProjection numberFireRemainingSeasonProjection) {
        this.numberFireRemainingSeasonProjection = numberFireRemainingSeasonProjection;
    }
}

package com.briantanabe.fd.dp.fantasy.player;

import com.briantanabe.fd.dp.nfl.position.Position;
import com.briantanabe.fd.dp.nfl.team.NflTeam;

import javax.persistence.Entity;
import java.util.EnumSet;

/**
 * Created by Brian on 10/13/14.
 */
@Entity
public class NflPlayerPositionAndTeam extends NflPlayer {
    private NflTeam nflTeam;
    private EnumSet<Position> positions = EnumSet.noneOf(Position.class);

    protected NflPlayerPositionAndTeam() {}     // for hibernate

    public NflPlayerPositionAndTeam(String name, int espnId, NflTeam nflTeam, EnumSet<Position> positions){
        setName(name);
        setEspnPlayerId(espnId);
        this.nflTeam = nflTeam;
        this.positions = positions;
    }

    public void setNflTeam(NflTeam nflTeam){
        this.nflTeam = nflTeam;
    }

    public void setPositions(EnumSet<Position> positions) {
        this.positions = positions;
    }

    public NflTeam getNflTeam(){
        return nflTeam;
    }

    public EnumSet<Position> getPositions() {
        return positions;
    }


    @Override
    public String toString() {
        return String.format("name=[%s]; espnId=[%d]; team=[%s]; position=%s", getName(), getEspnPlayerId(), getNflTeam(), getPositions());
    }
}

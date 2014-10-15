package com.briantanabe.fd.dp.fantasy.player;

import com.briantanabe.fd.dp.nfl.position.Position;
import com.briantanabe.fd.dp.nfl.team.NflTeam;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Brian on 10/13/14.
 */
@Entity
public class NflPlayerPositionAndTeam extends NflPlayer {
    private int id;
    private NflTeam nflTeam;
    private Set<Position> positions = new HashSet<>(2);

    protected NflPlayerPositionAndTeam() {}     // for hibernate

    public NflPlayerPositionAndTeam(String name, int espnId, NflTeam nflTeam, List<Position> positions){
        setName(name);
        setEspnPlayerId(espnId);
        this.nflTeam = nflTeam;
        this.positions = new HashSet<>(positions);
    }

    public void setNflTeam(NflTeam nflTeam){
        this.nflTeam = nflTeam;
    }

    public void setPositions(Set<Position> positions) {
        this.positions = positions;
    }

    public NflTeam getNflTeam(){
        return nflTeam;
    }

    public Set<Position> getPositions() {
        return positions;
    }


    @Override
    public String toString() {
        return String.format("name=[%s]; espnId=[%d]; team=[%s]; position=%s", getName(), getEspnPlayerId(), getNflTeam(), getPositions());
    }
}

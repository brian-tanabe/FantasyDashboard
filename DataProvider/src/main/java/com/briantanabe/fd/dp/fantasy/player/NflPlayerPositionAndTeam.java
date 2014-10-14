package com.briantanabe.fd.dp.fantasy.player;

import com.briantanabe.fd.dp.nfl.position.Position;
import com.briantanabe.fd.dp.nfl.team.NflTeam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brian on 10/13/14.
 */
public class NflPlayerPositionAndTeam extends NflPlayer {
    private NflTeam nflTeam;
    private List<Position> positions = new ArrayList<Position>(2);

    public NflPlayerPositionAndTeam(String name, int espnId, NflTeam nflTeam, List<Position> positions){
        setName(name);
        setEspnPlayerId(espnId);
        this.nflTeam = nflTeam;
        this.positions = positions;
    }

    public void setNflTeam(NflTeam nflTeam){
        this.nflTeam = nflTeam;
    }

    public void setEligiblePositions(List<Position> positions){
        this.positions = positions;
    }

    public List<Position> getAllEligiblePositions(){
        return positions;
    }

    public NflTeam getNflTeam(){
        return nflTeam;
    }
}

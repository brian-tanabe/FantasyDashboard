package com.briantanabe.fd.dp.fantasy.player;

import com.briantanabe.fd.dp.nfl.position.Position;
import com.briantanabe.fd.dp.nfl.position.PositionFactory;
import com.briantanabe.fd.dp.nfl.team.NflTeam;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Brian on 10/13/14.
 */
public class NflPlayerPositionAndTeam extends NflPlayer {
    private NflTeam nflTeam;
    private Set<Position> positionEligibility = new HashSet<Position>(2);
    private Set<String> positionSet = new HashSet<>(2);     // for hibernate

    protected NflPlayerPositionAndTeam() {}     // for hibernate

    public NflPlayerPositionAndTeam(String name, int espnId, NflTeam nflTeam, Set<Position> positions){
        setName(name);
        setEspnPlayerId(espnId);
        this.nflTeam = nflTeam;
        setPositionSetStrings(positions);
    }

    public void setNflTeam(NflTeam nflTeam){
        this.nflTeam = nflTeam;
    }

    public void setPositionSet(Set<String> positionSet) {
        this.positionSet = positionSet;
        for(String positionString : positionSet){
            positionEligibility.add(PositionFactory.whatPosition(positionString));
        }
    }

    public NflTeam getNflTeam(){
        return nflTeam;
    }

    public Set<Position> getPositionEligibility() {
        return positionEligibility;
    }

    // for hibernate:
    protected Set<String> getPositionSet(){
        return positionSet;
    }

    protected void setPositionSetStrings(Set<Position> positions){
        positionEligibility = positions;
        for(Position position : positions){
            positionSet.add(position.toString());
        }
    }

    @Override
    public String toString() {
        return String.format("name=[%s]; espnId=[%d]; team=[%s]; position=%s", getName(), getEspnPlayerId(), getNflTeam(), getPositionSet());
    }
}

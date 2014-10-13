package com.briantanabe.fd.ap.events;

import com.briantanabe.fd.dp.fantasy.player.NflPlayer;

import java.util.List;

/**
 * Created by btanabe on 10/13/2014.
 */
public class AllPlayerIdsEvent {
    private List<NflPlayer> allNflPlayerIds;

    public void setAllNflPlayerIds(List<NflPlayer> allNflPlayerIds){
        this.allNflPlayerIds = allNflPlayerIds;
    }

    public List<NflPlayer> getAllNflPlayerIds(){
        return allNflPlayerIds;
    }
}

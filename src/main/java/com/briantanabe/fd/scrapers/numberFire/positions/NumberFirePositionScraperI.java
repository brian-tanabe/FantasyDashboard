package com.briantanabe.fd.scrapers.numberFire.positions;

import com.briantanabe.fd.fantasy.player.NumberFireRanking;
import org.jsoup.nodes.Document;

import java.util.ArrayList;

/**
 * Created by Brian on 9/24/14.
 */
public interface NumberFirePositionScraperI {
    public ArrayList<NumberFireRanking> getPlayerRankings(Document document);
}

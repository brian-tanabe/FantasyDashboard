package com.briantanabe.fd.dp.scrapers.numberFire.positions;

import com.briantanabe.fd.dp.fantasy.player.NumberFireProjection;
import org.jsoup.nodes.Document;

import java.util.ArrayList;

/**
 * Created by Brian on 9/24/14.
 */
public interface NumberFirePositionScraperI {
    public <T extends NumberFireProjection> ArrayList<T> getPlayerRankings(Document document);
}

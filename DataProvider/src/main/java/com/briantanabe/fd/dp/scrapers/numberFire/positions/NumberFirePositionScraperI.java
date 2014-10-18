package com.briantanabe.fd.dp.scrapers.numberFire.positions;

import com.briantanabe.fd.dp.fantasy.player.NumberFireProjection;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by Brian on 9/24/14.
 */
public interface NumberFirePositionScraperI {
    public void scrape(Document document);
    public List getPlayerProjections();

    @Deprecated
    public <T extends NumberFireProjection> List<T> getPlayerRankings(Document document);
}

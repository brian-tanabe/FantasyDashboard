package com.briantanabe.fd.scrapers.numberFire;

import com.briantanabe.fd.fantasy.player.NumberFireRanking;
import com.briantanabe.fd.web.WebRequest;

import java.util.ArrayList;

/**
 * Created by BTanabe on 9/26/2014.
 */
public class NumberFireScraper {
    private WebRequest webRequest;

    public NumberFireScraper(WebRequest webRequest){
        this.webRequest = webRequest;
    }

    public ArrayList<NumberFireRanking> scrape(){
        ArrayList<NumberFireRanking> players = new ArrayList<NumberFireRanking>(2000);
        return players;
    }
}

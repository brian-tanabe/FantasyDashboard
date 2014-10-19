package com.briantanabe.fd.dm;

import com.briantanabe.fd.dm.datastore.DatabaseInterface;
import com.briantanabe.fd.dm.models.PlayerIdsEntity;
import com.briantanabe.fd.dm.models.PlayerProfileEntity;
import com.briantanabe.fd.dm.models.PositionEligibilityEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Brian on 10/17/2014.
 */
public class Main {

    public static void main(final String[] args) throws Exception {
        final Session session = DatabaseInterface.startSession();
        try {
            PlayerIdsEntity brianTanabe = new PlayerIdsEntity(123, 1123, "Brian Tanabe");
            PlayerIdsEntity kristinTanabe = new PlayerIdsEntity(124, 1124, "Kristin Tanabe");

            session.saveOrUpdate(brianTanabe);
            session.saveOrUpdate(kristinTanabe);

            PositionEligibilityEntity brianKicker = new PositionEligibilityEntity(brianTanabe.getId(), "K");
            PositionEligibilityEntity kristinPositionEligibility = new PositionEligibilityEntity(kristinTanabe.getId(), "K");
            PositionEligibilityEntity kristinPositionEligibilityTwo = new PositionEligibilityEntity(kristinTanabe.getId(), "TE");

            session.saveOrUpdate(brianKicker);
            session.saveOrUpdate(kristinPositionEligibility);
            session.saveOrUpdate(kristinPositionEligibilityTwo);

            PlayerProfileEntity briansPlayerProfile = new PlayerProfileEntity(brianTanabe.getId(), 1);
            PlayerProfileEntity kristinsPlayerProfile = new PlayerProfileEntity(kristinTanabe.getId(), 17);

            session.saveOrUpdate(briansPlayerProfile);
            session.saveOrUpdate(kristinsPlayerProfile);

            Query query = session.createQuery("from PlayerIdsEntity");
            List<PlayerIdsEntity> players = query.list();
            System.out.println("number of players: " + players.size());
            for(PlayerIdsEntity player : players){
                System.out.println(String.format("id=[%d]; name=[%s]; espn_id=[%d]; nfId=[%d]", player.getId(),player.getName(), player.getEspnId(), player.getNumberFireId()));
            }

            query = session.createQuery("from PositionEligibilityEntity");
            List<PositionEligibilityEntity> positions = query.list();
            System.out.println("number of positionEligibilities: " + positions.size());
            for(PositionEligibilityEntity position : positions){
                System.out.println(String.format("player_id=[%d]; position=[%s]", position.getPlayerId(), position.getPosition()));
            }

            query = session.createQuery("from PlayerProfileEntity");
            List<PlayerProfileEntity> playerProfiles = query.list();
            for(PlayerProfileEntity profiles : playerProfiles){
                System.out.println(String.format("player_id=[%d]; nflTeam=[%s]; positions=[%s]", profiles.getPlayerId(), profiles.getNflTeamId(), profiles.getEligiblePositions()));
            }
        } finally {
            session.close();
        }

//        try {
//        System.out.println("querying all the managed entities...");
//        final Map metadataMap = session.getSessionFactory().getAllClassMetadata();
//        for (Object key : metadataMap.keySet()) {
//            final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get(key);
//            final String entityName = classMetadata.getEntityName();
//            final Query query = session.createQuery("from " + entityName);
//            System.out.println("executing: " + query.getQueryString());
//            for (Object o : query.list()) {
//                System.out.println("  " + o);
//            }
//        }
//        } finally {
//            session.close();
//        }
    }
}

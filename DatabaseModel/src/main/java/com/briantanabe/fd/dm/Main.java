package com.briantanabe.fd.dm;

import com.briantanabe.fd.dm.datastore.DatabaseInterface;
import com.briantanabe.fd.dm.models.PlayerIdsEntity;
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
            Query query = session.createQuery("from com.briantanabe.fd.dm.models.PlayerIdsEntity");
            List<PlayerIdsEntity> players = query.list();

            System.out.println("number of players: " + players.size());
            for(PlayerIdsEntity player : players){
                System.out.println(String.format("name=[%s]; espn_id=[%d]; nfId=[%d]", player.getName(), player.getEspnId(), player.getNumberFireId()));
            }
        } finally {
            session.close();
        }

//        try {
//            System.out.println("querying all the managed entities...");
//            final Map metadataMap = session.getSessionFactory().getAllClassMetadata();
//            for (Object key : metadataMap.keySet()) {
//                final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get(key);
//                final String entityName = classMetadata.getEntityName();
//                final Query query = session.createQuery("from " + entityName);
//                System.out.println("executing: " + query.getQueryString());
//                for (Object o : query.list()) {
//                    System.out.println("  " + o);
//                }
//            }
//        } finally {
//            session.close();
//        }
    }
}

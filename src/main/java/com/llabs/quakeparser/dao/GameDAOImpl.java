package com.llabs.quakeparser.dao;

import com.llabs.quakeparser.dao.model.GameEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class GameDAOImpl implements IGameDAO {

    private final static String GET_ALL = " from Game as g ORDER BY g.id";

    private final static String WHERE_KEY_STRING = "<WHERE_CLAUSE> ";
    private final static String WHERE_CLAUSE     = "where g.id = :gameId AND k.player not in (:players) ";
    private final static String GET_TOTAL_KILLS =
            "select new map (g.id as gameId, count(k.id) as count) " +
                    "from Game g " +
                    "left join g.kills k " +
                    WHERE_CLAUSE +
                    "group by g.id ";
    private final static String GET_TOTAL_KILLS_BY_PLAYER =
            "select  new map (g.id as gameId, k.player as playerName, count(k.id) as count) " +
                    "from Game g " +
                    "left join g.kills k " +
                    WHERE_CLAUSE +
                    "group by g.id, k.player ";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(GameEntity game) {
        entityManager.persist(game);
    }

    @Override
    public void add(List<GameEntity> games) {
        for (GameEntity game : games) {
            add(game);
        }
    }

    @Override
    public List<GameEntity> list() {
        return entityManager.
                createQuery(GET_ALL).
                getResultList();
    }

    @Override
    public GameEntity find(Integer gameId) {
        return entityManager.find(GameEntity.class, gameId);
    }

    @Override
    public List<Map<Object, Object>> getTotalKills(Integer gameId, List<String> undesirablePlayers) {
        return getTotal(GET_TOTAL_KILLS, gameId, undesirablePlayers);
    }

    @Override
    public List<Map<Object, Object>> getTotalKillsByPlayer(Integer gameId, List<String> undesirablePlayers) {
        return  getTotal(GET_TOTAL_KILLS_BY_PLAYER, gameId, undesirablePlayers);
    }

    /**
     * Brings a result set as a list of maps with key -> values
     * @param q The String query to be performed
     * @param gameId The game Id
     * @param undesirablePlayers A list of players to
     */
    private List<Map<Object, Object>> getTotal(String q, Integer gameId, List<String> undesirablePlayers) {
        String queryClause = (gameId != null) ? WHERE_CLAUSE : "";
        q = q.replace(WHERE_KEY_STRING, queryClause);

        Query query = entityManager.createQuery(q);
        if (gameId != null) {
            query.setParameter("gameId", gameId);
            query.setParameter("players", undesirablePlayers);
        }
        return  query.getResultList();
    }

}

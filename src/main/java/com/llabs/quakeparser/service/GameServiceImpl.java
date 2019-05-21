package com.llabs.quakeparser.service;

import com.llabs.quakeparser.web.model.mapper.GameEntityMapper;
import com.llabs.quakeparser.dao.model.GameEntity;
import com.llabs.quakeparser.dao.IGameDAO;
import com.llabs.quakeparser.web.model.GameStatisticsViewModel;
import com.llabs.quakeparser.web.model.GameViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class GameServiceImpl implements IGameService {

    protected final static List<String> EMPTY_LIST = new ArrayList();
    protected final static List<String> UNDESIRABLE_PLAYERS = new ArrayList(Arrays.asList("<world>"));
    private static Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

    @Autowired
    private IGameDAO gameDAO;

    @Override
    public void create(GameViewModel game) {
        GameEntity entity = GameEntityMapper.from(game);
        gameDAO.add(entity);
    }

    @Override
    public void create(List<GameViewModel> games) {
        for (GameViewModel viewModel : games) {
            create(viewModel);
        }
    }

    @Override
    public List<GameViewModel> list() {
        List<GameViewModel> list = new ArrayList<>();
        List<GameEntity> entities = gameDAO.list();

        for (GameEntity entity : entities)  {
            list.add(GameEntityMapper.from(entity));
        }
        return list;
    }


    @Override
    /**
     * gets a
     */
    public GameStatisticsViewModel getById(Integer gameId) {

        GameStatisticsViewModel viewModel = null;

        GameEntity entity = gameDAO.find(gameId);
        if (entity != null) {
            viewModel = getByEntity(entity);
        }

        return viewModel;
    }

    @Override
    public List<GameStatisticsViewModel> getStatistics() {

        List<GameEntity> games = gameDAO.list();
        List<GameStatisticsViewModel> list = new ArrayList<>();

        for (GameEntity game : games) {
            list.add(getByEntity(game));
        }
        return list;
    }

    /**
     * gets a
     */
    private GameStatisticsViewModel getByEntity(GameEntity entity) {

        GameStatisticsViewModel viewModel = null;

        List<Map<Object, Object>> total = gameDAO.getTotalKills(entity.getId(), EMPTY_LIST);
        Long totalKills = 0L;
        if (total != null) {
            totalKills = (Long) total.get(0).get("count");
        }
        List<Map<Object, Object>> kills =
                gameDAO.getTotalKillsByPlayer(entity.getId(), UNDESIRABLE_PLAYERS);
        viewModel = GameEntityMapper.from(entity, totalKills, kills);

        return viewModel;
    }


}

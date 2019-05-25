package com.llabs.quakeparser.web.model.mapper;

import com.llabs.quakeparser.dao.model.GameEntity;
import com.llabs.quakeparser.dao.model.KillEntity;
import com.llabs.quakeparser.web.model.GameStatisticsViewModel;
import com.llabs.quakeparser.web.model.GameViewModel;
import com.llabs.quakeparser.web.model.KillCountByPlayerViewModel;
import com.llabs.quakeparser.web.model.KillViewModel;

import java.util.List;
import java.util.Map;

public class GameEntityMapper {

    /**
     * Creates an entity from a view
     * @param viewModel a viewModel
     * @return an entity
     */
    public static GameEntity from(GameViewModel viewModel) {
        GameEntity entity = new GameEntity();
        entity.setId(viewModel.getId());
        entity.setStart(viewModel.getStart());
        entity.setFinish(viewModel.getFinish());
        for (KillViewModel kill : viewModel.getKills()) {
            entity.addKill(KillEntityMapper.from(kill));
        }

        return entity;
    }

    /**
     * Creates a model from a entity
     * @param entity an entity
     * @return a view
     */
    public static GameViewModel from(GameEntity entity) {
        GameViewModel viewModel = new GameViewModel();
        viewModel.setId(entity.getId());
        viewModel.setStart(entity.getStart());
        viewModel.setFinish(entity.getFinish());
        for (KillEntity kill : entity.getKills()) {
            viewModel.getKills().add(KillEntityMapper.from(kill));
        }
        return viewModel;
    }

    /**
     * Creates a GameStatisticsViewModel from a entity, long and a map
     * @param entity an entity
     * @param totalKills the total number of kills
     * @param killCountByPlayer a list of maps containing the player's name and kill count
     * @return a view
     */
    public static GameStatisticsViewModel from(GameEntity entity,
                                               Long totalKills,
                                               List<Map<Object, Object>> killCountByPlayer)  {
        GameStatisticsViewModel viewModel = from(entity, totalKills);

        if (totalKills > 0) {
            for (Map<Object, Object> line : killCountByPlayer) {
                KillCountByPlayerViewModel killCount = KillCountByPlayerMapper.from(line);
                if (killCount.getName() != null) {
                    viewModel.getPlayers().add((String) line.get("playerName"));
                    viewModel.getKills().add(killCount);
                }
            }
        }
        return viewModel;
    }

    /**
     * Creates a GameStatisticsViewModel from a entity, long and a map
     * @param entity an entity
     * @param totalKills the total number of kills
     * @param killCountByPlayer a list of maps containing the player's name and kill count
     * @return a view
     */
    public static GameStatisticsViewModel from(GameEntity entity,
                                               Long totalKills,
                                               Map<String, Long> killCountByPlayer)  {
        GameStatisticsViewModel viewModel = from(entity, totalKills);

        for (Object key : killCountByPlayer.keySet()) {
            KillCountByPlayerViewModel killCount = new KillCountByPlayerViewModel();
            killCount.setName(key.toString());
            killCount.setCount(killCountByPlayer.get(key));
            viewModel.getKills().add(killCount);
        }
        return viewModel;
    }


    private static GameStatisticsViewModel from(GameEntity entity, Long totalKills) {
        GameStatisticsViewModel viewModel = new GameStatisticsViewModel();
        viewModel.setId(entity.getId());
        viewModel.setStart(entity.getStart());
        viewModel.setFinish(entity.getFinish());
        viewModel.setTotalKills(totalKills);

        return viewModel;
    }
}

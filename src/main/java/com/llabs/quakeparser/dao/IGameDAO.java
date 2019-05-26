package com.llabs.quakeparser.dao;

import com.llabs.quakeparser.dao.model.GameEntity;

import java.util.List;
import java.util.Map;

public interface IGameDAO {

    void add(GameEntity game);

    void add(List<GameEntity> games);

    List<GameEntity> list();

    GameEntity find(Integer gameId);

    List<Map<Object, Object>> getTotalKills(Integer gameId, List<String> undesirablePlayers);

    List<Map<Object, Object>> getTotalKillsByPlayer(Integer gameId, List<String> undesirablePlayers);

}

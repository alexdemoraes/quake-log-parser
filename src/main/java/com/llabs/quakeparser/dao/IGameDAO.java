package com.llabs.quakeparser.dao;

import com.llabs.quakeparser.dao.model.GameEntity;

import java.util.List;
import java.util.Map;

public interface IGameDAO {

    public void add(GameEntity game);

    public void add(List<GameEntity> games);

    public List<GameEntity> list();

    public GameEntity find(Integer gameId);

    public List<Map<Object, Object>> getTotalKills(Integer gameId, List<String> undesirablePlayers);

    public List<Map<Object, Object>> getTotalKillsByPlayer(Integer gameId, List<String> undesirablePlayers);


}

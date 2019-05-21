package com.llabs.quakeparser;

import com.llabs.quakeparser.dao.model.GameEntity;
import com.llabs.quakeparser.dao.model.KillEntity;
import com.llabs.quakeparser.web.model.GameStatisticsViewModel;
import com.llabs.quakeparser.web.model.GameViewModel;
import com.llabs.quakeparser.web.model.KillCountByPlayerViewModel;
import com.llabs.quakeparser.web.model.KillViewModel;

import java.util.*;

public class Utils {

    public static final String JASON_PLAYER = "Jason";
    public static final String FRED_PLAYER = "Fred";
    public static final String WORLD_PLAYER = "<world";
    public static final String KENNY_PLAYER = "Kenny";
    public static final String ALEX_PLAYER = "Alex";

    public static final String PUNCH = "Punch";
    public static final String GUNSHOT = "Gunshot";

    public static final String ID = "id";
    public static final String PLAYER_NAME = "playerName";
    public static final String KILL_COUNT = "count";

    /* game view models */

    public GameViewModel gameViewModel1() {
        GameViewModel viewModel = new GameViewModel();
        viewModel.setId(1);
        viewModel.setStart("0:00");
        viewModel.setFinish("3:14");
        viewModel.setKills(killsModelList1());
        return viewModel;
    }

    public GameViewModel gameViewModel2() {
        GameViewModel viewModel = new GameViewModel();
        viewModel.setId(2);
        viewModel.setStart("0:00");
        viewModel.setFinish("3:14");
        viewModel.setKills(killsModelList2());
        return viewModel;
    }

    /* game view model list */

    public List<GameViewModel> gamesModelList1() {
        List<GameViewModel> list = new ArrayList<>();
        list.add(gameViewModel1());
        return list;
    }


    public List<GameViewModel> gamesModelList2() {
        List<GameViewModel> list = new ArrayList<>();
        list.add(gameViewModel1());
        list.add(gameViewModel2());
        return list;
    }


    /* kill view models */
    public KillViewModel killViewModel1() {
        KillViewModel viewModel = new KillViewModel();
        viewModel.setId(1);
        viewModel.setPlayer(JASON_PLAYER);
        viewModel.setKilled(KENNY_PLAYER);
        viewModel.setMode(GUNSHOT);
        return viewModel;
    }

    public KillViewModel killViewModel2() {
        KillViewModel viewModel = new KillViewModel();
        viewModel.setId(2);
        viewModel.setPlayer(WORLD_PLAYER);
        viewModel.setKilled(ALEX_PLAYER);
        viewModel.setMode(PUNCH);
        return viewModel;
    }

    /* kill view model lists */
    public List<KillViewModel> killsModelList1() {
        List<KillViewModel> list = new ArrayList<>();
        list.add(killViewModel1());
        return list;
    }

    public List<KillViewModel> killsModelList2() {
        List<KillViewModel> list = new ArrayList<>();
        list.add(killViewModel1());
        list.add(killViewModel2());
        return list;
    }


    /* Statistics helpers */

    /* game statistics helper */
    public GameStatisticsViewModel gameStatisticsViewModel1() {
        GameStatisticsViewModel viewModel = new GameStatisticsViewModel();
        viewModel.setId(1);
        viewModel.setStart("0:00");
        viewModel.setFinish("3:14");
        viewModel.setTotalKills(10L);
        viewModel.setPlayers(
                new ArrayList<>(Arrays.asList(JASON_PLAYER)));
        viewModel.setKills(killCountsModelList1());
        return viewModel;
    }

    public GameStatisticsViewModel gameStatisticsViewModel2() {
        GameStatisticsViewModel viewModel = new GameStatisticsViewModel();
        viewModel.setId(2);
        viewModel.setStart("0:00");
        viewModel.setFinish("3:14");
        viewModel.setTotalKills(20L);
        viewModel.setPlayers(
                new ArrayList<>(Arrays.asList(JASON_PLAYER, FRED_PLAYER)));
        viewModel.setKills(killCountsModelList2());
        return viewModel;
    }

    public List<GameStatisticsViewModel> gameStatisticsModelList1() {
        List<GameStatisticsViewModel> list = new ArrayList<>();
        list.add(gameStatisticsViewModel1());
        return list;
    }


    public List<GameStatisticsViewModel> gameStatisticsModelList2() {
        List<GameStatisticsViewModel> list = new ArrayList<>();
        list.add(gameStatisticsViewModel1());
        list.add(gameStatisticsViewModel2());
        return list;
    }


    public KillCountByPlayerViewModel killCountViewModel1() {
        KillCountByPlayerViewModel viewModel = new KillCountByPlayerViewModel();
        viewModel.setName(JASON_PLAYER);
        viewModel.setCount(5L);
        return viewModel;
    }

    public KillCountByPlayerViewModel killCountViewModel2() {
        KillCountByPlayerViewModel viewModel = new KillCountByPlayerViewModel();
        viewModel.setName(FRED_PLAYER);
        viewModel.setCount(5L);
        return viewModel;
    }

    public List<KillCountByPlayerViewModel> killCountsModelList1() {
        List<KillCountByPlayerViewModel> list = new ArrayList<>();
        list.add(killCountViewModel1());
        return list;
    }

    public List<KillCountByPlayerViewModel> killCountsModelList2() {
        List<KillCountByPlayerViewModel> list = new ArrayList<>();
        list.add(killCountViewModel1());
        list.add(killCountViewModel2());
        return list;
    }

    /* entities */

    /* game entities */

    public GameEntity gameEntity1() {
        GameEntity entity = new GameEntity();
        entity.setId(1);
        entity.setStart("0:00");
        entity.setFinish("3:14");
        entity.setKills(killsEntityList1());
        return entity;
    }

    public GameEntity gameEntity2() {
        GameEntity entity = new GameEntity();
        entity.setId(2);
        entity.setStart("0:00");
        entity.setFinish("3:14");
        entity.setKills(killsEntityList2());
        return entity;
    }

    /* game entity list */

    public List<GameEntity> gamesEntityList1() {
        List<GameEntity> list = new ArrayList<>();
        list.add(gameEntity1());
        return list;
    }


    public List<GameEntity> gamesEntityList2() {
        List<GameEntity> list = new ArrayList<>();
        list.add(gameEntity1());
        list.add(gameEntity2());
        return list;
    }


    /* kill entities */
    public KillEntity killEntity1() {
        KillEntity entity = new KillEntity();
        entity.setId(1);
        entity.setPlayer(JASON_PLAYER);
        entity.setKilled(KENNY_PLAYER);
        entity.setMode(GUNSHOT);
        return entity;
    }

    public KillEntity killEntity2() {
        KillEntity entity = new KillEntity();
        entity.setId(2);
        entity.setPlayer(WORLD_PLAYER);
        entity.setKilled(ALEX_PLAYER);
        entity.setMode(PUNCH);
        return entity;
    }

    /* kill entity lists */
    public List<KillEntity> killsEntityList1() {
        List<KillEntity> list = new ArrayList<>();
        list.add(killEntity1());
        return list;
    }

    public List<KillEntity> killsEntityList2() {
        List<KillEntity> list = new ArrayList<>();
        list.add(killEntity1());
        list.add(killEntity2());
        return list;
    }







    /* Statistics helpers */

    /* game statistics helper */
//    public GameStatisticsViewModel gameStatisticsViewModel1() {
//        GameStatisticsViewModel viewModel = new GameStatisticsViewModel();
//        viewModel.setId(1);
//        viewModel.setStart("0:00");
//        viewModel.setFinish("3:14");
//        viewModel.setTotalKills(10L);
//        viewModel.setPlayers(
//                new ArrayList<>(Arrays.asList(JASON_PLAYER)));
//        viewModel.setKills(killCountsModelList1());
//        return viewModel;
//    }
//
//    public GameStatisticsViewModel gameStatisticsViewModel2() {
//        GameStatisticsViewModel viewModel = new GameStatisticsViewModel();
//        viewModel.setId(2);
//        viewModel.setStart("0:00");
//        viewModel.setFinish("3:14");
//        viewModel.setTotalKills(20L);
//        viewModel.setPlayers(
//                new ArrayList<>(Arrays.asList(JASON_PLAYER, FRED_PLAYER)));
//        viewModel.setKills(killCountsModelList2());
//        return viewModel;
//    }
//
//    public List<GameStatisticsViewModel> gameStatisticsModelList1() {
//        List<GameStatisticsViewModel> list = new ArrayList<>();
//        list.add(gameStatisticsViewModel1());
//        return list;
//    }
//
//
//    public List<GameStatisticsViewModel> gameStatisticsModelList2() {
//        List<GameStatisticsViewModel> list = new ArrayList<>();
//        list.add(gameStatisticsViewModel1());
//        list.add(gameStatisticsViewModel2());
//        return list;
//    }


    public Map<Object, Object> killCountMap1() {
        Map<Object, Object> map = new HashMap<>();
        map.put(ID, 1);
        map.put(PLAYER_NAME, JASON_PLAYER);
        map.put(KILL_COUNT, 5L);
        return map;
    }

    public Map<Object, Object> killCountMap2() {
        Map<Object, Object> map = new HashMap<>();
        map.put(ID, 2);
        map.put(PLAYER_NAME, FRED_PLAYER);
        map.put(KILL_COUNT, 5L);
        return map;
    }


    public List<Map<Object, Object>> killCountMapList1() {
        List<Map<Object, Object>> list = new ArrayList<>();
        list.add(killCountMap1());
        return list;
    }

    public List<Map<Object, Object>> killCountMapList2() {
        List<Map<Object, Object>> list = new ArrayList<>();
        list.add(killCountMap1());
        list.add(killCountMap2());
        return list;
    }



}

package com.llabs.quakeparser;

import com.llabs.quakeparser.web.model.GameStatisticsViewModel;
import com.llabs.quakeparser.web.model.GameViewModel;
import com.llabs.quakeparser.web.model.KillCountByPlayerViewModel;
import com.llabs.quakeparser.web.model.KillViewModel;
import org.springframework.boot.test.context.TestComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

    public static final String JASON_PLAYER = "Jason";
    public static final String FRED_PLAYER = "Fred";
    public static final String WORLD_PLAYER = "<world";
    public static final String KENNY_PLAYER = "Kenny";
    public static final String ALEX_PLAYER = "Alex";

    public static final String PUNCH = "Punch";
    public static final String GUNSHOT = "Gunshot";

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

}

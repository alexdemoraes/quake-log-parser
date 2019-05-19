package com.llabs.quakeparser.service;

import com.llabs.quakeparser.web.model.GameStatisticsViewModel;
import com.llabs.quakeparser.web.model.GameViewModel;

import java.util.List;

public interface IGameService {

    /* CRUD operations*/
    public List<GameViewModel> list();
    public void create(GameViewModel game);
    public void create(List<GameViewModel> games);



    /* Custom queries */
    public List<GameStatisticsViewModel> getStatistics();
    public GameStatisticsViewModel getById(Integer id);

}

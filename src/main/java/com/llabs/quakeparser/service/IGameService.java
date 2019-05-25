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

    /**
     * getStatistics()
     * returns a list of game statistics as {@link #getStatistics(Integer)}
     * @return a list
     */
    public List<GameStatisticsViewModel> getStatistics();
    /**
     * getStatistics(Integer gameId)
     * @return a game statistics object with all the players and the count of kills. WORLD kills
     * decreases the player counts, so that kill counts could be negative.
     *  eg. Alex has killed 5 times
     *      WORLD has killed Alex 2 times
     *      Alex count will be 2
     */
    public GameStatisticsViewModel getStatistics(Integer gameId);


    /* Custom queries */

    /**
     * getGameKillerStatistics()
     * returns a list of game killer statistics as {@link #getGameKillerStatistics(Integer)}
     * @return a list
     */
    public List<GameStatisticsViewModel> getGameKillerStatistics();
    /**
     * getGameKillerStatistics(Integer gameId)
     * @return a game statistics object with all the killers and the count of kills.
     */
    public GameStatisticsViewModel getGameKillerStatistics(Integer id);
}

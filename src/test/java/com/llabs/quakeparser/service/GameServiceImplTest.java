package com.llabs.quakeparser.service;

import com.llabs.quakeparser.Utils;
import com.llabs.quakeparser.dao.GameDAOImpl;
import com.llabs.quakeparser.dao.model.GameEntity;
import com.llabs.quakeparser.web.model.GameStatisticsViewModel;
import com.llabs.quakeparser.web.model.GameViewModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class GameServiceImplTest {

    @Mock
    private GameDAOImpl dao;

    @InjectMocks
    private GameServiceImpl gameService;

    private Utils utils;

    @Before
    public void init() {
        utils = new Utils();
    }

    @Test
    public void list() {
        List<GameEntity> entityList = utils.gamesEntityList1();
        given(dao.list()).willReturn(entityList);

        List<GameViewModel> viewModelList = gameService.list();

        Assert.assertEquals(entityList.get(0).getId(), viewModelList.get(0).getId());
        Assert.assertEquals(entityList.get(0).getStart(), viewModelList.get(0).getStart());
        Assert.assertEquals(entityList.get(0).getFinish(), viewModelList.get(0).getFinish());
        Assert.assertEquals(entityList.get(0).getKills().size(), viewModelList.get(0).getKills().size());
    }


    @Test
    public void getById() {
        Long totalKills11 = utils.gameStatisticsModelList1().get(0).getTotalKills();
        List<Map<Object, Object>> totalKills1 = new ArrayList<>();
        Map<Object, Object> totalKill1 = new HashMap<>();
        totalKill1.put("count", totalKills11);
        totalKills1.add(totalKill1);

        GameEntity entity1 = utils.gameEntity1();
        /* lists entities */
        given(dao.find(entity1.getId())).willReturn(entity1);
        Integer id1 = entity1.getId();
        /* get total kills */
        given(dao.getTotalKills(id1, GameServiceImpl.EMPTY_LIST)).willReturn(totalKills1);
        /* get kill count by player*/
        given(dao.getTotalKillsByPlayer(id1, GameServiceImpl.UNDESIRABLE_PLAYERS)).
                willReturn(utils.killCountMapList1());

        GameStatisticsViewModel viewModel = gameService.getById(id1);

        Assert.assertEquals(entity1.getId(), viewModel.getId());
        Assert.assertEquals(entity1.getStart(), viewModel.getStart());
        Assert.assertEquals(entity1.getFinish(), viewModel.getFinish());
        Assert.assertEquals(entity1.getKills().size(), viewModel.getKills().size());

        Assert.assertEquals(viewModel.getPlayers().size(), viewModel.getKills().size());
    }

    @Test
    public void getByIdNotFoundNull() {
        Integer id = 25;
        given(dao.find(id)).willReturn(null);

        GameStatisticsViewModel viewModel = gameService.getById(id);

        Assert.assertNull(viewModel);
    }


    @Test
    public void getStatistics() {
        Long totalKills11 = utils.gameStatisticsModelList2().get(0).getTotalKills();
        Long totalKills22 = utils.gameStatisticsModelList2().get(1).getTotalKills();
        List<Map<Object, Object>> totalKills1 = new ArrayList<>();
        Map<Object, Object> totalKill1 = new HashMap<>();
        totalKill1.put("count", totalKills11);
        totalKills1.add(totalKill1);
        List<Map<Object, Object>> totalKills2 = new ArrayList<>();
        Map<Object, Object> totalKill2 = new HashMap<>();
        totalKill2.put("count", totalKills22);
        totalKills2.add(totalKill2);

        List<GameEntity> entityList = utils.gamesEntityList2();
        /* lists entities */
        given(dao.list()).willReturn(entityList);
        Integer id1 = entityList.get(0).getId();
        Integer id2 = entityList.get(1).getId();
        /* get total kills */
        given(dao.getTotalKills(id1, GameServiceImpl.EMPTY_LIST)).willReturn(totalKills1);
        /* get kill count by player*/
        given(dao.getTotalKillsByPlayer(id1, GameServiceImpl.UNDESIRABLE_PLAYERS)).
                willReturn(utils.killCountMapList1());

        /* get total kills */
        given(dao.getTotalKills(id2, GameServiceImpl.EMPTY_LIST)).willReturn(totalKills2);
        /* get kill count by player*/
        given(dao.getTotalKillsByPlayer(id2, GameServiceImpl.UNDESIRABLE_PLAYERS)).
                willReturn(utils.killCountMapList2());

        List<GameStatisticsViewModel> viewModelList = gameService.getStatistics();

        Assert.assertEquals(entityList.get(0).getId(), viewModelList.get(0).getId());
        Assert.assertEquals(entityList.get(0).getStart(), viewModelList.get(0).getStart());
        Assert.assertEquals(entityList.get(0).getFinish(), viewModelList.get(0).getFinish());
        Assert.assertEquals(entityList.get(0).getKills().size(), viewModelList.get(0).getKills().size());

        Assert.assertEquals(viewModelList.get(0).getPlayers().size(), viewModelList.get(0).getKills().size());
    }


}

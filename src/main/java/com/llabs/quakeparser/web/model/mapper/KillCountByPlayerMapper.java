package com.llabs.quakeparser.web.model.mapper;

import com.llabs.quakeparser.web.model.KillCountByPlayerViewModel;

import java.util.Map;

public class KillCountByPlayerMapper {

    /**
     * Create a model from a map containing the keys <b>playerName</b> and <b>count</b>.
     * @param map a map containing the playerName name and kill count
     * @return
     */
    public static KillCountByPlayerViewModel from(Map<Object, Object> map) {
        KillCountByPlayerViewModel viewModel = new KillCountByPlayerViewModel();
        viewModel.setName((String) map.get("playerName"));
        viewModel.setCount((Long) map.get("count"));
        return viewModel;
    }
}

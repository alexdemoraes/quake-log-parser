package com.llabs.quakeparser.web.model.mapper;


import com.llabs.quakeparser.dao.model.KillEntity;
import com.llabs.quakeparser.web.model.KillViewModel;

public class KillEntityMapper {

    /**
     * Creates an entity from a view
     * @param viewModel a viewModel
     * @return an entity
     */
    public static KillEntity from(KillViewModel viewModel) {
        KillEntity entity = new KillEntity();
        entity.setId(viewModel.getId());
        entity.setPlayer(viewModel.getPlayer());
        entity.setKilled(viewModel.getKilled());
        entity.setMode(viewModel.getMode());
        return entity;
    }

    /**
     * Creates a model from a entity
     * @param entity an entity
     * @return a view
     */
    public static KillViewModel from(KillEntity entity) {
        return
                new KillViewModel(
                    entity.getId(),
                    entity.getPlayer(),
                    entity.getKilled(),
                    entity.getMode()
                );
    }

}

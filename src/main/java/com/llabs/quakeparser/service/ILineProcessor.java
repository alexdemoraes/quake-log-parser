package com.llabs.quakeparser.service;

import com.llabs.quakeparser.web.model.KillViewModel;

public interface ILineProcessor {

    public String getStart(String line);

    public KillViewModel getKill(String line);

    public String getFinish(String line);

}


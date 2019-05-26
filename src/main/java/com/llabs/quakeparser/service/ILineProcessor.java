package com.llabs.quakeparser.service;

import com.llabs.quakeparser.web.model.KillViewModel;

public interface ILineProcessor {

    String getStart(String line);

    KillViewModel getKill(String line);

    String getFinish(String line);

}


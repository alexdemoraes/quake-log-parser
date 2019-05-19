package com.llabs.quakeparser.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameViewModel {

    private Integer id;
    private String start;
    private String finish;

    private List<KillViewModel> kills = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public List<KillViewModel> getKills() {
        return kills;
    }

    public void setKills(List<KillViewModel> kills) {
        this.kills = kills;
    }

}

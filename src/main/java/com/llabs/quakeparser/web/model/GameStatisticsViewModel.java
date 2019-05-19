package com.llabs.quakeparser.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameStatisticsViewModel {

    private Integer id;
    private String start;
    private String finish;
    private List<String> players = new ArrayList<>();
    private List<KillCountByPlayerViewModel> kills = new ArrayList<>();
    @JsonProperty("total_kills")
    private Long totalKills;

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

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public List<KillCountByPlayerViewModel> getKills() {
        return kills;
    }

    public void setKills(List<KillCountByPlayerViewModel> kills) {
        this.kills = kills;
    }

    public Long getTotalKills() {
        return totalKills;
    }

    public void setTotalKills(Long totalKills) {
        this.totalKills = totalKills;
    }
}

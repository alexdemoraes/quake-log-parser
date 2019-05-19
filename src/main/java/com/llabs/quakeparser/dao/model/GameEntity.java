package com.llabs.quakeparser.dao.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Game")
@Table(name = "game")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String start;
    @Column
    private String finish;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn
    private List<KillEntity> kills = new ArrayList<>();


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

    public List<KillEntity> getKills() {
        return kills;
    }

    public void setKills(List<KillEntity> kills) {
        this.kills = kills;
    }

    public void addKill(KillEntity kill) {
        kill.setGame(this);
        kills.add(kill);
    }
}

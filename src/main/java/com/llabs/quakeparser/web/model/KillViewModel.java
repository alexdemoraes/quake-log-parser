package com.llabs.quakeparser.web.model;

public class KillViewModel {

    private Integer id;

    private String player;
    private String killed;
    private String mode;

    public KillViewModel() {

    }
    public KillViewModel(Integer id, String player, String killed, String mode) {
        this.id = id;
        this.player = player;
        this.killed = killed;
        this.mode = mode;
    }
    public KillViewModel(String player, String killed, String mode) {
        this(null, player, killed, mode);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getKilled() {
        return killed;
    }

    public void setKilled(String killed) {
        this.killed = killed;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}

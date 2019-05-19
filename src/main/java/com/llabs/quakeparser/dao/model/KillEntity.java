package com.llabs.quakeparser.dao.model;


import javax.persistence.*;

@Entity(name = "Kill")
@Table(name = "kill")
public class KillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "player", nullable = false)
    private String player;
    @Column(name = "killed", nullable = false)
    private String killed;
    @Column(name = "mode", nullable = false)
    private String mode;

    @ManyToOne(optional = false)
    @JoinColumn(name = "game_id")
    private GameEntity game;

    public KillEntity() {
    }
    public KillEntity(String player, String killed, String mode) {
        this(null, player, killed, mode);
    }
    public KillEntity(Integer id, String player, String killed, String mode) {
        this.player = player;
        this.killed = killed;
        this.mode   = mode;
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

    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}

package com.engcorner.epose.domain.user;

import com.engcorner.epose.domain.game.Game;

import javax.persistence.*;

@Entity
public class UserGame {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Game game; // 游戏编号

    @Column(nullable = false)
    private Long maxScore; // 最高得分

    @Column(nullable = false)
    private Long times; // 游戏次数

    @Column(nullable = false)
    private Long duration; // 游戏时长（秒）

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Long getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Long maxScore) {
        this.maxScore = maxScore;
    }

    public Long getTimes() {
        return times;
    }

    public void setTimes(Long times) {
        this.times = times;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}

package com.engcorner.epose.domain.user;

import com.engcorner.epose.domain.course.Action;

import javax.persistence.*;

@Entity
public class UserActionScore {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Action action; // 动作编号

    @Column(nullable = false)
    private Long totalScore; // 动作得分

    @Column(nullable = false)
    private String partScores; // 各部位得分

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Long totalScore) {
        this.totalScore = totalScore;
    }

    public String getPartScores() {
        return partScores;
    }

    public void setPartScores(String partScores) {
        this.partScores = partScores;
    }
}

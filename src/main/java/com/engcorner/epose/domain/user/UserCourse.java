package com.engcorner.epose.domain.user;

import com.engcorner.epose.domain.course.Action;
import com.engcorner.epose.domain.course.Course;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserCourse {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Course course; // 课程编号

    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<UserActionScore> userActionScores; // 已学习动作

    @OneToOne(fetch = FetchType.EAGER)
    private Action curAction; // 当前动作

    @Column(nullable = false)
    private Long finalScore; // 课程最终得分

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<UserActionScore> getUserActionScores() {
        return userActionScores;
    }

    public void setUserActionScores(List<UserActionScore> userActionScores) {
        this.userActionScores = userActionScores;
    }

    public Action getCurAction() {
        return curAction;
    }

    public void setCurAction(Action curAction) {
        this.curAction = curAction;
    }

    public Long getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Long finalScore) {
        this.finalScore = finalScore;
    }
}

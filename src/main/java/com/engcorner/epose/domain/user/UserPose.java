package com.engcorner.epose.domain.user;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserPose {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade={ CascadeType.ALL })
    private List<UserCourse> userCourses; // 课程状态

    private Long courseAverScore; // 课程平均分

    private Long courseMaxScore; // 课程最高分

    private Long courseMinScore; // 课程最低分

    @Column(nullable = false)
    private String partScores; // 各部位平均分

    @OneToMany(cascade={ CascadeType.ALL })
    private List<UserGame> userGames; // 游戏状态

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserCourse> getUserCourses() {
        return userCourses;
    }

    public void setUserCourses(List<UserCourse> userCourses) {
        this.userCourses = userCourses;
    }

    public Long getCourseAverScore() {
        return courseAverScore;
    }

    public void setCourseAverScore(Long courseAverScore) {
        this.courseAverScore = courseAverScore;
    }

    public Long getCourseMaxScore() {
        return courseMaxScore;
    }

    public void setCourseMaxScore(Long courseMaxScore) {
        this.courseMaxScore = courseMaxScore;
    }

    public Long getCourseMinScore() {
        return courseMinScore;
    }

    public void setCourseMinScore(Long courseMinScore) {
        this.courseMinScore = courseMinScore;
    }

    public String getPartScores() {
        return partScores;
    }

    public void setPartScores(String partScores) {
        this.partScores = partScores;
    }

    public List<UserGame> getUserGames() {
        return userGames;
    }

    public void setUserGames(List<UserGame> userGames) {
        this.userGames = userGames;
    }
}

package com.engcorner.epose.domain.course;

import javax.persistence.*;

@Entity
public class Action {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="phase_id")
    private Phase phase; // 课程名称

    @Column(nullable = false, length = 32)
    private String name; // 动作名称

    @Column(nullable = false, length = 1024)
    private String intro; // 动作介绍

    @Column(nullable = false, length = 256)
    private String imagePath; // 动作图片路径

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }
}

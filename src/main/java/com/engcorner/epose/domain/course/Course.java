package com.engcorner.epose.domain.course;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 32)
    private String name; // 课程名称

    @Column(nullable = false, length = 1024)
    private String overview; // 概述

    @Column(nullable = false, length = 1024)
    private String scope; // 适用范围

    @Column(length = 256)
    private String docPath; // 资料下载路径

    @OneToMany(cascade=CascadeType.ALL, mappedBy="course")
    private List<Action> actions = new ArrayList<Action>(); // 动作

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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public void addAction(Action action) {
        action.setCourse(this);
        this.actions.add(action);
    }
}

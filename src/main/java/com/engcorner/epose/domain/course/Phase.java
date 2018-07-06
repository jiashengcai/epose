package com.engcorner.epose.domain.course;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Phase {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 32)
    private String name; // 阶段名称

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="course_id")
    private Course course; // 课程编号

    @OneToMany(cascade=CascadeType.ALL, mappedBy="phase")
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

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void addAction(Action action) {
        action.setPhase(this);
        this.actions.add(action);
    }
}

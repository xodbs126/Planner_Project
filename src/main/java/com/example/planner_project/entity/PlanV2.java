package com.example.planner_project.entity;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Getter
@Setter
public class PlanV2 {
    private Long id;
    private String content;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PlanV2() {

    }

    public PlanV2(PlanV2 plan) {
        this.id = plan.getId();
        this.content = plan.getContent();
        this.createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
    }

    public PlanV2(String content, Long userId, String password) {
        this.content = content;
        this.userId = userId;
        this.createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    }


    public PlanV2(Long id, String content, String writer, String password) {
        this.id = id;
        this.content = content;
        this.createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
    }

}
package com.example.planner_project.plan;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Getter
@Setter
public class Plan {
    private Long id;
    private String content;
    private String writer;
    private String password;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Plan() {

    }

    public Plan(Plan plan) {
        this.id = plan.getId();
        this.content = plan.getContent();
        this.writer = plan.getWriter();
        this.password = plan.getPassword();
        this.createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
    }

    public Plan(String content, Long userId, String password) {
        this.content = content;
        this.userId = userId;
        this.password = password;
        this.createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    }


    public Plan(Long id, String content, String writer, String password) {
        this.id = id;
        this.content = content;
        this.writer = writer;
        this.password = password;
        this.createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
    }

}
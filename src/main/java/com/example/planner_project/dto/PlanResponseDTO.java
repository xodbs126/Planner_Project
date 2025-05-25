package com.example.planner_project.dto;


import com.example.planner_project.plan.Plan;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlanResponseDTO {

    Plan plan;

    private Long id;
    private String content;
    private String writer;
    private LocalDateTime createdAt;
    private LocalDateTime editedAt;


    public PlanResponseDTO(Plan plan) {
        this.plan = plan;
        this.id = plan.getId();
        this.content = plan.getContent();
        this.writer = plan.getWriter();
        this.createdAt = plan.getCreatedAt();
        this.editedAt = plan.getCreatedAt();
    }


}

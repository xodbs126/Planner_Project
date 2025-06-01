package com.example.planner_project.dto;


import com.example.planner_project.entity.PlanV2;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlanResponseDTOV2 {

    private Long id;
    private String content;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime editedAt;


    public PlanResponseDTOV2(PlanV2 plan) {
        this.id = plan.getId();
        this.content = plan.getContent();
        this.userId = plan.getUserId();
        this.createdAt = plan.getCreatedAt();
        this.editedAt = plan.getCreatedAt();
    }


}

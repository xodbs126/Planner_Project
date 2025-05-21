package com.example.planner_project.dto;



import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlanRequestDTO {

    private Long id;
    private String content;
    private String writer;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime editedAt;

}

package com.example.planner_project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PlanRequestDTO_V2 {

    @NotBlank
    @Size(max = 200)
    private String content;
    private Long userId;
}
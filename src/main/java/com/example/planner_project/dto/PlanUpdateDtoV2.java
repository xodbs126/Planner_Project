package com.example.planner_project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PlanUpdateDtoV2 {

    @NotBlank
    @Size(max = 200)
    private String content;
    private Long userId;
    private String userName;

    @NotBlank
    private String password;

}

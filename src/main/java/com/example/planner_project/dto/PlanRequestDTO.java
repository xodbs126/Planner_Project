package com.example.planner_project.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlanRequestDTO {

    private Long id;
    private String userName;
    @NotBlank
    @Size(max = 200)
    private String content;

    @NotBlank
    private String writer;

    @NotBlank
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime editedAt;

}

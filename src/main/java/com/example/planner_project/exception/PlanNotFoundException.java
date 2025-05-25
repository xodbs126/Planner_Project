package com.example.planner_project.exception;

public class PlanNotFoundException extends RuntimeException {
    public PlanNotFoundException(Long id) {
        super("해당 ID(" + id + ")의 일정을 찾을 수 없습니다.");
    }
}
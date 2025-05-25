package com.example.planner_project.exception;

public class PasswordMismatchException extends RuntimeException {
    public PasswordMismatchException() {
        super("사용자의 비밀번호가 일치하지 않습니다.");
    }
}
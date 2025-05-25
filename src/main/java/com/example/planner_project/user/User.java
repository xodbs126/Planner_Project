package com.example.planner_project.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class User {

    private Long id;

    @NotBlank
    private String userName;

    @NotBlank
    private String pw;

    @Email
    private String email;

    private Long planId;

    public User() {
    }

    public User(String userName, String pw, String email) {
        this.userName = userName;
        this.pw = pw;
        this.email = email;
    }
}

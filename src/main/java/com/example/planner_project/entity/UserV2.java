package com.example.planner_project.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class UserV2 {

    private Long id;

    @NotBlank(message = "username 입력은 필수입니다.")
    private String userName;

    @NotBlank(message = "비밀번호 입력은 필수입니다.")
    private String pw;

    @Email
    private String email;

    private LocalDateTime created_at;

    private LocalDateTime edited_at;


    public UserV2() {
    }

    public UserV2(String userName, String pw, String email) {
        this.userName = userName;
        this.pw = pw;
        this.email = email;
    }
}

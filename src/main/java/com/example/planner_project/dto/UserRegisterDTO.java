package com.example.planner_project.dto;


import com.example.planner_project.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDTO {
    private Long id;

    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    @Email
    private String email;

    public UserRegisterDTO(User user) {
        this.userName = user.getUserName();
        this.password = user.getPw();
        this.email = user.getEmail();
    }
}

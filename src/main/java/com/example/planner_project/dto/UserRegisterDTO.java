package com.example.planner_project.dto;


import com.example.planner_project.entity.User;
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

    public UserRegisterDTO() {
    }

    public UserRegisterDTO(User user) {
        this.userName = user.getUserName();
        this.password = user.getPw();
        this.email = user.getEmail();
    }

    public UserRegisterDTO(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
}

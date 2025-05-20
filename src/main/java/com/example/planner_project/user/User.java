package com.example.planner_project.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "User")
@Table(name="User")
public class User {


    public User(Long id, String userId, String userPW, String email) {
        this.id = id;
        this.userId = userId;
        this.userPW = userPW;
        this.email = email;
    }

    @Id
    private Long id;

    @Column(nullable = false,unique = true)
    private String userId;

    @Column(nullable = false)
    private String userPW;

    @Column(nullable = false)
    private String email;
}

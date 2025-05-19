package com.example.planner_project.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "User")
@Table(name="User")
public class User {


    @Id
    private Long id;

    @Column(nullable = false,unique = true)
    private String userId;

    @Column(nullable = false)
    private String userPW;

    @Column(nullable = false)
    private String email;
}

package com.example.planner_project.plan;

import com.example.planner_project.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "Plan")
@Table(name = "Plan")

@Getter
@Setter
public class Plan {

    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false)
    private String planName;

    @Column(nullable = false)
    private String planDescription;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private LocalDateTime postDate;

    @Column(nullable = false)
    private LocalDateTime editDate;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

}

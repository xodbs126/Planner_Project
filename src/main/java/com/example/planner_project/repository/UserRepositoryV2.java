package com.example.planner_project.repository;

import com.example.planner_project.entity.UserV2;

public interface UserRepositoryV2 {
    UserV2 findUserById(Long userId);

    UserV2 findUserByWriter(String writer);

    void save(UserV2 user);

    void updateUser(UserV2 user);

}

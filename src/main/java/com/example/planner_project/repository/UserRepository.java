package com.example.planner_project.repository;

import com.example.planner_project.user.User;

public interface UserRepository {
    User findById(Long id);

    void saveUser(User user);

    Long findIdByName(String writer);
}

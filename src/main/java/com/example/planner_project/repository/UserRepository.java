package com.example.planner_project.repository;

import com.example.planner_project.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {

    public static Map<Long, User> userDB = new HashMap<>();
    public static UserRepository userRepository = new UserRepository();
    private static Long incrementId = 0L;

    public static UserRepository getInstance() {
        return userRepository;
    }

    public User save(User user) {
        user.setId(incrementId++);
        user.setUserId(user.getUserId());
        user.setUserPW(user.getUserPW());
        user.setEmail(user.getEmail());

        return user;
    }

    public List<User> findAllUsers() {
        return new ArrayList<>(userDB.values());
    }

}

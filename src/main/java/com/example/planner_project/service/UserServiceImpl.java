package com.example.planner_project.service;


import com.example.planner_project.dto.UserRegisterDTO;
import com.example.planner_project.repository.UserRepository;
import com.example.planner_project.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserRegisterDTO registerUser(UserRegisterDTO registerDTO) {
        User user = new User(registerDTO.getUserName(), registerDTO.getPassword(), registerDTO.getEmail());
        userRepository.saveUser(user);

        return new UserRegisterDTO(user);
    }
}

package com.example.learn_spring.service;

import com.example.learn_spring.dto.request.UserCreationRequest;
import com.example.learn_spring.dto.request.UserUpdateRequest;
import com.example.learn_spring.entity.User;
import com.example.learn_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setBirthday(request.getBirthday());

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    }

    public User updateUser(String id, UserUpdateRequest request) {
        User findUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
        findUser.setPassword(request.getPassword());
        findUser.setFirstName(request.getFirstName());
        findUser.setLastName(request.getLastName());
        findUser.setEmail(request.getEmail());
        findUser.setBirthday(request.getBirthday());

        userRepository.save(findUser);
        return findUser;
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}

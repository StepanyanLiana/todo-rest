package com.example.todorest.service;

import com.example.todorest.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);

    void save(User user);

    Optional<User> findById(int id);

    boolean existsById(int id);

    void deleteById(int id);
}

package com.example.todorest.service;

import com.example.todorest.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category save(Category category);

    boolean existsById(int id);

    void deleteById(int id);

    List<Category> findAll();

    Optional<Category> findById(int id);
}

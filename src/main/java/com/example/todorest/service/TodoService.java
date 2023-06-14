package com.example.todorest.service;

import com.example.todorest.entity.Category;
import com.example.todorest.entity.Status;
import com.example.todorest.entity.Todo;
import com.example.todorest.entity.User;

import java.util.List;
import java.util.Optional;

public interface TodoService {
     Optional<Todo> findById(int id) ;

    List<Todo> findTodosByUser(User user);

    boolean existsById(int id);

    void deleteById(int id);

    Todo save(Todo todoFromDB);

    List<Todo> findAllTodoByCategory(Category category, int id);

    List<Todo> findAllTodosByStatus(Status status, int id);
}

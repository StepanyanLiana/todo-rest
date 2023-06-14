package com.example.todorest.repository;

import com.example.todorest.entity.Category;
import com.example.todorest.entity.Status;
import com.example.todorest.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findAllByUser_Id(int userId);
    List<Todo> findAllByStatusAndUser(Status status, int userId);
    List<Todo> findAllByCategoryAndUser(Category category, int userID);
}

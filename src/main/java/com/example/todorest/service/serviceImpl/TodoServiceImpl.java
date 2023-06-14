package com.example.todorest.service.serviceImpl;

import com.example.todorest.entity.*;
import com.example.todorest.repository.TodoRepository;
import com.example.todorest.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;

    @Override
    public Optional<Todo> findById(int id) {
        return todoRepository.findById(id);
    }

    @Override
    public List<Todo> findTodosByUser(User user) {
        List<Todo> todos;
        if (user.getRole() == Role.ADMIN) {
            todos = todoRepository.findAll();
        } else {
            todos = todoRepository.findAllByUser_Id(user.getId());
        }
        return todos;
    }

    @Override
    public boolean existsById(int id) {
        return todoRepository.existsById(id);
    }

    @Override
    public void deleteById(int id) {
        todoRepository.deleteById(id);
    }

    @Override
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> findAllTodoByCategory(Category category, int id) {
        return todoRepository.findAllByCategoryAndUser(category, id);
    }

    @Override
    public List<Todo> findAllTodosByStatus(Status status, int id) {
        return todoRepository.findAllByStatusAndUser(status, id);
    }
}

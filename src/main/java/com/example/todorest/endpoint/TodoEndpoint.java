package com.example.todorest.endpoint;

import com.example.todorest.entity.Category;
import com.example.todorest.entity.Status;
import com.example.todorest.entity.Todo;
import com.example.todorest.security.CurrentUser;
import com.example.todorest.service.CategoryService;
import com.example.todorest.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoEndpoint {
    private final TodoService todoService;
    private final CategoryService categoryService;

    @PostMapping
    public Todo createCategory(@RequestBody Category category, @AuthenticationPrincipal CurrentUser currentUser) {
        Todo todo = new Todo();
        todo.setCategory(category);
        todo.setUser(currentUser.getUser());
        todo.setStatus(Status.NOT_STARTED);
        return todoService.save(todo);
    }


    @GetMapping
    public ResponseEntity<List<Todo>> findTodoByUser(@AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseEntity.ok(todoService.findTodosByUser(currentUser.getUser()));
    }


    @GetMapping("/byStatus")
    public List<Todo> getStatusByTodo(@RequestParam Status status, @AuthenticationPrincipal CurrentUser currentUser) {
        return todoService.findAllTodosByStatus(status, currentUser.getUser().getId());
    }


    @GetMapping("/byCategory")
    public ResponseEntity<List<Todo>> categoryByTodo(@RequestParam Category category, @AuthenticationPrincipal CurrentUser currentUser) {
        Optional<Category> byId = categoryService.findById(category.getId());
        if (byId.isPresent()) {
            Category categoryId = byId.get();
            return ResponseEntity.ok(todoService.findAllTodoByCategory(categoryId, currentUser.getUser().getId()));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id, @AuthenticationPrincipal CurrentUser currentUser) {
        if (todoService.existsById(id)) {
            todoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodoStatus(@PathVariable("id") int id, @RequestBody Todo todo) {
        Optional<Todo> byId = todoService.findById(id);
        if (byId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Todo todoFromDB = byId.get();
        if (todo.getStatus() != null) {
            todoFromDB.setStatus(todo.getStatus());
        }
        return ResponseEntity.ok(todoService.save(todoFromDB));
    }
}
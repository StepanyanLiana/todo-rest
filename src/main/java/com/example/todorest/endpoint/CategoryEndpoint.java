package com.example.todorest.endpoint;

import com.example.todorest.entity.Category;
import com.example.todorest.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryEndpoint {
    private final CategoryService categoryService;

    @PostMapping()
    public Category create(@RequestBody Category category) {
        return categoryService.save(category);
    }
    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> all = categoryService.findAll();
        if (all.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        if (categoryService.existsById(id)) {
            categoryService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
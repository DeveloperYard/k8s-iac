package com.k8s.app.controller;

import com.k8s.app.model.Todo;
import com.k8s.app.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public Optional<Todo> getTodoById(@PathVariable("id") Long id) {
        return todoService.getTodoById(id);
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        Todo newTodo = new Todo();
        newTodo.setCompleted(todo.getCompleted());
        newTodo.setTitle(todo.getTitle());

        return todoService.createTodo(newTodo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable("id") Long id, @RequestBody Boolean isCompleted) {
        return todoService.updateTodo(id, isCompleted);
    }

    @DeleteMapping("/{id}")
    public void deleteTodoById(@PathVariable("id") Long id) {
        todoService.deleteTodoById(id);
    }
}

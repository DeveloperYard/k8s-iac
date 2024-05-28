package com.k8s.app.service;

import com.k8s.app.model.Todo;
import com.k8s.app.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    public Todo createTodo(Todo todo){
        return todoRepository.save(todo);
    }
    public Todo updateTodo(Long id, boolean isCompleted) {
        Todo find = todoRepository.getReferenceById(id);
        find.setCompleted(!isCompleted);
        return todoRepository.save(find);
    }

    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }
}


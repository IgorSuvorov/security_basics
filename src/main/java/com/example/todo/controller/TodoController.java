package com.example.todo.controller;

import com.example.todo.dto.TodoDTO;
import com.example.todo.model.Todo;
import com.example.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Igor Suvorov
 */
@AllArgsConstructor
@RestController
@RequestMapping("api/todos/")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TodoDTO> addTodo(@RequestBody TodoDTO todoDTO) {
        TodoDTO addedTodo = todoService.addTodo(todoDTO);
        return new ResponseEntity<>(addedTodo, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<TodoDTO> getTodoByID(@PathVariable("id") Long id) {
        TodoDTO todoDTO = todoService.getTodoById(id);
        return new ResponseEntity<>(todoDTO, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<TodoDTO>> getAllTodos() {
        List<TodoDTO> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TodoDTO> updateTodo(@RequestBody TodoDTO todoDTO,
                                              @PathVariable("id") Long id) {
        todoDTO.setId(id);
        TodoDTO updatedTodo = todoService.updateTodo(todoDTO);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>("Todo was deleted", HttpStatus.OK);
    }


    @PatchMapping("{id}/complete")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<TodoDTO> completeTodo(@PathVariable("id") Long id) {
        TodoDTO updatedTodo = todoService.completeTodo(id);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    @PatchMapping("{id}/incomplete")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<TodoDTO> InCompleteTodo(@PathVariable("id") Long id) {
        TodoDTO updatedTodo = todoService.inCompleteTodo(id);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }
}

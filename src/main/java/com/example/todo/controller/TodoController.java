package com.example.todo.controller;

import com.example.todo.dto.TodoDTO;
import com.example.todo.model.Todo;
import com.example.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Igor Suvorov
 */
@AllArgsConstructor
@RestController
@RequestMapping("api/todo/")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoDTO> addTodo(@RequestBody TodoDTO todoDTO) {
        TodoDTO addedTodo = todoService.addTodo(todoDTO);
        return new ResponseEntity<>(addedTodo, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoDTO> getTodoByID(@PathVariable("id") Long id) {
        TodoDTO todoDTO = todoService.getTodoById(id);
        return new ResponseEntity<>(todoDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TodoDTO>> getAllTodos() {
        List<TodoDTO> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<TodoDTO> updateTodo(@RequestBody TodoDTO todoDTO,
                                              @PathVariable("id") Long id) {
        todoDTO.setId(id);
        TodoDTO updatedTodo = todoService.updateTodo(todoDTO);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteTodo(Long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>("Todo was deleted", HttpStatus.OK);
    }

    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDTO> completeTodo(@PathVariable("id") Long id) {
        TodoDTO updatedTodo = todoService.completeTodo(id);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    @PatchMapping("{id}/incomplete")
    public ResponseEntity<TodoDTO> InCompleteTodo(@PathVariable("id") Long id) {
        TodoDTO updatedTodo = todoService.inCompleteTodo(id);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

}

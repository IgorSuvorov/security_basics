package com.example.todo.service;

import com.example.todo.dto.TodoDTO;

import java.util.List;

/**
 * @author Igor Suvorov
 */
public interface TodoService {

    TodoDTO addTodo(TodoDTO todoDTO);

    TodoDTO getTodoById(Long id);

    List<TodoDTO> getAllTodos();

    TodoDTO updateTodo(TodoDTO todoDTO);

    void deleteTodo(Long id);

    TodoDTO completeTodo(Long id);

    TodoDTO inCompleteTodo(Long id);
}

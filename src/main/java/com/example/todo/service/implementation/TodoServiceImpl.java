package com.example.todo.service.implementation;

import com.example.todo.dto.TodoDTO;
import com.example.todo.exception.TodoNotFoundException;
import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import com.example.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Igor Suvorov
 */
@AllArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;

    @Override
    public TodoDTO addTodo(TodoDTO todoDTO) {
        Todo todo = modelMapper.map(todoDTO, Todo.class);
        Todo savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo, TodoDTO.class);
    }

    @Override
    public TodoDTO getTodoById(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new TodoNotFoundException("todo", "id", id)
        );
        return modelMapper.map(todo, TodoDTO.class);
    }

    @Override
    public List<TodoDTO> getAllTodos() {
        List<Todo> users = todoRepository.findAll();
        return users.stream().map((user) -> modelMapper.map(user, TodoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDTO updateTodo(TodoDTO todoDTO) {
        Todo existingTodo = todoRepository.findById(todoDTO.getId()).orElseThrow(
                () -> new TodoNotFoundException("todo", "id", todoDTO.getId())
        );
        existingTodo.setTitle(todoDTO.getTitle());
        existingTodo.setDescription(todoDTO.getDescription());
        existingTodo.setCompleted(todoDTO.isCompleted());
        Todo savedTodo = todoRepository.save(existingTodo);
        return modelMapper.map(savedTodo, TodoDTO.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new TodoNotFoundException("todo", "id", id)
        );
        todoRepository.deleteById(id);
    }


    @Override
    public TodoDTO completeTodo(Long id) {
        Todo existingTodo = todoRepository.findById(id).orElseThrow(
                () -> new TodoNotFoundException("todo", "id", id)
        );
        existingTodo.setCompleted(Boolean.TRUE);
        Todo savedTodo = todoRepository.save(existingTodo);
        return modelMapper.map(savedTodo, TodoDTO.class);
    }

    @Override
    public TodoDTO inCompleteTodo(Long id) {
        Todo existingTodo = todoRepository.findById(id).orElseThrow(
                () -> new TodoNotFoundException("todo", "id", id)
        );
        existingTodo.setCompleted(Boolean.FALSE);
        Todo savedTodo = todoRepository.save(existingTodo);
        return modelMapper.map(savedTodo, TodoDTO.class);
    }
}

package com.example.todo.repository;

import com.example.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Igor Suvorov
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}

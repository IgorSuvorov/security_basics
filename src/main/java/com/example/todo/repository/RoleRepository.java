package com.example.todo.repository;

import com.example.todo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Igor Suvorov
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}

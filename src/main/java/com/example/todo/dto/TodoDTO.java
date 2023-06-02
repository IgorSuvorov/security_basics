package com.example.todo.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Igor Suvorov
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {

    private Long id;
    private String title;
    private String description;
    private boolean completed;
}

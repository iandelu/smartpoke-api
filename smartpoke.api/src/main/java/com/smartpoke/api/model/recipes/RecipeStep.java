package com.smartpoke.api.model.recipes;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RecipeStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer position;
    private Integer time;
    private String picture;
}
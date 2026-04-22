package com.example.recipe_api;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "recipes")   
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    
    @JsonProperty("making_time")
    @Column(name = "making_time")
    private String makingTime;

    private String serves;
    private String ingredients;
    private Integer cost;

    @JsonProperty("created_at")
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

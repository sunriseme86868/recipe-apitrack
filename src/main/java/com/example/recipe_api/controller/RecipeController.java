package com.example.recipe_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipe_api.dto.Recipe;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    //POST/api/recipes 新規登録
    @PostMapping
    public String postMethodName(@RequestBody String entity) {
    }
    
    //GET/api/recipes 全件取得
    @GetMapping
    public List<Recipe> getMethodName(@RequestParam String param) {
        return new ArrayList<>();
    }

    
    
}

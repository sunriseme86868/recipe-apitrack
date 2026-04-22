package com.example.recipe_api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.foreign.Linker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Optional;



@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    //GET/api/recipes 全件取得
    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    //GET/api/recipes/{id} 1件取得　なければ404を返す
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Integer id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            return ResponseEntity.ok(recipe.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //POST/api/recipes 新規登録
    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return recipeRepository.save(recipe);
    }
    
    //PATCH/api/recipes/{id} 更新　なければ404を返す
    @PatchMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Integer id, @RequestBody Recipe updateDate) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        // レシピが存在しない場合は404を返す
        if (recipeOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        // レシピが存在する場合は更新して保存する
        Recipe recipe = recipeOptional.get();

        // 更新データを反映させる
        if(updateDate.getTitle() != null) recipe.setTitle(updateDate.getTitle());
        if(updateDate.getMakingTime() != null) recipe.setMakingTime(updateDate.getMakingTime());

        if(updateDate.getServes() != null) recipe.setServes(updateDate.getServes());
        if(updateDate.getIngredients() != null) recipe.setIngredients(updateDate.getIngredients());
        if(updateDate.getCost() != null) recipe.setCost(updateDate.getCost());
        
        Recipe updatedRecipe = recipeRepository.save(recipe);
        return ResponseEntity.ok(updatedRecipe);
    }

    //DELETE/api/recipes/{id} 削除　なければ404を返す
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Integer id) {
        // レシピが存在するか確認
        if (recipeRepository.existsById(id)) {
            // レシピが存在する場合は削除
            recipeRepository.deleteById(id);
            // 削除成功のレスポンスを返す
            return ResponseEntity.noContent().build();
        } else {
            // レシピが存在しない場合は404を返す
            return ResponseEntity.notFound().build();
        }
    }
}
        

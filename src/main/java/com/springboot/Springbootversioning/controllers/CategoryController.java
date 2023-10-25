package com.springboot.Springbootversioning.controllers;

import com.springboot.Springbootversioning.entities.CategoryEntity;
import com.springboot.Springbootversioning.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/cats")
    public List<CategoryEntity> getCategories() {
       return categoryService.getCategories();
    }

    @GetMapping("/catsById/{id}")
    public CategoryEntity getCategoriesByid(@PathVariable Long id) {
        return categoryService.getCategoriesById(id).get();
    }

    @PostMapping("/updateCategory")
    public CategoryEntity updateCategory(@RequestBody CategoryEntity categoryEntity) {
        return categoryService.updateCategory(categoryEntity);
    }

}

package com.springboot.Springbootversioning.controllers;

import com.springboot.Springbootversioning.entities.CategoryEntity;
import com.springboot.Springbootversioning.services.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    Logger logger = LoggerFactory.getLogger(CategoryController.class);
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

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<Boolean> deleteCategoryById(@PathVariable Long id) {
        try {
            logger.info("deleting category with category id " + id);
            categoryService.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            logger.error("Error occurred while deleting category with category id " + id);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}

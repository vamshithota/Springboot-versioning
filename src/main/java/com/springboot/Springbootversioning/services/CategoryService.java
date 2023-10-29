package com.springboot.Springbootversioning.services;

import com.springboot.Springbootversioning.entities.CategoryEntity;
import com.springboot.Springbootversioning.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Cacheable(value = "category")
    public List<CategoryEntity> getCategories(){
        return  categoryRepository.findAll();
    }
    @Cacheable(value = "category",key = "#id")
    public Optional<CategoryEntity> getCategoriesById(Long id){
        return  categoryRepository.findById(id);
    }
    @CachePut(value = "category", key = "#categoryEntity.category_id")
    public CategoryEntity updateCategory(CategoryEntity categoryEntity){
        return categoryRepository.save(categoryEntity);
    }
    @CacheEvict(value = "category", key = "#id")
    public void deleteCategory(Long id){
        try{
            categoryRepository.deleteById(id);
        }catch(Exception ex){
            System.out.println("Exception occurred while deleting record with id  " + id);
            throw ex;
        }

    }
}

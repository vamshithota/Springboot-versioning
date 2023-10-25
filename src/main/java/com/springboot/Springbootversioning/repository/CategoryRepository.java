package com.springboot.Springbootversioning.repository;

import com.springboot.Springbootversioning.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}

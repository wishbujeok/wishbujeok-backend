package com.example.app.domain.Category.repository;

import com.example.app.domain.Category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}

package com.example.app.bujeok.repository;

import com.example.app.bujeok.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}

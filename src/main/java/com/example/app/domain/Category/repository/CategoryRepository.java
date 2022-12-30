package com.example.app.domain.Category.repository;

import com.example.app.domain.Category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByImgURL(String imgURL);
}

package com.example.app.Category.Service;

import com.example.app.Category.entity.Category;
import com.example.app.Category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    
    public Category findById(Long id){
        return categoryRepository.findById(id).get();
    } 
}

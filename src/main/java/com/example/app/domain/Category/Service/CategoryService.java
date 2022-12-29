package com.example.app.domain.Category.Service;

import com.example.app.domain.Category.entity.Category;
import com.example.app.domain.Category.entity.dto.CategoryDto;
import com.example.app.domain.Category.entity.mapper.CategoryDtoMapper;
import com.example.app.domain.Category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<CategoryDto> findById(Long id){
        Optional<Category> found = categoryRepository.findById(id);
        if(found.isEmpty()){
            return null;
            // 예외 처리
        }
        return Optional.ofNullable(CategoryDtoMapper.INSTANCE.CategoryToCategoryDto(found.get()));
    }

    @Transactional
    public CategoryDto create(CategoryDto categoryDto) {
        Category newCategory = CategoryDtoMapper.INSTANCE.CategoryDtoToCategory(categoryDto);
        categoryRepository.save(newCategory);
        return categoryDto;
    }


    public long getCategoryCount() {
        long count = categoryRepository.count();
        return count;
    }
}
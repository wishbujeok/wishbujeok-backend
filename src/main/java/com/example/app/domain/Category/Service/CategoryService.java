package com.example.app.domain.Category.Service;

import com.example.app.domain.Category.entity.Category;
import com.example.app.domain.Category.entity.dto.CategoryDto;
import com.example.app.domain.Category.entity.mapper.CategoryDtoMapper;
import com.example.app.domain.Category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public CategoryDto create(String frontUrl){
        Category category = Category.builder()
                                .frontUrl(frontUrl)
                                .build();

        categoryRepository.save(category);

        return CategoryDtoMapper.INSTANCE.CategoryToCategoryDto(category);
    }

    public long getCategoryCount() {
        long count = categoryRepository.count();
        return count;
    }
}

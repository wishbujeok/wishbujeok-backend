package com.example.app.domain.Category.Service;

import com.example.app.domain.Category.entity.Category;
import com.example.app.domain.Category.entity.dto.CategoryDto;
import com.example.app.domain.Category.entity.mapper.CategoryDtoMapper;
import com.example.app.domain.Category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
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

    public CategoryDto create(MultipartFile multipartFile) throws IOException {
        Base64.Encoder encoder = Base64.getEncoder();
        byte [] imgEncode = encoder.encode(multipartFile.getBytes());
        String imgBase64 = new String(imgEncode,"UTF8");

        Category category = Category.builder()
                                .base64(imgEncode)
                                .build();

        categoryRepository.save(category);
        return CategoryDtoMapper.INSTANCE.CategoryToCategoryDto(category);
    }

    public CategoryDto create(String frontUrl) throws UnsupportedEncodingException {
        byte [] imgEncode = frontUrl.getBytes("UTF8");

        Category category = Category.builder()
                                .base64(imgEncode)
                                .build();

        categoryRepository.save(category);

        return CategoryDtoMapper.INSTANCE.CategoryToCategoryDto(category);
    }

    public long getCategoryCount() {
        long count = categoryRepository.count();
        return count;
    }
}

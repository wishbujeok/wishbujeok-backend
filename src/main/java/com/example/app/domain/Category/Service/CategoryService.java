package com.example.app.domain.Category.Service;

import com.example.app.domain.Category.entity.Category;
import com.example.app.domain.Category.entity.dto.CategoryDto;
import com.example.app.domain.Category.entity.dto.CategoryResponse;
import com.example.app.domain.Category.entity.mapper.CategoryDtoMapper;
import com.example.app.domain.Category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
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

    public CategoryResponse create(MultipartFile multipartFile) throws IOException {
        Base64.Encoder encoder = Base64.getEncoder();
        // TODO 현재 byte[]에서는 오류 나지만 String변환시 오류 안남, 프론트 단에서 확인해야함
        byte [] imgEncode = encoder.encode(multipartFile.getBytes());
        String imgBase64 = new String(imgEncode,"UTF8");

        Category category = Category.builder()
                .base64(imgEncode)
                .build();

        categoryRepository.save(category);

        CategoryResponse categoryResponse = CategoryResponse.builder()
                .base64ToString(imgBase64)
                .id(category.getId())
                .build();
        return categoryResponse;
    }

    public CategoryResponse create(String frontUrl) throws UnsupportedEncodingException {
        byte [] imgEncode = frontUrl.getBytes("UTF8");
        String imgBase64 = new String(imgEncode,"UTF8");

        Category category = Category.builder()
                .base64(imgEncode)
                .build();

        categoryRepository.save(category);

        CategoryResponse categoryResponse = CategoryResponse.builder()
                .base64ToString(imgBase64)
                .id(category.getId())
                .build();

        return categoryResponse;
    }

    public long getCategoryCount() {
        long count = categoryRepository.count();
        return count;
    }
}
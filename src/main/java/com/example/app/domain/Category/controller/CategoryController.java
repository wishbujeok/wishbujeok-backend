package com.example.app.domain.Category.controller;

import com.example.app.domain.Category.Service.CategoryService;
import com.example.app.domain.Category.entity.dto.CategoryDto;
import com.example.app.domain.Category.entity.dto.CategoryResponse;
import com.example.app.global.common.base.api.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.example.app.global.common.base.api.ApiResult.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ApiResult<CategoryResponse> createCategory(MultipartFile file) throws IOException {
        CategoryResponse CategoryResponse = categoryService.create(file);

        return OK(CategoryResponse);
    }
}

package com.example.app.domain.Category.entity.mapper;

import com.example.app.domain.Category.entity.Category;
import com.example.app.domain.Category.entity.dto.CategoryDto;
import com.example.app.domain.Category.entity.dto.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryDtoMapper {
    CategoryDtoMapper INSTANCE = Mappers.getMapper(CategoryDtoMapper.class);
    Category CategoryDtoToCategory(CategoryDto categoryDto);
    CategoryDto CategoryToCategoryDto(Category category);
}

package com.example.app.Category.entity.mapper;

import com.example.app.Category.entity.Category;
import com.example.app.Category.entity.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryDtoMapper {
    CategoryDtoMapper INSTANCE = Mappers.getMapper(CategoryDtoMapper.class);

    Category CategoryDtoToCategory(CategoryDto categoryDto);

    CategoryDto CategoryToCategoryDto(Category category);
}

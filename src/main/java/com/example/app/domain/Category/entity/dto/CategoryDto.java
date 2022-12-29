package com.example.app.domain.Category.entity.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoryDto {
    private String imgURL;
    private String backColor;
}

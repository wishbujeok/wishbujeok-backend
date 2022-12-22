package com.example.app.domain.Category.entity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CategoryResponse {
    Long id;
    private String base64ToString;
}

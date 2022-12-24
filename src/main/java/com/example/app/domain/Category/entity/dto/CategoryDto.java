package com.example.app.domain.Category.entity.dto;

import com.example.app.global.common.base.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class CategoryDto extends BaseEntity {
    private byte[] base64;
}

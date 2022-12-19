package com.example.app.domain.Category.entity;

import com.example.app.global.common.base.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class Category extends BaseEntity {
    private String frontUrl;
}

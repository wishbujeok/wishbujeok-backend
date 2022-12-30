package com.example.app.domain.Category.entity;

import com.example.app.global.common.base.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class Category extends BaseEntity {
    @Column(unique = true)
    private String imgURL;
    private String backColor;
}

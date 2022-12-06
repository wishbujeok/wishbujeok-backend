package com.example.app.bujeok.entity.dto;

import com.example.app.Category.entity.Category;
import com.example.app.base.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Getter
@NoArgsConstructor
@SuperBuilder
public class BujeokDto extends BaseEntity {
    private Long userId;
    private Long replyId;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private String content;
    private String backUrl;
}

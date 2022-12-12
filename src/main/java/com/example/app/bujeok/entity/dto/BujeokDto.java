package com.example.app.bujeok.entity.dto;

import com.example.app.Category.entity.Category;
import com.example.app.base.entity.BaseEntity;
import com.example.app.reply.entity.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Getter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class BujeokDto extends BaseEntity {
    private Long userId;
    private Reply reply;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private String content;
    private String backUrl;
}

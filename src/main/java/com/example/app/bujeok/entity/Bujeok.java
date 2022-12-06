package com.example.app.bujeok.entity;

import com.example.app.base.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class Bujeok extends BaseEntity {
    private Long userId;
    private Long replyId;
    @ManyToOne
    @JoinColumn(name = "id")
    private Category category;
    private String content;
    private String backUrl;
}

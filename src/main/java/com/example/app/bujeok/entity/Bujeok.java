package com.example.app.bujeok.entity;

import com.example.app.base.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class Bujeok extends BaseEntity {
    long userId;
    long replyId;
    int category;
    String content;
    String frontUrl;
    String backUrl;
}

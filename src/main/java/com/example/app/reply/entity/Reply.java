package com.example.app.reply.entity;

import com.example.app.base.entity.BaseEntity;
import com.example.app.bujeok.entity.Bujeok;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Reply extends BaseEntity {

    @OneToOne(mappedBy = "reply")
    private Bujeok bujeok;
    // 후에 user 추가
    // private User user
    private String content;
}
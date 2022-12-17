package com.example.app.reply.entity;

import com.example.app.auth.entity.Member;
import com.example.app.base.entity.BaseEntity;
import com.example.app.bujeok.entity.Bujeok;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Reply extends BaseEntity {
    @OneToOne(mappedBy = "reply")
    private Bujeok bujeok;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;
}

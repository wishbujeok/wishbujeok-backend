package com.example.app.bujeok.entity;

import com.example.app.Category.entity.Category;
import com.example.app.auth.entity.Member;
import com.example.app.base.entity.BaseEntity;
import com.example.app.reply.entity.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class Bujeok extends BaseEntity {
//    private Long userId;
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "reply_id")
    private Reply reply;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private String content;
    private String backUrl;

    // TODO: 2022-12-08 추후에 수정방법 찾기
    public void setReply(Reply reply) {
        this.reply = reply;
    }
}

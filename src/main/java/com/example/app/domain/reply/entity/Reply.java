package com.example.app.domain.reply.entity;

import com.example.app.domain.auth.entity.Member;
import com.example.app.global.common.base.entity.BaseEntity;
import com.example.app.domain.bujeok.entity.Bujeok;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@SQLDelete(sql = "UPDATE reply SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Reply extends BaseEntity {
    @OneToOne(mappedBy = "reply") //,cascade = CascadeType.REFRESH)
    private Bujeok bujeok;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;
    private boolean deleted = Boolean.FALSE;
}

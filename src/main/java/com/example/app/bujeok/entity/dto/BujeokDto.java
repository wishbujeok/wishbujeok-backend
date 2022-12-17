package com.example.app.bujeok.entity.dto;

import com.example.app.Category.entity.Category;
import com.example.app.base.entity.BaseEntity;
import com.example.app.reply.entity.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Getter
@NoArgsConstructor
@SuperBuilder
@Setter
@ToString(callSuper = true)
public class BujeokDto {
    private Long id;
    private Long userId;
    private String reply;
    private String category;
    private String content;
    private String backUrl;
}

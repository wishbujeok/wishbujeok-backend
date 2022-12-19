package com.example.app.domain.reply.entity.dto;

import com.example.app.domain.bujeok.entity.Bujeok;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
@ToString
public class ReplyDto {
    private Long id;
    private String content;
    private Bujeok bujeok;
    //private User user;
}

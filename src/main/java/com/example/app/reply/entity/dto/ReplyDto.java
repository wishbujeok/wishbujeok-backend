package com.example.app.reply.entity.dto;

import com.example.app.bujeok.entity.Bujeok;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class ReplyDto {
    private Long id;
    private String content;
    private Bujeok bujeok;
    //private User user;
}

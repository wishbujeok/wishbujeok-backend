package com.example.app.domain.reply.entity.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReplyCreateDto {
    private String cheerUp;
    private Long otherWishId;
}

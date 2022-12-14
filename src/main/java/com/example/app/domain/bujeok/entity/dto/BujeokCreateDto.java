package com.example.app.domain.bujeok.entity.dto;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BujeokCreateDto { // 프론트 -> controller -> service
    private String content;
    private String cheerUp;
    private Long otherWishId;
}

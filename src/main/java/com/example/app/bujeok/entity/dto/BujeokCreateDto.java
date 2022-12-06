package com.example.app.bujeok.entity.dto;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BujeokCreateDto { // 프론트 -> controller -> service
    private Long category;
    private String content;
    private String cheerUp;
}

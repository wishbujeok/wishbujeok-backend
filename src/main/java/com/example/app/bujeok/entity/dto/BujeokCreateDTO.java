package com.example.app.bujeok.entity.dto;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BujeokCreateDTO { // 프론트 -> controller -> service
    private int category;
    private String content;
    private String cheerUp;
}

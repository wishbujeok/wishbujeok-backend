package com.example.app.domain.bujeok.entity.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BujeokCreateResponse {
    private String userName;
    private String otherWish;
    private Long otherWishId;
}

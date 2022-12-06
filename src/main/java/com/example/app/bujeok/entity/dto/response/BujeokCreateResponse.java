package com.example.app.bujeok.entity.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BujeokCreateResponse {
    String userName;
    String otherWish;
}

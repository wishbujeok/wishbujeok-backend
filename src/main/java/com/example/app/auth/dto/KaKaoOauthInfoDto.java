package com.example.app.auth.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KaKaoOauthInfoDto {

    @SerializedName("id")
    private Long kakaoId;

}

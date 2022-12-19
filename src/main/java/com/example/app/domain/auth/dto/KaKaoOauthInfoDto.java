package com.example.app.domain.auth.dto;

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

    @SerializedName("kakao_account")
    private KakaoAccount kakaoAccount;
    @Getter
    @Setter
    @ToString
    public static class KakaoAccount{
        private String email;
        private Profile profile;
    }
    @Getter
    @Setter
    @ToString
    public static class Profile{
        private String nickname;
    }
}

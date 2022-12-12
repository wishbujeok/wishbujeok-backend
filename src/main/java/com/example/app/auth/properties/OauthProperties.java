package com.example.app.auth.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "oauth.provider")
public class OauthProperties {

    private Kakao kakao;

    @Getter
    @Setter
    public static class Kakao {
        private String grantType;
        private String clientId;
        private String redirectUri;
        private String clientSecret;
        private String loginUri;
        private String userInfoUri;
        private Rule rule;
    }

    @Getter
    @Setter
    public static class Rule {
        private String prefix;
        private String postfix;

        public String makeFullText(String text) {
            return prefix + text + "@" + postfix;
        }

    }
}

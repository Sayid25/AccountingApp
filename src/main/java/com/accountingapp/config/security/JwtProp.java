package com.accountingapp.config.security;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(value = "spring")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtProp {
    Token token;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Token {
        String key;
        long expireTime;
    }
}
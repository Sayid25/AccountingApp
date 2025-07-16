package com.accountingapp.config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class JwtProvider {
    private final JwtProp jwtProp;

    public String generateToken(String email) {
        return Jwts
                .builder()
                .signWith(SignatureAlgorithm.HS512, jwtProp.getToken().getKey())
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProp.getToken().getExpireTime()))
                .compact();
    }

    public String getSubject(String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(jwtProp.getToken().getKey())
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isTokenValid(String token) {
        Date expireDate;
        try {
            expireDate = Jwts
                    .parser()
                    .setSigningKey(jwtProp.getToken().getKey())
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
        } catch (Exception e) {
            return false;
        }
        return !expireDate.before(new Date());
    }
}

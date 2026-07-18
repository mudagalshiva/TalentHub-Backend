package com.talenthub.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY =
            "TalentHubSecretKeyForJwtAuthentication2026TalentHub";

    public String generateToken(String email) {

        Date now = new Date();
        Date expiry = new Date(System.currentTimeMillis() + 86400000);

        System.out.println("==================================");
        System.out.println("Generating JWT");
        System.out.println("Email  : " + email);
        System.out.println("Issued : " + now);
        System.out.println("Expiry : " + expiry);

        return Jwts.builder()
                .subject(email)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();
    }

    public String extractEmail(String token) {

        return Jwts.parser()
                .verifyWith(
                        Keys.hmacShaKeyFor(SECRET_KEY.getBytes())
                )
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {

        try {
            Jwts.parser()
                    .verifyWith(
                            Keys.hmacShaKeyFor(SECRET_KEY.getBytes())
                    )
                    .build()
                    .parseSignedClaims(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
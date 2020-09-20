package com.example.toucan.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;
import java.util.function.Function;

/**
 * Utility class which help to manage JWT in application.
 * This is the only place which use <b>io.jsonwebtoken.*</b> directly.
 */
@Service
public class JwtUtil {

    private final String SECRET_KEY = "secret"; //todo change this later

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}

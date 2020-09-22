package com.example.toucan.util;

import com.example.toucan.model.dao.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Utility class which help to manage JWT in application.
 * This is the only place which use <b>io.jsonwebtoken.*</b> directly.
 */
@Service
public class JwtUtil {

    /**
     * Secret key for decode jwt.
     */
    private final String SECRET_KEY = "secret"; //todo change this later

    /**
     * This method extracts username from received JWT.
     * @param token received JWT
     * @return @see claim specified in second argument of {@link #extractClaim(String, Function)}
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * This method extracts claims from JWT using passed by {@code claimsResolver} method from {@link Claims}
     * @param token received JWT
     * @param claimsResolver parameter, which take value of returned by passed through {@code claimsResolver} method from {@link Claims} class
     * @param <T> used method from {@link Claims}
     * @return work effect of method passed in {@code claimsResolver}
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * This is the lower method in the class. This method decode JWT and returns decoded claims as {@link String}
     * @param token received JWT
     * @return decoded claims
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    /**
     * This method provides token using {@link #createToken(Map, String)}.
     * @param userDetails user, for which token will be generated
     * @return JWT in {@link String}
     */
    public String generateToken(UserDetailsImpl userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * This method generates token using data from {@link UserDetailsImpl} passed in {@link #generateToken(UserDetailsImpl)}.
     * @param claims required by {@link io.jsonwebtoken.JwtBuilder#setClaims(Map)}
     * @param subject {@code subject} is username of user, for which is the token created
     * @return JWT created as {@link String}
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }
}
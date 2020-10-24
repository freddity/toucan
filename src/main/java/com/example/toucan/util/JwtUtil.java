package com.example.toucan.util;

import com.example.toucan.service.userdetails.UserDetailsImpl;
import io.jsonwebtoken.Claims;
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
    private final static String SECRET_KEY = "4sm$0dJHEJDX!EIedl4PfPvr6pRa5gj4Gdl)ySmX%T1f$yJdiMipe0x0txa%X(H^8B585NcKB6ZUX7F0l99bV&Yvf$0puL&874Fm\n";
    private final int EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    /**
     * This method extracts username from received JWT.
     * @param token received JWT
     * @return @see claim specified in second argument of {@link #extractClaim(String, Function)}
     */
    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * This method extracts claims from JWT using passed by {@code claimsResolver} method from {@link Claims}
     * @param token received JWT
     * @param claimsResolver parameter, which take value of returned by passed through {@code claimsResolver} method from {@link Claims} class
     * @param <T> used method from {@link Claims}
     * @return work effect of method passed in {@code claimsResolver}
     */
    private static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * This is the lower method in the class. This method decode JWT and returns decoded claims as {@link String}
     * @param token received JWT
     * @return decoded claims
     */
    private static Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    /**
     * This method provides token using {@link #createToken(Map, String)} and add claims to {@link Map}.
     * @param userDetails user, for which token will be generated
     * @return JWT in {@link String}
     */
    public String generateToken(UserDetailsImpl userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities());
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
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    /**
     * This method compares username from token and username from passed {@link UserDetailsImpl} instance.
     * @param token received JWT
     * @param userDetails {@link UserDetailsImpl} with data about our user
     * @return {@code true} when username from token is equal to username from {@link UserDetailsImpl}
     */
    public static Boolean validateToken(String token, UserDetailsImpl userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * This method takes expiration date from token and compares with actually date.
     * @param token received JWT
     * @return {@code true} when token is not expired yet
     */
    private static Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * This method extracts expiration date from passed token.
     * @param token received JWT
     * @return expiration {@link Date}
     */
    private static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
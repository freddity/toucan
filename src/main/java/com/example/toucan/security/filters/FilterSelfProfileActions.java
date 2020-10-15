package com.example.toucan.security.filters;

import com.example.toucan.security.UserDetailsServiceImpl;
import com.example.toucan.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.example.toucan.util.JwtUtil.extractUsername;
import static com.example.toucan.util.JwtUtil.validateToken;

@Component
public class FilterSelfProfileActions extends OncePerRequestFilter {

    private final String AUTHORIZATION = "authorization";

    private final UserDetailsServiceImpl detailsService;
    private final JwtUtil jwtUtil;

    public FilterSelfProfileActions(UserDetailsServiceImpl detailsService) {
        this.detailsService = detailsService;
        this.jwtUtil = new JwtUtil();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String token;
        try {token = request.getHeader(AUTHORIZATION).substring(7);}
        catch (NullPointerException e) {return;}

        System.out.println(token);

        try {
            if (validateToken(token, detailsService.loadUserByUsername(extractUsername(token)))) {
                UsernamePasswordAuthenticationToken authResult = getAuthenticationByToken(token);
                SecurityContextHolder.getContext().setAuthentication(authResult);
                chain.doFilter(request, response);
            }
        } catch (SignatureException e) {
            throw new ResponseStatusException(HttpStatus.NOT_EXTENDED, "token signature is wrong");
        } catch (ExpiredJwtException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "token has expired");
        }
    }



    private UsernamePasswordAuthenticationToken getAuthenticationByToken(String token) {

        String username = extractUsername(token);
        Collection<? extends GrantedAuthority> authorities = detailsService.loadUserByUsername(extractUsername(token)).getAuthorities();
        System.out.println("FilterSelfProfileActions.getAuthenticationByToken() role = " + authorities);

        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }
}

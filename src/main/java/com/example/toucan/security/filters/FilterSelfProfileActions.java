package com.example.toucan.security.filters;

import com.example.toucan.service.userdetails.UserDetailsServiceImpl;
import com.example.toucan.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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

        if (jwtUtil.validateToken(token, detailsService.loadUserByUsername(jwtUtil.extractUsername(token)))) {
            UsernamePasswordAuthenticationToken authResult = getAuthenticationByToken(token);
            SecurityContextHolder.getContext().setAuthentication(authResult);
            chain.doFilter(request, response);
        }
    }



    private UsernamePasswordAuthenticationToken getAuthenticationByToken(String token) {

        String username = jwtUtil.extractUsername(token);
        Collection<? extends GrantedAuthority> authorities = detailsService.loadUserByUsername(jwtUtil.extractUsername(token)).getAuthorities();
        System.out.println("FilterSelfProfileActions.getAuthenticationByToken() role = " + authorities);

        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }
}

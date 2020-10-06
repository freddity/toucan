package com.example.toucan.security.filters;

import com.example.toucan.model.dao.UserDetailsImpl;
import com.example.toucan.security.UserDetailsServiceImpl;
import com.example.toucan.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.toucan.util.JwtUtil.extractUsername;
import static com.example.toucan.util.JwtUtil.validateToken;

@Component
public class FilterSelfProfileActions extends OncePerRequestFilter {

    private final String AUTHORIZATION = "authorization";
    private final String BEARER = "Bearer";

    private final UserDetailsServiceImpl detailsService;

    public FilterSelfProfileActions(UserDetailsServiceImpl detailsService) {
        this.detailsService = detailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String token;
        try {token = request.getHeader(AUTHORIZATION).substring(7);}
        catch (NullPointerException e) {return;}

        System.out.println(token);

        if (validateToken(token, detailsService.loadUserByUsername(extractUsername(token)))) {
            chain.doFilter(request, response);
        }
    }


}

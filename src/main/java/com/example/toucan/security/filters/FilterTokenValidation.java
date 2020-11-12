package com.example.toucan.security.filters;

import com.example.toucan.service.userdetails.UserDetailsServiceImpl;
import com.example.toucan.util.JwtUtil;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component
@Order(Ordered.LOWEST_PRECEDENCE-10)
public class FilterTokenValidation extends OncePerRequestFilter {

    private final UserDetailsServiceImpl detailsService;
    private final JwtUtil jwtUtil;

    public FilterTokenValidation(UserDetailsServiceImpl detailsService) {
        this.detailsService = detailsService;
        this.jwtUtil = new JwtUtil();
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith("/toucan/auth");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String token;
        try { token = request.getHeader("authorization").substring(7); } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "'authorization' header missing");
        }

        List path = Arrays.asList(request.getServletPath().split("/"));
        String pathUsername = (String) path.get(path.size()-2);
        String pathLast = (String) path.get(path.size()-1);

        if (!pathUsername.equals(jwtUtil.extractUsername(token))) {
            response.sendError(401, "It is not your profile. You are permissed to make changes only to your profile.");
            return;
        }

        if (jwtUtil.validateToken(token, detailsService.loadUserByUsername(jwtUtil.extractUsername(token)))) {
            UsernamePasswordAuthenticationToken authResult = getAuthenticationByToken(token);
            SecurityContextHolder.getContext().setAuthentication(authResult);
            chain.doFilter(request, response);return;
        }

        response.sendError(401, "You cannot be authorized.");
    }

    private UsernamePasswordAuthenticationToken getAuthenticationByToken(String token) {

        String username = jwtUtil.extractUsername(token);
        Collection<? extends GrantedAuthority> authorities = detailsService.loadUserByUsername(jwtUtil.extractUsername(token)).getAuthorities();
        System.out.println("FilterSelfProfileActions.getAuthenticationByToken() role = " + authorities);

        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }
}

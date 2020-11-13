package com.example.toucan.security.filters;

import com.example.toucan.service.userdetails.UserDetailsServiceImpl;
import com.example.toucan.util.JwtUtil;
import com.example.toucan.util.UUIDUtil;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
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

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;

    public FilterTokenValidation(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = new JwtUtil();
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        System.out.println("REQ WONT BE PASSING THROUGH THE FILTER");

        String path = request.getServletPath();
        return path.startsWith("/toucan/auth");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String token;
        try { token = request.getHeader("authorization").substring(7); } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "'authorization' header missing");
        }

        List path;
        String usernameFromPath;
        String lastFromPath;

        try {
            path = Arrays.asList(request.getServletPath().split("/"));

            usernameFromPath = (String) path.get(path.size()-2);
            lastFromPath = (String) path.get(path.size()-1);
        } catch (NullPointerException e) {
            response.sendError(400, "Bad request.");
            return;
        }

        if (!Objects.equals(usernameFromPath, jwtUtil.extractUsername(token))) {
            if (!userDetailsService.doesUserExists(usernameFromPath)) {
                response.sendError(404, "Request does not contains an username does not exists.");
                return;
            } else if (!userDetailsService.doesUserExists(jwtUtil.extractUsername(token))) {
                response.sendError(401, "Wrong token. User with username contained in token does not exists.");
                return;
            }
        } else if (!UUIDUtil.isUUID(lastFromPath) && !lastFromPath.equals("create") && !lastFromPath.equals("thumbnails")) {
            System.out.println(lastFromPath);
            response.sendError(400);
            return;
        }

        if (jwtUtil.validateToken(token, userDetailsService.loadUserByUsername(jwtUtil.extractUsername(token)))) {
            UsernamePasswordAuthenticationToken authResult = getAuthenticationByToken(token);
            SecurityContextHolder.getContext().setAuthentication(authResult);
            chain.doFilter(request, response);
            System.out.println("JNEFUIHBFEY");
            return;
        }

        response.sendError(401, "You cannot be authorized.");
    }

    private UsernamePasswordAuthenticationToken getAuthenticationByToken(String token) {

        String username = jwtUtil.extractUsername(token);
        Collection<? extends GrantedAuthority> authorities = userDetailsService.loadUserByUsername(jwtUtil.extractUsername(token)).getAuthorities();
        System.out.println("FilterSelfProfileActions.getAuthenticationByToken() role = " + authorities);

        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }
}

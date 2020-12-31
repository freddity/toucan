package com.example.toucan.security.filters;

import com.example.toucan.repository.RepositoryUser;
import com.example.toucan.util.JwtUtil;
import com.example.toucan.util.UUIDUtil;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
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

@Component
//@Order(Ordered.LOWEST_PRECEDENCE-10)
public class FilterTokenValidator extends OncePerRequestFilter {

    private final RepositoryUser repositoryUser;
    private final JwtUtil jwtUtil;

    public FilterTokenValidator(RepositoryUser repositoryUser) {
        this.repositoryUser = repositoryUser;
        this.jwtUtil = new JwtUtil();
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith("/signup") || path.startsWith("/signin");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

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

        //todo test how Objects.nonNull() works
        if (!Objects.equals(usernameFromPath, jwtUtil.extractUsername(token))) {
            if (Objects.nonNull(repositoryUser.findByUsername(usernameFromPath))) {
                response.sendError(401, "Given token not provided premission to this resource.");
                return;
            } else if (Objects.nonNull(repositoryUser.findByUsername(jwtUtil.extractUsername(token)))) {
                response.sendError(401, "Wrong token. User with username contained in token does not exists.");
                return;
            }
        } else if (!UUIDUtil.isUUID(lastFromPath) && !lastFromPath.equals("create") && !lastFromPath.equals("thumbnails")) {
            response.sendError(400);
            return;
        }

        if (jwtUtil.validateToken(token, repositoryUser.findByUsername(jwtUtil.extractUsername(token)))) {
            response.addHeader("authorization", "Bearer " + jwtUtil.generateToken(repositoryUser.findByUsername(jwtUtil.extractUsername(token))));
            filterChain.doFilter(request, response);
            return;
        }

        response.sendError(401, "You cannot be authorized.");
    }
}

package com.example.toucan.security.filters;

import com.example.toucan.service.notedetails.NoteDetailsService;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Order(Ordered.LOWEST_PRECEDENCE-9)
public class FilterUsernameId extends OncePerRequestFilter {

    private final NoteDetailsService noteDetailsService;
    private final UserDetailsService userDetailsService;

    public FilterUsernameId(NoteDetailsService noteDetailsService,
                            UserDetailsService userDetailsService) {
        this.noteDetailsService = noteDetailsService;
        this.userDetailsService = userDetailsService;
    }

    //here will start working on permissions validation for endpoints ending with UUID
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("MAIN METHOD FILTERUSERNAME ID INVOKED");

        String token = request.getHeader("authorization").substring(7);
        String verb = request.getMethod();
        List path = Arrays.asList(request.getServletPath().split("/"));
        String pathUsername = (String) path.get(path.size()-2);
        String pathLast = (String) path.get(path.size()-1);



    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String fullPath = request.getServletPath();
        List servletPath = Arrays.asList(request.getServletPath().split("/"));
        String lastFromPath = (String) servletPath.get(servletPath.size()-1);

        try {
            UUID uuid = UUID.fromString(lastFromPath);
        } catch (IllegalArgumentException e) {
            return true;
        }

        return !fullPath.startsWith("/toucan/note") || fullPath.startsWith("/toucan/auth");
    }
}

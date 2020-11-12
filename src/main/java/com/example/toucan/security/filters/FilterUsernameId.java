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

@Order(Ordered.LOWEST_PRECEDENCE-9)
public class FilterNotePermission extends OncePerRequestFilter {

    private final NoteDetailsService noteDetailsService;
    private final UserDetailsService userDetailsService;

    public FilterNotePermission(NoteDetailsService noteDetailsService,
                                UserDetailsService userDetailsService) {
        this.noteDetailsService = noteDetailsService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("authorization").substring(7);

        if (request.getServletPath().startsWith("/toucan/note/full")) {
            if () {

            }
        } else if (request.getServletPath().startsWith("/toucan/note/thumbnails")) {
            if () {

            }
        } else if (request.getServletPath().startsWith("/toucan/note/")) {
            if () {

            }
        }
        request.getMethod()
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return !path.startsWith("/toucan/note");
    }
}

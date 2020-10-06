package com.example.toucan.security.filters;

import com.example.toucan.model.dao.UserDetailsImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Component
public class FilterSelfProfileActions extends OncePerRequestFilter {

    private final String HEADER = "authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        Enumeration<String> headers = request.getHeaderNames();
        if (headers == null) return;

        while (headers.hasMoreElements()) {
            String header = headers.nextElement();
            if (header.equals(HEADER) && request.getHeader(HEADER).startsWith("Bearer")) {
                System.out.println("Header: " + header + " value:" + request.getHeader(header));
            }
        }



        chain.doFilter(request, response);
    }


}

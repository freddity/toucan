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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("### FILTER ###");

        Enumeration<String> headerNames = request.getHeaderNames();

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                if (headerNames.nextElement().matches("Bearer")) {
                    System.out.println("### Bearer found ###");
                }
            }
        }
        System.out.println("2222");

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                System.out.println("Header: " + name + " value:" + request.getHeader(name));
            }
        }


        System.out.println("33333");



        chain.doFilter(request, response);
    }


}

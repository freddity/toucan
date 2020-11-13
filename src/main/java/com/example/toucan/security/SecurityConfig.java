package com.example.toucan.security;

import com.example.toucan.security.filters.FilterUsernameId;
import com.example.toucan.security.filters.FilterTokenValidation;
import com.example.toucan.service.notedetails.NoteDetailsServiceImpl;
import com.example.toucan.service.userdetails.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final NoteDetailsServiceImpl noteDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService,
                          NoteDetailsServiceImpl noteDetailsService) {
        this.userDetailsService = userDetailsService;
        this.noteDetailsService = noteDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
            .addFilterAfter(new FilterTokenValidation(userDetailsService), BasicAuthenticationFilter.class)
            .addFilterAfter(new FilterUsernameId(noteDetailsService, userDetailsService), FilterTokenValidation.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /*@Bean
    public FilterRegistrationBean<FilterSelfProfileActions> filterRegistrationBean() {

        FilterRegistrationBean<FilterSelfProfileActions> registrationBean = new FilterRegistrationBean<>();
        FilterSelfProfileActions filter = new FilterSelfProfileActions(userDetailsService);

        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/toucan/user/*", "/toucan/note/*", "/toucan/note");
        return registrationBean;
    }*/
}
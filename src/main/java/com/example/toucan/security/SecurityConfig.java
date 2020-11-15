package com.example.toucan.security;

import com.example.toucan.repository.RepositoryNote;
import com.example.toucan.repository.RepositoryUser;
import com.example.toucan.security.filters.FilterNotePermissionProcessor;
import com.example.toucan.security.filters.FilterTokenValidator;
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

    private final NoteDetailsServiceImpl noteDetailsServiceImpl;
    private final RepositoryNote repositoryNote;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final RepositoryUser repositoryUser;

    public SecurityConfig(NoteDetailsServiceImpl noteDetailsServiceImpl, RepositoryNote repositoryNote,
                          UserDetailsServiceImpl userDetailsServiceImpl, RepositoryUser repositoryUser) {
        this.noteDetailsServiceImpl = noteDetailsServiceImpl;
        this.repositoryNote = repositoryNote;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.repositoryUser = repositoryUser;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        /*
         * For some reason FilterTokenValidator add itself to chain so I've deleted line adding it to filter chain.
         * I left only line adding FilterNotePermissionProcessor and chain works correctly.
         */
        http
            //.addFilterAfter(new FilterTokenValidator(userDetailsServiceImpl), BasicAuthenticationFilter.class)
            //.addFilterAfter(new FilterNotePermissionProcessor(noteDetailsServiceImpl, repositoryNote, userDetailsServiceImpl, repositoryUser), FilterTokenValidator.class);
              .addFilterAfter(new FilterNotePermissionProcessor(noteDetailsServiceImpl, repositoryNote, userDetailsServiceImpl, repositoryUser), BasicAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl);
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

    /*
    This bean is not necessary but may will be usefull in the future.
     */

    /*@Bean
    public FilterRegistrationBean<FilterSelfProfileActions> filterRegistrationBean() {

        FilterRegistrationBean<FilterSelfProfileActions> registrationBean = new FilterRegistrationBean<>();
        FilterSelfProfileActions filter = new FilterSelfProfileActions(userDetailsService);

        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/toucan/user/*", "/toucan/note/*", "/toucan/note");
        return registrationBean;
    }*/
}
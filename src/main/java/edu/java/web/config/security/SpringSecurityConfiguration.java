package edu.java.web.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by edwin on 18/01/17.
 */

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier(value = "userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private UsernamePasswordAuthenticationProvider authenticationProvider;

    @Autowired
    private CustomSuccessHandler customSuccessHandler;

    @Autowired
    @Qualifier(value = "accessDeniedHandler")
    private CustomAccessDeniedHandler accessDeniedHandler;

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .and()
                .authenticationProvider(authenticationProvider)
                .userDetailsService(userDetailsService)
                .formLogin().loginPage("/loginpage").loginProcessingUrl("/login").failureUrl("/loginpage?error")
                .usernameParameter("username").passwordParameter("password")
                .successHandler(customSuccessHandler)
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/loginpage")
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);

    }

}

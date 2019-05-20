package com.xqz.seckill.config;

import com.xqz.seckill.security.DBAuthenticationProvider;
import com.xqz.seckill.security.LoginPasswordEncoder;
import com.xqz.seckill.security.LoginUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(getAuthenticationProvider());
        getUserDetailsService();
        getPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/css/**", "/js/**").permitAll()
//                .antMatchers("/**").access("hasRole('USER')")
                .anyRequest().authenticated().and()
            .formLogin()
                .loginPage("/login").successForwardUrl("/hello/allusers").failureUrl("/login?error=true").permitAll().and()
                .logout().permitAll();
    }

    @Bean
    public UserDetailsService getUserDetailsService(){
        return new LoginUserDetailService();
    }

    @Bean
    public AuthenticationProvider getAuthenticationProvider(){
        return new DBAuthenticationProvider();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new LoginPasswordEncoder();
    }
}

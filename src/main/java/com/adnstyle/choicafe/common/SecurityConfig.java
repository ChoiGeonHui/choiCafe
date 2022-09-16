package com.adnstyle.choicafe.common;

import com.adnstyle.choicafe.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/error",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/**/*.jsp", "/oauth/**","/**").permitAll()
                .antMatchers("/board/**").hasAnyRole(Role.ADMIN.name(),Role.USER.name(),Role.SOCIAL.name())

                .and()
                .formLogin()
                .usernameParameter("id").passwordParameter("password")
                .loginPage("/oauth/login")
                .loginProcessingUrl("/oauth/sginIn")
                .defaultSuccessUrl("/oauth/")


                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .accessDeniedPage("/oauth/access")

                .and()
                .logout()
                .logoutSuccessUrl("/oauth/login")
                .invalidateHttpSession(true)

                .and()
                .exceptionHandling()
                .accessDeniedPage("/oauth/access");




        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();

    }
}

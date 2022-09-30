package com.adnstyle.choicafe.common;

import com.adnstyle.choicafe.domain.Role;
import com.adnstyle.choicafe.service.CustomOAuth2UserService;
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

    private final CustomOAuth2UserService customOAuth2UserService;


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
                            "/**/*.jsp", "/oauth/**").permitAll()
                    .antMatchers("/board/list/**","/board/view/detail").authenticated()
                    .antMatchers("/oauth/transform","oauth/transformMember").hasRole(Role.SOCIAL.name())
                    .antMatchers("/admin/**").hasRole(Role.ADMIN.name())
                    .antMatchers("/board/**").hasAnyRole(Role.USER.name(),Role.ADMIN.name())
                    .anyRequest().authenticated()

                .and()
                    .formLogin()
                    .usernameParameter("id").passwordParameter("password")
                    .loginPage("/oauth/login")
                    .loginProcessingUrl("/oauth/sginIn")
                    .defaultSuccessUrl("/oauth/", true)


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

        http
                    .sessionManagement()
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(false);

        http
                    .oauth2Login()
                    .defaultSuccessUrl("/board/list/list", true)
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService);

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();

    }
}

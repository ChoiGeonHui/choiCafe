package com.adnstyle.choicafe.security;

import com.adnstyle.choicafe.domain.Role;
import com.adnstyle.choicafe.jwt.JwtFilter;
import com.adnstyle.choicafe.jwt.JwtProvider;
import com.adnstyle.choicafe.oauth2.CustomOAuth2UserService2;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService2 customOAuth2UserService;

    private final LoginFailHandler loginFailHandler;

    private final LoginSuccessHandler loginSuccessHandler;

    private final JwtProvider jwtProvider;


    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic().disable();

        http
                    .authorizeRequests()
                    .antMatchers("/error",
                            "/**/*.png",
                            "/**/*.gif",
                            "/**/*.svg",
                            "/**/*.jpg",
                            "/**/*.html",
                            "/**/*.css",
                            "/**/*.js",
                            "/**/*.jsp", "/oauth/**","/sctran/**","/smsCheck/**","/static/**","/valid/**","/token/**").permitAll()
                    .antMatchers("/board/list/**","/board/view/detail").authenticated()
                    .antMatchers("/oauth/transform", "oauth/transformMember").hasRole(Role.SOCIAL.name())
                    .antMatchers("/admin/**").hasRole(Role.ADMIN.name())
                    .antMatchers("/board/**","/reply/**").hasAnyRole(Role.USER.name(),Role.ADMIN.name())
                    .anyRequest().authenticated()
                    .and()
                    .addFilterBefore(new JwtFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)
                    ;

        http
                    .formLogin()
                    .usernameParameter("id").passwordParameter("password")
                    .loginPage("/oauth/login")
                    .loginProcessingUrl("/oauth/sginIn") //해당 주소로 오는 로그인을 가로채서 요청 처리
                    .failureHandler(loginFailHandler)
                    .successHandler(loginSuccessHandler)
                    //.defaultSuccessUrl("/oauth/", true)


                .and()
                    .csrf()
                    .disable()
                    .exceptionHandling()
                    .accessDeniedPage("/oauth/access")

                .and()
                    .logout()
                    .logoutSuccessUrl("/oauth/login")
                    .deleteCookies("Authorization")//쿠키에 저장된 JWT 토크 제거
                    .invalidateHttpSession(true) //session 제거

                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/oauth/access");

        http
                    .sessionManagement()
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .invalidSessionUrl("/oauth/login")
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(false)
                    ;

        http
                    .oauth2Login()
                    .defaultSuccessUrl("/board/list/list", true)
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService);
//        http
//                .headers()
//                .frameOptions().sameOrigin();

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}

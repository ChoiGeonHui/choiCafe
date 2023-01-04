package com.adnstyle.choicafe.jwt;


import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtProvider {

    private static String secretKey = "adnstyle123";

    /** 토큰 생성 메소드 */
    public TokenInfo createToken(Authentication authentication) {
        Date now = new Date();

        Date accExpiration = new Date(now.getTime() + Duration.ofMinutes(30).toMillis()); /**만료기간 30분 설정 */
        Date expiration = new Date(now.getTime() + Duration.ofDays(1).toMillis()); /**만료기간 1일 설정 */

        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("role", authorities)
                .setIssuedAt(now) // 발급시간
                .setExpiration(accExpiration) // 만료일자
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes())) // 알고리즘, 시크릿 키
                .compact();


        String refreshToken = Jwts.builder()
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .compact();

        return TokenInfo.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }


    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        if (claims.get("role") == null) {
            throw new RuntimeException("권한정보가 없는 토큰입니다.");
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("role").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        UserDetails principal = new User(claims.getSubject(),"",authorities);
        return new UsernamePasswordAuthenticationToken(principal, "",authorities);
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parser()
                    .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }



    // 아이디 추출
    public static String getUserIdFormJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }



    private String BearerRemove(String token){
        return token.substring("Bearer ".length());
    }



}

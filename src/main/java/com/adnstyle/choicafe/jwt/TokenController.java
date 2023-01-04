package com.adnstyle.choicafe.jwt;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class TokenController {

    private final JwtProvider jwtProvider;


//    @GetMapping("/tokenCreate/{userId}")
//    public TokenResponse createToken(@PathVariable("userId")String userId) throws Exception {
//        TokenInfo token = jwtProvider.createToken(userId);
//        Claims claims = jwtProvider.parseClaims(token.getAccessToken());
//
//        TokenDataResponse tokenDataResponse = new TokenDataResponse(token, claims.getSubject(), claims.getIssuer(), claims.getExpiration().toString());
//        TokenResponse tokenResponse = new TokenResponse("200","OK", tokenDataResponse);
//
//        return tokenResponse;
//    }


    @GetMapping("/checkToken")
    public TokenResponseNoData checkToken(@RequestHeader(value = "Authorization")String token) throws Exception {
//        Claims claims = jwtProvider.parseClaims;
        TokenResponseNoData tokenResponseNoData = new TokenResponseNoData("200", "success");
        return tokenResponseNoData;
    }


    @Data
    @AllArgsConstructor
    static class TokenResponse<T> {
        private String code;
        private String msg;
        private T data;
    }


    @Data
    @AllArgsConstructor
    static class TokenResponseNoData<T> {
        private String code;
        private String msg;
    }

    @Data
    @AllArgsConstructor
    static class TokenDataResponse {
        private String token;
        private String subject;
        private String issued_time;
        private String expired_time;
    }

}

package com.adnstyle.choicafe.service;

import com.adnstyle.choicafe.domain.GhMember;
import com.adnstyle.choicafe.jwt.JwtProvider;
import com.adnstyle.choicafe.repository.maindb.BackOfficeRepository;
import com.adnstyle.choicafe.repository.maindb.GhMemberRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BackOfficeService {

    private final JwtProvider jwtProvider;

    private final GhMemberRepository ghMemberRepository;

    private final BackOfficeRepository backOfficeRepository;


    @Transactional
    public Map<String, Object> getResult(HttpServletRequest request, Long adminSeq,String menuName) {

        Cookie cookies[] = request.getCookies();

        //jwt Filter에서 토큰유효성을 확인하므로 여기서 관련 로직을 구현할 필요 없다.
        String token = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("Authorization")) {//Authorization 이름을 가진 쿠키 가져오기
                token = cookie.getValue();
            }
        }

        Claims claims = jwtProvider.parseClaims(token);
        String id = claims.getSubject();
        String role = String.valueOf(claims.get("role"));
        Map<String, Object> map = new HashMap<>();
        map.put("page", "access");
        map.put("result", "");
        map.put("result2", "");


        if (ghMemberRepository.checkAuthorization(id,role,menuName) <= 0) { //해당 권한을 가지고 있는 사용자 여부
            map.put("page", "access");
            return map;
        }

        if (menuName.equals("sys")) {
            List<GhMember> list = ghMemberRepository.selectMemberList();
            map.put("page", menuName);
            map.put("result", list);
            return map;

        }
        else if (menuName.equals("sysEdit")) {
            GhMember ghMember = new GhMember();
            ghMember.setSeq(adminSeq);
            ghMember = ghMemberRepository.selectMember(ghMember);
            map.put("page", menuName);
            map.put("result", ghMember); //선택한 사용자 정보
            map.put("result2", backOfficeRepository.selectList()); //bo 메뉴 리스트
            return map;
        }
        else {
            return null;
        }
    }



}

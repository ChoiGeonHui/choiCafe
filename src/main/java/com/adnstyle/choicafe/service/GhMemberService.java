package com.adnstyle.choicafe.service;


import com.adnstyle.choicafe.common.MemberDetail;
import com.adnstyle.choicafe.domain.GhMember;
import com.adnstyle.choicafe.domain.Role;
import com.adnstyle.choicafe.repository.maindb.GhMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GhMemberService implements UserDetailsService {//UserDetailsService : spring Security 사용시 필수로 상속받아야하는 인터 페이스

    private final GhMemberRepository ghMemberRepository;

    private final BCryptPasswordEncoder encoder;

    public int selectCount() {
        return ghMemberRepository.selectCount();

    }

    public GhMember selectMember (GhMember ghMember) {
        return ghMemberRepository.selectMember(ghMember);
    }

    
    public List<GhMember> selectMemberList () {
        return ghMemberRepository.selectMemberList();
    }



    /**
     * 아이디 중복 확인
     * @param ghMember
     * @return
     */
    public String selectMemberById (GhMember ghMember) {
        GhMember chkMember = ghMemberRepository.selectMember(ghMember);

        if (chkMember == null) {
            return "success";
        } else {
            return "is it";
        }
    }


    /**
     * 회원 가입
     * @param ghMember
     * @return
     */
    @Transactional
    public String insertMember(GhMember ghMember) {

        GhMember testMember = new GhMember();
        testMember.setEmail(ghMember.getEmail());

        //소셜 또는 이미 회원 가입된 이메일일 경우 가입을 못하게 한다.
        if (ghMemberRepository.selectMember(testMember) != null) {
            return "hasEmail";
        }

        ghMember.setRole(Role.USER.getKey());
        String password = ghMember.getPassword();
        String encPW = encoder.encode(password);

        ghMember.setPassword(encPW);

        int i = ghMemberRepository.insertMember(ghMember);

        if (i > 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * 소셜가입자 -> 일반회원으로 변경
     * @param ghMember 소셜 가입자 정보
     * @return
     */
    @Transactional
    public String updateMember(GhMember ghMember) {

        String password = ghMember.getPassword();
        String encPW = encoder.encode(password);

        ghMember.setPassword(encPW);

        int chk = ghMemberRepository.updateMember(ghMember);
        if(chk > 0){
            return "success";
        }
        return "fail";
    }

    /**
     * 비밀번호 찾기,변경하기
     * @param ghMember
     * @return
     */
    @Transactional
    public String updatePassword(GhMember ghMember) {

        if(ghMemberRepository.selectMember(ghMember) == null) {
            return "notUser";
        }

        String encPW = encoder.encode(ghMember.getPassword());

        ghMember.setPassword(encPW);

        int chk = ghMemberRepository.updatePassword(ghMember);

        if (chk > 0) {
            return "success";
        } else {
            return "fail";
        }
    }


    /**
     * 사용자의 아이디를 찾아서 암호화된 패스워드랑 매칭 확인및 인증하는 메서드
     * @param id
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        GhMember ghMember = new GhMember();
        ghMember.setId(id);
        ghMember = ghMemberRepository.selectMember(ghMember);

        if (ghMember == null) {
            throw new UsernameNotFoundException("User not authorizied");
        }
        return new MemberDetail(ghMember);
    }

    @Transactional
    public void failCount(GhMember ghMember) {
        ghMemberRepository.failCount(ghMember);
    }

    @Transactional
    public void lockMember (GhMember ghMember) {
        ghMemberRepository.lockMember(ghMember);
    }


    @Transactional
    public void unLockMember (GhMember ghMember) {

    }

}

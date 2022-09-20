package com.adnstyle.choicafe.service;


import com.adnstyle.choicafe.domain.GhMember;
import com.adnstyle.choicafe.domain.Role;
import com.adnstyle.choicafe.repository.GhMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GhMemberService implements UserDetailsService {
    
    //UserDetailsService : spring Security 사용시 필수로 상속받아야하는 인터 페이스

    private final GhMemberRepository ghMemberRepository;

    private final BCryptPasswordEncoder encoder;

    public int selectCount() {
        return ghMemberRepository.selectCount();

    }

    public String selectMemberById (GhMember ghMember) {
        GhMember chkMember = ghMemberRepository.selectMember(ghMember);

        if (chkMember == null) {
            return "success";
        } else {
            return "is it";
        }
    }



    @Transactional
    public String insertMember(GhMember ghMember){

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
        ghMember.getAuthorities();
        return ghMember;
    }
}

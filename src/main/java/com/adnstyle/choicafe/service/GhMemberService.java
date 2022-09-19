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

    private final GhMemberRepository ghMemberRepository;

    private final BCryptPasswordEncoder encoder;

    public int selectCount() {
        return ghMemberRepository.selectCount();

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

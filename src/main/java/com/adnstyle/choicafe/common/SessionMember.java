package com.adnstyle.choicafe.common;


import com.adnstyle.choicafe.domain.GhMember;
import com.adnstyle.choicafe.domain.Role;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionMember implements Serializable {

    private Long seq;
    private String name, email;

    private String role;

    public SessionMember(GhMember ghMember) {
        this.seq = ghMember.getSeq();
        this.name = ghMember.getName();
        this.email = ghMember.getEmail();
        this.role = ghMember.getRole();
    }


}

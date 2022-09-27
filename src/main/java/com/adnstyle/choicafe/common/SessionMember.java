package com.adnstyle.choicafe.common;


import com.adnstyle.choicafe.domain.GhMember;
import com.adnstyle.choicafe.domain.Role;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
public class SessionMember implements Serializable {

    private Long seq;

    private String id, password, name, email;

    private String role;

    private String delYN;

    private Date createdMember;

    public SessionMember(GhMember ghMember) {
        this.seq = ghMember.getSeq();
        this.id = ghMember.getId();
        this.password = ghMember.getPassword();
        this.name = ghMember.getName();
        this.email = ghMember.getEmail();
        this.role = ghMember.getRole();
        this.delYN = ghMember.getRole();
        this.createdMember = ghMember.getCreatedMember();
    }


}

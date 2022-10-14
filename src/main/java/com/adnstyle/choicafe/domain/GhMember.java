package com.adnstyle.choicafe.domain;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("ghMember")
@Data
@NoArgsConstructor
public class GhMember {

    private Long seq;

    private String id;

    private String password;

    private String name;

    private String email;

    private String phone;

    private String role;

    private String provider;

    private String providerId;

    private String delYN;

    private Date createdMember;


    @Builder
    public GhMember(String name, String email, String role,String provider, String providerId) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }

}

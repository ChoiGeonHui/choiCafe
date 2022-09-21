package com.adnstyle.choicafe.repository;


import com.adnstyle.choicafe.domain.GhMember;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhMemberRepository {

    int selectCount();


    GhMember selectMember(GhMember ghMember);

    int insertMember(GhMember ghMember);


    int insertSocialMember(GhMember ghMember);


}

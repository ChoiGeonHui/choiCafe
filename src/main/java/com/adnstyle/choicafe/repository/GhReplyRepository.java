package com.adnstyle.choicafe.repository;


import com.adnstyle.choicafe.domain.GhReply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GhReplyRepository {


    List<GhReply> selectReplyList(GhReply ghReply);

    int insertReply(GhReply ghReply);

}

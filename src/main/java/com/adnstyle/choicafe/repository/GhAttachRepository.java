package com.adnstyle.choicafe.repository;

import com.adnstyle.choicafe.domain.GhAttach;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GhAttachRepository {

    int selectAttachCount();

    List<GhAttach> selectAttach(GhAttach ghAttach);

    int insertAttach(List<GhAttach> ghAttachList);

    int deleteAttach(GhAttach ghAttach);


}

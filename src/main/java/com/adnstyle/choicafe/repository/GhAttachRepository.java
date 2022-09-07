package com.adnstyle.choicafe.repository;

import com.adnstyle.choicafe.domain.GhAttach;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhAttachRepository {

    int selectAttachCount();

    int insertAttach(GhAttach ghAttach);

}

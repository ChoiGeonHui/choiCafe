package com.adnstyle.choicafe.repository;

import com.adnstyle.choicafe.domain.GhAttach;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhAttachRepository {

    int selectAttachCount();

    GhAttach selectAttach(GhAttach ghAttach);

    int insertAttach(GhAttach ghAttach);

    int deleteAttach(GhAttach ghAttach);


}

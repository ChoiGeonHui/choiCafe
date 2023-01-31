package com.adnstyle.choicafe.repository.maindb;

import com.adnstyle.choicafe.domain.BackOffice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BackOfficeRepository {

    List<BackOffice> selectList();


}

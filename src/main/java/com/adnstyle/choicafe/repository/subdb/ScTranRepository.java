package com.adnstyle.choicafe.repository.subdb;


import com.adnstyle.choicafe.domain.ScTran;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.Resource;

@Mapper
@Resource(name = "subDBDataSource")
public interface ScTranRepository {

    int insertScTran(ScTran scTran);

}

package com.adnstyle.choicafe.repository.subdb;

import com.adnstyle.choicafe.domain.ScLog;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.Resource;
import java.util.List;

@Mapper
@Resource(name = "subDBDataSource")
public interface ScLogRepository {

    List<ScLog> selectList();


}

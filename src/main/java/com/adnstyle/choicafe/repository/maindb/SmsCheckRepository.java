package com.adnstyle.choicafe.repository.maindb;

import com.adnstyle.choicafe.domain.SmsCheck;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmsCheckRepository {



    SmsCheck selectsmsCheck (SmsCheck smsCheck);

    int insertsmsCheck(SmsCheck smsCheck);


}

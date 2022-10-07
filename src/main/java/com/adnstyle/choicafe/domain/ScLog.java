package com.adnstyle.choicafe.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("scLog")
@Data
@NoArgsConstructor
public class ScLog {

    private Long trNum;
    private Date trSendDate;
    private String trPhone;
    private String trCallBack;
    private String trMsg;



}

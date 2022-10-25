package com.adnstyle.choicafe.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("smsCheck")
@Data
@NoArgsConstructor
public class SmsCheck {

    private Long seq;

    private String phone;

    private String checkNumber;

}

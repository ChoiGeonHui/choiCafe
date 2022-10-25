package com.adnstyle.choicafe.service;

import com.adnstyle.choicafe.domain.SmsCheck;
import com.adnstyle.choicafe.repository.maindb.SmsCheckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsCheckService {

    private final SmsCheckRepository smsCheckRepository;


    public String selectSmsCheck (SmsCheck smsCheck) {

        if (smsCheckRepository.selectsmsCheck(smsCheck) != null ) {
            return "success";
        } else {
            return "fail";
        }
    }


    public String insertSmsCheck (SmsCheck smsCheck) {
        int chk = smsCheckRepository.insertsmsCheck(smsCheck);

        if (chk > 0) {
            return "success";
        } else {
            return "fail";
        }

    }



}

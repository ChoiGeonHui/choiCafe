package com.adnstyle.choicafe.service;

import com.adnstyle.choicafe.domain.ScTran;
import com.adnstyle.choicafe.repository.subdb.ScTranRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScTranService {


    private final ScTranRepository scTranRepository;

    @Transactional
    public String insertScTran(ScTran scTran) {

        int i = scTranRepository.insertScTran(scTran);

        if (i > 0) {
            return "success";
        } else {
            return "fail";
        }

    }


}

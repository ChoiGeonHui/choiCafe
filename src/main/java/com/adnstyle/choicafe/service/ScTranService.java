package com.adnstyle.choicafe.service;

import com.adnstyle.choicafe.domain.ScTran;
import com.adnstyle.choicafe.repository.subdb.ScTranRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class ScTranService {

    private final ScTranRepository scTranRepository;

    @Transactional
    public String insertScTran(ScTran scTran) {

        /** 무작위 인증번호 6자리 생성 */
        Random random = new Random();
        int cnt = 0;
        String ranNum = "";
        while (cnt < 6) {
            int r = random.nextInt(10);
            ranNum = ranNum + r;
            cnt ++;
        }

        scTranRepository.insertScTran(scTran, ranNum);

        return ranNum;

    }


}

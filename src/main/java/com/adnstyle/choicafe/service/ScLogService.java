package com.adnstyle.choicafe.service;

import com.adnstyle.choicafe.domain.ScLog;
import com.adnstyle.choicafe.repository.subdb.ScLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScLogService {

    private final ScLogRepository scLogRepository;

    public List<ScLog> selectList(){
        return scLogRepository.selectList();
    }

}

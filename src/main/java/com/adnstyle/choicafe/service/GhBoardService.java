package com.adnstyle.choicafe.service;


import com.adnstyle.choicafe.domain.GhBoard;
import com.adnstyle.choicafe.repository.GhBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GhBoardService {

    private final GhBoardRepository ghBoardRepository;

    @Transactional
    public int selectCount(){
        return ghBoardRepository.selectCount();
    }

    @Transactional
    public List<GhBoard> selectBoardList(){
        return ghBoardRepository.selectBoardList();
    }

    @Transactional
    public int insertBoard(GhBoard ghBoard){
        if(ghBoard.getSeq() == null || ghBoard.getSeq() == 0) {
            return ghBoardRepository.insertBoard(ghBoard);
        } else {
            return ghBoardRepository.updateBoard(ghBoard);
        }
    }



    @Transactional
    public int deleteBoard(Long seq){
        return ghBoardRepository.deleteBoard(seq);
    }


}

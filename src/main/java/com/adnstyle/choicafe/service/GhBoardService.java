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
    public GhBoard selectGhBoardBySeq(Long seq){
        return ghBoardRepository.selectGhBoardBySeq(seq);
    }

    @Transactional
    public List<GhBoard> selectBoardList(String searchWoard){
        return ghBoardRepository.selectBoardList(searchWoard);
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
    public String deleteBoard(Long seq){
        int i= ghBoardRepository.deleteBoard(seq);

        if (i >0){
            return "success";
        } else {
            return "fail";
        }


    }


}

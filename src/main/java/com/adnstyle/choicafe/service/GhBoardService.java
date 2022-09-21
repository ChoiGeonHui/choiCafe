package com.adnstyle.choicafe.service;


import com.adnstyle.choicafe.domain.GhBoard;
import com.adnstyle.choicafe.repository.GhBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GhBoardService {

    private final GhBoardRepository ghBoardRepository;

    private final GhAttachService ghAttachService;

    @Transactional
    public int selectCount(GhBoard ghBoard) {
        return ghBoardRepository.selectCount(ghBoard);
    }


    /**
     * 게시물 상세보기
     * @param seq 게시물 식별자
     * @return
     */
    public GhBoard selectGhBoardBySeq(Long seq) {
        GhBoard ghBoard = ghBoardRepository.selectGhBoardBySeq(seq);
        ghBoard.setGhAttachList(ghAttachService.selectAttach("ghBoard", ghBoard.getSeq()));
        updateViewCount(ghBoard);  //조회수 증가
        return ghBoard;
    }

    @Transactional
    public List<GhBoard> selectBoardList(GhBoard ghBoard) {
        return ghBoardRepository.selectBoardList(ghBoard);
    }


    /**
     * 게시물 수정, 등록
     * @param ghBoard 게시물
     * @param fileList 업로드할 파일
     * @return
     */
    @Transactional
    public int cuBoard(GhBoard ghBoard,List<MultipartFile> fileList) {
        if (ghBoard.getSeq() == null || ghBoard.getSeq() == 0) { //식별자 존재 여부에 따라 등록, 수정이 나뉜다.
            ghBoardRepository.insertBoard(ghBoard);
            ghAttachService.save(ghBoard.getSeq(), "ghBoard", fileList);
            return 1;
        } else {
            ghBoardRepository.updateBoard(ghBoard);
            ghAttachService.update(ghBoard.getSeq(),"ghBoard", ghBoard.getGhAttachList(),  fileList);
            return 1;
        }
    }


    /**
     * 조회수 증가
     * @param ghBoard
     */
    @Transactional
    public void updateViewCount (GhBoard ghBoard) {
        ghBoardRepository.updateViewConunt(ghBoard);
    }


    /**
     * 게시물 삭제상태로 변경
     * @param seq 식별자
     * @return
     */
    @Transactional
    public String deleteBoard(List<Long> seq) {
        int i = ghBoardRepository.deleteBoard(seq);

        if (i > 0) {
            return "success";
        } else {
            return "fail";
        }

    }


}

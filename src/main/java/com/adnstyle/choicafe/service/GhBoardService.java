package com.adnstyle.choicafe.service;


import com.adnstyle.choicafe.common.SessionMember;
import com.adnstyle.choicafe.domain.GhBoard;
import com.adnstyle.choicafe.repository.maindb.GhBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GhBoardService {

    private final HttpSession httpSession;

    private final GhBoardRepository ghBoardRepository;

    private final GhAttachService ghAttachService;

    private final GhReplyService ghReplyService;



    public int selectCount(GhBoard ghBoard) {
        return ghBoardRepository.selectCount(ghBoard);
    }


    public String checkBoardAccess (String boardHandle, GhBoard ghBoard) {

        SessionMember ghMember = (SessionMember) httpSession.getAttribute("user");

        if (ghBoard.getDelYN().equals("Y")) { // 삭제된 게시물 접근 시
            if (!ghMember.getRole().equals("ROLE_ADMIN")) {
                return "board/boardDeletedPage";
            }
        }

        if (boardHandle.equals("detail")) {
            return "board/boardView"; //게시물 보기 페이지로 이동
        }

        // 다른 사용자가 타인이 작성한 게시물을 무단으로 수정하려는 것을 막는다.
        // 사용자 식별자와 게시물 제작자의 식별자가 같아햐 함. 또는 사용자가 관리자일 경우 수정 가능
        if (ghMember.getSeq().equals(Long.valueOf(ghBoard.getCreatedBy()))) {

            return "board/boardInsertUpdate";

        }  else if (ghMember.getRole().equals("ROLE_ADMIN")) {

            return "board/boardInsertUpdate";

        } else {
            return "oauth/access";
        }

    }


    /**
     * 게시물 상세보기,수정   - 게시물의 해당 데이터 가져오기
     * @param seq 게시물 식별자
     * @return
     */
    @Transactional
    public GhBoard selectGhBoardBySeq(Long seq, String boardHandle) {
        GhBoard ghBoard = ghBoardRepository.selectGhBoardBySeq(seq);
        if (ghBoard.getDelYN().equals("N")){
            ghBoard.setGhAttachList(ghAttachService.selectAttach("ghBoard", ghBoard.getSeq()));
            ghBoard.setGhReplyList(ghReplyService.selectReplyList(ghBoard.getSeq()));
        }

        //상세보기시 증가하는 조회수 (수정페이지는 증가 안 함)
        if (boardHandle.equals("detail")){
            updateViewCount(ghBoard);  //조회수 증가
        }
        return ghBoard;
    }

    /**
     * 게시물 리스트 보기
     */
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
    public int cuBoard(GhBoard ghBoard, List<MultipartFile> fileList, HttpServletRequest request) {

        String referer = request.getHeader("REFERER");
        if (referer == null || referer.length() == 0) {
            log.debug("no referer. Who are you? : "+ referer); // 비정상적인 요청
            return 0;
        } else {
            log.debug("referer is not null: "+ referer); // 정상적인 요청
        }


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

    /**
     * 삭제된 게시물 원상복구
     * @param ghBoard
     * @return
     */
    @Transactional
    public String deleteRollback (GhBoard ghBoard) {
        int i = ghBoardRepository.deleteRollback(ghBoard);
        if (i > 0) {
            return "success";
        } else {
            return "fail";
        }

    }


}

package com.adnstyle.choicafe.service;


import com.adnstyle.choicafe.domain.GhReply;
import com.adnstyle.choicafe.repository.GhReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GhReplyService {

    private final GhReplyRepository ghReplyRepository;


    public List<GhReply> selectReplyList(Long boardSeq) {
        GhReply ghReply = new GhReply();
        ghReply.setBoardSeq(boardSeq);
        return ghReplyRepository.selectReplyList(ghReply);
    }


    /**
     * 댓글 작성
     * @param ghReply
     * @return
     */
    public String insertRely(GhReply ghReply) {
        int chk = ghReplyRepository.insertReply(ghReply);

        if (chk > 0) {
            return "success";
        } else {
            return "fail";
        }

    }
    public String deleteRely(GhReply ghReply) {
        int chk = ghReplyRepository.deleteReply(ghReply);

        if (chk > 0) {
            return "success";
        } else {
            return "fail";
        }

    }



}

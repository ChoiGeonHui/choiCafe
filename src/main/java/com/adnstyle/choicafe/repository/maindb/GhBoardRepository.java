package com.adnstyle.choicafe.repository.maindb;

import com.adnstyle.choicafe.domain.GhBoard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GhBoardRepository {


    int selectCount(GhBoard ghBoard);

    List<GhBoard> selectBoardList(GhBoard ghBoard);

    GhBoard selectGhBoardBySeq(Long seq);

    int insertBoard(GhBoard ghBoard);

    int updateBoard(GhBoard ghBoard);

    int updateViewConunt(GhBoard ghBoard);

    int deleteBoard(List<Long> seq);

    int deleteRollback(GhBoard ghBoard);

}

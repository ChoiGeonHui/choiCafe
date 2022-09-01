package com.adnstyle.choicafe.repository;

import com.adnstyle.choicafe.domain.GhBoard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GhBoardRepository {


    int selectCount();

    List<GhBoard> selectBoardList();

    int insertBoard(GhBoard ghBoard);

    int updateBoard(GhBoard ghBoard);

    int deleteBoard(Long seq);

}

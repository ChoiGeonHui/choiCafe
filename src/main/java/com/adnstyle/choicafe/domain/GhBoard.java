package com.adnstyle.choicafe.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("ghBoard")
@Data
@EqualsAndHashCode(callSuper = false)
public class GhBoard {

    // 식별자
    private Long seq;

    // 부모식별자
    private Long parentSeq;

    // 제목
    private String title;

    // 내용
    private String content;

    // 조회수
    private int viewCount;

    // 삭제여부
    private String delYN;

    // 작성자
    private String createdBy;

    //작성일
    private Date createdDate;

    //수정자
    private String modifiedBy;

    //수정일
    private Date modifiedDate;
}

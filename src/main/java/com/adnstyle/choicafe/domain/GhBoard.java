package com.adnstyle.choicafe.domain;


import com.adnstyle.choicafe.common.Criteria;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Alias("ghBoard")
@Data
@EqualsAndHashCode(callSuper = false)
public class GhBoard extends Criteria implements Serializable {

    // 식별자
    private Long seq;

    // 부모식별자
    private Long parentSeq;

    //답글 소속그룹
    private String groupOrd;

    //답글 깊이
    private int depth;

    // 제목
    private String title;

    // 내용
    private String content;

    // 조회수
    private int viewCount;

    // 삭제여부
    private String delYN;

    // 작성자 식별자
    private String createdBy;


    private String createdName;

    //작성일
    private Date createdDate;

    //수정자
    private String modifiedBy;

    //수정일
    private Date modifiedDate;

    //파일
    private List<GhAttach> ghAttachList;

}

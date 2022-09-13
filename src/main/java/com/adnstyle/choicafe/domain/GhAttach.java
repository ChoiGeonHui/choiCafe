package com.adnstyle.choicafe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("ghAttach")
@Data
@EqualsAndHashCode(callSuper = false)
public class GhAttach {

    //식별자
    private Long seq;
    
    //참조 테이블 형식
    private String tableType;
    
    //참조 테이블 식별자
    private Long tableSeq;
    
    //원본 파일명
    private String displayName;
    
    //파일 저장명
    private String savedName;
    
    //파일 저장 경로
    private String savedDir;
    
    //파일 형식
    private String type;
    
    //파일 크기
    private Long size;
    
    //작성자
    private Long createdBy;
    
    //작성일
    private Date createdDate;

    //파일 상태
    private String status;


}

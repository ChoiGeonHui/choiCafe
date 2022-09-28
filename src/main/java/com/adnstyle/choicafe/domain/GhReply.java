package com.adnstyle.choicafe.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("ghReply")
@Data
@NoArgsConstructor
public class GhReply {


    //식별자
    private Long seq;

    //부모식별자
    private Long parentSeq;

    //게시물 식별자
    private Long boardSeq;

    //내용
    private String content;

    //작성자 식별자
    private Long memberSeq;

    //작성자 이름
    private String memberName;

    //삭제 여부
    private String delYN;

    //생성일자
    private Date createdDate;
    
    //댓글 소속그룹
    private String groupOrd;

    //댓글 깊이
    private int depth;
    


}

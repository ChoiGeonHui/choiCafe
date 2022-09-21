package com.adnstyle.choicafe.common;

import lombok.Data;

@Data
public class Criteria {

    private String searchName; //검색 속성

    private String searchWord; //검색 값

    private int page; // 현재 페이지 번호

    private int perPageNum; //페이지 당 보여줄 게시물 갯수

    private int PageStart;

    public Criteria(){
        this.page = 1;
        this.perPageNum = 10;
//        this.PageStart = setPageStart();
    }


    public int setPageStart() {
        // 특정 페이지의 범위를 정하는 구간, 현재 페이지의 게시글 시작 번호
        // 0 ~ 10 , 10 ~ 20 이런식으로
        this.PageStart = (this.page - 1) * perPageNum;
        return this.PageStart;
    }


    public void setPage(int page){
        //특정 페이지의 게시글 시작번호, 게시글 시작 행 번호
        if (page <= 0) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }


    public void setPerPageNum(int pageCount){
        //페이지당 보여줄 게시글의 갯수
        int cnt = this.perPageNum;
        if (pageCount != cnt) {
            this.perPageNum = cnt;
        } else {
            this.perPageNum = pageCount;
        }
    }


}

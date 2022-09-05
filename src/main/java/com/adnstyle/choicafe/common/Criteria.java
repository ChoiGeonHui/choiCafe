package com.adnstyle.choicafe.common;

import lombok.Data;

@Data
public class Criteria {

    private String searchName;

    private String searchWord;

    private int page;

    private int perPageNum;

    public Criteria(){
        this.page = 1;
        this.perPageNum = 5;
    }


    // 한 페이지당 보여줄 게시글 갯수
    public int getPageStart(){
        return (this.page - 1)*perPageNum;
    }


    //특정 페이지의 게시글 시작번호, 게시글 시작 행 번호
    public void setPage(int page){
        if (page <= 0){
            this.page = 1;
        } else {
            this.page = page;
        }
    }


    public void setPerPageNum(int pageCount){
        int cnt = this.perPageNum;
        if (pageCount != cnt){
            this.perPageNum = cnt;
        } else {
            this.perPageNum = pageCount;
        }

    }

    @Override
    public String toString(){
        return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
    }




}

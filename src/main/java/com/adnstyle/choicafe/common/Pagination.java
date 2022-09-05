package com.adnstyle.choicafe.common;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Pagination {


    private int startPage; //화면 시작 번호
    private int endPage; //화면 끝 번호
    private boolean prev; //이전 페이지 버튼 활성화 여부
    private boolean next; // 다음 페이지 버튼 활성화 여부

    private int totalCount; // 게시물 리스트 갯수
    private int displayPageNum = 3; //한번에 보여줄 페이지 수

    private Criteria criteria;


    public void setTotalCount(int totalCount){
        this.totalCount = totalCount;
        pagingData();
    }

    private void pagingData(){
        endPage = (int)Math.ceil(criteria.getPage() / (double) displayPageNum) * displayPageNum;
        // endPage = (현재 페이지 번호 / 화면에 보여질 페이지 번호의 개수) * 화면에 보여질 페이지 번호의 개수
        startPage = (endPage - displayPageNum) + 1; // startPage = (끝 페이지 번호 - 화면에 보여질 페이지 번호의 개수) + 1

        int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getPerPageNum()));
        if(endPage > tempEndPage) {
            endPage = tempEndPage;
            // 마지막 페이지 번호 = 총 게시글 수 / 한 페이지당 보여줄 게시글의개수
        }

        prev = startPage == 1 ? false : true; // 이전 버튼 생성 여부 = 시작 페이지 번호가 1과 같으면 false, 아니면 true
        next = endPage * criteria.getPerPageNum() >= totalCount ? false : true;
        // 다음 버튼 생성 여부 = 끝 페이지 번호 * 한 페이지당 보여줄 게시글의 개수가 총 게시글의 수보다
        // 크거나 같으면 false, 아니면 true

    }




}

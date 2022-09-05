<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<div class="text-center">


    <div class="mb-2">
        <div class="d-flex flex-row-reverse">
            <a class="btn btn-primary text-white ml-3" href="/board/create"> 등록</a>
            <input type="button" id="btnSearch" class="btn btn-secondary ml-1" value="검색">
            <input type="text" id="search" name="search" value="${ghBoard.searchWord}">
            <select id="searchName" name="searchName" class="mr-1">
                <option value="all">전체</option>
                <option value="title">제목</option>
                <option value="content">내용</option>
            </select>
        </div>

    </div>


    <table class="table">
        <thead>
        <tr>
            <th class="col-1">번호</th>
            <th class="col-5">제목</th>
            <th class="col-1">조회</th>
            <th class="col-2">작성자</th>
            <th class="col-2">날짜</th>
            <th class="col-1">삭제</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ghBoardList}" var="list">
                <tr>
                    <td>${list.seq}</td>
                    <td><a href="/board/detail?seq=${list.seq}">${list.title}</a></td>
                    <td>${list.viewCount}</td>
                    <td>${list.createdBy}</td>
                    <td>
                        <fmt:formatDate value="${list.createdDate}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>
                        <input type="button" class="btn btn-danger btnDel" data-seq='${list.seq}' value="삭제">
                    </td>
                </tr>
        </c:forEach>

        </tbody>
    </table>

    <ul class="paging">
        <c:if test="${paging.prev}">
            <span><a href='/board/list?page=${paging.startPage-1}' data-page-number='${paging.startPage-1}' class="btn btn-white btnPage">이전</a></span>
        </c:if>
        <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="num">
            <span><a href='/board/list?page=${num}' data-page-number='${num}' class="btn btn-white btnPage">${num}</a></span>
        </c:forEach>
        <c:if test="${paging.next && paging.endPage>0}">
            <span><a href='/board/list?page=${paging.endPage+1}' data-page-number='${paging.endPage+1}' class="btn btn-white btnPage">다음</a></span>
        </c:if>
    </ul>


</div>
<script>
    $(document).ready(function (){

        $('.btnPage').on('click',function (e){
            e.preventDefault(); //href 이동 안함
            let pageNumber = $(this).data('page-number');
            let searchWord = $('#search').val();
            let searchName = $('#searchName').val();
            alert('pageNumber = ' + pageNumber + ". searchWord =" + searchWord + ". searchName =" + searchName);

            if (searchWord != null && searchWord != ''){ //검색 값이 있을 경우
                location.href = '/board/list?page=' + pageNumber + '&searchWord='+searchWord+'&searchName='+searchName;
            } else {
                location.href = '/board/list?page=' + pageNumber;
            }

        });


        $('#btnSearch').on('click',function (){
            let searchWord = $('#search').val();
            let searchName = $('#searchName').val();

            if (searchWord == '' || searchWord == null){
                alert('최소 1글자 이상 입력해주세요.');
                return;
            }
            alert('values : '+ searchWord+" "+searchName);
            location.href = '/board/list?searchWord='+searchWord+'&searchName='+searchName;
        });


        $('.btnDel').on('click',function (){
            let seq = $(this).data('seq');
            if(confirm('삭제 하시겠습니까?')){
                alert('seq : '+ seq);
                $.ajax({
                    type:"POST",
                    data:{'seq':seq},
                    url:"/board/delete",
                    success:function (data){
                        if (data.result=='success'){
                            alert('삭제를 완료하였습니다.');
                            location.reload();
                        } else {
                            alert('오류발생');
                        }
                    },
                    error: function (){
                        alert('에러발생');
                    }
                })
            }
        });

    });

</script>
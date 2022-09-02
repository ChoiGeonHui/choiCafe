<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<div class="text-center">


    <div class="mb-2">
        <div class="d-flex flex-row-reverse">
            <a class="btn btn-primary text-white ml-3" href="/board/create"> 등록</a>
            <input type="button" id="btnSearch" class="btn btn-secondary ml-1" value="검색">
            <input type="text" id="search" name="search">
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


</div>
<script>
    $(document).ready(function (){

        $('#btnSearch').on('click',function (){
            let searchWord = $('#search').val();

            if (searchWord == '' || searchWord == null){
                alert('최소 1글자 이상 입력해주세요.');
                return;
            }
            alert(searchWord);
            location.href = '/board/list?searchWord='+searchWord;
        })


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
        })

    })

</script>
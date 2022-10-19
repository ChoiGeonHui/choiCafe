<%--
  Created by IntelliJ IDEA.
  User: adnstyle1153
  Date: 2022-09-26
  Time: 오후 5:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container text-center mt-5">
    <h2>관리자 페이지 입니다.</h2>

    <div class="d-flex col-6">
        <a href="/admin" class="btn btn-outline-warning col-2 mx-1">사용자</a>
        <a href="/admin/board" class="btn btn-outline-danger col-2 mx-1">D-Board</a>
    </div>


    <table class="table mt-3">
        <thead>
        <tr>
            <th class="col-1">No</th>
            <th class="col-1">CTGR</th>
            <th class="col-5 text-left">title</th>
            <th class="col-1">조회수</th>
            <th class="col-1">작성자</th>
            <th class="col-2">날짜</th>
            <th class="col-1">복구</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ghBoardList}" var="list">
            <tr>
                <td>${list.seq}</td>
                <td>
                    <c:choose>
                        <c:when test="${list.category eq '자유'}"><span class="badge badge-pill badge-secondary"></c:when>
                        <c:when test="${list.category eq '이미지'}"><span class="badge badge-pill badge-primary"></c:when>
                        <c:when test="${list.category eq '동영상'}"><span class="badge badge-pill badge-success"></c:when>
                        <c:when test="${list.category eq '자료실'}"><span class="badge badge-pill badge-warning"></c:when>
                    </c:choose>
                                ${list.category}</span>
                </td>
                <td class="text-left">
                    <a href="#" class="badge badge-light col-12 text-left">
                        <c:forEach var="depth" begin="1" end="${list.depth}">
                            <c:if test="${depth ne list.depth}">&nbsp;&nbsp;</c:if>
                            <c:if test="${depth == list.depth}"> └</c:if>
                        </c:forEach>
                            ${list.title}
                    </a>
                </td>
                <td>${list.viewCount}</td>
                <td><b>${list.createdName}</b></td>
                <td>
                    <fmt:formatDate value="${list.createdDate}" pattern="yyyy-MM-dd"/>
                </td>
                <td>
                    <a href="#" class="btn btn-success active btnRollback"
                       data-seq='${list.seq}'>복구</a>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

    <a href="javascript:history.back()" class="btn btn-secondary">돌아가기</a>
</div>

<script type="text/javascript">
    $(document).ready(function () {

        $('.btnRollback').on('click', function (e) {
            e.preventDefault();

           let seq = $(this).data('seq');
           alert("복구 게시물 번호 번호 : " + seq);

           if (confirm("해당 게시물을 복구 하시겠습니까?")) {
               $.ajax({
                   type: "POST",
                   data: {"seq": seq},
                   url: "/board/deleteRollback",
                   success : function (data) {
                       if (data.result == 'success') {
                           alert('해당 게시글을 복구 하였습니다.');
                           location.reload();
                       }
                   },
                   error : function () {
                       alert('에러 발생!');
                   }

               })
           }
        });

    });
</script>
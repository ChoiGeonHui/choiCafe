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


    <table class="table">
        <thead>
        <tr>
<%--            <c:if test="${user.role eq 'ROLE_ADMIN'}">--%>
<%--                <th class="col-1"><input type="checkbox" id="selectAll" name="selectAll" onclick="selectAll()"></th>--%>
<%--            </c:if>--%>
            <th class="col-1">No</th>
            <th class="col-1">CTGR</th>
            <th class="col-5 text-left">title</th>
            <th class="col-1">조회수</th>
            <th class="col-1">작성자</th>
            <th class="col-2">날짜</th>
<%--            <th class="col-1">답글</th>--%>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ghBoardList}" var="list">
            <tr>
<%--                <c:if test='${list.delYN == "Y"}'>--%>
<%--                    <td colspan="7">삭제된 게시글 입니다.</td>--%>
<%--                </c:if>--%>
<%--                    <c:if test="${user.role eq 'ROLE_ADMIN'}">--%>
<%--                        <td><input type="checkbox" name="selectChk" class="selectChk" data-checkbox="${list.seq}" onclick='checkedAll()'></td>--%>
<%--                    </c:if>--%>
                    <td>${list.seq}</td>
                    <td>
                        <c:choose>
                        <c:when test="${list.category eq '자유'}"><span class="badge badge-pill badge-secondary"></c:when>
                        <c:when test="${list.category eq '이미지'}"><span class="badge badge-pill badge-primary"></c:when>
                            <c:when test="${list.category eq '동영상'}"><span class="badge badge-pill badge-success"></c:when>
                            </c:choose>
                                    ${list.category}</span>
                    </td>
                    <td class="text-left">
                        <a href="/#" class="badge badge-light col-12 text-left">
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
<%--                    <td>--%>
<%--                        <a href="/board/comment?seq=${list.seq}" class="btn btn-info btnComment" data-seq='${list.seq}'>답글</a>--%>
<%--                    </td>--%>

            </tr>
        </c:forEach>

        </tbody>
    </table>


    <a href="/board/list/list" class="btn btn-secondary">돌아가기</a>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<div class="text-center container">

    <jsp:include page="boardSearchForm.jsp"/>

    <table class="table">
        <thead>
        <tr>
            <c:if test="${user.role eq 'ROLE_ADMIN'}">
                <th class="col-1"><input type="checkbox" id="selectAll" name="selectAll" onclick="selectAll()"></th>
            </c:if>
            <th class="col-1">No</th>
            <th class="col-1">구분</th>
            <th class="col-5 text-left">title</th>
            <th class="col-1">조회수</th>
            <th class="col-1">작성자</th>
            <th class="col-2">날짜</th>
            <th class="col-1">답글</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ghBoardList}" var="list">
                <tr>
                    <c:if test='${list.delYN == "Y"}'>
                        <td colspan="7">삭제된 게시글 입니다.</td>
                    </c:if>
                    <c:if test='${list.delYN eq "N"}'>
                    <c:if test="${user.role eq 'ROLE_ADMIN'}">
                        <td><input type="checkbox" name="selectChk" class="selectChk" data-checkbox="${list.seq}" onclick='checkedAll()'></td>
                    </c:if>
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
                            <a href="/board/view/detail?seq=${list.seq}" class="badge badge-light col-12 text-left">
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
                            <a href="/board/comment?seq=${list.seq}" class="btn btn-info btnComment" data-seq='${list.seq}'>답글</a>
                        </td>
                    </c:if>
                </tr>
        </c:forEach>

        </tbody>
    </table>

    <c:if test="${user.role eq 'ROLE_ADMIN'}">
        <div class="mt-2 mb-2 d-flex justify-content-end">
            <input type="button" id="selectDel" class="btn btn-danger active" value="선택 삭제">
        </div>
    </c:if>
    <ul class="paging">
        <c:if test="${paging.prev}">
            <span><a href="#" data-page-number='${paging.startPage-1}' class="btn btn-white btnPage">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-left" viewBox="0 0 16 16">
                    <path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z"/>
                </svg>
            </a></span>
        </c:if>
        <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="num">
            <span><a href="javascript:void(0);" data-page-number='${num}' class="btn btn-white ${ghBoard.page ne num ? 'btnPage' : 'btn-info'} ">${num}</a></span>
        </c:forEach>
        <c:if test="${paging.next && paging.endPage>0}">
            <span>
                <a href="#" data-page-number='${paging.endPage+1}' class="btn btn-white btnPage">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-right" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z"/>
                    </svg>
                </a>
            </span>
        </c:if>
    </ul>


</div>


<%@ include file="boardPagingScript.jsp"%>
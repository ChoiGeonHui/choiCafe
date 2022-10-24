<%--
  Created by IntelliJ IDEA.
  User: adnstyle1153
  Date: 2022-09-22
  Time: 오전 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="text-center container">

    <jsp:include page="boardSearchForm.jsp"/>

    <table class="table table-bordered">
        <thead>
        </thead>
        <tbody>
        <c:forEach items="${ghBoardList}" var="list">

<%--            <c:if test='${list.delYN == "Y" and user.role eq "ROLE_ADMIN"}'>--%>
<%--                <tr>--%>
<%--                    <td  rowspan="2">삭제된 게시글 입니다.</td>--%>
<%--                    <td  rowspan="1">삭제된 게시글 입니다.</td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td>1</td>--%>
<%--                    <td>1</td>--%>
<%--                </tr>--%>
<%--            </c:if>--%>
            <c:if test="${list.delYN == 'N'}">
                <tr>

                    <td rowspan="2" class="col-2">

                        <c:if test="${boardHandle eq 'images'}">
                            <img width="100%" height="80px;" src="/files/view?tableSeq=${list.seq}&tableType=ghBoard"/>
                        </c:if>
                        <c:if test="${boardHandle eq 'videos'}">
                            <video width="100%" height="80px;" src="/files/view?tableSeq=${list.seq}&tableType=ghBoard"/>
                        </c:if>

                    </td>
                    <td colspan="2"><a href="/board/view/detail?seq=${list.seq}">${list.title}</a></td>
                </tr>
                <tr>
                    <td>${list.createdName}</td>
                    <td>작성 날짜 : <fmt:formatDate value="${list.createdDate}" pattern="yyyy-MM-dd"/></td>
                </tr>
            </c:if>

        </c:forEach>

        </tbody>
    </table>

    <ul class="paging">
        <c:if test="${paging.prev}">
            <span><a href="#" data-page-number='${paging.startPage-1}' class="btn btn-white btnPage">이전</a></span>
        </c:if>
        <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="num">
            <span><a href="javascript:void(0);" data-page-number='${num}' class="btn btn-white ${ghBoard.page ne num ? 'btnPage' : 'btn-info'} ">${num}</a></span>
        </c:forEach>
        <c:if test="${paging.next && paging.endPage > 0}">
            <span><a href="#" data-page-number='${paging.endPage+1}' class="btn btn-white btnPage">다음</a></span>
        </c:if>
    </ul>

</div>

<%@ include file="boardPagingScript.jsp"%>
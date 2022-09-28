<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: adnstyle1153
  Date: 2022-09-22
  Time: 오후 1:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form method="get" id="submitForm" name="submitForm" action="/board/list/${boardHandle}">
    <div class="mb-2 d-flex justify-content-between">

        <div class="d-flex col-6">
            <a href="/board/list/list" class="btn btn-outline-dark col-2 mx-1">전체</a>
            <a href="/board/list/list?category=자유" class="btn btn-outline-secondary col-2 mx-1">자유</a>
            <a href="/board/list/images?category=이미지" class="btn btn-outline-primary  col-2 mx-1">이미지</a>
            <a href="/board/list/videos?category=동영상" class="btn btn-outline-success col-2 mx-1">동영상</a>
            <a href="/board/list/list?category=자료실" class="btn btn-outline-warning col-2 mx-1">자료실</a>
        </div>

        <input hidden="hidden" id="boardHandle" value="${boardHandle}">
        <input hidden="hidden" id="category" name="category" value="${ghBoard.category}">

        <div class="d-flex flex-row-reverse col-6">
            <a class="btn btn-outline-primary mx-1" href="/board/create"> 등록</a>
            <button type="submit" id="btnSearch" class="btn btn-outline-secondary mx-1">검색
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                </svg>
            </button>
            <input type="text" id="search" name="searchWord" value="${ghBoard.searchWord}" class="mx-1">
            <select id="searchName" name="searchName">
                <option ${ghBoard.searchName eq 'all' ? 'selected' : ''} value="all">전체</option>
                <option ${ghBoard.searchName eq 'title' ? 'selected' : ''} value="title">제목</option>
                <option ${ghBoard.searchName eq 'content' ? 'selected' : ''} value="content">내용</option>
            </select>
        </div>

    </div>

</form>
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
            <a class="btn btn-outline-primary mx-1" href="/board/create">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                    <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                    <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                </svg>등록
            </a>
            <button type="submit" id="btnSearch" class="btn btn-outline-secondary mx-1">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                </svg>검색
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
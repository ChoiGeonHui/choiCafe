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
            <a href="/board/list/list" class="btn btn-light active col-2 mx-1">전체</a>
            <a href="/board/list/list?category=자유" class="btn btn-secondary col-2 mx-1">자유</a>
            <a href="/board/list/images?category=이미지" class="btn btn-primary active col-2 mx-1">이미지</a>
            <a href="/board/list/videos?category=동영상" class="btn btn-success active col-2 mx-1">동영상</a>
        </div>
        <input hidden="hidden" id="category" name="category" value="${ghBoard.category}">

        <div class="d-flex flex-row-reverse col-6">
            <a class="btn btn-primary text-white ml-2" href="/board/create"> 등록</a>
            <input type="submit" id="btnSearch" class="btn btn-secondary ml-1" value="검색">
            <input type="text" id="search" name="searchWord" value="${ghBoard.searchWord}">
            <select id="searchName" name="searchName" class="mr-1">
                <option ${ghBoard.searchName eq 'all' ? 'selected' : ''} value="all">전체</option>
                <option ${ghBoard.searchName eq 'title' ? 'selected' : ''} value="title">제목</option>
                <option ${ghBoard.searchName eq 'content' ? 'selected' : ''} value="content">내용</option>
            </select>
        </div>

    </div>

</form>
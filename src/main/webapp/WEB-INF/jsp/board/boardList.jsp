<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<div class="text-center">


    <div class="mb-2">
        <div class="d-flex flex-row-reverse">
            <a class="btn btn-primary text-white" href="/board/create"> 등록</a>
        </div>

    </div>


    <table class="table">
        <thead>
        <tr>
            <th class="col-1">번호</th>
            <th class="col-5">제목</th>
            <th class="col-1">조회</th>
            <th class="col-3">작성자</th>
            <th class="col-2">날짜</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ghBoardList}" var="list">

                <tr>
                    <td>${list.seq}</td>
                    <td><a href="/gh/board/detail?seq=${list.seq}">${list.title}</a></td>
                    <td>${list.viewCount}</td>
                    <td>${list.createdBy}</td>
                    <td>
                        <fmt:formatDate value="${list.createdDate}" pattern="yyyy-MM-dd"/></td>
                </tr>


        </c:forEach>

        </tbody>



    </table>




</div>
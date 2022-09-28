<%--
  Created by IntelliJ IDEA.
  User: adnstyle1153
  Date: 2022-09-26
  Time: 오전 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<header>
    <h2 class="mt-3"><a href="/board/list/list">ChoiCafe</a></h2>
</header>

<c:if test="${user ne null}">

    <div class="d-flex my-2 justify-content-end">
        <span>${user.name}님 환영합니다. </span><a href="/logout" class="btn btn-outline-danger btn-sm mx-1"> 로그아웃
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-right"
             viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0v2z"/>
            <path fill-rule="evenodd" d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
        </svg>
    </a>
    </div>
    <div class="d-flex my-2 justify-content-end">
        <a href="/oauth/detail" class="btn btn-sm btn-outline-warning mx-1"> 내정보 </a>
        <c:if test="${user.role eq 'ROLE_ADMIN'}">
            <a href="/admin" class="btn btn-sm btn-outline-success mx-1"> 관리자 페이지 </a>
        </c:if>
        <c:if test="${user.role eq 'ROLE_SOCIAL'}">
            <a href="#" class="btn btn-sm btn-outline-secondary mx-1">계정 연결</a>
        </c:if>
    </div>
</c:if>
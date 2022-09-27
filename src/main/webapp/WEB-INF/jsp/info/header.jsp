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
        <span>${user.name}님 환영합니다. </span><a href="/logout" class="btn btn-outline-danger btn-sm mx-1"> 로그아웃</a>
    </div>
    <div class="d-flex my-2 justify-content-end">
        <a href="/oauth/detail" class="btn btn-sm btn-outline-warning mx-1"> 내정보 </a>
        <c:if test="${user.role eq 'ROLE_ADMIN'}">
            <a href="/admin" class="btn btn-sm btn-outline-success mx-1"> 관리자 페이지 </a>
        </c:if>
    </div>
</c:if>
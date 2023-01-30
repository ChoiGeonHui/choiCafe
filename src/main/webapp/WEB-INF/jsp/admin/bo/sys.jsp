<%--
  Created by IntelliJ IDEA.
  User: adnstyle
  Date: 2023/01/25
  Time: 2:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="container text-center mt-5">
    <h2>This is Back Office SYS Page</h2>

    <div class="d-flex col-6">
        <a href="/admin" class="btn btn-outline-warning col-2 mx-1">사용자</a>
        <a href="/admin/board" class="btn btn-outline-danger col-2 mx-1">D-Board</a>
    </div>

    <b hidden="hidden">${menuName}</b>

    <table class="table text-center mt-3">
        <thead>
        <tr>
            <th>No</th>
            <th>ID</th>
            <th>NAME</th>
            <th>EMAil</th>
            <th>ROLE</th>
<%--            <th>Locked</th>--%>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${result}" var="list">
            <tr>
                <td>${list.seq}</td>
                <td>${list.role eq "ROLE_SOCIAL"? "외부 소셜유저": list.id}</td>
                <td>${list.name}</td>
                <td>${list.email}</td>
                <td>${list.role}</td>
<%--                <td>--%>
<%--                    <c:if test="${list.lockYN eq 'Y'}">--%>
<%--                        <button class="btn btn-warning btn-sm btnUnLock" data-seq="${list.seq}">제재 해제</button>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${list.lockYN eq 'N'}">--%>
<%--                        정상--%>
<%--                    </c:if>--%>
<%--                </td>--%>
            </tr>
        </c:forEach>

        </tbody>


    </table>
    <div>
        <a href="javascript:history.back()" class="btn btn-secondary">뒤로가기</a>
    </div>


</div>

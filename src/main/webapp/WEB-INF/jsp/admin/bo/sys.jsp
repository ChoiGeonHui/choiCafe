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

    <jsp:include page="../menuView.jsp"/>


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
            <c:if test="${list.role ne 'ROLE_SOCIAL'}">
                <tr>
                    <td>${list.seq}</td>
                    <td><a href="/admin/bo/sysEdit?adminSeq=${list.seq}">${list.id}</a></td>
                    <td>${list.name}</td>
                    <td>${list.email}</td>
                    <td>${list.role}</td>
                </tr>
            </c:if>
        </c:forEach>

        </tbody>


    </table>
    <div>
        <a href="javascript:history.back()" class="btn btn-secondary">뒤로가기</a>
    </div>


</div>

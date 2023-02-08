<%--
  Created by IntelliJ IDEA.
  User: adnstyle
  Date: 2023/01/26
  Time: 9:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container text-center">

    <input type="text" id="seq" hidden="hidden" value="${result.seq}">

    <h3 class="mt-2">User Information</h3>
    <div class="d-flex mx-auto input-group my-1 col-6">
        <span class="input-group-text col-2">아이디</span>
        <input type="text" class="form-control" readonly="readonly" value="${result.id}">
    </div>

    <div class="d-flex mx-auto input-group mx-auto col-6 my-1">
        <span class="input-group-text col-2">이름</span>
        <input type="text" class="form-control" readonly="readonly" value="${result.name}">
    </div>

    <div class="d-flex mx-auto input-group my-1 col-6">
        <span class="input-group-text col-2">이메일</span>
        <input type="text" class="form-control" readonly="readonly" value="${result.email}">
    </div>

    <div class="d-flex mx-auto input-group my-1 col-6">
        <span class="input-group-text col-2">전화번호</span>
        <input type="text" class="form-control" readonly="readonly" value="${result.phone}">
    </div>

    <div class="d-flex mx-auto input-group my-1 col-6">
        <span class="input-group-text col-2">Role</span>
        <input type="text" class="form-control" readonly="readonly" value="${result.role}">
    </div>

    <div class="d-flex mx-auto input-group my-1 col-6">
        <span class="input-group-text col-2">boMenu</span>
        <input type="text" class="form-control" readonly="readonly" value="${result.boMenu}">
    </div>

    <div>
        <c:forEach items="${result2}" var="checkList">
            <input type="checkbox" class="boCheck" name="${checkList.menuName}" ${(fn:contains(result.boMenu, checkList.menuName))? 'checked': ''}> ${checkList.menuName} &nbsp;
        </c:forEach>
    </div>

    <div class="mt-2">
        <button id="btnUpdate" class="btn btn-info">수정</button>
        <a class="btn btn-secondary text-white" href="/board/list/list">취소</a>
    </div>

</div>
<script type="text/javascript">

    $(function () {

        $("#btnUpdate").on("click", function () {

            // let boMenu =


            alert();
        })



    })

</script>

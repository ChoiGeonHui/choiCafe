<%--
  Created by IntelliJ IDEA.
  User: adnstyle1153
  Date: 2022-09-26
  Time: 오후 1:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">

  <h3 class="mt-2">내 정보</h3>

  <div class="d-flex mx-auto input-group my-1 col-6">
    <span class="input-group-text col-2">아이디</span>
    <input type="text" class="form-control" readonly="readonly" value="${user.id}">
  </div>

  <div class="d-flex mx-auto input-group mx-auto col-6 my-1">
    <span class="input-group-text col-2">이름</span>
    <input type="text" class="form-control" readonly="readonly" value="${user.name}">
  </div>

  <div class="d-flex mx-auto input-group my-1 col-6">
    <span class="input-group-text col-2">이메일</span>
    <input type="text" class="form-control" readonly="readonly" value="${user.email}">
  </div>

  <div class="d-flex mx-auto input-group my-1 col-6">
    <span class="input-group-text col-2">전화번호</span>
    <input type="text" class="form-control" readonly="readonly" value="${user.phone}">
  </div>

  <div class="d-flex mx-auto input-group my-1 col-6">
    <span class="input-group-text col-2">등급</span>
    <input type="text" class="form-control" readonly="readonly" value="${user.role}">
  </div>

</div>

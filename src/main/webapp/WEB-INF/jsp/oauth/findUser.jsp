<%--
  Created by IntelliJ IDEA.
  User: adnstyle1153
  Date: 2022-11-02
  Time: 오후 3:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">


    <h3 class="mt-2">비밀번호 찾기</h3>

    <div class="d-flex mx-auto input-group my-1 col-6">
        <span class="input-group-text col-3">아이디</span>
        <input type="text" id="id" name="id" class="form-control">
    </div>

    <div class="d-flex mx-auto input-group mx-auto col-6 my-1">
        <span class="input-group-text col-3">이름</span>
        <input type="text" class="form-control" name="name"  value="${user.name}">
    </div>

    <div class="d-flex mx-auto input-group my-1 col-6">
        <span class="input-group-text col-3">이메일</span>
        <input type="text" class="form-control" name="email" value="${user.email}">
    </div>

    <div class="d-flex mx-auto input-group my-1 col-6">
        <span class="input-group-text col-3">전화번호</span>
        <input type="text" id="phone" placeholder="전화번호를 입력하세요." maxlength="13" class="form-control"
               oninput="autoHyphen(this)">
        <div class="input-group-append">
            <input type="button" class="btn btn-info smsCheck" id="sendMessage" value="인증번호받기">
        </div>
    </div>

    <div class="d-flex mx-auto input-group my-1 col-6">
        <span class="input-group-text col-3">인증번호</span>
        <input type="text" id="inputMessageNum" class="form-control phoneInput" oninput="autoHyphen(this)">
        <div class="input-group-append">
            <span class="input-group-text input-group-prepend input-group-append text-danger"
                  id="spanTime">0 : 00</span>
            <input type="button" class="btn btn-info active smsCheck" id="checkNumber" value="인증확인">
        </div>
    </div>

    <div class="d-flex mx-auto input-group my-1 col-6 d-none">
        <span class="input-group-text col-3">비밀번호</span>
        <input type="password" id="password" name="password" class="form-control">
    </div>

    <div id="PWdiv" class="d-flex mx-auto input-group my-1 col-6">
        <span class="input-group-text col-3">비밀번호 확인</span>
        <input type="password" id="passwordChk" class="form-control">
    </div>
    <div>s
        <a class="btn btn-secondary text-white" href="javascript:history.back()">목록
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-list" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
            </svg>
        </a>
        <button id="modifyPW" class="btn btn-success">비밀번호 변경</button>
    </div>
</div>
<script type="text/javascript" charset="UTF-8">

</script>
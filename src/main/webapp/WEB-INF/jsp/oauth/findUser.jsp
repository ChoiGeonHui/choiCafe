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

    <div class="d-flex mx-auto input-group my-1 col-6">
        <span class="input-group-text col-3">전화번호</span>
        <input type="text" id="phone" placeholder="전화번호를 입력하세요." maxlength="13" class="form-control phoneInput" oninput="autoHyphen(this)">
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

    <div class="d-flex mx-auto input-group my-1 col-6">
        <span class="input-group-text col-3 pwd d-none">비밀번호</span>
        <input type="password" id="password" name="password" class="form-control pwd d-none" placeholder="변경할 비밀번호를 입력하세요.">
    </div>

    <div id="PWdiv" class="d-flex mx-auto input-group my-1 col-6">
        <span class="input-group-text col-3 pwd d-none">비밀번호 확인</span>
        <input type="password" id="passwordChk" class="form-control pwd d-none">
    </div>
    <div>
        <a class="btn btn-secondary text-white" href="javascript:history.back()">목록
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-list" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
            </svg>
        </a>
        <button id="modifyPW" class="btn btn-success pwd d-none mt-2">비밀번호 변경</button>
    </div>
</div>

<jsp:include page="smsOAuthScript.jsp"/>

<script type="text/javascript" charset="UTF-8">

    $(function () {

      $('#modifyPW').on('click', function () {

          let id = $("#id").val();
          let password = $("#password").val();
          let passwordChk = $("#passwordChk").val();
          let phone = $("#phone").val();

          if(id == '') {
              alert('아이디를 입력하세요.');
              return;
          }

          if(password == '' || password.length < 6) {
              alert('비밀번호를 6자리 이상 입력하세요.');
              return;
          }

          if (password != passwordChk) {
              alert('비밀번호 확인이 일치하기 않습니다.');
              return;
          }

          let regExpEn = /[!@#$%?]/;
          if (!regExpEn.test(password)) {
              alert('비밀번호에 특수문자(!@#$%?)가 포함 되어야 합니다.');
              return;
          }

          if($("#inputMessageNum").attr("readonly") == false) {
              alert("인증번호를 확인 하세요.");
              return;
          }

          $.ajax({
              type: "POST",
              url: "/oauth/updatePW",
              data: {'id': id, 'password': password, "phone" : phone},
              success: function (data) {
                  if (data.result == 'success') {
                      alert('비밀번호가 변경되었습니다.');
                      location.href = '/oauth/login';
                  } else if (data.result == 'notUser') {
                      alert('아이디와 전화번호가 일치하지 않습니다.');
                  } else {
                      alert('오류 발생');
                  }
              },
              error: function () {
                  alert('에러발생');
              }
          })
      })

    })

</script>
<%--
  Created by IntelliJ IDEA.
  User: adnstyle1153
  Date: 2022-09-15
  Time: 오전 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="container text-center">

    <div class="d-flex justify-content-center mt-5">
        <div class="col-4">
            <div><h1>로그인</h1></div>
            <div class="col-12 d-flex input-group"><span class="col-2 font-weight-bold input-group-text input-group-append">ID </span><input type="text" id="id" class="form-control"></div>
            <div class="mt-3 col-12 d-flex"><span class="col-2 font-weight-bold input-group-text input-group-append">PW  </span><input type="password" id="password" class="form-control"></div>
            <div><input type="button" id="btbSginIn" class="btn btn-success active mt-2 col-12" value="로그인"></div>
            <div><a href="/oauth/sginUp" type="button" class="btn btn-secondary mt-2 col-12">회원가입</a></div>
            <div><a href="/oauth/findUser" type="button" class="btn btn-warning mt-2 col-12">비밀번호 찾기</a></div>
<%--            <div><a href="/oauth2/authorization/google" class="btn btn-primary active col-12 mt-2" role="button">Google Login</a></div>--%>
<%--            <div><a href="/oauth2/authorization/naver" class="btn btn-success col-12 mt-2" role="button">Naver Login</a></div>--%>

            <form enctype="utf-8" id="formData">
                <div id="recaptcha" class="g-recaptcha mt-2 col-12" data-sitekey="6LfV-i0jAAAAANc5m7st8KAaE0Ki0sQ-hPnWqi9H" data-callback="recaptchaCallback"></div>
            </form>
        </div>
    </div>

</div>
<script type="text/javascript">

    function login() {

        let id = $("#id").val();
        let password = $("#password").val();

        if(id == '') {
            alert('아이디를 입력하세요.')
            return;
        }

        if(password == '') {
            alert('비밀번호를 입력하세요.')
            return;
        }

        $.ajax({
            type: "POST",
            url: "/oauth/sginIn",
            data: {'id': id, 'password': password},
            success: function (data) {
                if (data.result == 'success') {
                    location.href="/board/list/list";
                } else {
                    alert(data.error);
                }
            },
            error: function () {
                alert('에러발생');
            }
        })

    }

    function doVailRecaptchr() {
        let formData = $('#formData').serialize();
        $.ajax({
            type : "POST",
            url: "/valid-recaptcha",
            data : formData,
            cache : false,
            success : function (data) {
                if (data == 'success') {
                    login();
                } else {
                    alert('인증되지 않은 주소입니다.');
                }

            },
            error : function (xhr, status, error) {
                alert(error);
            }
        })
    }

    $(function () {

        $("#password").on('keypress',function (e) {
            if (e.keyCode === 13) {
                doVailRecaptchr();
            }
        })

        $("#btbSginIn").on('click', doVailRecaptchr)

    })

</script>


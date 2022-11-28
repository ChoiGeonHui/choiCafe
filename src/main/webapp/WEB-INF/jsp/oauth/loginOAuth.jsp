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

<%--            <form enctype="utf-8" id="formData">--%>
<%--                <div id="recaptcha" class="g-recaptcha mt-2 col-12" data-sitekey="${recaptchaSite}" data-callback="recaptchaCallback"></div>--%>
<%--            </form>--%>

            <input type="text" name="g-recaptcha-response" id="g-recaptcha-response" hidden="hidden" />
            <input id="recaptchaSite" type="text" hidden="hidden" value="${recaptchaSite}" />
        </div>
    </div>

</div>
<script src="https://www.google.com/recaptcha/api.js?render=6Le8ljsjAAAAAK8Xnp6XLy8TR_skUUKt2ZsoZIoL"></script>
<script type="text/javascript">

    let recaptchaSite = $('#recaptchaSite').val();


    /** 리캡챠 V3 */
    function doVailRecaptchr3 () {
        grecaptcha.ready(function () {
            grecaptcha.execute(recaptchaSite, {action: '/valid/recaptchaV3'})
                .then(function (token) {
                    $.ajax({
                        type: "POST",
                        url: "/valid/recaptchaV3",
                        data: {"token": token},
                        success: function (data) {
                            if (data == 'success') {
                                login()
                            } else {
                                alert('당신은 로봇입니까?');
                            }
                        },
                        error: function (request, status, error) {
                            alert(error);
                        }
                    });
                });
        });
    }

    /** 로그인 등록 페이지 */
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

    /** 리캡챠 V2 */
    function doVailRecaptchr() {
        let formData = $('#formData').serialize();
        $.ajax({
            type : "POST",
            url: "/valid/recaptcha",
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
                doVailRecaptchr3();
            }
        })

        $("#btbSginIn").on('click', doVailRecaptchr3)

    })

</script>


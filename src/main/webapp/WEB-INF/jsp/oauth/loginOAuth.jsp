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
            <div><input type="button" id="btbSginIn" class="btn btn-success mt-2 col-12" value="로그인"></div>
            <div><a href="/oauth/sginUp" type="button" class="btn btn-secondary mt-2 col-12">회원가입</a></div>
            <div><a href="/oauth2/authorization/google" class="btn btn-primary active col-12 mt-2" role="button">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-google" viewBox="0 0 16 16">
                    <path d="M15.545 6.558a9.42 9.42 0 0 1 .139 1.626c0 2.434-.87 4.492-2.384 5.885h.002C11.978 15.292 10.158 16 8 16A8 8 0 1 1 8 0a7.689 7.689 0 0 1 5.352 2.082l-2.284 2.284A4.347 4.347 0 0 0 8 3.166c-2.087 0-3.86 1.408-4.492 3.304a4.792 4.792 0 0 0 0 3.063h.003c.635 1.893 2.405 3.301 4.492 3.301 1.078 0 2.004-.276 2.722-.764h-.003a3.702 3.702 0 0 0 1.599-2.431H8v-3.08h7.545z"/>
                </svg>
                Google Login
            </a>
            </div>
        </div>

    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {

        $("#btbSginIn").on('click', function () {

            let id = $("#id").val();
            let password = $("#password").val();

            if(id == ''){
                alert('아이디를 입력하세요.')
                return;
            }

            if(password == ''){
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
                        alert('아이디 또는 비밀번호가 틀립니다.');
                    }
                },
                error: function () {
                    alert('에러발생');
                }
            })


        })




    })



</script>


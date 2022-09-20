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
        <div>
            <div><h1>로그인</h1></div>
            <div class="col-12"><b>ID : </b><input type="text" id="id"></div>
            <div class="mt-3 col-12"><b>PW : </b><input type="password" id="password"></div>
            <div><input type="button" id="btbSginIn" class="btn btn-success mt-2 col-12" value="로그인"></div>
            <div><a href="/oauth/sginUp" type="button" class="btn btn-secondary mt-2 col-12">회원가입</a></div>
<%--            <div><a href="/oauth2/authorization/google" class="btn btn-primary active col-12 mt-2" role="button">Google Login</a></div>--%>
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
                        location.href="/board/list";
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


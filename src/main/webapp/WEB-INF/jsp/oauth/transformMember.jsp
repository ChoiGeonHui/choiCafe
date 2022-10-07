<%--
  Created by IntelliJ IDEA.
  User: adnstyle1153
  Date: 2022-09-30
  Time: 오전 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">

    <form method="post" action="/oauth/transformMember">

        <h3 class="mt-2">계정 전환</h3>

        <div class="d-flex mx-auto input-group my-1 col-6">
            <span class="input-group-text col-3">아이디</span>
            <input type="text" id="id" name="id" class="form-control">
            <div class="input-group-append">
                <input type="button" class="btn btn-success" id="chkId" value="중복체크">
            </div>
        </div>

        <div id="idLength" class="text-danger d-none text-center">아이디를 4자 이상 입력하세요</div>
        <div id="idDuc" class="text-danger d-none text-center">중복된 아이디 입니다</div>
        <div id="idChk" class="text-success d-none text-center">사용가능한 아이디입니다</div>

        <div class="d-flex mx-auto input-group my-1 col-6">
            <span class="input-group-text col-3">비밀번호</span>
            <input type="password" id="password" name="password" class="form-control">
        </div>

        <div class="d-flex mx-auto input-group my-1 col-6">
            <span class="input-group-text col-3">비밀번호 확인</span>
            <input type="password" id="passwordChk" class="form-control">
        </div>

        <div class="d-flex mx-auto input-group mx-auto col-6 my-1">
            <span class="input-group-text col-3">이름</span>
            <input type="text" class="form-control" name="name" readonly="readonly" value="${user.name}">
        </div>

        <div class="d-flex mx-auto input-group my-1 col-6">
            <span class="input-group-text col-3">이메일</span>
            <input type="text" class="form-control" name="email" readonly="readonly" value="${user.email}">
        </div>

        <div class="d-flex mx-auto input-group my-1 col-6">
            <span class="input-group-text col-3">등급</span>
            <input type="text" class="form-control" name="role" readonly="readonly" value="${user.role}">
        </div>
    </form>
    <div>
        <button id="btnSubmit" class="btn btn-success">계정 전환</button>
    </div>
</div>
<script type="text/javascript">

    $(document).ready(function () {

        $("#id").on("propertychange change keyup paste input",function () {

            $("#idLength").addClass('d-none');
            $("#idDuc").addClass('d-none');
            $("#idChk").addClass('d-none');
        })


        $("#chkId").on('click', function () {

            let id = $("#id").val();

            if (id == '' || id == null || id.length < 4){
                $("#idLength").removeClass('d-none');
                $("#idDuc").addClass('d-none');
                $("#idChk").addClass('d-none');
                return;
            }


            $.ajax({
                type:"POST",
                url : "/oauth/idChk",
                data : {"id": id},
                success : function (data) {
                    if (data.result == "success") {
                        // alert('사용 가능한 아이디 입니다.');
                        $("#idLength").addClass('d-none');
                        $("#idDuc").addClass('d-none');
                        $("#idChk").removeClass('d-none');
                    } else {
                        // alert('중복되는 아이디 입니다.');
                        $("#idLength").addClass('d-none');
                        $("#idDuc").removeClass('d-none');
                        $("#idChk").addClass('d-none');
                    }
                },
                error : function () {
                    alert('오류 발생');
                }
            })

        })


        $("form").submit(function (e) {
            let id = $("#id").val();
            let password = $("#password").val();
            let passwordChk = $("#passwordChk").val();

            if(id == ''){
                alert('아이디를 입력하세요.');
                e.preventDefault();
                return;
            }

            if ($("#idChk").hasClass("d-none")) {
                alert('아이디 중복체크를 하세요');
                e.preventDefault();
                return;
            }

            if(password == '' || password.length < 6){
                alert('비밀번호를 6자리 이상 입력하세요.');
                e.preventDefault();
                return;
            }

            if (password != passwordChk) {
                alert('비밀번호 확인이 일치하기 않습니다.');
                e.preventDefault();
                return;
            }

            $.ajax({
                type: "POST",
                url: $(this).attr('action'),
                data: $(this).serialize(),
                success: function (data) {
                    if (data.result == 'success') {
                        alert('계정 전환이 완료 되었습니다. 다시 로그인 하세요');
                        location.href = '/logout';
                    } else {
                        alert('오류 발생');
                    }
                },
                error: function () {
                    alert('에러 발생');
                }

            })
            return false;

        });

        $("#btnSubmit").click(function () {
            $("form").submit();
        })

    })


</script>
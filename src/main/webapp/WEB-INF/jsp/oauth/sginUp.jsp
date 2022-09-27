<%--
  Created by IntelliJ IDEA.
  User: adnstyle1153
  Date: 2022-09-15
  Time: 오후 1:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container text-center">

    <h3 class="mt-2">회원 가입</h3>
    <div class="d-flex justify-content-center mt-2">
        <table class="col-6">

            <tr>
                <td> 아이디</td>
                <td>
                    <div class="input-group">
                        <input type="text" id="id" name="id" placeholder="아이디를 입력하세요"
                               class="form-control">
                        <div class="input-group-append">
                            <input type="button" class="btn btn-success" id="chkId" value="중복체크">
                        </div>
                    </div>

                </td>
            </tr>
            <tr>
                <td id="idLength" class="text-danger d-none text-center" colspan="2">아이디를 4자 이상 입력하세요</td>
                <td id="idDuc" class="text-danger d-none text-center" colspan="2">중복된 아이디 입니다</td>
                <td id="idChk" class="text-success d-none text-center" colspan="2">사용가능한 아이디입니다</td>
            </tr>
            <tr>
                <td>비밀번호</td>
                <td>
                    <div>
                        <input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요."
                               class="form-control">
                    </div>

                </td>
            </tr>
            <tr>
                <td>비밀번호 확인</td>
                <td>
                    <div>
                        <input type="password" id="passwordChk" name="passwordChk" class="form-control">
                    </div>

                </td>
            </tr>
            <tr>
                <td>이름</td>
                <td>
                    <div>
                        <input type="text" id="name" name="name" class="form-control">
                    </div>

                </td>
            </tr>
            <tr>
                <td>이메일</td>
                <td>
                    <div class="input-group">
                        <input type="text" id="email" name="email" class="form-control">
                        <span class="input-group-text input-group-prepend input-group-append">@</span>
                        <select class="form-control" id="emailhost" name="emailhost">
                            <option value="naver.com">naver.com</option>
                            <option value="gmail.com">gamil.com</option>
                            <option value="daum.net">daum.net</option>
                            <option value="kakao.com">kakao.com</option>
                        </select>
                    </div>

                </td>
            </tr>
        </table>


    </div>
    <div class="my-2">
        <button id="btnSgin" class="btn btn-primary col-3">가입하기</button>
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



        $("#btnSgin").on('click', function () {

            let id = $("#id").val();
            let password = $("#password").val();
            let passwordChk = $("#passwordChk").val();
            let name = $("#name").val();
            let email = $("#email").val();
            let emailhost = $("#emailhost").val();

            if(id == ''){
                alert('아이디를 입력하세요.');
                return;
            }

            if ($("#idChk").hasClass("d-none")) {
                alert('아이디 중복체크를 하세요');
                return;
            }


            if(password == '' || password.length < 6){
                alert('비밀번호를 6자리 이상 입력하세요.');
                return;
            }

            if (password != passwordChk) {
                alert('비밀번호 확인이 일치하기 않습니다.');
                return;
            }

            if(name == ''){
                alert('이름을 입력하세요.');
                return;
            }
            if(email == ''){
                alert('이메일을 입력하세요.');
                return;
            }
            email = email+'@'+emailhost;


            $.ajax({
                type: "POST",
                url: "/oauth/insertMember",
                data: {'id': id, 'password': password, 'name': name, 'email': email},
                success: function (data) {
                    if (data.result == 'success') {
                        alert('회원가입이 완료되었습니다.');
                        location.href = '/oauth/login';
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
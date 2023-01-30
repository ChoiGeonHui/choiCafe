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
<div class="container text-center">

    <h3 class="mt-2">회원 가입</h3>
    <div class="d-flex justify-content-center mt-2">
        <table class="col-7">
            <tr>
                <td>아이디</td>
                <td>
                    <div class="input-group">
                        <input type="text" id="id" name="id" placeholder="아이디를 입력하세요 (4자리 이상)"
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
                            <option value="nate.com">nate.com</option>
                            <option value="gmail.com">gmail.com</option>
                            <option value="daum.net">daum.net</option>
                            <option value="hanmail.net">hanmail.net</option>
                            <option value="kakao.com">kakao.com</option>
                        </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td>전화번호</td>
                <td>
                    <div class="input-group">
                        <input type="text" id="phone" placeholder="전화번호를 입력하세요." maxlength="13" class="form-control phoneInput" oninput="autoHyphen(this)">
                        <div class="input-group-append">
                            <input type="button" class="btn btn-info smsCheck"  id="sendMessage" value="인증하기">
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>인증번호</td>
                <td>
                    <div class="input-group">
                        <input type="text" id="inputMessageNum" class="form-control phoneInput">
                        <div class="input-group-append">
                            <span class="input-group-text input-group-prepend input-group-append text-danger" id="spanTime">0 : 00</span>
                            <input type="button" class="btn btn-info active smsCheck" id="checkNumber" value="확인">
                        </div>
                    </div>
                </td>
            </tr>
        </table>

    </div>
    <div class="my-2">
        <button id="btnSgin" class="btn btn-primary col-2">가입하기</button>
        <a class="btn btn-secondary text-white col-2" href="/board/list/list">취소</a>
    </div>
</div>

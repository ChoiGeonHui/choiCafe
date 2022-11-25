<%--
  Created by IntelliJ IDEA.
  User: adnstyle1153
  Date: 2022-09-20
  Time: 오전 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container text-center mt-5">
    <h2>관리자 페이지 입니다.</h2>

    <div class="d-flex col-6">
        <a href="/admin" class="btn btn-outline-warning col-2 mx-1">사용자</a>
        <a href="/admin/board" class="btn btn-outline-danger col-2 mx-1">D-Board</a>
    </div>


    <table class="table text-center mt-3">
        <thead>
        <tr>
            <th>No</th>
            <th>ID</th>
            <th>NAME</th>
            <th>EMAil</th>
            <th>ROLE</th>
            <th>Locked</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ghMemberList}" var="list">
            <tr>
                <td>${list.seq}</td>
                <td>${list.role eq "ROLE_SOCIAL"? "외부 소셜유저": list.id}</td>
                <td>${list.name}</td>
                <td>${list.email}</td>
                <td>${list.role}</td>
                <td>
                    <c:if test="${list.lockYN eq 'Y'}">
                        <button class="btn btn-warning btn-sm btnUnLock" data-seq="${list.seq}">제재 해제</button>
                    </c:if>
                    <c:if test="${list.lockYN eq 'N'}">
                        정상
                    </c:if>
                </td>
            </tr>
        </c:forEach>

        </tbody>


    </table>
    <div>
        <a href="javascript:history.back()" class="btn btn-secondary">뒤로가기</a>
    </div>


</div>

<script type="text/javascript">

    $(function () {

        $('.btnUnLock').on('click', function () {

            let seq = $(this).data('seq');

            if(confirm('해당 계정제재를 해제 하시겠습니까? \n 계정 번호 : '+seq)) {

                $.ajax({
                    type : "POST",
                    url : "/admin/unLock",
                    data : {"seq" : seq},
                    success : function (data) {
                        if (data.result == "success") {
                            alert('변경 되었습니다.');
                            location.reload();
                        } else {
                            alert("오류 발생");
                        }
                    },
                    error : function () {
                        alert("에러 발생!");
                    }

                });

            }

        })


    })


</script>
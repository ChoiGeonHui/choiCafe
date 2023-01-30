<%--
  Created by IntelliJ IDEA.
  User: adnstyle
  Date: 2023/01/25
  Time: 1:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container text-center mt-5">
    <h2>This is back office main page.</h2>


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


        </tbody>


    </table>
    <div>
        <a href="javascript:history.back()" class="btn btn-secondary">뒤로가기</a>
    </div>


</div>

<%--<script type="text/javascript">--%>

<%--    $(function () {--%>

<%--        $('.btnUnLock').on('click', function () {--%>

<%--            let seq = $(this).data('seq');--%>

<%--            if(confirm('해당 계정제재를 해제 하시겠습니까? \n 계정 번호 : '+seq)) {--%>

<%--                $.ajax({--%>
<%--                    type : "POST",--%>
<%--                    url : "/admin/unLock",--%>
<%--                    data : {"seq" : seq},--%>
<%--                    success : function (data) {--%>
<%--                        if (data.result == "success") {--%>
<%--                            alert('변경 되었습니다.');--%>
<%--                            location.reload();--%>
<%--                        } else {--%>
<%--                            alert("오류 발생");--%>
<%--                        }--%>
<%--                    },--%>
<%--                    error : function () {--%>
<%--                        alert("에러 발생!");--%>
<%--                    }--%>

<%--                });--%>

<%--            }--%>

<%--        })--%>


<%--    })--%>


<%--</script>--%>
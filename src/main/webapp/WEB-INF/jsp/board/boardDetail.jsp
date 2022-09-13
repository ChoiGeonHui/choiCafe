<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<div class="text-center">
    <form method="post"id="submitForm" name="submitForm" action="/board/insertUpdate" enctype="multipart/form-data">
        <input type="text" value="${ghBoard.seq}" id="seq" name="seq" hidden="hidden">
        <div>
            <b class="col-1">제목 : </b> <input type="text" id="title" name="title" class="col-6" value="${ghBoard.title}">
        </div>
        <div class="mt-2">
            <textarea id="content" name="content" class="col-7" style="height: 100px" placeholder="내용을 입력하세요.">${ghBoard.content}</textarea>
        </div>
        <div class="mt-2 col-12">
            <input type="file" name="file" id="file">
            <c:if test="${ghBoard.ghAttach.seq ne null}">
                <span id="fileName1"> 현재 저장된 파일 :</span>
                <a href="#" id="btnDownload" class="btn ml-2">${ghBoard.ghAttach.displayName}</a>
                <input type="button" id="btnDelFile" class="btn ml-2" value="X">
                <div id="fileName" class="ml-2 text-center ml-2"></div>
            </c:if>
            <input  name="ghAttach.seq" value="${ghBoard.ghAttach.seq}" hidden>
            <input  name="ghAttach.tableType" value="ghBoard" hidden>
            <input  name="ghAttach.tableSeq" value="${ghBoard.seq}" hidden>
            <input id="attachStatus"  name="ghAttach.status" value="exists" hidden>
        </div>
        <div class="mt-2">
            <button type="submit" class="btn btn-info">등록</button>
            <a class="btn btn-secondary text-white" href="/board/list">취소</a>
        </div>
    </form>
</div>

<script type="text/javascript">
    $(document).ready(function () {

        $('#btnDelFile').on('click', function () {
            $('input[name = "ghAttach.status"]').attr('value','_delete');
            $('#fileName1').text('');
            $('#btnDownload').attr('hidden','ture');
            $('#btnDelFile').attr('hidden','ture');
        })





    })
</script>
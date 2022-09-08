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
    <div class="mt-2 col-8">
        <input type="file" name="file" id="file">
        <span> 현재 저장된 파일 : </span>
        <a>${ghBoard.ghAttach.displayName}</a>
        <input  name="ghAttach.seq" value="${ghBoard.ghAttach.seq}" hidden>
        <input  name="ghAttach.tableType" value="ghBoard" hidden>
        <input  name="ghAttach.tableSeq" value="${ghBoard.seq}" hidden>
        <div id="fileName" class="ml-2 text-center ml-2"></div>
    </div>
    <div class="mt-2">
        <button type="submit" class="btn btn-info">등록</button>
        <a class="btn btn-secondary text-white" href="/board/list">취소</a>
    </div>
</form>

</div>
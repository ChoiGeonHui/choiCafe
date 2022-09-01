<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<div class="text-center">
<form method="post"id="submitForm" name="submitForm" action="/board/insert">
    <input type="text" value="${ghBoardList.seq}" id="seq" name="seq" hidden="hidden">
    <div>
        <b class="col-1">제목 : </b> <input type="text" id="title" name="title" class="col-6" value="${ghBoardList.title}">
    </div>
    <div class="mt-2">
        <textarea id="content" name="content" class="col-7" style="height: 100px" placeholder="내용을 입력하세요.">${ghBoardList.contnet}</textarea>
    </div>
    <div>
        <button type="submit" class="btn btn-info insertBoard">등록</button>
        <a class="btn btn-secondary text-white" href="/board/list">취소</a>
    </div>
</form>

</div>
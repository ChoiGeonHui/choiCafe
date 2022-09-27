<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<div class="text-center">
    <form method="post" id="submitForm" name="submitForm" action="/board/insertUpdate" enctype="multipart/form-data">
        <input type="text" value="${ghBoard.seq}" id="seq" name="seq" hidden="hidden">
        <input type="text" value="${ghBoard.parentSeq}" id="parentSeq" name="parentSeq" hidden="hidden">
        <input type="text" value="${user.seq}" id="createdBy" name="createdBy" hidden="hidden">
        <div class="input-group col-8 text-center mx-auto">
            <span class="input-group-text">제목 : </span>
            <input type="text" id="title" name="title" class="form-control" value="${ghBoard.title}">
        </div>
        <div class="my-2 col-8">
            <span>카테고리 : </span>
            <label class="radio-inline"><input type="radio" name="category" ${ghBoard.category eq '자유' or ghBoard.category eq null? 'checked = "checked"' : ''} value="자유"> 자유</label>&nbsp;
            <label><input type="radio" name="category" ${ghBoard.category eq '이미지'? 'checked = "checked"' : ''}value="이미지"> 이미지</label>&nbsp;
            <label><input type="radio" name="category" ${ghBoard.category eq '동영상'? 'checked = "checked"' : ''}value="동영상"> 동영상</label>
            <label><input type="radio" name="category" ${ghBoard.category eq '자료실'? 'checked = "checked"' : ''}value="자료실"> 자료실</label>
        </div>
        <div class="mt-2 mx-auto">
            <textarea id="content" name="content" class="col-7" style="height: 100px" placeholder="내용을 입력하세요.">${ghBoard.content}</textarea>
        </div>
        <div class="mt-2 d-flex justify-content-center">
            <input class="form-control col-7" type="file" accept="image/*, video/*" name="fileList" id="file" multiple="multiple">
        </div>
        <div>
            <c:if test="${ghBoard.ghAttachList ne null}">
                <span id="fileName1"> 현재 저장된 파일 :</span>
                <c:forEach items="${ghBoard.ghAttachList}" var="list" varStatus="status">
                    <a href="/files/download?seq=${list.seq}" id="btnDownload${list.seq}" class="btn ml-2">${list.displayName}</a>
                    <input type="button" data-file-seq="${list.seq}" id="btnDelFile${list.seq}" class="btnDelFile btn ml-2" value="X">
                    <input  name="ghAttachList[${status.index}].seq" value="${list.seq}" hidden>
                    <input id="attachStatus${list.seq}"  name="ghAttachList[${status.index}].status" value="exists" hidden>

                </c:forEach>

            </c:if>
            <div id="fileName" class="ml-2 text-center ml-2"></div>

        </div>
        <c:if test="${user.role eq 'ROLE_ADMIN' or user.seq eq ghBoard.createdBy or ghBoard.seq eq null}">
            <div class="mt-2">
                <button type="submit" class="btn btn-info">등록</button>
                <a class="btn btn-secondary text-white" href="/board/list/list">취소</a>
            </div>
        </c:if>
    </form>
</div>

<script type="text/javascript">
    $(document).ready(function () {

        $('.btnDelFile').on('click', function () {
            let seq = $(this).data('file-seq');
            $('#attachStatus'+seq).attr('value','_delete');
            $('#btnDownload'+seq).attr('hidden','ture');
            $('#btnDelFile'+seq).attr('hidden','ture');
        })





    })
</script>
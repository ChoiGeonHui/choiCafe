<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<div class="container text-center">

    <div>

        <input type="text" value="${ghBoard.seq}" hidden="hidden" id="seq">
        <input type="text" value="${user.seq}" hidden="hidden" id="useq">
        <table class="table col-12">
            <thead class="mb-2">
            <tr>
                <th scope="col" class="text-left" colspan="4"><h3>${ghBoard.title}</h3></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td colspan="2" class="text-left"><b>작성자 : ${ghBoard.createdName}</b></td>
                <td colspan="2" class="text-right"><b>작성일 : <fmt:formatDate value="${ghBoard.createdDate}" pattern="yyyy/MM/dd"/></b>
                    &nbsp;
                    <span><fmt:formatDate value="${ghBoard.createdDate}" pattern="HH:mm:ss"/></span></td>
            </tr>
            <tr>
                <td colspan="4">
                    <p>&nbsp;</p>
                    <c:forEach items="${ghBoard.ghAttachList}" var="list" varStatus="status">
                        <c:if test="${fn:contains(list.type, 'image' )}">
                            <img height="300px;" class="col-10" src="/files/view?seq=${list.seq}" alt="${list.displayName}"/>
                        </c:if>
                        <c:if test="${fn:contains(list.type, 'video' )}">
                            <video height="300px;" class="col-10" src="/files/view?seq=${list.seq}" controls="controls"/>
                        </c:if>
                        <br>
                    </c:forEach>
                    <p>&nbsp;</p>
                    <p class="ml-3 text-left">${ghBoard.content}</p>
                </td>

            </tr>

            </tbody>
        </table>

        <c:if test="${ghBoard.ghAttachList ne null and ghBoard.category eq '자료실'}">
            <div class="text-left">
                <span id="fileName1"> 첨부파일 :</span>
                <c:forEach items="${ghBoard.ghAttachList}" var="list" varStatus="status">
                    <a href="/files/download?seq=${list.seq}" id="btnDownload${list.seq}"
                       class="btn ml-2">${list.displayName}</a>
                </c:forEach>
            </div>
        </c:if>

        <hr>
        <div class="mt-2 mb-2 input-group">
            <div class="input-group-prepend col-12">
                <textarea type="text" id="replyContent"
                          class="form-control col-11" placeholder="댓글을 작성하세요"></textarea>
                <button id="insertReplyBtn" class="text-info btn btn-white commentBtn col-1" data-board-seq="${ghBoard.seq}">게시</button>
            </div>
        </div>

<%--        <a href="#" id="test11" data-target="commentReply">답글테스트</a>--%>

        <input type="text" class="d-none" id="commentReply">

        <hr>
        <div class="mt-2">
            <c:if test="${user.role eq 'ROLE_ADMIN' or user.seq eq ghBoard.createdBy}">
                <a class="btn btn-success" href="/board/view/update?seq=${ghBoard.seq}">수정하기</a>
            </c:if>
            <a class="btn btn-secondary text-white" href="/board/list/list">목록</a>
            <c:if test="${user.role eq 'ROLE_ADMIN' or user.seq eq ghBoard.createdBy}">
                <a class="btn btn-danger" id="btnDel" href="#">삭제하기</a>
            </c:if>
        </div>
        <hr>

        <c:forEach items="${ghBoard.ghReplyList}" var="list" varStatus="status">
            <div>
                <span>${list.seq}</span><br><span>${list.content}</span><br><span>${list.boardSeq}</span><br><span>${list.memberSeq}</span>
            </div>
            <br>
        </c:forEach>


    </div>

    <br>

    <table class="table text-left">
        <tr>
            <td class="col-2 text-center"> 이전글</td>
            <td class="col-1"> | </td>
            <td class="col-8">
                <c:if test="${ghBoard.prevBoard ne null}">
                    <a href="/board/view/detail?seq=${ghBoard.prevBoard}">${ghBoard.prevTitle}</a>
                </c:if>
                <c:if test="${ghBoard.prevBoard eq null}">
                    <span>이전글이 없습니다.</span>
                </c:if>
            </td>
        </tr>
        <tr>
            <td class="col-2 text-center">다음글</td>
            <td class="col-1"> | </td>
            <td class="col-8">
                <c:if test="${ghBoard.nextBoard ne null}">
                    <a href="/board/view/detail?seq=${ghBoard.nextBoard}">${ghBoard.nextTitle}</a>
                </c:if>
                <c:if test="${ghBoard.nextBoard eq null}">
                    <span>다음글이 없습니다.</span>
                </c:if>
            </td>
        </tr>
    </table>

</div>
<script type="text/javascript">

    $(document).ready(function () {

        $("#insertReplyBtn").on('click', function (){
            let content =  $("#replyContent").val();
            let boardSeq =  $("#seq").val();
            let memberSeq =  $("#useq").val();
            alert(content+ boardSeq+ memberSeq);
            // return;
            if (content.length = 0) {
                alert("댓글을 입력하세요.");
                return;
            }

            $.ajax({
                type: "POST",
                url: "/reply/insert",
                data: {"content" : content, "boardSeq" : boardSeq, "memberSeq" : memberSeq},
                success: function (data) {
                    if (data.result == 'success') {
                        alert('댓글 등록 완료.');
                        location.reload();
                    } else {
                        alert('오류발생');
                    }
                },
                error: function () {
                    alert('에러발생!');
                }
            })


        })

        $("#btnDel").on('click', function (e) {
            e.preventDefault();

            let seq = [];
            seq.push($("#seq").val());

            if (confirm("게시물을 삭제하시겠습니까?")) {

                $.ajax({
                    type: "POST",
                    url: "/board/delete",
                    data: {"seq" : seq},
                    success: function (data) {
                        if (data.result == 'success') {
                            alert('삭제를 완료하였습니다.');
                            location.href = "/board/list/list";
                        } else {
                            alert('오류발생');
                        }
                    },
                    error: function () {
                        alert('에러발생!');
                    }
                })
            }
        })


    })
</script>
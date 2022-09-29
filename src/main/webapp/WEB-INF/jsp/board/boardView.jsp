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
                <span id="fileName1"> 첨부파일 : </span>
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
                <button id="insertReplyBtn" class="text-info btn btn-white commentBtn col-1" data-board-seq="${ghBoard.seq}">게시
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-reply-fill" viewBox="0 0 16 16">
                        <path d="M5.921 11.9 1.353 8.62a.719.719 0 0 1 0-1.238L5.921 4.1A.716.716 0 0 1 7 4.719V6c1.5 0 6 0 7 8-2.5-4.5-7-4-7-4v1.281c0 .56-.606.898-1.079.62z"/>
                    </svg>
                </button>
            </div>
        </div>

        <hr>

<%--        댓글 작성리스트--%>
        <c:forEach items="${ghBoard.ghReplyList}" var="list" varStatus="status">
            <c:if test="${list.delYN eq 'N'}">
            <div class="col-12 bg-light">
                <div class="d-flex">
                    <b class="col-8 text-left">${list.memberName}</b>
                    <div class="col-3">
                        <span><fmt:formatDate value="${list.createdDate}" pattern="yyyy-MM-dd"/></span>
                        <small><fmt:formatDate value="${list.createdDate}" pattern="HH:mm:ss"/></small>
                    </div>
                    <a type="button" href="#" data-reply="${list.seq}" class="addBtn btn-info btn btn-sm col-1">대댓글
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-return-left" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M14.5 1.5a.5.5 0 0 1 .5.5v4.8a2.5 2.5 0 0 1-2.5 2.5H2.707l3.347 3.346a.5.5 0 0 1-.708.708l-4.2-4.2a.5.5 0 0 1 0-.708l4-4a.5.5 0 1 1 .708.708L2.707 8.3H12.5A1.5 1.5 0 0 0 14 6.8V2a.5.5 0 0 1 .5-.5z"/>
                        </svg>
                    </a>
                </div>
                <div class="d-flex">
                    <span class="text-left col-11">
                        <c:forEach var="depth" begin="1" end="${list.depth}">
                            <c:if test="${depth ne list.depth}">&nbsp;&nbsp;</c:if>
                            <c:if test="${depth == list.depth}"> └</c:if>
                        </c:forEach>
                            ${list.content}
                    </span>
                    <c:if test="${list.memberSeq eq user.seq}">
                        <a type="button" href="#" data-del-reply="${list.seq}" data-member-reply="${list.memberSeq}" class="delReply btn-danger btn btn-sm col-1">삭제
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-lg" viewBox="0 0 16 16">
                                <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z"/>
                            </svg>
                        </a>
                    </c:if>
                </div>
            </div>
            <div id="addInput${list.seq}" class="addInput"></div>
            <br>
            </c:if>
            <c:if test="${list.delYN eq 'Y'}">
                <div class="col-12 bg-light">
                    <span>삭제된 댓글 입니다.</span>
                </div>
                <br>
            </c:if>
        </c:forEach>

        <hr>

        <div class="mt-2">
            <c:if test="${user.role eq 'ROLE_ADMIN' or user.seq eq ghBoard.createdBy}">
                <a class="btn btn-success" href="/board/view/update?seq=${ghBoard.seq}">수정하기</a>
            </c:if>
            <a class="btn btn-secondary text-white" href="javascript:history.back()">목록</a>
<%--            뒤로 가기--%>
            <c:if test="${user.role eq 'ROLE_ADMIN' or user.seq eq ghBoard.createdBy}">
                <a class="btn btn-danger" id="btnDel" href="#">삭제하기</a>
            </c:if>
        </div>

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

        //대댓글란 생성
        $(".addBtn").on('click', function (e) {
            e.preventDefault();

            let datareply = $(this).data('reply');

            if ($(this).hasClass("showReply")){ //특정 클래스가 존재여부에 따라 대글란 생성, 삭제가 된다.
                $('#btnComment'+datareply).prev().remove(); //input 삭제   prev : 자신을 기준으로 앞에 있는 태그 지정
                $('#btnComment'+datareply).next().remove(); //br 삭제      next : 자신을 기준으로 뒤에 있는 태그 지정
                $('#btnComment'+datareply).remove();  // 버튼 삭제
                $(this).removeClass("showReply");
            } else {
                $('#addInput'+datareply).append(
                    '<input type="text" name="test" class="col-10">' +
                    '<button type="button" data-parent="'+datareply+'" id="btnComment'+datareply+'" class="btnComment btn btn-success btn-sm">등록</button><br>'
                );
                $(this).addClass("showReply");

            }
        })

        //대댓글 등록
        $('.addInput').on('click','.btnComment', function () { //동적으로 생성된 태그는 함수를 사용하기위해서 직접 클래스 이름을 작성해야함
            let content = $(this).prev().val();
            let parentSeq = $(this).data('parent');
            let boardSeq =  $("#seq").val();
            let memberSeq =  $("#useq").val();

            $.ajax({
                type: "POST",
                url: "/reply/insert",
                data: {"parentSeq" : parentSeq, "content" : content, "boardSeq" : boardSeq, "memberSeq" : memberSeq},
                success: function (data) {
                    if (data.result == 'success') {
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

        //댓글 등록
        $("#insertReplyBtn").on('click', function () {
            let content =  $("#replyContent").val();
            let boardSeq =  $("#seq").val();
            let memberSeq =  $("#useq").val();

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

        //댓글 삭제 버튼
        $(".delReply").on('click', function (e) {
            e.preventDefault();
            let seq = $(this).data('del-reply');
            let memberSeq = $(this).data('member-reply');

            if (confirm('댓글을 삭제하시겠습니까?')) {
                $.ajax({
                    type: "POST",
                    url: "/reply/delete",
                    data: {"seq" : seq, "memberSeq" : memberSeq},
                    success: function (data) {
                        if (data.result == 'success') {
                            alert('삭제를 완료하였습니다.');
                            location.reload();
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


        //게시물 삭제 버튼
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
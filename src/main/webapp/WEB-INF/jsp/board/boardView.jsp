<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<div class="container text-center">

    <div>

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
                            <img height="300px;" class="col-10" src="/viewImg?seq=${list.seq}"/>
                        </c:if>
                        <c:if test="${fn:contains(list.type, 'video' )}">
                            <video height="300px;" class="col-10" src="/viewImg?seq=${list.seq}" controls="controls"/>
                        </c:if>
                        <br>
                    </c:forEach>
                    <p>&nbsp;</p>
                    <p class="ml-3 text-left">${ghBoard.content}</p>
                </td>

            </tr>

            </tbody>
        </table>

        <hr>
        <div class="mt-2">
            <c:if test="${user.role eq 'ROLE_ADMIN' or user.seq eq ghBoard.createdBy or ghBoard.seq eq null}">
                <a class="btn btn-success" href="/board/detail?seq=${ghBoard.seq}">수정하기</a>
            </c:if>
            <a class="btn btn-secondary text-white" href="/board/list">목록</a>
        </div>

    </div>

    <br>

    <table class="table text-left">
        <tr>
            <td class="col-2 text-center"> 이전글</td>
            <td class="col-1"> | </td>
            <td class="col-8"> <a href="#">이전 게시글이 없습니다.</a></td>
        </tr>
        <tr>
            <td class="text-center">다음글</td>
            <td class="col-1"> | </td>
            <td><a href="#">다음글이 없습니다.</a></td>
        </tr>
    </table>



</div>

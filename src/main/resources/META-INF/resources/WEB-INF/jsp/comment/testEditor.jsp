<%--
  Created by IntelliJ IDEA.
  User: adnstyle1153
  Date: 2022-11-07
  Time: 오후 5:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script type="text/javascript" src="/static/se2/js/HuskyEZCreator.js"></script>
    <script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>

</head>
<body>
<img src="/static/img/koreanflag.jpg" height="100px">
<textarea id="content" name="content" rows="10" cols="100">에디터 테스트 입니다.</textarea>

</body>
<script type="text/javascript">
    var oEditors = [];
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: oEditors,
        elPlaceHolder: "content",
        sSkinURI: "/static/se2/SmartEditor2Skin.html",
        fCreator: "createSEditor2"
    });
</script>
</html>

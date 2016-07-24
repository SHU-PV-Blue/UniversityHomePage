<%--
  Created by IntelliJ IDEA.
  User: wolfogre
  Date: 16-7-24
  Time: 下午11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/library/bootstrap/css/bootstrap.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">
    <title>上传结果</title>
</head>
<body>
<div class="jumbotron center-block" style="width: 60%">
    <h1><%=(boolean)request.getAttribute("success") ? "上传成功" : "上传失败"%></h1>
    <%
        if((boolean)request.getAttribute("success")) {
    %>
    <p>你成功上传了<%=request.getAttribute("name").toString()%>的主页布局图，</p>
    <p>辛苦哒！</p>
    <%
        } else {
    %>
    <p>失败原因是 <%=request.getAttribute("reason").toString()%></p>
    <p>请仔细检查一下或联系管理员。</p>
    <%
        }
    %>
    <p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}" role="button">返回主页</a></p>
</div>
</body>
</html>

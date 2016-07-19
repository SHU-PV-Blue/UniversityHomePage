<%@ page import="java.util.List" %>
<%@ page import="com.wolfogre.uhp.domain.HomePageEntity" %><%--
  Created by IntelliJ IDEA.
  User: wolfogre
  Date: 16-7-17
  Time: 下午5:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/library/jquery/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/library/datatables/js/jquery.dataTables.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/library/datatables/js/dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/index.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/library/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/library/datatables/css/dataTables.bootstrap.css">

    <title>asdf</title>
  </head>
  <body>
    主页
  <%=request.getAttribute("test")%>
    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
      <thead>
      <tr>
        <th class="text-center">编号</th>
        <th class="text-center">大学</th>
        <th class="text-center">主页</th>
        <th class="text-center">主页图</th>
        <th class="text-center">移动版主页</th>
        <th class="text-center">移动版主页图</th>
      </tr>
      </thead>
      <tbody>
      <%
        List<HomePageEntity> universityList = (List<HomePageEntity>)request.getAttribute("universityList");
        for(HomePageEntity it : universityList) {
      %>
      <tr>
        <td class="text-center"><%=it.getId()%></td>
        <td><%=it.getName()%></td>
        <td><a href="<%="http://" + it.getUrl()%>" target="_blank"><%=it.getUrl()%></a></td>
        <%
          if(it.getImagePath() == null){
        %>
        <td class="text-center"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></td>
        <%
        } else {
        %>
        <td><%=it.getImagePath()%></td>
        <%
          }
        %>
        <%
          if(it.getMobileUrl() == null){
        %>
        <td class="text-center"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></td>
        <%
        } else {
        %>
        <td><%=it.getMobileUrl()%></td>
        <%
          }
        %>
        <%
          if(it.getMobileImagePath() == null){
        %>
        <td class="text-center"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></td>
        <%
        } else {
        %>
        <td><%=it.getMobileImagePath()%></td>
        <%
          }
        %>
      </tr>
      <%
        }
      %>
      </tbody>
    </table>
  </body>
</html>

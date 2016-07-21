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
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/library/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/index.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/library/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/library/datatables/css/dataTables.bootstrap.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">
    <title>大学主页速览</title>
  </head>
  <body>
    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
      <thead>
      <tr>
        <th class="text-center">编号</th>
        <th class="text-center">大学名称</th>
        <th class="text-center">主页地址</th>
        <th class="text-center">主页截图</th>
        <th class="text-center">桌面版主页布局</th>
        <th class="text-center">主页内容</th>
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

        <td class="text-center">
          <button type="button" class="btn btn-success" <%=it.getImagePath() == null ? "disabled=\"disabled\"" : ""%> data-toggle="modal" data-target="#exampleModal" style="width: 40%"
                  data-name="<%=it.getName()%>" data-image="<%=it.getImagePath()%> ">桌面版</button>
          <button type="button" class="btn btn-info" <%=it.getMobileImagePath() == null ? "disabled=\"disabled\"" : ""%> data-toggle="modal" data-target="#exampleModal" style="width: 40%"
                  data-name="<%=it.getName()%>" data-image="<%=it.getMobileImagePath()%>" data-mobile="true">移动版</button>
        </td>
        <%
          if(it.getLayoutImage() == null){
        %>
        <td class="text-center"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></td>
        <%
        } else {
        %>
        <td>建设中...</td>
        <%
          }
        %>
        <%
          if(it.getContent() == null){
        %>
        <td class="text-center"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></td>
        <%
        } else {
        %>
        <td><%=it.getContent()%></td>
        <%
          }
        %>
      </tr>
      <%
        }
      %>
      </tbody>
    </table>

    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel">New message</h4>
                </div>
                <div class="modal-body">
                    <img id="page-img" width="100%" height="auto" src=""/>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
  </body>
</html>

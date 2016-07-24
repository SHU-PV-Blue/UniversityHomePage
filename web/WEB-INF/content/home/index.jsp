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
        <th class="text-center" width="2%"></th>
        <th class="text-center" width="15%">大学名称</th>
        <th class="text-center" width="10%">网址</th>
        <th class="text-center" width="16%">截图</th>
        <th class="text-center" width="8%">布局</th>
        <th class="text-center">板块</th>
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
          <button type="button" class="btn btn-success" <%=it.getImagePath() == null || it.getImagePath().isEmpty() ? "disabled=\"disabled\"" : ""%> data-toggle="modal" data-target="#exampleModal" style="width: 40%"
                  data-name="<%=it.getName()%>" data-image="<%=it.getImagePath()%> ">桌面版</button>
          <button type="button" class="btn btn-info" <%=it.getMobileImagePath() == null || it.getMobileImagePath().isEmpty() ? "disabled=\"disabled\"" : ""%> data-toggle="modal" data-target="#exampleModal" style="width: 40%"
                  data-name="<%=it.getName()%>" data-image="<%=it.getMobileImagePath()%>" data-mobile="true">移动版</button>
        </td>

        <td class="text-center">

          <%
            if(session.getAttribute("admin") == null || !(boolean)session.getAttribute("admin")) {
          %>
          <button type="button" class="btn btn-success" <%=it.getLayoutImage() == null ? "disabled=\"disabled\"" : ""%> data-toggle="modal" data-target="#exampleModal" style="width: 80%"
                  data-name="<%=it.getName()%>" data-image="../../image/download?universityId=<%=it.getId()%>">查看</button>
          <%} else { %>
          <button type="button" class="btn btn-success" <%=it.getLayoutImage() == null ? "disabled=\"disabled\"" : ""%> data-toggle="modal" data-target="#exampleModal" style="width: 45%"
                  data-name="<%=it.getName()%>" data-image="../../image/download?universityId=<%=it.getId()%>">查看</button>
          <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#uploadModal" style="width: 45%"
                  data-name="<%=it.getName()%>" data-id="<%=it.getId()%>"">上传</button>
          <%}%>
        </td>

        <%
          if(session.getAttribute("admin") == null || !(boolean)session.getAttribute("admin")) {
        %>
        <td>
          <input type="text" class="form-control content" value="<%=it.getContent() == null ? "" : it.getContent()%>" style="width:100%" readonly="readonly">
        </td>
        <%
          } else {
        %>
        <td>
          <input type="text" class="form-control content" value="<%=it.getContent() == null ? "" : it.getContent()%>" style="width:80%">
          <button type="button" class="btn btn-warning content-update" disabled="disabled" data-id="<%=it.getId()%>"style="width:15%">更新</button>
        </td>
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
                    <h4 class="modal-title" id="exampleModalLabel"></h4>
                </div>
                <div class="modal-body">
                    <img id="page-img" width="100%" height="auto" src=""/>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>


    <div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="uploadModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="uploadModalLabel">上传布局图</h4>
          </div>
          <div class="modal-body">
            <form action="image/upload" method="post" enctype="multipart/form-data">
              <div class="form-group">
                <label for="uName">学校名称</label>
                <input type="text" class="form-control" id="uName" readonly="readonly">
              </div>
              <div class="form-group">
                <label for="universityId">学校编号</label>
                <input type="text" name="universityId" class="form-control" id="universityId" readonly="readonly">
              </div>
              <div class="form-group">
                <label for="uploadFile">请选择文件</label>
                <input type="file" id="uploadFile" name="uploadFile" accept=".jpg,.jpeg,.JPG,.JPEG">
                <p class="help-block">请上传.jpg,.jpeg,.JPG,.JPEG格式的图片，且务必谨慎，这可能会覆盖原先的图片。</p>
              </div>
              <button type="submit" class="btn btn-danger center-block">上传</button>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          </div>
        </div>
      </div>
    </div>


  </body>
</html>

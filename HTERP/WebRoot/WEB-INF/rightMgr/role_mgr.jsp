<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.ecside.org" prefix="ec"%>
<jsp:include page="../top.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <title></title>
</head>
<body>
    <div id="container" class="container">
      <div class="hr10"></div>
          <div class="hr10"></div>
          <h2>角色查询</h2>
		 <form id="form1" action="${pageContext.request.contextPath}/admin/basic/role_find.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <%-- <th class="tr">角色ID：</th>
                  <td><input type="text" class="u-ipt" name="role.roleId" value="${role.roleId}"></td> --%>
                  <th class="tr">角色名称：</th>
                  <td><input type="text" class="u-ipt" name="role.roleName" value="${role.roleName}"></td>    
                  <td>
					 <button type="button" class="u-btn" onclick="javascript:document.getElementById('form1').submit()">查询</button>&emsp;                   
                     <button type="button" class="u-btn" onclick="javascript:window.location='${pageContext.request.contextPath}/admin/basic/role_addRole.html'">新增</button>&emsp;           
                   </td>                 
                </tr>                				                           
             </tbody>
          </table>
          </form>   
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
			      
                   
          <h2>角色信息列表</h2> 
          <div align="center">
		   <ec:table items="roleList" var="role1" retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/basic/role_find.html"
				rowsDisplayed='12' pageSizeList="2,5,12,20,50,100"
				resizeColWidth="true" width="99%" listWidth="100%" height="600px"
				sortable="false" useAjax="false" style="align:center" doPreload="false"
				autoIncludeParameters="true" xlsFileName="abc.xls" >
				<ec:row>
					<ec:column property="ROLE_ID" title="角色ID" width="10%" />
					<ec:column property="ROLE_NAME" title="角色名称" width="10%" />
					<ec:column property="IS_LOGOUT" title="是否注销" width="10%" mappingItem="logout"/>
					<ec:column property="CREATE_TIME" title="创建时间" width="10%" />
					<ec:column property="REMARK" title="备注" width="10%" />
					<ec:column property="_0" title="动作" width="10%" >
						<a href="${pageContext.request.contextPath}/admin/basic/role_showModifyPage.html?role.roleId=${role1.ROLE_ID}">修 改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/admin/basic/role_authUI.html?role.roleId=${role1.ROLE_ID}">授 权</a>
					</ec:column>
				</ec:row>
			</ec:table>
		</div>
    </div><!-- /#container -->
  	<!--javascript start-->
  	<!--javascript end-->
</body>
</html>
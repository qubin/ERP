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
          <h2>用户查询</h2>
		  
		  <form action="${pageContext.request.contextPath}/admin/basic/user_find.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th class="tr">用户ID：</th>
                  <td><input type="text" class="u-ipt" name="user.userId" value="${user.userId}"></td>
                  <th class="tr">用户名称：</th>
                  <td><input type="text" class="u-ipt" name="user.userName" value="${user.userName}"></td>    
                  <td>
					 <button type="submit" class="u-btn" >查询</button>&emsp;                   
                     <button type="button" class="u-btn" onclick="javascript:window.location='${pageContext.request.contextPath}/admin/basic/user_addUI.html'">新增</button>&emsp;           
                   </td>                 
                </tr>                				                           
             </tbody>
           </form>  
          </table>
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
			      
                   
          <h2>用户信息列表</h2>
          <div align="center">
		   <ec:table items="userList" var="user"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/basic/role_find.html"
				rowsDisplayed='12' pageSizeList="2,5,12,20,50,100"
				resizeColWidth="true" width="99%" listWidth="100%" height="600px"
				sortable="false" useAjax="false" style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="USER_ID" title="用户ID" width="10%" />
					<ec:column property="USER_NAME" title="用户名称" width="10%" />
					<ec:column property="CREATE_TIME" title="创建时间" width="10%" />
					<ec:column property="IS_LOGOUT" title="角色" width="10%" />
					<ec:column property="CONTACT_PHONE" title="联系电话" width="15%" />
					<ec:column property="IS_LOGOUT" title="是否注销" width="20%" />
					<ec:column property="_0" title="动作" width="15%" >
						<a href="${pageContext.request.contextPath}/admin/basic/role_showModifyPage.html?role.roleUuid=${role1.ROLE_UUID}">修 改</a>
					</ec:column>	
				</ec:row>
			</ec:table>
		</div>
		
    </div><!-- /#container -->
  	<!--javascript start-->
    <script type="text/javascript">

    </script>
  	<!--javascript end-->
</body>
</html>
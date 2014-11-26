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
       
          <h2>角色授权</h2>
          <form action="${pageContext.request.contextPath}/admin/basic/role_auth.html" method="post">
          <table class="m-table">
              <thead>
              <tr>              	
              	<th class="cola"><input type="checkbox" name="" value=""></th>
              	<th class="cola">资源url</th>
              	<th class="cola">资源名称</th>
              	<th class="cola">主菜单名称
              	<input type="hidden" name="role.roleUuid" value="${role.roleUuid}"/>
              	</th>              	                       	
              </tr>
          </thead>
          <tbody>
          	<s:iterator value="#request.resourceList" status="sta">
              <s:if test="#sta.even == true">
              		<tr class="even">
              </s:if>
              <s:else>
              	<tr>
              </s:else>
              
              	<td><input type="checkbox" name="resourceIds" value="${resourceId}" id="${resourceId}">${resourceId}
              	</td>
              	<td>${resourceUrl}</td>
              	<td>${resourceTitle}</td>
              	<td>${parentTitle}</td>
              	             	              	
              </tr>
             </s:iterator> 
          <tfoot>
               <tr>
                 <td colspan="4" class="tc">
                   <button type="submit" class="u-btn">授权</button>
                   &emsp;
                   <button type="button" class="u-btn" onclick="javascript:history.back()" value="返回">取消</button>
                 </td>
               </tr>
             </tfoot>     
      </table>
     </form>
		
    </div><!-- /#container -->
  	<script type="text/javascript">
  		var ids = ${resourceIds};
		for (var i=0; i<ids.length; i++) {
 			jQuery("#" + ids[i]).attr("checked", true);
 		}
  	</script>
</body>
</html>
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
<!--           <form action="${pageContext.request.contextPath}/admin/basic/role_auth.html" method="post"> -->
<div align="center">
			<ec:table items="resourceList" var="res1" retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath }/admin/basic/role_authUI.html" 
				rowsDisplayed='20' pageSizeList="20,50,100"
				resizeColWidth="true" width="99%" listWidth="100%" height="600px"
				sortable="false" useAjax="false" style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="PARENT_TITLE" title="父资源名称" width="10%" />
					<ec:column property="RESOURCE_TITLE" title="资源名称" width="10%" />
					<ec:column property="RESOURCE_DESC" title="资源描述" width="10%" />
					<ec:column property="RESOURCE_URL" title="url" width="10%" />
					<ec:column property="RESOURCE_TYPE" title="类型" width="10%" mappingItem="resourceTypeMap"/>
					<ec:column property="_00" title="选择" width="10%">
						<input type="checkbox" name="role.resourceIds" id="${res1.RESOURCE_ID}" value="${res1.RESOURCE_ID}"/>
					</ec:column>
				</ec:row>
			</ec:table>
		</div>
		<br/>
<!-- 		<input type="button" value="授权" class="right-button08" onclick="javascript:submitAuth();"/> -->
<!-- 		<input type="button" value="返回" class="right-button08" onclick="javascript:window.location='${pageContext.request.contextPath}/admin/right/role_showHome.html';"/> -->
		<input type="hidden" name="role.roleId" value="${role.roleId }"/>
          <table class="m-table">
<!--               <thead> -->
<!--               <tr>              	 -->
<!--               	<th class="cola"><input type="checkbox" name="" value=""></th> -->
<!--               	<th class="cola">资源url</th> -->
<!--               	<th class="cola">资源名称</th> -->
<!--               	<th class="cola">主菜单名称 -->
<!--               	<input type="hidden" name="role.roleId" value="${role.roleId}"/> -->
<!--               	</th>              	                       	 -->
<!--               </tr> -->
<!--           </thead> -->
<!--           <tbody> -->
<!--           	<s:iterator value="#request.resourceList" status="sta"> -->
<!--               <s:if test="#sta.even == true"> -->
<!--               		<tr class="even"> -->
<!--               </s:if> -->
<!--               <s:else> -->
<!--               	<tr> -->
<!--               </s:else> -->
              
<!--               	<td><input type="checkbox" name="resourceIds" value="${resourceId}" id="${resourceId}">${resourceId} -->
<!--               	</td> -->
<!--               	<td>${resourceUrl}</td> -->
<!--               	<td>${resourceTitle}</td> -->
<!--               	<td>${parentTitle}</td> -->
              	             	              	
<!--               </tr> -->
<!--              </s:iterator>  -->

          <tfoot>
               <tr>
                 <td colspan="4" class="tc">
                   <button type="button" class="u-btn" onclick="javascript:submitAuth();">授权</button>
                   &emsp;
                   <button type="button" class="u-btn" onclick="javascript:history.back()" value="返回">取消</button>
                 </td>
               </tr>
             </tfoot>     
      </table>
     </form>
		
    </div><!-- /#container -->
  	<script type="text/javascript">
  		//var ids = ${resourceIds};
		//for (var i=0; i<ids.length; i++) {
 		//	jQuery("#" + ids[i]).attr("checked", true);
 		//}
 		
	 	jQuery(function(){
	 		var ids = ${authedResList};
	 		for (var i=0; i<ids.length; i++) {
	  			jQuery("#" + ids[i]).attr("checked", true);
	  		}
	 	
	 	})
 		
		
 		
 		function submitAuth() {
 			var authBox = document.getElementsByName("role.resourceIds");
 			var boxValues = "";
 			
 			for (var i=0; i<authBox.length; i++) {
 				if (authBox[i].checked == true) {
	 				boxValues += authBox[i].value + ",";
 				}
 			}
 			boxValues = boxValues.substring(0, boxValues.length - 1);       
 			window.location = "${pageContext.request.contextPath }/admin/basic/role_auth.html?role.roleId=" + '${role.roleId }' + "&resource_Ids=" + boxValues;
 		}
  	</script>
</body>
</html>
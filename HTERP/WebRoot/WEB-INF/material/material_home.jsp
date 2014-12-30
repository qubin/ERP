<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.ecside.org" prefix="ec"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../top.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <title></title>
  <script type="text/javascript">
	function delMsg(id,isLogout) {
  		if(isLogout == 0){
			if (confirm('您确定要注销此信息吗？')) {
				var str=prompt("注销原因？","");
				if(str!=null && str!=""){
					var h = "${pageContext.request.contextPath}/admin/material/material_delete.html?material.uuid="+id+"&material.isLogout=1&material.logoutReason="+str;
					window.location.href=h;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			if (confirm('您确定要恢复此信息吗？')) {
				var h = "${pageContext.request.contextPath}/admin/material/material_delete.html?material.uuid="+id+"&material.isLogout=0&material.logoutReason=' '";
				window.location.href=h;
			}
		}
	}
  </script>
</head>
<body>
    <div id="container" class="container">
      <div class="hr10"></div>
          <div class="hr10"></div>
          <h2>材料信息查询</h2>
		 <form id="form1" action="${pageContext.request.contextPath}/admin/material/material_find.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th width="100">
                  	查询条件：
                  </th>
                  <td width="10">
                  	<select class="u-ipt validate-selection" name="material_key">
                  		<option value="ALL">---查询所有---</option>
                  		<option value="NAME">供应商</option>
                  		<option value="DENSITY">面密度</option>
                  		<option value="THICKNESS">厚度</option>
                  		<option value="DESC1">描述</option>
                  		<option value="WEIGHT">重量</option>
                  		<option value="STANDARD">规格</option>
                  	</select>
                  </td>
                  <th width="50"><input type="text" class="u-ipt" name="material_name"/></th>        
                  <td>
					 <button type="button" class="u-btn" onclick="javascript:document.getElementById('form1').submit()">查询</button>&emsp;                   
                     <button type="button" class="u-btn" onclick="javascript:window.location='${pageContext.request.contextPath}/admin/material/material_showAddUI.html'">新增</button>&emsp;           
                   </td>
                </tr>                				                           
             </tbody>
          </table>
          </form>   
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
			      
                   
          <h2>材料信息列表</h2>
          <div align="center">
          <ec:table items="materialList" var="sr"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/material/material_find.html"
				rowsDisplayed='12' 
				pageSizeList="2,5,12,20,50,100,all"
				resizeColWidth="true" width="99%" listWidth="99%" height="600px"
				sortable="false" 
				useAjax="false" 
				style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="SUPPLIERNAME" title="供应商" width="11%" />
					<ec:column property="DENSITY" title="面密度" width="11%" style="text-align:right" />
					<ec:column property="THICKNESS" title="厚度" width="11%" style="text-align:right"/>
					<ec:column property="DESC1" title="描述" width="11%" />
					<ec:column property="WEIGHT" title="重量" width="11%"  style="text-align:right"/>
					<ec:column property="STANDARD" title="规格" width="11%"  style="text-align:center"/>
					<ec:column property="IS_LOGOUT" title="是否注销" width="11%" mappingItem="logoutMap"/>
					<ec:column property="LOGOUT_REASON" title="注销原因" width="11%" />
					<ec:column property="REMARK" title="备注" width="11%" />
					<ec:column property="_0" title="动作" width="11%" style="text-align:center">
						<c:if test="${sr.IS_LOGOUT==0}">
							<a href="${pageContext.request.contextPath}/admin/material/material_showModifyUI.html?material.uuid=${sr.ID}">修 改</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if>
						<a onclick="return delMsg(${sr.ID},${sr.IS_LOGOUT})" href="#">
							<c:if test="${sr.IS_LOGOUT==0}">
								注 销
							</c:if>
							<c:if test="${sr.IS_LOGOUT==1 }">
								恢复
							</c:if>
						</a>
					</ec:column>
				</ec:row>
			</ec:table>
		</div>
    </div><!-- /#container -->
  	<!--javascript start-->
  	<!--javascript end-->
</body>
</html>
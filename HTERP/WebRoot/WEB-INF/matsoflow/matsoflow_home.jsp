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
			if (confirm('恢复以后该材料重量会被重置为修正前重量，确定要注销此信息吗？')) {
				var str=prompt("注销原因？","");
				if(str!=null && str!=""){
					var h = "${pageContext.request.contextPath}/admin/matsoflow/matsoflow_delete.html?matsoflow.uuid="+id+"&matsoflow.isLogout=1&matsoflow.logoutReason="+str;
					window.location.href=h;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			if (confirm('恢复以后该材料重量会被重置为修正后重量，确定要恢复此信息吗？')) {
				var h = "${pageContext.request.contextPath}/admin/matsoflow/matsoflow_delete.html?matsoflow.uuid="+id+"&matsoflow.isLogout=0&matsoflow.logoutReason=' '";
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
          <h2>短溢处理记录查询</h2>
		 <form id="form1" action="${pageContext.request.contextPath}/admin/matsoflow/matsoflow_find.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th width="100">
                  	查询条件：
                  </th>
                  <td width="10">
                  	<select class="u-ipt" name="matsoflow_key">
                  		<option value="ALL">---查询所有---</option>
                  		<option value="PRC_TIME">处理时间</option>
                  		<option value="PRC_PERSON">处理人</option>
                  		<option value="PRC_REASON">处理原因</option>
                  		<option value="MAT_SUPPLIER_NAME">原材料</option>
                  	</select>
                  </td>
                  <th width="50"><input type="text" class="u-ipt" name="matsoflow_name"/></th>
                  <td>
					 <button type="button" class="u-btn" onclick="javascript:document.getElementById('form1').submit()">查询</button>&emsp;                   
                     <button type="button" class="u-btn" onclick="javascript:window.location='${pageContext.request.contextPath}/admin/matsoflow/matsoflow_showAddUI.html'">新增</button>&emsp;           
                   </td>
                </tr>                				                           
             </tbody>
          </table>
          </form>   
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
			      
                   
          <h2>短溢处理记录列表</h2>
          <div align="center">
          <ec:table items="matsoflowList" var="sr"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/matsoflow/matsoflow_find.html"
				rowsDisplayed='12' 
				pageSizeList="2,5,12,20,50,100,all"
				resizeColWidth="true" width="100%" listWidth="100%" height="600px"
				sortable="false" 
				useAjax="false" 
				style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="PRC_TIME" title="处理时间" width="11%" />
					<ec:column property="PRC_PERSON" title="处理人" width="11%" />
					<ec:column property="PRC_REASON" title="处理原因" width="11%" />
					<ec:column property="B_WEIGHT" title="修正前重量" width="11%" style="text-align:right" />
					<ec:column property="A_WEIGHT" title="修正后重量" width="11%" style="text-align:right"/>
					<ec:column property="MAT_SUPPLIER_NAME" title="原材料" width="11%"/>
					<ec:column property="_0" title="是否注销" width="11%" style="text-align:center">
						<c:if test="${sr.IS_LOGOUT==0}">
							<label>未注 销</label>
						</c:if>
						<c:if test="${sr.IS_LOGOUT==1}">
							<label style="color: red">注&nbsp;&nbsp;&nbsp;&nbsp;销</label>
						</c:if>
					</ec:column>
					<ec:column property="LOGOUT_REASON" title="注销原因" width="11%"/>
					<ec:column property="_0" title="动作" width="11%" style="text-align:center">
						<c:if test="${sr.IS_LOGOUT==0}">
							<a href="${pageContext.request.contextPath}/admin/matsoflow/matsoflow_showModifyUI.html?matsoflow.matD=${sr.MAT_D}&matsoflow.uuid=${sr.ID}">修 改</a>
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
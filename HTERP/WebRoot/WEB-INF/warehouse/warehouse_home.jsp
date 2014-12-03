<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.ecside.org" prefix="ec"%>
<jsp:include page="../top.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <title></title>
  <script type="text/javascript">
  	function recoverMsg() {
		if (confirm("您确定要恢复此信息吗？") == false) {
			return false;
		}
	}
  	function delMsg(Id){
  		if (confirm("您确定要恢复此信息吗？") == false) {
			return false;
		}else{
			var reason = prompt("请输入注销原因");
			if(reason != null && reason != ""){
	  			var href = "${pageContext.request.contextPath}/admin/warehouse/warehouse_delete.html?wh.WarehouseId=" + Id + "&wh.isLogout=1&wh.logOutReason=" + reason;
	  	  		window.location.href = href;
	  		}
		}
  	}
  	window.onload = function(){
  		var msg = "${msg}";
  		if(msg == 1){
  			alert("仓库中存在原材料，成品，无法注销");
  		}
  	};
  </script>
</head>
<body>
    <div id="container" class="container">
      <div class="hr10"></div>
          <div class="hr10"></div>
          <h2>仓库查询</h2>
		 <form id="form1" action="${pageContext.request.contextPath}/admin/warehouse/warehouse_find.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th class="tr">查询类型：</th>
                  <td>
	                  <select name="serachType" id="serachStr" class="u-slt validation-passed">
	                  	<option value="all">全部</option>
	                  	<option value="sign1">代码</option>
	                  	<option value="area">地区</option>
	                  </select>
                  </td>  
                  <td><input type="text" class="u-ipt" name="queryStr"></td>       
                  <td>
					 <button type="submit" class="u-btn">查询</button>&emsp;                   
                     <button type="button" class="u-btn" onclick="javascript:window.location='${pageContext.request.contextPath}/admin/warehouse/warehouse_showAddUI.html'">新增</button>&emsp;           
                   </td>
                </tr>                				                           
             </tbody>
          </table>
          </form>   
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
			      
                   
          <h2>仓库信息列表</h2>
          <div align="center">
          <ec:table items="whList" var="sr"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/warehouse/warehouse_showhome.html"
				rowsDisplayed='12' 
				pageSizeList="2,5,12,20,50,100,all"
				resizeColWidth="true" width="100%" listWidth="100%" height="600px"
				sortable="false" 
				useAjax="false" 
				style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="SIGN1" title="代码" width="25%" style="text-align:center"/>
					<ec:column property="AREA" title="地区" width="25%" style="text-align:center"/>
					<ec:column property="DESC1" title="描述" width="25%" style="text-align:center"/>
					<ec:column property="IS_LOGOUT" title="是否注销" width="25%" style="text-align:center" mappingItem="LOGOUT"/>
					<ec:column property="LOGOUT_REASON" title="注销原因" width="25%" style="text-align:center"/>
					<ec:column property="_0" title="动作" width="25%" style="text-align:center">
						<c:if test="${sr.IS_LOGOUT == 0}">
							<a href="${pageContext.request.contextPath}/admin/warehouse/warehouse_showModifyUI.html?wh.warehouseId=${sr.ID}">修 改</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a onclick="return delMsg(${sr.ID});" href="javascript:void(0)">注 销</a>
						</c:if>
						<c:if test="${sr.IS_LOGOUT == 1}">
							<a onclick="return recoverMsg()" href="${pageContext.request.contextPath}/admin/warehouse/warehouse_delete.html?wh.WarehouseId=${sr.ID}&wh.isLogout=0">恢 复</a>
						</c:if>
					</ec:column>
				</ec:row>
			</ec:table>
		</div>
    </div><!-- /#container -->
  	<!--javascript start-->
  	<!--javascript end-->
</body>
</html>
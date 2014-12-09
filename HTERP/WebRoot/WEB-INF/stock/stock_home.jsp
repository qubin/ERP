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
		if (confirm("您确定要恢复此信息吗？") == true) {
			var reason = prompt("请输入注销原因");
			if(reason != null && reason != ""){
				reason = encodeURI(reason);
				reason = encodeURI(reason);
				var href = "${pageContext.request.contextPath}/admin/rowFlow/rowFlow_delete.html?rowFlow.rfId=" + Id + "&rowFlow.isLogout=1&rowFlow.logoutReason=" + reason;
				window.location.href = href;
			}
		}	
	}
	var j = jQuery;
	j(document).ready(function(){
		j("#serachStr").bind("change",function(){
			if(j("#serachStr").val() == 1){
				j("#secStr").empty();
				j("#secStr").append("<option value=''>经办人</option>");
				j("#secStr").append("<option value=''>所在仓库</option>");
				j("#secStr").append("<option value=''>供应商</option>");
			}else{
				j("#secStr").empty();
				j("#secStr").append("<option value=''>华天产品编号</option>");
				j("#secStr").append("<option value=''>客户产品编号</option>");
				j("#secStr").append("<option value=''>所在仓库</option>");
				j("#secStr").append("<option value=''>客户ID</option>");
			}
		});
	});
	
  </script>
</head>
<body>
    <div id="container" class="container">
      <div class="hr10"></div>
          <div class="hr10"></div>
          <h2>库存查询</h2>
		 <form id="form1" action="${pageContext.request.contextPath}/admin/rowFlow/rowFlow_find.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th class="tr">查询类型：</th>
                  <td>
	                  <select name="serachType" id="serachStr" class="u-slt">
	                  	<option value="1">原材料</option>
	                  	<option value="2">成品</option>
	                  </select>
	                  <select name="serachType" id="secStr" class="u-slt validation-passed">
	                  	<option value=''>经办人</option>
	                  	<option value=''>所在仓库</option>
	                  	<option value=''>供应商</option>
	                  </select>
                  </td>  
                  <td><input type="text" class="u-ipt" name="queryStr"></td>       
                  <td>
					 <button type="submit" class="u-btn">查询</button>&emsp;                   
                   </td>
                </tr>                				                           
             </tbody>
          </table>
          </form>   
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
			      
                   
          <h2>原料出入库记录列表</h2>
          <div align="center">
          <ec:table items="rfList" var="sr"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/rowFlow/rowFlow_showHome.html"
				rowsDisplayed='12' 
				pageSizeList="2,5,12,20,50,100,all"
				resizeColWidth="true" width="100%" listWidth="100%" height="600px"
				sortable="false" 
				useAjax="false" 
				style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="OUT_PERSON" title="经办人" width="15%" style="text-align:center"/>
					<ec:column property="IN_OR_OUT" title="入库/出库" width="15%" style="text-align:center"/>
					<ec:column property="OUT_TIME" title="操作时间" width="15%" style="text-align:center"/>
					<ec:column property="COUNT" title="出/入库数量" width="15%" style="text-align:center"/>
					<ec:column property="PDCT_ID" title="成品ID" width="15%" style="text-align:center"/>
					<ec:column property="ISTCT_ID" title="生产指令单" width="15%" style="text-align:center"/>
					<ec:column property="BOX_NO" title="盒号" width="15%" style="text-align:center"/>
					<ec:column property="BATCH_CODE" title="批次" width="15%" style="text-align:center"/>
					<ec:column property="REMARK" title="备注" width="15%" style="text-align:center"/>
					<ec:column property="IS_LOGOUT" title="是否注销" width="15%" style="text-align:center" mappingItem="logoutMap"/>
					<ec:column property="LOGOUT_REASON" title="注销原因" width="15%" style="text-align:center"/>
					<ec:column property="_0" title="动作" width="15%" style="text-align:center">
						<c:if test="${sr.IS_LOGOUT == 0}">
							<a href="${pageContext.request.contextPath}/admin/rowFlow/rowFlow_showModifyUI.html?rowFlow.rfId=${sr.ID}">修 改</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a onclick="return delMsg(${sr.ID})" href="javascript:void(0)">注 销</a>
						</c:if>
						<c:if test="${sr.IS_LOGOUT == 1}">
							<a onclick="return recoverMsg()" href="${pageContext.request.contextPath}/admin/rowFlow/rowFlow_delete.html?rowFlow.rfId=${sr.ID}&rowFlow.isLogout=0">恢 复</a>
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
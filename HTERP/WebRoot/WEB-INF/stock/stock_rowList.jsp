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
		j("#statusSelect").hide();
		j("#whSelect").hide();
		
		j("#secStr").bind("change",function(){
			if(j(this).val() == "m.`is_logout`"){
				j("#queryStr").hide();
				j("#statusSelect").show();
				j("#whSelect").hide();
			}else if(j(this).val() == "sign1"){
				j("#whSelect").show();
				j("#queryStr").hide();
				j("#statusSelect").hide();
				addWh();
			}else if(j(this).val() == "all" || j(this).val() == "ht_mat_no" || j(this).val() == "mat_supplier_name" || j(this).val() == "s.`name`"){
				j("#whSelect").hide();
				j("#queryStr").show();
				j("#statusSelect").hide();
			}
		});
		
		function addWh(){
			j("#whSelect").empty();
			var uri = "${pageContext.request.contextPath}/admin/stock/stock_getWh.html";
			j.getJSON(uri,function(data){
				if(data != ""){
					for(var i = 0 ; i< data.length; i ++){
						j("#whSelect").append("<option value='" + data[i].warehouseId + "'>" + data[i].sign1 + "</option>");
					}
				}
			});
		}
		
		j("#whSelect").bind("change",function(){
			j("#queryStr").val(j(this).val());
		});
		
		j("#statusSelect").bind("change",function(){
			j("#queryStr").val(j(this).val());
		});
	});
	
  </script>
</head>
<body>
    <div id="container" class="container">
      <div class="hr10"></div>
      <input class="u-btn"type="button" onclick="javascript:history.back()" value="返回" />
          <div class="hr10"></div>
          <h2>库存查询</h2>
		 <form id="form1" action="${pageContext.request.contextPath}/admin/stock/stock_rowFind.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th class="tr">查询类型：</th>
                  <td>
	                  <select name="queryType" id="secStr" class="u-slt validate-selection">
	                  	<option value='all'>-全部-</option>
	                  	<option value='sign1'>所在仓库</option>
	                  	<option value='ht_mat_no'>华天材料号</option>
	                  	<option value='mat_supplier_name'>供应商材料名</option>
	                  	<option value='s.`name`'>供应商名</option>
	                  	<option value='m.`is_logout`'>状态</option>
	                  </select>
                  </td>  
                  <td>
                  	<input type="text" class="u-ipt" name="queryStr" id="queryStr">
                  	<select name="statusSelect" id="statusSelect" class="u-slt validate-selection">
                  		<option value="">请选择</option>
                  		<option value="0">未注销</option>
                  		<option value="1">注销</option>
                  	</select>
                  	<select name="wh" id="whSelect" class="u-slt validate-selection">
                  	</select>
                  </td>       
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
          <ec:table items="rowList" var="sr"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/stock/stock_rowList.html"
				rowsDisplayed='12' 
				pageSizeList="2,5,12,20,50,100,all"
				resizeColWidth="true" width="100%" listWidth="100%" height="600px"
				sortable="false" 
				useAjax="false" 
				style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="HT_MAT_NO" title="华天材料号" width="15%" style="text-align:center"/>
					<ec:column property="SIGN1" title="所在仓库" width="15%" style="text-align:center"/>
					<ec:column property="WEIGHT" title="重量" width="15%" style="text-align:center"/>
					<ec:column property="MAT_SUPPLIER_NAME" title="供应商材料名" width="15%" style="text-align:center"/>
					<ec:column property="NAME" title="供应商名" width="15%" style="text-align:center"/>
					<ec:column property="DESC1" title="描述" width="15%" style="text-align:center"/>
					<ec:column property="IS_LOGOUT" title="是否注销" width="15%" style="text-align:center" mappingItem="STATUS"/>
					<ec:column property="LOGOUT_REASON" title="注销原因" width="15%" style="text-align:center"/>
					<ec:column property="REMARK" title="备注" width="15%" style="text-align:center"/>
					<ec:column property="_0" title="动作" width="15%" style="text-align:center">
						<a href="${pageContext.request.contextPath}/admin/stock/stock_showDetail.html?m.uuid=${sr.ID}">详 情</a>
					</ec:column>
				</ec:row>
			</ec:table>
		</div>
    </div><!-- /#container -->
  	<!--javascript start-->
  	<!--javascript end-->
</body>
</html>
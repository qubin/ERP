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
		var arr = [j("#pSelect"),j("#mSelect"),j("#ptSelect"),j("#sSelect")];
		j.each(arr,function(k,v){
			this.hide();
		});
		
		j.each(arr,function(k,v){
			this.bind("change",function(){
				j("#queryStr").val(j(this).val());
			});
		});
		
		j("#secStr").bind("change",function(){
			var val = j(this).val();
			if(val == "properties"){
				j("#queryStr").hide();
				arr[0].show();
				arr[1].hide();
				arr[2].hide();
				arr[3].hide();
			}else if(val == "market"){
				j("#queryStr").hide();
				arr[0].hide();
				arr[1].show();
				arr[2].hide();
				arr[3].hide();
			}else if(val == "pattern_type"){
				j("#queryStr").hide();
				arr[0].hide();
				arr[1].hide();
				arr[2].show();
				arr[3].hide();
			}else if(val == "is_logout"){
				j("#queryStr").hide();
				arr[0].hide();
				arr[1].hide();
				arr[2].hide();
				arr[3].show();
			}else if(val == "all" || val == "ht_pn" || val == "app_for" || val == "pro_step"){
				j("#queryStr").show();
				j.each(arr,function(k,v){
					this.hide();
				});
			}
		});
	});
	
  </script>
</head>
<body>
    <div id="container" class="container">
      <div class="hr10"></div>
          <div class="hr10"></div>
          <input class="u-btn"type="button" onclick="javascript:history.back()" value="返回" />
          <h2>库存查询</h2>
		 <form id="form1" action="${pageContext.request.contextPath}/admin/stock/stock_prodFind.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th class="tr">查询类型：</th>
                  <td>
	                  <select name="secStr" id="secStr" class="u-slt validate-selection">
	                  	<option value="all">-全部-</option>
	                  	<option value="ht_pn">华天产品编号</option>
	                  	<option value="app_for">车型/刚被应用</option>
	                  	<option value="properties">产品性质</option>
	                  	<option value="market">产品市场</option>
	                  	<option value="pattern_type">模具类型</option>
	                  	<option value="pro_step">冲压步骤数</option>
	                  	<option value="is_logout">状态</option>
	                  </select>
	                  <select name="" id="pSelect" class="u-slt validate-selection">
	                  <option value="">请选择</option>
	                 	<option value="1">FLAT</option>
	                 	<option value="2">TAB</option>
	                 	<option value="3">DRIFTLOCK</option>
	                  </select>
	                  <select name="" id="mSelect" class="u-slt validate-selection">
	                  <option value="">请选择</option>
	                  	<option value="1">OE</option>
	                  	<option value="2">AF</option>
	                  </select>
	                  <select name="" id="ptSelect" class="u-slt validate-selection">
	                  <option value="">请选择</option>
	                 	<option value="1">PROG</option>
	                 	<option value="2">SINGLEDIE</option>
	                 	<option value="3">PROGSINGLE</option>
	                  </select>
	                  <select name="" id="sSelect" class="u-slt validate-selection">
	                 	<option value="">请选择</option>
	                 	<option value="1">注销</option>
	                 	<option value="0">未注销</option>
	                  </select>
                  </td>  
                  <td><input type="text" class="u-ipt" name="queryStr" id="queryStr"></td>       
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
          <ec:table items="prodList" var="sr"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/stock/stock_prodList.html"
				rowsDisplayed='12' 
				pageSizeList="2,5,12,20,50,100,all"
				resizeColWidth="true" width="100%" listWidth="100%" height="600px"
				sortable="false" 
				useAjax="false" 
				style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="HT_PN" title="华天产品编号" width="15%" style="text-align:center"/>
					<ec:column property="TOTAL_WEIGHT" title="总重量" width="15%" style="text-align:center"/>
					<ec:column property="APPFOR" title="车型/刚被应用" width="15%" style="text-align:center"/>
					<ec:column property="PROPERTIES" title="产品性质" width="15%" style="text-align:center" mappingItem="PROPERTIES"/>
					<ec:column property="MARKET" title="产品市场" width="15%" style="text-align:center" mappingItem="MARKET"/>
					<ec:column property="PATTERN_TYPE" title="模具类型" width="15%" style="text-align:center" mappingItem="PATTERN"/>
					<ec:column property="PROSTEP" title="冲压步骤数" width="15%" style="text-align:center"/>
					<ec:column property="IS_LOGOUT" title="是否注销" width="15%" style="text-align:center" mappingItem="LOG"/>
					
					<ec:column property="_0" title="动作" width="15%" style="text-align:center">
					<a href="${pageContext.request.contextPath}/admin/stock/stock_prodDetail.html?p.uuid=${sr.ID}">详情</a>
						</ec:column>
				</ec:row>
			</ec:table>
		</div>
    </div><!-- /#container -->
  	<!--javascript start-->
  	<!--javascript end-->
</body>
</html>
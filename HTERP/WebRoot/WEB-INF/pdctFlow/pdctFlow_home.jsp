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
		j("#inouts").hide();
		
		j("#serachStr").bind("change",function(){
			var val = j(this).val();
			if(val == "IN_OR_OUT"){
				j("#inouts").show();
				j("#queryStr").hide();
			}else{
				j("#inouts").hide();
				j("#queryStr").show();
			}
		});
		
		j("#inouts").bind("change",function(){
			j("#queryStr").val(j("#inouts").val());
		});
	});
  </script>
</head>
<body>
    <div id="container" class="container">
      <div class="hr10"></div>
          <div class="hr10"></div>
          <h2>客户查询</h2>
		 <form id="form1" action="${pageContext.request.contextPath}/admin/pdctFlow/pdctFlow_flowFind.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th class="tr">查询类型：</th>
                  <td>
	                  <select name="serachType" id="serachStr" class="u-slt validate-selection">
	                  	<option value="-1">-请选择-</option>
	                  	<option value="all">全部</option>
	                  	<option value="out_time">操作时间</option>
	                  	<option value="out_person">经办人</option>
	                  	<option value="HT_PN">华天产品号</option>
	                  	<option value="ISTCT_ID">生产指令单ID</option>
	                  	<option value="BATCH_CODE">批次</option>
						<option value="SIGN1">所在仓库</option>
						<option value="IN_OR_OUT">出/入库</option>
	                  </select>
                  </td>  
                  <td>
                  	<select name="" id="inouts" class="u-slt">
                  		<option value="-1">-请选择-</option>
                  		<option value="1">出库</option>
                  		<option value="2">入库</option>
                  	</select>
                  	<input type="text" class="u-ipt" name="queryStr" id="queryStr">     
                 	<img onclick="WdatePicker({el:'queryStr'})" src="${pageContext.request.contextPath}/assets/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
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
          <ec:table items="pfList" var="sr"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/pdctFlow/pdctFlow_flowHome.html"
				rowsDisplayed='12' 
				pageSizeList="2,5,12,20,50,100,all"
				resizeColWidth="true" width="100%" listWidth="100%" height="600px"
				sortable="false" 
				useAjax="false" 
				style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="OUT_PERSON" title="经办人" width="15%" style="text-align:center"/>
					<ec:column property="IN_OR_OUT" title="入库/出库" width="15%" style="text-align:center" mappingItem="inOrOut"/>
					<ec:column property="OUT_TIME" title="操作时间" width="15%" style="text-align:center"/>
					<ec:column property="COUNT" title="出/入库数量" width="15%" style="text-align:center"/>
					<ec:column property="HT_PN" title="华天成品号" width="15%" style="text-align:center"/>
					<ec:column property="ISTCT_ID" title="生产指令单" width="15%" style="text-align:center"/>
					<ec:column property="SIGN1" title="所在仓库" width="15%" style="text-align:center"/>
					<ec:column property="BOX_NO" title="盒号" width="15%" style="text-align:center">
						${sr.BOX_NUM - sr.BOX_NO } ~ ${sr.BOX_NUM }
					</ec:column>
					<ec:column property="BATCH_CODE" title="批次" width="15%" style="text-align:center"/>
					<ec:column property="REMARK" title="备注" width="15%" style="text-align:center"/>
					<ec:column property="_0" title="动作" width="15%" style="text-align:center">
					</ec:column>
				</ec:row>
			</ec:table>
		</div>
    </div><!-- /#container -->
  	<!--javascript start-->
  	<!--javascript end-->
</body>
</html>
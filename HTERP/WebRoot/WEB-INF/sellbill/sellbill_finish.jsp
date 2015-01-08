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
				var href = "${pageContext.request.contextPath}/admin/customer/customer_delete.html?cust.custId=" + Id + "&cust.isLogout=1&cust.logOutReason=" + reason;
				window.location.href = href;
			}
		}	
	}
	var j = jQuery;
	j(document).ready(function(){
		j("#serachStr").bind("change",function(){
			if(j(this).val() == "order_data"){
				j("#d12").attr("readonly","readonly");
			}else{
				j("#d12").removeAttr("readonly");
			}
		});
	});
	
	function checkFinishBill(sbId){
		j(document).ready(function(){
			var uri = "${pageContext.request.contextPath}/admin/sellbill/sellbill_checkFinishBill.html?sb.sbId=" + sbId;
			j.get(uri,function(data){
				if(data != ""){
					if(data == "false"){
						alert("该销售单未通过审核！");
					}else{
						window.location.href="${pageContext.request.contextPath}/admin/sellbill/sellbill_examineUI.html?sb.sbId=" + sbId + "&detail=finish";
					}
				}else{
					alert("该销售单未通过审核！");
				}
			});
		});
	}
  </script>
</head>
<body>
    <div id="container" class="container">
      <div class="hr10"></div>
          <div class="hr10"></div>
          <h2>订单查询</h2>
		 <form id="form1" action="${pageContext.request.contextPath}/admin/sellbill/sellbill_finishFind.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th class="tr">查询类型：</th>
                  <td>
	                  <select name="serachType" id="serachStr" class="u-slt">
	                  	<option value="">-请选择-</option>
	                  	<option value="all">全部</option>
	                  	<option value="code">订货编号</option>
	                  	<option value="order_date">订货日期</option>
	                  	<option value="name">客户</option>
	                  	<option value="con_person">联系人</option>
	                  	<option value="con_phone">联系电话</option>
	                  </select>
                  </td>  
                  <td>
                   <input type="text" class="u-ipt" name="queryStr" id="d12">
                  <img onclick="WdatePicker({el:'d12'})"
				  src="${pageContext.request.contextPath}/assets/js/My97DatePicker/skin/datePicker.gif"
				  width="16" height="22" align="absmiddle" id="datePick">
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
			      
                   
          <h2>订单信息列表</h2>
          <div align="center">
          <ec:table items="fList" var="sr"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/sellbill/sellbill_finish.html"
				rowsDisplayed='12' 
				pageSizeList="2,5,12,20,50,100,all"
				resizeColWidth="true" width="100%" listWidth="100%" height="600px"
				sortable="false" 
				useAjax="false" 
				style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="CODE" title="订货编号" width="11%" style="text-align:center"/>
					<ec:column property="ORDER_DATE" title="订货日期" width="11%" style="text-align:center"/>
					<ec:column property="NAME" title="客户" width="11%" style="text-align:center"/>
					<ec:column property="CON_PERSON" title="联系人" width="11%" style="text-align:center"/>
					<ec:column property="CON_PHONE" title="联系电话" width="11%" style="text-align:center"/>
					<ec:column property="REMARK" title="备注" width="11%" style="text-align:center"/>
					<ec:column property="VERIFY_STATUS" title="状态" width="11%" style="text-align:center" mappingItem="STATUS"/>
					<ec:column property="_0" title="动作" width="11%" style="text-align:center">
						<a href="" onclick="checkFinishBill(${sr.ID});">结  单</a>
					</ec:column>
				</ec:row>
			</ec:table>
		</div>
    </div><!-- /#container -->
  	<!--javascript start-->
  	<!--javascript end-->
</body>
</html>
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
					var h = "${pageContext.request.contextPath}/admin/finance/production_delete.html?finance.uuid="+id+"&finance.isLogout=1&finance.logoutReason="+str;
					window.location.href=h;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			if (confirm('您确定要恢复此信息吗？')) {
				var h = "${pageContext.request.contextPath}/admin/finance/production_delete.html?finance.uuid="+id+"&finance.isLogout=0&finance.logoutReason=' '";
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
          <h2>统计查询</h2>
		 <form id="form1" action="${pageContext.request.contextPath}/admin/finance/finance_findStatistics.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th width="100">
                  	统计日期：
                  </th>
                  <td width="10px">
                  	<input type="text" class="u-ipt" name="finance_key" id="d1"/>
                  </td>
                  <td width="10px">
                  	<img onclick="WdatePicker({el:'d1'})" src="${pageContext.request.contextPath}/assets/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
                  </td>
                  <td width="5px"><strong>到</strong></td>
                  <td width="10px">
                  	<input type="text" class="u-ipt" name="finance_name" id="d2"/>
                  </td>
                  <td width="10px">
                  	<img onclick="WdatePicker({el:'d2'})" src="${pageContext.request.contextPath}/assets/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
                  </td>      
                  <td>
					 <button type="button" class="u-btn" onclick="javascript:document.getElementById('form1').submit()">查询</button>&emsp;                   
                   </td>
                </tr>                				                           
             </tbody>
          </table>
          </form>   
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
			      
                   
          <h2>销售单信息列表</h2>
          <div align="center">
          <ec:table items="sellBillList" var="sr"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/finance/finance_findSellBill.html"
				rowsDisplayed='12' 
				pageSizeList="6"
				resizeColWidth="true" width="100%" listWidth="100%" height="600px"
				sortable="false" 
				useAjax="false" 
				style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="FINANCE_VERIFY_STATUS" title="审核状态" width="11%" style="text-align:center" mappingItem="STATUS" />
					<ec:column property="CODE" title="订单号" width="11%" style="text-align:center" />
					<ec:column property="ORDER_DATE" title="下单日期" width="11%" style="text-align:center"/>
					<ec:column property="LEAD_DATE" title="供货日期" width="11%" style="text-align:center"/>
					<ec:column property="CUS_PN" title="客户零件号" width="11%"/>
					<ec:column property="ORDER_COUNT" title="订单数量" width="11%"/>
					<ec:column property="AMOUNT" title="单据总金额" width="11%"/>
					<ec:column property="PREPAID" title="已付金额" width="11%"/>
				</ec:row>
			</ec:table>
			<br/>
			<table class="m-table-form" style="width:20%;" align="left" valign="middle">
				<tr>
					<td colspan="2" style="color: black;"><h2>销售单金额统计</h2></td>
				</tr>
				<tr>
					<td width="120px;" style="text-align: right;color: black;">所有销售单总金额：</td>
					<td style="text-align: right;color: black;"><strong>${sumAmount}</strong></td>
				</tr>
				<tr>
					<td width="120px;" style="text-align: right;color: black;">所有销售单已收金额：</td>
					<td style="text-align: right;color: black;"><strong>${sumPrepaid}</strong></td>
				</tr>
				<tr>
					<td width="120px;" style="text-align: right;color:red;">所有销售单未收金额：</td>
					<td style="text-align: right;color:red;"><strong>${sumNoPrepaid}</strong></td>
				</tr>
			</table>
		</div>
    </div><!-- /#container -->
  	<!--javascript start-->
  	<!--javascript end-->
</body>
</html>

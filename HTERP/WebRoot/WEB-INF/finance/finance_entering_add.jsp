<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="../top.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title></title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div id="container" class="container">
		<div class="hr10"><strong>订单付款</strong></div>
		<form id="form1" action="${pageContext.request.contextPath}/admin/finance/finance_saveAddFinance.html" method="post">
			
			<input type="hidden" name="financePay.sellBillId" value="${sellBillList[0].BILLID}"/>
			
			<table class="m-table-form">
				<tbody>
					<tr height="35px">
						<th width="47%" class="tr">订&nbsp;单&nbsp;号：</th>
						<td>${sellBillList[0].CODE}</td>
					</tr>
					<tr height="35px">
						<th class="tr">下单日期：</th>
						<td>${sellBillList[0].ORDER_DATE}</td>
					</tr>
					<tr height="35px">
						<th class="tr">供货日期：</th>
						<td>${sellBillList[0].LEAD_DATE}</td>
					</tr>
					<tr height="35px">
						<th class="tr">客户零件号：</th>
						<td>${sellBillList[0].CUS_PN}</td>
					</tr>
					<tr height="35px">
						<th class="tr">订单数量：</th>
						<td>${sellBillList[0].ORDER_COUNT}</td>
					</tr>
					<tr height="35px">
						<th class="tr">单据总金额：</th>
						<td>${sellBillList[0].AMOUNT}</td>
					</tr>
					<tr height="35px">
						<th class="tr">单据已付金额：</th>
						<td>${sellBillList[0].PREPAID}</td>
					</tr>
					<tr height="35px">
						<th class="tr">单据未付金额：</th>
						<td>${sellBillList[0].AMOUNT-sellBillList[0].PREPAID}</td>
					</tr>
					<tr>
						<th class="tr">本次支付金额：</th>
						<td><input type="text" class="u-ipt required" name="financePay.prepaid" maxlength="30"></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2" class="tc"><input class="u-btn" type="submit"
							value="提交" id="submitBtn" /> &emsp; <input class="u-btn"
							type="button" onclick="javascript:history.back()" value="返回" /></td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
	<!-- /#container -->
</body>
</body>
</html>
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
	//审核
	function audit() {
		if (confirm("确定要通过审核吗？")) {
			document.getElementById("txtVerifyStatus").value = "1"; //设置审核状态

			var valVerifyRemark = document.getElementById("txtVerifyRemark").value;
			if (valVerifyRemark == "") {
				document.getElementById("txtVerifyRemark").value = "审核通过";
			}
		} else {
			return false;
		}
	}
	//未通过
	function notAudit() {
		var valVerifyRemark = document.getElementById("txtVerifyRemark").value;
		if (valVerifyRemark == "") {
			alert("请填写未通过审核原因！");
			return false;
		} else {
			document.getElementById("txtVerifyStatus").value = "0"; //设置审核状态
		}
	}
</script>
</head>
<body>
	<div id="container" class="container">
		
		<form id="form1" action="${pageContext.request.contextPath}/admin/finance/finance_saveAudit.html" method="post">
			
			<input type="hidden" name="financePay.uuid" value="${sellBillList[0].FPID}"/>
			<input type="hidden" name="financePay.financeVerifyStatus" id="txtVerifyStatus"/>
			
			<table align="right">
				<tr>
					<td><input type="text" id="txtVerifyRemark" name="financePay.financeVerifyRemark" class="u-ipt" />&nbsp;&nbsp;</td>
					<td><input type="submit" onclick="return audit()" class="u-btn" value="审核通过">&nbsp;&nbsp;</td>
					<td><input type="submit" onclick="return notAudit()" class="u-btn" value="未通过">&nbsp;&nbsp;</td>
					<td><input type="button" onclick="javascript:history.back()" class="u-btn" value="返回">&nbsp;&nbsp;</td>
				</tr>
			</table>
			<br/>
			<div class="hr10"><strong>订单付款</strong></div>
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
						<td>${sellBillList[0].PREPAIDNOW}</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<!-- /#container -->
</body>
</body>
</html>
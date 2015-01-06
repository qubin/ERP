<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="../top.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title></title>
<style type="text/css">
<!--
.STYLE1 {
	color: #000000;
	font-size: 18px;
}

.text-box {
	border-bottom: #666 1px solid;
	border-left-width: 0px;
	border-right-width: 0px;
	border-top-width: 0px;
	background: #F6F5F5
}
-->
</style>
<script type="text/javascript">
	function getMaterialInfo(obj) {
		var url = "${pageContext.request.contextPath}/admin/production/production_getMaterialInfo.html?materId="
				+ obj.value;
		new Ajax.Request(
				url,
				{
					method : 'get',
					onSuccess : function(data) {
						var jsonData = eval("(" + data.responseText + ")");
						document.getElementById("txtClgg").innerHTML = jsonData.standard;
					}
				});
	}

	window.onload = function() {
		//获得销售信息
		var url = "${pageContext.request.contextPath}/admin/production/production_getSellBillInfoByPdceistctId.html";
		new Ajax.Request(
				url,
				{
					method : 'get',
					onSuccess : function(data) {
						var jsonData = eval("(" + data.responseText + ")");
						document.getElementById("txtDdsl").innerHTML = jsonData[0].order_count;
						document.getElementById("txtKhljh").innerHTML = jsonData[0].cus_pn;
						document.getElementById("txtXdrq").innerHTML = jsonData[0].order_date;
						document.getElementById("txtDdh").innerHTML = jsonData[0].code;
						document.getElementById("sellBillId").value = jsonData[0].id;
					}
				});

	}
</script>
</head>
<body>
	<div id="container" class="container">
		<div align="right">
			<br />
		</div>
		<form id="form1" action="${pageContext.request.contextPath}/admin/production/production_add.html" method="post">
			<input type="hidden" id="sellBillId" name="sellBillId" />
			<table width="102%" class="m-table-form">
				<tbody>
					<tr>
						<th class="tr" colspan="6"><h1 align="center" class="STYLE1">生产指令单( work order )</h1></th>
					</tr>
					<tr>
						<th class="tr">材料名称：</th>
						<td><s:select name="txtCLmc" 
								onchange="getMaterialInfo(this)"
								cssStyle="width:178px"
								cssClass="u-ipt required validate-selection"
								list="#request.materialList" listKey="ID"
								listValue="MAT_SUPPLIER_NAME" headerKey="-1"
								headerValue="---请选择---" /></td>
						<th class="tr">华天零件号：</th>
						<td><s:select name="txtHtljh" 
								cssStyle="width:178px"
								cssClass="u-ipt required validate-selection"
								list="#request.productList" listKey="ID" listValue="HT_PN"
								headerKey="-1" headerValue="---请选择---" /></td>
						<th class="tr">订单数量：</th>
						<td><label id="txtDdsl"></label></td>
					</tr>
					<tr>
						<th class="tr">使用重量：</th>
						<td><input type="text" class="u-ipt" id="txtSyzl" name="txtSyzl" />KG</td>
						<th class="tr">客户零件号：</th>
						<td><label id="txtKhljh"></label></td>
						<th class="tr">下单日期：</th>
						<td><label id="txtXdrq"></label></td>
					</tr>
					<tr>
						<th class="tr">材料规格：</th>
						<td><label id="txtClgg"></label></td>
						<th class="tr">订单号：</th>
						<td><label id="txtDdh"></label></td>
						<th class="tr">完成日期：</th>
						<td>
							<input type="text" class="u-ipt required" maxlength="13" id="d12" name="txtWcrq" readonly="readonly" />
							<img onclick="WdatePicker({el:'d12'})" src="${pageContext.request.contextPath}/assets/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
						</td>
					</tr>
					<!-- <tr>
						<th class="tr">O E件：</th>
						<td><input name="product.market" type="radio" value="radio" checked="checked"></td>
						<th class="tr">售后件：</th>
						<td><input name="product.market" type="radio" value="radio"></td>
					</tr> -->
				</tbody>
			</table>
			<br/>
			<div>
				<div align="center">
					<button class="u-btn" onClick="javascript:window.location.href='生产指令单.html';alert('提交成功！')">提&nbsp;&nbsp;交</button>
					<button class="u-btn" onClick="javascript:history.back()">返&nbsp;&nbsp;回</button>
				</div>
			</div>
			<br />
		</form>
	</div>
	<!-- /#container -->
</body>
</body>
</html>
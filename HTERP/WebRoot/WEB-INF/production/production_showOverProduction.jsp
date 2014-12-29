<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.ecside.org" prefix="ec"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	window.onload = function() {

	}
</script>
</head>

<body>

<form id="form1" action="${pageContext.request.contextPath}/admin/production/production_overProduction.html" method="post">

	<input type="hidden" value="${sczldList[0].SCZLDID}" name="pdceistct.uuid"><!-- 生产指令单ID -->
	<input type="hidden" value="${sczldList[0].MATERIALID}" name="material.uuid"><!-- 材料表ID -->
	<input type="hidden" value="${sczldList[0].CUS_PN}" name="cusPdct.cus_pn"><!-- 客户零件号 -->
	<input type="hidden" value="${sczldList[0].PRODUCTID}" name="product.uuid"><!-- 产品表ID -->

	<div id="container" class="container">
		<div align="left">
			<font color="red">审核信息：该指令单审核通过</font> <br /> <br />
		</div>
		<table width="102%" class="m-table-form">
			<tbody>
				<tr>
					<th class="tr" colspan="6"><h1 align="center" class="STYLE1">生产指令单( work order )</h1></th>
				</tr>
				<tr>
					<th class="tr">材料名称：</th>
					<td>${sczldList[0].MAT_SUPPLIER_NAME }</td>
					<th class="tr">华天零件号：</th>
					<td>${sczldList[0].HT_PN }</td>
					<th class="tr">订单数量：</th>
					<td>${sczldList[0].ORDER_COUNT}</td>
				</tr>
				<tr>
					<th class="tr">使用重量：</th>
					<td>${sczldList[0].USED_MAT_WEIGHT}KG</td>
					<th class="tr">客户零件号：</th>
					<td>${sczldList[0].CUS_PN}</td>
					<th class="tr">下单日期：</th>
					<td>${sczldList[0].ORDER_DATE}</td>
				</tr>
				<tr>
					<th class="tr">材料规格：</th>
					<td>${sczldList[0].STANDARD}</td>
					<th class="tr">订单号：</th>
					<td>${sczldList[0].CODE}</td>
					<th class="tr">完成日期：</th>
					<td>${sczldList[0].END_DATE}</td>
				</tr>
			</tbody>
		</table>
		<br />
		<table width="50" align="right" class="m-table-form">
			<tr>
				<th width="165" align="center"><div align="center">完成总数量</div></th>
				<th width="174" align="center">
					<div align="center">
						<input name="pdceistct.pdceCount" type="text" class="text-box" id="txtPdceCount" style="width:120px;">
					</div>
				</th>
				<th width="174" align="center"><div align="center">报废总数量</div></th>
				<th width="174" align="center">
					<div align="center">
						<input name="pdceistct.scpCount" type="text" class="text-box" id="txtScpCount" style="width:120px;">
					</div>
				</th>
				<th width="174" align="center"><div align="center">原料报废总数量</div></th>
				<th width="174" align="center">
					<input name="pdceistct.matScpWeight" type="text" class="text-box" id="txtMatScpWeight" style="width:120px;">
				</th>
				<th width="174" align="center"><div align="center">退料总数量</div></th>
				<th width="174" align="center">
					<input name="pdceistct.backMaterialCount" type="text" class="text-box" id="txtBackMaterialCount" style="width:120px;">
				</th>
			</tr>

			<tr>
				<td height="80" colspan="8">
					<table border="0" align="right" cellpadding="0" cellspacing="0">
						<tr>
							<td>生产负责人：</td>
							<td><input name="pdceistct.pdctPerson" type="text" style="width:120px;" class="text-box" id="txtPdctPerson"></td>
							<td>QC：</td>
							<td><input name="pdceistct.qcPerson" type="text" style="width:120px;" class="text-box" id="txtQcPerson"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="8" align="center">
					<div align="center">
						<input class="u-btn" type="submit" value="确认结单">
						<input class="u-btn" type="button" onclick="javascript:history.back()" value="返 &nbsp;&nbsp;&nbsp;&nbsp;回">
					</div>
				</td>
			</tr>
		</table>

		<br /> <br /> <br /> <br />
		<p>&nbsp;</p>
	</div>
	
</form>
	
	<!-- /#container -->
</body>
</html>
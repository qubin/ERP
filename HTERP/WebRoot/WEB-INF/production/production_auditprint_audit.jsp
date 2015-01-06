<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.ecside.org" prefix="ec"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css"
	href='${pageContext.request.contextPath}/ecside/css/ecside_style.css' />
<script type="text/javascript"
	src='${pageContext.request.contextPath}/ecside/js/prototype_mini.js'></script>
<script type="text/javascript"
	src='${pageContext.request.contextPath}/ecside/js/ecside_msg_utf8_cn.js'></script>
<script type="text/javascript"
	src='${pageContext.request.contextPath}/ecside/js/ecside.js'></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/libs/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/libs/jquery.easyui.custom.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery.easyui.extend.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery.plugins.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery.syadmin.js"></script>
<script language="JavaScript"
	src="${pageContext.request.contextPath}/assets/js/My97DatePicker/WdatePicker.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/validation/prototype.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/validation/effects.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/validation/validation_cn.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/libs/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/myUtils.js"
	type="text/javascript"></script>

<script language="javascript"
	src="${pageContext.request.contextPath}/assets/js/LodopFuncs.js"></script>

<title></title>

<style type="text/css" id="style1">
<!--
.input {
	border-style: none;
	border-bottom-style: solid;
	border-bottom-width: 1px;
	border-bottom-color: black;
}
.m-table-form {
	font-size: 12px;
}
-->
</style>
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

	function printView() {

		//window.open("${pageContext.request.contextPath}/admin/production/showView.html");

		var LODOP = getLodop(document.getElementById('LODOP_OB'), document
				.getElementById('LODOP_EM'));
		var strBodyStyle = "<style>"
				+ document.getElementById("style1").innerHTML + "</style>";
		LODOP.PRINT_INITA(0, 0, 1120, 1200, "打印控件功能演示_Lodop功能_表单一");
		LODOP.SET_PRINT_STYLE("FontSize", 18);
		LODOP.SET_PRINT_STYLE("Bold", 1);
		LODOP.ADD_PRINT_HTM(0, 0, 1100, 1000, strBodyStyle + "<body>"
				+ document.getElementById("container").innerHTML + "</body>");
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED", 1);//横向时的正向显示
		//LODOP.PRINT_DESIGN();
		LODOP.PREVIEW();
	}

	window.onload = function() {
		
	}
</script>
</head>

<body>

	<object id="LODOP_OB"
		classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
		<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
	</object>

	<form id="form1"
		action="${pageContext.request.contextPath}/admin/production/production_audit.html"
		method="post">

		<input type="hidden" name="txtVerifyStatus" id="txtVerifyStatus">

		<table align="right">
			<tr>
				<td><input type="text" id="txtVerifyRemark"
					name="pdceistct.verifyRemark" class="u-ipt" /></td>
				<td><input class="u-btn" type="button" value="预   览"
					onclick="javascript:printView()"></td>
				<td><input type="submit" onclick="return audit()" class="u-btn"
					value="审核通过并打印"></td>
				<td><input type="submit" onclick="return notAudit()"
					class="u-btn" value="未通过"></td>
				<td><input type="button" onclick="javascript:history.back()"
					class="u-btn" value="返回"></td>
			</tr>
		</table>


		<div id="container" class="container">
			&nbsp;&nbsp; &nbsp; &nbsp; <img
				src="<%=request.getContextPath()%>/assets/images/log.png">
			<table width="95%" align="center" class="m-table-form" widtd="102%">
				<tbody>
					<tr>
						<td colspan="8">
							<div align="center">
								<h3>生产指令单 Work Order</h3>
							</div>
						</td>
					</tr>
					<tr>
						<td width="9%" height="20" align="right" class="tr"><span
							class="m-table-form">材料名称：</span></td>
						<td width="19%" height="20"><span class="m-table-form">
								<input name="txt1" type="text" class="input" id="txt1" value="${sczldList[0].MAT_SUPPLIER_NAME}" readonly>
						</span></td>
						<td width="11%" height="20" align="right" class="tr">
							<span class="m-table-form">华天零件号：</span></td>
						<td width="17%" height="20"><span class="m-table-form">
								<input name="txt2" type="text" class="input" id="txt2" value="${sczldList[0].HT_PN}" readonly>
						</span></td>
						<td width="12%" height="20" align="right" class="tr"><span
							class="m-table-form">订单数量：</span></td>
						<td width="18%" height="20"><span class="m-table-form">
								<input name="txt3" type="text" class="input" id="txt3" readonly value="${sczldList[0].ORDER_COUNT}pcs">
						</span></td>
						<td width="7%" height="20" align="right"><span
							class="m-table-form">O E件：</span></td>
						<td width="7%" height="20"><span class="m-table-form">
								<input name="radio" type="radio" id="radio" value="radio"
								checked="">
						</span></td>
					</tr>

					<tr>
						<td height="20" align="right" class="tr"><span
							class="m-table-form">使用重量：</span></td>
						<td height="20"><span class="m-table-form"> <input
								name="txt4" type="text" class="input" id="txt4" readonly value="${sczldList[0].USED_MAT_WEIGHT}KG">
								
						</span></td>
						<td height="20" align="right" class="tr"><span
							class="m-table-form">客户零件号：</span></td>
						<td height="20"><span class="m-table-form"> <input
								name="txt4" type="text" class="input" value="LJH-1001" readonly value="${sczldList[0].CUS_PN}">
						</span></td>
						<td height="20" align="right" class="tr"><span
							class="m-table-form">下单日期：</span></td>
						<td height="20"><span class="m-table-form"> <input
								name="txt5" type="text" class="input" id="txt5" readonly value="${sczldList[0].ORDER_DATE}">
						</span></td>
						<td height="20" align="right">&nbsp;</td>
						<td height="20">&nbsp;</td>
					</tr>
					<tr>
						<td height="20" align="right" class="tr"><span
							class="m-table-form">材料规格：</span></td>
						<td height="20"><span class="m-table-form"> <input
								name="txt6" type="text" class="input" id="txt6" readonly value="${sczldList[0].STANDARD}">
						</span></td>
						<td height="20" align="right" class="tr"><span
							class="m-table-form">订单号：</span></td>
						<td height="20"><span class="m-table-form"> <input
								name="txt7" type="text" class="input" id="txt7" readonly value="${sczldList[0].CODE}">
						</span></td>
						<td height="20" align="right" class="tr"><span
							class="m-table-form">完成日期：</span></td>
						<td height="20"><span class="m-table-form"> <input
								name="txt8" type="text" class="input" id="txt8" readonly value="${sczldList[0].END_DATE}">
						</span></td>
						<td height="20" align="right"><span class="m-table-form">售后件：</span></td>
						<td height="20"><span class="m-table-form"> <input
								type="radio" name="radio" id="radio2" value="radio">
						</span></td>
					</tr>
				</tbody>
			</table>
			
			<table width="95%" border="1" align="center"
				style="border-collapse: collapse; font-size: 12px; ">
				<tbody>
					<tr>
						<td width="5%" height="30" align="center" class="cola" widtd="100">工序</td>
						<td width="10%" height="30" align="center" class="cola"
							widtd="150">机台号/工序名称</td>
						<td width="12%" height="30" align="center" class="colb">单工序完成数量(pcs)</td>
						<td width="13%" height="30" align="center" class="cola">签名/日期(月-日)</td>
						<td width="10%" height="30" align="center" class="cola">单盒数量(pcs)</td>
						<td width="10%" height="30" align="center" class="cola">单盒重量(kg)</td>
						<td width="14%" height="30" align="center" class="cola">单工序不良品数量(pcs)</td>
						<td width="10%" height="30" align="center" class="cola"
							widtd="100">调机品数量(pcs)</td>
						<td width="16%" height="30" align="center" class="colb">备注</td>
					</tr>
					<tr class="even">
						<td height="25" valign="middle" widtd="50"><div
								align="center">1</div></td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
					</tr>
					<tr class="hover">
						<td height="25" valign="middle" widtd="50"><div
								align="center">2</div></td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
					</tr>
					<tr class="even">
						<td height="25" valign="middle" widtd="50"><div
								align="center">3</div></td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
					</tr>
					<tr class="hover">
						<td height="25" valign="middle"><div align="center">4</div></td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
					</tr>
					<tr class="even">
						<td height="25" valign="middle"><div align="center">5</div></td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
					</tr>
					<tr class="hover">
						<td height="25" valign="middle"><div align="center">6</div></td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
					</tr>
					<tr class="even">
						<td height="25" valign="middle"><div align="center">7</div></td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
					</tr>
					<tr class="hover">
						<td height="25" valign="middle"><div align="center">8</div></td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
					</tr>
					<tr class="even">
						<td height="25" valign="middle"><div align="center">9</div></td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
					</tr>
					<tr class="hover">
						<td height="25" valign="middle"><div align="center">10</div></td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
						<td height="25">&nbsp;</td>
					</tr>
				</tbody>
			</table>
			<table width="95%" align="center" class="m-table-form">
				<tbody>
					<tr class="aa">
						<td width="18%" height="30"><div align="right">完成总数（pcs）：</div></td>
						<td width="16%" height="25"><div align="left">
								<input name="txt24" type="text" class="input" id="txt24"
									readonly>
							</div></td>
						<td width="14%" height="25"><div align="right">不良品总数(pcs):</div></td>
						<td width="21%" height="25"><input name="txt55" type="text"
							class="input" id="txt55" readonly></td>
						<td width="10%" height="25"><div align="right">不良率(%):</div></td>
						<td width="21%" height="25"><input name="txt56" type="text"
							class="input" id="txt56" readonly></td>
					</tr>
				</tbody>
			</table>


			<table width="95%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="m-table-form" widtd="100%">
				<tbody>
					<tr>
						<td width="178" height="20" align="center" widtd="189"><div
								align="center">用料信息(卷号)</div></td>
						<td width="99" height="20" align="center" widtd="96"><div
								align="center">重量</div></td>
						<td width="36" height="20" align="center" widtd="96">&nbsp;</td>
						<td width="168" height="20" align="center" widtd="136"><div
								align="center">签名/日期</div></td>
						<td height="20" colspan="3" align="center" widtd="102"><div
								align="center">退料信息(卷号)</div>
							<div align="center"></div></td>
						<td height="20" colspan="3" align="center" widtd="58"><div
								align="center">重量</div></td>
						<td width="195" height="20" align="center" widtd="136"><div
								align="center">签名/日期</div></td>
					</tr>
					<tr>
						<td height="20"><div align="center">
								<input style="width:120px" name="txt24" type="text"
									class="input" id="txt24" readonly>
							</div></td>
						<td width="99" height="20" align="center"><div align="center">
								<input style="width:80px" name="txt12" type="text" class="input"
									id="txt12" readonly>
							</div></td>
						<td height="20" align="left">KG</td>
						<td height="20" widtd="136"><div align="center">
								<input name="txt10" type="text" style="widtd:120px;"
									class="input" id="txt10" readonly>
							</div></td>
						<td width="62" height="20" widtd="110">&nbsp;</td>
						<td width="85" height="20" widtd="110">退&nbsp;&nbsp;料：</td>
						<td width="120" height="20"><input style="width:120px"
							name="txt30" type="text" class="input" id="txt30" readonly></td>
						<td width="55" height="20"><div align="center">重量：</div></td>
						<td width="80" height="20"><input style="width:80px;"
							name="txt37" type="text" class="input" id="txt37" readonly></td>
						<td width="29" height="20"><div align="left">KG</div></td>
						<td height="20"><div align="center">
								<input name="txt44" type="text" style="widtd:120px;"
									class="input" id="txt44" readonly>
							</div></td>
					</tr>
					<tr>
						<td height="20"><div align="center">
								<input style="width:120px" name="txt23" type="text"
									class="input" id="txt23" readonly>
							</div></td>
						<td height="20" align="center"><div align="center">
								<input style="width:80px" name="txt11" type="text" class="input"
									id="txt11" readonly>
							</div></td>
						<td height="20" align="left">KG</td>
						<td height="20" widtd="136"><div align="center">
								<input name="txt9" type="text" style="widtd:120px;"
									class="input" id="txt9" readonly>
							</div></td>
						<td width="62" height="20" widtd="110">&nbsp;</td>
						<td height="20" widtd="110">退&nbsp;&nbsp;料：</td>
						<td height="20"><input style="width:120px" name="txt31"
							type="text" class="input" id="txt31" readonly></td>
						<td height="20"><div align="center">重量：</div></td>
						<td height="20"><input style="width:80px;" name="txt38"
							type="text" class="input" id="txt38" readonly></td>
						<td height="20"><div align="left">KG</div></td>
						<td height="20"><div align="center">
								<input name="txt45" type="text" style="widtd:120px;"
									class="input" id="txt45" readonly>
							</div></td>
					</tr>
					<tr>
						<td height="20"><div align="center">
								<input style="width:120px" name="txt22" type="text"
									class="input" id="txt22" readonly>
							</div></td>
						<td height="20" align="center"><div align="center">
								<input style="width:80px" name="txt13" type="text" class="input"
									id="txt13" readonly>
							</div></td>
						<td height="20" align="left">KG</td>
						<td height="20" widtd="136"><div align="center">
								<input name="txt25" type="text" style="widtd:120px;"
									class="input" id="txt25" readonly>
							</div></td>
						<td width="62" height="20" widtd="110">&nbsp;</td>
						<td height="20" widtd="110">退&nbsp;&nbsp;料：</td>
						<td height="20"><input style="width:120px" name="txt32"
							type="text" class="input" id="txt32" readonly></td>
						<td height="20"><div align="center">重量：</div></td>
						<td height="20"><input style="width:80px;" name="txt39"
							type="text" class="input" id="txt39" readonly></td>
						<td height="20"><div align="left">KG</div></td>
						<td height="20"><div align="center">
								<input name="txt46" type="text" style="widtd:120px;"
									class="input" id="txt46" readonly>
							</div></td>
					</tr>
					<tr>
						<td height="20"><div align="center">
								<input style="width:120px" name="txt18" type="text"
									class="input" id="txt18" readonly>
							</div></td>
						<td height="20" align="center"><div align="center">
								<input style="width:80px" name="txt14" type="text" class="input"
									id="txt14" readonly>
							</div></td>
						<td height="20" align="left">KG</td>
						<td height="20" widtd="136"><div align="center">
								<input name="txt26" type="text" style="widtd:120px;"
									class="input" id="txt26" readonly>
							</div></td>
						<td width="62" height="20" widtd="110">&nbsp;</td>
						<td height="20" widtd="110">退&nbsp;&nbsp;料：</td>
						<td height="20"><input style="width:120px" name="txt33"
							type="text" class="input" id="txt33" readonly></td>
						<td height="20"><div align="center">重量：</div></td>
						<td height="20"><input style="width:80px;" name="txt40"
							type="text" class="input" id="txt40" readonly></td>
						<td height="20"><div align="left">KG</div></td>
						<td height="20"><div align="center">
								<input name="txt51" type="text" style="widtd:120px;"
									class="input" id="txt51" readonly>
							</div></td>
					</tr>
					<tr>
						<td height="20"><div align="center">
								<input style="width:120px" name="txt19" type="text"
									class="input" id="txt19" readonly>
							</div></td>
						<td height="20" align="center"><div align="center">
								<input style="width:80px" name="txt15" type="text" class="input"
									id="txt15" readonly>
							</div></td>
						<td height="20" align="left">KG</td>
						<td height="20" widtd="136"><div align="center">
								<input name="txt27" type="text" style="widtd:120px;"
									class="input" id="txt27" readonly>
							</div></td>
						<td width="62" height="20" widtd="110">&nbsp;</td>
						<td height="20" widtd="110">退&nbsp;&nbsp;料：</td>
						<td height="20"><input style="width:120px" name="txt34"
							type="text" class="input" id="txt34" readonly></td>
						<td height="20"><div align="center">重量：</div></td>
						<td height="20"><input style="width:80px;" name="txt41"
							type="text" class="input" id="txt41" readonly></td>
						<td height="20"><div align="left">KG</div></td>
						<td height="20"><div align="center">
								<input name="txt48" type="text" style="widtd:120px;"
									class="input" id="txt48" readonly>
							</div></td>
					</tr>
					<tr>
						<td height="20"><div align="center">
								<input style="width:120px" name="txt20" type="text"
									class="input" id="txt20" readonly>
							</div></td>
						<td height="20" align="center"><div align="center">
								<input style="width:80px" name="txt16" type="text" class="input"
									id="txt16" readonly>
							</div></td>
						<td height="20" align="left">KG</td>
						<td height="20" widtd="136"><div align="center">
								<input name="txt28" type="text" style="widtd:120px;"
									class="input" id="txt28" readonly>
							</div></td>
						<td width="62" height="20" widtd="110">&nbsp;</td>
						<td height="20" widtd="110">退&nbsp;&nbsp;料:</td>
						<td height="20"><input style="width:120px" name="txt35"
							type="text" class="input" id="txt35" readonly></td>
						<td height="20"><div align="center">重量：</div></td>
						<td height="20"><input style="width:80px;" name="txt42"
							type="text" class="input" id="txt42" readonly></td>
						<td height="20"><div align="left">KG</div></td>
						<td height="20"><div align="center">
								<input name="txt47" type="text" style="widtd:120px;"
									class="input" id="txt47" readonly>
							</div></td>
					</tr>
					<tr>
						<td height="20"><div align="center">
								<input style="width:120px" name="txt21" type="text"
									class="input" id="txt21" readonly>
							</div></td>
						<td height="20" align="center"><div align="center">
								<input style="width:80px" name="txt17" type="text" class="input"
									id="txt17" readonly>
							</div></td>
						<td height="20" align="left">KG</td>
						<td height="20" widtd="136"><div align="center">
								<input name="txt29" type="text" style="widtd:120px;"
									class="input" id="txt29" readonly>
							</div></td>
						<td width="62" height="20" widtd="110">&nbsp;</td>
						<td height="20" widtd="110">原材料报废：</td>
						<td height="20"><input style="width:120px" name="txt36"
							type="text" class="input" id="txt36" readonly></td>
						<td height="20"><div align="center">重量：</div></td>
						<td height="20"><input style="width:80px;" name="txt43"
							type="text" class="input" id="txt43" readonly></td>
						<td height="20"><div align="left">KG</div></td>
						<td height="20"><div align="center">
								<input name="txt49" type="text" style="widtd:120px;"
									class="input" id="txt49" readonly>
							</div></td>
					</tr>
				</tbody>
			</table>
			<table width="95%" align="center" class="m-table-form">
				<tbody>
					<tr>
						<td height="30" colspan="9">补充说明：(例)若生产过程中每道工序的不良率大于5%，需要进行情况说明，等等.</td>
					</tr>
					<tr>
						<td height="30" colspan="9">&nbsp;</td>
					</tr>
					<tr>
						<td width="85"><div align="right">
								<span class="dfd">生产负责人</span>：
							</div></td>
						<td width="168"><div align="left">
								<input name="txt50" type="text" style="widtd:120px;"
									class="input" id="txt50" readonly>
							</div></td>
						<td width="44"><div align="right">
								<span class="dfd">日期</span>：
							</div></td>
						<td width="200"><input name="txt52" type="text"
							style="widtd:120px;" class="input" id="txt52" readonly></td>
						<td width="30"><div align="right">
								<span class="dfd">QC</span>：
							</div></td>
						<td width="168"><input name="txt53" type="text"
							style="widtd:120px;" class="input" id="txt53" readonly></td>
						<td width="46"><div align="right">
								<span class="dfd">日期</span>：
							</div></td>
						<td width="319"><input name="txt54" type="text"
							style="widtd:120px;" class="input" id="txt54" readonly></td>
						<td width="7">&nbsp;</td>
					</tr>
				</tbody>
			</table>
			<br />
		</div>

	</form>

</body>
</html>

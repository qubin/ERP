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
td{
	font-size:15px;
	text-align:center;
	word-wrap:break-word;
	
}
</style>
<script type="text/javascript">

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
	var j = jQuery;
	j(document).ready(function(){
	var num = 1;
		$.each(j("td[name='No']"),function(k,v){
			jNo = j(v);
			jNo.html(num);
			num ++;
		})
	});
</script>
</head>

<body>

	<object id="LODOP_OB"
		classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
		<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
	</object>
	<input class="u-btn" type="button" style="float:right;margin-right:80px;" onclick="javascript:history.back()" value="返回" />
	<input class="u-btn" type="button" style="float:right;margin-right:20px;" value="预   览" onclick="javascript:printView()">
	<div id="container">
		<img src="${pageContext.request.contextPath}/assets/images/log.png" alt="" style="margin-top:20px;margin-left:10px;"/>
		<div style="margin-left:15px;margin-top:10px;">
			<span style="font-size:15px;"><b>Sales Order / 销售单</b></span>
		</div>
		<div style="margin-left:15px;">
			<table border="1" style="border-collapse:collapse;">
			<tr >
				<td style="width:75px;">序号<div>S/No.</div></td>
				<td style="width:75px;">客户<div>Customers</div></td>
				<td style="width:75px;">客户订单号<div>Order No.</div></td>
				<td style="width:75px;">客户型号<div>Customers</div></td>
				<td style="width:75px;">华天型号<div>Customers</div></td>
				<td style="width:75px;">数量<div>Quantity</div></td>
				<td style="width:75px;background:#00FF00;">单价<div>Unit price</div></td>
				<td style="width:75px;background:#99CCFF;">总价<div>Amount</div></td>
				<td style="width:75px;">发行日期<div>Release Date</div></td>
				<td style="width:75px;">关闭日期<div>Closed Date</div></td>
				<td style="width:75px;">备注<div>Remarks</div></td>
			</tr>
			<s:iterator value="#request.data" id="d">
				<tr>
					<td style="width:75px;" name="No"></td>
					<td style="width:75px;"><s:property value="#d.name"/></td>
					<td style="width:75px;"><s:property value="#d.code"/></td>
					<td style="width:75px;"><s:property value="#d.cus_pn"/></td>
					<td style="width:75px;"><s:property value="#d.ht_pn"/></td>
					<td style="width:75px;"><s:property value="#d.order_count"/></td>
					<td style="width:75px;background:#00FF00;"><s:property value="#d.price"/></td>
					<td style="width:75px;background:#99CCFF;"><s:property value="#d.amount"/></td>
					<td style="width:75px;"><s:property value="#d.start_date"/></td>
					<td style="width:75px;"><s:property value="#d.end_date"/></td>
					<td style="width:75px;"><s:property value="#d.remark"/></td>
				</tr>
			</s:iterator>
		</table>
		</div>
		
	</div>
	
</body>
</html>

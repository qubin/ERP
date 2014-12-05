<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="../top.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title></title>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/findUserRole.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
	<style type="text/css">
	<!--
	.text-box{
	   border-bottom:#666 1px solid;
	   border-left-width:0px;
	   border-right-width:0px;
	   border-top-width:0px;
	   background:#F6F5F5}
	-->
   </style>
<script type="text/javascript">
	window.onload=function(){
		//产品性质
		if("${productInfo.PROPERTIES}" == "1"){
			document.getElementById("pdu_proper").value = "flat";
		}else if("${productInfo.PROPERTIES}" == "2"){
			document.getElementById("pdu_proper").value = "tab";
		}else{
			document.getElementById("pdu_proper").value = "drift lock";
		}
		//产品市场
		if("${productInfo.MARKET}" == "1"){
			document.getElementById("pdu_market").value = "OE";
		}else{
			document.getElementById("pdu_market").value = "AF";
		}
		//模具类型
		if("${productInfo.PATTERN_TYPE}" == "1"){
			document.getElementById("pdu_patterntype").value = "prog";
		}else if("${productInfo.PATTERN_TYPE }" == "2"){
			document.getElementById("pdu_patterntype").value = "single die";
		}else{
			document.getElementById("pdu_patterntype").value = "prog + single";
		}
		//PPAP状态
		if("${productInfo.PPAP_STATUS}" == "1"){
			document.getElementById("pdu_ppap").value = "ok";
		}else{
			document.getElementById("pdu_ppap").value = "null";
		}
		
	}
</script>
</head>
<body>
	<div id="container" class="container">
		<div class="hr10"></div>
		<form id="form1"
			action="${pageContext.request.contextPath }/admin/product/product_modify.html"
			method="post">
			
			<input type="hidden" name="product.uuid" value="${product.uuid }" />

			<table class="m-table-form">
				<tr>
					<td colspan="6" style="text-align: left;" class="tc">
						<input class="u-btn" type="button" onclick="javascript:history.back()" value="返回" />
					</td>
				</tr>
				<tbody>
					<tr>
						<th class="tr" colspan="6" style="text-align: center;"><font size="5px">产品详情</font></th>
					</tr>
					<tr>
						<th width="12%" align="right" class="tr">华天产品编号：</th>
						<td><input type="text" class="text-box" readonly="readonly" name="product.htPn" maxlength="13" value="${productInfo.HT_PN }" /></td>
						<th width="12%" align="right" class="tr">客户产品编号：</th>
						<td><input type="text" class="text-box" readonly="readonly" name="product.cusPn"
							maxlength="60" value="${productInfo.CUS_PN }"></td>
						<th align="right" class="tr">车型/钢背应用：</th>
						<td><input type="text" class="text-box" readonly="readonly" name="product.appFor"
							maxlength="60" value="${productInfo.APP_FOR }"></td>
					</tr>
					<tr>
						
						<th align="right" class="tr">产品性质：</th>
						<td><input type="text" class="text-box" readonly="readonly" id="pdu_proper"
							name="product.properties" maxlength="60">
						</td>
						<th align="right" class="tr">产品市场：</th>
						<td><input type="text" class="text-box" readonly="readonly" name="product.market"  id="pdu_market"
							maxlength="60"></td>
						<th align="right" class="tr">模具类型：</th>
						<td><input type="text" class="text-box" readonly="readonly"  id="pdu_patterntype"
							name="product.patternType" maxlength="60"
							value="${productInfo.PATTERN_TYPE }"></td>
					</tr>
					<tr>
						
						<th align="right" class="tr">冲压步骤数：</th>
						<td><input type="text" class="text-box" readonly="readonly" name="product.proStep"
							maxlength="60" value="${productInfo.PRO_STEP }"></td>
						<th align="right" class="tr">步距：</th>
						<td><input type="text" class="text-box" readonly="readonly" name="product.stepPitch"
							maxlength="60" value="${productInfo.STEP_PITCH }"></td>
						<th align="right" class="tr">料宽：</th>
						<td><input type="text" class="text-box" readonly="readonly" name="product.matWidth"
							maxlength="60" value="${productInfo.MAT_WIDTH }"></td>
					</tr>
					<tr>
						<th align="right" class="tr">面积：</th>
						<td><input type="text" class="text-box" readonly="readonly" name="product.areca"
							maxlength="60" value="${productInfo.ARECA }"></td>
						<th align="right" class="tr">单片重量：</th>
						<td><input type="text" class="text-box" readonly="readonly"
							name="product.singleWeight" maxlength="60"
							value="${productInfo.SINGLE_WEIGHT }"></td>
						<th align="right" class="tr">包装盒尺寸：</th>
						<td><input type="text" class="text-box" readonly="readonly" name="product.packSize"
							maxlength="60" value="${productInfo.PACK_SIZE }"></td>
					</tr>
					<tr>
						<th align="right" class="tr">PPAP状态：</th>
						<td><input type="text" class="text-box" readonly="readonly" id="pdu_ppap"
							name="product.ppapStatus" maxlength="60"></td>
						<th align="right" class="tr">备注：</th>
						<td colspan="3"><input type="text" class="text-box" readonly="readonly"
							name="product.remark" maxlength="60" value="${productInfo.REMARK }"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<!-- /#container -->
</body>
</body>
</html>
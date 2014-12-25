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
<script language="javascript" src="../assets/js/LodopFuncs.js"></script>
<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
       <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
</object>
<link type="text/css" rel="stylesheet" href="../assets/css/easyui.css" />
<link type="text/css" rel="stylesheet" href="../assets/css/font-awesome.min.css" />
<!--[if IE 7]>
  <link type="text/css" rel="stylesheet" href="../assets/css/font-awesome-ie7.min.css"/>
  <![endif]-->
<link type="text/css" rel="stylesheet" href="../assets/css/base.css" />
<link type="text/css" rel="stylesheet" href="../assets/css/style.css" />
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
	//审核
	function audit(){
		if(confirm("确定要通过审核吗？")){
			document.getElementById("txtVerifyStatus").value = "1";	//设置审核状态
		
			var valVerifyRemark = document.getElementById("txtVerifyRemark").value;
			if(valVerifyRemark == ""){
				document.getElementById("txtVerifyRemark").value = "审核通过";
			}
		}else{
			return false;
		}
	}
	//未通过
	function notAudit(){
		var valVerifyRemark = document.getElementById("txtVerifyRemark").value;
		if(valVerifyRemark == ""){
			alert("请填写未通过审核原因！");
			return false;
		}else{
			document.getElementById("txtVerifyStatus").value = "0";	//设置审核状态
		}
	}
	
	function printView(){
		var LODOP = getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
	}

	window.onload = function() {

	}
</script>
</head>

<body>
	<div id="container" class="container">
		<form id="form1" action="${pageContext.request.contextPath}/admin/production/production_audit.html" method="post">
			<div>
				<div align="right">
					<table>
						<tr>
							<td><input type="text" id="txtVerifyRemark" name="pdceistct.verifyRemark" class="u-ipt" /></td>
							<td>
								<button class="u-btn" onclick="return printView()">预&nbsp;&nbsp;览</button>
							</td>
							<td>
								<button class="u-btn" type="submit" onclick="return audit()">审核通过并打印</button>
							</td>
							<td>
								<button class="u-btn" type="submit" onClick="return notAudit()">未通过</button>
							</td>
							<td>
								<button class="u-btn" onClick="javascript:history.back()">返&nbsp;&nbsp;回</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div align="right">
				<br />
			</div>
			
			<input type="hidden" value="${sczldList[0].SCZLDID}" name="pdceistct.uuid" />
			<input type="hidden" name="pdceistct.verifyStatus" id="txtVerifyStatus"/>
			
			<table width="100%" class="m-table-form">
				<tbody>
					<tr height="40">
						<th class="tr" colspan="6"><h1 align="center" class="STYLE1">生产指令单( work order )</h1></th>
					</tr>
					<tr height="30">
						<th class="tr">材料名称：</th>
						<td>${sczldList[0].MAT_SUPPLIER_NAME}</td>
						<th class="tr">华天零件号：</th>
						<td>${sczldList[0].HT_PN}</td>
						<th class="tr">订单数量：</th>
						<td>${sczldList[0].ORDER_COUNT}</td>
					</tr>
					<tr height="30">
						<th class="tr">使用重量：</th>
						<td>${sczldList[0].USED_MAT_WEIGHT}KG</td>
						<th class="tr">客户零件号：</th>
						<td>${sczldList[0].CUS_PDCT_NO}</td>
						<th class="tr">下单日期：</th>
						<td>${sczldList[0].ORDER_DATE}</td>
					</tr>
					<tr height="30">
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
			<table class="m-table">
				<thead>
					<tr>
						<th class="cola" width="1%">工序</th>
						<th class="cola">日期</th>
						<th class="colb">完成数量</th>
						<th class="cola">签名</th>
						<th class="cola">单盒数量</th>
						<th class="cola">重量</th>
						<th class="cola">报废数量</th>
						<th class="colb">备注</th>
					</tr>
				</thead>
				<tbody>
					<tr class="even">
						<td rowspan="3"><div align="center">
								<strong>1</strong>
							</div></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr class="hover">
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr class="even">
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr class="hover">
						<td rowspan="3"><div align="center">
								<strong>2</strong>
							</div></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr class="even">
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr class="hover">
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr class="even">
						<td rowspan="3"><div align="center">
								<strong>3</strong>
							</div></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr class="hover">
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr class="even">
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr class="hover">
						<td rowspan="3"><div align="center">
								<strong>4</strong>
							</div></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr class="even">
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr class="hover">
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</tbody>
			</table>
			<br />
			<table width="100%" class="m-table-form">
				<tr>
					<th width="189" align="center"><div align="center">用料信息、钢卷号</div></th>
					<th width="96" align="center"><div align="center">重量</div></th>
					<th width="136" align="center"><div align="center">签名、日期</div></th>
					<th width="102" align="center"><div align="center"></div></th>
					<th width="206" align="center"><div align="center"></div></th>
					<th width="58" align="center"><div align="center"></div></th>
					<th width="107" align="center"><div align="center"></div></th>
					<th width="136" align="center"><div align="center">签名、日期</div></th>
				</tr>
				<tr>
					<td><div align="center">
							<input name="txt24" type="text" class="text-box" id="txt24"
								readonly>
						</div></td>
					<td>
						<div align="center">
							<input name="txt12" type="text" style="width:50px;"
								class="text-box" id="txt12" readonly> KG
						</div>
					</td>
					<td width="136"><input name="txt10" type="text"
						style="width:120px;" class="text-box" id="txt10" readonly></td>
					<td width="102">退料：</td>
					<td><input name="txt30" type="text" class="text-box"
						id="txt30" readonly></td>
					<td>重量：</td>
					<td><input name="txt37" type="text" style="width:50px;"
						class="text-box" id="txt37" readonly> KG</td>
					<td><input name="txt44" type="text" style="width:120px;"
						class="text-box" id="txt44" readonly></td>
				</tr>
				<tr>
					<td><div align="center">
							<input name="txt23" type="text" class="text-box" id="txt23"
								readonly>
						</div></td>
					<td>
						<div align="center">
							<input name="txt11" type="text" style="width:50px;"
								class="text-box" id="txt11" readonly> KG
						</div>
					</td>
					<td width="136"><input name="txt9" type="text"
						style="width:120px;" class="text-box" id="txt9" readonly></td>
					<td width="102">退料：</td>
					<td><input name="txt31" type="text" class="text-box"
						id="txt31" readonly></td>
					<td>重量：</td>
					<td><input name="txt38" type="text" style="width:50px;"
						class="text-box" id="txt38" readonly> KG</td>
					<td><input name="txt45" type="text" style="width:120px;"
						class="text-box" id="txt45" readonly></td>
				</tr>
				<tr>
					<td><div align="center">
							<input name="txt22" type="text" class="text-box" id="txt22"
								readonly>
						</div></td>
					<td>
						<div align="center">
							<input name="txt13" type="text" style="width:50px;"
								class="text-box" id="txt13" readonly> KG
						</div>
					</td>
					<td width="136"><input name="txt25" type="text"
						style="width:120px;" class="text-box" id="txt25" readonly></td>
					<td width="102">退料：</td>
					<td><input name="txt32" type="text" class="text-box"
						id="txt32" readonly></td>
					<td>重量：</td>
					<td><input name="txt39" type="text" style="width:50px;"
						class="text-box" id="txt39" readonly> KG</td>
					<td><input name="txt46" type="text" style="width:120px;"
						class="text-box" id="txt46" readonly></td>
				</tr>
				<tr>
					<td><div align="center">
							<input name="txt18" type="text" class="text-box" id="txt18"
								readonly>
						</div></td>
					<td>
						<div align="center">
							<input name="txt14" type="text" style="width:50px;"
								class="text-box" id="txt14" readonly> KG
						</div>
					</td>
					<td width="136"><input name="txt26" type="text"
						style="width:120px;" class="text-box" id="txt26" readonly></td>
					<td width="102">退料：</td>
					<td><input name="txt33" type="text" class="text-box"
						id="txt33" readonly></td>
					<td>重量：</td>
					<td><input name="txt40" type="text" style="width:50px;"
						class="text-box" id="txt40" readonly> KG</td>
					<td><input name="txt51" type="text" style="width:120px;"
						class="text-box" id="txt51" readonly></td>
				</tr>
				<tr>
					<td><div align="center">
							<input name="txt19" type="text" class="text-box" id="txt19"
								readonly>
						</div></td>
					<td>
						<div align="center">
							<input name="txt15" type="text" style="width:50px;"
								class="text-box" id="txt15" readonly> KG
						</div>
					</td>
					<td width="136"><input name="txt27" type="text"
						style="width:120px;" class="text-box" id="txt27" readonly></td>
					<td width="102">退料：</td>
					<td><input name="txt34" type="text" class="text-box"
						id="txt34" readonly></td>
					<td>重量：</td>
					<td><input name="txt41" type="text" style="width:50px;"
						class="text-box" id="txt41" readonly> KG</td>
					<td><input name="txt48" type="text" style="width:120px;"
						class="text-box" id="txt48" readonly></td>
				</tr>
				<tr>
					<td><div align="center">
							<input name="txt20" type="text" class="text-box" id="txt20"
								readonly>
						</div></td>
					<td>
						<div align="center">
							<input name="txt16" type="text" style="width:50px;"
								class="text-box" id="txt16" readonly> KG
						</div>
					</td>
					<td width="136"><input name="txt28" type="text"
						style="width:120px;" class="text-box" id="txt28" readonly></td>
					<td width="102">报 废：</td>
					<td><input name="txt35" type="text" class="text-box"
						id="txt35" readonly></td>
					<td>重量：</td>
					<td><input name="txt42" type="text" style="width:50px;"
						class="text-box" id="txt42" readonly> KG</td>
					<td><input name="txt47" type="text" style="width:120px;"
						class="text-box" id="txt47" readonly></td>
				</tr>
				<tr>
					<td><div align="center">
							<input name="txt21" type="text" class="text-box" id="txt21"
								readonly>
						</div></td>
					<td>
						<div align="center">
							<input name="txt17" type="text" style="width:50px;"
								class="text-box" id="txt17" readonly> KG
						</div>
					</td>
					<td width="136"><input name="txt29" type="text"
						style="width:120px;" class="text-box" id="txt29" readonly></td>
					<td width="102">原材料报废：</td>
					<td><input name="txt36" type="text" class="text-box"
						id="txt36" readonly></td>
					<td>重量：</td>
					<td><input name="txt43" type="text" style="width:50px;"
						class="text-box" id="txt43" readonly> KG</td>
					<td><input name="txt49" type="text" style="width:120px;"
						class="text-box" id="txt49" readonly></td>
				</tr>
			</table>
			<br />
			<table class="m-table-form3">
				<tr>
					<td height="50" colspan="4">补充说明：</td>
				</tr>
				<tr>
					<td>生产负责人：</td>
					<td><input name="txt49" type="text" style="width:120px;"
						class="text-box" id="txt49" readonly></td>
					<td>QC：</td>
					<td><input name="txt49" type="text" style="width:120px;"
						class="text-box" id="txt49" readonly></td>
				</tr>
			</table>


		</form>
	</div>
	<!-- /#container -->
</body>
</html>
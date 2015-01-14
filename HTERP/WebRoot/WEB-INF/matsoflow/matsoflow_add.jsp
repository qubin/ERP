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
<script type="text/javascript">
	function ajax(obj) {
		var url = "${pageContext.request.contextPath}/admin/matsoflow/matsoflow_getSupplierInfoById.html?supid=" + obj.value;
		new Ajax.Request(url, {
			method : 'get',
			onSuccess : function(data) {
				var strDate = data.responseText.split(',');
				var osel = document.getElementById("htSelectVal");

				document.getElementById("htSelectVal").options.length = 0;

				osel.options.add(new Option("--请选择--", "-1"));
				for (var i = 0; i < strDate.length; i++) {
					osel.options.add(new Option(strDate[i].substr(1,strDate[i].length), strDate[i].substr(0, 1)));
				}

				//var jsonData = eval("(" + data.responseText + ")");
				//document.getElementById("labSuppCode").innerHTML = jsonData.name;
			}
		});
	}

	function getMaterialInfo(obj) {
		var url = "${pageContext.request.contextPath}/admin/matsoflow/matsoflow_getMaterialInfoById.html?matid=" + obj.value;
		new Ajax.Request(url, {
			method : 'get',
			onSuccess : function(data) {
				var jsonData = eval("(" + data.responseText + ")");
				document.getElementById("labSuppMd").innerHTML = jsonData[0].density;
				document.getElementById("labSuppHd").innerHTML = jsonData[0].thickness;
				document.getElementById("labSuppMs").innerHTML = jsonData[0].remark;
				document.getElementById("labSuppCode").innerHTML = jsonData[0].name;
				document.getElementById("labSuppCk").innerHTML = jsonData[0].sign1;
				document.getElementById("labSuppRk").innerHTML = jsonData[0].out_time;
				document.getElementById("strMaterialId").value = jsonData[0].id;
			}
		});
	}
	function showSuccess(){
		alert("转库成功！");
	}
	function resetForm(){
		document.getElementById("form1").reset();
		document.getElementById("labSuppMd").innerHTML = "";
		document.getElementById("labSuppHd").innerHTML = "";
		document.getElementById("labSuppMs").innerHTML = "";
		document.getElementById("labSuppCode").innerHTML = "";
		document.getElementById("labSuppCk").innerHTML = "";
		document.getElementById("labSuppRk").innerHTML = "";
		document.getElementById("strMaterialId").value = "";
	}
</script>
</head>
<body>
	<div id="container" class="container">
		<div class="hr10"></div>
		<div class="hr10"></div>
		<h2>材料短溢处理</h2>
		<form id="form1" action="${pageContext.request.contextPath }/admin/matsoflow/matsoflow_add.html" method="post">
			<input type="hidden" name="strMaterialId" id="strMaterialId">
			<table class="m-table-form">
				<tbody>
					<tr>
						<th width="45%" height="40" class="tr">供&nbsp;应&nbsp;商：</th>
						<td height="40"><s:select name="material.supplyId"
								onchange="ajax(this)"
								cssStyle="width:178px"
								cssClass="u-ipt required validate-selection"
								list="#request.supplierList" listKey="uuid" listValue="code"
								headerKey="-1" headerValue="--请选择--" /></td>
					</tr>
					<tr>
						<th width="45%" height="40" class="tr">华天材料编号：</th>
						<td height="40"><select name="supplyMat.supplyMatId" id="htSelectVal" style="width:178px"
							class="u-ipt required validate-selection"
							onchange="getMaterialInfo(this)">
						</select></td>
					</tr>
					<tr>
						<th height="40" class="tr">供应商代码：</th>
						<td height="40"><label id="labSuppCode"></label></td>
					</tr>
					<tr>
						<th height="40" class="tr">密&nbsp;&nbsp;&nbsp;度：</th>
						<td height="40"><label id="labSuppMd"></label></td>
					</tr>
					<tr>
						<th height="40" class="tr">厚&nbsp;&nbsp;&nbsp;度：</th>
						<td height="40"><label id="labSuppHd"></label></td>
					</tr>
					<tr>
						<th height="40" class="tr">处理重量：</th>
						<td height="40">
							<input type="text" class="u-ipt required validate-number" name="material.weight"/>
							(<font color="red">* 注：正数为进库，负数为出库</font>)
						</td>
					</tr>
					<tr>
						<th height="40" class="tr">处理原因：</th>
						<td height="40">
							<input type="text" class="u-ipt required" name="matsoflow.prcReason"/>
						</td>
					</tr>
					<tr>
						<th height="40" class="tr">现在所在库：</th>
						<td height="40"><label id="labSuppCk"></label></td>
					</tr>
					<tr>
						<th height="40" class="tr">入库时间：</th>
						<td height="40"><label id="labSuppRk"></label></td>
					</tr>
					<tr>
						<th height="40" class="tr">描&nbsp;&nbsp;&nbsp;述：</th>
						<td height="40"><label id="labSuppMs"></label></td>
					</tr>
					
				</tbody>
			</table>
			<p>
				<label></label>
			</p>
			<br>
			<p align="center">
				<input name="button" type="submit" class="u-btn" id="button" value="提交">
				<input name="button" type="button" onclick="javascript:history.back()" class="u-btn" id="button" value="返回">
			</p>
		</form>
		<div class="hr10">
			<!--javascript end-->
		</div>
	</div>

	<!-- /#container -->
	<!--javascript start-->
	<!--javascript end-->
</body>
</html>
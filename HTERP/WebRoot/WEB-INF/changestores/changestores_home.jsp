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
body,div,ul,li {
	margin: 0 auto;
	padding: 0;
}

body {
	font: 12px "宋体";
	text-align: center;
}

a:link {
	color: #00F;
	text-decoration: none;
}

a:visited {
	color: #00F;
	text-decoration: none;
}

a:hover {
	color: #c00;
	text-decoration: underline;
}

ul {
	list-style: none;
}

.main {
	clear: both;
	padding: 8px;
	text-align: center;
}

#tabs1 {
	text-align: left;
	width: 900px;
}

.menu1box {
	position: relative;
	overflow: hidden;
	height: 22px;
	width: 900px;
	text-align: left;
}

#menu1 {
	position: absolute;
	top: 0;
	left: 0;
	z-index: 1;
}

#menu1 li {
	float: left;
	display: block;
	cursor: pointer;
	width: 72px;
	text-align: center;
	line-height: 21px;
	height: 21px;
}

#menu1 li.hover {
	background: #fff;
	border-left: 1px solid #333;
	border-top: 1px solid #333;
	border-right: 1px solid #333;
}

.main1box {
	clear: both;
	margin-top: -1px;
	border: 1px solid #333;
	height: 480px;
	width: 900px;
}

#main1 ul {
	display: none;
}

#main1 ul.block {
	display: block;
}
-->
</style>

<script type="text/javascript">
	function ajax(obj) {
		var url = "${pageContext.request.contextPath}/admin/changestores/changestores_getSupplierInfoById.html?supid=" + obj.value;
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
		var url = "${pageContext.request.contextPath}/admin/changestores/changestores_getMaterialInfoById.html?matid="
				+ obj.value;
		new Ajax.Request(
				url,
				{
					method : 'get',
					onSuccess : function(data) {
						var jsonData = eval("(" + data.responseText + ")");
						document.getElementById("labSuppMd").innerHTML = jsonData[0].density;
						document.getElementById("labSuppHd").innerHTML = jsonData[0].thickness;
						document.getElementById("labSuppMs").innerHTML = jsonData[0].desc1;
						document.getElementById("labSuppCode").innerHTML = jsonData[0].name;
						document.getElementById("labSuppCk").innerHTML = jsonData[0].sign1;
						document.getElementById("labSuppRk").innerHTML = jsonData[0].out_time;
						document.getElementById("strMaterialId").value = jsonData[0].id;
					}
				});
	}
	function showSuccess() {
		alert("转库成功！");
	}
	function resetForm() {
		document.getElementById("form1").reset();
		document.getElementById("labSuppMd").innerHTML = "";
		document.getElementById("labSuppHd").innerHTML = "";
		document.getElementById("labSuppMs").innerHTML = "";
		document.getElementById("labSuppCode").innerHTML = "";
		document.getElementById("labSuppCk").innerHTML = "";
		document.getElementById("labSuppRk").innerHTML = "";
		document.getElementById("strMaterialId").value = "";
	}
	
	function resetForm2(){
		document.getElementById("form2").reset();
		document.getElementById("labProBj").innerHTML = "";
		document.getElementById("labProLk").innerHTML = "";
		document.getElementById("labProMj").innerHTML = "";
		document.getElementById("labProDpzl").innerHTML = "";
		document.getElementById("labProSzk").innerHTML = "";
	}

	function getProductInfo(obj) {
		var url = "${pageContext.request.contextPath}/admin/changestores/changestores_getProductInfo.html?proid=" + obj.value;
		new Ajax.Request(
				url,
				{
					method : 'get',
					onSuccess : function(data) 
					{
						var jsonData = eval("(" + data.responseText + ")");
						document.getElementById("labProBj").innerHTML = jsonData[0].step_pitch;
						document.getElementById("labProLk").innerHTML = jsonData[0].mat_width;
						document.getElementById("labProMj").innerHTML = jsonData[0].areca;
						document.getElementById("labProDpzl").innerHTML = jsonData[0].weight;
						document.getElementById("labProSzk").innerHTML = jsonData[0].sign1;
					}
				});
	}

	function confimProduct(){
		if(confirm("您确定要转到该库吗？")){
			alert("转库成功！");
		}
	}

	function confimMaterial(){
		if(confirm("您确定要转到该库吗？")){
			alert("转库成功！");
		}
	}

	function setTab(m, n) {
		var tli = document.getElementById("menu" + m).getElementsByTagName("li");
		var mli = document.getElementById("main" + m).getElementsByTagName("ul");
		for (var i = 0; i < tli.length; i++) {
			tli[i].className = i == n ? "hover" : "";
			mli[i].style.display = i == n ? "block" : "none";
		}
	}
</script>
</head>
<body>

	<br>
	<br>

	<div id="tabs1">

		<div class="menu1box">

			<ul id="menu1">
				<li class="hover" onclick="setTab(1,0)"><a href="#">材料转库</a></li>
				<li onclick="setTab(1,1)"><a href="#">成品转库</a></li>
			</ul>

		</div>

		<div class="main1box">

			<div class="main" id="main1">

				<ul class="block">
					<li>

						<form id="form1"
							action="${pageContext.request.contextPath }/admin/changestores/changestores_changestores.html"
							method="post">
							<input type="hidden" name="strMaterialId" id="strMaterialId">
							<table class="m-table-form">
								<tbody>
									<tr>
										<th width="50%" height="40" class="tr">供&nbsp;应&nbsp;商：</th>
										<td height="40"><s:select name="material.supplyId"
												onchange="ajax(this)"
												cssClass="u-ipt required validate-selection"
												list="#request.supplierList" listKey="uuid" listValue="code"
												headerKey="-1" headerValue="--请选择--" /></td>
									</tr>
									<tr>
										<th width="50%" height="40" class="tr">华天材料编号：</th>
										<td height="40"><select name="" id="htSelectVal"
											class="u-ipt required validate-selection"
											onchange="getMaterialInfo(this)">
										</select></td>
									</tr>
									<tr>
										<th height="40" class="tr">供应商代码：</th>
										<td height="40"><label id="labSuppCode"></label></td>
									</tr>
									<tr>
										<th height="40" class="tr">密&nbsp;&nbsp;&nbsp;&nbsp;度：</th>
										<td height="40"><label id="labSuppMd"></label></td>
									</tr>
									<tr>
										<th height="40" class="tr">厚&nbsp;&nbsp;&nbsp;&nbsp;度：</th>
										<td height="40"><label id="labSuppHd"></label></td>
									</tr>
									<tr>
										<th height="40" class="tr">入库时间：</th>
										<td height="40"><label id="labSuppRk"></label></td>
									</tr>
									<tr>
										<th height="40" class="tr">描&nbsp;&nbsp;&nbsp;&nbsp;述：</th>
										<td height="40"><label id="labSuppMs"></label></td>
									</tr>
									<tr>
										<th height="40" class="tr">现在所在库：</th>
										<td height="40"><label id="labSuppCk"></label></td>
									</tr>
									<tr>
										<th height="40" class="tr">要&nbsp;转&nbsp;到：</th>
										<td height="40"><s:select name="updatestore"
												cssClass="u-ipt required validate-selection"
												list="#request.warehouseList" listKey="warehouseId"
												listValue="sign1" headerKey="-1" headerValue="--请选择--" /></td>
									</tr>
								</tbody>
							</table>
							<p>
								<label></label>
							</p>
							<br>
							<p align="center">
								<input name="button" onclick="return confimMaterial()" type="submit" class="u-btn" id="button" value="提交"> 
								<input name="button" type="button" onclick="resetForm()" class="u-btn" id="button" value="重置">
							</p>
						</form>

					</li>
				</ul>

				<ul>
					<li>


						<form id="form2"
							action="${pageContext.request.contextPath }/admin/changestores/changestores_changestoresProduct.html"
							method="post">
							<input type="hidden" name="strProductlId" id="strProductlId">
							<table class="m-table-form">
								<tbody>
									<tr>
										<th width="50%" height="40" class="tr">华天产品编号：</th>
										<td height="40"><s:select name="product.uuid"
												onchange="getProductInfo(this)"
												cssClass="u-ipt required validate-selection"
												list="#request.productList" listKey="ID" listValue="HT_PN"
												headerKey="-1" headerValue="--请选择--" /></td>
									</tr>
									<tr>
										<th height="40" class="tr">步&nbsp;&nbsp;&nbsp;&nbsp;距：</th>
										<td height="40"><label id="labProBj"></label></td>
									</tr>
									<tr>
										<th height="40" class="tr">料&nbsp;&nbsp;&nbsp;&nbsp;宽：</th>
										<td height="40"><label id="labProLk"></label></td>
									</tr>
									<tr>
										<th height="40" class="tr">面&nbsp;&nbsp;&nbsp;&nbsp;积：</th>
										<td height="40"><label id="labProMj"></label></td>
									</tr>
									<tr>
										<th height="40" class="tr">单片重量：</th>
										<td height="40"><label id="labProDpzl"></label></td>
									</tr>
									<tr>
										<th height="40" class="tr">现在所在库：</th>
										<td height="40"><label id="labProSzk"></label></td>
									</tr>
									<tr>
										<th height="40" class="tr">要&nbsp;转&nbsp;到：</th>
										<td height="40">
											<s:select name="custPdct.area"
													  cssClass="u-ipt required validate-selection"
													  list="#request.warehouseList" 
													  listKey="warehouseId"
													  listValue="sign1" 
													  headerKey="-1" 
													  headerValue="--请选择--" />
										</td>
									</tr>
								</tbody>
							</table>
							<p>
								<label></label>
							</p>
							<br>
							<p align="center">
								<input name="button" onclick="return confimProduct()" type="submit" class="u-btn" id="button" value="提交"> 
								<input name="button" type="button" onclick="resetForm2()" class="u-btn" id="button" value="重置">
							</p>
						</form>
						
					</li>
				</ul>

			</div>

		</div>

	</div>

	<!-- /#container -->
	<!--javascript start-->
	<!--javascript end-->
</body>
</html>

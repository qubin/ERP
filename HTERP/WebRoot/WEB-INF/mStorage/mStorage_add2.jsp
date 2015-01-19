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
	height: 510px;
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


	function setTab(m, n) {
		var tli = document.getElementById("menu" + m).getElementsByTagName("li");
		var mli = document.getElementById("main" + m).getElementsByTagName("ul");
		for (var i = 0; i < tli.length; i++) {
			tli[i].className = i == n ? "hover" : "";
			mli[i].style.display = i == n ? "block" : "none";
		}
	}
	
	var j = jQuery;
	j(document).ready(function(){
		var temp = "";
		var temp2 = "";
	
		j("#supply").bind("change",function(){
			j("#materialName").empty();
			var value = j(this).val();
			var s = "${pageContext.request.contextPath}/admin/mStorage/mStorage_findMaterial.html";
			j.getJSON(s,{uuid:value},function(data){
				if(data != null){
					temp = data;
					j("#materialName").append("<option value=''>--请选择--</option>");
					for(var i = 0; i < data.length; i ++){
						j("#materialName").append("<option value=" + data[i].supplyMatId + ">" + data[i].matSupplierName +　"</option>");
					}
				}else{
					alert("改供应商下没有材料");
				}
			});	
		});
		
		j("#supplyOut").bind("change",function(){
			j("#materialNameOut").empty();
			var value = j(this).val();
			var s = "${pageContext.request.contextPath}/admin/mStorage/mStorage_findMaterial.html";
			j.getJSON(s,{uuid:value},function(data){
				if(data != null){
					temp2 = data;
					j("#materialNameOut").append("<option value=''>--请选择--</option>");
					for(var i = 0; i < data.length; i ++){
						j("#materialNameOut").append("<option value=" + data[i].supplyMatId + ">" + data[i].matSupplierName +　"</option>");
					}
				}else{
					alert("改供应商下没有材料");
				}
			});	
		});
		
		j("#materialNameOut").bind("change",function(){
			var smId = j(this).val();
			for(var i = 0; i < temp2.length; i ++){
				if(temp2[i].supplyMatId == smId){
					j("#htNo2").html(temp2[i].htMatNo);
				}
			}
			var uri = "${pageContext.request.contextPath}/admin/mStorage/mStorage_findDetail.html?id=" + smId;
			j.getJSON(uri,function(data){
				if(data != ""){
					j("#materialId2").val(data.uuid);
					j("#den2").html(data.density);
					j("#thick2").html(data.thickness);
					j("#desc2").html(data.desc);
					j("#wh2").val(data.warehouseId);
					j("#wh2").attr("disabled",true);
					j("#scId2").html(data.mmatId);
					j("#LaScrollId2").html(data.scrollId);
				}
			});
		});
		
		j("#materialName").bind("change",function(){
			var smId = j(this).val();
			for(var i = 0; i < temp.length; i ++){
				if(temp[i].supplyMatId == smId){
					j("#htNo").html(temp[i].htMatNo);
				}
			}
			var uri = "${pageContext.request.contextPath}/admin/mStorage/mStorage_findDetail.html?id=" + smId;
			j.getJSON(uri,function(data){
				if(data != ""){
					j("#materialId").val(data.uuid);
					j("#den").html(data.density);
					j("#thick").html(data.thickness);
					j("#desc").html(data.desc);
					j("#wh").val(data.warehouseId);
					j("#strMaterialId").val();
					if(data.scrollId != ""){
						j("#LaScrollId").show();
						j("#LaScrollId").html(data.scrollId);
						j("#scrollId").val(data.scrollId);
						j("#inScrollId").hide();
					}else{
						j("#LaScrollId").hide();
						j("#inScrollId").show();
					}
					if(data.mmatId != ""){
							j("#scInput").hide();
							j("#scId").html(data.mmatId);
							j("#scId").show();
							j("#bar1").hide();
							j("#bar2").hide();
						}else{
							j("#scInput").show();
							j("#scId").html("");
							j("#bar1").show();
							j("#bar2").show();
						}
				}
			});
		});
		
		j("#cRadio").bind("click",function(){
			j("#scInput").hide();
			j("#scInput").val("");
			j("#scInput").attr("disabled",true);
		});
		j("#mRadio").bind("click",function(){
			j("#scInput").show();
			j("#scInput").attr("disabled",false);
		});
		
		j("#mWeight").bind("change",function(){
			var materialId = j("#materialId2").val();
			if(materialId != ""){
				var num = j("#mWeight").val();
				var r = /^[0-9]+\.{0,1}[0-9]{0,2}$/;
				if(r.test(num)){
					var uri = "${pageContext.request.contextPath}/admin/mStorage/mStorage_checkOutNum.html?material.uuid=" + materialId + "&material.weight=" + num;
					j.get(uri,function(data){
						if(data){
							j("#mWeight").val(" ");
							alert("出库数量不能超过库存！");
						}
					});
				}else{
					alert("请输入合法数字");
					j("#mWeight").val("");
				}
			}else{
				alert("请先选择入库材料");
			}
	
		});
	});
</script>
</head>
<body>

	<br>
	<br>

	<div id="tabs1">

		<div class="menu1box">

			<ul id="menu1">
				<li class="hover" onclick="setTab(1,0)"><a href="#">原材料入库</a></li>
				<li onclick="setTab(1,1)"><a href="#">原材料出库</a></li>
			</ul>

		</div>

		<div class="main1box">

			<div class="main" id="main1">

				<ul class="block">
					<li>

						<form id="form1"
							action="${pageContext.request.contextPath }/admin/mStorage/mStorage_materialIn.html"
							method="post">
							<input type="hidden" name="material.uuid" value="" id="materialId"/>
							<input type="hidden" name="rawFlow.outPerson" value="${sessionScope.loggedUser.userLoginId}" />
							<table class="m-table-form">
								<tbody>
									<tr>
										<th class="tr" width="42%">供应商：</th>
										<td><s:select name="supplyMat.supplyId"
											cssClass="u-ipt required validate-selection"
											cssStyle="width:178px"
											list="#request.sList"
											listKey="uuid" listValue="name" headerKey="-1"
											headerValue="--请选择--" id="supply" />
										</td>
									</tr>
									<tr>
										<th height="40" class="tr" width="42%">供应商材料名：</th>
										<td height="40">
										<select name="" id="materialName" class="u-ipt required validate-selection" style="width:180px;">
											
										</select>
										</td>
									</tr>
									<tr>
						<th height="40" class="tr" width="42%">华天材料号：</th>
						<td height="40" ><label id="htNo"></label>
							</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%">所在仓库：</th>
						<td height="40" >
						<s:select name="material.warehouseId"
							cssClass="u-ipt required validate-selection"
							cssStyle="width:178px"
							list="#request.wList"
							listKey="warehouseId" listValue="sign1" headerKey="-1"
							headerValue="--请选择--" id="wh" />
						</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%">面密度：</th>
						<td height="40" ><label id="den"></label>
							</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%">厚度：</th>
						<td height="40" ><label id="thick"></label>
							</td>
					</tr>
					
					<tr>
						<th height="40" class="tr" width="42%">描述：</th>
						<td height="40" ><label id="desc"></label>
							</td>
					</tr>
					<tr>
					<tr>
						<th height="40" class="tr" width="42%">备注：</th>
						<td height="40"><input type="text" class="u-ipt"
							name="rawFlow.remark" maxlength="13" /></label>
							</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%">时间：</th>
						<td height="40" ><input type="text" class="u-ipt required"
							name="rawFlow.outTime" maxlength="13" id="d13" readonly="readonly"/>
							<img onclick="WdatePicker({el:'d13'})" src="${pageContext.request.contextPath}/assets/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
							</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%">卷号：</th>
						<td height="40"><input type="text" class="u-ipt"
							name="material.scrollId" maxlength="13" id="inScrollId"/>
							<label for="" id="LaScrollId"></label>
							</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%" id="th1">母卷ID</th>
						<td height="40" id="td1">
						<span id="bar1">	母卷<input type="radio" name="foo" id="mRadio" checked="checked"/>&nbsp;&nbsp;&nbsp;</span>
						<span id="bar2">子卷<input type="radio" name="foo" id="cRadio" /></span>
							<label for="" id="scId"></label>
							<input type="text" class="u-ipt required" id="scInput" name="material.mmatId"/>
						</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%">重量：</th>
						<td height="40"><input type="text" class="u-ipt required validate-number "
							name="rawFlow.weight" maxlength="13" id=""/>
							
						</td>
					</tr>
								</tbody>
							</table>
							<p>
								<label></label>
							</p>
							<br>
							<p align="center">
								<input name="button" type="submit" class="u-btn" id="button" value="提交"> 
							</p>
						</form>

					</li>
				</ul>

				<ul>
					<li>


						<form id="form2"
							action="${pageContext.request.contextPath }/admin/mStorage/mStorage_materialOut.html"
							method="post">
							<input type="hidden" name="material.uuid" value="" id="materialId2"/>
							<input type="hidden" name="rawFlow.outPerson" value="${sessionScope.loggedUser.userLoginId}" />
							<table class="m-table-form">
								<tbody>
										<tr>
										<th class="tr" width="42%">供应商：</th>
										<td><s:select name="supplyMat.supplyId"
											cssClass="u-ipt required validate-selection"
											cssStyle="width:178px"
											list="#request.sList"
											listKey="uuid" listValue="name" headerKey="-1"
											headerValue="--请选择--" id="supplyOut" />
										</td>
									</tr>
									<tr>
										<th height="40" class="tr" width="42%">供应商材料名：</th>
										<td height="40">
										<select name="" id="materialNameOut" class="u-ipt required validate-selection" style="width:180px;">
											
										</select>
										</td>
									</tr>
									<tr>
						<th height="40" class="tr" width="42%">华天材料号：</th>
						<td height="40" ><label id="htNo2"></label>
							</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%">所在仓库：</th>
						<td height="40" >
						<s:select name=""
							cssClass="u-ipt required validate-selection"
							cssStyle="width:178px"
							list="#request.wList"
							listKey="warehouseId" listValue="sign1" headerKey="-1"
							headerValue="--请选择--" id="wh2" />
						</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%">面密度：</th>
						<td height="40" ><label id="den2"></label>
							</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%">厚度：</th>
						<td height="40" ><label id="thick2"></label>
							</td>
					</tr>
					
					<tr>
						<th height="40" class="tr" width="42%">描述：</th>
						<td height="40" ><label id="desc2"></label>
							</td>
					</tr>
					<tr>
					<tr>
						<th height="40" class="tr" width="42%">备注：</th>
						<td height="40"><input type="text" class="u-ipt"
							name="rawFlow.remark" maxlength="13" /></label>
							</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%">时间：</th>
						<td height="40" ><input type="text" class="u-ipt required"
							name="rawFlow.outTime" maxlength="13" id="d12" readonly="readonly"/>
							<img onclick="WdatePicker({el:'d12'})" src="${pageContext.request.contextPath}/assets/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
							</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%">卷号：</th>
						<td height="40">
							<label for="" id="LaScrollId2"></label>
							</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%" id="th1">母卷ID</th>
						<td height="40" id="td1">
						<label for="" id="scId2"></label>
						</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%">重量：</th>
						<td height="40"><input type="text" class="u-ipt required validate-number "
							name="rawFlow.weight" maxlength="13" id="mWeight"/>
							
						</td>
					</tr>
								</tbody>
							</table>
							<p>
								<label></label>
							</p>
							<br>
							<p align="center">
								<input name="button" type="submit" class="u-btn" id="" value="提交"> 
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

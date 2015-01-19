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
	height: 550px;
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
	
	j("#wh2").attr("disabled",true);
	//已有入库
	j("#flag").val("1");
		j("#customer").bind("change",function(){
			var custId = j("#customer").val();
			var uri = "${pageContext.request.contextPath}/admin/pdctFlow/pdctFlow_findCusPn.html?custId=" + custId;
			j("#cusPn").empty();
			j.getJSON(uri,function(data){
				if(data != ""){
					j("#cusPn").append("<option value='-1'>--请选择--</option>");
					for(var i = 0 ; i < data.length; i ++){
						j("#cusPn").append("<option value=" + data[i].cpId + ">" + data[i].cus_pn +"</option>");
					}
				}else{
					alert("该产品为空");
				}
			});
		});
		
		j("#customer2").bind("change",function(){
			var custId = j("#customer2").val();
			var uri = "${pageContext.request.contextPath}/admin/pdctFlow/pdctFlow_findCusPn.html?custId=" + custId;
			j("#cusPn2").empty();
			j.getJSON(uri,function(data){
				if(data != ""){
					j("#cusPn2").append("<option value='-1'>--请选择--</option>");
					for(var i = 0 ; i < data.length; i ++){
						j("#cusPn2").append("<option value=" + data[i].cpId + ">" + data[i].cus_pn +"</option>");
					}
				}else{
					alert("该产品为空");
				}
			});
		});
		
		j("#customer3").bind("change",function(){
			var custId = j("#customer3").val();
			var uri = "${pageContext.request.contextPath}/admin/pdctFlow/pdctFlow_findCusPn3.html?custId=" + custId;
			j("#cusPn3").empty();
			j.getJSON(uri,function(data){
				if(data != ""){
					j("#cusPn3").append("<option value='-1'>--请选择--</option>");
					for(var i = 0 ; i < data.length; i ++){
						j("#cusPn3").append("<option value=" + data[i].cpId + ">" + data[i].cus_pn +"</option>");
					}
				}else{
					alert("该产品为空");
				}
			});
		});
		j("#cusPn").bind("change",function(){
			j("#wh1").val(-1);
			var arr = [j("#htPn"),j("#appfor"),j("#properties"),j("#market"),j("#patternType"),j("#proStep"),j("#stepPitch"),j("#matWidth"),j("#areca"),j("#packSize"),j("#ppapStatus"),j("#swLa"),j("#batchCodeLa"),j("#picNumLa"),j("#boxNumLa"),j("#weight"),j("#picCount")];
			for(var i = 0 ;i < arr.length; i ++){
				arr[i].html("");
			}
			j("#cpId").val(j(this).val());
			var uri2 = "${pageContext.request.contextPath}/admin/pdctFlow/pdctFlow_findProd.html?cp.cpId=" + j(this).val();
			j.getJSON(uri2,function(data){
				if(data != null && data != ""){
					j("#htPn").html(data[0].htPn);
					j("#appfor").html(data[0].appFor);
					j("#properties").html(data[0].properties);
					j("#market").html(data[0].market);
					j("#patternType").html(data[0].patternType);
					j("#proStep").html(data[0].proStep);
					j("#stepPitch").html(data[0].stepPitch);
					j("#matWidth").html(data[0].matWidth);
					j("#areca").html(data[0].areca);
					j("#packSize").html(data[0].packSize);
					j("#ppapStatus").html(data[0].ppapStatus);
	
					j("#wh1").val(data[1].area);
					j("#boxNumLa").html(data[2].boxNo);
					j("#weight").html(data[0].totalWeight);
					j("#picCount").html(data[1].picCount);
					if(data[1].area != 0){
						//已有入库
						flag = "1";
						j("#swLa").html(data[1].weight);
						j("#batchCodeLa").html(data[2].batchCode);
						j("#picNumLa").html(data[1].picNum);
						j("#sw").attr("disabled",true);
						j("#sw").hide();
						j("#batchCode").attr("disabled",true);
						j("#batchCode").hide();
						j("#picNum").attr("disabled",true);
						j("#picNum").hide();
						j("#wh1").attr("disabled",true);
					}else{
						//从未入库
						flag = "2";
						j("#sw").attr("disabled",false);
						j("#sw").show();
						j("#batchCode").attr("disabled",false);
						j("#batchCode").show();
						j("#picNum").attr("disabled",false);
						j("#picNum").show();
						j("#wh1").attr("disabled",false);
					}
				}
			});
		});
		j("#cusPn2").bind("change",function(){
			j("#cpId2").val(j(this).val());
			var uri2 = "${pageContext.request.contextPath}/admin/pdctFlow/pdctFlow_findProd.html?cp.cpId=" + j(this).val();
			j.getJSON(uri2,function(data){
				if(data != ""){
					j("#htPn2").html(data[0].htPn);
					j("#appfor2").html(data[0].appFor);
					j("#properties2").html(data[0].properties);
					j("#market2").html(data[0].market);
					j("#patternType2").html(data[0].patternType);
					j("#proStep2").html(data[0].proStep);
					j("#stepPitch2").html(data[0].stepPitch);
					j("#matWidth2").html(data[0].matWidth);
					j("#areca2").html(data[0].areca);
					j("#packSize2").html(data[0].packSize);
					j("#ppapStatus2").html(data[0].ppapStatus);
					j("#swLa2").html(data[1].weight);
					j("#batchCodeLa2").html(data[2].batchCode);
					j("#picNumLa2").html(data[1].picNum);
					j("#wh2").val(data[1].area);
					j("#boxNumLa2").html(data[2].boxNo);
					j("#weight2").html(data[0].totalWeight);
					j("#picCount2").html(data[1].picCount);
				}
			});
		});
		j("#cusPn3").bind("change",function(){
			var custId = j("#customer3").val();
			var uri = "${pageContext.request.contextPath}/admin/pdctFlow/pdctFlow_findWh.html?custId=" + custId + "&cp.cpId=" + j(this).val();
			j("#newWh").empty();
			j.getJSON(uri,function(data){
				if(data != null){
					if(data != "false"){
						j("#newWh").append("<option value='-1'>--请选择--</option>");
						for(var i = 0 ; i < data.length; i ++){
							j("#newWh").append("<option value=" + data[i].warehouseId + ">" + data[i].sign1 +"</option>");
						}
					}else{
						alert("该产品为空");
					}
				}
			});
			j("#cpId3").val(j(this).val());
			var uri2 = "${pageContext.request.contextPath}/admin/pdctFlow/pdctFlow_findProd3.html?cp.cpId=" + j(this).val();
			j.getJSON(uri2,function(data){
				if(data != ""){
					
					
					j("#htPn3").html(data[0].htPn);
					j("#appfor3").html(data[0].appFor);
					j("#properties3").html(data[0].properties);
					j("#market3").html(data[0].market);
					j("#patternType3").html(data[0].patternType);
					j("#proStep3").html(data[0].proStep);
					j("#stepPitch3").html(data[0].stepPitch);
					j("#matWidth3").html(data[0].matWidth);
					j("#areca3").html(data[0].areca);
					j("#packSize3").html(data[0].packSize);
					j("#ppapStatus3").html(data[0].ppapStatus);
					j("#swLa3").html(data[2].weight);
					j("#batchCodeLa3").html(data[1].batchCode);
					j("#picNumLa3").html(data[2].picNum);
					j("#weight3").html(data[0].totalWeight);
					
				}
			});
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
				<li class="hover" onclick="setTab(1,0)"><a href="#">成品入库</a></li>
				<li onclick="setTab(1,1)"><a href="#">成品出库</a></li>
				<li onclick="setTab(1,2)"><a href="#">成品新入库</a></li>
			</ul>

		</div>

		<div class="main1box">

			<div class="main" id="main1">

				<ul class="block">
					<li>

						<form id="form1"
							action="${pageContext.request.contextPath }/admin/pdctFlow/pdctFlow_inExist.html"
							method="post">
							<input type="hidden" name="cp.cpId" id="cpId"/>
							<input type="hidden" name="flag" id="flag"/>
							<table class="m-table-form">
								<tbody>
									<tr height="40">
						<th class="tr">客户：</th>
						<td>
							<s:select name=""
							cssClass="u-ipt required validate-selection"
							cssStyle="width:178px"
							list="#request.cList"
							listKey="custId" listValue="custName" headerKey="-1"
							headerValue="--请选择--" id="customer"/>
						</td>
						
						<th class="tr" width="42%">华天产品编号：</th>
						<td><label id="htPn"></label></td>
					</tr>
					<tr height="40">
						
						<th class="tr" width="42%">客户产品编号：</th>
						<td>
							<select name="" id="cusPn" class="u-ipt required validate-selection" style="width:178px;"></select>
						</td>
						<th class="tr">车型/钢背应用：</th>
						<td><label id="appfor"></label></td>
					</tr>
					<tr height="40">
						
						<th class="tr">产品性质：</th>
						<td>
							<label id="properties"></label>
						</td>
						<th class="tr">PPAP状态：</th>
						<td>
							<label id="ppapStatus"></label>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">产品市场：</th>
						<td>
							<label id="market"></label>
						</td>
						<th class="tr">模型类型：</th>
						<td>
							<label id="patternType"></label>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">冲压步骤数：</th>
						<td>
							<label id="proStep"></label>
						</td>
						<th class="tr">步距：</th>
						<td>
							<label id="stepPitch"></label>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">料宽：</th>
						<td>
							<label id="matWidth"></label>
						</td>
						<th class="tr">面积：</th>
						<td>
							<label id="areca"></label>
						</td>
					</tr>
					<tr height="40">
						
							<th class="tr">库存片数：</th>
						<td>
							<label id="picCount"></label>
						</td>
						<th class="tr">包装盒尺寸：</th>
						<td>
							<label id="packSize"></label>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">单片重量：</th>
						<td>
							<input type="text" class="u-ipt "
							name="cp.weight" maxlength="13" id="sw"/>
							<label for="" id="swLa"></label>
						</td>
					
						<th class="tr">总重量:</th>
						<td>
							<label id="weight"></label>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">所在仓库：</th>
						<td>
							<div id="">
								<s:select name="whId"
								cssClass="u-ipt required validate-selection"
								cssStyle="width:178px"
								list="#request.wList"
								listKey="warehouseId" listValue="sign1" headerKey="-1"
								headerValue="--请选择--" id="wh1"/>
							</div>
							<label id="whlabel"></label>
						</td>
						
						<th class="tr">入/出 库片数：</th>
						<td>
							<input type="text" class="u-ipt required validate-number"
							name="pf.count" maxlength="13" id="IOInput"/>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">批号：</th>
						<td>
							<input type="text" class="u-ipt required"
							name="cpn.batchCode" id="batchCode" maxlength="13" />
							<label for="" id="batchCodeLa"></label>
						</td>
						<th class="tr">入\出盒数：</th>
						<td>
							<input type="text" class="u-ipt required validate-number"
							name="boxNum" id="boxNum" maxlength="13" />
						</td>
					
					</tr>	
					<tr height="40">
						<th class="tr">当前盒数：</th>
						<td>
							<label id="boxNumLa"></label>
						</td>
						
						<th class="tr">每盒片数：</th>
						<td>
							<input type="text" class="u-ipt "
							name="cp.area" maxlength="13" id="picNum"/>
							<label for="" id="picNumLa"></label>
						</td>
					</tr>		
					<tr height="40">
						<th class="tr">操作时间：</th>
						<td>
							<input type="text" class="u-ipt "
							name="pf.outTime" maxlength="13" id="nowTime" style="width:160px;"/>
							<img onclick="WdatePicker({el:'nowTime'})" src="${pageContext.request.contextPath}/assets/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
						</td>
					</tr>		
					<tr height="40">
						<th class="tr">备注：</th>
						<td>
							<input type="text" class="u-ipt "
							name="pf.remark" maxlength="13" id="remark"/>
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
							action="${pageContext.request.contextPath }/admin/pdctFlow/pdctFlow_outWh.html"
							method="post">
							<input type="hidden" name="cp.cpId" id="cpId2"/>
							<table class="m-table-form">
								<tbody>
									<tr height="40">
						<th class="tr">客户：</th>
						<td>
							<s:select name=""
							cssClass="u-ipt required validate-selection"
							cssStyle="width:178px"
							list="#request.cList"
							listKey="custId" listValue="custName" headerKey="-1"
							headerValue="--请选择--" id="customer2"/>
						</td>
						
						<th class="tr" width="42%">华天产品编号：</th>
						<td><label id="htPn2"></label></td>
					</tr>
					<tr height="40">
						
						<th class="tr" width="42%">客户产品编号：</th>
						<td>
							<select name="" id="cusPn2" class="u-ipt required validate-selection" style="width:178px;"></select>
						</td>
						<th class="tr">车型/钢背应用：</th>
						<td><label id="appfor2"></label></td>
					</tr>
					<tr height="40">
						
						<th class="tr">产品性质：</th>
						<td>
							<label id="properties2"></label>
						</td>
						<th class="tr">PPAP状态：</th>
						<td>
							<label id="ppapStatus2"></label>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">产品市场：</th>
						<td>
							<label id="market2"></label>
						</td>
						<th class="tr">模型类型：</th>
						<td>
							<label id="patternType2"></label>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">冲压步骤数：</th>
						<td>
							<label id="proStep2"></label>
						</td>
						<th class="tr">步距：</th>
						<td>
							<label id="stepPitch2"></label>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">料宽：</th>
						<td>
							<label id="matWidth2"></label>
						</td>
						<th class="tr">面积：</th>
						<td>
							<label id="areca2"></label>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">单片重量：</th>
						<td>
							<label for="" id="swLa2"></label>
						</td>
						<th class="tr">包装盒尺寸：</th>
						<td>
							<label id="packSize2"></label>
						</td>
					</tr>
					<tr height="40">
						
						<th class="tr">库存片数：</th>
						<td>
							<label id="picCount2"></label>
						</td>
						<th class="tr">总重量:</th>
						<td>
							<label id="weight2"></label>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">所在仓库：</th>
						<td>
							<s:select name=""
								cssClass="u-ipt required validate-selection"
								cssStyle="width:178px"
								list="#request.wList"
								listKey="warehouseId" listValue="sign1" headerKey="-1"
								headerValue="--请选择--" id="wh2"/>
						</td>
						
						<th class="tr">入/出 库片数：</th>
						<td>
							<input type="text" class="u-ipt required validate-number"
							name="pf.count" maxlength="13" id=""/>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">批号：</th>
						<td>
							<label for="" id="batchCodeLa2"></label>
						</td>
						<th class="tr">入\出盒数：</th>
						<td>
							<input type="text" class="u-ipt required validate-number"
							name="boxNum" id="" maxlength="13" />
						</td>
					
					</tr>	
					<tr height="40">
						<th class="tr">当前盒数：</th>
						<td>
							<label id="boxNumLa2"></label>
						</td>
						
						<th class="tr">每盒片数：</th>
						<td>
							<label for="" id="picNumLa2"></label>
						</td>
					</tr>		
					<tr height="40">
						<th class="tr">操作时间：</th>
						<td>
							<input type="text" class="u-ipt "
							name="pf.outTime" maxlength="13" id="nowTime2" style="width:160px;"/>
							<img onclick="WdatePicker({el:'nowTime2'})" src="${pageContext.request.contextPath}/assets/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
						</td>
					</tr>		
					<tr height="40">
						<th class="tr">备注：</th>
						<td>
							<input type="text" class="u-ipt "
							name="pf.remark" maxlength="13" id="remark"/>
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

				<ul>
					<li>


						<form id="form3"
							action="${pageContext.request.contextPath }/admin/pdctFlow/pdctFlow_newIn.html"
							method="post">
							<input type="hidden" name="cp.cpId" id="cpId3"/>
							<table class="m-table-form">
								<tbody>
									<tr height="40">
						<th class="tr">客户：</th>
						<td>
							<s:select name=""
							cssClass="u-ipt required validate-selection"
							cssStyle="width:178px"
							list="#request.cList"
							listKey="custId" listValue="custName" headerKey="-1"
							headerValue="--请选择--" id="customer3"/>
						</td>
						
						<th class="tr" width="42%">华天产品编号：</th>
						<td><label id="htPn3"></label></td>
					</tr>
					<tr height="40">
						
						<th class="tr" width="42%">客户产品编号：</th>
						<td>
							<select name="" id="cusPn3" class="u-ipt required validate-selection" style="width:178px;"></select>
						</td>
						<th class="tr">车型/钢背应用：</th>
						<td><label id="appfor3"></label></td>
					</tr>
					<tr height="40">
						
						<th class="tr">产品性质：</th>
						<td>
							<label id="properties3"></label>
						</td>
						<th class="tr">PPAP状态：</th>
						<td>
							<label id="ppapStatus3"></label>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">产品市场：</th>
						<td>
							<label id="market3"></label>
						</td>
						<th class="tr">模型类型：</th>
						<td>
							<label id="patternType3"></label>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">冲压步骤数：</th>
						<td>
							<label id="proStep3"></label>
						</td>
						<th class="tr">步距：</th>
						<td>
							<label id="stepPitch3"></label>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">料宽：</th>
						<td>
							<label id="matWidth3"></label>
						</td>
						<th class="tr">面积：</th>
						<td>
							<label id="areca3"></label>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">单片重量：</th>
						<td>
							<label for="" id="swLa3"></label>
						</td>
						<th class="tr">包装盒尺寸：</th>
						<td>
							<label id="packSize3"></label>
						</td>
					</tr>
					<tr height="40">
						
						<th class="tr">库存片数：</th>
						<td>
							<label id="picCount3"></label>
						</td>
						<th class="tr">总重量:</th>
						<td>
							<label id="weight3"></label>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">所在仓库：</th>
						<td>
							<div id="whId">
								<select name="" id="newWh" class="u-ipt required validate-selection">
								
								</select>
							</div>
							<label id="whlabel"></label>
						</td>
						
						<th class="tr">入/出 库片数：</th>
						<td>
							<input type="text" class="u-ipt required validate-number"
							name="cp.picCount" maxlength="13" id=""/>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">批号：</th>
						<td>
							<label for="" id="batchCodeLa3"></label>
						</td>
						<th class="tr">入\出盒数：</th>
						<td>
							<input type="text" class="u-ipt required validate-number"
							name="cpn.boxNum" id="" maxlength="13" />
						</td>
					
					</tr>	
					<tr height="40">
						<th class="tr">当前盒数：</th>
						<td>
							<label id="boxNumLa3"></label>
						</td>
						
						<th class="tr">每盒片数：</th>
						<td>
							<label for="" id="picNumLa3"></label>
						</td>
					</tr>		
					<tr height="40">
						<th class="tr">操作时间：</th>
						<td>
							<input type="text" class="u-ipt "
							name="pf.outTime" maxlength="13" id="nowTime3" style="width:160px;" readonly="readonly"/>
							<img onclick="WdatePicker({el:'nowTime3'})" src="${pageContext.request.contextPath}/assets/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
						</td>
					</tr>		
					<tr height="40">
						<th class="tr">备注：</th>
						<td>
							<input type="text" class="u-ipt "
							name="pf.remark" maxlength="13" id="remark"/>
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

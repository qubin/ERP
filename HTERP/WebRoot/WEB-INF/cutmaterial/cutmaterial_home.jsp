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
<title></title>
<script type="text/javascript">
	var j = jQuery;
	var fjnum = 1;
	
	function addTableTr(){
		
		var txtMjid = j("#txtMjid").val();
		var txtGysjh = j("#txtGysjh" + fjnum).val();
		var txtCjjs = j("#txtCjjs" + fjnum).val();
		var txtCjcc = j("#txtCjcc" + fjnum).val();
		
// 		alert("txtMjid:"+txtMjid);
// 		alert("txtGysjh:"+txtGysjh);
// 		alert("txtCjjs:"+txtCjjs);
// 		alert("txtCjcc:"+txtCjcc);
		
		if(txtMjid == "-1" || txtGysjh == "-1" || txtCjjs == "" || txtCjcc == ""){
			alert("请先将以上信息填写完整！");
			return false;
		}
		
		fjnum = fjnum + 1;
		
		j("#txtFjRow").val(fjnum);
		
		var jsonData = eval('${JsonSupplyMatList}');
		
		var t  = "<tr>";
			t +=    "<td>";
			t +=		"<div align='center'>";
			t +=			"<select name='txtGysjh" + fjnum + "' id='txtGysjh" + fjnum + "' class='u-ipt'>";
			t +=				"<option value='-1'>--请选择--</option>";
							for(var i=0;i<jsonData.length;i++){
			t +=			 	"<option value='"+jsonData[i].uuid+"'>"+jsonData[i].scrollId+"</option>";
					 		}
			t +=			"</select>";
			t +=		"</div>";
			t +=	"</td>";
			t +=	"<td>";
			t +=		"<div align='center'>";
			t +=			"<label id='txtClxh"+fjnum+"'></label>";
			t +=			"<input type='hidden' id='txtClxh"+fjnum+"' name='txtClxh" + fjnum + "' />";
			t +=		"</div>";
			t +=	"</td>";
			t +=	"<td>";
			t +=		"<div align='center'>";
			t +=			"<input type='text' class='u-ipt' id='txtCjjs" + fjnum + "' name='txtCjjs" + fjnum + "'>";
			t +=		"</div>";
			t +=	"</td>";
			t +=	"<td>";
			t +=		"<div align='center'>";
			t +=			"<input type='text' class='u-ipt' id='txtCjcc" + fjnum + "' name='txtCjcc" + fjnum + "'>";
			t +=		"</div>";
			t +=	"</td>";
			t += "</tr>";
		
		j("#cutMaterialTable").append(t);
		
		j("#txtGysjh"+fjnum).change(function() {
			var url = "${pageContext.request.contextPath}/admin/cutmaterial/cutmaterial_getMaterialModel.html?materId=" + j("#txtGysjh"+fjnum).val();
			new Ajax.Request(url, {
				method : 'get',
				onSuccess : function(data) {
					j("#txtClxh"+fjnum).html(data.responseText);
					j("#txtClxh"+fjnum).val(data.responseText);
				}
			});
		});
	}
	
	function getMmatDesc(obj){
	    var url = "${pageContext.request.contextPath}/admin/cutmaterial/cutmaterial_getMmatDesc.html?mmatId="+obj.value;
  		new Ajax.Request(url,
  	  	{
  	  		method:'get',
  	  		onSuccess: function(data){
  	  			j("#mmatDesc").html(data.responseText);
  	  		}
  	  	});
	}
	
	function getMaterialModel(obj) {
		var url = "${pageContext.request.contextPath}/admin/cutmaterial/cutmaterial_getMaterialModel.html?materId=" + obj.value;
		new Ajax.Request(url, {
			method : 'get',
			onSuccess : function(data) {
				j("#txtClxh1").html(data.responseText);
				j("#txtClxh1").val(data.responseText);
			}
		});
	}
	

	window.onload = function() {
	
		
	}
</script>
</head>
<body>
<form id="form1" action="${pageContext.request.contextPath}/admin/cutmaterial/cutmaterial_cutmaterial.html" method="post">
	<div id="container" class="container">
		<div align="right">
			<input type="hidden" id="txtFjRow" name="txtFjRow" value="1">
			<table width="100%">
				<tr>
					<td width="55%" align="right">
						<h1 align="right">
							<span class="icon-2x"><strong>分卷</strong></span><br />
						</h1>
					</td>
					<td>
						<div align="right">
						  <input type="button" onclick="addTableTr()" class="u-btn" value=" + 增加行"/>
					  </div>
					</td>
				</tr>
			</table>
		</div>

		<br>

		<table width="80%" class="m-table" id="cutMaterialTable">
			<thead>
				<tr height="35">
					<th class="tr"><div align="center">母卷ID#</div></th>
					<th class="tr"><div align="center">母卷描述</div></th>
					<th><div align="center">
							<span class="tr">供应商卷号</span>
						</div></th>
					<th><div align="center">材料型号</div></th>
					<th><div align="center">裁剪卷数</div></th>
					<th><div align="center">裁剪尺寸及公差</div></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="10%" rowspan="100"><div align="center">
							<s:select name="material.uuid"
							   id="txtMjid"
							   onchange="getMmatDesc(this)"
							   cssClass="u-ipt required validate-selection"
						       list="#request.materialList"
						       listKey="uuid" listValue="mmatId" headerKey="-1"
						       headerValue="--请选择--" />
						</div></td>
					<td width="10%" rowspan="100">
						<label id="mmatDesc"></label>
					</td>
					<td>
						<div align="center">
							<s:select name="txtGysjh1"
							   onchange="getMaterialModel(this)"
							   cssClass="u-ipt required validate-selection"
						       list="#request.sonList"
						       listKey="uuid" listValue="scrollId" headerKey="-1"
						       headerValue="--请选择--" />
						</div>
					</td>
					<td>
						<div align="center">
							<label id="txtClxh1"></label>
							<input type="hidden" id="txtClxh1" name="txtClxh1">
						</div>
					</td>
					<td><div align="center">
							<input type="text"  class="u-ipt" name="txtCjjs1" id="txtCjjs1"/>
						</div></td>
					<td><div align="center">
							<input type="text" class="u-ipt" name="txtCjcc1" id="txtCjcc1"/>
						</div></td>
				</tr>
			</tbody>

		</table>

		<br>

		<div align="center">
			<input type="submit" class="u-btn" value="提交"> 
			<input type="reset" class="u-btn" value="重置">
		</div>
		<p>
			<br /> <br />
		</p>
	</div>
</form>
	<!-- /#container -->
	<!--javascript start-->
	<!--javascript end-->
</body>
</html>

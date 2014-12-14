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
		
		fjnum = fjnum + 1;
		
		j("#txtFjRow").val(fjnum);
		
		var t  = "<tr>";
			t +=    "<td>";
			t +=		"<div align='center'><input type='text' class='u-ipt' name='txtGysjh" + fjnum + "' />";
// 			t +=			"<select name='select2' id='select2'  class='u-ipt'>";
// 			t +=				"<option value='1'>--请选择--</option>";
// 			t +=			"</select>";
			t +=		"</div>";
			t +=	"</td>";
			t +=	"<td>";
			t +=		"<div align='center'><input type='text' class='u-ipt' name='txtClxh" + fjnum + "' />";
// 			t +=			"<select name='select10' id='select10'  class='u-ipt'>";
// 			t +=				"<option value='1' selected>--请选择--</option>";
// 			t +=			"</select>";
			t +=		"</div>";
			t +=	"</td>";
			t +=	"<td>";
			t +=		"<div align='center'>";
			t +=			"<input type='text' class='u-ipt' name='txtCjjs" + fjnum + "'>";
			t +=		"</div>";
			t +=	"</td>";
			t +=	"<td>";
			t +=		"<div align='center'>";
			t +=			"<input type='text' class='u-ipt'name='txtCjcc" + fjnum + "'>";
			t +=		"</div>";
			t +=	"</td>";
			t += "</tr>";
		
		j("#cutMaterialTable").append(t);
	}
	
	function getMmatDesc(obj){
	    var url = "${pageContext.request.contextPath}/admin/cutmaterial/cutmaterial_getMmatDesc.html?mmatId="+obj.value;
  		new Ajax.Request(url,
  	  	{
  	  		method:'get',
  	  		onSuccess: function(data){
  	  			j("#mmatDesc").html(data.responseText);
 	  			//document.getElementById("mmatDesc").innerText = data.responseText;
  	  		}
  	  	});
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
							<s:select name="material.mmatId"
							   onchange="getMmatDesc(this)"
							   cssClass="u-ipt required validate-selection"
						       list="#request.materialList"
						       listKey="uuid" listValue="mmatId" headerKey="-1"
						       headerValue="--请选择--" />
						</div></td>
					<td width="10%" rowspan="100">
						<label id="mmatDesc"></label>
					</td>
					<td><div align="center">
							<input type="text"  class="u-ipt" name="txtGysjh1"/>
						</div></td>
					<td><div align="center">
							<input type="text"  class="u-ipt" name="txtClxh1" />
						</div></td>
					<td><div align="center">
							<input type="text"  class="u-ipt" name="txtCjjs1" />
						</div></td>
					<td><div align="center">
							<input type="text" class="u-ipt" name="txtCjcc1" />
						</div></td>
				</tr>
			</tbody>

		</table>

		<br>

		<table width="100%" class="m-table-form">
			<tbody>
				<tr>
					<th width="10%" class="tr">&nbsp;</th>
					<th width="19%" class="tr">
						<div align="right">下单人签名 Singnature：</div>
					</th>
					<th width="71%" class="tr">
						<div align="left">
							<input name="txt30" type="text" class="text-box" id="txt30">
						</div>
					</th>
				</tr>
				<tr>
					<th class="tr">&nbsp;</th>
					<th class="tr"><div align="right">日期 Date：</div></th>
					<th class="tr">
						<div align="left">
							<input name="txt30" type="text" class="text-box" id="txt30">
						</div>
					</th>
				</tr>
			</tbody>
		</table>


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
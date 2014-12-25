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
	//获取警示类型
	function getAlarmType(obj){
		var url = "${pageContext.request.contextPath}/admin/stockalarm/stockalarm_getHtContentByAlarmType.html?type=" + obj.value;
			new Ajax.Request(url, {
				method : 'get',
				onSuccess : function(data) {
					var jsonData = eval("(" + data.responseText + ")");
					
					var sv = document.getElementById("htSelectVal");
					sv.options.length = 0;	//清空华天select选项
					sv.options.add(new Option("--请选择--", "-1"));
					
					for (var i = 0; i < jsonData.length; i++) {
						sv.options.add(new Option(jsonData[i].HTNO,jsonData[i].ID));
					}
				}
		});
	}
</script>
</head>
<body>
	<div id="container" class="container">


		<div class="hr10"></div>

		<div class="hr10"></div>
		<h2>库存警示设置</h2>
		<form id="form1" action="${pageContext.request.contextPath }/admin/stockalarm/stockalarm_saveAlarm.html" method="post">
			<input type="hidden" name="strMaterialId" id="strMaterialId">
			<table class="m-table-form">
				<tbody>
					<tr>
						<th width="45%" height="40" class="tr">警示类型：</th>
						<td height="40">
							<s:select cssStyle="width:178px" name="txtAlarmType" onchange="getAlarmType(this)" cssClass="u-ipt required validate-selection" list="#{-1:'--请选择--',1:'原料',2:'产品' }"></s:select>
						</td>
					</tr>
					<tr>
						<th width="45%" height="40" class="tr">华天(材料/产品)编号：</th>
						<td height="40">
							<select id="htSelectVal" name="txtHtContent" class="u-ipt required validate-selection" style="width:178px" onchange="getMaterialInfo(this)">
							</select>
						</td>
					</tr>
					<tr>
						<th height="40" class="tr">库存警示重量：</th>
						<td height="40"><input type="text" name="txtAlarmWeight" class="u-ipt required validate-digits"/></td>
					</tr>
					<tr>
						<th height="40" class="tr"></th>
						<th height="40" class="tr" style=" text-align:left;">*注：如果库存低于警示重量，会在主页面提示。</th>
					</tr>
				</tbody>
			</table>
			<p>
				<label></label>
			</p>
			<br>
			<p align="center">
				<input name="button" type="submit" class="u-btn" id="button" value="提交"> 
				<input name="button" type="button" onclick="resetForm()" class="u-btn" id="button" value="重置">
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
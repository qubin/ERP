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

</script>
</head>
<body>
	<div id="container" class="container">
		<div class="hr10"></div>
		<div class="hr10"></div>
		<h2>材料短溢处理</h2>
		<form id="form1" action="${pageContext.request.contextPath }/admin/matsoflow/matsoflow_modify.html" method="post">
			<input type="hidden" name="matsoflow.uuid" value="${matsoflow.uuid}"/>
			<table class="m-table-form">
				<tbody>
					<tr>
						<th width="45%" height="40" class="tr">供&nbsp;应&nbsp;商：</th>
						<td height="40">
							<s:property value="supplier.code"/>
						</td>
					</tr>
					<tr>
						<th width="45%" height="40" class="tr">华天材料编号：</th>
						<td height="40">
							<s:property value="supplyMat.htMatNo"/>
						</td>
					</tr>
					<tr>
						<th height="40" class="tr">供应商代码：</th>
						<td height="40">
							<s:property value="supplier.name"/>
						</td>
					</tr>
					<tr>
						<th height="40" class="tr">密&nbsp;&nbsp;&nbsp;度：</th>
						<td height="40">
							<s:property value="material.density"/>
						</td>
					</tr>
					<tr>
						<th height="40" class="tr">厚&nbsp;&nbsp;&nbsp;度：</th>
						<td height="40">
							<s:property value="material.thickness"/>
						</td>
					</tr>
					<tr>
						<th height="40" class="tr">处理重量：</th>
						<td height="40">
							<input type="text" class="u-ipt required validate-number" name="matsoflowAWeight" value="${matsoflow.aWeight}" />
							(<font color="red">* 注：正数为进库，负数为出库</font>)
						</td>
					</tr>
					<tr>
						<th height="40" class="tr">处理原因：</th>
						<td height="40">
							<input type="text" class="u-ipt required" name="matsoflow.prcReason" value="${matsoflow.prcReason}" />
						</td>
					</tr>
					<tr>
						<th height="40" class="tr">现在所在库：</th>
						<td height="40">
							<s:property value="warehouse.sign1"/>
						</td>
					</tr>
					<tr>
						<th height="40" class="tr">入库时间：</th>
						<td height="40">
							${outTime}
						</td>
					</tr>
					<tr>
						<th height="40" class="tr">描&nbsp;&nbsp;&nbsp;述：</th>
						<td height="40">
							<s:property value="material.desc"/>
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
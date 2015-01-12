<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
		<form id="form1" action="${pageContext.request.contextPath }/admin/material/material_add.html" method="post">
			<table class="m-table-form">
				<tbody>
					<tr>
						<th class="tr" width="42%">供应商：</th>
						<td><s:select name="supplyMat.supplyId"
							  cssClass="u-ipt required validate-selection"
							  cssStyle="width:178px"
						      list="#request.supplierList"
						      listKey="uuid" listValue="name" headerKey="-1"
						      headerValue="--请选择--" /></td>
					</tr>
					<tr>
						<th class="tr" width="42%">材料名称：</th>
						<td><input type="text" class="u-ipt required"
							name="supplyMat.matSupplierName" maxlength="30" /></td>
					</tr>
					<tr>
						<th class="tr">华天材料号：</th>
						<td><input type="text" class="u-ipt required" name="supplyMat.htMatNo"
							maxlength="60"></td>
					</tr>
					<tr>
						<th class="tr">面密度：</th>
						<td><input type="text" class="u-ipt validate-number" name="material.density"
							maxlength="60"></td>
					</tr>
					<tr>
						<th class="tr">厚度：</th>
						<td><input type="text" class="u-ipt validate-number" name="material.thickness"
							maxlength="60"></td>
					</tr>
					<tr>
						<th class="tr">备注：</th>
						<td><input type="text" class="u-ipt" name="material.remark"
							maxlength="60"></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2" class="tc"><input class="u-btn" type="submit"
							value="提交" id="submitBtn" /> &emsp; <input class="u-btn"
							type="button" onclick="javascript:history.back()" value="返回" /></td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
	<!-- /#container -->
</body>
</body>
</html>
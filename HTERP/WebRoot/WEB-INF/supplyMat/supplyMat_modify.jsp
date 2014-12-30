<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="../top.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title></title>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/dwr/interface/findUserRole.js'></script>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/dwr/engine.js'></script>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/dwr/util.js'></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div id="container" class="container">
		<div class="hr10"></div>
		<form id="form1"
			action="${pageContext.request.contextPath }/admin/supplyMat/supplyMat_modify.html"
			method="post">
			
			<input type="hidden" value="${supplyMat.supplyMatId}" name="supplyMat.supplyMatId"/>
			
			<table class="m-table-form">
				<tbody>
					<tr>
						<th class="tr" width="42%">仓库ID：</th>
						<td>
							<s:select name="supplyMat.supplyId"
							cssClass="u-ipt required"
							cssStyle="width:178px"
							list="#request.supplierList"
							listKey="uuid" listValue="name" headerKey="-1"
							headerValue="--请选择--" />
						</td>
					</tr>
					<tr>
						<th class="tr">材料名：</th>
						<td><input type="text" class="u-ipt required" name="supplyMat.matSupplierName"
							maxlength="60" value="${supplyMat.matSupplierName}"></td>
					</tr>
					<tr>
						<th class="tr">华天材料号：</th>
						<td><input type="text" class="u-ipt required"
							name="supplyMat.htMatNo" maxlength="60" value="${supplyMat.htMatNo}"></td>
					</tr>
					<tr>
						<th class="tr">供应商卷号：</th>
						<td><input type="text" class="u-ipt required"
							name="supplyMat.matSupplierScrollId" maxlength="60" value="${supplyMat.matSupplierScrollId}"></td>
					</tr>
					<tr>
						<th class="tr">材料型号：</th>
						<td><input type="text" class="u-ipt required"
							name="supplyMat.materialModel" maxlength="60" value="${supplyMat.materialModel}"></td>
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
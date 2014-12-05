<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>>
<jsp:include page="../top.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title></title>
</head>
<body>
	<div id="container" class="container">
		<div class="hr10"></div>
		<form id="form1"
			action="${pageContext.request.contextPath }/admin/supplyMat/supplyMat_add.html"
			method="post">
			<table class="m-table-form">
				<tbody>
					<tr>
						<th class="tr" width="42%">所属供应商：</th>
						<td>
							<s:select name="supplyMat.supplyId"
							cssClass="u-ipt required validate-selection"
							cssStyle="width:178px"
							list="#request.supplierList"
							listKey="uuid" listValue="name" headerKey="-1"
							headerValue="--请选择--" />
						</td>
					</tr>
					<tr>
						<th class="tr" width="42%">材料名称：</th>
						<td><input type="text" class="u-ipt required"
							name="supplyMat.matSupplierName" maxlength="13" /></td>
					</tr>
					<tr>
						<th class="tr">华天材料号：</th>
						<td><input type="text" class="u-ipt required" name="supplyMat.htMatNo"
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
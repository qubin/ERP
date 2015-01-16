<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
			action="${pageContext.request.contextPath }/admin/customer/customer_add.html"
			method="post">
			<table class="m-table-form">
				<tbody>
					<tr>
						<th class="tr" width="200">盒号：</th>
						<td><label for="">${boxNo}</label></td>
						<th class="tr" width="200">所在仓库：</th>
						<td><label for="">${sign}</label></td>
					</tr>
					<tr>
						<th class="tr">批号：</th>
						<td><label for="">${batchCode}</label></td>
						<th class="tr">盒号：</th>
						<td><label for="">${boxNo}</label></td>
					</tr>
					<tr>
						<th class="tr">状态：</th>
						<td><label for="">${flag}</label></td>
					</tr>
				
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4" class="tc"><input class="u-btn" type="submit"
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
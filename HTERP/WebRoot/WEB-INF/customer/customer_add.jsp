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
						<th class="tr" width="42%">名称：</th>
						<td><input type="text" class="u-ipt required"
							name="cust.custName" maxlength="13" /></td>
					</tr>
					<tr>
						<th class="tr" width="42%">联系电话：</th>
						<td><input type="text" class="u-ipt required"
							name="cust.telephone" maxlength="13" /></td>
					</tr>
					<tr>
						<th class="tr">联系人：</th>
						<td><input type="text" class="u-ipt required " name="cust.custConPerson"
							maxlength="60"></td>
					</tr>
					<tr>
						<th class="tr">联系人电话：</th>
						<td><input type="text" class="u-ipt validate-tel-phone" name="cust.custPhone"
							maxlength="60"></td>
					</tr>
					<tr>
						<th class="tr">传真：</th>
						<td><input type="text" class="u-ipt required" name="cust.fax"
							maxlength="60"></td>
					</tr>
					<tr>
						<th class="tr">备注：</th>
						<td><input type="text" class="u-ipt required" name="cust.remark"
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
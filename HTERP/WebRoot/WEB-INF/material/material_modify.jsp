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
			action="${pageContext.request.contextPath }/admin/supplier/supplier_modify.html"
			method="post">
			
			<input type="hidden" value="${supplier.uuid}" name="supplier.uuid"/>
			
			<table class="m-table-form">
				<tbody>
					<tr>
						<th class="tr" width="42%">名称：</th>
						<td><input type="text" class="u-ipt required"
							name="supplier.name" maxlength="13" value="${supplier.name}"/></td>
					</tr>
					<tr>
						<th class="tr">描述：</th>
						<td><input type="text" class="u-ipt" name="supplier.desc"
							maxlength="60" value="${supplier.desc}"></td>
					</tr>
					<tr>
						<th class="tr">联系人：</th>
						<td><input type="text" class="u-ipt"
							name="supplier.conPerson" maxlength="60" value="${supplier.conPerson}"></td>
					</tr>
					<tr>
						<th class="tr">联系电话：</th>
						<td><input type="text" class="u-ipt" name="supplier.conPhone"
							maxlength="60" value="${supplier.conPhone}"></td>
					</tr>
					<tr>
						<th class="tr">传真：</th>
						<td><input type="text" class="u-ipt" name="supplier.fax"
							maxlength="60" value="${supplier.fax}"></td>
					</tr>
					<tr>
						<th class="tr">地址：</th>
						<td><input type="text" class="u-ipt" name="supplier.address"
							maxlength="60" value="${supplier.address}"></td>
					</tr>
					<tr>
						<th class="tr">邮箱：</th>
						<td><input type="text" class="u-ipt" name="supplier.email"
							maxlength="60" value="${supplier.email}"></td>
					</tr>
					<tr>
						<th class="tr">备注：</th>
						<td><input type="text" class="u-ipt" name="supplier.remark"
							maxlength="60" value="${supplier.remark}"></td>
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
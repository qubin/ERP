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
			action="${pageContext.request.contextPath }/admin/warehouse/warehouse_modify.html"
			method="post">
			
			<input type="hidden" value="${wh.warehouseId}" name="wh.warehouseId"/>
			
			<table class="m-table-form">
				<tbody>
					<tr>
						<th class="tr" width="42%">代码：</th>
						<td><input type="text" class="u-ipt required"
							name="wh.sign1" maxlength="30" value="${wh.sign1}"/></td>
					</tr>
					<tr>
						<th class="tr">地区：</th>
						<td><input type="text" class="u-ipt required" name="wh.area"
							maxlength="60" value="${wh.area}"></td>
					</tr>
					<tr>
						<th class="tr">描述：</th>
						<td><input type="text" class="u-ipt required"
							name="wh.desc1" maxlength="60" value="${wh.desc1}"></td>
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
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
			action="${pageContext.request.contextPath }/admin/custPdct/custPdct_add.html"
			method="post">
			<table class="m-table-form">
				<tbody>
					<tr>
						<th class="tr" width="42%">客户：</th>
						<td>
							<s:select name="cp.custId"
							cssClass="u-ipt required validate-selection"
							cssStyle="width:178px"
							list="#request.cList"
							listKey="custId" listValue="custName" headerKey="-1"
							headerValue="--请选择--" id=""/>
						</td>
					</tr>
					<tr>
						<th class="tr" width="42%">产品：</th>
						<td>
							<s:select name="cp.prodId"
							cssClass="u-ipt required validate-selection"
							cssStyle="width:178px"
							list="#request.pList"
							listKey="uuid" listValue="htPn" headerKey="-1"
							headerValue="--请选择--" id=""/>
						</td>
					</tr>
					<tr>
						<th class="tr">客户产品编号：</th>
						<td><input type="text" class="u-ipt required " name="cp.cus_pn"
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
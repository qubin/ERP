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
			action="${pageContext.request.contextPath }/admin/custPdct/custPdct_modify.html"
			method="post">
			<input type="hidden" name="cp.cpId" value="${ cp.id}"/>
			<table class="m-table-form">
				<tbody>
					<tr height="40">
						<th class="tr" width="42%">客户：</th>
						<td>
							<label id="" >${cp.name}</label>
						</td>
					</tr>
					<tr height="40">
						<th class="tr" width="42%">产品：</th>
						<td>
							<label id="">${cp.ht_pn}</label>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">客户产品编号：</th>
						<td><input type="text" class="u-ipt required " name="cp.cus_pn"
							maxlength="60" value="${ cp.cus_pn}"></td>
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
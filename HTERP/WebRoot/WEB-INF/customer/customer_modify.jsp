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
			action="${pageContext.request.contextPath }/admin/customer/customer_modify.html"
			method="post">
			
			<input type="hidden" value="${cust.custId}" name="cust.custId"/>
			
			<table class="m-table-form">
				<tbody>
					<tr>
						<th class="tr">名称：</th>
						<td><input type="text" class="u-ipt required"
							name="cust.name" maxlength="13" value="${cust.custName}"/></td>
							<th class="tr">电话：</th>
						<td><input type="text" class="u-ipt" name="cust.telephone"
							maxlength="60" value="${cust.telephone}"></td>
					</tr>
					<tr>
						<th class="tr">联系人：</th>
						<td><input type="text" class="u-ipt"
							name="cust.custConPerson" maxlength="60" value="${cust.custConPerson}"></td>
							<th class="tr">联系电话：</th>
						<td><input type="text" class="u-ipt" name="cust.custPhone"
							maxlength="60" value="${cust.custPhone}"></td>
					</tr>
			
					<tr>
						<th class="tr">传真：</th>
						<td><input type="text" class="u-ipt" name="cust.fax"
							maxlength="60" value="${cust.fax}"></td>
						<th class="tr">税号：</th>
						<td><input type="text" class="u-ipt" name="cust.tariff"
							maxlength="60"></td>
					</tr>
					<tr>
						<th class="tr">开户银行：</th>
						<td><input type="text" class="u-ipt" name="cust.bank"
							maxlength="60"></td>
						<th class="tr">银行账号：</th>
						<td><input type="text" class="u-ipt" name="cust.account"
							maxlength="60"></td>
					</tr>
					<tr>
						<th class="tr">单位地址：</th>
						<td><input type="text" class="u-ipt" name="cust.address"
							maxlength="60"></td>
						<th class="tr">备注：</th>
						<td><input type="text" class="u-ipt" name="cust.remark"
							maxlength="60" value="${cust.remark}"></td>
					</tr>
					<s:iterator value="#request.dataList" id="data">
						<tr>
							<th class="tr">华天产品编号：</th>
							<td ><label for=""><s:property value="#data.ht_pn"/></label></td>
							<th class="tr">客户产品编号：</th>
							<td><input type="text" class="u-ipt required" name="cusPn"
								maxlength="60" value="${data.cus_pn}">
								所在仓库<label for="">${data.sign1 }</label>
								<input type="hidden" name="cpId" value="${data.id}"/>
							</td>
						</tr>
					</s:iterator>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4" class="tc"><input class="u-btn" type="submit"
							value="提交" id="submitBtn" /> &emsp; 
							<input class="u-btn"type="button" onclick="javascript:history.back()" value="返回" />
							</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
	<!-- /#container -->
</body>
</body>
</html>
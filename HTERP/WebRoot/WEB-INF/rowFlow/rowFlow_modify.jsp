<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../top.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title></title>
<script type="text/javascript">
var j = jQuery;
j(document).ready(function(){
	j("#mWeight").bind("change",function(){
		var num = j("#mWeight").val();
		var mId = j("#mId").val();
		var r = /^[0-9]+\.{0,1}[0-9]{0,2}$/;
		if(r.test(num)){
			var uri = "${pageContext.request.contextPath}/admin/mStorage/mStorage_checkOutNum.html?material.uuid=" + mId + "&material.weight=" + num;
			j.get(uri,function(data){
				if(data){
					j("#mWeight").val(" ");
					alert("出库数量不能超过库存！");
				}
			});
		}
	});
});
</script>
</head>
<body>
	<div id="container" class="container">
		<div class="hr10"></div>
		<form id="form1"
			action="${pageContext.request.contextPath }/admin/rowFlow/rowFlow_modify.html"
			method="post">
			
			<input type="hidden" value="${rowFlow.rfId}" name="rowFlow.rfId"/>
			
			<table class="m-table-form">
				<tbody>
					<tr>
						<th height="40" class="tr" width="42%">经办人：</th>
						<td height="40"><input type="text" class="u-ipt required"
							name="rowFlow.outPerson" maxlength="13" value="${rowFlow.outPerson}" disabled/></td>
					</tr>
					<tr>
						<th height="40" class="tr">入库/出库：</th>
						<td height="40">
							<c:if test="${rowFlow.inOrOut == 1}">
								出库
							</c:if>
							<c:if test="${rowFlow.inOrOut == 0}">
								入库
							</c:if>
						</td>
					</tr>
					<tr>
						<th height="40" class="tr">操作时间：</th>
						<td height="40"><input type="text" class="u-ipt"
							name="rowFlow.outTime" maxlength="60" value="${rowFlow.outTime}" disabled></td>
					</tr>
					<tr>
						<th height="40" class="tr">材料ID：</th>
						<td height="40"> <input type="text" class="u-ipt" name="rowFlow.materialId"
							maxlength="60" value="${rowFlow.materialId}" readonly="readonly" id="mId"></td>
					</tr>
					<tr>
						<th height="40" class="tr">备注：</th>
						<td height="40"><input type="text" class="u-ipt" name="rowFlow.remark"
							maxlength="60" value="${rowFlow.remark}"></td>
					</tr>
					<tr>
						<th height="40" class="tr">重量：</th>
						<td height="40" ><input type="text" class="u-ipt required validate-number" name="rowFlow.weight"
							maxlength="60" value="${rowFlow.weight}" id= "mWeight"></td>
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
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="../top.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title></title>
<script type="text/javascript">
function checkPFOut(Id){
	var value = document.getElementById("mWeight").value;
	var url = "${pageContext.request.contextPath}/admin/rowFlow/rowFlow_checkPFOut.html?rowFlow.materialId=" + Id + "&rowFlow.weight=" + value;
	new Ajax.Request(url,
  	{
  		method:'get',
  		onSuccess: function(data){
  			 if(data.responseText == 'false'){
  				alert('出库重量不能大于库存');
  			 }
  		},
  		onFailure: function(){ alert('出库重量不能大于库存') }
  	});
}
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
						<th class="tr" width="42%">经办人：</th>
						<td><input type="text" class="u-ipt required"
							name="rowFlow.outPerson" maxlength="13" value="${rowFlow.outPerson}" disabled/></td>
					</tr>
					<tr>
						<th class="tr">入库/出库：</th>
						<td><input type="text" class="u-ipt" name="rowFlow.inOrOut"
							maxlength="60" value="${rowFlow.inOrOut}" disabled></td>
					</tr>
					<tr>
						<th class="tr">操作时间：</th>
						<td><input type="text" class="u-ipt"
							name="rowFlow.outTime" maxlength="60" value="${rowFlow.outTime}" disabled></td>
					</tr>
					<tr>
						<th class="tr">材料ID：</th>
						<td><input type="text" class="u-ipt" name="rowFlow.materialId"
							maxlength="60" value="${rowFlow.materialId}" disabled></td>
					</tr>
					<tr>
						<th class="tr">备注：</th>
						<td><input type="text" class="u-ipt" name="rowFlow.remark"
							maxlength="60" value="${rowFlow.remark}" disabled></td>
					</tr>
					<tr>
						<th class="tr">重量：</th>
						<td><input type="text" class="u-ipt" name="rowFlow.weight"
							maxlength="60" value="${rowFlow.weight}" id= "mWeight" onchange="checkPFOut(${rowFlow.rfId})"></td>
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
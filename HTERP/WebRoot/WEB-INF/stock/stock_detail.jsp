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
			<table class="m-table-form">
				<tbody>
				
					<tr>
						<th class="tr">华天材料编号：</th>
						<td><label for="">${sm.htMatNo}</label></td>
						<th class="tr">供应商材料编号:</th>
						<td><label for="">${sm.matSupplierName }</label></td>
					</tr>
					<tr>
						<th class="tr">面密度：</th>
						<td><label for="">${m.density}</label></td>
						<th class="tr">厚度:</th>
						<td><label for="">${m.thickness}</label></td>
					</tr>
					<tr>
						<th class="tr">描述：</th>
						<td><label for="">${m.remark}</label></td>
						<th class="tr">总重量:</th>
						<td><label for="">${m.weight}</label></td>
					</tr>
					<tr>
						<th class="tr">规格：</th>
						<td><label for="">${m.standard}</label></td>
						<th class="tr">卷数:</th>
						<td><label for="">${m.scrollCount}</label></td>
					</tr>
					<tr>
						<th class="tr">库存警示重量：</th>
						<td><label for="">${m.alarmWeight}</label></td>
						<th class="tr">供应商编号:</th>
						<td><label for="">${s.code}</label></td>
					</tr>
					<tr>
						<th class="tr">所在仓库</th>
							<td><label for="">${w.sign1}</label></td>
						<th class="tr">备注</th>
						<td><label for="">${m.remark}</label></td>
					</tr>
					<tr>
						<th class="tr">供应商名称：</th>
						<td><label for="">${s.name}</label></td>
						<th class="tr">供应商编号:</th>
						<td><label for="">${s.code}</label></td>
					</tr>
					<tr>
						<th class="tr">供应商联系人：</th>
						<td><label for="">${s.conPerson}</label></td>
						<th class="tr">供应商联系电话:</th>
						<td><label for="">${s.conPhone}</label></td>
					</tr>
					<tr>
						<th class="tr">供应商传真：</th>
						<td><label for="">${s.fax}</label></td>
						<th class="tr">供应商地址:</th>
						<td><label for="">${s.address}</label></td>
					</tr>
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
	</div>
	<!-- /#container -->
</body>
</body>
</html>
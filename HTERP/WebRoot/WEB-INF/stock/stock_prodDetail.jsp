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
						<td><label for="">${p.htPn}</label></td>
						<th class="tr">车型/钢背应用:</th>
						<td><label for="">${p.appFor}</label></td>
					</tr>
					<tr>
						<th class="tr">产品性质：</th>
						<td><label for="">${p.properties}</label></td>
						<th class="tr">产品市场:</th>
						<td><label for="">${p.market}</label></td>
					</tr>
					<tr>
						<th class="tr">模具类型：</th>
						<td><label for="">${p.patternType}</label></td>
						<th class="tr">冲压步骤数:</th>
						<td><label for="">${p.proStep}</label></td>
					</tr>
					<tr>
						<th class="tr">步距：</th>
						<td><label for="">${p.stepPitch}</label></td>
						<th class="tr">料宽:</th>
						<td><label for="">${p.matWidth}</label></td>
					</tr>
					<tr>
						<th class="tr">面积：</th>
						<td><label for="">${p.areca}</label></td>
						<th class="tr">包装盒尺寸:</th>
						<td><label for="">${p.packSize}</label></td>
					</tr>
					<tr>
						<th class="tr">PPAP状态</th>
							<td><label for="">${p.ppapStatus}</label></td>
						<th class="tr">备注</th>
						<td><label for="">${p.remark}</label></td>
					</tr>
					<tr>
						<th class="tr">总重量</th>
							<td><label for="">${p.totalWeight}</label></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4" class="tc">
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
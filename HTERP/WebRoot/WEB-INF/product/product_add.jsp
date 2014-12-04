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
		<form id="form1" action="${pageContext.request.contextPath }/admin/product/product_add.html" method="post">
			<table class="m-table-form">
				<tbody>
					<tr>
						<th width="45%" align="right" class="tr">华天产品编号：</th>
					  	<td><input type="text" class="u-ipt required" name="product.htPn" maxlength="13" /></td>
				  </tr>
				  <tr>
				  		<th align="right" class="tr">客户产品编号：</th>
						<td><input type="text" class="u-ipt" name="product.cusPn" maxlength="60"></td>
				  </tr>
					<tr>
						<th align="right" class="tr">所在仓库：</th>
					  	<td><input type="text" class="u-ipt required" name="product.area" maxlength="60"></td>
				  </tr>
				  <tr>
				  		<th align="right" class="tr">客户：</th>
						<td><input type="text" class="u-ipt" name="product.customerId" maxlength="60"></td>
				  </tr>
					<tr>
						<th align="right" class="tr">车型/钢背应用：</th>
					  	<td><input type="text" class="u-ipt" name="product.appFor" maxlength="60"></td>
				  </tr>
				  <tr>
				  		<th align="right" class="tr">产品性质：</th>
						<td><input type="text" class="u-ipt" name="product.properties" maxlength="60"></td>
				  </tr>
				  <tr>
						<th align="right" class="tr">产品市场：</th>
					  	<td><input type="text" class="u-ipt" name="product.market" maxlength="60"></td>
				  </tr>
				  <tr>
				  		<th align="right" class="tr">模具类型：</th>
						<td><input type="text" class="u-ipt" name="product.patternType" maxlength="60"></td>
				  </tr>
					<tr>
						<th align="right" class="tr">冲压步骤数：</th>
					  	<td><input type="text" class="u-ipt" name="product.proStep" maxlength="60"></td>
				  </tr>
				  <tr>
				  		<th align="right" class="tr">步距：</th>
						<td><input type="text" class="u-ipt" name="product.stepPitch" maxlength="60"></td>
				  </tr>
					<tr>
						<th align="right" class="tr">料宽：</th>
					  	<td><input type="text" class="u-ipt" name="product.matWidth" maxlength="60"></td>
				  </tr>
				  <tr>
				  		<th align="right" class="tr">面积：</th>
						<td><input type="text" class="u-ipt" name="product.areca" maxlength="60"></td>
				  </tr>
					<tr>
						<th align="right" class="tr">单片重量：</th>
					  	<td><input type="text" class="u-ipt" name="product.singleWeight" maxlength="60"></td>
				  </tr>
				  <tr>
				  		<th align="right" class="tr">包装盒尺寸：</th>
						<td><input type="text" class="u-ipt" name="product.packSize" maxlength="60"></td>
				  </tr>
					<tr>
						<th align="right" class="tr">PPAP状态：</th>
					  	<td><input type="text" class="u-ipt" name="product.ppapStatus" maxlength="60"></td>
				  </tr>
				  <tr>
				  		<th align="right" class="tr">库存片数：</th>
						<td><input type="text" class="u-ipt" name="product.picCount" maxlength="60"></td>
				  </tr>
					<tr>
						<th align="right" class="tr">备注：</th>
					  	<td colspan="3"><input type="text" class="u-ipt" name="product.remark" maxlength="60"></td>
				  </tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4" align="center" class="tc">
							<input class="u-btn" type="submit" value="提交" id="submitBtn" /> 
							&emsp; 
							<input class="u-btn" type="button" onclick="javascript:history.back()" value="返回" />
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
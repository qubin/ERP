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
		<form id="form1" action="${pageContext.request.contextPath }/admin/product/product_modify.html" method="post">
			
			<input type="hidden" name="product.uuid" value="${product.uuid }" />
			
			<table class="m-table-form">
				<tbody>
					<tr>
						<th width="45%" align="right" class="tr">华天产品编号：</th>
					  	<td><input type="text" class="u-ipt required" name="product.htPn" value="${product.htPn }" maxlength="30" /></td>
				  </tr>
				  <tr>
				  		<th align="right" class="tr">客户产品编号：</th>
						<td><input type="text" class="u-ipt" name="product.cusPn" value="${product.cusPn }" maxlength="30"></td>
				  </tr>
				  <tr>
						<th align="right" class="tr">车型/钢背应用：</th>
					  	<td><input type="text" class="u-ipt" name="product.appFor" value="${product.appFor }" maxlength="30"></td>
				  </tr>
				  <tr>
				  		<th align="right" class="tr">产品性质：</th>
						<td>
							<s:select cssClass="u-ipt required validate-selection" cssStyle="width:178px" name="product.properties" list="#{-1:'--请选择--',1:'flat',2:'tab',3:'drift lock'}"></s:select>
						</td>
				  </tr>
				  <tr>
						<th align="right" class="tr">产品市场：</th>
					  	<td>
					  		<s:select cssClass="u-ipt required validate-selection" cssStyle="width:178px" name="product.market" list="#{-1:'--请选择--',1:'OE',2:'AF'}"></s:select>
					  	</td>
				  </tr>
				  <tr>
				  		<th align="right" class="tr">模具类型：</th>
						<td>
							<s:select cssClass="u-ipt required validate-selection" cssStyle="width:178px" name="product.patternType" list="#{-1:'--请选择--',1:'prog',2:'single die',3:'prog + single die'}"></s:select>
						</td>
				  </tr>
				  <tr>
						<th align="right" class="tr">冲压步骤数：</th>
					  	<td><input type="text" class="u-ipt" name="product.proStep" value="${product.proStep }" maxlength="60"></td>
				  </tr>
				  <tr>
				  		<th align="right" class="tr">步距：</th>
						<td><input type="text" class="u-ipt validate-number" name="product.stepPitch" value="${product.stepPitch }" maxlength="5"></td>
				  </tr>
					<tr>
						<th align="right" class="tr">料宽：</th>
					  	<td><input type="text" class="u-ipt validate-number" name="product.matWidth" value="${product.matWidth }" maxlength="6"></td>
				  </tr>
				  <tr>
				  		<th align="right" class="tr">面积：</th>
						<td><input type="text" class="u-ipt validate-number" name="product.areca" value="${product.areca }" maxlength="6"></td>
				  </tr>
					<tr>
						<th align="right" class="tr">单片重量：</th>
					  	<td><input type="text" class="u-ipt validate-number" name="product.singleWeight" value="${product.singleWeight }" maxlength="6"></td>
				  </tr>
				  <tr>
				  		<th align="right" class="tr">包装盒尺寸：</th>
						<td><input type="text" class="u-ipt" name="product.packSize" value="${product.packSize }" maxlength="15"></td>
				  </tr>
					<tr>
						<th align="right" class="tr">PPAP状态：</th>
					  	<td>
					  		<s:select cssClass="u-ipt required validate-selection" cssStyle="width:178px" name="product.ppapStatus" list="#{-1:'--请选择--',1:'ok',2:'null'}"></s:select>
				  		</td>
				  </tr>
				  <tr>
						<th align="right" class="tr">备注：</th>
					  	<td colspan="3"><input type="text" class="u-ipt" name="product.remark" value="${product.remark }" maxlength="60"></td>
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
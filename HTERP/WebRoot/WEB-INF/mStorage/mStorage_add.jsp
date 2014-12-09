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
		<h2>采购单基本信息</h2>
		<form id="form1"
			action="${pageContext.request.contextPath }/admin/customer/customer_add.html"
			method="post">
			<table class="m-table-form">
				<tbody>
					<tr>
						<th class="tr" width="42%">名称：</th>
						<td></td>
					</tr>
					<tr>
						<th class="tr" width="42%">名称：</th>
						<td></td>
					</tr>
					<tr>
						<th class="tr" width="42%">华天材料号：</th>
						<td>
							</td>
					</tr>
					<tr>
						<th class="tr" width="42%">面密度：</th>
						<td>
							</td>
					</tr>
					<tr>
						<th class="tr" width="42%">厚度：</th>
						<td>
							</td>
					</tr>
					
					<tr>
						<th class="tr" width="42%">描述：</th>
						<td>
							</td>
					</tr>
					<tr>
						<th class="tr" width="42%">规格：</th>
						<td>
							</td>
					</tr>
					<tr>
						<th class="tr" width="42%">备注：</th>
						<td>
							</td>
					</tr>
					<tr>
						<th class="tr" width="42%">时间：</th>
						<td>
							</td>
					</tr>
					<tr>
						<th class="tr" width="42%">入库/出库：</th>
						<td>入库<input type="radio" name="" id="" value="in"/>&nbsp;&nbsp;&nbsp;
							出库<input type="radio" name="" id="" value="out"/>
						</td>
					</tr>
					<tr>
						<th class="tr" width="42%">重量：</th>
						<td><input type="text" class="u-ipt required"
							name="supplyMat.matSupplierName" maxlength="13" />
						</td>
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
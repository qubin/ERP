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
<script type="text/javascript">
	var j = jQuery;
	j(document).ready(function(){
		j("#htNo").bind("change",function(){
			var id = j("#htNo").val();
			var uri = "${pageContext.request.contextPath}/admin/pdctFlow/pdctFlow_findDetail.html?product.uuid=" + id;
			j.getJSON(uri,function(data){
				if(data != ""){
					j("#custNo").html(data.cusPn);
					j("#appfor").html(data.appFor);
					j("#market").html(data.market);
					j("#properties").html(data.properties);
					j("#patternType").html(data.patternType);
					j("#proStep").html(data.proStep);
					j("#stepPitch").html(data.stepPitch);
					j("#matWidth").html(data.matWidth);
					j("#areca").html(data.areca);
					j("#singleWeight").html(data.singleWeight);
					j("#packSize").html(data.packSize);
					j("#ppapStatus").html(data.ppapStatus);
					j("#picCount").html(data.picCount);
				}
			});
		});
	});
</script>
<body>
	<div id="container" class="container">
		<div class="hr10"></div>
		<form id="form1"
			action="${pageContext.request.contextPath }/admin/customer/customer_add.html"
			method="post">
			<table class="m-table-form">
				<tbody>
					<tr height="40">
						<th class="tr" width="42%">华天材料编号：</th>
						<td>
							<s:select name=""
							cssClass="u-ipt required validate-selection"
							cssStyle="width:178px"
							list="#request.pList"
							listKey="uuid" listValue="htPn" headerKey="-1"
							headerValue="--请选择--" id="htNo"/>
						</td>
						<th class="tr" width="42%">客户产品编号：</th>
						<td><label id="custNo"></label></td>
					</tr>
					<tr height="40">
						<th class="tr">所在仓库：</th>
						<td>
							<s:select name=""
							cssClass="u-ipt required validate-selection"
							cssStyle="width:178px"
							list="#request.wList"
							listKey="warehouseId" listValue="sign1" headerKey="-1"
							headerValue="--请选择--" />
						</td>
						<th class="tr">客户：</th>
						<td>
							<s:select name=""
							cssClass="u-ipt required validate-selection"
							cssStyle="width:178px"
							list="#request.cList"
							listKey="custId" listValue="custName" headerKey="-1"
							headerValue="--请选择--" />
						</td>
					</tr>
					<tr height="40">
						<th class="tr">车型/钢背应用：</th>
						<td><label id="appfor"></label></td>
						<th class="tr">产品性质：</th>
						<td>
							<label id="properties"></label>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">产品市场：</th>
						<td>
							<label id="market"></label>
						</td>
						<th class="tr">模型类型：</th>
						<td>
							<label id="patternType"></label>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">冲压步骤数：</th>
						<td>
							<label id="proStep"></label>
						</td>
						<th class="tr">步距：</th>
						<td>
							<label id="stepPitch"></label>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">料宽：</th>
						<td>
							<label id="matWidth"></label>
						</td>
						<th class="tr">面积：</th>
						<td>
							<label id="areca"></label>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">单片重量：</th>
						<td>
							<label id="singleWeight"></label>
						</td>
						<th class="tr">包装盒尺寸：</th>
						<td>
							<label id="packSize"></label>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">PPAP状态：</th>
						<td>
							<label id="ppapStatus"></label>
						</td>
						<th class="tr">库存片数：</th>
						<td>
							<label id="picCount"></label>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr height="40">
						<td colspan="4" class="tc">
							入库<input type="radio" name="" id="" />
							&nbsp;&nbsp;&nbsp;&nbsp;
							出库<input type="radio" name="" id="" />
						</td>
					</tr>
					<tr>
						<td colspan="4" class="tc"><input class="u-btn" type="submit"
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
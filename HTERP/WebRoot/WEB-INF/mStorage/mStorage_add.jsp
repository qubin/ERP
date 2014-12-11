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
	window.onload = function(){
		if("${msg}" == "success"){
			alert("操作成功");
		}
	}
	var j = jQuery;
	j(document).ready(function(){
		var temp = "";
		j("#supply").bind("change",function(){
			j("#materialName").empty();
			j("#den").val("");
			j("#thick").val("");
			j("#desc").val("");
			j("#stan").val("");
			j("#remark").val("");
			j("#htNo").val("");
			var value = j("#supply").val();
			var s = "${pageContext.request.contextPath}/admin/mStorage/mStorage_findMaterial.html";
			j.getJSON(s,{uuid:value},function(data){
				if(data != null){
					temp = data;
					j("#materialName").append("<option value=''>--请选择--</option>");
					for(var i = 0; i < data.length; i ++){
						j("#materialName").append("<option value=" + data[i].supplyMatId + ">" + data[i].matSupplierName +　"</option>");
					}
				}else{
					alert("改供应商下没有材料");
				}
			});	
		});
		
		j("#materialName").bind("change",function(){
			var id = j("#materialName").val();
			for(var i = 0; i < temp.length; i ++){
				if(temp[i].supplyMatId == id){
					j("#htNo").html(temp[i].htMatNo);
				}
			}
			var uri = "${pageContext.request.contextPath}/admin/mStorage/mStorage_findDetail.html";
			j.getJSON(uri,{id:id},function(data){
				if(data != null){
						j("#materialId").val(data[0].uuid);
						j("#den").html(data[0].density);
						j("#thick").html(data[0].thickness);
						j("#desc").html(data[0].desc);
						j("#stan").html(data[0].standard);
						j("#remark").html(data[0].remark);
				}else{
					alert("该型号没有原材料");
				}
			});
		});
		
		j("#mWeight").bind("change",function(){
			var bar = j("input:radio:checked").val();
			var materialId = j("#materialId").val();
			if(materialId != ""){
				if(bar == 0){
					var num = j("#mWeight").val();
					var r = /^[0-9]+\.{0,1}[0-9]{0,2}$/;
					if(r.test(num)){
						var uri = "${pageContext.request.contextPath}/admin/mStorage/mStorage_checkOutNum.html?material.uuid=" + materialId + "&material.weight=" + num;
						j.get(uri,function(data){
							if(data){
								j("#mWeight").val(" ");
								alert("出库数量不能超过库存！");
							}
						});
					}
				}
			}else{
				alert("请先选择入库材料");
			}
	
		});
	});
	
</script>
<body>
	<div id="container" class="container">
		<div class="hr10"></div>
		<h2>采购单基本信息</h2>
		<form id="form1"
			action="${pageContext.request.contextPath}/admin/mStorage/mStorage_materialInOrOut.html"
			method="post">
			<input type="hidden" name="material.uuid" value="" id="materialId"/>
			<input type="hidden" name="rawFlow.outPerson" value="${sessionScope.loggedUser.userLoginId}" />
			<table class="m-table-form">
				<tbody>
					<tr>
						<th class="tr" width="42%">供应商：</th>
						<td><s:select name="supplyMat.supplyId"
							cssClass="u-ipt required validate-selection"
							cssStyle="width:178px"
							list="#request.sList"
							listKey="uuid" listValue="name" headerKey="-1"
							headerValue="--请选择--" id="supply" />
						</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%">供应商材料名：</th>
						<td height="40">
						<select name="" id="materialName" class="u-ipt required validate-selection" style="width:180px;">
							
						</select>
						</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%">华天材料号：</th>
						<td height="40" ><label id="htNo"></label>
							</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%">面密度：</th>
						<td height="40" ><label id="den"></label>
							</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%">厚度：</th>
						<td height="40" ><label id="thick"></label>
							</td>
					</tr>
					
					<tr>
						<th height="40" class="tr" width="42%">描述：</th>
						<td height="40" ><label id="desc"></label>
							</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%">规格：</th>
						<td height="40" ><label id="stan"></label>
							</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%">备注：</th>
						<td height="40"><input type="text" class="u-ipt"
							name="rawFlow.remark" maxlength="13" /></label>
							</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%">时间：</th>
						<td height="40" ><input type="text" class="u-ipt required"
							name="rawFlow.outTime" maxlength="13" id="d12" readonly="readonly"/>
							<img onclick="WdatePicker({el:'d12'})" src="${pageContext.request.contextPath}/assets/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
							</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%">入库/出库：</th>
						<td height="40">
						入库<input type="radio" name="rawFlow.inOrOut" id="" value="1" checked="checked"/>&nbsp;&nbsp;&nbsp;
						出库<input type="radio" name="rawFlow.inOrOut" id="outRadio" value="0"/>
						</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%">重量：</th>
						<td height="40"><input type="text" class="u-ipt required validate-number "
							name="rawFlow.weight" maxlength="13" id="mWeight"/>
							
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
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
		j("#scId").hide();
		var temp = "";
		j("#supply").bind("change",function(){
			j("#materialName").empty();
			j("#den").val("");
			j("#thick").val("");
			j("#desc").val("");
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
			j("#inScrollId").val("");
			var id = j("#materialName").val();
			for(var i = 0; i < temp.length; i ++){
				if(temp[i].supplyMatId == id){
					j("#htNo").html(temp[i].htMatNo);
				}
			}
			var uri = "${pageContext.request.contextPath}/admin/mStorage/mStorage_findDetail.html";
			j.getJSON(uri,{id:id},function(data){
				if(data != ""){
						j("#materialId").val(data[0].uuid);
						j("#den").html(data[0].density);
						j("#thick").html(data[0].thickness);
						j("#desc").html(data[0].desc);
						j("#remark").html(data[0].remark);
						j("#scValue").val(data[0].mmatId);
						if(data[0].scrollId != ""){
							j("#LaScrollId").show();
							j("#LaScrollId").html(data[0].scrollId);
							j("#scrollId").val(data[0].scrollId);
							j("#inScrollId").hide();
						}else{
							j("#LaScrollId").hide();
							j("#inScrollId").show();
						}
						if(data[0].mmatId != ""){
							j("#scInput").hide();
							j("#scId").html(data[0].mmatId);
							j("#scId").show();
							j("#bar1").hide();
							j("#bar2").hide();
						}else{
							j("#scInput").show();
							j("#scId").html("");
							j("#bar1").show();
							j("#bar2").show();
						}
						var whList = ${wList};
						if(data[0].warehouseId != ""){
							for(var i in whList){
								if(whList[i].warehouseId == data[0].warehouseId){
									j("#wh").append("<option>" + whList[i].sign1  + "</option>");
									j("#wh").removeClass("validate-selection");
									j("#wh").attr("disabled",true);
								}
							}
						}else{
							j("#wh").attr("disabled",false);
							j("#wh").addClass("validate-selection");
							j("#wh").html("");
							j("#wh").append("<option value=''>-请选择-</option>");
							for(var i = 0 ; i < whList.length; i ++){
								j("#wh").append("<option value='" + whList[i].warehouseId + "'>"+ whList[i].sign1 +"</option>");
							}
						}
				}else{
					alert("该型号没有对应原材料");
				}
			});
		});
		
		j("#mWeight").bind("change",function(){
			var materialId = j("#materialId").val();
			if(materialId != ""){
				if(j("#outRadio").attr("checked") == "checked"){
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
					}else{
						alert("请输入合法数字");
						j("#mWeight").val("");
					}
				}
			}else{
				alert("请先选择入库材料");
			}
	
		});
		j("#cRadio").bind("click",function(){
			j("#scInput").hide();
			j("#scInput").val("");
			j("#scInput").attr("disabled",true);
		});
		j("#mRadio").bind("click",function(){
			j("#scInput").show();
			j("#scInput").attr("disabled",false);
		});
		
		j("#inRadio").bind("click",function(){
			if(j(this).attr("checked") == "checked"){
				if(j("#scValue").val() != ""){
					j("#scId").show();
					j("#scId").html(j("#scValue").val());
					j("#bar1").hide();
					j("#bar2").hide();
					j("#scInput").hide();
				}else{
					j("#scId").hide();
					j("#scId").html("");
					j("#bar1").show();
					j("#bar2").show();
					j("#scInput").show();
				}
			}
		});
		j("#outRadio").bind("click",function(){
			if(j(this).attr("checked") == "checked"){
				j("#bar1").hide();
				j("#bar2").hide();
				j("#scInput").hide();
				j("#scId").show();
				j("#scId").val(j("#scValue").val());
				j("#scInput").val("");
				j("#LaScrollId").show(j("#scrollId").val());
				j("#inScrollId").hide();
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
			<input type="hidden" name="" value="" id="scValue"/>
			<input type="hidden" name="" value="" id="scrollId"/>
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
						<th height="40" class="tr" width="42%">所在仓库：</th>
						<td height="40" >
						<select class="u-ipt required validate-selection" name="material.warehouseId" id="wh">
							<option value="-1">---请选择---</option>
						</select>
						
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
						<th height="40" class="tr" width="42%">卷号：</th>
						<td height="40"><input type="text" class="u-ipt"
							name="rawFlow.scrollId" maxlength="13" id="inScrollId"/>
							<label for="" id="LaScrollId"></label>
							</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%">入库/出库：</th>
						<td height="40">
						入库<input type="radio" name="rawFlow.inOrOut" id="inRadio" value="2" checked="checked"/>&nbsp;&nbsp;&nbsp;
						出库<input type="radio" name="rawFlow.inOrOut" id="outRadio" value="1"/>
					
						</td>
					</tr>
					<tr>
						<th height="40" class="tr" width="42%" id="th1">母卷ID</th>
						<td height="40" id="td1">
						<span id="bar1">	母卷<input type="radio" name="foo" id="mRadio" checked="checked"/>&nbsp;&nbsp;&nbsp;</span>
						<span id="bar2">子卷<input type="radio" name="foo" id="cRadio" /></span>
							<label for="" id="scId"></label>
							<input type="text" class="u-ipt required" id="scInput" name="material.mmatId"/>
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
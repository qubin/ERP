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
		j("#batchCodeLa").hide();
		j("#boxNoLa").hide();
		j("#swLa").hide();
		j("#batchCode").hide();
		//入库点击
		j("#in").bind("click",function(){
			j("#swLa").hide();
			j("#sw").show();
			j("#sw").attr("disabled",false);
			findWh("in");
			checkSame();
		});
		//出库点击
		j("#out").bind("click",function(){
			j("#swLa").show();
			j("#sw").hide();
			j("#sw").attr("disabled",true);
			findWh("out");
		});
		//出入库盒数是否合法
		j("#boxNum").bind("change",function(){
			if(j(this).val() == 0){
				j(this).val("");
			}
			if(j("#out").attr("checked") == "checked"){
				if(j(this).val() != ""){
					var cpId = j("#cusPn").val();
					if(cpId != null){
						var uri = "${pageContext.request.contextPath}/admin/pdctFlow/pdctFlow_checkBoxNum.html?cpId=" + cpId + "&boxNum=" + j(this).val();
						j.get(uri,function(data){
							if(data != ""){
								if(data == "false"){
									j("#boxNum").val("");
									alert("出库片数不能大于库存");
								}
							}
						});
					}
				}
			}
		});
		//出入库片数是否合法
		j("#IOInput").bind("change",function(){
			if(j(this).val() == 0){
				j(this).val("");
			}
			if(j("#out").attr("checked") == "checked"){
				if(j(this).val() != ""){
					var cpId = j("#cusPn").val();
					if(cpId != null){
						var uri = "${pageContext.request.contextPath}/admin/pdctFlow/pdctFlow_checkPic.html?cpId=" + cpId + "&picNum=" + j(this).val();
						j.get(uri,function(data){
							if(data != ""){
								if(data == "false"){
									j("#IOInput").val("");
									alert("出库片数不能大于库存");
								}
							}
						});
					}
				}
			}
		});
		
		//当前仓库
		var temp = "";
		//切换仓库
		j("#whselect").bind("change",function(){
			checkSame();
		});
		//当前是否统一仓库
		function checkSame(){
			if(j("#whselect").val() != temp && j("#whselect").val() != -1){
				j("#boxNoLa").hide();	
				j("#whflag").val(j("#whselect").val());
				j("#boxNumLa").hide();
				j("#batchCode").show();
				j("#batchCode").attr("disabled",false);
				j("#batchCodeLa").hide();
			}else{
				j("#boxNoLa").show();
				j("#whflag").val(-1);
				j("#boxNumLa").show();
				j("#batchCode").hide();
				j("#batchCode").attr("disabled",true);
				j("#batchCodeLa").show();
			}
		}
		
		j("#customer").bind("change",function(){
			var custId = j("#customer").val();
			var uri = "${pageContext.request.contextPath}/admin/pdctFlow/pdctFlow_findCusPn.html?custId=" + custId;
			j("#cusPn").empty();
			j.getJSON(uri,function(data){
				if(data != ""){
					j("#cusPn").append("<option value='-1'>--请选择--</option>");
					for(var i = 0 ; i < data.length; i ++){
						j("#cusPn").append("<option value=" + data[i].cpId + ">" + data[i].cus_pn +"</option>");
					}
				}else{
					alert("该产品为空");
				}
			});
		});
		
		j("#cusPn").bind("change",function(){
			if(j("#in").attr("checked") == "checked"){
				findWh("in");
			}else if(j("#out").attr("checked") == "checked"){
				findWh("out");
			}
		
		});
		
		function findWh(inOrOut){
			j("#whselect").attr("disabled",false);
			j("#whId").hide();
			j("#whlabel").hide();
			var cpId = j("#cusPn").val();
			j("#cpId").val(cpId);
			if(cpId != -1 && cpId != null){
				var uri = "${pageContext.request.contextPath}/admin/pdctFlow/pdctFlow_findProd.html?cpId=" + cpId;
				j.getJSON(uri,function(data){
					if(data != ""){
						if(data[2] == true){
							//原入库，换仓库区别
							j("#nowflag").val("same");
							//从未入库
							j("#appfor").html(data[0].appFor);
							j("#properties").html(data[0].properties);
							j("#market").html(data[0].market);
							j("#patternType").html(data[0].patternType);
							j("#proStep").html(data[0].proStep);
							j("#stepPitch").html(data[0].stepPitch);
							j("#matWidth").html(data[0].matWidth);
							j("#areca").html(data[0].areca);
							j("#packSize").html(data[0].packSize);
							j("#ppapStatus").html(data[0].ppapStatus);
							j("#htPn").html(data[0].htPn);
							j("#weight").html(data[0].totalWeight);
							
							j("#picCount").html(data[1].picCount);
							j("#remark").val(data[1].remark);
							//批号label
							var prefix = data[1].area + data[3].batchCode + "";
							var tempNum = data[3].boxNum - data[3].boxNo;
							if(tempNum == 0){
								tempNum = 0;
							}
							var str = prefix + tempNum + " ~ " + prefix + data[3].boxNum;
							j("#batchCodeLa").html(data[3].batchCode);
							j("#boxNoLa").html(str);
							j("#swLa").html(data[1].weight);
							
							j("#batchCode").val(data[3].batchCode);
							j("#sw").val(data[1].weight);
							j("#boxNumLa").html(data[3].boxNum);
						}else{
							j("#nowflag").val("diff");
						}
						temp = data[1].area;
						if(inOrOut == "in"){
							j("#whId").show();
							if(data[2] == true){  	//true 为库存已有    false 为从未入库
								j("#whselect").val(data[1].area);
								temp = data[1].area;
								//批号 不可改 label
								j("#batchCodeLa").show();
								j("#boxNoLa").show();
								j("#swLa").show();
								//input
								j("#batchCode").hide();
								j("#batchCode").attr("disabled",true);
								j("#sw").hide();
								j("#sw").attr("disabled",true);
								//j("#area").val(data[1].warehouseId);
							}else{
								j("#batchCode").show();
								j("#batchCode").attr("disabled",false);
								j("#batchCodeLa").hide();
								j("#whselect").val(-1);
								j("#sw").show();
								j("#sw").attr("disabled",false);
							}
						}else{
							j("#whselect").attr("disabled",true);
							j("#batchCode").hide();
							j("#batchCode").attr("disabled",true);
							j("#batchCodeLa").show();
							if(data[2] == true){  	
								//正常出库
								j("#whId").show();
								j("#whselect").val(data[1].area);
								//j("#area").val(data[1].warehouseId);
							}else{
								alert("请先入库该产品");
								//出库  从未入库
								//j("#whselect").val("请先入库该产品");
								//还有库存为0，出库情况
								j("#whId").show();
							}
						}
						
					}else{
						alert("该产品为空");
					}
				});
			}
		}
	});
	
	
</script>
<body>
	<div id="container" class="container">
		<div class="hr10"></div>
		<form id="form1"
			action="${pageContext.request.contextPath }/admin/pdctFlow/pdctFlow_inWarehouse.html"
			method="post">
			<input type="hidden" name="cp.cpId" value="" id="cpId"/>
			<input type="hidden" name="whflag" value="-1" id="whflag"/>
			<input type="hidden" name="nowflag" value="-1" id="nowflag"/>
			<table class="m-table-form">
				<tbody>
					<tr height="40">
						<th class="tr">客户：</th>
						<td>
							<s:select name=""
							cssClass="u-ipt required validate-selection"
							cssStyle="width:178px"
							list="#request.cList"
							listKey="custId" listValue="custName" headerKey="-1"
							headerValue="--请选择--" id="customer"/>
						</td>
						
						<th class="tr" width="42%">华天产品编号：</th>
						<td><label id="htPn"></label></td>
					</tr>
					<tr height="40">
						
						<th class="tr" width="42%">客户产品编号：</th>
						<td>
							<select name="" id="cusPn" class="u-ipt required validate-selection" style="width:178px;"></select>
						</td>
						<th class="tr">车型/钢背应用：</th>
						<td><label id="appfor"></label></td>
					</tr>
					<tr height="40">
						
						<th class="tr">产品性质：</th>
						<td>
							<label id="properties"></label>
						</td>
						<th class="tr">PPAP状态：</th>
						<td>
							<label id="ppapStatus"></label>
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
							<input type="text" class="u-ipt "
							name="cp.weight" maxlength="13" id="sw"/>
							<label for="" id="swLa"></label>
						</td>
						<th class="tr">包装盒尺寸：</th>
						<td>
							<label id="packSize"></label>
						</td>
					</tr>
					<tr height="40">
						
						<th class="tr">库存片数：</th>
						<td>
							<label id="picCount"></label>
						</td>
						<th class="tr">总重量:</th>
						<td>
							<label id="weight"></label>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">所在仓库：</th>
						<td>
							<div id="whId">
								<s:select name=""
								cssClass="u-ipt required validate-selection"
								cssStyle="width:178px"
								list="#request.wList"
								listKey="warehouseId" listValue="sign1" headerKey="-1"
								headerValue="--请选择--" id="whselect"/>
							</div>
							<label id="whlabel"></label>
						</td>
						
						<th class="tr">入/出 库片数：</th>
						<td>
							<input type="text" class="u-ipt required validate-number"
							name="inOutNum" maxlength="13" id="IOInput"/>
						</td>
					</tr>
					<tr height="40">
						<th class="tr">批号：</th>
						<td>
							<input type="text" class="u-ipt required"
							name="cpn.batchCode" id="batchCode" maxlength="13" />
							<label for="" id="batchCodeLa"></label>
						</td>
						<th class="tr">入\出盒数：</th>
						<td>
							<input type="text" class="u-ipt required validate-number"
							name="boxNum" id="boxNum" maxlength="13" />
						</td>
					
					</tr>	
					<tr height="40">
						<th class="tr">当前盒数：</th>
						<td>
							<label id="boxNumLa"></label>
						</td>
						
						<th class="tr">备注：</th>
						<td>
							<input type="text" class="u-ipt "
							name="remark" maxlength="13" id="remark"/>
						</td>
					</tr>		
					<tr height="40">
						<th class="tr">操作时间：</th>
						<td>
							<input type="text" class="u-ipt "
							name="nowTime" maxlength="13" id="nowTime" style="width:160px;"/>
							<img onclick="WdatePicker({el:'nowTime'})" src="${pageContext.request.contextPath}/assets/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
						</td>
						
						<th class="tr">出入库盒号：</th>
						<td>
							<label for="" id="boxNoLa"></label>
						</td>
					</tr>		
				</tbody>
				<tfoot>
					<tr height="40">
						<td colspan="4" class="tc">
							入库<input type="radio" name="choice" id="in" value="in" checked="checked"/>
							&nbsp;&nbsp;&nbsp;&nbsp;
							出库<input type="radio" name="choice" id="out" value="out"/>
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
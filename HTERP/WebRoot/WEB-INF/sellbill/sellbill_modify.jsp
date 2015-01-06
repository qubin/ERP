<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.ecside.org" prefix="ec"%>
<jsp:include page="../top.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title></title>
<script type="text/javascript">
	var j = jQuery;
	var no = 0;
	
	
	window.onload=function initSellDetail(){
		var l = eval('${jsonList}');
		var temp = 0;
		var data = eval('${sdList}');
		for(var i = 0; i < data.length; i ++){
			no += 1;
			var str = "<tr>";
			str += 	"<td class='tr'><div align='center'>" + no + "</div></td>";
			str += 	"<td class='tr'>";
			str +=  "<select name='cpList' id='' class='u-ipt required validate-selection'><option value=''>-请选择-</option>";
			for(var j = 0; j < l.length; j ++){
				if(l[j].cpId == data[i].cpId){
					str += "<option value='" + l[j].cpId + "' selected='selected'>" + l[j].cus_pn + "</option>";
					temp = j;
				}else{
					str += "<option value='" + l[j].cpId + "'>" + l[j].cus_pn + "</option>";
				}
			}
			str +=  "</select>";
			str +=	"</td>";
			str +=  "<td class='tr'><div align='center'><label for='' name='cpn'>"+l[temp].code+"</label></div>";
			str +=	"</td>";
			str +=  "<td class='tr'><div align='center'>pic</div></td>";
			str +=  "<td class='tr'><input type='text' class='u-ipt required ' name='orderCount'";
			str +=	"maxlength='60' style='width:110px;' id='oc" + no +"' value='" + data[i].orderCount +"'></td>";
			str +=  "<td class='tr'><input type='text' class='u-ipt required ' name='price'";
			str +=	"maxlength='60' style='width:110px;' id='p" + no +"' value='" + data[i].price +"'></td>";
			str +=	"<td class='tr'><input type='text' class='u-ipt' name='amount'";
			str +=	"maxlength='60' style='width:110px;' id='a" + no +"' readonly='readonly' value='" + data[i].amount +"'></td>";		
			str +=	"<td class='tr'><input type='text' class='u-ipt' name='remark'";
			str +=	"maxlength='60' style='width:110px;' value='"+ data[i].remark +"'></td>";		
			str +=	"<input type='hidden' value='" + data[i].sellDetailId + "' name='sdId'/>";		
			str +=	"</tr>";
		}
		document.getElementById("add").innerHTML=str;
		
		bindCode2();
		document.getElementById("sdNum").value=no;
	}
	
	function bindCode2(){
		j(document).ready(function(){
			var tempL = eval('${jsonList}');
			var laL = j("label[name='cpn']")
			j("select[name='cpList']").each(function(i){
				j(this).bind("change",function(){
					for(var j = 0; j < tempL.length; j ++){
						if(j(this).val() == tempL[j].cpId){
							laL[i].innerHTML = tempL[j].code;
						}
					}
				
				});
			});
		});
	}
	
	j(document).ready(function(){
		
		
		j("#textReason").hide();
		if( ${sb.verifyStatus} == 2){
			j("#textReason").show();
		}
		
		j("#cust").val("${cust.custId}");
		
		
		
		function bindCalc(no){
			var oc = "#oc" + no;
			var p = "#p" + no;
			var a = "#a" + no;
			j(oc).bind("change",function(){
				var bar,foo = 0;
				if(j(oc).val() != ""){
					bar = parseInt(j(oc).val());
				}
				if(j(p).val() != ""){
					foo = parseInt(j(p).val());
				}
				j(a).val(bar * foo);
			});
			j(p).bind("change",function(){
				var bar,foo = 0;
				if(j(oc).val() != ""){
					bar = parseInt(j(oc).val());
				}
				if(j(p).val() != ""){
					foo = parseInt(j(p).val());
				}
				j(a).val(bar * foo);
			});
		}
		
		j("#addbtn").bind("click",function(){
			if(checkAdd()){
				init();
			}
		});
		
		function checkAdd(){
			var code = "#code" + no;
			var oc = "#oc" + no;
			var p = "#p" + no;
			
			if(j(code).val() != "" && j(oc).val() != "" && j(p).val() != ""){
				return true;
			}else{
				alert("请把当前记录填写完整！");
			}
		}
		
		
		function init(){
			no += 1;
			var data = eval('${jsonList}');
			var str = "<tr>";
				str += 	"<td class='tr'><div align='center'>" + no + "</div></td>";
				str += 	"<td class='tr'><select name='cpList' id='' class='u-ipt required validate-selection'><option value=''>-请选择-</option>";
				for(var i = 0; i < data.length; i ++){
					str += "<option value='"+data[i].cpId+"'>"+data[i].cus_pn+"</option>";
				}
				str +=  "</select>";
				str +=	"</td>";
				str +=  "<td class='tr'><div align='center'><label for='' name='cpn'></label></div></label>";
				str +=	"</td>";
				str +=  "<td class='tr'><div align='center'>pic</div></td>";
				str +=  "<td class='tr'><input type='text' class='u-ipt required ' name='orderCount'"; //
				str +=	"maxlength='60' style='width:110px;' id='oc" + no +"'></td>";
				str +=  "<td class='tr'><input type='text' class='u-ipt required ' name='price'";
				str +=	"maxlength='60' style='width:110px;' id='p" + no +"'></td>";
				str +=	"<td class='tr'><input type='text' class='u-ipt' name='amount'";
				str +=	"maxlength='60' style='width:110px;' id='a" + no +"' readonly='readonly'></td>";		
				str +=	"<td class='tr'><input type='text' class='u-ipt  ' name='remark'";
				str +=	"maxlength='60' style='width:110px;'></td>";		
				str +=	"</tr>";
				j("#sdNum").val(no);
			j("#selltable").append(str);
			j("input[name='orderCount']").each(function(i){
				j(this).bind("change",function(){
					var total = 0;
					j("input[name='orderCount']").each(function(i){
						if(j(this).val() != ""){
							total += parseInt(j(this).val());
						}
					});
					j("#total").html(total);
				});
			});
			bindCalc(no);
			bindCode();
		}
	
		
		j("#submitBtn").bind("click",function(){
			if(confirm("确定提交修改么")){
				var sbId = j("#sbId").val();
				var uri = "${pageContext.request.contextPath}/admin/sellbill/sellbill_checkModify.html?sb.sbId=" + sbId;
				j.get(uri,function(data){
					if(data != ""){
						if(data != "true"){
							alert("该订单已审核不可修改");
							return false;
						}
					}else{
						alert("订单不可修改");
						return false;
					}
				});
			}
			return false;
		});
		function bindCode(){
			var tempL = eval('${jsonList}');
			var laL = j("label[name='cpn']")
			j("select[name='cpList']").each(function(i){
				var temp = j(this);
				temp.bind("change",function(){
					for(var j = 0; j < tempL.length; j ++){
						if(temp.val() == tempL[j].cpId){
							laL[i].innerHTML = tempL[j].code;
						}
					}
				
				});
			});
		}
	});
</script>
</head>
<body>
	<!-- /#container -->
	<div id="container" class="container">
		<div class="hr10"></div>
		<div class="hr10"></div>
		<h2>修改订单</h2>
		<form id="form1"
			action="${pageContext.request.contextPath}/admin/sellbill/sellbill_modify.html"
			method="post">
			<input type="hidden" id="sbId" name="sb.sbId" value="${sb.sbId}"/>
		<input type="hidden" name="sdNum" value="" id="sdNum"/>
			<table class="m-table-form">
				<tbody>
					<tr>
						<th class="tr">订货编号:</th>
						<td><input type="text" class="u-ipt required" name="sb.code" value="${sb.code}"></td>
						<th class="tr">供货单位:</th>
						<td><label for="">江阴华天汽车零部件有限公司</label></td>
					</tr>
					<tr>
						<th class="tr">订货部门:</th>
						<td><label for="">${cust.custName}</label></td>
						<th class="tr">TEL:</th>
						<td><label for="">86559185</label></td>
					</tr>
					<tr height="40">
						<th class="tr">联系人:</th>
						<td><label id="custConPerson">${cust.custConPerson}</label></td>
						<th class="tr">FAX:</th>
						<td><label for="">86559185</label></td>
					</tr>
					<tr>
						<th class="tr">联系人电话:</th>
						<td><label id="custPhone">${cust.custPhone}</label></td>
						<th class="tr">订货日期:</th>
						<td><input type="text" class="u-ipt required"
							name="sb.orderData" id="d12" value="${sb.orderData}"> <img
							onclick="WdatePicker({el:'d12'})"
							src="${pageContext.request.contextPath}/assets/js/My97DatePicker/skin/datePicker.gif"
							width="16" height="22" align="absmiddle"></td>
					</tr>
				</tbody>
			</table>
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
			<input type="button" value="添加一行" class="u-btn" id="addbtn"
				style="float:right;" />
			<table class="m-table" id="selltable">
				<tbody id="">
					<tr>
						<th class="tr"><div align="center">NO</div></th>
						<th class="tr"><div align="center">编号</div></th>
						<th class="tr"><div align="center">CODE</div></th>
						<th class="tr"><div align="center">单位</div></th>
						<th class="tr"><div align="center">订单数量</div></th>
						<th class="tr"><div align="center">单价(含税)</div></th>
						<th class="tr"><div align="center">金额</div></th>
						<th class="tr"><div align="center">备注</div></th>
					</tr>
				<tr id="add">
				</tr>
				</tbody>
			</table>
			<table class="m-table" id="">
				<tbody>
					<tr height="40">
						<td class="tr" colspan="4"><div align="center">TOTAL:</div></td>
						<td class="tr"><div align="center" id="total"></div></td>
						<td class="tr"><div align="center"></div></td>
						<td class="tr"><div align="center"></div></td>
						<td class="tr"><div align="center"></div></td>
					</tr>
				</tbody>
			</table>

			<table width="937" height="143" border="1" class="m-table"
				style="margin-top:100px;">
				<tr>
					<td width="29" rowspan="6"><div align="center">定货条件</div></td>
					<td width="78"><div align="center">供货方</div></td>
					<td width="336">江阴华天汽车零部件有限公司</td>
					<td width="50">支付方式</td>
					<td width="410">
					<s:radio list="#{'0':'现金','1':'支票','2':'电汇'}" name="sb.payWay" value="sb.payWay"></s:radio>
					</td>
				</tr>
				<tr>
					<td><div align="center">供货日期</div></td>
					<td><input type="text" class="u-ipt required"
						name="sb.leadDate" id="d13" value="${sb.leadDate}"> <img
						onclick="WdatePicker({el:'d13'})"
						src="${pageContext.request.contextPath}/assets/js/My97DatePicker/skin/datePicker.gif"
						width="16" height="22" align="absmiddle"></td>
					<td>付款方式</td>
					<td>
					<s:radio list="#{'0':'款到发货','1':'货到付款','2':'定额支付'}" name="sb.payType" value="sb.payType"></s:radio>
					</td>
				</tr>
				<tr>
					<td><div align="center">检查方法</div></td>
					<td>
					<s:radio list="#{'0':'出差检查','1':'入库后检查','2':'无检查'}" name="sb.checkMothod" value="sb.checkMothod"></s:radio>
					</td>
					<td>材料支付</td>
					<td>
					<s:radio list="#{'0':'有（有偿，无偿）','1':'没有'}" name="sb.matPay" value="sb.matPay"></s:radio>
					</td>
				</tr>
				<tr>
					<td><div align="center">供货厂地</div></td>
					<td><input type="text" class="u-ipt"
						name="sb.supplyArea" id="" value="${sb.supplyArea}"></td>
					<td>产品单价</td>
					<td>
					<s:radio list="#{'0':'含税','1':'不含税'}" name="sb.pdctPrice" value="sb.pdctPrice"></s:radio>
					</td>
				</tr>
				<tr>
					<td><div align="center">运输费</div></td>
					<td>
					<s:radio list="#{'0':'包含','1':'不包含'}" name="sb.transFee" value="sb.transFee"></s:radio>
					</td>
					<td>质量证书</td>
					<td>
					<s:radio list="#{'0':'必要','1':'不必要'}" name="sb.qttCtfc" value="sb.qttCtfc"></s:radio>
					</td>
				</tr>
				<tr>
					<td><div align="center">协议书</div></td>
					<td>
					<s:radio list="#{'0':'有（总1份）','1':'没有'}" name="sb.argeeMent" value="sb.argeeMent"></s:radio>
					</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2"><div align="center">其他</div></td>
					<td colspan="3">&nbsp;</td>
				</tr>
			</table>
			<div align="center" style="margin-top:20px;" id="textReason">
				<h2 align="center">审核不通过原因</h2>
				<textarea name="" id="reason" cols="50" rows="30" class="u-ipt" style="width:320px;height:120px;" disabled>${sb.verifyRemark}</textarea>
			</div>
			<div align="center" style="margin-top:50px;"><input class="u-btn" type="submit" value="确认修改" id="submitBtn" />
			&nbsp;<input class="u-btn" type="button" onclick="javascript:history.back()" value="返回" /></div>
		</form>
	</div>
	<!-- /#container -->
	<!--javascript start-->
	<!--javascript end-->
</body>
</html>
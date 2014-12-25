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
	j(document).ready(function(){
		j("#refuse").bind("click",function(){
			var str = j("#reason").val();
			var sbId = j("#sbId").val();
			if(str != ""){
				window.location.href="${pageContext.request.contextPath}/admin/sellbill/sellbill_refuse.html?sb.sbId=" + sbId + "&reason=" + str;
			}else{
				alert("请输入不通过原因");
			}
		});
		
		j("#confirm").bind("click",function(){
			var sbId = j("#sbId").val();
			if(confirm("确认审核通过")){
				window.location.href="${pageContext.request.contextPath}/admin/sellbill/sellbill_pass.html?sb.sbId=" + sbId;
			}
		});
		
		j("input:radio").each(function(){
			j(this).attr("disabled","disabled");
		});
		
		j("#cust").val("${cust.custId}");
		
		initSellDetail();
		
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
			var cpn = "#cpn" + no;
			var oc = "#oc" + no;
			var p = "#p" + no;
			
			if(j(code).val() != "" && j(cpn).val() != "" && j(oc).val() != "" && j(p).val() != ""){
				return true;
			}else{
				alert("请把当前记录填写完整！");
			}
		}
		
		function initSellDetail(){
			var data =${sdList};
			for(var i = 0; i < data.length; i ++){
				no += 1;
				var str = "<tr height='40'>";
				str += 	"<td class='tr'><div align='center'>" + no + "</div></td>";
				str += 	"<td class='tr'><div align='center'>" + data[i].sdCode + "</div></label>";
				str +=	"</td>";
				str +=  "<td class='tr'><div align='center'>" + data[i].cpn + "</div>";
				str +=	"</td>";
				str +=  "<td class='tr'><div align='center'>pic</div></td>";
				str +=  "<td class='tr'><div align='center'>"+ data[i].orderCount +"</div>";
				str +=	"</td>";
				str +=  "<td class='tr'><div align='center'>" + data[i].price + "</div>";
				str +=	"</td>";
				str +=	"<td class='tr'><div align='center'>"+data[i].amount+"</div>";
				str +=	"</td>";		
				str +=	"<td class='tr'><div align='center'>"+  data[i].remark +"</div>";
				str +=	"</td>";		
				str +=	"<input type='hidden' value='" + data[i].sellDetailId + "' name='sdId'/>";		
				str +=	"</tr>";
				j("#selltable").append(str);
				j("#sdNum").val(no);
			}
		}
		
		function init(){
			no += 1;
			var str = "<tr>";
				str += 	"<td class='tr'><div align='center'>" + no + "</div></td>";
				str += 	"<td class='tr'><input type='text' class='u-ipt required ' name='sdCode'";
				str +=	"maxlength='60' style='width:110px;' id='code" + no +"'></td>";
				str +=  "<td class='tr'><input type='text' class='u-ipt required ' name='cpn'";
				str +=	"maxlength='60' style='width:110px;' id='cpn" + no +"'></td>";
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
		}
		
		j("#cust").bind("change",function(){
			var custId = j("#cust").val();
			var uri = "${pageContext.request.contextPath}/admin/sellbill/sellbill_findCust.html?cust.custId=" + custId;
			j.getJSON(uri,function(data){
				if(data != ""){
					j("#custConPerson").html(data.custConPerson);					
					j("#custPhone").html(data.custPhone);					
				}else{
					alert("该客户信息不完整！");
				}
			});
		});
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
					<tr height="40">
						<th class="tr">订货编号:</th>
						<td><label for="">${sb.code}</label></td>
						<th class="tr">供货单位:</th>
						<td><label for="">江阴华天汽车零部件有限公司</label></td>
					</tr>
					<tr height="40">
						<th class="tr">订货部门:</th>
						<td>
						<label for="">${cust.custName}</label>
						</td>
						<th class="tr">TEL:</th>
						<td><label for="">86559185</label></td>
					</tr>
					<tr height="40">
						<th class="tr">联系人:</th>
						<td><label id="custConPerson">${cust.custConPerson}</label></td>
						<th class="tr">FAX:</th>
						<td><label for="">86559185</label></td>
					</tr>
					<tr height="40">
						<th class="tr">联系人电话:</th>
						<td><label id="custPhone">${cust.custPhone}</label></td>
						<th class="tr">订货日期:</th>
						<td><label for="">${sb.orderData}</label></td>
					</tr>
				</tbody>
			</table>
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
			<table class="m-table" id="selltable">
				<tbody>
					<tr>
						<th class="tr"><div align="center">NO</div></th>
						<th class="tr"><div align="center">CODE</div></th>
						<th class="tr"><div align="center">型号</div></th>
						<th class="tr"><div align="center">单位</div></th>
						<th class="tr"><div align="center">订单数量</div></th>
						<th class="tr"><div align="center">单价(含税)</div></th>
						<th class="tr"><div align="center">金额</div></th>
						<th class="tr"><div align="center">备注</div></th>
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
					<s:radio list="#{'0':'现金','1':'支票','2':'电汇'}" name="sb.payWay" value="sb.payWay" ></s:radio>
					</td>
				</tr>
				<tr>
					<td><div align="center">供货日期</div></td>
					<td>
					<label for="">${sb.leadDate}</label>
					</td>
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
					<td><label for="">${sb.supplyArea}</label></td>
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
			
			<div align="center" style="margin-top:20px;">
				<h2 align="center">审核不通过原因</h2>
				<textarea name="" id="reason" cols="50" rows="30" class="u-ipt" style="width:320px;height:120px;">${sb.verifyRemark }</textarea>
			</div>
			<div align="center" style="margin-top:50px;">
			<input class="u-btn" type="button" value="审核通过" id="confirm" />
			<input class="u-btn" type="button" value="审核不通过" id="refuse" />
			&nbsp;<input class="u-btn" type="button" onclick="javascript:history.back()" value="返回" /></div>
		</form>
	</div>
	<!-- /#container -->
	<!--javascript start-->
	<!--javascript end-->
</body>
</html>
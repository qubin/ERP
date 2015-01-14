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
		init();
		
		function bindCalc(no){
			var oc = "#oc" + no;
			var p = "#p" + no;
			var a = "#a" + no;
			j(oc).bind("change",function(){
				var bar,foo = 0;
				if(j(oc).val() != ""){
					bar = parseFloat(j(oc).val());
				}
				if(j(p).val() != ""){
					foo = parseFloat(j(p).val());
				}
				j(a).val(bar * foo);
			});
			j(p).bind("change",function(){
				var bar,foo = 0;
				if(j(oc).val() != ""){
					bar = parseFloat(j(oc).val());
				}
				if(j(p).val() != ""){
					foo = parseFloat(j(p).val());
				}
				j(a).val(bar * foo);
			});
		}
		
		j("#addbtn").bind("click",function(){
			if(checkAdd()){
				init();
			}
			if(j("#cust").val() != ""){
				bindSelect();
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
			var str = "<tr>";
				str += 	"<td class='tr'><div align='center'>" + no + "</div></td>";
				str += 	"<td class='tr'><select name='cpList' id='' class='u-ipt required validate-selection'><option value=''>-请选择-</option></select>";
				str +=	"</td>";
				str +=  "<td class='tr'><div align='center'>pic</div></td>";
				str +=  "<td class='tr'><input type='text' class='u-ipt required ' name='orderCount'"; //
				str +=	"maxlength='60' style='width:110px;' id='oc" + no +"'></td>";
				str +=  "<td class='tr'><input type='text' class='u-ipt required ' name='price'";
				str +=	"maxlength='60' style='width:110px;' id='p" + no +"'></td>";
				str +=	"<td class='tr'><input type='text' class='u-ipt' name='amount'";
				str +=	"maxlength='60' style='width:110px;' id='a" + no +"' readonly='readonly'></td>";		
				str +=	"<td class='tr'><input type='text' class='u-ipt ' name='remark'";
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
			bindSelect();
		});
		
		function bindSelect(){
			var custId = j("#cust").val();
			var uri = "${pageContext.request.contextPath}/admin/sellbill/sellbill_findCP.html?cust.custId=" + custId;
			j.getJSON(uri,function(data){
				if(data != ""){
					j.each(j("select[name='cpList']"),function(k,v){
						v.innerHTML = "";
						var jtemp = j(v);
						jtemp.append("<option value=''>-请选择-</option>");
						j.each(data,function(k2,v2){
							jtemp.append("<option value='" + v2.cpId + "'>" + v2.cus_pn + "</option>");
						});
					});
				}else{
					alert("该客户没有对应成品");
					j.each(j("select[name='cpList']"),function(k,v){
						j(this).html("");
					});
				}
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
		<h2>录入订单</h2>
		<form id="form1"
			action="${pageContext.request.contextPath}/admin/sellbill/sellbill_add.html"
			method="post">
		<input type="hidden" name="sdNum" value="" id="sdNum"/>
			<table class="m-table-form">
				<tbody>
					<tr>
						<th class="tr">订货编号:</th>
						<td><input type="text" class="u-ipt required" name="sb.code"></td>
						<th class="tr">供货单位:</th>
						<td><label for="">江阴华天汽车零部件有限公司</label></td>
					</tr>
					<tr>
						<th class="tr">订货部门:</th>
						<td><s:select name="sb.custId"
								cssClass="u-ipt required validate-selection"
								cssStyle="width:178px" list="#request.cList" listKey="custId"
								listValue="custName" headerKey="-1" headerValue="--请选择--"
								id="cust" /></td>
						<th class="tr">TEL:</th>
						<td><label for="">86559185</label></td>
					</tr>
					<tr height="40">
						<th class="tr">联系人:</th>
						<td><label id="custConPerson"></label></td>
						<th class="tr">FAX:</th>
						<td><label for="">86559185</label></td>
					</tr>
					<tr>
						<th class="tr">联系人电话:</th>
						<td><label id="custPhone"></label></td>
						<th class="tr">订货日期:</th>
						<td><input type="text" class="u-ipt required"
							name="sb.orderData" id="d12"> <img
							onclick="WdatePicker({el:'d12'})"
							src="${pageContext.request.contextPath}/assets/js/My97DatePicker/skin/datePicker.gif"
							width="16" height="22" align="absmiddle"></td>
					</tr>
					<tr height="40">
						<th class="tr">备注:</th>
						<td>
							<input type="text" class="u-ipt" name="sb.remark">
						</td>
					</tr>
				</tbody>
			</table>
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
			<input type="button" value="添加一行" class="u-btn" id="addbtn"
				style="float:right;" />
			<table class="m-table" id="selltable">
				<tbody>
					<tr>
						<th class="tr"><div align="center">NO</div></th>
						<th class="tr"><div align="center">编号</div></th>
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
						<td class="tr" colspan="3"><div align="center">TOTAL:</div></td>
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
					<span><input type="radio" name="sb.payWay" class="" id="" value="0" />现金
					<input type="radio" name="sb.payWay" class="" id="" value="1" />
					支票
					<input type="radio" name="sb.payWay" class="" id="" value="2" /></span>
					电汇</td>
				</tr>
				<tr>
					<td><div align="center">供货日期</div></td>
					<td><input type="text" class="u-ipt required"
						name="sb.leadDate" id="d13"> <img
						onclick="WdatePicker({el:'d13'})"
						src="${pageContext.request.contextPath}/assets/js/My97DatePicker/skin/datePicker.gif"
						width="16" height="22" align="absmiddle"></td>
					<td>付款方式</td>
					<td><span><input type="radio" name="sb.payType"
							class="" id="" value="0" /> 款到发货
					<input type="radio" name="sb.payType"
							class="" id="" value="1" />
					货到付款 <input type="radio" name="sb.payType"
							class="" id="" value="2" />
					定额支付</span></td>
				</tr>
				<tr>
					<td><div align="center">检查方法</div></td>
					<td><span><input type="radio" name="sb.checkMothod"
							class="" id="radio" value="0" />
					出差检查<input type="radio" name="sb.checkMothod"
							class="" id="radio2" value="1" />
					入库后检查<input type="radio" name="sb.checkMothod"
							class="" id="radio3" value="2" />
					无检查</span></td>
					<td>材料支付</td>
					<td><span><input type="radio" name="sb.matPay"
							class="" id="" value="0" />
					有（有偿，无偿）<input type="radio" name="sb.matPay"
							class="" id="" value="1" />
					没有</span></td>
				</tr>
				<tr>
					<td><div align="center">供货厂地</div></td>
					<td><input type="text" class="u-ipt required"
						name="sb.supplyArea" id=""></td>
					<td>产品单价</td>
					<td><span><input type="radio" name="sb.pdctPrice"
							class="" id="" value="0" /> 含税
					<input type="radio" name="sb.pdctPrice"
							class="" id="" value="1" /> 不含税</span>
					</td>
				</tr>
				<tr>
					<td><div align="center">运输费</div></td>
					<td><span><input type="radio" name="sb.transFee"
							class="" id="" value="0" />包含
					<input type="radio" name="sb.transFee"
						class="" id="" value="1" /> 不包含</span></td>
					<td>质量证书</td>
					<td><span><input type="radio" name="sb.qttCtfc"
							class="" id="" value="0" /> 必要
					<input type="radio" name="sb.qttCtfc"
							class="" id="" value="1" />
					不必要</span></td>
				</tr>
				<tr>
					<td><div align="center">协议书</div></td>
					<td><span><input type="radio" name="sb.argeeMent"
							class="" id="" value="0" />有（总1份）
					<input type="radio" name="sb.argeeMent"
						class="" id="" value="1" />没有</span></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2"><div align="center">其他</div></td>
					<td colspan="3">&nbsp;</td>
				</tr>
			</table>
			<div align="center" style="margin-top:50px;"><input class="u-btn" type="submit" value="提交" id="submitBtn" /></div>
		</form>
	</div>
	<!-- /#container -->
	<!--javascript start-->
	<!--javascript end-->
</body>
</html>
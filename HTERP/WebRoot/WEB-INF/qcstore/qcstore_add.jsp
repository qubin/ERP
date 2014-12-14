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
       function ajax(obj){
	  		var url = "${pageContext.request.contextPath }/admin/supplier/supplier_checkCodeIsNull.html?supcode="+obj.value;
	  		new Ajax.Request(url,
	  	  	{
	  	  		method:'get',
	  	  		onSuccess: function(data){
	  	  			if(data.responseText == 'false'){
	  	  				document.getElementById("resText").innerHTML = "该编号有重复，请重新添加！";
	  	  				obj.focus();
	  	  			}else{
	  	  				document.getElementById("resText").innerHTML = "";
	  	  			}
	  	  		},
	  	  		onFailure: function(){ 
	  	  			document.getElementById("resText").innerHTML = "该编号有重复，请重新添加！"; 
	  	  			obj.focus();
	  	  		}
	  	  	});
	  	}
</script>
</head>
<body>
	<div id="container" class="container">
		<div class="hr10"></div>
		<form id="form1"
			action="${pageContext.request.contextPath }/admin/supplier/supplier_add.html"
			method="post">
			<table class="m-table-form">
				<tbody>
					<tr>
						<th class="tr" width="42%">编号：</th>
						<td><input type="text" class="u-ipt required" id="supCode" onblur="ajax(this)"
							name="supplier.code" maxlength="13" />
							<span style="color:red" id="resText"></span>
						</td>
					</tr>
					<tr>
						<th class="tr">名称：</th>
						<td><input type="text" class="u-ipt required"
							name="supplier.name" maxlength="60" /></td>
					</tr>
					<tr>
						<th class="tr">描述：</th>
						<td><input type="text" class="u-ipt" name="supplier.desc"
							maxlength="60"></td>
					</tr>
					<tr>
						<th class="tr">联系人：</th>
						<td><input type="text" class="u-ipt required" name="supplier.conPerson"
							maxlength="60"></td>
					</tr>
					<tr>
						<th class="tr">联系电话：</th>
						<td><input type="text" class="u-ipt required validate-tel-phone" name="supplier.conPhone"
							maxlength="20"></td>
					</tr>
					<tr>
						<th class="tr">传真：</th>
						<td><input type="text" class="u-ipt" name="supplier.fax"
							maxlength="20"></td>
					</tr>
					<tr>
						<th class="tr">地址：</th>
						<td><input type="text" class="u-ipt" name="supplier.address"
							maxlength="120"></td>
					</tr>
					<tr>
						<th class="tr">邮箱：</th>
						<td><input type="text" class="u-ipt validate-email" name="supplier.email"
							maxlength="30"></td>
					</tr>
					<tr>
						<th class="tr">备注：</th>
						<td><input type="text" class="u-ipt" name="supplier.remark"
							maxlength="120"></td>
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
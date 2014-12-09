<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.ecside.org" prefix="ec"%>
<jsp:include page="../top.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <title></title>
  <script type="text/javascript">
	function recoverMsg() {
  		if (confirm("您确定要恢复此信息吗？") == false) {
			return false;
		}
	}
	
	function delMsg(Id){
		if (confirm("您确定要恢复此信息吗？") == true) {
			var reason = prompt("请输入注销原因");
			if(reason != null && reason != ""){
				reason = encodeURI(reason);
				reason = encodeURI(reason);
				var href = "${pageContext.request.contextPath}/admin/customer/customer_delete.html?cust.custId=" + Id + "&cust.isLogout=1&cust.logOutReason=" + reason;
				window.location.href = href;
			}
		}	
	}
	jQuery(document).ready(function(){
		jQuery("#btn").bind("click",function(){
			alert("success");
		});
	});
  </script>
</head>
<body>
<input type="button" value="testbtn" id="btn" />
    <div id="container" class="container">
      <div class="hr10"></div>
          <div class="hr10"></div>
          <h2>客户查询</h2>
		 <form id="form1" action="${pageContext.request.contextPath}/admin/customer/customer_find.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th class="tr">查询类型：</th>
                  <td>
	                  <select name="serachType" id="serachStr" class="u-slt validation-passed">
	                  	<option value="all">全部</option>
	                  	<option value="name">名称</option>
	                  	<option value="telephone">联系电话</option>
	                  	<option value="fax">传真</option>
	                  	<option value="con_person">联系人</option>
	                  	<option value="con_phone">联系人电话</option>
	                  </select>
                  </td>  
                  <td><input type="text" class="u-ipt" name="queryStr"></td>       
                  <td>
					 <button type="submit" class="u-btn">查询</button>&emsp;                   
                     <button type="button" class="u-btn" onclick="javascript:window.location='${pageContext.request.contextPath}/admin/customer/customer_showAddUI.html'">新增</button>&emsp;           
                   </td>
                </tr>                				                           
             </tbody>
          </table>
          </form>   
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
			      
                   
          <h2>客户信息列表</h2>
          <div align="center">
          <ec:table items="custList" var="sr"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/customer/customer_showHome.html"
				rowsDisplayed='12' 
				pageSizeList="2,5,12,20,50,100,all"
				resizeColWidth="true" width="100%" listWidth="100%" height="600px"
				sortable="false" 
				useAjax="false" 
				style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="NAME" title="名称" width="11%" style="text-align:center"/>
					<ec:column property="CON_PHONE" title="联系电话" width="11%" style="text-align:center"/>
					<ec:column property="FAX" title="传真" width="11%" style="text-align:center"/>
					<ec:column property="CON_PERSON" title="联系人" width="11%" style="text-align:center"/>
					<ec:column property="CON_PHONE" title="联系人电话" width="11%" style="text-align:center"/>
					<ec:column property="TARIFF" title="税号" width="11%" style="text-align:center"/>
					<ec:column property="BANK" title="开户银行" width="11%" style="text-align:center"/>
					<ec:column property="ACCOUNT" title="银行账号" width="11%" style="text-align:center"/>
					<ec:column property="ADDRESS" title="单位地址" width="11%" style="text-align:center"/>
					<ec:column property="IS_LOGOUT" title="是否注销" width="11%" style="text-align:center" mappingItem="LOGOUT"/>
					<ec:column property="LOGOUT_REASON" title="注销原因" width="11%" style="text-align:center"/>
					<ec:column property="REMARK" title="备注" width="11%" style="text-align:center"/>
					<ec:column property="_0" title="动作" width="11%" style="text-align:center">
						<c:if test="${sr.IS_LOGOUT == 0}">
							<a href="${pageContext.request.contextPath}/admin/customer/customer_showModifyUI.html?cust.custId=${sr.ID}">修 改</a>
							&nbsp;&nbsp;
							<a onclick="return delMsg(${sr.ID})" href="javascript:void(0)">注 销</a>
						</c:if>
						<c:if test="${sr.IS_LOGOUT == 1}">
							<a onclick="return recoverMsg()" href="${pageContext.request.contextPath}/admin/customer/customer_delete.html?cust.custId=${sr.ID}&cust.isLogout=0">恢 复</a>
						</c:if>
						</ec:column>
				</ec:row>
			</ec:table>
		</div>
    </div><!-- /#container -->
  	<!--javascript start-->
  	<!--javascript end-->
</body>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
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
  	function delMsg(isLeaf) {
		if (confirm('您确定要注销此信息吗？') == false) {
			return false;
		} else {
			if (isLeaf == "0") {
				alert("非叶子节点不允许删除！");
				return false;
			}
		}
	}
  </script>
</head>
<body>
    <div id="container" class="container">
      <div class="hr10"></div>
          <div class="hr10"></div>
          <h2>客户查询</h2>
		 <form id="form1" action="${pageContext.request.contextPath}/admin/customer/customer_find.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th class="tr">供应商名称：</th>
                  <td>
	                  <select name="serachType" id="serachStr" class="u-slt validation-passed">
	                  	<option value="all"></option>
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
			      
                   
          <h2>客户商信息列表</h2>
          <div align="center">
          <ec:table items="custList" var="sr"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/customer/customer_showhome.html"
				rowsDisplayed='12' 
				pageSizeList="2,5,12,20,50,100,all"
				resizeColWidth="true" width="99%" listWidth="99%" height="600px"
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
					<ec:column property="REMARK" title="备注" width="11%" style="text-align:center"/>
					<ec:column property="_0" title="动作" width="11%" style="text-align:center">
						<a href="${pageContext.request.contextPath}/admin/customer/customer_showModifyUI.html?cust.custId=${sr.ID}">修 改</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a onclick="return delMsg(${org1.IS_LEAF})" href="${pageContext.request.contextPath}/admin/customer/customer_delete.html?cust.custId=${sr.ID}&cust.isLogout=1">注 销</a>
					</ec:column>
				</ec:row>
			</ec:table>
		</div>
    </div><!-- /#container -->
  	<!--javascript start-->
  	<!--javascript end-->
</body>
</html>
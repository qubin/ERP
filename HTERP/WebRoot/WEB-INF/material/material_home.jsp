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
          <h2>材料信息查询</h2>
		 <form id="form1" action="${pageContext.request.contextPath}/admin/supplier/supplier_find.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th class="tr">材料名称：</th>
                  <td><input type="text" class="u-ipt" name="supplier.name"></td>
                  <th class="tr">联系人：</th>
                  <td><input type="text" class="u-ipt" name="supplier.conPerson"></td>        
                  <td>
					 <button type="button" class="u-btn" onclick="javascript:document.getElementById('form1').submit()">查询</button>&emsp;                   
                     <button type="button" class="u-btn" onclick="javascript:window.location='${pageContext.request.contextPath}/admin/supplier/supplier_showAddUI.html'">新增</button>&emsp;           
                   </td>
                </tr>                				                           
             </tbody>
          </table>
          </form>   
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
			      
                   
          <h2>材料信息列表</h2>
          <div align="center">
          <ec:table items="supplierList" var="sr"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/supplier/supplier_find.html"
				rowsDisplayed='12' 
				pageSizeList="2,5,12,20,50,100,all"
				resizeColWidth="true" width="99%" listWidth="99%" height="600px"
				sortable="false" 
				useAjax="false" 
				style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="NAME" title="名称" width="11%" />
					<ec:column property="DESC1" title="描述" width="11%" />
					<ec:column property="CON_PERSON" title="联系人" width="11%" />
					<ec:column property="CON_PHONE" title="联系电话" width="11%" style="text-align:center"/>
					<ec:column property="FAX" title="传真" width="11%" style="text-align:center"/>
					<ec:column property="ADDRESS" title="地址" width="11%" />
					<ec:column property="EMAIL" title="邮箱" width="11%" />
					<ec:column property="REMARK" title="备注" width="11%" />
					<ec:column property="_0" title="动作" width="11%" style="text-align:center">
						<a href="${pageContext.request.contextPath}/admin/supplier/supplier_showModifyUI.html?supplier.uuid=${sr.ID}">修 改</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a onclick="return delMsg(${org1.IS_LEAF})" href="${pageContext.request.contextPath}/admin/supplier/supplier_delete.html?supplier.uuid=${sr.ID}&supplier.isLogout=1">注 销</a>
					</ec:column>
				</ec:row>
			</ec:table>
		</div>
    </div><!-- /#container -->
  	<!--javascript start-->
  	<!--javascript end-->
</body>
</html>
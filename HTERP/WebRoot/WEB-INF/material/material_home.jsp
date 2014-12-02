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
		 <form id="form1" action="${pageContext.request.contextPath}/admin/material/material_find.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th class="tr">供应商：</th>
                  <td>
                  	   <s:select name="material.supplyId"
						  cssClass="u-ipt required"
						  cssStyle="width:178px"
					      list="#request.supplierList"
					      listKey="uuid" listValue="name" headerKey="-1"
					      headerValue="--查询所有--" />
                  </td>            
                  <th class="tr">规格：</th>
                  <td><input type="text" class="u-ipt" name="material.standard"></td>        
                  <td>
					 <button type="button" class="u-btn" onclick="javascript:document.getElementById('form1').submit()">查询</button>&emsp;                   
                     <button type="button" class="u-btn" onclick="javascript:window.location='${pageContext.request.contextPath}/admin/material/material_showAddUI.html'">新增</button>&emsp;           
                   </td>
                </tr>                				                           
             </tbody>
          </table>
          </form>   
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
			      
                   
          <h2>材料信息列表</h2>
          <div align="center">
          <ec:table items="materialList" var="sr"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/material/material_find.html"
				rowsDisplayed='12' 
				pageSizeList="2,5,12,20,50,100,all"
				resizeColWidth="true" width="99%" listWidth="99%" height="600px"
				sortable="false" 
				useAjax="false" 
				style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="WAREHOUSESIGN" title="所在仓库" width="11%" />
					<ec:column property="SUPPLIERNAME" title="供应商" width="11%" />
					<ec:column property="DENSITY" title="面密度" width="11%" style="text-align:right" />
					<ec:column property="THICKNESS" title="厚度" width="11%" style="text-align:right"/>
					<ec:column property="DESC1" title="描述" width="11%" />
					<ec:column property="WEIGHT" title="重量" width="11%"  style="text-align:right"/>
					<ec:column property="STANDARD" title="规格" width="11%"  style="text-align:center"/>
					<ec:column property="SCROLL_COUNT" title="卷数" width="11%"  style="text-align:right"/>
					<ec:column property="MMAT_ID" title="母卷编号" width="11%" />
					<ec:column property="REMARK" title="备注" width="11%" />
					<ec:column property="_0" title="动作" width="11%" style="text-align:center">
						<a href="${pageContext.request.contextPath}/admin/material/material_showModifyUI.html?material.uuid=${sr.ID}">修 改</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a onclick="return delMsg(${org1.IS_LEAF})" href="${pageContext.request.contextPath}/admin/material/material_delete.html?material.uuid=${sr.ID}&material.isLogout=1">注 销</a>
					</ec:column>
				</ec:row>
			</ec:table>
		</div>
    </div><!-- /#container -->
  	<!--javascript start-->
  	<!--javascript end-->
</body>
</html>
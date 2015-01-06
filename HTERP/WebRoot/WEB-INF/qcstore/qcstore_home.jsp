<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.ecside.org" prefix="ec"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../top.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <title></title>
  <script type="text/javascript">
  	function delMsg(id) {
		if (!confirm('您确定要将此QC移除隔离库吗？')) {
			return false;
		}
	}
  </script>
</head>
<body>
    <div id="container" class="container">
      <div class="hr10"></div>
          <div class="hr10"></div>
          <h2>QC隔离库查询</h2>
		 <form id="form1" action="${pageContext.request.contextPath}/admin/qcstore/qcstore_find.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th width="100">
                  	查询条件：
                  </th>
                  <td width="97">
                  	<input type="radio" value="1" name="qcstore.matOrPdct">&nbsp;材料&nbsp;&nbsp;&nbsp;
                  	<input type="radio" value="2" name="qcstore.matOrPdct">&nbsp;成品
                  </td>
                  <td width="10">
                  	<select class="u-ipt" name="qcstore_key">
                  		<option value="ALL">---查询所有---</option>
                  		<option value="MAT_PDCT_HT">华天(材料/成品)编号</option>
                  		<option value="ISOLATE_REASON">隔离原因</option>
                  	</select>
                  </td>
                  <th width="50"><input type="text" class="u-ipt" name="qcstore_name"/></th>
                  <td>
					 <button type="button" class="u-btn" onclick="javascript:document.getElementById('form1').submit()">查询</button>&emsp;                   
                     <button type="button" class="u-btn" onclick="javascript:window.location='${pageContext.request.contextPath}/admin/qcstore/qcstore_showAddUI.html'">新增</button>&emsp;           
                   </td>
                </tr>                				                           
             </tbody>
          </table>
          </form>   
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
			      
                   
          <h2>QC隔离库信息列表</h2>
          <div align="center">
          <ec:table items="qcStoreList" var="sr"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/qcstore/qcstore_find.html"
				rowsDisplayed='12' 
				pageSizeList="2,5,12,20,50,100,all"
				resizeColWidth="true" width="100%" listWidth="100%" height="600px"
				sortable="false" 
				useAjax="false" 
				style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="MAT_OR_PDCT" title="材料/成品" width="11%" mappingItem="qcTypeMap" style="text-align:center"/>
					<ec:column property="MAT_PDCT_HT" title="华天(材料/产品)编号" width="11%" style="text-align:center"/>
					<ec:column property="WEIGHT" title="QC重量" width="11%" style="text-align:right"/>
					<ec:column property="PIC_COUNT" title="QC数量" width="11%" style="text-align:right"/>
					<ec:column property="ISOLATE_REASON" title="隔离原因" width="11%"/>
					<ec:column property="_0" title="动作" width="11%" style="text-align:center">
						<a onclick="return delMsg(${sr.ID})" href="${pageContext.request.contextPath}/admin/qcstore/qcstore_delete.html?qcstore.uuid=${sr.ID}" style="color:blue;text-decoration:underline;">转出隔离库</a>
					</ec:column>
				</ec:row>
			</ec:table>
		</div>
    </div><!-- /#container -->
  	<!--javascript start-->
  	<!--javascript end-->
</body>
</html>
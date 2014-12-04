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
	function delMsg(id,isLogout) {
  		if(isLogout == 0){
			if (confirm('您确定要注销此信息吗？')) {
				var str=prompt("注销原因？","");
				if(str!=null && str!=""){
					var h = "${pageContext.request.contextPath}/admin/product/product_delete.html?product.uuid="+id+"&product.isLogout=1&product.logoutReason="+str;
					window.location.href=h;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			if (confirm('您确定要恢复此信息吗？')) {
				var h = "${pageContext.request.contextPath}/admin/product/product_delete.html?product.uuid="+id+"&product.isLogout=0&product.logoutReason=' '";
				window.location.href=h;
			}
		}
	}
  </script>
</head>
<body>
    <div id="container" class="container">
      <div class="hr10"></div>
          <div class="hr10"></div>
          <h2>产品查询</h2>
		 <form id="form1" action="${pageContext.request.contextPath}/admin/product/product_find.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th width="100">
                  	查询条件：
                  </th>
                  <td width="10">
                  	<select class="u-ipt" name="product_key">
                  		<option value="ALL">---查询所有---</option>
                  		<option value="HT_PN">华天产品编号</option>
                  		<option value="CUS_PN">客户产品编号</option>
                  		<option value="SIGN1">所在仓库</option>
                  		<option value="CUSNAME">客户</option>
                  	</select>
                  </td>
                  <th width="50"><input type="text" class="u-ipt" name="product_name"/></th>
                  <td>
					 <button type="button" class="u-btn" onclick="javascript:document.getElementById('form1').submit()">查询</button>&emsp;                   
                     <button type="button" class="u-btn" onclick="javascript:window.location='${pageContext.request.contextPath}/admin/product/product_showAddUI.html'">新增</button>&emsp;           
                   </td>
                </tr>                				                           
             </tbody>
          </table>
          </form>   
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
                   
          <h2>产品信息列表</h2>
          <div align="center">
          <ec:table items="productList" var="sr"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/product/product_find.html"
				rowsDisplayed='12' 
				pageSizeList="2,5,12,20,50,100,all"
				resizeColWidth="true" width="100%" listWidth="100%" height="600px"
				sortable="false" 
				useAjax="false" 
				style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="HT_PN" title="华天产品编号" width="10%" style="text-align:center"/>
					<ec:column property="CUS_PN" title="客户产品编号" width="10%" style="text-align:center"/>
					<ec:column property="SIGN1" title="所在仓库" width="10%" />
					<ec:column property="CUSNAME" title="客户" width="10%" />
					<ec:column property="PATTERN_TYPE" title="模具类型" width="10%" />
					<ec:column property="PIC_COUNT" title="库存片数" width="10%" />
					<ec:column property="IS_LOGOUT" title="是否注销" width="10%" mappingItem="logoutMap"/>
					<ec:column property="LOGOUT_REASON" title="注销原因" width="10%" />
					<ec:column property="_0" title="动作" width="10%" style="text-align:center">
						<a href="${pageContext.request.contextPath}/admin/product/product_showDetailUI.html?product.uuid=${sr.ID}">详情</a>
						&nbsp;&nbsp;&nbsp;
						<c:if test="${sr.IS_LOGOUT==0}">
							<a href="${pageContext.request.contextPath}/admin/product/product_showModifyUI.html?product.uuid=${sr.ID}">修 改</a>
							&nbsp;&nbsp;&nbsp;
						</c:if>
						<a onclick="return delMsg(${sr.ID},${sr.IS_LOGOUT})" href="#">
							<c:if test="${sr.IS_LOGOUT==0}">
								注 销
							</c:if>
							<c:if test="${sr.IS_LOGOUT==1 }">
								恢复
							</c:if>
						</a>
					</ec:column>
				</ec:row>
			</ec:table>
		</div>
    </div><!-- /#container -->
  	<!--javascript start-->
  	<!--javascript end-->
</body>
</html>
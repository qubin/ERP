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
					var h = "${pageContext.request.contextPath}/admin/production/production_delete.html?production.uuid="+id+"&production.isLogout=1&production.logoutReason="+str;
					window.location.href=h;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			if (confirm('您确定要恢复此信息吗？')) {
				var h = "${pageContext.request.contextPath}/admin/production/production_delete.html?production.uuid="+id+"&production.isLogout=0&production.logoutReason=' '";
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
          <h2>销售单信息查询</h2>
		 <form id="form1" action="${pageContext.request.contextPath}/admin/production/production_find.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th width="100">
                  	查询条件：
                  </th>
                  <td width="10">
                  	<select class="u-ipt" name="production_key">
                  		<option value="ALL">---查询所有---</option>
                  		<option value="CODE">供应商</option>
                  		<option value="ORDER_DATE">面密度</option>
                  		<option value="LEAD_DATE">厚度</option>
                  		<option value="SUPPLY_AREA">描述</option>
                  	</select>
                  </td>
                  <th width="50"><input type="text" class="u-ipt" name="production_name"/></th>        
                  <td>
					 <button type="button" class="u-btn" onclick="javascript:document.getElementById('form1').submit()">查询</button>&emsp;                   
                   </td>
                </tr>                				                           
             </tbody>
          </table>
          </form>   
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
			      
                   
          <h2>销售单信息列表</h2>
          <div align="center">
          <ec:table items="pdceistctList" var="sr"
				retrieveRowsCallback="limit"
				action="${pageContext.request.contextPath}/admin/production/production_find.html"
				rowsDisplayed='12' 
				pageSizeList="2,5,12,20,50,100,all"
				resizeColWidth="true" width="100%" listWidth="100%" height="600px"
				sortable="false" 
				useAjax="false" 
				style="align:center"
				autoIncludeParameters="true">
				<ec:row>
					<ec:column property="CODE" title="订货编号" width="11%" style="text-align:center" />
					<ec:column property="ORDER_DATE" title="订货日期" width="11%" style="text-align:center"/>
					<ec:column property="LEAD_DATE" title="供货日期" width="11%" style="text-align:center"/>
					<ec:column property="SUPPLY_AREA" title="供货场地" width="11%"/>
					<ec:column property="REMARK" title="备注" width="11%"/>
					<ec:column property="_0" title="动作" width="11%" style="text-align:center">
						<a style="color:blue;text-decoration:underline;" href="${pageContext.request.contextPath}/admin/production/production_showAddUI.html?pdceistct.uuid=${sr.ID}">转指令单</a>
					</ec:column>
				</ec:row>
			</ec:table>
		</div>
    </div><!-- /#container -->
  	<!--javascript start-->
  	<!--javascript end-->
</body>
</html>
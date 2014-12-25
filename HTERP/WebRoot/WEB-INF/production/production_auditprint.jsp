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
          <h2>指令单信息查询</h2>
		 <form id="form1" action="${pageContext.request.contextPath}/admin/production/production_findAuditPrint.html" method="post">
          <table class="m-table-form">
             <tbody>
                <tr>
                  <th width="100">
                  	查询条件：
                  </th>
                  <td width="10">
                  	<select class="u-ipt" name="pdceistct_key">
                  		<option value="ALL">---查询所有---</option>
                  		<option value="CODE">订单号</option>
                  		<option value="CUS_PDCT_NO">产品型号</option>
                  		<option value="START_DATE">生产开始日期</option>
                  	</select>
                  </td>
                  <th width="50"><input type="text" class="u-ipt" name="pdceistct_name"/></th>        
                  <td>
					 <button type="button" class="u-btn" onclick="javascript:document.getElementById('form1').submit()">查询</button>&emsp;                   
                   </td>
                </tr>                				                           
             </tbody>
          </table>
          </form>   
			<div class="hr10">&nbsp;</div>
			<div class="hr10"></div>
			      
                   
          <h2>指令单信息列表</h2>
          <div align="center">
          <ec:table items="sczldList" var="sr"
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
					<ec:column property="VERIFY_STATUS" title="状态" width="11%" style="text-align:center" mappingItem="logoutMap"/>
					<ec:column property="CODE" title="订单号" width="11%"/>
					<ec:column property="CUS_PDCT_NO" title="产品型号" width="11%"/>
					<ec:column property="START_DATE" title="生产开始日期" width="11%" style="text-align:center"/>
					<ec:column property="PDCE_COUNT" title="生产数量" width="11%" style="text-align:center"/>
					<ec:column property="USED_MAT_WEIGHT" title="消耗原材料数量" width="11%"/>
					<ec:column property="_0" title="动作" width="11%" style="text-align:center">
						<a style="color:blue;text-decoration:underline;" href="${pageContext.request.contextPath}/admin/production/production_showAudit.html?pdceistct.uuid=${sr.ID}">预&nbsp;&nbsp;览</a>
					</ec:column>
				</ec:row>
			</ec:table>
		</div>
    </div><!-- /#container -->
  	<!--javascript start-->
  	<!--javascript end-->
</body>
</html>
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
	
  </script>
</head>
<body>
    <div id="container" class="container">
     	<div align="center" style="margin-top:200px;">
     	<a href="${pageContext.request.contextPath}/admin/stock/stock_rowList.html"><input type="button" value="原材料查询" class="u-btn" style="width:100px;height:50px;"/></a>
     	<a href="${pageContext.request.contextPath}/admin/stock/stock_prodList.html">	<input type="button" value="成品查询" class="u-btn" style="width:100px;height:50px;"/></a>
     	</div>
    </div><!-- /#container -->
  	<!--javascript start-->
  	<!--javascript end-->
</body>
</html>
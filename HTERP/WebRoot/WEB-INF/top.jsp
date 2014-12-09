<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.ecside.org" prefix="ec"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">	
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/easyui.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/font-awesome.min.css"/>
    <!--[if IE 7]>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/font-awesome-ie7.min.css"/>
    <![endif]-->
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/base.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/style.css"/>
    
    <link rel="stylesheet" type="text/css" href='${pageContext.request.contextPath}/ecside/css/ecside_style.css' />
	<script type="text/javascript" src='${pageContext.request.contextPath}/ecside/js/prototype_mini.js'></script>
	<script type="text/javascript" src='${pageContext.request.contextPath}/ecside/js/ecside_msg_utf8_cn.js'></script>
	<script type="text/javascript" src='${pageContext.request.contextPath}/ecside/js/ecside.js'></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/libs/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/libs/jquery.easyui.custom.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery.easyui.extend.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery.plugins.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery.syadmin.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery.syadmin.js"></script>
    <script language="JavaScript" src="${pageContext.request.contextPath}/assets/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/js/validation/prototype.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/assets/js/validation/effects.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/assets/js/validation/validation_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/assets/js/myUtils.js" type="text/javascript"></script>
	 <script type="text/javascript"> 
	 	$.noconflict();
		window.onload = function(){   
			//  form1 为 表单的名称   
			if(document.getElementById('form1')!=null){
	    		var validator = new Validation('form1', {stopOnFirst:true, immediate:true}); 
			}
		}
	</script>
<!-- 加上此段样式，它的显示格式有改变  -->
<STYLE type=text/css>
BODY TD {
	FONT-SIZE: 9pt; COLOR: #333; FONT-FAMILY: Arial, Helvetica, sans-serif
}
.validation-advice {
	PADDING-RIGHT: 0px; DISPLAY: inline; PADDING-LEFT: 0px; FONT-WEIGHT: bold; PADDING-BOTTOM: 0px; MARGIN: 0px 0px 0px 10px; COLOR: #ff3300; PADDING-TOP: 0px
}
</STYLE>
<script type="text/javascript" language="JavaScript">   
               
   </script> 
</head>

<body>


</body>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>  

		<title>HelloStruts2</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>

	<body>
		<center>
			<font size="6" color="red">您无权访问该资源！请重新登录</font>
		</center>
		<form name=loading>
			<P align=center>
				<FONT face=Arial color=#0066ff size=2><br> <br> <br>
					跳转至登陆页面...</FONT>
				<INPUT
					style="PADDING-RIGHT: 0px; PADDING-LEFT: 0px; FONT-WEIGHT: bolder; PADDING-BOTTOM: 0px; COLOR: #0066ff; BORDER-TOP-style: none; PADDING-TOP: 0px; FONT-FAMILY: Arial; BORDER-RIGHT-style: none; BORDER-LEFT-style: none; BACKGROUND-COLOR: white; BORDER-BOTTOM-style: none"
					size=46 name=chart>
				<BR>
				<INPUT
					style="BORDER-RIGHT: medium none; BORDER-TOP: medium none; BORDER-LEFT: medium none; COLOR: #0066ff; BORDER-BOTTOM: medium none; TEXT-ALIGN: center"
					size=47 name=percent>
				<script language="javascript"> 
var bar=0 ;
var line="||" ;
var amount="||" ;
count() ;
function count(){ 
    bar=bar+2 ;
    amount =amount + line ;
    document.loading.chart.value=amount ;
    document.loading.percent.value=bar+"%" ;
    if (bar<99){
        setTimeout("count()",10);
    }else{
        window.top.location.href = "${pageContext.request.contextPath}/"; 
    } 
}
</script>
			</P>
		</form>
		<br>
	</body>
</html>

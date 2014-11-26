<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 6]><html class="ie6 lte9 lte8 lte7" lang="zh-CN"><![endif]-->
<!--[if IE 8]><html class="ie8 lte9 lte8" lang="zh-CN"><![endif]-->
<!--[if IE 9]><html class="ie9 lte9" lang="zh-CN"><![endif]-->
<!--[if IE 7]><html class="ie7 lte9 lte8 lte7" lang="zh-CN"><![endif]-->
<!--[if !(IE 6) | !(IE 7) | !(IE 8) | !(IE 9)  ]><!--><html lang="zh-CN"><!--<![endif]-->
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <title></title>
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css"/>
  <!--[if IE 6]>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/libs/dd_belatedpng.js" ></script>
	<script type="text/javascript">
	DD_belatedPNG.fix('.main-nav li,.quick-nav li,.slider-nav,.login-box,.slider-bottom-nav');
	</script>
  <![endif]-->
</head>
<body>
<div id="top-bar">
	<div id="top-bar-inner" class="group">
		<h1 class="fl logo">进销存管理信息系统</h1>
		<ul class="fr quick-nav group">
		</ul>
	</div>
</div><!-- /#top-bar -->
<div id="header">
	<div id="banner-list" class="slider">
    <img src="${pageContext.request.contextPath}/assets/images/login/pic.png" width="600" height="480" hspace="170" align="absmiddle">
    <p>&nbsp;</p>
	  <p>&nbsp;</p>
	</div>
</div><!-- /#header -->
<div id="content">
	<div class="login-box">
		<div class="inner group">
			<form action="${pageContext.request.contextPath }/login.html" method="post">				
				<p><input type="text" id="username" name="user.userLoginId" value="用户名" onfocus="if(this.value=='用户名'){this.value='';}" onblur="if(this.value==''){this.value='用户名';}"/></p>
				<p><input type="password" id="password" name="user.password1" value="密码" onfocus="if(this.value=='密码'){this.value='';}" onblur="if(this.value==''){this.value='密码';}"/></p>
				<!--<p><input type="text" id="captchacode" name="captchacode" value="验证码" onfocus="if(this.value=='验证码'){this.value='';}" onblur="if(this.value==''){this.value='验证码';}"/><span class="captcha"><img src="assets/images/login/code.jpg" alt="" width="76" height="31"/></span></p>-->
				<p><button class="btn" ><span>登录</span></button></p>				
			</form>
		</div>
	</div><!-- /.login-box -->
</div><!-- /.content -->
<!--
<div id="footer" class="group">
	<div align="right">
		<p class="fr copyright">
		<a href="#">忘记ID或密码？</a>&emsp;</p>
	</div>
</div>--><!-- #footer -->
<!-- javascrpt start -->
<script src="${pageContext.request.contextPath}/assets/js/libs/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/js/sider.js" type="text/javascript"></script>
<!-- javascrpt end -->

</body>
</html>
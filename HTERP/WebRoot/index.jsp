<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<!--[if IE 6]><html class="ie6 lte9 lte8 lte7" lang="zh-CN"><![endif]-->
<!--[if IE 8]><html class="ie8 lte9 lte8" lang="zh-CN"><![endif]-->
<!--[if IE 9]><html class="ie9 lte9" lang="zh-CN"><![endif]-->
<!--[if IE 7]><html class="ie7 lte9 lte8 lte7" lang="zh-CN"><![endif]-->
<!--[if !(IE 6) | !(IE 7) | !(IE 8) | !(IE 9)  ]><!--><html lang="zh-CN"><!--<![endif]-->
<head>
	<meta charset="utf-8"/>           
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<!--[if IE 6]>
	<meta http-equiv="refresh" content="0;url=ie6.html" />
	<![endif]-->
	<title>进销存系统管理信息系统</title>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/easyui.css"/>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/base.css"/>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/style.css"/>
</head>
<body id="home" class="easyui-layout bg">
		<!--<div id="preloader">
			<div id="status">页面加载中...</div>
		</div>--><!-- /#preloader -->
		<div id="header" data-options="region:'north',border:false">
			<div id="header-inner">
				<h1 class="logo">进销存系统管理信息系统</span></h1>		
				<ul class="top-nav group">
					<li><a href="javascript:;" title="帮助"><i class="icon icon-question-sign"></i><span>帮助</span></a></li>
					<li><a href="javascript:;" title="退出" onclick="quitLogin()"><i class="icon icon-signout"></i><span>退出</span></a></li>
				</ul>
			</div><!-- /#header-inner -->
		</div><!-- /#header -->   
		<div id="sidebar" data-options="region:'west',split:false, border:false">
			<div class="sidebar-wrapper">
				<div class="side-options">
                    <span id="timedate">2014-01-17 星期五</span>
		            <a title="隐藏/显示 导航菜单" href="javascript:;" id="sidebar-toggler" class=""><span></span></a>
		        </div>
				<div class="mini-profile group">
					<div class="mini-user-avatar">
						<a href="#" title="欢迎您，${user.userLoginId}!">
							<img class="img-circle" alt="头像" src="${pageContext.request.contextPath }/assets/images/avatar.jpg" width="32" height="32">
						</a>
					</div>
					<div class="mini-user-info">
						${loggedUser.userLoginId} <i class="icon icon-caret-down"></i>
					</div>
					<div class="mini-profile-options">
						<div class="user-info-cnt">
							欢迎您，${loggedUser.userLoginId}! 
						</div>
						<!--<ul class="mini-user-nav">
							<li><a title="系统设置" href="javascript:;"><i class="icon icon-cog"></i></a></li>
							<li><a title="查看用户" href="javascript:;"><i class="icon icon-user"></i></a></li>
							<li><a title="查看首页" href="javascript:;"><i class="icon icon-home"></i></a></li>
							<li><a title="退出系统" href="javascript:;" onclick="quitLogin()"><i class="icon icon-signout"></i></a></li>
						</ul>-->
					</div>
				</div>
				<div id="mainnav">
					<div id="navlist-wraper">
						<ul id="navlist" class="nav nav-list">
						<s:iterator value="#session.loggedUser.funcTreeMap" id="root">
<%--						<li class="current hasSub">--%>
							<s:if test="key.resourceOrder == 2">
							<li class="current hasSub">
							</s:if>
							<s:else>
							<li class="hasSub">
							</s:else>
								<a href="javascript:;" data-nbid="${key.resourceOrder}" title="${key.resourceTitle}" <s:if test='key.resourceOrder == 2'>
							 		class="expand"
								</s:if>
								<s:else>
									class="notExpand"
								</s:else>>
									<i class="icon icon-xxgl"></i>
									<span class="txt">${key.resourceTitle}</span>
									<span class="more"><i class="icon-caret-up"></i></span>
								</a>
								
								<s:if test="key.resourceOrder == 2">
							<ul class="sub-nav" style="display: block;">
							</s:if>
							<s:else>
							<ul class="sub-nav" >
							</s:else>
								<s:iterator value="#root.value">
									<li>
										<a  href="javascript:;" data-url="${pageContext.request.contextPath }/${resourceUrl }">
											<i class="icon icon-circle"></i>
											<span class="txt">${resourceTitle}</span>
										</a>
									</li>
								</s:iterator> 
								</ul> 
							</li>
						</s:iterator>	
						</ul><!-- /.nav-list -->
					</div>
					<div id="nav-list-pan" class="fn-usn"></div>
				</div><!-- /.mainnav -->

			</div>
<%--			<div class="sidebar-quickmenu">--%>
<%--				<h2>快捷方式</h2>--%>
<%--				<ul class="hide">--%>
<%--					<li class="sqm1"><a href="#">11</a></li>--%>
<%--					<li class="sqm2"><a href="#">22</a></li>--%>
<%--					<li class="sqm3"><a href="#">33</a></li>--%>
<%--					<li class="sqm4"><a href="#">44</a></li>--%>
<%--					<li class="sqm5"><a href="#">55</a></li>--%>
<%--					<li class="sqm6"><a href="#">66</a></li>--%>
<%--				</ul>--%>
<%--			</div>--%>
		</div><!-- /#sidebar -->
		<div id="content" data-options="region:'center',border:false">
			<div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
				<div data-options="title:'我的工作台'">
<%--					<div id="iframe-wraper"><iframe name="mainFrame" id="mainFrame" src="${pageContext.request.contextPath}/main.html" frameborder="0" scrolling="auto" width="100%" height="100%"></iframe></div>--%>
				</div>
			</div>
		</div><!-- /#content -->
		<div id="footer" data-options="region:'south',border:false">
			<div id="footer-inner"></div><!-- /#footer-inner -->
		</div><!-- /#footer -->

		<!--javascript start-->
		<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/libs/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/libs/jquery.easyui.custom.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery.easyui.extend.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery.plugins.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery.syadmin.js"></script>
		<script type="text/javascript">
			function quitLogin(){
				$.messager.confirm('系统提示', '确认退出当前系统？', function(data){
					if(data){
						window.location.href="${pageContext.request.contextPath}/logout.html";
					}
				});
			}

			//测试dialog
			function emailDialog(){
				$('#email-dialog').dialog({  
					title: '邮件',  
					width: 500,  
					height: 300, 
					shadow:false,
					closed: false,  
					cache: false,  
					href: 'test.html', 
					modal: true
				});
			}
			
		</script>
		<!--javascript end-->

</body>
</html>
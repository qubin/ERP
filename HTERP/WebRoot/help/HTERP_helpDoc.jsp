<%@ page language="java" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib uri="/struts-tags"
prefix="s"%> <%@ taglib uri="http://www.ecside.org" prefix="ec"%>

<!DOCTYPE html>
<html lang="zh-CN" xmlns:o="urn:schemas-microsoft-com:office:office"
	xmlns:w="urn:schemas-microsoft-com:office:word"
	xmlns="http://www.w3.org/TR/REC-html40">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<script language="javascript" src="${pageContext.request.contextPath}/assets/js/LodopFuncs.js"></script>
<title>华天ERP系统帮助文档</title>
<style>
@font-face {
	font-family: "Times New Roman";
}

@font-face {
	font-family: "宋体";
}

@font-face {
	font-family: "Wingdings";
}

@font-face {
	font-family: "Arial";
}

@font-face {
	font-family: "黑体";
}

@
list l0:level1 {
	mso-level-number-format: decimal;
	mso-level-suffix: none;
	mso-level-text: "%1.";
	mso-level-tab-stop: none;
	mso-level-number-position: left;
	margin-left: 0.0000pt;
	text-indent: 0.0000pt;
	font-family: 'Times New Roman';
}

@
list l1:level1 {
	mso-level-start-at: 2;
	mso-level-number-format: chinese-counting;
	mso-level-suffix: none;
	mso-level-text: "%1．";
	mso-level-tab-stop: none;
	mso-level-number-position: left;
	margin-left: 0.0000pt;
	text-indent: 0.0000pt;
	font-family: 'Times New Roman';
}

@
list l1:level2 {
	mso-level-number-format: alpha-lower;
	mso-level-suffix: tab;
	mso-level-text: "%2)";
	mso-level-tab-stop: 42.0000pt;
	mso-level-number-position: left;
	margin-left: 42.0000pt;
	text-indent: -21.0000pt;
	font-family: 'Times New Roman';
}

@
list l1:level3 {
	mso-level-number-format: lower-roman;
	mso-level-suffix: tab;
	mso-level-text: "%3.";
	mso-level-tab-stop: 63.0000pt;
	mso-level-number-position: left;
	margin-left: 63.0000pt;
	text-indent: -21.0000pt;
	font-family: 'Times New Roman';
}

@
list l1:level4 {
	mso-level-number-format: decimal;
	mso-level-suffix: tab;
	mso-level-text: "%4.";
	mso-level-tab-stop: 84.0000pt;
	mso-level-number-position: left;
	margin-left: 84.0000pt;
	text-indent: -21.0000pt;
	font-family: 'Times New Roman';
}

@
list l1:level5 {
	mso-level-number-format: alpha-lower;
	mso-level-suffix: tab;
	mso-level-text: "%5)";
	mso-level-tab-stop: 105.0000pt;
	mso-level-number-position: left;
	margin-left: 105.0000pt;
	text-indent: -21.0000pt;
	font-family: 'Times New Roman';
}

@
list l1:level6 {
	mso-level-number-format: lower-roman;
	mso-level-suffix: tab;
	mso-level-text: "%6.";
	mso-level-tab-stop: 126.0000pt;
	mso-level-number-position: left;
	margin-left: 126.0000pt;
	text-indent: -21.0000pt;
	font-family: 'Times New Roman';
}

@
list l1:level7 {
	mso-level-number-format: decimal;
	mso-level-suffix: tab;
	mso-level-text: "%7.";
	mso-level-tab-stop: 147.0000pt;
	mso-level-number-position: left;
	margin-left: 147.0000pt;
	text-indent: -21.0000pt;
	font-family: 'Times New Roman';
}

@
list l1:level8 {
	mso-level-number-format: alpha-lower;
	mso-level-suffix: tab;
	mso-level-text: "%8)";
	mso-level-tab-stop: 168.0000pt;
	mso-level-number-position: left;
	margin-left: 168.0000pt;
	text-indent: -21.0000pt;
	font-family: 'Times New Roman';
}

@
list l1:level9 {
	mso-level-number-format: lower-roman;
	mso-level-suffix: tab;
	mso-level-text: "%9.";
	mso-level-tab-stop: 189.0000pt;
	mso-level-number-position: left;
	margin-left: 189.0000pt;
	text-indent: -21.0000pt;
	font-family: 'Times New Roman';
}

@
list l2:level1 {
	mso-level-number-format: decimal;
	mso-level-suffix: tab;
	mso-level-text: "%1.";
	mso-level-tab-stop: 39.0000pt;
	mso-level-number-position: left;
	margin-left: 39.0000pt;
	text-indent: -18.0000pt;
	font-family: 'Times New Roman';
}

@
list l3:level1 {
	mso-level-number-format: bullet;
	mso-level-suffix: tab;
	mso-level-text: \F06C;
	mso-level-tab-stop: 81.0000pt;
	mso-level-number-position: left;
	margin-left: 81.0000pt;
	text-indent: -18.0000pt;
	font-family: Wingdings;
}

@
list l4:level1 {
	mso-level-number-format: decimal;
	mso-level-suffix: tab;
	mso-level-text: "%1.";
	mso-level-tab-stop: 18.0000pt;
	mso-level-number-position: left;
	margin-left: 18.0000pt;
	text-indent: -18.0000pt;
	font-family: 'Times New Roman';
}

@
list l5:level1 {
	mso-level-number-format: bullet;
	mso-level-suffix: tab;
	mso-level-text: \F06C;
	mso-level-tab-stop: 18.0000pt;
	mso-level-number-position: left;
	margin-left: 18.0000pt;
	text-indent: -18.0000pt;
	font-family: Wingdings;
}

@
list l6:level1 {
	mso-level-number-format: bullet;
	mso-level-suffix: tab;
	mso-level-text: \F06C;
	mso-level-tab-stop: 60.0000pt;
	mso-level-number-position: left;
	margin-left: 60.0000pt;
	text-indent: -18.0000pt;
	font-family: Wingdings;
}

@
list l7:level1 {
	mso-level-number-format: decimal;
	mso-level-suffix: tab;
	mso-level-text: "%1.";
	mso-level-tab-stop: 60.0000pt;
	mso-level-number-position: left;
	margin-left: 60.0000pt;
	text-indent: -18.0000pt;
	font-family: 'Times New Roman';
}

@
list l8:level1 {
	mso-level-number-format: bullet;
	mso-level-suffix: tab;
	mso-level-text: \F06C;
	mso-level-tab-stop: 39.0000pt;
	mso-level-number-position: left;
	margin-left: 39.0000pt;
	text-indent: -18.0000pt;
	font-family: Wingdings;
}

@
list l9:level1 {
	mso-level-number-format: bullet;
	mso-level-suffix: tab;
	mso-level-text: \F06C;
	mso-level-tab-stop: 102.0000pt;
	mso-level-number-position: left;
	margin-left: 102.0000pt;
	text-indent: -18.0000pt;
	font-family: Wingdings;
}

@
list l10:level1 {
	mso-level-number-format: decimal;
	mso-level-suffix: tab;
	mso-level-text: "%1.";
	mso-level-tab-stop: 81.0000pt;
	mso-level-number-position: left;
	margin-left: 81.0000pt;
	text-indent: -18.0000pt;
	font-family: 'Times New Roman';
}

@
list l11:level1 {
	mso-level-number-format: decimal;
	mso-level-suffix: tab;
	mso-level-text: "%1.";
	mso-level-tab-stop: 102.0000pt;
	mso-level-number-position: left;
	margin-left: 102.0000pt;
	text-indent: -18.0000pt;
	font-family: 'Times New Roman';
}

p.MsoNormal {
	mso-style-name: 正文;
	mso-style-parent: "";
	margin: 0pt;
	margin-bottom: .0001pt;
	mso-pagination: none;
	text-align: justify;
	text-justify: inter-ideograph;
	font-family: 'Times New Roman';
	mso-fareast-font-family: 宋体;
	font-size: 10.5000pt;
	mso-font-kerning: 1.0000pt;
}

h2 {
	mso-style-name: "标题 2";
	mso-style-noshow: yes;
	mso-style-next: 正文;
	margin-top: 13.0000pt;
	margin-bottom: 13.0000pt;
	mso-para-margin-top: 0.0000gd;
	mso-para-margin-bottom: 0.0000gd;
	page-break-after: avoid;
	mso-pagination: lines-together;
	text-align: justify;
	text-justify: inter-ideograph;
	mso-outline-level: 2;
	line-height: 172%;
	font-family: Arial;
	mso-fareast-font-family: 黑体;
	font-weight: bold;
	font-size: 16.0000pt;
	mso-font-kerning: 1.0000pt;
}

span.10 {
	font-family: 'Times New Roman';
}

span.15 {
	font-family: 'Times New Roman';
	vertical-align: super;
}

p.MsoFootnoteText {
	mso-style-name: 脚注文本;
	mso-style-noshow: yes;
	margin: 0pt;
	margin-bottom: .0001pt;
	layout-grid-mode: char;
	mso-pagination: none;
	text-align: left;
	font-family: 'Times New Roman';
	mso-fareast-font-family: 宋体;
	font-size: 9.0000pt;
	mso-font-kerning: 1.0000pt;
}

p.MsoHeader {
	mso-style-name: 页眉;
	margin: 0pt;
	margin-bottom: .0001pt;
	border-top: none;;
	mso-border-top-alt: none;;
	border-right: none;;
	mso-border-right-alt: none;;
	border-bottom: none;;
	mso-border-bottom-alt: none;;
	border-left: none;;
	mso-border-left-alt: none;;
	padding: 1pt 4pt 1pt 4pt;
	layout-grid-mode: char;
	mso-pagination: none;
	text-align: justify;
	text-justify: inter-ideograph;
	font-family: 'Times New Roman';
	mso-fareast-font-family: 宋体;
	font-size: 9.0000pt;
	mso-font-kerning: 1.0000pt;
}

p.MsoFooter {
	mso-style-name: 页脚;
	margin: 0pt;
	margin-bottom: .0001pt;
	layout-grid-mode: char;
	mso-pagination: none;
	text-align: left;
	font-family: 'Times New Roman';
	mso-fareast-font-family: 宋体;
	font-size: 9.0000pt;
	mso-font-kerning: 1.0000pt;
}

span.msoIns {
	mso-style-type: export-only;
	mso-style-name: "";
	text-decoration: underline;
	text-underline: single;
	color: blue;
}

span.msoDel {
	mso-style-type: export-only;
	mso-style-name: "";
	text-decoration: line-through;
	color: red;
}

table.MsoNormalTable {
	mso-style-name: 普通表格;
	mso-style-parent: "";
	mso-style-noshow: yes;
	mso-tstyle-rowband-size: 0;
	mso-tstyle-colband-size: 0;
	mso-padding-alt: 0.0000pt 5.4000pt 0.0000pt 5.4000pt;
	mso-para-margin: 0pt;
	mso-para-margin-bottom: .0001pt;
	mso-pagination: widow-orphan;
	font-family: 'Times New Roman';
	font-size: 10.0000pt;
	mso-ansi-language: #0400;
	mso-fareast-language: #0400;
	mso-bidi-language: #0400;
}

@page {
	mso-page-border-surround-header: no;
	mso-page-border-surround-footer: no;
}

@page Section0 {
	margin-top: 72.0000pt;
	margin-bottom: 72.0000pt;
	margin-left: 90.0000pt;
	margin-right: 90.0000pt;
	size: 595.3000pt 841.9000pt;
	layout-grid: 15.6000pt;
}

div.Section0 {
	page: Section0;
}
</style>


</head>
<body style="tab-interval:21pt; text-justify-trim:punctuation; ">
	<!--StartFragment-->
	<div class="Section0" style="layout-grid:15.6000pt; ">
		<h2 style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:黑体; mso-ascii-font-family:Arial; mso-hansi-font-family:Arial; font-weight:bold; font-size:16.0000pt; mso-font-kerning:1.0000pt; ">华天<font
				face="Arial">ERP</font><font face="黑体">系统使用帮助</font></span><span
				style="mso-spacerun:'yes'; font-family:黑体; mso-ascii-font-family:Arial; mso-hansi-font-family:Arial; font-weight:bold; font-size:16.0000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</h2>
		<p class=MsoNormal>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:bold; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">一．使用流程说明：</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:bold; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p></span>
		</p>
		<p class=MsoNormal>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">基本信息&nbsp;<font
				face="Times New Roman">-----&#62;&nbsp;</font><font face="宋体">库存&nbsp;</font><font
				face="Times New Roman">-----&#62;&nbsp;</font><font face="宋体">销售&nbsp;</font><font
				face="Times New Roman">-----&#62;&nbsp;</font><font face="宋体">生产&nbsp;</font><font
				face="Times New Roman">-----&#62;&nbsp;</font><font face="宋体">财务（结算）</font></span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p></span>
		</p>
		<p class=MsoNormal>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; color:rgb(255,0,0); font-size:10.5000pt; mso-font-kerning:1.0000pt; ">*<font
				face="宋体">注：</font></span><span
				style="mso-spacerun:'yes'; font-family:宋体; color:rgb(255,0,0); font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal style="mso-list:l0 level1 lfo1; ">
			<![if !supportLists]>
			<span
				style="font-family:宋体; color:rgb(255,0,0); font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>1.</span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; color:rgb(255,0,0); font-size:10.5000pt; mso-font-kerning:1.0000pt; ">初期使用系统时必须仔细完成基本信息中基础数据的录入，确保基础数据的正确完整。</span><span
				style="mso-spacerun:'yes'; font-family:宋体; color:rgb(255,0,0); font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal style="mso-list:l0 level1 lfo1; ">
			<![if !supportLists]>
			<span
				style="font-family:宋体; color:rgb(255,0,0); font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>2.</span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; color:rgb(255,0,0); font-size:10.5000pt; mso-font-kerning:1.0000pt; ">系统使用必须按照以上流程来操作，不能跨级使用，否则无法操作（如：必须有入库的材料等才能进行销售，销售单生成以后才能进行生产操作）</span><span
				style="mso-spacerun:'yes'; font-family:宋体; color:rgb(255,0,0); font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; color:rgb(255,0,0); font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p></span>
		</p>
		<p class=MsoNormal style="mso-list:l1 level1 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:宋体; font-weight:bold; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>二．</span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:bold; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">模块使用说明：</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:bold; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:bold; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:42.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level2 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>a)<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">基本信息：为了使用时只需关注业务，可在首次使用系统时一次性录入所有需要的基础数据。</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:42.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level2 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>b)<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">库存：</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>i.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">原料出入库</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>ii.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">原料出入库记录</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>iii.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">转库：</span><span
				style="mso-spacerun:'yes'; font-family:宋体; color:rgb(255,0,0); font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">注意分为&#8220;材料转库&#8221;和&#8220;成品转库&#8221;。</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>iv.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">分卷：将材料大卷分成小卷。操作时选择母卷<font
				face="Times New Roman">ID</font><font face="宋体">自动获取母卷描述和供应商卷号（母卷出入库时所填描述和卷号），再选择要分卷的材料型号，输入裁剪卷数和裁剪尺寸及公差，如需分为多卷只需点击右上角&#8220;增加行&#8221;，填写完毕后保存即可。</font></span><span
				style="mso-spacerun:'yes'; font-family:宋体; color:rgb(255,0,0); font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">注意必须有材料的出入库操作，且库存中存在母卷材料。</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>v.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">成品出入库</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>vi.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">成品出入库记录</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>vii.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">QC<font
				face="宋体">隔离库：手动添加报废材料或报废成品，也可进行转出隔离库。</font></span><span
				style="mso-spacerun:'yes'; font-family:宋体; color:rgb(255,0,0); font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">注意分为&#8220;材料隔离&#8221;和&#8220;成品隔离&#8221;。</span><span
				style="mso-spacerun:'yes'; font-family:宋体; color:rgb(255,0,0); font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>viii.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">材料短溢处理：手动调节材料库存数量。</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>ix.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">库存查询：</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>x.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">库存警示：设置材料或成品警示下限数量，如果库存低于警示下限则会在系统主页（我的工作台）显示该材料或成品。</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:42.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level2 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>c)<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">销售：</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>i.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">录入订单</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>ii.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">修改订单</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>iii.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">审核订单</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>iv.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">订单查询<font
				face="Times New Roman">-</font><font face="宋体">打印</font></span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>v.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">结单</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:42.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level2 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>d)<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">生产：</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>i.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">指令单录入：将销售单转为生产单。</span><span
				style="mso-spacerun:'yes'; font-family:宋体; color:rgb(255,0,0); font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">注意必须存在审核后的销售单。</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>ii.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">审核<font
				face="Times New Roman">-</font><font face="宋体">打印：打印并审核生产单（指令单录入中所提交的单据）。</font></span><span
				style="mso-spacerun:'yes'; font-family:宋体; color:rgb(255,0,0); font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">注意单据信息，不通过时该单据退回至指令单录入，通过时该单据进入结单状态，无法对此信息进行操作。</span><span
				style="mso-spacerun:'yes'; font-family:宋体; color:rgb(255,0,0); font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>iii.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">结单：生产完成后（来源于审核通过后指令单），填写完成产品的汇总信息，确认信息无误后结单。</span><span
				style="mso-spacerun:'yes'; font-family:宋体; color:rgb(255,0,0); font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">注意结单前先确认所填写信息的无误。</span><span
				style="mso-spacerun:'yes'; font-family:宋体; color:rgb(255,0,0); font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>iv.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">已结单单据查询：查询已结单单据信息。</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:42.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level2 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>e)<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">财务：</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>i.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">录入：为已审核销售单支付款项。</span><span
				style="mso-spacerun:'yes'; font-family:宋体; color:rgb(255,0,0); font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">注意必须存在已审核的销售单。</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>ii.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">审核：确认付款的金额是否正确，确认好后提交即可。</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>iii.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">查询：查询已审核的付款单据信息。</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>iv.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">统计：查询已审核的付款单据信息并统计出所查询出所有单据的统计金额。</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:42.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level2 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>f)<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">权限：</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>i.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">角色管理：<font
				face="Times New Roman">&#60;</font><font face="宋体">权</font><font
				face="Times New Roman">&#62;</font><font face="宋体">公司有</font><font
				face="Times New Roman">10</font><font face="宋体">个层级。</font><font
				face="Times New Roman">10</font><font face="宋体">代表老总，</font><font
				face="Times New Roman">10</font><font face="宋体">可以有最大权限；</font><font
				face="Times New Roman">20</font><font face="宋体">代表副总，他有相关的权限，但是不能查看老总的任何数据，以此类推。</font></span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>ii.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">用户管理：保存完初始密码为&#8220;<font
				face="Times New Roman">123456</font><font face="宋体">&#8221;。</font></span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:63.0000pt; mso-para-margin-left:0.0000gd; text-indent:-21.0000pt; mso-char-indent-count:0.0000; mso-list:l1 level3 lfo2; ">
			<![if !supportLists]>
			<span
				style="font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><span
				style='mso-list:Ignore; '>iii.<span>&nbsp;</span></span></span>
			<![endif]>
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">密码修改：修改当前已登录用户密码。</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p></span>
		</p>
		<p class=MsoNormal
			style="margin-left:21.0000pt; mso-para-margin-left:0.0000gd; ">
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:normal; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p></span>
		</p>
		<p class=MsoNormal style="mso-para-margin-left:0.0000gd; ">
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:bold; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p></span>
		</p>
	</div>
	<!--EndFragment-->
	<!-- /#container -->
  	<!--javascript start-->
  	<!--javascript end-->
</body>
</html>
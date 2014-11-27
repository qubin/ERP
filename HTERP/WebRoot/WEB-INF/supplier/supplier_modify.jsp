<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="../top.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>课程信息添加</title>
<script type="text/javascript">

</script>
</head>

<body class="ContentBody">
	<form action="${pageContext.request.contextPath}/admin/course/course_modify.html"
		method="post" name="form1" id="form1">
		<input type="hidden" id="course.uuid" name="course.uuid" value="${course.uuid }" />
		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">课程修改页面</th>
				</tr>
				<tr>
					<td class="CPanel">
						<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
							<tr>
								<td width="100%">
									<fieldset style="height:100%;">
										<legend>修改课程信息</legend>
										<table border="0" cellpadding="2" cellspacing="1" style="width:100%">
											<tr>
												<td align="right" width="19%">名称:</td>
												<td width="27%">
													<input name="course.name" class="text required" style="width:154px" maxlength="20" value="${course.name }" />
												</td>
											</tr>
											<tr>
												<td align="right" width="19%">描述:</td>
												<td width="27%">
													<input name="course.desc" class="text required" style="width:154px" maxlength="20" value="${course.desc }" />
												</td>
											</tr>
											<tr>
												<td align="right" width="19%">所属年级:</td>
												<td width="27%">
													<input type="hidden" name="courseGrade.courseId" value="${courseGrade.courseId}" />
													<s:select id="courseGrade.gradeId" name="courseGrade.gradeId"
															  cssStyle="width:154px"
														      list="#request.gradeList"
														      listKey="UUID" listValue="NAME1" headerKey="-1"
														      headerValue="--请选择--" />
												</td>
											</tr>
										</table>
										<br />
									</fieldset>
								</TD>
							</TR>

						</TABLE>


					</td>
				</tr>
				<TR>
					<TD colspan="2" align="center" height="50px"><input
						type="submit" name="submit" value="保 存" class="button" /> <input
						type="button" name="Submit2" value="返 回" class="button"
						onclick="window.history.go(-1);" /></TD>
				</TR>
			</TABLE>

			</td>
			</tr>
			</table>

		</div>
	</form>
</body>
</html>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.ecside.org" prefix="ec"%>
<jsp:include page="../top.jsp"></jsp:include>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <title></title>
  <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/findUserRole.js'></script> 
  <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>  
  <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
  <script type="text/javascript">
	function isSingleUser(userId) {
		findUserRole.getExistUserCount(userId, null, function(data) {
			if (data > 0) {
				document.getElementById("notSingle").style.display = "";
				document.getElementById("submitBtn").disabled = "disabled";
			} else {
				document.getElementById("notSingle").style.display = "none";
				document.getElementById("submitBtn").disabled = "";
			}
		});
	}
  </script>
</head>
<body>
    <div id="container" class="container">
    <div class="hr10"></div>   
    <form id="form1" action="${pageContext.request.contextPath }/admin/basic/user_add.html" method="post">  
      <table class="m-table-form">
         <tbody>
            <tr>  
              <th class="tr">用户名称：</th>
              <td><input type="text" class="u-ipt required" name="user.userName" maxlength="20"><font color="red">*</font></td>
            </tr>
            <tr>  
              <th class="tr">用户登录名：</th>
              <td><input type="text" class="u-ipt required" name="user.userId" maxlength="6"
              onblur="isSingleUser(this.value)"><font color="red">*</font>
              <span id="notSingle" style="display : none">用户ID已经存在</span></td>                                   
            </tr> 
            <tr>
              <th class="tr">联系电话：</th>
              <td><input type="text" class="u-ipt validate-number" name="user.contactPhone" maxlength="15"></td>
            </tr>
            <tr>  
              <th class="tr">角色名称：</th>
              <td>
                 <s:select name="roleUuids" list="#session.roleList" listKey="roleUuid" 
             	 	listValue="roleName" cssClass="u-slt required validate-selection" cssStyle="width:21%" headerKey="-1"
             	 	headerValue="--请选择--"/>
             	 <font color="red">*</font>
             </td>
            </tr>                       
            <tr>  
              <th class="tr">备注：</th>
              <td><input type="text" class="u-ipt" name="user.remark" maxlength="60"></td>                                   
            </tr>
         </tbody> 
         <tfoot>
               <tr>
                 <td colspan="2" class="tc">
                   <button type="submit" class="u-btn" id="submitBtn">提交</button>
                   &emsp;
                   <button type="reset" class="u-btn" onclick="javascript:history.back()">返回</button>
                 </td>
               </tr>
         </tfoot>                          
      </table>
     </form> 
    </div><!-- /#container -->
</body>
</html>
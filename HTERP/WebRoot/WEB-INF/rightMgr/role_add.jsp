<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
	function isSingleRole(roleName) {
		if (roleName == null || roleName == "") {
			return;
		}
		
		findUserRole.getExistRoleCount(roleName, null, function(data) {
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
    <form id="form1" action="${pageContext.request.contextPath }/admin/basic/role_add.html" method="post">    
      <table class="m-table-form">
         <tbody>
            <tr>  
              <th class="tr">角色名称：</th>
              <td><input type="text" class="u-ipt required" id="2" name="role.roleName" maxlength="13"
               onblur="isSingleRole(this.value)">
              <span id="notSingle" style="display : none">角色名称已经存在</span><font color="red">*</font></td>
            </tr>
            <tr>  
              <th class="tr">角色权：</th>
              <td>
				<s:select list="#request.quotaList" name="role.roleQuota" cssClass="u-slt"/>              
              </td>                                   
            </tr>                       
            <tr>  
              <th class="tr">备注：</th>
              <td><input type="text" class="u-ipt" name="role.remark" maxlength="60"></td>                                   
            </tr>
         </tbody> 
         <tfoot>
               <tr>
                 <td colspan="2" class="tc">
                 	<input class="u-btn" type="submit" value="提交" id="submitBtn"/>
                   &emsp;
					<input class="u-btn" type="button" onclick="javascript:history.back()" value="返回"/>
                 </td>
               </tr>
             </tfoot>                          
      </table>
      </form>
    </div><!-- /#container -->
    </body>
</body>
</html>
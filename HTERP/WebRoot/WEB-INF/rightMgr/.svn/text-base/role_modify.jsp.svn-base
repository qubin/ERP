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
</head>
<body>
    <div id="container" class="container">
    <div class="hr10"></div> 
    <form action="${pageContext.request.contextPath}/admin/basic/role_modify.html"	id="form1" method="post">    
      <table class="m-table-form">
         <tbody>
         	<tr>
              <th class="tr">角色编号：</th>
              <td><input type="text" class="u-ipt required validate-number" value="${role.roleId}" name="role.roleId" maxlength="10"><font color="red">*</font>
				  <input type="hidden" name="role.roleUuid" value="${role.roleUuid}"/>              
              </td>
            </tr>
            <tr>  
              <th class="tr">角色名称：</th>
              <td><input type="text" class="u-ipt required " value="${role.roleName}" name="role.roleName" maxlength="13"
              onblur="isSingleRole(this.value, '${role.roleUuid}')">
              <span id="notSingle" style="display : none">角色名称已经存在</span><font color="red">*</font></td>
            </tr>  
              <th class="tr">最大优惠金额：</th>
              <td><input type="text" class="u-ipt required validate-currency-dollar" value="${role.maxMinsMoney}" name="role.maxMinsMoney" maxlength="6">元</td>                                   
            </tr>                       
            </tr>  
              <th class="tr">备注：</th>
              <td><input type="text" class="u-ipt" value="${role.remark}" name="role.remark" maxlength="60"></td>                                   
            </tr>
             </tr>  
              <th class="tr">是否注销：</th>
              <td><s:checkbox name="role.isLogout" onclick="cascadeReason()" id="isLogout"/></td>                                   
            </tr>                       
            </tr>  
              <th class="tr">注销原因：</th>
              <td><input type="text" class="u-ipt" id="logoutReason" name="role.logoutReason" value="${role.logoutReason}"
              maxlength="60"
              	<s:if test='role.isLogout!="false"'>
              	   disabled="disabled"
              	</s:if>
              ></td>                                   
            </tr>
         </tbody> 
         <tfoot>
               <tr>
                 <td colspan="2" class="tc">
                   <button type="submit" class="u-btn" id="submitBtn">修改</button>
                   &emsp;
                   <button type="button" class="u-btn" onclick="javascript:history.back()">返回</button>
                 </td>
               </tr>
             </tfoot>                          
      </table>
    </form>
    </div><!-- /#container -->
  	<!--javascript start-->
  	<script type="text/javascript">
  		function cascadeReason() {
  			var status = jQuery("#isLogout").attr("checked");
  			var reason = jQuery("#logoutReason");
  			if (status == "checked") {
  				reason.attr("disabled", false);
  			} else {
  				reason.attr("disabled", true);
  			}
  		}
  		
  		function isSingleRole(roleName, roleUuid) {
			findUserRole.getExistRoleCount(roleName, roleUuid, function(data) {
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
</body>
</html>
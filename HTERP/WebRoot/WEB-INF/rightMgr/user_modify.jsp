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
  	function cascadeReason() {
		var status = jQuery("#isLogout").attr("checked");
		var reason = jQuery("#logoutReason");
		if (status == "checked") {
			reason.attr("disabled", false);
		} else {
			reason.attr("disabled", true);
		}
  	}
  	
  	function isSingleUser(userId, userCode) {
		findUserRole.getExistUserCount(userId, userCode, function(data) {
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
    <form action="${pageContext.request.contextPath}/admin/basic/user_modify.html"	id="form1" method="post">    
      <table class="m-table-form">
         <tbody>
            <tr>  
              <th class="tr">用户名称：</th>
              <td><input type="text" class="u-ipt required" name="user.userName" value="${user.userName}" maxlength="20"><font color="red">*</font></td>
              	  <input type="hidden"  value="${user.userCode}" name="user.userCode">
            </tr> 
            <tr> 
              <th class="tr">用户登录名：</th>
              <td><input type="text" class="u-ipt required" name="user.userId" value="${user.userId}" maxlength="6"
              onblur="isSingleUser(this.value, '${user.userCode}')"><font color="red">*</font>
              <span id="notSingle" style="display : none">用户ID已经存在</span></td>                                   
            </tr> 
            <tr>
              <th class="tr">联系电话：</th>
              <td><input type="text" class="u-ipt validate-number" name="user.contactPhone" value="${user.contactPhone}" maxlength="15"></td>
            </tr>
            <tr>  
              <th class="tr">角色名称：</th>
              <td>
              <s:if test="user.userCode == #session.loggedUser.userCode">
              	<s:select name="roleUuids" list="#session.roleList" listKey="roleUuid" 
             	 	listValue="roleName" cssClass="u-slt" cssStyle="width:21%" headerKey="-1"
             	 	headerValue="--请选择--" disabled="true"/>
              </s:if>
              <s:else>
              	<s:select name="roleUuids" list="#session.roleList" listKey="roleUuid" 
             	 	listValue="roleName" cssClass="u-slt required validate-selection" cssStyle="width:21%" headerKey="-1"
             	 	headerValue="--请选择--" />
              </s:else>	 	
             	 <font color="red">*</font>	
             	 
              </td>                                   
            </tr>                       
            <tr>  
              <th class="tr">备注：</th>
              <td><input type="text" class="u-ipt" name="user.remark" maxlength="60"></td>                                   
            </tr>
             <tr>  
              <th class="tr">是否注销：</th>
              <td><s:checkbox name="user.isLogout" onclick="cascadeReason()" id="isLogout"/>
              </td>                                   
            </tr>                       
            <tr>  
              <th class="tr">注销原因：</th>
              <td><input type="text" class="u-ipt" name="user.logoutReason" value="${user.logoutReason}" id="logoutReason" maxlength="60"
              	<s:if test='user.isLogout!="false"'>
              	   disabled="disabled"
              	</s:if>
              >
              </td>                                   
            </tr>
         </tbody> 
         <tfoot>
               <tr>
                 <td colspan="2" class="tc">
                   <button type="submit" class="u-btn" id="submitBtn">修改</button>
                   &emsp;
                   <button type="button" class="u-btn" onclick="javascript:history.back();">返回</button>
                 </td>
               </tr>
             </tfoot>                          
      </table>
      </form>
    </div><!-- /#container -->
  	<!--javascript start-->
</body>
</html>
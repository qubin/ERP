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
			jQuery("#is_logout").val("1");
			reason.attr("disabled", false);
		} else {
			jQuery("#is_logout").val("0");
			reason.attr("disabled", true);
		}
  	}
  	
  /* 	function isSingleUser(userId, userCode) {
		findUserRole.getExistUserCount(userId, userCode, function(data) {
			if (data > 0) {
				document.getElementById("notSingle").style.display = "";
				document.getElementById("submitBtn").disabled = "disabled";
			} else {
				document.getElementById("notSingle").style.display = "none";
				document.getElementById("submitBtn").disabled = "";
			}
		});
	}	 */
	
	function repeat(){
		var user_id=jQuery("#user_id").val();
		var old_userId=jQuery("#old_userId").val();
		if(user_id==''){
			jQuery("#notSingle").html("不能为空");
			return false;
		}
		if(user_id == old_userId){
			return true;
		}else{
		 var data = jQuery.ajax({
			  url: "${pageContext.request.contextPath }/admin/basic/user_findUserByName.html",
			  data: {user_name:user_id},
			  type:"post",
			  async: false
			 }).responseText;
		if(data=="true"){
			jQuery("#notSingle").html("用户账户称已经存在");
			return false;
		}else{
			return true;
		}
	   }
	}
	function clears(){
	jQuery("#notSingle").html("");
	}
  </script>
  </head>
<body>
    <div id="container" class="container">
    <div class="hr10"></div>  
    <form action="${pageContext.request.contextPath}/admin/basic/user_modify.html"	id="form1" method="post" onsubmit="return repeat();">    
      <table class="m-table-form">
         <tbody>
            <tr>  
               <th class="tr">用户登录帐号:</th>
              <td><input name="user.userLoginId"  class="u-ipt required"  id="user_id" maxlength="20"  type="text" value="${user.userLoginId }" onfocus="clears();"/><font color="red"><span id="notSingle"></span></font>
              	<input type="hidden" name="user.userId" value="${user.userId}" />
              	<input type="hidden"  value="${user.userLoginId}" id="old_userId"/>
              </td>	  
            </tr> 
            <tr> 
              <th class="tr">角色:</th>
              <td><s:select list="#request.roleList" name="user.role.roleId"  listKey="roleId" listValue="roleName" cssClass="u-slt"/>                                  
            </tr> 
            <tr>
               <th class="tr">备注：</th>
              <td><input type="text" class="u-ipt" name="user.remark" maxlength="60"></td>   
            </tr>
             <tr>  
              <th class="tr">是否注销：</th>
              <td>
              	<s:if test="user.isLogout==0">
				<%-- <s:checkbox name="user.isLogout" onclick="cascadeReason()" id="isLogout"/>  --%>
              		<input type="checkbox"  onclick="cascadeReason()"  id="isLogout">
              	</s:if>
              	<s:else>
              		<input type="checkbox" checked="checked" onclick="cascadeReason()" id="isLogout">
              </s:else>
              	<input type="hidden"  name="user.isLogout" id="is_logout" value="${user.isLogout }">
              	
              	
              </td>                                   
            </tr>                       
            <tr>  
              <th class="tr">注销原因：</th>
              <td>
              	<s:if test='user.isLogout=="0"'>
              	   <input type="text" class="u-ipt" disabled="disabled" name="user.logoutReason"  value="${user.logoutReason}" id="logoutReason" maxlength="60"  />
              	</s:if>
            	<s:if test='user.isLogout=="1"'>
              	   <input type="text" class="u-ipt" name="user.logoutReason" value="${user.logoutReason}" id="logoutReason" maxlength="60"  />
              	</s:if>
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
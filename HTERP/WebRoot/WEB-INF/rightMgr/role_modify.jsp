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
    <form action="${pageContext.request.contextPath}/admin/basic/role_modify.html"	id="form1" method="post" onsubmit="return repeat();">    
      <table class="m-table-form">
         <tbody>
            <tr>  
              <th class="tr">角色名称：</th>
              <td><input type="text" class="u-ipt required " value="${role.roleName}" id="roleName" name="role.roleName" maxlength="13" onfocus="clears();">
              <font color="red"><span id="notSingle" ></span></font>
               <input type="hidden" name="role.roleId" value="${role.roleId}"/>
               <input type="hidden"  id="oldName" value="${role.roleName}"/>
                </td>
            </tr>
            <tr>  
              <th class="tr">角色权：</th>
              <td>
				<s:select list="#request.quotaList" name="role.roleQuota" cssClass="u-slt"/>              
              </td>                                   
            </tr>       
            <tr>  
              <th class="tr">备注：</th>
              <td><input type="text" class="u-ipt" value="${role.remark}" name="role.remark" maxlength="60"></td>                                   
            </tr>
             </tr>  
              <th class="tr">是否注销：</th>
              <td>
              	<s:checkbox name="role.isLogout" onclick="cascadeReason()" id="isLogout"/>
              </td>                                   
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
  		
  	/* 	function isSingleRole(roleName, roleUuid) {
			findUserRole.getExistRoleCount(roleName, roleUuid, function(data) {
			if (data > 0) {
				document.getElementById("notSingle").style.display = "";
				document.getElementById("submitBtn").disabled = "disabled";
			} else {
				document.getElementById("notSingle").style.display = "none";
				document.getElementById("submitBtn").disabled = "";
			}
		});
	} */
	
	function repeat(){
		var role_name=jQuery("#roleName").val();
		var old_role_name=jQuery("#oldName").val();
		if(role_name==''){
			jQuery("#notSingle").html("不能为空");
			return false;
		}
		if(role_name == old_role_name){
			return true;
		}else{
		
		 var data = jQuery.ajax({
			  url: "${pageContext.request.contextPath }/admin/basic/role_findRole.html",
			  data: {role_name:role_name},
			  type:"post",
			  async: false
			 }).responseText;
		if(data=="true"){
			jQuery("#notSingle").html("角色名称已经存在");
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
</body>
</html>
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
	/* function isSingleUser(userId) {
		findUserRole.getExistUserCount(userId, null, function(data) {
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
		var user_id=jQuery("#user_id").val();
		if(user_id==''){
			jQuery("#notSingle").html("不能为空");
			return false;
		}
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
	function clears(){
	jQuery("#notSingle").html("");
	}
  </script>
</head>
<body>
    <div id="container" class="container">
    <div class="hr10"></div>   
    <form id="form1" action="${pageContext.request.contextPath }/admin/basic/user_add.html" method="post" onsubmit="return repeat();">  
      <table class="m-table-form">
         <tbody>
            <tr>  
              <th class="tr">用户登录账号:</th>
              <td><input name="user.userLoginId"  class="u-ipt required"  id="user_id" maxlength="20" type="text" onfocus="clears();"/><font color="red"><span id="notSingle"></span></font></td>
            </tr>
            <tr>  
              <th class="tr">角色:</th>
              <td><s:select list="#request.roleList" name="user.role.roleId"  listKey="roleId" listValue="roleName" cssClass="u-slt"/>
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
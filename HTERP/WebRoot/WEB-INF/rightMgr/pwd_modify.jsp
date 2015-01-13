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
  <script type="text/javascript">
  	if (${flag} == 1) {
  		alert("密码修改成功！");
  	}
  </script>
  
</head>
<body>
    <div id="container" class="container">
    <div class="hr10"></div> 
    <span id="msg" ></span>  
    <form action="${pageContext.request.contextPath}/admin/basic/user_pwdModify.html" id="form1" >  
      <table class="m-table-form">
         <tbody>
         	<tr>
              <th class="tr">旧密码：</th>
              <td><input type="password" class="u-ipt required" name="password"><font color="red">*</font></td>
            </tr>
            <tr>  
              <th class="tr">新密码：</th>
              <td><input type="password" class="u-ipt required" name="password" id="pwd1"><font color="red">*</font></td>
            </tr>  
              <th class="tr">确认新密码：</th>
              <td><input type="password" class="u-ipt required validate-equals-pwd1" id="pwd2" name="password"><font color="red">*</font></td>                                   
            </tr> 
            
         </tbody> 
         <tfoot>
               <tr>
                 <td colspan="2" class="tc">
                   <button type="submit" class="u-btn">修改</button>
                   &emsp;
                   <button type="reset" class="u-btn" >重置</button>
                 </td>
               </tr>
             </tfoot>                          
      </table>
      </form>
    </div><!-- /#container -->
  	<!--javascript start-->
</body>
</html>
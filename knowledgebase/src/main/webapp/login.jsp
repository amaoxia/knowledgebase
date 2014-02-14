<%@ page contentType="text/html; charset=utf-8"%>
<%
  String contextPath=request.getContextPath();
%>
<html>
<head>
<title>欢迎登录系统管理平台</title>
<link rel="stylesheet" href="<%=contextPath%>/css/style.css" type="text/css">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" align="center" onkeydown="doSubmit()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<tr>
	<td>
	   <table width="593" border="0" align="center" cellpadding="0" cellspacing="0">
	      <tr>
	        <td height="65" background="<%=contextPath%>/images/login_top.gif">
	        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		          <tr>
		            <td width="25">　</td>
		            <td height="54" valign="bottom">
		            	<img src="<%=contextPath%>/images/title_login.gif" width="546" height="43">
		            </td>
		          </tr>
					  </table>
					</td>
				</tr>
				<tr>
				  <td height="277" align="center" background="<%=contextPath%>/images/login_middle.jpg">
				  	 <table width="247" border="0" cellpadding="3" cellspacing="4">
				        <form name="loginform" action="<%=contextPath%>/j_spring_security_check" method="post">
				          <tr>
				            <td width="65" nowrap><strong><font color="#000000">帐号：</font></strong></td>
				            <td width="142"> <input type="text" id="j_username" name="j_username" class="mytext" style="width:140px;" value="administrator"/></td>
				          </tr>
				          <tr>
				            <td nowrap><strong><font color="#000000">密码：</font></strong></td>
				            <td><input type="password" id="j_password" name="j_password" class="mytext" style="width:140px;" value="123"/></td>
				          </tr>
				          <tr>
				            <td>&nbsp;</td>
				            <td nowrap>
				            	<input type="button" name="Submit3" class="mybutton" value="登录" onclick="doLogin()">
				              <input type="reset" name="Submit22" class="mybutton" value="取消">
				            </td>
				          </tr>
				        </form>
				     </table>
				   </td>
				</tr>
				<tr>
				  <td height="68" valign="bottom" background="<%=contextPath%>/admin/images/login_bottom.gif">
				  	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				      <tr>
				        <td height="30" align="center" valign="top">
				        	<font face="Arial, Helvetica, sans-serif">&nbsp;</font>&nbsp;&nbsp;&nbsp;<a href="#" class="syslink2"></a>
				        </td>
				      </tr>
				      <tr>
				        <td height="30" align="center" valign="top">
				        	<font face="Arial, Helvetica, sans-serif">&copy;</font>&nbsp;2009 
					          版权所有 
					      </td>
				      </tr>
				    </table>
				  </td>
				</tr>
		 </table>
	</td>
</tr>
</table>
</body>
</html>
<script language="javascript">
	function doLogin()
	{
		var uname=document.getElementById('j_username').value;
		var upwd=document.getElementById('j_password').value;
		if(uname.length<1  || upwd.length<1 ) return;
		document.loginform.submit();		
	}
	function doSubmit()
	{
		if(event.keyCode==13){
			doLogin();
		}
	}
</script>
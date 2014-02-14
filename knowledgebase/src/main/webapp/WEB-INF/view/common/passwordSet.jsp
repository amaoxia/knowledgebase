<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
 String ctx=request.getContextPath();
%>
<link href="<%=ctx%>/css/style.css" rel="stylesheet" type="text/css"/>

</head>
<body>	
<form action="<%=ctx%>/passwordSave.htm" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" bgcolor="DEE8F6" class="right">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="right" valign="top" class="czmb_2">
            	<div class="tool">
              <table border="0" cellspacing="0" cellpadding="0">
                <tr>                  
                  <td width="70" style="cursor:hand">
                  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="center"><img src="<%=ctx%>/images/tj.gif" width="22" height="21"></td>
                        <td><a href="#" onclick="doSubmit()">提交</a></td>
                      </tr>
                     </table>
                  </td>
                </tr>
              </table>
              </div>
              <div class="blankH10"></div>
              <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="border_blue">
                <tr>
                  <td class="operate_title">
                  	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td width="62%">密码修改</td>
                        <td width="38%" align="right"><img src="<%=ctx%>/images/zk.gif" width="15" height="14"></td>
                      </tr>
                    </table>
                  </td>
                </tr>
                <tr>
                  <td>                  	
                  	<div  style="padding-top:10px; padding-bottom:10px;">
		                   <table width="90%" border="0" align="center" cellpadding="0" cellspacing="2" id="edittb">									       
									      <tr align="center">
														<td  width="30%" align="center">旧密码<font style="color:red">(*)</font></td>
														<td  width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
															<input type="password" id="oldpwd" name="oldpwd" style="width:20%;"/>
														</td>
									      </tr>
									      
									      <tr align="center">
														<td  width="30%" align="center">新密码<font style="color:red">(*)</font></td>
														<td  width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
															<input type="password" id="newpwd" name="newpwd" style="width:20%;"/>
														</td>
									      </tr>
									      <tr align="center">
														<td  width="30%" align="center">确认密码<font style="color:red">(*)</font></td>
														<td  width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
															<input type="password" id="newpwd1" name="newpwd1" style="width:20%;"/>
														</td>
									      </tr>
								      </table>
		                </div>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
      </table>
      </td>
  </tr>
</table>
</form>
</body>
</html>
<script language="javascript">
	function loadMsg()
	{
		var msg='${msg}';
		if(msg.length>0){
		   alert(msg);
		}
	}
	loadMsg();
	
	function validPwd()
	{
	  return true;
	}
	
	
	
	function doSubmit()
	{		
	  document.forms[0].submit();
	}
</script>
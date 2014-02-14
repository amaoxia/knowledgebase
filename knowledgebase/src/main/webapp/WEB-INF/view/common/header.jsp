<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	String ctx = request.getContextPath(); 
%>
<link href="<%=ctx%>/css/top.css" rel="stylesheet" type="text/css" />
</head>
<body>
<table width="100%" height="84" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="right" background="../images/top_bj.jpg">
    	<table width="98%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <th width="84" scope="col"><img src="../images/title_body.gif" width="330" height="84" /></th>
        <th valign="bottom" scope="col">
        	<table width="98%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <th align="right" scope="col">
            	<table width="100%" height="40" border="0" cellpadding="0" cellspacing="0">
	              <tr>
	                <td align="right">
	                	<a href="#" class="zi_nh">EC前台</a><span class="zi_nh"> |</span> &nbsp;
	                	<a href="<%=ctx%>/passwordSet.htm" target="mainFrame" class="zi_nh">密码修改</a><span class="zi_nh"> |</span> &nbsp;
	                	<a href="#" onclick="exitLogin()" class="zi_nh">安全退出</a>
	                </td>
	                <td width="20">&nbsp;</td>
	              </tr>
              </table>
            </th>
          </tr>
          <tr>
            <th height="40" align="right" scope="row">
            	<table width="150" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	               	                
	                <th align="center" scope="col"><a href="#" target="_blank" class="zi_l"></a></th>
	                <th align="left" scope="col">
	                	<!--
	                	<a href="http://218.240.28.185:8080/gxcms/rightsAction!checkLoginBySSO.action?j_username=${acegiUser.useraccount}&j_password=${acegiUser.userpwd}" target="_blank" class="zi_l">内容发布系统</a>
	                -->
	                </th>
	                
	                <th align="center" scope="col">&nbsp;</th>
	               
	              </tr>
            	</table>
            </th>
          </tr>
        </table></th>
      </tr>
    </table>
    </td>
  </tr>
</table>
</body>
</html>
<script language="javascript">
function exitLogin()
{
	if(confirm("你确定要退出系统吗？")){
	   top.location.href="<%=ctx%>/loginout.htm";		
	}	
}
</script>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<%
	String contextpath = request.getContextPath(); 
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%= contextpath%>/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%= contextpath%>/js/jquery/themes/default/easyui.css" rel="stylesheet" type="text/css" />
</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
<noscript>
<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
    <img src="../images/noscript.gif" alt='抱歉，请开启脚本支持！' />
</div>
</noscript>
<table width="100%" height="84" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="right" background="../images/top_bj.jpg">
    	<table width="98%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <th width="84" scope="col"><img src="../images/logo1.gif" width="330" height="84" /></th>
        <th valign="bottom" scope="col">
        	<table width="98%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <th align="right" scope="col">
            	<table width="100%" height="40" border="0" cellpadding="0" cellspacing="0">
	              <tr>
	                <td align="right">
	                	
	                	<a href="<%=contextpath%>/common/passwordEdit.jsp" target="mainFrame" class="zi_nh">修改密码</a><span class="zi_nh"> |</span> &nbsp;
	                	<a href="#" class="zi_nh" onclick="exitLogin()">退出</a>
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
	                <th align="center" scope="col">&nbsp;</th>
	              </tr>
            	</table>
            </th>
          </tr>
        </table>
        </th>
      </tr>
    </table>
    </td>
  </tr>
</table>



    <div region="north" split="true" border="false" style="overflow: hidden; height: 2px;
        background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        
    </div>
         
    <div region="west" split="true" title="导航菜单" style="width:180px;" id="west">
			  <div class="easyui-accordion" fit="true" border="false">
				<!--  导航内容 -->
				
				</div>
    </div>
    
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden:width:100%;">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
					
		    </div>
    </div>
    
    <!-- page footer -->
    <div id="footer" region="south" split="true" style="height: 30px; background: #D2E0F2; ">
        
    </div>
    
		<!--div id="mm" class="easyui-menu" style="width:150px; display:none;">
			<div id="mm-tabclose">关闭</div>
			<div id="mm-tabcloseall">全部关闭</div>
			<div id="mm-tabcloseother">除此之外全部关闭</div>
			<div class="menu-sep"></div>
			<div id="mm-exit">退出</div>
		</div-->
		
</body>
</html>
<script type="text/javascript" src="<%=contextpath%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=contextpath%>/js/jquery/jquery.easyui.pack.js"></script>
<script type="text/javascript" src='<%=contextpath%>/js/jquery/outlook.js'></script>
<script language="javascript">
//init menus
var menus=<%=request.getAttribute("menus")%>;
setMenusJson('<%=contextpath%>',menus);
//init footer
document.getElementById("footer").innerHTML='<div class="footer">版权所有@北京启明星辰有限公司</div>';

</script>
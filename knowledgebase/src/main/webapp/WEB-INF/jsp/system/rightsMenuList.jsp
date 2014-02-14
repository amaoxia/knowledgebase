<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@include file="/common/taglibs.jsp"%>
	</head>
	</head>
	<body>
		<form action="rightsAction" method="post">
			<input type="hidden" name="roleid" id="roleid" value="${roleid }"/>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="con">
				<tr>
					<td>
						<div class="tool">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="95%">
										&nbsp;&nbsp;角色名称：${sysRole.rolename}&nbsp;&nbsp;&nbsp;&nbsp;角色描述：${sysRole.roledesc}&nbsp;&nbsp;&nbsp;&nbsp;

										<if test="#request.sysmarks!=null">
										系统名称：&nbsp;
										<select name="sysmark" onchange="changeRightsMenu(this.value)">
										<c:forEach items="${sysmarks }" var="sentry" varStatus="status">
											<option value='${sentry.key}'>
												${sentry.value}
											</option>
										</c:forEach>
										</select>
										</if>

									</td>
									<td width="70" height="27" style="cursor: hand"
										onMouseOver="this.style.backgroundImage='url(images/tool_bg1.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#DFAF0D';"
										onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
										<table width="100%" border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td align="center">
													<img src="<%=ctx%>/images/back.gif" width="22" height="21">
												</td>
												<td>
													<a href="#" class="v1" onclick="goBack()">返回</a>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>

				<tr>
					<td colspan="2">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>


								<td width="40%">
									<fieldset>
										<legend>
											未分配菜单
										</legend>
										<iframe id="lefttree" name="lefttree"
											src="<%=ctx%>/sys/getRoleRights.htm?flag=left&roleid=${sysRole.roleid}&sysmark=${sysmark}"
											width="100%" scrolling="no" frameborder="0"
											allowtransparency="true" marginheight="0" marginwidth="0"
											onload="document.all('lefttree').height=400;"></iframe>
									</fieldset>
								</td>

								<td width="4%" align="center">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td>
												&nbsp;&nbsp;&nbsp;
												<a href="#" onclick="javascript:doEditMenuRole('addMenus')"
													class="syslink">&nbsp;&nbsp;>>&nbsp;&nbsp;</a>
											</td>
										</tr>
										<tr>
											<td>
												&nbsp;&nbsp;&nbsp;
												<a href="#" onclick="javascript:doEditMenuRole('delMenus')"
													class="syslink">&nbsp;&nbsp;<<&nbsp;&nbsp;</a>
											</td>
										</tr>
									</table>

								</td>

								<td width="40%">
									<fieldset>
										<legend>
											已分配菜单
										</legend>
										<iframe id="righttree" name="righttree"
											src="<%=ctx%>/sys/getRoleRights.htm?flag=right&roleid=${sysRole.roleid}&sysmark=${sysmark}"
											width="100%" scrolling="no" frameborder="0"
											allowtransparency="true" marginheight="0" marginwidth="0"
											onload="document.all('righttree').height=400;"></iframe>
									</fieldset>
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
  document.forms[0].sysmark.value='${sysmark}';
  
	//新增、移除菜单操作
	function doEditMenuRole(flag){
		var svalue="";
		if(flag=='addMenus'){
			svalue=document.frames['lefttree'].window.getTreeCheckedValue();
			if(svalue=="") {
			  alert("请至少选择一条菜单新增。");
			  return;	
			}
	  } else{
	  	svalue=document.frames['righttree'].window.getTreeCheckedValue();
			if(svalue=="") {
			  alert("请至少选择一条菜单移除。");
			  return;
			}
	  }
	  var sysmark='${sysmark}';
		document.forms[0].action="<%=ctx%>/sys/editMenuForRights.htm?sysmark="+sysmark+"&flag="+flag+"&Ids="+svalue;
		document.forms[0].submit();
	}
	
	function changeRightsMenu(svalue){		
		document.forms[0].action="<%=ctx%>/sys/toEditRoleRightsPage.htm?sysmark="+svalue;
		document.forms[0].submit();		
	}
	
	function goBack(){
		document.forms[0].action="<%=ctx%>/sys/rights.htm";
		document.forms[0].submit();
	}
 
</script>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/common/taglibs.jsp"%>
	</head>
	<body>
		<form action="" name="form1" id="form1" method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top" bgcolor="DEE8F6" class="right">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td valign="top" bgcolor="#FFFFFF" class="czmb_l">
									<table width="100%" border="0" align="center" cellpadding="0"
										cellspacing="2">
										<tr height="22">
											<TD width="10%">
												角色名称
											</TD>
											<TD width="60%">
												<input id="rolename" name="rolename"
													cssClass="mytext" theme="simple" value="${param.rolename }"/>
											</TD>
											<TD width="30%" align="right">
												<div class="search">
													<a href="#" onclick="doSubmit();return false;"><img src="<%=ctx%>/images/search.gif" width="60" height="20" border="0"></a>
												</div>
											</TD>
										</tr>
									</table>

									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="border_blue">
										<tr>
											<td class="operate_title">&nbsp;</td>
										</tr>
										<tr>
											<td>
												<div id="listdiv" class="listdiv">
													<table width="100%" border="0" cellpadding="0"
														cellspacing="0">
														<tr align="center">
															<td class="list_title" nowrap width="5%">
																序号
																<br>
															</td>
															<td class="list_title" nowrap width="10%">
																角色名称
																<br>
															</td>
															<td class="list_title" nowrap width="30%">
																角色描述
																<br>
															</td>
															<td class="list_title" nowrap width="10%">
																创建人
																<br>
															</td>
															<td class="list_title" nowrap width="10%">
																创建日期
																<br>
															</td>
															<td class="list_title" nowrap width="12%">
																操作
															</td>
														</tr>
														<c:forEach items="${pagerResult.items }" var="role"	varStatus="status">
															<tr align="center" onMouseOver="changeto()"	onmouseout="changeback()">
																<td nowrap class="list_content">
																	${status.index+1}
																	<br>
																</td>
																<td class="list_content" nowrap bgcolor="#FFFFFF">
																	&nbsp;
																	${role.rolename }
																</td>
																<td class="list_content" nowrap bgcolor="#FFFFFF">
																	&nbsp;
																	${role.roledesc }
																</td>
																<td class="list_content" nowrap bgcolor="#FFFFFF">
																	&nbsp;
																	${role.creater }
																</td>
																<td class="list_content" nowrap bgcolor="#FFFFFF">
																	&nbsp;
																	${role.createtime }
																</td>
																<td nowrap class="list_content">
																	<c:if test="${role.roleid=='1'}">&nbsp;</c:if>
																	<c:if test="${role.roleid!='1'}">
																		 <a href="#" onclick="addUserForRole(${role.roleid})">分配用户</a>&nbsp;
																		 <a href="#" onclick="addMenuForRole(${role.roleid})">分配菜单</a>&nbsp;
																	</c:if>
																</td>
															</tr>
														</c:forEach>
													</table>
												</div>
											</td>
										</tr>
									</table>
									<div>
										<%@include file="/common/pageHibernate.jsp"%>
									</div>
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
	//分配用户
	function addUserForRole(roleid){
		$('#form1').attr("action", "<%=ctx%>/sys/toEditUserRole.htm?roleid="+roleid);
		$('#form1').submit();
	}
	
	//分配菜单
	function addMenuForRole(roleid){
		$('#form1').attr("action", "<%=ctx%>/sys/toEditRoleRights.htm?roleid="+roleid);
		$('#form1').submit();
	}
	
	function doSubmit(){
		$('#form1').attr("action", "<%=ctx%>/sys/rights.htm");
		$('#form1').submit();
	}
	
</script>
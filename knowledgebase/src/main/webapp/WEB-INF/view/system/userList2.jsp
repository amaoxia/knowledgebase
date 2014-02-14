<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/common/taglibs.jsp"%>
	</head>
	<body>
		<form action="<%=ctx%>/sys/user.htm" method="post">
			<input type="hidden" id="pagerAction" name="pagerAction">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top" bgcolor="DEE8F6" class="right">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td valign="top" bgcolor="#FFFFFF" class="czmb_l">
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="2">										
										<tr height="22">
											<TD width="10%">
												用户名称
											</TD>
											<TD width="60%">
												<input id="username" name="user.username" value="${username}"/>
											</TD>
											<TD width="30%" align="right">
												<div class="search">
													<a href="#" onclick="doSubmit()"><img	src="<%=ctx%>/images/search.gif" width="60"	height="20" border="0"></a>
												</div>
											</TD>
										</tr>
									</table>

									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="border_blue">										
										<tr>
											<td class="operate_title">
												<%@include file="/common/pageToolBar.jsp"%>
											</td>
										</tr>

										<tr>
											<td>
												<div id="listdiv" class="listdiv">
													<table width="100%" border="0" cellpadding="0" cellspacing="0">
														
														<tr align="center">
															<td width="5%" class="list_title">
																<input type="checkbox" id="cbxAll" name="cbxAll" onclick="selectForCbxAll(this,'cbxItem')" />																	
																<br>
															</td>
															<td class="list_title" nowrap width="5%">
																序号
																<br>
															</td>
															<td class="list_title" nowrap width="15%">
																登录账号
																<br>
															</td>
															<td class="list_title" nowrap width="10%">
																姓名
																<br>
															</td>
															<td class="list_title" nowrap width="10%">
																用户编号
																<br>
															</td>
															<td class="list_title" nowrap width="10%">
																所属机构
																<br>
															</td>
															<td class="list_title" nowrap width="5%">
																性别
																<br>
															</td>
															<td class="list_title" nowrap width="15%">
																联系方式
																<br>
															</td>
															<!--  td class="list_title" nowrap width="10%">
																创建人
																<br>
															</td-->
															<td class="list_title" nowrap width="15%">
																创建日期
																<br>
															</td>
														</tr>
														<c:forEach items="${pagerResult.items }" var="user" varStatus="status">
														<tr align="center" onMouseOver="changeto()" onmouseout="changeback()">															
															<td class="list_content">
																<input type="checkbox" id="cbxItem" name="cbxItem" value="${user.userid }" onclick="selectForCbxItem(this,'cbxAll')" />																	
																<br>
															</td>
															<td nowrap class="list_content">
																${status.index+1}
																<br>
															</td>
															<td class="list_content" nowrap bgcolor="#FFFFFF">
																&nbsp;
																${user.loginuser }
															</td>
															<td class="list_content" nowrap bgcolor="#FFFFFF">
																&nbsp;
																${user.username }
															</td>
															<td class="list_content" nowrap bgcolor="#FFFFFF">
																&nbsp;
																${user.usercode }
															</td>
															<td class="list_content" nowrap bgcolor="#FFFFFF">
																&nbsp;
																${user.orgname }
															</td>
															<td class="list_content" nowrap bgcolor="#FFFFFF">
																&nbsp;
																${user.usersexString}
															</td>
															<td class="list_content" nowrap bgcolor="#FFFFFF">
																&nbsp;
																${user.usermp}
															</td>
															<!--td class="list_content" nowrap bgcolor="#FFFFFF">
																&nbsp;
																${user.creater }
															</td-->
															<td class="list_content" nowrap bgcolor="#FFFFFF">
																&nbsp;
																${user.createtime }
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
	
	function doEdit(nflag) {
		var saction="";
		if(nflag==1) {
			document.forms[0].action="<%=ctx%>/sys/userToEdit.htm";
			document.forms[0].submit();
			return;
		}
		var svalue=getCbxValue(document.all.cbxItem);
		if(nflag==2) {
			if(svalue=="" || svalue.indexOf(",")!=-1) {
			  alert("请选择一条记录。");
			  return;	
			}
		}
		saction="<%=ctx%>/sys/userToEdit.htm?Ids="+svalue;
		if(nflag==3) {
			if(svalue=="") {
			  alert("请选择一条记录。");
			  return;	
			}
			saction="<%=ctx%>/sys/userDelete.htm?Ids="+svalue;
		}
		document.forms[0].action=saction;
		document.forms[0].submit();
		
	}
	function doDelete() {
		var svalue=getCbxValue(document.all.cbxItem);
		if(svalue=="") {
		  alert("请选择一条记录。");
		  return;	
		}
		if(!window.confirm("确认删除？")) {
			return;
		}
		document.forms[0].action = "<%=ctx%>/sys/userDelete.htm?Ids="+svalue;
		document.forms[0].submit();
	}
	function doSubmit() {
		document.forms[0].action="<%=ctx%>/sys/user.htm";
		document.forms[0].submit();
	}
</script>
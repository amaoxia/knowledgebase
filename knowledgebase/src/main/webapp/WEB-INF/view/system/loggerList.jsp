<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/common/taglibs.jsp"%>
	</head>
	<body>
		<form action="logsController" method="post">
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
												操作者
											</TD>
											<TD width="60%">
												<input id="loginuser" name="logEntity.loginuser" cssClass="mytext"
													theme="simple" />
											</TD>
											<TD width="30%" align="right">
												<div class="search">
													<a href="#" onclick="doSubmit()"><img
															src="<%=ctx%>/images/search.gif" width="60"
															height="20" border="0">
													</a>
												</div>
											</TD>
										</tr>
									</table>

									<table width="100%" border="0" align="center" cellpadding="0"
										cellspacing="0" class="border_blue">
										<tr>
											<td class="operate_title">
												<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
									              <tr>
									               <td width="62%">日志查询</td>
									               <td width="38%">
									               <br></td>
									              </tr>
									            </table>
											</td>
										</tr>

										<tr>
											<td>
												<div id="listdiv" class="listdiv">
													<table width="100%" border="0" cellpadding="0"
														cellspacing="0">
														<tr align="center" >
													      <td nowrap class="list_title" width="5%">序号</td>
													      <td nowrap class="list_title" width="10%">功能名称</td>
													      <td nowrap class="list_title" width="40%">操作信息</td>
													      <td nowrap class="list_title" width="10%">操作者</td>
													      <td nowrap class="list_title" width="10%">操作时间</td>
													    </tr>
													    <c:forEach items="${logsList}" var="logs" varStatus="status">				    		  
										   		   	   		<tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
															      <td nowrap class="list_content" width="5%">
															      	${status.index+1}
															      </td>
															      <td nowrap class="list_content" bgcolor="#FFFFFF">&nbsp;${logs.menuname}</td>
															      <td nowrap class="list_content" bgcolor="#FFFFFF">&nbsp;${logs.actionname}</td>
															      <td nowrap class="list_content" bgcolor="#FFFFFF">&nbsp;${logs.loginuser}</td>
															      <td nowrap class="list_content" bgcolor="#FFFFFF">&nbsp;${logs.createtime}</td>
															      
														     </tr>
										           		</c:forEach>
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
	function doSubmit() {
		document.forms[0].action="<%=ctx%>/sys/logs.htm";
		document.forms[0].submit();
	}
</script>
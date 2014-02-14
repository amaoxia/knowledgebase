<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/common/taglibs.jsp"%>
	</head>
	<body>
		<form action="paramController" method="post">
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
												参数名称
											</TD>
											<TD width="60%">
												<input id="paramkey" name="paramkey" />
											</TD>
											<TD width="30%" align="right">
												<div class="search">
													<a href="#" onclick="doSubmit()"><img src="<%=ctx%>/images/search.gif" width="60"	height="20" border="0"></a>
												</div>
											</TD>
										</tr>
									</table>

									<table width="100%" border="0" align="center" cellpadding="0"
										cellspacing="0" class="border_blue">
										<tr>
											<td class="operate_title">
												<%@ page language="java" pageEncoding="utf-8"%>
												<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
												<html>
												  <head>
												  	<%String sContext = request.getContextPath(); %>
												    <title></title>
													  </head>
													  <body>
											    		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
											              <tr>
											               <td width="62%">参数列表</td>
											               <td width="38%">
											               	<table border="0" align="right" cellpadding="0" cellspacing="0">
											                   <tr>
											                   	<td width="18"><img src="<%=sContext%>/images/edit.gif" width="10" height="10"><br></td>
											                    <td width="35"><a href="#" onclick="doEdit()">修改</a><br></td>
											                   </tr>
											                  </table>
											               <br></td>
											              </tr>
											            </table>
												  </body>
												</html>	
											</td>
										</tr>

										<tr>
											<td>
												<div id="listdiv" class="listdiv">
													<table width="100%" border="0" cellpadding="0"
														cellspacing="0">
														<tr align="center">
															<td width="10%" class="list_title">
																<input type="checkbox" id="cbxAll" name="cbxAll"
																	onclick="selectForCbxAll(this,'cbxItem')" />
																<br>
															</td>
															<td class="list_title" nowrap width="10%">
																序号
																<br>
															</td>
															<td class="list_title" nowrap width="25%">
																参数名称
																<br>
															</td>
															<td class="list_title" nowrap width="25%">
																参数值
																<br>
															</td>
															<td class="list_title" nowrap width="15%">
																修改人员
																<br>
															</td>
															<td class="list_title" nowrap width="15%">
																修改时间
																<br>
															</td>
														</tr>
														<c:forEach items="${paramList}" var="sysparam" varStatus="status">
														<tr align="center" onMouseOver="changeto()"
															onmouseout="changeback()">
															<td class="list_content">
																<input type="checkbox" id="cbxItem" name="cbxItem"
																	value="${sysparam.id }"
																	onclick="selectForCbxItem(this,'cbxAll')" />
																<br>
															</td>
															<td nowrap class="list_content">
																${status.index+1}
																<br>
															</td>
															<td class="list_content" nowrap bgcolor="#FFFFFF">
																&nbsp;
																${sysparam.paramkey}
															</td>
															<td class="list_content" nowrap bgcolor="#FFFFFF">
																&nbsp;
																${sysparam.paramvalue}
															</td>
															<td class="list_content" nowrap bgcolor="#FFFFFF">
																&nbsp;
																${sysparam.editer}
															</td>
															<td class="list_content" nowrap bgcolor="#FFFFFF">
																&nbsp;
																${sysparam.edittime}
															</td>
														</tr>
														</c:forEach>
													</table>
												</div>
											</td>
										</tr>
									</table>
									<div>
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
	
	function doEdit() {
		var svalue=getCbxValue(document.all.cbxItem);
		if(svalue=="" || svalue.indexOf(",")!=-1) {
		  alert("请选择一条记录。");
		  return;	
		}
		document.forms[0].action="<%=ctx%>/sys/paramToEdit.htm?Ids="+svalue;
		document.forms[0].submit();
	}
	function doSubmit() {
		document.forms[0].action="<%=ctx%>/sys/param.htm";
		document.forms[0].submit();
	}
</script>
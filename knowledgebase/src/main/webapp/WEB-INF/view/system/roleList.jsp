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
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="2">										
										<tr height="22">
											<TD width="10%">
												角色名称
											</TD>
											<TD width="60%">
												<input id="rolename" name="rolename" cssClass="mytext" value="${param.rolename }"/>
											</TD>
											<TD width="30%" align="right">
												<div class="search">
													<a href="#" onclick="doSubmit();return false;">
														<img src="<%=ctx%>/images/search.gif" width="60" height="20" border="0">
													</a>
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
													<table width="100%" border="0" cellpadding="0"
														cellspacing="0">
														<tr align="center">
															<td width="5%" class="list_title">
																<input type="checkbox" id="cbxAll" name="cbxAll" onclick="selectForCbxAll(this,'cbxItem')" />																	
																<br>
															</td>
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
														</tr>
														<c:forEach items="${pagerResult.items }" var="role" varStatus="status">
														<tr align="center" onMouseOver="changeto()"
															onmouseout="changeback()">
															<td class="list_content">
																<input type="checkbox" id="cbxItem" name="cbxItem"
																	value="${role.roleid }"
																	onclick="selectForCbxItem(this,'cbxAll')" />
																<br>
															</td>
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
	function doEdit(nflag){
		if(nflag==1){
			$('#form1').attr("action","<%=ctx%>/sys/toAddRole.htm");
			$('#form1')[0].submit();
			return;
		}
		
		var svalue=getCbxValue(document.all.cbxItem);
		if(svalue=="" || svalue.indexOf(",")!=-1) {
		  alert("请选择一条记录。");
		  return;	
		}
		$('#form1').attr("action","<%=ctx%>/sys/toEditRole.htm?roleid="+svalue);
		$('#form1')[0].submit();
	}
	
	function doDelete(){
		var svalue=getCbxValue(document.all.cbxItem);
		if(svalue=="") {
		  alert("请选择一条记录。");
		  return;	
		}
		if(!confirm("确定要删除吗？")) return false;
		var url = "<%=ctx%>/sys/doDelRole.htm?Ids="+svalue;
		$.post(url,null,function(data, textStatus){
			alert("删除成功！");		
			doSubmit();
		},'json');
	}
	
	function doSubmit(){
		$('#form1').attr("action","<%=ctx%>/sys/role.htm");
		$('#form1')[0].submit();
	}
</script>
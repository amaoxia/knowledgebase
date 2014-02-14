<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/common/taglibs.jsp"%>
	</head>
	<body>
		<form action="dictController" method="post">
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
												字典标识
											</TD>
											<TD width="60%">
												<input id="dictkey" name="dict.dictkey" cssClass="mytext"
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
																<input type="checkbox" id="cbxAll" name="cbxAll"
																	onclick="selectForCbxAll(this,'cbxItem')" />
																<br>
															</td>
															<td class="list_title" nowrap width="5%">
																序号
																<br>
															</td>
															<td class="list_title" nowrap width="15%">
																字典编号
																<br>
															</td>
															<td class="list_title" nowrap width="15%">
																字典标识
																<br>
															</td>
															<td class="list_title" nowrap width="15%">
																字典名称
																<br>
															</td>
															<td class="list_title" nowrap width="35%">
																备注
																<br>
															</td>
															<td class="list_title" nowrap width="10%">
																操作
																<br>
															</td>
														</tr>
														<c:forEach items="${dictList}" var="dict" varStatus="status">
														<tr align="center" onMouseOver="changeto()"
															onmouseout="changeback()">
															<td class="list_content">
																<input type="checkbox" id="cbxItem" name="cbxItem"
																	value="${dict.dictid }"
																	onclick="selectForCbxItem(this,'cbxAll')" />
																<br>
															</td>
															<td nowrap class="list_content">
																${status.index+1}
																<br>
															</td>
															<td class="list_content" nowrap bgcolor="#FFFFFF">
																&nbsp;
																${dict.dictcode }
															</td>
															<td class="list_content" nowrap bgcolor="#FFFFFF">
																&nbsp;
																${dict.dictkey }
															</td>
															<td class="list_content" nowrap bgcolor="#FFFFFF">
																&nbsp;
																${dict.dictname }
															</td>
															<td class="list_content" nowrap bgcolor="#FFFFFF">
																&nbsp;
																${dict.descinfo}
															</td>
															<td class="list_content" nowrap bgcolor="#FFFFFF">
																&nbsp;
																<a href="#" onclick = showDetailList('${dict.dictcode}','${dict.dictkey}','${dict.dictname}')>编辑明细</a>
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
	
	function doEdit(nflag) {
		var saction="";
		if(nflag==1) {
			document.forms[0].action="<%=ctx%>/sys/dictToEdit.htm";
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
		saction="<%=ctx%>/sys/dictToEdit.htm?Ids="+svalue;
		if(nflag==3) {
			if(svalue=="") {
			  alert("请选择一条记录。");
			  return;	
			}
			saction="<%=ctx%>/sys/dictDelete.htm?Ids="+svalue;
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
		document.forms[0].action = "<%=ctx%>/sys/dictDelete.htm?Ids="+svalue;
		document.forms[0].submit();
	}
	function doSubmit() {
		document.forms[0].action="<%=ctx%>/sys/dict.htm";
		document.forms[0].submit();
	}
	function showDetailList(dictpcode,dictpkey,dictpname) {
		document.forms[0].action="<%=ctx%>/sys/dictToDetail.htm?dictpcode="+dictpcode+"&dictpkey="+dictpkey+"&dictpname="+escape(encodeURIComponent(dictpname));
		document.forms[0].submit();
	}
</script>
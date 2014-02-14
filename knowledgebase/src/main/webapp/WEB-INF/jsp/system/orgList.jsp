<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/common/taglibs.jsp"%>
	</head>
	<body>
		<form action="<%=ctx%>/sys/org.htm" name="form1" id="form1" method="post">
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
												机构简称
											</TD>
											<TD width="60%">
												<input id="orgasname" name="orgasname" cssClass="mytext"
													theme="simple" value="${param.orgasname }" />
											</TD>
											<TD width="30%" align="right">
												<div class="search">
													<a href="#" onclick="doSubmit()"><img
															src="<%=ctx%>/images/search.gif" width="60" height="20"
															border="0"> </a>
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
															<td class="list_title" nowrap width="10%">
																机构代码
																<br>
															</td>
															<td class="list_title" nowrap width="10%">
																机构名称
																<br>
															</td>
															<td class="list_title" nowrap width="5%">
																机构简称
																<br>
															</td>
															<td class="list_title" nowrap width="10%">
																联系电话
																<br>
															</td>
															<td class="list_title" nowrap width="10%">
																传真
																<br>
															</td>
															<td class="list_title" nowrap width="20%">
																地址
																<br>
															</td>
															<!--  
															<td class="list_title" nowrap width="10%">
																创建人
																<br>
															</td>
															<td class="list_title" nowrap width="5%">
																创建日期
																<br>
															</td>
															-->
															<td class="list_title" nowrap width="5%">
																操作
																<br>
															</td>
														</tr>
														<c:forEach items="${pagerResult.items }" var="org"
															varStatus="status">
															<tr align="center" onMouseOver="changeto()"
																onmouseout="changeback()">
																<td class="list_content">
																	<input type="checkbox" id="cbxItem" name="cbxItem"
																		value="${org.orgid }"
																		onclick="selectForCbxItem(this,'cbxAll')" />
																	<input type="hidden" id="scode" name="scode"
																		value="${org.scode }"/>
																	<br>
																</td>
																<td nowrap class="list_content">
																	${status.index+1}
																	<br>
																</td>
																<td class="list_content" nowrap bgcolor="#FFFFFF">
																	&nbsp; ${org.orgcode }
																</td>
																<td class="list_content" nowrap bgcolor="#FFFFFF">
																	&nbsp; ${org.orgname }
																</td>
																<td class="list_content" nowrap bgcolor="#FFFFFF">
																	&nbsp; ${org.orgasname }
																</td>
																<td class="list_content" nowrap bgcolor="#FFFFFF">
																	&nbsp; ${org.orgtel }
																</td>
																<td class="list_content" nowrap bgcolor="#FFFFFF">
																	&nbsp; ${org.orgfax }
																</td>
																<td class="list_content" nowrap bgcolor="#FFFFFF">
																	&nbsp; ${org.orgaddress }
																</td>
																<!--  
																<td class="list_content" nowrap bgcolor="#FFFFFF">
																	&nbsp; ${org.creater }
																</td>
																<td class="list_content" nowrap bgcolor="#FFFFFF">
																	&nbsp; ${role.createtime }
																</td>
																-->
																<td class="list_content" nowrap bgcolor="#FFFFFF">
																	<a hef="#" style="cursor:hand;" onclick="toMaintain('${org.orgid }');return false;">机构维护</a>
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
	$(document).ready(function() {
		var options = {
			beforeSubmit: function() {	
				return true;
			},
			success: function(responseText, statusText) {	
				if(!(responseText.success)) {
					alert(responseText);
				}else {
					$('#form1').attr("action","<%=ctx%>/sys/org.htm");
					$('#form1')[0].submit();
				}
			},
			error: function(responseText, statusText) {		
				alert("删除失败！");											
			},
			dataType: 'json'
		}	
		$('#form1').ajaxForm(options);	
	});

	function doEdit(nflag){
		if(nflag==1){
			$('#form1').attr("action","<%=ctx%>/sys/toAddOrg.htm");
			$('#form1')[0].submit();
			return;
		}
		
		var svalue=getCbxValue(document.all.cbxItem);
		if(svalue=="" || svalue.indexOf(",")!=-1) {
		  alert("请选择一条记录。");
		  return;	
		}
		$('#form1').attr("action","<%=ctx%>/sys/toEditOrg.htm?orgid="+svalue);
		$('#form1')[0].submit();
	}
	
	function doDelete(){
		var svalue=getCbxValue(document.all.cbxItem);
		if(svalue=="") {
		  alert("请选择一条记录。");
		  return;	
		}
		var sArray = $('#listdiv input[type="checkbox"]:checked');
		var scodes = "";
		$.each(sArray, function(index, domEle){
			var obj = $(domEle).parent().find('input[name="scode"]');
			scodes += obj.val()+",";
		});
		if(!confirm("确定要删除该机构及其子机构吗？")) return false;
		$('#form1').attr("action","<%=ctx%>/sys/doDelOrg.htm?scodes="+scodes);
		$('#form1').submit();
	}
	
	function toMaintain(orgid){
		$('#form1').attr("action","<%=ctx%>/sys/toMaintainOrg.htm?orgid="+orgid);
		$('#form1')[0].submit();
	}
	
	function doSubmit(){
		$('#form1').attr("action","<%=ctx%>/sys/org.htm");
		$('#form1')[0].submit();
	}
</script>
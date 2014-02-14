<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@include file="/common/taglibs.jsp"%>
	</head>
	</head>
	<body>
		<form action="rightsAction" method="post">
			<input type="hidden" name="orgid" id="orgid" value="${sysOrg.orgid }" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="con">
				<tr>
					<td>
						<div class="tool">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="80%">
										&nbsp;&nbsp;机构名称：${sysOrg.orgname}
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
									<td width="70" style="cursor: hand"
										onMouseOver="this.style.backgroundImage='url(images/tool_bg1.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#DFAF0D'; "
										onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
										<table width="100%" border="0" cellpadding="0"
											cellspacing="0">
											<tr>
												<td align="center">
													<img src="<%=ctx%>/images/cz.gif" width="22"
														height="21">
												</td>
												<td>
													<a href="#" onclick="doReset();">重置</a>
												</td>
											</tr>
										</table>
									</td>
									<td width="70" style="cursor: hand"
										onMouseOver="this.style.backgroundImage='url(images/tool_bg1.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#DFAF0D'; "
										onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
										<table width="100%" border="0" cellpadding="0"
											cellspacing="0">
											<tr>
												<td align="center">
													<img src="<%=ctx%>/images/tj.gif" width="22"
														height="21">
												</td>
												<td>
													<a href="#" onclick="doSubmit();">提交</a>
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
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td valign="top" class="right" width="20%">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr class="right">
											<td id="tool" width="100%" align="right" height="30"
												class="operate_title">
												<img src="<%=ctx%>/images/add.gif" width="10" height="10">
												<a href="#" onclick="doEdit(1,'<%=ctx%>')">新增</a>
												<img src="<%=ctx%>/images/del.gif" width="10" height="10">
												<a href="#" onclick="doEdit(2,'<%=ctx%>')">删除</a>&nbsp;&nbsp;
											</td>
										</tr>
										<tr>
											<td>
												<fieldset>
													<iframe id="lefttree" name="lefttree"
														src="<%=ctx%>/sys/toTreeOrg.htm?orgid=${sysOrg.orgid}&scode=${sysOrg.scode}"
														width="100%" scrolling="no" frameborder="0"
														allowtransparency="true" marginheight="0" marginwidth="0"
														onload="document.all('lefttree').height=400;"></iframe>
												</fieldset>
											</td>
										</tr>
									</table>
								</td>
								<td valign="top" class="right" width="80%">
									<fieldset>
										<iframe id="rightContent" name="rightContent"
											src="<%=ctx%>/sys/toViewOrg.htm?orgid=${sysOrg.orgid}"
											width="100%" scrolling="no" frameborder="0"
											allowtransparency="true" marginheight="0" marginwidth="0" onload="document.all('rightContent').height=400;"></iframe>
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
	function goBack(){
		document.forms[0].action="<%=ctx%>/sys/org.htm";
		document.forms[0].submit();
	}
 
 	function doEdit(nFlag,code){
		var pno = document.frames['lefttree'].window.doBackValue(nFlag);
		// 树状结构新增节点
		if(nFlag==1){
			if(pno){}else{
				pno = "${sysOrg.scode }"
			}
			document.getElementById('rightContent').src="<%=ctx%>/sys/toAddOrg.htm?parentcode="+pno;
		}else if(nFlag==2){// 树状结构删除节点
			if(pno==""){
				alert("请选择要删除的节点!");
			}else{
				if(!confirm("确定要删除该节点及其子节点吗？")) return false;
				var url = "<%=ctx%>/sys/doDelOrgByCode.htm?Ids="+pno;
				$.post(url,{parentno:pno},function(data, textStatus){
					if(data.success){
						//alert("删除节点成功！");
						treeReload();
						$('#rightContent').attr("src","<%=ctx%>/sys/toViewOrg.htm?orgid=${sysOrg.orgid}");
					}	
				},'json');
			}
		}
	}
	
	function showNodeInfo(scode, id, title, param){
		if(id=="0"){
			$('#lefttree')[0].contentWindow.doUncheckedAll();
			$('#rightContent').attr("src","<%=ctx%>/sys/toViewOrg.htm?orgid=${sysOrg.orgid}");
			return;
		}
		$('#rightContent').attr("src", "<%=ctx%>/sys/toEditOrg.htm?scode="+scode);
	}
	
	function treeReload(){
		window.frames["lefttree"].location.reload();
		//$('#lefttree').attr("src","<%=ctx%>/sys/toTreeOrg.htm?orgid=${sysOrg.orgid}&orgcode=${sysOrg.orgcode}");
	}
	
	function doSubmit(){
	    window.frames["rightContent"].doSubmit();
	}
	
	function doReset(){
		window.frames["rightContent"].doReset();
	}
</script>
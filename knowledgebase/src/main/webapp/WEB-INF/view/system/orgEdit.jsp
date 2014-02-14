<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/common/taglibs.jsp"%>
		<%@include file="/common/jqueryValidate.jsp"%>
	</head>
	<body>
		<form action="" name="form1" id="form1" method="post">
			<input type="hidden" name="orgid" id="orgid" value="${sysOrg.orgid }"/>
			<input type="hidden" name="orgtype" id="orgtype" value="0"/>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top" bgcolor="DEE8F6" class="right">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td align="right" valign="top" class="czmb_2">
									<div class="tool" <c:if test="${not empty sysOrg.parentcode }">style="display:none;"</c:if>>
										<table border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="70" height="27" style="cursor: hand"
													onMouseOver="this.style.backgroundImage='url(images/tool_bg1.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#DFAF0D'; "
													onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
													<table width="100%" border="0" cellpadding="0"
														cellspacing="0">
														<tr>
															<td align="center">
																<img src="<%=ctx%>/images/back.gif"
																	width="22" height="21">
															</td>
															<td>
																<a href="#" class="v1" onclick="toGoBack()">返回</a>
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
									<div class="blankH10"></div>
									<table width="98%" border="0" align="center" cellpadding="0"
										cellspacing="0" class="border_blue">
										<tr>
											<td class="operate_title">
												<table width="98%" border="0" align="center" cellpadding="0"
													cellspacing="0">
													<tr>
														<td width="62%">
															机构信息
														</td>
														<td width="38%" align="right">
															<img src="<%=ctx%>/images/zk.gif" width="15"
																height="14">
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<div style="padding-top: 10px; padding-bottom: 10px;">
													<table width="90%" border="0" align="center"
														cellpadding="0" cellspacing="2" id="edittb">

														<tr align="center" onmouseout="reChangeBackground(this)"
															onmouseover="changeBackground(this)">
															<td width="30%" align="center">
																机构代码<font style="color:red">(*)</font>
															</td>
															<td width="70%" align="left">
																&nbsp;&nbsp;&nbsp;&nbsp;
																<input id="orgcode" name="orgcode"
																	style="width:200px; margin-left:2px;" value="${sysOrg.orgcode }"/>
															</td>
														</tr>
														 
														<tr align="center" onmouseout="reChangeBackground(this)"
															onmouseover="changeBackground(this)">
															<td width="30%" align="center">
																机构名称<font style="color:red">(*)</font>
															</td>
															<td width="70%" align="left">
																&nbsp;&nbsp;&nbsp;&nbsp;
																<input id="orgname" name="orgname"
																	style="width:200px; margin-left:2px;" value="${sysOrg.orgname }"/>
															</td>
														</tr>
														<tr align="center" onmouseout="reChangeBackground(this)"
															onmouseover="changeBackground(this)">
															<td width="30%" align="center">
																机构简称
															</td>
															<td width="70%" align="left">
																&nbsp;&nbsp;&nbsp;&nbsp;
																<input id="orgasname" name="orgasname"
																	style="width:200px; margin-left:2px;" value="${sysOrg.orgasname }"/>
															</td>
														</tr>
														<tr align="center" onmouseout="reChangeBackground(this)"
															onmouseover="changeBackground(this)">
															<td width="30%" align="center">
																联系电话
															</td>
															<td width="70%" align="left">
																&nbsp;&nbsp;&nbsp;&nbsp;
																<input id="orgtel" name="orgtel"
																	style="width:200px; margin-left:2px;" value="${sysOrg.orgtel }"/>
															</td>
														</tr>
														<tr align="center" onmouseout="reChangeBackground(this)"
															onmouseover="changeBackground(this)">
															<td width="30%" align="center">
																公司传真
															</td>
															<td width="70%" align="left">
																&nbsp;&nbsp;&nbsp;&nbsp;
																<input id="orgfax" name="orgfax"
																	style="width:200px; margin-left:2px;" value="${sysOrg.orgfax }"/>
															</td>
														</tr>
														<tr align="center" onmouseout="reChangeBackground(this)"
															onmouseover="changeBackground(this)">
															<td width="30%" align="center">
																公司地址
															</td>
															<td width="70%" align="left">
																&nbsp;&nbsp;&nbsp;&nbsp;
																<input id="orgaddress" name="orgaddress"
																	style="width:500px; margin-left:2px;" value="${sysOrg.orgaddress }"/>
															</td>
														</tr>
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
	$(document).ready(function(){
		var options = {
			beforeSubmit: function() {	
				return true;
			},
			success: function(responseText, statusText) {	
				if(!(responseText.success)) {
					alert(responseText);
				}else {
					var parentcode = "${sysOrg.parentcode }";
					if(parentcode){
						alert("保存成功！");
						parent.treeReload();
					}else{
						toGoBack();
					}
				}
			},
			error: function(responseText, statusText) {		
				alert("保存失败！");											
			},
			dataType: 'json'
		}	
		$('#form1').ajaxForm(options);	
	

	});
	function validTextValue(){
	}
	
	function doSubmit(){
		$('#form1').attr("action","<%=ctx%>/sys/doEditOrg.htm");
	    $('#form1').submit();
	}
	
	function toGoBack(){	
	    //$('#form1').attr("action","<%=ctx%>/sys/org.htm");
	    //$('#form1')[0].submit();
	    this.location.href="<%=ctx%>/sys/org.htm";
	}
	
	function doReset(){
		$('#form1').reset();
	}
</script>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/common/taglibs.jsp"%>
		<%@include file="/common/jqueryValidate.jsp"%>
	</head>
	<body>
		<form action="" name="form1" id="form1" method="post">
			<input type="hidden" name="roleid" id="roleid" value="${sysRole.roleid }"/>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top" bgcolor="DEE8F6" class="right">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td align="right" valign="top" class="czmb_2">
									<div class="tool">
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
															角色信息
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
																角色名称
															</td>
															<td width="70%" align="left">
																&nbsp;&nbsp;&nbsp;&nbsp;
																<input id="rolename" name="rolename"
																	cssClass="mytext" theme="simple" value="${sysRole.rolename }"/>
															</td>
														</tr>
														<tr align="center" onmouseout="reChangeBackground(this)"
															onmouseover="changeBackground(this)">
															<td width="30%" align="center">
																角色描述
															</td>
															<td width="70%" align="left">
																&nbsp;&nbsp;&nbsp;&nbsp;
																<textarea id="roledesc" name="roledesc"
																	cssClass="mytext" size="120" theme="simple">${sysRole.roledesc }</textarea>
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
	$(document).ready(function() {
		var options = {
			beforeSubmit: function() {	
				return true;
			},
			success: function(responseText, statusText) {	
				if(!(responseText.success)) {
					alert(responseText);
				}else {
					alert("修改成功！");		
					toGoBack();
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
		$('#form1').attr("action","<%=ctx%>/sys/doEditRole.htm");
	    $('#form1').submit();
	}
	
	function toGoBack(){	
	    window.location.href = "<%=ctx%>/sys/role.htm";
	}
	
	function doReset(){
		$('#form1')[0].reset();
	}
</script>
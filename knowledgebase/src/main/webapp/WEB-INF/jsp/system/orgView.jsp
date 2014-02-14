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
								<td align="right" valign="top" class="czmb_2">
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
																机构代码
															</td>
															<td width="70%" align="left">
																&nbsp;&nbsp;&nbsp;&nbsp;
																${sysOrg.orgcode }
															</td>
														</tr>
														<tr align="center" onmouseout="reChangeBackground(this)"
															onmouseover="changeBackground(this)">
															<td width="30%" align="center">
																机构类型
															</td>
															<td width="70%" align="left">
																&nbsp;&nbsp;&nbsp;&nbsp;
																<c:choose> 
																  <c:when test="${sysOrg.orgtype eq '0' }"> 
																        总公司
																  </c:when> 
																  <c:when test="${sysOrg.orgtype eq '1' }"> 
																        分公司 
																  </c:when> 
																   <c:when test="${sysOrg.orgtype eq '2' }"> 
																        部门
																  </c:when> 
																</c:choose> 
															</td>
														</tr>
														<tr align="center" onmouseout="reChangeBackground(this)"
															onmouseover="changeBackground(this)">
															<td width="30%" align="center">
																机构名称
															</td>
															<td width="70%" align="left">
																&nbsp;&nbsp;&nbsp;&nbsp;
																${sysOrg.orgname }
															</td>
														</tr>
														<tr align="center" onmouseout="reChangeBackground(this)"
															onmouseover="changeBackground(this)">
															<td width="30%" align="center">
																机构简称
															</td>
															<td width="70%" align="left">
																&nbsp;&nbsp;&nbsp;&nbsp;
																${sysOrg.orgasname }
															</td>
														</tr>
														<tr align="center" onmouseout="reChangeBackground(this)"
															onmouseover="changeBackground(this)">
															<td width="30%" align="center">
																联系电话
															</td>
															<td width="70%" align="left">
																&nbsp;&nbsp;&nbsp;&nbsp;
																${sysOrg.orgtel }
															</td>
														</tr>
														<tr align="center" onmouseout="reChangeBackground(this)"
															onmouseover="changeBackground(this)">
															<td width="30%" align="center">
																传真
															</td>
															<td width="70%" align="left">
																&nbsp;&nbsp;&nbsp;&nbsp;
																${sysOrg.orgfax }
															</td>
														</tr>
														<tr align="center" onmouseout="reChangeBackground(this)"
															onmouseover="changeBackground(this)">
															<td width="30%" align="center">
																地址
															</td>
															<td width="70%" align="left">
																&nbsp;&nbsp;&nbsp;&nbsp;
																${sysOrg.orgaddress }
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
	});
</script>
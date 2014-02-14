<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<%@include file="/common/taglibs.jsp"%>
		<script language="javascript">
			$(function(){
			});
		</script>
	</head>
	<body style="padding:0;height:100%;width:100%;">
		<form action="<%=ctx%>/sys/user.htm" method="post">
			<input type="hidden" id="pagerAction" name="pagerAction">
			<div class="easyui-layout" style="position:absolute;left:0;top:0;border:0;width:100%;height:100%;" scroll="no">
				<div region="north" border="false" style="overflow: hidden; height: 60px; background: #ffffff; font-family: Verdana, 微软雅黑, 黑体">
					<fieldset style="width:98%;margin:auto;">
							<legend>
								查询条件
							</legend>
							<table width="98%" border="0" align="center" cellpadding="0" cellspacing="2">										
								<tr>
									<TD width="10%">
										用户名称
									</TD>
									<TD width="60%">
										<input id="username" name="user.username" value="${username}"/>
									</TD>
									<TD width="30%" align="right">
										<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSubmit()"></a>
									</TD>
								</tr>
							</table>
					</fieldset>
					
				</div>
				<div id="mainPanle" region="center" border="false" style="background: #ffff; overflow-y: auto; font-family: Verdana, 微软雅黑, 黑体">
						<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="border_blue">										
							<tr>
								<td class="operate_title_ui">
									<%@include file="/common/pageToolBar.jsp"%>
								</td>
							</tr>
							<tr>
								<td>
									<table width="100%" class="list_table">
										<thead class="datagrid-header">
											<tr align="center">
												<td width="5%">
													<input type="checkbox" id="cbxAll" name="cbxAll" onclick="selectForCbxAll(this,'cbxItem')" />																	
												</td>
												<td width="5%">
													序号
												</td>
												<td width="15%">
													登录账号
												</td>
												<td width="10%">
													姓名
												</td>
												<td width="10%">
													用户编号
												</td>
												<td width="10%">
													所属机构
												</td>
												<td width="5%">
													性别
												</td>
												<td width="15%">
													联系方式
												</td>
												<!--  td width="10%">
													创建人
												</td-->
												<td width="15%">
													创建日期
												</td>
											</tr>
										</thead>
										<tbody>
										<c:forEach items="${pagerResult.items }" var="user" varStatus="status">
											<tr align="center" onMouseOver="changeto()" onmouseout="changeback()">															
												<td>
													<input type="checkbox" id="cbxItem" name="cbxItem" value="${user.userid }" onclick="selectForCbxItem(this,'cbxAll')" />																	
												</td>
												<td>
													${status.index+1}
												</td>
												<td>
													&nbsp;
													${user.loginuser }
												</td>
												<td>
													&nbsp;
													${user.username }
												</td>
												<td>
													&nbsp;
													${user.usercode }
												</td>
												<td>
													&nbsp;
													${user.orgname }
												</td>
												<td>
													&nbsp;
													${user.usersexString}
												</td>
												<td>
													&nbsp;
													${user.usermp}
												</td>
												<!--td>
													&nbsp;
													${user.creater }
												</td-->
												<td >
													&nbsp;
													${user.createtime }
												</td>
											</tr>
										</c:forEach>
										</tbody>
									</table>
								</td>
							</tr>
							<tfoot>
								<td>
									<%@include file="/common/pageHibernate.jsp"%>
								</td>
							</tfoot>
						</table>
				</div>
			</div>
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
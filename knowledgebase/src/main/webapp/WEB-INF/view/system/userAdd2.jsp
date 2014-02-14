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
	<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
		<div region="north" border="false" style="overflow: hidden; height: 26px; background: #efefef; font-family: Verdana, 微软雅黑, 黑体">
			<span style="float: right; padding-right: 20px;"> 
				<a href="javascript:void(0)" id="btnback" class="easyui-linkbutton" iconCls="icon-back" plain="true">返回</a> 
				<a href="javascript:void(0)" id="btnredo" class="easyui-linkbutton" iconCls="icon-redo" plain="true">重置</a> 
				<a href="javascript:void(0)" id="btnsave" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存</a> 
			</span>
		</div>
		<div id="mainPanle" region="center" icon="icon-save" title="用户信息-新增" border="true" style="background: #ffff; overflow-y: auto; padding-top: 10px; padding-bottom: 10px; font-family: Verdana, 微软雅黑, 黑体">
			<form id="ff" method="post">
				<fieldset style="width:85%;margin:auto;">
					<legend>
						用户信息
					</legend>
					<table width="95%" border="0" align="center" cellpadding="0" cellspacing="2" class="edittb">
						<tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
							<td width="30%" align="center">
								登录帐号
								<font style="color: red">(*)</font>
							</td>
							<td width="70%" align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="text" id="useraccount" name="user.loginuser"
									style="width: 200px;">
							</td>
						</tr>
						<tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
							<td width="30%" align="center">
								登录密码
								<font style="color: red">(*)</font>
							</td>
							<td width="70%" align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="password" id="loginpwd" name="user.loginpwd"
									style="width: 200px;">
							</td>
						</tr>
						<tr align="center" onmouseout="reChangeBackground(this)"
							onmouseover="changeBackground(this)">
							<td width="30%" align="center">
								确认密码
								<font style="color: red">(*)</font>
							</td>
							<td width="70%" align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="password" id="upwd" name="upwd"
									style="width: 200px;">
							</td>
						</tr>
						<tr align="center" onmouseout="reChangeBackground(this)"
							onmouseover="changeBackground(this)">
							<td width="30%" align="center">
								员工姓名
								<font style="color: red">(*)</font>
							</td>
							<td width="70%" align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="text" id="username" name="user.username"
									style="width: 200px;">
							</td>
						</tr>
						<tr align="center" onmouseout="reChangeBackground(this)"
							onmouseover="changeBackground(this)">
							<td width="30%" align="center">
								员工编号
								<font style="color: red">(*)</font>
							</td>
							<td width="70%" align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="text" id="usercode" name="user.usercode"
									style="width: 200px;">
							</td>
						</tr>
						<tr align="center" onmouseout="reChangeBackground(this)"
							onmouseover="changeBackground(this)">
							<td width="30%" align="center">
								所属机构
								<font style="color: red">(*)</font>
							</td>
							<td width="70%" align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="text" id="orgname" name="user.orgname"
									style="width: 200px;" readonly="true">
							</td>
						</tr>
						<tr align="center" onmouseout="reChangeBackground(this)"
							onmouseover="changeBackground(this)">
							<td width="30%" align="center">
								用户类型
								<font style="color: red">(*)</font>
							</td>
							<td width="70%" align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<select id="usertype" name="user.usertype" style="width: 205px;">
									<option value="-1">
										请选择
									</option>
									<option value="1">
										系统用户
									</option>
									<option value="2">
										代理商
									</option>
									<option value="3">
										区域商务人员
									</option>
									<option value="4">
										中央商务人员
									</option>
								</select>
							</td>
						</tr>
						<tr align="center" onmouseout="reChangeBackground(this)"
							onmouseover="changeBackground(this)" id="provinceclass"
							style="display: none">
							<td width="30%" align="center">
								管理省份
								<font style="color: red">(*)</font>
							</td>
							<td width="70%" align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="hidden" id="orgid" name="user.orgid">
								<input type="text" id="provincename" name="user.provincename"
									style="width: 200px;" readonly="true">
								<img src="<%=ctx%>/images/icon.gif" alt="请选择省份"
									style="border-width: 0px; cursor: hand"
									onclick="return openProvinceList('<%=ctx%>');">
							</td>
						</tr>
						<tr align="center" onmouseout="reChangeBackground(this)"
							onmouseover="changeBackground(this)">
							<td width="30%" align="center">
								电子邮箱
								<font style="color: red">(*)</font>
							</td>
							<td width="70%" align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="text" id="useremail" name="user.useremail"
									style="width: 200px;">
							</td>
						</tr>
						<tr align="center" onmouseout="reChangeBackground(this)"
							onmouseover="changeBackground(this)">
							<td width="30%" align="center">
								性别
							</td>
							<td width="70%" align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="hidden" id="usersex" name="user.usersex">
								<input type="radio" name="rdosex" value="1" checked="true"
									onclick="setHiddenValue('usersex',this.value)">
								男&nbsp;&nbsp;
								<input type="radio" name="rdosex" value="2"
									onclick="setHiddenValue('usersex',this.value)">
								女
							</td>
						</tr>
						<tr align="center" onmouseout="reChangeBackground(this)"
							onmouseover="changeBackground(this)">
							<td width="30%" align="center">
								电话
							</td>
							<td width="70%" align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="text" id="usertel" name="user.usertel"
									style="width: 200px;">
							</td>
						</tr>
						<tr align="center" onmouseout="reChangeBackground(this)"
							onmouseover="changeBackground(this)">
							<td width="30%" align="center">
								手机
							</td>
							<td width="70%" align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="text" id="usermp" name="user.usermp"
									style="width: 200px;">
							</td>
						</tr>
						<tr align="center" onmouseout="reChangeBackground(this)"
							onmouseover="changeBackground(this)">
							<td width="30%" align="center">
								QQ
							</td>
							<td width="70%" align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="text" id="userqq" name="user.userqq"
									style="width: 200px;">
							</td>
						</tr>
						<tr align="center" onmouseout="reChangeBackground(this)"
							onmouseover="changeBackground(this)">
							<td width="30%" align="center">
								MSN
							</td>
							<td width="70%" align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="text" id="usermsn" name="user.usermsn"
									style="width: 200px;">
							</td>
						</tr>
					</table>
				</fieldset>
			</form>
		</div>
	</body>
</html>
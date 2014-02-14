<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="/common/taglibs.jsp"%>
	<%@include file="/common/jqueryValidate.jsp"%>
	<script type="text/javascript" src="<%=ctx%>/js/manifier.js"></script>
</head>
<body>
<form action="" name="addform" id="addform" method="post">
<input type="hidden" name="userid" id="userid" value="${sysUser.userid }"/>
<input type = "hidden" id="orgid" name="user.orgid" value="${sysUser.orgid}" style="width:200px;">
<input type = "hidden" id="provinceid" name="user.provinces" value="${sysUser.provinces}" style="width:200px;">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" bgcolor="DEE8F6" class="right">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="right" valign="top" class="czmb_2">
            	<div class="tool">
              <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="70" height="27" style="cursor:hand" onMouseOver="this.style.backgroundImage='url(images/tool_bg1.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#DFAF0D'; "onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
                  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="center"><img src="<%=ctx%>/images/back.gif" width="22" height="21"></td>
                        <td><a href="#" class="v1" onclick="toGoBack()">返回</a></td>
                      </tr>
                    </table>
                  </td>
                  <!--  td width="70" style="cursor:hand" onMouseOver="this.style.backgroundImage='url(images/tool_bg1.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#DFAF0D'; "onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
                  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="center"><img src="<%=ctx%>/images/cz.gif" width="22" height="21"></td>
                        <td><a href="#">重置</a></td>
                      </tr>
                    </table>
                  </td-->
                  <td width="70" style="cursor:hand" onMouseOver="this.style.backgroundImage='url(images/tool_bg1.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#DFAF0D'; "onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
                  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="center"><img src="<%=ctx%>/images/tj.gif" width="22" height="21"></td>
                        <td><a href="#" id="aSubmit">提交</a></td>
                      </tr>
                     </table>
                  </td>
                </tr>
              </table>
            </div>
            <div class="blankH10"></div>
            <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="border_blue">
                <tr>
                  <td class="operate_title">
                  	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td width="62%">用户信息-修改</td>
                        <td width="38%" align="right"><img src="<%=ctx%>/images/zk.gif" width="15" height="14"></td>
                      </tr>
                    </table>
                  </td>
                </tr>
                <tr>
                  <td>                  	
                  	<div  style="padding-top:10px; padding-bottom:10px;">
                   	<table width="90%" border="0" align="center" cellpadding="0" cellspacing="2" id="edittb">
									      <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
													<td class="result_content" width="30%" align="center">登录帐号<font style="color:red">(*)</font></td>
													<td class="result_content" width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp; 
														<input type = "text" id="useraccount" name="user.loginuser"  readonly value="${sysUser.loginuser}" style="width:200px;">
													</td>
									      </tr>
									      <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
													<td class="result_content" width="30%" align="center">登录密码<font style="color:red">(*)</font></td>
													<td class="result_content" width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
														<input type = "password" id="loginpwd" name="user.loginpwd" value="${sysUser.loginpwd}" style="width:200px;">
													</td>
									      </tr>
									      <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
													<td class="result_content" width="30%" align="center">确认密码<font style="color:red">(*)</font></td>
													<td class="result_content" width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
														<input type = "password" id="upwd" name="upwd" value="${sysUser.loginpwd}" style="width:200px;">
													</td>
									      </tr>
									      <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
													<td class="result_content" width="30%" align="center">员工姓名<font style="color:red">(*)</font></td>
													<td class="result_content" width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
														<input type = "text" id="username" name="user.username" value="${sysUser.username}" style="width:200px;">
													</td>
									      </tr>
									      <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
													<td class="result_content" width="30%" align="center">员工编号<font style="color:red">(*)</font></td>
													<td class="result_content" width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
														<input type = "text" id="usercode" name="user.usercode" value="${sysUser.usercode}"  style="width:200px;">
													</td>
									      </tr>
									      <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
													<td class="result_content" width="30%" align="center">所属机构<font style="color:red">(*)</font></td>
													<td class="result_content" width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
														<input type = "text" id="orgname" name="user.orgname" value="${sysOrg.orgname }"  style="width:200px;" readonly>
														<img src="<%=ctx%>/images/icon.gif" alt="请选择机构" 
																style="border-width: 0px; cursor: hand" onclick="return openOrg();">
													</td>
									     </tr>
									     <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
											<td class="result_content" width="30%" align="center">用户类型<font style="color:red">(*)</font></td>
											<td class="result_content" width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
												<select id="usertype" name="user.usertype" defaultValue="${sysUser.usertype}" style="width:200px;" >
													<option value="-1">请选择</option>
													<option value ="1" <c:if test="${sysUser.usertype=='1'}">selected </c:if>>系统用户</option>
													<option value ="2" <c:if test="${sysUser.usertype=='2'}">selected </c:if>>代理商</option>
													<option value ="3" <c:if test="${sysUser.usertype=='3'}">selected </c:if>>区域商务人员</option>
													<option value ="4" <c:if test="${sysUser.usertype=='4'}">selected </c:if>>中央商务人员</option>
												</select>
											</td>
								   		</tr>
								   		<tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)" id="provinceclass" style="display:none">
											<td class="result_content" width="30%" align="center">管理省份<font style="color:red">(*)</font></td>
											<td class="result_content" width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
											<input type = "hidden" id="orgid" name="user.orgid">
											<input type = "text" id="provincename" name="user.provincename" value = "${provincename }" style="width:200px;" readonly="true">
											<img src="<%=ctx%>/images/icon.gif" alt="请选择省份" style="border-width: 0px; cursor: hand" onclick="return openProvinceList('<%=ctx%>');">
											</td>
								   		</tr>
									      <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
												<td class="result_content" width="30%" align="center">电子邮箱<font style="color:red">(*)</font></td>
												<td class="result_content" width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
													<input type = "text" id="useremail" name="user.useremail" value="${sysUser.useremail}" style="width:200px;">
												</td>
									     </tr> 
									     
									     <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
														<td class="result_content" width="30%" align="center">性别</td>
														<td class="result_content" width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
															<input type = "hidden" id="usersex" name="user.usersex" value="${sysUser.usersex}">
															<input type="radio" name="rdosex" value="1" <c:if test="${sysUser.usersex=='1'}">checked </c:if> onclick="setHiddenValue('usersex',this.value)">男&nbsp;&nbsp;
															<input type="radio" name="rdosex" value="2" <c:if test="${sysUser.usersex=='2'}">checked </c:if> onclick="setHiddenValue('usersex',this.value)">女
														</td>
									      </tr>
									    	<tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
													<td class="result_content" width="30%" align="center">电话</td>
													<td class="result_content" width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
														<input type = "text" id="usertel" name="user.usertel" value="${sysUser.usertel}" style="width:200px;">
													</td>
									      </tr>
									      <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
													<td class="result_content" width="30%" align="center">手机</td>
													<td class="result_content" width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
														<input type = "text" id="usermp" name="user.usermp" value="${sysUser.usermp}" style="width:200px;">
													</td>
									      </tr>
									      
									      <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
													<td class="result_content" width="30%" align="center">QQ</td>
													<td class="result_content" width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
														<input type = "text" id="userqq" name="user.userqq" value="${sysUser.userqq}" style="width:200px;">
													</td>
									      </tr>
									      <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
													<td class="result_content" width="30%" align="center">MSN</td>
													<td class="result_content" width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
														<input type = "text" id="usermsn" name="user.usermsn" value="${sysUser.usermsn}" style="width:200px;">
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
	function setHiddenValue(hiddenname,svalue) {
		$("#"+hiddenname).val(svalue);
	}
	
	$(document).ready(function() { 
		var usertype='${sysUser.usertype}';
		if(usertype=='3'||usertype=='4') {
			$("#provinceclass").show();
		}
		$("#usertype").change(function() {
			if($(this).val()=="3"||$(this).val()=="4") {
				$("#provinceclass").show();
			} else {
				$("#provinceclass").hide();
			}
       	});
		var options = {
			beforeSubmit: function() {	
				return true;
			},
			success: function(responseText, statusText) {	
				alert("保存成功！");	
			},
			error: function(responseText, statusText) {											
				alert("保存失败！");											
			},
			dataType: 'json'
		};	
		$("#addform").ajaxForm(options);
		
		// 手机号码验证    
		jQuery.validator.addMethod("isMobile", function(value, element) {    
		  var length = value.length;    
		  return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/.test(value));    
		}, "请正确填写您的手机号码"); 
		// 电话号码验证    
		jQuery.validator.addMethod("isPhone", function(value, element) {    
		  var tel = /^(\d{3,4}-?)?\d{7,9}$/g;    
		  return this.optional(element) || (tel.test(value));    
		}, "请正确填写您的电话号码");  
		
       	var validator = $("#addform").validate({  
			/* 错误信息的显示位置 */    
       	    debug: true,
			showErrors: showErrors, 
   	  		rules: {    
	  			  'user.loginuser': {                 
		       	  	required: true,
		       	  	maxlength:64
		       	  },     
		       	  'user.username': {                 
		       	  	required: true,
		       	  	maxlength:64
		       	  }, 
		       	  'user.usercode': {                 
		       	  	required: true,
		       	  	maxlength:64
		       	  }, 
		       	  'user.orgname': {                 
		       	  	required: true
		       	  }, 
		       	  'user.postid': {                 
		       	  	required: true,
		       	  	maxlength:64
		       	  }, 
		       	  'user.loginpwd': {                 
		       	  	required: true,
		       	  	maxlength:64                       
		       	  },
		       	  'upwd': {     
		       	  	equalTo: "#loginpwd"                      
		       	  },
		       	  'user.useremail': { 
		       	  	maxlength: 75,    
		       	  	email: true                      
		       	  },
		       	  'user.usertel': {     
		       	  	maxlength: 60,
		       	  	isPhone: true                     
		       	  }, 
		       	  'user.usermp': {     
		       	  	maxlength: 60,
		       	  	isMobile: true                   
		       	  }, 
		       	  'user.userjob': {     
		       	  	maxlength: 60                     
		       	  },  
		       	  'user.userqq': {     
		       	  	maxlength: 30,
		       	  	digits:true                     
		       	  },  
		       	  'user.usermsn': {     
		       	  	maxlength: 45,
		       	  	digits:true                      
		       	  }             
			},
			messages: { 
				'user.loginuser': {                 
	       	 	 	required:"帐号不能为空！",
		       	 	maxlength:"帐号最大长度为64!"
	       	 	 }, 
	       	 	 'user.username': {                 
	       	  	 	required:"姓名不能为空！",
	       	  	 	maxlength:"姓名最大长度为64!"
		       	  },
		       	  'user.usercode': {                 
		       	  	required:"用户编码不能为空！",
	       	  	 	maxlength:"用户编码最大长度为64!"
		       	  }, 
		       	  'user.orgname': {                 
		       	  	required:"机构不能为空！"
		       	  },  
		       	  'user.postid': {                 
		       	  	required:"岗位不能为空！",
	       	  	 	maxlength:"岗位最大长度为64!"
		       	  },  
	       	 	 'user.loginpwd': {                 
	       	  	 	required:"密码不能为空！",
	       	  	 	maxlength:"密码最大长度为64!"
		       	  },             
		       	 'upwd': {                 
		       	  	equalTo:"两次输入密码不一致！"                           
		       	  },
		       	  'user.useremail': {   
		       	  	maxlength: "邮箱最大长度为64!",                          
		       	  	email:"邮箱格式不正确！"                         
		       	  },
		       	  'user.usertel': {                             
		       	  	maxlength: "联系电话最大长度为64!",
		       	  	isPhone: "电话号码格式不正确！"                         
		       	  },
		       	  'user.usermp': {                             
		       	  	maxlength:"移动电话最大长度为60",
		       	  	isMobile: "移动电话格式不正确！"                          
		       	  },
		       	  'user.userjob': {                             
		       	  	maxlength:"工作单位最大长度为1000"                        
		       	  },
		       	  'user.userqq': {                             
		       	  	maxlength:"QQ号最大长度为30",
		       	  	digits:"QQ格式不正确！"                      
		       	  },
		       	  'user.usermsn': {                             
		       	  	maxlength:"MSN最大长度为40",     
		       	  	digits:"MSN格式不正确！"                   
		       	  }
       	   }
       	   
		});
		$("#aSubmit").click(function() { 
			  if(validator.form()) {
			  	doSubmit();
			  }
		});
	});
	function doSubmit() {
		if(!window.confirm("确定保存？"))return;
		document.forms[0].action="<%=ctx%>/sys/userEdit.htm";
		$("#addform").submit();
	}
	function toGoBack() {	
	  	window.location.href ="<%=ctx%>/sys/user.htm";
	}
	
	function onReset() {
		document.forms[0].reset();	
	}
	
	function openOrg() {
		window.open("<%=ctx%>/sys/openOrgTree.htm","机构信息",'width=850,height=400,left=250,top=100,Status=yes,toolbar=no,menubar=no,resizable=yes,location=no,scrollbars=1')
	}
</script>
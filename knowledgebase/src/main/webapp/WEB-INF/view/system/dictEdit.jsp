<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="/common/taglibs.jsp"%>
	<%@include file="/common/jqueryValidate.jsp"%>
</head>
<body>
<form action="" name="addform" id="addform" method="post">
<input type="hidden" name="dictid" id="dictid" value="${sysDict.dictid }"/>
<input type="hidden" name="dictcode" id="dictcode" value="${sysDict.dictcode }"/>
<input type="hidden" name="enabled" id="enabled" value="${sysDict.enabled }"/>
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
						<td class="result_content" width="30%" align="center">字典标识<font style="color:red">(*)</font></td>
						<td class="result_content" width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
							<input type = "text" id="dictkey" name="dictkey" value="${sysDict.dictkey}" cssClass="mytext" theme="simple" readonly>
						</td>
				      </tr>
				      <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
						<td class="result_content" width="30%" align="center">字典名称<font style="color:red">(*)</font></td>
						<td class="result_content" width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
							<input type = "text" id="dictname" name="dictname" value="${sysDict.dictname}" cssClass="mytext" theme="simple">
						</td>
				      </tr>
				      <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
						<td class="result_content" width="30%" align="center">备注</td>
						<td class="result_content" width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
							<input type = "text" id="descinfo" name="descinfo" value="${sysDict.descinfo}" cssClass="mytext" theme="simple">
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
				alert("保存成功！");	
				location.href="<%=ctx%>/sys/dict.htm";
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
	  			  'dictkey': {                 
		       	  	required: true,
		       	  	maxlength:45
		       	  },     
		       	  'dictname': {                 
		       	  	required: true,
		       	  	maxlength:120
		       	  }, 
		       	  'descinfo': {                 
		       	  	maxlength:120                       
		       	  }          
			},
			messages: { 
				'dictkey': {                 
	       	 	 	required:"字典编号不能为空！",
		       	 	maxlength:"字典编号最大长度为45!",
		       	 	remote:"该字典编号已存在!"   
	       	 	 }, 
	       	 	 'dictname': {                 
	       	  	 	required:"字典名称不能为空！",
	       	  	 	maxlength:"字典名称最大长度为120!"
		       	  }, 
	       	 	 'descinfo': {                 
	       	  	 	maxlength:"备注最大长度为120!"
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
		if(!window.confirm("确定要保存吗？\n\n请确认！"))return;
		document.forms[0].action="<%=ctx%>/sys/dictEdit.htm";
		$("#addform").submit();
	}
	function toGoBack() {	
	  	window.location.href ="<%=ctx%>/sys/dict.htm";
	}
	
	function onReset() {
		document.forms[0].reset();	
	}
</script>
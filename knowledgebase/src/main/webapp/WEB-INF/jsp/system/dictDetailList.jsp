<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<%@include file="/common/taglibs.jsp"%>
	<script type="text/javascript" src="<%=ctx%>/js/jquery/jquery.form.js"></script>
	<script type="text/javascript"
		src="<%=ctx%>/js/jquery/jquery-validate/jquery.validate.js"></script>
	<script type="text/javascript"
		src="<%=ctx%>/js/jquery/jquery-validate/additional-methods.js"></script>
	<script type="text/javascript"
		src="<%=ctx%>/js/jquery/jquery-validate/jquery.metadata.js"></script>
	<script type="text/javascript"
		src="<%=ctx%>/js/jquery/jquery-validate/jquery.validate.plu.js"></script>
</head>
  <body>
  	<form action="dictController" method="Post">
  	<input type="hidden" name="dictpcode" id="dictpcode" value="${dictpcode}"/>
  	<input type="hidden" name="dictpkey" id="dictpkey" value="${dictpkey}"/>
  	<input type="hidden" name="dictpname" id="dictpname" value="${dictpname}"/>
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
					                        <td><a href="#" onclick="toGoBack()">返回</td>
					                      </tr>
					                    </table>
					                  </td>
					                </tr>
					              </table>
				              </div>
				              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="border_blue">
			  					<tr>
			  						<td class="operate_title">
									<%@ page language="java" pageEncoding="utf-8"%>
									<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
									<html>
									  <head>
									  	<%String sContext = request.getContextPath(); %>
									    <title></title>
										  </head>
										  <body>
									    		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
									              <tr>
									               <td width="62%">字典明细列表</td>
									               <td width="38%">
									               	<table border="0" align="right" cellpadding="0" cellspacing="0">
									                   <tr>
									                    <td width="18"><img src="<%=sContext%>/images/del.gif" width="10" height="10"><br></td>
									                    <td width="35"><a href="#" onclick="doDelete()">删除</a><br></td>
									                   </tr>
									                  </table>
									               <br></td>
									              </tr>
									            </table>
										  </body>
										</html>	
			  						</td>
			  					</tr>
				  				<tr>
				  					<td>
				  						<div id="listdiv" class="listdiv">
				  							<table width="100%"  border="0" cellspacing="0" cellpadding="0" id="dictTbl">
				  								<tr align="center">
				  									<td class="list_title" nowrap width="10%">
				  										<input type="checkbox"  id="cbxAll" name="cbxAll" onclick="selectForCbxAll(this,'cbxItem')"/>
				  									</td>
				  									<td class="list_title" nowrap width="20%">父类编号</td>
				  									<td class="list_title" nowrap width="20%">父类名称</td>
				  									<td class="list_title" nowrap width="20%">字典编号</td>
				  									<td class="list_title" nowrap width="20%">字典名称</td>
				  									<td class="list_title" nowrap width="10%">操作</td>
				  								</tr>
				  								<tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
				  										<td class="list_content" nowrap bgcolor="#FFFFFF">
				  											录入行：
				  										</td>
				  										<td class="list_content" id = "dictpcode" nowrap bgcolor="#FFFFFF">
															${dictpkey}
				  										</td>
				  										<td class="list_content" nowrap bgcolor="#FFFFFF">
															${dictpname}
				  										</td>
				  										<td class="list_content" nowrap bgcolor="#FFFFFF">
				  											<input type = "text" id="dictkey" name="dictkey" title="base"  cssClass="mytext" theme="simple" >
				  										</td>
				  										<td class="list_content" nowrap bgcolor="#FFFFFF">
				  											<input type = "text" id="dictname" name="dictname"   cssClass="mytext" theme="simple">
				  										</td>
				  										<td class="list_content" nowrap bgcolor="#FFFFFF">
				  											<a href="#" onclick="doSubmit(this)" >保存</a>
				  										</td>
				  									</tr>
				  								<c:forEach items="${dictList}" var="dict" varStatus="status">
				  									<tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
				  										<td class="list_content" nowrap bgcolor="#FFFFFF">
				  											<input type="checkbox"  id="cbxItem" name="cbxItem" value='${dict.dictid}' onclick="selectForCbxItem(this,'cbxAll')">
				  										</td>
				  										<td class="list_content" id = "dictpcode" nowrap bgcolor="#FFFFFF">
															${dictpkey}
				  										</td>
				  										<td class="list_content" nowrap bgcolor="#FFFFFF">
															${dictpname}
				  										</td>
				  										<td class="list_content" nowrap bgcolor="#FFFFFF">
				  											<input type = "text" id="dictkey" name="dictkey" title="${status.index+1}" value="${dict.dictkey}" cssClass="mytext" theme="simple">
				  										</td>
				  										<td class="list_content" nowrap bgcolor="#FFFFFF">
				  											<input type = "text" id="dictname" name="dictname" value="${dict.dictname}" cssClass="mytext" theme="simple">
				  										</td>
				  										<td class="list_content" nowrap bgcolor="#FFFFFF">
				  											<a href="#" onclick="doSubmit(this)" id="${status.index+1}">保存</a>
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
$(function(){
	//doAdd();
});
	
	function doSubmit(obj) {
		var tem = $(obj).parent().parent();;
		var dataid = '';
		var dictpcode =$('#dictpcode').val();
		var dictpname =$('#dictpname').val();
		var dictpkey =$('#dictpkey').val();
		var dictkey = tem.find("input[id='dictkey']").val();
		var selectedArray = $("input[id='dictkey']");
		var run = true;
		$.each(selectedArray, function(index, domEle) {
			if($(domEle).attr("title")==tem.find("input[id='dictkey']").attr("title")) {
				return;
			} else {
				if(tem.find("input[id='dictkey']").val()==$(domEle).val()) {
					alert("已有相同的字典编码!");
					tem.find("input[id='dictkey']").val('');
					run = false;
				}
			} 
		});
		if(run==false) return;
		var dictname = tem.find("input[id='dictname']").val();
		var sMsg="";
		var pat=new RegExp("[^a-zA-Z0-9\_\u4e00-\u9fa5]","i"); 
		if(dictkey.trim().length<1) {
			sMsg+="字典编号不能为空。\n";
		}
	    if(pat.test(dictkey.trim())==true||escape(dictkey).indexOf("%u")!=-1) { 
	        sMsg+="字典编号输入含有非法字符!\n"; 
	    }	  
	    if(dictkey.trim().length>120) {
	     	sMsg+="字典编号最大字符长度不能超过120。\n";
	    }	  
	    if(dictname.trim().length<1) {
	  		sMsg+="字典名称不能为空。\n";
	    }
	    if(pat.test(dictname.trim())==true) { 
	        sMsg+="字典名称输入含有非法字符!\n"; 
	    }
	    if(dictname.trim().length>120) {
	  		sMsg+="字典名称最大字符长度不能超过120。\n";
	    }
	    if(sMsg!="") {
		    alert(sMsg);
		    return;	
	    }		
	    var dictid = tem.find("input[id='cbxItem']").val();
	    var src="<%=ctx%>/sys/dictAddItems.htm?dictpcode="+dictpcode+"&dictpkey="+dictpkey+"&dictpname="+escape(encodeURIComponent(dictpname))+"&dictkey="+dictkey+"&dictname="+escape(encodeURIComponent(dictname));
	    if(dictid!=null&&dictid!=''&&typeof dictid!=undefined) {
	    	src += "&dictid="+dictid;
	    }
	    window.location.href=src;
	}
	
	function toGoBack() {
		document.forms[0].action="<%=ctx%>/sys/dict.htm";
		document.forms[0].submit();
	}
	function doDelete() {
		var svalue=getCbxValue(document.all.cbxItem);
		if(svalue=="") {
		  	alert("请选择一条记录。");
		 	return;	
		}
		var dictpcode =$('#dictpcode').val();
		var dictpname =$('#dictpname').val();
		var dictpkey =$('#dictpkey').val();
		var src="<%=ctx%>/sys/dictDeleteItems.htm?Ids="+svalue+"&dictpcode="+dictpcode+"&dictpkey="+dictpkey+"&dictpname="+escape(encodeURIComponent(dictpname));
		window.location.href=src;
	}
</script>

<#include "/common/taglibs.ftl">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="${ctx}/js/jquery/jquery-ui-1.9.2.custom/css/cupertino/jquery-ui-1.9.2.custom.min.css">
	    <script type="text/javascript" src="${ctx}/js/jquery/lib/jquery-1.8.3.js"></script>
	    <script type="text/javascript" src="${ctx}/js/jquery/jquery-ui-1.9.2.custom/js/jquery-ui-1.9.2.custom.min.js"></script>
	    <script type="text/javascript" src="${ctx}/js/jquery/jquery-layout/jquery.layout-latest.min.js"></script>
	    <script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.chromatable.js"></script>
		<script language="javascript">
			  $(document).ready(function() {
				mylayout = $("body").layout({   
		        	 north: {
						paneSelector:".ui-layout-north",	
						size:"auto",// eg: "auto", "30%", .30, 200,	
						resizerCursor:"n-resize",// custom = url(myCursor.cur),	
						customHotkey:""// EITHER a charCode (43) OR a character ("o")
					},	
					center: {
						paneSelector:".ui-layout-center",	
						minWidth:0,	
						minHeight:0
					}
	        	});
	        	//$('#tbdiv').css("height","300px");
	        	//$('#tbdiv').css("width","1065px");
	        	//width:1100px;height:300px;
				$("#yourTableID2").chromatable({
					width: "100%",
					height: "400px",
					scrolling: "yes"
				});	
			});
		</script>
		<style>
			body {
				font-size: 62.5%;
				font-family: "Trebuchet MS", "Arial", "Helvetica", "Verdana", "sans-serif";
			}
	    	.ui-table {width:100%}
			.ui-table thead tr,
			.ui-table tfoot tr,
			.ui-table-header { background: #cccccc url(${ctx}/images/ui-bg_glass_50_3baae3_1x400.png) 50% 50% repeat-x; font-weight: normal; color: #000000; outline: none;text-shadow:1px 1px 1px white}
			.ui-table th,.ui-table td {font-weight:bold;text-align:left;padding:0.5em 0.3em;border:1px solid #ffffff;border-width:0 0 1px 1px}
			.ui-table thead th {border-width:0 0 1px 1px;text-align:center}
			.ui-table thead > tr > th:first-child,.ui-table tfoot > tr > th:first-child {border-width: 0 0 1px}
			.ui-table tbody > tr > td:first-child {border-width: 0 0 1px 0}
			.ui-table tbody td {font-weight:normal}
			.ui-table .text-center,.ui-table .text-center * {text-align:center}
			.ui-table tbody > tr:nth-child(odd) {background-color:#f6f6f6}
			.ui-table tbody > tr:nth-child(even) {background-color:#eeeeee}
			
			.datalist {
			  border: solid 1px #D5D5D5;
			  border-collapse: collapse;
			  width:100%; 
			}

.datalist td {
	border:1px solid #D5D5D5;
	font-size:12px;
	padding:7px 5px;
}

.datalist th {
	background-color:#EEE;
	border-right:1px solid #D5D5D5;
	font-size:13.5px;
	line-height:120%;
	font-weight:bold;
	padding:8px 5px;
	text-align:left;
}
	    </style>
	</head>
	<body>
		<form action="${ctx}/sys/user.htm" method="post">
			<input type="hidden" id="pagerAction" name="pagerAction">
				<div class="ui-layout-north">
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
										<input id="username" name="user.username" value="${username?default('')}"/>
									</TD>
									<TD width="30%" align="right">
										<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSubmit()"></a>
									</TD>
								</tr>
							</table>
					</fieldset>
				</div>
				<div class="ui-layout-center" style="padding:10px;">
					<table id="yourTableID2" border="0" cellspacing="0" cellpadding="0" class="datalist">
						<thead>
							<tr>
								<th>Check out this header</th>
								<th>Look here's another one</th>
								<th>Wow, look at me!</th>
							</tr>
						</thead>
						<tbody>	
							<tr>
								<td>Some content goes in here</td>
								<td>Praesent vitae ligula nec orci pretium vestibulum</td>
								<td>Maecenas tempus dictum libero</td>
							</tr>
							<tr>
								<td>Quisque in wisi quis orci tincidunt fermentum</td>
								<td>Mauris aliquet mattis metus</td>
								<td>Etiam eu ante non leo egestas nonummy</td>
							</tr>
							<tr>
								<td>Some content goes in here</td>
								<td>Praesent vitae ligula nec orci pretium vestibulum</td>
								<td>Maecenas tempus dictum libero</td>
							</tr>
							<tr>
								<td>Quisque in wisi quis orci tincidunt fermentum</td>
								<td>Mauris aliquet mattis metus</td>
								<td>Etiam eu ante non leo egestas nonummy</td>
							</tr>
							<tr>
								<td>Some content goes in here</td>
								<td>Praesent vitae ligula nec orci pretium vestibulum</td>
								<td>Maecenas tempus dictum libero</td>
							</tr>
							<tr>
								<td>Quisque in wisi quis orci tincidunt fermentum</td>
								<td>Mauris aliquet mattis metus</td>
								<td>Etiam eu ante non leo egestas nonummy</td>
							</tr>
							<tr>
								<td>Some content goes in here</td>
								<td>Praesent vitae ligula nec orci pretium vestibulum</td>
								<td>Maecenas tempus dictum libero</td>
							</tr>
							<tr>
								<td>Quisque in wisi quis orci tincidunt fermentum</td>
								<td>Mauris aliquet mattis metus</td>
								<td>Etiam eu ante non leo egestas nonummy</td>
							</tr>
							<tr>
								<td>Some content goes in here</td>
								<td>Praesent vitae ligula nec orci pretium vestibulum</td>
								<td>Maecenas tempus dictum libero</td>
							</tr>
							<tr>
								<td>Quisque in wisi quis orci tincidunt fermentum</td>
								<td>Mauris aliquet mattis metus</td>
								<td>Etiam eu ante non leo egestas nonummy</td>
							</tr>
							<tr>
								<td>Some content goes in here</td>
								<td>Praesent vitae ligula nec orci pretium vestibulum</td>
								<td>Maecenas tempus dictum libero</td>
							</tr>
							<tr>
								<td>Quisque in wisi quis orci tincidunt fermentum</td>
								<td>Mauris aliquet mattis metus</td>
								<td>Etiam eu ante non leo egestas nonummy</td>
							</tr>
							<tr>
								<td>Some content goes in here</td>
								<td>Praesent vitae ligula nec orci pretium vestibulum</td>
								<td>Maecenas tempus dictum libero</td>
							</tr>
							<tr>
								<td>Quisque in wisi quis orci tincidunt fermentum</td>
								<td>Mauris aliquet mattis metus</td>
								<td>Etiam eu ante non leo egestas nonummy</td>
							</tr>
							<tr>
								<td>Some content goes in here</td>
								<td>Praesent vitae ligula nec orci pretium vestibulum</td>
								<td>Maecenas tempus dictum libero</td>
							</tr>
							<tr>
								<td>Quisque in wisi quis orci tincidunt fermentum</td>
								<td>Mauris aliquet mattis metus</td>
								<td>Etiam eu ante non leo egestas nonummy</td>
							</tr>
							<tr>
								<td>Some content goes in here</td>
								<td>Praesent vitae ligula nec orci pretium vestibulum</td>
								<td>Maecenas tempus dictum libero</td>
							</tr>
							<tr>
								<td>Quisque in wisi quis orci tincidunt fermentum</td>
								<td>Mauris aliquet mattis metus</td>
								<td>Etiam eu ante non leo egestas nonummy</td>
							</tr>
						</tbody>
						</table>

					
				</div>
		</form>
	</body>
</html>
<script language="javascript">
	
	function doEdit(nflag) {
		var saction="";
		if(nflag==1) {
			document.forms[0].action="${ctx}/sys/userToEdit.htm";
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
		saction="${ctx}/sys/userToEdit.htm?Ids="+svalue;
		if(nflag==3) {
			if(svalue=="") {
			  alert("请选择一条记录。");
			  return;	
			}
			saction="${ctx}/sys/userDelete.htm?Ids="+svalue;
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
		document.forms[0].action = "${ctx}/sys/userDelete.htm?Ids="+svalue;
		document.forms[0].submit();
	}
	function doSubmit() {
		document.forms[0].action="${ctx}/sys/user.htm";
		document.forms[0].submit();
	}
</script>
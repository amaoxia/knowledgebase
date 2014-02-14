<#include "/common/taglibs.ftl">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="${ctx}/js/jquery/jquery-ui-1.9.2.custom/css/cupertino/jquery-ui-1.9.2.custom.min.css">
		<link href="${ctx}/js/jquery/plugins/fixedheadertable/960.css" rel="stylesheet" media="screen" />
        <link href="${ctx}/js/jquery/plugins/fixedheadertable/defaultTheme.css" rel="stylesheet" media="screen" />
        <link href="${ctx}/js/jquery/plugins/fixedheadertable/myTheme.css" rel="stylesheet" media="screen" />
	    <script type="text/javascript" src="${ctx}/js/jquery/lib/jquery-1.8.3.js"></script>
	    <script type="text/javascript" src="${ctx}/js/jquery/jquery-layout/jquery.layout-latest.min.js"></script>
	    <script src="${ctx}/js/jquery/plugins/fixedheadertable/jquery.fixedheadertable.js"></script>
	    <script src="${ctx}/js/jquery/plugins/fixedheadertable/demo.js"></script>
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
	        	$('#tt').fixedHeaderTable({
					footer: true,
					altClass: 'odd',
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
				<div class="ui-layout-center">
				<div id="tbdiv" style="height:300px;padding:10px;">
					<table class="fancyTable" id="tt" cellpadding="0" cellspacing="0">
        		    <thead>
        		        <tr>
        		            <th>Browser</th>
        		            <th>Visits</th>
        		            <th>Pages/Visit</th>
        		            <th>Avg. Time on Site</th>
        		            <th>% New Visits</th>
        		            <th>Bounce Rate</th>
        		        </tr>
        		    </thead>
        		    <tfoot>
        		        <tr>
        		            <td colSpan="6">This is a unique footer</td>
        		        </tr>
        		    </tfoot>
        		    <tbody>
        		        <tr>
        		            <td>Firefox</td>
        		            <td class="numeric">1,990</td>
        		            <td class="numeric">3.11</td>
        		            <td class="numeric">00:04:22</td>
        		            <td class="numeric">70.00%</td>
        		            <td class="numeric">32.61%</td>
        		        </tr>
        		        <tr>
        		            <td>Firefox</td>
        		            <td class="numeric">1,990</td>
        		            <td class="numeric">3.11</td>
        		            <td class="numeric">00:04:22</td>
        		            <td class="numeric">70.00%</td>
        		            <td class="numeric">32.61%</td>
        		        </tr>
        		        <tr>
        		            <td>Firefox Web Browser Version 4.0</td>
        		            <td class="numeric">1,990</td>
        		            <td class="numeric">3.11</td>
        		            <td class="numeric">00:04:22</td>
        		            <td class="numeric">70.00%</td>
        		            <td class="numeric">32.61%</td>
        		        </tr>
        		        <tr>
        		            <td>Firefox</td>
        		            <td class="numeric">1,990</td>
        		            <td class="numeric">3.11</td>
        		            <td class="numeric">00:04:22</td>
        		            <td class="numeric">70.00%</td>
        		            <td class="numeric">32.61%</td>
        		        </tr>
        		        <tr>
        		            <td>Firefox</td>
        		            <td class="numeric">1,990</td>
        		            <td class="numeric">3.11</td>
        		            <td class="numeric">00:04:22</td>
        		            <td class="numeric">70.00%</td>
        		            <td class="numeric">32.61%</td>
        		        </tr>
        		        <tr>
        		            <td>Firefox</td>
        		            <td class="numeric">1,990</td>
        		            <td class="numeric">3.11</td>
        		            <td class="numeric">00:04:22</td>
        		            <td class="numeric">70.00%</td>
        		            <td class="numeric">32.61%</td>
        		        </tr>
        		        <tr>
        		            <td>Firefox</td>
        		            <td class="numeric">1,990</td>
        		            <td class="numeric">3.11</td>
        		            <td class="numeric">00:04:22</td>
        		            <td class="numeric">70.00%</td>
        		            <td class="numeric">32.61%</td>
        		        </tr>
        		        <tr>
        		            <td>Firefox</td>
        		            <td class="numeric">1,990</td>
        		            <td class="numeric">3.11</td>
        		            <td class="numeric">00:04:22</td>
        		            <td class="numeric">70.00%</td>
        		            <td class="numeric">32.61%</td>
        		        </tr>
        		        <tr>
        		            <td>Firefox</td>
        		            <td class="numeric">1,990</td>
        		            <td class="numeric">3.11</td>
        		            <td class="numeric">00:04:22</td>
        		            <td class="numeric">70.00%</td>
        		            <td class="numeric">32.61%</td>
        		        </tr>
        		        <tr>
        		            <td>Firefox</td>
        		            <td class="numeric">1,990</td>
        		            <td class="numeric">3.11</td>
        		            <td class="numeric">00:04:22</td>
        		            <td class="numeric">70.00%</td>
        		            <td class="numeric">32.61%</td>
        		        </tr>
        		        <tr>
        		            <td>Firefox</td>
        		            <td class="numeric">1,990</td>
        		            <td class="numeric">3.11</td>
        		            <td class="numeric">00:04:22</td>
        		            <td class="numeric">70.00%</td>
        		            <td class="numeric">32.61%</td>
        		        </tr>
        		        <tr>
        		            <td>Firefox</td>
        		            <td class="numeric">1,990</td>
        		            <td class="numeric">3.11</td>
        		            <td class="numeric">00:04:22</td>
        		            <td class="numeric">70.00%</td>
        		            <td class="numeric">32.61%</td>
        		        </tr>
        		        <tr>
        		            <td>Firefox</td>
        		            <td class="numeric">1,990</td>
        		            <td class="numeric">3.11</td>
        		            <td class="numeric">00:04:22</td>
        		            <td class="numeric">70.00%</td>
        		            <td class="numeric">32.61%</td>
        		        </tr>
        		        <tr>
        		            <td>Firefox</td>
        		            <td class="numeric">1,990</td>
        		            <td class="numeric">3.11</td>
        		            <td class="numeric">00:04:22</td>
        		            <td class="numeric">70.00%</td>
        		            <td class="numeric">32.61%</td>
        		        </tr>
        		        <tr>
        		            <td>Firefox</td>
        		            <td class="numeric">1,990</td>
        		            <td class="numeric">3.11</td>
        		            <td class="numeric">00:04:22</td>
        		            <td class="numeric">70.00%</td>
        		            <td class="numeric">32.61%</td>
        		        </tr>
        		        <tr>
        		            <td>Firefox</td>
        		            <td class="numeric">1,990</td>
        		            <td class="numeric">3.11</td>
        		            <td class="numeric">00:04:22</td>
        		            <td class="numeric">70.00%</td>
        		            <td class="numeric">32.61%</td>
        		        </tr>
        		        <tr>
        		            <td>Firefox</td>
        		            <td class="numeric">1,990</td>
        		            <td class="numeric">3.11</td>
        		            <td class="numeric">00:04:22</td>
        		            <td class="numeric">70.00%</td>
        		            <td class="numeric">32.61%</td>
        		        </tr>
        		        <tr>
        		            <td>Firefox</td>
        		            <td class="numeric">1,990</td>
        		            <td class="numeric">3.11</td>
        		            <td class="numeric">00:04:22</td>
        		            <td class="numeric">70.00%</td>
        		            <td class="numeric">32.61%</td>
        		        </tr>
        		        <tr>
        		            <td>Firefox</td>
        		            <td class="numeric">1,990</td>
        		            <td class="numeric">3.11</td>
        		            <td class="numeric">00:04:22</td>
        		            <td class="numeric">70.00%</td>
        		            <td class="numeric">32.61%</td>
        		        </tr>
        		        <tr>
        		            <td>Firefox</td>
        		            <td class="numeric">1,990</td>
        		            <td class="numeric">3.11</td>
        		            <td class="numeric">00:04:22</td>
        		            <td class="numeric">70.00%</td>
        		            <td class="numeric">32.61%</td>
        		        </tr>
        		    </tbody>
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
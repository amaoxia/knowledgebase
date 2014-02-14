<#global ctx = request.contextPath >
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>home页</title>
	    <link rel="stylesheet" href="${ctx}/js/jquery/jquery-ui-1.10.4.custom/css/cupertino/jquery-ui-1.10.4.custom.min.css">
	    <link rel="stylesheet" href="${ctx}/js/jquery/jquery-layout/layout-default-latest.css">
	    <script type="text/javascript" src="${ctx}/js/jquery/lib/jquery-1.8.3.js"></script>
	    <script type="text/javascript" src="${ctx}/js/jquery/jquery-layout/jquery.layout-latest.min.js"></script>
		<script src="${ctx}/js/jquery/jquery-ui-1.10.4.custom/development-bundle/ui/jquery.ui.core.js"></script>
		<script src="${ctx}/js/jquery/jquery-ui-1.10.4.custom/development-bundle/ui/jquery.ui.widget.js"></script>
	    <script src="${ctx}/js/jquery/jquery-ui-1.10.4.custom/development-bundle/ui/jquery.ui.accordion.js"></script>
	    <script src="${ctx}/js/jquery/jquery-ui-1.10.4.custom/development-bundle/ui/jquery.ui.tabs.js"></script>
	    <script src="${ctx}/js/jquery/jquery-ui-1.10.4.custom/development-bundle/ui/jquery.ui.menu.js"></script>
	    <script type='text/javascript' src='${ctx}/js/jsrender.js'></script>
	    <script id="accordionTemplate" type="text/x-jsrender">
			<h3>{{>menuname}}</h3>
			<div>
				<ul class="menu" style="height:98.9%;">
					{{for menus tmpl='#menuTemplate'/}} 
				</ul>
			</div>
		</script>
		<script id="menuTemplate" type="text/x-jsrender">
        	<li><a href="#" url="{{>url}}"><span class="ui-icon ui-icon-disk"></span>{{>menuname}}</a></li>
		</script>
	    <style>
	    	body {
				font-size: 62.5%;
				font-family: "Trebuchet MS", "Arial", "Helvetica", "Verdana", "sans-serif";
			}
			.ui-accordion .ui-accordion-content {
			    padding: 0em;
			}
			.ui-accordion .ui-accordion-header {
			    margin-top: 0px;
			}
			.ui-layout-pane { 
				border: 0.5px solid #AED0EA;
			    padding:0px;
			}
			.ui-helper-reset {
				line-height: 1.0;
			}
			.ui-tabs .ui-tabs-panel {
				 padding: 0;
			}
	    	#tabs { margin-top: 1em; }
			#tabs li .ui-icon-close { float: left; margin: 0.4em 0.2em 0 0; cursor: pointer; }
			#css3menu li {
				float: left;
				list-style-type: none;
			}
			
			#css3menu li a {
				color: #fff;
				padding-right: 20px;
			}
			
			#css3menu li a.active {
				color: yellow;
			}
	    </style>
	    <script type="text/javascript">
	    	var mylayout = null;
	    	var tabs = null;
	    	var tabCounter = 2;
	    	var tabTitle = "tab";
	        $(document).ready(function() {
	        	mylayout = $("body").layout({   
		        	 north: {
						paneSelector:".ui-layout-north",	
						size:"40",// eg: "auto", "30%", .30, 200,	
						resizerCursor:"n-resize",// custom = url(myCursor.cur),	
						spacing_open:0,
						customHotkey:""// EITHER a charCode (43) OR a character ("o")
					},	
					south: {
						paneSelector:".ui-layout-south",	
						size:"20",	
						resizerCursor:"s-resize",	
						spacing_open:0,
						customHotkey:""
					},	
					west: {
						paneSelector:".ui-layout-west",	
						size:150,	
						resizerCursor:"w-resize",
						slideTrigger_open:"click", 
						customHotkey:""
					},	
					center: {
						paneSelector:".ui-layout-center",	
						minWidth:0,	
						minHeight:0
					}
	        	});
				
	        	tabs = $( "#tabs" ).tabs();
	        	
	        	$("#accordion").html($("#accordionTemplate").render(${menus}));
	        	$( ".menu" ).menu();
	        	$('.menu a').click(function(){
	        		var url = $(this).attr("url");
	        		if(url){
		        		var tabseq = $(this).attr("tabseq");
		        		if(!tabseq){
		        			tabseq = tabCounter;
		        			$(this).attr("tabseq",tabseq);
		        			tabCounter++;
		        		}
		        		addTab(tabseq,$(this).text(),url);
	        		}
	        	});
	        	
	        	var icons = {
					header: "ui-icon-circle-arrow-e",
					activeHeader: "ui-icon-circle-arrow-s"
				};
				$("#accordion").accordion({
					heightStyle: "fill",
				
					icons: icons
				});
				$("#west").resize(function(){
					$("#accordion").accordion("refresh");
				});
	        });
	        function addTab(tabseq,title,url) {
				var id = "tabs-" + tabseq;
				var tab_t = $("#" + id);
                if (!tab_t.get(0)) {
                    tabs.find(".ui-tabs-nav").append("<li><a>"+title+"</a><span class='ui-icon ui-icon-close'>关闭</span></li>").find('a :last').attr("href","#"+id);
					tabs.append( "<div id='" + id + "'><iframe id='iframe-"+id+"' src='${ctx}"+url+"' frameborder='0' width='100%' height='450px'></iframe></div>");
					//$('#'+id).find('iframe :first').attr("src",url);
					tabs.tabs("refresh");
                } 
                tabs.tabs('select', '#' + id);
			}
			$( "#tabs span.ui-icon-close" ).live( "click", function() {
				var panelId = $( this ).closest( "li" ).remove().attr( "aria-controls" );
				$( "#" + panelId ).remove();
				tabs.tabs("refresh");
			});
	    </script>
	</head>
	<body>
		<div class="ui-layout-center" id="tabs">
			<ul>
				<li><a href="#tabs-1">主页</a> <span class="ui-icon ui-icon-home">主页</span></li>
			</ul>
			<div id="tabs-1">
				<p>Proin</p>
			</div>
		</div>
		<div class="ui-layout-north" style="overflow: hidden;  background: url(images/ui-bg_glass_50_3baae3_1x400.png) #7f99be repeat-x center 50%; line-height: 30px; color: #fff; font-family: Verdana, 微软雅黑, 黑体;">
			<span style="float: right; padding-right: 20px;" class="head">欢迎
				PEPE <a href="#" id="editpass"> 修改密码</a> <a href="#" id="loginOut">安全退出</a>
			</span>
			<span style="padding-left: 10px; font-size: 16px; float: left;">
				<img src="images/blocks.gif" width="20" height="20"
					align="absmiddle" /> PEPE</span>
			<ul id="css3menu"
				style="padding: 0px; margin: 0px; list-type: none; float: left; margin-left: 40px;">
				<li>
					<a class="active" name="basic" href="javascript:;" title="常用菜单">常用菜单</a>
				</li>
				<li>
					<a name="point" href="javascript:;" title="邮件列表">邮件列表</a>
				</li>
			</ul>
		</div>
		<div class="ui-layout-south" style="overflow: hidden; background: url(images/ui-bg_glass_50_3baae3_1x400.png) #7f99be repeat-x center 50%;  line-height: 15px; text-align :center; color: #fff; font-family: Verdana, 微软雅黑, 黑体;">
			版权所有
		</div>
		
		<div class="ui-layout-west" id="west">
			<div id="accordion"/>
		</div>
	</body>
</html>

<#global ctx = request.contextPath >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>监控页面</title>
    <script type="text/javascript" src="${ctx}/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery/operamasks-ui/ui/om-core.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery/operamasks-ui/ui/om-mouse.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery/operamasks-ui/ui/om-resizable.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery/operamasks-ui/ui/om-button.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery/operamasks-ui/ui/om-buttonbar.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery/operamasks-ui/ui/om-panel.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery/operamasks-ui/ui/om-tabs.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery/operamasks-ui/ui/om-tree.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery/operamasks-ui/ui/om-draggable.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery/operamasks-ui/ui/om-scrollbar.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery/operamasks-ui/ui/om-borderlayout.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery/operamasks-ui/themes/default/om-all.css" />
    <style>
		html, body{ width: 100%; height: 100%; padding: 0; margin: 0;overflow: hidden;}
		#north-panel{
			background: url("images/header-bg.jpg") repeat-x scroll 0 0;
			text-align: center;
		}
		#north-panel h2{
			font-size: 18px;
			font-weight: bold;
			margin: 5px;
		}
		.om-borderlayout-region-west .om-borderlayout-region-header{
		 	padding: 0px;
		 	border: 0;
		 	height: 28px;
		 	line-height: 28px;
		 	background: url("images/accordion/leftmenu_bg.jpg") repeat-x scroll 0 0 #FFFFFF;
		 	border-right:1px solid #99A8BB;
		 	font-size: 14px;
		 	font-weight: bold;
		 	cursor: pointer;
		}
		.om-borderlayout-region-west .om-panel-body{
			padding: 0;
		}
		.nav-panel {
			padding: 0;
		}
		.nav-panel div.nav-item{
			line-height: 25px;
			font-size: 13px;
			font-weight: bold;
			padding-left: 40px;
			cursor: pointer;
			list-style-type: none;
		}
		.nav-panel div.user{
			background: url("images/user.png") no-repeat scroll 20px 4px;
		}
		.nav-panel div.nav-item:hover{
			border: 1px solid #99A8BC;
			background-color: #C4D6EC;
			padding-left: 39px;
			line-height: 23px;
		}
		.nav-panel div.user:hover{
			background-position: 19px 3px;
		}
    </style>
    <script type="text/javascript">
    	var id = 0;
    	
        $(document).ready(function() {
            var element = $('body').omBorderLayout({
           	   panels:[{
           	        id:"north-panel",
           	        region:"north",
           	        header : false,
           	        height : 40
           	    },{
           	        id:"south-panel",
           	        title:"版权所有",
           	        region:"south",
           	        resizable:true,
           	        collapsible:true,
           	        height:20,
           	        header:false
           	    },{
           	        id:"center-panel",
           	     	header:false,
           	        region:"center"
           	    },{
           	        id:"west-panel",
           	        resizable:true,
           	        collapsible:true,
           	        title:"菜单列表",
           	        region:"west",
           	        expandToBottom : false,
           	        width:170
           	    }],
           	    hideCollapsBtn : true,
           	    spacing : 8
            });
            // 默认关闭下面的面板
            element.omBorderLayout("collapseRegion","south");
            // 导航面板里由5个单独的panel构成
            var menuPanel = [{id:"nav-panel-1" , title:"系统资源"},
                             {id:"nav-panel-2" , title:"系统日志"},
                             {id:"nav-panel-3" , title:"系统管理"},
                             {id:"nav-panel-4" , title:"系统信息"},
                             {id:"nav-panel-5" , title:"系统性能"}];
            $(menuPanel).each(function(index , panel){
                $("#"+panel.id).omPanel({
                    title : panel.title,
                    collapsible:true,
                    // 面板收缩和展开的时候重新计算自定义滚动条是否显示
                    onCollapse : function(){
                        $("#west-panel").omScrollbar("refresh");
                    },
                    onExpand : function(){
                        $("#west-panel").omScrollbar("refresh");
                    }
                });
            });
            
            // 初始化中间的tab页签
            $('#center-tab').omTabs({
                switchMode : 'click',
                width: 550,
                height: 250,
                closable : true,
                onActivate : function(n,event) {
                    $('<br/>').insertBefore($('#end-scroll'));
                },
                onBeforeActivate : function(n,event) {
                    $('<br/>').insertBefore($('#end-scroll'));
                },
                onClose : function(n,event) {
                    $('<br/>').insertBefore($('#end-scroll'));
                },
                onBeforeClose : function(n,event) {
                    $('<br/>').insertBefore($('#end-scroll'));
                },
                onAdd : function(config,event) {
                    $('<br/>').insertBefore($('#end-scroll'));
                },
                onBeforeAdd : function(config,event) {
                    $('<br/>').insertBefore($('#end-scroll'));
                }
            });
            
            var treedata = [{id:"n1",text:"品牌",expanded:true},
                         {id:"n2",text:"运营商",expanded:true},
                         {id:"n11",pid:"n1",text:"三星"},
    			         {id:"n12",pid:"n1",text:"诺基亚"},
    			         {id:"n13",pid:"n1",text:"摩托罗拉"},
    			         {id:"n14",pid:"n1",text:"索尼"},
    			         {id:"n21",pid:"n2",text:"移动"},
    			         {id:"n22",pid:"n2",text:"联通"},
    			         {id:"n23",pid:"n2",text:"电信"}];
            $("#navtree").omTree({
                dataSource : treedata,
                simpleDataModel: true
            });
            // 导航面板使用自定义滚动条
            $("#west-panel").omScrollbar();
        });
        
        function ia(){
                id ++ ;
                $('#center-tab').omTabs('add',{
                        title : 'NewTitle' + id, 
                        content : 'New Body' + id
                });
            }
            ia();
    </script>
</head>
<body>
   	<div id="north-panel"><h2>XXX 监控平台</h2></div>
   	<div id="center-panel" style="padding: 5px 10px 0px 10px;">
    	 <ul>
            <li><a href="#tab1">苹果</a></li>
        </ul>
        <div id="tab1" >
            <img alt="" src="images/pg.jpg" style="float: left;margin-right: 10px;">
            &nbsp;&nbsp;&nbsp;&nbsp;苹果，落叶乔木，叶子椭圆形，花白色带有红晕。果实圆形，味甜或略酸，
            是常见水果，具有丰富营养成分，有食疗、辅助治疗功能。苹果原产于欧洲、
            中亚、西亚和土耳其一带，于十九世纪传入中国。中国是世界最大的苹果生产国，
            在东北、华北、华东、西北和四川、云南等地均有栽培。
        </div>
   	</div>
   	<div id="west-panel" class="om-accordion" style="position: relative;">
   		<div id="nav-panel-1" class="nav-panel">
			<div class="nav-item user">服务</div>
 			<div class="nav-item">流程</div>
 			<div class="nav-item">触发器</div>
 			<div class="nav-item">闪回</div>
 			<div class="nav-item">CDC</div>
 			<div class="nav-item">数据源</div>
 			<div class="nav-item">文件池</div>
 			<div class="nav-item">队列</div>
 			<div class="nav-item">调度</div>
   		</div>
   		<div id="nav-panel-2" class="nav-panel">
   			<ul id="navtree"></ul>
   		</div>
   		<div id="nav-panel-3" class="nav-panel">
 			<div class="nav-item">流程</div>
 			<div class="nav-item">触发器</div>
 			<div class="nav-item">闪回</div>
   		</div>
   		<div id="nav-panel-4" class="nav-panel">
 			<div class="nav-item">流程</div>
 			<div class="nav-item">触发器</div>
 			<div class="nav-item">闪回</div>
   		</div>
   		<div id="nav-panel-5" class="nav-panel">
 			<div class="nav-item">流程</div>
 			<div class="nav-item">触发器</div>
 			<div class="nav-item">闪回</div>
   		</div>
   	</div>
   	<div id="south-panel">版权所有</div>
</body>
</html>

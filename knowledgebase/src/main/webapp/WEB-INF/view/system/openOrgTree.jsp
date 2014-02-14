<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/common/taglibs.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/dtree/dtree.css"  type="text/css"/>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/dtree/dtree.js"></script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="con">  
  <tr>
  	 <td width="5%" align="top">&nbsp;</td>
  	 <td width="80%" align="top">    	
		 	  <div id="leftdtree" name="leftdtree" class="dtree"></div>
					 <script>
						var sPath="<%=request.getContextPath()%>";
					   	var tree=new dTree(document.getElementById("leftdtree"),sPath,2); 		
					   	tree.add('${sysOrg.orgcode}',-1,'${sysOrg.orgname}','#','${sysOrg.orgname}');
					   	tree.data="${orgTree}";
					   	tree.initDTreeNode();
					   	tree.showNodeInfo=function(){}
					</script> 
     </td>
  </tr>  
</table>
</body>
</html>
<script>
	
	$(document).ready(function() {
		$('input[type="radio"]').click(function() {
			var doc = window.opener.document;
			var pdomEleId = doc.getElementById("orgid");
			var pdomEleName = doc.getElementById("orgname");
			pdomEleId.value = $(this).attr("value");
			pdomEleName.value = $(this).next().text();
			window.close();
		});
	});
</script>
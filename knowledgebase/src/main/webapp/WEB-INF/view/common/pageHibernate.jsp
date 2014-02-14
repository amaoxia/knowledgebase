<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/pageHibernate.js"></script>
<%@ page import="com.framework.core.mvc.pager.PaginationSupport"%>
<%
	PaginationSupport pageResult=(PaginationSupport)request.getAttribute("pagerResult");	
%>
<script>
		$(function(){
			$('#pp').pagination({
				total:<%=pageResult.getTotalCount()%>,
				beforePageText:'转到第',
				afterPageText:'页，共<%=pageResult.getPageCount()%>页',
				displayMsg:'第 {from} - {to} 条/共 {total} 条',
				onSelectPage:function(pageNumber, pageSize){
					$(this).pagination('loading');
					locatePage(pageNumber);
					$(this).pagination('loaded');
				}
			});
		});
</script>
<div id="pp" style="background:#efefef;border-top:1px solid #ccc;"></div>
<input type="hidden" id="pageCount"  name="pageCount" value='<%=pageResult.getPageCount()%>'>
<input type="hidden" id="startIndex"  name="startIndex" value='<%=pageResult.getStartIndex()%>'>
<input type="hidden" id="pageSize" name="pageSize" value="<%=pageResult.getPageSize()%>" >
<input type="hidden" id="currentPage" name="currentPage" value='<%=pageResult.getCurrentPage()%>'>
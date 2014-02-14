<#import "/common/pager.ftl" as pager>
<#global ctx = request.contextPath >
<#function subStr str len>
	<#if (len)?exists>
		<#assign len=15>
	</#if>
	<#if str?length lt len>
	   <#return str>
	   <#else>
	   <#return str.substring(0,len)+'...'>
	</#if>
</#function>

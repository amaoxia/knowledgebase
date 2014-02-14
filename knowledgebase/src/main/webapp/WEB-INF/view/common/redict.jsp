<%@ page contentType="text/html; charset=utf-8"%>

<script language="javascript">
	function forwardPage()
	{
		var surl=window.location.href;
		var pars=surl.substring(surl.indexOf("?"),surl.length);
		alert(pars);
		window.location.href='<%=request.getContextPath()%>/forwardPage.htm?'+pars;
	}
	forwardPage();
</script>
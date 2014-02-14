<#--分页-->
<#macro pageTag formName="pageForm" showHidden="true"> 
  <#if (page.pageCount gt -1)> 
  		<#if showHidden=="true">
			<input type="hidden" name="page.total" value="${page.total}"/>
			<input type="hidden" name="page.perPage" id="perPage" value="${page.perPage}"/>
			<input id="pageNo" type="hidden" name="page.index" value="${page.index}"/>
			<#list sortParas as sort>
				<input type="hidden" name="orders[${sort.property},,]" value="${sort.order}"/>
				<input type="hidden" id="orderBy" value="${sort.property}"/>
			</#list>
		</#if>
		
		 <span class="pageR">
			第 <span class="page_orange">${page.index}/${page.pageCount}</span>页&nbsp;
			共<span class="page_green" >${page.total}</span>条记录&nbsp;&nbsp;
			<a href="javascript:chanagePageNO(1)"><img src="${path}/images/p_1.png" align="absbottom" /></a>
			<a href="javascript:chanagePageNO(${page.upNo})"><img src="${path}/images/p_2.png" align="absbottom" /></a>
				
			&nbsp;&nbsp;	
			<span>
			    <#if (page.pageCount lt 10)>
			    	 <#list 1..page.pageCount as num>
			    	 	  <a href="javascript:chanagePageNO(${num})" class="page_s">${num}</a>
			    	 </#list>
			    <#else>
			         <#if (page.index gt 6)>
			            <#if ((page.index+4) gt page.pageCount)>
			               <#list (page.index-5)..page.pageCount as num>
			    	 		   <a href="javascript:chanagePageNO(${num})" >${num}</a>
			    	 	   </#list>
			    	 	<#else>
			    	 		<#list (page.index-5)..(page.index+4) as num>
			    	 			<a href="javascript:chanagePageNO(${num})" >${num}</a>
			    	 	   </#list>
			            </#if>
			          <#else>
			             <#list 1..10 as num>
			    	 	     <a href="javascript:chanagePageNO(${num})" >${num}</a>
			    	      </#list>
			          </#if>
			    </#if>
			</span>
			&nbsp;&nbsp;
			<a href="javascript:chanagePageNO(${page.nextNo})"><img src="${path}/images/p_4.png" align="absbottom" /></a>
			<a href="javascript:chanagePageNO(${page.pageCount})"><img src="${path}/images/p_5.png" align="absbottom" /></a>
			
			<!--每页显示-->
            <span style="padding-left:20px;">每页</span> 
			<span>
				<select name="pageSize" id="pageSize" onchange="chanagePageSize(this.value);">
				  <option value="10" <#if page.perPage==10>selected</#if> >10</option>
	              <option value="20" <#if page.perPage==20>selected</#if> >20</option>
	              <option value="50" <#if page.perPage==50>selected</#if> >50</option>
	              <option value="100" <#if page.perPage==100>selected</#if> >100</option>
	              <option value="200" <#if page.perPage==200>selected</#if> >200</option>
				</select>
			</span> 
			<span>条</span>
       </span>	

		<#if showHidden=="true">
			<script type="text/javascript">
				//换页
				function chanagePageNO(pageIndex){
					if(${page.index}==pageIndex){
					//	return;
					}
					var pageNo = document.getElementById("pageNo");
					pageNo.value = pageIndex;
					//document.forms['${formName}'].submit();
					//最得表单的target属性
					var reponseTarget =document.forms['${formName}'].target;
					if(!reponseTarget){//如果没有则提交表单
						document.forms['${formName}'].submit();
					}else{//如果有则异步提交表单,注意主页面要引入:jquery.form.js
						$("#pageForm").ajaxForm({ 
						url:document.forms['${formName}'].action,    
					    type:     'post',    
					    success:function(msg){  
					    	 $("#"+reponseTarget).html(msg);
					    	 $(".table3 tbody tr:odd").css("background-color","#EBEBEB");
					    	}
						});  	
					    $("#pageForm").submit(); 
					} 
					
				   

					
					
					
				}
				
				//改变页大小 
				function chanagePageSize(pageSize){
					var perPage = document.getElementById("perPage");
					perPage.value = pageSize; 
				    // 写入cookie
				    //setCookie("pageSize",pageSize);
					document.forms['${formName}'].submit();
				}
				
				function setOrder(e){
					var ename=e.name;
					orderby(ename);
				}
				//排序
				function orderby(title){
					var pageform=document.forms['${formName}'];
					var order = $("input[name*='orders']");
					var orderValue = order.val();
					if(orderValue=="desc"){
						order.val("asc");
					}else{
					 	order.val("desc");
					}
					order.attr("name","orders["+title+",,]");
					pageform.submit();
				}
				
				//在document加载中,需要设置排序的图片.
				function setOrderImg(){
					var order = $("input[name*='orders']");
					var orderValue = order.val();
					var orderName=jQuery("#orderBy").val();
					if(orderName==""){
						return;
					}
					if(jQuery("a[name='"+orderName+"']")==null){
						return;
					}
					if (orderValue == "desc"){
						jQuery("a[name='"+orderName+"'] span").html("<img src='${path}/images/arrows/arrow1_down.gif'/>");
					}else if(orderValue == "asc"){
						jQuery("a[name='"+orderName+"'] span").html("<img src='${path}/images/arrows/arrow1_up.gif'/>");
					}
				}
			</script> 
		</#if>
  </#if>
</#macro>
			
			
		
			
			
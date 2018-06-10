<%@page language="java" pageEncoding="UTF-8" import="com.jshx.core.utils.Constants" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Success</title>
<meta name="robots" content="all" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="Stylesheet"
	          href='<c:url value="/webResources/themes/${curr_user.cssId}/css/style.css"/>' />

<script language="JavaScript">
		var target;
		<%
    	if(Constants.USER_TAB){
    	%> 
    	if(opener==null){
            //target = parent.parent.frames["frm"].getCurrTab();
            target = window;
        }else
        	target = opener;
    	<%
    	}else{
    	%>
    	if(opener==null){
            target = parent.document.getElementById("frm");
        }else
        	target = opener.parent.parent.document.getElementById("frm");
    	<%
    	}
    	%>   
        	if(target==null)
                target = opener;
            if(opener!=null){
            	var ah = screen.availHeight - 300;
        	    var aw = screen.availWidth - 240;
        		self.moveTo(aw/2,ah/2);
        		self.resizeTo(250,220);
        		self.focus();
            }
            
       function returnPage(successUrl){
    	   var randomnumber=Math.floor(Math.random()*100000);
    	   
           if(successUrl!=""){
        	   <%
           	if(Constants.USER_TAB){
           	%> 
           	if(successUrl.indexOf("?")!=-1){
    	  		target.open("${ctx}/"+successUrl+"&refresh=1","_self");
    	    }else{
    	   		target.open("${ctx}/"+successUrl+"?refresh=1","_self");
    	    }
           	<%
           	}else{
           	%>
           	if(target.src==null){
    	   		if(successUrl.indexOf("?")!=-1){
        	  		target.open("${ctx}/"+successUrl+"&refresh=1","_self");
        	    }else{
        	   		target.open("${ctx}/"+successUrl+"?refresh=1","_self");
        	    }
        	}
    	   else{
    		   if(successUrl.indexOf("?")!=-1)
                   target.src = "${ctx}/"+successUrl+"&refresh=1";
               else
            	   target.src = "${ctx}/"+successUrl+"?refresh=1";
    	   }
           	<%
           	}
           	%>  
           }
           window.close("_self");
       }
</script>
</head>
<%
String successUrl = request.getParameter("/jsp/company/companyInitEdit.action");
%>
<c:set var="curr_path" value="操作结果"></c:set>
<body class="pages_right" onload="returnPage('/jsp/company/companyInitEdit.action')">
<%@include file="/WEB-INF/template/content_notitle.jsp"%>

<div class="caption">操作提示：</div>
<div class="submitdata">
<table width="200" align="center" border="0"
	cellspacing="0" cellpadding="0">
	<tr>
		<td class="set_c"><c:if test="${not empty message}">${message}</c:if> <c:if
			test="${empty message}">操作成功！</c:if></td>
	</tr>
	<tr>
		<td class="set_c"><input type="button"
			class="input_button" value="返 回"
			onclick="returnPage('/jsp/company/companyInitEdit.action')" /></td>
	</tr>
</table>
</div>

<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

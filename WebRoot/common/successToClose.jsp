<%@page language="java" pageEncoding="UTF-8"
	import="com.jshx.core.utils.Constants" isELIgnored="false"%>
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
    	if(opener==null){
            target = window;
        }else
        	target = opener;
    	if(opener==null){
            target = parent.document.getElementById("frm");
        }else
        	target = opener.parent.parent.document.getElementById("frm");
        	if(target==null)
                target = opener;
            if(opener!=null){
            	var ah = screen.availHeight - 300;
        	    var aw = screen.availWidth - 240;
        		self.moveTo(aw/2,ah/2);
        		self.resizeTo(250,220);
        		self.focus();
            }
       function returnPage(){
           window.close("_self");
       }
</script>
	</head>
	<c:set var="curr_path" value="操作结果"></c:set>
	<body>
		<div class="caption">
			操作提示：
		</div>
		<div class="submitdata">
			<table width="200" align="center" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td class="set_c">
						<c:if test="${not empty message}">${message}</c:if>
						<c:if test="${empty message}">操作成功！</c:if>
					</td>
				</tr>
				<tr>
					<td class="set_c">
						<input type="button" class="input_button" value="返 回"
							onclick="returnPage()" />
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>

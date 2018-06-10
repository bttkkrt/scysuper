<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<c:set var="curr_path" value="选择传阅人"></c:set>
<html>
	<head>
	<title>选择传阅人</title>
	<script type="text/javascript" src="${ctx}/webResources/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/webResources/js/easyui/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src='<c:url value="/webResources/js/easyui/jquery.easyui.min.js"/>'></script>
	<link rel="stylesheet" type="text/css" href='<c:url value="/webResources/js/easyui/themes/${curr_user.cssId}/easyui.css"/>'> 
	<link rel="stylesheet" type="text/css" href='<c:url value="/webResources/js/easyui/themes/icon.css"/>'>
	<script type="text/javascript" src="${ctx}/webResources/js/easyui/locale/easyui-lang-zh_CN.js"></script>  
	<link rel="stylesheet" type="text/css" href='<c:url value="/webResources/themes/${curr_user.cssId}/css/style.css" />' />
	<script>		
        function save_users(){
		var ff= document.getElementById("settingFrm");
        	var userids = ff.userids;
        	if(userids == null && userids.length==0)
        	{
        		alert("请选择任务提交的用户！");
        		return false;
        	}else{
	      		var currTab = parent.document.frames["frm"].getCurrTab();
			   	var frmId = currTab[0].innerHTML.substring(11,currTab[0].innerHTML.length);
			   	frmId = frmId.substring(0,frmId.indexOf(" "));
			   	var frm = parent.document.frames["frm"].document.frames[frmId];
			   	
        		frm.document.getElementById("userids").value = document.getElementById("checkUserId").value;
        		frm.document.getElementById("checkUserName").value = document.getElementById("checkUserName").value;
        		frm.document.getElementById("nextNodeId").value  =document.getElementById("nodeId").value;
	        	var pform = frm.document.getElementById("form1");
	        	pform.submit();
	        	parent.close_win();
        	}
        }
        
        function checkAll(obj){
			var userids2 = document.getElementsByName("userids");
       	 	var checkUserid = "";
       	 	var checkUserName = "";
            if(userids2.length>0){
            	for(var i=0;i<userids2.length;i++){
            		if(obj.checked){
            			userids2[i].checked =true;
            			if(""==checkUserid){
            				checkUserid =userids2[i].value ;
            				checkUserName =userids2[i].title ;
            			}else{
            		    	checkUserid =checkUserid+","+userids2[i].value ;
            		    	checkUserName =checkUserName+","+userids2[i].title ;
            			}
            		}else{
            			userids2[i].checked = false;
            			checkUserid="";
            			checkUserName="";
            		}
            	}
            }
			document.getElementById("checkUserId").value= checkUserid;
			document.getElementById("checkUserName").value= checkUserName;
       	}
       	
		function setCheckValues(){
			var userids2 = document.getElementsByName("userids");
			var checkUserid = "";
			var checkUserName = "";
           	if(userids2.length>0){
	           	for(var i=0;i<userids2.length;i++){
	           		if(userids2[i].checked){
	           			if(""==checkUserid){
	           				checkUserid = userids2[i].value;
	           				checkUserName = userids2[i].title;
	           			}else{
	           		    	checkUserid =checkUserid+","+userids2[i].value;
	           		    	checkUserName =checkUserName+","+userids2[i].title;
	           			}
	           		}
           		}
           	}
          	document.getElementById("checkUserId").value = checkUserid;
          	document.getElementById("checkUserName").value = checkUserName;
		}
   	</script>
</head>

<body style="overflow: auto">
<%@include file="/WEB-INF/template/content_title.jsp"%>

<form name="settingFrm" method="post">
<input type="hidden" name="nodeId" value="${nodeId}" id="nodeId"/>
<input type="hidden" id="checkUserId"/>
<input type="hidden" id="checkUserName"/>
<div class="outputdata">
<table align=center border="1">
	<tr height="22px">
		<td>传阅人</td>
		<td align="center">
			<input type="checkbox" onclick="checkAll(this);">
		</td>
	</tr>
	
	<c:forEach items="${dispatchUserList}" var="dispatchUser">
		<tr>
			<td>
				${dispatchUser.displayName} 
			</td>
			<td align="center">
				<input type="checkbox" name="userids" id="userids" value="${dispatchUser.id}" title="${dispatchUser.displayName}" 
					onclick="setCheckValues()">
			</td>
		</tr>
	</c:forEach>
	
	<tr height="22px">
		<td colspan='3' align='center'>
			<input type='button' value='提交' class="input_button" onclick="save_users(this.form)" /> 
			<input type='reset'	value='取消' class="input_button" /> 
			<input type='button' value='关闭' class="input_button" onclick="window.close('_self')" />
		</td>
	</tr>
</table>
</div>
</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
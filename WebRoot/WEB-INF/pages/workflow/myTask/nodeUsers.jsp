<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<c:set var="curr_path" value="选择任务参与人"></c:set>
<html>
	<head>
		<title>选择任务参与人</title>
		<script type="text/javascript" src="${ctx}/webResources/js/common.js"></script>
		<script type="text/javascript" src="${ctx}/webResources/js/easyui/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src='<c:url value="/webResources/js/easyui/jquery.easyui.min.js"/>'></script>
		<link rel="stylesheet" type="text/css" href='<c:url value="/webResources/js/easyui/themes/${curr_user.cssId}/easyui.css"/>'> 
		<link rel="stylesheet" type="text/css" href='<c:url value="/webResources/js/easyui/themes/icon.css"/>'>
		<script type="text/javascript" src="${ctx}/webResources/js/easyui/locale/easyui-lang-zh_CN.js"></script>  
		<link rel="stylesheet" type="text/css" href='<c:url value="/webResources/themes/${curr_user.cssId}/css/style.css" />' />
		<script>		
        function save_users(){
			var ff = document.getElementById("settingFrm");
			var checkUserId = document.getElementById("checkUserId").value;
		
        	if("" == checkUserId)
        	{
        		alert("请选择任务提交的用户！");
        		return false;
        	}else{
        		//ff.action = "saveSelectedParticipant.action";
        		//ff.submit();
       		   	var currTab = parent.document.frames["frm"].getCurrTab();
			   	var frmId = currTab[0].innerHTML.substring(11,currTab[0].innerHTML.length);
			   	frmId = frmId.substring(0,frmId.indexOf(" "));
			   	var frm = parent.document.frames["frm"].document.frames[frmId];

        		frm.document.getElementById("taskids").value = document.getElementById("checkTaskId").value;
        		frm.document.getElementById("userids").value = document.getElementById("checkUserId").value;
        		frm.document.getElementById("checkUserName").value = document.getElementById("checkUserName").value;
        		frm.document.getElementById("nextNodeId").value  =document.getElementById("nodeId").value;
        		frm.document.getElementById("procNodeId").value  =document.getElementById("procNodeId").value;
	        	var pform = frm.document.getElementById("form1");
	        	pform.submit();
	        	parent.close_win();
        	}
        }
        
		function checkAll(obj){
			var userids2 = document.getElementsByName("userids");
			var checkUserid="";
			var checkUserName="";
			var checkTaskId="";
            if(userids2.length>0){
            	for(var i=0;i<userids2.length;i++){
            		if(obj.checked){
            			userids2[i].checked =true;
	            		if(""==checkUserid){
	            			checkUserid =userids2[i].value ;
	            			checkTaskId =userids2[i].id ;
	            			checkUserName=userids2[i].title;
	            		}else{
	            		    checkUserid =checkUserid+","+userids2[i].value ;
	            			checkTaskId =checkTaskId+","+userids2[i].id ;
	            			checkUserName =checkUserName+","+userids2[i].title ;
	            		}
            		}else{
            			userids2[i].checked = false;
						checkUserid="";
						checkTaskId="";
            		}
            	}
            }
            document.getElementById("checkUserId").value= checkUserid;
            document.getElementById("checkTaskId").value=checkTaskId;
            document.getElementById("checkUserName").value=checkUserName;
       }
       
       function setCheckValues(){
			var userids2 = document.getElementsByName("userids");
			var checkUserid = "";
			var checkUserName = "";
			var checkTaskId = "";
            if(userids2.length>0){
				for(var i=0;i<userids2.length;i++){
            		if(userids2[i].checked){
	            		if(""==checkUserid){
	            			checkUserid =userids2[i].value ;
	            			checkTaskId =userids2[i].id ;
	            			checkUserName=userids2[i].title;
	            		}else{
	            		    checkUserid =checkUserid+","+userids2[i].value ;
	            			checkTaskId =checkTaskId+","+userids2[i].id ;
	            			checkUserName =checkUserName+","+userids2[i].title ;
	            		}
            		}
            	}
            }
           document.getElementById("checkUserId").value = checkUserid;
           document.getElementById("checkTaskId").value = checkTaskId;
           document.getElementById("checkUserName").value = checkUserName;
       }
</script>
	</head>

	<body style="overflow: auto">
		<%@include file="/WEB-INF/template/content_title.jsp"%>

		<form name="settingFrm" method="post">
			<input type="hidden" name="nodeId" value="${nodeId}" id="nodeId" />
			<input type="hidden" name="procNodeId" value="${procNodeId}" id="procNodeId" />
			<input type="hidden" name="checkUserId" id="checkUserId" />
			<input type="hidden" name="checkUserName" id="checkUserName" />
			<input type="hidden" name="checkTaskId" id="checkTaskId" />
			<div class="outputdata">
			<table align=center>
				<tr height="22px">
					<td>
						任务名称
					</td>
					<td>
						参与人
					</td>
					<td align="center">
						<input type="checkbox" onclick="checkAll(this);">
					</td>
				</tr>
				<c:forEach items="${stNodeTaskList }" var="stNodeTask">
					<tr>
						<c:if test="${stNodeTask.participantNum eq 1 }">
							<td>
								${stNodeTask.taskName }
							</td>
							<c:forEach items="${stNodeTask.nodeTaskParticipantList }"
								var="participant">
								<td>
									${participant.userName }
								</td>
								<td align="center">
									<input type="checkbox" name="userids"
										title="${participant.userName }" id="${stNodeTask.taskId }"
										value="${participant.userId }" onclick="setCheckValues()">
									<input type="hidden" name="taskids" id="taskids"
										value="${stNodeTask.taskId }" />
								</td>
							</c:forEach>
						</c:if>
					</tr>
					<tr>
					
					<c:if test="${stNodeTask.participantNum gt 1}">
						<td rowspan="${stNodeTask.participantNum }">
							${stNodeTask.taskName }
						</td>
						<c:forEach items="${stNodeTask.nodeTaskParticipantList }"
							var="participant">
							<td>
								${participant.userName }
							</td>
							<td align="center">
								<input type="checkbox" name="userids"
									title="${participant.userName }" id="${stNodeTask.taskId }"
									value="${participant.userId }" onclick="setCheckValues()">
								<input type="hidden" name="taskids" id="taskids"
									value="${stNodeTask.taskId }" />
							</td>
							</tr>
						</c:forEach>
					</c:if>
				</c:forEach>
				<tr>
					<td style="text-align: center;" valign="middle" colspan="4">
						<a href="###" class="easyui-linkbutton" onclick="save_users()" iconCls="icon-save">提交</a>
						<a href="###" class="easyui-linkbutton" onclick="parent.close_win()" iconCls="icon-back">关闭</a>
					</td>
				</tr>
			</table>
        </div>
		</form>
		<%@include file="/WEB-INF/template/pagefoot.jsp"%>
	</body>
</html>
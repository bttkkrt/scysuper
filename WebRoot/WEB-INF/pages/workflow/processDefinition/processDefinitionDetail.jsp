<%@page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>工作流管理</title>
		<link rel="stylesheet" type="text/css"
              href='<c:url value="/webResources/themes/${curr_user.cssId}/css/style.css" />' />
        <script type="text/javascript"	src='<c:url value="/webResources/js/easyui/jquery-1.4.4.min.js"/>'></script>
		<script src='<c:url value="/webResources/js/common.js"/>'></script>
		<script type="text/javascript">
	function doReturn() {
		window.history.go(-1);
	}
	
	function loadNodeConf(id) {
		$.ajaxSetup ({
			cache: false 
		});
		var procDefId = document.getElementById("procDefId").value;
		//alert("/workflow/workflow/initTaskNodeConfig.action?staticTaskNodeID=" + id+"&procDefId="+procDefId);
		//return;
		
			$("#editTaskNode").load('<c:url value="/workflow/initTaskNodeConfig.action"/>'+"?staticTaskNodeID=" + id + "&procDefId=" + procDefId );
	}
	
	function loadTaskConf(nodeId, taskId) {
		$.ajaxSetup ({
			cache: false 
		});
		var procDefId = document.getElementById("procDefId").value;
			$("#editTaskConf").load('<c:url value="/workflow/taskConfigIndex.action"/>'+"?staticTaskNodeID=" 
				+ nodeId + "&procDefId=" + procDefId + "&taskId=" + taskId );			
	}
	
	function changeshow(srcEle){
		var srcElement = $(srcEle);
		var imgShow = '<c:url value="/webResources/images/Workflow/Lminus.png"/>';
		var imgHidden = '<c:url value="/webResources/images/Workflow/Lplus.png"/>';
		while(srcElement.attr('tagName') !='TR'){
			srcElement = srcElement.parent();
		}
		srcElement = srcElement.next();
		while(srcElement.attr('canShow') == '1'){  
			srcElement.children("td[canShow='1']").each(function(){
				if($(this).css('display') == 'none'){
					$(this).css('display','block');
					srcEle.src = imgShow;
				}else{
					$(this).css('display','none');
					srcEle.src = imgHidden;
				}
			});
			srcElement = srcElement.next();
		}
	}	
</script>
	</head>
	<body class="pages_right">
		<c:set var="curr_path" value="流程节点配置"></c:set>
		<%@include file="/WEB-INF/template/content_title.jsp"%>
		<form action="" method="post" id="form">

			<input type="hidden" name="procDefId" id="procDefId"
				value="${procDefId}" />
          <div class="outputdata">
			<table cellspacing="0" cellpadding="0" width="99%" border="1" >
				<tr>
					<th>
						序号
					</th>
					<th>
						流程定义名称
					</th>
					<th>
						节点名称
					</th>
					<th>
						节点属性
					</th>
					<!-- <th>
						节点参与者配置
					</th> -->
					<th>
						任务参与者配置
					</th>
				</tr>

				<c:forEach items="${staticProcessNodeList}" var="staticProcessNode"
					varStatus="status">
					<c:if test="${status.index %2==0}">
						<tr height="22px" >
					</c:if>
					<c:if test="${status.index%2==1}">
						<tr height="22px">
					</c:if>
					<td rowspan="${staticProcessNode.numOfTaskList}"
						style="text-align: center">
						${status.index}
					</td>
					<td rowspan="${staticProcessNode.numOfTaskList}"
						style="text-align: center">
						${staticProcessNode.staticProcessDefinition.processName}
					</td>
					<td rowspan="${staticProcessNode.numOfTaskList}"
						style="text-align: center">
						${staticProcessNode.nodeName}
					</td>
					<td rowspan="${staticProcessNode.numOfTaskList}"
						style="text-align: center">
						<c:if test="${staticProcessNode.nodeType eq 'D'}">决策节点</c:if>
						<c:if test="${staticProcessNode.nodeType eq 'E'}">结束节点</c:if>
						<c:if test="${staticProcessNode.nodeType eq 'F'}">分支节点</c:if>
						<c:if test="${staticProcessNode.nodeType eq 'J'}">join节点</c:if>
						<c:if test="${staticProcessNode.nodeType eq 'K'}">任务节点</c:if>
						<c:if test="${staticProcessNode.nodeType eq 'M'}">邮件节点</c:if>
						<c:if test="${staticProcessNode.nodeType eq 'N'}">node节点</c:if>
						<c:if test="${staticProcessNode.nodeType eq 'R'}">开始节点</c:if>
						<c:if test="${staticProcessNode.nodeType eq 'S'}">状态节点</c:if>
						<c:if test="${staticProcessNode.nodeType eq 'C'}">流程状态节点</c:if>
					</td>
					<!--  <td rowspan="${staticProcessNode.numOfTaskList}"
							style="text-align: center">
							<img src='<c:url value="/images/SmallIcon/edit.gif"/>'
								onclick="loadNodeConf('${staticProcessNode.id}')"
								title="配置节点参与者" style="cursor: pointer;" />
						</td>
-->
					<c:if test="${ empty staticProcessNode.staticNodeTaskList}">
						<c:if test="${status.index %2==0}">
							<td height="22px">
						</c:if>
						<c:if test="${status.index%2==1}">
							<td height="22px">
						</c:if>
					</c:if>
					<c:forEach items="${staticProcessNode.staticNodeTaskList}"
						var="staticNodeTask" varStatus="s">
						<c:set var="displayValue" value="block" />
						<c:set var="canShow" value="0" />
						<c:if test="${s.index gt 0}">
							<c:set var="canShow" value="1" />
							<c:set var="displayValue" value="none" />
							<c:if test="${s.index %2==0}">
								<tr canShow="${canShow }" border="0"
									class="DataGridAlternatingItemStyle">
							</c:if>
							<c:if test="${s.index%2==1}">
								<tr canShow="${canShow }" border="0">
							</c:if>
							<td style="text-align: center" style="display:${displayValue }"
								canShow="${canShow }" border="0">
								${staticNodeTask.taskName}
								<img
									src='<c:url value='/webResources/images/icons/edit.gif'/>'
									onclick="loadTaskConf('${staticProcessNode.id}','${staticNodeTask.id}')"
									title="配置任务参与者" style="cursor: pointer;" />
							</td>
						</c:if>
						<c:if test="${s.index eq 0}">
							<td style="text-align: center" style="display:${displayValue }"
								canShow="${canShow }" border="0">
								<c:if test="${staticProcessNode.numOfTaskList gt 1}">
									<img
										src='<c:url value="/webResources/images/Workflow/Lplus.png"/>'
										onclick="changeshow(this)" style="cursor: pointer;" />
								</c:if>
								${staticNodeTask.taskName}
								<img
									src='<c:url value='/webResources/images/icons/edit.gif'/>'
									onclick="loadTaskConf('${staticProcessNode.id}','${staticNodeTask.id}')"
									title="配置任务参与者" style="cursor: pointer;" />
							</td>
						</c:if>
					</c:forEach>
				</c:forEach>
			</table>
			</div>
		</form>
        
       
		<table>
			<tr>
				<td align="center" >
					<div id="editTaskNode"></div>
				</td>
				<td align="center" style="height: 300px">
					<div id="editTaskConf"></div>
				</td>
			</tr>
		</table>
		
	</body>
</html>
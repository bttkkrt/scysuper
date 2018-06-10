<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
	<style type="text/css">
		#tableItem tr td{
			border:black 1px solid;
			text-align: center;
		}
		#tableItem tr th{
			border:black 1px solid;
			text-align: center;
			background-color: silver;
		}
	</style>
	<script type="text/javascript">
	$(function(){
		 $.ajax({
		    url: '${ctx}/jsp/admin/code/findCodeValue.action',
		    type: 'post',
		    dataType: 'json',
		    async : false,
		    data:{        
		    	"codeValue.itemValue" : '${safeInspectDistribute.inspectType}',
		        "codeValue.codeId" : "402881e7497d66f001497d7f65a40007"    
		    },
		    error: function(){
		        $.messager.alert('提示','获取一维代码错误！');
		    },
		    success: function(data){
		       	$("#itemText").html(data.itemText);
	    	}
		});
		$.ajax({
		    url: '${ctx}/jsp/safeInspectDistribute/findUsernameById.action',
		    type: 'post',
		    dataType: 'json',
		    async : false,
		    data:{        
		    	"userId" : '${safeInspectDistribute.personnel}'
		    },
		    error: function(){
		    },
		    success: function(data){
		       	$("#personnel").html(data.userName);
		   	}
		});
		if(""!=reviewUserId){
			$.ajax({
			    url: '${ctx}/jsp/safeInspectDistribute/findUsernameById.action',
			    type: 'post',
			    dataType: 'json',
			    async : false,
			    data:{        
			    	"userId" : '${reviewUserId}'
			    },
			    error: function(){
			    },
			    success: function(data){
			       	$("#reviewUser").html(data.userName);
			   	}
			});
		}
		$.ajax({
		    url: '${ctx}/jsp/safeInspectDistribute/findDeptnameByDeptcode.action',
		    type: 'post',
		    dataType: 'json',
		    async : false,
		    data:{        
		    	"deptCode" : '${safeInspectDistribute.quarters}'
		    },
		    error: function(){
		    },
		    success: function(data){
		       	$("#deptName").html(data.deptName);
	    	}
		});
		
	});
	//图片放大查看
	function amplifyImg(path)
	{
		var iWidth=800; //弹出窗口的宽度;
		var iHeight=600; //弹出窗口的高度;
		var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
		var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
		window.open('${ctx}/jsp/distributeItem/viewImg.action?imgPath='+path,'newwindow','height='+iHeight+',width='+iWidth+',top='+iTop+',left='+iLeft);
	}
	</script>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<div style="overflow-y: auto;width:100%;height:599px;" >
	<input type="hidden"  id="companyFlag" name="companyFlag" value="${companyFlag}">
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
			
			
				<tr>
					<th width="15%">检查类型</th>
					<td width="35%" id="itemText"></td>
					<th width="15%">巡检标题</th>
					<td width="35%">${safeInspectDistribute.title}</td>
				</tr>
				<tr>
					<th width="15%">检测岗位</th>
					<td width="35%" id="deptName"></td>
					<th width="15%">检查总次数</th>
					<td width="35%" id="deptName">${safeInspectDistribute.inspectNum}</td>
				</tr>
				<tr>
					<th width="15%">初查人员</th>
					<td width="35%" id="personnel"></td>
					<th width="15%">复查人员</th>
					<td width="35%" id="reviewUser"></td>
				</tr>
				<tr>
					<th width="15%">检查时间</th>
					<td width="35%" id="personnel">${safeInspectDistribute.reportTime}</td>
					<th width="15%">检查位置</th>
					<td width="35%" id="deptName">${safeInspectDistribute.local}</td>
				</tr>
				
				<tr><td colspan="4" >&nbsp;</td></tr>
				<tr >
					<td colspan="4"  style="text-align: center;width: 99%">
						<table id="tableItem" style=" border: black 1px solid;">
							
							<c:forEach  var="taskMap" items="${totalTaskList}" varStatus="taskstatus" >
								
								<tr><td colspan="6" style="font-size: medium">第${taskstatus.index+1}次检查</td></tr>
								<tr >
									<th style="width: 10%;">巡检项</th>
									<th style="width: 25%;">巡检要求</th>
									<th style="width: 10%;">巡检标记</th>
									<th style="width: 10%;">是否达标</th>
									<th style="width: 20%;">描述</th>
									<th style="width: 25%;">照片信息</th>
								</tr>
								<c:forEach  var="dist" items="${taskMap.distList}" varStatus="status">
									<tr >
										<td style="width: 150px;" rowspan="2">${dist.item}</td>
										<td style="width: 320px;" rowspan="2">${dist.requirement}</td>
										<td>初查</td>
										<td style="width: 80px;">
											<c:if test="${2==dist.isinspect}">
												是
											</c:if>
											<c:if test="${3==dist.isinspect}">
												否
											</c:if>
											<c:if test="${2!=dist.isinspect && 3!=dist.isinspect}">
												未检查
											</c:if>
										</td>
										<td style="width: 320px;">${dist.describe}</td>
										<td>
											<c:forEach  var="tempImgList" items="${taskMap.imgList[fn:length(taskMap.distList)*taskstatus.index+status.index]}" varStatus="imgstatus">
													<img style="width:60px;heigh:60px;" src="${tempImgList}" onclick="amplifyImg(&quot;${tempImgList}&quot;)" title="单击查看大图" />
											</c:forEach>
										</td>
									</tr>
									<tr>
										<td>复查</td>
										<td style="width: 80px;">
											<c:if test="${2==dist.reviewResult}">
												是
											</c:if>
											<c:if test="${3==dist.reviewResult}">
												否
											</c:if>
											<c:if test="${2!=dist.reviewResult && 3!=dist.reviewResult}">
												未检查
											</c:if>
											
										</td>
										<td style="width: 320px;">${dist.reviewRemark}</td>
										<td>
											<c:forEach  var="rtempImgList" items="${taskMap.reviewImgList[fn:length(taskMap.distList)*taskstatus.index+status.index]}" varStatus="rimgstatus">
													<img style="width:60px;heigh:60px;" src="${rtempImgList}" onclick="amplifyImg(&quot;${rtempImgList}&quot;)" title="单击查看大图" />
											</c:forEach>
										</td>
									</tr>
								</c:forEach>
							</c:forEach>
						</table>
					</td>
				</tr>
				<tr><td colspan="4" >&nbsp;</td></tr>
				<tr>
					<td colspan="4" height="100px" style="text-align: center;">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="parent.close_win();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
	</div>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

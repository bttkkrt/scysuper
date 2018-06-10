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
		
		var temp = "";
		var arr = '${safeInspectDistribute.personnel}'.split(",");
		for( i=0;i<arr.length;i++){
			 $.ajax({
			    url: '${ctx}/jsp/safeInspectDistribute/findUsernameById.action',
			    type: 'post',
			    dataType: 'json',
			    async : false,
			    data:{        
			    	"userId" : arr[i]
			    },
			    error: function(){
			        
			    },
			    success: function(data){
			    	if(temp.length>0)
			    		temp +="、";
		        	temp += data.userName;
		    	}
			});
		}
		$("#personnel").html(temp);
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
		
		getCycleVal();
	});
	function getCycleVal(){
		var temp = "";
		var arr = '${safeInspectDistribute.cycleValue}'.split(",");
		for( i=0;i<arr.length;i++){
			 if(arr[i]==2){
				 if(temp.length>0)
			    		temp +="、";
				 temp += "周一";
			 }
			 if(arr[i]==3){
				 if(temp.length>0)
			    		temp +="、";
				 temp += "周二";
			 }
			 if(arr[i]==4){
				 if(temp.length>0)
			    		temp +="、";
				 temp += "周三";
			 }
			 if(arr[i]==5){
				 if(temp.length>0)
			    		temp +="、";
				 temp += "周四";
			 }
			 if(arr[i]==6){
				 if(temp.length>0)
			    		temp +="、";
				 temp += "周五";
			 }
			 if(arr[i]==7){
				 if(temp.length>0)
			    		temp +="、";
				 temp += "周六";
			 }
			 if(arr[i]==1){
				 if(temp.length>0)
			    		temp +="、";
				 temp += "周日";
			 }
			 
		}
		$("#cycleValue").html(temp);
	}
	</script>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<input type="hidden"  id="companyFlag" name="companyFlag" value="${companyFlag}">
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="15%">巡检标题</th>
					<td width="35%">${safeInspectDistribute.title}</td>
					<th width="15%">检查类型</th>
					<td width="35%"  id="itemText"></td>
				</tr>
				<tr>
					<th width="15%">起止日期</th>
					<td width="35%">${safeInspectDistribute.startDate}&nbsp;-&nbsp;${safeInspectDistribute.endDate}</td>
					<th width="15%">检查次数</th>
					<td width="35%">${safeInspectDistribute.inspectNum}</td>
				</tr>
				<tr>
					<c:if test="${safeInspectDistribute.cycleFlag==2 }">
						<th width="15%">检查周期</th>
						<td width="35%">月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<c:if test="${safeInspectDistribute.cycleValue=='any' }">
								任意（周期内任意时间上报）
							</c:if>
						</td>
						<th width="15%">检查日期</th>
						<td width="35%">
							<c:if test="${safeInspectDistribute.cycleValue!='any' }">
								${safeInspectDistribute.cycleValue}
							</c:if>
						</td>
					</c:if>
					<c:if test="${safeInspectDistribute.cycleFlag==1 }">
						<th width="15%">检查周期</th>
						<td width="35%">周&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<c:if test="${safeInspectDistribute.cycleValue=='any' }">
								任意（周期内任意时间上报）
							</c:if>
						</td>
						<th width="15%">检查日期</th>
						<td width="35%" id="cycleValue"></td>
					</c:if>
				</tr>
				<tr>
					<th width="15%">检测岗位</th>
					<td width="35%" id="deptName"></td>
					<th width="15%">任务状态</th>
					<td width="35%"><c:if test="${safeInspectDistribute.taskStatus==0 }">启用</c:if><c:if test="${safeInspectDistribute.taskStatus!=0 }">不启用</c:if></td>
				</tr>
				<tr>
					<th width="15%">巡检人员</th>
					<td width="35%"colspan="3" id="personnel"></td>
				</tr>
				<tr><td colspan="4" >&nbsp;</td></tr>
				<tr>
					<td colspan="4" style="text-align: center;">
						<table id="tableItem" style="width: 650px; border: black 1px solid;margin:auto;">
							<tr >
								<th style="width: 150px;">巡检项</th>
								<th style="width: 500px;">巡检要求</th>
							</tr>
							
							<c:forEach  var="dist" items="${distList}"  varStatus="status">
								<tr>
									<td>${dist.item}</td>
									<td>${dist.requirement}</td>
								</tr>
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
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

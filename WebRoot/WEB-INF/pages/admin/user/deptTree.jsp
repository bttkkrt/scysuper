<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>部门树</title>
		<%
			String func = request.getParameter("func");
		%>
		<script type="text/javascript" src="${ctx}/webResources/js/dept.js"></script>
		<%@include file="/common/jsLib.jsp"%>
		<script>
			$(function(){
	             $('#deptTree').tree({  
	                 checkbox: true,
	                 cascadeCheck : false,
	                 animate:true,   
	                 url: 'findLinkedDept.action?userId=${userId}', 
	                 onBeforeExpand:function(node,param){
	                     $('#deptTree').tree('options').url = "findLinkedDept.action?userId=${userId}&selDept=" + node.id;
                 	 },onLoadSuccess:function(node,data){
                 		for(var i=0;i<data.length;i++){
                 			//if(node)
                     		//	$('#deptTree').tree("expand",$('#deptTree').tree("getChildren",node.target)[i].target);
                			//else
                				$('#deptTree').tree("expandAll",$('#deptTree').tree("getRoots")[i].target);
                 		} 
                 	 },onCheck: function(node){
                 		 if(node.id=="${user.deptCode}" && !node.checked){
                 			 $.messager.alert("提示","该用户的主部门不能设置为关联部门");
                 			 node.checked = false;
               			     $('#deptTree').tree('update', node);
                 		 }
                 	 }
	             });   
			}); 
			function saveUserLinkedDept(){
				var nodes = $('#deptTree').tree('getChecked');
				var s = '';
				for(var i=0; i<nodes.length; i++){
					if("${user.deptCode}".indexOf(nodes[i].id)==-1){
						s += nodes[i].id + "|";
					}
				}
				$.ajax({
					url : "saveLinkedDept.action",
					data : {
						"userId" : "${user.id}",
						"linkedDeptIds" : s
					},
					datatype : "json",
					success : function(data){
						data = eval("(" + data + ")");
						if(data.result){
							$.messager.alert("成功","保存用户的关联部门成功", "info", function(flag){
								parent.close_win('set_userLinkedDept');
							});							
						}else{
							$.messager.alert("错误", "保存用户的关联部门出错", "error");
						}
					},
					error : function(){
						$.messager.alert("错误", "保存用户的关联部门出错", "error");
					}
				});
			}
        </script>
	</head>
	<body>
		<table style="width:100%; height:100%">
			<tr><td height="300px">
				<div style="overflow-x: hidden;overflow-y: auto; height: 100%; width: 100%; border: 0px solid #c3daf9;">
					<ul id="deptTree"></ul>
				</div>
			</td></tr>
			<tr><td height="30" style="text-align:center">
		    	<a href="###" class="btn_01" onclick="saveUserLinkedDept()">保存<b></b></a>
				<a href="###" class="btn_01" onclick="parent.close_win('set_userLinkedDept')">关闭<b></b></a>
			</td></tr>
		</table>
	</body>
</html>
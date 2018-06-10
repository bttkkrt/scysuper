<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>添加/修改用户信息</title>
		<%@include file="/common/jsLib.jsp"%>
		
		<script>
		function search_parent(){
			//createWindow("setUserDept","设置用户部门",50,50,"250","400","${ctx}/jsp/admin/dept/deptTree.action?func=setDept");
			//createSimpleWindow("setUserDept", "设置用户部门", "${ctx}/jsp/admin/dept/deptTree.action?func=setDept", 250, 400);
			var indexLayer =layer.open({
				  type: 2,
				  title: "设置用户部门",
				  maxmin: true,
				  shadeClose: true, //点击遮罩关闭层
				  area : [250, 400],
				  content: "${ctx}/jsp/admin/dept/deptTree.action?func=setDept"
			});
		}
		function close_win(windowId){
			debugger;
			if(!windowId || windowId=="" || windowId == "null"){
				$("#win").attr("src", "<c:url value='/blank.html'/>");
				$("#newWindow").window("close", true);
			}else{
				$("#"+windowId+"_frm").attr("src", "<c:url value='/blank.html'/>");
				$("#"+windowId).window("close", true);
				$("#"+windowId).remove();
			}
		}
		function init_password(id){
			$.messager.confirm('初始化密码', '确定要初始化该用户的密码？', function(result){
				if (result){
					$.ajax({
						url : "initUserPassword.action",
						type: 'post',
						dataType: 'json',
						async : false,
						data:{ 
							"user.id" : id
						},
						error: function(){
							$.messager.alert('错误','始化该用户的密码时出错！');
						},
						success: function(data){
							if(data.result){
								$.messager.alert('提示','始化该用户的密码成功！', 'info',function(){
									parent.close_win();
									reloadData('edit_user');
								});
							}else{
								$.messager.alert('错误','始化该用户的密码时出错！');
							}
						}
					});					
				}
			});
		}
		
		function save()
		{
			var strs = new Array(); //定义一数组
        	var pic1= $("#upload").val();
        	strs = pic1.split('.');
        	var suffix = strs [strs .length - 1];

        	if (suffix != '' && suffix != 'jpg' && suffix != 'gif' && suffix != 'jpeg' && suffix != 'png' && suffix != 'bmp') {
           	 	alert("只能上传jpg、png、gif、bmp、jpeg格式文件！");
            	var obj = document.getElementById('upload');
            	obj.outerHTML = obj.outerHTML; //这样清空，在IE8下也能执行成功
        	}
        	else
        	{
        		var formData = new FormData($( "#myform" )[0]);  
        		layer.load(0, {shade: false});
	     		$.ajax({  
	          		url: 'saveUser.action' ,  
	          		type: 'POST',  
	          		data: formData,  
	          		dataType: 'json',
	          		async: false,  
	          		cache: false,  
	          		contentType: false,  
	          		processData: false,  
	          		success: function (data) {
	          			parent.getResponse(data);
	          			closeLayer();
	             	 	/* if(data.status=="y"){
        					reloadData('edit_user');
		             	 	closeLayer();
	        				/* parent.$.messager.alert("成功","保存成功！", "info",function(){
	        				}); 
	        			}else{
	        				parent.$.messager.alert("错误",data.info);
	        			} */
	          		},  
	          		error: function () {  
	              		alert("保存时出错");  
	              		closeLayer();
	          		}  
	     		});  
        	}
		}
		
		
		function formAjaxCallback(data){
			if(data.status=="y"){
        		parent.$.messager.alert("成功","保存成功！", "info",function(){
        			reloadData('edit_user');
        		});
        		//parent.close_win('edit_user');
        		//暂缺刷新代码
        	}else{
        		parent.$.messager.alert("错误",data.info);
        	}
		}
    </script>
	</head>
	<body validform="true">
		<div class="page_dialog">
				<div class="inner6px">
					<div class="cell">
						<form id="myform" name="myform" method="post">
							<s:token />
							<c:if test="${not empty user.id}">
								<input type="hidden" name="user.id" value="${user.id}">
								<input type="hidden" name="user.delFlag" value="${user.delFlag}">
							</c:if>
							<table>
								<tr>
									<th width="15%">姓名<font color="red">*</font></th>
									<td width="35%"><input name="user.displayName" value="${user.displayName}" datatype="*1-127" errormsg="昵称至少1个字符,最多127个字符！"></td>
								</tr>
								<tr>
									<th>用户名<font color="red">*</font></th>
									<td>
										<c:if test="${empty user.loginId}">
											<input name="user.loginId" datatype="*1-50" errormsg="用户名必须填写">
										</c:if>
										<c:if test="${not empty user.loginId}">
											<input value="${user.loginId}" name="user.loginId" >
										</c:if>
									</td>
								</tr>
								<tr>
									<th>所属部门<font color="red">*</font></th>
									<td>
										<input id="deptNameOne" name="deptName" value="${deptName}" datatype="*" nullmsg="请选择所属部门！" readOnly>
										<input id="deptCodeOne" type="hidden" name="user.deptCode" value="${deptCode}">
										<a href="###" class="btn_01_mini" onclick="search_parent();">查找<b></b></a>
									</td>
								</tr>
								<tr>
									<th>职务</th>
									<td><input name="user.duty" value="${user.duty}" maxlength="50"></td>
								</tr>
								<tr>
									<th>电话</th>
									<td><input name="user.tel" value="${user.tel}" dataType="n1-12" ignore="ignore"></td>
								</tr>
								<tr>
									<th>手机</th>
									<td><input name="user.mobile" value="${user.mobile}" dataType="m" maxlength="11" errormsg="必须为11位手机号码" ignore="ignore"></td>
								</tr>
								<tr>
									<th>电子邮箱</th>
									<td><input name="user.email" value="${user.email}" dataType="e" maxlength="60" errormsg="邮箱地址输入错误" ignore="ignore"></td>
								</tr>
								<tr>
									<th>执法证号</th>
									<td><input name="user.zfzh" value="${user.zfzh}" maxlength="127"></td>
								</tr>
								<tr>
									<th>电子签名</th>
									<td>
				    					<input type="file" name="upload" id="upload"  datatype="*"
											nullmsg='请选择图片  ' sucmsg='图片选择正确！'/>
											<br/>
											<span class="red">*只能上传jpg、png、gif、bmp、jpeg格式文件</span>
				    				</td>
								</tr>
								<tr>
									<th>
										序号
										<font color="red">*</font>
									</th>
									<td>
										<input type='text' dataType="n1-1000" maxlength="4"
											msg="请输入序号，序号必须是小于1000的数字" name="user.sortSq" id="sortSq"
										value="${user.sortSq }" class="input_text">
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="btn_area_setc"> 
											<a href="###" class="btn_01_mini" onclick="init_password('${user.id}');">初始化密码<b></b></a>
											<a href="###" class="btn_01_mini" onclick="save()">保存<b></b></a>
											<!--<a href="###" class="btn_01_mini" onclick="clear_form(document.myform);">取消<b></b></a>-->
											<a href="###" class="btn_01_mini" onclick="closeLayer();">关闭<b></b></a>
										</div>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
		</div>
	</body>
</html>
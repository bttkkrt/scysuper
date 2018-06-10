<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>登录页面管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_loginImage","添加登录页背景图","${ctx}/jsp/loginImage/loginImageInitEdit.action?flag=add&dt="+dt.getTime(),400,300);
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_loginImage","修改登录页背景图","${ctx}/jsp/loginImage/loginImageInitEdit.action?flag=mod&loginImage.id="+row_Id+"&dt="+dt.getTime(),400,300);
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_loginImage","查看登录页背景图","${ctx}/jsp/loginImage/loginImageView.action?loginImage.id="+row_Id+"&dt="+dt.getTime(),800,600);
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_loginImage();
        }
        function del(){
        	var rows = $('#pagination').datagrid('getSelections');
        	var ids = "";
			for(var i=0;i<rows.length;i++){
				ids += rows[i].id+"|";
			}
			if(rows.length<1){
			    $.messager.alert('提示','至少选择一项删除！');
			}else{
			    $.messager.confirm("删除","确定要删除吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "loginImageDel.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	ids : ids
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','删除时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	$.messager.alert('提示','删除成功！');
		                        	search_loginImage();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_loginImage(){
        	var queryParams = {
				"loginImage.imageName": $("#imageName").val(),
				"loginImage.isUsing": $("#isUsing").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        $(function(){
			$('#pagination').datagrid($.extend(dg_cm_pp,{
				title:'登录页面管理列表',
				toolbar:[{
					id:'btnadd',
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
						addNew();
					}
				},{
					id:'btncut',
					text:'删除',
					iconCls:'icon-remove',
					handler:function(){
						del();
					}
				}],				
				url:'loginImageQuery.action',
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],				
				columns:[[
				          	{field:'imageName',title:'图片名',width:50},
							{field:'imageUrl',title:'图片地址',width:200},
							{field:'isUsing',title:'使用标识',width:50,formatter:function(value,rec){
								if(rec.isUsing == 1) return "正在使用";
								else return "未使用";
							}},
							{field:'op',title:'操作',width:50,formatter:function(value,rec){
		                        var restr="<a href='#' onclick=\"view('"+rec.id+"')\" class='btn_01_mini'>查看<b></b></a><a href='#' onclick=\"edit('"+rec.id+"')\" class='btn_01_mini'>修改<b></b></a>";
		                        if(rec.isUsing == 0){
		                        	restr+="<a href='#' onclick=\"do_on('"+rec.id+"')\" class='btn_01_mini'>启用<b></b></a>";
		                        }else if(rec.isUsing == 1){
		                        	restr+="<a href='#' onclick=\"do_off('"+rec.id+"')\" class='btn_01_mini'>停用<b></b></a>";
		                        }
                        		return restr;							
							}}
				        ]]
				
			}));
		});
        function do_on(id){
			$.messager.confirm("提示","确定使用该图片作为登录背景图吗？",function(result){
		        if(result){
	                $.ajax({
	                	url : "loginImageOn.action",
	                	type: 'post',
	                    dataType: 'json',
	                    async : false,
	                    data:{ 
	                    	"loginImage.id" : id
	                    },
	                    error: function(){
	                    	$.messager.alert('错误','操作出错！');
	                    },
	                    success: function(data){
	                        if(data.result){
	                        	$.messager.alert('提示','操作成功！','info',function(){
	                        		search_loginImage();
	                        	});
	                        }else{
	                        	$.messager.alert('错误','操作出错！');
	                        }
	                    }
	                });
		        }
		    });
        }
        function do_off(id){
			$.messager.confirm("提示","确定要停用该图片作为登录背景图吗?",function(result){
		        if(result){
	                $.ajax({
	                	url : "loginImageOff.action",
	                	type: 'post',
	                    dataType: 'json',
	                    async : false,
	                    data:{ 
	                    	"loginImage.id" : id
	                    },
	                    error: function(){
	                    	$.messager.alert('错误','操作出错！');
	                    },
	                    success: function(data){
	                        if(data.result){
	                        	$.messager.alert('提示','操作成功！','info',function(){
	                        		search_loginImage();
	                        	});
	                        }else{
	                        	$.messager.alert('错误','操作出错！');
	                        }
	                    }
	                });
		        }
		    });
        }
    </script>
</head>
<body>
	<div class="page_content">
		<div class="box_01 submitdata">
			<div class="inner12px">
				<form name="myform" method="post">
					<div class="cell boxBmargin12" >
						<table>
							<tr>
								<th width="15%">图片名</th>
								<td width="35%"><input class="form_text" name="loginImage.imageName" id="imageName" value="${loginImage.imageName}" type="text"></td>
								<th width="15%">使用状态</th>
								<td width="35%">
									<select id="isUsing" name="loginImage.isUsing" style="width: 136px;">
										<option value="">---请选择---</option>
										<option value="0"
											<c:if test="${loginImage.isUsing==0}">selected</c:if>>
											未使用
										</option>
										<option value="1"
											<c:if test="${loginImage.isUsing==1}">selected</c:if>>
											正在使用
										</option>
									</select>
								</td>
							</tr>
							<tr></tr>
							<tr>
								<td colspan="4">
									<div class="btn_area_setc">
										<a href="###" class="btn_01" onclick="search_loginImage()">查询<b></b></a>
										<a href="###" class="btn_01" onclick="clear_form(document.myform);">清空<b></b></a>
									</div>
								</td>
							</tr>							
						</table>
					</div>
					<div id="pagination">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>

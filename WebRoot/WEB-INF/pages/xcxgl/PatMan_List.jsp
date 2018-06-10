<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>巡查项管理管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_patMan","添加巡查项管理","${ctx}/jsp/xcxgl/patManInitEdit.action?flag=add&dt="+dt.getTime()+"&patMan.patrolType=${patMan.patrolType}",550,400);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_patMan","修改巡查项管理","${ctx}/jsp/xcxgl/patManInitEdit.action?flag=mod&patMan.id="+row_Id+"&dt="+dt.getTime()+"&patMan.patrolType=${patMan.patrolType}",550,400);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_patMan","查看巡查项管理","${ctx}/jsp/xcxgl/patManView.action?patMan.id="+row_Id+"&dt="+dt.getTime(),550,400);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_patMan();
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
		                	url : "patManDel.action",
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
		                        	search_patMan();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_patMan(){
        	var queryParams = {
				"patMan.patrolName": $("#patrolName").val(),
"patMan.patrolType": "${patMan.patrolType}"
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	var toolbar = [];
        	var frozen=[];
        	if('${roleName}'=='11'){//判断登录角色
				toolbar = [{
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
				}];
				
				frozen=[[
				    {field:'id',checkbox:true}
				]];
				
				};
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'巡查项管理列表',
				url:'patManQuery.action',
				queryParams:{
					"patMan.patrolName": $("#patrolName").val(),
					"patMan.patrolType": "${patMan.patrolType}"
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'patrolName',title:'巡查项',width:100},
							{field:'patrolType',title:'巡查项类型',width:100,formatter:function(value,rec){
								 var temp = '';
								    $.ajax({
								    url: '${ctx}/jsp/xcxlxgl/patTypManGetName.action',
								    type: 'post',
								    dataType: 'json',
								    async : false,
								    data:{ "patTypMan.id" : rec.patrolType},
								    error: function(){
								        $.messager.alert('提示','获取一维代码错误!');
								    },
								    success: function(data){
								        temp = data.itemText;
								    }});
								    return temp;}},
							{field:'op',title:'操作',width:100,formatter:function(value,rec){
								var temp =  "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;";
									if("${roleName}" == '11'){
										temp += "<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
									}
									return temp;
								}}
				        ]],
				toolbar:toolbar 
			})); 
		
		});

        
    </script>
</head>
<body>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
			<div class="cell boxBmargin12">
			<table width="100%">
				<tr>
					
				<th width="15%">巡查项名称</th>
				<td width="35%"><input name="patMan.patrolName" style="width: 50%" id="patrolName" value="${patMan.patrolName}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_patMan()" >查询<b></b></a>&nbsp;
						<a href="###" class="btn_01" onclick="clear_form(document.myform);" >清空<b></b></a>&nbsp;				
					</td>
				</tr>
			</table>
		</div>
		
		<div id="pagination" >
		</div>
		</div>
		</div>
	</div>
</body>
</html>

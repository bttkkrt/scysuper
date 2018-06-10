<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>巡查项类型管理管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_patTypMan","添加巡查项类型管理","${ctx}/jsp/xcxlxgl/patTypManInitEdit.action?flag=add&dt="+dt.getTime(),500,250);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_patTypMan","修改巡查项类型管理","${ctx}/jsp/xcxlxgl/patTypManInitEdit.action?flag=mod&patTypMan.id="+row_Id+"&dt="+dt.getTime(),500,250);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_patTypMan","查看巡查项类型管理","${ctx}/jsp/xcxlxgl/patTypManView.action?patTypMan.id="+row_Id+"&dt="+dt.getTime(),500,250);
        	
        }
        function xcxgl(row_Id){
            var id="newWindow";
    		var text = "巡查项管理";
    		var url ="/jsp/xcxgl/patManList.action?patMan.patrolType="+row_Id;
             window.parent.addTab(id,text,url);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_patTypMan();
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
		                	url : "patTypManDel.action",
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
		                        	search_patTypMan();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_patTypMan(){
        	var queryParams = {
				"patTypMan.patrolTypeName": $("#patrolTypeName").val()
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
				title:'巡查项类型管理列表',
				url:'patTypManQuery.action',
				queryParams:{
					"patTypMan.patrolTypeName": $("#patrolTypeName").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'patrolTypeName',title:'巡查项类型名称',width:100},
						  {field:'op',title:'操作',width:100,formatter:function(value,rec){
								var temp =  "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;";
								if("${roleName}" == '11'){
									temp += "<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
								}
								temp += "<a class='btn_04_mini' onclick=xcxgl('"+rec.id+"')>巡查项管理<b></b></a>";
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
					
				<th width="15%">巡查项类型名称</th>
				<td width="35%"><input name="patTypMan.patrolTypeName" style="width: 50%" id="patrolTypeName" value="${patTypMan.patrolTypeName}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_patTypMan()" >查询<b></b></a>&nbsp;
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

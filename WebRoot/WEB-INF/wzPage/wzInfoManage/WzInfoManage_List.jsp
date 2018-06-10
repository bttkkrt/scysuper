<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>网站信息管理管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        var t='';
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_wzInfoManage","添加"+t+"信息","${ctx}/jsp/wzInfoManage/wzInfoManageInitEdit.action?flag=add&wzInfoManage.infoType="+"${wzInfoManage.infoType}"+"&dt="+dt.getTime(),900,550);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_wzInfoManage","修改"+t+"信息","${ctx}/jsp/wzInfoManage/wzInfoManageInitEdit.action?flag=mod&wzInfoManage.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_wzInfoManage","查看"+t+"信息","${ctx}/jsp/wzInfoManage/wzInfoManageView.action?wzInfoManage.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_wzInfoManage();
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
		                	url : "wzInfoManageDel.action",
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
		                        	search_wzInfoManage();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_wzInfoManage(){
        	var queryParams = {
				"wzInfoManage.infotitle": $("#infotitle").val(),
				"wzInfoManage.infoType": '${wzInfoManage.infoType}',
				"wzInfoManage.createUserID":$("#createUserID").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
        	//1:安委会工作,2:政务互动,3:专题报道,4:媒体关注,5:职业卫生,6:组织机构,7:信息公开,8:行证许可公示,9:办事流程
        	if('${wzInfoManage.infoType}'=='1'){
        		t='安委会工作';
        	}else if('${wzInfoManage.infoType}'=='2'){
        		t='政务互动';
        	}else if('${wzInfoManage.infoType}'=='3'){
        		t='专题报道';
        	}else if('${wzInfoManage.infoType}'=='4'){
        		t='媒体关注';
        	}else if('${wzInfoManage.infoType}'=='5'){
        		t='职业卫生';
        	}else if('${wzInfoManage.infoType}'=='6'){
        		t='组织机构';
        	}else if('${wzInfoManage.infoType}'=='7'){
        		t='信息公开';
        	}else if('${wzInfoManage.infoType}'=='8'){
        		t='行证许可公示';
        	}else if('${wzInfoManage.infoType}'=='9'){
        		t='办事流程';
        	}else {
        	
        	}
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:t+"列表",
				url:'wzInfoManageQuery.action',
				queryParams:{
					"wzInfoManage.infotitle": $("#infotitle").val(),
					"wzInfoManage.infoType": '${wzInfoManage.infoType}',
					"wzInfoManage.createUserID":$("#createUserID").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'infotitle',title:'标题',width:100},
{field:'createUserID',title:'创建人',width:100,formatter:function(value,rec){return rec.user.displayName;}},
{field:'createTime',title:'创建时间',width:100,formatter:function(value,rec){
						return value.substring(0,10);						
					}},
{field:'clickTime',title:'点击量',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>&nbsp;<a class='btn_04_mini' onclick=move('"+rec.id+"')>移动<b></b></a>";}}
				        ]],
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
				}]
			}));
		});

		function move(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_wzInfoManage","移动"+t+"信息","${ctx}/jsp/wzInfoManage/wzInfoManageMove.action?flag=move&type=0&ids="+row_Id+"&dt="+dt.getTime(),500,150);
        	
        }
        
    </script>
</head>
<body>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
			<div class="cell boxBmargin12">
			<table width="100%">
				<tr>
					
				<th width="15%">标题</th>
				<td width="35%"><input name="wzInfoManage.infotitle" id="infotitle" value="${wzInfoManage.infotitle}" type="text" style="width:50%"></td>
				<th width="15%">创建人</th>
				<td width="35%"><input name="wzInfoManage.createUserID" id="createUserID" value="${wzInfoManage.createUserID}" type="text" style="width:50%"></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_wzInfoManage()" >查询<b></b></a>&nbsp;
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

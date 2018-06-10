<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>职业健康体检管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_occHeaExa","添加职业健康体检","${ctx}/jsp/zyjktj/occHeaExaInitEdit.action?flag=add&dt="+dt.getTime(),1000,300);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_occHeaExa","修改职业健康体检","${ctx}/jsp/zyjktj/occHeaExaInitEdit.action?flag=mod&occHeaExa.id="+row_Id+"&dt="+dt.getTime(),1000,300);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_occHeaExa","查看职业健康体检","${ctx}/jsp/zyjktj/occHeaExaView.action?occHeaExa.id="+row_Id+"&dt="+dt.getTime(),1000,300);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_occHeaExa();
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
		                	url : "occHeaExaDel.action",
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
		                        	search_occHeaExa();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_occHeaExa(){
        	var queryParams = {
				"occHeaExa.areaId": $("#areaId").val(),
"occHeaExa.companyName": $("#companyName").val(),
"occHeaExa.medicalInstitutionName": $("#medicalInstitutionName").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
	        var toolbar = [];
	        var frozen=[];
        	if('${roleName}'=='0'){//判断登录角色
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
									},{
				    					text:'导入职业健康体检',
										iconCls:'icon-add',
										handler:importUser
				
									}];
									frozen=[[
									    {field:'id',checkbox:true}
									]];
							                        }
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'职业健康体检列表',
				url:'occHeaExaQuery.action',
				queryParams:{
					"occHeaExa.areaId": $("#areaId").val(),
"occHeaExa.companyName": $("#companyName").val(),
"occHeaExa.medicalInstitutionName": $("#medicalInstitutionName").val()
				},
				frozenColumns:frozen,
				columns:[[
						{field:'areaId',title:'所在区域',width:100},
				          {field:'companyName',title:'企业名称',width:100},
{field:'medicalInstitutionName',title:'体检机构',width:100},
{field:'createTime',title:'录入时间',width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	if('${roleName}'=='0'){
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
	}else{
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	}

}}					        ]],
				toolbar:toolbar
			}));
			if('${roleName}'=='0'){//判断登录角色
        	$('#pagination').datagrid('hideColumn', 'areaId');
        	$('#pagination').datagrid('hideColumn', 'companyName');
        	}
		});
	function importUser(){ 
        	createSimpleWindow("importUser","批量导入职业健康体检","${ctx}/jsp/zyjktj/initImportOccHeaExa.action", 350, 200);
        }
        
    </script>
</head>
<body>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
			<div class="cell boxBmargin12">
			<table width="100%">
				<s:if test='roleName!="0"'>
				<tr>
					
				<th width="15%">所在区域</th>
				<td width="35%">
					<cus:SelectOneTag property="occHeaExa.areaId" defaultText='请选择' codeName="企业属地" value="${occHeaExa.areaId}" style="width:50%"/>
				</td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="occHeaExa.companyName" id="companyName" value="${occHeaExa.companyName}" type="text" maxlength="127" style="width:50%"></td>
			</tr></s:if>
			<tr>
					<th width="15%">体检机构</th>
					<td width="35%"><input id="medicalInstitutionName" name="occHeaExa.medicalInstitutionName" value="${occHeaExa.medicalInstitutionName}" type="text" maxlength="127" style="width:50%"></td>
				</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_occHeaExa()" >查询<b></b></a>&nbsp;
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

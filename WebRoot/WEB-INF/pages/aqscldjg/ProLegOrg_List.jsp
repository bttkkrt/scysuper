<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>安全生产领导机构管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_proLegOrg","添加安全生产领导机构","${ctx}/jsp/aqscldjg/proLegOrgInitEdit.action?flag=add&dt="+dt.getTime(),800,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_proLegOrg","修改安全生产领导机构","${ctx}/jsp/aqscldjg/proLegOrgInitEdit.action?flag=mod&proLegOrg.id="+row_Id+"&dt="+dt.getTime(),800,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_proLegOrg","查看安全生产领导机构","${ctx}/jsp/aqscldjg/proLegOrgView.action?proLegOrg.id="+row_Id+"&dt="+dt.getTime(),800,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_proLegOrg();
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
		                	url : "proLegOrgDel.action",
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
		                        	search_proLegOrg();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_proLegOrg(){
        	var queryParams = {
				"proLegOrg.areaId": $("#areaId").val(),
"proLegOrg.companyName": $("#companyName").val(),
"proLegOrg.orgenizationCharge": $("#orgenizationCharge").val(),
"proLegOrg.orgenizationName": $("#orgenizationName").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        var toolbar = [];
        var frozen=[];
        	if('${roleName}'==0){//判断登录角色
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
			}
        
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'安全生产领导机构列表',
				url:'proLegOrgQuery.action',
				queryParams:{
					"proLegOrg.areaId": $("#areaId").val(),
"proLegOrg.companyName": $("#companyName").val(),
"proLegOrg.orgenizationCharge": $("#orgenizationCharge").val(),

"proLegOrg.orgenizationName": $("#orgenizationName").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'areaId',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'orgenizationName',title:'机构名称',width:100},
{field:'orgenizationMenberCount',title:'成员数量',width:100},
{field:'orgenizationCharge',title:'主要负责人',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	if('${roleName}'=='0'){
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
	}else{
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	}

}}
				        ]],
				toolbar:toolbar
			})); 
			if('${roleName}'=='0'){//判断登录角色
        	$('#pagination').datagrid('hideColumn', 'areaId');
        	$('#pagination').datagrid('hideColumn', 'companyName');
        	}
		});

    </script>
</head>
<body>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
			<div class="cell boxBmargin12">  
			<table width="100%">
			<c:if test='${roleName!=0}'>
				<tr>
					
				<th width="15%">所在区域</th>
				<td width="35%"><cus:SelectOneTag property="proLegOrg.areaId" defaultText='请选择' codeName="企业属地" value="${proLegOrg.areaId}"  maxlength="127" style="width:50%"/></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="proLegOrg.companyName" id="companyName" value="${proLegOrg.companyName}" type="text" maxlength="127" style="width:50%"></td>
			</tr>
			</c:if>
			<tr>
				<th width="15%">机构名称</th>
				<td width="35%"><input name="proLegOrg.orgenizationName" id="orgenizationName" value="${proLegOrg.orgenizationName}" type="text" maxlength="127" style="width:50%"></td>
				<th width="15%">主要负责人 </th>
				<td width="35%"><input name="proLegOrg.orgenizationCharge" id="orgenizationCharge" value="${proLegOrg.orgenizationCharge}" type="text" maxlength="127" style="width:50%"></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_proLegOrg()" >查询<b></b></a>&nbsp;
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

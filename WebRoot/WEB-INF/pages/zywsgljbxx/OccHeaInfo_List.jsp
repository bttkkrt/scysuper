<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>职业卫生管理基本信息管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_occHeaInfo","添加职业卫生管理基本信息","${ctx}/jsp/zywsgljbxx/occHeaInfoInitEdit.action?flag=add&dt="+dt.getTime(),1000,550);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_occHeaInfo","修改职业卫生管理基本信息","${ctx}/jsp/zywsgljbxx/occHeaInfoInitEdit.action?flag=mod&occHeaInfo.id="+row_Id+"&dt="+dt.getTime(),1000,550);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_occHeaInfo","查看职业卫生管理基本信息","${ctx}/jsp/zywsgljbxx/occHeaInfoView.action?occHeaInfo.id="+row_Id+"&dt="+dt.getTime(),1200,550);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_occHeaInfo();
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
		                	url : "occHeaInfoDel.action",
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
		                        	search_occHeaInfo();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_occHeaInfo(){
        	var queryParams = {
				"occHeaInfo.areaId": $("#areaId").val(),
"occHeaInfo.companyName": $("#companyName").val(),
"occHeaInfo.hazardIndustryCategory": $("#hazardIndustryCategory").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'职业卫生管理基本信息列表',
				url:'occHeaInfoQuery.action',
				queryParams:{
					"occHeaInfo.areaId": $("#areaId").val(),
"occHeaInfo.companyName": $("#companyName").val(),
"occHeaInfo.hazardIndustryCategory": $("#hazardIndustryCategory").val()
				},
//				frozenColumns:[[
//				    {field:'id',checkbox:true}
//				]],
				columns:[[
				          {field:'areaId',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'hazardIndustryCategory',title:'职业病危害行业类别',width:100},
{field:'femaleWorkersDiseasesNumber',title:'接触职业病危害因素女工人数',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";}}
				        ]],
//				toolbar:[{
//					id:'btnadd',
//					text:'添加',
//					iconCls:'icon-add',
//					handler:function(){
//						addNew();
//					}
//				},{
//					id:'btncut',
//					text:'删除',
//					iconCls:'icon-remove',
//					handler:function(){
//						del();
//					}
//				}]
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
					
				<th width="15%">所在区域</th>
				<td width="35%">
					<cus:SelectOneTag property="occHeaInfo.areaId" defaultText='请选择' codeName="企业属地" value="${occHeaInfo.areaId}" style="width:50%"/>
				</td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="occHeaInfo.companyName" id="companyName" value="${occHeaInfo.companyName}" type="text" maxlength="127" style="width:50%"></td>
			</tr>
			<tr>
				<th width="15%">职业病危害行业类别</th>
				<td width="35%"><cus:SelectOneTag property="occHeaInfo.hazardIndustryCategory" defaultText='请选择' codeName="职业病危害行业类别" value="${occHeaInfo.hazardIndustryCategory}" style="width:50%"/></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_occHeaInfo()" >查询<b></b></a>&nbsp;
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

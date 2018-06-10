<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>检测超标管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_detOverStaMan","添加检测超标管理","${ctx}/jsp/jccbgl/detOverStaManInitEdit.action?flag=add&dt="+dt.getTime(),700,350);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_detOverStaMan","修改检测超标管理","${ctx}/jsp/jccbgl/detOverStaManInitEdit.action?flag=mod&detOverStaMan.id="+row_Id+"&dt="+dt.getTime(),700,350);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_detOverStaMan","查看检测超标管理","${ctx}/jsp/jccbgl/detOverStaManView.action?detOverStaMan.id="+row_Id+"&dt="+dt.getTime(),700,350);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_detOverStaMan();
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
		                	url : "detOverStaManDel.action",
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
		                        	search_detOverStaMan();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_detOverStaMan(){
        	var queryParams = {
				"detOverStaMan.areaId": $("#areaId").val(),
"detOverStaMan.areaName": $("#areaName").val(),
"detOverStaMan.companyName": $("#companyName").val(),
"detOverStaMan.sampleEncoding": $("#sampleEncoding").val(),
"detOverStaMan.detectionCategory": $("#detectionCategory").val(),
"detOverStaMan.testItems": $("#testItems").val(),
"detOverStaMan.detectionMechanism": $("#detectionMechanism").val()
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
				}];
				frozen=[[
				    {field:'id',checkbox:true}
				]];
			}
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'检测超标管理列表',
				url:'detOverStaManQuery.action',
				queryParams:{
					"detOverStaMan.areaId": $("#areaId").val(),
"detOverStaMan.areaName": $("#areaName").val(),
"detOverStaMan.companyName": $("#companyName").val(),
"detOverStaMan.sampleEncoding": $("#sampleEncoding").val(),
"detOverStaMan.detectionCategory": $("#detectionCategory").val(),
"detOverStaMan.testItems": $("#testItems").val(),
"detOverStaMan.detectionMechanism": $("#detectionMechanism").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
				          {field:'companyName',title:'企业名称',width:100},
				          {field:'detectionMechanism',title:'检测机构',width:100},
{field:'detectionCategory',title:'检测类别',width:100},

{field:'op',title:'操作',width:100,formatter:function(value,rec){
	if('${roleName}'=='13'){
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
	}else{
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	}

}}				        ]],
				toolbar:toolbar
			}));
			if('${roleName}'=='0'){//判断登录角色
        	$('#pagination').datagrid('hideColumn', 'areaName');
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
			<s:if test='roleName!="0"'>
				<tr>
				<th width="15%">所在区域</th>
				<td width="35%"><cus:SelectOneTag property="detOverStaMan.areaId" defaultText='请选择' codeName="企业属地" value="${detOverStaMan.areaId}" style="width:50%"/></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="detOverStaMan.companyName" id="companyName" value="${detOverStaMan.companyName}" type="text" maxlength="127" style="width:50%"></td>
			</tr>
			</s:if>
			<tr>
			
				<th width="15%">检测类别</th>
				<td width="35%"><cus:SelectOneTag property="detOverStaMan.detectionCategory" defaultText='请选择' codeName="检测类别" value="${detOverStaMan.detectionCategory}" style="width:50%"/></td>
				<th width="15%">检测机构</th>
				<td width="35%"><input name="detOverStaMan.detectionMechanism" id="detectionMechanism" value="${detOverStaMan.detectionMechanism}" type="text" maxlength="127" style="width:50%"></td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_detOverStaMan()" >查询<b></b></a>&nbsp;
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

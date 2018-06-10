<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>应急预案管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        var type="${type}";
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_emePla","添加应急预案","${ctx}/jsp/yjya/emePlaInitEdit.action?flag=add&dt="+dt.getTime()+"&type="+type,700,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_emePla","修改应急预案","${ctx}/jsp/yjya/emePlaInitEdit.action?flag=mod&emePla.id="+row_Id+"&dt="+dt.getTime()+"&type="+type,700,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_emePla","查看应急预案","${ctx}/jsp/yjya/emePlaView.action?emePla.id="+row_Id+"&dt="+dt.getTime()+"&type="+type,700,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_emePla();
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
		                	url : "emePlaDel.action",
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
		                        	search_emePla();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_emePla(){
        	var queryParams = {
        	"emePla.type": $("#type").val(),
				"emePla.areaId": $("#areaId").val(),
"emePla.companyName": $("#companyName").val(),
"emePla.planName": $("#planName").val(),
"emePla.planType": $("#planType").val(),
"emePla.planLevel": $("#planLevel").val(),
"emePla.type": "0",
"emePla.planFilingNumber": $("#planFilingNumber").val()
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
				title:'应急预案列表',
				url:'emePlaQuery.action',
				queryParams:{
				
					"emePla.areaId": $("#areaId").val(),
"emePla.companyName": $("#companyName").val(),
"emePla.planName": $("#planName").val(),
"emePla.planType": $("#planType").val(),
"emePla.planLevel": $("#planLevel").val(),
"emePla.type": "0",
"emePla.planFilingNumber": $("#planFilingNumber").val()
				},
					frozenColumns:frozen,
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'planName',title:'预案名称',width:100},
{field:'planType',title:'预案类别',width:100},
{field:'planLevel',title:'预案级别',width:100},
{field:'planFilingNumber',title:'预案备案编号',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	if('${roleName}'=='0'){
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_02_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
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
				<td width="35%"><cus:SelectOneTag property="emePla.areaId" defaultText='请选择' style="width:50%" codeName="企业属地" value="${emePla.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="emePla.companyName" id="companyName" style="width:50%" value="${emePla.companyName}" type="text" maxlength="127"></td>
			</tr>
			</s:if>
			<tr>
				<th width="15%">预案名称</th>
				<td width="35%"><input name="emePla.planName" id="planName" style="width:50%" value="${emePla.planName}" type="text" maxlength="127"></td>
				<th width="15%">预案类别</th>
				<td width="35%"><cus:SelectOneTag property="emePla.planType" style="width:50%" defaultText='请选择' codeName="应急预案类别" value="${emePla.planType}" /></td>
			</tr>
			<tr>
				<th width="15%">预案级别</th>
				<td width="35%"><cus:SelectOneTag property="emePla.planLevel" style="width:50%" defaultText='请选择' codeName="应急预案级别" value="${emePla.planLevel}" /></td>
				<th width="15%">预案备案编号</th>
				<td width="35%"><input name="emePla.planFilingNumber" style="width:50%" id="planFilingNumber" value="${emePla.planFilingNumber}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_emePla()" >查询<b></b></a>&nbsp;
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

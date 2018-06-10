<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>安全生产费用提取情况管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_secProFeeExt","添加安全生产费用提取情况","${ctx}/jsp/aqscfytqqk/secProFeeExtInitEdit.action?flag=add&dt="+dt.getTime(),700,300);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_secProFeeExt","修改安全生产费用提取情况","${ctx}/jsp/aqscfytqqk/secProFeeExtInitEdit.action?flag=mod&secProFeeExt.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_secProFeeExt","查看安全生产费用提取情况","${ctx}/jsp/aqscfytqqk/secProFeeExtView.action?secProFeeExt.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function toto(){
        $('#pagination').datagrid('hideColumn', 'areaName'); 
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_secProFeeExt();
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
		                	url : "secProFeeExtDel.action",
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
		                        	search_secProFeeExt();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_secProFeeExt(){
        	var queryParams = {
				"secProFeeExt.areaId": $("#areaId").val(),
"secProFeeExt.companyName": $("#companyName").val(),
"secProFeeExt.feeExtractFee": $("#feeExtractFee").val(),
 "queryFeeExtractTimeStart" :$("#queryFeeExtractTimeStart").val(),
 "queryFeeExtractTimeEnd" :$("#queryFeeExtractTimeEnd").val()
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
				title:'安全生产费用提取情况列表',
				url:'secProFeeExtQuery.action',
				queryParams:{
					"secProFeeExt.areaId": $("#areaId").val(),
"secProFeeExt.companyName": $("#companyName").val(),
"secProFeeExt.feeExtractFee": $("#feeExtractFee").val(),
 "queryFeeExtractTimeStart" :$("#queryFeeExtractTimeStart").val(),
 "queryFeeExtractTimeEnd" :$("#queryFeeExtractTimeEnd").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'feeExtractFee',title:'提取费用',width:100},
{field:'feeExtractTime',title:'提取时间',width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'feeExtractRemark',title:'用途',width:100,formatter:function(value,rec){
if(value.length>20)
{
	return value.substring(0,20)+"...";
}
else
{
	return value;
}
}},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	if('${roleName}'=='0'){
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
				<td width="35%"><cus:SelectOneTag property="secProFeeExt.areaId" style="width:50%" defaultText='请选择' codeName="企业属地" value="${secProFeeExt.areaId}"  maxlength="127"/></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="secProFeeExt.companyName" style="width:50%" id="companyName" value="${secProFeeExt.companyName}" type="text" maxlength="127"></td>
			</tr>
			</s:if>
			<tr>
				<th width="15%">提取费用</th>
				<td width="35%"><input name="secProFeeExt.feeExtractFee" style="width:50%" id="feeExtractFee" value="${secProFeeExt.feeExtractFee}" type="text" maxlength="127"></td>
				<th width="15%">提取时间</th>
				<td width="35%"><input name="queryFeeExtractTimeStart" id="queryFeeExtractTimeStart" value="<fmt:formatDate type='both' value='${queryFeeExtractTimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryFeeExtractTimeEnd\')}'})" >
					-<input name="queryFeeExtractTimeEnd" id="queryFeeExtractTimeEnd" value="<fmt:formatDate type='both' value='${queryFeeExtractTimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryFeeExtractTimeStart\')}'})" ></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_secProFeeExt()" >查询<b></b></a>&nbsp;
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

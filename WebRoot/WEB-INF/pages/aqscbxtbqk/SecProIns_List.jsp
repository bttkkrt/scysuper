<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>安全生产保险投保情况管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_secProIns","添加安全生产保险投保情况","${ctx}/jsp/aqscbxtbqk/secProInsInitEdit.action?flag=add&dt="+dt.getTime(),1100,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_secProIns","修改安全生产保险投保情况","${ctx}/jsp/aqscbxtbqk/secProInsInitEdit.action?flag=mod&secProIns.id="+row_Id+"&dt="+dt.getTime(),1100,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_secProIns","查看安全生产保险投保情况","${ctx}/jsp/aqscbxtbqk/secProInsView.action?secProIns.id="+row_Id+"&dt="+dt.getTime(),1100,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_secProIns();
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
		                	url : "secProInsDel.action",
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
		                        	search_secProIns();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_secProIns(){
        	var queryParams = {
        	"secProIns.areaId": $("#areaId").val(),
        	"secProIns.insuranceCompnay": $("#insuranceCompnay").val(),
        	 "queryInsuranceTimeStart" :$("#queryInsuranceTimeStart").val(),
 "queryInsuranceTimeEnd" :$("#queryInsuranceTimeEnd").val(),
  "secProIns.insuranceEnterprisePersons" :$("#insuranceEnterprisePersons").val(),
				"secProIns.companyName": $("#companyName").val()
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
				title:'安全生产保险投保情况列表',
				url:'secProInsQuery.action',
				queryParams:{
					"secProIns.companyName": $("#companyName").val()
				},
				frozenColumns:frozen,
				columns:[[
				{field:'areaName',title:'所在区域',width:100},
				{field:'companyName',title:'企业名称',width:100},
				
				{field:'insuranceCompnay',title:'承保保险公司',width:100},
				{field:'insuranceTime',title:'投保时间',width:100,formatter:function(value,rec){return value.substring(0,10);}},
				          {field:'insuranceWorkerCount',title:'一线员工数',width:100},

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
				<td width="35%"><cus:SelectOneTag style="width:50%" property="secProIns.areaId" defaultText='请选择' codeName="企业属地" value="${secProIns.areaId}"  maxlength="127"/></td>	
				<th width="15%">企业名称</th>
				<td width="35%"><input name="secProIns.companyName" style="width:50%" id="companyName" value="${secProIns.companyName}" type="text" maxlength="127"></td>
				</tr>
			</s:if>
			<tr>
				<th width="15%">承保保险公司</th>
				<td width="35%"><input name="secProIns.insuranceCompnay" style="width:50%" id="insuranceCompnay" value="${secProIns.insuranceCompnay}" type="text" maxlength="127"></td>
				<th width="15%">投保时间</th>
				<td width="35%"><input name="queryInsuranceTimeStart" id="queryInsuranceTimeStart" value="<fmt:formatDate type='both' value='${queryInsuranceTimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryInsuranceTimeEnd\')}'})" >
					-<input name="queryInsuranceTimeEnd" id="queryInsuranceTimeEnd" value="<fmt:formatDate type='both' value='${queryInsuranceTimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryInsuranceTimeStart\')}'})" ></td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_secProIns()" >查询<b></b></a>&nbsp;
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

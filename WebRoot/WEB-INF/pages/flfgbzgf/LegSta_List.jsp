<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>法律法规标准规范管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_legSta","添加法律法规标准规范","${ctx}/jsp/flfgbzgf/legStaInitEdit.action?flag=add&dt="+dt.getTime(),700,400);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_legSta","修改法律法规标准规范","${ctx}/jsp/flfgbzgf/legStaInitEdit.action?flag=mod&legSta.id="+row_Id+"&dt="+dt.getTime(),700,400);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_legSta","查看法律法规标准规范","${ctx}/jsp/flfgbzgf/legStaView.action?legSta.id="+row_Id+"&dt="+dt.getTime(),700,400);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_legSta();
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
		                	url : "legStaDel.action",
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
		                        	search_legSta();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_legSta(){
        	var queryParams = {
				"legSta.areaId": $("#areaId").val(),
"legSta.areaName": $("#areaName").val(),
"legSta.companyName": $("#companyName").val(),
"legSta.legalName": $("#legalName").val(),
 "queryLegalStandardTimeStart" :$("#queryLegalStandardTimeStart").val(),
 "queryLegalStandardTimeEnd" :$("#queryLegalStandardTimeEnd").val()
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
				title:'法律法规标准规范列表',
				url:'legStaQuery.action',
				queryParams:{
					"legSta.areaId": $("#areaId").val(),
"legSta.areaName": $("#areaName").val(),
"legSta.companyName": $("#companyName").val(),
 "queryLegalStandardTimeStart" :$("#queryLegalStandardTimeStart").val(),
 "queryLegalStandardTimeEnd" :$("#queryLegalStandardTimeEnd").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'legalName',title:'清单名称',width:100},
{field:'legalStandardTime',title:'上传时间',width:100},
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
				<td width="35%"><cus:SelectOneTag property="legSta.areaId" style="width:50%" defaultText='请选择' codeName="企业属地" value="${legSta.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="legSta.companyName" style="width:50%" id="companyName" value="${legSta.companyName}" type="text" maxlength="127"></td>
			</tr>
			</s:if>
			<tr>
				<th width="15%">清单名称</th>
					<td width="35%" ><input name="legSta.legalName" style="width:50%" id="legalName" value="${legSta.legalName}" type="text"  maxlength="127"></td>
				<th width="15%">上传时间</th>
				<td width="35%"><input name="queryLegalStandardTimeStart" id="queryLegalStandardTimeStart" value="<fmt:formatDate type='both' value='${queryLegalStandardTimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryLegalStandardTimeEnd\')}'})" >
					-<input name="queryLegalStandardTimeEnd" id="queryLegalStandardTimeEnd" value="<fmt:formatDate type='both' value='${queryLegalStandardTimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryLegalStandardTimeStart\')}'})" ></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_legSta()" >查询<b></b></a>&nbsp;
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

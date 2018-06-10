<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业基本信息管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function view(id,name,szzid,zzjgm,gszch){
        	window.returnValue=id + ";" + name + ";" + szzid+";"+zzjgm+";"+gszch;
			window.close();
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_entBaseInfo();
        }
        function search_entBaseInfo(){
        	var queryParams = {
					"supPla.checkUserId": '${checkUserId}',
					"supPla.planId": '${planId}',
					"supPla.id": '${supPla.id}' ,
					"companyName": $("#companyName").val() ,
					"companyType": $("#companyType").val() 
					
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'可选企业信息列表（红色表示有未完成的任务）',
				pagination:false,
				url:'selectCompanyForImportPlan.action',
				queryParams:{
					"supPla.checkUserId": '${checkUserId}',
					"supPla.planId": '${planId}',
					"supPla.id": '${supPla.id}' 
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				            {field:'companyName',title:'企业名称',width:100,formatter:function(value,rec){ if(rec.ifFinish=='1'){return "<span style='color:red'>"+value+"</span>";}else{return value;}}},
				            //{field:'area',title:'所属区域',width:100},
							//{field:'address',title:'地  址',width:100},
							//{field:'charger',title:'主要负责人',width:100},
							//{field:'chargerPhone',title:'联系电话',width:100},
							{field:'contact',title:'联系人',width:100,formatter:function(value,rec){ if(rec.ifFinish=='1'){return "<span style='color:red'>"+value+"</span>";}else{return value;}}},
							{field:'contactPhone',title:'联系电话',width:100,formatter:function(value,rec){ if(rec.ifFinish=='1'){return "<span style='color:red'>"+value+"</span>";}else{return value;}}},
							//{field:'email',title:'邮 箱',width:100,formatter:function(value,rec){ if(rec.ifFinish=='1'){return "<span style='color:red'>"+value+"</span>";}else{return value;}}},
							{field:'typeDetail',title:'监管项目',width:100,formatter:function(value,rec){ if(rec.ifFinish=='1'){return "<span style='color:red'>"+value+"</span>";}else{return value;}}},
							{field:'remark',title:'备注',width:100,formatter:function(value,rec){ if(rec.ifFinish=='1'){return "<span style='color:red'>"+value+"</span>";}else{return value;}}}
				        ]],
				onLoadSuccess:function(){
                        var comIds='${companyIds}';
                        var rows = $("#pagination").datagrid("getRows");
						for(var i=0;i<rows.length;i++){
							if(comIds.indexOf(rows[i].id) != -1){
								$('#pagination').datagrid('selectRow', i);
							
							}
						}
                    }
			}));
		});

        
        
         function returnCheckedUsers(flowType) {
         	var rows = $('#pagination').datagrid('getSelections');
        	var companyids="";
			var companynames="";
			for(var i=0;i<rows.length;i++){
				companyids += rows[i].id+",";
				companynames += rows[i].companyName+",";
			}
			if(rows.length<1){
			    $.messager.alert('提示','至少选择一项！');
			}else{
				if(companyids&&""!=companyids){
					window.opener.document.getElementById("companyIds").value = companyids.substring(0,companyids.length-1);
					window.opener.document.getElementById("companyNames").value = companynames.substring(0,companynames.length-1);
					window.close();			
				}else{
					alert("请选择企业");
				}
			}
         
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
					<th width="15%">企业名称</th>
					<td width="35%"><input name="companyName" id="companyName" value="${companyName}" type="text" maxlength="127"></td>
					<th width="15%">监管项目</th>
					<td width="35%">
						<select name="companyType" id="companyType">
							<option value="">请选择</option>
							<option value="1">危化企业</option>
							<option value="2">职业卫生企业C</option>
							<option value="3">职业卫生企业B</option>
							<option value="4">职业卫生企业A</option>
							<option value="5">涉爆粉尘企业</option>
							<option value="6">涉氨制冷企业</option>
							<option value="7">小微标准化企业</option>
							<option value="8">推总监制度企业</option>
							<option value="9">涉有限空间企业</option>
							<option value="10">冶金企业</option>
							<option value="11">工业用燃气企业</option>
							<option value="12">工伤事故多发企业</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_entBaseInfo()" >查询<b></b></a>&nbsp;
						<a href="###" class="btn_01" onclick="clear_form(document.myform);" >清空<b></b></a>&nbsp;				
					</td>
				</tr>
			</table>
		</div>
		
		<div id="pagination" >
		</div>
		<div>
			<table width="100%">
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="returnCheckedUsers()" >确定<b></b></a>&nbsp;
					</td>
				</tr>
			</table>
		</div>
		</div>
		</div>
	</div>
</body>
</html>

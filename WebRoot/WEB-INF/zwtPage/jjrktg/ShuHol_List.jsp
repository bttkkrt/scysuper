<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>节假日开停工管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_shuHol","添加节假日开停工","${ctx}/jsp/jjrktg/shuHolInitEdit.action?flag=add&dt="+dt.getTime(),700,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_shuHol","修改节假日开停工","${ctx}/jsp/jjrktg/shuHolInitEdit.action?flag=mod&shuHol.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_shuHol","查看节假日开停工","${ctx}/jsp/jjrktg/shuHolView.action?shuHol.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
        
        function check(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_shuHol","审核节假日开停工","${ctx}/jsp/jjrktg/shuHolCheck.action?flag=check&shuHol.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_shuHol();
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
		                	url : "shuHolDel.action",
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
		                        	search_shuHol();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_shuHol(){
        	var queryParams = {
				"shuHol.areaId": $("#areaId").val(),
"shuHol.companyName": $("#companyName").val(),
"shuHol.auditState": $("#auditState").val(),
 "queryHolidayTimeStart" :$("#queryHolidayTimeStart").val(),
 "queryHolidayTimeEnd" :$("#queryHolidayTimeEnd").val()
//"shuHol.ifStart": $("#ifStart").val()
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
				title:'节假日开停工列表',
				url:'shuHolQuery.action',
				queryParams:{
					"shuHol.areaId": $("#areaId").val(),
"shuHol.auditState": $("#auditState").val(),
"shuHol.companyName": $("#companyName").val(),
 "queryHolidayTimeStart" :$("#queryHolidayTimeStart").val(),
 "queryHolidayTimeEnd" :$("#queryHolidayTimeEnd").val()
//"shuHol.ifStart": $("#ifStart").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'areaId',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'holidayTimeStart',title:'放假开始时间',width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'holidayTimeEnd',title:'放假结束时间',width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'auditState',title:'审核状态',width:100},
//{field:'ifStart',title:'是否开工',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	var button1="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=check('"+rec.id+"')>审核<b></b></a>";
	var button2="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	var button3="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
		if(rec.auditState=='待审核'&&"true"=="${canCheck}"){
			return button1;
		}else if('${roleName}'=='0'&&rec.auditState=='审核未通过'){
			return button3;
		}else{
			return button2;
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
				<s:if test='roleName!="0"'>
				<tr>
					
				<th width="15%">所在区域</th>
				<td width="35%"><cus:SelectOneTag style="width:50%" property="shuHol.areaId" defaultText='请选择' codeName="企业属地" value="${shuHol.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="shuHol.companyName" style="width:50%" id="companyName" value="${shuHol.companyName}" type="text" maxlength="127"></td>
			</tr></s:if>
			<tr>
				<th width="15%">放假时间</th>
				<td width="35%"><input name="queryHolidayTimeStart" id="queryHolidayTimeStart" value="<fmt:formatDate type='both' pattern='yyyy-MM-dd' value='${queryHolidayTimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryHolidayTimeEnd\')}'})" >
					-<input name="queryHolidayTimeEnd" id="queryHolidayTimeEnd" value="<fmt:formatDate type='both' pattern='yyyy-MM-dd' value='${queryHolidayTimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryHolidayTimeStart\')}'})" ></td>
			 	<th width="15%">审核状态</th>
			 	<td width="35%">
			 		<s:select id="auditState" name="shuHol.auditState" cssStyle="width:50%;"  list="#{'':'请选择','待审核':'待审核','审核通过':'审核通过','审核未通过':'审核未通过'}" theme="simple"/>
			 	</td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_shuHol()" >查询<b></b></a>&nbsp;
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

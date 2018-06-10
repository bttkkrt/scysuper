<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>安全生产信用管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_enterpriseGrade","添加安全生产信用评分","${ctx}/jsp/enterpriseGrade/enterpriseGradeTime.action?dt="+dt.getTime(),1000,550);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_enterpriseGrade","修改安全生产信用评分","${ctx}/jsp/enterpriseGrade/enterpriseGradeInitEdit.action?flag=mod&enterpriseGrade.id="+row_Id+"&dt="+dt.getTime(),1000,550);
        	
        }
        function deal(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_enterpriseGrade","审核安全生产信用评分","${ctx}/jsp/enterpriseGrade/enterpriseGradeInitEdit.action?flag=deal&enterpriseGrade.id="+row_Id+"&dt="+dt.getTime(),1000,550);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_enterpriseGrade","查看安全生产信用评分","${ctx}/jsp/enterpriseGrade/enterpriseGradeView.action?enterpriseGrade.id="+row_Id+"&dt="+dt.getTime(),1000,550);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_enterpriseGrade();
        }
        function del(){
        	var rows = document.getElementsByName("xxx");
        	var ids = "";
        	var len = rows.length;
			for(var i=0;i<len;i++){
				if(rows[i].checked){
					ids += rows[i].value+"|";
				}
			}
			if(ids == ""){
			    $.messager.alert('提示','至少选择一项删除！');
			}else{
			    $.messager.confirm("删除","确定要删除吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "enterpriseGradeDel.action",
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
		                        	search_enterpriseGrade();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_enterpriseGrade(){
        	var queryParams = {
					"queryStartTimeStart": $("#queryStartTimeStart").val(),
					"queryStartTimeEnd": $("#queryStartTimeEnd").val(),
					"queryEndTimeStart": $("#queryEndTimeStart").val(),
					"queryEndTimeEnd": $("#queryEndTimeEnd").val(),
					"enterpriseGrade.areaId": $("#areaId").val(),
					"enterpriseGrade.companyName": $("#companyName").val()
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
				    {field:'id',formatter:function(value,rec){
  					  if( rec.state == '0'){
  							return '<input type="checkbox" name="xxx" value='+rec.id+'>';
  							}else{
  								return '';
  							}
    					}}
				]];
			}
        
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'安全生产信用管理列表',
				url:'enterpriseGradeQuery.action',
				queryParams:{
					"queryStartTimeStart": $("#queryStartTimeStart").val(),
					"queryStartTimeEnd": $("#queryStartTimeEnd").val(),
					"queryEndTimeStart": $("#queryEndTimeStart").val(),
					"queryEndTimeEnd": $("#queryEndTimeEnd").val(),
					"enterpriseGrade.areaId": $("#areaId").val(),
					"enterpriseGrade.companyName": $("#companyName").val()
				},
				frozenColumns:frozen,
				columns:[[
				 {field:'areaName',title:'所在区域',width:100},
				          {field:'companyName',title:'企业名称',width:100},
				          {field:'startTime',title:'评分时间',width:100,formatter:function(value,rec){
				          		return value.substring(0,10) + "~" + rec.endTime.substring(0,10);
							}},	
				          {field:'state',title:'状态',width:100,formatter:function(value,rec){
				          		if(rec.state=='0'){
				          			return "待提交";
				          		}
				          		else if(rec.state=='1'){
				          			return "待审核";
				          		}else{
				          			return "已审核";
				          		}
							}},	
							{field:'zpzf',title:'自评分',width:100},			   
							{field:'ajzf',title:'监管部门评分',width:100},			          
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	if('${roleName}'=='1'){//网格管理员
		if(rec.state=='1'){
			return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_04_mini' onclick=deal('"+rec.id+"')>审核<b></b></a>";
		}
		else if(rec.state=='2'){
			return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=deal('"+rec.id+"')>修改<b></b></a>";
		}
		else{
			return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
		}
	}else if('${roleName}'=='0'){//企业人员
		if(rec.state=='0'){
			return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
		}
		else{
			return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
		}
	}
	else{
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	}
}}
				        ]],
				toolbar:toolbar
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
			<s:if test='roleName!="0"'>
				<tr>
					
				<th width="15%">所在区域</th>
				<td width="35%"><cus:SelectOneTag style="width:50%;" property="enterpriseGrade.areaId" defaultText='请选择' codeName="企业属地" value="${enterpriseGrade.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="enterpriseGrade.companyName" style="width: 50%"  id="companyName" value="${enterpriseGrade.companyName}" type="text" maxlength="127"></td>
			</tr></s:if>
				<tr>
					
				<th width="15%">开始时间</th>
				<td width="35%"><input name="queryStartTimeStart" id="queryStartTimeStart" value="<fmt:formatDate type='date' value='${queryStartTimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryStartTimeEnd\')}'})" >
					-<input name="queryStartTimeEnd" id="queryStartTimeEnd" value="<fmt:formatDate type='date' value='${queryStartTimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryStartTimeStart\')}'})" ></td>
				<th width="15%">结束时间</th>
				<td width="35%"><input name="queryEndTimeStart" id="queryEndTimeStart" value="<fmt:formatDate type='date' value='${queryEndTimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryEndTimeEnd\')}'})" >
					-<input name="queryEndTimeEnd" id="queryEndTimeEnd" value="<fmt:formatDate type='date' value='${queryEndTimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryEndTimeStart\')}'})" ></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_enterpriseGrade()" >查询<b></b></a>&nbsp;
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

<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>巡查计划管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_danPlaMan","添加巡查计划","${ctx}/jsp/wxyxcjhgl/danPlaManInitEdit.action?flag=add&dt="+dt.getTime(),700,400);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_danPlaMan","修改巡查计划","${ctx}/jsp/wxyxcjhgl/danPlaManInitEdit.action?flag=mod&danPlaMan.id="+row_Id+"&dt="+dt.getTime(),700,400);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_danPlaMan","查看巡查计划","${ctx}/jsp/wxyxcjhgl/danPlaManView.action?danPlaMan.id="+row_Id+"&dt="+dt.getTime(),700,400);
        	
        }
        //审核
        function audit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_danPlaMan","巡查计划审核","${ctx}/jsp/wxyxcjhgl/danPlaManInitAudit.action?danPlaMan.id="+row_Id+"&dt="+dt.getTime(),700,500);
        
        }
         //查看计划关联任务
        function taskList(row_Id){
        	var url =  "jsp/wxyxcrwgl/danTasManList.action?ids="+row_Id
	        window.parent.addTab("win_taskList","查看任务列表",url);
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_danPlaMan();
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
		                	url : "danPlaManDel.action",
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
		                        	search_danPlaMan();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_danPlaMan(){
        	var queryParams = {
				"danPlaMan.areaId": $("#areaId").val(),
"danPlaMan.areaName": $("#areaName").val(),
"danPlaMan.companyName": $("#companyName").val(),
"danPlaMan.planName": $("#planName").val(),
"danPlaMan.dangerName": $("#dangerName").val(),
"danPlaMan.checkFrequency": $("#checkFrequency").val(),
"danPlaMan.checkPeopleName": $("#checkPeopleName").val(),
"danPlaMan.checkTypeId": $("#checkTypeId").val(),
"danPlaMan.auditResult": $("#auditResult").val()
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
  					  if( rec.auditResult == '审核未通过'){
  							return '<input type="checkbox" name="xxx" value='+rec.id+'>';
  							}else{
  								return '';
  							}
    					}}
				]];
				
				};
        	
        
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'巡查计划管理列表',
				url:'danPlaManQuery.action',
				queryParams:{
					"danPlaMan.areaId": $("#areaId").val(),
"danPlaMan.areaName": $("#areaName").val(),
"danPlaMan.companyName": $("#companyName").val(),
"danPlaMan.planName": $("#planName").val(),
"danPlaMan.dangerName": $("#dangerName").val(),
"danPlaMan.checkFrequency": $("#checkFrequency").val(),
"danPlaMan.checkPeopleName": $("#checkPeopleName").val(),
"danPlaMan.checkTypeId": $("#checkTypeId").val(),
"danPlaMan.auditResult": $("#auditResult").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'companyName',title:'企业名称',width:100},
				   
{field:'planName',title:'计划名称',width:100},
{field:'checkFrequency',title:'巡查频率',width:80},
{field:'checkPeopleName',title:'巡查人员姓名',width:80},
{field:'isAudit',title:'是否上报审核',width:100,formatter:function(value,rec){   

if(value=='1'){
		value='否';
	}else if(value=='0'){
	   value='是';
	}
	return value;
}}, 
{field:'auditResult',title:'状态',width:50,formatter:function(value,rec){
	if(value=='审核通过'){
		value='执行中';
	}
	return value;
}},
{field:'op',title:'操作',width:150,formatter:function(value,rec){
			 var temp =  "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;";
			
			if('${roleName}'=='0'){
				temp += "<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
			}
			if(rec.auditResult == '审核通过'){
				 temp += "<a class='btn_04_mini' onclick=taskList('"+rec.id+"')>查看任务<b></b></a>&nbsp;";
			}
			//监察大队 执行审核操作
			if(rec.auditResult == '待审核'&&'${roleName}' == '1'){
				temp += "<a class='btn_05_mini' onclick=audit('"+rec.id+"')>审核<b></b></a>";
			}
			return temp;
		}}
				        ]],
				        
				toolbar:toolbar
				
				
				
				})); 
			if('${roleName}'=='0'){//判断登录角色
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
			<s:if test='roleName != 0'>
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%"><cus:SelectOneTag property="danPlaMan.areaId" style="width:50%" defaultText='请选择' codeName="企业属地" value="${danPlaMan.areaName}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%"><input name="danPlaMan.companyName" style="width:50%" id="companyName" value="${danPlaMan.companyName}" type="text" maxlength="127"></td>
				</tr>
			</s:if>
			<tr>
				<th width="15%">计划名称</th>
				<td width="35%"><input name="danPlaMan.planName" style="width:50%" id="planName" value="${danPlaMan.planName}" type="text" maxlength="127"></td>
				
			
				<th width="15%">巡查人员姓名</th>
				<td width="35%"><input name="danPlaMan.checkPeopleName" style="width:50%" id="checkPeopleName" value="${danPlaMan.checkPeopleName}" type="text" maxlength="127"></td>
				
			
		    </tr>
			<tr>
				<th width="15%">审核状态</th>
				<td width="35%">
					<s:select  name="danPlaMan.auditResult" cssStyle="width:50%" id="auditResult" list="{'待审核','执行中','审核未通过'}" theme="simple" headerKey="" headerValue="--请选择--"></s:select>
				</td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_danPlaMan()" >查询<b></b></a>&nbsp;
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

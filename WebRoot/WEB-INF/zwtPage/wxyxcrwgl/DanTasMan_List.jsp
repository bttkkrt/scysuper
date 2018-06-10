<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>巡查任务管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_danTasMan","添加巡查任务","${ctx}/jsp/wxyxcrwgl/danTasManInitEdit.action?flag=add&dt="+dt.getTime(),700,400);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_danTasMan","修改巡查任务","${ctx}/jsp/wxyxcrwgl/danTasManInitEdit.action?flag=mod&danTasMan.id="+row_Id+"&dt="+dt.getTime(),700,400);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_danTasMan","查看巡查任务详情","${ctx}/jsp/wxyxcrwgl/danTasManView.action?danTasMan.id="+row_Id+"&dt="+dt.getTime(),700,400);
        	
        }
        
        function upload(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_danTasMan","上报巡查信息","${ctx}/jsp/wxyxcrwgl/danTasManUpload.action?danTasMan.id="+row_Id+"&dt="+dt.getTime(),700,400);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_danTasMan();
        }
        
         //查看任务关联隐患 
        function taskYhbList(row_Id){
        	var url =  "jsp/yhb/troManList.action?troMan.taskId="+row_Id+"&flag=qy_troman"
	        window.parent.addTab("win_yhbList","隐患列表",url);
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
		                	url : "danTasManDel.action",
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
		                        	search_danTasMan();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_danTasMan(){
        	
        	var queryParams = {
				"danTasMan.areaId": $("#areaId").val(),
"danTasMan.companyName": $("#companyName").val(),
"danTasMan.taskName": $("#taskName").val(),
"danTasMan.dangerName": $("#dangerName").val(),
 "queryCheckTimeStart" :$("#queryCheckTimeStart").val(),
 "queryCheckTimeEnd" :$("#queryCheckTimeEnd").val(),
"danTasMan.checkPeopleName": $("#checkPeopleName").val(),
"danTasMan.result": $("#result").val(),
"danTasMan.checkType": $("#checkType").val(),
"danTasMan.assPlanNo":'${ids}' 
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	var toolbar = [];
        	var frozen=[];
        	if('${roleName}'=='0'&&'${ids}'==''){//判断登录角色
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
  					  if( rec.result=='待巡查' && rec.assPlanNo == ''){
  							return '<input type="checkbox" name="xxx" value='+rec.id+'>';
  							}else{
  								return '';
  							}
    					}}
				]];
				};
			
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'巡查任务管理列表',
				url:'danTasManQuery.action',
				queryParams:{
					"danTasMan.areaId": $("#areaId").val(),
"danTasMan.companyName": $("#companyName").val(),
"danTasMan.taskName": $("#taskName").val(),
"danTasMan.dangerName": $("#dangerName").val(),
 "queryCheckTimeStart" :$("#queryCheckTimeStart").val(),
 "queryCheckTimeEnd" :$("#queryCheckTimeEnd").val(),
"danTasMan.checkPeopleName": $("#checkPeopleName").val(),
"danTasMan.result": $("#result").val(),
"danTasMan.assPlanNo":'${ids}' 
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'areaName',title:'所在区域',width:60},
{field:'companyName',title:'企业名称',width:100},
{field:'taskName',title:'任务名称',width:100},
{field:'checkTime',title:'巡查开始时间',width:80,formatter:function(value,rec){return value.substring(0,10);}},
{field:'checkTimeEnd',title:'巡查结束时间',width:80,formatter:function(value,rec){return value.substring(0,10);}},
{field:'checkPeopleName',title:'巡查人员姓名',width:100},
    {field:'result',title:'巡查结果',width:50},
{field:'op',title:'操作',width:150,formatter:function(value,rec){
	 var temp =  "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;";
	
	 if(rec.result=='待巡查' && '${roleName}'=='0' && rec.assPlanNo == ''){
	 	temp += "<a class='btn_04_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
	 }
	 if(rec.result=='待巡查' && '${roleName}'=='0'){
	 	temp += "<a class='btn_05_mini' onclick=upload('"+rec.id+"')>上报<b></b></a>";
	 }
	 if(rec.result=='待巡查' || rec.result=='已巡查' ){
	 	 temp += "<a class='btn_03_mini' onclick=taskYhbList('"+rec.id+"')>隐患信息<b></b></a>&nbsp;";
	 }
	return temp;
	}}
				        ]],
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
			<s:if test='roleName != 0'>
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%"><cus:SelectOneTag style="width:50%" property="danTasMan.areaId" defaultText='请选择' codeName="企业属地" value="${danTasMan.areaId}"/></td>
					<th width="15%">企业名称</th>
					<td width="35%"><input name="danTasMan.companyName" style="width:50%" id="companyName" value="${danTasMan.companyName}" type="text" maxlength="127"></td>
				</tr>
			</s:if>
			<tr>
				<th width="15%">任务名称</th>
				<td width="35%"><input name="danTasMan.taskName" style="width:50%" id="taskName" value="${danTasMan.taskName}" type="text" maxlength="127"></td>
				
			
				<th width="15%">巡查时间</th>
				<td width="35%"><input name="queryCheckTimeStart" id="queryCheckTimeStart" value="<fmt:formatDate type='both' value='${queryCheckTimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryCheckTimeEnd\')}'})" >
					-<input name="queryCheckTimeEnd" id="queryCheckTimeEnd" value="<fmt:formatDate type='both' value='${queryCheckTimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryCheckTimeStart\')}'})" ></td>
		    </tr>
			<tr>
				<th width="15%">巡查人员姓名</th>
				<td width="35%">
					<input name="danTasMan.checkPeopleName" style="width:50%" id="checkPeopleName" value="${danTasMan.checkPeopleName}" type="text" maxlength="127"></td>
			
				
				<th width="15%">巡查结果</th>
				<td width="35%">
					<s:select id="result" name="danTasMan.result" cssStyle="width:50%" list="{'待巡查','已巡查','过期任务'}" theme="simple" headerKey="" headerValue="--请选择--"></s:select>
				</td>
			</tr>
			<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_danTasMan()" >查询<b></b></a>&nbsp;
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

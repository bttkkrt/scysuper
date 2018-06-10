<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>隐患表管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
			
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_troMan","添加隐患","${ctx}/jsp/yhb/troManInitEdit.action?flag=add&dt="+dt.getTime()+"&troMan.taskId=${troMan.taskId}",900,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_troMan","修改隐患","${ctx}/jsp/yhb/troManInitEdit.action?flag=mod&troMan.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_troMan","查看隐患","${ctx}/jsp/yhb/troManView.action?troMan.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
        }
        //上传整改信息
       function uploadRect(row_Id){
       		var dt=new Date();
            createSimpleWindow("win_troMan","上传整改信息","${ctx}/jsp/yhb/troManUploadRect.action?troMan.id="+row_Id+"&dt="+dt.getTime(),900,500);
       }
       //审核信息
       function audit(row_Id){
       		var dt=new Date();
            createSimpleWindow("win_troMan","审核信息","${ctx}/jsp/yhb/troManAudit.action?troMan.id="+row_Id+"&dt="+dt.getTime(),900,500);
       }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_troMan();
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
		                	url : "troManDel.action",
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
		                        	search_troMan();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_troMan(){
        	var queryParams = {
				"troMan.troubleName": $("#troubleName").val(),
"troMan.troubleSource": $("#troubleSource").val(),
"troMan.areaId": $("#areaId").val(),
"troMan.companyName": $("#companyName").val(),
"troMan.userName": $("#userName").val(),
"troMan.troubleLevel": $("#troubleLevel").val(),
"troMan.troubleSort": $("#troubleSort").val(),
"troMan.taskId": '${troMan.taskId}',
"troMan.rectificationState": $("#rectificationState").val()
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
				}];
			
			frozen=[[
				    {field:'id',checkbox:true}
				]];
				
			}

        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'隐患列表',
				url:'troManQuery.action',
				queryParams:{
					"troMan.troubleName": $("#troubleName").val(),
"troMan.troubleSource": $("#troubleSource").val(),
"troMan.areaId": $("#areaId").val(),
"troMan.companyName": $("#companyName").val(),
"troMan.userName": $("#userName").val(),
"troMan.troubleLevel": $("#troubleLevel").val(),
"troMan.troubleSort": $("#troubleSort").val(),
"troMan.taskId": '${troMan.taskId}',
"troMan.rectificationState": $("#rectificationState").val()
				},
				frozenColumns:frozen,
				columns:[[
{field:'companyName',title:'企业名称',width:100},
				          {field:'troubleName',title:'隐患名称',width:100},
{field:'troubleSource',title:'隐患来源',width:100},
{field:'userName',title:'上报人员名称',width:100},
{field:'troubleLevel',title:'隐患级别',width:50},
{field:'troubleSort',title:'隐患类别',width:50},
{field:'rectificationState',title:'整改状态',width:60,formatter:function(value,rec){
		if(value=='1'){
		return "审核未通过";
	}else if(value=='4'||value=='6'||value=='11'){
		return "待整改";
	}else if(value=='2'||value=='3'||value=='5'||value=='7'||value=='20'||value=='21'){//20是转接，也算审核；21 待安委会审核
		return "待审核";
	}else{
		if(rec.dealState=='整改未完成'){
			return "整改未完成";
		}else{
			return "整改完成 ";
		}
	}
	}
},
{field:'op',title:'操作',width:200,formatter:function(value,rec){
	var temp = "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;";
	if((rec.rectificationState == '11'||rec.rectificationState == '1')&& '${roleName}'=='0'){//待整改
		 temp += "<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>&nbsp;";
			 if(rec.ifReportAwh == '0'){ //没有上报安委会  自己上报整改信息
			 	temp += "<a class='btn_04_mini' onclick=uploadRect('"+rec.id+"')>上传整改信息<b></b></a>&nbsp;";
			 }
	}
	else if(rec.dealState=='整改未完成'&& rec.troubleSource == '企业')
	{
		temp += "<a class='btn_04_mini' onclick=uploadRect('"+rec.id+"')>上传整改信息<b></b></a>&nbsp;";
	}
	return temp;
	}}
				        ]],
				toolbar:toolbar 
			}));
			if('${roleName}'=='0'){//判断登录角色
        	$('#pagination').datagrid('hideColumn', 'areaId');
        	$('#pagination').datagrid('hideColumn', 'companyName');
        	}
		});

         function exprtXls(){
        	var rows = $('#pagination').datagrid('getSelections');
        	var ids = "";
			for(var i=0;i<rows.length;i++){
				ids += rows[i].id+"|";
			}
			var data = "&troMan.userName=" + $("#userName").val();
			data = data + "&troMan.troubleName=" + $("#troubleName").val();
			data = data + "&troMan.troubleSource=" + $("#troubleSource").val();
			data = data + "&troMan.troubleLevel=" + $("#troubleLevel").val();
			data = data + "&troMan.troubleSort=" + $("#troubleSort").val();
			data = data + "&troMan.rectificationState=" + $("#rectificationState").val();
			window.open("${ctx}/jsp/yhb/troManExportXls.action?flag=qy&ids="+ids+data);
        }
        
    </script>
</head>
<body>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
			<div class="cell boxBmargin12">
			<form name="myform1" method="post" enctype="multipart/form-data" action="troManExportXls.action">
			<input type="hidden" name="flag" value="qy"/>
			<input type="hidden" name="ids" id="ids" value=""/>
			<table width="100%">
			<s:if test='roleName!="0"'>
			<tr>
				<th width="15%">所在区域</th>
				<td width="35%">
					<cus:SelectOneTag property="troMan.areaId" style="width:50%" defaultText='请选择' codeName="企业属地" value="${troMan.areaId}" onchange="clearCompany()" />
				</td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="troMan.companyName" style="width:50%" id="companyName" value="${troMan.companyName}" type="text" maxlength="127"></td>
			</tr></s:if>
				<tr>
					
				<th width="15%">隐患名称</th>
				<td width="35%"><input name="troMan.troubleName" style="width:50%" id="troubleName" value="${troMan.troubleName}" type="text" maxlength="127"></td>
				<th width="15%">隐患来源</th>
				<td width="35%"><s:select name="troMan.troubleSource" cssStyle="width:50%;"  id="troubleSource" list="#{'':'请选择','企业':'企业','安监':'安监'}" theme="simple"/></td>
			</tr>
			<tr>
				<th width="15%">上报人员名称</th>
				<td width="35%"><input name="troMan.userName" style="width:50%" id="userName" value="${troMan.userName}" type="text" maxlength="127"></td>
				<th width="15%">隐患级别</th>
				<td width="35%"><cus:SelectOneTag  style="width:50%" property="troMan.troubleLevel" defaultText='请选择' codeName="隐患级别" value="${troMan.troubleLevel}" /></td>
			</tr>
			<tr>
				<th width="15%">隐患类别</th>
				<td width="35%"><cus:SelectOneTag  style="width:50%" property="troMan.troubleSort" defaultText='请选择' codeName="隐患类别" value="${troMan.troubleSort}" /></td>
				<th width="15%">整改状态</th>
				<td width="35%"><s:select name="troMan.rectificationState" cssStyle="width:50%;"  id="rectificationState" list="#{'':'请选择','待审核':'待审核','审核未通过':'审核未通过','待整改':'待整改','整改未完成':'整改未完成','整改完成':'整改完成'}" theme="simple"/></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_troMan()" >查询<b></b></a>&nbsp;
						<a href="###" class="btn_01" onclick="clear_form(document.myform);" >清空<b></b></a>&nbsp;				
						<a href="###" class="btn_01" onclick="exprtXls();" >导出<b></b></a>&nbsp;	
					</td>
				</tr>
			</table>
			</form>
		</div>
		
		<div id="pagination" >
		</div>
		</div>
		</div>
	</div>
</body>
</html>

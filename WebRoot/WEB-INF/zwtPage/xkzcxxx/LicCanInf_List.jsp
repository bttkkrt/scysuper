<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>许可证撤销信息管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_licCanInf","添加许可证撤销信息","${ctx}/jsp/xkzcxxx/licCanInfInitEdit.action?flag=add&dt="+dt.getTime(),700,350);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_licCanInf","修改许可证撤销信息","${ctx}/jsp/xkzcxxx/licCanInfInitEdit.action?flag=mod&licCanInf.id="+row_Id+"&dt="+dt.getTime(),700,350);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_licCanInf","查看许可证撤销信息","${ctx}/jsp/xkzcxxx/licCanInfView.action?licCanInf.id="+row_Id+"&dt="+dt.getTime(),700,350);
        	
        }
        
         function check(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_licCanInf","审核许可证撤销信息","${ctx}/jsp/xkzcxxx/licCanInfCheck.action?flag=check&licCanInf.id="+row_Id+"&dt="+dt.getTime(),700,450);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_licCanInf();
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
		                	url : "licCanInfDel.action",
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
		                        	search_licCanInf();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_licCanInf(){
        	var queryParams = {
				"licCanInf.areaId": $("#areaId").val(),
"licCanInf.areaName": $("#areaName").val(),
"licCanInf.companyId": $("#companyId").val(),
"licCanInf.auditState": $("#auditState").val(),
"licCanInf.companyName": $("#companyName").val(),
"licCanInf.licenseName": $("#licenseName").val(),
"licCanInf.licenseNumber": $("#licenseNumber").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        var toolbar = [];
        	if('${roleName}'=='1'){//判断登录角色
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
				
			}
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'许可证撤销信息列表',
				url:'licCanInfQuery.action',
				queryParams:{
					"licCanInf.areaId": $("#areaId").val(),
"licCanInf.areaName": $("#areaName").val(),
"licCanInf.companyId": $("#companyId").val(),
"licCanInf.companyName": $("#companyName").val(),
"licCanInf.licenseName": $("#licenseName").val(),
"licCanInf.licenseNumber": $("#licenseNumber").val()
				},
				frozenColumns:[[
				   {field:'id',width:20,formatter:function(value,rec){
				    		var opt = '<input type="checkbox" name="xxx" value='+rec.id+'>'; 
						    if((rec.createUserID != "${sessionScope['LOGIN_USER_ID']}")||(rec.auditState=='待审核')||(rec.auditState=='审核通过')){
								opt = ''; 
						    } 
						    return opt ; 

				    }}
				]],
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'licenseName',title:'许可证名称',width:100},
{field:'licenseNumber',title:'许可证编号',width:100},
{field:'auditState',title:'审核状态',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	var button1="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=check('"+rec.id+"')>审核<b></b></a>";
	var button2="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	var button3="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
		if(rec.auditState=='待审核'&&"true"=="${canCheck}"){
			return button1;
		}else if(rec.auditState=='审核未通过'&&rec.createUserID == "${sessionScope['LOGIN_USER_ID']}"){
			return button3;
		}else{
			return button2;
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
				<tr>
				<th width="15%">所在区域</th>
				<td width="35%"><cus:SelectOneTag property="licCanInf.areaId" style="width: 50%" defaultText='请选择' codeName="企业属地" value="${licCanInf.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="licCanInf.companyName" id="companyName" style="width: 50%" value="${licCanInf.companyName}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">许可证名称</th>
				<td width="35%"><input name="licCanInf.licenseName" id="licenseName" style="width: 50%" value="${licCanInf.licenseName}" type="text" maxlength="127"></td>
				<th width="15%">许可证编号</th>
				<td width="35%"><input name="licCanInf.licenseNumber" id="licenseNumber" style="width: 50%" value="${licCanInf.licenseNumber}" type="text" maxlength="127"></td>
			</tr>
			<tr>
			 <th width="15%">审核状态</th>
			 	<td width="35%">
			 		<s:select id="auditState" cssStyle="width:50%;"  list="#{'':'请选择','待审核':'待审核','审核通过':'审核通过','审核未通过':'审核未通过'}" theme="simple"/>
			 	</td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_licCanInf()" >查询<b></b></a>&nbsp;
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

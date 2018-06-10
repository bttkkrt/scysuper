<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业重点危险源的识别评估分级管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>

        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_comDanIde","添加企业重点危险源的识别评估分级","${ctx}/jsp/zdwxysbpgfj/comDanIdeInitEdit.action?flag=add&dt="+dt.getTime(),1000,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_comDanIde","修改企业重点危险源的识别评估分级","${ctx}/jsp/zdwxysbpgfj/comDanIdeInitEdit.action?flag=mod&comDanIde.id="+row_Id+"&dt="+dt.getTime(),1000,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_comDanIde","查看企业重点危险源的识别评估分级","${ctx}/jsp/zdwxysbpgfj/comDanIdeView.action?comDanIde.id="+row_Id+"&dt="+dt.getTime(),1000,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_comDanIde();
        }
        
         function check(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_comDanIde","审核企业重点危险源的识别评估分级","${ctx}/jsp/zdwxysbpgfj/comDanIdeCheck.action?flag=check&comDanIde.id="+row_Id+"&dt="+dt.getTime(),1000,500);
        	
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
		                	url : "comDanIdeDel.action",
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
		                        	search_comDanIde();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_comDanIde(){
        	var queryParams = {
				"comDanIde.areaId": $("#areaId").val(),
"comDanIde.companyName": $("#companyName").val(),
"comDanIde.auditState": $("#auditState").val(),
"comDanIde.dangerName": $("#dangerName").val(),
"comDanIde.auditResult": $("#auditResult").val(),
"comDanIde.dangerLevel": $("#dangerLevel").val(),
"comDanIde.safePerson": $("#safePerson").val(),
"comDanIde.dangerType": $("#dangerType").val()
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
  					  if( rec.auditState == '审核未通过'|| rec.auditState == '待提交'){
  							return '<input type="checkbox" name="xxx" value='+rec.id+'>';
  							}else{
  								return '';
  							}
    					}}
				]];
			}
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'企业重点危险源的识别评估分级列表',
				url:'comDanIdeQuery.action',
				queryParams:{
					"comDanIde.areaId": $("#areaId").val(),
"comDanIde.companyName": $("#companyName").val(),
"comDanIde.dangerName": $("#dangerName").val(),
"comDanIde.auditState": $("#auditState").val(),
"comDanIde.auditResult": $("#auditResult").val(),
"comDanIde.dangerLevel": $("#dangerLevel").val(),
"comDanIde.safePerson": $("#safePerson").val(),
"comDanIde.dangerType": $("#dangerType").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'dangerName',title:'重点危险源名称',width:100},
{field:'dangerLevel',title:'重点危险源级别',width:100},
{field:'safePerson',title:'安全负责人',width:100},
{field:'dangerType',title:'重点危险源类别',width:100},
{field:'auditState',title:'审核状态',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	var button1="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=check('"+rec.id+"')>审核<b></b></a>";
	var button2="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	var button3="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
		if(rec.auditState=='待审核'&&"true"=="${canCheck}"){
			return button1;
		}else if('${roleName}'=='0'&&(rec.auditState=='审核未通过' || rec.auditState == '待提交' )){
			return button3;
		}else{
			return button2;
		}
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
			<s:if test='roleName!="0"'>
				<tr>
					
				<th width="15%">所在区域</th>
				<td width="35%"><cus:SelectOneTag property="comDanIde.areaId" style="width:50%" defaultText='请选择' codeName="企业属地" value="${comDanIde.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="comDanIde.companyName" style="width:50%" id="companyName" value="${comDanIde.companyName}" type="text" maxlength="127"></td>
			</tr>
			</s:if>
			<tr>
				<th width="15%">重点危险源名称</th>
				<td width="35%"><input name="comDanIde.dangerName" style="width:50%" id="dangerName" value="${comDanIde.dangerName}" type="text" maxlength="127"></td>
				<th width="15%">安全负责人</th>
				<td width="35%"><input name="comDanIde.safePerson" style="width:50%" id="safePerson" value="${comDanIde.safePerson}" type="text" maxlength="127"></td>
			</tr>
			<tr>
			  <th width="15%">状态</th>
			 	<td width="35%">
			 		<s:if test='roleName!="0"'>
			 		<s:select id="auditState" name="comDanIde.auditState" cssStyle="width:50%;"  list="#{'':'请选择','待审核':'待审核','审核通过':'审核通过','审核未通过':'审核未通过'}" theme="simple"/>
			 		</s:if>
			 		<s:else>
			 		<s:select id="auditState" name="comDanIde.auditState" cssStyle="width:50%;" list="#{'':'请选择','待提交':'待提交','待审核':'待审核','审核通过':'审核通过','审核未通过':'审核未通过'}" theme="simple"/>
			 		</s:else>
			 	</td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_comDanIde()" >查询<b></b></a>&nbsp;
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

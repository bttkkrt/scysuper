<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>领导带班情况管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_leaCla","添加领导带班情况","${ctx}/jsp/lddbqk/leaClaInitEdit.action?flag=add&dt="+dt.getTime(),900,400);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_leaCla","修改领导带班情况","${ctx}/jsp/lddbqk/leaClaInitEdit.action?flag=mod&leaCla.id="+row_Id+"&dt="+dt.getTime(),900,400);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_leaCla","查看领导带班情况","${ctx}/jsp/lddbqk/leaClaView.action?leaCla.id="+row_Id+"&dt="+dt.getTime(),900,400);
        	
        }
        
        function check(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_leaCla","审核领导带班情况","${ctx}/jsp/lddbqk/leaClaCheck.action?flag=check&leaCla.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
        }
        
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_leaCla();
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
		                	url : "leaClaDel.action",
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
		                        	search_leaCla();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_leaCla(){
        	var queryParams = {
				"leaCla.areaId": $("#areaId").val(),
				"leaCla.auditState": $("#auditState").val(),
"leaCla.companyName": $("#companyName").val(),
"leaCla.plannedMonth": $("#plannedMonth").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
 var toolbar = [];
        var frozen=[];
        	if('${roleName}'==0){//判断登录角色
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
				title:'领导带班情况列表',
				url:'leaClaQuery.action',
				queryParams:{
					"leaCla.areaId": $("#areaId").val(),
					"leaCla.auditState": $("#auditState").val(),
"leaCla.companyName": $("#companyName").val(),
"leaCla.plannedMonth": $("#plannedMonth").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'plannedMonth',title:'计划月份',width:100,formatter:function(value,rec){return value.substring(0,7);}},
{field:'auditState',title:'审核状态',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	var button1="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=check('"+rec.id+"')>审核<b></b></a>";
	var button2="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	var button3="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
	if(rec.auditState=='待审核'&&"true"=="${canCheck}"){
			return button1;
		}else if("${roleName}"=='0'&&rec.auditState=='审核未通过'){
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
			<c:if test='${roleName!=0}'>
				<tr>
					
				<th width="15%">所在区域</th>
				<td width="35%"><cus:SelectOneTag style="width:50%" property="leaCla.areaId" defaultText='请选择' codeName="企业属地" value="${leaCla.areaId}" /></td>
				
				<th width="15%">企业名称</th>
				<td width="35%"><input name="leaCla.companyName" style="width:50%" id="companyName" value="${leaCla.companyName}" type="text" maxlength="127"></td>
			</tr>
			</c:if>
			<tr>
				<th width="15%">计划月份</th>
				<td width="35%">
					<input name="leaCla.plannedMonth"  style="width:50%" id="plannedMonth" value="<fmt:formatDate type='both' value='${leaCla.plannedMonth}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM'})">
				</td>
				<th width="15%">审核状态</th>
			 	<td width="35%">
			 		<s:select id="auditState" name="leaCla.auditState" cssStyle="width:50%;"  list="#{'':'请选择','待审核':'待审核','审核通过':'审核通过','审核未通过':'审核未通过'}" theme="simple"/>
			 	</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_leaCla()" >查询<b></b></a>&nbsp;
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

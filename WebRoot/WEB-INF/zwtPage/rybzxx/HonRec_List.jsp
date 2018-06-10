<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>荣誉表彰信息管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_honRec","添加荣誉表彰信息","${ctx}/jsp/rybzxx/honRecInitEdit.action?flag=add&dt="+dt.getTime(),700,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_honRec","修改荣誉表彰信息","${ctx}/jsp/rybzxx/honRecInitEdit.action?flag=mod&honRec.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_honRec","查看荣誉表彰信息","${ctx}/jsp/rybzxx/honRecView.action?honRec.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
         function check(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_honRec","审核","${ctx}/jsp/rybzxx/honRecCheck.action?flag=check&honRec.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_honRec();
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
		                	url : "honRecDel.action",
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
		                        	search_honRec();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_honRec(){
        	var queryParams = {
				"honRec.areaId": $("#areaId").val(),
"honRec.recognitionDept": $("#recognitionDept").val(),
"honRec.honor": $("#honor").val(),
"honRec.companyName": $("#companyName").val(),
"honRec.auditState": $("#auditState").val(),
"honRec.auditResult": $("#auditResult").val(),
"queryBzyearStart": $("#queryBzyearStart").val(),
"queryBzyearEnd": $("#queryBzyearEnd").val()
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
  					  if( rec.createUserID == "${sessionScope['LOGIN_USER_ID']}" && (rec.auditState == '审核未通过' || rec.auditState == '待提交')){
  							return '<input type="checkbox" name="xxx" value='+rec.id+'>';
  							}else{
  								return '';
  							}
    					}}
				]];
				};
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'荣誉表彰信息列表',
				url:'honRecQuery.action',
				queryParams:{
					"honRec.areaId": $("#areaId").val(),
"honRec.recognitionDept": $("#recognitionDept").val(),
"honRec.honor": $("#honor").val(),
"honRec.companyName": $("#companyName").val(),
"honRec.auditState": $("#auditState").val(),
"honRec.auditResult": $("#auditResult").val(),
"queryBzyearStart": $("#queryBzyearStart").val(),
"queryBzyearEnd": $("#queryBzyearEnd").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'bzyear',title:'年度',width:100,formatter:function(value,rec){return value.substring(0,4);}},
{field:'honor',title:'荣誉称号',width:100},
{field:'recognitionDept',title:'表彰部门',width:100},
{field:'auditState',title:'审核状态',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	var button1="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=check('"+rec.id+"')>审核<b></b></a>";
	var button2="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	var button3="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
		if(rec.auditState=='待审核'&&"true"=="${canCheck}"){
			return button1;
		}else if((rec.auditState=='审核未通过' || rec.auditState=='待提交')&& rec.createUserID == "${sessionScope['LOGIN_USER_ID']}")
		{
			return button3;
		}
		else{
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
				<td width="35%"><cus:SelectOneTag property="honRec.areaId" defaultText='请选择' codeName="企业属地" value="${honRec.areaId}" style="width:50%"/></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="honRec.companyName" id="companyName" value="${honRec.companyName}" type="text" maxlength="127" style="width:50%"></td>
			</tr></s:if>
			<tr>
				<th width="15%">年度</th>
				<td width="35%">
					<input name="queryBzyearStart" id="queryBzyearStart" value="<fmt:formatDate type='date' value='${queryBzyearStart}' pattern="yyyy"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy',maxDate:'#F{$dp.$D(\'queryBzyearEnd\')}'})" >
					-<input name="queryBzyearEnd" id="queryBzyearEnd" value="<fmt:formatDate type='date' value='${queryBzyearEnd}' pattern="yyyy"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy',minDate:'#F{$dp.$D(\'queryBzyearStart\')}'})" >
				</td>
				<th width="15%">荣誉称号</th>
				<td width="35%"><input name="honRec.honor" id="honor" value="${honRec.honor}" type="text" maxlength="127" style="width:50%"></td>
			</tr>
			<tr>
				<th width="15%">表彰部门</th>
				<td width="35%"><input name="honRec.recognitionDept" id="recognitionDept" value="${honRec.recognitionDept}" type="text" maxlength="127" style="width:50%"></td>
			  	<th width="15%">状态</th>
			 	<td width="35%">
			 		<s:if test='roleName!="0"'>
			 		<s:select id="auditState" name="honRec.auditState" cssStyle="width:50%;" list="#{'':'请选择','待审核':'待审核','审核通过':'审核通过','审核未通过':'审核未通过'}" theme="simple"/>
			 		</s:if>
			 		<s:else>
			 		<s:select id="auditState" name="honRec.auditState"  cssStyle="width:50%;" list="#{'':'请选择','待提交':'待提交','待审核':'待审核','审核通过':'审核通过','审核未通过':'审核未通过'}" theme="simple"/>
			 		</s:else>
			 	</td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_honRec()" >查询<b></b></a>&nbsp;
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

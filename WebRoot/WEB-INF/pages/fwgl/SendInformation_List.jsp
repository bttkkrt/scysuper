<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>发文管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_sendInformation","添加发文管理","${ctx}/jsp/fwgl/sendInformationInitEdit.action?flag=add&dt="+dt.getTime(),1050,550);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_sendInformation","修改发文管理","${ctx}/jsp/fwgl/sendInformationInitEdit.action?flag=mod&sendInformation.id="+row_Id+"&dt="+dt.getTime(),1050,550);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_sendInformation","查看发文管理","${ctx}/jsp/fwgl/sendInformationView.action?sendInformation.id="+row_Id+"&dt="+dt.getTime(),1050,550);
        	
        }
        
         function check(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_sendInformation","审核发文管理","${ctx}/jsp/fwgl/sendInformationCheck.action?flag=check&sendInformation.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_sendInformation();
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
		                	url : "sendInformationDel.action",
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
		                        	search_sendInformation();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_sendInformation(){
        	var queryParams = {
				"sendInformation.sendinfoNum": $("#sendinfoNum").val(),
				"sendInformation.sendinfoTitle": $("#sendinfoTitle").val(),
"sendInformation.sendinfoCheckUserid": $("#sendinfoCheckUserid").val(),
"sendInformation.sendinfoDraftUsername": $("#sendinfoDraftUsername").val(),
"sendInformation.auditState": $("#auditState").val(),
"sendInformation.sendinfoUsernames": $("#sendinfoUsernames").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'发文管理列表',
				url:'sendInformationQuery.action',
				queryParams:{
					"sendInformation.sendinfoTitle": $("#sendinfoTitle").val(),
"sendInformation.sendinfoCheckUserid": $("#sendinfoCheckUserid").val(),
"sendInformation.auditState": $("#auditState").val(),
"sendInformation.sendinfoUsernames": $("#sendinfoUsernames").val()
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
{field:'sendinfoTitle',title:'发文标题',width:100},
{field:'sendinfoNum',title:'发文字号',width:100},
{field:'sendinfoDraftUsername',title:'拟稿人',width:100},
{field:'auditState',title:'审核状态',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	var button1="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=check('"+rec.id+"')>审核<b></b></a>";
	var button2="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	var button3="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
		if(rec.auditState=='待审核'&&"true"=="${canCheck}"){
			return button1;
		}else if(rec.auditState=='审核通过'){
			return button2;
		}else if(rec.auditState=='审核未通过'&&(rec.createUserID == "${sessionScope['LOGIN_USER_ID']}")){
			return button3;
		}else{
			return button2;
		}
	}}
				        ]],
				toolbar:[{
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
				}]
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
				<th width="15%">发文标题</th>
				<td width="35%"><input name="sendInformation.sendinfoTitle" style="width:50%"  id="sendinfoTitle" value="${sendInformation.sendinfoTitle}" type="text" maxlength="127"></td>
                <th width="15%">发文字号</th>
				<td width="35%"><input name="sendInformation.sendinfoNum" style="width:50%" id="sendinfoNum" value="${sendInformation.sendinfoNum}" type="text" maxlength="127"></td>
				
			</tr>
			<tr>
			<th width="15%">拟稿人</th>
				<td width="35%"><input name="sendInformation.sendinfoDraftUsername" style="width:50%" id="sendinfoDraftUsername" value="${sendInformation.sendinfoDraftUsername}" type="text" maxlength="127"></td>
			  <th width="15%">审核状态</th>
			 	<td width="35%">
			 		<s:select id="auditState" name="sendInformation.auditState" cssStyle="width:50%;"  list="#{'':'请选择','待审核':'待审核','审核通过':'审核通过','审核未通过':'审核未通过'}" theme="simple"/>
			 	</td>
			</tr>
			
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_sendInformation()" >查询<b></b></a>&nbsp;
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

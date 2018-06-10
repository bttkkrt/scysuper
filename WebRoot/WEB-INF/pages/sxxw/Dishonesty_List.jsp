<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>失信行为管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_dishonesty","添加失信行为","${ctx}/jsp/sxxw/dishonestyInitEdit.action?flag=add&dt="+dt.getTime(),900,550);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_dishonesty","修改失信行为","${ctx}/jsp/sxxw/dishonestyInitEdit.action?flag=mod&dishonesty.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_dishonesty","查看失信行为","${ctx}/jsp/sxxw/dishonestyView.action?dishonesty.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_dishonesty();
        }
        function del(){
        	var rows = document.getElementsByName('delBox');
        	var ids = "";
			for(var i=0;i<rows.length;i++){
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
		                	url : "dishonestyDel.action",
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
		                        	search_dishonesty();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_dishonesty(){
        	var queryParams = {
				"dishonesty.areaId": $("#areaId").val(),
"dishonesty.companyName": $("#companyName").val(),
"dishonesty.punishName": $("#punishName").val(),
"dishonesty.symbolDecision": $("#symbolDecision").val(),
"dishonesty.punishedSpecies": $("#punishedSpecies").val(),
"dishonesty.creditRating": $("#creditRating").val(),
"dishonesty.checkStatus": $("#checkStatus").val()
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
				title:'失信行为列表',
				url:'dishonestyQuery.action',
				queryParams:{
					"dishonesty.areaId": $("#areaId").val(),
"dishonesty.companyName": $("#companyName").val(),
"dishonesty.punishName": $("#punishName").val(),
"dishonesty.symbolDecision": $("#symbolDecision").val(),
"dishonesty.punishedSpecies": $("#punishedSpecies").val(),
"dishonesty.creditRating": $("#creditRating").val(),
"dishonesty.checkStatus": $("#checkStatus").val()
				},
				//frozenColumns:frozen,
				columns:[[
{field:'id',width:25,formatter:function(value,rec){
	var box="<input type='checkbox' name='delBox' value='"+value+"'>";
	if(rec.createUserID=="${sessionScope['LOGIN_USER_ID']}"&& (rec.checkStatus=='审核未通过' || rec.checkStatus=='审批未通过')){
		return box;
	}else{
		return "";//return "<input type='checkbox' name='delBox' disabled value='"+value+"'>";
	}
	
}},
{field:'areaId',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'punishName',title:'处罚名称',width:100},
{field:'symbolDecision',title:'处罚决定书文号',width:100},
{field:'punishedSpecies',title:'处罚种类',width:100},
{field:'creditRating',title:'失信等级',width:100},
{field:'checkStatus',title:'审核状态',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	var button1="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
	var button2="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=check1('"+rec.id+"')>审核<b></b></a>";
	var button3="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=check2('"+rec.id+"')>审批<b></b></a>";
	var button4="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	if(rec.checkStatus=='待审核'){
		if("false"=="${check1}"&&"false"=="${check2}"){//安监中队队长
			return button4;
		}else if("true"=="${check1}"&&"false"=="${check2}"){//监察大队队长
			return button2;
		}else if("false"=="${check1}"&&"true"=="${check2}"){//安监局领导
			return button4;
		}
	}else if(rec.checkStatus=='审核未通过'){
		if("false"=="${check1}"&&"false"=="${check2}"){//安监中队队长
			if("${sessionScope['LOGIN_USER_ID']}"==rec.createUserID){
				return  button1;
			}else{
				return button4;
			}
			
		}else if("true"=="${check1}"&&"false"=="${check2}"){//监察大队队长
			return button4;
		}else if("false"=="${check1}"&&"true"=="${check2}"){//安监局领导
			return button4;
		}
	}else if(rec.checkStatus=='待审批'){
		if("false"=="${check1}"&&"false"=="${check2}"){//安监中队队长
			return button4;
		}else if("true"=="${check1}"&&"false"=="${check2}"){//监察大队队长
			return button4;
		}else if("false"=="${check1}"&&"true"=="${check2}"){//安监局领导
			return button3;
		}
	}else if(rec.checkStatus=='审批通过'){
		return button4;
	}else if(rec.checkStatus=='审批未通过'){
		if("false"=="${check1}"&&"false"=="${check2}"){//安监中队队长
			if("${sessionScope['LOGIN_USER_ID']}"==rec.createUserID){
				return  button1;
			}else{
				return button4;
			}
		}else if("true"=="${check1}"&&"false"=="${check2}"){//监察大队队长
			return button4;
		}else if("false"=="${check1}"&&"true"=="${check2}"){//安监局领导
			return button4;
		}
	}else{
		return button4;
	}
	
}}
				        ]],
				toolbar:toolbar
			}));
		});

		function check1(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_dishonesty","审核失信行为","${ctx}/jsp/sxxw/dishonestyCheck1.action?flag=check1&dishonesty.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
        function check2(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_dishonesty","审批失信行为","${ctx}/jsp/sxxw/dishonestyCheck2.action?flag=check2&dishonesty.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
        
    </script>
</head>
<body>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
			<div class="cell boxBmargin12">
			<table width="100%">
				<s:if test='roleName!="1"'>
				<tr>
					
				<th width="15%">所在区域</th>
				<td width="35%"><cus:SelectOneTag style="width: 50%"  property="dishonesty.areaId" defaultText='请选择' codeName="企业属地" value="${dishonesty.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="dishonesty.companyName" style="width: 50%" id="companyName" value="${dishonesty.companyName}" type="text" maxlength="127"></td>
			</tr></s:if>
			<tr>
				<th width="15%">处罚名称</th>
				<td width="35%"><input name="dishonesty.punishName" style="width: 50%" id="punishName" value="${dishonesty.punishName}" type="text" maxlength="127"></td>
				<th width="15%">处罚决定书文号</th>
				<td width="35%"><input name="dishonesty.symbolDecision" style="width: 50%" id="symbolDecision" value="${dishonesty.symbolDecision}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">处罚种类</th>
				<td width="35%"><cus:SelectOneTag style="width: 50%"  property="dishonesty.punishedSpecies" defaultText='请选择' codeName="处罚种类" value="${dishonesty.punishedSpecies}" /></td>
				<th width="15%">失信等级</th>
				<td width="35%"><cus:SelectOneTag style="width: 50%"  property="dishonesty.creditRating" defaultText='请选择' codeName="失信等级" value="${dishonesty.creditRating}" /></td>
			</tr>
			<tr>
				<th width="15%">审核状态</th>
			 	<td width="35%">
			 		<s:select id="checkStatus" name="dishonesty.checkStatus" cssStyle="width:50%;"  list="#{'':'请选择','待审核':'待审核','审核未通过':'审核未通过','待审批':'待审批','审批通过':'审批通过','审批未通过':'审批未通过'}" theme="simple"/>
			 	</td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_dishonesty()" >查询<b></b></a>&nbsp;
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

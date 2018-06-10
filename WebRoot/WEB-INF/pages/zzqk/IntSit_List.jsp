<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>资质情况管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_intSit","添加行政许可及资质情况","${ctx}/jsp/zzqk/intSitInitEdit.action?flag=add&dt="+dt.getTime(),700,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_intSit","修改行政许可及资质情况","${ctx}/jsp/zzqk/intSitInitEdit.action?flag=mod&intSit.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_intSit","查看行政许可及资质情况","${ctx}/jsp/zzqk/intSitView.action?intSit.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
         function check(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_intSit","审核行政许可及资质情况","${ctx}/jsp/zzqk/intSitCheck.action?flag=check&intSit.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_intSit();
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
		                	url : "intSitDel.action",
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
		                        	search_intSit();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_intSit(){
        	var queryParams = {
				"intSit.areaId": $("#areaId").val(),
"intSit.areaName": $("#areaName").val(),
"intSit.companyName": $("#companyName").val(),
"intSit.auditState": $("#auditState").val(),
"intSit.auditResult": $("#auditResult").val(),
"intSit.intelligenceCardnum": $("#intelligenceCardnum").val(),
"intSit.intelligenceCardname": $("#intelligenceCardname").val(),
"intSit.intelligenceType": $("#intelligenceType").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	var entBaseInfoId=GetQueryString("entBaseInfoId");
        	var toolbar = [];
        	var frozen=[];
        	//if(true){//判断登录角色
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
  					  if( rec.createUserID == "${sessionScope['LOGIN_USER_ID']}" && (rec.auditState == '审核未通过'|| rec.auditState == '待提交')){
  							return '<input type="checkbox" name="xxx" value='+rec.id+'>';
  							}else{
  								return '';
  							}
    					}}
				]];
				
				};
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'行政许可及资质情况列表',
				url:'intSitQuery.action',
				queryParams:{
					"intSit.areaId": $("#areaId").val(),
					"entBaseInfoId":entBaseInfoId,
"intSit.areaName": $("#areaName").val(),
"intSit.companyName": $("#companyName").val(),
"intSit.auditState": $("#auditState").val(),
"intSit.auditResult": $("#auditResult").val(),
"intSit.intelligenceCardnum": $("#intelligenceCardnum").val(),
"intSit.intelligenceCardname": $("#intelligenceCardname").val(),
"intSit.intelligenceType": $("#intelligenceType").val()
				},
				frozenColumns:frozen,
				columns:[[
				          //{field:'areaName',title:'所在区域',width:100},
//{field:'companyName',title:'企业名称',width:100},
{field:'intelligenceCardnum',title:'证书编号',width:100},
{field:'intelligenceCardname',title:'证书名称',width:100},
{field:'intelligenceType',title:'资质类型',width:100},
{field:'auditState',title:'审核状态',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	var button1="<a class='btn_02_mini' onclick=view('"+rec.id+"')>查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=check('"+rec.id+"')>审核<b></b></a>";
	var button2="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	var button3="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
		if(rec.auditState=='待审核'&&"true"=="${canCheck}"){
			return button1;
		}else if((rec.auditState=='审核未通过' || rec.auditState == '待提交') && rec.createUserID == "${sessionScope['LOGIN_USER_ID']}")
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
				<%-- <s:if test='roleName!="0"'>
					<tr>
						<th width="15%">所在区域</th>
						<td width="35%"><cus:SelectOneTag property="intSit.areaId" defaultText='请选择' codeName="企业属地" value="${intSit.areaId}" Style="width:50%"/></td>
						<th width="15%">企业名称</th>
						<td width="35%"><input name="intSit.companyName" id="companyName" value="${intSit.companyName}" type="text" maxlength="127" style="width:50%"></td>
					</tr>
				</s:if> --%>
			<tr>
				<th width="15%">资质类型</th>
				<td width="35%"><cus:SelectOneTag property="intSit.intelligenceType" defaultText='请选择' codeName="资质类型" value="${intSit.intelligenceType}" Style="width:50%"/></td>
				<th width="15%">证书名称</th>
				<td width="35%"><input name="intSit.intelligenceCardname" id="intelligenceCardname" value="${intSit.intelligenceCardname}" type="text" maxlength="127" style="width:50%"></td>
			</tr>
			<tr>
			  <th width="15%">状态</th>
			 	<td width="35%">
			 		<s:if test='roleName!="0"'>
			 		<s:select id="auditState" name="intSit.auditState"  cssStyle="width:50%;" list="#{'':'请选择','待审核':'待审核','审核通过':'审核通过','审核未通过':'审核未通过'}" theme="simple"/>
			 		</s:if>
			 		<s:else>
			 		<s:select id="auditState" name="intSit.auditState" cssStyle="width:50%;" list="#{'':'请选择','待提交':'待提交','待审核':'待审核','审核通过':'审核通过','审核未通过':'审核未通过'}" theme="simple"/>
			 		</s:else>
			 	</td>
				<th width="15%">证书编号</th>
				<td width="35%"><input name="intSit.intelligenceCardnum" id="intelligenceCardnum" value="${intSit.intelligenceCardnum}" type="text" maxlength="127" style="width:50%"></td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_intSit()" >查询<b></b></a>&nbsp;
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

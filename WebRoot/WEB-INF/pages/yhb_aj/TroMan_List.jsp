<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>隐患表管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_troMan","添加隐患","${ctx}/jsp/yhb/troManInitEditAJ.action?flag=add&dt="+dt.getTime(),900,550);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_troMan","修改隐患","${ctx}/jsp/yhb/troManInitEditAJ.action?flag=mod&troMan.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
         function editCXXG(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_troMan","修改隐患","${ctx}/jsp/yhb/troManInitEditAJ.action?flag=cxxg&troMan.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
        
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_troMan","查看隐患","${ctx}/jsp/yhb/troManViewAJ.action?troMan.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
        //上传整改信息
       function uploadRect(row_Id){
       		var dt=new Date();
            createSimpleWindow("win_troMan","上传整改信息","${ctx}/jsp/yhb/troManUploadRectAJ.action?troMan.id="+row_Id+"&dt="+dt.getTime(),900,550);
       }
       //审核信息
       function audit(row_Id){
       		var dt=new Date();
            createSimpleWindow("win_troMan","审核信息","${ctx}/jsp/yhb/troManAuditAJ.action?troMan.id="+row_Id+"&dt="+dt.getTime(),900,550);
       }
       
       function createXcjl(row_Id)
       {
       		var dt=new Date();
            createSimpleWindow("win_troMan","现场检查记录","${ctx}/jsp/yhb/troManXcjc.action?troMan.id="+row_Id+"&dt="+dt.getTime(),900,550);
       }
       
       function createCase(row_Id)
       {
       		var dt=new Date();
            createSimpleWindow("win_troMan","添加案件","${ctx}/jsp/yhb/troManCase.action?troMan.id="+row_Id+"&dt="+dt.getTime(),900,550);
       }
       
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_troMan();
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
"troMan.rectificationState": $("#rectificationState").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	frozen=[[
				    {field:'id',checkbox:true}
				]];
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'隐患列表',
				url:'troManQueryAJ.action',
				queryParams:{
					"troMan.troubleName": $("#troubleName").val(),
"troMan.troubleSource": $("#troubleSource").val(),
"troMan.areaId": $("#areaId").val(),
"troMan.companyName": $("#companyName").val(),
"troMan.userName": $("#userName").val(),
"troMan.troubleLevel": $("#troubleLevel").val(),
"troMan.troubleSort": $("#troubleSort").val(),
"troMan.rectificationState": $("#rectificationState").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'troubleName',title:'隐患名称',width:fixWidth(0.1)},
{field:'troubleSource',title:'隐患来源',width:fixWidth(0.1)},
{field:'areaName',title:'所在区域',width:fixWidth(0.1)},
{field:'companyName',title:'企业名称',width:fixWidth(0.1)},
{field:'userName',title:'上报人员名称',width:fixWidth(0.1)},
{field:'troubleLevel',title:'隐患级别',width:fixWidth(0.1)},
{field:'troubleSort',title:'隐患类别',width:fixWidth(0.1)},
{field:'rectificationState',title:'整改状态',width:fixWidth(0.1),formatter:function(value,rec){
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
{field:'op',title:'操作',width:fixWidth(0.2),formatter:function(value,rec){
	var button1="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;";
	var button2="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
	var button3="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=deal('"+rec.id+"')>处理<b></b></a>";
	var button4="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=deal('"+rec.id+"')>修改<b></b></a>";
	var button5="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=report('"+rec.id+"')>上报整改信息<b></b></a>";//上报
	var button6="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=check('"+rec.id+"')>审核<b></b></a>";//审核
	var button7="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=editCXXG('"+rec.id+"')>修改<b></b></a>";
	var button8="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=report('"+rec.id+"')>重新整改<b></b></a>";//上报
	var button9 = "<a class='btn_04_mini' onclick=createXcjl('"+rec.id+"') >现场检查记录<b></b></a>";
	var button10 = "<a class='btn_05_mini' onclick=createCase('"+rec.id+"') >立案<b></b></a>";
	var button = "";
	//隐患上报隐患人
	if("${ids}"==rec.createUserID&&(rec.rectificationState=="1"||rec.rectificationState=="3")){
		if("审核通过"==rec.auditResult){
			button = button1;
		}else if("审核未通过"==rec.auditResult){
			button = button7;
		}
	}
	else if((rec.rectificationState=="1"||rec.rectificationState=="2"||rec.rectificationState=="3")&&'${userType}'=='7'){
		button = button1;
	}else
	//安委会办公室的人，在状态是2(2的时候必须是上报安委会办公室的)或4的时候，可以进行修改操作
	if(((rec.rectificationState=="2"&&rec.ifReportAwh=="1"))&&'${userType}'=='1'){
		button = button1;//11-07 费谦修改（提交之后不能修改，只有审核不通过可以修改）
	}else
	//安委会办公室的人，在状态是21，可以进行审核操作  （转接审核）
	if(((rec.rectificationState=="21"))&&'${userType}'=='1'){
		button = button6;//2016-1-22 费谦修改（转接审核） 
	}else
	//安委会职能部门的人，在状态是4的时候，可以进行查看和上报整改信息操作
	if(rec.rectificationState=="4"&&'${userType}'=='6'&&rec.dealDeptId=='${deptId}'){
			button = button5;
	}else
	
	//安监中队队长，在状态是2的时候，可以进行查看和审核操作 20转接相同
	if((rec.rectificationState=="2"||rec.rectificationState=="20")&&'${userType}'=='3'){
		button = button6;
	}else
	
	//网格管理员，在状态是6的时候，可以进行查看和上报整改信息操作
	if(rec.rectificationState=="6"&&'${userType}'=='2'){
		//if("整改未通过"==rec.auditResult&&"${ids}"==rec.userId){
		if("整改未通过"==rec.auditResult){
			button = button8;
		}else{
			button = button5;
		}
	}else
	
	//网格管理员，在状态是3的时候，可以进行审核信息操作
	if(rec.rectificationState=="3"&&'${userType}'=='2'){
		button = button6;
				
	}else
	
	//监察大队队长，在状态是5的时候，可以进行查看和审核信息操作
	if(rec.rectificationState=="5"&&'${userType}'=='4'){
		if(rec.troubleLevel=='重大'){
			button = button6;
		}else if(rec.troubleLevel=='特别重大'&&rec.ifCorrected=='0'){
			button = button6;
		}else{
			button = button1;	
		}
	}else
	
	//局领导，在状态是7的时候，可以进行查看和审核信息操作
	if(rec.rectificationState=="7"&&'${userType}'=='5'){
		if(rec.troubleLevel=='特别重大'){
			button = button6;
		}else{
			button = button1;	
		}
		button = button6;
	}
	else{
		button = button1;	
	}
	if("${ids}"==rec.createUserID)
	{
		button = button+button9;
	}
	
	if(rec.dealState=='整改未完成'&&'${roleName}'=='1'&&rec.ifla != '1')
	{
		button = button+button10;
	}
	return button;
	}}
				        ]],
				toolbar:[{
					id:'btnadd',
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
						addNew();
					}
				}]
			}));
		});
		function deal(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_troMan","修改隐患表","${ctx}/jsp/yhb/troManInitEditAJ.action?flag=mod&troMan.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
        function report(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_troMan","上报整改信息","${ctx}/jsp/yhb/troManInitEditAJ.action?flag=mod&troMan.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
        
        function check(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_troMan","审核隐患信息","${ctx}/jsp/yhb/troManAuditAJ.action?flag=check&troMan.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
        
        function exprtXls(){
        	var rows = $('#pagination').datagrid('getSelections');
        	var ids = "";
			for(var i=0;i<rows.length;i++){
				ids += rows[i].id+"|";
			}
			var data = "&troMan.companyName="+$("#companyName").val();
			data = data + "&troMan.userName=" + $("#userName").val();
			data = data + "&troMan.troubleName=" + $("#troubleName").val();
			data = data + "&troMan.troubleSource=" + $("#troubleSource").val();
			data = data + "&troMan.areaId=" + $("#areaId").val();
			data = data + "&troMan.troubleLevel=" + $("#troubleLevel").val();
			data = data + "&troMan.troubleSort=" + $("#troubleSort").val();
			data = data + "&troMan.rectificationState=" + $("#rectificationState").val();
			window.open("${ctx}/jsp/yhb/troManExportXls.action?flag=aj&ids="+ids+data);
        }
        
        
    </script>
</head>
<body>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
			<div class="cell boxBmargin12">
			<form name="myform1" method="post" enctype="multipart/form-data" action="troManExportXls.action">
			<input type="hidden" name="flag" value="aj"/>
			<input type="hidden" name="ids" id="ids" value=""/>
			<table width="100%">
			<tr>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="troMan.companyName" id="companyName" value="${troMan.companyName}" type="text" maxlength="127" style="width:50%"></td>
				<th width="15%">上报人员名称</th>
				<td width="35%"><input name="troMan.userName" id="userName" value="${troMan.userName}" type="text" maxlength="127" style="width:50%"></td>
			</tr>
			<tr>
				<th width="15%">隐患名称</th>
				<td width="35%"><input name="troMan.troubleName" id="troubleName" value="${troMan.troubleName}" type="text" maxlength="127" style="width:50%"></td>
				<th width="15%">隐患来源</th>
				<td width="35%"><s:select name="troMan.troubleSource" cssStyle="width:50%;"  id="troubleSource" list="#{'':'请选择','企业':'企业','安监':'安监'}" theme="simple"/></td>
			</tr>
			<tr>
				<th width="15%">所在区域</th>
				<td width="35%">
					<cus:SelectOneTag property="troMan.areaId" defaultText='请选择' codeName="企业属地" value="${troMan.areaId}" style="width:50%"/>
				</td>
				<th width="15%">隐患级别</th>
				<td width="35%"><cus:SelectOneTag  property="troMan.troubleLevel" defaultText='请选择' codeName="隐患级别" value="${troMan.troubleLevel}" style="width:50%"/></td>
			</tr>
			<tr>
				<th width="15%">隐患类别</th>
				<td width="35%"><cus:SelectOneTag  property="troMan.troubleSort" defaultText='请选择' codeName="隐患类别" value="${troMan.troubleSort}" style="width:50%"/></td>
				<th width="15%">整改状态</th>
				<td width="35%">
					<s:select name="troMan.rectificationState" cssStyle="width:50%;"  id="rectificationState" list="#{'':'请选择','待审核':'待审核','审核未通过':'审核未通过','待整改':'待整改','整改未完成':'整改未完成','整改完成':'整改完成'}" theme="simple"/>
				</td>
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

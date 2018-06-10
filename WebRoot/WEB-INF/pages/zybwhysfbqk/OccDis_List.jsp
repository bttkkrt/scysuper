<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>职业病危害因素分布情况管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
	
        function addNew(){
        	var occHazBasId=$("#occHazBasId").val();
	        var dt=new Date();
            //window.parent.addTab("win_occDis","添加职业病危害因素分布情况","/jsp/zybwhys/occDisIndList.action?flag=add&occHazBas.id="+occHazBasId+"&dt="+dt.getTime());
        	createSimpleWindow("win_occDis","添加职业病危害因素分布情况","${ctx}/jsp/zybwhysfbqk/occDisInitEdit.action?flag=add&occHazBas.id="+occHazBasId+"&dt="+dt.getTime(),700,300);
        }
        
        function editJcry(row_Id){
	        var dt=new Date();
            window.parent.addTab("win_occDis","编辑接触人员","/jsp/zybwhys/occDisIndList.action?flag=add&occDis.id="+row_Id+"&dt="+dt.getTime());
      
        }
        
        function edit(row_Id){
       	 	var occHazBasId=$("#occHazBasId").val();
        	var dt=new Date();
            createSimpleWindow("win_occDis","修改职业病危害因素分布情况","${ctx}/jsp/zybwhysfbqk/occDisInitEdit.action?flag=mod&occDis.id="+row_Id+"&occHazBas.id="+occHazBasId+"&dt="+dt.getTime(),700,300);
        	
        }
        function view(row_Id){
        	var occHazBasId=$("#occHazBasId").val();
        	var dt=new Date();
            createSimpleWindow("win_occDis","查看职业病危害因素分布情况","${ctx}/jsp/zybwhysfbqk/occDisView.action?occDis.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_occDis();
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
		                	url : "occDisDel.action",
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
		                        	search_occDis();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_occDis(){
        	var queryParams = {
				"occHazBas.id": $("#occHazBasId").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'职业病危害因素分布情况列表',
				url:'occDisQuery.action',
				queryParams:{
					"occHazBas.id": $("#occHazBasId").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
						  {field:'workPlace',title:'作业场所名称',width:100},
						  {field:'contactNum',title:'接触人数（不可重复）',width:100},
				          {field:'op',title:'操作',width:100,formatter:function(value,rec){return "<a class='btn_02_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>&nbsp;<a class='btn_03_mini' onclick=editJcry('"+rec.id+"')>编辑接触人员<b></b></a>";}}
				         //{field:'op',title:'操作',width:100,formatter:function(value,rec){return "<a class='btn_01_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_01_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>&nbsp;<a class='btn_01_mini' onclick=editJcry('"+rec.id+"')>编辑接触人员<b></b></a>";}}
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

        function sub(){
        	document.myform1.submit();
        }
    </script>
</head>
<body>
 <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
 <div class="box_01 boxBmargin12 submitdata">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="../zywhjbysqk/occHazBasSave.action">
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="occHazBas.id" value="${occHazBas.id}" id="occHazBasId">
		<input type="hidden" name="occHazBas.createTime" value="<fmt:formatDate type="both" value="${occHazBas.createTime}" />">
		<input type="hidden" name="occHazBas.updateTime" value="${occHazBas.updateTime}">
		<input type="hidden" name="occHazBas.createUserID" value="${occHazBas.createUserID}">
		<input type="hidden" name="occHazBas.updateUserID" value="${occHazBas.updateUserID}">
		<input type="hidden" name="occHazBas.deptId" value="${occHazBas.deptId}">
		<input type="hidden" name="occHazBas.delFlag" value="${occHazBas.delFlag}">
		<input type="hidden" name="occHazBas.areaId" value="${occHazBas.areaId}">
		<input type="hidden" name="occHazBas.companyId" value="${occHazBas.companyId}">
		<input type="hidden" name="occHazBas.companyName" value="${occHazBas.companyName}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">粉尘类</th>
					<td width="35%"><cus:SelectOneTag style="width:60%"  property="occHazBas.ifDust" defaultText='请选择' codeName="有或无" value="${occHazBas.ifDust}" /></td>
					<th width="15%">粉尘类接触人数</th>
					<td width="35%"><input name="occHazBas.dustContactNumber"  style="width:60%"  value="${occHazBas.dustContactNumber}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">化学物质类</th>
					<td width="35%"><cus:SelectOneTag style="width:60%"  property="occHazBas.ifChemical" defaultText='请选择' codeName="有或无" value="${occHazBas.ifChemical}" /></td>
					<th width="15%">化学物质类接触人数</th>
					<td width="35%"><input name="occHazBas.chemicalContactNumber" style="width:60%"   value="${occHazBas.chemicalContactNumber}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">物理因素类</th>
					<td width="35%"><cus:SelectOneTag style="width:60%"  property="occHazBas.ifPhysical" defaultText='请选择' codeName="有或无" value="${occHazBas.ifPhysical}" /></td>
					<th width="15%">物理因素类接触人数</th>
					<td width="35%"><input name="occHazBas.physicalContactNumber"  style="width:60%"  value="${occHazBas.physicalContactNumber}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">放射性物质类</th>
					<td width="35%"><cus:SelectOneTag style="width:60%"  property="occHazBas.ifRadioactivity" defaultText='请选择' codeName="有或无" value="${occHazBas.ifRadioactivity}" /></td>
					<th width="15%">放射性物质类接触人数</th>
					<td width="35%"><input name="occHazBas.radioactivityContactNumber" style="width:60%"   value="${occHazBas.radioactivityContactNumber}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">其他</th>
					<td width="35%"><cus:SelectOneTag style="width:60%"  property="occHazBas.ifOther" defaultText='请选择' codeName="有或无" value="${occHazBas.ifOther}" /></td>
					<th width="15%">其他接触人数</th>
					<td width="35%"><input name="occHazBas.otherContactNumber" style="width:60%"   value="${occHazBas.otherContactNumber}" type="text" maxlength="127"></td>
				</tr>
				<!-- <tr>
					<th width="15%">接触职业病危害总人数</th>
					<td width="35%"><input name="occHazBas.totalNumber" style="width:60%" value="${occHazBas.totalNumber}" type="text" maxlength="127"></td>
					<th width="15%">合计</th>
					<td width="35%"><input name="occHazBas.total" style="width:60%"  value="${occHazBas.total}" type="text" maxlength="127"></td>
				</tr> -->
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="button" onclick="sub();" >保存<b></b></a>&nbsp;
						</s:else>						
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>

   
			<div class="cell boxBmargin12" style="display:none">
			<table width="100%">
				<tr>
					
				</tr>
				<tr >
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_occDis()" >查询<b></b></a>&nbsp;
						<a href="###" class="btn_01" onclick="clear_form(document.myform);" >清空<b></b></a>&nbsp;				
					</td>
				</tr>
			</table>
		</div>
		<div id="pagination" style="margin-top:1px;" >
		</div>
		</div>
		</div>
	</div>
</body>
</html>

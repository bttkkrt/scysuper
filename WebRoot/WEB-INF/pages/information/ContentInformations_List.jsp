<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>通知公告管理</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
        function addNew(){
        	createSimpleWindow("infoWindow","添加通知公告","${ctx}/jsp/information/contentInformationsInitEdit.action?flag=add", 1050, 550);
        }
        function edit(row_Id){
        	createSimpleWindow("infoWindow","修改通知公告","${ctx}/jsp/information/contentInformationsInitEdit.action?flag=mod&contentInformations.id="+row_Id, 1050, 550);
        }
        function view(row_Id){
        	createSimpleWindow("infoWindow","查看通知公告","${ctx}/jsp/information/contentInformationsView.action?contentInformations.id="+row_Id, 1050, 550);
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
		                	url : "contentInformationsDel.action",
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
		                        	search_contentInformations();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_contentInformations(){
        	var queryParams = {
					"contentInformations.infoTitle": $("#infoTitle").val(),
					"contentInformations.infoType": $("#infoType").val(),
					"isDelshow":$("#isDelshow").val(),
					"isExpireshow":$("#isExpireshow").val(),
					"contentInformations.topFlag": $("#topFlag").val(),
					"username": $("#username").val(),
					"queryPublicDateStart" :$("#queryPublicDateStart").val(),
					"queryPublicDateEnd" :$("#queryPublicDateEnd").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        $(function(){
           var toolbar = [];
       	 var frozen=[];
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
				frozen=[[
				    {field:'id',width:20,formatter:function(value,rec){
				    		var opt = '<input type="checkbox" name="xxx" value='+rec.id+'>'; 
						    if(rec.createUserID != "${sessionScope['LOGIN_USER_ID']}"){
								opt = ''; 
						    } 
						    return opt ; 

				    }}
				]];
			}
			$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'通知公告列表',
				url:'contentInformationsQuery.action',
				
				frozenColumns:frozen,
				columns:[[
				    {field:'infoTitle',title:'标题',width:0.1,formatter:function(value,rec){
			             if(rec.infoTitle.length>20){
			                var temp=rec.infoTitle.substr(0,20)+'...';
			             	return "<span title="+rec.infoTitle+">"+temp+"</span>";
			             }	
			             else{
			                var temp1=rec.infoTitle;
			              	return "<span title="+rec.infoTitle+">"+temp1+"</span>";
			             }  
				    }},
					{field:'infoType',title:'信息类型',width:0.1,formatter:function(value,rec){
					 	var temp = '';
					    $.ajax({
						    url: '${ctx}/jsp/admin/code/findCodeValue.action',
						    type: 'post',
						    dataType: 'json',
						    async : false,
						    data:{ 
						    	"codeValue.itemValue" : rec.infoType,
								"codeValue.codeId" : "0464f1a63362fccd013362fe3a390004"
						    },
						    error: function(){
						        $.messager.alert('提示','获取一维代码错误！');
						    },
						    success: function(data){
						        temp = data.itemText;
						 	}
						});
					    return temp;
					}},
					{field:'userId',title:'发布人',width:0.1,formatter:function(value,rec){
						return rec.user.displayName;
					}},
					{field:'deptId',title:'所属部门',width:0.1,formatter:function(value,rec){
						return rec.dept.deptName;
					}},
					{field:'publicDate',title:'发布日期',width:0.2,formatter:function(value,rec){
						if(rec.publicDate==null) return;
						var date = new Date(rec.publicDate.time);
						var retStr = date.format("yyyy-MM-dd hh:mm:ss");
						return retStr;						
					}},
		            {field:'op',title:'操作',width:0.2,formatter:function(value,rec){
		            
                       var button1="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
var button2="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
if("${sessionScope['LOGIN_USER_ID']}"==rec.createUserID){
	return  button1;
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
					<form name="myform" method="post">
						<div class="cell boxBmargin12">
							<table>
								<tbody>
									<tr>
										<th width="15%">
											标题
										</th>
										<td width="35%">
											<input name="contentInformations.infoTitle" id="infoTitle"
												class="form_text" value="${contentInformations.infoTitle}"
												type="text" maxlength="127" style="width: 50%"  >
										</td>
										<th width="15%">
											信息类型
										</th>
										<td width="35%">
											<cus:SelectOneTag property="contentInformations.infoType"
												defaultText='请选择' codeName="信息类型"
												value="${contentInformations.infoType}"
												style="width: 50%"   />
										</td>
									</tr>
									<tr>
										<th width="15%">
											发布人
										</th>
										<td width="35%">
											<input name="username" id="username" value="${username}"
												class="form_text" type="text" maxlength="127" style="width: 50%"  >
										</td>
										<th width="15%">
											发布日期
										</th>
										<td width="35%">
											<input style="width:35%;" name="queryPublicDateStart" id="queryPublicDateStart"
												value="<fmt:formatDate type='both' value='${queryPublicDateStart}' />"
												type="text" class="Wdate"
												onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queryPublicDateEnd\')}'})">
											-
											<input style="width:35%;" name="queryPublicDateEnd" id="queryPublicDateEnd"
												value="<fmt:formatDate type='both' value='${queryPublicDateEnd}' />"
												type="text" class="Wdate"
												onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queryPublicDateStart\')}'})">
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<div class="btn_area_setc">
												<a href="###" class="btn_01"
													onclick="search_contentInformations()">查询<b></b> </a>
												<a href="###" class="btn_01"
													onclick="clear_form(document.myform);">清空<b></b> </a>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div id="pagination">
						</div>
					</form>
				</div>
				
			</div>
		</div>
		</div>
	</body>
</html>

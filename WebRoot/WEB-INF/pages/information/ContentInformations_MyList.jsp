<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>我发布的公告管理</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
        
        function edit(row_Id){
        	createSimpleWindow("infoWindow","修改通知公告","${ctx}/jsp/information/contentInformationsInitEdit.action?flag=mod&contentInformations.id="+row_Id, 1050, 550);

        	//location.href="${ctx}/jsp/information/contentInformationsInitEdit.action?flag=mod&contentInformations.id="+row_Id;
        }
        function view(row_Id){
        	createSimpleWindow("infoWindow","查看通知公告","${ctx}/jsp/information/contentInformationsView.action?contentInformations.id="+row_Id, 1050, 550);
        	//window.open("${ctx}/jsp/information/contentInformationsView.action?contentInformations.id="+row_Id,"查看信息内容");
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('reload'); 
        }
        
        function inactive_info(id){
        	$.messager.confirm('禁用信息', '确定要禁用该信息？', function(result){
				if (result){
	                $.ajax({
	                	url : "inactiveInfo.action",
	                	type: 'post',
	                    dataType: 'json',
	                    async : false,
	                    data:{ 
	                    	"contentInformations.id" : id
	                    },
	                    error: function(){
	                    	$.messager.alert('错误','禁用信息时出错！');
	                    },
	                    success: function(data){
	                        if(data.result){
	                        	$.messager.alert('提示','禁用信息成功！');
	                        	search_contentInformations();
	                        }else{
	                        	$.messager.alert('错误','禁用信息时出错！');
	                        }
	                    }
	                });					
				}
			});
        }
        
        function active_info(id){
        	$.messager.confirm('激活信息', '确定要激活该信息？', function(result){
				if (result){
	                $.ajax({
	                	url : "activeInfo.action",
	                	type: 'post',
	                    dataType: 'json',
	                    async : false,
	                    data:{ 
	                    	"contentInformations.id" : id
	                    },
	                    error: function(){
	                    	$.messager.alert('错误','激活信息时出错！');
	                    },
	                    success: function(data){
	                        if(data.result){
	                        	$.messager.alert('提示','激活信息成功！');
	                        	search_contentInformations();
	                        }else{
	                        	$.messager.alert('错误','激活信息时出错！');
	                        }
	                    }
	                });					
				}
			});
        }
        
        function expire_info(id){
        	$.messager.confirm('设置信息过期', '确定要设置该信息过期？', function(result){
				if (result){
	                $.ajax({
	                	url : "expireInfo.action",
	                	type: 'post',
	                    dataType: 'json',
	                    async : false,
	                    data:{ 
	                    	"contentInformations.id" : id
	                    },
	                    error: function(){
	                    	$.messager.alert('错误','设置信息过期时出错！');
	                    },
	                    success: function(data){
	                        if(data.result){
	                        	$.messager.alert('提示','设置信息过期成功！');
	                        	search_contentInformations();
	                        }else{
	                        	$.messager.alert('错误','设置信息过期时出错！');
	                        }
	                    }
	                });					
				}
			});
        }
        
        function inexpire_info(id){
        	$.messager.confirm('设置信息使用', '确定要设置该信息使用？', function(result){
				if (result){
	                $.ajax({
	                	url : "inexpireInfo.action",
	                	type: 'post',
	                    dataType: 'json',
	                    async : false,
	                    data:{ 
	                    	"contentInformations.id" : id
	                    },
	                    error: function(){
	                    	$.messager.alert('错误','设置信息使用时出错！');
	                    },
	                    success: function(data){
	                        if(data.result){
	                        	$.messager.alert('提示','设置信息使用成功！');
	                        	search_contentInformations();
	                        }else{
	                        	$.messager.alert('错误','设置信息使用时出错！');
	                        }
	                    }
	                });					
				}
			});
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
        
        //清除查询表单中的搜索条件
        function clear_form(ff){
            var elements = ff.elements;
            for(i=0;i<elements.length;i++){
                var element = elements[i];
                if(element.type=="text"){
                    element.value = "";
                }else if(element.type=="radio" || element.type=="checkbox"){
                	element.checked = false;
                }else if(element.options!=null){
                	element.options[0].selected  = true;
                }
            }
        }
        
        function search_contentInformations(){
        	var queryParams = {
					"contentInformations.infoTitle": $("#infoTitle").val(),
					"contentInformations.infoType": $("#infoType").val(),
					"isDelshow":$("#isDelshow").val(),
					"isExpireshow":$("#isExpireshow").val(),
					"queryPublicDateStart" :$("#queryPublicDateStart").val(),
					"queryPublicDateEnd" :$("#queryPublicDateEnd").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'信息内容列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'contentInformationsQuerys.action',
				queryParams:{
					"contentInformations.infoTitle": $("#infoTitle").val(),
					"contentInformations.infoType": $("#infoType").val(),
					"isDelshow":$("#isDelshow").val(),
					"isExpireshow":$("#isExpireshow").val(),
					"queryPublicDateStart" :$("#queryPublicDateStart").val(),
					"queryPublicDateEnd" :$("#queryPublicDateEnd").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				    {field:'infoTitle',title:'标题',width:fixWidth(0.25),formatter:function(value,rec){
			             if(rec.infoTitle.length>20){
			                var temp=rec.infoTitle.substr(0,20)+'...';
			             	return "<span title="+rec.infoTitle+">"+temp+"</span>";
			             }	
			             else{
			                var temp1=rec.infoTitle;
			              	return "<span title="+rec.infoTitle+">"+temp1+"</span>";
			             }  
				    }},
					{field:'infoType',title:'信息类型',width:fixWidth(0.1),formatter:function(value,rec){
					 	var temp = '';
					    $.ajax({
						    url: '${ctx}/jsp/admin/code/findCodeValue.action',
						    type: 'post',
						    dataType: 'json',
						    async : false,
						    data:{ "codeValue.itemValue" : rec.infoType,
						           "codeValue.codeId" : "0464f1a63362fccd013362fe3a390004"},
						    error: function(){
						        $.messager.alert('提示','获取一维代码错误！');
						    },
						    success: function(data){
						        temp = data.itemText;
						 }});
					    return temp;}
					},
					{field:'time',title:'发布日期',width:fixWidth(0.15)},
					{field:'realnum',title:'已读人数',width:fixWidth(0.1)},
					{field:'viewnum',title:'查看用户',width:fixWidth(0.1),formatter:function(value,rec){
                        var restr="指定" + value + "人";
                        return restr;
                    }},
		            {field:'op',title:'操作',width:fixWidth(0.2),formatter:function(value,rec){
                        var restr="<span style='color:red' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red' onclick=\"edit('"+rec.id+"')\">修改</span>";
                        if(rec.delFlag=='0')
                        	restr+="&nbsp;<span style='color:red' onclick=\"inactive_info('"+rec.id+"')\">禁用</span>";
                        else
                        	restr+="&nbsp;<span style='color:red' onclick=\"active_info('"+rec.id+"')\">激活</span>";
                        if(rec.expireFlag=='0')
                        	restr+="&nbsp;<span style='color:red' onclick=\"expire_info('"+rec.id+"')\">设置过期</span>";
                        else
                        	restr+="&nbsp;<span style='color:red' onclick=\"inexpire_info('"+rec.id+"')\">设置使用</span>";
                        return restr;
                    }}
				]],
				pagination:true,
				rownumbers:true,
				pageList:[10,20,30],
				onLoadSuccess:tabOnloadSuccess,
				onLoadError:tabOnloadSuccess,
				onHeaderContextMenu: function(e, field){
					e.preventDefault();
					if (!$('#tmenu').length){
						createColumnMenu();
					}
					$('#tmenu').menu('show', {
						left:e.pageX,
						top:e.pageY
					});
				}
			});
		});

        var titles = new Array();
        function createColumnMenu(){
			var tmenu = $('<div id="tmenu" style="width:150px;"></div>').appendTo('body');
			var fields = $('#pagination').datagrid('getColumnFields');
			
			for(var i=0; i<fields.length; i++){
				var option = $('#pagination').datagrid('getColumnOption',fields[i]);
				var obj = {};
				obj.title = option.title;
				obj.field = fields[i];
				titles[i] = obj;
			}			
			for(var i=0; i<titles.length; i++){
				$('<div iconCls="icon-ok"/>').html(titles[i].title).appendTo(tmenu);
			}
			tmenu.menu({
				onClick: function(item){
					if (item.iconCls=='icon-ok'){
						var field;
						for(var i=0; i<titles.length; i++){
							if(titles[i].title==item.text){
								field = titles[i].field;
							}
						}
						$('#pagination').datagrid('hideColumn', field);
						tmenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-empty'
						});
					} else {
						var field;
						for(var i=0; i<titles.length; i++){
							if(titles[i].title==item.text){
								field = titles[i].field;
							}
						}
						$('#pagination').datagrid('showColumn', field);
						tmenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-ok'
						});
					}
				}
			});
		}
    </script>
</head>

<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>

<form name="myform" method="post">
	<div class="submitdata">
		<table width="100%">
			<tr>
				<th width="15%">标题</th>
				<td width="35%"><input name="contentInformations.infoTitle" id="infoTitle" value="${contentInformations.infoTitle}" type="text"></td>
				<th width="15%">信息类型</th>
				<td width="35%"><cus:SelectOneTag property="contentInformations.infoType" defaultText='请选择' codeName="信息类型" value="${contentInformations.infoType}" style="width: 136px;"/></td>
			</tr>
			<tr>
				<th width="15%">是否显示禁用信息</th>
				<td width="35%">
				   <select id="isDelshow" onchange="" name="isDelshow" style="width: 136px;">
                      <option value="0" <c:if test="${isDelshow==0}">selected</c:if>>否</option>
                      <option value="1" <c:if test="${isDelshow==1}">selected</c:if>>是</option>
                   </select>
				</td>
				<th width="15%">是否显示过期信息</th>
				<td width="35%">
				   <select id="isExpireshow" onchange="" name="isExpireshow" style="width: 136px;">
                      <option value="0" <c:if test="${isExpireshow==0}">selected</c:if>>否</option>
                      <option value="1" <c:if test="${isExpireshow==1}">selected</c:if>>是</option>
                   </select>
				</td>
			</tr>
			<tr>
				<th width="15%">发布日期</th>
				<td width="35%"><input name="queryPublicDateStart" id="queryPublicDateStart" value="<fmt:formatDate type='both' value='${queryPublicDateStart}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queryPublicDateEnd\')}'})" >
					-<input name="queryPublicDateEnd" id="queryPublicDateEnd" value="<fmt:formatDate type='both' value='${queryPublicDateEnd}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queryPublicDateStart\')}'})" ></td>
			</tr>
			<tr>
				<td colspan="4" style="text-align:center">
					<div align="center">
						<a href="###" class="easyui-linkbutton" onclick="search_contentInformations()" iconCls="icon-search">查询</a>&nbsp;
						<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
						<a href="###" class="easyui-linkbutton" onclick="del();" iconCls="icon-remove">删除</a>
					</div>
				</td>
			</tr>
		</table>
	</div>
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr>
				<td>
				<div id="pagination" style="background:#efefef;border:1px solid #ccc;">
				
				</div>
				</td>
			</tr>
		</table>
</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

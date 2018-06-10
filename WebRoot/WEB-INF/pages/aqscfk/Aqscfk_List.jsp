<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>行政处罚上报管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        var flag = "${flag}";
        var createUserID = "${createUserID}";
        function addNew(){
        	window.open("${ctx}/jsp/aqscfk/aqscfkInitEdit.action?flag=add");
        }
        function edit(row_Id){
        	window.open("${ctx}/jsp/aqscfk/aqscfkInitEdit.action?flag=mod&aqscfk.id="+row_Id);
        }
        function view(row_Id){
        	window.open("${ctx}/jsp/aqscfk/aqscfkView.action?aqscfk.id="+row_Id);
        }
        function shenhe(row_Id){
        	window.open("${ctx}/jsp/aqscfk/aqscfkShenhe.action?aqscfk.id="+row_Id);
        }
        function exportdata()
        {
        	document.myform.action = "${ctx}/jsp/aqscfk/aqscfkExport.action";
        	document.myform.submit();
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_aqscfk();
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
			if(rows.length<1){
			    $.messager.alert('提示','至少选择一项删除！');
			}else{
			    $.messager.confirm("删除","确定要删除吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "aqscfkDel.action",
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
		                        	search_aqscfk();
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
        
        function search_aqscfk(){
        	var queryParams = {
				"aqscfk.szzid": $("#szzid").val(),
"aqscfk.qymc": $("#qymc").val(),
"aqscfk.fkfl": $("#fkfl").val(),
 "queryJasjStart" :$("#queryJasjStart").val(),
 "queryJasjEnd" :$("#queryJasjEnd").val(),
 "aqscfk.zfwsh":$("#zfwsh").val(),
 "aqscfk.sgfl":$("#sgfl").val(),
 "aqscfk.state":$("#state").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'行政处罚上报列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'aqscfkQuery.action',
				queryParams:{
					"aqscfk.szzid": $("#szzid").val(),
"aqscfk.qymc": $("#qymc").val(),
"aqscfk.fkfl": $("#fkfl").val(),
 "queryJasjStart" :$("#queryJasjStart").val(),
 "queryJasjEnd" :$("#queryJasjEnd").val(),
 "aqscfk.zfwsh":$("#zfwsh").val(),
 "aqscfk.sgfl":$("#sgfl").val(),
 "aqscfk.state":$("#state").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',width:30,formatter:function(value,rec){
				    		var opt = '<input type="checkbox" name="xxx" value='+rec.id+'>'; 
						    if(rec.createUserID != createUserID){//这里判断是否是自己创建
								opt = ''; 
						    } 
						    return opt ; 
				    }}
				]],
				columns:[[
				          {field:'szzname',title:'所在镇',width:fixWidth(0.1)},
{field:'qymc',title:'企业名称',width:fixWidth(0.2)},
{field:'zfwsh',title:'执法文书号',width:fixWidth(0.1)},
{field:'cfje',title:'处罚金额（万元）',width:fixWidth(0.1)},
{field:'fkfl',title:'罚款分类',width:fixWidth(0.1),formatter:function(value,rec){
	if(rec.fkfl == '0')
	{
		return "事故处罚";
	}
	else
	{
		return "监督监察处罚";
	}
}}
,
{field:'jasj',title:'结案时间',width:fixWidth(0.1),formatter:function(value,rec){
if(rec.jasj==null) return;
var date = new Date(rec.jasj.time);
var month = parseInt(date.getMonth()+1);
return date.getFullYear()+'年'+month+'月'+date.getDate()+'日  ';}}
,
{field:'state',title:'审核状态',width:fixWidth(0.1),formatter:function(value,rec){
    if(rec.state == '1'){
	    return "<span style='color:green;cursor:hand'>待审核</span>";
	}
	else if(rec.state == '2'){
	    return "<span style='color:red;cursor:hand'>审核不通过</span>";
	}
	else
	{
		return "<span style='color:blue;cursor:hand'>审核通过</span>";
	}
}
},
				          {field:'op',title:'操作',width:fixWidth(0.15),formatter:function(value,rec){
	                           /**
	                           var str="";
					        	  $.ajax({
					        		    url: '${ctx}/jsp/admin/role/buttons.action',
					        		    type: 'post',
					        		    dataType: 'json',
					        		    async : false,
					        		    data:{    
					        		    	"function":"",
					        		    	"num":"8", 
					        		    	"moduleId":"${currModuleCode}",
					        		    	"functionName":"查看" 
					        		    	},
					        		    error: function(){
					        		        $.messager.alert('提示','获取一维代码错误！');
					        		    },
					        		    success: function(data){
					        		    	if(""!=data.result){
					        		        str+= "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;";
					        		    	}
					        		    }});
					        	  $.ajax({
					        		    url: '${ctx}/jsp/admin/role/buttons.action',
					        		    type: 'post',
					        		    dataType: 'json',
					        		    async : false,
					        		    data:{    
					        		    	"function":"",
					        		    	"num":"3", 
					        		    	"moduleId":"${currModuleCode}",
					        		    	"functionName":"修改" 
					        		    	},
					        		    error: function(){
					        		        $.messager.alert('提示','获取一维代码错误！');
					        		    },
					        		    success: function(data){
					        		    	if(""!=data.result){
					        		        str+= "<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>";
					        		    	}
					        		    }});
				        		    $.ajax({
					        		    url: '${ctx}/jsp/admin/role/buttons.action',
					        		    type: 'post',
					        		    dataType: 'json',
					        		    async : false,
					        		    data:{    
					        		    	"function":"",
					        		    	"num":"7", 
					        		    	"moduleId":"${currModuleCode}",
					        		    	"functionName":"审核" 
					        		    	},
					        		    error: function(){
					        		        $.messager.alert('提示','获取一维代码错误！');
					        		    },
					        		    success: function(data){
					        		    	if(""!=data.result){
					        		        str+= "<span style='color:red;cursor:hand' onclick=\"shenhe('"+rec.id+"')\">审核</span>";
					        		    	}
					        		    }});
					        	 return str;
	                           **/
	                            if(rec.createUserID == createUserID && (rec.state == 1 || rec.state == 2))
				          		{
				          			 return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>";
				          		}
				          		else if(rec.state == 1 && flag == 1)
				          		{
				          			return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"shenhe('"+rec.id+"')\">审核</span>";
				          		}
				          		else
				          		{
				          			 return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>";
				          		}
                          }}
				        ]],
				pagination:true,
				onLoadSuccess:tabOnloadSuccess,
				onLoadError:tabOnloadSuccess,
				rownumbers:true,
				pageList:[10,20,30],
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
		$(window).resize(function(){
            $('#pagination').datagrid('resize',{
            	width: document.body.clientWidth-20
            });
        });
    </script>
</head>

<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>

<form name="myform" method="post">
<input type="hidden" name="flag" value="1"/>
	<div class="submitdata">
		<table width="100%">
			<tr>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="aqscfk.qymc" id="qymc" value="${aqscfk.qymc}" type="text"></td>
				<th width="15%">结案时间</th>
				<td width="35%"><input name="queryJasjStart" id="queryJasjStart" value="<fmt:formatDate type='date' value='${queryJasjStart}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryJasjEnd\')}'})" >
					-<input name="queryJasjEnd" id="queryJasjEnd" value="<fmt:formatDate type='date' value='${queryJasjEnd}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryJasjStart\')}'})" ></td>
			</tr>
			<tr>
				<th width="15%">罚款分类</th>
				<td width="35%">
					<s:select id="fkfl" listKey="key" listValue="value"  theme="simple" list="#{'':' ','0':'事故处罚','1':'监督监察处罚'}" name="aqscfk.fkfl" value="{aqscfk.fkfl}"/>	
				</td>
				<th width="15%">执法文书号</th>
				<td width="35%"><input name="aqscfk.zfwsh" id="zfwsh" value="${aqscfk.zfwsh}" type="text"></td>
			</tr>
			<tr>	
				<th width="15%">审核状态</th>
				<td width="35%"><s:select id="state" listKey="key" listValue="value"  theme="simple" list="#{'':' ','1':'待审核','2':'审核不通过','3':'审核通过'}" name="aqscfk.state" value="{aqscfk.state}"/></td>
				<s:if test="flag != 3">
					<th width="15%">所在镇</th>
					<td width="35%"><cus:SelectOneTag property="aqscfk.szzid" defaultText='请选择' codeName="相城地址" value="${aqscfk.szzid}" /></td>
				</s:if>
			</tr>
			<tr>
			</tr>
			<tr>
				<td colspan="4" style="text-align:center">
				
				<a href="###" class="easyui-linkbutton" onclick="search_aqscfk()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				<s:if test="flag == 2">
				<a href="###" class="easyui-linkbutton" onclick="addNew();" iconCls="icon-add">添加</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="del();" iconCls="icon-remove">删除</a>
				</s:if>
				<a href="###" class="easyui-linkbutton" onclick="exportdata();" iconCls="icon-add">导出</a>&nbsp;
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

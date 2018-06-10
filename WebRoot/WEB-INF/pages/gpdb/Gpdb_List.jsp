<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>挂牌督办管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        var flag = "${flag}";
        var createUserID = "${createUserID}";
        function addNew(type){
        	var dt=new Date();
        	window.open("${ctx}/jsp/gpdb/gpdbInitEdit.action?flag=add&dt="+dt.getTime()+"&tt="+type);
        }
        function edit(row_Id,type){
        	window.open("${ctx}/jsp/gpdb/gpdbInitEdit.action?flag=mod&gpdb.id="+row_Id+"&type="+type);
        }
        function edits(row_Id,type){
        	window.open("${ctx}/jsp/gpdb/gpdbInitEdit.action?flag=mod&gpdb.id="+row_Id+"&tt="+type);
        }
        function view(row_Id){
        	var dt=new Date();
        	window.open("${ctx}/jsp/gpdb/gpdbView.action?gpdb.id="+row_Id+"&dt="+dt.getTime());
        }
        function shenhe(row_Id){
        	window.open("${ctx}/jsp/gpdb/gpdbShenhe.action?gpdb.id="+row_Id);
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_gpdb();
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
		                	url : "gpdbDel.action",
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
		                        	search_gpdb();
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
        
        function search_gpdb(){
        	var queryParams = {
				"gpdb.szzid": $("#szzid").val(),
"gpdb.qymc": $("#qymc").val(),
"gpdb.yhmc": $("#yhmc").val(),
 "queryGpsjStart" :$("#queryGpsjStart").val(),
 "queryGpsjEnd" :$("#queryGpsjEnd").val(),
"gpdb.yhlb": $("#yhlb").val(),
 "queryZgwcsjStart" :$("#queryZgwcsjStart").val(),
 "queryZgwcsjEnd" :$("#queryZgwcsjEnd").val(),
 "queryYssjStart" :$("#queryYssjStart").val(),
 "queryYssjEnd" :$("#queryYssjEnd").val(),
"gpdb.state": $("#state").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'挂牌督办列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'gpdbQuery.action',
				queryParams:{
					"gpdb.szzid": $("#szzid").val(),
"gpdb.qymc": $("#qymc").val(),
"gpdb.yhmc": $("#yhmc").val(),
 "queryGpsjStart" :$("#queryGpsjStart").val(),
 "queryGpsjEnd" :$("#queryGpsjEnd").val(),
"gpdb.yhlb": $("#yhlb").val(),
 "queryZgwcsjStart" :$("#queryZgwcsjStart").val(),
 "queryZgwcsjEnd" :$("#queryZgwcsjEnd").val(),
 "queryYssjStart" :$("#queryYssjStart").val(),
 "queryYssjEnd" :$("#queryYssjEnd").val(),
"gpdb.state": $("#state").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'szzname',title:'所在镇',width:fixWidth(0.1)},
{field:'qymc',title:'企业名称',width:fixWidth(0.1)},
{field:'yhmc',title:'隐患名称',width:fixWidth(0.1)},
{field:'gpsj',title:'挂牌时间',width:fixWidth(0.1),formatter:function(value,rec){
if(rec.gpsj==null) return;
var date = new Date(rec.gpsj.time);
var month = parseInt(date.getMonth()+1);
return date.getFullYear()+'年'+month+'月'+date.getDate()+'日  ';}}
,
{field:'yhlb',title:'隐患类别',width:fixWidth(0.1),formatter:function(value,rec){
  var temp = '';
    $.ajax({
    url: '${ctx}/jsp/admin/code/findCodeValue.action',
    type: 'post',
    dataType: 'json',
    async : false,
    data:{        "codeValue.itemValue" : rec.yhlb,
        "codeValue.codeId" : "402880484101fccb01412eadbcb009ec"    },
    error: function(){
        $.messager.alert('提示','获取一维代码错误！');
    },
    success: function(data){
        temp = data.itemText;
    }});
    return temp;}
},
{field:'zgwcsj',title:'整改完成时间',width:fixWidth(0.1),formatter:function(value,rec){
if(rec.zgwcsj==null) return;
var date = new Date(rec.zgwcsj.time);
var month = parseInt(date.getMonth()+1);
return date.getFullYear()+'年'+month+'月'+date.getDate()+'日  ';}}
,
{field:'yssj',title:'验收时间',width:fixWidth(0.1),formatter:function(value,rec){
if(rec.yssj==null) return;
var date = new Date(rec.yssj.time);
var month = parseInt(date.getMonth()+1);
return date.getFullYear()+'年'+month+'月'+date.getDate()+'日  ';}}
,
{field:'username',title:'上报人',width:fixWidth(0.1)},
{field:'state',title:'状态',width:fixWidth(0.1),formatter:function(value,rec){
	if(rec.state == '0'){
	  	return "<span style='color:red;cursor:hand'>待整改</span>";
	}else if(rec.state == '1'){
	    return "<span style='color:green;cursor:hand'>已整改待审核</span>";
	}
	else if(rec.state == '2'){
	    return "<span style='color:yellow;cursor:hand'>审核不通过</span>";
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
									$.ajax({
										url: '${ctx}/jsp/admin/role/buttons.action',
										type: 'post',
										dataType: 'json',
										async : false,
										data:{    
											"function":"",
											"num":"10", 
											"moduleId":"${currModuleCode}",
											"functionName":"上报整改信息" 
											},
										error: function(){
											$.messager.alert('提示','获取一维代码错误！');
										},
										success: function(data){
											if(""!=data.result){
											str+= "<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"','1')\">上报整改信息</span>";
											}
										}});
								 return str;
								 **/
				          		if(rec.state == 0 && flag == 1 &&　createUserID == rec.createUserID)
				          		{
				          			return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"','0')\">修改</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"','1')\">上报整改信息</span>";
				          		}
				          		else if(rec.state == 2 && flag == 1 &&　createUserID == rec.createUserID)
				          		{
				          			return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"','1')\">上报整改信息</span>";
				          		}
				          		else if(rec.state == 1 && flag == 1 &&　createUserID == rec.createUserID)
				          		{
				          			return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"','1')\">修改</span>";
				          		}
				          		else if(flag == 2 && rec.state == 1 && rec.yhlb == 2)
				          		{
				          			return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"shenhe('"+rec.id+"')\">审核</span>";
				          		}
	                            else if(flag == 3 && rec.state == 1 && rec.yhlb != 2)
	                            {
	                            	return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"shenhe('"+rec.id+"')\">审核</span>";
	                            }
	                            else if(flag == 3 && createUserID == rec.createUserID)
	                            {
	                            	return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"edits('"+rec.id+"','1')\">修改</span>";
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
		function querySzz(obj)
	    {
	    	$.ajax({
					type:"POST",
					url:"${ctx}/jsp/company/companyQuerySzz.action?mode=ajaxJson&company.county="+obj,
					success:function(json){
						json = eval('(' + json + ')');
						var selectContainer = $('#szzid'); 
						selectContainer.empty();
						var option = $('<option></option>').text("").val(""); 
						selectContainer.append(option); 
		  				for(var i=0; i<json.length; i++){
					    	option = $('<option></option>').text(json[i].name).val(json[i].id);
							selectContainer.append(option); 
					 	}
					},
					dateType:"json"
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
				<th width="15%">企业名称</th>
				<td width="35%"><input name="gpdb.qymc" id="qymc" value="${gpdb.qymc}" type="text"></td>
				<th width="15%">隐患名称</th>
				<td width="35%"><input name="gpdb.yhmc" id="yhmc" value="${gpdb.yhmc}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">挂牌时间</th>
				<td width="35%"><input name="queryGpsjStart" id="queryGpsjStart" value="<fmt:formatDate type='date' value='${queryGpsjStart}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryGpsjEnd\')}'})" >
					-<input name="queryGpsjEnd" id="queryGpsjEnd" value="<fmt:formatDate type='date' value='${queryGpsjEnd}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryGpsjStart\')}'})" ></td>
				<th width="15%">整改完成时间</th>
				<td width="35%"><input name="queryZgwcsjStart" id="queryZgwcsjStart" value="<fmt:formatDate type='date' value='${queryZgwcsjStart}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryZgwcsjEnd\')}'})" >
					-<input name="queryZgwcsjEnd" id="queryZgwcsjEnd" value="<fmt:formatDate type='date' value='${queryZgwcsjEnd}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryZgwcsjStart\')}'})" ></td>
			</tr>
			<tr>
				<th width="15%">验收时间</th>
				<td width="35%"><input name="queryYssjStart" id="queryYssjStart" value="<fmt:formatDate type='date' value='${queryYssjStart}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryYssjEnd\')}'})" >
					-<input name="queryYssjEnd" id="queryYssjEnd" value="<fmt:formatDate type='date' value='${queryYssjEnd}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryYssjStart\')}'})" ></td>
				<th width="15%">状态</th>
				<td width="35%">
					<s:select id="state" listKey="key" listValue="value"  theme="simple" list="#{'':' ','0':'待整改','1':'已整改待审核','2':'审核不通过','3':'审核通过'}" name="gpdb.state" value="{gpdb.state}"/>
				</td>
			</tr>
			<tr>
				<th width="15%">隐患类别</th>
				<td width="35%"><cus:SelectOneTag property="gpdb.yhlb" defaultText='请选择' codeName="隐患类别" value="${gpdb.yhlb}" /></td>
				<s:if test="%{flag==3 || flag == 0}">
				 	<th width="15%">所属地区</th>
					<td width="35%">
					<cus:SelectOneTag property="county" defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"  style="width:150px;" />
					<select id="szzid" name="gpdb.szzid" style="width:150px;" ><option value="">请选择</option></select>
						
					<!--hanxc 2014/11/11 		
					<cus:SelectOneTag property="gpdb.szzid" defaultText='请选择' codeName="相城地址" value="${gpdb.szzid}" /></td>
					 -->
				</s:if>
			</tr>
			<tr>
			</tr>
			<tr>
				<td colspan="4" style="text-align:center">
				 
				<a href="###" class="easyui-linkbutton" onclick="search_gpdb()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				<s:if test="flag == 1">
				<a href="###" class="easyui-linkbutton" onclick="addNew('0');" iconCls="icon-add">添加</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="del();" iconCls="icon-remove">删除</a>
				</s:if>
				<s:elseif test="flag == 3">
				<a href="###" class="easyui-linkbutton" onclick="addNew('1');" iconCls="icon-add">添加</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="del();" iconCls="icon-remove">删除</a>
				</s:elseif>
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

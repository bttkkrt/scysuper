<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>zjjtzsbs管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        var flag = "${flag}";
        var companyId = "${companyId}";
        function addNew(){
        	window.open("${ctx}/jsp/zjjtzsbs/zjjtzsbsInitEdit.action?flag=add");
        }
        function edit(row_Id){
        	window.open("${ctx}/jsp/zjjtzsbs/zjjtzsbsInitEdit.action?flag=mod&zjjtzsbs.id="+row_Id);
        }
        function view(row_Id){
        	window.open("${ctx}/jsp/zjjtzsbs/zjjtzsbsView.action?zjjtzsbs.id="+row_Id);
        }
        //图片上传 lj 2013-04-18
        function uploadFile(){
       		window.open("${ctx}/jsp/zjjtzsbs/zjjtzsbsUpload.action");
		}
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_zjjtzsbs();
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
		                	url : "zjjtzsbsDel.action",
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
		                        	search_zjjtzsbs();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        
        function dels(){
			    $.messager.confirm("删除","确定要全部删除吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "zjjtzsbsDels.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','删除时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	$.messager.alert('提示','删除成功！');
		                        	search_zjjtzsbs();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
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
        
        function search_zjjtzsbs(){
        	var queryParams = {
				"zjjtzsbs.qymc": $("#qymc").val(),
"zjjtzsbs.szzname": $("#szzname").val(),
"zjjtzsbs.zcdm": $("#zcdm").val(),
"zjjtzsbs.sbdah": $("#sbdah").val(),
"zjjtzsbs.ccbh": $("#ccbh").val(),
"queryDjrqStart": $("#queryDjrqStart").val(),
"queryDjrqEnd": $("#queryDjrqEnd").val(),
"queryCcrqStart": $("#queryCcrqStart").val(),
"queryCcrqEnd": $("#queryCcrqEnd").val(),
"queryJyrqStart": $("#queryJyrqStart").val(),
"queryJyrqEnd": $("#queryJyrqEnd").val(),
"queryXcjyrqStart": $("#queryXcjyrqStart").val(),
"queryXcjyrqEnd": $("#queryXcjyrqEnd").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'质监局特种设备列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'zjjtzsbsQuery.action',
				queryParams:{
					"zjjtzsbs.qymc": $("#qymc").val(),
"zjjtzsbs.szzname": $("#szzname").val(),
"zjjtzsbs.zcdm": $("#zcdm").val(),
"zjjtzsbs.sbdah": $("#sbdah").val(),
"zjjtzsbs.ccbh": $("#ccbh").val(),
"queryDjrqStart": $("#queryDjrqStart").val(),
"queryDjrqEnd": $("#queryDjrqEnd").val(),
"queryCcrqStart": $("#queryCcrqStart").val(),
"queryCcrqEnd": $("#queryCcrqEnd").val(),
"queryJyrqStart": $("#queryJyrqStart").val(),
"queryJyrqEnd": $("#queryJyrqEnd").val(),
"queryXcjyrqStart": $("#queryXcjyrqStart").val(),
"queryXcjyrqEnd": $("#queryXcjyrqEnd").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'qymc',title:'使用单位',width:fixWidth(0.15)},
{field:'szzname',title:'所属地区',width:fixWidth(0.08)},
{field:'zcdm',title:'注册代码',width:fixWidth(0.15)},
{field:'zcdjrq',title:'注册登记日期',width:fixWidth(0.1)},
{field:'sbdah',title:'设备档案号',width:fixWidth(0.1)},
{field:'sblb',title:'设备类别',width:fixWidth(0.1)},
{field:'xsblb',title:'新设备类别',width:fixWidth(0.1)},
{field:'ccbh',title:'出厂编号',width:fixWidth(0.1)},
{field:'jyrq',title:'检验日期',width:fixWidth(0.08)},
{field:'xcjyrq',title:'下次检验日期',width:fixWidth(0.08)},
				          {field:'op',title:'操作',width:fixWidth(0.1),formatter:function(value,rec){
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
					        	 return str;
					        	 **/
				          		if(flag == 1) //质监局
				          		{
				          			return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:blue;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>";
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
						var selectContainer = $('#szzname'); 
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
				<th width="15%">所在镇</th>
				<td width="35%">
					<cus:SelectOneTag property="county" defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"  style="width:150px;" />
					<select id="szzname" name="zjjtzsbs.szzname" style="width:150px;" ><option value="">请选择</option></select>
						
					<!--hanxc 2014/11/11 		
					
					<s:select id="szzname" listKey="key" listValue="value"  theme="simple" list="#{'':'请选择','高铁新城':'高铁新城','度假区':'度假区','开发区':'开发区','北桥街道':'北桥街道'
					,'黄桥街道':'黄桥街道','太平街道':'太平街道','元和街道':'元和街道','阳澄湖镇':'阳澄湖镇','渭塘镇':'渭塘镇','黄埭镇':'黄埭镇','望亭镇':'望亭镇'}" name="zjjtzsbs.szzname" value="{zjjtzsbs.szzname}"/>
					 -->
				</td>
				<th width="15%">使用单位</th>
				<td width="35%">
					<input id="qymc" name="zjjtzsbs.qymc" type="text" maxlength="255" value="${zjjtzsbs.qymc}">
				</td>
			</tr>
			<tr>
				<th width="15%">注册代码</th>
				<td width="35%"><input id="zcdm" name="zjjtzsbs.zcdm" value="${zjjtzsbs.zcdm}"  type="text" maxlength="255"></td>
				<th width="15%">注册登记日期</th>
				<td width="35%">
					<input name="queryDjrqStart" id="queryDjrqStart" value="${queryDjrqStart}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryDjrqEnd\')}'})" >
					-<input name="queryDjrqEnd" id="queryDjrqEnd" value="${queryDjrqEnd}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryDjrqStart\')}'})" >
				</td>
			</tr>
			<tr>
				<th width="15%">设备档案号</th>
				<td width="35%"><input id="sbdah" name="zjjtzsbs.sbdah" value="${zjjtzsbs.sbdah}" type="text" maxlength="255"></td>
				<th width="15%">出厂日期</th>
				<td width="35%">
					<input name="queryCcrqStart" id="queryCcrqStart" value="${queryCcrqStart}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryCcrqEnd\')}'})" >
					-<input name="queryCcrqEnd" id="queryCcrqEnd" value="${queryCcrqEnd}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryCcrqStart\')}'})" >
				</td>
			</tr>
			<tr>
				<th width="15%">出厂编号</th>
				<td width="35%"><input id="ccbh" name="zjjtzsbs.ccbh" value="${zjjtzsbs.ccbh}" type="text" maxlength="255"></td>
				<th width="15%">检验日期</th>
				<td width="35%">
					<input name="queryJyrqStart" id="queryJyrqStart" value="${queryJyrqStart}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryJyrqEnd\')}'})" >
					-<input name="queryJyrqEnd" id="queryJyrqEnd" value="${queryJyrqEnd}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryJyrqStart\')}'})" >
				</td>
			</tr>
			<tr>
				<th width="15%">下次检验日期</th>
				<td width="35%">
					<input name="queryXcjyrqStart" id="queryXcjyrqStart" value="${queryXcjyrqStart}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryXcjyrqEnd\')}'})" >
					-<input name="queryXcjyrqEnd" id="queryXcjyrqEnd" value="${queryXcjyrqEnd}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryXcjyrqStart\')}'})" >
				</td>
			</tr>
			<tr>
				<td colspan="4" style="text-align:center">
				
				<a href="###" class="easyui-linkbutton" onclick="search_zjjtzsbs()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				<s:if test="flag == 1">
					<a href="###" class="easyui-linkbutton" onclick="addNew();" iconCls="icon-add">添加</a>&nbsp;
					<a href="###" class="easyui-linkbutton" onclick="uploadFile();" iconCls="icon-add">导入</a>&nbsp;
					<a href="###" class="easyui-linkbutton" onclick="del();" iconCls="icon-remove">删除</a>
					<a href="###" class="easyui-linkbutton" onclick="dels();" iconCls="icon-remove">全部删除</a>
				</s:if>
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

<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>质监局特种设备管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        var flag = "${flag}";
        var companyId = "${companyId}";
        function addNew(){
        	window.open("${ctx}/jsp/zjjtzsb/zjjtzsbInitEdit.action?flag=add");
        }
        function edit(row_Id){
        	window.open("${ctx}/jsp/zjjtzsb/zjjtzsbInitEdit.action?flag=mod&zjjtzsb.id="+row_Id);
        }
        function view(row_Id){
        	window.open("${ctx}/jsp/zjjtzsb/zjjtzsbView.action?zjjtzsb.id="+row_Id);
        }
        function shenhe(row_Id){
        	window.open("${ctx}/jsp/zjjtzsb/zjjtzsbShenhe.action?zjjtzsb.id="+row_Id);
        }
        function baoyan(row_Id)
        {
        	$.messager.confirm("报验","确定已整改完成并报验吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "zjjtzsbBy.action?zjjtzsb.id=" + row_Id,
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','报验时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	$.messager.alert('提示','报验成功！');
		                        	search_zjjtzsb();
		                        }else{
		                        	$.messager.alert('错误','报验时出错！');
		                        }
		                    }
		                });
			        }
			    });
        }
        function shangbao(row_Id)
        {
        	window.open("${ctx}/jsp/zjjtzsb/zjjtzsbBj.action?zjjtzsb.id="+row_Id);
        }
        //图片上传 lj 2013-04-18
        function uploadFile(){
       		window.open("${ctx}/jsp/zjjtzsb/zjjtzsbUpload.action");
		}
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_zjjtzsb();
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
		                	url : "zjjtzsbDel.action",
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
		                        	search_zjjtzsb();
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
		                	url : "zjjtzsbDels.action",
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
		                        	search_zjjtzsb();
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
        
        function search_zjjtzsb(){
        	var queryParams = {
				"zjjtzsb.qymc": $("#qymc").val(),
"zjjtzsb.szzid": $("#szzid").val(),
"zjjtzsb.sblb": $("#sblb").val(),
"zjjtzsb.sbdah": $("#sbdah").val(),
"zjjtzsb.zcdm": $("#zcdm").val(),
"zjjtzsb.jcry": $("#jcry").val(),
"zjjtzsb.state": $("#state").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'质监局特种设备隐患列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'zjjtzsbQuery.action',
				queryParams:{
					"zjjtzsb.qymc": $("#qymc").val(),
"zjjtzsb.szzid": $("#szzid").val(),
"zjjtzsb.sblb": $("#sblb").val(),
"zjjtzsb.sbdah": $("#sbdah").val(),
"zjjtzsb.zcdm": $("#zcdm").val(),
"zjjtzsb.jcry": $("#jcry").val(),
"zjjtzsb.state": $("#state").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'qymc',title:'单位名称',width:fixWidth(0.1)},
{field:'szzname',title:'所属地区',width:fixWidth(0.1)},
{field:'sblb',title:'设备类别',width:fixWidth(0.1)},
{field:'sbdah',title:'设备档案号',width:fixWidth(0.1)},
{field:'zcdm',title:'注册代码',width:fixWidth(0.1)},
{field:'jyrq',title:'检验日期',width:fixWidth(0.1)},
{field:'jyjl',title:'检验结论',width:fixWidth(0.1)},
{field:'jcrq',title:'检查日期',width:fixWidth(0.1)},
{field:'jcry',title:'检查人员',width:fixWidth(0.1)},
{field:'state',title:'状态',width:fixWidth(0.1),formatter:function(value,rec){
if(value == "0")
	return "待整改";
else if(value == "1")
	return "待核实";
else if(value == "2")
	return "待审核";
else if(value == "3")
	return "已办结";
else
	return "整改不合格";
                          }},
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
											str+= "<span style='color:orange;cursor:hand' onclick=\"shenhe('"+rec.id+"')\">审核</span>";
											}
										}});
										str+= "<span style='color:green;cursor:hand' onclick=\"baoyan('"+rec.id+"')\">报验</span>";
										str+= "<span style='color:purple;cursor:hand' onclick=\"shangbao('"+rec.id+"')\">上报核实结果</span>";
								 return str;
								 **/
				          		if(rec.isFirst == "0" && flag == 1) //质监局
				          		{
				          			return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:blue;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>";
				          		}
				          		else if(rec.qyid == companyId && (rec.state == 0 || rec.state == 4) && rec.shbs == "0") //企业
				          		{
				          			return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:green;cursor:hand' onclick=\"baoyan('"+rec.id+"')\">报验</span>";
				          		}
				          		else if((rec.state == 1 || rec.state == 4) && rec.shbs == "1" && flag == 2) //安监中队
				          		{
				          			return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:purple;cursor:hand' onclick=\"shangbao('"+rec.id+"')\">上报核实结果</span>";
				          		}
				          		else if(rec.state == 2&& rec.shbs == "2" && flag == 1) //质监局
				          		{
				          			return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:orange;cursor:hand' onclick=\"shenhe('"+rec.id+"')\">审核</span>";
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
				<th width="15%">单位名称</th>
				<td width="35%"><input name="zjjtzsb.qymc" id="qymc" value="${zjjtzsb.qymc}" type="text"></td>
				<th width="15%">状态</th>
				<td width="35%">
					<s:select id="state" listKey="key" listValue="value"  theme="simple" list="#{'':' ','0':'待整改','1':'待核实','2':'待审核','3':'已办结','4':'整改不合格'}" name="zjjtzsb.state" value="{zjjtzsb.state}"/>
				</td>
			</tr>
			<tr>
				<th width="15%">设备类别</th>
				<td width="35%"><input name="zjjtzsb.sblb" id="sblb" value="${zjjtzsb.sblb}" type="text"></td>
				<th width="15%">设备档案号</th>
				<td width="35%"><input name="zjjtzsb.sbdah" id="sbdah" value="${zjjtzsb.sbdah}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">注册代码</th>
				<td width="35%"><input name="zjjtzsb.zcdm" id="zcdm" value="${zjjtzsb.zcdm}" type="text"></td>
				<th width="15%">检查人员</th>
				<td width="35%"><input name="zjjtzsb.jcry" id="jcry" value="${zjjtzsb.jcry}" type="text"></td>
			</tr>
			<s:if test="flag != 2">
			<tr>
				<th width="15%">所属地区</th>
				<td width="35%">
					<cus:SelectOneTag property="county" defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"  style="width:150px;" />
					<select id="szzid" name="zjjtzsb.szzid" style="width:150px;" ><option value="">请选择</option></select>
						
					<!--hanxc 2014/11/11 		
					<cus:SelectOneTag property="zjjtzsb.szzid" defaultText='请选择' codeName="相城地址" value="${zjjtzsb.szzid}" /></td>
					 -->
			</tr>
			</s:if>
			<tr>
				<td colspan="4" style="text-align:center">
				
				<a href="###" class="easyui-linkbutton" onclick="search_zjjtzsb()" iconCls="icon-search">查询</a>&nbsp;
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

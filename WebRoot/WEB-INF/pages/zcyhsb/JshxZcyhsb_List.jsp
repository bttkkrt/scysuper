<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>企业自查隐患管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        var flag = "${flag}";
        function addNew(){
        	var dt=new Date();
        	window.open("${ctx}/jsp/zcyhsb/jshxZcyhsbInitEdit.action?flag=add&dt="+dt.getTime());
        }
        function edit(row_Id){
        	var dt=new Date();
        	window.open("${ctx}/jsp/zcyhsb/jshxZcyhsbInitEdit.action?flag=mod&jshxZcyhsb.id="+row_Id+"&dt="+dt.getTime());
        }
        function view(row_Id){
        	var dt=new Date();
        	window.open("${ctx}/jsp/zcyhsb/jshxZcyhsbView.action?jshxZcyhsb.id="+row_Id+"&dt="+dt.getTime());
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_jshxZcyhsb();
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
		                	url : "jshxZcyhsbDel.action",
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
		                        	search_jshxZcyhsb();
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
        
        function search_jshxZcyhsb(){
        	var queryParams = {
        	"jshxZcyhsb.szzid": $("#areaName").val(),
				 "queryJcsjStart" :$("#queryJcsjStart").val(),
				 "queryJcsjEnd" :$("#queryJcsjEnd").val(),
				"jshxZcyhsb.jcry": $("#jcry").val(),
				 "queryJhwcsjStart" :$("#queryJhwcsjStart").val(),
				 "jshxZcyhsb.qymc" :$("#qymc").val(),
				 "jshxZcyhsb.mqzt": "${mqzt}",
				 "queryJhwcsjEnd" :$("#queryJhwcsjEnd").val(),
				 "jshxZcyhsb.ifzsqy": $("#ifzsqy").val(),
				 "jshxZcyhsb.qyid": $("#qyid").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'企业自查隐患上报列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'jshxZcyhsbQuery.action',
				queryParams:{
				"jshxZcyhsb.szzid": $("#szzid").val(),
					 "queryJcsjStart" :$("#queryJcsjStart").val(),
					 "queryJcsjEnd" :$("#queryJcsjEnd").val(),
					"jshxZcyhsb.jcry": $("#jcry").val(),
					"jshxZcyhsb.mqzt": "${mqzt}",
					 "queryJhwcsjStart" :$("#queryJhwcsjStart").val(),
					 "jshxZcyhsb.qymc" :$("#qymc").val(),
					 "queryJhwcsjEnd" :$("#queryJhwcsjEnd").val(),
				 "jshxZcyhsb.qyid": $("#qyid").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				{field:'szzname',title:'所属地区',width:fixWidth(0.15)},
						 {field:'qymc',title:'企业名称',width:fixWidth(0.15)},
				          {field:'jcsj',title:'检查时间',width:fixWidth(0.15),formatter:function(value,rec){
							if(rec.jcsj==null) return;
							var date = new Date(rec.jcsj.time);
							var month = parseInt(date.getMonth()+1);
							return date.getFullYear()+'年'+month+'月'+date.getDate()+'日  ';}}
							,
							{field:'jcry',title:'检查人员',width:fixWidth(0.15)},
							{field:'mqzt',title:'目前状态',width:fixWidth(0.15),formatter:function(value,rec){
	                             if(rec.mqzt == '0'){
	                             		return "<span style='color:red;cursor:hand'>整改完成</span>";
	                             }else{
	                             	return "<span style='color:green;cursor:hand'>整改中</span>";
	                             }
	                             
                         	 }},

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
								return str;
								**/
	                           //if(flag == '1')
	                        	if(rec.mqzt != '0'){  
				          			 return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>";
				          		}else{
				          			 return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;";
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
		function querySzc(obj){
			var szzid = $('#szzid').val();
			if(szzid == ""){
				$('#areaName').val($('#county').val());
			}else{
				$('#areaName').val($('#szzid').val());
			}
	    }
		function querySzz(obj)
	    {
			$('#areaName').val($('#county').val());
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
	<input type="hidden" id="qyid" value="${jshxZcyhsb.qyid}"/>
	<div class="submitdata">
		<table width="100%">
			<tr>
				<th width="15%">检查人员</th>
				<td width="35%"><input name="jshxZcyhsb.jcry" id="jcry" value="${jshxZcyhsb.jcry}" type="text"></td>
				<th width="15%">检查时间</th>
				<td width="35%"><input name="queryJcsjStart" id="queryJcsjStart" value="<fmt:formatDate type='date' value='${queryJcsjStart}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryJcsjEnd\')}'})" >
					-<input name="queryJcsjEnd" id="queryJcsjEnd" value="<fmt:formatDate type='date' value='${queryJcsjEnd}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryJcsjStart\')}'})" ></td>
			</tr>
			<tr>
				<th width="15%">计划完成时间</th>
				<td width="35%"><input name="queryJhwcsjStart" id="queryJhwcsjStart" value="<fmt:formatDate type='date' value='${queryJhwcsjStart}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryJhwcsjEnd\')}'})" >
					-<input name="queryJhwcsjEnd" id="queryJhwcsjEnd" value="<fmt:formatDate type='date' value='${queryJhwcsjEnd}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryJhwcsjStart\')}'})" ></td>
				<s:if test = "isshow != 1">
					<th width="15%">企业名称</th>
					<td width="35%"><input name="jshxZcyhsb.qymc" id="qymc" value="${jshxZcyhsb.qymc}" type="text"></td>
				</s:if>
			</tr>
			<s:if test="flag==3&&isshow != 1">
			  	<tr>
				 	<th width="15%">所属地区</th>
					<td width="35%">
						<input type="hidden" id="areaName">
						<cus:SelectOneTag property="county" style="width:37%;"   defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"/>
						<select id="szzid" name="jshxZcyhsb.szzid" style="width:37%;" onchange="querySzc(this.value);"><option value="">请选择</option></select>
					</td>
					<%-- <cus:SelectOneTag property="county" defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"  style="width:150px;" />
					<select id="szzid" name="jshxZcyhsb.szzid" style="width:150px;" onchange="querySzc(this.value);"><option value="">请选择</option></select> --%>
					<th width="15%">是否直属</th>
					<td width="35%" >
						<cus:SelectOneTag property="jshxZcyhsb.ifzsqy" style="width:37%;" defaultText='请选择' codeName="是否直属" value="${jshxZcyhsb.ifzsqy}" />
					</td>
				</tr>
			</s:if>
			<tr>
				<td colspan="4" align="center" style="text-align:center;">
				 
				<a href="###" class="easyui-linkbutton" onclick="search_jshxZcyhsb()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				<s:if test="flag == 1&&isshow != 1">
					<a href="###" class="easyui-linkbutton" onclick="addNew();" iconCls="icon-add">添加</a>
					<!-- <a href="###" class="easyui-linkbutton" onclick="del();" iconCls="icon-remove">删除</a> 企业不能删除已上报自查隐患-->
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

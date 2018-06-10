<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>安全检查查询管理</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
        
        function addNew(){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 400) / 2;
    		var yc = (ah - 350) / 2;
            openparentWindow("newWindow","添加安全检查查询",xc,yc,"400","350","${ctx}/jsp/distributeItem/distributeItemInitEdit.action?flag=add&dt="+dt.getTime(),true,true,true,false,true,"win");
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 400) / 2;
    		var yc = (ah - 350) / 2;
            openparentWindow("newWindow","修改安全检查查询",xc,yc,"400","350","${ctx}/jsp/distributeItem/distributeItemInitEdit.action?flag=mod&distributeItem.id="+row_Id+"&dt="+dt.getTime(),true,true,true,false,true,"win");
        	
        }
        function view(row_Id){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 800) / 2;
    		var yc = (ah - 650) / 2;
            openparentWindow("newWindow","查看安全检查查询",xc,yc,"800","650","${ctx}/jsp/distributeItem/distributeItemView.action?safeInspectDistribute.id="+row_Id+"&dt="+dt.getTime(),true,true,true,false,true,"win");
        	
        }
        
        function viewSafeDist(row_Id){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 800) / 2;
    		var yc = (ah - 650) / 2;
            openparentWindow("newWindow","查看SAFE_INSPECT_DISTRIBUTE",xc,yc,"800","650","${ctx}/jsp/safeInspectDistribute/safeInspectDistributeView.action?safeInspectDistribute.id="+row_Id+"&dt="+dt.getTime(),true,true,true,false,true,"win");
        	
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_distributeItem();
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
		                	url : "${ctx}/jsp/safeInspectDistribute/safeInspectDistributeDel.action",
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
		                        	search_distributeItem();
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
        
        function search_distributeItem(){
        	if("请选择"==$("#thisquarters").val()){
	        	$("#thisquarters").val("");
        	}
        	var queryParams = {
					"taskStatusNotZero":"taskStatusNotZero",
					"safeInspectDistribute.inspectType": $("#inspectType").val(),
					"safeInspectDistribute.title": $("#title").val(),
					"safeInspectDistribute.quarters": $("#thisquarters").val(),
					"safeInspectDistribute.cycleFlag": $("#cycleFlag").val(),
					"safeInspectDistribute.cycleValue": $("#cycleValue").val(),
					"safeInspectDistribute.personnel": $("#personnel").val(),
					"safeInspectDistribute.companyFlag": $("#companyFlag").val(),
					"safeInspectDistribute.inspectNum": $("#inspectNum").val(),
					 "queryTimeStart" :$("#queryTimeStart").val(),
				     "queryTimeEnd" :$("#queryTimeEnd").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        
        
         $(function(){
        	
			$('#pagination').datagrid({
				title:'安全检查查询列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'${ctx}/jsp/safeInspectDistribute/safeInspectDistributeQuery.action',
				queryParams:{
					"taskStatusNotZero":"taskStatusNotZero",
					"safeInspectDistribute.inspectType": $("#inspectType").val(),
					"safeInspectDistribute.title": $("#title").val(),
					"safeInspectDistribute.cycleFlag": $("#cycleFlag").val(),
					"safeInspectDistribute.cycleValue": $("#cycleValue").val(),
					"safeInspectDistribute.personnel": $("#personnel").val(),
					"safeInspectDistribute.companyFlag": $("#companyFlag").val(),
					"safeInspectDistribute.inspectNum": $("#inspectNum").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[{field:'taskTime',title:'检查任务日期',width:fixWidth(0.1),align:'center',formatter:function(value,rec){
								var temp = "";
								    if(rec.taskStatus=='2'){
								    	temp = rec.taskTime;
								    }else{
								    	temp = "<span style='color:red;'>"+rec.taskTime+"</span>";
								    }
								    return temp;
							  }
						  },
				          {field:'inspectType',title:'检查类型',width:fixWidth(0.1),align:'center',formatter:function(value,rec){
			  						var temp = "";
								    $.ajax({
									    url: '${ctx}/jsp/admin/code/findCodeValue.action',
									    type: 'post',
									    dataType: 'json',
									    async : false,
									    data:{        
									    	"codeValue.itemValue" : rec.inspectType,
									        "codeValue.codeId" : "402881e7497d66f001497d7f65a40007"    
									    },
									    error: function(){
									        $.messager.alert('提示','获取一维代码错误！');
									    },
									    success: function(data){
								        	temp = data.itemText;
								    	}
									});
								    if(rec.taskStatus!='2'){
								    	temp = "<span style='color:red;'>"+temp+"</span>";
								    }
								    return temp;
					    		}
				         	},
				         	{field:'count',title:'不合格数',width:fixWidth(0.05),align:'center',formatter:function(value,rec){
								var temp = "";
								    if(rec.taskStatus=='2'){
								    	temp = rec.count;
								    }else{
								    	temp = "<span style='color:red;'>"+rec.count+"</span>";
								    }
								    return temp;
							  }},
							{field:'title',title:'巡检标题',width:fixWidth(0.15),align:'center',formatter:function(value,rec){
								var temp = "";
								    if(rec.taskStatus=='2'){
								    	temp = rec.title;
								    }else{
								    	temp = "<span style='color:red;'>"+rec.title+"</span>";
								    }
								    return temp;
							  }},
							{field:'inspectNum',title:'检查总次数',width:fixWidth(0.05),align:'center',formatter:function(value,rec){
								var temp = "";
								    if(rec.taskStatus=='2'){
								    	temp = rec.inspectNum;
								    }else{
								    	temp = "<span style='color:red;'>"+rec.inspectNum+"</span>";
								    }
								    return temp;
							  }},
							{field:'quarters',title:'检测岗位',width:fixWidth(0.1),align:'center',formatter:function(value,rec){
			  						var temp = "";
								    $.ajax({
									    url: '${ctx}/jsp/safeInspectDistribute/findDeptnameByDeptcode.action',
									    type: 'post',
									    dataType: 'json',
									    async : false,
									    data:{        
									    	"deptCode" : rec.quarters   
									    },
									    error: function(){
									        $.messager.alert('提示','获取一维代码错误！');
									    },
									    success: function(data){
								        	temp = data.deptName;
								    	}
									});
								    if(rec.taskStatus!='2'){
								    	temp = "<span style='color:red;'>"+temp+"</span>";
								    }
								    return temp;
					    		}
				         	},
							{field:'personnel',title:'安全检查员',width:fixWidth(0.1),align:'center',formatter:function(value,rec){
			  						var temp = "";
								    $.ajax({
									    url: '${ctx}/jsp/safeInspectDistribute/findUsernameById.action',
									    type: 'post',
									    dataType: 'json',
									    async : false,
									    data:{        
									    	"userId" : rec.personnel
									    },
									    error: function(){
									        
									    },
									    success: function(data){
								        	temp = data.userName;
								    	}
									});
								    if(rec.taskStatus!='2'){
								    	temp = "<span style='color:red;'>"+temp+"</span>";
								    }
								    return temp;
					    		}
				         	},
							{field:'reportTime',title:'上报时间',width:fixWidth(0.1),align:'center',formatter:function(value,rec){
									var temp = "";
								    if(rec.taskStatus=='2'){
								    	temp = rec.reportTime;
								    }else{
								    	temp = "<span style='color:red;'>"+rec.reportTime+"</span>";
								    }
								    return temp;
							  }
						  },
							{field:'taskStatus',title:'检查状态',width:fixWidth(0.1),align:'center',formatter:function(value,rec){
			  						var temp = "";
								    if(rec.taskStatus=='2'){
								    	temp = "已完成";
								    }else if(rec.taskStatus=='3'){
								    	temp = "<span style='color:red;'>已完成</span>";
								    }else{
								    	temp = "<span style='color:red;'>未检查</span>";
								    }
								    return temp;
					    		}
				         	},
				          	{field:'op',title:'检查结果',width:fixWidth(0.1),align:'center',formatter:function(value,rec){
	                             return "<span style='cursor:hand' onclick=\"view('"+rec.id+"')\">查看详细</span>";
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
        
        $(function(){
	        $('#thisquarters').combotree({    
				    url: '${ctx}/jsp/safeInspectDistribute/findChildDept.action?',    
				    required: true,
				    panelHeight:'auto',
				    onClick:function(node){
	                 	$("#thisquarters").val(node.id);
	           		},  
					onBeforeExpand:function(node) {
					      $('#thisquarters').combotree("tree").tree("options").url = "${ctx}/jsp/safeInspectDistribute/findChildDept.action?selDept=" + node.id;
					}
			});
		});
    </script>
	</head>

	<body>
		<%@include file="/WEB-INF/template/content_title.jsp"%>

		<form name="myform" method="post">
			<input type="hidden" name="safeInspectDistribute.inspectNum" id="inspectNum" value="1">
			<div class="submitdata">
				<table width="100%">
					<tr>
						<th width="15%">
							安全检测类型
						</th>
						<td width="35%">
							<cus:SelectOneTag property="safeInspectDistribute.inspectType"
								defaultText='请选择' codeName="安全检测类型"
								value="${safeInspectDistribute.inspectType}" />
						</td>
						<th width="15%">
							巡检标题
						</th>
						<td width="35%">
							<input name="safeInspectDistribute.title" id="title"
								value="${safeInspectDistribute.title}" type="text">
						</td>
					</tr>
					<tr>
						<th width="15%">
							检测岗位
						</th>
						<td id="quarters">
							<input id="thisquarters" name="safeInspectDistribute.quarters"
								value="请选择" style="width: 150px; height: 180px; overflow: auto;">
						</td>
						<th width="15%">
							检查任务日期
						</th>
						<td width="35%">
							<input name="queryTimeStart" id="queryTimeStart"
								value="${queryTimeStart}"
								type="text" class="Wdate"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryTimeEnd\')}'})">
							-
							<input name="queryTimeEnd" id="queryTimeEnd"
								value="${queryTimeEnd}"
								type="text" class="Wdate"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryTimeStart\')}'})">
						</td>
					</tr>
					<tr>
						<td colspan="4" style="text-align: center;">
							<a href="###" class="easyui-linkbutton"
								onclick="search_distributeItem()" iconCls="icon-search">查询</a>&nbsp;
							<a href="###" class="easyui-linkbutton"
								onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;
							<!-- <a href="###" class="easyui-linkbutton" onclick="addNew();" iconCls="icon-add">添加</a>&nbsp; -->
							<a href="###" class="easyui-linkbutton" onclick="del();"
								iconCls="icon-remove">删除</a>
						</td>
					</tr>
				</table>
			</div>
			<table cellspacing="0" cellpadding="0" width="100%" border="0">
				<tr>
					<td>
						<div id="pagination"
							style="background: #efefef; border: 1px solid #ccc;">

						</div>
					</td>
				</tr>
			</table>
		</form>
		<%@include file="/WEB-INF/template/pagefoot.jsp"%>
	</body>
</html>

<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>SEGMENTPLANT管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	window.open("${ctx}/jsp/segmentplant/segmentplantInitEdit.action?flag=add");
        }
        function edit(row_Id){
            window.open("${ctx}/jsp/segmentplant/segmentplantInitEdit.action?flag=mod&segmentplant.id="+row_Id);
        }
        function view(row_Id){
        	window.open("${ctx}/jsp/segmentplant/segmentplantView.action?segmentplant.id="+row_Id);
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_segmentplant();
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
		                	url : "segmentplantDel.action",
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
		                        	search_segmentplant();
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
        
        function search_segmentplant(){
        	var queryParams = {
				"segmentplant.facilityname": $("#facilityname").val(),
						"segmentplant.deptId":$("#deptId").val(),
					"segmentplant.comName":$("#comName").val(),
			 "createTimeStart" :$("#createTimeStart").val(),
			 "createTimeEnd" :$("#createTimeEnd").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'消防设施分布图列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'segmentplantQuery.action',
				queryParams:{
					"segmentplant.facilityname": $("#facilityname").val(),
							"segmentplant.deptId":$("#deptId").val(),
					"segmentplant.comName":$("#comName").val(),
			 "createTimeStart" :$("#createTimeStart").val(),
			 "createTimeEnd" :$("#createTimeEnd").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
					 {field:'deptName',title:'所属街道',width:fixWidth(0.2)},
				 {field:'comName',title:'企业名称',width:fixWidth(0.2)},
				 {field:'facilityname',title:'设施名称',width:fixWidth(0.2)},
				 {field:'createTime',title:'上传时间',width:fixWidth(0.2),formatter:function(value,rec){
				          	if(rec.createTime==null) {return;}
							var date = new Date(rec.createTime.time);
							var month = parseInt(date.getMonth()+1);
							return date.getFullYear()+'年'+month+'月'+date.getDate()+'日  '+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();
				          }},
				          {field:'op',title:'操作',width:fixWidth(0.2),formatter:function(value,rec){
	                            /*
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
					        	  
					        	 return str;*/
								 return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;";
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
						var selectContainer = $('#deptId'); 
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
				<td width="35%"><input name="segmentplant.comName" size=30 id="comName" value="${segmentplant.comName}" type="text"></td>
				<th width="15%">设施名称</th>
				<td width="35%"><input name="segmentplant.facilityname" id="facilityname" value="${segmentplant.facilityname}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">文件上传时间</th>
				<td width="35%"><input name="createTimeStart" id="createTimeStart" value="<fmt:formatDate type='both' value='${createTimeStart}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'createTimeEnd\')}'})" >
					-<input name="createTimeEnd" id="createTimeEnd" value="<fmt:formatDate type='both' value='${createTimeEnd}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'createTimeStart\')}'})" ></td>
				<s:if test="visable==1">
				<th width="15%">所属地区</th>
				<td width="35%">
					<cus:SelectOneTag property="county" defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"  style="width:150px;" />
					<select id="deptId" name="segmentplant.deptId" style="width:150px;" ><option value="">请选择</option></select>
						
					<!--hanxc 2014/11/11 		
					<cus:SelectOneTag property="segmentplant.deptId" defaultText='请选择' codeName="相城地址" value="${segmentplant.deptId}" /></td>
					 -->
				</s:if>
			</tr>
			<tr>
				<td colspan="4" style="text-align:center">
				<a href="###" class="easyui-linkbutton" onclick="search_segmentplant()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				
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

<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>主要负责人、安全管理员安全培训和职业卫生培训管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        var flag = "${flag}";
        function addNew(){
        	window.open("${ctx}/jsp/zazPxb/jshxZazPxbInitEdit.action?flag=add");
        }
        
        function edit(row_Id){
        	window.open("${ctx}/jsp/zazPxb/jshxZazPxbInitEdit.action?flag=mod&jshxZazPxb.id="+row_Id);
        }
        
        function view(row_Id){
        	window.open("${ctx}/jsp/zazPxb/jshxZazPxbView.action?jshxZazPxb.id="+row_Id);
        }
        function importData(){
        	window.open("${ctx}/jsp/zazPxb/jshxZazPxbImportData.action");
        }
        function exportdata()
        {
        	document.myform.action = "${ctx}/jsp/zazPxb/jshxZazPxbExport.action";
        	document.myform.submit();
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_jshxZazPxb();
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
		                	url : "jshxZazPxbDel.action",
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
		                        	search_jshxZazPxb();
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
        
        function search_jshxZazPxb(){
        	var queryParams = {
"jshxZazPxb.szzname": $("#szzname").val(),
"jshxZazPxb.qymc": $("#qymc").val(),
"jshxZazPxb.personName": $("#personName").val(),
"jshxZazPxb.zw": $("#zw").val(),
 "jshxZazPxb.xl": $("#xl").val(),
  "jshxZazPxb.sfz": $("#sfz").val(),
 "queryPxsjStart" :$("#queryPxsjStart").val(),
 "queryPxsjEnd" :$("#queryPxsjEnd").val(),
"jshxZazPxb.pxzh": $("#pxzh").val(),
"jshxZazPxb.whpqylx": $("#whpqylx").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'主要负责人、安全管理员安全培训和职业卫生培训列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'jshxZazPxbQuery.action',
				queryParams:{
				"jshxZazPxb.szzname": $("#szzname").val(),
"jshxZazPxb.qymc": $("#qymc").val(),
"jshxZazPxb.personName": $("#personName").val(),
"jshxZazPxb.zw": $("#zw").val(),
 "jshxZazPxb.xl": $("#xl").val(),
  "jshxZazPxb.sfz": $("#sfz").val(),
 "queryPxsjStart" :$("#queryPxsjStart").val(),
 "queryPxsjEnd" :$("#queryPxsjEnd").val(),
"jshxZazPxb.pxzh": $("#pxzh").val(),
"jshxZazPxb.whpqylx": $("#whpqylx").val()

				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
			{field:'szzname',title:'所在镇',width:fixWidth(0.1)},
			{field:'qymc',title:'企业名称',width:fixWidth(0.1)},	
{field:'personName',title:'姓名',width:fixWidth(0.1)},
{field:'zw',title:'职务',width:fixWidth(0.1)},
{field:'xl',title:'文化',width:fixWidth(0.1)},
{field:'sfz',title:'身份证',width:fixWidth(0.1)},
{field:'cpsj',title:'初培时间',width:fixWidth(0.1)},
{field:'pxzh',title:'资格证号',width:fixWidth(0.1)},
{field:'whpqylx',title:'危化品企业类型',width:fixWidth(0.1)},
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
	                           if(flag == '1')
				          		{
				          			return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>";
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
<input type="hidden" name="flag" value="1"/>
	<div class="submitdata">
		<table width="100%">
			<tr>
				<th width="15%">所在镇</th>
				<td width="35%">
					<cus:SelectOneTag property="county" defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"  style="width:150px;" />
					<select id="szzname" name="jshxZazPxb.szzname" style="width:150px;" ><option value="">请选择</option></select>
						
					<!--hanxc 2014/11/11 		
					
					<s:select id="szzname" listKey="key" listValue="value"  theme="simple" list="#{'':'请选择','高铁新城':'高铁新城','度假区':'度假区','开发区':'开发区','北桥街道':'北桥街道'
					,'黄桥街道':'黄桥街道','太平街道':'太平街道','元和街道':'元和街道','阳澄湖镇':'阳澄湖镇','渭塘镇':'渭塘镇','黄埭镇':'黄埭镇','望亭镇':'望亭镇'}" name="jshxZazPxb.szzname" value="{jshxZazPxb.szzname}"/>
					 -->
				</td>
				<th width="15%">企业名称</th>
				<td width="35%">
					<input id="qymc" name="jshxZazPxb.qymc"　type="text" value="${jshxZazPxb.qymc}">
				</td>
			</tr>
			<tr>
				<th width="15%">姓名</th>
				<td width="35%"><input name="jshxZazPxb.personName" id="personName" value="${jshxZazPxb.personName}" type="text"></td>
				<th width="15%">职务</th>
					<td width="35%"><s:select id="zw" listKey="key" listValue="value"  theme="simple" list="#{'':'请选择','法人':'法人','主要负责人':'主要负责人','安全管理员':'安全管理员','职业卫生管理员':'职业卫生管理员'}" name="jshxZazPxb.zw" value="{jshxZazPxb.zw}"/>
				</td>
			</tr>
			<tr>
				<th width="15%">文化</th>
				<td width="35%"><s:select id="xl" listKey="key" listValue="value"  theme="simple" list="#{'':'请选择','博士':'博士','硕士':'硕士','本科':'本科','大专':'大专','中专':'中专','高中':'高中','其它':'其它'}" name="jshxZazPxb.xl" value="{jshxZazPxb.xl}"/></td>
				<th width="15%">身份证</th>
				<td width="35%"><input id="sfz" name="jshxZazPxb.sfz" value="${jshxZazPxb.sfz}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">初培时间</th>
				<td width="35%"><input name="queryPxsjStart" id="queryPxsjStart" value="${queryPxsjStart}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryPxsjEnd\')}'})" >
					-<input name="queryPxsjEnd" id="queryPxsjEnd" value="${queryPxsjEnd}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryPxsjStart\')}'})" ></td>
				<th width="15%">资格证号</th>
				<td width="35%"><input name="jshxZazPxb.pxzh" id="pxzh" value="${jshxZazPxb.pxzh}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">危化品企业类型</th>
				<td width="35%">
					<s:select id="whpqylx" listKey="key" listValue="value"  theme="simple" list="#{'':'请选择','化工生产企业':'化工生产企业','危险化学品经营企业':'危险化学品经营企业','烟花爆竹经营企业':'烟花爆竹经营企业','其它工贸企业':'其它工贸企业'}" name="jshxZazPxb.whpqylx" value="{jshxZazPxb.whpqylx}"/>
				</td>
			</tr>
			<tr>
				<td colspan="4" style="text-align:center">
				
				<a href="###" class="easyui-linkbutton" onclick="search_jshxZazPxb()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				<s:if test="flag == 1">
					<a href="###" class="easyui-linkbutton" onclick="addNew();" iconCls="icon-add">添加</a>&nbsp;
					<a href="###" class="easyui-linkbutton" onclick="del();" iconCls="icon-remove">删除</a>&nbsp;
					<a href="###" class="easyui-linkbutton" onclick="importData();" iconCls="icon-remove">导入</a>
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

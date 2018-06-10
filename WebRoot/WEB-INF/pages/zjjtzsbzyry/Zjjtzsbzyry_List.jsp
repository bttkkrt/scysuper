<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>质监局特种设备作业人员管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        var flag = "${flag}";
        function addNew(){
        	window.open("${ctx}/jsp/zjjtzsbzyry/zjjtzsbzyryInitEdit.action?flag=add");
        }
        function edit(row_Id){
        	window.open("${ctx}/jsp/zjjtzsbzyry/zjjtzsbzyryInitEdit.action?flag=mod&zjjtzsbzyry.id="+row_Id);
        }
        function view(row_Id){
        	window.open("${ctx}/jsp/zjjtzsbzyry/zjjtzsbzyryView.action?zjjtzsbzyry.id="+row_Id);
        }
        //图片上传 lj 2013-04-18
        function uploadFile(){
      		window.open("${ctx}/jsp/zjjtzsbzyry/zjjtzsbzyryUpload.action");
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_zjjtzsbzyry();
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
		                	url : "zjjtzsbzyryDel.action",
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
		                        	search_zjjtzsbzyry();
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
		                	url : "zjjtzsbzyryDels.action",
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
		                        	search_zjjtzsbzyry();
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
        
        function search_zjjtzsbzyry(){
        	var queryParams = {
				"zjjtzsbzyry.mc": $("#mc").val(),
				"zjjtzsbzyry.szzname": $("#szzname").val(),
				"zjjtzsbzyry.xl": $("#xl").val(),
"zjjtzsbzyry.sfz": $("#sfz").val(),
"zjjtzsbzyry.xm": $("#xm").val(),
"zjjtzsbzyry.pydw": $("#pydw").val(),
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
				title:'质监局特种设备作业人员列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'zjjtzsbzyryQuery.action',
				queryParams:{
					"zjjtzsbzyry.mc": $("#mc").val(),
				"zjjtzsbzyry.szzname": $("#szzname").val(),
				"zjjtzsbzyry.xl": $("#xl").val(),
"zjjtzsbzyry.sfz": $("#sfz").val(),
"zjjtzsbzyry.xm": $("#xm").val(),
"zjjtzsbzyry.pydw": $("#pydw").val(),
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
				          {field:'mc',title:'姓名',width:fixWidth(0.08)},
				          {field:'szzname',title:'乡镇',width:fixWidth(0.1)},
				          {field:'xl',title:'文化程度',width:fixWidth(0.08)},
{field:'sfz',title:'身份证',width:fixWidth(0.12)},
{field:'xm',title:'证书类型',width:fixWidth(0.15),formatter:function(value,rec){
	if(rec.xm == 'A3')
	{
		return '锅炉压力容器压力管道安全管理';
	}
	else if(rec.xm == 'A4')
	{
		return '电梯安全管理';
	}
	else if(rec.xm == 'A5')
	{
		return '起重机械安全管理';
	}
	else if(rec.xm == 'A8')
	{
		return '场（厂）内专用机动车辆安全管理';
	}
	else if(rec.xm == 'G1')
	{
		return '一级锅炉司炉';
	}
	else if(rec.xm == 'G2')
	{
		return '二级锅炉司炉';
	}
	else if(rec.xm == 'G4')
	{
		return '一级锅炉水质处理';
	}
	else if(rec.xm == 'G5')
	{
		return '二级锅炉水质处理（电站锅炉除外）';
	}
	else if(rec.xm == 'R1')
	{
		return '固定式压力容器操作';
	}
	else if(rec.xm == 'T3')
	{
		return '电梯司机';
	}
	else if(rec.xm == 'Q3')
	{
		return '起重机械指挥';
	}
	else if(rec.xm == 'Q4')
	{
		return '桥门式起重机司机';
	}
	else if(rec.xm == 'Q6')
	{
		return '门座式起重机司机';
	}
	else if(rec.xm == 'Q8')
	{
		return '流动式起重机司机';
	}
	else if(rec.xm == 'Q10')
	{
		return '机械式停车设备司机';
	}
	else if(rec.xm == 'N2')
	{
		return '叉车司机';
	}
	else if(rec.xm == 'N5')
	{
		return '蓄电池观光车司机';
	}
	else
	{
		return rec.xm;
	}
                          }},
{field:'pydw',title:'用人单位',width:fixWidth(0.15)},
{field:'pzrq',title:'首次领证日期',width:fixWidth(0.1)},
{field:'yxrq',title:'当前证书有效期',width:fixWidth(0.1)},
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
	<div class="submitdata">
		<table width="100%">
			<tr>
				<th width="15%">姓名</th>
				<td width="35%"><input name="zjjtzsbzyry.mc" id="mc" value="${zjjtzsbzyry.mc}" type="text"></td>
				<th width="15%">乡镇</th>
				<td width="35%">
					<cus:SelectOneTag property="county" defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"  style="width:150px;" />
					<select id="szzname" name="zjjtzsbzyry.szzname" style="width:150px;" ><option value="">请选择</option></select>
						
					<!--hanxc 2014/11/11 		
					
					<s:select id="szzname" listKey="key" listValue="value"  theme="simple" list="#{'':'请选择','高铁新城':'高铁新城','度假区':'度假区','开发区':'开发区','北桥街道':'北桥街道'
					,'黄桥街道':'黄桥街道','太平街道':'太平街道','元和街道':'元和街道','阳澄湖镇':'阳澄湖镇','渭塘镇':'渭塘镇','黄埭镇':'黄埭镇','望亭镇':'望亭镇','其它':'其它'}" name="zjjtzsbzyry.szzname" value="{zjjtzsbzyry.szzname}"/>
					 -->
				</td>
			</tr>
			<tr>
				<th width="15%">文化程度</th>
				<td width="35%">
					<s:select id="xl" listKey="key" listValue="value"  theme="simple" list="#{'':'请选择','初中以下':'初中以下','初中以上':'初中以上'
					,'初中':'初中','技校':'技校','中专':'中专','中技':'中技','职高':'职高','高中':'高中','大专':'大专','本科':'本科','硕士':'硕士','博士':'博士'}" name="zjjtzsbzyry.xl" value="{zjjtzsbzyry.xl}"/>
				</td>
				<th width="15%">身份证</th>
				<td width="35%"><input name="zjjtzsbzyry.sfz" id="sfz" value="${zjjtzsbzyry.sfz}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">证书类型</th>
				<td width="35%">
					<s:select id="xm" listKey="key" listValue="value"  theme="simple" list="#{'':'','A3':'锅炉压力容器压力管道安全管理','A4':'电梯安全管理','A5':'起重机械安全管理','A8':'场（厂）内专用机动车辆安全管理','G1':'一级锅炉司炉','G2':'二级锅炉司炉','G4':'一级锅炉水质处理','G5':'二级锅炉水质处理（电站锅炉除外）','R1':'固定式压力容器操作','T3':'电梯司机','Q3':'起重机械指挥','Q4':'桥门式起重机司机','Q6':'门座式起重机司机','Q8':'流动式起重机司机','Q10':'机械式停车设备司机','N2':'叉车司机','N5':'蓄电池观光车司机'}" name="zjjtzsbzyry.xm"/>
				</td>
				<th width="15%">用人单位</th>
				<td width="35%"><input name="zjjtzsbzyry.pydw" id="pydw" value="${zjjtzsbzyry.pydw}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">首次领证日期</th>
				<td width="35%">
					<input name="queryJyrqStart" id="queryJyrqStart" value="${queryJyrqStart}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryJyrqEnd\')}'})" >
					-<input name="queryJyrqEnd" id="queryJyrqEnd" value="${queryJyrqEnd}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryJyrqStart\')}'})" >
				</td>
				<th width="15%">当前证书有效期</th>
				<td width="35%">
					<input name="queryXcjyrqStart" id="queryXcjyrqStart" value="${queryXcjyrqStart}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryXcjyrqEnd\')}'})" >
					-<input name="queryXcjyrqEnd" id="queryXcjyrqEnd" value="${queryXcjyrqEnd}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryXcjyrqStart\')}'})" >
				</td>
			</tr>
			<tr>
				<td colspan="4" style="text-align:center">
				
				<a href="###" class="easyui-linkbutton" onclick="search_zjjtzsbzyry()" iconCls="icon-search">查询</a>&nbsp;
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

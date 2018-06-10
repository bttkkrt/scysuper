<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>特种作业人员培训管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        var flag = "${flag}";
        var selects=[];
		selects[0]=new Array(
			new Option('','0'));
		selects['1']=new Array(
			new Option('','0'),
			new Option('高压电工作业','1'),
			new Option('低压电工作业','2'),
			new Option('防爆电气作业','3')
		);
		selects['2']=new Array(
			new Option('','0'),
			new Option('熔化焊接与热切割作业','1'),
			new Option('压力焊作业','2'),
			new Option('钎焊作业','3')
		);
		selects['3']=new Array(
			new Option('','0'),
			new Option('登高架设作业','1'),
			new Option('高处安装、维护、拆除作业','2')
		);
		selects['4']=new Array(
			new Option('','0'),
			new Option('制冷与空调设备运行操作作业','1'),
			new Option('制冷与空调设备安装修理作业','2')
		);
		selects['5']=new Array(
			new Option('','0'),
			new Option('煤气作业','1')
		);
		selects['6']=new Array(
			new Option('','0'),
			new Option('光气及光气化工艺作业','1'),
			new Option('氯碱电解工艺作业','2'),
			new Option('氯化工艺作业','3'),
			new Option('硝化工艺作业','4'),
			new Option('合成氨工艺作业','5'),
			new Option('裂解（裂化）工艺作业','6'),
			new Option('氟化工艺作业','7'),
			new Option('加氢工艺作业','8'),
			new Option('重氮化工艺作业','9'),
			new Option('氧化工艺作业','10'),
			new Option('过氧化工艺作业','11'),
			new Option('胺基化工艺作业','12'),
			new Option('磺化工艺作业','13'),
			new Option('聚合工艺作业','14'),
			new Option('烷基化工艺作业','15'),
			new Option('化工自动化控制仪表作业','16')
		);
		selects['7']=new Array(
			new Option('','0'),
			new Option('烟火药制造作业','1'),
			new Option('黑火药制造作业','2'),
			new Option('引火线制造作业','3'),
			new Option('烟花爆竹产品涉药作业','4'),
			new Option('烟花爆竹储存作业','5')
		);
		
		function changeZL(obj){
			var gzxl=document.getElementById("gzxl");
			if(obj != null && obj != "")
			{
				gzxl.options.length=0;
   				for(var i=0;i<selects[obj].length;i++){
   					gzxl.options.add(selects[obj][i]);
   				}
			}
		}
        
        function edit(row_Id){
        	window.open("${ctx}/jsp/tzzyPxb/jshxTzzyPxbInitEdit.action?flag=mod&jshxTzzyPxb.id="+row_Id);
        }
        function view(row_Id){
        	window.open("${ctx}/jsp/tzzyPxb/jshxTzzyPxbView.action?jshxTzzyPxb.id="+row_Id);
        }
        function addNew(){
        	window.open("${ctx}/jsp/tzzyPxb/jshxTzzyPxbInitEdit.action?flag=add");
        }
        function importData(){
        	window.open("${ctx}/jsp/tzzyPxb/jshxTzzyPxbImportData.action");
        }
        function exportdata()
        {
        	document.myform.action = "${ctx}/jsp/tzzyPxb/jshxTzzyPxbExport.action";
        	document.myform.submit();
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_jshxTzzyPxb();
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
		                	url : "jshxTzzyPxbDel.action",
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
		                        	search_jshxTzzyPxb();
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
        
        function search_jshxTzzyPxb(){
        	var queryParams = {
        	"jshxTzzyPxb.szzname": $("#szzname").val(),
"jshxTzzyPxb.qymc": $("#qymc").val(),
"jshxTzzyPxb.personName": $("#personName").val(),
"jshxTzzyPxb.xl": $("#xl").val(),
"jshxTzzyPxb.tzzh": $("#tzzh").val(),
 "queryPxsjStart" :$("#queryPxsjStart").val(),
 "queryPxsjEnd" :$("#queryPxsjEnd").val(),
 "jshxTzzyPxb.sfz": $("#sfz").val(),
 "queryYxqStart" :$("#queryYxqStart").val(),
 "queryYxqEnd" :$("#queryYxqEnd").val(),
 "jshxTzzyPxb.gz": $("#gz").val(),
"jshxTzzyPxb.gzxl": $("#gzxl").val(),
"jshxTzzyPxb.qylx": $("#qylx").val(),
 "queryLzrqStart" :$("#queryLzrqStart").val(),
 "queryLzrqEnd" :$("#queryLzrqEnd").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'特种作业人员培训列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'jshxTzzyPxbQuery.action',
				queryParams:{
"jshxTzzyPxb.szzname": $("#szzname").val(),
"jshxTzzyPxb.qymc": $("#qymc").val(),
"jshxTzzyPxb.personName": $("#personName").val(),
"jshxTzzyPxb.xl": $("#xl").val(),
"jshxTzzyPxb.tzzh": $("#tzzh").val(),
 "queryPxsjStart" :$("#queryPxsjStart").val(),
 "queryPxsjEnd" :$("#queryPxsjEnd").val(),
 "jshxTzzyPxb.sfz": $("#sfz").val(),
 "queryYxqStart" :$("#queryYxqStart").val(),
 "queryYxqEnd" :$("#queryYxqEnd").val(),
 "jshxTzzyPxb.gz": $("#gz").val(),
"jshxTzzyPxb.gzxl": $("#gzxl").val(),
"jshxTzzyPxb.qylx": $("#qylx").val(),
 "queryLzrqStart" :$("#queryLzrqStart").val(),
 "queryLzrqEnd" :$("#queryLzrqEnd").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				{field:'szzname',title:'所在镇',width:fixWidth(0.1)},
			{field:'qymc',title:'工作单位',width:fixWidth(0.1)},	
{field:'personName',title:'姓名',width:fixWidth(0.1)},
{field:'xl',title:'学历',width:fixWidth(0.1)},
{field:'tzzh',title:'特种证号',width:fixWidth(0.1)}, 
{field:'sj',title:'培训时间',width:fixWidth(0.1)},
{field:'sfz',title:'身份证',width:fixWidth(0.1)},
   {field:'gz',title:'工种大类',width:fixWidth(0.1),formatter:function(value,rec){
	var a = new Array(' ','电工作业','焊接与热切割作业','高处作业',
				'制冷与空调作业','冶金（有色）生产安全作业','危险化学品安全作业','烟花爆竹安全作业');
	return a[rec.gz];
                          }},     
    {field:'gzxl',title:'工种小类',width:fixWidth(0.1),formatter:function(value,rec){
	var b = new Array(new Array(''),new Array('','高压电工作业','低压电工作业','防爆电气作业'),new Array('','熔化焊接与热切割作业','压力焊作业','钎焊作业'),
	new Array('','登高架设作业','高处安装、维护、拆除作业'),new Array('','制冷与空调设备运行操作作业','制冷与空调设备安装修理作业'),new Array('','煤气作业'),
	new Array('','光气及光气化工艺作业','氯碱电解工艺作业','氯化工艺作业','硝化工艺作业','合成氨工艺作业','裂解（裂化）工艺作业','氟化工艺作业','加氢工艺作业',
	'重氮化工艺作业','氧化工艺作业','过氧化工艺作业','胺基化工艺作业','磺化工艺作业','聚合工艺作业','烷基化工艺作业','化工自动化控制仪表作业'),
	new Array('','烟火药制造作业','黑火药制造作业','引火线制造作业','烟花爆竹产品涉药作业','烟花爆竹储存作业'));
	return b[rec.gz][rec.gzxl];
                          }},          
{field:'qylx',title:'企业类型',width:fixWidth(0.1)},   
{field:'lzrq',title:'领证日期',width:fixWidth(0.1)},                  
{field:'yxsj',title:'有效期',width:fixWidth(0.1)},
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
					<select id="szzname" name="jshxTzzyPxb.szzname" style="width:150px;" ><option value="">请选择</option></select>
						
					<!--hanxc 2014/11/11 		
					
					<s:select id="szzname" listKey="key" listValue="value"  theme="simple" list="#{'':'请选择','高铁新城':'高铁新城','度假区':'度假区','开发区':'开发区','北桥街道':'北桥街道'
					,'黄桥街道':'黄桥街道','太平街道':'太平街道','元和街道':'元和街道','阳澄湖镇':'阳澄湖镇','渭塘镇':'渭塘镇','黄埭镇':'黄埭镇','望亭镇':'望亭镇','其它':'其它'}" name="jshxTzzyPxb.szzname" value="{jshxTzzyPxb.szzname}"/>
					 -->
				</td>
				<th width="15%">企业名称</th>
				<td width="35%">
					<input id="qymc" name="jshxTzzyPxb.qymc"　type="text" value="${jshxTzzyPxb.qymc}">
				</td>
			</tr>
			<tr>
				<th width="15%">姓名</th>
				<td width="35%"><input name="jshxTzzyPxb.personName" id="personName" value="${jshxTzzyPxb.personName}" type="text"></td>
				<th width="15%">文化</th>
				<td width="35%"><s:select id="xl" listKey="key" listValue="value"  theme="simple" list="#{'':'请选择','博士':'博士','硕士':'硕士','本科':'本科','大专':'大专','中专':'中专','高中':'高中','其它':'其它'}" name="jshxTzzyPxb.xl" value="{jshxTzzyPxb.xl}"/></td>
			</tr>
			<tr>
				<th width="15%">特种证号</th>
				<td width="35%"><input name="jshxTzzyPxb.tzzh" id="tzzh" value="${jshxTzzyPxb.tzzh}" type="text"></td>
				<th width="15%">培训时间</th>
				<td width="35%"><input name="queryPxsjStart" id="queryPxsjStart" value="${queryPxsjStart}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryPxsjEnd\')}'})" >
					-<input name="queryPxsjEnd" id="queryPxsjEnd" value="${queryPxsjEnd}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryPxsjStart\')}'})" ></td>
			</tr>
			<tr>
				<th width="15%">身份证</th>
				<td width="35%"><input id="sfz" name="jshxTzzyPxb.sfz" value="${jshxTzzyPxb.sfz}" type="text"></td>
				<th width="15%">企业类型</th>
				<td width="35%">
					<s:select id="qylx" listKey="key" listValue="value"  theme="simple" list="#{'':'请选择','化工生产企业':'化工生产企业','危险化学品经营企业':'危险化学品经营企业','烟花爆竹经营企业':'烟花爆竹经营企业','其它工贸企业':'其它工贸企业'}" name="jshxTzzyPxb.qylx" value="{jshxTzzyPxb.qylx}"/>
				</td>
			</tr>
			<tr>
				<th width="15%">工种大类</th>
				<td width="35%">
					<s:select id="gz" listKey="key" listValue="value"  theme="simple" list="#{'0':' ','1':'电工作业','2':'焊接与热切割作业','3':'高处作业','4':'制冷与空调作业','5':'冶金（有色）生产安全作业','6':'危险化学品安全作业','7':'烟花爆竹安全作业'}" name="jshxTzzyPxb.gz" value="" onchange="changeZL(this.value);"/>
				</td>
				<th width="15%">工种小类</th>
				<td width="35%">
					<select name="jshxTzzyPxb.gzxl" id="gzxl">
      					<option value="0" selected="selected">&nbsp;&nbsp;</option>
   					</select>
				</td>
			</tr>
			<tr>
				<th width="15%">领证日期</th>
				<td width="35%"><input name="queryLzrqStart" id="queryLzrqStart" value="${queryLzrqStart}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryLzrqEnd\')}'})" >
					-<input name="queryLzrqEnd" id="queryLzrqEnd" value="${queryLzrqEnd}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryLzrqStart\')}'})" ></td>
				<th width="15%">有效期</th>
				<td width="35%"><input name="queryYxqStart" id="queryYxqStart" value="${queryYxqStart}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryYxqEnd\')}'})" >
					-<input name="queryYxqEnd" id="queryYxqEnd" value="${queryYxqEnd}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryYxqStart\')}'})" ></td>
			</tr>
			<tr>
				<td colspan="4" style="text-align:center">
				
				<a href="###" class="easyui-linkbutton" onclick="search_jshxTzzyPxb()" iconCls="icon-search">查询</a>&nbsp;
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

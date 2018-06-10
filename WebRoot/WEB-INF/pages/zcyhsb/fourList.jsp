<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>部门列表</title>
		<%@include file="/common/jsLib.jsp"%>
		<link rel="stylesheet" type="text/css" href="${ctx}/h-ui/1.0.8/iconfont.css">
		<script>
	        function search_dept(){
	        	
	        	var queryParams = {
					"dept.deptCode" : window.currentDeptCode||"${dept.deptCode}",
					"dept.deptName" : $("#deptName").val(),
					"dept.delFlag" : $("#delFlag").val()
				};        	
	        	$('#pagination').datagrid('options').queryParams = queryParams;
	        	$("#pagination").datagrid('load'); 
	        }
	        function view(row_Id){
	        	createSimpleWindow("win_agencyInfo","","${ctx}/jsp/zcyhsb/jshxZcyhsbView.action?jshxZcyhsb.id="+row_Id,900,600);
	        }
	        function search_jshxZcyhsb(){
	        	var queryParams = {
	        	"jshxZcyhsb.szzid": $("#szzid").val(),
					 "queryJcsjStart" :$("#queryJcsjStart").val(),
					 "queryJcsjEnd" :$("#queryJcsjEnd").val(),
					"jshxZcyhsb.jcry": $("#jcry").val(),
					 "queryJhwcsjStart" :$("#queryJhwcsjStart").val(),
					 "jshxZcyhsb.qymc" :$("#qymc").val(),
					 "jshxZcyhsb.mqzt": "${mqzt}",
					 "queryJhwcsjEnd" :$("#queryJhwcsjEnd").val(),
					 "jshxZcyhsb.ifzsqy": $("#ifzsqy").val(),
					 "cityClick" :$("#cityClick").val(),
					 "jshxZcyhsb.qyid": $("#qyid").val()
				};        	
	        	$('#pagination').datagrid('options').queryParams = queryParams;
	        	$('#pagination').datagrid('clearSelections');
	        	$("#pagination").datagrid('load'); 
	        }
	        
	        $(function(){
	        	$('#deptTree').tree({   
	                url: '${ctx}/jsp/admin/dept/findChildDeptByCurrUser.action?selDept=002', 
	                onBeforeExpand:function(node,param){
	                    $('#deptTree').tree('options').url = "${ctx}/jsp/admin/dept/findChildDeptByCurrUser.action?selDept=" + node.id;
                 	},
                 	onClick:function(node){
                 		$("#cityClick").val("1"),
                 		$("#ifzsqy").val("0");
                 		$("#szzid").val("");
                 		clear_form(document.myform);
                 		window.currentDeptCode=node.id;
                 		console.log(node.id);
                 		$("#szzid").val(node.id);
                 		if(node.id == "002010"||node.id == "002001"||node.id == "002011"){
	                 		$("#ifzsqy").val("1");
                 		}
                 		search_jshxZcyhsb();
               		}
	            });
	        	
	        	$('#pagination').datagrid({
					title:'企业自查隐患上报四清单',
					iconCls:'icon-save',
					nowrap: false,
					striped: true,
					collapsible:true,
					url:'fourList.action',
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
					{field:'szzname',title:'所属地区',width:fixWidth(0.1)},
							 {field:'qymc',title:'企业名称',width:fixWidth(0.15)},
					          {field:'jcsj',title:'检查时间',width:fixWidth(0.1),formatter:function(value,rec){
								if(rec.jcsj==null) return;
								var date = new Date(rec.jcsj.time);
								var month = parseInt(date.getMonth()+1);
								return date.getFullYear()+'年'+month+'月'+date.getDate()+'日  ';}}
								,
								{field:'jcry',title:'检查人员',width:fixWidth(0.1)},
								{field:'mqzt',title:'目前状态',width:fixWidth(0.1),formatter:function(value,rec){
		                             if(rec.mqzt == '0'){
		                             		return "<span style='color:red;cursor:hand'>整改完成</span>";
		                             }else{
		                             	return "<span style='color:green;cursor:hand'>整改中</span>";
		                             }
		                             
	                         	 }},
	                         	{field:'renwuList',title:'任务清单',width:fixWidth(0.08),formatter:function(value,rec){
		                             if(rec.renwuList == '1'){
		                             		return '<icon class="Hui-iconfont Hui-iconfont-gouxuan"></icon>';
		                             }else{
		                             	return '<icon class="Hui-iconfont Hui-iconfont-shenhe-butongguo2"></icon>';
		                             }
		                             
	                         	 }},
	                         	{field:'yinhuanList',title:'隐患清单',width:fixWidth(0.08),formatter:function(value,rec){
		                             if(rec.yinhuanList == '1'){
		                             		return '<icon class="Hui-iconfont Hui-iconfont-gouxuan"></icon>';
		                             }else{
		                             	return '';
		                             }
		                             
	                         	 }},
	                         	{field:'zhenggaiList',title:'整改清单',width:fixWidth(0.08),formatter:function(value,rec){
		                             if(rec.zhenggaiList == '0'){
		                             		return '<icon class="Hui-iconfont Hui-iconfont-gouxuan"></icon>';
		                             }else{
		                             	return '<icon class="Hui-iconfont Hui-iconfont-shenhe-butongguo2"></icon>';
		                             }
		                             
	                         	 }},
	                         	{field:'fuchaList',title:'复查清单',width:fixWidth(0.08),formatter:function(value,rec){
		                             if(rec.fuchaList == '1'){
		                             		return '<icon class="Hui-iconfont Hui-iconfont-gouxuan"></icon>';
		                             }else{
		                             	return '<icon class="Hui-iconfont Hui-iconfont-shenhe-butongguo2"></icon>';
		                             }
		                             
	                         	 }},
	                         	{field:'op',title:'操作',width:fixWidth(0.05),align:'center',formatter:function(value,rec){
	  				        	  var info = "<span style='cursor:hand;color:green;' onclick=\"view('"+rec.id+"')\">详情</span>&nbsp;"
	  							return info;
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
	    </script>
	</head>

	<body>
		<div class="page_content">
		<s:if test = "flag  == 1">
			<div class="layout_01_left">
			<div class="layout_overflow">
				<ul id="deptTree"></ul>
			</div>
			</div>
		</s:if>
			<div class="layout_01_right">
			<div class="layout_overflow">
				<div class="inner6px submitdata">
					<div class="boxBmargin12 cell">
						<form name="myform" method="post">
							<input type="hidden" name="deptCode" value="${dept.deptCode}">
							<input type="hidden" name="ifzsqy" id="ifzsqy" value="0">
							<input type="hidden" name="szzid" id="szzid" value="">
							<input type="hidden" name="cityClick" id="cityClick" value="">
							<table>
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
								<tr>
									<td colspan="4">
										<div class="btn_area_setc">
											<a href="###" class="btn_01" onclick="search_jshxZcyhsb();">查询<b></b></a>
										</div>
									</td>
								</tr>
							</table>
						</form>
					</div>
					
					<div id="pagination"></div>
				</div>
			</div>
			</div>
		</div>
	</body>
</html>

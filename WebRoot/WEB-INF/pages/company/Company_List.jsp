<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>COMPANY管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        var type= "${type}";
        var flag = "${flag}";
        function viewNew(row_Id){
        	var dt=new Date();
        	var url = "${ctx}/jsp/company/companyInitEdit.action?company.id="+row_Id;
        	//window.open(url, '_blank', 'fullscreen=1,titlebar=yes, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');
            //createSimpleWindow("win_agencyInfo",agencyName,"${ctx}/jsp/agencyInfo/agencyInfoView.action?agencyInfo.id="+row_Id+"&dt="+dt.getTime(),900,600);
        	//打开新窗口全屏
           var tmp=window.open("about:blank","","fullscreen=1") 
        	tmp.moveTo(0,0); 
        	tmp.resizeTo(screen.width+20,screen.height); 
        	tmp.focus(); 
        	tmp.location=url; 
        }
        function view(row_Id){
        	/* var grid = $('#pagination');
			var options = grid.datagrid('getPager').data("pagination").options;
			var curr = options.pageNumber; */
       		$.ajax({
              	url : "${ctx}/jsp/company/companyInfo.action?company.id="+row_Id,
              	type: 'post',
                dataType: 'json',
                async : false,
                data:{ 
                },
                error: function(){
                  	alert('错误','查看时出错！');
                },
                success: function(data){
                    if(data.result == 'true'){
                    	viewNew(row_Id);
                    	//parent.parent.parent.parent.window.location.href = "${ctx}/index.action?indexFlag=1&pageNumber="+curr;	
                	}
                	else
                	{
                		alert(data.message);
                	}
                }
           });
        }
        
        function createUser(row_Id)
        {
        	$.ajax({
              	url : "${ctx}/jsp/company/companyUser.action?company.id="+row_Id,
              	type: 'post',
                dataType: 'json',
                async : false,
                data:{ 
                },
                error: function(){
                  	alert('错误','查看时出错！');
                },
                success: function(data){
                    if(data.result == 'true'){	
                    	alert("成功");	
                	}
                	else
                	{
                		alert(data.message);
                	}
                }
           });
        }
         function viewShenhe(row_Id){
        	//var grid = $('#pagination');
			//var options = grid.datagrid('getPager').data("pagination").options;
			//var curr = options.pageNumber;
        	//parent.parent.parent.parent.window.location.href = "${ctx}/jsp/company/companyView.action?company.id="+row_Id+"&type="+type+"&isShenhe=1&pageNumber="+curr;
        	var url ="${ctx}/jsp/company/companyView.action?company.id="+row_Id+"&type="+type+"&isShenhe=1";
        	//打开新窗口全屏
           var tmp=window.open("about:blank","","fullscreen=1") 
         	tmp.moveTo(0,0); 
         	tmp.resizeTo(screen.width+20,screen.height); 
         	tmp.focus(); 
         	tmp.location=url; 
         }
        
        function exportdata()
        {
        	document.myform.action = "${ctx}/jsp/company/companyExport.action";
        	document.myform.submit();
        }
        function importCompany()
        {
       		var location = getCenterLocation(500,450);
            openparentWindow("newWindow","批量导入用户",location.left,location.top,"500","200","${ctx}/jsp/company/initImportCompany.action",true,true,true,false,true,"win");
        }
        
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_company();
        }
        function del(){
        	var rows = $('#pagination').datagrid('getSelections');
        	var ids = "";
			for(var i=0;i<rows.length;i++){
				ids += rows[i].id+"|";
			}
			if(rows.length<1){
				layer.msg('请选择需要删除的数据!', {icon: 0});
			}else{
				layer.confirm('确定要删除该企业？', {
	        		  btn: ['确定','取消'] //按钮
	        		}, function(){
	        			$.ajax({
		                	url : "companyDel.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	ids : ids
		                    },
		                    error: function(){
		                    	layer.msg('删除时出错！', {icon: 2});
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	layer.msg('删除成功', {icon: 1});
		                        	search_company();
		                        }else{
		                        	layer.msg('删除时出错！', {icon: 2});
		                        }
		                    }
		                });
	        		}, function(){
	          		  return;
	        		});
			    /* $.messager.confirm("删除","确定要删除吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "companyDel.action",
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
		                        	search_company();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    }); */
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
        
        function search_company(){
        	var queryParams = {
				"company.companyname": $("#companyname").val(),
				"company.fddbr": $("#fddbr").val(),
				"company.zzjgdm": $("#zzjgdm").val(),
				"company.gszch": $("#gszch").val(),
				"company.qylx": $("#qylx").val(),
				"company.hyfl": $("#hyfl").val(),
				"company.qyzclx": $("#qyzclx").val(),
				"company.whpqylx": $("#whpqylx").val(),
				"company.yhbzjyqy": $("#yhbzjyqy").val(),
				"company.zywhqylx": $("#zywhqylx").val(),
				"queryQyclsjStart" :$("#queryQyclsjStart").val(),
				"queryQyclsjEnd" :$("#queryQyclsjEnd").val(),
				"company.basePass" :$("#basePass").val(),
				"company.dwdz1":$("#dwdz1").val(),
				"company.szc":$("#szc").val(),
				"company.ifwhpqylx":$("#ifwhpqylx").val(),
				"company.ifzywhqylx":$("#ifzywhqylx").val(),
				"company.aqbzdbjb":$("#aqbzdbjb").val(),
				"company.zsqytype":$("#zsqytype").val(),
				"company.ifzsqy":$("#ifzsqy").val(),
				"jglx":$("#jglx").val(),
				"county":$("#county").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
        	var currModuleCode='${currModuleCode}';
        	var pn = '${pageNumber}';
			$('#pagination').datagrid({
				title:'企业信息管理列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'companyQuery.action',
				queryParams:{
					"company.companyname": $("#companyname").val(),
"company.fddbr": $("#fddbr").val(),
"company.zzjgdm": $("#zzjgdm").val(),
"company.gszch": $("#gszch").val(),
"company.qylx": $("#qylx").val(),
"company.hyfl": $("#hyfl").val(),
"company.qyzclx": $("#qyzclx").val(),

"company.zywhqylx": $("#zywhqylx").val(),
"company.whpqylx": $("#whpqylx").val(),
"company.yhbzjyqy": $("#yhbzjyqy").val(),

 "queryQyclsjStart" :$("#queryQyclsjStart").val(),
 "queryQyclsjEnd" :$("#queryQyclsjEnd").val(),
 "company.basePass" :$("#basePass").val(),
  "company.dwdz1":$("#dwdz1").val(),
  "company.szc":$("#szc").val(),
  "company.ifwhpqylx":$("#ifwhpqylx").val(),
  "company.ifzywhqylx":$("#ifzywhqylx").val(),
   "company.aqbzdbjb":$("#aqbzdbjb").val(),
   "company.ifzsqy":$("#ifzsqy").val(),
				"company.feature":$("#feature").val(),
				"jglx":$("#jglx").val(),
				"county":$("#county").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'companyname',title:'单位名称',width:fixWidth(0.2)},
{field:'fddbr',title:'法定代表人',width:fixWidth(0.1)},
{field:'tyshxydm',title:'社会信用代码',width:fixWidth(0.1)},
//{field:'zzjgdm',title:'组织机构代码',width:fixWidth(0.1)},
{field:'dwdz2',title:'单位地址',width:fixWidth(0.1)},
/* {field:'qylx',title:'企业类型',width:fixWidth(0.08),formatter:function(value,rec){
  var temp = '';
    $.ajax({
    url: '${ctx}/jsp/admin/code/findCodeValue.action',
    type: 'post',
    dataType: 'json',
    async : false,
    data:{        "codeValue.itemValue" : rec.qylx,
        "codeValue.codeId" : "4028e56c3ff0d189013ff0e6b99e000c"    },
    error: function(){
        $.messager.alert('提示','获取一维代码错误！');
    },
    success: function(data){
        temp = data.itemText;
    }});
    return temp;}
}, 
{field:'hyfl',title:'行业分类',width:fixWidth(0.08),formatter:function(value,rec){
  var temp = '';
    $.ajax({
    url: '${ctx}/jsp/admin/code/findCodeValue.action',
    type: 'post',
    dataType: 'json',
    async : false,
    data:{        "codeValue.itemValue" : rec.hyfl,
        "codeValue.codeId" : "402880484076bce30140a04236590a02"    },
    error: function(){
        $.messager.alert('提示','获取一维代码错误！');
    },
    success: function(data){
        temp = data.itemText;
    }});
    return temp;}
},*/
{field:'aqbzdbjb',title:'标准化达标级别',width:fixWidth(0.1),align:'center',formatter:function(value,rec){
  var temp = '无';
  if(value=="1"){
	  temp = '一级';
  }else if(value=="2"){
	  temp = '二级';
  }else if(value=="3"){
	  temp = '三级';
  }else{
	  temp = '无';
  }
   /*  $.ajax({
    url: '${ctx}/jsp/admin/code/findCodeValue.action',
    type: 'post',
    dataType: 'json',
    async : false,
    data:{        "codeValue.itemValue" : rec.aqbzdbjb,
        "codeValue.codeId" : "402880484028bef601404c8b68a40047"    },
    error: function(){
        $.messager.alert('提示','获取一维代码错误！');
    },
    success: function(data){
        temp = data.itemText;
    }}); */
    return temp;}
},
{field:'basePass',title:'审核状态',width:fixWidth(0.1),align:'center',formatter:function(value,rec){
					/*var str = "";
				$.ajax({
       				url: '${ctx}/jsp/company/findState.action',
        		    type: 'post',
        		    dataType: 'json',
        		    async : false,
        		    data:{    
        		    	"ids":rec.id
        		    	},
        		    error: function(){
        		    },
        		    success: function(data){
        		    	if(data.state==2){
        		    		str = "<span style='color:red'>未通过</span>";
        		    	}else if(data.state==1 && rec.dutyFlag==1){
        		    		str = "<span style='color:blue'>通过</span>";
        		    	}else{
							str = "<span style='color:green'>待审核</span>";
        		    	}
        		    }});*/

							var str = "";
	        		    	if(rec.basePass==2){
	        		    		str = "<span style='color:red'>未通过</span>";
	        		    	}else if(rec.basePass==1){
	        		    		str = "<span style='color:green'>通过</span>";
	        		    	}else{
								str = "<span style='color:blue'>待审核</span>";
	        		    	}
	        		    	return str;
	        		    	
}},
				         /*  {field:'progress',title:'填报完成度&nbsp;&nbsp;&nbsp;(<span style="color:green">绿色</span>：已填报&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:blue">蓝色</span>：部分填报&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red">红色</span>：未填报)',width:fixWidth(0.43),align:'center',formatter:function(value,rec){
				        	  var progressResult = "";
				        	  $.ajax({
			       				url: '${ctx}/jsp/company/findInputProgress.action',
			        		    type: 'post',
			        		    dataType: 'json',
			        		    async : false,
			        		    data:{    
			        		    	"ids":rec.id
			        		    	},
			        		    error: function(){
			        		    },
			        		    success: function(data){
			        		    	//alert("data.JshxZzbw="+data.JshxZzbw);
			        		    	//1关键装置和重点部位
			        		    	if(data.jshxZzbw==0){//尚未填报
			        		    		progressResult +="<span style='color:red' title='尚未填报'>关键装置和重点部位</span>&nbsp;&nbsp;&nbsp;";
			        		    	}else{//填报完成
			        		    		progressResult +="<span style='color:green' title='填报完成'>关键装置和重点部位</span>&nbsp;&nbsp;&nbsp;";
			        		    	}
			        		    	
			        		    	//2安全警示标志设置情况
			        		    	if(data.safetywarining==0){//尚未填报
			        		    		progressResult +="<span style='color:red' title='尚未填报'>安全警示标志</span>&nbsp;&nbsp;&nbsp;";
			        		    	}else{//填报完成
			        		    		progressResult +="<span style='color:green' title='填报完成'>安全警示标志</span>&nbsp;&nbsp;&nbsp;";
			        		    	}
			        		    	//3培训
			        		    	if(data.px==0){//尚未填报
			        		    		progressResult +="<span style='color:red' title='尚未填报'>培训</span>&nbsp;&nbsp;&nbsp;";
			        		    	}else if(data.px==1){//部分填报完成
			        		    		progressResult +="<span style='color:blue' title='部分填报完成'>培训</span>&nbsp;&nbsp;&nbsp;";
			        		    	}else if(data.px==2){//填报完成
			        		    		progressResult +="<span style='color:green' title='填报完成'>培训</span>&nbsp;&nbsp;&nbsp;";
			        		    	}
			        		    	//4安全管理制度及安全操作规程
			        		    	if(data.glzd==0){//尚未填报
			        		    		progressResult +="<span style='color:red' title='尚未填报'>安全管理制度及安全操作规程</span>&nbsp;&nbsp;&nbsp;";
			        		    	}else if(data.glzd==1){//部分填报完成
			        		    		progressResult +="<span style='color:blue' title='部分填报完成'>安全管理制度及安全操作规程</span>&nbsp;&nbsp;&nbsp;";
			        		    	}else if(data.glzd==2){//填报完成
			        		    		progressResult +="<span style='color:green' title='填报完成'>安全管理制度及安全操作规程</span>&nbsp;&nbsp;&nbsp;";
			        		    	}
			        		    	//5安全设施
			        		    	if(data.aqss==0){//尚未填报
			        		    		progressResult +="<span style='color:red' title='尚未填报'>安全设施</span>&nbsp;&nbsp;&nbsp;";
			        		    	}else if(data.aqss==1){//部分填报完成
			        		    		progressResult +="<span style='color:blue' title='部分填报完成'>安全设施</span>&nbsp;&nbsp;&nbsp;";
			        		    	}else if(data.aqss==2){//填报完成
			        		    		progressResult +="<span style='color:green' title='填报完成'>安全设施</span>&nbsp;&nbsp;&nbsp;";
			        		    	}
			        		    	
			        		    	//5安全设施
			        		    	if(data.zyws==0){//尚未填报
			        		    		progressResult +="<span style='color:red' title='尚未填报'>职业卫生</span>&nbsp;&nbsp;&nbsp;";
			        		    	}else if(data.zyws==1){//部分填报完成
			        		    		progressResult +="<span style='color:blue' title='部分填报完成'>职业卫生</span>&nbsp;&nbsp;&nbsp;";
			        		    	}else if(data.zyws==2){//填报完成
			        		    		progressResult +="<span style='color:green' title='填报完成'>职业卫生</span>&nbsp;&nbsp;&nbsp;";
			        		    	}
			        		    	
			        		    }
			        		   });
							return progressResult;
                          }}, */
				          {field:'op',title:'操作',width:fixWidth(0.15),align:'center',formatter:function(value,rec){
				        	 /*  var tempPassFlag = "";
				        	  $.ajax({
			       				url: '${ctx}/jsp/company/findReviewLogState.action',
			        		    type: 'post',
			        		    dataType: 'json',
			        		    async : false,
			        		    data:{    
			        		    	"ids":rec.id
			        		    	},
			        		    error: function(){
			        		    },
			        		    success: function(data){
			        		    	if(data.state==0){//该用户对该条数据具有审核权限，且尚未审核
			        		    		tempPassFlag="0";
			        		    	}
			        		    }
			        		   }); */
				        	  var info = "<span style='cursor:hand;color:green;' onclick=\"viewNew('"+rec.id+"')\">详情</span>&nbsp;"
								if(("${flag}"=="1"&&rec.basePass != '1')||("${deptflag}"=='1'&&!(rec.basePass == '1'))){//用户具有审核任务权限，对该条数据未审核，该条数据未最终审核通过
									info += "&nbsp;&nbsp;&nbsp;<span style='cursor:hand;color:blue;' onclick=\"viewShenhe('"+rec.id+"')\">审核</span>&nbsp;";
								}
							return info;
                          }}
				        ]],
				pagination:true,
				onLoadSuccess:tabOnloadSuccess,
				onLoadError:tabOnloadSuccess,
				rownumbers:true,
				pageList:[30,20,10],
				pageNumber:pn == '0' ? 1 : pn,
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
        
        
         function querySzc(obj)
    {
    	$.ajax({
				type:"POST",
				url:"${ctx}/jsp/company/companyQuerySzz.action?mode=ajaxJson&company.county="+obj,
				success:function(json){
					json = eval('(' + json + ')');
					var selectContainer = $('#szc'); 
					selectContainer.empty();
					var option = $('<option></option>').text("").val(""); 
					selectContainer.append(option); 
	  				for(var i=0; i<json.length; i++){
						var option = $('<option></option>').text(json[i].name).val(json[i].id); 
						selectContainer.append(option); 
				 	}
				},
				dateType:"json"
			});
    }   
         function querySzz(obj)
	    {
	    	$.ajax({
					type:"POST",
					url:"${ctx}/jsp/company/companyQuerySzz.action?mode=ajaxJson&company.county="+obj,
					success:function(json){
						json = eval('(' + json + ')');
						var selectContainer = $('#dwdz1'); 
						selectContainer.empty();
						var option = $('<option></option>').text("请选择").val(""); 
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
	<div class="submitdata" style="    margin: 10px 0;">
		<table width="100%">
			<tr>
				<th width="5%">所属区域</th>
				<td width="15%">
						<cus:SelectOneTag property="county" style="width:44%;"   defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"/>
						<select id="dwdz1" name="company.dwdz1" style="width:45%;" onchange="querySzc(this.value);"><option value="">请选择</option></select>
						<!--<s:select theme="simple" cssStyle="width:100px;"  headerKey=""  headerValue="请选择" id="szc" emptyOption= "true" name="company.szc" list="%{deptlist}" listKey="deptCode" listValue="deptName" value="{company.szc}"></s:select>-->
						
						<!--hanxc 2014/11/8 
					<cus:SelectOneTag property="company.dwdz1" defaultText='请选择' codeName="相城地址" value="${company.dwdz1}" onchange="querySzc(this.value);" style="width:45%;"/>
					<s:select theme="simple" cssStyle="width:45%;" id="szc" emptyOption= "true" name="company.szc" list="%{deptlist}" listKey="deptCode" listValue="deptName" value="{company.szc}"></s:select>
						 -->
				</td>
				<th width="5%">单位名称</th>
				<td width="15%"><input name="company.companyname" id="companyname"  value="${company.companyname}" type="text" style="width:90%;"></td>
				<th width="5%">审核状态</th>
				<td width="15%"><cus:SelectOneTag property="company.basePass" codeName="审核状态" value="${company.basePass}" style="width:90%;"/></td>
				
				<%-- <th width="5%">企业类型</th>
				<td width="15%"><cus:SelectOneTag property="company.qylx" defaultText='请选择' codeName="企业类型" value="${company.qylx}" style="width:90%;"/></td> --%>
			</tr>
			
			<br/>
			<tr >
				<th width="5%">企业所属监管类型</th>
				<td width="15%" >
					<cus:SelectOneTag style="width:90%;" property="jglx" defaultText='请选择' codeName="监管类型" value="${jglx}" />
				</td>
				<s:if test="%{cityflag eq 1}">
					<th width="5%">是否直属</th>
					<td width="15%" >
						<cus:SelectOneTag style="width:90%;" property="company.ifzsqy" defaultText='请选择' codeName="是否直属" value="${company.ifzsqy}" />
					</td>
				</s:if>
			</tr>
			
			<%-- <tr >
					<th width="5%">是否直属</th>
					<td width="15%" >
						<cus:SelectOneTag style="width:90%;" property="company.ifzsqy" defaultText='请选择' codeName="是否直属" value="${company.ifzsqy}" />
					</td>
			</tr> --%>
			<%-- <tr>
				<th width="5%">行业分类</th>
				<td width="10%"><cus:SelectOneTag property="company.hyfl" defaultText='请选择' codeName="企业行业分类" value="${company.hyfl}" style="width:90%;"/></td>
				<th width="5%">职业危害企业类型</th>
				<td width="15%"><cus:SelectOneTag property="company.zywhqylx" defaultText='请选择' codeName="职业危害企业类型" value="${company.zywhqylx}" style="width:90%;"/></td>
				<th width="5%">危化品企业类型</th>
				<td width="15%"><cus:SelectOneTag property="company.whpqylx" defaultText='请选择' codeName="危化品企业类型" value="${company.whpqylx}" style="width:90%;"/></td>
			</tr> 
			<tr>
				<th width="5%">烟花爆竹企业类型</th>
				<td width="15%"><cus:SelectOneTag property="company.yhbzjyqy" defaultText='请选择' codeName="烟花爆竹企业" value="${company.yhbzjyqy}" style="width:90%;"/></td>
				<th width="5%">企业注册类型</th>
				<td width="15%"><cus:SelectOneTag property="company.qyzclx" defaultText='请选择' codeName="注册类型" value="${company.qyzclx}" style="width:90%;"/></td>
				<th width="5%">审核状态</th>
				<td width="15%"><cus:SelectOneTag property="company.basePass" codeName="审核状态" value="${company.basePass}" style="width:90%;"/></td>
			</tr>
			<tr>
				<th width="5%">是否危化品企业</th>
				<td width="15%"><s:select id="ifwhpqylx" cssStyle="width:90%;" listKey="key" listValue="value"  theme="simple" list="#{'':'请选择','1':'是','0':'否'}" name="company.ifwhpqylx"/></td>
				<th width="5%">是否职业危害企业</th>
				<td width="15%"><s:select id="ifzywhqylx" cssStyle="width:90%;" listKey="key" listValue="value"  theme="simple" list="#{'':'请选择','1':'是','0':'否'}" name="company.ifzywhqylx"/></td>
				<th width="5%">安全生产标准化达标级别</th>
				<td width="15%"><cus:SelectOneTag property="company.aqbzdbjb" defaultText='请选择' codeName="标准化达标级别" value="${company.aqbzdbjb}" style="width:90%;"/></td>
			</tr>
			<tr >
					<th width="5%">直属等级</th>
					<td width="15%" >
						<cus:SelectOneTag style="width:90%;" property="company.zsqytype" defaultText='请选择' codeName="直属等级" value="${company.zsqytype}" />
					</td>
					<th width="5%">企业特征</th>
					<td width="15%" >
						<cus:SelectOneTag style="width:150px;" property="company.feature" defaultText='请选择' codeName="企业特征" value="${company.feature}" />
					</td>
			</tr>--%>
			<tr>
				<td colspan="8" style="text-align:center">
				 
				<a href="###" class="easyui-linkbutton" onclick="search_company();" iconCls="icon-search" >查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				<s:if test="%{type eq 1 || flag eq 1}">
					<a href="###" class="easyui-linkbutton" onclick="del();" iconCls="icon-remove">删除</a>&nbsp;
				</s:if>
				<a href="###" class="easyui-linkbutton" onclick="exportdata();" iconCls="icon-add">导出</a>&nbsp;
				<!--<a href="###" class="easyui-linkbutton" onclick="importCompany();" iconCls="icon-add">导入</a>&nbsp;-->
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

<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>尾矿库信息管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        var type= "${type}";
        function addNew(){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 400) / 2;
    		var yc = (ah - 350) / 2;
            //openparentWindow("newWindow","添加尾矿库信息",xc,yc,"400","350","${ctx}/jsp/tailinglibinfo/tailingLibInfoInitEdit.action?flag=add&dt="+dt.getTime(),true,true,true,false,true,"win");
        	window.open("${ctx}/jsp/tailinglibinfo/tailingLibInfoInitEdit.action?flag=add&dt="+dt.getTime());
        }
        function edit(row_Id){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 400) / 2;
    		var yc = (ah - 350) / 2;
            //openparentWindow("newWindow","修改尾矿库信息",xc,yc,"400","350","${ctx}/jsp/tailinglibinfo/tailingLibInfoInitEdit.action?flag=mod&tailingLibInfo.id="+row_Id+"&dt="+dt.getTime(),true,true,true,false,true,"win");
        	window.open("${ctx}/jsp/tailinglibinfo/tailingLibInfoInitEdit.action?flag=mod&tailingLibInfo.id="+row_Id+"&dt="+dt.getTime());
        }
        function view(row_Id){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 400) / 2;
    		var yc = (ah - 350) / 2;
            //openparentWindow("newWindow","查看尾矿库信息",xc,yc,"400","350","${ctx}/jsp/tailinglibinfo/tailingLibInfoView.action?tailingLibInfo.id="+row_Id+"&dt="+dt.getTime(),true,true,true,false,true,"win");
        	window.open("${ctx}/jsp/tailinglibinfo/tailingLibInfoView.action?tailingLibInfo.id="+row_Id+"&dt="+dt.getTime());
        	
        }
        
        /**
		 *@param row_id 传入的参数id，也可以传入多个
		 *@param actionurl 每个大tab对应的访问地址
		 *@param tabid 需要传入的参数 （对应你自己的action）
		 *@param tabname 每个action对应tab的名字
		 *@param winid 弹出窗口对应的id
		 *@param winname 弹出窗口对应的名字
		 *@param 所有参数均可以|符号隔开，但是必须一一对应

		     特殊字符可以用16进制编码替换
			 1. + URL 中+号表示空格 %2B 
			 2. 空格 URL中的空格可以用+号或者编码 %20 
			 3. / 分隔目录和子目录 %2F 
			 4. ? 分隔实际的 URL 和参数 %3F 
			 5. % 指定特殊字符 %25 
			 6. # 表示书签 %23 
			 7. & URL 中指定的参数间的分隔符 %26 
			 8. = URL 中指定参数的值 %3D
		 */
        
        function view1(row_Id){
        	var dt=new Date();
            
        	var proctx = "${ctx}"
            	
        	var actionurl = proctx+"/jsp/tailinglibinfo/tailingLibInfoView.action?tailingLibInfo.id="+row_Id+"&dt="+dt.getTime()+"|"+proctx+"/jsp/tailinglibinfo/tailingLibInfoView.action?tailingLibInfo.id="+row_Id+"&dt="+dt.getTime();
        	var tabid = "tailingLibInfo.id="+row_Id+"&aaa=bbb|tailingLibInfo.id="+row_Id;
        	var tabname = "变电站基本信息|合同信息";
        	
        	var winname = "查看变电站基本信息表";
        	var winid = "win_lgSubstationbaseinfo";

        	var actionpost =  encodeURI("${ctx}/jsp/tailinglibinfo/tailingLibInfoView.action?actionurl="+actionurl+"&tabid="+tabid+"&tabname="+tabname);
        	actionpost =  encodeURI(actionpost);
            //openparentWindow(winid,winname,actionpost,700,600);
            
            
            
            var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 400) / 2;
    		var yc = (ah - 350) / 2;
            openparentWindow(winid,winname,xc,yc,"400","350",actionpost,true,true,true,false,true,"win");
        	
        }
        function shenhe(row_Id){
        	window.open("${ctx}/jsp/tailinglibinfo/tailingLibInfoShenhe.action?tailingLibInfo.id="+row_Id+"&type="+type+"&isShenhe=1");
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_tailingLibInfo();
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
		                	url : "tailingLibInfoDel.action",
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
		                        	search_tailingLibInfo();
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
        
        function search_tailingLibInfo(){
        	var queryParams = {
				"tailingLibInfo.baseLocal": $("#baseLocal").val(),
"tailingLibInfo.baseTrade": $("#baseTrade").val(),
"tailingLibInfo.baseTailingType": $("#baseTailingType").val(),
"tailingLibInfo.baseTailingLevel": $("#baseTailingLevel").val(),
"tailingLibInfo.baseSafetyDegree": $("#baseSafetyDegree").val(),
"tailingLibInfo.baseBuildDamType": $("#baseBuildDamType").val(),
 "queryBaseProduceStartdateStart" :$("#queryBaseProduceStartdateStart").val(),
 "queryBaseProduceStartdateEnd" :$("#queryBaseProduceStartdateEnd").val(),
 "tailingLibInfo.baseCompany" :$("#baseCompany").val(),
 "tailingLibInfo.baseLegalPerson" :$("#baseLegalPerson").val(),
 "tailingLibInfo.baseMainbody" :$("#baseMainbody").val(),
"tailingLibInfo.baseTailingHead": $("#baseTailingHead").val(),
"tailingLibInfo.baseTailingHeadTel": $("#baseTailingHeadTel").val(),
"tailingLibInfo.baseCountyHead": $("#baseCountyHead").val(),
"tailingLibInfo.baseCountyHeadTel": $("#baseCountyHeadTel").val(),
"tailingLibInfo.baseTownHead": $("#baseTownHead").val(),
"tailingLibInfo.baseTownHeadTel": $("#baseTownHeadTel").val(),
 "queryBaseSafetyLicStartdateStart" :$("#queryBaseSafetyLicStartdateStart").val(),
 "queryBaseSafetyLicStartdateEnd" :$("#queryBaseSafetyLicStartdateEnd").val(),
"tailingLibInfo.baseSafetyLicValidity": $("#baseSafetyLicValidity").val(),
"tailingLibInfo.baseStandardLevel": $("#baseStandardLevel").val(),
"tailingLibInfo.baseTailingBelong": $("#baseTailingBelong").val(),
"tailingLibInfo.baseTailingLong": $("#baseTailingLong").val(),
"tailingLibInfo.baseTailingLat": $("#baseTailingLat").val(),
"tailingLibInfo.baseTailingImg": $("#baseTailingImg").val(),
"tailingLibInfo.paraDesignTime": $("#paraDesignTime").val(),
"tailingLibInfo.paraFactTime": $("#paraFactTime").val(),
"tailingLibInfo.paraDesignTotal": $("#paraDesignTotal").val(),
"tailingLibInfo.paraAlready": $("#paraAlready").val(),
"tailingLibInfo.paraFinalHigh": $("#paraFinalHigh").val(),
"tailingLibInfo.paraSafetyHigh": $("#paraSafetyHigh").val(),
"tailingLibInfo.paraMoistenHigh": $("#paraMoistenHigh").val(),
"tailingLibInfo.paraLength": $("#paraLength").val(),
"tailingLibInfo.paraStackType": $("#paraStackType").val(),
"tailingLibInfo.paraDrainfloods": $("#paraDrainfloods").val(),
"tailingLibInfo.primeDamHigh": $("#primeDamHigh").val(),
"tailingLibInfo.primeDamLength": $("#primeDamLength").val(),
"tailingLibInfo.primeDamWidth": $("#primeDamWidth").val(),
"tailingLibInfo.primeInnerSlope": $("#primeInnerSlope").val(),
"tailingLibInfo.primeOutSlope": $("#primeOutSlope").val(),
"tailingLibInfo.accumulateDesignHigh": $("#accumulateDesignHigh").val(),
"tailingLibInfo.accumulateFactHigh": $("#accumulateFactHigh").val(),
"tailingLibInfo.accumulateDamLength": $("#accumulateDamLength").val(),
"tailingLibInfo.accumulateInnerSlope": $("#accumulateInnerSlope").val(),
"tailingLibInfo.accumulateOutSlope": $("#accumulateOutSlope").val(),
"tailingLibInfo.accumulateDamWidth": $("#accumulateDamWidth").val(),
"tailingLibInfo.checkDanger": $("#checkDanger").val(),
"tailingLibInfo.checkPercolation": $("#checkPercolation").val(),
"tailingLibInfo.checkBeautyspot": $("#checkBeautyspot").val(),
"tailingLibInfo.checkFamilyNo": $("#checkFamilyNo").val(),
"tailingLibInfo.checkPersonNo": $("#checkPersonNo").val(),
"tailingLibInfo.checkRoadNo": $("#checkRoadNo").val(),
"tailingLibInfo.checkRailwayNo": $("#checkRailwayNo").val(),
"tailingLibInfo.checkSchoolNo": $("#checkSchoolNo").val(),
"tailingLibInfo.checkFactoryNo": $("#checkFactoryNo").val(),
"tailingLibInfo.checkDrainfloodsState": $("#checkDrainfloodsState").val(),
"tailingLibInfo.checkMonitorEquipment": $("#checkMonitorEquipment").val(),
"tailingLibInfo.checkAqxzUnit": $("#checkAqxzUnit").val(),
"tailingLibInfo.checkYpjbgbzUnit": $("#checkYpjbgbzUnit").val(),
"tailingLibInfo.checkYpjbaUnit": $("#checkYpjbaUnit").val(),
"tailingLibInfo.checkCbsjbzUnit": $("#checkCbsjbzUnit").val(),
"tailingLibInfo.checkCbsjspUnit": $("#checkCbsjspUnit").val(),
"tailingLibInfo.checkJgyspjbgbzUnit": $("#checkJgyspjbgbzUnit").val(),
"tailingLibInfo.checkJgyspjspUnit": $("#checkJgyspjspUnit").val(),
"tailingLibInfo.checkTdsyspUnit": $("#checkTdsyspUnit").val(),
"tailingLibInfo.checkHbysspUnit": $("#checkHbysspUnit").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'尾矿库信息列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'tailingLibInfoQuery.action',
				queryParams:{
					"tailingLibInfo.baseLocal": $("#baseLocal").val(),
"tailingLibInfo.baseTrade": $("#baseTrade").val(),
"tailingLibInfo.baseTailingType": $("#baseTailingType").val(),
"tailingLibInfo.baseTailingLevel": $("#baseTailingLevel").val(),
"tailingLibInfo.baseSafetyDegree": $("#baseSafetyDegree").val(),
"tailingLibInfo.baseBuildDamType": $("#baseBuildDamType").val(),
 "queryBaseProduceStartdateStart" :$("#queryBaseProduceStartdateStart").val(),
 "queryBaseProduceStartdateEnd" :$("#queryBaseProduceStartdateEnd").val(),
 "tailingLibInfo.baseCompany" :$("#baseCompany").val(),
 "tailingLibInfo.baseLegalPerson" :$("#baseLegalPerson").val(),
 "tailingLibInfo.baseMainbody" :$("#baseMainbody").val(),
"tailingLibInfo.baseTailingHead": $("#baseTailingHead").val(),
"tailingLibInfo.baseTailingHeadTel": $("#baseTailingHeadTel").val(),
"tailingLibInfo.baseCountyHead": $("#baseCountyHead").val(),
"tailingLibInfo.baseCountyHeadTel": $("#baseCountyHeadTel").val(),
"tailingLibInfo.baseTownHead": $("#baseTownHead").val(),
"tailingLibInfo.baseTownHeadTel": $("#baseTownHeadTel").val(),
 "queryBaseSafetyLicStartdateStart" :$("#queryBaseSafetyLicStartdateStart").val(),
 "queryBaseSafetyLicStartdateEnd" :$("#queryBaseSafetyLicStartdateEnd").val(),
"tailingLibInfo.baseSafetyLicValidity": $("#baseSafetyLicValidity").val(),
"tailingLibInfo.baseStandardLevel": $("#baseStandardLevel").val(),
"tailingLibInfo.baseTailingBelong": $("#baseTailingBelong").val(),
"tailingLibInfo.baseTailingLong": $("#baseTailingLong").val(),
"tailingLibInfo.baseTailingLat": $("#baseTailingLat").val(),
"tailingLibInfo.baseTailingImg": $("#baseTailingImg").val(),
"tailingLibInfo.paraDesignTime": $("#paraDesignTime").val(),
"tailingLibInfo.paraFactTime": $("#paraFactTime").val(),
"tailingLibInfo.paraDesignTotal": $("#paraDesignTotal").val(),
"tailingLibInfo.paraAlready": $("#paraAlready").val(),
"tailingLibInfo.paraFinalHigh": $("#paraFinalHigh").val(),
"tailingLibInfo.paraSafetyHigh": $("#paraSafetyHigh").val(),
"tailingLibInfo.paraMoistenHigh": $("#paraMoistenHigh").val(),
"tailingLibInfo.paraLength": $("#paraLength").val(),
"tailingLibInfo.paraStackType": $("#paraStackType").val(),
"tailingLibInfo.paraDrainfloods": $("#paraDrainfloods").val(),
"tailingLibInfo.primeDamHigh": $("#primeDamHigh").val(),
"tailingLibInfo.primeDamLength": $("#primeDamLength").val(),
"tailingLibInfo.primeDamWidth": $("#primeDamWidth").val(),
"tailingLibInfo.primeInnerSlope": $("#primeInnerSlope").val(),
"tailingLibInfo.primeOutSlope": $("#primeOutSlope").val(),
"tailingLibInfo.accumulateDesignHigh": $("#accumulateDesignHigh").val(),
"tailingLibInfo.accumulateFactHigh": $("#accumulateFactHigh").val(),
"tailingLibInfo.accumulateDamLength": $("#accumulateDamLength").val(),
"tailingLibInfo.accumulateInnerSlope": $("#accumulateInnerSlope").val(),
"tailingLibInfo.accumulateOutSlope": $("#accumulateOutSlope").val(),
"tailingLibInfo.accumulateDamWidth": $("#accumulateDamWidth").val(),
"tailingLibInfo.checkDanger": $("#checkDanger").val(),
"tailingLibInfo.checkPercolation": $("#checkPercolation").val(),
"tailingLibInfo.checkBeautyspot": $("#checkBeautyspot").val(),
"tailingLibInfo.checkFamilyNo": $("#checkFamilyNo").val(),
"tailingLibInfo.checkPersonNo": $("#checkPersonNo").val(),
"tailingLibInfo.checkRoadNo": $("#checkRoadNo").val(),
"tailingLibInfo.checkRailwayNo": $("#checkRailwayNo").val(),
"tailingLibInfo.checkSchoolNo": $("#checkSchoolNo").val(),
"tailingLibInfo.checkFactoryNo": $("#checkFactoryNo").val(),
"tailingLibInfo.checkDrainfloodsState": $("#checkDrainfloodsState").val(),
"tailingLibInfo.checkMonitorEquipment": $("#checkMonitorEquipment").val(),
"tailingLibInfo.checkAqxzUnit": $("#checkAqxzUnit").val(),
"tailingLibInfo.checkYpjbgbzUnit": $("#checkYpjbgbzUnit").val(),
"tailingLibInfo.checkYpjbaUnit": $("#checkYpjbaUnit").val(),
"tailingLibInfo.checkCbsjbzUnit": $("#checkCbsjbzUnit").val(),
"tailingLibInfo.checkCbsjspUnit": $("#checkCbsjspUnit").val(),
"tailingLibInfo.checkJgyspjbgbzUnit": $("#checkJgyspjbgbzUnit").val(),
"tailingLibInfo.checkJgyspjspUnit": $("#checkJgyspjspUnit").val(),
"tailingLibInfo.checkTdsyspUnit": $("#checkTdsyspUnit").val(),
"tailingLibInfo.checkHbysspUnit": $("#checkHbysspUnit").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'baseLocal',title:'具体位置',width:fixWidth(0.18),align:'center'},
							{field:'baseTrade',title:'所属行业',width:fixWidth(0.16),align:'center'},
							{field:'baseTailingType',title:'库类型',width:fixWidth(0.16),align:'center'},
							{field:'baseTailingLevel',title:'库级别',width:fixWidth(0.13),align:'center'},
							{field:'baseSafetyDegree',title:'安全度',width:fixWidth(0.13),align:'center'},
							{field:'state',title:'状态',width:fixWidth(0.1),formatter:function(value,rec){
								var str = "";
								$.ajax({
				       				url: '${ctx}/jsp/tailinglibinfo/findState.action',
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
				        		    }});
									return str;
						                          }},
				          {field:'op',title:'操作',width:fixWidth(0.13),align:'center',formatter:function(value,rec){
	                                var tempPassFlag = "9";
									$.ajax({
					       				url: '${ctx}/jsp/tailinglibinfo/findState.action',
					        		    type: 'post',
					        		    dataType: 'json',
					        		    async : false,
					        		    data:{    
					        		    	"ids":rec.id
					        		    	},
					        		    error: function(){
					        		    },
					        		    success: function(data){
					        		    	if(data.state==1){
					        		    		tempPassFlag="1";
					        		    	}else if(data.state==0 || data.state==2){
					        		    		tempPassFlag="2";
					        		    	}else{
					        		    		tempPassFlag="3";
						        		    }
					        		    }});
										
									var info = "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;"
									if(${deptflag}=='2'){
									
										info += "<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>&nbsp;";
									}
									if(${deptflag}=='1'&&tempPassFlag=='2'&&!(rec.state == '1'&&rec.dutyFlag=='1')){
									
										info += "<span style='color:red;cursor:hand' onclick=\"shenhe('"+rec.id+"')\">审核</span>&nbsp;";
									}
									return info;
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
					        		if(rec.state == '0'){
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
										}
					        	 return str;
					        	 **/
	                             // return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>";
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
    </script>
</head>

<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>

<form name="myform" method="post">
	<div class="submitdata">
		<table width="100%">
			<tr>
				<th width="15%">具体位置</th>
				<td width="35%"><input name="tailingLibInfo.baseLocal" id="baseLocal" value="${tailingLibInfo.baseLocal}" type="text"></td>
				<th width="15%">所属行业</th>
				<td width="35%"><input name="tailingLibInfo.baseTrade" id="baseTrade" value="${tailingLibInfo.baseTrade}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">库类型</th>
				<td width="35%"><input name="tailingLibInfo.baseTailingType" id="baseTailingType" value="${tailingLibInfo.baseTailingType}" type="text"></td>
				<th width="15%">库级别</th>
				<td width="35%"><input name="tailingLibInfo.baseTailingLevel" id="baseTailingLevel" value="${tailingLibInfo.baseTailingLevel}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">安全度</th>
				<td width="35%"><input name="tailingLibInfo.baseSafetyDegree" id="baseSafetyDegree" value="${tailingLibInfo.baseSafetyDegree}" type="text"></td>
				<th width="15%">筑坝方式</th>
				<td width="35%"><input name="tailingLibInfo.baseBuildDamType" id="baseBuildDamType" value="${tailingLibInfo.baseBuildDamType}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">投产日期</th>
				<td width="35%"><input name="queryBaseProduceStartdateStart" id="queryBaseProduceStartdateStart" value="<fmt:formatDate type='both' value='${queryBaseProduceStartdateStart}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queryBaseProduceStartdateEnd\')}'})" >
					-<input name="queryBaseProduceStartdateEnd" id="queryBaseProduceStartdateEnd" value="<fmt:formatDate type='both' value='${queryBaseProduceStartdateEnd}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queryBaseProduceStartdateStart\')}'})" ></td>
				<th width="15%">所属企业</th>
				<td width="35%">
					<input name="tailingLibInfo.baseCompany" id="baseCompany" value="${tailingLibInfo.baseCompany}" type="text">
					</td>
			</tr>
			<tr>
				<th width="15%">企业法人</th>
				<td width="35%">
					<input name="tailingLibInfo.baseLegalPerson" id="baseLegalPerson" value="${tailingLibInfo.baseLegalPerson}" type="text">
					</td>
				<th width="15%">是否有主体矿山</th>
				<td width="35%">
					<input name="tailingLibInfo.baseMainbody" id="baseMainbody" value="${tailingLibInfo.baseMainbody}" type="text">
					</td>
			</tr>
			
			<tr>
			</tr>
			<tr>
				<td colspan="4" style="text-align: center;">
				<a href="###" class="easyui-linkbutton" onclick="search_tailingLibInfo()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;
				<c:if test="${deptflag==2}">				
					<a href="###" class="easyui-linkbutton" onclick="addNew();" iconCls="icon-add">添加</a>&nbsp;
					<a href="###" class="easyui-linkbutton" onclick="del();" iconCls="icon-remove">删除</a>
				</c:if>
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

<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>综合查询</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
		function changDiv(n){
			document.getElementById('qyxx').style.display="none";
			document.getElementById('zzjghzz').style.display="none";
			document.getElementById('jypx').style.display="none";
			document.getElementById('yhpchzl').style.display="none";
			document.getElementById('qyzdwxyjk').style.display="none";
			document.getElementById('qt').style.display="none";
			if(n=='1'){
				qyxx();
			}else if(n=='2'){
				zzjghzz();
			}else if(n=='3'){
				jypx();
			}else if(n=='4'){
				yhpchzl();
			}else if(n=='5'){
				qyzdwxyjk();
			}else{
				document.getElementById('qt').style.display="";
			}
		}
		
		 function view0(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_intSit","查看行政许可及资质情况","${ctx}/jsp/zzqk/intSitView.action?intSit.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
		 function view1(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_honRec","查看荣誉表彰信息","${ctx}/jsp/rybzxx/honRecView.action?honRec.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
		 function view2(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_secProGoa","查看企业安全生产目标","${ctx}/jsp/qyaqscmb/secProGoaView.action?secProGoa.id="+row_Id+"&dt="+dt.getTime(),800,500);
        	
        }
       function view3(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_proCom","查看企业安全生产承诺","${ctx}/jsp/qyaqsccn/proComView.action?proCom.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
        function view4(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_companyScore","查看标准化评分","${ctx}/jsp/companyScore/companyScoreView.action?companyScore.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
         function view5(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_enterpriseGrade","查看安全生产信用评分","${ctx}/jsp/enterpriseGrade/enterpriseGradeView.action?enterpriseGrade.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
        function viewa1(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_proLegOrg","查看安全生产领导机构","${ctx}/jsp/aqscldjg/proLegOrgView.action?proLegOrg.id="+row_Id+"&dt="+dt.getTime(),800,500);
        	
        }
        
        function viewa2(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_proManOrg","查看安全生产管理机构","${ctx}/jsp/aqscgljg/proManOrgView.action?proManOrg.id="+row_Id+"&dt="+dt.getTime(),800,350); 
        	
        }
        function viewa3(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_entChaPer","查看企业主要负责人","${ctx}/jsp/qyzyfzr/entChaPerView.action?entChaPer.id="+row_Id+"&dt="+dt.getTime(),700,480);
        	
        }
        function viewa4(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_secProCha","查看安全生产分管负责人","${ctx}/jsp/aqscfgfzr/secProChaView.action?secProCha.id="+row_Id+"&dt="+dt.getTime(),700,480);
        	
        }
         function viewa5(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_secProMan","查看安全生产管理经理","${ctx}/jsp/aqscgljl/secProManView.action?secProMan.id="+row_Id+"&dt="+dt.getTime(),700,480);
        	
        }
        
        function viewa6(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_secProMaj","查看安全生产总监","${ctx}/jsp/aqsczj/secProMajView.action?secProMaj.id="+row_Id+"&dt="+dt.getTime(),700,480);
        	
        }
        
         function viewa7(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_secProChaPer","查看安全生产管理人员","${ctx}/jsp/aqscfzry/secProChaPerView.action?secProChaPer.id="+row_Id+"&dt="+dt.getTime(),700,480);
        	
        }
        
        function viewa8(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_speJobPer","查看特种作业人员","${ctx}/jsp/tzzyry/speJobPerView.action?speJobPer.id="+row_Id+"&dt="+dt.getTime(),700,550);
        	
        }
        
        function viewb1(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_maiChaTra","查看企业主要负责人培训","${ctx}/jsp/qyzyfzrpx/maiChaTraView.action?maiChaTra.id="+row_Id+"&dt="+dt.getTime(),800,500);
        	
        }
        function viewb2(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_chargeTraining","查看安全生产分管负责人培训","${ctx}/jsp/aqscfgfzrpx/chargeTrainingView.action?chargeTraining.id="+row_Id+"&dt="+dt.getTime(),800,500);
        	
        }
        function viewb3(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_managerTraining","查看安全生产管理经理培训","${ctx}/jsp/aqscgljlpx/managerTrainingView.action?managerTraining.id="+row_Id+"&dt="+dt.getTime(),800,500);
        	
        }
        function viewb4(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_majordomoTraining","查看安全生产总监培训","${ctx}/jsp/aqsczjpx/majordomoTrainingView.action?majordomoTraining.id="+row_Id+"&dt="+dt.getTime(),800,500);
        	
        }
         function viewb5(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_secProChaTra","查看安全生产负责人员培训","${ctx}/jsp/aqscfzrypx/secProChaTraView.action?secProChaTra.id="+row_Id+"&dt="+dt.getTime(),800,500);
        	
        }
        function viewb6(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_speJobTra","查看企业特种人员培训","${ctx}/jsp/qytzrypx/speJobTraView.action?speJobTra.id="+row_Id+"&dt="+dt.getTime(),800,500);
        	
        }
        function viewb7(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_teaLeaTra","查看班组长培训","${ctx}/jsp/bzzpx/teaLeaTraView.action?teaLeaTra.id="+row_Id+"&dt="+dt.getTime(),800,450);
        	
        }
        
         function viewb8(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_newWorTra","查看新员工上岗培训","${ctx}/jsp/xygsgpx/newWorTraView.action?newWorTra.id="+row_Id+"&dt="+dt.getTime(),800,500);
        	
        }
        
        function viewb9(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_allMenTra","查看企业内部全员培训","${ctx}/jsp/qynbqypx/allMenTraView.action?allMenTra.id="+row_Id+"&dt="+dt.getTime(),800,500);
        	
        }
        
         function viewc1(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_danPlaMan","查看巡查计划","${ctx}/jsp/wxyxcjhgl/danPlaManView.action?danPlaMan.id="+row_Id+"&dt="+dt.getTime(),700,400);
         }	
         
        function viewc2(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_danTasMan","查看巡查任务详情","${ctx}/jsp/wxyxcrwgl/danTasManView.action?danTasMan.id="+row_Id+"&dt="+dt.getTime(),700,350);
        	
        }
        
         function viewc3(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_troMan","查看隐患","${ctx}/jsp/yhb/troManView.action?troMan.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
        } 
        
        function viewc4(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_dot","查看巡检点管理","${ctx}/jsp/dot/dotView.action?dot.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        
        function viewc5(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_road","查看巡查路线管理","${ctx}/jsp/road/roadView.action?road.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        
         function viewc6(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_patrolUser","查看巡查人员管理","${ctx}/jsp/patrolUser/patrolUserView.action?patrolUser.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        
        function viewd1(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_comDanIde","查看企业重点危险源的识别评估分级","${ctx}/jsp/zdwxysbpgfj/comDanIdeView.action?comDanIde.id="+row_Id+"&dt="+dt.getTime(),1000,500);
        	
        }
        
        function viewd2(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_comManSys","查看企业重点危险源的管理制度","${ctx}/jsp/zdwxyglzd/comManSysView.action?comManSys.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
        function viewd3(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_comOpeRul","查看企业重点危险源的操作规程","${ctx}/jsp/zdwxyczgc/comOpeRulView.action?comOpeRul.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
        
        function viewd4(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_comDanEme","查看企业重点危险源的应急处置","${ctx}/jsp/zdwxyyjcz/comDanEmeView.action?comDanEme.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
        
        
        window.autoDatagridHeight = <%=(String)session.getAttribute("autoDatagridHeight")%>;
        $(function(){
        	//qyxx();
		});
		
		function qyxx(){
			debugger;
			document.getElementById('qyxx').style.display="";
			$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='xzxkjzzqklb' >行政许可及资质情况列表</a>",
				url:'${ctx}/jsp/zzqk/intSitQuery.action',
				height:'auto',
				width:'auto',
				queryParams:{
					"intSit.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
						{field:'companyName',title:'企业名称',width:100},
						{field:'intelligenceCardnum',title:'证书编号',width:100},
						{field:'intelligenceCardname',title:'证书名称',width:100},
						{field:'intelligenceType',title:'资质类型',width:100},
						{field:'auditState',title:'审核状态',width:100},
						{field:'op',title:'操作',width:60,formatter:function(value,rec){
							return "<a class='btn_02_mini' onclick=view0('"+rec.id+"') >查看<b></b></a>";
							}}
				        ]]
			})); 
			
			$('#pagination1').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='rybzxxlb'>荣誉表彰信息列表</a>",
				url:'${ctx}/jsp/rybzxx/honRecQuery.action',
				queryParams:{
					"honRec.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
						{field:'companyName',title:'企业名称',width:100},
						{field:'bzyear',title:'年度',width:100,formatter:function(value,rec){return value.substring(0,4);}},
						{field:'honor',title:'荣誉称号',width:100},
						{field:'recognitionDept',title:'表彰部门',width:100},
						{field:'auditState',title:'审核状态',width:100},
						{field:'op',title:'操作',width:60,formatter:function(value,rec){
								return "<a class='btn_02_mini' onclick=view1('"+rec.id+"') >查看<b></b></a>";
							}}
				        ]]
			})); 
			
			$('#pagination2').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='qyaqscmblb'>企业安全生产目标列表</a>",
				url:'${ctx}/jsp/qyaqscmb/secProGoaQuery.action',
				queryParams:{
					"secProGoa.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
						{field:'companyName',title:'企业名称',width:100},
						{field:'productGoalYear',title:'年度',width:100,formatter:function(value,rec){return value.substring(0,4);}},
						{field:'productGoalYearGoal',title:'年度目标',width:100,formatter:function(value,rec){
						if(value.length>20)
						{
							return value.substring(0,20)+"...";
						}
						else
						{
							return value;
						}
						}},
						{field:'op',title:'操作',width:60,formatter:function(value,rec){
							return "<a class='btn_02_mini' onclick=view2('"+rec.id+"') >查看<b></b></a>";
						}}
				        ]]
			})); 
			
			
			$('#pagination3').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='qyaqsccn'>企业安全生产承诺列表</a>",
				url:'${ctx}/jsp/qyaqsccn/proComQuery.action',
				queryParams:{
					"proCom.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				          {field:'areaId',title:'所在区域',width:100},
						{field:'companyName',title:'企业名称',width:100},
						{field:'commitmentYear',title:'年度',width:100,formatter:function(value,rec){return value.substring(0,4);}},
						{field:'commitmentContent',title:'承诺内容',width:100,formatter:function(value,rec){
						if(value.length>20)
						{
							return value.substring(0,20)+"...";
						}
						else
						{
							return value;
						}
						}},
						{field:'op',title:'操作',width:60,formatter:function(value,rec){
							var button2="<a class='btn_02_mini' onclick=view3('"+rec.id+"') >查看<b></b></a>";
							return button2;
						}}					     
						   ]]
			})); 
			
			
			$('#pagination4').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='companyScore'>标准化评分列表</a>",
				url:'${ctx}/jsp/companyScore/companyScoreQuery.action',
				queryParams:{
					"companyScore.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				 {field:'areaName',title:'所在区域',width:100},
				          {field:'companyName',title:'企业名称',width:100},
				          {field:'startTime',title:'评分时间',width:100,formatter:function(value,rec){
				          		return value.substring(0,10) + "~" + rec.endTime.substring(0,10);
							}},	
				          {field:'state',title:'状态',width:100,formatter:function(value,rec){
				          		if(rec.state=='1'){
				          			return "待审核";
				          		}else{
				          			return "已审核";
				          		}
							}},	
							{field:'zpzf',title:'自评总分',width:100},			   
							{field:'ajzf',title:'安监总分',width:100},			          
							{field:'op',title:'操作',width:60,formatter:function(value,rec){
									return "<a class='btn_02_mini' onclick=view4('"+rec.id+"') >查看<b></b></a>";
							
							}}
				        ]]
			})); 
		
			$('#pagination5').datagrid($.extend(window.dg_cm_pp,{
				title:'<a name="1">安全生产信用管理列表</a>',
				url:'${ctx}/jsp/enterpriseGrade/enterpriseGradeQuery.action',
				queryParams:{
					"enterpriseGrade.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				 {field:'areaName',title:'所在区域',width:100},
				          {field:'companyName',title:'企业名称',width:100},
				          {field:'startTime',title:'评分时间',width:100,formatter:function(value,rec){
				          		return value.substring(0,10) + "~" + rec.endTime.substring(0,10);
							}},	
				          {field:'state',title:'状态',width:100,formatter:function(value,rec){
				          		if(rec.state=='1'){
				          			return "待审核";
				          		}else{
				          			return "已审核";
				          		}
				          
							}},	
							{field:'zpzf',title:'自评总分',width:100},			   
							{field:'ajzf',title:'安监总分',width:100},			          
							{field:'op',title:'操作',width:60,formatter:function(value,rec){
								return "<a class='btn_02_mini' onclick=view5('"+rec.id+"') >查看<b></b></a>";
							}}
				        ]]
			})); 
		
		}
		
		
		
		function zzjghzz(){
			document.getElementById('zzjghzz').style.display="";
			
			$('#paginationa1').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='aqscldjg' >安全生产领导机构列表</a>",
				url:'${ctx}/jsp/aqscldjg/proLegOrgQuery.action',
				queryParams:{
					"proLegOrg.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				          {field:'areaId',title:'所在区域',width:100},
						{field:'companyName',title:'企业名称',width:100},
						{field:'orgenizationName',title:'机构名称',width:100},
						{field:'orgenizationMenberCount',title:'成员数量',width:100},
						{field:'orgenizationCharge',title:'主要负责人',width:100},
						{field:'op',title:'操作',width:60,formatter:function(value,rec){
								return "<a class='btn_02_mini' onclick=viewa1('"+rec.id+"') >查看<b></b></a>";
						
						}}
				        ]]
			})); 
			
			$('#paginationa2').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='aqscgljg'>安全生产管理机构列表</a>",
				url:'${ctx}/jsp/aqscgljg/proManOrgQuery.action',
				queryParams:{
					"proManOrg.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				        {field:'areaId',title:'所在区域',width:100},
						{field:'companyName',title:'企业名称',width:100},
						{field:'orgenizationName',title:'机构名称',width:100},
						{field:'orgenizationResponsibility',title:'机构职责',width:100},
						{field:'orgenizationMenberCount',title:'成员数量',width:100},
						{field:'orgenizationCharge',title:'负责人',width:100},
						{field:'op',title:'操作',width:60,formatter:function(value,rec){
								return "<a class='btn_02_mini' onclick=viewa2('"+rec.id+"') >查看<b></b></a>";
							}}
				        ]]
			})); 
			
			$('#paginationa3').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='qyzyfzr'>企业主要负责人列表</a>",
				url:'${ctx}/jsp/qyzyfzr/entChaPerQuery.action',
				queryParams:{
					"entChaPer.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				          {field:'areaId',title:'所在区域',width:100},
						{field:'companyName',title:'企业名称',width:100},
						{field:'chargeName',title:'姓名',width:100},
						{field:'chargeSpecializedNum',title:'主要负责人安全生产资格证号',width:100},
						{field:'chargePhone',title:'联系方式',width:100},
						{field:'op',title:'操作',width:60,formatter:function(value,rec){
							return "<a class='btn_02_mini' onclick=viewa3('"+rec.id+"') >查看<b></b></a>";
						}}
				        ]]
			})); 
			
			
			$('#paginationa4').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='aqscfgfzr'>安全生产分管负责人列表</a>",
				url:'${ctx}/jsp/aqscfgfzr/secProChaQuery.action',
				queryParams:{
					"secProCha.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				         {field:'areaId',title:'所在区域',width:100},
						{field:'companyName',title:'企业名称',width:100},
						{field:'chargerName',title:'姓名',width:100},
						{field:'chargerSpecializedNum',title:'安全生产管理员资格证号',width:100},
						{field:'chargerPhone',title:'联系方式',width:100},
						{field:'op',title:'操作',width:60,formatter:function(value,rec){
							var button2="<a class='btn_02_mini' onclick=viewa4('"+rec.id+"') >查看<b></b></a>";
							return button2;
						}}					     
						   ]]
			})); 
			
			
			$('#paginationa5').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='aqsczj'>安全生产总监列表</a>",
				url:'${ctx}/jsp/aqsczj/secProMajQuery.action',
				queryParams:{
					"secProMaj.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				 			{field:'areaId',title:'所在区域',width:100},
							{field:'companyName',title:'企业名称',width:100},
							{field:'majordomoName',title:'姓名',width:100},
							{field:'majordomoSpecializedNum',title:'安全生产管理员资格证号',width:100},
							{field:'majordomoPhone',title:'联系方式',width:100},	          
							{field:'op',title:'操作',width:60,formatter:function(value,rec){
								return "<a class='btn_02_mini' onclick=viewa6('"+rec.id+"') >查看<b></b></a>";
							}}
				        ]]
			})); 
			
			$('#paginationa6').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='aqscgljl'>安全生产管理经理列表</a>",
				url:'${ctx}/jsp/aqscgljl/secProManQuery.action',
				queryParams:{
					"secProMan.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				 			{field:'areaId',title:'所在区域',width:100},
				            {field:'companyName',title:'企业名称',width:100},
							{field:'managerName',title:'姓名',width:100},
							{field:'managerSpecializedNum',title:'安全生产管理员资格证号',width:100},
							{field:'managerPhone',title:'联系方式',width:100},		          
							{field:'op',title:'操作',width:60,formatter:function(value,rec){
									return "<a class='btn_02_mini' onclick=viewa5('"+rec.id+"') >查看<b></b></a>";
							
							}}
				        ]]
			})); 
		
			$('#paginationa7').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='aqscfzry'>安全生产管理人员列表</a>",
				url:'${ctx}/jsp/aqscfzry/secProChaPerQuery.action',
				queryParams:{
					"secProChaPer.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				 			{field:'areaId',title:'所在区域',width:100},
							{field:'companyName',title:'企业名称',width:100},
							{field:'chargeName',title:'姓名',width:100},
							{field:'chargeSpecializedNum',title:'安全生产管理员资格证号',width:100},
							{field:'chargePhone',title:'联系方式',width:100},
							{field:'op',title:'操作',width:60,formatter:function(value,rec){
								return "<a class='btn_02_mini' onclick=viewa7('"+rec.id+"') >查看<b></b></a>";
							}}
				        ]]
			})); 
			
			$('#paginationa8').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='tzzyry'>特种作业人员列表</a>",
				url:'${ctx}/jsp/tzzyry/speJobPerQuery.action',
				queryParams:{
					"speJobPer.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				 			{field:'areaId',title:'所在区域',width:100},
							{field:'companyName',title:'企业名称',width:100},
							{field:'specialName',title:'姓名',width:100},
							{field:'specialJobType',title:'特种作业类型',width:100},
							{field:'specialJobCradnum',title:'特种作业证号',width:100},
							{field:'specialVerificationDate',title:'复审日期',width:100,formatter:function(value,rec){return value.substring(0,10);}},
							{field:'op',title:'操作',width:60,formatter:function(value,rec){
								return "<a class='btn_02_mini' onclick=viewa8('"+rec.id+"') >查看<b></b></a>";
							}}
				        ]]
			})); 
		}
		
		
		function jypx(){
			document.getElementById('jypx').style.display="";
			
			$('#paginationb1').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='qyzyfzrpx' >企业主要负责人培训列表</a>",
				url:'${ctx}/jsp/qyzyfzrpx/maiChaTraQuery.action',
				queryParams:{
					"maiChaTra.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				         {field:'areaName',title:'所在区域',width:100},
						{field:'companyName',title:'企业名称',width:100},
						{field:'trainingPersonName',title:'主要负责人姓名',width:100},
						{field:'trainingCardnum',title:'证书号码',width:100},
						{field:'trainingTime',title:'培训开始时间',width:100,formatter:function(value,rec){return value.substring(0,11);}},
						{field:'trainingTimeEnd',title:'培训结束时间',width:100,formatter:function(value,rec){return value.substring(0,11);}},
						{field:'op',title:'操作',width:60,formatter:function(value,rec){
								return "<a class='btn_02_mini' onclick=viewb1('"+rec.id+"') >查看<b></b></a>";
						
						}}
				        ]]
			})); 
			
			$('#paginationb2').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='aqscfgfzrpx'>安全生产分管负责人培训列表</a>",
				url:'${ctx}/jsp/aqscfgfzrpx/chargeTrainingQuery.action',
				queryParams:{
					"chargeTraining.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				        {field:'areaName',title:'所在区域',width:100},
						{field:'companyName',title:'企业名称',width:100},
						{field:'trainingPersonName',title:'安全生产分管负责人姓名',width:100},
						{field:'trainingCardnum',title:'证书号码',width:100},
						{field:'trainingTime',title:'培训开始时间',width:100,formatter:function(value,rec){return value.substring(0,11);}},
						{field:'trainingTimeEnd',title:'培训结束时间',width:100,formatter:function(value,rec){return value.substring(0,11);}},
						{field:'op',title:'操作',width:60,formatter:function(value,rec){
								return "<a class='btn_02_mini' onclick=viewb2('"+rec.id+"') >查看<b></b></a>";
							}}
				        ]]
			})); 
			
			
			$('#paginationb3').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='aqsczjpx'>安全生产总监培训列表</a>",
				url:'${ctx}/jsp/aqsczjpx/majordomoTrainingQuery.action',
				queryParams:{
					"majordomoTraining.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				         {field:'areaName',title:'所在区域',width:100},
						{field:'companyName',title:'企业名称',width:100},
						{field:'trainingPersonName',title:'安全生产总监姓名',width:100},
						{field:'trainingCardnum',title:'证书号码',width:100},
						{field:'trainingTime',title:'培训开始时间',width:100,formatter:function(value,rec){return value.substring(0,11);}},
						{field:'trainingTimeEnd',title:'培训结束时间',width:100,formatter:function(value,rec){return value.substring(0,11);}},
						{field:'op',title:'操作',width:60,formatter:function(value,rec){
							var button2="<a class='btn_02_mini' onclick=viewb4('"+rec.id+"') >查看<b></b></a>";
							return button2;
						}}					     
						   ]]
			})); 
			
			$('#paginationb4').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='aqscgljlpx'>安全生产管理经理培训列表</a>",
				url:'${ctx}/jsp/aqscgljlpx/managerTrainingQuery.action',
				queryParams:{
					"managerTraining.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				        {field:'areaName',title:'所在区域',width:100},
						{field:'companyName',title:'企业名称',width:100},
						{field:'trainingPersonName',title:'安全生产管理经理姓名',width:100},
						{field:'trainingCardnum',title:'证书号码',width:100},
						{field:'trainingTime',title:'培训开始时间',width:100,formatter:function(value,rec){return value.substring(0,11);}},
						{field:'trainingTimeEnd',title:'培训结束时间',width:100,formatter:function(value,rec){return value.substring(0,11);}},
						{field:'op',title:'操作',width:60,formatter:function(value,rec){
							return "<a class='btn_02_mini' onclick=viewb3('"+rec.id+"') >查看<b></b></a>";
						}}
				        ]]
			})); 
			
			
			$('#paginationb5').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='aqscfzrypx'>安全生产负责人员培训列表</a>",
				url:'${ctx}/jsp/aqscfzrypx/secProChaTraQuery.action',
				queryParams:{
					"secProChaTra.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				 			{field:'areaName',title:'所在区域',width:100},
							{field:'companyName',title:'企业名称',width:100},
							{field:'trainingPersonName',title:'安全生产负责人员姓名',width:100},
							{field:'trainingCardnum',title:'证书号码',width:100},
							{field:'trainingTime',title:'培训开始时间',width:100,formatter:function(value,rec){return value.substring(0,11);}},
							{field:'trainingTimeEnd',title:'培训结束时间',width:100,formatter:function(value,rec){return value.substring(0,11);}},          
							{field:'op',title:'操作',width:60,formatter:function(value,rec){
									return "<a class='btn_02_mini' onclick=viewb5('"+rec.id+"') >查看<b></b></a>";
							
							}}
				        ]]
			})); 
		
			$('#paginationb6').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='qytzrypx'>企业特种人员培训列表</a>",
				url:'${ctx}/jsp/qytzrypx/speJobTraQuery.action',
				queryParams:{
					"speJobTra.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				 			{field:'areaName',title:'所在区域',width:100},
							{field:'companyName',title:'企业名称',width:100},
							{field:'trainingPersonName',title:'特种作业人员姓名',width:100},
							{field:'trainingCardnum',title:'证书号码',width:100},
							{field:'trainingTime',title:'培训开始时间',width:100,formatter:function(value,rec){return value.substring(0,11);}},
							{field:'trainingTimeEnd',title:'培训结束时间',width:100,formatter:function(value,rec){return value.substring(0,11);}},        
							{field:'op',title:'操作',width:60,formatter:function(value,rec){
								return "<a class='btn_02_mini' onclick=viewb6('"+rec.id+"') >查看<b></b></a>";
							}}
				        ]]
			})); 
		
			$('#paginationb7').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='bzzpx'>班组长培训列表</a>",
				url:'${ctx}/jsp/bzzpx/teaLeaTraQuery.action',
				queryParams:{
					"teaLeaTra.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				 			{field:'areaName',title:'所在区域',width:100},
							{field:'companyName',title:'企业名称',width:100},
							{field:'trainingName',title:'姓名',width:100},
							{field:'trainingWorkshopName',title:'车间名称',width:100},
							{field:'trainingTime',title:'培训开始时间',width:100,formatter:function(value,rec){return value.substring(0,11);}},
							{field:'trainingTimeEnd',title:'培训结束时间',width:100,formatter:function(value,rec){return value.substring(0,11);}},
							{field:'op',title:'操作',width:60,formatter:function(value,rec){
								return "<a class='btn_02_mini' onclick=viewb7('"+rec.id+"') >查看<b></b></a>";
							}}
				        ]]
			})); 
			
			$('#paginationb8').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='xygsgpx'>新员工上岗培训列表</a>",
				url:'${ctx}/jsp/xygsgpx/newWorTraQuery.action',
				queryParams:{
					"newWorTra.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				 			 {field:'areaName',title:'所在区域',width:100},
							{field:'companyName',title:'企业名称',width:100},
							{field:'trainingName',title:'培训班名称',width:100},
							{field:'trainingTime',title:'培训开始时间',width:100,formatter:function(value,rec){return value.substring(0,11);}},
							{field:'trainingTimeEnd',title:'培训结束时间',width:100,formatter:function(value,rec){return value.substring(0,11);}},
							{field:'op',title:'操作',width:60,formatter:function(value,rec){
								return "<a class='btn_02_mini' onclick=viewb8('"+rec.id+"') >查看<b></b></a>";
							}}
				        ]]
			})); 
			
			$('#paginationb9').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='qynbqypx'>企业内部全员培训列表</a>",
				url:'${ctx}/jsp/qynbqypx/allMenTraQuery.action',
				queryParams:{
					"allMenTra.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				 			{field:'areaName',title:'所在区域',width:100},
							{field:'companyName',title:'企业名称',width:100},
							{field:'trainingName',title:'培训班名称',width:100},
							{field:'trainingTime',title:'培训开始时间',width:100,formatter:function(value,rec){return value.substring(0,11);}},
							{field:'trainingTimeEnd',title:'培训结束时间',width:100,formatter:function(value,rec){return value.substring(0,11);}},
							{field:'op',title:'操作',width:60,formatter:function(value,rec){
								return "<a class='btn_02_mini' onclick=viewb9('"+rec.id+"') >查看<b></b></a>";
							}}
				        ]]
			})); 
		}
		
		
		function yhpchzl(){
			document.getElementById('yhpchzl').style.display="";
			
			$('#paginationc1').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='wxyxcjhgl' >巡查计划管理列表</a>",
				url:'${ctx}/jsp/wxyxcjhgl/danPlaManQuery.action',
				queryParams:{
					"danPlaMan.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				          {field:'companyName',title:'企业名称',width:100},
						{field:'planName',title:'计划名称',width:100},
						{field:'checkFrequency',title:'巡查频率',width:80},
						{field:'checkPeopleName',title:'巡查人员姓名',width:80},
						{field:'isAudit',title:'是否上报审核',width:100,formatter:function(value,rec){   
						if(value=='1'){
								value='否';
							}else if(value=='0'){
							   value='是';
							}
							return value;
						}}, 
						{field:'auditResult',title:'状态',width:50,formatter:function(value,rec){
							if(value=='审核通过'){
								value='执行中';
							}
							return value;
						}},
						{field:'op',title:'操作',width:60,formatter:function(value,rec){
								return "<a class='btn_02_mini' onclick=viewc1('"+rec.id+"') >查看<b></b></a>";
						
						}}
				        ]]
			})); 
			
			$('#paginationc2').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='wxyxcrwgl'>巡查任务管理列表</a>",
				url:'${ctx}/jsp/wxyxcrwgl/danTasManQuery.action',
				queryParams:{
					"danTasMan.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				         {field:'areaName',title:'所在区域',width:60},
						{field:'companyName',title:'企业名称',width:100},
						{field:'taskName',title:'任务名称',width:100},
						{field:'checkTime',title:'巡查开始时间',width:80,formatter:function(value,rec){return value.substring(0,10);}},
						{field:'checkTimeEnd',title:'巡查结束时间',width:80,formatter:function(value,rec){return value.substring(0,10);}},
						{field:'checkPeopleName',title:'巡查人员姓名',width:100},
						{field:'result',title:'巡查结果',width:50},
						{field:'op',title:'操作',width:60,formatter:function(value,rec){
								return "<a class='btn_02_mini' onclick=viewc2('"+rec.id+"') >查看<b></b></a>";
							}}
				        ]]
			})); 
			
			$('#paginationc3').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='yhb'>隐患列表</a>",
				url:'${ctx}/jsp/yhb/troManQuery.action',
				queryParams:{
					"troMan.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				        {field:'areaName',title:'所在区域',width:100},
						{field:'companyName',title:'企业名称',width:100},
				        {field:'troubleName',title:'隐患名称',width:100},
						{field:'troubleSource',title:'隐患来源',width:100},
						{field:'userName',title:'上报人员名称',width:100},
						{field:'troubleLevel',title:'隐患级别',width:50},
						{field:'troubleSort',title:'隐患类别',width:50},
						{field:'rectificationState',title:'整改状态',width:60,formatter:function(value,rec){
								if(rec.ifReportAwh=='0'){
									if(value=='0'&&(rec.dealState=='整改完成'||rec.dealState=='')){
										return '整改完成';
									}else if(value=='0'&&rec.dealState=='整改未完成'){
										return '整改未完成';
									}else if(value=='3'){
										return '已上报待处理';
									}else{
										return '待整改';
									}
								}else{
									if(value=='0'&&(rec.dealState=='整改完成'||rec.dealState=='')){
										return '整改完成';
									}else if(value=='0'&&rec.dealState=='整改未完成'){
										return '整改未完成';
									}else if(value=='1'){
										return "审核未通过";
									}else{
										return "已上报待处理";
									}
								}
							}
						},
						{field:'op',title:'操作',width:60,formatter:function(value,rec){
							return "<a class='btn_02_mini' onclick=viewc3('"+rec.id+"') >查看<b></b></a>";
						}}
				        ]]
			})); 
			
			
//			$('#paginationc4').datagrid($.extend(window.dg_cm_pp,{
//				title:"<a name='dot'>巡检点管理列表</a>",
//				url:'${ctx}/jsp/dot/dotQuery.action',
//				queryParams:{
//					"dot.companyId": '${entBaseInfo.id}'
//				},
//				frozenColumns:frozen,
//				columns:[[
//				         {field:'dotName',title:'名称',width:100},
//						{field:'position',title:'位置',width:100},
//						{field:'op',title:'操作',width:60,formatter:function(value,rec){
//							var button2="<a class='btn_02_mini' onclick=viewc4('"+rec.id+"') >查看<b></b></a>";
//							return button2;
//						}}					     
//						   ]],
//				toolbar:toolbar
//			})); 
			
			
//			$('#paginationc5').datagrid($.extend(window.dg_cm_pp,{
//				title:"<a name='road'>巡查路线管理列表</a>",
//				url:'${ctx}/jsp/road/roadQuery.action',
//				queryParams:{
//					"road.companyId": '${entBaseInfo.id}'
//				},
//				frozenColumns:frozen,
//				columns:[[
//				 			{field:'roadName',title:'路线名称',width:100},          
//							{field:'op',title:'操作',width:60,formatter:function(value,rec){
//									return "<a class='btn_02_mini' onclick=viewc5('"+rec.id+"') >查看<b></b></a>";
//							
//							}}
//				        ]],
//				toolbar:toolbar
//			})); 
		
//			$('#paginationc6').datagrid($.extend(window.dg_cm_pp,{
//				title:"<a name='patrolUser'>巡查人员管理列表</a>",
//				url:'${ctx}/jsp/patrolUser/patrolUserQuery.action',
//				queryParams:{
//					"patrolUser.companyId": '${entBaseInfo.id}'
//				},
//				frozenColumns:frozen,
//				columns:[[
//				 			{field:'userName',title:'姓名',width:100},
//							{field:'loginId',title:'用户名',width:100},
//							{field:'mobile',title:'手机号',width:100},
//							{field:'job',title:'职务',width:100},       
//							{field:'op',title:'操作',width:60,formatter:function(value,rec){
//								return "<a class='btn_02_mini' onclick=viewc6('"+rec.id+"') >查看<b></b></a>";
//							}}
//				        ]],
//				toolbar:toolbar
//			})); 
		
		}
		
		
		function qyzdwxyjk(){
			document.getElementById('qyzdwxyjk').style.display="";
			
			$('#paginationd1').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='zdwxysbpgfj' >企业重点危险源的识别评估分级列表</a>",
				url:'${ctx}/jsp/zdwxysbpgfj/comDanIdeQuery.action',
				queryParams:{
					"comDanIde.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				        {field:'areaName',title:'所在区域',width:100},
						{field:'companyName',title:'企业名称',width:100},
						{field:'dangerName',title:'重点危险源名称',width:100},
						{field:'dangerLevel',title:'重点危险源级别',width:100},
						{field:'safePerson',title:'安全负责人',width:100},
						{field:'dangerType',title:'重点危险源类别',width:100},
						{field:'auditState',title:'审核状态',width:100},
						{field:'op',title:'操作',width:60,formatter:function(value,rec){
								return "<a class='btn_02_mini' onclick=viewd1('"+rec.id+"') >查看<b></b></a>";
						
						}}
				        ]]
			})); 
			
			$('#paginationd2').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='zdwxyglzd'>企业重点危险源的管理制度列表</a>",
				url:'${ctx}/jsp/zdwxyglzd/comManSysQuery.action',
				queryParams:{
					"comManSys.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
						{field:'companyName',title:'企业名称',width:100},
						{field:'systemName',title:'制度名称',width:100},
						{field:'dangerName',title:'危险源名称',width:100},
						{field:'op',title:'操作',width:60,formatter:function(value,rec){
								return "<a class='btn_02_mini' onclick=viewd2('"+rec.id+"') >查看<b></b></a>";
							}}
				        ]]
			})); 
			
			$('#paginationd3').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='zdwxyczgc'>企业重点危险源的操作规程列表</a>",
				url:'${ctx}/jsp/zdwxyczgc/comOpeRulQuery.action',
				queryParams:{
					"comOpeRul.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				        {field:'areaName',title:'所在区域',width:100},
						{field:'companyName',title:'企业名称',width:100},
						{field:'ruleName',title:'操作规程名称',width:100},
						{field:'dangerName',title:'重点危险源名称',width:100},
						{field:'op',title:'操作',width:60,formatter:function(value,rec){
							return "<a class='btn_02_mini' onclick=viewd3('"+rec.id+"') >查看<b></b></a>";
						}}
				        ]]
			})); 
			
			
			$('#paginationd4').datagrid($.extend(window.dg_cm_pp,{
				title:"<a name='zdwxyyjcz'>企业重点危险源的应急处置列表</a>",
				url:'${ctx}/jsp/zdwxyyjcz/comDanEmeQuery.action',
				queryParams:{
					"comDanEme.companyId": '${entBaseInfo.id}'
				},
				columns:[[
				         {field:'areaName',title:'所在区域',width:100},
						{field:'companyName',title:'企业名称',width:100},
						{field:'emergencyName',title:'应急处置名称',width:100},
						{field:'dangerName',title:'重点危险源名称',width:100},
						{field:'op',title:'操作',width:60,formatter:function(value,rec){
							var button2="<a class='btn_02_mini' onclick=viewd4('"+rec.id+"') >查看<b></b></a>";
							return button2;
						}}					     
						   ]]
			})); 
		
		}
		
		
		function openTab(url,id,text)
        {
        	url = url + encodeURIComponent('${entBaseInfo.enterpriseName}');
            window.parent.addTab(id,text,url);
        }
		
    	</script>
    	
    	<style>
    	
    	* { padding: 0; margin: 0; }
 
.tabs { width: 100%;  margin: 5px auto; font-size: 0px;text-align:center }
.tab-label { display: inline-block; height: 36px; font: 26px/36px cambria; color: #333; background: #1175C8; padding: 0px 20px; border-radius: 10px 10px 0 0; margin-right: 8px; cursor: pointer; }
.tab-label:hover { background: #10CDE5; }

.tab-radio { position: absolute; display: none; }
.tab-radio:checked + label { background: #22239B; color: #fff; }
.tabs .tab-radio:nth-of-type(3):checked ~ .panels .panel-item:nth-child(3) { opacity: 1; -o-transition: 3s; -ms-transition: 3s; -moz-transition: 3s; -webkit-transition: 3s; }
.tabs .tab-radio:nth-of-type(2):checked ~ .panels .panel-item:nth-child(2) { opacity: 1; -o-transition: 3s; -ms-transition: 3s; -moz-transition: 3s; -webkit-transition: 3s; }
.tabs .tab-radio:nth-of-type(1):checked ~ .panels .panel-item:nth-child(1) { opacity: 1; -o-transition: 3s; -ms-transition: 3s; -moz-transition: 3s; -webkit-transition: 3s; }
.panels { width: 600px; height: 400px; padding: 10px; border-radius: 0 10px 10px 10px; background: #fff; font-size: 20px; }
.panel-item { width: 600px; height: 400px; opacity: 0; position: absolute; }
    	</style>
    	
	</head>
	<body>
	
    
    
    
		<div class="page_content">
		
		
		
    
    
    
			<div class="layout_01_right" style="left:1px;right:1px;">
			<div class="layout_overflow">
			<div class="boxBmargin12 cell">
			
			
				<div class="tabs">
	        <input type="radio" checked name="tab-item" id="tab-one" class="tab-radio">
	        <label for="tab-one" class="tab-label" onclick="changDiv('1');">企业信息</label>
	
	        <input type="radio" name="tab-item" id="tab-two" class="tab-radio">
	        <label for="tab-two" class="tab-label" onclick="changDiv('2');">组织机构和职责</label>
	        
	        <input type="radio" name="tab-item" id="tab-three" class="tab-radio">
	        <label for="tab-three" class="tab-label" onclick="changDiv('3');">教育培训</label>
	        
	         <input type="radio" name="tab-item" id="tab-four" class="tab-radio">
	        <label for="tab-four" class="tab-label" onclick="changDiv('4');">隐患排查和治理</label>
	        
	         <input type="radio" name="tab-item" id="tab-five" class="tab-radio">
	        <label for="tab-five" class="tab-label" onclick="changDiv('5');">企业重点危险源监控</label>
	        
	         <input type="radio" name="tab-item" id="tab-six" class="tab-radio">
	        <label for="tab-six" class="tab-label" onclick="changDiv('6');">其他</label>
        
    	</div>
					
					</div>
				<div class="inner6px submitdata">
					<div id='qyxx' style='display:none'>
					<table width="100%">
						<tr>
							<td>
								<div class="cell">
			<table width="100%">
				<tr>
					<th width="15%">企业名称</th>
					<td width="35%">${entBaseInfo.enterpriseName}</td>
					<th width="15%">工商注册号</th>
					<td width="35%">${entBaseInfo.registrationNumber}</td>
				</tr>
				<tr>
					<th width="15%">组织机构代码</th>
					<td width="35%">${entBaseInfo.enterpriseCode}</td>
					<th width="15%">注册地址</th>
					<td width="35%">${entBaseInfo.enterpriseAddress}</td>
				</tr>
				<tr>
					<th width="15%">生产经营地址</th>
					<td width="35%">${entBaseInfo.factoryAddress}</td>
					<th width="15%">企业属地</th>
					<td width="35%"><cus:hxlabel codeName="企业属地" itemValue="${entBaseInfo.enterprisePossession}" /></td>
				</tr>
				<tr>	
					<th width="15%">邮政编码</th>
					<td width="35%">${entBaseInfo.enterpriseZipcode}</td>
					<th width="15%">企业性质</th>
					<td width="35%"><cus:hxlabel codeName="企业性质" itemValue="${entBaseInfo.enterpriseNature}" /></td>
				</tr>
				<tr>	
					<th width="15%">企业规模</th>
					<td width="35%"><cus:hxlabel codeName="企业规模" itemValue="${entBaseInfo.enterpriseScale}" /></td>	
					<th width="15%">企业分类</th>
					<td width="35%"><cus:hxmulselectlabel codeName="企业分类" itemValue="${entBaseInfo.enterpriseType}" /></td>	
				</tr>
				<tr>
					<th width="15%">行业类别</th>
					<td width="35%"><cus:hxlabel codeName="行业类别" itemValue="${entBaseInfo.enterpriseCategory}" /></td>	
					<th width="15%">投资方国籍</th>
					<td width="35%">${entBaseInfo.enterpriseNationnality}</td>	
				</tr>
				<tr>
					<th width="15%">法人代表姓名</th>
					<td width="35%">${entBaseInfo.enterpriseLegalName}</td>
					<th width="15%">法人代表性别</th>
					<td width="35%"><cus:hxlabel codeName="性别" itemValue="${entBaseInfo.enterpriseLegalSex}" /></td>	
				</tr>
				<tr>
					<th width="15%">法人代表年龄</th>
					<td width="35%">${entBaseInfo.enterpriseLegalAge}</td>
					<th width="15%">法人代表联系电话</th>
					<td width="35%">${entBaseInfo.enterpriseLegalPhone}</td>
				</tr>
				<tr>	
					<th width="15%">法人代表证件号码</th>
					<td width="35%">${entBaseInfo.enterpriseLegalCardnum}</td>
					<th width="15%">法人代表电子邮箱</th>
					<td width="35%">${entBaseInfo.enterpriseLegalEmail}</td>
				</tr>
				<tr>
					<th width="15%">法人代表职务</th>
					<td width="35%">${entBaseInfo.enterpriseLegalZw}</td>
					<th width="15%">企业设立日期</th>
					<td width="35%"><fmt:formatDate type="date" value="${entBaseInfo.enterpriseFoundDate}" /></td>
				</tr>
				<tr>
					<th width="15%">企业投产日期</th>
					<td width="35%"><fmt:formatDate type="date" value="${entBaseInfo.enterpriseProductDate}" /></td>
					<th width="15%">注册资本</th>
					<td width="35%">${entBaseInfo.enterpriseRegisterMoney}<cus:hxlabel codeName="货币种类" itemValue="${entBaseInfo.enterpriseRegisterMoneyDw}" /></td>
				</tr>
				<tr>
					<th width="15%">投资总额</th>
					<td width="35%">${entBaseInfo.enterpriseInvestMoney}<cus:hxlabel codeName="货币种类" itemValue="${entBaseInfo.enterpriseInvestMoneyDw}" /></td>
					<th width="15%">固定资产总额</th>
					<td width="35%">${entBaseInfo.enterpriseFixedassetMoney}<cus:hxlabel codeName="货币种类" itemValue="${entBaseInfo.enterpriseFixedassetMoneyDw}" /></td>
				</tr>
				<tr>
					<th width="15%">占地面积（m2）</th>
					<td width="35%">${entBaseInfo.enterpriseFloorArea}</td>
					<th width="15%">办公楼建筑面积（m2）</th>
					<td width="35%">${entBaseInfo.enterpriseOfficeArea}</td>
				</tr>
				<tr>
					<th width="15%">车间厂房建筑面积（m2）</th>
					<td width="35%">${entBaseInfo.enterpriseWorkshopArea}</td>
					<th width="15%">仓库建筑面积（m2）</th>
					<td width="35%">${entBaseInfo.enterpriseWearhouseArea}</td>
				</tr>
				<tr>
					<th width="15%">厂房归属</th>
					<td width="35%"><cus:hxlabel codeName="厂房归属" itemValue="${entBaseInfo.enterprisWorkshopOwn}" /></td>
					<th width="15%">管理人员数</th>
					<td width="35%">${entBaseInfo.enterpriseManagerCount}</td>
				</tr>
				<tr>
					<th width="15%">工人数</th>
					<td width="35%">${entBaseInfo.enterpriseWorkerCount}</td>
					<th width="15%">所属网格</th>
					<td width="35%" >${entBaseInfo.gridName}</td>
				</tr>
				<tr>
					<th width="15%">网格管理中队</th>
					<td width="35%" >${entBaseInfo.gridManageteamName}</td>
					<th width="15%">网格管理人员</th>
					<td width="35%" >${comGriMan.gridManagePersonName}</td>
				</tr>
				<tr>
					<th width="15%">经营范围</th>
					<td width="85%" colspan="3">
						<textarea id="entBaseInfo.enterpriseScope" name="entBaseInfo.enterpriseScope" style="width:96%;height:120px" readonly="readonly">${entBaseInfo.enterpriseScope}</textarea>
					</td>
				</tr>
				<tr>
					<th width="15%">厂区平面图</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
							<table>
							  	<c:forEach var="item" items="${picList}">
									<tr id='${item.id}' style="text-align: center">
								  		<td width="70%">
								   			<c:choose>
												<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
													||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
													||fn:endsWith(fn:toLowerCase(item.picName),'.png')
													||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
													||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
														<a href="${item.httpUrl}/upload/photo/${item.picName}" rel="example_group">	
															<img src="${item.httpUrl}/upload/photo/${item.picName}"
															border='0' width='220' height='150'/>
														</a>
												</c:when> 
												<c:otherwise> 
													&nbsp;&nbsp;&nbsp;${item.fileName}
												</c:otherwise>
											</c:choose>
								   		</td>
								   		<td width="30%"><a href="javascript:downloadFile('${item.id}');">下载</a>&nbsp;&nbsp;</td>
									</tr>
							  	</c:forEach>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<th width="15%">审核记录</th>
					<td width="85%" colspan="3">
						<c:forEach items="${checkRecords }" var="cr">
							<fmt:formatDate type="both" value="${cr.createTime}" />，${cr.checkUsername}${cr.checkResult}[${cr.checkRemark}]<br/>
						</c:forEach>
					
					</td>
				</tr>
			</table>
		</div>
							</td>
						</tr>
						<tr>
							<td><div id="pagination"></div></td>
						</tr>
						<tr>
							<td><div id="pagination1"></div></td>
						</tr>
						<tr>
							<td><div id="pagination2"></div></td>
						</tr>
						<tr>
							<td><div id="pagination3"></div></td>
						</tr>
						<tr>
							<td><div id="pagination4"></div></td>
						</tr>
						<tr>
							<td><div id="pagination5"></div></td>
						</tr>
					</table>
					</div>
					<div id='zzjghzz' style='display:none'>
					<table width="99%">
						<tr>
							<td><div id="paginationa1"></div></td>
						</tr>
						<tr>
							<td><div id="paginationa2"></div></td>
						</tr>
						<tr>
							<td><div id="paginationa3"></div></td>
						</tr>
						<tr>
							<td><div id="paginationa4"></div></td>
						</tr>
						<tr>
							<td><div id="paginationa5"></div></td>
						</tr>
						<tr>
							<td><div id="paginationa6"></div></td>
						</tr>
						<tr>
							<td><div id="paginationa7"></div></td>
						</tr>
						<tr>
							<td><div id="paginationa8"></div></td>
						</tr>
					</table>
					</div>
					<div id='jypx' style='display:none'>
					<table width="99%">
						<tr>
							<td><div id="paginationb1"></div></td>
						</tr>
						<tr>
							<td><div id="paginationb2"></div></td>
						</tr>
						<tr>
							<td><div id="paginationb3"></div></td>
						</tr>
						<tr>
							<td><div id="paginationb4"></div></td>
						</tr>
						<tr>
							<td><div id="paginationb5"></div></td>
						</tr>
						<tr>
							<td><div id="paginationb6"></div></td>
						</tr>
						<tr>
							<td><div id="paginationb7"></div></td>
						</tr>
						<tr>
							<td><div id="paginationb8"></div></td>
						</tr>
						<tr>
							<td><div id="paginationb9"></div></td>
						</tr>
					</table>
					</div>
					<div id='yhpchzl' style='display:none'>
					<table width="99%">
						<tr>
							<td><div id="paginationc1"></div></td>
						</tr>
						<tr>
							<td><div id="paginationc2"></div></td>
						</tr>
						<tr>
							<td><div id="paginationc3"></div></td>
						</tr>
						<tr>
							<td><div id="paginationc4"></div></td>
						</tr>
						<tr>
							<td><div id="paginationc5"></div></td>
						</tr>
						<tr>
							<td><div id="paginationc6"></div></td>
						</tr>
					</table>
					</div>
					<div id='qyzdwxyjk' style='display:none'>
					<table width="99%">
						<tr>
							<td><div id="paginationd1"></div></td>
						</tr>
						<tr>
							<td><div id="paginationd2"></div></td>
						</tr>
						<tr>
							<td><div id="paginationd3"></div></td>
						</tr>
						<tr>
							<td><div id="paginationd4"></div></td>
						</tr>
					</table>
					</div>
					<div id='qt' style='display:none' class="cell">
					<table width="99%">
						<tr>
							<th style="text-align:center" colspan="3">安全生产投入</th>
						</tr>
						<tr>
							<td><a href="javaScript:openTab('jsp/aqscfytqqk/secProFeeExtList.action?secProFeeExt.companyName=','M080904','安全生产费用提取情况')">安全生产费用提取情况</a></td>
							<td><a href="javaScript:openTab('jsp/aqscfysytz/secProFeeAccList.action?secProFeeAcc.companyName=','M080905','安全生产费用使用台账')">安全生产费用使用台账</a></td>
							<td><a href="javaScript:openTab('jsp/aqscbxtbqk/secProInsList.action?secProIns.companyName=','M080910','安全生产保险投保情况')">安全生产保险投保情况</a></td>
						</tr>
						
						<tr>
							<th style="text-align:center" colspan="3">法律法规与安全管理制度</th>
						</tr>
						<tr>
							<td><a href="javaScript:openTab('jsp/flfgbzgf/legStaList.action?legSta.companyName=','M081412','法律法规和标准规范')">法律法规和标准规范</a></td>
							<td><a href="javaScript:openTab('jsp/aqscgzzd/secProSysList.action?secProSys.companyName=','M081413','安全生产规章制度')">安全生产规章制度</a></td>
							<td><a href="javaScript:openTab('jsp/czgc/opeProList.action?opePro.companyName=','M081414','操作规程')">操作规程</a></td>
						</tr>
						<tr>
							<th style="text-align:center" colspan="3">企业作业安全</th>
						</tr>
						<tr>
							<td><a href="javaScript:openTab('jsp/sccjgl/workshopList.action?workshop.companyName=','M082901','生产车间管理')">生产车间管理</a></td>
							<td><a href="javaScript:openTab('jsp/scsbss/proDevList.action?proDev.companyName=','M082902','生产设备设施')">生产设备设施</a></td>
							<td><a href="javaScript:openTab('jsp/scxcgl/productionManageList.action?productionManage.companyName=','M082903','生产现场管理和生产过程控制')">生产现场管理和生产过程控制</a></td>
						</tr>
						<tr>
							<td><a href="javaScript:openTab('jsp/ggl/publicBoardList.action?publicBoard.companyName=','M082904','公告栏')">公告栏</a></td>
							<td><a href="javaScript:openTab('jsp/gzk/informCardList.action?informCard.companyName=','M082905','告知卡')">告知卡</a></td>
							<td><a href="javaScript:openTab('jsp/mxbz/signsList.action?signs.companyName=','M082906','安全标识')">安全标识</a></td>
						</tr>
						<tr>
							<td><a href="javaScript:openTab('jsp/xgfgl/partyManageList.action?partyManage.companyName=','M082908','相关方管理')">相关方管理</a></td>
						</tr>
						
						<tr>
							<th style="text-align:center" colspan="3">劳保用品配置</th>
						</tr>
						<tr>
							<td><a href="javaScript:openTab('jsp/lbypgl/ppeManagList.action?ppeManag.companyName=','M081313','劳保用品管理')">劳保用品管理</a></td>
							<td><a href="javaScript:openTab('jsp/lbypkfgl/ppeWareManagList.action?ppeWareManag.companyName=','M081314','劳保用品库房管理')">劳保用品库房管理</a></td>
							<td><a href="javaScript:openTab('jsp/lbyplygl/ppeUseManageList.action?ppeUseManage.companyName=','M081301','劳保用品领用管理')">劳保用品领用管理</a></td>
						</tr>
						
						<tr>
							<th style="text-align:center" colspan="3">职业健康</th>
						</tr>
						<tr>
							<td><a href="javaScript:openTab('jsp/zywsaqglzd/occHeaManList.action?occHeaMan.companyName=','M081808','职业卫生安全管理制度')">职业卫生安全管理制度</a></td>
							<td><a href="javaScript:openTab('jsp/ndzybfzjf/occDisPreList.action?occDisPre.companyName=','M081809','年度职业病防治经费')">年度职业病防治经费</a></td>
							<td><a href="javaScript:openTab('jsp/zywsgljbxx/occHeaInfoList.action?occHeaInfo.companyName=','M081811','职业卫生管理基本信息')">职业卫生管理基本信息</a></td>
						</tr>
						<tr>
							<td><a href="javaScript:openTab('jsp/sbsskjzywszydbj/equAndFacList.action?equAndFac.companyName=','M081813','设备设施空间职业卫生作业点布局')">设备设施空间职业卫生作业点布局</a></td>
							<td><a href="javaScript:openTab('jsp/zywhjbysqk/occHazBasList.action?occHazBas.companyName=','M081801','职业危害基本因素情况')">职业危害基本因素情况</a></td>
							<td><a href="javaScript:openTab('jsp/whysjc/hazDetList.action?hazDet.companyName=','M081803','危害因素检测')">危害因素检测</a></td>
						</tr>
						<tr>
							<td><a href="javaScript:openTab('jsp/jccbgl/detOverStaManList.action?detOverStaMan.companyName=','M081812','检测超标管理')">检测超标管理</a></td>
							<td><a href="javaScript:openTab('jsp/zyjktj/occHeaExaList.action?occHeaExa.companyName=','M081804','职业健康体检')">职业健康体检</a></td>
							<td><a href="javaScript:openTab('jsp/tjbhgjl/phyUnqRecList.action?phyUnqRec.companyName=','M081814','体检不合格记录')">体检不合格记录</a></td>
						</tr>
						<tr>
							<td><a href="javaScript:openTab('jsp/dgjl/proRecList.action?proRec.companyName=','M081815','调岗记录')">调岗记录</a></td>
						</tr>
						<tr>
							<th style="text-align:center" colspan="3">危化企业信息管理</th>
						</tr>
						<tr>
							<td><a href="javaScript:openTab('jsp/qyzyfzrlzqk/maiPerRepList.action?maiPerRep.companyName=','M082401','企业主要负责人履职情况报告')">企业主要负责人履职情况报告</a></td>
							<td><a href="javaScript:openTab('jsp/wxhxpzdwxyba/majSouRecList.action?majSouRec.companyName=','M082402','危险化学品重大危险源备案')">危险化学品重大危险源备案</a></td>
							<td><a href="javaScript:openTab('jsp/wxhxpzdwxyhx/majSouVerList.action?majSouVer.companyName=','M082403','危险化学品重大危险源核销')">危险化学品重大危险源核销</a></td>
						</tr>
						<tr>
							<td><a href="javaScript:openTab('jsp/zdwhpclqkb/cheResList.action?cheRes.companyName=','M082404','重点危化品储量情况表')">重点危化品储量情况表</a></td>
							<td><a href="javaScript:openTab('jsp/lddbqk/leaClaList.action?leaCla.companyName=','M082405','领导带班情况')">领导带班情况</a></td>
							<td><a href="javaScript:openTab('jsp/jjrktg/shuHolList.action?shuHol.companyName=','M082406','节假日开停工')">节假日开停工</a></td>
						</tr>
						<tr>
							<td><a href="javaScript:openTab('jsp/gylc/tecProList.action?tecPro.companyName=','M083001','工艺流程')">工艺流程</a></td>
							<td><a href="javaScript:openTab('jsp/qygjzzhzdbw/keyParList.action?keyPar.companyName=','M083002','企业关键装置和重点部位')">企业关键装置和重点部位</a></td>
							<td><a href="javaScript:openTab('jsp/zyyl/maiMatList.action?maiMat.companyName=','M083003','主要原料')">主要原料</a></td>
						</tr>
						<tr>
							<td><a href="javaScript:openTab('jsp/xfssfbt/firMapList.action?firMap.companyName=','M083004','消防设施分布图')">消防设施分布图</a></td>
							<td><a href="javaScript:openTab('jsp/aqssdjtz/safLedList.action?safLed.companyName=','M083005','安全设施登记台账')">安全设施登记台账</a></td>
							<td><a href="javaScript:openTab('jsp/swwzxx/matInfList.action?matInf.companyName=','M083006','涉危物质信息')">涉危物质信息</a></td>
						</tr>
						<tr>
							<td><a href="javaScript:openTab('jsp/zbhjxx/envInfList.action?envInf.companyName=','M083007','周边环境信息')">周边环境信息</a></td>
							<td><a href="javaScript:openTab('jsp/wpwxxzsk/risKnoBasList.action?risKnoBas.companyName=','M083008','物品危险性知识库')">物品危险性知识库</a></td>
							<td><a href="javaScript:openTab('jsp/whaqglzd/cheSafManList.action?cheSafMan.companyName=','M083009','危化安全管理制度')">危化安全管理制度</a></td>
						</tr>
						
						<tr>
							<th style="text-align:center" colspan="3">涉爆粉尘企业信息管理</th>
						</tr>
						<tr>
							<td><a href="javaScript:openTab('jsp/fczycsgl/dusWorManList.action?dusWorMan.companyName=','M082501','粉尘作业场所管理')">粉尘作业场所管理</a></td>
						</tr>
						
						<tr>
							<th style="text-align:center" colspan="3">应急救援</th>
						</tr>
						<tr>
							<td><a href="javaScript:openTab('jsp/yjjgydw/emeAgeList.action?emeAge.companyName=','M082601','应急机构与队伍')">应急机构与队伍</a></td>
							<td><a href="javaScript:openTab('jsp/yjya/emePlaList.action?type=0&emePla.companyName=','M082602','应急预案')">应急预案</a></td>
							<td><a href="javaScript:openTab('jsp/yjsszbwz/emeFacList.action?type=0&emeFac.companyName=','M082603','应急设施装备物资')">应急设施装备物资</a></td>
						</tr>
						<tr>
							<td><a href="javaScript:openTab('jsp/yjyl/emeDriList.action?emeDri.companyName=','M082604','应急演练')">应急演练</a></td>
						</tr>
						<tr>
							<th style="text-align:center" colspan="3">事故报告调查和处理</th>
						</tr>
						<tr>
							<td><a href="javaScript:openTab('jsp/sgbgdchcl/accRepInvHanList.action?accRepInvHan.companyName=','M082701','事故报告调查和处理')">事故报告调查和处理</a></td>
						</tr>
					</table>
					</div>
				</div>
			</div>
			</div>
		</div>
	</body>
</html>

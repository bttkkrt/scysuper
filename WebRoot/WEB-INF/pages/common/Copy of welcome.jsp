<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>首页</title>
		 <%@include file="/common/jsLib.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="${ctx }/webResources/styleForIndex/css/default/animation.css">
		<link rel="stylesheet" type="text/css" href="${ctx }/webResources/styleForIndex/css/default/base.css">
		<link rel="stylesheet" type="text/css" href="${ctx }/webResources/styleForIndex/css/default/form.css">
		<link rel="stylesheet" type="text/css" href="${ctx }/webResources/styleForIndex/css/default/login.css">
		<script type="text/javascript" src="${ctx}/webResources/json.js"></script>
		
		<script type="text/javascript">
			function getMark(){
				return '${roleName}';
			}
			function getCompanyCode(){
				return '${codeId}';
			}
			function initMap(){
			 	if('${roleName}' == '0'){
			 		
			 		window.frames["map"].LocateComPany("${codeId}","企业");	//调用展示企业打点方法  ${codeId}  这个地方调有问题没？
			 	}else{
			 		window.frames["map"].InitGrid();//调用显示所有的网格
			 	}
			 }
		
			function GetCompanyGrids(){
				var name = $("#name").val();
					//异步获取网格的数据
		    	 $.ajax({
		                	url : "${ctx}/jsp/map/tbMapGetGridJSON.action?flag="+name,
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	ids : ""
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','查询时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	window.frames["map"].LocateComPany(data.ids);
		                        }else{
		                        	$.messager.alert('错误','查询时出错！');
		                        }
		                    }
		                });
			}
		    function PopupComPany(){//模糊查询企业列表
		    	var name = $("#com").val();
		    	//异步获取企业的数据
		    	 $.ajax({
		                	url : "${ctx}/jsp/map/tbMapGetCompanyJSON.action?flag="+encodeURI(encodeURI(name)),
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	ids : ""
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','查询时出错！');
		                    },
		                    success: function(data){
		                    	
		                        if(data.result){
		                        	var dots = data.ids+data.ids;
		                        	window.frames["map"].LocateComPany(dots,"企业");
		                        }else{
		                        	$.messager.alert('错误','查询时出错2！');
		                        }
		                    }
		                });
		    }
		    function GetCompanyDetail(obj){
		    	var detail = "";
			        //异步获取企业的数据
		    	 $.ajax({
		                	url : "${ctx}/jsp/map/tbMapGetCompanyDetailJSON.action?flag="+obj,
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	ids : obj 
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','查询时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	detail = data.detail;
		                        }else{
		                        	$.messager.alert('错误','查询时出错！');
		                        }
		                    }
		                });
			    return detail;
		    	
		    }
		    //此方法接收silverlight中穿过来的id 获取企业详情
        function DetailedInfo(id) {
            //向sl传入企业详细信息进行气泡展示
            var infoStr = GetCompanyDetail(id);//json字符串
            window.frames["map"].PopupComPany(infoStr);
        }
        function k(){
        	alert(1);
        }
        
        function searchCom()
        {
        	var companyName = document.getElementById('companyName').value;
        	 $.ajax({
		                	url : "${ctx}/jsp/map/searchCom.action?companyName="+encodeURIComponent(companyName),
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    },
		                    error: function(){
		                    	alert('查询时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	parent.document.getElementById('companyName').value=companyName;
		                        	parent.document.getElementById('drawer').style.display="";
		                        }else{
		                        	alert('查询时出错！');
		                        }
		                    }
		                });
        }
        
        function zhcx(code,name)
        {
        	var id="newWindow";
    		var text = name+"综合查询";
    		var url ="/jsp/qyjbxx/zhcxTab.action?entBaseInfo.enterpriseCode="+code+"&entBaseInfo.enterpriseName="+encodeURIComponent(name);
            window.parent.addTab(id,text,url);
        }
        
        function getGridYhDataByMapKey(key){
        	var json;
        	$.ajax({
              	url : "${ctx}/jsp/yhb/getTongjiByGridCode.action",
              	 data:{ 
		                    	mapKey:key
		                    },
              	type: 'post',
                  dataType: 'json',
                  async : false,
                  error: function(){
                  	$.messager.alert('错误','查询时出错！');
                  },
                  success: function(data){
                  		json=data;	 
                  			
                  		
                  	}
	                });
	        return json;
        }
        
		</script>
	</head>
	<body style="overflow: auto;">
		<div class="newpage" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
        <div class="matters_lf">
        	<div class="Pending">
            	<div class="pd_title">未处理事项</div>
            	<ul>
        	<s:if test="roleName == 0"><!-- 企业 -->
        		<li>待整改隐患<span>${auditInfo.yhCount }</span>个<a href="#" onclick="window.parent.addTab('todo','隐患管理','jsp/yhb/troManList.action?troMan.rectificationState='+ encodeURIComponent('待整改'));">查看</a></li>
        	</s:if>
        	
        	<s:if test="roleName == 1"><!-- 安委会办公室 -->
        		<li>待审核隐患<span>${auditInfo.yhShCount }</span>个<a href="#" onclick="window.parent.addTab('todo','安全生产隐患管理','jsp/yhb/troManList.action?troMan.rectificationState='+ encodeURIComponent('待审核'));">查看</a></li>
        	</s:if>
        	
        	<s:if test="roleName == 2"><!-- 中队队员 -->
        		<li>待审核隐患<span>${auditInfo.yhShCount }</span>个<a href="#" onclick="window.parent.addTab('todo','安全生产隐患管理','jsp/yhb/troManList.action?troMan.rectificationState='+ encodeURIComponent('待审核'));">查看</a></li>
        		<li>待整改隐患<span>${auditInfo.yhCount }</span>个<a href="#" onclick="window.parent.addTab('todo','安全生产隐患管理','jsp/yhb/troManList.action?troMan.rectificationState='+ encodeURIComponent('待整改'));">查看</a></li>
        		<li>待检查任务<span>${auditInfo.jdjcCount }</span>个<a href="#" onclick="window.parent.addTab('todo','监督检查任务管理','jsp/jdjcrw/supTasList.action?supTas.taskState='+ encodeURIComponent('未完成'));">查看</a></li>
        		<li>待审核企业<span>${auditInfo.qyCount }</span>个<a href="#" onclick="window.parent.addTab('todo','企业基本信息','jsp/qyjbxx/entBaseInfoList.action?entBaseInfo.basePass=0');">查看</a></li>             
        	</s:if>
        	
        	<s:if test="roleName == 3"><!-- 中队长 -->
        		<li>待审核隐患<span>${auditInfo.yhCount }</span>个<a href="#" onclick="window.parent.addTab('todo','安全生产隐患管理','jsp/yhb/troManList.action?troMan.rectificationState='+ encodeURIComponent('待审核'));">查看</a></li>
        	</s:if>
        	
        	<s:if test="roleName == 4"><!-- 监察大队 -->
        		<li>待审核重点危险源<span>${auditInfo.wxyCount }</span>个<a href="#" onclick="window.parent.addTab('todo','企业重点危险源的识别评估分级','jsp/zdwxysbpgfj/comDanIdeList.action?comDanIde.auditState='+ encodeURIComponent('待审核'));">查看</a></li>
        		<li>待审核巡查计划<span>${auditInfo.wxyxcCount }</span>个<a href="#" onclick="window.parent.addTab('todo','危险源巡查计划管理','jsp/wxyxcjhgl/danPlaManList.action?danPlaMan.auditResult='+ encodeURIComponent('待审核'));">查看</a></li>
        		<li>待审核隐患<span>${auditInfo.yhCount }</span>个<a href="#" onclick="window.parent.addTab('todo','安全生产隐患管理','jsp/yhb/troManList.action?troMan.rectificationState='+ encodeURIComponent('待审核'));">查看</a></li>
        		<li>待审核案件<span>${auditInfo.ajCount }</span>个<a href="#" onclick="window.parent.addTab('todo','案件管理','jsp/case/caseInfoList.action?caseInfo.caseStatus=0');">查看</a></li>
            	<li>待审核文书<span>${auditInfo.wsCount }</span>个<a href="#" onclick="window.parent.addTab('todo','文书管理','jsp/wsgl/instrumentsInfoList.action?instrumentsInfo.ifCheck=1');">查看</a></li>           
        		<li>待审核失信行为<span>${auditInfo.sxxwCount }</span>个<a href="#" onclick="window.parent.addTab('todo','企业失信行为管理','jsp/sxxw/dishonestyList.action?dishonesty.checkStatus='+ encodeURIComponent('待审核'));">查看</a></li>     
        	</s:if>
        	
        	<s:if test="roleName == 5"><!-- 局领导 -->
  				<li>待审核资质情况<span>${auditInfo.zzCount }</span>个<a href="#" onclick="window.parent.addTab('todo','行政许可及资质情况','jsp/zzqk/intSitList.action?intSit.auditState='+ encodeURIComponent('待审核'));">查看</a></li>
        		<li>待审核荣誉表彰<span>${auditInfo.bzCount }</span>个<a href="#" onclick="window.parent.addTab('todo','荣誉表彰信息','jsp/rybzxx/honRecList.action?honRec.auditState='+ encodeURIComponent('待审核'));">查看</a></li>
        		<li>待审核发文<span>${auditInfo.fwCount }</span>个<a href="#" onclick="window.parent.addTab('todo','发文管理','jsp/fwgl/sendInformationList.action?sendInformation.auditState='+ encodeURIComponent('待审核'));">查看</a></li>
        		<li>待审核隐患<span>${auditInfo.yhCount }</span>个<a href="#" onclick="window.parent.addTab('todo','安全生产隐患管理','jsp/yhb/troManList.action?troMan.rectificationState='+ encodeURIComponent('待审核'));">查看</a></li>
        		<li>待审核案件<span>${auditInfo.ajCount }</span>个<a href="#" onclick="window.parent.addTab('todo','案件管理','jsp/case/caseInfoList.action?caseInfo.caseStatus=0');">查看</a></li>  
            	<li>待审核文书<span>${auditInfo.wsCount }</span>个<a href="#" onclick="window.parent.addTab('todo','文书管理','jsp/wsgl/instrumentsInfoList.action?instrumentsInfo.ifCheck=1');">查看</a></li>
            	<li>待审批失信行为<span>${auditInfo.sxxwCount }</span>个<a href="#" onclick="window.parent.addTab('todo','企业失信行为管理','jsp/sxxw/dishonestyList.action?dishonesty.checkStatus='+ encodeURIComponent('待审批'));">查看</a></li>                
        		<li>待审批挂牌督办<span>${auditInfo.dbCount1 }</span>个<a href="#" onclick="window.parent.addTab('todo','挂牌督办','jsp/gpdb/superviceList.action?supervice.rectificationState='+ encodeURIComponent('待审批'));">查看</a></li>
            	<li>已整改待审批挂牌督办<span>${auditInfo.dbCount2 }</span>个<a href="#" onclick="window.parent.addTab('todo','挂牌督办','jsp/gpdb/superviceList.action?supervice.rectificationState='+ encodeURIComponent('已整改待审批'));">查看</a></li>          
        	</s:if>
        	
			<s:if test="roleName == 6"><!-- 综合处长 -->
            	<li>待审核挂牌督办<span>${auditInfo.dbCount1 }</span>个<a href="#" onclick="window.parent.addTab('todo','挂牌督办','jsp/gpdb/superviceList.action?supervice.rectificationState='+ encodeURIComponent('待审核'));">查看</a></li> 
            	<li>已整改待审核挂牌督办<span>${auditInfo.dbCount2 }</span>个<a href="#" onclick="window.parent.addTab('todo','挂牌督办','jsp/gpdb/superviceList.action?supervice.rectificationState='+ encodeURIComponent('已整改待审核'));">查看</a></li>        
        		<li>待审核文书<span>${auditInfo.wsCount }</span>个<a href="#" onclick="window.parent.addTab('todo','文书管理','jsp/wsgl/instrumentsInfoList.action?instrumentsInfo.ifCheck=1');">查看</a></li>           
        	</s:if>
        	
			<s:if test="roleName == 7"><!-- 危化处处长 -->
            	<li>待审核领导带班情况<span>${auditInfo.lddbCount }</span>个<a href="#" onclick="window.parent.addTab('todo','领导带班情况','jsp/lddbqk/leaClaList.action?leaCla.auditState='+ encodeURIComponent('待审核'));">查看</a></li>  
            	<li>待审核节假日开停工<span>${auditInfo.jjrktgCount }</span>个<a href="#" onclick="window.parent.addTab('todo','节假日开停工','jsp/jjrktg/shuHolList.action?shuHol.auditState='+ encodeURIComponent('待审核'));">查看</a></li>            
        		<li>待审核文书<span>${auditInfo.wsCount }</span>个<a href="#" onclick="window.parent.addTab('todo','文书管理','jsp/wsgl/instrumentsInfoList.action?instrumentsInfo.ifCheck=1');">查看</a></li>            
        	</s:if>
        	
        	<s:if test="roleName == 8"><!-- 职能部门 -->
        		<li>待整改隐患<span>${auditInfo.yhCount }</span>个<a href="#" onclick="window.parent.addTab('todo','安全生产隐患管理','jsp/yhb/troManList.action?troMan.rectificationState='+ encodeURIComponent('待整改'));">查看</a></li>
        	</s:if>
        	
        	<s:if test="roleName == 9"><!-- 综合处科员 -->
        		<li>待整改挂牌督办<span>${auditInfo.dbCount }</span>个<a href="#" onclick="window.parent.addTab('todo','挂牌督办','jsp/gpdb/superviceList.action?supervice.rectificationState='+ encodeURIComponent('待整改'));">查看</a></li>
        		<li>待审核文书<span>${auditInfo.wsCount }</span>个<a href="#" onclick="window.parent.addTab('todo','文书管理','jsp/wsgl/instrumentsInfoList.action?instrumentsInfo.ifCheck=1');">查看</a></li>            
        	</s:if>
        	
        	<s:if test="roleName == 10"><!-- 监察大队队员 -->
        		<li>待审核案件<span>${auditInfo.ajCount }</span>个<a href="#" onclick="window.parent.addTab('todo','案件管理','jsp/case/caseInfoList.action?caseInfo.caseStatus=0');">查看</a></li>
            	<li>待审核文书<span>${auditInfo.wsCount }</span>个<a href="#" onclick="window.parent.addTab('todo','文书管理','jsp/wsgl/instrumentsInfoList.action?instrumentsInfo.ifCheck=1');">查看</a></li>
        	</s:if>
        	
        	<s:if test="roleName == 11"><!-- 安监局其他人员 -->
            	<li>待审核文书<span>${auditInfo.wsCount }</span>个<a href="#" onclick="window.parent.addTab('todo','文书管理','jsp/wsgl/instrumentsInfoList.action?instrumentsInfo.ifCheck=1');">查看</a></li>
        	</s:if>
        	
        	<s:if test="roleName == 1012"><!-- 中队队员 -->
        		<li>待审核隐患<span>${auditInfo.yhShCount }</span>个<a href="#" onclick="window.parent.addTab('todo','安全生产隐患管理','jsp/yhb/troManList.action?troMan.rectificationState='+ encodeURIComponent('待审核'));">查看</a></li>
        		<li>待整改隐患<span>${auditInfo.yhCount }</span>个<a href="#" onclick="window.parent.addTab('todo','安全生产隐患管理','jsp/yhb/troManList.action?troMan.rectificationState='+ encodeURIComponent('待整改'));">查看</a></li>
        		<li>待检查任务<span>${auditInfo.jdjcCount }</span>个<a href="#" onclick="window.parent.addTab('todo','监督检查任务管理','jsp/jdjcrw/supTasList.action?supTas.taskState='+ encodeURIComponent('未完成'));">查看</a></li>
        		<li>待审核企业<span>${auditInfo.qyCount }</span>个<a href="#" onclick="window.parent.addTab('todo','企业基本信息','jsp/qyjbxx/entBaseInfoList.action?entBaseInfo.basePass=0');">查看</a></li>
        		<li>待审核案件<span>${auditInfo.ajCount }</span>个<a href="#" onclick="window.parent.addTab('todo','案件管理','jsp/case/caseInfoList.action?caseInfo.caseStatus=0');">查看</a></li>
            	<li>待审核文书<span>${auditInfo.wsCount }</span>个<a href="#" onclick="window.parent.addTab('todo','文书管理','jsp/wsgl/instrumentsInfoList.action?instrumentsInfo.ifCheck=1');">查看</a></li>  
        	</s:if>
        	
        	<s:if test="roleName == 1011"><!-- 中队长 -->
        		<li>待审核隐患<span>${auditInfo.yhCount }</span>个<a href="#" onclick="window.parent.addTab('todo','安全生产隐患管理','jsp/yhb/troManList.action?troMan.rectificationState='+ encodeURIComponent('待审核'));">查看</a></li>
        		<li>待审核案件<span>${auditInfo.ajCount }</span>个<a href="#" onclick="window.parent.addTab('todo','案件管理','jsp/case/caseInfoList.action?caseInfo.caseStatus=0');">查看</a></li> 
            	<li>待审核文书<span>${auditInfo.wsCount }</span>个<a href="#" onclick="window.parent.addTab('todo','文书管理','jsp/wsgl/instrumentsInfoList.action?instrumentsInfo.ifCheck=1');">查看</a></li>
        	</s:if>
        </ul>
  	</div>     
  	
  	
  	<div class="information">
         <div class="infor_title_my">我的信息<a href="#" onclick="window.parent.addTab('notice','通知公告','/jsp/information/contentInformationsList.action');" class="more">更多</a></div>
             <ul>
        	<c:forEach items="${notices}" var="un" varStatus="i">
        		<li><a href="#" id="a" onclick="window.open('${ctx}/jsp/information/contentInformationsView.action?contentInformations.id=${un.id}&flag=0');" title="${un.title }"> 
        			<div style="text-overflow:ellipsis; white-space: nowrap; overflow: hidden;">
        			${un.title}
        			</div>
        		</a></li>
        	</c:forEach>
          </ul>                
       </div>
    </div>
    
    
</div>
<div class="map_rh">

		<div style="position:absolute;z-index:120;margin-left:70px; margin-top:8px;">
			<div class="search radius4px">
				<input type="text" id="com"/>
				<a href="#" class="btn" onclick="PopupComPany();">检索</a>
			</div>
		</div>
		<div style="width:100%;position:absolute;z-index:100;">
        	<iframe id="map" name="map" src="${ctx}/webResources/Map/index2.html"  style="height:800px;width:100%;"scrolling="no" ></iframe>
    	</div>
</div>
</div>
	</body>
</html>
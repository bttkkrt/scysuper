<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>危化信息3</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8" />
    <link href="${ctx}/webResources/zwt/css/chosen.min.css" rel="stylesheet">
    <link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/webResources/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/easyui/windowOnMove.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/easyui/locale/easyui-lang-zh_CN.js"></script>

<!-- easyui css -->
<link rel="stylesheet" type="text/css" href="${ctx}/webResources/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/webResources/js/easyui/themes/icon.css">

<script src="${ctx}/webResources/zwt/layer/layer.js"></script>

<style>
html{background-color:#FDFDFD; font-size:14px; color:#000; font-family:'微软雅黑'}
a,a:hover{ text-decoration:none;}
pre{font-family:'微软雅黑'}
.box{padding:20px; background-color:#fff; margin:50px 100px; border-radius:5px;}
.box a{padding-right:15px;}
#about_hide{display:none}
.layer_text{background-color:#fff; padding:20px;}
.layer_text p{margin-bottom: 10px; text-indent: 2em; line-height: 23px;}
.button{display:inline-block; *display:inline; *zoom:1; line-height:30px; padding:0 20px; background-color:#56B4DC; color:#fff; font-size:14px; border-radius:3px; cursor:pointer; font-weight:normal;}
.photos-demo img{width:200px;}
</style>
    <!--[if lt IE 8]>
    <![endif]-->
    <script type="text/javascript">
    	var ps=50;
    //i=1:zbhj,2wxxzsk,3:whaqglzd,4:xfssfb,5:aqssdj
    	 $(function(){
    	 	//$("#div1").load("${ctx}/zwt/envInfQuery.action?entBaseInfo.id=${entBaseInfo.id}&searchLike=${entBaseInfo.enterpriseName}");
    	 	getList('1',1,ps,"${ctx}/zwt/envInfQuery.action?entBaseInfo.id=${entBaseInfo.id}");
    	 	getList('2',1,ps,"${ctx}/zwt/risKnoBasQuery.action?entBaseInfo.id=${entBaseInfo.id}");
    	 	getList('3',1,ps,"${ctx}/zwt/cheSafManQuery.action?entBaseInfo.id=${entBaseInfo.id}");
    	 	getList('4',1,ps,"${ctx}/zwt/firMapQuery.action?entBaseInfo.id=${entBaseInfo.id}");
    	 	getList('5',1,ps,"${ctx}/zwt/safLedQuery.action?entBaseInfo.id=${entBaseInfo.id}");
    	 	
		});
		 function getSpanByName(name)
        {
        	return "<span title='" + name + "' style='cursor:pointer'>" + name + "</span>";
        }
		function getList(i,num,size,url){ 
			ajaxData.pageNo=num;
			ajaxData.pageSize=size;
			ajaxData.searchLike="${searchLike}";
			$("#currPage"+i).val(num);
			$.ajax({
		                	url : url,
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:ajaxData,
		                    error: function(){
		                    },
		                    success: function(data){
		                    	var trs=getTrs(data,i);
		                    	document.getElementById('tbody'+i).innerHTML=trs;
		                    	document.getElementById('pageNo'+i).innerHTML=data.pageNo;
		                    	document.getElementById('totalPage'+i).innerHTML=data.totalPage;
		                        document.getElementById('count'+i).innerHTML=data.count;
		                    }
		                });
		}
		
		
		function getTrs(data,i){
			var t='';
			if(i=='1'){
				var index=(data.pageNo-1)*ps+1;
			    $.each(data.result,function(idx,item){ 
					//获取操作按钮
			         var tr="";
			         if(idx%2==0){
			             tr="<tr class='row_1'><td>"+index+++"</td>";
			         }else{
			             tr="<tr><td>"+index+++"</td>";
			         }
			         tr+="<td><a onclick=viewZbhj('"+item.id+"') href='javascript:;' style='color:#27a9e3' >"
			             +getSpanByName(item.surroundingEnvironmentType)+"</a></td><td>"+getSpanByName(item.personnelType)+"</td><td>"+getSpanByName(item.surroundingEnvironmentName)+"</td><td>"+getSpanByName(item.contactPerson)+"</td></tr>"
			          t+=tr;
				});
				
			}else if(i=='2'){
				var index=(data.pageNo-1)*ps+1;
			    $.each(data.result,function(idx,item){ 
		            //获取操作按钮
		            var tr="";
		            if(idx%2==0){
		            	tr="<tr class='row_1'><td>"+index+++"</td>";
		            }else{
		                tr="<tr><td>"+index+++"</td>";
		            }
		            tr+="<td><a onclick=viewWxxzsk('"+item.id+"') href='javascript:;' style='color:#27a9e3' >"
		              +getSpanByName(item.itemName)+"</a></td><td>"+getSpanByName(item.dangerousContent)+"</td><td>"+getSpanByName(item.responseMeasures)+"</td></tr>"
		            t+=tr;			          
			          
				});
				
			}else if(i=='3'){
				var index=(data.pageNo-1)*ps+1;
			    $.each(data.result,function(idx,item){ 

		            //获取操作按钮
		            var tr="";
		            if(idx%2==0){
		            	tr="<tr class='row_1'><td>"+index+++"</td>";
		            }else{
		                tr="<tr><td>"+index+++"</td>";
		            }
		            tr+="<td><a onclick=viewWhaqglzd('"+item.id+"') href='javascript:;' style='color:#27a9e3' >"
		               +getSpanByName(item.systemName)+"</a></td></tr>"
		            t+=tr;			          
			          
				});
				
			}else if(i=='4'){
				var index=(data.pageNo-1)*ps+1;
			    $.each(data.result,function(idx,item){ 

		            //获取操作按钮
		            var tr="";
		            if(idx%2==0){
		            	tr="<tr class='row_1'><td>"+index+++"</td>";
		            }else{
		                tr="<tr><td>"+index+++"</td>";
		            }
		            tr+="<td><a onclick=viewXfssfb('"+item.id+"') href='javascript:;' style='color:#27a9e3' >"
		               +getSpanByName(item.facilityName)+"</a></td></tr>"
		            t+=tr;			          
			          
				});
				
			}else if(i=='5'){
				var index=(data.pageNo-1)*ps+1;
			    $.each(data.result,function(idx,item){ 
			    	var accountType=item.accountType;
		            if(accountType==1){
                    	accountType="预防事故设施台帐";
                    } else if(accountType==2){
                        accountType="控制事故设施台帐";
                    }else if(accountType==3){
                        accountType="减少与消除事故影响设施台帐";
                    }
                    
                    var category=item.category;
                    if(item.accountType==1&&(category==1)){
			            category="检测、报警设施";
			        } else if(item.accountType==1&&(category==2)){
			            category="设备安全防护设施";
			        }else if(item.accountType==1&&(category==3)){
			             category="防爆设施";
			        }else if(item.accountType==1&&(category==4)){
			             category="作业场所防护设施";
			        }else if(item.accountType==1&&(category==5)){
			             category="安全警示标志";
			        }else if(item.accountType==2&&(category==1)){
			             category="泄压和止逆设施";
			        }else if(item.accountType==2&&(category==2)){
			             category="紧急处理设施";
			        }else if(item.accountType==3&&(category==1)){
			             category="防止火灾蔓延设施";
			        }else if(item.accountType==3&&(category==2)){
			             category="灭火设施";
			        }else if(item.accountType==3&&(category==3)){
			             category="紧急个体处置设施";
			        }else if(item.accountType==3&&(category==4)){
			             category="应急救援设施";
			        }else if(item.accountType==3&&(category==5)){
			             category="逃生避难设施";
			        }else if(item.accountType==3&&(rec.category==6)){
			             category="劳动防护用品和装备";
			         }
                                 

		            //获取操作按钮
		            var tr="";
		            if(idx%2==0){
		            	tr="<tr class='row_1'><td>"+index+++"</td>";
		            }else{
		                tr="<tr><td>"+index+++"</td>";
		            }
		            tr+="<td><a onclick=viewAqssdj('"+item.id+"') href='javascript:;' style='color:#27a9e3' >"
		              +getSpanByName(accountType)+"</a></td><td>"+getSpanByName(category)+"</td></tr>"
		            t+=tr;			          
			          
				});
				
			}else {}
			
			return t;
		}
		
		
		 function viewZbhj(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_envInf","查看周边环境信息","${ctx}/zwt/envInfView.action?envInf.id="+row_Id+"&dt="+dt.getTime(),1000,560);
        	
        }
		
		function viewWxxzsk(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_risKnoBas","查看物品危险性知识库","${ctx}/zwt/risKnoBasView.action?risKnoBas.id="+row_Id+"&dt="+dt.getTime(),700,480);
        	
        }
        
		function viewWhaqglzd(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_cheSafMan","查看危化安全管理制度","${ctx}/zwt/cheSafManView.action?cheSafMan.id="+row_Id+"&dt="+dt.getTime(),700,450);
        	
        }
		
		function viewXfssfb(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_firMap","查看消防设施分布图","${ctx}/zwt/firMapView.action?firMap.id="+row_Id+"&dt="+dt.getTime(),1000,550);
        	
        }
        
         function viewAqssdj(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_safLed","查看安全设施登记台账","${ctx}/zwt/safLedView.action?safLed.id="+row_Id+"&dt="+dt.getTime(),700,400);
        	
        }
		
		function first(i,url){
			getList(i,1,ps,url);
		}
		function previous(i,url){
			var curPage= parseInt(document.getElementById('pageNo'+i).innerHTML);
			if(curPage==1){
				return false;
			}else{
				getList(i,curPage-1,ps,url);			
			}
		}
		
		function next(i,url){
			var curPage= parseInt(document.getElementById('pageNo'+i).innerHTML);
			var totalPage= parseInt(document.getElementById('totalPage'+i).innerHTML);
			if(curPage==totalPage){
				return false;
			}else{
				getList(i,curPage+1,ps,url);	
			}
		}
		
		function last(i,url){
			var totalPage= parseInt(document.getElementById('totalPage'+i).innerHTML);
			getList(i,totalPage,ps,url);	
		}
		
		function createSimpleWindow(a,b,c,d,e){
        	var dt=new Date();
        	var index = layer.open({
		        type: 2,
		        skin: 'layui-layer-lan',
		        title: b,
		        shadeClose: true,
		        maxmin: true,
		        area: ['1000px', '500px'],
		        content: c,
		        end: function(){
		        		getList(ajaxData.pageNo,ajaxData.pageSize);
		        	}
		    });
		    layer.full(index);
        }
    </script>
</head>
<body>
<!-- Wrap all page content here -->
                    <!--bgtable-->
                    <div class="sipac_tableBox" >
                       <div class="sipac_tableBar">
                            <div class="sipac_table_menu"><span>周边环境</span></div>
                        </div>
                       <div class="table-head">
                	<table style="width:100%;">
                		<colgroup>
             				<col style="width:10%"/>
              				<col style="width:25%"/>
              				<col style="width:15%"/>
              				<col style="width:25%"/>
              				<col style="width:15%"/>
          				</colgroup>
	                    	<thead>
	                        <tr>
	                        	<!--<th style="width:40px;"><span><input type="checkbox"></span></th>
	                        	--><th><span>序号</span></th>
	                        	<th><span>周边环境类型 </span></th>
	                        	<th><span>人员数量</span></th>
	                        	<th><span>周边环境名称 </span></th>
	                        	<th><span>联系人 </span></th>
	                        </tr>
	  						</thead>
	                    	</table>
     			</div>
     			<div class="table-body">
     				<table style="width:100%;">
         				<colgroup>
         					<col style="width:10%"/>
              				<col style="width:25%"/>
              				<col style="width:15%"/>
              				<col style="width:25%"/>
              				<col style="width:15%"/>
         				</colgroup>
                    	<tbody id="tbody1">
  						</tbody>
                    </table>
                </div>
	                	<div class="sipac_pagebox">
	                        <div class="s_p_info">第<span id="pageNo1">1</span>页 / 共<span id="totalPage1"></span>页，共<span id="count1"></span>条数据</div> 
	                        <div class="s_p_btn"><a href="###" onclick="first('1','${ctx}/zwt/envInfQuery.action')">首页</a><a href="###"  onclick="previous('1','${ctx}/zwt/envInfQuery.action')"  >上一页</a><a href="###" onclick="next('1','${ctx}/zwt/envInfQuery.action')" >下一页</a><a href="###" onclick="last('1','${ctx}/zwt/envInfQuery.action')" >尾页</a></div>
	                	</div>
                    </div>
                    <!--bgtable end-->
                    <div style="height:10px"/>   
                    <!--bgtable-->
                    <div class="sipac_tableBox" >
                       <div class="sipac_tableBar">
                            <div class="sipac_table_menu"><span>危险性知识库</span></div>
                        </div>
                       <div class="table-head">
                	<table style="width:100%;">
                		<colgroup>
             				<col style="width:10%"/>
              				<col style="width:20%"/>
              				<col style="width:35%"/>
              				<col style="width:35%"/>
          				</colgroup>
	                    	<thead>
	                         <tr>
                        	<!--<th style="width:40px;"><span><input type="checkbox"></span></th>
                        	--><th><span>序号</span></th>
                        	<th><span>物品名称</span></th>
                        	<th><span>危险性内容 </span></th>
                        	<th><span>应对措施 </span></th>
                        </tr>
	  						</thead>
	                    	</table>
     			</div>
     			<div class="table-body">
     				<table style="width:100%;">
         				<colgroup>
         					<col style="width:10%"/>
              				<col style="width:20%"/>
              				<col style="width:35%"/>
              				<col style="width:35%"/>
         				</colgroup>
                    	<tbody id="tbody2">
  						</tbody>
                    </table>
                </div>
	                	<div class="sipac_pagebox">
	                        <div class="s_p_info">第<span id="pageNo2">1</span>页 / 共<span id="totalPage2"></span>页，共<span id="count2"></span>条数据</div> 
	                        <div class="s_p_btn"><a href="###" onclick="first('2','${ctx}/zwt/risKnoBasQuery.action')">首页</a><a href="###"  onclick="previous('2','${ctx}/zwt/risKnoBasQuery.action')"  >上一页</a><a href="###" onclick="next('2','${ctx}/zwt/risKnoBasQuery.action')" >下一页</a><a href="###" onclick="last('2','${ctx}/zwt/risKnoBasQuery.action')" >尾页</a></div>
	                	</div>
                    </div>
                    <!--bgtable end-->
                    <div style="height:10px"/>   
                    <!--bgtable-->
                    <div class="sipac_tableBox" >
                       <div class="sipac_tableBar">
                            <div class="sipac_table_menu"><span>危化安全管理制度</span></div>
                        </div>
                       <div class="table-head">
                	<table style="width:100%;">
                		<colgroup>
             				<col style="width:10%"/>
              				<col style="width:90%"/>
          				</colgroup>
	                    	<thead>
		                        <tr>
	                        	<!--<th style="width:40px;"><span><input type="checkbox"></span></th>
	                        	--><th><span>序号</span></th>
	                        	<th><span>制度名称 </span></th>
	                        	</tr>
	  						</thead>
	                    	</table>
     			</div>
     			<div class="table-body">
     				<table style="width:100%;">
         				<colgroup>
         					<col style="width:10%"/>
              				<col style="width:90%"/>
         				</colgroup>
                    	<tbody id="tbody3">
  						</tbody>
                    </table>
                </div>
	                	<div class="sipac_pagebox">
	                        <div class="s_p_info">第<span id="pageNo3">1</span>页 / 共<span id="totalPage3"></span>页，共<span id="count3"></span>条数据</div> 
	                        <div class="s_p_btn"><a href="###" onclick="first('3','${ctx}/zwt/cheSafManQuery.action')">首页</a><a href="###"  onclick="previous('3','${ctx}/zwt/cheSafManQuery.action')"  >上一页</a><a href="###" onclick="next('3','${ctx}/zwt/cheSafManQuery.action')" >下一页</a><a href="###" onclick="last('3','${ctx}/zwt/cheSafManQuery.action')" >尾页</a></div>
	                	</div>
                    </div>
                    <!--bgtable end-->   
                    <div style="height:10px"/>   
                    <!--bgtable-->
                    <div class="sipac_tableBox" >
                       <div class="sipac_tableBar">
                            <div class="sipac_table_menu"><span>消防设施分布</span></div>
                        </div>
                      <div class="table-head">
                	<table style="width:100%;">
                		<colgroup>
             				<col style="width:10%"/>
              				<col style="width:90%"/>
          				</colgroup>
	                    	<thead>
		                        <tr>
	                        	<!--<th style="width:40px;"><span><input type="checkbox"></span></th>
	                        	--><th><span>序号</span></th>
	                        	<th><span>设施名称 </span></th>
	                        	</tr>
	  						</thead>
	                    	</table>
     			</div>
     			<div class="table-body">
     				<table style="width:100%;">
         				<colgroup>
         					<col style="width:10%"/>
              				<col style="width:90%"/>
         				</colgroup>
                    	<tbody id="tbody4">
  						</tbody>
                    </table>
                </div>
	                	<div class="sipac_pagebox">
	                        <div class="s_p_info">第<span id="pageNo4">1</span>页 / 共<span id="totalPage4"></span>页，共<span id="count4"></span>条数据</div> 
	                        <div class="s_p_btn"><a href="###" onclick="first('4','${ctx}/zwt/firMapQuery.action')">首页</a><a href="###"  onclick="previous('4','${ctx}/zwt/firMapQuery.action')"  >上一页</a><a href="###" onclick="next('4','${ctx}/zwt/firMapQuery.action')" >下一页</a><a href="###" onclick="last('4','${ctx}/zwt/firMapQuery.action')" >尾页</a></div>
	                	</div>
                    </div>
                    <!--bgtable end--> 
                    <div style="height:10px"/>   
                    <!--bgtable-->
                    <div class="sipac_tableBox" >
                       <div class="sipac_tableBar">
                            <div class="sipac_table_menu"><span>安全设施登记台账</span></div>
                        </div>
                       <div class="table-head">
                	<table style="width:100%;">
                		<colgroup>
             				<col style="width:10%"/>
              				<col style="width:45%"/>
              				<col style="width:45%"/>
          				</colgroup>
	                    	<thead>
		                        <tr>
	                        	<!--<th style="width:40px;"><span><input type="checkbox"></span></th>
	                        	--><th><span>序号</span></th>
	                        	<th><span>台账类型 </span></th>
                        		<th><span>类别 </span></th>
	                        	</tr>
	  						</thead>
	                    	</table>
     			</div>
     			<div class="table-body">
     				<table style="width:100%;">
         				<colgroup>
         					<col style="width:10%"/>
              				<col style="width:45%"/>
              				<col style="width:45%"/>
         				</colgroup>
                    	<tbody id="tbody5">
  						</tbody>
                    </table>
                </div>
	                	<div class="sipac_pagebox">
	                        <div class="s_p_info">第<span id="pageNo5">1</span>页 / 共<span id="totalPage5"></span>页，共<span id="count5"></span>条数据</div> 
	                        <div class="s_p_btn"><a href="###" onclick="first('5','${ctx}/zwt/safLedQuery.action')">首页</a><a href="###"  onclick="previous('5','${ctx}/zwt/safLedQuery.action')"  >上一页</a><a href="###" onclick="next('5','${ctx}/zwt/safLedQuery.action')" >下一页</a><a href="###" onclick="last('5','${ctx}/zwt/safLedQuery.action')" >尾页</a></div>
	                	</div>
                    </div>
                    <!--bgtable end-->                


</body>
<script type="text/javascript" src="${ctx}/webResources/zwt/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/zwt/js/chosen.jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/zwt/js/main.js"></script>
</html>

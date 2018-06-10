<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>企业安全生产目标</title>
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
    var no=1;
    var ps=50;
    var ajaxData={ };
    	 $(function(){
    	 	//初始化样式
    	 	if($("#searchType").val()==1){
    	 		ajaxData={ 
		                    	pageNo :$("#currPage").val(),
		                    	pageSize:ps,
		                    	searchLike:$("#searchLike").val()
		                    	
		                    };
    	 	}else{
    	 		ajaxData={
    				pageNo : $("#currPage").val(),
		            pageSize:ps,
		            searchLike:$("#searchLike").val()
		         };
    	 	}
    	 	
    		//$("body").addClass("sipac_full");
			//$(this).addClass("hide");
			///$(".frame_Btn .f_more").removeClass("hide");
			
			getList(1,ps);
			
			$('.sipac_searchbar .s_s_text').click(function(){
		        var email = $(this).val();
		        if(email == "搜索企业名称或年度"){
		            $(this).val("");
		        }
		    }).blur(function(){
		        var email = $(this).val();
		        if(email == ""){
		            $(this).val("搜索企业名称或年度");
		        }
		    });
		});
		
		function search(){
			$("#searchType").val(1);
			ajaxData={ 
		                    	pageNo : no,
		                    	pageSize:ps,
		                    	searchLike:$("#searchLike").val()
		                    	
		                    };
			getList(1,ps);
		}
		
		function searchByNameOrCode(){
			$("#searchType").val(0);
			ajaxData={ 
		                 pageNo : no,
		                 pageSize:ps,
		                 searchLike:$("#searchLike").val()
		                 
		              };
		    getList(1,ps);
		}
		function first(){
			getList(1,ps);
		}
		function previous(){
			var curPage= parseInt(document.getElementById('pageNo').innerHTML);
			if(curPage==1){
				return false;
			}else{
				getList(curPage-1,ps);			
			}
		}
		
		function next(){
			var curPage= parseInt(document.getElementById('pageNo').innerHTML);
			var totalPage= parseInt(document.getElementById('totalPage').innerHTML);
			if(curPage==totalPage){
				return false;
			}else{
				getList(curPage+1,ps);	
			}
		}
		
		function last(){
			var totalPage= parseInt(document.getElementById('totalPage').innerHTML);
			getList(totalPage,ps);	
		}
		
		function getList(num,size){
			ajaxData.pageNo=num;
			ajaxData.pageSize=size;
			$("#currPage").val(num);
			$.ajax({
		                	url : "secProGoaQuery.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:ajaxData,
		                    error: function(){
		                    },
		                    success: function(data){
		                    	var trs="";
		                    	var index=(data.pageNo-1)*ps+1;
		                    	$.each(data.result,function(idx,item){ 
		                    		
		                    		var productGoalYearGoal=item.productGoalYearGoal;
		                    		if(productGoalYearGoal.length>10){
		                    			productGoalYearGoal=productGoalYearGoal.substring(0,10)+"...";
		                    		}
		                    		
		                    		//获取操作按钮
		                    		var button=getButton(item);
		                    		//var button="<a style='color:blue' href='#' onclick=view('"+item.id+"') >查看<b></b></a>&nbsp;";
		                    		var tr="";
		                    		if(idx%2==0){
		                    			tr="<tr class='row_1'><td>"+index+++"</td>";
		                    		}else{
		                    			tr="<tr><td>"+index+++"</td>";
		                    		}
		                    		tr+="<td>"+item.productGoalYear.substring(0,4)+"</td><td>"+productGoalYearGoal+"</td>"
		                    		//+"<td class='s_t_control'><a style='color:blue' href='#' onclick=view('"+item.id+"','"+item.enterpriseName+"')>详情</a></tr>";
		                    		+"<td class='s_t_control'>"+button+"</td></tr>";
		                    		trs+=tr;
								});
		                    	
		                    	document.getElementById('tbody').innerHTML=trs;
		                    	document.getElementById('pageNo').innerHTML=data.pageNo;
		                    	document.getElementById('totalPage').innerHTML=data.totalPage;
		                        document.getElementById('count').innerHTML=data.count;
		                    }
		                });
		}
		
        
        function getButton(rec){
        	var button1="<a class='btn_01_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
			var button2="<a class='btn_01_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_01_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
			if('${roleName}'!='0'){
				return button1;
			}else{
				return button2;
			}
        }
        
       function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_secProGoa","添加企业安全生产目标","${ctx}/zwt/secProGoaInitEdit.action?flag=add&dt="+dt.getTime(),800,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_secProGoa","修改企业安全生产目标","${ctx}/zwt/secProGoaInitEdit.action?flag=mod&secProGoa.id="+row_Id+"&dt="+dt.getTime(),800,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_secProGoa","查看企业安全生产目标","${ctx}/zwt/secProGoaView.action?secProGoa.id="+row_Id+"&dt="+dt.getTime(),800,500);
        	
        }
        function createSimpleWindow(a,b,c,d,e){
        	var dt=new Date();
        	layer.open({
		        type: 2,
		        skin: 'layui-layer-lan',
		        title: b,
		        fix: false,
		        shadeClose: true,
		        maxmin: true,
		        area: ['1000px', '500px'],
		        content: c,
		        end: function(){
		        		getList(ajaxData.pageNo,ajaxData.pageSize);
		        	}
		    });
        }
        
        
    </script>
</head>
<body>
<input type="hidden" id="searchType" value="0"/>
<input type="hidden" id="showType" value="0"/>
<input type="hidden" id="currPage" value="1"/>
<!-- Wrap all page content here -->
	<div class="sipac_wrap" >
<!-- head style -->
    	<div class="sipac_head">
        	<a href="#" class="s_h_logo"><i></i><span>①企业信息查询</span></a>
            <div class="s_h_nav">
               <ul>
                	<li>
                		<a href="${ctx }/zwt/entBaseInfoListZwt.action">企业基本信息</a>
                    </li>
                	<li>
                		<a href="${ctx }/zwt/intSitList.action">资质情况</a>
                    </li>
                    <li>
                		<a href="${ctx }/zwt/honRecList.action">荣誉表彰</a>
                    </li>
                    <li>
                		<a class="active" href="${ctx }/zwt/secProGoaList.action">安全生产目标</a>
                    </li>
                     <li>
                		<a href="${ctx }/zwt/proComList.action">安全生产承诺</a>
                    </li>
                     <li>
                		<a href="${ctx }/zwt/companyScoreList.action">标准化评分</a>
                    </li>
                     <li>
                		<a href="${ctx }/zwt/enterpriseGradeList.action">分级分类评分</a>
                    </li>
                </ul>
            </div>
             
            <div class="sipac_rtbox">
                <ul class="sipac_adminbox">
                    <li style="display:none;">
                		<a class="help" title="帮助" href="#"></a>
                	</li>
                	<li>
                		<!--<a class="manage" title="管理" href="#"></a>
                        --><div class="dropdown_nav">
                        	<em></em>
                            <ul>
                            	<li class="frame_Btn">
                                <a class="f_more hide" href="javascript:void(0);">展开菜单</a><a class="f_less"  href="javascript:void(0);">隐藏菜单</a></li>
                            	<li><a href="#">帮助</a></li>
                            </ul>
                        </div>
                	</li>
                </ul>
            	<div class="sipac_searchbar">
                	<input class="s_s_text" type="text" id="ids" value="搜索企业名称或组织机构代码"><input class="s_s_btn" type="submit"  onclick="searchByNameOrCode();"> 
                	<div class="s_s_mBox"><a href="#" class="s_s_morebtn"></a>
                	</div>
                </div>
            </div>
    	</div>

<!-- body style -->
        <div class="sipac_body" >
        	<div class="sipac_menu" >
            	<ul>
                	<li>
                    	<a class="m_icon m_i_0" href="index.html"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="index.html">返回主界面</a>
                        </div>
                    </li>
                	<li class="dropdown_active" style="text-align:center;">
                    	<a  style="font-size:30px;" href="${ctx }/zwt/entBaseInfoListZwt.action">①</a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="${ctx }/zwt/entBaseInfoListZwt.action">企业信息查询</a>
                        </div>
                    </li>
                	<li style="text-align:center;">
                    	<a style="font-size:30px;" href="${ctx }/zwt/proLegOrgList.action">②</a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="${ctx }/zwt/proLegOrgList.action">组织机构职责</a>
                        </div>
                    </li>
                	<li style="text-align:center;">
                    	<a style="font-size:30px;" href="${ctx }/zwt/secProFeeExtList.action">③</a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="${ctx }/zwt/secProFeeExtList.action">安全生产投入</a>
                        </div>
                    </li>
                    
                    <li style="text-align:center;">
                    	<a style="font-size:30px;" href="${ctx }/zwt/legStaList.action">④</a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="${ctx }/zwt/legStaList.action">法律法规与安全管理制度</a>
                        </div>
                    </li>
                    <li style="text-align:center;">
                    	<a style="font-size:30px;" href="${ctx }/zwt/maiChaTraList.action">⑤</a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="${ctx }/zwt/maiChaTraList.action">教育培训</a>
                        </div>
                    </li>
                    <li style="text-align:center;">
                    	<a style="font-size:30px;" href="${ctx }/zwt/workshopList.action">⑥</a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="${ctx }/zwt/workshopList.action">企业作业安全</a>
                        </div>
                    </li>
                    <li style="text-align:center;">
                    	<a style="font-size:30px;" href="${ctx }/zwt/ppeManagList.action">⑦</a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="${ctx }/zwt/ppeManagList.action">劳保用品配置</a>
                        </div>
                    </li>
                    <li style="text-align:center;">
                    	<a style="font-size:30px;" href="${ctx }/zwt/danPlaManList.action">⑧</a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="${ctx }/zwt/danPlaManList.action">隐患排查和治理</a>
                        </div>
                    </li>
                    <li style="text-align:center;">
                    	<a style="font-size:30px;" href="${ctx }/zwt/comDanIdeList.action">⑨</a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="${ctx }/zwt/comDanIdeList.action">企业重点危险源监控</a>
                        </div>
                    </li>
                    <li style="text-align:center;">
                    	<a style="font-size:30px;" href="${ctx }/zwt/occHeaManList.action">⑩</a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="${ctx }/zwt/occHeaManList.action">职业健康</a>
                        </div>
                    </li>
                    <li style="text-align:center;">
                    	<a style="font-size:30px;" href="${ctx }/zwt/maiPerRepList.action">⑪</a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="${ctx }/zwt/maiPerRepList.action">危化企业信息1</a>
                        </div>
                    </li>
                    <li style="text-align:center;">
                    	<a style="font-size:30px;" href="${ctx }/zwt/tecProList.action">⑫</a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="${ctx }/zwt/tecProList.action">危化企业信息2</a>
                        </div>
                    </li>
                    <li style="text-align:center;">
                    	<a style="font-size:30px;" href="${ctx }/zwt/dusWorManList.action">⑬</a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="${ctx }/zwt/dusWorManList.action">涉爆粉尘企业信息</a>
                        </div>
                    </li>
                    <li style="text-align:center;">
                    	<a style="font-size:30px;" href="${ctx }/zwt/emeAgeList.action">⑭</a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="${ctx }/zwt/emeAgeList.action">应急救援</a>
                        </div>
                    </li>
                    <li style="text-align:center;">
                    	<a style="font-size:30px;" href="${ctx }/zwt/accRepInvHanList.action">⑮</a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="${ctx }/zwt/accRepInvHanList.action">事故报告调查和处理</a>
                        </div>
                    </li>
                </ul>
                
            </div>
            <div class="sipac_container" id="tableDiv">
            	<div class="sipac_searchBox sipac_form" id="searchForm">
                	<!--<table>
                    	<tr>
                        	<th>企业名称</th>
                            <td width="20%"><input class="s_input" id="enterpriseName" type="text" value=""></td>
                        	<th>工商注册号</th>
                            <td width="20%"><input class="s_input" id="registrationNumber" type="text" value=""></td>
                            <th >企业性质</th>
							<td width="20%"><cus:SelectOneTag property="entBaseInfo.enterpriseNature" defaultText='请选择' codeName="企业性质" class="s_select" /></td>
                        </tr>
                        <tr>
                        	<th>组织机构代码</th>
                            <td width="20%"><input class="s_input" id="enterpriseCode" type="text" value=""></td>
                        	<th>法人姓名</th>
                            <td width="20%"><input class="s_input" id="enterpriseLegalName" type="text" value=""></td>
                        	
                            <th>企业属地</th>
							<td width="20%"><cus:SelectOneTag property="entBaseInfo.enterprisePossession" defaultText='请选择' codeName="企业属地" class="s_select" /></td>
                        </tr>
                        <tr>
                        	<th>所属网格</th>
                            <td width="20%"><input class="s_input" id="gridName" type="text" value=""></td>
                        </tr>
                        
                    </table>
                    --><div class="s_s_bar"><button class="sipac_btn s_b_submit" onclick="search();">搜索</button></div>
                    <div class="s_s_close"><a href="#"></a></div>
                </div>
                <!--<div class="sipac_crumbs"><a class="s_c_back" href="#" title="返回">&lt;</a><div class="s_c_cont">已选条件: 普通应用</div></div>-->
            	<div class="sipac_tableBox">
            		<c:if test="${roleName=='0'}">
	                    <div class="sipac_tableBar">
	                        <div class="sipac_table_menu"><a href="javascript:addNew();">新建</a><!--<em></em><a href="#">修改</a><em></em><a href="#">批量删除</a><em></em><a href="#">通过</a><em></em><a href="#">禁用</a><em></em><a href="#">启用</a> --><em></em><a href="javascript:rel();">刷新</a>
	                       </div>
	                    </div>
	                </c:if>
                	<table>
                    	<thead>
                        <tr>
                        	<!--<th style="width:40px;"><span><input type="checkbox"></span></th>
                        	--><th><span>序号</span></th>
                        	<th><span>年度  </span></th>
                        	<th><span>年度目标</span></th>
                        	<th ><span>操作项</span></th>
                        </tr>
  						</thead>
                    	<tbody id="tbody">
  						</tbody>
                    </table>
                	<div class="sipac_pagebox">
                        <div class="s_p_info">第<span id="pageNo">1</span>页 / 共<span id="totalPage"></span>页，共<span id="count"></span>条数据</div> 
                        <div class="s_p_btn"><a href="#" onclick="first()">首页</a><a href="#"  onclick="previous()"  >上一页</a><a href="#" onclick="next()" >下一页</a><a href="#" onclick="last()" >尾页</a></div>
                	</div>
            	</div>
            </div>
        </div>
    </div> 
    

</body>
<script type="text/javascript" src="${ctx}/webResources/zwt/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/zwt/js/chosen.jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/zwt/js/main.js"></script>
</html>

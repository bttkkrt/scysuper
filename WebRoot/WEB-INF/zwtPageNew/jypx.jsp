<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>教育培训</title>
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
    var startTime=1;
    var ps=50;
    var ajaxData={ };
    	 $(function(){
    	 	//初始化样式
    	 	if($("#searchType").val()==1){
    	 		ajaxData={ 
		                    	pageNo :$("#currPage").val(),
		                    	pageSize:ps,
		                    	searchLike:"${searchLike}"
		                    	
		                    };
    	 	}else{
    	 		ajaxData={
    				pageNo : $("#currPage").val(),
		            pageSize:ps,
		            searchLike:"${searchLike}"
		         };
    	 	}
    	 	
    		//$("body").addClass("sipac_full");
			//$(this).addClass("hide");
			///$(".frame_Btn .f_more").removeClass("hide");
			
			getList(1,ps);
			
			$('.sipac_searchbar .s_s_text').click(function(){
		        var email = $(this).val();
		        if(email == "搜索企业名称、证书名称或编号"){
		            $(this).val("");
		        }
		    }).blur(function(){
		        var email = $(this).val();
		        if(email == ""){
		            $(this).val("搜索企业名称、证书名称或编号");
		        }
		    });
		});
		
		function search(){
			$("#searchType").val(1);
			ajaxData={ 
		                    	pageNo : startTime,
		                    	pageSize:ps,
		                    	searchLike:"${searchLike}"
		                    	
		                    };
			getList(1,ps);
		}
		
		function searchByNameOrCode(){
			$("#searchType").val(0);
			ajaxData={ 
		                 pageNo : startTime,
		                 pageSize:ps,
		                 searchLike:"${searchLike}"
		                 
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
		 function getSpanByName(name)
        {
        	return "<span title='" + name + "' style='cursor:pointer'>" + name + "</span>";
        }
		function getList(num,size){ 
			ajaxData.pageNo=num;
			ajaxData.pageSize=size;
			$("#currPage").val(num);
			$.ajax({
		                	url : "zwtJypxList.action",
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
		                    		var name=getName(item);
		                    		
		                    		//获取操作按钮
		                    		var button=getButton(item); 
		                    		var tr="";
		                    		if(idx%2==0){
		                    			tr="<tr class='row_1'><td>"+index+++"</td>";
		                    		}else{
		                    			tr="<tr><td>"+index+++"</td>";
		                    		}
		                    		tr+="<td>"+name+"</td><td>"
		                    		+getSpanByName(item.lx)+"</td><td>"+getSpanByName(item.startTime)+"</td><td>"
		                    		+getSpanByName(item.endTime)+"</td>"
		                    		//+"<td class='s_t_control'><a class='s_t_Btn stb_3' href='javascript:;' onclick=view('"+item.id+"','"+item.enterpriseName+"')>详情</a></tr>";
		                    		+"</tr>";
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
        	if(rec.lx=='企业主要负责人培训'){
				return "<a class='s_t_Btn stb_3' href='javascript:;' onclick=viewQyzyfzr('"+rec.id+"') >查看<b></b></a>";
			}else if(rec.lx=='安全生产分管负责人培训'){
				return "<a class='s_t_Btn stb_3' href='javascript:;' onclick=viewAqscfgfzr('"+rec.id+"') >查看<b></b></a>";
			}else if(rec.lx=='安全生产管理经理培训'){
				return "<a class='s_t_Btn stb_3' href='javascript:;' onclick=viewAqscgljl('"+rec.id+"') >查看<b></b></a>";
			}else if(rec.lx=='安全生产总监培训'){
				return "<a class='s_t_Btn stb_3' href='javascript:;' onclick=viewAqsczj('"+rec.id+"') >查看<b></b></a>";
			}else if(rec.lx=='安全生产负责人员培训'){
				return "<a class='s_t_Btn stb_3' href='javascript:;' onclick=viewAqscfzry('"+rec.id+"') >查看<b></b></a>";
			}else if(rec.lx=='企业特种人员培训'){
				return "<a class='s_t_Btn stb_3' href='javascript:;' onclick=viewQytzry('"+rec.id+"') >查看<b></b></a>";
			}else if(rec.lx=='班组长培训'){
				return "<a class='s_t_Btn stb_3' href='javascript:;' onclick=viewBzz('"+rec.id+"') >查看<b></b></a>";
			}else if(rec.lx=='新员工上岗培训'){
				return "<a class='s_t_Btn stb_3' href='javascript:;' onclick=viewXygsg('"+rec.id+"') >查看<b></b></a>";
			}else if(rec.lx=='企业内部全员培训'){
				return "<a class='s_t_Btn stb_3' href='javascript:;' onclick=viewQynbqy('"+rec.id+"') >查看<b></b></a>";
			}else {}
        }
        
        function getName(rec){
        	if(rec.lx=='企业主要负责人培训'){
				return "<a href='javascript:;' style='color:#27a9e3'  onclick=viewQyzyfzr('"+rec.id+"') >"+getSpanByName(rec.name)+"<b></b></a>";
			}else if(rec.lx=='安全生产分管负责人培训'){
				return "<a href='javascript:;' style='color:#27a9e3'  onclick=viewAqscfgfzr('"+rec.id+"') >"+getSpanByName(rec.name)+"<b></b></a>";
			}else if(rec.lx=='安全生产管理经理培训'){
				return "<a href='javascript:;' style='color:#27a9e3'  onclick=viewAqscgljl('"+rec.id+"') >"+getSpanByName(rec.name)+"<b></b></a>";
			}else if(rec.lx=='安全生产总监培训'){
				return "<a href='javascript:;' style='color:#27a9e3'  onclick=viewAqsczj('"+rec.id+"') >"+getSpanByName(rec.name)+"<b></b></a>";
			}else if(rec.lx=='安全生产负责人员培训'){
				return "<a href='javascript:;' style='color:#27a9e3'  onclick=viewAqscfzry('"+rec.id+"') >"+getSpanByName(rec.name)+"<b></b></a>";
			}else if(rec.lx=='企业特种人员培训'){
				return "<a href='javascript:;' style='color:#27a9e3'  onclick=viewQytzry('"+rec.id+"') >"+getSpanByName(rec.name)+"<b></b></a>";
			}else if(rec.lx=='班组长培训'){
				return "<a href='javascript:;' style='color:#27a9e3'  onclick=viewBzz('"+rec.id+"') >"+getSpanByName(rec.name)+"<b></b></a>";
			}else if(rec.lx=='新员工上岗培训'){
				return "<a href='javascript:;' style='color:#27a9e3'  onclick=viewXygsg('"+rec.id+"') >"+getSpanByName(rec.name)+"<b></b></a>";
			}else if(rec.lx=='企业内部全员培训'){
				return "<a href='javascript:;' style='color:#27a9e3'  onclick=viewQynbqy('"+rec.id+"') >"+getSpanByName(rec.name)+"<b></b></a>";
			}else {}
        }
        
        
        
        
        function viewQyzyfzr(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_maiChaTra","查看企业主要负责人培训","${ctx}/zwt/maiChaTraView.action?maiChaTra.id="+row_Id+"&dt="+dt.getTime(),800,500);
        }
        function viewAqscfgfzr(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_chargeTraining","查看安全生产分管负责人培训","${ctx}/zwt/chargeTrainingView.action?chargeTraining.id="+row_Id+"&dt="+dt.getTime(),800,500);
        }
        function viewAqscgljl(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_managerTraining","查看安全生产管理经理培训","${ctx}/zwt/managerTrainingView.action?managerTraining.id="+row_Id+"&dt="+dt.getTime(),800,500);
        }
        function viewAqsczj(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_majordomoTraining","查看安全生产总监培训","${ctx}/zwt/majordomoTrainingView.action?majordomoTraining.id="+row_Id+"&dt="+dt.getTime(),800,500);
        }
        function viewAqscfzry(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_secProChaTra","查看安全生产负责人员培训","${ctx}/zwt/secProChaTraView.action?secProChaTra.id="+row_Id+"&dt="+dt.getTime(),800,500);
        }
        function viewQytzry(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_speJobTra","查看企业特种人员培训","${ctx}/zwt/speJobTraView.action?speJobTra.id="+row_Id+"&dt="+dt.getTime(),800,500);
        }
        function viewBzz(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_teaLeaTra","查看班组长培训","${ctx}/zwt/teaLeaTraView.action?teaLeaTra.id="+row_Id+"&dt="+dt.getTime(),800,450);
        }
        function viewXygsg(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_newWorTra","查看新员工上岗培训","${ctx}/zwt/newWorTraView.action?newWorTra.id="+row_Id+"&dt="+dt.getTime(),800,500);
        }
        function viewQynbqy(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_allMenTra","查看企业内部全员培训","${ctx}/zwt/allMenTraView.action?allMenTra.id="+row_Id+"&dt="+dt.getTime(),800,500);
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
<input type="hidden" id="searchType" value="0"/>
<input type="hidden" id="showType" value="0"/>
<input type="hidden" id="currPage" value="1"/>
<!-- Wrap all page content here -->
	<div class="sipac_wrap" >
<!-- head style -->

<!-- body style -->
        <div class="sipac_body" >
            <div class="sipac_container" id="tableDiv" style="margin: 0 0 0 0px;">
                <!--<div class="sipac_crumbs"><a class="s_c_back" href="#" title="返回">&lt;</a><div class="s_c_cont">已选条件: 普通应用</div></div>-->
            	<div class="sipac_tableBox">
            		<c:if test="${roleName=='0'}">
	                    <div class="sipac_tableBar">
	                        <div class="sipac_table_menu"><a href="javascript:addNew();">新建</a><!--<em></em><a href="#">修改</a><em></em><a href="#">批量删除</a><em></em><a href="#">通过</a><em></em><a href="#">禁用</a><em></em><a href="#">启用</a> --><em></em><a href="javascript:rel();">刷新</a>
	                       </div>
	                    </div>
	                </c:if>
                	<div class="table-head">
                	<table style="width:100%;">
                		<colgroup>
             				<col style="width:10%"/>
              				<col style="width:25%"/>
              				<col style="width:25%"/>
              				<col style="width:25%"/>
              				<col style="width:15%"/>
          				</colgroup>
                    	<thead>
                       <tr>
                        	<!--<th style="width:40px;"><span><input type="checkbox"></span></th>
                        	--><th><span>序号</span></th>
                        	<th><span>姓名（名称）  </span></th>
                        	<th><span>培训类型  </span></th>
                        	<th><span>开始时间 </span></th>
                        	<th><span>结束时间</span></th>
                        </tr>
  						</thead>
                    	</table>
     			</div>
     			<div class="table-body">
     				<table style="width:100%;">
         				<colgroup>
         					<col style="width:10%"/>
              				<col style="width:25%"/>
              				<col style="width:25%"/>
              				<col style="width:25%"/>
              				<col style="width:15%"/>
         				</colgroup>
                    	<tbody id="tbody">
  						</tbody>
                    </table>
                </div>
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

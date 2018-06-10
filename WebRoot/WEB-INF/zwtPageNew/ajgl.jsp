<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>案件管理</title>
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
    var flag="${flag}";
    var loginUserId = "${loginUserId}";
    var no=1;
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
		                    	pageNo : no,
		                    	pageSize:ps,
		                    	searchLike:"${searchLike}"
		                    	
		                    };
			getList(1,ps);
		}
		
		function searchByNameOrCode(){
			$("#searchType").val(0);
			ajaxData={ 
		                 pageNo : no,
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
		                	url : "caseInfoQuery.action",
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
		                    	
		                    	  
									
									var caseStatus=getStatus(item.caseStatus,item);
									
									//获取操作按钮
		                    		var button=getButton(item); 
		                    		var tr="";
		                    		if(idx%2==0){
		                    			tr="<tr class='row_1'>";
		                    		}else{
		                    			tr="<tr>";
		                    		}
		                    		
		                    		if((item.caseStatus == 7 && item.undertakerId == loginUserId)
						    		|| (flag.indexOf("A02") != -1 && item.caseStatus == 1)
						    		|| (flag.indexOf("A09") != -1 && item.caseStatus == 0)
						    		|| (flag.indexOf("A29") != -1))
		                    		{
		                    			tr+="<td><input type='checkbox' name='xxx' value='"+item.id+"'></td>";
		                    		}
		                    		else if(flag.indexOf("A02") != -1 || flag.indexOf("A09") != -1 || flag.indexOf("A10") != -1 || flag.indexOf("A30") != -1 || flag.indexOf("A29") != -1)
		                    		{
		                    			tr+="<td>&nbsp;</td>";
		                    		}
		                    		tr+="<td>"+index+++"</td>";
		                    		tr+="<td><a onclick=view('"+item.id+"') href='javascript:;' style='color:#27a9e3' >"
		                    		+getSpanByName(item.caseName)+"</a></td><td>"+getSpanByName(item.companyName)+"</td><td>"
		                    		+getSpanByName(item.caseTime.substring(0,10))+"</td><td>"
		                    		+getSpanByName(item.undertakerName)+"</td><td>"
		                    		+getSpanByName(caseStatus)+"</td>"
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
		
		function getStatus(value,rec){
			if(value == '0' || value == '1' || value == '7' || value == '8')
			{
				return "待审核";
			}
			else if(value == '2')
			{
				return "执行中";
			}
			else if(value == '3')
			{
				return "已结案";
			}
			else if(value == '4')
			{
				return "已归档";
			}
			else if(value == '5')
			{
				return "审核不通过";
			}
		}
		
        
        function getButton(rec){
			var aa = "<a class='s_t_Btn stb_1' href='javascript:;' onclick=wsList('"+rec.id+"','"+rec.caseName+"')>文书<b></b></a>&nbsp;";
			if(flag.indexOf("A10") != -1 && rec.caseStatus == '2')
			{
				aa += "<a class='s_t_Btn stb_1' href='javascript:;' onclick=editWs('"+rec.id+"','"+rec.caseName+"')>处理<b></b></a>&nbsp;";
			}
			if(flag.indexOf("A10") != -1 && (rec.caseStatus == '2' || rec.caseStatus == '3'))
			{
				aa += "<a class='s_t_Btn stb_1' href='javascript:;' onclick=uploadCl('"+rec.id+"','"+rec.caseName+"')>材料<b></b></a>&nbsp;<a class='s_t_Btn stb_1' href='javascript:;' onclick=uploadZj('"+rec.id+"','"+rec.caseName+"')>证据<b></b></a>&nbsp;";
			}
			if(rec.caseStatus == '5' && rec.createUserID == loginUserId)
			{
				aa += "<a class='s_t_Btn stb_1' href='javascript:;' onclick=edit('"+rec.id+"','0')>修改<b></b></a>&nbsp;";
			}
			if((rec.caseStatus == '0' && flag.indexOf("A09") != -1) || (rec.caseStatus == '1' && flag.indexOf("A02") != -1) || (rec.caseStatus == '7' && rec.undertakerId == loginUserId) || (rec.caseStatus == '8' && flag.indexOf("A30") != -1))
			{
				aa += "<a class='s_t_Btn stb_1' href='javascript:;' onclick=shenhe('"+rec.id+"')>审核<b></b></a>&nbsp;";
			}
			if(rec.caseStatus == '3' && rec.createUserID == loginUserId)
			{
				aa += "<a class='s_t_Btn stb_1' href='javascript:;' onclick=guidang('"+rec.id+"')>归档<b></b></a>&nbsp;";
			}
			if(flag.indexOf("A01") != -1 || flag.indexOf("A29") != -1)
			{
				aa += "<a class='s_t_Btn stb_1' href='javascript:;' onclick=edit('"+rec.id+"','1')>修改<b></b></a>&nbsp;";
			}
			return aa;
        }
        
       function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_caseInfo","添加案件","${ctx}/zwt/caseInfoInitEdit.action?flag=add&dt="+dt.getTime(),900,550);
        }
        function edit(row_Id,type){
        	var dt=new Date();
            createSimpleWindow("win_caseInfo","修改案件","${ctx}/zwt/caseInfoInitEdit.action?flag=mod&caseInfo.id="+row_Id+"&dt="+dt.getTime()+"&type="+type,900,550);
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_caseInfo","查看案件","${ctx}/zwt/caseInfoView.action?caseInfo.id="+row_Id+"&dt="+dt.getTime(),900,550);
        }
        function editWs(row_Id,name)
        {
        	var dt=new Date();
            createSimpleWindow("win_instrumentsInfo","处理案件-"+name,"${ctx}/zwt/queryWsType.action?instrumentsInfo.caseId=" + row_Id,950,550);
        }
        function shenhe(row_Id)
        {
        	var dt=new Date();
        	createSimpleWindow("win_caseInfo","审核案件-立案审批","${ctx}/zwt/caseInfoShenhe.action?caseInfo.id="+row_Id+"&dt="+dt.getTime(),900,550);
        }
        
        function guidang(row_Id)
        {
        	var dt=new Date();
        	createSimpleWindow("win_caseInfo","案件归档","${ctx}/zwt/caseInfoGuidang.action?caseInfo.id="+row_Id+"&dt="+dt.getTime(),900,550);
        }
        
        function uploadCl(row_Id,caseName)
        {
        	var id  = "win_caseCl";
        	var text = caseName + "-案件材料上传";
        	var url = "${ctx}/zwt/initUploadFile.action?caseInfo.id="+row_Id;
        	window.open(url);
        	//createSimpleWindow(id,text,url,900,550);
        }
        
        function uploadZj(row_Id,caseName)
        {
        	var id  = "win_caseZj";
        	var text = caseName + "-证据上传";
        	var url = "${ctx}/zwt/initUploadZjlb.action?caseInfo.id="+row_Id;
        	window.open(url);
        	//window.parent.addTab(id,text,url);
        }
        
        function wsList(row_Id,caseName)
        {
        	var ms=$(".s_sb_nav a");
        	for(var i=0;i<ms.length;i++){
        		if(ms.eq(i).html()=='案件管理'){
        			ms.eq(i).removeClass('active');
        		}
        		if(ms.eq(i).html()=='文书管理'){
        			ms.eq(i).addClass('active');
        		}
        	}
        	$("#mainContent").load("${ctx}/zwt/wsgl.action?instrumentsInfo.caseId="+row_Id);
        	
        }
        
        
        function del(){
       	 	var rows = document.getElementsByName("xxx");
        	var ids = "";
        	var len = rows.length;
			for(var i=0;i<len;i++){
				if(rows[i].checked){
					ids += rows[i].value+"|";
				}
			}
			if(ids == ""){
			   	alert('至少选择一项删除！');
			}else{
			    var a= confirm("确定要删除吗?");
			   		if(a==true){
		                $.ajax({
		                	url : "caseInfoDel.action",
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
		                        	search_caseInfo();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			}
        }
        
        function checkPass(){
       	 	var rows = document.getElementsByName("xxx");
        	var ids = "";
        	var len = rows.length;
			for(var i=0;i<len;i++){
				if(rows[i].checked){
					ids += rows[i].value+"|";
				}
			}
			if(ids == ""){
			    alert('至少选择一项审核！');
			}else{
				var dt=new Date();
            	createSimpleWindow("win_caseInfo","审核案件","${ctx}/zwt/caseInfoShenheAll.action?flag=mod&ids="+ids+"&dt="+dt.getTime(),600,300);
			}
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
        
         function selectAll(){
        	var coll = document.getElementsByName('xxx');
        	var collid = document.getElementById("all");
  			if (collid.checked){
    			for(var i = 0; i < coll.length; i++)
       				coll[i].checked = true;
  			}else{
     			for(var i = 0; i < coll.length; i++){
       				coll[i].checked = false;
  				}
  			}
  
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
            	
            		 <s:if test='flag.indexOf("A02") != -1 || flag.indexOf("A09") != -1 || flag.indexOf("A10") != -1 || flag.indexOf("A30") != -1 || flag.indexOf("A29") != -1'>
	                    <div class="sipac_tableBar">
	                        <div class="sipac_table_menu">
	                        <s:if test='flag.indexOf("A10") != -1'>
	                        <a href="javascript:addNew();">新建</a><em></em>
	                        </s:if>
	                        <s:if test='flag.indexOf("A29") != -1'>
							<a href="javascript:del();">批量删除</a><em></em>
							 </s:if>
							 <s:if test='flag.indexOf("A02") != -1 || flag.indexOf("A09") != -1 || flag.indexOf("A10") != -1 || flag.indexOf("A30") != -1'>
							<a href="javascript:checkPass();">审核</a><em></em>
							</s:if>
	                       </div>
	                    </div>
	                 </s:if>
	                 
	                    <div class="table-head">
                	<table style="width:100%;">
                		<colgroup>
                			<s:if test='flag.indexOf("A02") != -1 || flag.indexOf("A09") != -1 || flag.indexOf("A10") != -1 || flag.indexOf("A30") != -1 || flag.indexOf("A29") != -1'>
                        	<col style="width:5%"/>
                        	<col style="width:5%"/>
                        	</s:if>
                        	<s:else>
                        	<col style="width:10%"/>
                        	</s:else>
              				<col style="width:20%"/>
              				<col style="width:20%"/>
              				<col style="width:10%"/>
              				<col style="width:10%"/>
              				<col style="width:10%"/>
              				<col style="width:20%"/>
         				</colgroup>
                    	<thead>
                        <tr>
                        	<s:if test='flag.indexOf("A02") != -1 || flag.indexOf("A09") != -1 || flag.indexOf("A10") != -1 || flag.indexOf("A30") != -1 || flag.indexOf("A29") != -1'>
                        	<th><span><input type='checkbox' id="all" onclick="selectAll()"/></span></th>
                        	</s:if>
                        	<th><span>序号</span></th>
                        	<th><span>案件名称    </span></th>
                        	<th><span>企业名称 </span></th>
                        	<th><span>案件时间  </span></th>
                        	<th><span>承办人   </span></th>
                        	<th><span>案件状态   </span></th>
                        	<th ><span>操作项</span></th>
                        </tr>
  						</thead>
  					</table>
     			</div>
     			<div class="table-body">
     				<table style="width:100%;">
         				<colgroup>
             				<s:if test='flag.indexOf("A02") != -1 || flag.indexOf("A09") != -1 || flag.indexOf("A10") != -1 || flag.indexOf("A30") != -1 || flag.indexOf("A29") != -1'>
                        	<col style="width:5%"/>
                        	<col style="width:5%"/>
                        	</s:if>
             				<s:else>
                        	<col style="width:10%"/>
                        	</s:else>
              				<col style="width:20%"/>
              				<col style="width:20%"/>
              				<col style="width:10%"/>
              				<col style="width:10%"/>
              				<col style="width:10%"/>
              				<col style="width:20%"/>
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

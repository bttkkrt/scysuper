<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>基础信息查询</title>
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

.s_c_back {
    border: 1px solid #444444;
    font: 18px/18px "SimSun";
    overflow: hidden;
    text-align: center;
    margin: 13px 0 0;
    border-radius: 50%;
    width: 18px;
    padding: 3px 3px 0 0;
    float: left;
    margin-right: -4px;
}
</style>
    <!--[if lt IE 8]>
    <![endif]-->
    <script type="text/javascript">
    var no=1;
    var ps=50;
    var ajaxData={ };
    	 $(function(){
    	 	//用户登录，保存session
    	 	$.ajax({
		                	url : "${ctx}/ajaxLoginMd5.action",
		                	type: 'post',
		                	dataType: 'json',
		                    data: {loginId:"${u.loginId}",password:"${u.password}"},
		                    async : false,
		                    error: function(){
		                    },
		                    success: function(data){
		                    }
		                });
		                
    	 	//初始化样式
    	 	if($("#searchType").val()==1){
    	 		ajaxData={ 
		                    	pageNo :$("#currPage").val(),
		                    	pageSize:ps,
		                    	"entBaseInfo.enterpriseName":$("#enterpriseName").val(),
		                    	"entBaseInfo.registrationNumber":$("#registrationNumber").val(),
		                    	"entBaseInfo.enterpriseNature":$("#enterpriseNature").val(),
		                    	"entBaseInfo.enterpriseCode":$("#enterpriseCode").val(),
		                    	"entBaseInfo.enterpriseLegalName":$("#enterpriseLegalName").val(),
		                    	"entBaseInfo.enterprisePossession":$("#enterprisePossession").val(),
		                    	"entBaseInfo.basePass": $("#basePass").val(),
		                    	"entBaseInfo.gridName":$("#gridName").val()
		                    	
		                    };
    	 	}else{
    	 		ajaxData={
    				pageNo : $("#currPage").val(),
		            pageSize:ps,
		            ids:$("#ids").val()
		         };
    	 	}
    	 	
    		//$("body").addClass("sipac_full");
			//$(this).addClass("hide");
			///$(".frame_Btn .f_more").removeClass("hide");
			
			getList(1,ps);
			
			$('.sipac_searchbar .s_s_text').click(function(){
		        var email = $(this).val();
		        if(email == "搜索企业名称或组织机构代码"){
		            $(this).val("");
		        }
		    }).blur(function(){
		        var email = $(this).val();
		        if(email == ""){
		            $(this).val("搜索企业名称或组织机构代码");
		        }
		    });
		});
		
		function search(){
			$("#searchType").val(1);
			ajaxData={ 
		                    	pageNo : no,
		                    	pageSize:ps,
		                    	"entBaseInfo.enterpriseName":$("#enterpriseName").val(),
		                    	"entBaseInfo.registrationNumber":$("#registrationNumber").val(),
		                    	"entBaseInfo.enterpriseNature":$("#enterpriseNature").val(),
		                    	"entBaseInfo.enterpriseCode":$("#enterpriseCode").val(),
		                    	"entBaseInfo.enterpriseLegalName":$("#enterpriseLegalName").val(),
		                    	"entBaseInfo.enterprisePossession":$("#enterprisePossession").val(),
		                    	"entBaseInfo.basePass": $("#basePass").val(),
		                    	"entBaseInfo.gridName":$("#gridName").val()
		                    	
		                    };
			getList(1,ps);
		}
		
		function searchByNameOrCode(){
			$("#searchType").val(0);
			ajaxData={ 
		                 pageNo : no,
		                 pageSize:ps,
		                 ids:$("#ids").val()
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
		                	url : "zwtList.action",
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
		                    		//获取操作按钮
		                    		var button=getButton(item); 
		                    		var tr="";
		                    		if(idx%2==0){
		                    			tr="<tr class='row_1'><td>"+index+++"</td>";
		                    		}else{
		                    			tr="<tr><td>"+index+++"</td>";
		                    		}
		                    		var state = item.basePass;
		                    		if(state == '1')
									{
										state= "审核通过";
									}
									else if(state == '2')
									{
										state= "审核不通过";
									}
									else
									{
										state= "待审核";
									}
		                    		tr+="<td><a onclick=view('"+item.id+"') href='javascript:;' style='color:#27a9e3' >"+getSpanByName(item.enterpriseName)+"</a></td><td>"+getSpanByName(item.enterpriseCode)+"</td><td>"
		                    		+getSpanByName(item.enterpriseLegalName)+"</td><td>"+getSpanByName(item.enterpriseNature)+"</td><td>"
		                    		+getSpanByName(item.gridName)+"</td><td>"
		                    		+getSpanByName(state)+"</td>"
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
			var button1="<a class='s_t_Btn stb_1' href='javascript:;' onclick=edit('"+rec.id+"')>修改<b></b></a>";
			var button2="<a class='s_t_Btn stb_1' href='javascript:;' onclick=check('"+rec.id+"')>审核<b></b></a>";
			var button3="";
			if("${deptCode}" == rec.gridManageteamCode || "${loginId}" == '1') 
			{
				button3 += button1;
				if(rec.basePass=="0")
				{
					button3 += button2;
				}
			}
			return button3;
        }
        
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_entBaseInfo","修改企业基本信息","${ctx}/zwt/entBaseInfoInitEdits.action?entBaseInfo.id="+row_Id+"&dt="+dt.getTime(),800,550);
        	
        }
        function check(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_entBaseInfo","审核企业基本信息","${ctx}/zwt/entBaseInfoCheck.action?flag=mod&entBaseInfo.id="+row_Id+"&dt="+dt.getTime(),800,550);
        	
        }
		
		 function view(id){
		 	window.location.href="${ctx}/zwt/chajian1.action?entBaseInfo.id="+id;
    
        }
        
        function getSpanByName(name)
        {
        	return "<span title='" + name + "' style='cursor:pointer'>" + name + "</span>";
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
    	<div class="sipac_head">
    		<div style="position:absolute;left:15px">
        	<a href="###" class="s_h_logo"> <span>企业管理</span></a>
            </div>
            <div class="sipac_rtbox"  style="right:15px">
                <ul class="sipac_adminbox">
                	<li>
                		<div class="dropdown_nav">
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
                	<input class="s_s_text" type="text" id="ids" value="搜索企业名称或组织机构代码"><input class="s_s_btn" type="submit" onclick="searchByNameOrCode();"> 
                	<div class="s_s_mBox"><a href="#" class="s_s_morebtn"></a>
                	</div>
                </div>
            </div>
    	</div>

<!-- body style -->
        <div class="sipac_body" >
        	<div class="sipac_menu" style="display:none">
                
            </div>
            
            <div class="sipac_container" id="tableDiv" style="margin: 0 0 0 0px;">
            	<div class="sipac_searchBox sipac_form" id="searchForm" style="display:none">
                	<table>
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
                        	<th>企业状态</th>
							<td width="20%"><s:select id="basePass" name="entBaseInfo.basePass" list='#{"":"请选择","0":"待审核","1":"审核通过","2":"审核不通过"}' theme="simple" class="s_select"></s:select></td>
                        </tr>
                        
                    </table>
                    <div class="s_s_bar"><button class="sipac_btn s_b_submit" onclick="search();">搜索</button></div>
                    <div class="s_s_close"><a href="#"></a></div>
                </div>
                <!--<div class="sipac_crumbs"><a class="s_c_back" href="#" title="返回">&lt;</a><div class="s_c_cont">已选条件: 普通应用</div></div>-->
            	<div class="sipac_tableBox" style="left:5px;">
                   <!-- <div class="sipac_tableBar">
                        <div class="sipac_table_menu"><a href="#">新建</a><em></em><a href="#">修改</a><em></em><a href="#">批量删除</a><em></em><a href="#">通过</a><em></em><a href="#">禁用</a><em></em><a href="#">启用</a><em></em><a href="#">刷新</a>
                        </div>
                    </div>-->
                    <div class="table-head">
                	<table style="width:100%;">
                		<colgroup>
             				<col style="width:5%"/>
              				<col style="width:25%"/>
              				<col style="width:15%"/>
              				<col style="width:15%"/>
              				<col style="width:10%"/>
              				<col style="width:10%"/>
              				<col style="width:10%"/>
              				<col style="width:10%"/>
          				</colgroup>
                    	<thead>
                        <tr>
                        	<th><span>序号</span></th>
                        	<th><span>企业名称</span></th>
                        	<th><span>组织机构代码 </span></th>
                        	<th><span>法人姓名 </span></th>
                        	<th><span>企业性质  </span></th>
                        	<th><span>所属网格 </span></th>
                        	<th><span>企业状态 </span></th>
                        	<th><span>操作项</span></th>
                        </tr>
  						</thead>
  					</table>
     			</div>
     			<div class="table-body" style="height:400px;">
     				<table style="width:100%;">
         				<colgroup>
         					<col style="width:5%"/>
              				<col style="width:25%"/>
              				<col style="width:15%"/>
              				<col style="width:15%"/>
              				<col style="width:10%"/>
              				<col style="width:10%"/>
              				<col style="width:10%"/>
              				<col style="width:10%"/>
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

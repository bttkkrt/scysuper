<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>企业安全信用查询</title>
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
		        if(email == "搜索企业名称、处罚名称或文书号"){
		            $(this).val("");
		        }
		    }).blur(function(){
		        var email = $(this).val();
		        if(email == ""){
		            $(this).val("搜索企业名称、处罚名称或文书号");
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
		                	url : "ZwtdishonestyQuery.action",
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
		                    		tr+="<td>"+item.areaId+"</td><td>"+item.companyName+"</td><td>"
		                    		+item.punishName+"</td><td>"+item.symbolDecision+"</td><td>"
		                    		+item.punishedSpecies+"</td><td>"+item.creditRating+"</td><td>"
		                    		+item.checkStatus+"</td>"
		                    		//+<td class='s_t_control'><a style='color:blue' href='#' onclick=view('"+item.id+"','"+item.enterpriseName+"')>详情</a></tr>";
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
		
		 function view(id,name){
		 	layer.open({
		        type: 2,
		        skin: 'layui-layer-lan',
		        title: name+'详细信息',
		        fix: false,
		        shadeClose: true,
		        maxmin: true,
		        area: ['1000px', '500px'],
		        content: "${ctx}/zwt/entBaseInfoView.action?entBaseInfo.id="+id
		    });
    
        }
        
        function getButton(rec){
        	var button1="<a style='color:blue' href='#' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a style='color:blue' href='#' onclick=edit('"+rec.id+"')>修改<b></b></a>";
			var button2="<a style='color:blue' href='#' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a style='color:blue' href='#' onclick=check1('"+rec.id+"')>审核<b></b></a>";
			var button3="<a style='color:blue' href='#' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a style='color:blue' href='#' onclick=check2('"+rec.id+"')>审批<b></b></a>";
			var button4="<a style='color:blue' href='#' onclick=view('"+rec.id+"') >查看<b></b></a>";
			if(rec.checkStatus=='待审核'){
				if("false"=="${check1}"&&"false"=="${check2}"){//安监中队队长
					return button4;
				}else if("true"=="${check1}"&&"false"=="${check2}"){//监察大队队长
					return button2;
				}else if("false"=="${check1}"&&"true"=="${check2}"){//安监局领导
					return button4;
				}
			}else if(rec.checkStatus=='审核未通过'){
				if("false"=="${check1}"&&"false"=="${check2}"){//安监中队队长
					if("${sessionScope['LOGIN_USER_ID']}"==rec.createUserID){
						return  button1;
					}else{
						return button4;
					}
					
				}else if("true"=="${check1}"&&"false"=="${check2}"){//监察大队队长
					return button4;
				}else if("false"=="${check1}"&&"true"=="${check2}"){//安监局领导
					return button4;
				}
			}else if(rec.checkStatus=='审核通过'){
				if("false"=="${check1}"&&"false"=="${check2}"){//安监中队队长
					return button4;
				}else if("true"=="${check1}"&&"false"=="${check2}"){//监察大队队长
					return button4;
				}else if("false"=="${check1}"&&"true"=="${check2}"){//安监局领导
					return button3;
				}
			}else if(rec.checkStatus=='审批通过'){
				return button4;
			}else if(rec.checkStatus=='审批未通过'){
				if("false"=="${check1}"&&"false"=="${check2}"){//安监中队队长
					if("${sessionScope['LOGIN_USER_ID']}"==rec.createUserID){
						return  button1;
					}else{
						return button4;
					}
				}else if("true"=="${check1}"&&"false"=="${check2}"){//监察大队队长
					return button4;
				}else if("false"=="${check1}"&&"true"=="${check2}"){//安监局领导
					return button4;
				}
			}else{
				return button4;
			}
		}
        
        function addNew(){
        	var dt=new Date();
        	layer.open({
		        type: 2,
		        skin: 'layui-layer-lan',
		        title: "添加失信行为",
		        fix: false,
		        shadeClose: true,
		        maxmin: true,
		        area: ['1000px', '500px'],
		        content: "${ctx}/zwt/dishonestyInitEdit.action?flag=add&dt="+dt.getTime(),
		        end: function(){
		        		getList(ajaxData.pageNo,ajaxData.pageSize);
		        	}
		    });
        	
        }
        function edit(row_Id){
        	var dt=new Date();
        	layer.open({
		        type: 2,
		        skin: 'layui-layer-lan',
		        title: "修改失信行为",
		        fix: false,
		        shadeClose: true,
		        maxmin: true,
		        area: ['1000px', '500px'],
		        content: "${ctx}/zwt/dishonestyInitEdit.action?flag=mod&dishonesty.id="+row_Id+"&dt="+dt.getTime(),
		        end: function(){
		        		getList(ajaxData.pageNo,ajaxData.pageSize);
		        	}
		    });
        	
        }
        function view(row_Id){
        	var dt=new Date();
        	layer.open({
		        type: 2,
		        skin: 'layui-layer-lan',
		        title: "查看失信行为",
		        fix: false,
		        shadeClose: true,
		        maxmin: true,
		        area: ['1000px', '500px'],
		        content: "${ctx}/zwt/dishonestyView.action?dishonesty.id="+row_Id+"&dt="+dt.getTime(),
		        end: function(){
		        		getList(ajaxData.pageNo,ajaxData.pageSize);
		        	}
		    });
        	
        }
        
        function check1(row_Id){
        	var dt=new Date();
        	layer.open({
		        type: 2,
		        skin: 'layui-layer-lan',
		        title: "审核失信行为",
		        fix: false,
		        shadeClose: true,
		        maxmin: true,
		        area: ['1000px', '500px'],
		        content: "${ctx}/zwt/dishonestyCheck1.action?flag=check1&dishonesty.id="+row_Id+"&dt="+dt.getTime(),
		        end: function(){
		        		getList(ajaxData.pageNo,ajaxData.pageSize);
		        	}
		    });
        	
        }
        function check2(row_Id){
        	var dt=new Date();
        	layer.open({
		        type: 2,
		        skin: 'layui-layer-lan',
		        title: "审批失信行为",
		        fix: false,
		        shadeClose: true,
		        maxmin: true,
		        area: ['1000px', '500px'],
		        content: "${ctx}/zwt/dishonestyCheck2.action?flag=check2&dishonesty.id="+row_Id+"&dt="+dt.getTime(),
		        end: function(){
		        		getList(ajaxData.pageNo,ajaxData.pageSize);
		        	}
		    });
        	
        }
        
        function rel(){
        	getList(ajaxData.pageNo,ajaxData.pageSize);
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
        	<a href="#" class="s_h_logo"><i></i><span>基础信息查询</span></a>
            <div class="s_h_nav">
                <ul>
                	<li>
                		<a href="${ctx }/zwt/entBaseInfoListZwt.action">企业安全基本信息查询</a>
                    </li>
                	<li>
                		<a href="${ctx }/zwt/qyzcyhListZwt.action">企业自查隐患查询</a>
                    </li>
                	<li>
                		<a href="${ctx }/zwt/jgbmjcyhListZwt.action">监管部门检查隐患查询</a>
                	</li>
                	<li>
                		<a class="active" href="#">企业安全信用查询</a>
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
                	<input class="s_s_text" type="text" id="searchLike" value="搜索企业名称、处罚名称或文书号"><input class="s_s_btn" type="submit" onclick="searchByNameOrCode();"> 
                	<!--<div class="s_s_mBox"><a href="#" class="s_s_morebtn"></a>
                	</div>
                --></div>
            </div>
    	</div>

<!-- body style -->
        <div class="sipac_body">
        	<div class="sipac_menu">
            	<ul>
                	<li>
                    	<a class="m_icon m_i_0" href="index.html"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="index.html">返回主界面</a>
                        </div>
                    </li>
                	<!--li>
                    	<a class="m_icon m_i_1" href="style_guide.html"></a>
                        <div class="dropdown_nav">
                        	<a class="s_m_tit" href="style_guide.html">样式规范</a>
                        	<ul>
                        	<li><a href="">图标</a></li>
                        	<li><a href="">按钮</a></li>
                        	</ul>
                        </div>
                    </li-->
                	<li class="dropdown_active">
                    	<a class="m_icon m_i_2" href="search_table.html"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="search_table.html">例：表格+搜索</a>
                        </div>
                    </li>
                	<li>
                    	<a class="m_icon m_i_3" href="form.html"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="form.html">例：表单示例</a>
                        </div>
                    </li>
                	<li>
                    	<a class="m_icon m_i_4" href="simple_page.html"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="simple_page.html">例：简洁框架</a>
                        </div>
                    </li>
                </ul>
                
            </div>
            <div class="sipac_container" id="tableDiv">
            	<div class="sipac_searchBox sipac_form" id="searchForm">
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
                        </tr>
                        
                    </table>
                    <div class="s_s_bar"><button class="sipac_btn s_b_submit" onclick="search();">搜索</button></div>
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
                        	-->
                        	<th><span>序号</span></th>
                        	<th><span>所在区域</span></th>
                        	<th><span>企业名称 </span></th>
                        	<th><span>处罚名称</span></th>
                        	<th><span>处罚决定书文号 </span></th>
                        	<th><span>处罚种类 </span></th>
                        	<th><span>失信等级  </span></th>
                        	<th><span>审核状态 </span></th>
                        	<th><span>操作项</span></th>
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

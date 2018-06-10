<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>监管业务管理</title>
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
    var searchText="搜索计划名称";
    	 $(function(){
    	 
    	 	$('.sipac_searchbar .s_s_text').click(function(){
		        var email = $(this).val();
		        if(email == searchText){
		            $(this).val("");
		        }
		    }).blur(function(){
		        var email = $(this).val();
		        if(email == ""){
		            $(this).val(searchText);
		        }
		    });
    	 	
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
    	 	
    	 	
    	 	 $("#mainContent").load("${ctx}/zwt/jdjcjh.action");
		   
		});
		
		 function selectCompany(){
		 	window.location.href="${ctx}/zwt/zwtJgyw.action";
    
        }
        
        function bind1(title,obj)
        {
        	$(".sipac_menu li").removeClass('dropdown_active');
	        $(obj).addClass('dropdown_active');  
	        changeMenu1( title); //一级菜单切换
        }
        
        function bind2(title,obj)
        {
        	$(".s_sb_nav a").removeClass('active');
	        $(obj).addClass('active');  
	        changeMenu3(title); //三级菜单切换
        }
        
        var jdjgMenu2="<a class='active' href='javascript:;' onclick=bind2('监督检查计划',this)>监督检查计划</a>"+
	                				"<a href='javascript:;' onclick=bind2('监督检查任务',this)>监督检查任务</a>"+
	                				"<a href='javascript:;' onclick=bind2('监督检查结果',this)>监督检查结果</a>"+
	                				"<a href='javascript:;' onclick=bind2('专项整治计划',this)>专项整治计划</a>"+
	                				"<a href='javascript:;' onclick=bind2('专项整治检查记录',this)>专项整治检查记录</a>";
	    var yhglMenu2="<a class='active' href='javascript:;' onclick=bind2('安全生产隐患',this)>安全生产隐患</a>"+
	                				"<a href='javascript:;' onclick=bind2('挂牌督办',this)>挂牌督办</a>";
        var xzzfMenu2="<a class='active' href='javascript:;' onclick=bind2('案件管理',this)>案件管理</a>"+
	                				"<a href='javascript:;' onclick=bind2('文书管理',this)>文书管理</a>";
	                		
	                		
        
        function changeMenu1(str){
        	if(str=='监督监管'){
	             $(".s_sb_nav").html(jdjgMenu2);
	             changeMenu3('监督检查计划');
	             $("#title").html('监督监管');
        	}else if(str=='隐患管理'){
	             $(".s_sb_nav").html(yhglMenu2);
	             $("#title").html('隐患管理');
	             changeMenu3('安全生产隐患');
        	}else if(str=='行政执法'){
	             $(".s_sb_nav").html(xzzfMenu2);
	             $("#title").html('行政执法');
	             changeMenu3('案件管理');
        	}
        }
        
        
        var loadUrl='${ctx}/zwt/jdjcjh.action';
        function  changeMenu3(str){
        	if(str=='监督检查计划'){
        		searchText="搜索计划名称";
        		loadUrl="${ctx}/zwt/jdjcjh.action";
        	}else if(str=='监督检查任务'){
        		searchText="搜索企业名称";
        		loadUrl="${ctx}/zwt/jdjcrw.action";
        	}else if(str=='监督检查结果'){
        		searchText="搜索企业名称";
        		loadUrl="${ctx}/zwt/jdjcjg.action";
        	}else if(str=='专项整治计划'){
        		searchText="搜索计划名称";
        		loadUrl="${ctx}/zwt/zxzzjh.action";
        	}else if(str=='专项整治检查记录'){
        		searchText="搜索计划名称";
        		loadUrl="${ctx}/zwt/zxzzjcjl.action";
        	}else if(str=='安全生产隐患'){
        		searchText="搜索企业名称或隐患名称";
        		loadUrl="${ctx}/zwt/aqscyh.action";
        	}else if(str=='挂牌督办'){
        		searchText="搜索企业名称或隐患名称";
        		loadUrl="${ctx}/zwt/gpdb.action";
        	}else if(str=='案件管理'){
        		searchText="搜索企业名称或案件名称";
        		loadUrl="${ctx}/zwt/ajgl.action";
        	}else if(str=='文书管理'){
        		searchText="搜索企业名称或文书名称";
        		loadUrl="${ctx}/zwt/wsgl.action";
        	}
        	$("#mainContent").load(loadUrl);
        	changeSearchText(searchText);
        }
        
        function changeSearchText(s){
        	$("#searchText").val(s);
        }
        
        function searchUrl(){
        var s=$("#searchText").val();
        	if("搜索企业名称"!=s&&"搜索计划名称"!=s&&"搜索企业名称或隐患名称"!=s&&"搜索企业名称或案件名称"!=s&&"搜索案件名称或文书名称"!=s){
        		var u=loadUrl+"?searchLike="+encodeURIComponent(s);
	        	$("#mainContent").load(u);
        	}else{
        		$("#mainContent").load(loadUrl);
        	}
        }
    </script>
</head>
<body>
<input type="hidden" id="searchType" value="0"/>
<input type="hidden" id="showType" value="0"/>
<input type="hidden" id="currPage" value="1"/>
	<div class="sipac_wrap">
    	<div class="sipac_head" style="display:none">
        	<a href="javaScript:selectCompany()" class="s_h_logo"><i></i><span></span></a>
             
            
    	</div>
        <div class="sipac_body">
        	<div class="sipac_menu">
            	<ul>
                	<li style="display:none">
                    	<a class="m_icon m_i_0" href="javaScript:selectCompany()"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="javaScript:selectCompany()">返回主界面</a>
                        </div>
                    </li>
                	<li class="dropdown_active" onclick=bind1('监督监管',this)>
                    	<a class="m_icon m_i_2 " href="#"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="#">监督监管</a>
                        </div>
                    </li>
                    <li onclick=bind1('隐患管理',this)>
                    	<a class="m_icon m_i_3" href="#"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="#">隐患管理</a>
                        </div>
                    </li>
                	<li onclick=bind1('行政执法',this)>
                    	<a class="m_icon m_i_4" href="#"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="#">行政执法</a>
                        </div>
                    </li>
                </ul>
                
            </div>
            <div class="sipac_container">
                <div class="sipac_crumbs" >
                	<div class="s_c_cont" id="title">监督监管</div>
                	
                	
                	
                	<div class="sipac_rtbox">
		            	<div class="sipac_searchbar"><input class="s_s_text" id="searchText" type="text" value="搜索计划名称"><input class="s_s_btn" type="button" onclick="searchUrl();">
		                </div>
            		</div>
                 </div>
                <div class="sipac_selectbox">
                	<div class="s_sb_nav">
                		<a class="active" href="#" onclick=bind2('监督检查计划',this)>监督检查计划</a><a href="#" onclick=bind2('监督检查任务',this)>监督检查任务</a><a href="#" onclick=bind2('监督检查结果',this)>监督检查结果</a>
                		<a href="#" onclick=bind2('专项整治计划',this)>专项整治计划</a><a href="#" onclick=bind2('专项整治检查记录',this)>专项整治检查记录</a>
                	</div>
                    <div class="s_sb_cont sipac_form" id="mainContent">                         
                         	数据加载中，请稍等...
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

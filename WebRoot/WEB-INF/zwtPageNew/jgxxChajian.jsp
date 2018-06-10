<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>监管信息管理</title>
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
    var searchText="搜索企业名称、备案编号或评价机构名称";
    var ajaxData={ };
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
    	 	$("#mainContent").load("${ctx}/zwt/assRepFilList.action");
		   
		});
		
        
        
      function bind1(title,obj)
      {	
      		$(".sipac_menu li").removeClass('dropdown_active');
	         $(obj).addClass('dropdown_active');               
	         changeMenu1( title); //一级菜单切换
      }
      
      function bind2(title,obj)
      {
      		$(".s_h_nav a").removeClass('active');
	        $(obj).addClass('active');  
	        changeMenu2( title); //二级菜单切换
      }
      
      function bind3(title,obj)
      {
      		$(".s_sb_nav a").removeClass('active');
	        $(obj).addClass('active');  
	        changeMenu3( title); //三级菜单切换
      }
        
        var amenu2="<ul><li><a class='active' href='javascript:;' onclick=bind2('一般行政许可',this)>一般行政许可</a></li>"+
	                		"<li><a href='javascript:;' onclick=bind2('危化品行政许可',this)>危化品行政许可</a></li>"+
	                		"<li><a href='javascript:;' onclick=bind2('职业健康行政许可',this)>职业健康行政许可</a></li>"+
	                		"<li><a href='javascript:;' onclick=bind2('烟花爆竹行政许可',this)>烟花爆竹行政许可</a></li>"+
	                		"</ul>";
	                		
	    var bmenu2="<ul><li><a href='javascript:;' onclick=bind2('危化品建设项目',this)>危化品建设项目</a></li>"+
	                		"</ul>";
	    
	    var cmenu2="<ul><li><a href='javascript:;' onclick=bind2('行政备案',this)>行政备案</a></li>"+
	                		"</ul>";
	    
	    var dmenu2="<ul><li><a href='javascript:;' onclick=bind2('安全生产分级管理',this)>安全生产分级管理</a></li>"+
	                		"</ul>";
        
        var aMenu3="<a class='active' href='javascript:;' title='一般企业安评报告备案' onclick=bind3('一般安评备案',this)>一般安评备案</a> "+
			             			 "<a href='javascript:;' title='安全条件论证与安全预评价' onclick=bind3('安全预评价',this)>安全预评价</a>"+
			             		  	 "<a href='javascript:;' title='安全设施设计审查' onclick=bind3('设施设计审查',this)>设施设计审查</a>"+
			             		 	 "<a href='javascript:;' title='安全设施施工和竣工验收' onclick=bind3('施工和竣工验收',this)>施工和竣工验收</a>"+
			             		 	 "<a href='javascript:;' title='许可证注销信息' onclick=bind3('许可证注销',this)>许可证注销</a>"+
			             		 	 "<a href='javascript:;' title='事故应急救援预案备案' onclick=bind3('应急预案备案',this)>应急预案备案</a>";
			             		 	 
		var bMenu3="<a class='active' href='javascript:;' title='危化企业经营许可' onclick=bind3('危化经营许可',this)>危化经营许可</a>"+
			             			 "<a href='javascript:;' title='危化企业生产许可' onclick=bind3('危化生产许可',this)>危化生产许可</a>"+
			             		  	 "<a href='javascript:;' title='非药品类易制毒化学品经营许可' onclick=bind3('非药品类经营许可',this)>非药品类经营许可</a>"+
			             		 	 "<a href='javascript:;' title='非药品类易制毒化学品生产许可' onclick=bind3('非药品类生产许可',this)>非药品类生产许可</a>";
	             			
	    var cMenu3="<a class='active' href='javascript:;' title='职危项目预评价审核' onclick=bind3('预评价审核',this)>预评价审核</a>"+
			             			 "<a href='javascript:;' title='职危项目防护设计审查' onclick=bind3('防护设计审查',this)>防护设计审查</a>"+
			             			 "<a href='javascript:;' title='职危项目防护设施竣工审查' onclick=bind3('防护设施竣工审查',this)>防护设施竣工审查</a>"+
			             			 "<a href='javascript:;' title='职危项目预评价报告备案通知书' onclick=bind3('预评价备案通知书',this)>预评价备案通知书</a>"+
			             			 "<a href='javascript:;' title='职危项目防护设施竣工备案通知书' onclick=bind3('竣工备案通知书',this)>竣工备案通知书</a>";	
	             					 
	    var dMenu3="<a class='active' href='javascript:;' onclick=bind3('烟花爆竹行政许可',this)>烟花爆竹行政许可</a>";
			             			 
		var eMenu3="<a class='active' href='javascript:;' title='危化品建设项目安全条件审查' onclick=bind3('安全条件审查',this)>安全条件审查</a>"+
	    							 "<a href='javascript:;' title='危险化学品建设项目安全设施设计审查' onclick=bind3('危建设计审查',this)>危建设计审查</a>"+
	   								 "<a href='javascript:;' title='危险化学品建设项目安全设施竣工验收' onclick=bind3('危建竣工验收',this)>危建竣工验收</a>"+
	             					 "<a href='javascript:;' title='危险化学品建设项目安全设施试生产备案' onclick=bind3('危建试生产备案',this)>危建试生产备案</a>";
	             					            			 
		
		var fMenu3="<a class='active' href='javascript:;' title='第二三类非药品类易制毒化学品生产备案' onclick=bind3('第二三类生产备案',this)>第二三类生产备案</a>"+
			             			 "<a href='javascript:;' title='第二三类非药品类易制毒化学品经营备案' onclick=bind3('第二三类经营备案',this)>第二三类经营备案</a>"+
			             			 "<a href='javascript:;' title='危化品安全条件评价报告备案' onclick=bind3('危化安评备案',this)>危化安评备案</a>";	 	    
		
		
		var gMenu3="<a class='active' href='javascript:;' onclick=bind3('企业失信行为管理',this)>企业失信行为管理</a>"+
			             			 "<a href='javascript:;' onclick=bind3('安全生产诚信黑名单管理',this)>安全生产诚信黑名单管理</a>";	
        
        function changeMenu1(str){
        	document.getElementById('ejcd').style.display="";
        	if(str == '行政许可'){
        		 $(".s_h_nav").html(amenu2);
	             $(".s_sb_nav").html(aMenu3); 
	             changeMenu3('一般安评备案');
        	}else if(str=='危化品建设项目'){
        		$(".s_h_nav").html(bmenu2);
	             $(".s_sb_nav").html(eMenu3); 
	             changeMenu3('安全条件审查');
        	}else if(str=='行政备案'){
        		$(".s_h_nav").html(cmenu2);
	             $(".s_sb_nav").html(fMenu3); 
	             changeMenu3('第二三类生产备案');
        	}else if(str=='安全生产分级管理'){
        		$(".s_h_nav").html(dmenu2);
	             $(".s_sb_nav").html(gMenu3); 
	             changeMenu3('企业失信行为管理');
        	}
        }
        
        function changeMenu2(str){
        	if(str == '一般行政许可'){
        		document.getElementById('ejcd').style.display="";
	             $(".s_sb_nav").html(aMenu3); 
	             changeMenu3('一般安评备案');
        	}else if(str=='危化品行政许可'){
        		document.getElementById('ejcd').style.display="";
	             $(".s_sb_nav").html(bMenu3); 
	             changeMenu3('危化经营许可');
        	}else if(str=='职业健康行政许可'){
        		document.getElementById('ejcd').style.display="";
	             $(".s_sb_nav").html(cMenu3); 
	             changeMenu3('预评价审核');
        	}else if(str=='烟花爆竹行政许可'){	
        		 document.getElementById('ejcd').style.display="none";
	             $(".s_sb_nav").html(dMenu3); 
	             changeMenu3('烟花爆竹行政许可');
        	}else if(str=='危化品建设项目'){
        		document.getElementById('ejcd').style.display="";
	             $(".s_sb_nav").html(eMenu3); 
	             changeMenu3('安全条件审查');
        	}else if(str=='行政备案'){
        		document.getElementById('ejcd').style.display="";
	             $(".s_sb_nav").html(fMenu3); 
	             changeMenu3('第二三类生产备案');
        	}else if(str=='安全生产分级管理'){
        		document.getElementById('ejcd').style.display="";
	             $(".s_sb_nav").html(gMenu3); 
	             changeMenu3('企业失信行为管理');
        	}
        }
        
    	var loadUrl='${ctx}/zwt/assRepFilList.action';
     	function  changeMenu3(str){
        	if(str=='一般安评备案'){
        		searchText="搜索企业名称、备案编号或评价机构名称";
        		loadUrl="${ctx}/zwt/assRepFilList.action";
        	}else if(str=='安全预评价'){
        		searchText="搜索企业名称或评价机构名称";
        		loadUrl="${ctx}/zwt/proSafAssList.action";
        	}else if(str=='设施设计审查'){
        		searchText="搜索企业名称或备案编号";
        		loadUrl="${ctx}/zwt/proSafDesList.action";
        	}else if(str=='施工和竣工验收'){
        		searchText="搜索企业名称、评价单位或审批编号";
        		loadUrl="${ctx}/zwt/proSafComList.action";
        	}else if(str=='许可证注销'){
        		searchText="搜索企业名称、许可证名称或许可证编号";
        		loadUrl="${ctx}/zwt/licCanInfList.action";
        	}else if(str=='应急预案备案'){
        		searchText="搜索企业名称、备案编号或备案机构";
        		loadUrl="${ctx}/zwt/accPlaRecList.action";
        	}else if(str=='危化经营许可'){
        		searchText="搜索企业名称或评价机构";
        		loadUrl="${ctx}/zwt/cheManLicList.action";
        	}else if(str=='危化生产许可'){
        		searchText="搜索企业名称或评价机构名称";
        		loadUrl="${ctx}/zwt/cheProLicList.action";
        	}else if(str=='非药品类经营许可'){
        		searchText="搜索企业名称或档案编号";
        		loadUrl="${ctx}/zwt/preBusLicList.action";
        	}else if(str=='非药品类生产许可'){
        		searchText="搜索企业名称、许可证编号或档案编号";
        		loadUrl="${ctx}/zwt/preProLicList.action";
        	}else if(str=='安全条件审查'){
        		searchText="搜索企业名称或评价机构名称";
        		loadUrl="${ctx}/zwt/cheSafConList.action";
        	}else if(str=='危建设计审查'){
        		searchText="搜索企业名称或设计单位";
        		loadUrl="${ctx}/zwt/cheSafDesList.action";
        	}else if(str=='危建竣工验收'){
        		searchText="搜索企业名称或评价单位";
        		loadUrl="${ctx}/zwt/cheSafComList.action";
        	}else if(str=='危建试生产备案'){
        		searchText="搜索企业名称或档案编号";
        		loadUrl="${ctx}/zwt/preProRecList.action";
        	}else if(str=='第二三类生产备案'){
        		searchText="搜索企业名称或备案编号";
        		loadUrl="${ctx}/zwt/cheSafProList.action";
        	}else if(str=='第二三类经营备案'){
        		searchText="搜索企业名称或档案编号";
        		loadUrl="${ctx}/zwt/preManRecList.action";
        	}else if(str=='危化安评备案'){
        		searchText="搜索企业名称、备案编号或评价机构名称";
        		loadUrl="${ctx}/zwt/cheRepFilList.action";
        	}else if(str=='预评价审核'){
        		searchText="搜索企业名称或档案编号";
        		loadUrl="${ctx}/zwt/zwxmypjsh.action";
        	}else if(str=='防护设计审查'){
        		searchText="搜索企业名称";
        		loadUrl="${ctx}/zwt/zwxmfhsjsc.action";
        	}else if(str=='防护设施竣工审查'){
        		searchText="搜索企业名称";
        		loadUrl="${ctx}/zwt/zwxmfhjgsc.action";
        	}else if(str=='预评价备案通知书'){
        		searchText="搜索企业名称";
        		loadUrl="${ctx}/zwt/jsxmzzybwhyp.action";
        	}else if(str=='竣工备案通知书'){
        		searchText="搜索企业名称";
        		loadUrl="${ctx}/zwt/jsxmzybfhssba.action";
        	}else if(str=='烟花爆竹行政许可'){
        		searchText="搜索企业名称或档案号";
        		loadUrl="${ctx}/zwt/firLicList.action";
        	}else if(str=='企业失信行为管理'){
        		searchText="搜索企业名称、处罚名称或文书号";
        		loadUrl="${ctx}/zwt/sxxw.action";
        	}else if(str=='安全生产诚信黑名单管理'){
        		searchText="搜索企业名称";
        		loadUrl="${ctx}/zwt/hmd.action";
        	}
			$("#mainContent").load(loadUrl);
        	$("#searchText").val(searchText);
        }
    
    
    	function searchUrl(){
        	var s=$("#searchText").val();
        	if(s.indexOf("搜索企业名称") < 0){
        		var u=loadUrl+"?searchLike="+encodeURIComponent(s);
	        	$("#mainContent").load(u);
        	}else{
        		$("#mainContent").load(loadUrl);
        	}
        }
    
    
    	
    </script>
</head>
<body>

	<div class="sipac_wrap">
    	<div class="sipac_body">
		<div class="sipac_menu">
            	<ul>
                    <li class="dropdown_active" onclick="bind1('行政许可',this)">
                    	<a class="m_icon m_i_1" href="#"></a>
                        <div class="dropdown_nav">
                        	<a class="s_m_tit" href="#">行政许可</a>
                        </div>
                    </li>
                    <li onclick="bind1('危化品建设项目',this)">
                    	<a class="m_icon m_i_2" href="#"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="#">危化品建设项目</a>
                        </div>
                    </li>
		    		<li onclick="bind1('行政备案',this)">
                    	<a class="m_icon m_i_3" href="#"></a>
                        <div class="dropdown_nav">
                        	<a class="s_m_tit" href="#">行政备案</a>
                        </div>
                    </li>
		    		<li onclick="bind1('安全生产分级管理',this)">
                    	<a class="m_icon m_i_4" href="#"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="#">安全生产分级管理</a>
                        </div>
                    </li>
                </ul>
                
            </div>
            <div class="sipac_container">
                <div class="sipac_crumbs" >
                	<div class="s_h_nav">
	                	<ul>
		                	<li>
		                		<a class="active" href="javascript:;" onclick=bind2('一般行政许可',this)>一般行政许可</a>
		                    </li>
		                	<li >
		                		<a href="javascript:;" onclick=bind2('危化品行政许可',this)>危化品行政许可</a>
		                    </li>
		                	<li >
		                		<a href="javascript:;" onclick=bind2('职业健康行政许可',this)>职业健康行政许可</a>
		                	</li>
		                	<li >
		                		<a href="javascript:;" onclick=bind2('烟花爆竹行政许可',this)>烟花爆竹行政许可</a>
		                	</li>
		                </ul>
	                </div>
	                
                	<div class="sipac_rtbox">
		            	<div class="sipac_searchbar"><input class="s_s_text" id="searchText" type="text" value="搜索企业名称、备案编号或评价机构名称"><input class="s_s_btn" type="button" onclick="searchUrl();">
		                </div>
            		</div>
                 </div>
                <div class="sipac_selectbox">
                	<div class="s_sb_nav" id="ejcd">
						<a class="active" href="#" title="一般企业安评报告备案" onclick="bind3('一般安评备案',this)">一般安评备案</a>
			             			<a href="#" title="安全条件论证与安全预评价" onclick="bind3('安全预评价',this)">安全预评价</a>
			             		  	 <a href="#" title="安全设施设计审查" onclick="bind3('设施设计审查',this)">设施设计审查</a>
			             		 	 <a href="#" title="安全设施施工和竣工验收" onclick="bind3('施工和竣工验收',this)">施工和竣工验收</a>
			             		 	 <a href="#" title="许可证注销信息" onclick="bind3('许可证注销',this)">许可证注销</a>
			             		 	 <a href="#" title="事故应急救援预案备案" onclick="bind3('应急预案备案',this)">应急预案备案</a>
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

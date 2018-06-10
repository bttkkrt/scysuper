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
    var currentModule='';
    var no=1;
    var ps=50;
    var ajaxData={ };
    	 $(function(){
    	 	 changeMenu3("基本信息");
		   
		});
		
		 function selectCompany(){
		 	window.location.href="${ctx}/zwt/zwtJcxxs.action";
    
        }
        
        function bind1(title,obj){
	         $(".sipac_menu li").removeClass('dropdown_active');
	         $(obj).addClass('dropdown_active');               
	         changeMenu1( title); //一级菜单切换
        }
        
        function bind2(title,obj){
	        $(".s_h_nav a").removeClass('active');
	        $(obj).addClass('active');  
	        changeMenu2( title); //二级菜单切换
        }
        
        function bind3(title,obj){
	        $(".s_sb_nav a").removeClass('active');
	        $(obj).addClass('active');  
	        changeMenu3( title); //三级菜单切换
        }
        
        var jcxxMenu2="<ul><li><a class='active' href='javascript:;' onclick=bind2('企业信息',this)>企业信息</a></li>"+
	                		"<li><a href='javascript:;' onclick=bind2('组织机构',this)>组织机构</a></li>"+
	                		"<li><a href='javascript:;' onclick=bind2('法律法规',this)>法律法规</a></li>"+
	                		"<li><a href='javascript:;' onclick=bind2('应急预案',this)>应急预案</a></li>"+
	                		"</ul>";
        var aqscMenu2="<ul><li><a class='active' href='javascript:;' onclick=bind2('生产投入',this)>生产投入</a></li>"+
	                		"<li><a href='javascript:;' onclick=bind2('教育培训',this)>教育培训</a></li>"+
	                		"<li><a href='javascript:;' onclick=bind2('作业安全',this)>作业安全</a></li>"+
	                		"<li><a href='javascript:;' onclick=bind2('劳保用品',this)>劳保用品</a></li>"+
	                		"</ul>";
        var yhpcMenu2="<ul><li><a class='active' href='javascript:;' onclick=bind2('自查隐患',this)>自查隐患</a></li>"+
	                		"<li><a href='javascript:;' onclick=bind2('危险源监控',this)>危险源监控</a></li>"+
	                		"<li><a href='javascript:;' onclick=bind2('事故报告',this)>事故报告</a></li>"+
	                		"</ul>";
	    var qtMenu2="<ul><li><a class='active' href='javascript:;' onclick=bind2('职业健康',this)>职业健康</a></li>"+
	                		"<li><a href='javascript:;' onclick=bind2('危化信息',this)>危化信息</a></li>"+
	                		"<li><a href='javascript:;' onclick=bind2('涉爆粉尘信息',this)>涉爆粉尘信息</a></li>"+
	                		"</ul>";
        var qyxxMenu3="<a class='active' href='javascript:;' onclick=bind3('基本信息',this)>基本信息</a>"+
			             			 "<a href='javascript:;' onclick=bind3('资质情况',this)>资质情况</a>"+
			             		  	 "<a href='javascript:;' onclick=bind3('荣誉表彰',this)>荣誉表彰</a>"+
			             		 	 "<a href='javascript:;' onclick=bind3('生产目标',this)>生产目标</a>"+
			             		 	 "<a href='javascript:;' onclick=bind3('生产承诺',this)>生产承诺</a>"+
			             	 		 "<a href='javascript:;' onclick=bind3('标准化评分',this)>标准化评分</a>"+
			             		 	 "<a href='javascript:;' onclick=bind3('分级分类评分',this)>分级分类评分</a>";
	             			
	    var zzjgMenu3="<a class='active' href='javascript:;' onclick=bind3('机构信息',this)>机构信息</a>"+
	             					 "<a href='javascript:;' onclick=bind3('人员信息',this)>人员信息</a>";
	             					 
	    var flfgMenu3="<a class='active' href='javascript:;' onclick=bind3('法律法规和标准规范',this)>法律法规和标准规范</a>"+
			             			 "<a href='javascript:;' onclick=bind3('安全生产规章制度',this)>安全生产规章制度</a>"+
			             			 "<a href='javascript:;' onclick=bind3('操作规程',this)>操作规程</a>";
			             			 
		var yjyaMenu3="<a class='active' href='javascript:;' onclick=bind3('应急机构与队伍',this)>应急机构与队伍</a>"+
			             			 "<a href='javascript:;' onclick=bind3('应急预案',this)>应急预案</a>"+
			             			 "<a href='javascript:;' onclick=bind3('应急设施装备物资',this)>应急设施装备物资</a>"+
			             			 "<a href='javascript:;' onclick=bind3('应急演练',this)>应急演练</a>";	             			 
		
		var sctrMenu3="<a class='active' href='javascript:;' onclick=bind3('费用提取',this)>费用提取</a>"+
			             			 "<a href='javascript:;' onclick=bind3('使用台账',this)>使用台账</a>"+
			             			 "<a href='javascript:;' onclick=bind3('投保情况',this)>投保情况</a>";	 	    
		
		var jypxMenu3="<a class='active' href='javascript:;' onclick=bind3('教育培训',this)>教育培训</a>";	          			 
		
		var zyaqMenu3="<a class='active' href='javascript:;' onclick=bind3('生产车间',this)>生产车间</a>"+
			             			 "<a href='javascript:;' onclick=bind3('设备设施',this)>设备设施</a>"+
			             			 "<a href='javascript:;' onclick=bind3('现场管理和过程控制',this)>现场管理和过程控制</a>"+
			             			 "<a href='javascript:;' onclick=bind3('公告栏',this)>公告栏</a>"+
			             			 "<a href='javascript:;' onclick=bind3('告知卡',this)>告知卡</a>"+
			             			 "<a href='javascript:;' onclick=bind3('安全标识',this)>安全标识</a>"+
			             			 "<a href='javascript:;' onclick=bind3('相关方',this)>相关方</a>";	
			             			 
		var lbypMenu3="<a class='active' href='javascript:;' onclick=bind3('劳保用品信息',this)>劳保用品信息</a>"+
			             			 "<a href='javascript:;' onclick=bind3('劳保用品库房',this)>劳保用品库房</a>"+
			             			 "<a href='javascript:;' onclick=bind3('劳保用品领用',this)>劳保用品领用</a>";	 	             			 
			             			 
		var zcyhMenu3="<a class='active' href='javascript:;' onclick=bind3('巡查计划',this)>巡查计划</a>"+
			             			 "<a href='javascript:;' onclick=bind3('巡查任务',this)>巡查任务</a>";
			             			// "<a href='javascript:;'>隐患管理</a>";		             			 
		
		var wxyjkMenu3="<a class='active' href='javascript:;' onclick=bind3('识别评估分级',this)>识别评估分级</a>"+
			             			 "<a href='javascript:;' onclick=bind3('管理制度',this)>管理制度</a>"+
			             			 "<a href='javascript:;' onclick=bind3('操作规章',this)>操作规章</a>"+
			             			 "<a href='javascript:;' onclick=bind3('应急处置',this)>应急处置</a>";	             			 	             			 
		
		var sgbgMenu3="<a class='active' href='javascript:;' onclick=bind3('事故报告',this)>事故报告</a>";	 	    
		
		var zyjkMenu3="<a class='active' href='javascript:;' onclick=bind3('职业健康1',this)>职业健康1</a>"+
			             			 "<a href='javascript:;' onclick=bind3('职业健康2',this)>职业健康2</a>";	
			             			 
		var whxxMenu3="<a class='active' href='javascript:;' onclick=bind3('危化信息1',this)>危化信息1</a>"+
			             			 "<a href='javascript:;' onclick=bind3('危化信息2',this)>危化信息2</a>"+
			             			 "<a href='javascript:;' onclick=bind3('危化信息3',this)>危化信息3</a>";	   
			             			 
		var sbfcxxMenu3="<a class='active' href='javascript:;' onclick=bind3('涉爆粉尘作业场所',this)>涉爆粉尘作业场所</a>";	      			 
        
        function changeMenu1(str){
        	if(str=='基础信息'){
	             $(".s_h_nav").html(jcxxMenu2);
	             $(".s_sb_nav").html(qyxxMenu3); 
	             changeMenu3("基本信息");
	             $("#title").html('基础信息');
        	}else if(str=='安全生产'){
	             $(".s_h_nav").html(aqscMenu2);
	             $(".s_sb_nav").html(sctrMenu3); 
	             changeMenu3("费用提取");
	             $("#title").html('安全生产');
        	}else if(str=='隐患排查'){
	             $(".s_h_nav").html(yhpcMenu2);
	             $(".s_sb_nav").html(zcyhMenu3); 
	             changeMenu3("巡查计划")
	             $("#title").html('隐患排查');
        	}else if(str=='其他'){
	             $(".s_h_nav").html(qtMenu2);
	             $(".s_sb_nav").html(zyjkMenu3); 
	             changeMenu3("职业健康1");
	             $("#title").html('其他');
        	}
        }
        
        function changeMenu2(str){
        	if(str=='企业信息'){
	             $(".s_sb_nav").html(qyxxMenu3); 
	             changeMenu3('基本信息');
        	}else if(str=='组织机构'){
	             $(".s_sb_nav").html(zzjgMenu3); 
	             changeMenu3('机构信息');
        	}else if(str=='法律法规'){
	             $(".s_sb_nav").html(flfgMenu3); 
	             changeMenu3('法律法规和标准规范');
        	}else if(str=='应急预案'){
	             $(".s_sb_nav").html(yjyaMenu3); 
	             changeMenu3('应急机构与队伍');
        	}else if(str=='生产投入'){
	             $(".s_sb_nav").html(sctrMenu3); 
	             changeMenu3('费用提取');
        	}else if(str=='教育培训'){
	             $(".s_sb_nav").html(jypxMenu3); 
	             changeMenu3('教育培训');
        	}else if(str=='作业安全'){
	             $(".s_sb_nav").html(zyaqMenu3); 
	             changeMenu3('生产车间');
        	}else if(str=='劳保用品'){
	             $(".s_sb_nav").html(lbypMenu3); 
	             changeMenu3('劳保用品信息');
        	}else if(str=='自查隐患'){
	             $(".s_sb_nav").html(zcyhMenu3); 
	             changeMenu3('巡查计划');
        	}else if(str=='危险源监控'){
	             $(".s_sb_nav").html(wxyjkMenu3); 
	             changeMenu3('识别评估分级');
        	}else if(str=='事故报告'){
	             $(".s_sb_nav").html(sgbgMenu3); 
	             changeMenu3('事故报告');
        	}else if(str=='职业健康'){
	             $(".s_sb_nav").html(zyjkMenu3); 
	             changeMenu3('职业健康1');
        	}else if(str=='危化信息'){
	             $(".s_sb_nav").html(whxxMenu3); 
	             changeMenu3('危化信息1');
        	}else if(str=='涉爆粉尘信息'){
	             $(".s_sb_nav").html(sbfcxxMenu3); 
	             changeMenu3('涉爆粉尘作业场所');
        	}else {}
        }
        
        
        function  changeMenu3(str){
        	if(str=='基本信息'){
        		$("#mainContent").load("${ctx}/zwt/baseInfo.action?entBaseInfo.id=${entBaseInfo.id}");
        		
        	}else if(str=='资质情况'){
        		$("#mainContent").load("${ctx}/zwt/zzqk.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='荣誉表彰'){
        		$("#mainContent").load("${ctx}/zwt/rybz.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='生产目标'){
        		$("#mainContent").load("${ctx}/zwt/scmb.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='生产承诺'){
        		$("#mainContent").load("${ctx}/zwt/sccn.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='标准化评分'){
        		$("#mainContent").load("${ctx}/zwt/bzhpf.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='分级分类评分'){ 
        		$("#mainContent").load("${ctx}/zwt/fjflpf.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='机构信息'){
        		$("#mainContent").load("${ctx}/zwt/jgxx.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='人员信息'){
        		$("#mainContent").load("${ctx}/zwt/ryxx.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='法律法规和标准规范'){
        		$("#mainContent").load("${ctx}/zwt/flfghbzgf.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='安全生产规章制度'){
        		$("#mainContent").load("${ctx}/zwt/aqscgzzd.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='操作规程'){
        		$("#mainContent").load("${ctx}/zwt/czgc.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='应急机构与队伍'){
        		$("#mainContent").load("${ctx}/zwt/yjjgydw.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='应急预案'){
        		$("#mainContent").load("${ctx}/zwt/yjya.action?type=0&entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='应急设施装备物资'){
        		$("#mainContent").load("${ctx}/zwt/yjsszbwz.action?type=0&entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='应急演练'){
        		$("#mainContent").load("${ctx}/zwt/yjyl.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	
        	}else if(str=='费用提取'){
        		$("#mainContent").load("${ctx}/zwt/aqscfytqqk.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='使用台账'){
        		$("#mainContent").load("${ctx}/zwt/aqscfysytz.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='投保情况'){
        		$("#mainContent").load("${ctx}/zwt/aqscbxtbqk.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='生产车间'){
        		$("#mainContent").load("${ctx}/zwt/sccjgl.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='设备设施'){
        		$("#mainContent").load("${ctx}/zwt/scsbss.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='现场管理和过程控制'){
        		$("#mainContent").load("${ctx}/zwt/scxcgl.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='公告栏'){
        		$("#mainContent").load("${ctx}/zwt/ggl.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='告知卡'){
        		$("#mainContent").load("${ctx}/zwt/gzk.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='安全标识'){
        		$("#mainContent").load("${ctx}/zwt/mxbz.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='相关方'){
        		$("#mainContent").load("${ctx}/zwt/xgfgl.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='劳保用品信息'){
        		$("#mainContent").load("${ctx}/zwt/lbypxx.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='劳保用品库房'){
        		$("#mainContent").load("${ctx}/zwt/lbypkf.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='劳保用品领用'){
        		$("#mainContent").load("${ctx}/zwt/lbyply.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='巡查计划'){
        		$("#mainContent").load("${ctx}/zwt/wxyxcjhgl.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='巡查任务'){
        		$("#mainContent").load("${ctx}/zwt/wxyxcrwgl.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='识别评估分级'){
        		$("#mainContent").load("${ctx}/zwt/zdwxysbpgfj.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='管理制度'){
        		$("#mainContent").load("${ctx}/zwt/zdwxyglzd.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='操作规章'){
        		$("#mainContent").load("${ctx}/zwt/zdwxyczgc.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='应急处置'){
        		$("#mainContent").load("${ctx}/zwt/zdwxyyjcz.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='危化信息1'){
        		$("#mainContent").load("${ctx}/zwt/whxx1.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='危化信息2'){
        		$("#mainContent").load("${ctx}/zwt/whxx2.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='危化信息3'){
        		$("#mainContent").load("${ctx}/zwt/whxx3.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='教育培训'){
        		$("#mainContent").load("${ctx}/zwt/jypx.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}
			else if(str=='事故报告'){
        		$("#mainContent").load("${ctx}/zwt/sgbg.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	
        	}else if(str=='管理制度'){
        		$("#mainContent").load("${ctx}/zwt/glzd.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	
        	}else if(str=='防治经费'){
        		$("#mainContent").load("${ctx}/zwt/fzjf.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	
        	}else if(str=='职业健康基本信息'){
        		$("#mainContent").load("${ctx}/zwt/zyjkjbxx.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	
        	}else if(str=='作业点布局'){
        		$("#mainContent").load("${ctx}/zwt/zydbj.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	
        	}else if(str=='基本因素'){
        		$("#mainContent").load("${ctx}/zwt/jbys.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	
        	}else if(str=='危害因素检测'){
        		$("#mainContent").load("${ctx}/zwt/whysjc.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	
        	}else if(str=='检测超标管理'){
        		$("#mainContent").load("${ctx}/zwt/jccbgl.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	
        	}else if(str=='职业健康体检'){
        		$("#mainContent").load("${ctx}/zwt/zyjktj.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	
        	}else if(str=='体检不合格记录'){
        		$("#mainContent").load("${ctx}/zwt/tjbhgjl.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	
        	}else if(str=='调岗记录'){
        		$("#mainContent").load("${ctx}/zwt/dgjl.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	
        	}else if(str=='负责人履职情况'){
        		$("#mainContent").load("${ctx}/zwt/fzrlzqk.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	
        	}else if(str=='危险源备案'){
        		$("#mainContent").load("${ctx}/zwt/wxyba.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	
        	}else if(str=='危险源核销'){
        		$("#mainContent").load("${ctx}/zwt/wxyhx.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	
        	}else if(str=='危化品储量'){
        		$("#mainContent").load("${ctx}/zwt/whpcl.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	
        	}else if(str=='领导带班'){
        		$("#mainContent").load("${ctx}/zwt/lldb.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	
        	}else if(str=='节假日开停工'){
        		$("#mainContent").load("${ctx}/zwt/jjrktg.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	
        	}else if(str=='工艺流程'){
        		$("#mainContent").load("${ctx}/zwt/gylc.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	
        	}else if(str=='关键装置和重点部位'){
        		$("#mainContent").load("${ctx}/zwt/gjzzhzdbw.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	
        	}else if(str=='职业健康1'){
        		$("#mainContent").load("${ctx}/zwt/zyjk1.action?ids=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='职业健康2'){
        		$("#mainContent").load("${ctx}/zwt/zyjk2.action?ids=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}else if(str=='涉爆粉尘作业场所'){
        		$("#mainContent").load("${ctx}/zwt/fczycs.action?entBaseInfo.id=${entBaseInfo.id}&searchLike="+encodeURIComponent('${entBaseInfo.enterpriseName}'));
        	}

        	
        	
        	
        	
        }
        
        
    </script>
</head>
<body>
<input type="hidden" id="searchType" value="0"/>
<input type="hidden" id="showType" value="0"/>
<input type="hidden" id="currPage" value="1"/>
	<div class="sipac_wrap">
    	<div class="sipac_head"> 
    		<div style="position:absolute;left:15px">
        	<a href="###" class="s_h_logo"> <span id="title">基础信息</span></a>
            </div>
    		<div style="position:absolute;right:15px">
        	<a href="javaScript:selectCompany()" title="返回企业列表" class="s_h_logo"> <span>${entBaseInfo.enterpriseName }</span></a><a class="s_c_back" href="javaScript:selectCompany()" title="返回企业列表">&gt;</a>
    		</div>
    	</div>
        <div class="sipac_body">
        	<div class="sipac_menu">
            	<ul>
                	<li>
                    	<a class="m_icon m_i_0" href="javaScript:selectCompany()"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="javaScript:selectCompany()">返回企业列表</a>
                        </div>
                    </li>
                	<li class="dropdown_active"  onclick=bind1('基础信息',this)>
                    	<a class="m_icon m_i_2 " href="#"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="#">基础信息</a>
                        </div>
                    </li>
                	<li   onclick=bind1('安全生产',this)>
                    	<a class="m_icon m_i_3" href="#"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="#">安全生产</a>
                        </div>
                    </li>
                	<li   onclick=bind1('隐患排查',this)>
                    	<a class="m_icon m_i_4" href="#"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="#">隐患排查</a>
                        </div>
                    </li>
                    <li   onclick=bind1('其他',this)>
                    	<a class="m_icon m_i_1" href="#"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="#">其他</a>
                        </div>
                    </li>
                </ul>
                
            </div>
            <div class="sipac_container">
                <div class="sipac_crumbs">
                	<div class="s_h_nav">
	                	<ul>
		                	<li >
		                		<a class="active" href="#" onclick=bind2('企业信息',this)>企业信息</a>
		                    </li>
		                	<li  >
		                		<a href="#" onclick=bind2('组织机构',this)>组织机构</a>
		                    </li>
		                	<li  >
		                		<a href="#" onclick=bind2('法律法规',this)>法律法规</a>
		                	</li>
		                	<li  >
		                		<a href="#" onclick=bind2('应急预案',this)>应急预案</a>
		                	</li>
		                </ul>
	                </div>
                	
                 </div>
                <div class="sipac_selectbox">
                	<div class="s_sb_nav">
                		<a class="active" href="#"  onclick=bind3('基本信息',this)>基本信息</a><a href="#"  onclick=bind3('资质情况',this)>资质情况</a><a href="#"  onclick=bind3('荣誉表彰',this)>荣誉表彰</a>
                		<a  href="#"  onclick=bind3('生产目标',this)>生产目标</a><a href="#"  onclick=bind3('生产承诺',this)>生产承诺</a><a href="#"  onclick=bind3('标准化评分',this)>标准化评分</a>
                		<a href="#"  onclick=bind3('分级分类评分',this)>分级分类评分</a>
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

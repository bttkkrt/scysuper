<%@page language="java" import="com.jshx.core.utils.SysPropertiesUtil" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>苏州工业园区综合行政执法局</title>
<!-- jquery & easyui js -->
<script type="text/javascript" src="${ctx}/webResources/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/easyui/locale/easyui-lang-zh_CN.js"></script>

<link href="${ctx}/webResources/wzCss/css/index.css" rel="stylesheet" type="text/css"/>


<link rel="stylesheet" href="${ctx}/webResources/wzCss/ninoSlider/default.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${ctx}/webResources/wzCss/ninoSlider/nivo-slider.css" type="text/css" media="screen" />
<script type="text/javascript" src="${ctx}/webResources/wzCss/ninoSlider/jquery.nivo.slider.js"></script>

<script>
var Cookies = {};
	Cookies.set = function(name, value){
	     var argv = arguments;
	     var argc = arguments.length;
	     var expires = (argc > 2) ? argv[2] : null;
	     var path = (argc > 3) ? argv[3] : '/';
	     var domain = (argc > 4) ? argv[4] : null;
	     var secure = (argc > 5) ? argv[5] : false;
	     document.cookie = name + "=" + escape (value) +
	       ((expires == null) ? "" : ("; expires=" + expires.toGMTString())) +
	       ((path == null) ? "" : ("; path=" + path)) +
	       ((domain == null) ? "" : ("; domain=" + domain)) +
	       ((secure == true) ? "; secure" : "");
	};
	Cookies.get = function(name){
	    var arg = name + "=";
	    var alen = arg.length;
	    var clen = document.cookie.length;
	    var i = 0;
	    var j = 0;
	    while(i < clen){
	        j = i + alen;
	        if (document.cookie.substring(i, j) == arg)
	            return Cookies.getCookieVal(j);
	        i = document.cookie.indexOf(" ", i) + 1;
	        if(i == 0)
	            break;
	    }
	    return null;
	};
	Cookies.clear = function(name) {
	  if(Cookies.get(name)){
	    var expdate = new Date(); 
	    expdate.setTime(expdate.getTime() - (86400 * 1000 * 1)); 
	    Cookies.set(name, "", expdate); 
	  }
	};

	Cookies.getCookieVal = function(offset){
	   var endstr = document.cookie.indexOf(";", offset);
	   if(endstr == -1){
	       endstr = document.cookie.length;
	   }
	   return unescape(document.cookie.substring(offset, endstr));
	};
</script>
<script>
$(function()
{
	$('#slider').nivoSlider();
	getSavedUserName();
	startTime();
});
function getSavedUserName(){
	var userName = Cookies.get("loginId");
	var password = Cookies.get("password");
	if(userName!=null){
		$("#loginId").val(userName);
		$("#password").val(password);
		$("#rem").attr("checked",true);
	}
}

function startTime() {
	var today=new Date()
	var years=today.getFullYear();
	var months=today.getMonth();
	var d=today.getDate()
	months=months+1
	months=checkTime(months)
	d=checkTime(d)
	var weekday=new Array(7)
	weekday[0]="星期日"
	weekday[1]="星期一"
	weekday[2]="星期二"
	weekday[3]="星期三"
	weekday[4]="星期四"
	weekday[5]="星期五"
	weekday[6]="星期六"
	var w=weekday[today.getDay()]
	document.getElementById('ShowTime').innerHTML=years+"年"+months+"月"+d+"日 "+w;
}

			function checkTime(i) {
			    if (i < 10) {
			        i = "0" + i
			    }
			    return i
			}
			
function showNews(obj)
{
	if(obj == '0')
	{
		$("#now1").removeClass("now");
		$("#now0").addClass("now");
		document.getElementById('new1').style.display="none";
		document.getElementById('more1').style.display="none";
		document.getElementById('new0').style.display="";
		document.getElementById('more0').style.display="";
	}
	else
	{
		$("#now0").removeClass("now");
		$("#now1").addClass("now");
		document.getElementById('new0').style.display="none";
		document.getElementById('more0').style.display="none";
		document.getElementById('new1').style.display="";
		document.getElementById('more1').style.display="";
	}
}

			function doLogin() {
				ff = document.loginForm;
				if(ff.rem.checked){
					var expires = new Date();
					expires.setTime(expires.getTime() + 1000*60*60*24*<%=SysPropertiesUtil.getInt("COOKIES_EXPIRED_DAYS", 0)%>);
					Cookies.set("checked",ff.rem.checked,expires);
					Cookies.set("loginId",ff.loginId.value,expires);
					Cookies.set("password",ff.password.value,expires);
				}else{
					Cookies.clear("loginId");
					Cookies.clear("password");
					Cookies.clear("checked");
				}
			  	$.ajax({
					url: "${ctx}/ajaxLogin.action",
					type: "post",
					dataType: "json",
					data: {
						loginId: top.$("#loginId").val(),
						password: top.$("#password").val()
					},
					success: function(data){
						if(data.result){
							location.href = "${ctx}/index.action";
						}else{
							document.getElementById('loginMessage').innerHTML = data.error;
						}
					}
				});
			}
			
			
			function sousuo(){
			var search=$("#search").val();
			if(search==""){
			   alert("请输出搜索条件");
			}else{
			     window.open("${ctx}/wzxx/sousuoList.action?pageNum=1"+"&title="+search);
			}
			
			    
			}
			
	function openYqcg()
	{
		var flag = "${flag}";
		if(flag == '1')
		{
			window.open("http://szcg.sipac.gov.cn");
		}
		else
		{
			alert("此平台需要在政务内网中才能使用！");
		}
	}
</script>

</head>

<body>
<div class="wrapper">

<!--header-->
	<div class="header">
    	<div class="logo fleft">
          <a href="#"><img src="${ctx}/webResources/wzCss/images/logo.png"/></a>
        </div>
<div class="myclear"></div>
    </div>
<!--/header end-->

<!--nav-->
    <div id="navBg">
        <div class="nav">
             <ul>
                <li><a class="navnow" href="${ctx}/wzxx/index.action" >首页</a></li>
                <li><a href="${ctx}/wzxx/awhgzList.action?wzInfoManage.infoType=6">组织机构</a></li>
                <li><a href="${ctx}/wzxx/gzdtList.action?pageNum=1">工作动态</a></li>
                <li><a href="${ctx}/wzxx/newsList.action?pageNum=1">通知公告</a></li>
                <li><a href="${ctx}/wzxx/awhgzList.action?wzInfoManage.infoType=7">信息公开</a></li>
                <li><a href="${ctx}/wzxx/zhzfWzList.action">综合执法</a></li>
                <li><a href="${ctx}/wzxx/csglWzList.action">城市管理</a></li>                 
                <li><a href="${ctx}/wzxx/aqwhList.action">安全生产</a></li>
                <li><a href="${ctx}/wzxx/jgdlWzList.action">机关党建</a></li>
                <li><a href="${ctx}/wzxx/flfgList.action">法律法规</a></li>             
                 <li><a href="${ctx}/wzxx/lxwmList.action">联系我们</a></li>        
          </ul>
        </div>	
    </div>
<!--/nav end-->	
<!--/weather-->	
    <div class="weather">
        <div class="wea_lf fleft">
            <div class="date" id="ShowTime">2015年12月15日 星期二</div>
            <div class="weather-info">
                <span class="label">天气预报</span>
                <iframe src="http://webvote.sipac.gov.cn/weather.html" allowtransparency="true" frameborder="0" scrolling="no"></iframe>
            </div>
        </div>
        <div class="sea_rh fright">
        	<div class="search_box">
   	        	<p>站内搜索：</p>        
        		<div class="search radius4px">
                    <input id="search" name="" type="text" value="" />
                    <input name="" value="搜索" type="button" onclick="sousuo();" class="btn_01">
            	</div>
             </div>        
        </div>
        <div class="myclear"></div>
    </div>
<!--/weather end-->	


<!--wrap_01-->
    <div class="wrap_01">
	    <!--lfpns-->
	    <div class="lfpns fleft theme-default">
    		<div id="slider" class="nivoSlider">
    			<c:forEach var="sytp" items="${sytpList}">
                	<img src="${sytp.filePath}"  title="${sytp.infoTitle}" />
				</c:forEach>  
    		</div>
    	</div>
        <!--/lfpns end-->             
        <!--hot_news-->
        <div class="news fleft">
            <div class="newstitle">
                <div class="hot">
                    <ul>
                        <li><a class="now" href="#">工作动态</a></li>                        
                    </ul>
                </div>
                <div class="more"><a href="${ctx}/wzxx/gzdtList.action?pageNum=1">更多>></a></div>
                <div class="myclear"></div>
                <div class="TabContent">
                    
                      <c:forEach var="gzdt" items="${gzdtList}">
                		<div class="list">
                        	<p class="listtitle"><a href="${ctx}/wzxx/gzdtContent.action?gzdt.id=${gzdt.id}">${gzdt.infoTitle}</a></p>
                       	 	                      	
                    	</div>
					</c:forEach>                                  	
                  
                                               
                </div> 
            </div>
        </div>
                <div class="news fright">
            <div class="newstitle">
                <div class="hot">
                    <ul>
                        <li><a class="now" href="#">通知公告</a></li>                        
                    </ul>
                </div>
                <div class="more"><a href="${ctx}/wzxx/newsList.action?pageNum=1">更多>></a></div>
                <div class="myclear"></div>
                <div class="TabContent">
                    
                         <c:forEach var="tzgg" items="${tzggList}">
                		<div class="list">
                        	<p class="listtitle"><a href="${ctx}/wzxx/newsContent.action?tzgg.id=${tzgg.id}">${tzgg.infoTitle}</a></p>
                       	 	                    	
                    	</div>
					</c:forEach>                                                     	
                   
                                  
                </div> 
            </div>
        </div>
        <!--/hot_news end-->        
    </div>
    <div class="myclear"></div>
<!--/wrap_01 end-->	
    
<!--/main-->
	<div class="main">
    	<!--/main_left-->
    	<div class="main_left fleft">
          <div class="juli">
            <div class="bgxz">
                <img src="${ctx}/webResources/wzCss/images/biaoge.png">
                <a href="#">表格下载</a>
            </div>            
           <div class="bgxz2">                                
                 <a href="${ctx}/wzxx/bgxzList.action?pageNum=1&bgxz.bgzl=1"><img src="${ctx}/webResources/wzCss/images/icon_03.png"/>城市管理</a>                              
                 <a href="${ctx}/wzxx/bgxzList.action?pageNum=1&bgxz.bgzl=2"><img src="${ctx}/webResources/wzCss/images/icon_03.png"/>安全生产</a>
                 <a href="${ctx}/wzxx/bgxzList.action?pageNum=1&bgxz.bgzl=3"><img src="${ctx}/webResources/wzCss/images/icon_03.png"/>行政执法</a>
                 <a href="${ctx}/wzxx/bgxzList.action?pageNum=1&bgxz.bgzl=4"><img src="${ctx}/webResources/wzCss/images/icon_03.png"/>其他</a>           
                    
               
              
         </div>            
         <div class="juli"> 
             <div class="bgxz">
                <img src="${ctx}/webResources/wzCss/images/xiashu.png">                
                <a href="#">下属单位</a>
            </div>            
           <div class="bgxz3">                
                <a href="${ctx}/wzxx/xsdw.action?flag=cgdd"><img src="${ctx}/webResources/wzCss/images/icon_03.png">城市管理综合执法大队</a>                             
                <a href="${ctx}/wzxx/xsdw.action?flag=ajdd"><img src="${ctx}/webResources/wzCss/images/icon_03.png">安全生产监察大队</a>   
                <a href="${ctx}/wzxx/xsdw.action?flag=jcdd"><img src="${ctx}/webResources/wzCss/images/icon_03.png">基层综合执法大队</a>     
          </div>
        </div>          
         <div class="juli"> 
           <div class="bgxz">
                <img src="${ctx}/webResources/wzCss/images/zhengwu.png">
                <a href="#">公众参与</a>
            </div>            
           <div class="bgxz3">                              
                <a href="http://gzjd.sipac.gov.cn/Web/BBS/MainList.aspx" target="_blank"><img src="${ctx}/webResources/wzCss/images/icon_03.png">公众监督</a>
                <a href="http://zch.sipac.gov.cn/login/login.jsp" target="_blank"><img src="${ctx}/webResources/wzCss/images/icon_03.png">市容环境问题上报</a>
                <a href="http://gzjd.sipac.gov.cn/Web/BBS/findout.aspx" target="_blank"><img src="${ctx}/webResources/wzCss/images/icon_03.png">安全生产事故隐患举报</a>      
          </div>                                                  
       </div>
      </div>
     </div>
       
        <!--/main_left end-->
        
        <!--/main_center-->        
        <div class="main_center fleft">
        	<div class="section fleft">
            	<div class="title"><h4>政务互动</h4><a href="${ctx}/wzxx/awhgzList.action?wzInfoManage.infoType=2" class="more"></a></div>
                <div class="myclear"></div>                
                <div class="section_box">
                	<ul>
                    	<c:forEach items="${zwxxList }" var="map">
                    		<c:if test="${fn:length(map.infotitle)>20 }">
	    						<li><a href="${ctx}/wzxx/awhgzContent.action?wzInfoManage.id=${map.row_id }"+ >${fn:substring(map.infotitle, 0, 20)}...</a></li>
                    		</c:if>
                    		<c:if test="${fn:length(map.infotitle)<=20 }">
	    						<li><a href="${ctx}/wzxx/awhgzContent.action?wzInfoManage.id=${map.row_id }"+ >${map.infotitle}</a></li>
                    		</c:if>
    					</c:forEach>                       
                    </ul>                            
                </div>
            </div>
            
            <div class="section fleft">
            	<div class="title"><h4>安委会工作</h4><a href="${ctx}/wzxx/awhgzList.action?wzInfoManage.infoType=1" class="more"></a></div>
                <div class="myclear"></div>                
                <div class="section_box">
                	<ul>
                    	<c:forEach items="${awhgzList }" var="map">
    						<c:if test="${fn:length(map.infotitle)>20 }">
	    						<li><a href="${ctx}/wzxx/awhgzContent.action?wzInfoManage.id=${map.row_id }"+ >${fn:substring(map.infotitle, 0, 20)}...</a></li>
                    		</c:if>
                    		<c:if test="${fn:length(map.infotitle)<=20 }">
	    						<li><a href="${ctx}/wzxx/awhgzContent.action?wzInfoManage.id=${map.row_id }"+ >${map.infotitle}</a></li>
                    		</c:if>
    					</c:forEach>                       
                    </ul>                              
                </div>
            </div> 
            
            <div class="section fleft">
            	<div class="title"><h4>专题报道</h4><a href="${ctx}/wzxx/awhgzList.action?wzInfoManage.infoType=3" class="more"></a></div>
                <div class="myclear"></div>                
                <div class="section_box">
                	<ul>
                    	<c:forEach items="${zfjcList }" var="map">
                    		<c:if test="${fn:length(map.infotitle)>20 }">
	    						<li><a href="${ctx}/wzxx/awhgzContent.action?wzInfoManage.id=${map.row_id }"+ >${fn:substring(map.infotitle, 0, 20)}...</a></li>
                    		</c:if>
                    		<c:if test="${fn:length(map.infotitle)<=20 }">
	    						<li><a href="${ctx}/wzxx/awhgzContent.action?wzInfoManage.id=${map.row_id }"+ >${map.infotitle}</a></li>
                    		</c:if>
    					</c:forEach>                       
                    </ul>                              
                </div>
            </div> 
            
            <div class="section fleft">
            	<div class="title"><h4>媒体关注</h4><a href="${ctx}/wzxx/awhgzList.action?wzInfoManage.infoType=4" class="more"></a></div>
                <div class="myclear"></div>                
                <div class="section_box">
                	<ul>
                    	<c:forEach items="${wxhxpList }" var="map">
                    		<c:if test="${fn:length(map.infotitle)>20 }">
	    						<li><a href="${ctx}/wzxx/awhgzContent.action?wzInfoManage.id=${map.row_id }"+ >${fn:substring(map.infotitle, 0, 20)}...</a></li>
                    		</c:if>
                    		<c:if test="${fn:length(map.infotitle)<=20 }">
	    						<li><a href="${ctx}/wzxx/awhgzContent.action?wzInfoManage.id=${map.row_id }"+ >${map.infotitle}</a></li>
                    		</c:if>
    					</c:forEach>                       
                    </ul>                     
                </div>
            </div>                                                    
        </div>
        <!--/main_center end-->
        
        <!--/main_right-->
        <div class="main_right fright">
        	<div class="zxbs">
				<div class="zxbs_title"><p>综合执法局应用系统</p></div> 
                <ul>
                    <li><a href="http://58.210.114.27:9085/cas/login" target="_blank">市安监局信息平台</a></li>
                    <li><a href="http://58.210.186.172:8000/" target="_blank">省局隐患排查平台</a></li>
                    <li><a href="http://www.fwzx.suzhou.gov.cn/" target="_blank">市行政服务中心</a></li>
                    <li><a href="http://221.226.220.82:8008/waoswh/" target="_blank">省危化品监管信息</a></li>
                    <li><a href="http://yzd.chinasafety.gov.cn/" target="_blank">易制毒化学品信息</a></li>
                    <li><a href="http://211.100.47.109/zywsmain/index.asp" target="_blank">职业危害申报</a></li> 
                    <li><a href="http://59.252.192.18:8012/cwreport/" target="_blank">职业卫生统计</a></li> 
                    <li><a href="http://aqbzh.chinasafety.gov.cn/sps/loginaction!initPage.action" target="_blank">工贸企业标准化</a></li> 
                    <li><a href="http://59.252.192.60/jbtj/" target="_blank">举报统计系统</a></li> 
                    <li><a href="http://zftj.chinasafety.gov.cn/bb/xzzfsy.asp" target="_blank">行政执法隐患排查统计系统</a></li> 
                    <li><a href="http://register.szzpzx.com/" target="_blank">安全培训在线报名</a></li>
                </ul>                            
	        </div>
            
            <div class="xzxkgs">
				<div class="xzxkgs_title"><p style="text-indent:4px;">行证许可公示</p><a href="${ctx}/wzxx/awhgzList.action?wzInfoManage.infoType=8" class="zxbs_more">更多>></a></div> 
                 <table width="100%" border="0" align="center">
                  <tr>
                    <td colspan="2"><div class="bgname"><p>类别<span>申报企业</span></p></div></td>
                  </tr>
                  <c:forEach items="${xzxkgsList }" var="map">
                    		<c:if test="${fn:length(map.company_name)>8 }">
                    			 <tr>
				                    <td width="31%">${map.category }</td>
				                    <td width="69%"><a href="${ctx}/wzxx/awhgzContent.action?wzInfoManage.id=${map.row_id }">${fn:substring(map.company_name, 0, 8)}...</a></td>
				                  </tr>
                    		</c:if>
                    		<c:if test="${fn:length(map.company_name)<=8 }">
	    						<tr>
				                    <td width="31%">${map.category }</td>
				                    <td width="69%"><a href="${ctx}/wzxx/awhgzContent.action?wzInfoManage.id=${map.row_id }">${map.company_name}</a></td>
				                  </tr>
                    		</c:if>
    			</c:forEach>    
                </table>        
	        </div>  
                        
            <div class="zxbs">
				<div class="zxbs_title1"><p style="text-indent:18px;">办事流程</p><a href="${ctx}/wzxx/awhgzList.action?wzInfoManage.infoType=9" class="zxbs_more">更多>></a></div> 
                <ul>
	                <c:forEach items="${bslcList }" var="map">
	                    <c:if test="${fn:length(map.infotitle)>13 }">
	                   		<li><a href="${ctx}/wzxx/awhgzContent.action?wzInfoManage.id=${map.row_id }">${fn:substring(map.infotitle, 0, 13)}...</a></li>
	                    </c:if>
	                    <c:if test="${fn:length(map.infotitle)<=13 }">
		    				<li><a href="${ctx}/wzxx/awhgzContent.action?wzInfoManage.id=${map.row_id }">${map.infotitle}</a></li>
	                    </c:if>
	    			</c:forEach>  
                </ul>                          
	        </div>       
            
         
     
        <!--/main_right end-->        
    </div>
    <div class="myclear"></div>
<!--/main end-->

<!--/banner-->
    <div class="banner">
    	<a href="#" onclick="openYqcg()"><img src="${ctx}/webResources/wzCss/images/ad_01.png"/></a>
        <a href="http://zhzf.sipac.gov.cn/yqzhaj" target="_blank"><img src="${ctx}/webResources/wzCss/images/ad_02.png"/></a>
        <a href="${ctx}/wzxx/search.action" target="_blank"><img src="${ctx}/webResources/wzCss/images/ad_03.png"/></a>
        <a style="margin-right:0" href="http://credit.sipac.gov.cn/ghtml/index.html" target="_blank"><img src="${ctx}/webResources/wzCss/images/ad_04.png"/></a>    
    </div>
<!--/banner end-->

<!--/links-->
    <div class="friend_link">友情链接</div>
    <table class="smallbox">
      <tr>       
          <td><a href="http://stat.sipac.gov.cn/page/default.aspx" target="_blank">统计办</a></td> 
          <td><a href="http://www.dpchina.com/dpchina" target="_blank">规划建设委员会</a></td>
          <td><a href="http://gtfc.sipac.gov.cn" target="_blank">国土房产局</a></td>
          <td><a href="http://www.siplss.gov.cn/publish/index.html" target="_blank">劳动和社会保障局</a></td>
          <td><a href="http://sipedu.sipac.gov.cn/website/Category_319/Index.aspx" target="_blank">教育局</a></td>
          <td><a href="http://lw.sipac.gov.cn" target="_blank">经工委、监察局</a></td>              
      </tr>  
      
      <tr>       
          <td><a href="http://price.sipac.gov.cn" target="_blank">园区物价</a></td> 
          <td><a href="http://epb.sipac.gov.cn" target="_blank">环境保护局</a></td>
          <td><a href="http://zfw.sipac.gov.cn/Page/Default.aspx" target="_blank">政法委员会</a></td>
          <td><a href="http://www.sipholdings.gov.cn" target="_blank">国有资产监督理办公室</a></td>
          <td><a href="http://iftz.sipac.gov.cn" target="_blank">国际商务区</a></td>            
      </tr>            
    </table>
    

<!--/bottom-->
    <div class="bottom">
        <span>Copyright ©2015</span>
        <span>苏州工业园区综合行政执法局 版权所有.</span>
        <span>ALL RIGHTS RESERVED.</span>
        <p><span>苏ICP备09024432</span><span><a href="#">技术支持：南京拓构软件有限公司</a></span></p>
    </div>
<!--/bottom end-->

	
</div>
<script>
/*
*author锛歝yj
*product锛氬弸鎯呴摼鎺ユ晥鏋�
*date锛�2014-05-04
*/
//鍙嬫儏閾炬帴
  var menu = ['menu_1','menu_2','menu_3','menu_4'];
  var arra = ['div_1','div_2','div_3','div_4'];
  function show_div(id){
	  if(id==1){
		  for(var i = 1; i < arra.length; i++){
			  document.getElementById(arra[i]).style.display = 'none';
			  document.getElementById(menu[i]).className = 'yqlj';
		  }
	  }else{
		  var classname = document.getElementById('menu_' + id).className;
		  for(var i = 1; i < arra.length; i++){
			  document.getElementById(arra[i]).style.display = 'none';
			  document.getElementById(menu[i]).className = 'yqlj';
		  }
		  if(classname=="yqlj"){
			  document.getElementById('div_' + id).style.display = '';
			  document.getElementById('menu_' + id).className = 'yqlj2';
		  }
	  }
  }
</script>
</body>
</html>

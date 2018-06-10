<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>苏州工业园区综合行政执法局</title>
<link href="${ctx}/webResources/wzCss/css/index.css" rel="stylesheet" type="text/css">
<script src="${ctx}/webResources/js/bgt/jquery-1.10.1.min.js"></script>
</head>
<style>
/*base*/
ul,
li {
	margin: 0;
	padding: 0;
	list-style: none;
}
.clearfix:before,
.clearfix:after {
	content: " ";
	display: table;
}
.clearfix:after {
	clear: both;
}
.clearfix {
 *zoom: 1;
}
/*main*/
.imgContainer {
	width: 745px;
	margin: 20px auto;
	padding: 10px;
	box-shadow: 1px 0 6px 0 rgba(180, 180, 180, 0.4);
}
.imgUl {
	position: relative;
	width:2500px;
}
.box {
	width: 684px;
	margin-left: 30px;
	overflow: hidden;
}
.detailImg,
.smallImg {
	position: relative;
}
/*detailImg*/
#detailImg-box {
	min-height: 200px;
}
.detailImg {
	text-align: center;
}
.detailImg img {
	max-width: 684px;
}
.detailImg p {
	text-align: left;
	color: #999;
}
#detailImg-pre,
#detailImg-next {
	position: absolute;
	width: 30px;
	height: 46px;
	top: 50%;
	background: url(${ctx}/webResources/images/page_button.gif) 0 0 no-repeat;
	cursor: pointer;
	text-indent:-9999px;
}
#detailImg-pre {
	left: 0;
	background-position: -35px 0;
}
#detailImg-next {
	right: 0;
	background-position: -99px 0;
}
#detailImg-pre:hover {
	background-position: -35px -47px;
}
#detailImg-next:hover {
	background-position: -99px -47px;
}
/*smallImg*/
#smallImg-box {
	height: 92px;
}
.smallImg li {
	float: left;
	width: 76px;
}
.smallImg li a {
	display: block;
	width: 70px;
	height: 70px;
	border: 3px solid #fff;
	overflow: hidden;
	vertical-align: middle;
	margin: 5px auto;
}
.smallImg img {
	width: 70px;
}
.smallImg li a:hover,
.smallImg .cur a {
	border-color: #F45E5E;
}
#smallImg-pre,
#smallImg-next {
	position: absolute;
	top: 0;
	height: 92px;
	width: 28px;
	margin: 0;
	background: url(${ctx}/webResources/images/album-arrow.png) 0 0 no-repeat;
	cursor: pointer;
}
#smallImg-pre {
	left: 0;
}
#smallImg-next {
	right: 0;
	background-position: 0 -94px;
}
#smallImg-pre:hover {
	background-position: -28px 0;
}
#smallImg-next:hover {
	background-position: -28px -94px;
}
</style>

<script>
     $(function(){
     
    
     
     
    
     
     
	var i=0,//大图编号
		len=img.length,//img数组的长度
		cur=0;//当前图片编号
		j=6,//默认显示小图个数
		page=0,//小图的页码
		$s_next=$('#smallImg-next'),//小图下一页
		$s_pre=$('#smallImg-pre'),//小图上一页
		box=$('#smallImg-box').width(),//显示的长度
		$ul=$('#smallImg-ul'),//小图外层
		$imgLi=$ul.find('li'),//小图li
		html=_html='';//存放载入的代码		
	$('#detailImg-box').append('<a href=\"'+img[0].href+'\" class=\"detailImg_1\"><img alt=\"'+img[0].alt+'\" src=\"'+img[i].src+'\" width="800px" height="600px" ></a><p>'+img[i].title+'</p>');
	//大图	
	$('#detailImg-next').click(function(){
		++i;
		detailImg_click($s_next,i,len);
	})
	$('#detailImg-pre').click(function(){
		--i;
		detailImg_click($s_pre,i,len);
	})
	//小图
	for(var k=0;k<j;k++){
		var _k=k%len;
		s_html(_k);
		html+=h;
	}
	$ul.append(html);
	$('.smallImg_1').addClass('cur');	
	//小图下一页
	$('#smallImg-next').click(function(){
		if(!$ul.is(':animated')){
			page++;
			var a=page*j,_a,c;
			for(var k=0;k<j;k++,a++){
				smallImg_click(a,_a,len,i);
				_html+=h;
			}
			$ul.append(_html);
			$ul.css({'left':0,'right':'auto'});
			$ul.animate({left:-box},1600,function(){
				$ul.find('li:lt('+j+')').detach();
				$ul.css('left',0);
				_html='';
			});//动画执行后,再删除前9个li,将left设回0
			$('#smallImg-ul li').click(function(){//三处一样，不知道这个要怎么优化？？？
				var _this=$(this);
				i=_this.attr('class').replace(/[^0-9]/ig,'')-1;
				img_info(i);
				s_a_r(_this,'cur');
				cur=i;
			})
		}
	})
	//小图上一页
	$('#smallImg-pre').click(function(){
		if(!$ul.is(':animated')){
			page--;
			var a=(page-1)*j,_a,c;
			for(var k=0;k<j;k++,a--){
				smallImg_click(a,_a,len,i);
				_html=h+_html;
			}
			$ul.prepend(_html).css({'right':box,'left':'auto'});
			$ul.animate({right:0},1600,function(){
				$ul.find('li:gt('+(j-1)+')').detach();//删除后9个li,从8开始
				_html='';
			});
			$('#smallImg-ul li').click(function(){
				var _this=$(this);
				i=_this.attr('class').replace(/[^0-9]/ig,'')-1;
				img_info(i);
				s_a_r(_this,'cur');
				cur=i;
			})
		}
			
	})
	//点击小图
	$('#smallImg-ul li').click(function(){
		var _this=$(this);
		i=_this.attr('class').replace(/[^0-9]/ig,'')-1;
		img_info(i);
		s_a_r(_this,'cur');
		cur=i;
	})
})

 /*----自定义函数-----------*/
 var img=eval('(' +'${aabb}' + ')');
//大图图片信息
function img_info(i){
	var href=img[i].href,
		alt=img[i].alt,
		src=img[i].src,
		title=img[i].title,
		$main=$('#detailImg-box');
	$main.find('a').attr({'href':href,'class':'detailImg_'+(i+1)});
	$main.find('img').attr({'alt':alt,'src':src});
	$main.find('p').text(title);
}
function s_a_r(o,c){
	o.addClass(c).siblings().removeClass(c);	
}
//大图左右点击
function i_cur(i,len){
	i=i%len;
	if(i<0){
		i=len+i;
	}
	return i;	
}
function detailImg_click($pn,i,len){
	i_cur(i,len);
	img_info(i);
	var imgCur=$('.smallImg_'+(i+1));
	if(!imgCur.html()){
		$pn.click();
	} 
	s_a_r($('.smallImg_'+(i+1)),'cur');//小图选中
}
//小图左右点击
function smallImg_click(a,_a,len,i){
	_a=a;
	_a=a%len;
	if(_a<0){
		_a+=len;
	}
	c=_a==i?'cur':'';
	s_html(_a,c);
}
function s_html(_a,c){
	return h='<li class=\"smallImg_'+(_a+1)+' '+c+'\"><a><img alt=\"'+img[_a].alt+'\" src=\"'+img[_a].smallSrc+'\"  width="200px" height="100px"></a></li>';
}
</script>
<body>
<div class="wrapper">
<!--header-->
	<div class="header">
    	<div class="logo fleft"><a href="#"><img src="${ctx}/webResources/wzCss/images/logo.png"/></a></div>
        <div class="erweima fright"><img src="${ctx}/webResources/wzCss/images/Android.png"/><a href="#">安卓</a></div>
        <div class="myclear"></div>
    </div>
<!--/header end-->

<!--nav-->
    <div id="navBg">
        <div class="nav">
             <ul>
                <li><a href="${ctx}/wzxx/index.action" >首页</a></li>
                <li><a href="${ctx}/wzxx/awhgzList.action?wzInfoManage.infoType=6">组织机构</a></li>
                <li><a href="${ctx}/wzxx/gzdtList.action?pageNum=1">工作动态</a></li>
                <li><a href="${ctx}/wzxx/newsList.action?pageNum=1">通知公告</a></li>
                <li><a href="${ctx}/wzxx/awhgzList.action?wzInfoManage.infoType=7">专项整治</a></li>
                <li><a href="${ctx}/wzxx/aqwhList.action">安全文化</a></li>
                <li><a href="${ctx}/wzxx/flfgList.action">法律规章</a></li>
                <li><a href="${ctx}/wzxx/awhgzList.action?wzInfoManage.infoType=2">政务信息</a></li>
                <li><a href="${ctx}/wzxx/lxwmList.action">联系我们</a></li>        
          </ul>
        </div>	
    </div>
<!--/nav end-->	
<div class="main">
	<div class="location"><p>您现在的位置：<a href="${ctx}/wzxx/index.action">首页</a>&gt;<a href="${ctx}/wzxx/bgtList.action">曝光台</a>&gt;<a href="#">${lighthouse.title}</a></p></div>
    <div class="newscon">
           <div class="imgContainer">
	<!--大图-->
	<div class="detailImg" > 
		<a id="detailImg-pre">&lt;</a>
		<div id="detailImg-box" class="box"> </div>
		<a id="detailImg-next">&gt;</a> 
	</div>
	<!--小图-->
	<div class="smallImg"> 
		<a id="smallImg-pre"></a>
		<div id="smallImg-box" class="box">
			<ul id="smallImg-ul" class="imgUl">
			</ul>
		</div>
		<a id="smallImg-next"></a> 
	</div>
</div>                           
</div>


<!--/bottom-->
<div class="bottom">
    <span>Copyright ©2015</span>
    <span>苏州工业园区综合行政执法局 版权所有.</span>
    <span>ALL RIGHTS RESERVED.</span>
    <p><span>苏ICP备09024432</span><span><a href="#">技术支持：南京拓构软件有限公司</a></span></p>
</div>
<!--/bottom end-->

	
</div>

</body>
</html>



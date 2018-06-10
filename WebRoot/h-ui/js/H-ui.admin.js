/* -----------H-ui前端框架-------------
* H-ui.admin.js v2.3.1
* http://www.h-ui.net/
* Created & Modified by guojunhui
* Date modified 15:42 2015.08.19
*
* Copyright 2013-2015 北京颖杰联创科技有限公司 All rights reserved.
* Licensed under MIT license.
* http://opensource.org/licenses/MIT
*
*/
var num=0,oUl=$("#min_title_list"),hide_nav=$("#Hui-tabNav");

/*获取顶部选项卡总长度*/
function tabNavallwidth(){
	var taballwidth=0,
		$tabNav = hide_nav.find(".acrossTab"),
		$tabNavWp = hide_nav.find(".Hui-tabNav-wp"),
		$tabNavitem = hide_nav.find(".acrossTab li"),
		$tabNavmore =hide_nav.find(".Hui-tabNav-more");
	if (!$tabNav[0]){return}
	$tabNavitem.each(function(index, element) {
        taballwidth+=Number(parseFloat($(this).width()+60))
    });
	$tabNav.width(taballwidth+25);
	var w = $tabNavWp.width();
	if(taballwidth+25>w){
		$tabNavmore.show()}
	else{
		$tabNavmore.hide();
		$tabNav.css({left:0})
	}
}

/*左侧菜单响应式*/
function Huiasidedisplay(){
	if($(window).width()>=768){
		$(".Hui-aside").show()
	} 
}
/*刷新父页面*/	
function refreshParent(){
	var frameIndex =  $("#min_title_list .active", top.document).index(); 
	var targetFrame =  $(".show_iframe", top.document).eq(frameIndex).find("iframe");
	if(targetFrame){
	targetFrame[0].contentWindow.refresh();
	}
}

function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}

function getskincookie(){
	var v = getCookie("Huiskin");
	if(v==null||v==""){
		v="blue";
	}
	$("#skin").attr("href",getRootPath()+"/h-ui/skin/"+v+"/skin.css");
}
function Hui_admin_tab(obj){
	if($(obj).attr('_href')&&$(obj).attr('_href')!=""){
		var bStop=false;
		var bStopIndex=0;
		var _href=$(obj).attr('_href');
		var _titleName=$(obj).attr("data-title");
		var topWindow=$(window.parent.document);
		var show_navLi=topWindow.find("#min_title_list li");
		show_navLi.each(function() {
			if($(this).find('span').attr("data-href")==_href){
				bStop=true;
				bStopIndex=show_navLi.index($(this));
				return false;
			}
		});
		if(!bStop){
			creatIframe(_href,_titleName);
			min_titleList();
			
		}
		else{
			show_navLi.removeClass("active").eq(bStopIndex).addClass("active");
			var iframe_box=topWindow.find("#iframe_box");
			iframe_box.find(".show_iframe").hide().eq(bStopIndex).show().find("iframe").attr("src",_href);
		}
	}

}
function min_titleList(){
	var topWindow=$(window.parent.document);
	var show_nav=topWindow.find("#min_title_list");
	var aLi=show_nav.find("li");
};
function creatIframe(href,titleName){
	var topWindow=$(window.parent.document);
	var show_nav=topWindow.find('#min_title_list');
	show_nav.find('li').removeClass("active");
	var iframe_box=topWindow.find('#iframe_box');
	show_nav.append('<li class="active"><span data-href="'+href+'">'+titleName+'</span><i></i><em></em></li>');
	var taballwidth=0,
		$tabNav = topWindow.find(".acrossTab"),
		$tabNavWp = topWindow.find(".Hui-tabNav-wp"),
		$tabNavitem = topWindow.find(".acrossTab li"),
		$tabNavmore =topWindow.find(".Hui-tabNav-more");
	if (!$tabNav[0]){return}
	$tabNavitem.each(function(index, element) {
        taballwidth+=Number(parseFloat($(this).width()+60))
    });
	$tabNav.width(taballwidth+25);
	var w = $tabNavWp.width();
	if(taballwidth+25>w){
		$tabNavmore.show()}
	else{
		$tabNavmore.hide();
		$tabNav.css({left:0})
	}
	var iframeBox=iframe_box.find('.show_iframe');
	iframeBox.hide();
	iframe_box.append('<div class="show_iframe"><div class="loading"></div><iframe frameborder="0" src='+href+'></iframe></div>');
	var showBox=iframe_box.find('.show_iframe:visible');
	showBox.find('iframe').load(function(){
		showBox.find('.loading').hide();
	});
}
function removeIframe(){
	var topWindow = $(window.parent.document);
	var iframe = topWindow.find('#iframe_box .show_iframe');
	var tab = topWindow.find(".acrossTab li");
	var showTab = topWindow.find(".acrossTab li.active");
	var showBox=topWindow.find('.show_iframe:visible');
	var i = showTab.index();
	tab.eq(i-1).addClass("active");
	iframe.eq(i-1).show();
	tab.eq(i).remove();
	iframe.eq(i).remove();
}
/*弹出层*/
/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
function layer_show(title,url,w,h){
	if (title == null || title == '') {
		title=false;
	};
	if (url == null || url == '') {
		url="404.html";
	};
	if (w == null || w == '') {
		w=800;
	};
	if (h == null || h == '') {
		//h=($(window).height() - 50);
		h = 600;
	};
	layer.open({
		type: 2,
		area: [w+'px', h +'px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: title,
		content: url
	});
}
/*关闭弹出框口*/
function layer_close(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}
/*刷新Select2状态*/
function select2Refresh(){
	$("select").trigger("change.select2");
}

/*layer第三方皮肤变更*/
function layerConfig(){
	layer.config({
		extend: ['skin/espresso/style.css','extend/layer.ext.js'], //加载新皮肤
		skin: 'layer-ext-espresso' //一旦设定，所有弹层风格都采用此主题。
	});
}


$(function(){
	getskincookie();
	 layerConfig();
	Huiasidedisplay();
	var resizeID;
	$(window).resize(function(){
		clearTimeout(resizeID);
		resizeID = setTimeout(function(){
			Huiasidedisplay();
		},500);
	});
	
	$(".Hui-nav-toggle").click(function(){
		$(".Hui-aside").slideToggle();
	});
	$(".Hui-aside").on("click",".menu_dropdown dd li a",function(){
		if($(window).width()<768){
			$(".Hui-aside").slideToggle();
		}
	});
	
	$(document).on("click","#min_title_list li",function(){
		var bStopIndex=$(this).index();
		var iframe_box=$("#iframe_box");
		$("#min_title_list li").removeClass("active").eq(bStopIndex).addClass("active");
		iframe_box.find(".show_iframe").hide().eq(bStopIndex).show();
	});
	$(document).on("click","#min_title_list li i",function(){
		var aCloseIndex=$(this).parents("li").index();
		$(this).parent().remove();
		$('#iframe_box').find('.show_iframe').eq(aCloseIndex).remove();	
		num==0?num=0:num--;
		tabNavallwidth();
	});
	$(document).on("dblclick","#min_title_list li",function(){
		var aCloseIndex=$(this).index();
		var iframe_box=$("#iframe_box");
		if(aCloseIndex>0){
			$(this).remove();
			$('#iframe_box').find('.show_iframe').eq(aCloseIndex).remove();	
			num==0?num=0:num--;
			$("#min_title_list li").removeClass("active").eq(aCloseIndex-1).addClass("active");
			iframe_box.find(".show_iframe").hide().eq(aCloseIndex-1).show();
			tabNavallwidth();
		}else{
			return false;
		}
	});
	tabNavallwidth();
	
	$('#js-tabNav-next').click(function(){
		num==oUl.find('li').length-1?num=oUl.find('li').length-1:num++;
		toNavPos();
	});
	$('#js-tabNav-prev').click(function(){
		num==0?num=0:num--;
		toNavPos();
	});
	
	function toNavPos(){
		oUl.stop().animate({'left':-num*100},100);
	}
	
	/*换肤*/
	$("#Hui-skin .dropDown-menu a").click(function(){
		var v = $(this).attr("data-val");
		setCookie("Huiskin", v);
		$("#skin").attr("href",getRootPath()+"/h-ui/skin/"+v+"/skin.css");
		$(window.frames.document).contents().find("#skin").attr("href",getRootPath()+"/h-ui/skin/"+v+"/skin.css");
	});
	
	/***
	 * 
	 * 自定义区域
	 * 
	 * ****/
	/*设置全局图片查看器  PS:依赖于 viewer.min.js和 viewer.min.css*/
	try{
		//$('body').viewer();
	}catch(e){
		console.warn("当前页面图片查看器加载失败，"+e.message );
	}
	/*设置全局图片懒加载  PS:依赖于 jquery.lazyload.js ,图片必须设置长宽*/
	try{
		$('img.lazy').lazyload();
	}catch(e){
		console.warn("当前页面懒加载图片失败，"+e.message );
	}
	
	/*设置全局下拉选项初始化  PS:依赖于 select2.min.js 和 select2.min.css*/
	try{
		$('.mySelect').select2({
			  minimumResultsForSearch: Infinity  //关闭搜索栏
		});
	}catch(e){
		console.warn("当前页面Select2下拉框失败，"+e.message );
	}
	/*设置全局radio初始化  PS:依赖于 jquery.icheck.min.js 和 icheck.css*/
	try{
		$('.radio-box input').iCheck({
			radioClass: 'iradio-blue',
			increaseArea: '20%'
		});
	}catch(e){
		console.warn("当前页面Select2下拉框失败，"+e.message );
	}
	
}); 

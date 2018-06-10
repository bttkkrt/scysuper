function get_menuH()
{
	var H =$(document).height(),
	menuTop = $(".sipac_menu").offset().top;
		$('.sipac_menu').css({
			height: H-menuTop
		});
}
function dropdown(){
	$(".sipac_menu li,.sipac_adminbox li").hover(function() {
		$(this).addClass("dropdown_hover");
	},function(){
		$(this).removeClass("dropdown_hover");
});
}

function popup(){
	var W = $(window).width();
	var H = $(window).height();
	var popupW = $(".sipac_popup").width(); 
	var popupH = $(".sipac_popup").height(); 
	$('.sipac_popup').css({
		height:popupH,
		top: (H - popupH)/2,
		left: (W - popupW)/2
	});
}

$(function(){
	$(".frame_Btn .f_more").click(function() {
		$("body").removeClass("sipac_full");
		$(this).addClass("hide");
		$(".frame_Btn .f_less").removeClass("hide");
	});
	$(".frame_Btn .f_less").click(function() {
		$("body").addClass("sipac_full");
		$(this).addClass("hide");
		$(".frame_Btn .f_more").removeClass("hide");
	});
//下拉菜单项
	$(".s_select").chosen({disable_search_threshold: 5});
	$(".sipac_searchBox .chosen-container").width("90%");
//搜索弹出项1
		$(".sipac_searchBox").hide();
		$(".s_s_mBox").click(function() {
			$(".sipac_searchBox").slideDown(200);
			$(".sipac_searchbar").fadeOut(200);
		});
		$(".s_s_close a").click(function() {
			$(".sipac_searchBox").slideUp(200);
			$(".sipac_searchbar").fadeIn(200);
		});
		$(".s_s_mDrop .s_s_morebtn").click(function() {
			$(".s_s_popup,.s_s_lessbtn").show();
			$(".s_s_morebtn").hide();
		});
		$(".s_s_popup .close,.s_s_lessbtn").click(function() {
			$(".s_s_morebtn").show();
			$(".s_s_popup,.s_s_lessbtn").hide();
		});
		
	$('.sipac_searchbar .s_s_text').click(function(){
        var email = $(this).val();
        if(email == "搜索相关信息"){
            $(this).val("");
        }
    }).blur(function(){
        var email = $(this).val();
        if(email == ""){
            $(this).val("搜索相关信息");
        }
    });
		
	dropdown();
	get_menuH();
		
	$(window).resize(function() {
		get_menuH();
	});
	$(window).scroll(function() {
		get_menuH();
	});
});
	



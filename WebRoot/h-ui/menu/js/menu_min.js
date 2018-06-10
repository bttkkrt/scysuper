(function($) {
	var up = "up";
	var down = "down";
    $.fn.menu = function(b) {
        var c,
        item,
        httpAdress;
        b = jQuery.extend({
            Speed: 220,
            autostart: 1,
            autohide: 1
        },
        b);
        c = $(this);
        item = c.children("ul").parent("li").children("a");
        httpAdress = window.location;
        item.addClass(up);
        item.find("i").addClass("Hui-iconfont Hui-iconfont-arrow2-right");
        function _item() {
            var a = $(this);
            if (b.autohide) {
                a.parent().parent().find("."+down).parent("li").children("ul").slideUp(b.Speed / 1.2, 
                function() {
                    $(this).parent("li").children("a").removeAttr("class");
                    $(this).parent("li").children("a").attr("class", up);
                    $(this).parent("li").children("a").find("i").removeAttr("class");
                    $(this).parent("li").children("a").find("i").attr("class", "Hui-iconfont Hui-iconfont-arrow2-right");
                })
            }
            if (a.attr("class") == up) {
                a.parent("li").children("ul").slideDown(b.Speed, 
                function() {
                    a.removeAttr("class");
                    a.addClass(down);
                    a.find("i").removeAttr("class");
                    a.find("i").addClass("Hui-iconfont Hui-iconfont-arrow2-bottom");
                })
            }
            if (a.attr("class") == down) {
                a.find("i").removeAttr("class");
                a.removeAttr("class");
                a.find("i").addClass("Hui-iconfont Hui-iconfont-arrow2-right");
                a.addClass(up);
                a.parent("li").children("ul").slideUp(b.Speed)
            }
        }
        item.unbind('click').click(_item);
        if (b.autostart) {
            c.children("a").each(function() {
                if (this.href == httpAdress) {
                    $(this).parent("li").parent("ul").slideDown(b.Speed, 
                    function() {
                        $(this).parent("li").children("."+up).removeAttr("class");
                        $(this).parent("li").children("a").addClass(down);
                        $(this).parent("li").children("i").removeAttr("class");
                        $(this).parent("li").children("a").find("i").addClass("Hui-iconfont Hui-iconfont-arrow2-bottom");
                    })
                }
            })
        }
    }
})(jQuery);
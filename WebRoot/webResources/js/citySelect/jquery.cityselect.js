/*
 Ajax 三级省市联动
 
 settings 参数说明
 -----
 url:省市数据josn文件路径
 prov:默认省份
 city:默认城市
 dist:默认地区（县）
 nodata:无数据状态
 required:必选项
 ------------------------------ */
(function($) {
    $.fn.citySelect = function(settings) {
        if (this.length < 1) {
            return;
        }
        ;
        // 默认值
        settings = $.extend({
            //url: "city.min.js",
            url: "../../jsp/admin/dept/getProvinces.action",
            prov: null,
            city: null,
            dist: null,
            street: null,
            nodata: null,
            required: false
        }, settings);

        var box_obj = this;
        var prov_obj = box_obj.find(".prov");
        var city_obj = box_obj.find(".city");
        var dist_obj = box_obj.find(".dist");
        var street_obj = box_obj.find(".street");
        var prov_val = settings.prov;
        var city_val = settings.city;
        var dist_val = settings.dist;
        var street_val = settings.street;
        var select_prehtml = (settings.required) ? "" : "<option value=''>请选择</option>";
        var city_json;
        var areaId = $("#enterprisePossession").val();
        var temp_area = $("#setAreaName").val();
        var localAreaName = $("#localAreaName").val();

        // 赋值市级函数
        var cityStart = function() {
            var prov_id = prov_obj.get(0).selectedIndex;
            
            if (!settings.required) {
                prov_id--;
            }
            ;
            city_obj.empty().attr("disabled", true);
            dist_obj.empty().attr("disabled", true);
            street_obj.empty().attr("disabled", true);

            if (prov_id < 0 || typeof (city_json) == "undefined") {
                if (settings.nodata == "none") {
                    city_obj.css("display", "none");
                    dist_obj.css("display", "none");
                    street_obj.css("display", "none");
                } else if (settings.nodata == "hidden") {
                    city_obj.css("visibility", "hidden");
                    dist_obj.css("visibility", "hidden");
                    street_obj.css("visibility", "hidden");
                }
                ;
                return;
            }
            ;
            
            // 遍历赋值市级下拉列表
            temp_html = select_prehtml;
            $.each(city_json, function(i, city) {
            	temp_html += "<option rowid='"+city[0]+"' value='" + city[1] + "'>" + city[2] + "</option>";
            });
            city_obj.html(temp_html).attr("disabled", false).css({"display": "", "visibility": ""});
            distStart();
        };

        // 赋值地区（县）函数
        var distStart = function() {
            var prov_id = prov_obj.get(0).selectedIndex;
            var city_id = city_obj.get(0).selectedIndex;
            if (!settings.required) {
                prov_id--;
                city_id--;
            }
            ;
            dist_obj.empty().attr("disabled", true);
            street_obj.empty().attr("disabled", true);

            if (prov_id < 0 || city_id < 0 || typeof (city_json) == "undefined") {
                if (settings.nodata == "none") {
                    dist_obj.css("display", "none");
                    street_obj.css("display", "none");
                } else if (settings.nodata == "hidden") {
                    dist_obj.css("visibility", "hidden");
                    street_obj.css("visibility", "hidden");
                }
                ;
                return;
            }
            ;

            // 遍历赋值市级下拉列表
            temp_html = select_prehtml;

            $.each(city_json, function(i, dist) {
            	temp_html += "<option rowid='"+dist[0]+"' value='" + dist[1] + "'>" + dist[2] + "</option>";
            });
            dist_obj.html(temp_html).attr("disabled", false).css({"display": "", "visibility": ""});
            streetStart();
        };
        
     // 赋值地区（县）函数
        var streetStart = function() {
            var prov_id = prov_obj.get(0).selectedIndex;
            var city_id = city_obj.get(0).selectedIndex;
            var dist_id = dist_obj.get(0).selectedIndex;
            if (!settings.required) {
                prov_id--;
                city_id--;
                dist_id--;
            }
            ;
            street_obj.empty().attr("disabled", true);

            if (prov_id < 0 || city_id < 0 || dist_id < 0 || typeof (city_json) == "undefined") {
                if (settings.nodata == "none") {
                	street_obj.css("display", "none");
                } else if (settings.nodata == "hidden") {
                	street_obj.css("visibility", "hidden");
                }
                ;
                return;
            }
            ;

            // 遍历赋值市级下拉列表
            temp_html = select_prehtml;
            $.each(city_json, function(i, street) {
            	temp_html += "<option rowid='"+street[0]+"' value='" + street[1] + "'>" + street[2] + "</option>";
            });
            street_obj.html(temp_html).attr("disabled", false).css({"display": "", "visibility": ""});
        };

        var init = function() {
            // 遍历赋值省份下拉列表
            temp_html = select_prehtml;
            $.each(city_json, function(i, prov) {
            	temp_html += "<option rowid='"+prov[0]+"' value='" + prov[1] + "'>" + prov[2] + "</option>";
            });
            prov_obj.html(temp_html);
           

            // 若有传入省份与市级的值，则选中。（setTimeout为兼容IE6而设置）
            setTimeout(function() {
                if (settings.prov != null) {
                    prov_obj.val(settings.prov);
                    cityStart();
                    setTimeout(function() {
                        if (settings.city != null) {
                            city_obj.val(settings.city);
                            distStart();
                            setTimeout(function() {
                                if (settings.dist != null) {
                                    dist_obj.val(settings.dist);
                                }
                                ;
                            }, 1);
                        }
                        ;
                    }, 1);
                }
                ;
            }, 1);

            // 选择省份时发生事件
            prov_obj.bind("change", function() {
            	var  val =prov_obj.get(0).options[prov_obj.get(0).selectedIndex].text;
            	$(".city").removeAttr("hidden");
            	var rowid = prov_obj.find("option:selected").attr("rowid");
            	var code = prov_obj.find("option:selected").attr("value");
            	$("#areaId").val(code);
            	settings.url="../../jsp/admin/dept/findChildrenByParentId.action?dept.id="+rowid;
                $.getJSON(settings.url, function(json) {
                	city_json = json;
                	cityStart();
                	if(temp_area &&areaId!="undefined"&&areaId!=""){
                		var cityId = areaId.substring(0,9);
                		$(".city option[value='"+cityId+"']").attr("selected","selected");
                		$(".city").trigger("change");
                	}
                	if(localAreaName){
                		$(".city option[value='012008004']").attr("selected","selected");
                		$(".city").trigger("change");
                		$(".city").attr("disabled", true);
                	}
                });
            });

            // 选择市级时发生事件
            city_obj.bind("change", function() {
            	$(".dist").removeAttr("hidden");
            	var rowid = city_obj.find("option:selected").attr("rowid");
            	var code = city_obj.find("option:selected").attr("value");
            	var codeName = city_obj.find("option:selected").get(0).text;
            	$("#areaId").val(code);
            	$("#areaName").val(codeName);
            	settings.url="../../jsp/admin/dept/findChildrenByParentId.action?dept.id="+rowid;
                $.getJSON(settings.url, function(json) {
                	city_json = json;
                	distStart();
                	if(temp_area &&areaId!="undefined"&&areaId!=""){
                		var distId = areaId.substring(0,12);
                		$(".dist option[value='"+distId+"']").attr("selected","selected");
                		$(".dist").trigger("change");
                	}
                });
            });
            
         // 选择区级时发生事件
            dist_obj.bind("change", function() {
            	$(".street").removeAttr("hidden");
            	var rowid = dist_obj.find("option:selected").attr("rowid");
            	var code = dist_obj.find("option:selected").attr("value");
            	var codeName = dist_obj.find("option:selected").get(0).text;
            	$("#areaId").val(code);
            	$("#areaName").val(codeName);
            	settings.url="../../jsp/admin/dept/findChildrenByParentId.action?dept.id="+rowid;
                $.getJSON(settings.url, function(json) {
                	city_json = json;
                	streetStart();
                	if(temp_area &&areaId!="undefined"&&areaId!=""){
                		var streetId = areaId.substring(0,15);
                		$(".street option[value='"+streetId+"']").attr("selected","selected");
                		$(".street").trigger("change");
                	}
                });
            });
         // 选择街道级时发生事件
            street_obj.bind("change", function() {
            	var code = street_obj.find("option:selected").attr("value");
            	var name = street_obj.find("option:selected").get(0).text;
            	$("#areaId").val(code);
            	$("#areaName").val(name);
            	$("#areaId").attr("areaname",name);
            });
        };
        // 设置省市json数据
        if (typeof (settings.url) == "string") {
            $.getJSON(settings.url, function(json) {
                city_json = json;
                init();
                if(temp_area &&areaId!="undefined"&&areaId!=""){
            		var proviceId = areaId.substring(0,6);
            		var cityId = areaId.substring(0,9);
            		var distId = areaId.substring(0,12);
            		var streetId = areaId.substring(0,15);
            		$(".prov option[value='"+proviceId+"']").attr("selected","selected");
            		$(".prov").trigger("change");
                }
                if(localAreaName){
                	$(".prov option[value='012008']").attr("selected","selected");
            		$(".prov").trigger("change");
            		
                }
            });
        } else {
            city_json = settings.url;
            init();
        }
        ;
    };
})(jQuery);
<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>快捷菜单设置</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
			$(function(){
	             $('#moduleTree').tree({   
	                 checkbox: true,
	                 onlyLeafCheck:true,
	                 url: '${ctx}/jsp/personal/findModuleTreeForQuicklyStart.action', 
                 	 onCheck:function(node,checked){
                     	//设置选中的对象
		    	         var options =document.getElementById("moduleId").options;  
		    	         var id = node.id;
	    	         
		    	     	  if(checked){
		    	               var i,bFlag;
							   for (i=0; i < options.length; i++){
								   if(options[i].value==id){
									   bFlag=true;
									   break;
							       }	
							   }
							   if(!bFlag){
								   options.add(new Option(node.text,id));
								}
		    	           }else {
			    	           var i;
			    	           for (i=0; i < options.length; i++) {
				    	            if(options[i].value==id){
					    	            options[i] = null;
				                        i--;
				                    }	
				                }
		    	           }
		    	           node.checked = checked;  
		    	     	  
		    	     } 
	             });   
	         });
	function changeQuicklyStart(ff){
        document.ff.action = "listQuicklyStart.action";
        document.ff.submit();
    }
    function RemoveSelect(ff){
       	var options =document.getElementById("moduleId").options;  
        var i;
        for(i=0;i<options.length;i++){
        	if(options[i].selected)
	        {
	            options[i]=null;
	            i--;
	        }
        }
    }
  function confirm_onclick(ff){
   	var con = confirm("确定要修改快捷菜单吗？");
      	if(con){
    	var options =document.getElementById("moduleId").options;
    	var moduleIds = "";
    	for(i=0;i<options.length;i++){
    		moduleIds += options[i].value+",";
        }
        document.ff.moduleIds.value = moduleIds;
        document.ff.action = "saveQuicklyStart.action";
        document.ff.submit();
       }
   }
    
	</script>
	</head>
	<body>

		<div class="page_content">
			<div class="popwin_layer_01">
				<div class="popwin_layer_01_left">
					<div class="box_01">
						<div class="caption_01">
							<span class="caption_img"></span> 待选择模块
						</div>
						<div class="inner6px popwin_layer_01_height">
							<ul id="moduleTree">
							</ul>
						</div>
					</div>
				</div>
				<div class="popwin_layer_01_right">
					<form method="post" name="ff">
						<input type="hidden" name="moduleIds">
						<div class="box_01">
							<div class="caption_01">
								<span class="caption_img"></span> 已选择模块
								<div class="right">
									<a href="javascript:RemoveSelect(this.form);"
										class="btn_01_ultra_mini" style="width: 40px"><b></b>×</a>
									<a href="javascript:confirm_onclick(this.form);"
										class="btn_01_ultra_mini" style="width: 40px"><b></b>√</a>
								</div>
							</div>
							<div class="inner6px popwin_layer_01_height">
								<select id="moduleId" class="multipleSelect" multiple
									name="moduleId">
									<c:forEach items="${startList}" var="quicklyStart">
										<option value="${quicklyStart.module.id }">
											${quicklyStart.module.moduleName}
										</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>

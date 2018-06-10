<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>功能点角色设置</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
		
		    function RemoveSelect(ff){
		        ff=document.forms[0];
		        var options = ff.roleIds.options;
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
		    	ff=document.forms[0];
		    	var options = ff.roleIds.options;
		    	var roleCodes = "";
		    	for(i=0;i<options.length;i++){
		    		roleCodes += options[i].value+"|";
		        }
		        $.ajax({
		        	url : "saveFuncUser.action",
		        	type : "post",
		        	datatype : "json",
		        	data : {
		        		"roleCodes" : roleCodes,
		        		"functionPoint.id" : "${functionPoint.id}"
		        	},
		        	success : function(data){
		        		data = eval("(" + data + ")");
		        		if(data.result){
		        			$.messager.alert("成功","保存权限成功", "success", function(){
		            			parent.close_win("edit_func_user");
		            		});
		        		}else{
		        			$.meassager.alert("错误","保存权限失败<br>" + data.error, "error");
		        		}
		        	},
		        	error : function(){
		        		$.meassager.alert("错误","保存权限失败", "error");
		        	}
		        });
		    }
		    $(function(){
	             $('#userTree').tree({   
	                 checkbox: true,
	                 onlyLeafCheck:true,
					 cascadeCheck:false, 
	                 url: '${ctx}/jsp/admin/permission/findUserTreeForFunc.action?functionPoint.id=${functionPoint.id}', 
	                 onBeforeExpand:function(node,param){
	                     $('#userTree').tree('options').url = "${ctx}/jsp/admin/permission/findUserTreeForFunc.action?functionPoint.id=${functionPoint.id}&selDept=" + node.id;
                 	 },
                 	 onCheck:function(node,checked){
                     	//设置选中的对象
		    	         var options = document.getElementById("roleIds").options;  
		    	         var id = node.id;
		    	         if(node.id.indexOf("D")!=-1)
		    	         	return;
		    	     	 else{
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
               		 }
	             });   
	         });
		</script>
	</head>

	<body>
		<div class="page_dialog">
			<div class="inner6px">
				<form method="get">
					<input type="hidden" name="roleCodes">
					<input type="hidden" name="functionPoint.id" value="${functionPoint.id}">
					<div class="popwin_layer_01">
						<div class="popwin_layer_01_left">
							<div class="box_01">
								<div class="caption_01">待选择用户</div>
								<div class="inner6px popwin_layer_01_height">
									<ul id="userTree"></ul>
								</div>
							</div>
						</div>
						<div class="popwin_layer_01_right">
							<div class="box_01">
								<div class="caption_01">已选择用户
									<div class="right">
										<a href="javascript:RemoveSelect(this.form);" class="btn_01_ultra_mini" style="width:40px"><b></b>×</a>
										<a href="javascript:confirm_onclick(this.form);" class="btn_01_ultra_mini" style="width:40px"><b></b>√</a>
									</div>
								</div>
								<div class="inner6px popwin_layer_01_height">
									<select id="roleIds" style="width:100%; height:100%" multiple name="roleIds">
				                        <c:forEach items="${funcRightList}" var="right">
				                            <c:if test="${not empty right.user}">
											<option value="${right.user.id}">${right.user.displayName}</option>
											</c:if>
										</c:forEach>
				                    </select>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>

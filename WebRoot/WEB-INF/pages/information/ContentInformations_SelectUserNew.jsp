<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<title>公告用户选择</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
	    function RemoveSelect(ff){
	        var options = ff.users.options;
	        var i;
	        for(i=0;i<options.length;i++){
	        	if(options[i].selected)
		        {
		            options[i]=null;
		            i--;
		        }
	        }
	    }
	
	    function conflick(){
	        var ff = document.forms[0];
	        ff.action = "saveUserRight.action";
	        ff.submit();
	    }
	    $(function(){
             $('#deptTree').tree({
                 url: '${ctx}/jsp/admin/dept/findChildDept.action',
                 onBeforeExpand:function(node,param){
                     $('#deptTree').tree('options').url = "${ctx}/jsp/admin/dept/findChildDeptByCurrUser.action?selDept=" + node.id;
               	 },
               	 onClick:function(node){
                     $.get("${ctx}/jsp/admin/user/listAllUsersByDept.action",{deptCode:node.id},function(data){
                         $("#sel3").empty();
                         for(var i=0;i<data.length;i++){
                         	$("<option value="+data[i].id+">"+data[i].displayName+"</option>").appendTo($("#sel3"));
                         }
                     },"json");
           		 }
             });   
        });
		function insertOption_task() {
			var sels = $("#sel3 option:selected").clone();
			$.each(sels,function(i, n) {
				var userid = $(n).val();
				var username = $(n).attr("text");
				$("#sel4").append(n);
				/* $.post("saveUserRole.action", 
						{userid:userid},
						function(data, textStatus) {
							if (data != "-1") {
								$(n).val(data);
								$("#sel4").append(n);
							}
						}); */
			});
		}
		function delOption_task() {
		   var sels =$("#sel4 option:selected").clone();
			/* $.each(sels,function (i,n) {
				var userid = $(n).val();
				$.post("delUserRole.action", 
						{userid:userid},
						function(data, textStatus) {
						});
			}); */
			$("#sel4 option:selected").remove();
		}
		function returnCheckedUsers(flowType) {
			var userids="";
			var usernames="";
			
			 var options = document.all["sel4"].options;
			 for (i=0; i < options.length; i++) 
			 {
			        	userids=userids+$.trim(options[i].value)+",";
			        	usernames=usernames+$.trim(options[i].text)+",";
			 }
			 
			if(userids&&""!=userids){
				window.parent.document.getElementById("userIds").value = userids.substring(0,userids.length-1);
				window.parent.document.getElementById("userNames").value = usernames.substring(0,usernames.length-1);
				closeLayer();		
			}else{
				alert("请选择接收人");
			}
		}
	</script>
	</head>

	<body>
		<form method="post">
		<input type="hidden" name="userIds">
		</form>
		<div class="page_dialog">
			<div class="inner6px">
				<div class="popwin_layer_02">
					<div class="btn_area_setc btn_area_bg popwin_layer_02_btnarea" style="z-index:10;">
						<a href="#" class="btn_01" onclick="returnCheckedUsers();" id="confirm">确认<b></b></a>
					</div>
				
					<div class="popwin_layer_02_left">
						<div class="box_01">
							<div class="caption_01">部门</div>
							<div class="inner6px popwin_layer_02_height">
								<ul id="deptTree"></ul>
							</div>
						</div>
					</div>
					<div class="popwin_layer_02_center">
						<div class="box_01">
							<div class="caption_01">待选择用户</div>
							<div class="inner6px popwin_layer_02_height" style="overflow-y:hidden;">
								<select id="sel3" style="height:100%;width:100%;" multiple>
									<c:forEach items="${users}" var="user">
										<option value="${user.id}">${user.displayName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="popwin_layer_02_btn">
						<a href="javascript:insertOption_task();" class="user_adbtn_01"></a>
						<a href="javascript:delOption_task();" class="user_adbtn_02"></a>
					</div>
					<div class="popwin_layer_02_right">
						<div class="box_01">
							<div class="caption_01">已选择用户</div>
							<div class="inner6px popwin_layer_02_height" style="overflow-y:hidden;">
								<select id="sel4" style="height:100%;width:100%;" multiple>
								</select>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>

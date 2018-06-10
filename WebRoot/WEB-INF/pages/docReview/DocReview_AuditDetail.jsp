<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
		<script> 
        //下载附件
        function downloadFile(attachId){
                location.href = "${ctx}/jsp/reportWork/download.action?fileId="+attachId;
        }
        function doAudit(id,taskId,isNext){
        	var optmsg;
        	 
        	var auditStatus=$("input[name='auditStatus']:checked").val(); 

        	if(auditStatus==1){
        		optmsg="确定通过该公文？";
        	}
        	if(auditStatus==2){
        		optmsg="确定不通过该公文？";
        	}
			$.messager.confirm('公文审核', optmsg, function(result){
				if (result){
					var option = 3;
					if(isNext=='2' && auditStatus == 2){  //不继续，并且不通过
						option = 1;
					}
					if(isNext=='1' && auditStatus ==1){  //继续，并且通过
						option = 2;
					} 
					$.ajax({
		            	url : "doAudit.action",
		            	type: 'post',
		                dataType: 'json',
		                async : false,
		                data: {
		                	"docReview.id" : id,
		                	"taskId" : taskId,
		                	"docReview.auditStatus" : option,
		                	"docReview.backReason" :$("#backReason").val(),
		                },
		                error: function(){
		                	$.messager.alert('错误','审核出错！');
		                },
		                success: function(data){
		                    if(data.result){
		                    	$.messager.alert('提示','审核完成！','info',function(){
		                    		parent.reloadDate();
		                    		parent.close_win();
		                    	});
		                    }else{
		                    	$.messager.alert('错误','审核时出错！');
		                    }
		                }
		            });					
				}
			});		
		}
	</script>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post"   >
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="10%">公文标题</th>
					<td colspan="3">${docReview.docTitle}</td>
				</tr>
				<tr>
					<th width="10%">用户名称</th>
					<td colspan="3">${docReview.userName}</td>
				</tr>
				<tr>
					<th width="10%">工作内容</th>
					<td colspan="3">${docReview.docContent}</td>
				</tr>
				<tr>
					<th width="15%">公文内容</th>
					<td colspan="3">${docReview.docContent}</td>
				</tr>
				<tr>
					<th width='10%'>接收人</th>
					<td colspan='3'>${userNames}</td>
				</tr>
				<tr>
					<th width="10%">附件</th>
					<td width="70%">
						<div style="color:green;overflow:auto;height:160px;">
						<table id="more">
							  <c:forEach var="item" items="${picList}">
								<tr id='${item.id}' style="text-align: center">
								   <td width="70%">${item.fileName}</td>
								   <td width="30%"><a href="javascript:downloadFile('${item.id}');">下载</a>&nbsp;&nbsp;
								   </td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>		
				<tr>
				<th width='10%'>审核意见：</th>
				<td colspan='3'><input type="radio" name="auditStatus" checked="checked" onclick="$('.reason').show();$('#backReason').attr('disabled',true);"  value="1">同意<input type="radio" name="auditStatus"  value="2" onclick="$('.reason').hide();$('#backReason').attr('disabled',false);"/>不同意</td>
				</tr>
				<tr >
				<th width='10%'>理由：</th>
				<td colspan='3'><textarea  id="backReason"  disabled="disabled" ></textarea></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" align="center"> 
					    <a href="#" class="easyui-linkbutton reason" onclick="doAudit('${docReview.id}','${taskId}','1')" iconCls="icon-save">确认</a>&nbsp;<a href="#" class="easyui-linkbutton"   onclick="doAudit('${docReview.id}','${taskId}','2')" iconCls="icon-save">确认并中止流程</a>&nbsp;<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="parent.close_win()">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

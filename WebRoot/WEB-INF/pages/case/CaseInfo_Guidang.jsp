<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script type="text/javascript">
	$(function(){
		bgChange();
	 });
	//上移
	function UpMove(obj)
	{
		var v1=$("#"+obj+"_hid").val();//当前的 序号
		if(parseInt(v1)!=1){//不是第一行
			 var onthis = $("#"+obj);
			 var getup = $("#"+obj).prev();
			 var n2 = $(getup).attr('id');//后一个的dotId
			 var v2 = $("#"+n2+"_hid").val();
			 if(/^\d+$/.test(v2)){
			 	$("#"+obj+"_hid").val(v2);//设置自己的序号为前一个的
				$("#"+n2+"_hid").val(v1);//设置自己的序号为前一个的
				$(getup).before(onthis);
			 }else{
			 	$(getup).before(onthis);
			 	onthis = $("#"+obj);
			   getup = $("#"+obj).prev();
			   n2 = $(getup).attr('id');//后一个的dotId
			   v2 = $("#"+n2+"_hid").val();
				 if(/^\d+$/.test(v2)){
				 	$("#"+obj+"_hid").val(v2);//设置自己的序号为前一个的
					$("#"+n2+"_hid").val(v1);//设置自己的序号为前一个的
					$(getup).before(onthis);
				 }
			 }
		}
		bgChange();
	}
	//下移
	function DownMove(obj)
	{
		var v1=$("#"+obj+"_hid").val();//当前的 序号
		if(parseInt(v1)!=parseInt(len)){//不是最后一行
			 var onthis = $("#"+obj);
			 var getdown = $("#"+obj).next();
			 var n2 = $(getdown).attr('id');//后一个的dotId
			 var v2 = $("#"+n2+"_hid").val();
				
			 if(/^\d+$/.test(v2)){
			 	$("#"+obj+"_hid").val(v2);//设置自己的序号为前一个的
				$("#"+n2+"_hid").val(v1);//设置自己的序号为前一个的
				 $(getdown).after(onthis);
			 }else{
			 	 $(getdown).after(onthis);
			 	 onthis = $("#"+obj);
				 getdown = $("#"+obj).next();
				 n2 = $(getdown).attr('id');//后一个的dotId
				 v2 = $("#"+n2+"_hid").val();
					
				 if(/^\d+$/.test(v2)){
				 	$("#"+obj+"_hid").val(v2);//设置自己的序号为前一个的
					$("#"+n2+"_hid").val(v1);//设置自己的序号为前一个的
					 $(getdown).after(onthis);
				 }
			 }
		}
		bgChange();
	}
		function bgChange(){
		  var tables = document.getElementById("sel4");
		    trs = tables.getElementsByTagName("tr");
		    for(var j=1; j<trs.length; j++){
		      if(j%2==1){
		        trs[j].style.background = "#E9F0F7";
		      }else{
		      	trs[j].style.background = "#C8D7E6";
		      }
		    }
		  }
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="caseInfoGuidangSave.action">
		<s:token />
		<input type="hidden" name="caseInfo.id" value="${caseInfo.id}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">归档日期</th>
					<td width="35%"><input name="caseInfo.gdTime" value="<fmt:formatDate type='date' value='${caseInfo.gdTime}' pattern='yyyy-MM-dd'/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" datatype="*1-127"  nullmsg='归档日期不能为空！' sucmsg='归档日期填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">保存期限</th>
					<td width="35%"><input name="caseInfo.bcqx" value="${caseInfo.bcqx}" type="text" datatype="*1-127"  nullmsg='保存期限不能为空！' sucmsg='保存期限填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">文书排序</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<table id="sel4" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<th style="text-align:left">文书名称</th>
								<th style="text-align:left">操作</th>
							</tr>
							<c:forEach var="wsxx" items="${wsList}" varStatus="status">
								<tr id="${wsxx.id}">
								  	<td>${wsxx.instrumentName}</td>
								  	<td>
								  		<input type="hidden" name="instrumentsInfo.id" value="${wsxx.id}">
								  		<input id="${wsxx.id}_hid" value="${status.count}" name="instrumentsInfo.sort" type="hidden">
								  		<a href="javascript:UpMove('${wsxx.id}')">&nbsp;上移</a>
										<a href="javascript:DownMove('${wsxx.id}')">&nbsp;下移</a>
								  	</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01" type="submit" >确认<b></b></a>&nbsp;
						<a href="#" class="btn_01"  onclick="parent.close_win('win_caseInfo');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

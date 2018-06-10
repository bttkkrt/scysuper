<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
	
	Date.prototype.Format = function(fmt)   
{ //author: meizz   
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
} 

		function saveSelText(i,text){
			$("#"+i).val(text);
		}
		function queryQy()
		{
			var szzid = document.getElementById('areaId').value;
			popupCenter("${ctx}/jsp/qyjbxx/queryCompanyList.action?flag=idmcqy&entBaseInfo.enterprisePossession="+szzid, "setCompany", "800", "600", "no", "no", "no", "no", "no","no");
		}
		function clearCompany(){
        	document.getElementById("companyName").value="";
        	document.getElementById("companyId").value="";
        }
        
        $(document).ready(function() {
		  uploadPicOnly("uploadify","${linkId}","troMan","troManRect","fileQueue");
		});
		$(function(){
		zgwcChange();
		$("#money").keyup(function(event){
			var txt = $(this).val();
			var keyCode = event.which;
			if ( txt.length<=0 ) {
				$("#fixval").hide();
			}else{
				//先把非数字的都替换掉，除了数字和. 
        		txt = txt.replace(/[^\d.]/g,""); 
        		//必须保证第一个为数字而不是. 
       	 		txt = txt.replace(/^\./g,""); 
        		//保证只有出现一个.而没有多个. 
        		txt = txt.replace(/\.{2,}/g,"."); 
        		//保证.只出现一次，而不能出现两次以上 
        		txt = txt.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
        		if(txt.length >= 2 && txt.substring(0,1) == "0" && txt.substring(1,2) != ".")
        		{
       				txt = txt.substring(1,txt.length);
       			}
       			$("#money").val(txt);
				txt = formatNum(txt);
				
				$("#fixval").html(txt).show();
			}
			
			
		});
	});
	
	function formatNum(str){
		var newStr = "";
		var count = 0;
		
		if(str.indexOf(".")==-1){
		   for(var i=str.length-1;i>=0;i--){
		 if(count % 3 == 0 && count != 0){
		   newStr = str.charAt(i) + "," + newStr;
		 }else{
		   newStr = str.charAt(i) + newStr;
		 }
		 count++;
		   }
		   str = newStr + ".00"; //自动补小数点后两位
		}
		else
		{
		   for(var i = str.indexOf(".")-1;i>=0;i--){
		 if(count % 3 == 0 && count != 0){
		   newStr = str.charAt(i) + "," + newStr;
		 }else{
		   newStr = str.charAt(i) + newStr; //逐个字符相接起来
		 }
		 count++;
		   }
		   str = newStr + (str + "00").substr((str + "00").indexOf("."),3);
		 }
		 return str;
	}
	
	function zgwcChange(){
	 	var s=$("#zgwc").val();
	 	if(s=='整改完成'){
	 		document.getElementById('zgsj').style.display='';
	 		$("#time").val('');
	 		$("#money").val('');
	 	}else{
	 		document.getElementById('zgsj').style.display='none';
	 		$("#time").val(new Date().format("yyyy-MM-dd"));
	 		$("#money").val(0);
	 	}
	}
	
	function redirect(){
		var reason=prompt('请输入转接原因','');
		if( typeof(reason)=="string"){
			if(reason==""){
				alert("请输入原因！");
			}else{
				$("#ids").val(reason);
				document.myform2.submit();
			}
		}else{
		}
	}
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="troManSaveAJ.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="troMan.id" value="${troMan.id}">
		<input type="hidden" name="troMan.linkId" value="${troMan.linkId}">
		<input type="hidden" name="linkId" value="${linkId}">
			<table>
			<tr>
					<th width="15%">隐患名称</th>
					<td width="35%" >${troMan.troubleName}</td>
					<th width="15%">隐患来源</th>
					<td width="35%" >${troMan.troubleSource}</td>
				</tr>
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${troMan.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${troMan.companyName}</td>
				</tr>
				<tr>
					<th width="15%">上报人员名称</th>
					<td width="35%" >${troMan.userName}</td>
					<th width="15%">上报时间</th>
					<td width="35%" ><fmt:formatDate pattern="yyyy-MM-dd"  type="both" value="${troMan.reportTime}" /></td>
				</tr>
				<tr>
					<th width="15%">隐患地点</th>
					<td width="35%" >${troMan.troubleAdd}</td>
					<th width="15%">处理职能部门名称</th>
					<td width="35%" ><cus:hxlabel   codeSql="select t.DEPT_CODE,t.DEPT_NAME from DEPARTMENT t where t.del_flag = 0 and t.DEPT_CODE='${troMan.dealDeptId}'"  itemValue="${troMan.dealDeptId}" /></td>
				</tr>
				<tr>
					<th width="15%">隐患级别</th>
					<td width="35%" ><cus:hxlabel codeName="隐患级别" itemValue="${troMan.troubleLevel}" /></td>
					<th width="15%">隐患类别</th>
					<td width="35%" ><cus:hxlabel codeName="隐患类别" itemValue="${troMan.troubleSort}" /></td>
				</tr>
				<tr>
					<th width="15%">检查项</th>
					<td width="35%">
						<cus:hxlabel  codeSql="select t.row_id,PATROL_NAME from PAT_MAN t where t.delflag = 0"  itemValue="${troMan.checkItem}" />
					</td>
					<th width="15%">整改期限</th>
					<td width="35%" ><fmt:formatDate  pattern="yyyy-MM-dd" type="both" value="${troMan.rectificationTerm}" /></td>
				</tr>
				<tr>
					<th width="15%">整改责任部门</th>
					<td width="35%">${troMan.rectDept}</td>
					<th width="15%">整改责任人</th>
					<td width="35%">${troMan.rectPerson}</td>
				</tr>
				<tr>
					<th width="15%">整改责任人联系方式</th>
					<td width="35%">${troMan.rectTel}</td>
				</tr>
				<tr>
					<th width="15%">隐患详情</th>
					<td width="35%" colspan="3">
						${troMan.introduce}
					</td>
				</tr>
				<tr>
					<th width="15%">整改前图片</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table>
							  <c:forEach var="item" items="${picList1}">
								<tr id='${item.id}' style="text-align: center">
								   <td width="70%">
								   		<c:choose>
											<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
											||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
											||fn:endsWith(fn:toLowerCase(item.picName),'.png')
											||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
											||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
											<a href="${item.httpUrl}/upload/photo/${item.picName}" rel="example_group">	
												<img src="${item.httpUrl}/upload/photo/${item.picName}"
												border='0' width='220' height='150'/>
											</a>
											</c:when> 
											<c:otherwise> 
												&nbsp;&nbsp;&nbsp;${item.fileName}
											</c:otherwise>
										</c:choose>
								   </td>
								   <td width="30%"><a href="javascript:downloadFile('${item.id}');">下载</a>&nbsp;&nbsp;</td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				<c:forEach var="rect" items="${rectInfos}">
				<c:if test="${rect.state=='整改完成' }">
					<tr>
						<th width="15%">整改时间</th>
						<td width="35%" >
							${rect.rectTime }
						</td>
						<th width="15%">整改资金（元）</th>
						<td width="35%" >${rect.money }</td>
					</tr>
				</c:if>
					<tr>
						<th width="15%">整改结果</th>
						<td width="35%" >${rect.state }</td>
						<th width="15%">备注</th>
						<td width="35%" >${rect.remark }</td>
					</tr>
					<tr>
					<th width="15%">整改图片</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table>
							  <c:forEach var="item" items="${rect.photos}">
								<tr id='${item.id}' style="text-align: center">
								   <td width="70%">
								   		<c:choose>
											<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
											||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
											||fn:endsWith(fn:toLowerCase(item.picName),'.png')
											||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
											||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
												<a href="${item.httpUrl}/upload/photo/${item.picName}" rel="example_group">	
												<img src="${item.httpUrl}/upload/photo/${item.picName}"
												border='0' width='220' height='150'/>
											</a>
											</c:when> 
											<c:otherwise> 
												&nbsp;&nbsp;&nbsp;${item.fileName}
											</c:otherwise>
										</c:choose>
								   </td>
								   <td width="30%"><a href="javascript:downloadFile('${item.id}');">下载</a>&nbsp;&nbsp;
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${fn:length(checkRecords) >0}">
				<tr>
					<th width="15%">审核记录</th>
					<td width="96%" colspan="3">
						<c:forEach items="${checkRecords }" var="cr">
							<fmt:formatDate type="both" value="${cr.createTime}" />，${cr.checkUsername}${cr.checkResult}[${cr.checkRemark}]<br/>
						</c:forEach>
					
					</td>
				</tr>
				</c:if>
				<tr>
					<th width="15%">整改结果</th>
					<td width="35%">
					<s:select id='zgwc' name="troMan.dealState" list="#{'整改完成':'整改完成','整改未完成':'整改未完成'}" theme="simple" onchange="zgwcChange()" cssStyle="width:60%"/>
					</td>
					<th width="15%">备注</th>
					<td width="35%"><input name="troMan.rectRemark"  type="text" maxlength="100" style="width:60%"></td>
				</tr>
				<tr id='zgsj'>
					<th width="15%">整改时间</th>
					<td width="35%" ><input name="troMan.recfinishTime" style="width:60%" id='time' type="text" class="Wdate" datatype="*1-127" errormsg='整改时间是1到127位字符！' nullmsg='整改时间不能为空！' sucmsg='整改时间填写正确！' onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"><font style="color:red">*</font></td>
					<th width="15%">整改资金（元）</th>
					<td width="35%" ><input name="troMan.rectificationMoney" style="width:60%" dataType="*1-127"  nullmsg='整改资金（元）不能为空！' sucmsg='整改资金（元）填写正确！' id='money' type="text" maxlength="127"><font style="color:red">*</font><span id="fixval"></span></td>
				</tr>
				
				
				<tr>
					<th width="15%">整改后图片上传</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue"/>
				    	<input type="file" name="uploadify" id="uploadify"/>
			    		<a href="javascript:jQuery('#uploadify').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="15%">已添加图片</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="troManRect">
							  <c:forEach var="item" items="${picList2}">
								<tr id='${item.id}' style="text-align: center">
								   <td width="70%">
								   		<c:choose>
											<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
											||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
											||fn:endsWith(fn:toLowerCase(item.picName),'.png')
											||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
											||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
												<img src="${item.httpUrl}/upload/photo/${item.picName}"
												border='0' width='220' height='150'/><br>&nbsp;&nbsp;&nbsp;${item.fileName}
											</c:when> 
											<c:otherwise> 
												&nbsp;&nbsp;&nbsp;${item.fileName}
											</c:otherwise>
										</c:choose>
								   </td>
								   <td width="30%"><a href="javascript:downloadFile('${item.id}');">下载</a>&nbsp;&nbsp;
								   <a href="javascript:del('${item.id}');">删除</a></td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >提交<b></b></a>&nbsp;
						</s:else>						
						<!--<a href="#" class="btn_01"  onclick="parent.close_win('win_troMan');">关闭<b></b></a>-->
						<a href="#" class="btn_01"  onclick="redirect();">转接<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	
	<form name="myform2" method="post" enctype="multipart/form-data" action="redirect.action">
		<s:token />
		<input type="hidden" name="ids"  id="ids">
		<input type="hidden" name="troMan.id" value="${troMan.id}">
	</form>
	</div></div></div>
</body>
</html>

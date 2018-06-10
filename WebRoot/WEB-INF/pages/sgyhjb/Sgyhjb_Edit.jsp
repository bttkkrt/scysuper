<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		function save(){
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="sgyhjbSave.action";
				document.myform1.submit();
			}
		}
		
		function queryQy()
		{
			var szzid = document.getElementById('jbszz').value;
			var OpenWindow = window.showModalDialog("${ctx}/jsp/company/queryCompanyList.action?company.dwdz1="+szzid, '', 'dialogHeight=600px;dialogWidth=800px;scroll=no,resizable=no, status=no');
			if(typeof(OpenWindow) != 'undefined')
			{
				var sonValue = OpenWindow.split(";");
				document.getElementById('qyid').value = sonValue[0];
				document.getElementById('qymc').value = sonValue[1];
				document.getElementById('jbszz').value = sonValue[2];
			}
		}
		
		$(function(){       
		$(".filetype1").change(function(){  
			var obj =  this.value;
    		if(obj == '0')
    		{
    			document.getElementById('tt').style.display = "";
    		}
    		else
    		{
    			document.getElementById('tt').style.display = "none";
    		}
		});   
	}); 
	
	
		 //图片上传 lj 2013-08-31
        function uploadPhoto(obj){
        		window.open("${ctx}/jsp/sgyhjb/sgyhjbPicUpload.action?sgyhjb.linkId=${sgyhjb.linkId}&flag="+obj);
        	}
         function savepic(i){
        		window.location.href="${ctx}/jsp/sgyhjb/sgyhjbSaveFile.action?picName="+i;
			}
			//删除附件
		function del(picName){
			$.ajax({
				url: "${ctx}/jsp/sgyhjb/sgyhjbImageDel.action",
				data:{ picName : picName},
				type: "POST",
				success:function(){
					$("tr").remove("tr[id="+picName+"]");
				}
		    });
        }
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="sgyhjb.id" value="${sgyhjb.id}">
		<input type="hidden" name="sgyhjb.createTime" value="<fmt:formatDate type="both" value="${sgyhjb.createTime}" />">
		<input type="hidden" name="sgyhjb.updateTime" value="${sgyhjb.updateTime}">
		<input type="hidden" name="sgyhjb.createUserID" value="${sgyhjb.createUserID}">
		<input type="hidden" name="sgyhjb.updateUserID" value="${sgyhjb.updateUserID}">
		<input type="hidden" name="sgyhjb.deptId" value="${sgyhjb.deptId}">
		<input type="hidden" name="sgyhjb.delFlag" value="${sgyhjb.delFlag}">
		<input type="hidden" name="sgyhjb.state" value="${sgyhjb.state}">
		<input type="hidden" name="sgyhjb.linkId" value="${sgyhjb.linkId}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<s:if test="flag == 'add' || (((flag == 0 && sgyhjb.state == 0) || (flag == 1 && sgyhjb.state == 1)) && type == 1)">
				<tr>
					<th width="15%">举报人</th>
					<td width="35%"><input name="sgyhjb.jbr" value="${sgyhjb.jbr}" type="text" maxlength="255"></td>
					<th width="15%">举报人电话</th>
					<td width="35%"><input name="sgyhjb.jbrdh" value="${sgyhjb.jbrdh}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">举报所在地</th>
					<td width="85%" colspan="3"><input name="sgyhjb.jbszd" value="${sgyhjb.jbszd}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">收信时间</th>
					<td width="35%"><input name="sgyhjb.sxsj" value="${sgyhjb.sxsj}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">隐患等级</th>
					<td width="35%"><cus:SelectOneTag property="sgyhjb.yhjb" defaultText='请选择' codeName="隐患等级" value="${sgyhjb.yhjb}" /></td>
				</tr>
				<tr>
					<th width="15%">举报内容</th>
					<td width="85%" colspan="3"><textarea name="sgyhjb.jbnr" style="width:80%;height:120px">${sgyhjb.jbnr}</textarea><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">举报编号</th>
					<td width="35%"><input name="sgyhjb.jbbh" value="${sgyhjb.jbbh}" type="text" maxlength="255"></td>
					<th width="15%">来电类别</th>
					<td width="35%"><cus:SelectOneTag property="sgyhjb.ldlb" defaultText='请选择' codeName="来电类别" value="${sgyhjb.ldlb}" /></td>
				</tr>
				<tr>
					<th width="15%">举报所在镇</th>
					<td width="35%"><cus:SelectOneTag property="sgyhjb.jbszz" defaultText='请选择' codeName="相城地址" value="${sgyhjb.jbszz}" dataType="Require" msg="此项为必选" /><font style="color:red">*</font></td>
					<th width="15%">举报企业或其它</th>
					<td width="35%">
						<input id="qymc" name="sgyhjb.jbqy" type="text" maxlength="255" dataType="Require" msg="此项为必填" value="${sgyhjb.jbqy}"><font style="color:red">*</font>
						<input name="sgyhjb.jbqyid" value="${sgyhjb.jbqyid}"  type="hidden" id="qyid" type="text" maxlength="255">
						<a href="#" class="easyui-linkbutton" onclick="javascript:queryQy()" iconCls="icon-save">选择</a>
					</td>
				</tr>
				<tr>
					<th width="15%">首次举报</th>
					<td width="35%"><cus:hxradio property="sgyhjb.scjb" codeName="是或否" value="${sgyhjb.scjb}" class="filetype1"/></td>
					<td width="50%" colspan="2">
						<table id="tt" style="display:none">
							<th width="15%">举报次数</th>
							<td width="35%"><input name="sgyhjb.jbcs" value="${sgyhjb.jbcs}" type="text" maxlength="255"></td>
						</table>
					</td>
				</tr>
				<tr>
					<th width="15%">记录人</th>
					<td width="35%"><input name="sgyhjb.jlr" value="${sgyhjb.jlr}" type="text" maxlength="255"></td>
					<th width="15%">来自何部门</th>
					<td width="35%"><input name="sgyhjb.lzbm" value="${sgyhjb.lzbm}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">交办时间</th>
					<td width="35%"><input name="sgyhjb.jbsj" value="${sgyhjb.jbsj}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">要求办结时间</th>
					<td width="35%"><input name="sgyhjb.yqbjsj" value="${sgyhjb.yqbjsj}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<th width="15%">领导批示</th>
					<td width="35%"><input name="sgyhjb.ldps" value="${sgyhjb.ldps}" type="text" maxlength="255"></td>
					<th width="15%">领导批示时间</th>
					<td width="35%"><input name="sgyhjb.ldpssj" value="${sgyhjb.ldpssj}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<th width="15%">分管领导意见</th>
					<td width="35%"><input name="sgyhjb.fgldyj" value="${sgyhjb.fgldyj}" type="text" maxlength="255"></td>
					<th width="15%">分管领导批示时间</th>
					<td width="35%">
						<input name="sgyhjb.fgldpssj" value="${sgyhjb.fgldpssj}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					</td>
				</tr>
				<tr>
					<th width="15%">交办部门</th>
					<td width="35%">
						<s:select theme="simple" cssStyle="width:100px;" name="sgyhjb.jbbm" list="%{deptlist1}" listKey="deptCode" listValue="deptName" value="{sgyhjb.jbbm}"></s:select>
					</td>
					<th width="15%">交办部门接收时间</th>
					<td width="35%">
						<input name="sgyhjb.jbbmjssj" value="${sgyhjb.jbbmjssj}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					</td>
				</tr>
				</s:if>
				<s:else>
				<tr>
					<th width="15%">举报人</th>
					<td width="35%">${sgyhjb.jbr}</td>
					<th width="15%">举报人电话</th>
					<td width="35%">${sgyhjb.jbrdh}</td>
				</tr>
				<tr>
					<th width="15%">举报所在地</th>
					<td width="85%" colspan="3">${sgyhjb.jbszd}</td>
				</tr>
				<tr>
					<th width="15%">收信时间</th>
					<td width="35%">${sgyhjb.sxsj}</td>
					<th width="15%">隐患等级</th>
					<td width="35%"><cus:hxlabel codeName="隐患等级" itemValue="${sgyhjb.yhjb}" /></td>
				</tr>
				<tr>
					<th width="15%">举报内容</th>
					<td width="85%" colspan="3"><textarea name="sgyhjb.jbnr" style="width:80%;height:120px" disabled>${sgyhjb.jbnr}</textarea></td>
				</tr>
				<tr>
					<th width="15%">举报编号</th>
					<td width="35%">${sgyhjb.jbbh}</td>
					<th width="15%">来电类别</th>
					<td width="35%"><cus:hxlabel codeName="来电类别" itemValue="${sgyhjb.ldlb}" /></td>
				</tr>
				<tr>
					<th width="15%">举报所在镇</th>
					<td width="35%"><cus:hxlabel codeName="相城地址" itemValue="${sgyhjb.jbszz}" /></td>
					<th width="15%">举报企业或其它</th>
					<td width="35%">${sgyhjb.jbqy}</td>
				</tr>
				<tr>
					<th width="15%">首次举报</th>
					<td width="35%"><cus:hxlabel codeName="是或否" itemValue="${sgyhjb.scjb}" /></td>
					<td width="50%" colspan="2">
						<table id="tt" <s:if test="sgyhjb.scjb != 0">style="display:none"</s:if>>
							<th width="15%">举报次数</th>
							<td width="35%">${sgyhjb.jbcs}</td>
						</table>
					</td>
				</tr>
				<tr>
					<th width="15%">记录人</th>
					<td width="35%">${sgyhjb.jlr}</td>
					<th width="15%">来自何部门</th>
					<td width="35%">${sgyhjb.lzbm}</td>
				</tr>
				<tr>
					<th width="15%">交办时间</th>
					<td width="35%">${gpdb.gpsj}</td>
					<th width="15%">要求办结时间</th>
					<td width="35%">${sgyhjb.yqbjsj}</td>
				</tr>
				<tr>
					<th width="15%">领导批示</th>
					<td width="35%">${sgyhjb.ldps}</td>
					<th width="15%">领导批示时间</th>
					<td width="35%">${sgyhjb.ldpssj}</td>
				</tr>
				<tr>
					<th width="15%">分管领导意见</th>
					<td width="35%">${sgyhjb.fgldyj}</td>
					<th width="15%">分管领导批示时间</th>
					<td width="35%">${sgyhjb.fgldpssj}</td>
				</tr>
				<tr>
					<th width="15%">交办部门</th>
					<td width="35%">${sgyhjb.jbbmname}</td>
					<th width="15%">交办部门接收时间</th>
					<td width="35%">${sgyhjb.jbbmjssj}</td>
				</tr>
				</s:else>		
				<s:if test="((flag == 0 && sgyhjb.state == 1) || (flag == 1 && sgyhjb.state == 2)) && type == 2">	
				<tr>
					<th width="15%">交办部门处理情况</th>
					<td width="85%" colspan="3"><textarea name="sgyhjb.jbbmclqk" style="width:80%;height:120px">${sgyhjb.jbbmclqk}</textarea></td>
				</tr>
				<tr>
					<th width="15%">交办部门处理时间</th>
					<td width="35%">
						<input name="sgyhjb.jbbmclsj" value="${sgyhjb.jbbmclsj}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					</td>
					<th width="15%">交办部门处理方式</th>
					<td width="35%">
						<input type="radio" name="sgyhjb.jbbmclfh" value="0" <s:if test="sgyhjb.jbbmclfh != 1">checked</s:if>>乡镇处理
                     	<input type="radio" name="sgyhjb.jbbmclfh" value="1" <s:if test="sgyhjb.jbbmclfh == 1">checked</s:if>>自己处理
					</td>
				</tr>
				<tr>
					<th width="15%">处理部门接收时间</th>
					<td width="35%">
						<input name="sgyhjb.clbmjssj" value="${sgyhjb.clbmjssj}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					</td>
				</tr>
				</s:if>
				<s:elseif test="((flag == 0 && sgyhjb.state == 1) || (flag == 1 && sgyhjb.state == 2)) && type == 3">	
				<tr>
					<th width="15%">交办部门处理情况</th>
					<td width="85%" colspan="3"><textarea name="sgyhjb.jbbmclqk" style="width:80%;height:120px">${sgyhjb.jbbmclqk}</textarea></td>
				</tr>
				<tr>
					<th width="15%">交办部门处理时间</th>
					<td width="35%">
						<input name="sgyhjb.jbbmclsj" value="${sgyhjb.jbbmclsj}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					</td>
				</tr>
				</s:elseif>
				<s:elseif test="sgyhjb.state ==2 || sgyhjb.state ==3 || sgyhjb.state ==4">
				<tr>
					<th width="15%">交办部门处理情况</th>
					<td width="85%" colspan="3"><textarea name="sgyhjb.jbbmclqk" style="width:80%;height:120px" disabled>${sgyhjb.jbbmclqk}</textarea></td>
				</tr>
				<tr>
					<th width="15%">交办部门处理时间</th>
					<td width="35%">
						${sgyhjb.jbbmclsj}
					</td>
					<th width="15%">处理部门</th>
					<td width="35%">
						${sgyhjb.clbmname}
					</td>
				</tr>
				<tr>
					<th width="15%">处理部门接收时间</th>
					<td width="35%">
						${sgyhjb.clbmjssj}
					</td>
				</tr>
				</s:elseif>
				<s:if test="((flag == 0 && sgyhjb.state == 2) || (flag == 1 && sgyhjb.state == 3)) && (type == 2 || type == 3)">
				<tr>
					<th width="15%">处理结果</th>
					<td width="85%" colspan="3"><textarea name="sgyhjb.cljg" style="width:80%;height:120px">${sgyhjb.cljg}</textarea></td>
				</tr>
				<tr>
					<th width="15%">处理人</th>
					<td width="35%"><input name="sgyhjb.clr" value="${sgyhjb.clr}" type="text" maxlength="255"></td>
					<th width="15%">处理时间</th>
					<td width="35%">
						<input name="sgyhjb.clsj" value="${sgyhjb.clsj}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					</td>
				</tr>
				<tr>
					<th width="15%">隐患照片</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('yhtp')" title="点击上传隐患照片">选择隐患照片</a>
								</td>
								<td width="70%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="yhtp">
													<c:forEach var="item" items="${picList1}">
														<tr id='${item.id}' style="text-align: center">
															<td width="70%">
																<c:choose>
																    <c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
																    	||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
																    	||fn:endsWith(fn:toLowerCase(item.picName),'.png')
																    	||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
																    	||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
																    	<img src="/upload/photo/${item.picName}"
																			border='0' width='220' height='150'/>
																    </c:when> 
																    <c:otherwise> 
																     ${item.fileName}
																    </c:otherwise>
															   </c:choose>
															</td>
															<td width="30%">
																<a href="javascript:savepic('${item.id}');">下载</a>&nbsp;&nbsp;
																<a href="javascript:del('${item.id}');">删除</a>
															</td>
														</tr>
													</c:forEach>
											</table>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<th width="15%">整改完成时间</th>
					<td width="35%">
						<input name="sgyhjb.zgwcsj" value="${sgyhjb.zgwcsj}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					</td>
					<th width="15%">奖励金额</th>
					<td width="35%"><input name="sgyhjb.jlje" value="${sgyhjb.jlje}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">整改后照片</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('zghzp')" title="点击上传整改后照片">选择整改后照片</a>
								</td>
								<td width="70%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="zghzp">
													<c:forEach var="item" items="${picList2}">
														<tr id='${item.id}' style="text-align: center">
															<td width="70%">
																<c:choose>
																    <c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
																    	||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
																    	||fn:endsWith(fn:toLowerCase(item.picName),'.png')
																    	||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
																    	||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
																    	<a href="/upload/photo/${item.picName}" rel="example_group">
																    	<img src="/upload/photo/${item.picName}"
																			border='0' width='220' height='150'/>
																		</a>
																    </c:when> 
																    <c:otherwise> 
																     ${item.fileName}
																    </c:otherwise>
															   </c:choose>
															</td>
															<td width="30%">
																<a href="javascript:savepic('${item.id}');">下载</a>&nbsp;&nbsp;
																<a href="javascript:del('${item.id}');">删除</a>
															</td>
														</tr>
													</c:forEach>
											</table>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				</s:if>
				<s:elseif test="sgyhjb.state == 3 || sgyhjb.state ==4">
				<tr>
					<th width="15%">处理结果</th>
					<td width="85%" colspan="3"><textarea name="sgyhjb.cljg" style="width:80%;height:120px" disabled>${sgyhjb.cljg}</textarea></td>
				</tr>
				<tr>
					<th width="15%">处理人</th>
					<td width="35%">${sgyhjb.clr}</td>
					<th width="15%">处理时间</th>
					<td width="35%">
						${sgyhjb.clsj}
					</td>
				</tr>
				<tr>
					<th width="15%">隐患照片</th>
					<td width="85%" colspan="3">
									<div style="color:green;overflow:auto;height:200px;">
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
																    	<a href="/upload/photo/${item.picName}" rel="example_group">
																    	<img src="/upload/photo/${item.picName}"
																			border='0' width='220' height='150'/></a>
																    </c:when> 
																    <c:otherwise> 
																     ${item.fileName}
																    </c:otherwise>
															   </c:choose>
															</td>
															<td width="30%">
																<a href="javascript:savepic('${item.id}');">下载</a>
															</td>
														</tr>
													</c:forEach>
											</table>
									</div>
					</td>
				</tr>
				<tr>
					<th width="15%">整改完成时间</th>
					<td width="35%">
						${sgyhjb.zgwcsj}
					</td>
					<th width="15%">奖励金额</th>
					<td width="35%">${sgyhjb.jlje}</td>
				</tr>
				<tr>
					<th width="15%">整改后照片</th>
					<td width="85%" colspan="3">
									<div style="color:green;overflow:auto;height:200px;">
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
																    	<a href="/upload/photo/${item.picName}" rel="example_group">
																    	<img src="/upload/photo/${item.picName}"
																			border='0' width='220' height='150'/></a>
																    </c:when> 
																    <c:otherwise> 
																     ${item.fileName}
																    </c:otherwise>
															   </c:choose>
															</td>
															<td width="30%">
																<a href="javascript:savepic('${item.id}');">下载</a>
															</td>
														</tr>
													</c:forEach>
											</table>
									</div>
					</td>
				</tr>
				</s:elseif>
				<s:if test="((flag == 0 && sgyhjb.state == 3) || (flag == 1 && sgyhjb.state == 4)) && (type == 2 || type == 3)">
				<tr>
					<th width="15%">交办部门负责人</th>
					<td width="35%"><input name="sgyhjb.jbbmfzr" value="${sgyhjb.jbbmfzr}" type="text" maxlength="255"></td>
					<th width="15%">审查时间</th>
					<td width="35%">
						<input name="sgyhjb.jbbmyjsj" value="${sgyhjb.jbbmyjsj}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					</td>
				</tr>
				<tr>
					<th width="15%">交办部门意见</th>
					<td width="85%" colspan="3"><textarea name="sgyhjb.jbbmyj" style="width:80%;height:120px">${sgyhjb.jbbmyj}</textarea></td>
				</tr>
				</s:if>
				<s:elseif test="sgyhjb.state ==4">
				<tr>
					<th width="15%">交办部门负责人</th>
					<td width="35%">${sgyhjb.jbbmfzr}</td>
					<th width="15%">审查时间</th>
					<td width="35%">
						${sgyhjb.jbbmyjsj}
					</td>
				</tr>
				<tr>
					<th width="15%">交办部门意见</th>
					<td width="85%" colspan="3"><textarea name="sgyhjb.jbbmyj" style="width:80%;height:120px" disabled>${sgyhjb.jbbmyj}</textarea></td>
				</tr>
				</s:elseif>
				<s:if test="((flag == 0 && sgyhjb.state == 4) || (flag == 1 && sgyhjb.state == 5)) && type == 1">
				<tr>
					<th width="15%">审查结果</th>
					<td width="35%"><cus:SelectOneTag property="sgyhjb.scjg" defaultText='请选择' codeName="审核结果" value="${sgyhjb.scjg}" /></td>
					<th width="15%">得分</th>
					<td width="35%"><cus:SelectOneTag property="sgyhjb.df" defaultText='请选择' codeName="得分" value="${sgyhjb.df}" /></td>
				</tr>
				</s:if>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
						</s:if>
						<s:elseif test="flag==0">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">上报</a>&nbsp;
						</s:elseif>
						<s:else>
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
						</s:else>						
						<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="document.myform1.reset()">重置</a>&nbsp;
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>上报核实结果</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		function save(){
			document.myform1.action="zjjtzsbShangbaoSave.action";
			document.myform1.submit();
		}
		
		 //图片上传 lj 2013-08-31
        function uploadPhoto(obj){
        	window.open("zjjtzsbPicUpload.action?zjjtzsb.linkId=${zjjtzsb.linkId}&flag="+obj);
        	}
         function savepic(i,j){
        		window.location.href="zjjtzsbSavePic.action?picName="+i+"&fileName="+j;
			}
			//删除附件
		function del(picName){
			$.ajax({
				url: "zjjtzsbImageDel.action",
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
		<input type="hidden" name="zjjtzsb.id" value="${zjjtzsb.id}"/>
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="15%">单位名称</th>
					<td width="35%">${zjjtzsb.qymc}</td>
					<th width="15%">单位地址</th>
					<td width="35%">${zjjtzsb.dwdz}</td>
				</tr>
				<tr>
					<th width="15%">所属地区</th>
					<td width="35%">${zjjtzsb.szzname}</td>
					<th width="15%">设备类别</th>
					<td width="35%">${zjjtzsb.sblb}</td>
				</tr>
				<tr>
					<th width="15%">设备档案号</th>
					<td width="35%">${zjjtzsb.sbdah}</td>
					<th width="15%">注册代码</th>
					<td width="35%">${zjjtzsb.zcdm}</td>
				</tr>
				<tr>
					<th width="15%">检验日期</th>
					<td width="35%">${zjjtzsb.jyrq}</td>
					<th width="15%">检验结论</th>
					<td width="35%">${zjjtzsb.jyjl}</td>
				</tr>
				<tr>
					<th width="15%">检查日期</th>
					<td width="35%">${zjjtzsb.jcrq}</td>
					<th width="15%">检查人员</th>
					<td width="35%">${zjjtzsb.jcry}</td>
				</tr>
				<tr>
					
					<th width="15%">单位联系人</th>
					<td width="35%">${zjjtzsb.dwlxr}</td>
					<th width="15%">电话</th>
					<td width="35%">${zjjtzsb.dh}</td>
				</tr>
				<tr>
					<th width="15%">主要问题</th>
					<td width="85%" colspan="3">
						<textarea readOnly name="zjjtzsb.zywt" style="width:100%;height:120px">${zjjtzsb.zywt}</textarea>
					</td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="85%" colspan="3">
						<textarea readOnly name="zjjtzsb.bz" style="width:100%;height:120px">${zjjtzsb.bz}</textarea>
					</td>
				</tr>
				<tr>
					<th width="15%">整改情况</th>
					<td width="85%" colspan="3">
						<textarea name="zjjtzsb.zgqk" style="width:100%;height:120px"></textarea>
					</td>					
				</tr>
				<tr>
					<th width="15%">整改后图片</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td>
									<center><a style="color:blue;" href="javascript:uploadPhoto('zjjtzsb')" title="点击上传附件">选择整改后图片</a></center>
								</td>
							</tr>
							<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
										<div>
											<table id="zjjtzsb">
												<!--<c:forEach var="item" items="${picList}">
													<tr id='${item.id}' style="text-align: center">
														<td width="70%">
															<img src="/upload/photo/${item.picName}" border='0' width='220' height='150'/>
														</td>
														<td width="25%">
															<a href="javascript:savepic('${item.id}','${item.fileName}');">下载</a>&nbsp;&nbsp;
															<a href="javascript:del('${item.id}');">删除</a>
														</td>
													</tr>
												</c:forEach>-->
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
						<input name="zjjtzsb.zgwcsj" value="" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">确定</a>&nbsp;
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

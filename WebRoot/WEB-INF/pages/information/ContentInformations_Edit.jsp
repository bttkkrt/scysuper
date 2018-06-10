<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><s:if test="flag=='add'">新增</s:if> <s:else>修改</s:else>记录</title>
		<%@include file="/common/jsLib.jsp"%>
		<script src="${ctx}/webResources/kindeditor/kindeditor.js"></script>
		<script src="${ctx}/webResources/kindeditor/lang/zh_CN.js"></script>
		<script src="${ctx}/webResources/kindeditor/plugins/code/prettify.js"></script>

		<script>
		$(document).ready(function() {
			if("${updateFlag}"=="1"){
				layer.alert('更新成功', {
					  skin: 'layui-layer-molv' //样式类名
					  ,closeBtn: 0
					}, function(){
						closeLayer();
						/* window.opener=null;
						window.open('','_self');
						window.close(); */
					});
			}else if("${updateFlag}"=="2"){
				layer.alert('保存失败');
			}
		  //uploadPic("uploadify","${contentInformations.linkId}","contentInformations","contentInformationsfj","fileQueue");
		});
		
		
		function selectUser()
		{
			createSimpleWindow("infoWindow","选择公告用户","${ctx}/jsp/information/selectUsers.action", 800, 600);
			//popupCenter("${ctx}/jsp/information/selectUsers.action", "setUser", "800", "600", "no", "no", "no", "no", "no","no");
		}

		/* KindEditor.ready(function(K) {
		    var editor = K.create('textarea[name="infoContent"]', {
		        cssPath: '${ctx}/webResources/kindeditor/plugins/code/prettify.css',
		        uploadJson: '${ctx}/webResources/kindeditor/upload_json.jsp',
		        fileManagerJson: '${ctx}/webResources/kindeditor/file_manager_json.jsp',
		        allowFileManager: true,
		        afterCreate: function() {
		            var self = this;
		            K.ctrl(document, 13,
		            function() {
		                self.sync();
		                save();
		            });
		            K.ctrl(self.edit.doc, 13,
		            function() {
		                self.sync();
		                save();
		            });
		        },
		        afterBlur: function() {
		            this.sync();
		        }
		    });
		    prettyPrint();
		}); */
		
		layui.use('upload', function(){
			  var $ = layui.jquery
			  ,upload = layui.upload;
			  
			//多文件列表示例
			  var demoListView = $('#demoList')
			  ,uploadListIns = upload.render({
			    elem: '#testList'
			    ,url: '/uploadFile.action'
			    ,accept: 'file'
			    ,multiple: true
			    ,auto: false
			    ,bindAction: '#testListAction'
			    ,choose: function(obj){   
			      var files = obj.pushFile(); //将每次选择的文件追加到文件队列
			      //读取本地文件
			      obj.preview(function(index, file, result){
			        var tr = $(['<tr id="upload-'+ index +'">'
			          ,'<td>'+ file.name +'</td>'
			          ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
			          ,'<td>等待上传</td>'
			          ,'<td>'
			            ,'<button class="layui-btn layui-btn-mini demo-reload layui-hide">重传</button>'
			            ,'<button class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</button>'
			          ,'</td>'
			        ,'</tr>'].join(''));
			        
			        //单个重传
			        tr.find('.demo-reload').on('click', function(){
			          obj.upload(index, file);
			        });
			        
			        //删除
			        tr.find('.demo-delete').on('click', function(){
			          delete files[index]; //删除对应的文件
			          tr.remove();
			        });
			        
			        demoListView.append(tr);
			      });
			    }
			    ,done: function(res, index, upload){
			      if(res.code == 0){ //上传成功
			        var tr = demoListView.find('tr#upload-'+ index)
			        ,tds = tr.children();
			        tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
			        tds.eq(3).html(''); //清空操作
			        delete files[index]; //删除文件队列已经上传成功的文件
			        return;
			      }
			      this.error(index, upload);
			    }
			    ,error: function(index, upload){
			      var tr = demoListView.find('tr#upload-'+ index)
			      ,tds = tr.children();
			      tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
			      tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
			    }
			  });
		});
	</script>

	</head>
<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: auto;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
		<form name="myform1" method="post" enctype="multipart/form-data" action="save.action">
				<s:token />
				<input type="hidden" name="flag" value="${flag}">
				<input type="hidden" name="contentInformations.id"
					value="${contentInformations.id}">
				<input type="hidden" name="contentInformations.createTime"
					value="<fmt:formatDate type="both" value="${contentInformations.createTime}" />">
				<input type="hidden" name="contentInformations.updateTime"
					value="${contentInformations.updateTime}">
				<input type="hidden" name="contentInformations.createUserID"
					value="${contentInformations.createUserID}">
				<input type="hidden" name="contentInformations.updateUserID"
					value="${contentInformations.updateUserID}">
				<input type="hidden" name="contentInformations.deptId"
					value="${contentInformations.deptId}">
				<input type="hidden" name="contentInformations.delFlag"
					value="${contentInformations.delFlag}">
				<input type="hidden" name="contentInformations.userId"
					value="${contentInformations.userId}">
				<input type="hidden" name="contentInformations.expireFlag"
					value="${contentInformations.expireFlag}">
					<input type="hidden" name="contentInformations.linkId" value="${contentInformations.linkId}">
						<table width="100%" border="0">
							<tr>
								<th width="15%">
									标题
								</th>
								<td colspan="3">
									<input name="contentInformations.infoTitle"  id="infoTitle"
										value="${contentInformations.infoTitle}" type="text" datatype="*1-50"  nullmsg='请输入标题  ' sucmsg='标题填写正确！' style="width: 80%" >
									<font style='color:red'>*</font>
								</td>
							</tr>
							<tr>
								<th width="15%">
									信息类型
								</th>
								<td width="35%">
									<cus:SelectOneTag property="contentInformations.infoType" id="infoType"
										defaultText='请选择' codeName="信息类型"
										value="${contentInformations.infoType}" style="width: 60%" datatype="*1-127"  nullmsg='请选择信息类型  ' sucmsg='信息类型填写正确！'/><font style='color:red'>*</font>
								</td>
								<th width="15%">
									阅读期限
								</th>
									<td width="35%"><input name="contentInformations.readPeriod" style="width: 60%" value="<fmt:formatDate type='date' value='${contentInformations.readPeriod}' pattern="yyyy-MM-dd HH"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH'})"></td>
								
							</tr>
							<tr>
								<th width="15%">
									发布人
								</th>
								<s:if test="flag=='add'">
									<td width="35%">
										${user.displayName}
									</td>
								</s:if>
								<s:if test="flag=='mod'">
									<td width="35%">
										${contentInformations.user.displayName}
									</td>
								</s:if>
								<th width="15%">
									所属部门
								</th>
								<s:if test="flag=='add'">
									<td width="35%">
										${user.dept.deptName}
									</td>
								</s:if>
								<s:if test="flag=='mod'">
									<td width="35%">
										${contentInformations.dept.deptName}
									</td>
								</s:if>
							</tr>
				<tr>
					<th width='15%'>
						发送给
					</th>
					<td colspan='3'>
						<textarea rows="5" style="width:85%;word-break:break-all;word-wrap:break-word;" readonly="readonly" 
						name="contentInformations.userNames" id="userNames" datatype="*"  nullmsg='请选择人员  ' sucmsg='人员填写正确！'>${contentInformations.userNames}</textarea><font style='color:red'>*</font>
						<a href="###" class="btn_01_mini1" onclick="selectUser();">选择</a>
						<input type="hidden" id ="userIds" name="contentInformations.userIds" value="${contentInformations.userIds}">
					</td>
				</tr>
							
							
							
				<tr>
					<th width="15%">附件上传</th>
					<td width="85%" colspan="3">
						<div class="layui-upload">
						  <button type="button" class="layui-btn layui-btn-normal" id="testList">选择多文件</button> 
						  <div class="layui-upload-list">
						    <table class="layui-table">
						      <thead>
						        <tr><th>文件名</th>
						        <th>大小</th>
						        <th>状态</th>
						        <th>操作</th>
						      </tr></thead>
						      <tbody id="demoList"></tbody>
						    </table>
						  </div>
						  <button type="button" class="layui-btn" id="testListAction" style="display: none;">开始上传</button>
						</div> 
						<!-- <div class="layui-upload">
						  <button type="button" class="layui-btn layui-btn-normal" id="test8">选择文件</button>
						  <button type="button" class="layui-btn" id="test9"style="display: none;">开始上传</button>
						</div> -->
				    </td>
				</tr>
				<tr>
					<th width="15%">已添加附件</th>
					<td width="85%" colspan="3">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="contentInformationsfj">
							  <c:forEach var="item" items="${picList}">
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
							<th width='15%'>
						            公告内容
					       </th>
								<td colspan="3" style="padding: 0">
									<textarea id="infoContent" name="infoContent" style="width: 100%; height: 230px;" datatype="*"  nullmsg='请填写公告内容  ' sucmsg='公告内容填写正确！'>${infoContent}</textarea><font style='color:red'>*</font>
								</td>
							</tr>
						<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="###" class="btn_01" type="submit">发布<b></b> </a>
						</s:if>
						<s:else>
							<a href="###" class="btn_01" type="submit">更新<b></b> </a>
						</s:else>
						<a href="###" class="btn_01"
							onclick="parent.close_win('infoWindow');">关闭<b></b> </a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
	</body>
</html>

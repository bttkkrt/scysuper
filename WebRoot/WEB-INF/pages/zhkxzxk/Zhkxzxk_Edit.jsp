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
				
				document.myform1.action="zhkxzxkSave.action";
				document.myform1.submit();
			}
		}
		//图片上传 lj 2013-08-31
        function uploadPhoto(obj){
        		window.open("${ctx}/jsp/zhkxzxk/zhkxzxkUpload.action?zhkxzxk.linkid=${zhkxzxk.linkid}&flag="+obj);
        	}
         function savepic(i){
        		window.location.href="${ctx}/jsp/zhkxzxk/zhkxzxkSaveFile.action?picName="+i;
			}
			//删除附件
		function del(picName){
			$.ajax({
				url: "${ctx}/jsp/zhkxzxk/zhkxzxkImageDel.action",
				data:{ picName : picName},
				type: "POST",
				success:function(){
					$("tr").remove("tr[id="+picName+"]");
				}
		    });
        }
        
        function queryQy()
		{
			var szzid = document.getElementById('szzid').value;
			var OpenWindow = window.showModalDialog("${ctx}/jsp/company/queryCompanyList.action?company.dwdz1="+szzid, '', 'dialogHeight=600px;dialogWidth=800px;scroll=no,resizable=no, status=no');
			if(typeof(OpenWindow) != 'undefined')
			{
				var sonValue = OpenWindow.split(";");
				document.getElementById('qyid').value = sonValue[0];
				document.getElementById('qymc').value = sonValue[1];
				document.getElementById('szzid').value = sonValue[2];
			}
		}
		
		function clearCompany(){
        	document.getElementById("qymc").value="";
        	document.getElementById("qyid").value="";
        }
        
        
		
		function changeCaiLiao()
		{  
			var val = $("input[name='isCaiLiao']:checked").val(); //获得选中的radio的值             
			if(val=='1'){    
				$('#isCaiLiao').val(1);    
			}else{   
				$('#isCaiLiao').val(0);  
			}                    
		} 
		      
	
	
		function init1()
        {
        	
	        if($('#isCaiLiao').val()==1)
			{
			    document.getElementById("isCaiLiao1").checked=true;
			    document.getElementById("isCaiLiao0").checked=false;
			}else if($('#isCaiLiao').val()==0)
			{
			    document.getElementById("isCaiLiao1").checked=false;
			    document.getElementById("isCaiLiao0").checked=true;
			}
		}
	</script>
	
</head>
<body onload="init1()">
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="zhkxzxk.id" value="${zhkxzxk.id}">
		<input type="hidden" name="zhkxzxk.createTime" value="<fmt:formatDate type="both" value="${zhkxzxk.createTime}" />">
		<input type="hidden" name="zhkxzxk.updateTime" value="${zhkxzxk.updateTime}">
		<input type="hidden" name="zhkxzxk.createUserID" value="${zhkxzxk.createUserID}">
		<input type="hidden" name="zhkxzxk.updateUserID" value="${zhkxzxk.updateUserID}">
		<input type="hidden" name="zhkxzxk.deptId" value="${zhkxzxk.deptId}">
		<input type="hidden" name="zhkxzxk.delFlag" value="${zhkxzxk.delFlag}">
		<input type="hidden" name="zhkxzxk.linkid" value="${zhkxzxk.linkid}">
		<input type="hidden" name="zhkxzxk.remark" value="${zhkxzxk.remark}">
		<input type="hidden" name="zhkxzxk.shenhe" value="${zhkxzxk.shenhe}">
		
		<input type="hidden" id="isCaiLiao" name="zhkxzxk.isCaiLiao" value="${zhkxzxk.isCaiLiao}">
		<input type="hidden" id="isCunChu" name="zhkxzxk.isCunChu" value="${zhkxzxk.isCunChu}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在镇</th>
					<td width="35%">
						<cus:SelectOneTag property="zhkxzxk.szzid" defaultText='请选择' codeName="相城地址" value="${zhkxzxk.szzid}" dataType="Require" msg="此项为必填" onchange="clearCompany()"/><font style="color:red">*</font>
					</td>
					<th width="15%">企业名称</th>
					<td width="35%">
					<input id="qymc" name="zhkxzxk.qymc" type="text" maxlength="255" dataType="Require" msg="此项为必填" value="${zhkxzxk.qymc}"><font style="color:red">*</font>
					<input name="zhkxzxk.qyid" value="${zhkxzxk.qyid}"  type="hidden" id="qyid" type="text" maxlength="255">
					<a href="#" class="easyui-linkbutton" onclick="javascript:queryQy()" iconCls="icon-save">选择</a>
					</td>
				</tr>
				
				<tr>	
					<th width="15%" >档案号</th>
					<td width="35%">
					<input name="zhkxzxk.fileId" value="${zhkxzxk.fileId}" size= 50 type="text"   maxlength="255">
					</td>
					<th width="15%" >材料审查人员</th>
					<td width="35%">
					<input name="zhkxzxk.clscry" value="${zhkxzxk.clscry}" size= 50 type="text"   maxlength="255">
					</td>
				</tr>	
				
				<tr>
					<th width="15%" >材料接收人员</th>
					<td width="35%">
					<input name="zhkxzxk.cljsry" value="${zhkxzxk.cljsry}" size= 50 type="text"   maxlength="255">
					</td>
					<th width="15%" >材料接收日期</th>
					<td width="35%">
				    <input name="zhkxzxk.cljsrq" id="cljsrq"  
				  value="${zhkxzxk.cljsrq}"
					type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					</td>
				</tr>	
				
				<tr>
					<th width="15%" >受理材料日期</th>
					<td width="35%">
				    <input name="zhkxzxk.slclrq" id="slclrq"  
				   value="${zhkxzxk.slclrq}"
					type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					</td>
					<th width="15%" >许可证有效期</th>
					<td width="35%">
				    <input name="zhkxzxk.xkzyxq" id="xkzyxq"  
				   value="${zhkxzxk.xkzyxq}"
					type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					</td>
				</tr>
				
				<tr>
					<th width="15%" >发证单位</th>
					<td width="35%">
					<input name="zhkxzxk.fzdw" value="${zhkxzxk.fzdw}" size= 50 type="text"   maxlength="255">
					</td>
					<th width="15%">本次领证情况</th>
					<td width="35%">
					<s:select id="bclzqk" listKey="key" listValue="value"  theme="simple" list="#{'':'请选择','延期换证':'延期换证','变更法人':'变更法人','变更企业类型':'变更企业类型','变更营业执照':'变更营业执照','变更生产品种':'变更生产品种','新发证':'新发证'}" name="zhkxzxk.bclzqk" value="{zhkxzxk.bclzqk}"/>
					</td>
				 
				</tr>
				
				<tr>
					<th width="15%">申请材料是否齐全</th>
					<td width="35%">
					
					<input type="radio" onchange="changeCaiLiao()" name="isCaiLiao" id="isCaiLiao0" value="0"  class="filetype1">否
                   	<input type="radio" onchange="changeCaiLiao()" name="isCaiLiao" id="isCaiLiao1" value="1"  class="filetype1">是 
                     	
                     	
					</td>
					<th width="15%">签字领导</th>
					<td width="35%">
					<input name="zhkxzxk.qzld" value="${zhkxzxk.qzld}" size= 50 type="text"   maxlength="255">
					</td>
				</tr>
				
				<tr>
					<th width="15%">乡镇预审意见</th>
					<td  colspan="3">
					
					<textarea id="xzysyj"  
						name="zhkxzxk.xzysyj" rows="4"
						style="width: 80%">${zhkxzxk.xzysyj}</textarea>
								
					</td>
				 
				</tr>
				<tr>
					<th width="15%">现场检查部门</th>
					<td  colspan="3">
					<input name="zhkxzxk.xchcbm" value="${zhkxzxk.xchcbm}" size= 50 type="text"   maxlength="255">
				
					</td>
				 
				</tr>
				
				<tr>
					<th width="15%">核查结论</th>
					<td  colspan="3">
					<textarea id="hcjl"  
						name="zhkxzxk.hcjl" rows="4"
						style="width: 80%">${zhkxzxk.hcjl}</textarea>
					</td>
				 
				</tr>
				<tr>
					<th width="15%">材料审查情况</th>
					<td  colspan="3">
					<textarea id="clscqk"  
						name="zhkxzxk.clscqk" rows="4"
						style="width: 80%">${zhkxzxk.clscqk}</textarea>
					</td>
				 
				</tr>
				
				
				
				<tr>
					<th width="15%">行政许可建议</th>
					<td  colspan="3">
					<textarea id="xzxkjy"  
						name="zhkxzxk.xzxkjy" rows="4"
						style="width: 80%">${zhkxzxk.xzxkjy}</textarea>
					</td>
				 
				</tr>
				
				
				<tr>
					<th width="15%">局审会意见</th>
					<td  colspan="3">
					<textarea id="jhsjl"  
						name="zhkxzxk.jhsjl" rows="4"
						style="width: 80%">${zhkxzxk.jhsjl}</textarea>
					</td>
				 
				</tr>
				
				<tr>
					<th width="15%">
						烟花爆竹经营许可证申请书
					</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td>
									<center>
									<a style="color:blue;" href="javascript:uploadPhoto('zhkxzxk1')" title="点击上传附件">
											选择烟花爆竹经营许可证申请书
									</a>
									</center>
								</td>
							</tr>
							<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
									  	<div>
										  	<table id="zhkxzxk1">
												<c:forEach var="item" items="${picList1}">
													<tr id='${item.id}' style="text-align: center">
														<td width="70%">
													     	${item.fileName}
														</td>
														<td width="25%">
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
					<th width="15%">
						烟花爆竹经营许可证申请材料
					</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td>
									<center>
									<a style="color:blue;" href="javascript:uploadPhoto('zhkxzxk2')" title="点击上传附件">
											选择烟花爆竹经营许可证申请材料
									</a>
									</center>
								</td>
							</tr>
							<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
									  	<div>
										  	<table id="zhkxzxk2">
												<c:forEach var="item" items="${picList2}">
													<tr id='${item.id}' style="text-align: center">
														<td width="70%">
													     	${item.fileName}
														</td>
														<td width="25%">
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
					<th width="15%">
						行政审批文件
					</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td>
									<center>
									<a style="color:blue;" href="javascript:uploadPhoto('zhkxzxk3')" title="点击上传附件">
											选择行政审批文件
									</a>
									</center>
								</td>
							</tr>
							<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
									  	<div>
										  	<table id="zhkxzxk3">
												<c:forEach var="item" items="${picList3}">
													<tr id='${item.id}' style="text-align: center">
														<td width="70%">
													     	${item.fileName}
														</td>
														<td width="25%">
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
					<th width="15%">
						烟花爆竹经营许可证扫描件
					</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td>
									<center>
									<a style="color:blue;" href="javascript:uploadPhoto('zhkxzxk4')" title="点击上传附件">
											选择烟花爆竹经营许可证扫描件
									</a>
									</center>
								</td>
							</tr>
							<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
									  	<div>
										  	<table id="zhkxzxk4">
												<c:forEach var="item" items="${picList4}">
													<tr id='${item.id}' style="text-align: center">
														<td width="70%">
													     	${item.fileName}
														</td>
														<td width="25%">
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
				
				
				
				
				
				<s:if test="zhkxzxk.state == 2">
				<tr>
					<th width="15%">审核记录</th>
					<td width="85%" colspan=3>
						<table>
							<s:iterator  value="shenheList" status="sta">
		    					<tr>
		    	 					<th> ${sta.index+1}</th>
		    	 					<td><s:property  /></td>
		    					</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
				</s:if>
				<tr>
					<th width="15%">选择审核人员</th>
					<td width="85%" colspan="3">
						<s:select  theme="simple" cssStyle="width:100px;" id="shuserid" list="%{userList}" listKey="id"
							name="zhkxzxk.shuserid" listValue="displayName" dataType="Require" msg="此项为必填">
						</s:select><font style="color:red">*</font>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
						</s:if>
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

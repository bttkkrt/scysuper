<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>审核</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		function save(){
			var state = document.getElementById('state').value;
			var remark = document.getElementById('remark').value;
			if(state == "2" && remark == "")
			{
				alert("审核不通过必须注明理由！");
			}
			else
			{
				document.myform.action="${ctx}/jsp/zhkxzxk/zhkxzxkShenheSave.action";
				document.myform.submit();
			}
		}
		
		 function showUpload(obj)
		{
			if(obj == '0')
			{
				document.getElementById("upload").style.display="";
				document.getElementById("isfinish").value="0";
			}
			else
			{
				document.getElementById("upload").style.display="none";
				document.getElementById("isfinish").value="1";
			}
			
		}
		
		function showop(obj)
		{
			if(obj == 3)
			{
				document.getElementById("op").style.display="";
			}
			else
			{
				document.getElementById("op").style.display="none";
			}
		}
		
		function savepic(i){
        	window.location.href="${ctx}/jsp/zhkxzxk/zhkxzxkSaveFile.action?picName="+i;
		}
	</script>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
	<s:token />
		<input type="hidden" name="zhkxzxk.id" value="${zhkxzxk.id}">
		<input type="hidden" id="isfinish" name="zhkxzxk.isfinish" value="${zhkxzxk.isfinish}">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="15%">所在镇</th>
					<td width="35%">${zhkxzxk.szzname}</td>
					<th width="15%">企业名称</th>
					<td width="35%">${zhkxzxk.qymc}</td>
				</tr>
				<tr>	
					<th width="15%" >档案号</th>
					<td width="35%">
					 ${zhkxzxk.fileId} 
					</td>
					<th width="15%" >材料审查人员</th>
					<td width="35%">
					 ${zhkxzxk.clscry} 
					</td>
				</tr>
				
				<tr>
					<th width="15%" >材料接收人员</th>
					<td width="35%">
					 ${zhkxzxk.cljsry} 
					</td>
					<th width="15%" >材料接收日期</th>
					<td width="35%">
					 ${zhkxzxk.cljsrq}
					</td>
				</tr>
				
				<tr>
					<th width="15%" >受理材料日期</th>
					<td width="35%">
					 ${zhkxzxk.slclrq}
					</td>
					<th width="15%" >许可证有效期</th>
					<td width="35%">
					 ${zhkxzxk.xkzyxq} 
					</td>
				</tr>
				
				<tr>
					<th width="15%" >发证单位</th>
					<td width="35%">
					 ${zhkxzxk.fzdw} 
					</td>
					<th width="15%">本次领证情况</th>
					<td width="35%">
					 ${zhkxzxk.bclzqk} 
					</td>
				 
				</tr>
				
				
				
				
				
				
				
				
				<tr>
					<th width="15%">申请材料是否齐全</th>
					<td width="35%" id="showCaiLiao">
					
                     	
					</td>
					<th width="15%">签字领导</th>
					<td width="35%">
					${zhkxzxk.qzld}
					</td>
				</tr>
				
				<tr>
					<th width="15%">乡镇预审意见</th>
					<td  colspan="3">
					 ${zhkxzxk.xzysyj} 
								
					</td>
				 
				</tr>
				<tr>
					<th width="15%">现场检查部门</th>
					<td  colspan="3">
					${zhkxzxk.xchcbm}
				
					</td>
				 
				</tr>
				
				<tr>
					<th width="15%">核查结论</th>
					<td  colspan="3">
					 ${zhkxzxk.hcjl} 
					</td>
				 
				</tr>
				<tr>
					<th width="15%">材料审查情况</th>
					<td  colspan="3">
					 ${zhkxzxk.clscqk} 
					</td>
				 
				</tr>
				
				<tr>
					<th width="15%">行政许可建议</th>
					<td  colspan="3">
					 ${zhkxzxk.xzxkjy} 
					</td>
				 
				</tr>
				
				
				<tr>
					<th width="15%">局审会意见</th>
					<td  colspan="3">
				 ${zhkxzxk.jhsjl} 
					</td>
				 
				</tr>
				<tr>
					<th width="15%">
						烟花爆竹经营许可证申请书
					</th>
					<td width="85%" colspan="3">
					  	<div style="color:green;overflow:auto; height:200px;">
						    <table width="100%">
							    <tr>
									<s:iterator id="taskPic" value="%{picList1}" status="sta">
										<s:if test="#sta.index%4 eq 0">
											</tr>
										</s:if>
										<td>
											<a href="javascript:savepic('<s:property value="#taskPic.id"/>')" title="点击下载">
												<s:property value="#taskPic.fileName" />
											</a>
										</td>
										<s:if test="#sta.last"><br/></tr></s:if>
									</s:iterator>
							    </tr>
							</table>
						</div>
					</td>
				</tr>
				
				<tr>
					<th width="15%">
					烟花爆竹经营许可证申请材料
					</th>
					<td width="85%" colspan="3">
					  	<div style="color:green;overflow:auto; height:200px;">
						    <table width="100%">
							    <tr>
									<s:iterator id="taskPic" value="%{picList2}" status="sta">
										<s:if test="#sta.index%4 eq 0">
											</tr>
										</s:if>
										<td>
											<a href="javascript:savepic('<s:property value="#taskPic.id"/>')" title="点击下载">
												<s:property value="#taskPic.fileName" />
											</a>
										</td>
										<s:if test="#sta.last"><br/></tr></s:if>
									</s:iterator>
							    </tr>
							</table>
						</div>
					</td>
				</tr>
				
				<tr>
					<th width="15%">
					     行政审批文件
					</th>
					<td width="85%" colspan="3">
					  	<div style="color:green;overflow:auto; height:200px;">
						    <table width="100%">
							    <tr>
									<s:iterator id="taskPic" value="%{picList3}" status="sta">
										<s:if test="#sta.index%4 eq 0">
											</tr>
										</s:if>
										<td>
											<a href="javascript:savepic('<s:property value="#taskPic.id"/>')" title="点击下载">
												<s:property value="#taskPic.fileName" />
											</a>
										</td>
										<s:if test="#sta.last"><br/></tr></s:if>
									</s:iterator>
							    </tr>
							</table>
						</div>
					</td>
				</tr>
				
				<tr>
					<th width="15%">
						烟花爆竹经营许可证扫描件
					</th>
					<td width="85%" colspan="3">
					  	<div style="color:green;overflow:auto; height:200px;">
						    <table width="100%">
							    <tr>
									<s:iterator id="taskPic" value="%{picList4}" status="sta">
										<s:if test="#sta.index%4 eq 0">
											</tr>
										</s:if>
										<td>
											<a href="javascript:savepic('<s:property value="#taskPic.id"/>')" title="点击下载">
												<s:property value="#taskPic.fileName" />
											</a>
										</td>
										<s:if test="#sta.last"><br/></tr></s:if>
									</s:iterator>
							    </tr>
							</table>
						</div>
					</td>
				</tr>
				
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
				<tr>
					<th width="15%">审核结果</th>
					<td width="35%">
						<s:select id="state" listKey="key" listValue="value"  theme="simple" list="#{'3':'审核通过','2':'审核不通过'}" name="zhkxzxk.state"  onchange="showop(this.value)"/>
					</td>
				</tr>
				<tr>
					<th width="15%">审核备注</th>
					<td width="85%" colspan=3>
						<textarea id="remark" name="zhkxzxk.remark" rows="5" style="width: 80%">${zhkxzxk.remark}</textarea>
					</td>
				</tr>
				<tr id="op">
					<th width="15%">操作</th>
				 	<td width="35%">
              			<input type="radio" name="sex" checked=true onClick="showUpload('1')">完结
						<input type="radio" name="sex" onClick="showUpload('0')" >上报
				  	</td>
           			<td width="50%" colspan="2">
             			<div id="upload" style="display:none; font-weight: bold">
              				选择审核人员
							<s:select  theme="simple" cssStyle="width:100px;" id="shuserid" list="%{userList}" listKey="id"
							name="zhkxzxk.shuserid" listValue="displayName" dataType="Require" msg="此项为必填">
						</s:select><font style="color:red">*</font>
						</div>
             		</td>
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

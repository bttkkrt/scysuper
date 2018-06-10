<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		function savepic(i){
        	window.location.href="${ctx}/jsp/zhkxzxk/zhkxzxkSaveFile.action?picName="+i;
		}
		
		
		function init1()
        {
	        if($('#isCaiLiao').val()==1)
			{
			    document.getElementById("showCaiLiao").innerHTML="是";
			}else if($('#isCaiLiao').val()==0)
			{
			    document.getElementById("showCaiLiao").innerHTML="否";
			}
			
		}
	</script>
</head>
<body onload="init1()">
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
	
	<input type="hidden" id="isCaiLiao" name="zhkxzxk.isCaiLiao" value="${zhkxzxk.isCaiLiao}">
	<input type="hidden" id="isCunChu" name="zhkxzxk.isCunChu" value="${zhkxzxk.isCunChu}">
		
		
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
					<th width="15%">审核人</th>
					<td width="35%">${zhkxzxk.shusername}</td>
					<th width="15%">审核结果</th>
					<td width="35%">
						<s:if test="zhkxzxk.state == 1">
							待审核
						</s:if>
						<s:elseif test="zhkxzxk.state == 2">
							审核不通过
						</s:elseif>
						<s:else>
							审核通过
						</s:else>
						
					</td>
				</tr>
				<tr>
					<th width="15%">审核备注</th>
					<td width="85%" colspan=3>
						<textarea id="remark" name="zhkxzxk.remark" rows="5" style="width: 80%" readonly="readonly">${zhkxzxk.remark}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

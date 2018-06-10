package cn.jpush.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushResult;

public class Test {
	 private static final String appKey = "90e7f52b9cb49b8d10f33f7c";
	 private static final String masterSecret = "e6ca05d6cda785edc8c12989";
	/**
	 * @param args
	 * @throws APIRequestException 
	 * @throws APIConnectionException 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		JPushClient jpush = new JPushClient();
		 // 在实际业务中，建议 sendNo 是一个你自己的业务可以处理的一个自增数字。
	    // 除非需要覆盖，请确保不要重复使用。详情请参考 API 文档相关说明。
	    int sendNo = 1;
	    String msgTitle = "+;//jpush\"\"";
	    String msgContent = "\\&;w\"\"a--【\npush】";

	    /*
	     * IOS设备扩展参数,
	     * 设置badge，设置声音
	     */

	    Map<String, Object> extra = new HashMap<String, Object>();
	    List<String> l = new ArrayList<String>();
	    l.add("hqajzddy");
	    
	    //对所有用户发送通知, 更多方法请参考文档
	    //PushResult msgResult = jpush.sendNotificationAll("sd","",null);
	    
	    //MessageResult msgResult = jpush.sendCustomMessageWithAppKey(sendNo,msgTitle, msgContent);
	   // PushResult msgResult  = jpush.sendAndroidNotificationWithAlias("哈哈哈","wufan",null,"1111","402880fe5084f3de01508522673a0037");
		Map send = new HashMap();

		send.put("type", "1");
		JSONObject json = new JSONObject();
		json.put("id", "ba53a59a511ffea4015122cec8730059");
		send.put("content",json.toString());
		  PushResult msgResult  =   jpush.sendAndroidNotificationWithAlias("园区安监","哈哈", send,new String[]{"402880fe5084f3de01508522673a0037"});
	    //覆盖指定msgId的消息,msgId可以从msgResult.getMsgid()获取。
	    //MessageResult msgResult = jpush.sendNotificationWithAppKey(sendNo, msgTitle, msgContent, 0, extra,msgResult.getMsgid());


	    if (null != msgResult) {
	      System.out.println("服务器返回数据: " + msgResult.toString());
	      if (msgResult.isResultOK()) {
	        System.out.println(String.format("发送成功"));
	      } else {
	        System.out.println("发送失败， 错误代码=" );
	      }
	    } else {
	      System.out.println("无法获取数据");
	    }
	}

}

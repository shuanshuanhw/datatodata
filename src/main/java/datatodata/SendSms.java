package datatodata;

import java.io.IOException;
import java.util.Properties;

public class SendSms {

	static public String SendSms(String mobile,String content){
	
		//Properties p = new Properties();
		String url;
		String channel;
		String channelPassword;
		String responseContent="";
		//p.load(SendSms.class.getClassLoader().getResourceAsStream("commons.properties"));
		// http://61.142.131.10:888/OaListenerServlet?mType=118&mPassword=K!eyHSAQqgWj2YoM&phones=15007572525;%E9%A9%AC%E7%AB%8B%E5%85%8B;&content=%E4%B8%AD%E5%9B%BD&relateDocUuid=24a4a360-4f5e-4eee-ae69-9bbce9429c3d
		url="http://61.142.131.10:888/OaListenerServlet";
		channel="36";
		channelPassword="SMS_TSG_92611";
		responseContent = HttpClientUtil.getInstance().sendHttpPost("http://61.142.131.10:888/OaListenerServlet","mType=118&mPassword=K!eyHSAQqgWj2YoM&phones="+mobile+"&content="+content);
		 
		return responseContent;
	}
}

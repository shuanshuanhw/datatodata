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
		url="http://61.142.131.10:888/OaListenerServlet";
		channel="36";
		channelPassword="SMS_TSG_92611";
		responseContent = HttpClientUtil.getInstance().sendHttpPost("http://61.142.131.10:888/OaListenerServlet","mType=36&mPassword=SMS_TSG_92611&phones="+mobile+"&content="+content);
		 
		return responseContent;
	}
}

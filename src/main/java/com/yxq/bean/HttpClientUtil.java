package com.yxq.bean;

import java.io.File;  
import java.io.IOException;  
import java.net.URL;  
import java.util.ArrayList;  
import java.util.List;  
import java.util.Map;
  
import org.apache.http.HttpEntity;  
import org.apache.http.NameValuePair;  
import org.apache.http.client.config.RequestConfig;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.CloseableHttpResponse;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.conn.ssl.DefaultHostnameVerifier;  
import org.apache.http.conn.util.PublicSuffixMatcher;  
import org.apache.http.conn.util.PublicSuffixMatcherLoader;  
import org.apache.http.entity.ContentType;  
import org.apache.http.entity.StringEntity;  
import org.apache.http.entity.mime.MultipartEntityBuilder;  
import org.apache.http.entity.mime.content.FileBody;  
import org.apache.http.entity.mime.content.StringBody;  
import org.apache.http.impl.client.CloseableHttpClient;  
import org.apache.http.impl.client.HttpClients;  
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.util.EntityUtils;  
public class HttpClientUtil {  
    private RequestConfig requestConfig = RequestConfig.custom()  
            .setSocketTimeout(15000)  
            .setConnectTimeout(15000)  
            .setConnectionRequestTimeout(15000)  
            .build();  
      
    private static HttpClientUtil instance = null;  
    private HttpClientUtil(){}  
    public static HttpClientUtil getInstance(){  
        if (instance == null) {  
            instance = new HttpClientUtil();  
        }  
        return instance;  
    }  
      
    /** 
     * ���� post���� 
     * @param httpUrl ��ַ 
     */  
    public String sendHttpPost(String httpUrl) {  
        HttpPost httpPost = new HttpPost(httpUrl);// ����httpPost    
        return sendHttpPost(httpPost);
    }  
      
    /** 
     * ���� post���� 
     * @param httpUrl ��ַ 
     * @param params ����(��ʽ:key1=value1&key2=value2) 
     */  
    public String sendHttpPost(String httpUrl, String params) {  
        HttpPost httpPost = new HttpPost(httpUrl);// ����httpPost    
        try {  
            //���ò���  
            StringEntity stringEntity = new StringEntity(params, "GBK");  
            stringEntity.setContentType("application/x-www-form-urlencoded");  
            httpPost.setEntity(stringEntity);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return sendHttpPost(httpPost);  
    }  
      
    /** 
     * ���� post���� 
     * @param httpUrl ��ַ 
     * @param maps ���� 
     */  
    public String sendHttpPost(String httpUrl, Map<String, String> maps) {  
        HttpPost httpPost = new HttpPost(httpUrl);// ����httpPost    
        // ������������    
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();  
        for (String key : maps.keySet()) {  
            nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));  
        }  
        try {  
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return sendHttpPost(httpPost);  
    }  
      
      
    /** 
     * ���� post���󣨴��ļ��� 
     * @param httpUrl ��ַ 
     * @param maps ���� 
     * @param fileLists ���� 
     */  
    public String sendHttpPost(String httpUrl, Map<String, String> maps, List<File> fileLists) {  
        HttpPost httpPost = new HttpPost(httpUrl);// ����httpPost    
        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();  
        for (String key : maps.keySet()) {  
            meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));  
        }  
        for(File file : fileLists) {  
            FileBody fileBody = new FileBody(file);  
            meBuilder.addPart("files", fileBody);  
        }  
        HttpEntity reqEntity = meBuilder.build();  
        httpPost.setEntity(reqEntity);  
        return sendHttpPost(httpPost);  
    }  
      
    /** 
     * ����Post���� 
     * @param httpPost 
     * @return 
     */  
    private String sendHttpPost(HttpPost httpPost) {  
        CloseableHttpClient httpClient = null;  
        CloseableHttpResponse response = null;  
        HttpEntity entity = null;  
        String responseContent = null;  
        try {  
            // ����Ĭ�ϵ�httpClientʵ��.  
            httpClient = HttpClients.createDefault();  
            httpPost.setConfig(requestConfig);  
            // ִ������  
            response = httpClient.execute(httpPost);  
            entity = response.getEntity();  
            responseContent = EntityUtils.toString(entity, "UTF-8");  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                // �ر�����,�ͷ���Դ  
                if (response != null) {  
                    response.close();  
                }  
                if (httpClient != null) {  
                    httpClient.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return responseContent;  
    }  
  
    /** 
     * ���� get���� 
     * @param httpUrl 
     */  
    public String sendHttpGet(String httpUrl) {  
        HttpGet httpGet = new HttpGet(httpUrl);// ����get����  
        return sendHttpGet(httpGet);  
    }  
      
    /** 
     * ���� get����Https 
     * @param httpUrl 
     */  
    public String sendHttpsGet(String httpUrl) {  
        HttpGet httpGet = new HttpGet(httpUrl);// ����get����  
        return sendHttpsGet(httpGet);  
    }  
      
    /** 
     * ����Get���� 
     * @param httpPost 
     * @return 
     */  
    private String sendHttpGet(HttpGet httpGet) {  
        CloseableHttpClient httpClient = null;  
        CloseableHttpResponse response = null;  
        HttpEntity entity = null;  
        String responseContent = null;  
        try {  
            // ����Ĭ�ϵ�httpClientʵ��.  
            httpClient = HttpClients.createDefault();  
            httpGet.setConfig(requestConfig);  
            // ִ������  
            response = httpClient.execute(httpGet);  
            entity = response.getEntity();  
            responseContent = EntityUtils.toString(entity, "UTF-8");  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                // �ر�����,�ͷ���Դ  
                if (response != null) {  
                    response.close();  
                }  
                if (httpClient != null) {  
                    httpClient.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return responseContent;  
    }  
      
    /** 
     * ����Get����Https 
     * @param httpPost 
     * @return 
     */  
    private String sendHttpsGet(HttpGet httpGet) {  
        CloseableHttpClient httpClient = null;  
        CloseableHttpResponse response = null;  
        HttpEntity entity = null;  
        String responseContent = null;  
        try {  
            // ����Ĭ�ϵ�httpClientʵ��.  
            PublicSuffixMatcher publicSuffixMatcher = PublicSuffixMatcherLoader.load(new URL(httpGet.getURI().toString()));  
            DefaultHostnameVerifier hostnameVerifier = new DefaultHostnameVerifier(publicSuffixMatcher);  
            httpClient = HttpClients.custom().setSSLHostnameVerifier(hostnameVerifier).build();  
            httpGet.setConfig(requestConfig);  
            // ִ������  
            response = httpClient.execute(httpGet);  
            entity = response.getEntity();  
            responseContent = EntityUtils.toString(entity, "UTF-8");  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                // �ر�����,�ͷ���Դ  
                if (response != null) {  
                    response.close();  
                }  
                if (httpClient != null) {  
                    httpClient.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return responseContent;  
    }  
    
    
    public static void main(String args[]) { 
       /*
       oa us=new oa();
      String spnumber=us.getUser();
       String pwd=us.getPassword();
       String mobile_6="15007572525";
       String content_6="test";
       String wenxing="����";
       
       String key1="";
       //key1=value1&key2=value2
       try
		{
			for(int i=0;i<1;i++)
			{
			
       String responseContent = HttpClientUtil.getInstance()  
               .sendHttpPost("http://61.142.131.10:888/OaListenerServlet","mType="+spnumber+"&mPassword="+pwd+"&phones="+mobile_6+"&content="+content_6+wenxing);  
        System.out.println("reponse content:" + responseContent);
        }}
        catch(Exception e)
		{
			System.out.println(e.toString());
		}
		*/
    }
    }

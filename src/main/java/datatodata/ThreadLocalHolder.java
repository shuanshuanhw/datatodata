package datatodata;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * 功能： <p></p>
 * 创建人：黄维
 * 时间：2023/3/8 10:45
 */
public class ThreadLocalHolder {
    private static String accessToken = null;
    private static int expiresIn = 7200*1000;
    private static long startTime;
    static
    {
        get();
        startTime = new Date().getTime();
    }

    public static String getAccessToken() {
        long endTime = new Date().getTime();
        if(StringUtils.isNotEmpty(accessToken) && endTime - startTime < expiresIn)
        return accessToken;
        else
        {
            get();
            return accessToken;
        }
    }

    private static void get()
    {
        HttpResponse execute = HttpRequest.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx067c77c0807c631f&secret=18847f1bdeb85293f2f335ed04eb9fdb").execute();
        cn.hutool.json.JSONObject body = JSONUtil.parseObj(execute.body());
        String errmsg = (String)body.get("errmsg");
        String access_token = null;
        int expires_in = 0;
        if(StringUtils.isEmpty(errmsg))
        {
            access_token = (String) body.get("access_token");
            expires_in = (int)body.get("expires_in");
            accessToken = access_token;
            expiresIn = expires_in;
        }
    }

    /**
     * <p>todo 发送相关文本消息到指定微信号</p>
     * @param accessToken
     * @param openId
     * @param content
     * @return {@link String}
     * <p>author 黄维</p>
     * createTime  2023/3/8 15:36
     */
    public static String sendMessageByOpenId(String accessToken,String openId,String content)
    {
        JSONObject data = new JSONObject(true);
        data.put("touser",openId);
        data.put("msgtype", "text");

        JSONObject text = new JSONObject();
        text.put("content", content);
        data.put("text", text);

        String json = JSON.toJSONString(data);
        String result = HttpUtils.postJson("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+accessToken, json);
        return result;
    }

    public static String sendMessageByOpenIds(String accessToken,String[] openIds,String content)
    {
        JSONObject data = new JSONObject(true);
        data.put("touser",openIds);
        data.put("msgtype","text");

        JSONObject text = new JSONObject();
        text.put("content",content);

        data.put("text",text);

        String json = data.toJSONString();
        System.out.println(json);
        String result = HttpUtils.postJson("https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token="+accessToken, json);

        return result;
    }
    /**
     * <p>todo 取所有关注的微信的openid</p>
     * @param accessToken
     * @return {@link String[]}
     * <p>author 黄维</p>
     * createTime  2023/3/8 15:37
     */
    public static Object[] getUserOpenId(String accessToken)
    {
        // https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID
        // 第一个拉取的OPENID，不填默认从头开始拉取
        HttpResponse execute = HttpRequest.get("https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + accessToken).execute();
        cn.hutool.json.JSONObject json = JSONUtil.parseObj(execute.body());
        if(StringUtils.isEmpty((CharSequence) json.get("errmsg")))
        {
            Map<String,Object> data = (Map<String, Object>) json.get("data");
            JSONArray result = (JSONArray) data.get("openid");
            if(result.size() == 0)
            {
                return new String[]{};
            }
            Object[] openIds = result.stream().toArray();
//            Arrays.stream(objects).forEach(obj->{
//                System.out.println(obj);
//            });
            return openIds;
        }
        else
        {
            System.out.println(json.get("errmsg"));
            return null;
        }

    }
}

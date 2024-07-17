package datatodata;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import datatodata.db.ConnectDB;

import java.sql.Connection;

/**
 * 功能： <p></p>
 * 创建人：黄维
 * 时间：2023/12/29 11:55
 */
public class WXUtils {

    public static void main(String[] args) {
//        WXUtils.sendTemplateMessage("oa7HK5-kxBFgpyDM9s2iizpuS8PQ","XVxZjetXFR8C_Is8-N3Tw_8K-GQ9L5SE0-17lEwiwpk","0000000000","置身事内等","2023年10月19日","22808600");
    }

    public static boolean sendMessage()
    {
        Connection connection;
        connection = ConnectDB.getConnection("localhost", "sms", "root", "651392qQ");

        return false;
    }
    // 发送模板消息，读者借阅文献滞留通知 读者证号 文献题名 应还日期 咨询电话
    public static int sendTemplateMessage(String openId,String templateId,String name,String readerId,String title,String returnDate)
    {
            String accessToken = ThreadLocalHolder.getAccessToken();
            // 显示
            System.out.println("accessToken:"+accessToken);
            JSONObject map = new JSONObject();
            map.set("touser", openId);
            map.set("template_id", templateId);
            JSONObject data = new JSONObject();
            JSONObject keyword0 = new JSONObject();
            JSONObject keyword1 = new JSONObject();
            JSONObject keyword2 = new JSONObject();
            JSONObject keyword3 = new JSONObject();
            JSONObject keyword4 = new JSONObject();
            keyword0.set("value", name);
            keyword1.set("value", readerId);
            keyword2.set("value", title);
            keyword3.set("value", returnDate);
            keyword4.set("value", "22808600");

            data.set("thing7", keyword0);
            data.set("character_string1", keyword1);
            data.set("thing2", keyword2);
            data.set("time6", keyword3);
            data.set("phone_number5", keyword4);


//            System.out.println(data);

            map.set("data", data);
//            System.out.println(map);

            HttpRequest form = HttpRequest.post("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken)
                    .header("Content-Type", "application/json; encoding=utf-8")
                    .body(JSON.toJSONString(map));
            // 显示form的内容
//            System.out.println(form);
            HttpResponse execute = form.execute();
//            System.out.println(execute);
            System.out.println("发送状态："+execute.getStatus());
            JSONObject jsonObject = JSONUtil.parseObj(execute.body());
            System.out.println(jsonObject);

            return execute.getStatus();

    }

}

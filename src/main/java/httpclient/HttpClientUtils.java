package httpclient;

import cn.hutool.http.HttpRequest;

/**
 * 功能： <p></p>
 * 创建人：黄维
 * 时间：2023/11/28 16:23
 */
public class HttpClientUtils {


    public static void main(String[] args) {
        String body = HttpRequest.get("http://www.njjnlib.cn/news/index/id/15").execute().body();
        System.out.println(body);
    }
}

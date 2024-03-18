package datatodata;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import datatodata.entity.ov.OverdueBookOV;
import datatodata.entity.ov.OverdueBookOVStatus;
import datatodata.entity.ov.ReaderAuthOV;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能： <p>20240126更新</p>
 * 创建人：黄维
 * 时间：2024/1/5 9:17
 */
public class IlasUtilsJson {

    private String Passtoken = "C7974CBB09F34A02816F96533D936EA2SD001";
    private String appid = " SD00102";
    private String passid = "SD001";
    private String nonce = "111555";
    private long times = 0L;
    private String timestamp = "";
    private String sn="";
    // 取时间戳
    private void getTimestamp()
    {
        this.times = System.currentTimeMillis();
        this.timestamp = String.valueOf(this.times);
        this.sn = sn=EncryptSha256Util.getSha256Str(this.timestamp+this.Passtoken+this.nonce+this.passid+this.Passtoken+this.timestamp);

    }


    public OverdueBookOVStatus queryOverdueBooks(String libcode, String days, String page, String pageSize)
    {
        OverdueBookOVStatus sd002 = null;
        List<OverdueBookOV> list = new ArrayList<>();
        do{
            sd002 = queryOverdueBook(libcode, days, page, pageSize);
            list.addAll(sd002.getDatas());
            System.out.println(list.size());
            // 将page 加1
            page = String.valueOf(Integer.parseInt(page)+1);
        }while (500 == sd002.getDatas().size());

        sd002.setDatas(list);
        return sd002;
    }
    //过期文献信息查询接口
    public OverdueBookOVStatus queryOverdueBook(String libcode, String days, String page, String pageSize) {
        // 取当前服务器的时间戳
        getTimestamp();
        HttpRequest httpRequest = HttpRequest.get("http://202.105.30.27:6082/onlineLoan/webservice/queryOverdueBooks?libcode="+libcode+"&days="+days+"&pageno="+page+"&pagecount="+pageSize);
        httpRequest.contentType("application/json");
        httpRequest.header("passid",this.passid);
        httpRequest.header("Passtoken",this.Passtoken);
        httpRequest.header("timestamp",this.timestamp);
        httpRequest.header("sn",this.sn);
        httpRequest.header("nonce",this.nonce);
        httpRequest.header("appid",this.appid);

        HttpResponse execute = httpRequest.execute();
        String body = execute.body();
        JSONObject json = JSONUtil.parseObj(body);
//        System.out.println(json);
        String code = json.get("code").toString();
        OverdueBookOVStatus overdueBookOVStatus = new OverdueBookOVStatus();
        overdueBookOVStatus.setCode(code);
        overdueBookOVStatus.setMessage(json.get("message").toString());
        if("1".equals(code))
        {
            Object data = json.get("data");
            JSONObject jsonObject = JSONUtil.parseObj(data);
            String count = jsonObject.get("count").toString();
            String page1 = jsonObject.get("page").toString();
            JSONArray jsonArray = JSONUtil.parseArray(jsonObject.get("overdueBooks"));
//            System.out.println(jsonArray);
            List<OverdueBookOV> overdueBookOVS = JSONUtil.toList(jsonArray, OverdueBookOV.class);
            overdueBookOVStatus.setDatas(overdueBookOVS);
            overdueBookOVStatus.setCount(count);
            overdueBookOVStatus.setPage(page1);
            System.out.println("查询成功");
        }
        else
        {
            System.out.println("查询失败");
        }
        return overdueBookOVStatus;
    }
    // 读者信息查询接口
     public ReaderAuthOV readerAuth(String identifier, String password)
    {
        // 取当前服务器的时间戳
        getTimestamp();
        HttpRequest httpRequest = HttpRequest.post("http://202.105.30.27:6082/onlineLoan/webservice/readerAuth");
        httpRequest.contentType("application/json");
        httpRequest.header("passid",this.passid);
        httpRequest.header("Passtoken",this.Passtoken);
        httpRequest.header("timestamp",this.timestamp);
        httpRequest.header("sn",this.sn);
        httpRequest.header("nonce",this.nonce);
        httpRequest.header("appid",this.appid);
        Map<String,Object> paramMap = new HashMap();
        // 如果identifier是读者证号，那么idType=0，如果是身份证号，那么idType=1
        if(identifier.length()==10)
        {
            paramMap.put("idType","0");
        }
        else
        {
            paramMap.put("idType","1");
        }
        paramMap.put("identifier",identifier);
        paramMap.put("password",password);

        JSONObject jsonObject = new JSONObject(paramMap);
        httpRequest.body(jsonObject.toString());

        HttpResponse execute = httpRequest.execute();
        String body = execute.body();
        JSONObject json = JSONUtil.parseObj(body);
        System.out.println(json);
        ReaderAuthOV readerAuthOV = new ReaderAuthOV();
        Integer code = (Integer) json.get("code");

        // 如果code=1，那么说明查询成功
        if(code == 1)
        {
            JSONObject data = (JSONObject) json.get("data");
            String cardNo = (String) data.get("cardNo");
            String rdrName = (String) data.get("rdrName");
            readerAuthOV.setCardNo(cardNo);
            readerAuthOV.setRdrName(rdrName);
            readerAuthOV.setIfSuccess("1");
        }
        else
        {
            readerAuthOV.setIfSuccess("0");
        }

        return readerAuthOV;

    }

    // 读者信息查询接口 知道读者证号，可以直接不需要密码查询
    public ReaderAuthOV queryReaderInfo(String identifier)
    {
        // 取当前服务器的时间戳
        getTimestamp();
        HttpRequest httpRequest = HttpRequest.get("http://202.105.30.27:6082/onlineLoan/webservice/queryReaderInfo?identifier="+identifier);
        httpRequest.contentType("application/json");
        httpRequest.header("passid",this.passid);
        httpRequest.header("Passtoken",this.Passtoken);
        httpRequest.header("timestamp",this.timestamp);
        httpRequest.header("sn",this.sn);
        httpRequest.header("nonce",this.nonce);
        httpRequest.header("appid",this.appid);

        HttpResponse execute = httpRequest.execute();
        String body = execute.body();
        JSONObject json = JSONUtil.parseObj(body);

        String code = json.get("code").toString();
        String message = json.get("message").toString();
        if("1".equals(code))
        {
            Object data = json.get("data");
            JSONObject jsonObject = JSONUtil.parseObj(data);
            String patronIdentifier = jsonObject.get("patronIdentifier").toString();
            String patronName = jsonObject.get("patronName").toString();
            System.out.println("查询成功");
            ReaderAuthOV readerAuthOV = new ReaderAuthOV();
            readerAuthOV.setCardNo(patronIdentifier);
            readerAuthOV.setRdrName(patronName);
            readerAuthOV.setIfSuccess("1");
            return readerAuthOV;
        }
        else
        {
            System.out.println("查询失败");
            ReaderAuthOV readerAuthOV = new ReaderAuthOV();
            readerAuthOV.setIfSuccess("0");
            return readerAuthOV;
        }
    }
    public static void main(String[] args) {
        IlasUtilsJson ilasUtilsJson = new IlasUtilsJson();
//        ReaderAuthOV readerAuthOV = ilasUtilsJson.readerAuth("44152198302170012", "170012");
//        System.out.println(readerAuthOV);


        OverdueBookOVStatus sd001 = ilasUtilsJson.queryOverdueBooks("SD001", "-5", "1", "500");
        System.out.println(sd001);
        ReaderAuthOV readerAuthOV = ilasUtilsJson.queryReaderInfo("9130100100");
        System.out.println(readerAuthOV);
    }
}

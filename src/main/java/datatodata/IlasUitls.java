package datatodata;

import cn.hutool.http.Header;
import cn.hutool.http.webservice.SoapClient;
import cn.hutool.http.webservice.SoapProtocol;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import datatodata.entity.ilas.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能： <p></p>
 * 创建人：黄维
 * 时间：2023/5/10 16:12
 */
public class IlasUitls {

    // 第三方系统向图书馆系统发起读者认证请求，图书馆系统收到认证请求后，返回认证结果,就是读者证号，这个读者证号可以用来查询读者信息
    public static String readerAuth(String idType,String identifier,String password)
    {
        String apiURL = "http://202.105.30.39:8280/fslibNew5U/readerAuth";
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("idType", idType);
        map.put("identifier", identifier);
        map.put("password", password);
        SoapClient client = SoapClient.create(apiURL).init(SoapProtocol.SOAP_1_2)
                .setMethod("impl:readerAuth", "http://impl.services.webservice.ilas.com")
                .setParams(map)
                .header(Header.AUTHORIZATION, "Basic U0QwMDEubGliOjE1OSMjc2QwMDE6ZjMwNTg4ZmU2ZTZiNDIyMTliMDU5NDQxNjhmMWRlZDg=")
                .header(Header.CONTENT_TYPE, "text/html");
        String body = client.send(true);
        JSONObject json = JSONUtil.parseFromXml(body);
        JSONObject soapenvEnvelope = (JSONObject) json.get("soapenv:Envelope");
        JSONObject soapenvBody= (JSONObject) soapenvEnvelope.get("soapenv:Body");
        JSONObject nsReaderAuthResponse = (JSONObject) soapenvBody.get("ns:readerAuthResponse");
        Integer nsReturn = (Integer) nsReaderAuthResponse.get("ns:return");

        return String.valueOf(nsReturn);
    }

    // 读者信息查询
    /**
     * <p>todo </p>
     * @param readerNumber
     * @return {@link }
     * <p>author 黄维</p>
     * createTime  2023/5/11 9:23
     */
    public static IlasUser queryReaderInfo(String readerNumber, String identifier, String password) {
        String apiURL = "http://202.105.30.39:8280/fslibNew5U/queryReaderInfo";
        SoapClient client = SoapClient.create(apiURL).init(SoapProtocol.SOAP_1_2)
                .setMethod("impl:queryReaderInfo", "http://impl.services.webservice.ilas.com")
                .setParam("identifier", readerNumber)
                .header(Header.AUTHORIZATION, "Basic U0QwMDEubGliOjE1OSMjc2QwMDE6YWYzMTYyODJiOGM5NGI5NWJhZmQzMjRiMzhhM2Q2ZTM=")
                .header(Header.CONTENT_TYPE, "text/html");
        String body = client.send(true);
        JSONObject json = JSONUtil.parseFromXml(body);
        JSONObject soapenvEnvelope = (JSONObject) json.get("soapenv:Envelope");
        JSONObject soapenvBody= (JSONObject) soapenvEnvelope.get("soapenv:Body");
        JSONObject queryReaderInfoResponse = (JSONObject) soapenvBody.get("ns:queryReaderInfoResponse");
        JSONObject nsReturn = (JSONObject) queryReaderInfoResponse.get("ns:return");
        IlasUser ilasUser = new IlasUser();
        ilasUser.setAddress(nsReturn.getStr("ax21:address"));
        ilasUser.setLibcode(nsReturn.getStr("ax21:libcode"));
        ilasUser.setInstitutionId(nsReturn.getStr("ax21:institutionId"));
        ilasUser.setPatronCirculationType(nsReturn.getStr("ax21:patronCirculationType"));
        ilasUser.setPatronIdentifier(nsReturn.getStr("ax21:patronIdentifier"));
        ilasUser.setPatronName(nsReturn.getStr("ax21:patronName"));
        ilasUser.setPhone(nsReturn.getStr("ax21:phone"));
        ilasUser.setSex(nsReturn.getStr("ax21:sex"));
        return ilasUser;
    }


    // * 图书列表。
    static public List<IlasCollecitonInfo> queryCollectionInfo(String bookRecNO){// 书目号
        String apiURL = "http://202.105.30.39:8280/fslibNew5U/queryCollectionInfo";
        SoapClient client = SoapClient.create(apiURL).init(SoapProtocol.SOAP_1_2)
                .setMethod("impl:queryCollectionInfo", "http://impl.services.webservice.ilas.com")
                .setParam("bookRecNO", bookRecNO)
//
                .header(Header.AUTHORIZATION, "Basic U0QwMDEubGliOjE1OSMjc2QwMDE6NGVlMTUwYWVlOGEzNGM5YmEyODhlMzFhM2M1NGFlZWY=")
                .header(Header.CONTENT_TYPE, "text/html");
        String body = client.send(true);

//        System.out.println(body);
        JSONObject json = JSONUtil.parseFromXml(body);
//        System.out.println(json);
        JSONObject soapenvEnvelope = (JSONObject) json.get("soapenv:Envelope");
//        System.out.println(soapenvEnvelope);
        JSONObject soapenvBody= (JSONObject) soapenvEnvelope.get("soapenv:Body");
//        System.out.println(soapenvBody);
        JSONObject queryAdvancedBookInfoResponse = (JSONObject) soapenvBody.get("ns:queryCollectionInfoResponse");
//        System.out.println(queryAdvancedBookInfoResponse);
        JSONArray nsReturn = (JSONArray) queryAdvancedBookInfoResponse.get("ns:return");
        // 遍历nsReturn
        List<IlasCollecitonInfo> ilasCollecitonInfos = new ArrayList<>();
        for (int i = 0; i < nsReturn.size(); i++) {
            JSONObject jsonObject = (JSONObject) nsReturn.get(i);
            IlasCollecitonInfo ilasCollecitonInfo = new IlasCollecitonInfo();
            ilasCollecitonInfo.setBookRecNO(jsonObject.getStr("ax21:bookRecNO"));
            ilasCollecitonInfo.setBarCode(jsonObject.getStr("ax21:barCode"));
            ilasCollecitonInfo.setCirType(jsonObject.getStr("ax21:cirType"));
            ilasCollecitonInfo.setCurLib(jsonObject.getStr("ax21:curLib"));
            ilasCollecitonInfo.setCurLocal(jsonObject.getStr("ax21:curLocal"));
            ilasCollecitonInfo.setOrgLib(jsonObject.getStr("ax21:orgLib"));
            ilasCollecitonInfo.setOrgLocal(jsonObject.getStr("ax21:orgLocal"));
            ilasCollecitonInfo.setStatus(jsonObject.getStr("ax21:status"));
            ilasCollecitonInfo.setTitle(jsonObject.getStr("ax21:title"));
            ilasCollecitonInfo.setVolInfo(jsonObject.getStr("ax21:volInfo"));

            ilasCollecitonInfos.add(ilasCollecitonInfo);
        }

//        JSONArray ax21Books = (JSONArray) nsReturn.get("ax21:books");
//        System.out.println(ax21Books);

        return ilasCollecitonInfos;
    }

    // 读者通过接口查询图书馆藏信息，图书馆返回图书的相关简单信息用以页面列表显示，接口返回
    static public List<IlasCollecitonInfo> queryCollectionInfoFinal(String bookRecNO) {// 书目号
        List<IlasCollecitonInfo> ilasCollecitonInfos = queryCollectionInfo(bookRecNO);
        List<IlasCollecitonInfo> all = new ArrayList<>();
        String[] libids = { "SD001", "SD002", "SD003", "SD005", "SD006", "SD007", "SD008", "SD009", "SD010",
                "SD011", "SD012", "DX001" }; // 图书馆代码
        // 遍历结果
        ilasCollecitonInfos.stream().iterator().forEachRemaining(item->{

            for(String libid : libids)
            {
                if(libid.equals(item.getCurLib()))
                {
                    all.add(item);
                }
            }
        });
        // 显示结果
        all.stream().forEach(System.out::println);

        return all;
    }

    //图书高级检索接口
    static public List<IlasBook> queryAdvancedBookInfo(String title)
    {
        String apiURL = "http://202.105.30.39:8280/fslibNew5U/queryAdvancedBookInfo";
        SoapClient client = SoapClient.create(apiURL).init(SoapProtocol.SOAP_1_2)
                .setMethod("impl:queryAdvancedBookInfo", "http://impl.services.webservice.ilas.com")
                .setParam("institutionId", "ALL")
                .setParam("titleIdentifier", title)
                .setParam("pageno", 0)
                .setParam("pagecount",10)
//
                .header(Header.AUTHORIZATION, "Basic U0QwMDEubGliOjE1OSMjc2QwMDE6MTdlZWZjMTA2ZTI1NDc2ZTlhZTM0OGZjMmU5OWIzMjQ=")
                .header(Header.CONTENT_TYPE, "text/html");
        String body = client.send(true);

//        System.out.println(body);
        JSONObject json = JSONUtil.parseFromXml(body);
//        System.out.println(json);
        JSONObject soapenvEnvelope = (JSONObject) json.get("soapenv:Envelope");
//        System.out.println(soapenvEnvelope);
        JSONObject soapenvBody= (JSONObject) soapenvEnvelope.get("soapenv:Body");
//        System.out.println(soapenvBody);
        JSONObject queryAdvancedBookInfoResponse = (JSONObject) soapenvBody.get("ns:queryAdvancedBookInfoResponse");
//        System.out.println(queryAdvancedBookInfoResponse);
        JSONObject nsReturn = (JSONObject) queryAdvancedBookInfoResponse.get("ns:return");
        System.out.println(nsReturn);
        JSONObject ax21Books = (JSONObject) nsReturn.get("ax21:books");
//        System.out.println("ax21Books:"+ax21Books);

        JSONObject jsonObject = (JSONObject) ax21Books;
        IlasBook ilasBook = new IlasBook();
        ilasBook.setAuthor(jsonObject.getStr("ax21:author"));
        ilasBook.setBookRecNO(jsonObject.getStr("ax21:bookRecNO"));
        ilasBook.setCallNO(jsonObject.getStr("ax21:callNO"));
        ilasBook.setClassNO(jsonObject.getStr("ax21:classNO"));
        ilasBook.setPublisher(jsonObject.getStr("ax21:publisher"));
        ilasBook.setTitleIdentifier(jsonObject.getStr("ax21:titleIdentifier"));
        List<IlasBook> ilasBooks = new ArrayList<IlasBook>();
        ilasBooks.add(ilasBook);

//        ax21Books.stream().iterator().forEachRemaining(jb->{
//            JSONObject jsonObject = (JSONObject) jb;
//            IlasBook ilasBook = new IlasBook();
//            ilasBook.setAuthor(jsonObject.getStr("ax21:author"));
//            ilasBook.setBookRecNO(jsonObject.getStr("ax21:bookRecNO"));
//            ilasBook.setCallNO(jsonObject.getStr("ax21:callNO"));
//            ilasBook.setClassNO(jsonObject.getStr("ax21:classNO"));
//            ilasBook.setPublisher(jsonObject.getStr("ax21:publisher"));
//            ilasBook.setTitleIdentifier(jsonObject.getStr("ax21:titleIdentifier"));
//
//            ilasBooks.add(ilasBook);
//        });
//        System.out.println(ilasBooks);
        return ilasBooks;
    }

    //图书馆过期图书查询接口
    static public List<IlasOverBook> queryOverdueBooks(String libcode, int days, int page, int size)
    {
//        String apiURL = "http://202.105.30.27:6086/fslibNew5U/queryOverdueBooks";
        String apiURL = "http://202.105.30.39:8280/fslibNew5U/queryOverdueBooks";
        SoapClient client = SoapClient.create(apiURL).init(SoapProtocol.SOAP_1_2)
                .setMethod("impl:queryOverdueBooks", "http://impl.services.webservice.ilas.com")
                .setParam("libcode", libcode)
                // 负数为提前天数，正数为延后天数
                .setParam("days", days)
                .setParam("page", page)
                .setParam("size",size)
//
                .header(Header.AUTHORIZATION, "Basic U0QwMDEubGliOjE1OSMjc2QwMDE6YTMwZDkwYmM2Y2IwNDk0N2E0M2E5OGM0NzQ3MzJhOTk=")
                .header(Header.CONTENT_TYPE, "text/html");
//        System.out.println("1");
//        System.out.println("3");
        String body = client.send(true);
//        System.out.println(body);
//        System.out.println("2");
        JSONObject json = JSONUtil.parseFromXml(body);
//        System.out.println(json);
        JSONObject soapenvEnvelope = (JSONObject) json.get("soapenv:Envelope");
//        System.out.println(soapenvEnvelope);
        JSONObject soapenvBody= (JSONObject) soapenvEnvelope.get("soapenv:Body");
//        System.out.println(soapenvBody);

        JSONObject queryOverdueBooksResponse = (JSONObject) soapenvBody.get("ns:queryOverdueBooksResponse");
//        System.out.println(queryOverdueBooksResponse);
        JSONObject nsReturn = (JSONObject) queryOverdueBooksResponse.get("ns:return");
//        System.out.println("nsReturn:"+nsReturn);
        JSONArray ax21OverdueBooks = null;
        JSONObject ax21OverdueBooks1 = null;

        try{
            ax21OverdueBooks = (JSONArray) nsReturn.get("ax21:overdueBooks");
        }catch (ClassCastException ex)
        {
            ax21OverdueBooks1 = (JSONObject) nsReturn.get("ax21:overdueBooks");
        }
//        System.out.println(ax21OverdueBooks);
//        System.out.println(ax21OverdueBooks1);
//        System.out.println("3");
//       // 存储过期图书信息列表
        List<IlasOverBook> ilasOverBooks = new ArrayList<>();
        if(ax21OverdueBooks1!=null)
        {
            // 说明只有一条记录
            IlasOverBook ilasOverBook = new IlasOverBook();
            ilasOverBook.setBarCode(ax21OverdueBooks1.getStr("ax21:barCode"));
            ilasOverBook.setCallno(ax21OverdueBooks1.getStr("ax21:callno"));
            ilasOverBook.setIdentifier(ax21OverdueBooks1.getStr("ax21:identifier"));
            ilasOverBook.setLoanCount(ax21OverdueBooks1.getStr("ax21:loanCount"));
            ilasOverBook.setLoanDate(ax21OverdueBooks1.getStr("ax21:loanDate"));
            ilasOverBook.setOrgLib(ax21OverdueBooks1.getStr("ax21:orgLib"));
            ilasOverBook.setOrgLocal(ax21OverdueBooks1.getStr("ax21:orgLocal"));
            ilasOverBook.setPhone(ax21OverdueBooks1.getStr("ax21:phone"));
            ilasOverBook.setReturnTime(ax21OverdueBooks1.getStr("ax21:returnTime"));
            ilasOverBook.setTitle(ax21OverdueBooks1.getStr("ax21:title"));
            ilasOverBooks.add(ilasOverBook);
        }
        if(ax21OverdueBooks!=null)
        {
            // 说明有多条记录
            ax21OverdueBooks.stream().iterator().forEachRemaining(item->{
                JSONObject jsonObject = (JSONObject) item;
                IlasOverBook ilasOverBook = new IlasOverBook();
                ilasOverBook.setBarCode(jsonObject.getStr("ax21:barCode"));
                ilasOverBook.setCallno(jsonObject.getStr("ax21:callno"));
                ilasOverBook.setIdentifier(jsonObject.getStr("ax21:identifier"));
                ilasOverBook.setLoanCount(jsonObject.getStr("ax21:loanCount"));
                ilasOverBook.setLoanDate(jsonObject.getStr("ax21:loanDate"));
                ilasOverBook.setOrgLib(jsonObject.getStr("ax21:orgLib"));
                ilasOverBook.setOrgLocal(jsonObject.getStr("ax21:orgLocal"));
                ilasOverBook.setPhone(jsonObject.getStr("ax21:phone"));
                ilasOverBook.setReturnTime(jsonObject.getStr("ax21:returnTime"));
                ilasOverBook.setTitle(jsonObject.getStr("ax21:title"));
                ilasOverBooks.add(ilasOverBook);
            });
        }

//        System.out.println(ilasOverBooks);

        return ilasOverBooks;
    }

    /**
     * <p>todo 通过条码查询是否可借阅接口</p>
     * @param barcode 文献条码
     * @param identifier 读者证号
     * @param duelib 馆代码
     * @return {@link boolean}
     * <p>author 黄维</p>
     * createTime  2023/5/11 14:24
     */
    static public IlasIfCanLoan canLoanbook(String barcode, String identifier, String duelib) {
        String apiURL = "http://202.105.30.39:8280/fslibNew5U/canLoanbook";
        SoapClient client = SoapClient.create(apiURL).init(SoapProtocol.SOAP_1_2)
                .setMethod("impl:canLoanBook", "http://impl.services.webservice.ilas.com")
                .setParam("identifier", identifier)
                .setParam("barcode", barcode)
                .setParam("duelib", duelib)
                .header(Header.AUTHORIZATION, "Basic U0QwMDEubGliOjE1OSMjc2QwMDE6OGM0MjM5OWEzYmJkNDM3ZTk2MjA3ZDA3NDQwMmFmY2E=")
                .header(Header.CONTENT_TYPE, "text/html");
        String body = client.send(true);
        System.out.println(body);

        JSONObject json = JSONUtil.parseFromXml(body);
        System.out.println(json);
        JSONObject soapenvEnvelope = (JSONObject) json.get("soapenv:Envelope");
        System.out.println(soapenvEnvelope);
        JSONObject soapenvBody= (JSONObject) soapenvEnvelope.get("soapenv:Body");
        System.out.println(soapenvBody);
        JSONObject canLoanBookResponse = (JSONObject) soapenvBody.get("ns:canLoanBookResponse");
        System.out.println(canLoanBookResponse);
        JSONObject nsReturn = (JSONObject) canLoanBookResponse.get("ns:return");
        System.out.println(nsReturn);
        IlasIfCanLoan ilasIfCanLoan = new IlasIfCanLoan();
        ilasIfCanLoan.setBarcode(nsReturn.getStr("ax21:barcode"));
        ilasIfCanLoan.setOK(nsReturn.getStr("ax21:ok"));
        ilasIfCanLoan.setIdentifier(nsReturn.getStr("ax21:identifier"));
        ilasIfCanLoan.setScreenMessage(nsReturn.getStr("ax21:screenMessage"));

        System.out.println(ilasIfCanLoan);
        return ilasIfCanLoan;
    }



    static public boolean updateUserInfo(String identifier,String phone)
    {
        String apiURL = "http://202.105.30.39:8280/fslibUnifiedAuthentication/updateUserInfo";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("loginName",identifier);
        jsonObject.put("phone",phone);
        SoapClient client = SoapClient.create(apiURL).init(SoapProtocol.SOAP_1_2)
                .setMethod("impl:updateUserInfo", "http://impl.services.webservice.ilas.com")
                .setParam("datas", jsonObject)
//                .setParam("phone", phone)
                .header(Header.AUTHORIZATION, "Basic U0QwMDEubGliOjE1OSMjc2QwMDE6MTc5ZGY0ZmI3MWQ5NGY0NWFkN2Q5MTIwNDkwNWM2ODI=")
                .header(Header.CONTENT_TYPE, "text/html");
        String body = client.send(true);
        System.out.println(body);
        return false;
    }

    static  public boolean queryHoldingInfo()
    {

        return false;
    }



    /**
     * <p>todo 违约金查询接口</p>
     * @param identifier 读者证号
     * @return {@link float}
     * <p>author 黄维</p>
     * createTime  2023/5/11 16:12
     */
    static public float queryPenaltyInfo(String identifier)
    {
        String apiURL = "http://202.105.30.39:8280/fslibNew5U/queryPenaltyInfo";
        SoapClient client = SoapClient.create(apiURL).init(SoapProtocol.SOAP_1_2)
                .setMethod("impl:queryPenaltyInfo", "http://impl.services.webservice.ilas.com")
                .setParam("identifier", identifier)
                .header(Header.AUTHORIZATION, "Basic U0QwMDEubGliOjE1OSMjc2QwMDE6NTY4YTMxYTBiNzNmNDlkMDgxMzJmMjM5MDNmOTgwMjg=")
                .header(Header.CONTENT_TYPE, "text/html");
        String body = client.send(true);
        System.out.println(body);
        JSONObject json = JSONUtil.parseFromXml(body);
        System.out.println(json);
        JSONObject soapenvEnvelope = (JSONObject) json.get("soapenv:Envelope");
        System.out.println(soapenvEnvelope);
        JSONObject soapenvBody= (JSONObject) soapenvEnvelope.get("soapenv:Body");
        System.out.println(soapenvBody);
        JSONObject queryPenaltyInfoResponse = (JSONObject) soapenvBody.get("ns:queryPenaltyInfoResponse");
        System.out.println(queryPenaltyInfoResponse);
        JSONObject nsReturn = (JSONObject) queryPenaltyInfoResponse.get("ns:return");
        System.out.println(nsReturn);
        AtomicInteger totalFee = new AtomicInteger();
        try{
            Object feeInfo = nsReturn.get("ax21:feeInfo");
            Class<?> aClass = feeInfo.getClass();
            String simpleName = aClass.getSimpleName();
            if("JSONArray".equals(simpleName))
            {
                JSONArray feeInfoArray = (JSONArray) feeInfo;
                feeInfoArray.stream().iterator().forEachRemaining(item->{
                    JSONObject jsonObject = (JSONObject) item;
                    System.out.println(jsonObject);
                    int fee = (int) jsonObject.get("ax21:fee");
                    totalFee.addAndGet(fee);
                });
            }
            else
            {
                JSONObject jsonObject = (JSONObject) feeInfo;
                System.out.println(jsonObject);
                int fee = (int) jsonObject.get("ax21:fee");
                totalFee.addAndGet(fee);
            }
        }catch (NullPointerException ex)
        {
            System.out.println("没有欠费信息");
            return 0;
        }

        return totalFee.get();
    }


    /**
     * <p>todo 获取时间范围内的办证读者信息，包括证号、登录密码、读者姓名、身份证、性别、证件类型、
     * 所属馆 ID、启用日期，停用日期，地址，手机号码</p>
     * @param institutionId
     * @param begDate
     * @param endDate
     * @param pageno
     * @param pagecount
     * @return
     * <p>author 黄维</p>
     * createTime  2023/5/12 14:34
     */
    static public List<IlasRegisterReader> queryRegisterList(String institutionId, String begDate, String endDate, int pageno, int pagecount)
    {
        String apiURL = "http://202.105.30.39:8280/fslibNew5U/queryRegisterList";
        SoapClient client = SoapClient.create(apiURL).init(SoapProtocol.SOAP_1_2)
                .setMethod("impl:queryRegisterList", "http://impl.services.webservice.ilas.com")
                .setParam("institutionId", institutionId)
                .setParam("begDate", begDate)
                .setParam("endDate", endDate)
                .setParam("pageno", pageno)
                .setParam("pagecount", pagecount)
                .header(Header.AUTHORIZATION, "Basic U0QwMDEubGliOjE1OSMjc2QwMDE6NzJlOTYzOTc3NGVhNDAxY2FlYmYzM2Q5MmMzODgzMTA=")
                .header(Header.CONTENT_TYPE, "text/html");
        String body = client.send(true);
        System.out.println(body);

        JSONObject json = JSONUtil.parseFromXml(body);
        System.out.println(json);
        JSONObject soapenvEnvelope = (JSONObject) json.get("soapenv:Envelope");
        System.out.println(soapenvEnvelope);
        JSONObject soapenvBody= (JSONObject) soapenvEnvelope.get("soapenv:Body");
        System.out.println(soapenvBody);

        JSONObject queryRegisterListResponse = (JSONObject) soapenvBody.get("ns:queryRegisterListResponse");
        System.out.println(queryRegisterListResponse);
        JSONObject nsReturn = (JSONObject) queryRegisterListResponse.get("ns:return");
        System.out.println(nsReturn);

        int curpage = (int) nsReturn.get("ax21:curpage");
        int itemCount = (int) nsReturn.get("ax21:itemCount");
        int pagerows = (int) nsReturn.get("ax21:pagerows");
        int pages = (int) nsReturn.get("ax21:pages");
        JSONArray registerList = (JSONArray) nsReturn.get("ax21:readers");

        List<IlasRegisterReader> ilasRegisterReaders = new ArrayList<>();
        System.out.println(registerList);
        registerList.stream().iterator().forEachRemaining(item->{
            IlasRegisterReader ilasRegisterReader = new IlasRegisterReader();
            JSONObject jsonObject = (JSONObject) item;
            System.out.println(jsonObject);
            ilasRegisterReader.setCertify(jsonObject.get("ax21:certify").toString());
            ilasRegisterReader.setAddress(jsonObject.get("ax21:address").toString());
            ilasRegisterReader.setMobile(jsonObject.get("ax21:mobile").toString());
            ilasRegisterReader.setLibcode(jsonObject.get("ax21:libcode").toString());
            ilasRegisterReader.setPatronIdentifier(jsonObject.get("ax21:patronIdentifier").toString());
            ilasRegisterReader.setPatronName(jsonObject.get("ax21:patronName").toString());
            ilasRegisterReader.setPatronPassword(jsonObject.get("ax21:patronPassword").toString());
            ilasRegisterReader.setSex(jsonObject.get("ax21:sex").toString());
            ilasRegisterReader.setStartDate(jsonObject.get("ax21:startDate").toString());
            ilasRegisterReader.setStopDate(jsonObject.get("ax21:stopDate").toString());
            ilasRegisterReader.setType(jsonObject.get("xsi:type").toString());
            ilasRegisterReader.setPatronCirculationType(jsonObject.get("ax21:patronCirculationType").toString());
            ilasRegisterReaders.add(ilasRegisterReader);
        });
        return ilasRegisterReaders;
    }

    // 	查询时间范围内的读者借阅列表
    static public List<IlasHistoryLoan> queryHistoryLoanList(String institutionId,String begDate,String endDate,int pageno,int pagecount)
    {
        String apiURL = "http://202.105.30.39:8280/fslibNew5U/queryHistoryLoanList";
        SoapClient client = SoapClient.create(apiURL).init(SoapProtocol.SOAP_1_2)
                .setMethod("impl:queryHistoryLoanList", "http://impl.services.webservice.ilas.com")
                .setParam("institutionId", institutionId)
                .setParam("begDate", begDate)
                .setParam("endDate", endDate)
                .setParam("pageno", pageno)
                .setParam("pagecount", pagecount)
                .header(Header.AUTHORIZATION, "Basic U0QwMDEubGliOjE1OSMjc2QwMDE6M2E3MDU4NDg0YjBjNDBhNDg4ODc1ZmI1NWQzNmFlN2Q=")
                .header(Header.CONTENT_TYPE, "text/html");
        String body = client.send(true);
        System.out.println(body);

        JSONObject json = JSONUtil.parseFromXml(body);
        System.out.println(json);
        JSONObject soapenvEnvelope = (JSONObject) json.get("soapenv:Envelope");
        System.out.println(soapenvEnvelope);
        JSONObject soapenvBody= (JSONObject) soapenvEnvelope.get("soapenv:Body");
        System.out.println(soapenvBody);

        JSONObject queryHistoryLoanListResponse = (JSONObject) soapenvBody.get("ns:queryHistoryLoanListResponse");
        System.out.println(queryHistoryLoanListResponse);
        JSONObject nsReturn = (JSONObject) queryHistoryLoanListResponse.get("ns:return");
        System.out.println(nsReturn);

        int curpage = (int) nsReturn.get("ax21:curpage");
        int itemCount = (int) nsReturn.get("ax21:itemCount");
        int pagerows = (int) nsReturn.get("ax21:pagerows");
        int pages = (int) nsReturn.get("ax21:pages");
        JSONArray registerList = (JSONArray) nsReturn.get("ax21:loanInfo");

        List<IlasHistoryLoan> loans = new ArrayList<>();
        registerList.stream().iterator().forEachRemaining(item->{
            JSONObject jsonObject = (JSONObject) item;
            System.out.println(jsonObject);
            IlasHistoryLoan ilasHistoryLoan = new IlasHistoryLoan();
            ilasHistoryLoan.setLoanDate(jsonObject.get("ax21:loanDate").toString());
            ilasHistoryLoan.setDueDate(jsonObject.get("ax21:dueDate").toString());
            ilasHistoryLoan.setBarCode(jsonObject.get("ax21:barCode").toString());
            ilasHistoryLoan.setPatronIdentifier(jsonObject.get("ax21:patronIdentifier").toString());
            ilasHistoryLoan.setPatronName(jsonObject.get("ax21:patronName").toString());
            ilasHistoryLoan.setTitle(jsonObject.get("ax21:title").toString());
            ilasHistoryLoan.setBookRecNO(jsonObject.get("ax21:bookRecNO").toString());
            loans.add(ilasHistoryLoan);
        });
        System.out.println(loans);

        return loans;
    }


    // 查询时间范围内的读者借阅列表顺德
    static public List<IlasHistoryLoan> queryHistoryLoanListSd(String institutionId,String begDate,String endDate,int pageno,int pagecount)
    {
        String apiURL = "http://202.105.30.39:8280/fslibNew5U/queryHistoryLoanListSd";
        SoapClient client = SoapClient.create(apiURL).init(SoapProtocol.SOAP_1_2)
                .setMethod("impl:queryHistoryLoanListSd", "http://impl.services.webservice.ilas.com")
                .setParam("institutionId", institutionId)
                .setParam("begDate", begDate)
                .setParam("endDate", endDate)
                .setParam("pageno", pageno)
                .setParam("pagecount", pagecount)
                .header(Header.AUTHORIZATION, "Basic U0QwMDEubGliOjE1OSMjc2QwMDE6MjI2NWI1OWY5MzlhNGEzNzgxNjM2NjgzYjJmOTFkZTQ=")
                .header(Header.CONTENT_TYPE, "text/html");
        String body = client.send(true);
        System.out.println(body);

        JSONObject json = JSONUtil.parseFromXml(body);
        System.out.println(json);
        JSONObject soapenvEnvelope = (JSONObject) json.get("soapenv:Envelope");
        System.out.println(soapenvEnvelope);
        JSONObject soapenvBody= (JSONObject) soapenvEnvelope.get("soapenv:Body");
        System.out.println(soapenvBody);

        JSONObject queryHistoryLoanListSdResponse = (JSONObject) soapenvBody.get("ns:queryHistoryLoanListSdResponse");
        System.out.println(queryHistoryLoanListSdResponse);
        JSONObject nsReturn = (JSONObject) queryHistoryLoanListSdResponse.get("ns:return");
        System.out.println(nsReturn);

        int curpage = (int) nsReturn.get("ax21:curpage");
        int itemCount = (int) nsReturn.get("ax21:itemCount");
        int pagerows = (int) nsReturn.get("ax21:pagerows");
        int pages = (int) nsReturn.get("ax21:pages");
        JSONArray registerList = (JSONArray) nsReturn.get("ax21:loanInfo");

        List<IlasHistoryLoan> loans = new ArrayList<>();
        registerList.stream().iterator().forEachRemaining(item->{
            JSONObject jsonObject = (JSONObject) item;
            System.out.println(jsonObject);
            IlasHistoryLoan ilasHistoryLoan = new IlasHistoryLoan();
            ilasHistoryLoan.setLoanDate(jsonObject.get("ax21:loanDate").toString());
            ilasHistoryLoan.setDueDate(jsonObject.get("ax21:dueDate").toString());
            ilasHistoryLoan.setBarCode(jsonObject.get("ax21:barCode").toString());
            // 如果为换系统（20181218）
            //之前的办证的读者，返回
            //的是顺德旧系统中的证号
            ilasHistoryLoan.setPatronIdentifier(jsonObject.get("ax21:patronIdentifier").toString());
            ilasHistoryLoan.setPatronName(jsonObject.get("ax21:patronName").toString());
            ilasHistoryLoan.setTitle(jsonObject.get("ax21:title").toString());
            ilasHistoryLoan.setBookRecNO(jsonObject.get("ax21:bookRecNO").toString());
            loans.add(ilasHistoryLoan);
        });
        System.out.println(loans);

        return loans;
    }


    // 	读者密码修改接口
    static public boolean alterReaderPassword(String identifier,String password,String newPassword)
    {
        String apiURL = "http://202.105.30.39:8280/fslibNew5U/alterReaderPassword";
        SoapClient client = SoapClient.create(apiURL).init(SoapProtocol.SOAP_1_2)
                .setMethod("impl:alterReaderPassword", "http://impl.services.webservice.ilas.com")
                .setParam("identifier", identifier)
                .setParam("password", password)
                .setParam("newPassword", newPassword)
                .header(Header.AUTHORIZATION, "Basic U0QwMDEubGliOjE1OSMjc2QwMDE6MjQxZjA4ODA4MDY4NGQ2MWFhOGMwNDMwNDhjYzdmYjE=")
                .header(Header.CONTENT_TYPE, "text/html");
        String body = client.send(true);
        System.out.println(body);
        return false;
    }


    // 图书馆管理状态查询接口 状态说明 0 正常 1 异常
    static public String queryLibraryState()
    {
        String apiURL = "http://202.105.30.39:8280/fslibNew5U/queryLibraryState";
        SoapClient client = SoapClient.create(apiURL).init(SoapProtocol.SOAP_1_2)
                .setMethod("impl:queryLibraryState", "http://impl.services.webservice.ilas.com")
                .header(Header.AUTHORIZATION, "Basic U0QwMDEubGliOjE1OSMjc2QwMDE6MmIzMWY1ZWM3NzllNDhiY2IxNGExNDNhM2NmMWRiYzY=")
                .header(Header.CONTENT_TYPE, "text/html");
        String body = client.send(true);
        System.out.println(body);
        JSONObject json = JSONUtil.parseFromXml(body);
        System.out.println(json);
        JSONObject soapenvEnvelope = (JSONObject) json.get("soapenv:Envelope");
        System.out.println(soapenvEnvelope);
        JSONObject soapenvBody= (JSONObject) soapenvEnvelope.get("soapenv:Body");
        System.out.println(soapenvBody);

        JSONObject queryLibraryStateResponse = (JSONObject) soapenvBody.get("ns:queryLibraryStateResponse");
        System.out.println(queryLibraryStateResponse);
        Integer nsReturn = (Integer) queryLibraryStateResponse.get("ns:return");
        System.out.println(nsReturn);

        switch (nsReturn)
        {
            case 0:
                return "正常";
            case 1:
                return "不正常";
            default:
                return "不正常";
        }
    }


    // 读者现借记录查询
    static public List<IlasBorrowBook> queryBorrowBook(String identifier)
    {
        String apiURL = "http://202.105.30.39:8280/fslibNew5U/queryBorrowBook";
        SoapClient client = SoapClient.create(apiURL).init(SoapProtocol.SOAP_1_2)
                .setMethod("impl:queryBorrowBook", "http://impl.services.webservice.ilas.com")
                .setParam("identifier", identifier)
                .header(Header.AUTHORIZATION, "Basic U0QwMDEubGliOjE1OSMjc2QwMDE6MjQ5NTk2N2RmZWFkNDc1YzhiYzhkN2RlZDA0OWI5ODU=")
                .header(Header.CONTENT_TYPE, "text/html");
        String body = client.send(true);
        System.out.println(body);

        JSONObject json = JSONUtil.parseFromXml(body);
        System.out.println(json);
        JSONObject soapenvEnvelope = (JSONObject) json.get("soapenv:Envelope");
        System.out.println(soapenvEnvelope);
        JSONObject soapenvBody= (JSONObject) soapenvEnvelope.get("soapenv:Body");
        System.out.println(soapenvBody);

        JSONObject queryBorrowBookResponse = (JSONObject) soapenvBody.get("ns:queryBorrowBookResponse");
        System.out.println(queryBorrowBookResponse);
        JSONArray nsReturn = (JSONArray) queryBorrowBookResponse.get("ns:return");
        System.out.println(nsReturn);

        List<IlasBorrowBook> ilasBorrowBooks = new ArrayList<>();
        nsReturn.stream().iterator().forEachRemaining(item->{
            JSONObject jsonObject = (JSONObject) item;
            System.out.println(jsonObject);
            IlasBorrowBook ilasBorrowBook = new IlasBorrowBook();
            ilasBorrowBook.setLoanDate(jsonObject.get("ax21:loanDate").toString());
            ilasBorrowBook.setBarCode(jsonObject.get("ax21:barCode").toString());
            ilasBorrowBook.setBookRecNO(jsonObject.get("ax21:bookRecNO").toString());
            ilasBorrowBook.setAuthor(jsonObject.get("ax21:author").toString());
            ilasBorrowBook.setCallNO(jsonObject.get("ax21:callNO").toString());
            ilasBorrowBook.setLoanCount(jsonObject.get("ax21:loanCount").toString());
            ilasBorrowBook.setMaxLoanCount(jsonObject.get("ax21:maxLoanCount").toString());
            ilasBorrowBook.setOrgLib(jsonObject.get("ax21:orgLib").toString());
            ilasBorrowBook.setOrgLocal(jsonObject.get("ax21:orgLocal").toString());
            ilasBorrowBook.setReturnDate(jsonObject.get("ax21:returnDate").toString());
            ilasBorrowBook.setSinglePrice(jsonObject.get("ax21:singlePrice").toString());
            ilasBorrowBook.setVolInfo(jsonObject.get("ax21:volInfo").toString());
            ilasBorrowBook.setTitleIdentifier(jsonObject.get("ax21:titleIdentifier").toString());
            System.out.println(ilasBorrowBook);
            ilasBorrowBooks.add(ilasBorrowBook);
        });

        return ilasBorrowBooks;
    }

    // 读者借阅历史查询
    static public List<IlasBorrowHistory> queryBorrowHistory(String identifier)
    {
        String apiURL = "http://202.105.30.39:8280/fslibNew5U/queryBorrowHistory";
        SoapClient client = SoapClient.create(apiURL).init(SoapProtocol.SOAP_1_2)
                .setMethod("impl:queryBorrowHistory", "http://impl.services.webservice.ilas.com")
                .setParam("identifier", identifier)
                .header(Header.AUTHORIZATION, "Basic U0QwMDEubGliOjE1OSMjc2QwMDE6YjVkODlmYTQ5ODA3NGIwMTkxNmYyZGU2MTQ4M2YxMmQ=")
                .header(Header.CONTENT_TYPE, "text/html");
        String body = client.send(true);
        System.out.println(body);

        JSONObject json = JSONUtil.parseFromXml(body);
        System.out.println(json);
        JSONObject soapenvEnvelope = (JSONObject) json.get("soapenv:Envelope");
        System.out.println(soapenvEnvelope);
        JSONObject soapenvBody= (JSONObject) soapenvEnvelope.get("soapenv:Body");
        System.out.println(soapenvBody);

        JSONObject queryBorrowHistoryResponse = (JSONObject) soapenvBody.get("ns:queryBorrowHistoryResponse");
        System.out.println(queryBorrowHistoryResponse);
        JSONArray nsReturn = (JSONArray) queryBorrowHistoryResponse.get("ns:return");
        System.out.println(nsReturn);

        List<IlasBorrowHistory> ilasBorrowHistories = new ArrayList<>();
        nsReturn.stream().iterator().forEachRemaining(item->{
            JSONObject jsonObject = (JSONObject) item;
            System.out.println(jsonObject);
            IlasBorrowHistory ilasBorrowHistory = new IlasBorrowHistory();
            ilasBorrowHistory.setAction(jsonObject.get("ax21:action").toString());
            ilasBorrowHistory.setAuthor(jsonObject.get("ax21:author").toString());
            ilasBorrowHistory.setBarCode(jsonObject.get("ax21:barCode").toString());
            ilasBorrowHistory.setDuelocal(jsonObject.get("ax21:duelocal").toString());
            ilasBorrowHistory.setDueman(jsonObject.get("ax21:dueman").toString());
            ilasBorrowHistory.setBookRecNO(jsonObject.get("ax21:bookRecNO").toString());
            ilasBorrowHistory.setDuetime(jsonObject.get("ax21:duetime").toString());
            ilasBorrowHistory.setMemo(jsonObject.get("ax21:memo").toString());
            ilasBorrowHistory.setCallNO(jsonObject.get("ax21:callNO").toString());
            ilasBorrowHistory.setOrgLib(jsonObject.get("ax21:orgLib").toString());
            ilasBorrowHistory.setOrgLocal(jsonObject.get("ax21:orgLocal").toString());
            ilasBorrowHistory.setTitleIdentifier(jsonObject.get("ax21:titleIdentifier").toString());
            ilasBorrowHistory.setSinglePrice(jsonObject.get("ax21:singlePrice").toString());
            ilasBorrowHistory.setVolInfo(jsonObject.get("ax21:volInfo").toString());
            System.out.println(ilasBorrowHistory);
            ilasBorrowHistories.add(ilasBorrowHistory);
        });

        return ilasBorrowHistories;
    }

    //
    /**
     * <p>todo 简单书目查询</p>
     * @param institutionId 馆代码
     * @param titleIdentifier 标题
     * @param pageno 页码
     * @param pagecount 每页数量
     * @return
     * <p>author 黄维</p>
     * createTime  2023/5/16 14:14
     */
    static public List<IlasBook> querySimpleBookInfo(String institutionId, String titleIdentifier, int pageno, int pagecount)
    {
        String apiURL = "http://202.105.30.39:8280/fslibNew5U/querySimpleBookInfo";
        SoapClient client = SoapClient.create(apiURL).init(SoapProtocol.SOAP_1_2)
                .setMethod("impl:querySimpleBookInfo", "http://impl.services.webservice.ilas.com")
                .setParam("institutionId", institutionId)
                .setParam("titleIdentifier", titleIdentifier)
                .setParam("pageno", pageno)
                .setParam("pagecount", pagecount)
                .header(Header.AUTHORIZATION, "Basic U0QwMDEubGliOjE1OSMjc2QwMDE6ZjQwZGQ4MzdjMmEwNDBjNWJmMDAyODdmMGY1YTlkNDk=")
                .header(Header.CONTENT_TYPE, "text/html");
        String body = client.send(true);
        System.out.println(body);

        JSONObject json = JSONUtil.parseFromXml(body);
        System.out.println(json);
        JSONObject soapenvEnvelope = (JSONObject) json.get("soapenv:Envelope");
        System.out.println(soapenvEnvelope);
        JSONObject soapenvBody= (JSONObject) soapenvEnvelope.get("soapenv:Body");
        System.out.println(soapenvBody);


        JSONObject querySimpleBookInfoResponse = (JSONObject) soapenvBody.get("ns:querySimpleBookInfoResponse");
        System.out.println(querySimpleBookInfoResponse);
        JSONObject nsReturn = (JSONObject) querySimpleBookInfoResponse.get("ns:return");
        System.out.println(nsReturn);

        List<IlasBook> ilasBooks = new ArrayList<>();
        JSONArray books = (JSONArray) nsReturn.get("ax21:books");
        System.out.println(books);
        books.stream().iterator().forEachRemaining(item->{
            item = (JSONObject) item;
//            System.out.println(item);
            IlasBook ilasBook = new IlasBook();
            ilasBook.setBookRecNO(((JSONObject) item).get("ax21:bookRecNO").toString());
            ilasBook.setVolInfo(((JSONObject) item).get("ax21:volInfo").toString());
            ilasBook.setAuthor(((JSONObject) item).get("ax21:author").toString());
            ilasBook.setCallNO(((JSONObject) item).get("ax21:callNO").toString());
            ilasBook.setPublisher(((JSONObject) item).get("ax21:publisher").toString());
            ilasBook.setTitleIdentifier(((JSONObject) item).get("ax21:titleIdentifier").toString());
            ilasBook.setClassNO(((JSONObject) item).get("ax21:classNO").toString());

//            System.out.println(ilasBook);
            ilasBooks.add(ilasBook);
        });

        System.out.println(ilasBooks);
        return ilasBooks;
    }


    // ISBN/ISSN查询书目
    static public void queryISBNBookInfo()
    {

    }

    public static void main(String[] args) {

//        String[] libids = { "SD001", "SD002", "SD003", "SD005", "SD006", "SD007", "SD008", "SD009", "SD010",
//                "SD011", "SD012", "DX001" }; // 图书馆代码
//        int overdueDays = -5; // 过期天数参数
//        int page = 0; // 页码
//        int pageSize = 50; // 每页请求数量
//
//        for (String libid : libids) {
//            page = 0;
//            List<IlasOverBook> tempBooks = null;
//            List<IlasOverBook> finalBooks = new ArrayList<>();
//            // 如果结果等于size，则继续查询
//            do
//            {
//                tempBooks = queryOverdueBooks(libid, overdueDays, page, pageSize);
//                page++;
//                finalBooks.addAll(tempBooks);
//            }while (tempBooks.size() == pageSize);
//            System.out.println(libid+" 的数量："+finalBooks.size());

            // 保存到数据库

//        List<IlasBook> ilasBooks = querySimpleBookInfo("ALL","西藏深度摄影之旅",0,10);
        // 显示结果
//        ilasBooks.stream().forEach(item->{
//            System.out.println(item);
//            List<IlasCollecitonInfo> ilasCollecitonInfos = queryCollectionInfoFinal(item.getBookRecNO());
//            // 显示结果
//            System.out.println("馆藏信息：");
//            ilasCollecitonInfos.stream().forEach(item1->{
//                System.out.println(item1);
//            });
//        });

        queryCollectionInfoFinal("893564");

    }
    }


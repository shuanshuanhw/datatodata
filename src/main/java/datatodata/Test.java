package datatodata;


import com.ilas.entity.xsd.OverdueBooks;
import com.ilas.entity.xsd.OverdueResult;
import com.ilas.webservice.services.impl.QueryOverdueBooks;
import com.ilas.webservice.services.impl.QueryOverdueBooksResponse;
import com.ilas.webservice.services.impl.ServiceServerStub;
import datatodata.entity.ilas.IlasOverBook;
import datatodata.entity.ilas.IlasUser;
import datatodata.entity.ov.OverdueBookOV;
import datatodata.entity.ov.OverdueBookOVStatus;
import datatodata.entity.ov.ReaderAuthOV;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


// 新的ilas接口json
public class Test {

	public void testneww() throws RemoteException, ClassNotFoundException, SQLException, MalformedURLException {

		//Properties pro = Test.getProperties();


		Connection conn = null;
		PreparedStatement pst = null;
		Class.forName("com.mysql.cj.jdbc.Driver");// 指定连接类型
//		Class.forName("com.mysql.jdbc.Driver");// 指定连接类型

		// 连接用户的数据库
		Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/wxpublic?useUnicode=true&characterEncoding=utf8", "root",
				"651392qQ");
		PreparedStatement pst2 = null;
		ResultSet rs2 = null;

		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sms?useUnicode=true&characterEncoding=utf8", "root",
				"651392qQ");//  获取连接

		// 用来插入消息发送明细
		Connection conn3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sms?useUnicode=true&characterEncoding=utf8", "root",
				"651392qQ");//  获取连接
		String sql3 = "insert into sended_message(openid,card_no,LoanDate,true_name,return_time,title) values(?,?,?,?,?,?)";
		PreparedStatement ps3 = conn3.prepareStatement(sql3);

		String sql = "INSERT INTO sms1(BarCode,Callno,LoanDate,Phone,ReturnTime,Title,LoanCount,Cardno,readerInfo,readerInfoResponse,readerInfo2,readerName) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		pst = conn.prepareStatement(sql);// 准备执行语句

		String readerName = "";
		String cardno = "";
		String phone = "";
		String BarCode = "";
		String Title = "";
		String Callno = "";
		String LoanDate = "";
		String ReturnTime = "";
		String LoanCount = "";
		
		String sh_txtx="";
		String message = "未发送";
		URL wsdlLocation = new URL("http://202.105.30.27:6086/ilasWebservice/services/ServiceServer?wsdl");
//		ServiceServer serverStub = new ServiceServer(wsdlLocation);
		ServiceServerStub serverStub = new ServiceServerStub("http://202.105.30.27:6086/ilasWebservice/services/ServiceServer?wsdl");
		try {
		// 调用查询过期文献接口
		// 查询读者信息
//			QueryReaderInfo readerInfo = new QueryReaderInfo();
//			readerInfo.setIdentifier("1000000109");
//			System.out.println(serverStub.queryReaderInfo(readerInfo).toString());
//			QueryReaderInfoResponse readerInfoResponse = serverStub.queryReaderInfo(readerInfo);
//			ReaderInfo readerInfo2 = readerInfoResponse.get_return();
//			System.out.println(readerInfo2);
			// 显示读者信息

			String[] libids = { "SD001", "SD002", "SD003", "SD005", "SD006", "SD007", "SD008", "SD009", "SD010",
					"SD011", "SD012", "DX001" }; // 图书馆代码

			IlasUtilsJson ilasUtilsJson = new IlasUtilsJson();
			for(String lib:libids)
			{
				HashSet<OverBook> list = new HashSet();
				ArrayList<OverBook> arrayList = new ArrayList<OverBook>();
				String overdueDays = "-5"; // 过期天数参数Test
				String page = "0"; // 页码
				String pageSize = "500"; // 每页请求数量
//				List<IlasOverBook> sd002 = new ArrayList<>();
//				List<IlasOverBook> sd001 = new ArrayList<>();
//				do{
//					sd001 = IlasUitls.queryOverdueBooks(lib, overdueDays, page, pageSize);
//					sd002.addAll(sd001);
//					page++;
//				}while (sd001.size() == pageSize);

				OverdueBookOVStatus overdueBookOVStatus = ilasUtilsJson.queryOverdueBooks(lib, overdueDays, page, pageSize);
				List<OverdueBookOV> datas = overdueBookOVStatus.getDatas();

				// 显示结果
				System.out.println("总数：" + datas.size());// 总数

				for(OverdueBookOV overdueBooks2 :datas)
				{
//					System.out.println(overdueBooks2.getPhone()+" "+overdueBooks2.getTitle()+" "+overdueBooks2.getReturnTime()+" "+overdueBooks2.getLoanDate()+" "+overdueBooks2.getIdentifier());

					BarCode = overdueBooks2.getBarCode();// 书标
					Callno = overdueBooks2.getCallno();
					LoanDate = overdueBooks2.getLoanDate();
					phone = overdueBooks2.getPhone();
					ReturnTime = overdueBooks2.getReturnTime();
					Title = overdueBooks2.getTitle();
					String title = overdueBooks2.getTitle();
					LoanCount = overdueBooks2.getLoanCount();
					cardno = overdueBooks2.getIdentifier();

					if(BarCode==null)BarCode="";
					if(Callno==null)Callno="";
					if(LoanDate==null)LoanDate="";
					if(ReturnTime==null)ReturnTime="";
					if(Title==null)Title="";
					if(title==null)title="";
					if(LoanCount==null)LoanCount="";
					if(cardno==null)cardno="";
					if(readerName==null)readerName="";

					// 处理title的敏感信息
//					if(Title.length() <= 1) {
//						Title = Title;
//					}
//					else if(Title.length() == 2) {
//						Title = Title.substring(0, 1) + "*";
//					}
//					else if(Title.length() == 3) {
//						Title = Title.substring(0, 1) + "*" + Title.substring(2, 3);
//					}
//					else if(Title.length() == 4) {
//						Title = Title.substring(0, 1) + "**" + Title.substring(3, Title.length());
//					}
//					else if(Title.length() >= 5) {
//						Title = Title.substring(0, 1) + "***" + Title.substring(Title.length()-2, Title.length());
//					}

					if(phone==null)phone="";

					LoanDate = LoanDate.substring(0, 10);
					ReturnTime = ReturnTime.substring(0, 10);
					OverBook overBook = new OverBook();
					overBook.setLoanDate(LoanDate);
					overBook.setReturnTime(ReturnTime);
					overBook.setPhone(phone);
					overBook.setTitle(Title);
					overBook.setCardNo(cardno);

					// 根据书证号查询读者信息
					ReaderAuthOV readerAuthOV = ilasUtilsJson.queryReaderInfo(cardno);
//		            System.out.println(ilasUser.getPatronName());
					overBook.setName(readerAuthOV.getRdrName());
					list.add(overBook);
					arrayList.add(overBook);


					pst.setString(1, BarCode);
					pst.setString(2, Callno);
					pst.setString(3, LoanDate);
					pst.setString(4, phone);
					pst.setString(5, ReturnTime);
					pst.setString(6, title);
					pst.setString(7, LoanCount);
					pst.setString(8, cardno);
					pst.setString(9, "");
					pst.setString(10, "");
					pst.setString(11, "");
					pst.setString(12, readerAuthOV.getRdrName());
					pst.executeUpdate();

					String identifier = overdueBooks2.getIdentifier();
					// 根据书证号查询读者信息
//					IlasUser ilasUser = IlasUitls.queryReaderInfo(identifier, "", "");
		            System.out.println(readerAuthOV.getRdrName());
					System.out.println(overdueBooks2.getPhone()+overdueBooks2.getTitle()+overdueBooks2.getLoanDate()+overdueBooks2.getReturnTime()+overdueBooks2.getIdentifier());
				}
				System.out.println("datas.size()"+datas.size());

				System.out.println("去重前有" + list.size() + "条数据");
				// 显示list
				System.out.println("去重前数据");
				for(OverBook overBook : list) {
					System.out.println(overBook);
				}

// 这里显示去重后有多少数据
				for(OverBook overBook : list)
				{
					System.out.println("-------------------------------------------------------------------------------------------------------------------------");
					System.out.println(overBook);
					// 通过书证号、借书日期、还书日期 取出所有的数据
					List<OverBook> subOverBookList = new ArrayList<OverBook>();
					for(OverBook overBook2 : arrayList)
					{
						if(overBook.getCardNo().equals(overBook2.getCardNo()) && overBook.getLoanDate().equals(overBook2.getLoanDate()) && overBook.getReturnTime().equals(overBook2.getReturnTime()))
						{
							subOverBookList.add(overBook2);
						}
					}

//					System.out.println("清理后的数据：");
//					for(OverBook overBook2 : subOverBookList)
//					{
//						System.out.println(overBook2);
//					}
					// 在这里处理短信发送
					String smsText = "";
					String loanDate = overBook.getLoanDate().trim();
					String returnTime = overBook.getReturnTime().trim();
//						System.out.println("loanDate:"+loanDate.length());
					if(loanDate.trim().length() == 8)
						loanDate = loanDate.substring(0,4)+"年"+loanDate.substring(4,6)+"月"+loanDate.substring(6,8)+"日";
					if(returnTime.length() == 8)
						returnTime = returnTime.substring(0,4)+"年"+returnTime.substring(4,6)+"月"+returnTime.substring(6,8)+"日";

					// 通过借书证号和手机号码，取出读者的openid
					String openid = "";
					String sql2 = "select openid from sys_user where reader_id = ?";
					pst2 = conn2.prepareStatement(sql2);
					pst2.setString(1, overBook.getCardNo());
//						pst2.setString(2, overBook.getPhone());
					rs2 = pst2.executeQuery();
					if(rs2.next())
					{
						openid = rs2.getString("openid");
						System.out.println("openid:"+openid);
						openid = openid.trim();
						if(subOverBookList.size() == 1 && StringUtils.isNotEmpty(openid) )
						{
//								smsText = "尊敬的读者，您于 "+loanDate+" 所借书刊 "+subOverBookList.get(0).getTitle()+" 将于 "
//										+returnTime+" 到期，请及时归还。咨询电话：22808600,如果书已归还，请忽略些短信";
							smsText = "尊敬的读者，"+subOverBookList.get(0).getName()+"您于 "+loanDate+" 所借书刊 "+subOverBookList.get(0).getTitle()+" 将于 "
									+returnTime+" 到期，请及时归还。咨询电话：22808600,如果书已归还，请忽略些短信";
							System.out.println("发送短信："+smsText);
							boolean check = WXUtils.sendTemplateMessage(openid, "XVxZjetXFR8C_Is8-N3TwxNjoamFByg0MnxXYCanKv4", subOverBookList.get(0).getName(),subOverBookList.get(0).getCardNo(), subOverBookList.get(0).getTitle(), returnTime);
							System.out.println("发送模板消息："+check);
							ps3.setString(1,openid);
							ps3.setString(2,subOverBookList.get(0).getCardNo());
							ps3.setString(3,loanDate);
							ps3.setString(4,"");
							ps3.setString(5,returnTime);
							ps3.setString(6,subOverBookList.get(0).getTitle());
							int i = ps3.executeUpdate();
							System.out.printf("成功插入%d条",i);
						}
						else if(StringUtils.isNotEmpty(openid))
						{
							smsText = "尊敬的读者，"+subOverBookList.get(0).getName()+"您于 "+loanDate+" 所借书刊 "+subOverBookList.get(0).getTitle()+"等"+subOverBookList.size()+"本 将于 "
									+returnTime+" 到期，请及时归还。咨询电话：22808600,如果书已归还，请忽略些短信";
							System.out.println("11发送短信："+smsText);
							boolean check = WXUtils.sendTemplateMessage(openid, "XVxZjetXFR8C_Is8-N3TwxNjoamFByg0MnxXYCanKv4",subOverBookList.get(0).getName(), subOverBookList.get(0).getCardNo(), subOverBookList.get(0).getTitle()+"等"+subOverBookList.size()+"本书", returnTime);
							System.out.println("发送模板消息："+check);
							ps3.setString(1,openid);
							ps3.setString(2,subOverBookList.get(0).getCardNo());
							ps3.setString(3,loanDate);
							ps3.setString(4,"");
							ps3.setString(5,returnTime);
							ps3.setString(6,subOverBookList.get(0).getTitle());
							int i = ps3.executeUpdate();
							System.out.printf("成功插入%d条",i);
						}
						else
						{
							System.out.println("没有openid"+overBook);
						}
					}


//						System.out.println("短信内容："+smsText);
//						message = SendSms.SendSms(overBook.getPhone(), smsText);
//						System.out.println(overBook.getPhone()+" : "+message);
				}
			}

				System.out.println("success");
//				String s = SendSms.SendSms("15007572525", new Date() + " sms already send!");
//				System.out.println("管理员是否收到短信"+s);
				// 今天的日期
				String returnTime = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
				boolean check = WXUtils.sendTemplateMessage("oa7HK5-kxBFgpyDM9s2iizpuS8PQ", "XVxZjetXFR8C_Is8-N3TwxNjoamFByg0MnxXYCanKv4", "黄维","00000000", "已经成功发送", returnTime);
				System.out.println("发送模板消息："+check);
				pst.close();
				ps3.close();
				conn.close();
				conn3.close();
		} catch (Exception e) {
			System.out.print("循环体出现空异常："+e.toString());
			e.printStackTrace();
		}
	}

	public static Properties getProperties() {
		try {
			// 获取配置文件，转换成流
			InputStream in = null;
			try {
				// 开发环境读取
				URL url = Test.class.getClassLoader().getResource("datasource.properties");
				File file = new File(url.getFile());
				in = new FileInputStream(file);
			} catch (Exception e) {

			}
			// 创建properties对象
			Properties properties = new Properties();
			// 加载流
			properties.load(in);
			return properties;
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String args[])
			throws IOException, ClassNotFoundException, SQLException {

		System.out.println("开始执行");
		datatodata.Test test = new datatodata.Test();
		test.testneww();

	}

}
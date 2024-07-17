package datatodata;


import com.ilas.entity.xsd.OverdueBooks;
import com.ilas.entity.xsd.OverdueResult;
import com.ilas.webservice.services.impl.QueryOverdueBooks;
import com.ilas.webservice.services.impl.QueryOverdueBooksResponse;
import com.ilas.webservice.services.impl.ServiceServerStub;
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
import java.util.Date;
import java.util.*;

public class Test20240104 {

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
					"SD011", "SD012", "DX001","NH001" }; // 图书馆代码
			int overdueDays = -365; // 过期天数参数Test
			int page = 0; // 页码
			int pageSize = 50; // 每页请求数量
			QueryOverdueBooks queryOverdueBooks = new QueryOverdueBooks();

			queryOverdueBooks.setDays(overdueDays);
			queryOverdueBooks.setSize(pageSize);

			if (libids.length > 0) {
				for (String libid : libids) {
					page = 0;
					queryOverdueBooks.setPage(page);
					queryOverdueBooks.setLibcode(libid);

					// 调用接口
					QueryOverdueBooksResponse queryOverdueBooksResponse = serverStub
							.queryOverdueBooks(queryOverdueBooks);
					// 获取返回
					OverdueResult overdueResult = queryOverdueBooksResponse.get_return();

					if (overdueResult == null) {
						continue;
					}
					int total = Integer.valueOf(overdueResult.getCount());
					System.out.println("总数：" + overdueResult.getCount());// 总数

					// 图书列表
					OverdueBooks[] overdueBooks = overdueResult.getOverdueBooks();
//					ArrayList<OverdueBooks> list = new ArrayList<OverdueBooks>(Arrays.asList(overdueBooks)) ;
//					for (int i = 0; i < list.size(); i++) {
//						for (int j = 0; j < list.size(); j++) {
//							if (i != j && list.get(i).getPhone().equals(list.get(j).getPhone()) ) {
//								list.remove(list.get(j));
//							}
//						}
//					}


					HashSet<OverBook> list = new HashSet();
//					HashSet<OverBook> list2 = new HashSet();
					ArrayList<OverBook> arrayList = new ArrayList<OverBook>();
					if (total > 0) {
						int totalPage = (total + pageSize - 1) / pageSize;

						for (int i = page + 1; i <= totalPage; i++) {
							queryOverdueBooks.setPage(i);
							queryOverdueBooksResponse = serverStub.queryOverdueBooks(queryOverdueBooks);
							// 获取返回
							overdueResult = queryOverdueBooksResponse.get_return();
							// 图书列表
							overdueBooks = overdueResult.getOverdueBooks();

							for (OverdueBooks overdueBooks2 : overdueBooks) {
								System.out.println(overdueBooks2.getPhone()+" "+overdueBooks2.getTitle()+" "+overdueBooks2.getReturnTime()+" "+overdueBooks2.getLoanDate()+" "+overdueBooks2.getIdentifier());



								BarCode = overdueBooks2.getBarCode();// 书标
								Callno = overdueBooks2.getCallno();
								LoanDate = overdueBooks2.getLoanDate();
//overdueBooks2.getOrgLib();
								phone = overdueBooks2.getPhone();
								ReturnTime = overdueBooks2.getReturnTime();
								Title = overdueBooks2.getTitle();
								String title = Title;
								LoanCount = overdueBooks2.getLoanCount();
								cardno = overdueBooks2.getIdentifier();
								//readerInfo = overdueBooks2.setIdentifier(cardno);
								//readerInfoResponse = serverStub.queryReaderInfo(readerInfo);
								//readerInfo2 = readerInfoResponse.get_return();
//								readerInfo.setIdentifier(cardno);
//								readerInfoResponse = serverStub.queryReaderInfo(readerInfo);
//								readerInfo2 = readerInfoResponse.get_return();
//								readerName = readerInfo2.getPatronName();

//								System.out.println(readerName);
								if(BarCode==null)BarCode="";
								if(Callno==null)Callno="";
								if(LoanDate==null)LoanDate="";
								if(ReturnTime==null)ReturnTime="";
								if(Title==null)Title="";
								if(LoanCount==null)LoanCount="";
								if(cardno==null)cardno="";
								if(readerName==null)readerName="";

								// 处理title的敏感信息
								if(Title.length() <= 1) {
									Title = Title;
								}
								else if(Title.length() == 2) {
								Title = Title.substring(0, 1) + "*";
								}
								else if(Title.length() == 3) {
								Title = Title.substring(0, 1) + "*" + Title.substring(2, 3);
								}
								else if(Title.length() == 4) {
								Title = Title.substring(0, 1) + "**" + Title.substring(3, Title.length());
								}
								else if(Title.length() >= 5) {
								Title = Title.substring(0, 1) + "***" + Title.substring(Title.length()-2, Title.length());
								}
//								sh_txtx="尊敬的读者，您于 "+LoanDate+" 所借书刊 "+Title+" 将于 "
//										  +ReturnTime+" 到期，请及时归还。咨询电话：22808600,如果书已归还，请忽略些短信";
//								System.out.println(sh_txtx);
								if(phone==null)phone="";

								LoanDate = LoanDate.substring(0, 10);
								ReturnTime = ReturnTime.substring(0, 10);
								OverBook overBook = new OverBook();
								overBook.setLoanDate(LoanDate);
								overBook.setReturnTime(ReturnTime);
								overBook.setPhone(phone);
								overBook.setTitle(Title);
								overBook.setCardNo(cardno);
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
								pst.setString(12, readerName);
								pst.executeUpdate();
							}


						}

					}
					System.out.println("去重前有" + list.size() + "条数据");
					// 显示list
					System.out.println("去重前数据");
					for(OverBook overBook : list) {
						System.out.println(overBook);
					}
					// 显示数据
//					for(OverBook overBook : list) {
//						if (overBook.getPhone().length() == 11) {
//							list2.add(overBook);
//						}
//					}

					// 这里显示去重后有多少数据
//					System.out.println("去重后有" + list2.size() + "条数据");
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

						System.out.println("清理后的数据：");
						for(OverBook overBook2 : subOverBookList)
						{
							System.out.println(overBook2);
						}
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

							if(subOverBookList.size() == 1 && StringUtils.isNotEmpty(openid))
							{
//								smsText = "尊敬的读者，您于 "+loanDate+" 所借书刊 "+subOverBookList.get(0).getTitle()+" 将于 "
//										+returnTime+" 到期，请及时归还。咨询电话：22808600,如果书已归还，请忽略些短信";
								smsText = "尊敬的读者，您于 "+loanDate+" 所借书刊 "+subOverBookList.get(0).getTitle()+" 将于 "
										+returnTime+" 到期，请及时归还。咨询电话：22808600,如果书已归还，请忽略些短信";
								System.out.println("发送短信："+smsText);
								int check = WXUtils.sendTemplateMessage(openid, "XVxZjetXFR8C_Is8-N3Tw_8K-GQ9L5SE0-17lEwiwpk", subOverBookList.get(0).getName(),subOverBookList.get(0).getCardNo(), subOverBookList.get(0).getTitle(), returnTime);
								System.out.println("发送模板消息："+check);
							}
							else if(StringUtils.isNotEmpty(openid))
							{
								smsText = "尊敬的读者，您于 "+loanDate+" 所借书刊 "+subOverBookList.get(0).getTitle()+"等"+subOverBookList.size()+"本 将于 "
										+returnTime+" 到期，请及时归还。咨询电话：22808600,如果书已归还，请忽略些短信";
								System.out.println("11发送短信："+smsText);
								int check = WXUtils.sendTemplateMessage(openid, "XVxZjetXFR8C_Is8-N3Tw_8K-GQ9L5SE0-17lEwiwpk", subOverBookList.get(0).getName(),subOverBookList.get(0).getCardNo(), subOverBookList.get(0).getTitle()+"等"+subOverBookList.size()+"本书", returnTime);
								System.out.println("发送模板消息："+check);
							}
							else
							{
//							System.out.println("没有openid"+overBook.getPhone());
							}
						}


//						System.out.println("短信内容："+smsText);
//						message = SendSms.SendSms(overBook.getPhone(), smsText);
//						System.out.println(overBook.getPhone()+" : "+message);
					}
					System.out.println(" : "+list.size());
				}
				System.out.println("success");
//				String s = SendSms.SendSms("15007572525", new Date() + " sms already send!");
//				System.out.println("管理员是否收到短信"+s);
				// 今天的日期
				String returnTime = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
				int check = WXUtils.sendTemplateMessage("oa7HK5-kxBFgpyDM9s2iizpuS8PQ", "XVxZjetXFR8C_Is8-N3Tw_8K-GQ9L5SE0-17lEwiwpk","", "00000000", "已经成功发送", returnTime);
				System.out.println("发送模板消息："+check);
			}
			pst.close();
			conn.close();
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
				URL url = Test20240104.class.getClassLoader().getResource("datasource.properties");
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
		Test20240104 test = new Test20240104();
		test.testneww();

	}

}
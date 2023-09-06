package datatodata;

import java.sql.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.rmi.RemoteException;
import com.ilas.entity.xsd.OverdueBooks;
import com.ilas.entity.xsd.OverdueResult;
import com.ilas.entity.xsd.ReaderInfo;
import com.ilas.webservice.services.impl.QueryOverdueBooks;
import com.ilas.webservice.services.impl.QueryOverdueBooksResponse;
import com.ilas.webservice.services.impl.QueryReaderInfo;
import com.ilas.webservice.services.impl.QueryReaderInfoResponse;
import com.ilas.webservice.services.impl.ServiceServerStub;

import java.util.*;
import java.util.Date;

public class Test {

	public void testneww() throws RemoteException, ClassNotFoundException, SQLException {

		//Properties pro = Test.getProperties();

		Connection conn = null;
		PreparedStatement pst = null;
		Class.forName("com.mysql.cj.jdbc.Driver");// 指定连接类型
//		Class.forName("com.mysql.jdbc.Driver");// 指定连接类型
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sms?useUnicode=true&characterEncoding=utf8", "root",
				"651392qQ");//  获取连接
		String sql = "INSERT INTO sms(BarCode,Callno,LoanDate,Phone,ReturnTime,Title,LoanCount,Cardno,readerInfo,readerInfoResponse,readerInfo2,readerName) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
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
		ServiceServerStub serverStub = new ServiceServerStub(
				"http://202.105.30.27:6086/ilasWebservice/services/ServiceServer?wsdl");
		try {
// 调用查询过期文献接口
//查询读者信息
			QueryReaderInfo readerInfo = new QueryReaderInfo();
			readerInfo.setIdentifier("1000000109");
			QueryReaderInfoResponse readerInfoResponse = serverStub.queryReaderInfo(readerInfo);
			ReaderInfo readerInfo2 = readerInfoResponse.get_return();
			System.out.println(readerInfo2);
			// 显示读者信息

			String[] libids = { "SD001", "SD002", "SD003", "SD005", "SD006", "SD007", "SD008", "SD009", "SD010",
					"SD011", "SD012", "DX001" }; // 图书馆代码
			int overdueDays = -5; // 过期天数参数
			int page = 1; // 页码
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

								BarCode = overdueBooks2.getBarCode();// 书标
								Callno = overdueBooks2.getCallno();
								LoanDate = overdueBooks2.getLoanDate();
//overdueBooks2.getOrgLib();
								phone = overdueBooks2.getPhone();
								ReturnTime = overdueBooks2.getReturnTime();
								Title = overdueBooks2.getTitle();
								LoanCount = overdueBooks2.getLoanCount();
								cardno = overdueBooks2.getIdentifier();
								//readerInfo = overdueBooks2.setIdentifier(cardno);
								//readerInfoResponse = serverStub.queryReaderInfo(readerInfo);
								//readerInfo2 = readerInfoResponse.get_return();
								readerInfo.setIdentifier(cardno);
								readerInfoResponse = serverStub.queryReaderInfo(readerInfo);
								readerInfo2 = readerInfoResponse.get_return();
								readerName = readerInfo2.getPatronName();

								System.out.println(readerName);
								if(BarCode==null)BarCode="";
								if(Callno==null)Callno="";
								if(LoanDate==null)LoanDate="";
								if(ReturnTime==null)ReturnTime="";
								if(Title==null)Title="";
								if(LoanCount==null)LoanCount="";
								if(cardno==null)cardno="";
								if(readerName==null)readerName="";

								sh_txtx="尊敬的 "+readerName+" 读者，您于 "+LoanDate+" 所借书刊 "+Title+" 将于 "
										  +ReturnTime+" 到期，请及时归还。咨询电话：22808600,如果书已归还，请忽略些短信";
								System.out.println(sh_txtx);
								if(phone==null)phone="";
								if(phone.equals("")||phone.length()!=11)
									System.out.println("出现 不合格phone");
								else
								{
								//System.out.println("success"+phone);
								message = SendSms.SendSms(phone, sh_txtx);
								System.out.println(phone+" : "+message);
								}

								pst.setString(1, BarCode);
								pst.setString(2, Callno);
								pst.setString(3, LoanDate);
								pst.setString(4, phone);
								pst.setString(5, ReturnTime);
								pst.setString(6, Title);
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
				}SendSms.SendSms("15007572525", new Date()+" sms already send!");
			}
			pst.close();
			conn.close();
		} catch (Exception e) {
			System.out.print("循环体出现空异常："+e.toString());
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
		Test test = new Test();
		test.testneww();

	}
}
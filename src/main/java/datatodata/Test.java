package datatodata;


import com.ilas.webservice.services.impl.ServiceServerStub;
import com.mysql.cj.jdbc.Blob;
import datatodata.entity.ov.OverdueBookOV;
import datatodata.entity.ov.OverdueBookOVStatus;
import datatodata.entity.ov.ReaderAuthOV;
import org.apache.axis2.AxisFault;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;


// 新的ilas接口json
public class Test {

	public void testneww() throws SQLException {

		//Properties pro = Test.getProperties();


		Connection conn = null;
		PreparedStatement pst = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// 指定连接类型
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("MYSQL驱动找不到 出现了错误");
		}
//		Class.forName("com.mysql.jdbc.Driver");// 指定连接类型

		// connect to date_if_sended and log_exception
		Connection connection_date_if_sended = DriverManager.getConnection("jdbc:mysql://localhost:3306/sms?useUnicode=true&characterEncoding=utf8", "root",
				"651392qQ");
		PreparedStatement ps_date_if_sended = null;
		String sql_date_if_sended = "insert into date_if_sended(if_del) values(?)";
		ps_date_if_sended = connection_date_if_sended.prepareStatement(sql_date_if_sended);
		// 开始启动，在这里插入数据库的date_if_sended表
		ps_date_if_sended.setString(1,"0");
		int execute = ps_date_if_sended.executeUpdate();
		if(execute == 1) System.out.println("是否已经启动催还消息发送程序 成功。");
		else System.out.println("是否已经启动催还消息发送程序 失败 插入数据库多少行："+execute);



		Connection connection_log_exception = DriverManager.getConnection("jdbc:mysql://localhost:3306/sms?useUnicode=true&characterEncoding=utf8", "root",
				"651392qQ");
		PreparedStatement ps_log_exception = null;
		String sql_log_exception = "insert into log_exception(exception_name,exception_introduce,exception_txt) values(?,?,?)";
		ps_log_exception = connection_log_exception.prepareStatement(sql_log_exception);
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
//		URL wsdlLocation = new URL("http://202.105.30.27:6086/ilasWebservice/services/ServiceServer?wsdl");
//		ServiceServer serverStub = new ServiceServer(wsdlLocation);
		try {
			ServiceServerStub serverStub = new ServiceServerStub("http://202.105.30.27:6086/ilasWebservice/services/ServiceServer?wsdl");
		} catch (AxisFault axisFault) {
			axisFault.printStackTrace();
			System.out.println("在ilas连接时出现了错误");
			StringWriter sw = new StringWriter();
			axisFault.printStackTrace( new PrintWriter(sw, true));

			ByteArrayInputStream txt = new ByteArrayInputStream(sw.getBuffer().toString().getBytes());
			ps_log_exception.setString(1,"在ilas连接时出现了错误");
			ps_log_exception.setString(2,axisFault.getMessage());
			ps_log_exception.setBlob(3, txt);

			int resultNumber = ps_log_exception.executeUpdate();
			if(resultNumber == 1) System.out.println("在ilas连接时出现了错误 异常插入数据库 成功");
			else
				System.out.println("在ilas连接时出现了错误 异常插入数据库 失败");

			return;
		}

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
				HashSet<OverBook> list = new HashSet();ArrayList<OverBook> arrayList = new ArrayList<OverBook>();
				try {


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


					for (OverdueBookOV overdueBooks2 : datas) {
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

						if (BarCode == null) BarCode = "";
						if (Callno == null) Callno = "";
						if (LoanDate == null) LoanDate = "";
						if (ReturnTime == null) ReturnTime = "";
						if (Title == null) Title = "";
						if (title == null) title = "";
						if (LoanCount == null) LoanCount = "";
						if (cardno == null) cardno = "";
						if (readerName == null) readerName = "";


						if (phone == null) phone = "";

						LoanDate = LoanDate.substring(0, 10);
						ReturnTime = ReturnTime.substring(0, 10);
						OverBook overBook = new OverBook();
						overBook.setLoanDate(LoanDate);
						overBook.setReturnTime(ReturnTime);
						overBook.setPhone(phone);
						overBook.setTitle(Title);
						overBook.setCardNo(cardno);

						// 根据书证号查询读者信息
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
						pst.setString(12, "");
						pst.executeUpdate();

						String identifier = overdueBooks2.getIdentifier();
						// 根据书证号查询读者信息
//					IlasUser ilasUser = IlasUitls.queryReaderInfo(identifier, "", "");
//						System.out.println(readerAuthOV.getRdrName());
						System.out.println(overdueBooks2.getPhone() + overdueBooks2.getTitle() + overdueBooks2.getLoanDate() + overdueBooks2.getReturnTime() + overdueBooks2.getIdentifier());
					}
					System.out.println("datas.size()" + datas.size());

					System.out.println("去重前有" + list.size() + "条数据");
					// 显示list
					System.out.println("去重前数据");
					for (OverBook overBook : list) {
						System.out.println(overBook);
					}
				}catch (Exception ex)
				{
					ex.printStackTrace();
					System.out.println("第一部分出现错误");
					StringWriter sw = new StringWriter();
					ex.printStackTrace( new PrintWriter(sw, true));

					ByteArrayInputStream txt = new ByteArrayInputStream(sw.getBuffer().toString().getBytes());
					ps_log_exception.setString(1,"第一部分出现错误");
					ps_log_exception.setString(2,ex.getMessage());
					ps_log_exception.setBlob(3, txt);
					int resultNumber = ps_log_exception.executeUpdate();
					if(resultNumber == 1) System.out.println("第一部分异常插入数据库 成功");
					else
						System.out.println("第一部分异常插入数据库 失败");
					return;
				}
// 这里显示去重后有多少数据
				try {
					for (OverBook overBook : list) {
						System.out.println("-------------------------------------------------------------------------------------------------------------------------");
						System.out.println(overBook);
						// 通过书证号、借书日期、还书日期 取出所有的数据
						List<OverBook> subOverBookList = new ArrayList<OverBook>();
						for (OverBook overBook2 : arrayList) {
							if (overBook.getCardNo().equals(overBook2.getCardNo()) && overBook.getLoanDate().equals(overBook2.getLoanDate()) && overBook.getReturnTime().equals(overBook2.getReturnTime())) {
								subOverBookList.add(overBook2);
							}
						}

						// 在这里处理短信发送
						String smsText = "";
						String loanDate = overBook.getLoanDate().trim();
						String returnTime = overBook.getReturnTime().trim();
//						System.out.println("loanDate:"+loanDate.length());
						if (loanDate.trim().length() == 8)
							loanDate = loanDate.substring(0, 4) + "年" + loanDate.substring(4, 6) + "月" + loanDate.substring(6, 8) + "日";
						if (returnTime.length() == 8)
							returnTime = returnTime.substring(0, 4) + "年" + returnTime.substring(4, 6) + "月" + returnTime.substring(6, 8) + "日";

						// 通过借书证号和手机号码，取出读者的openid
						String openid = "";
						String sql2 = "select openid from sys_user where reader_id = ?";
						pst2 = conn2.prepareStatement(sql2);
						pst2.setString(1, overBook.getCardNo());
//						pst2.setString(2, overBook.getPhone());
						rs2 = pst2.executeQuery();
						if (rs2.next()) {
							openid = rs2.getString("openid");
							System.out.println("openid:" + openid);
							openid = openid.trim();
							if (subOverBookList.size() == 1 && StringUtils.isNotEmpty(openid)) {
//								smsText = "尊敬的读者，您于 "+loanDate+" 所借书刊 "+subOverBookList.get(0).getTitle()+" 将于 "
//										+returnTime+" 到期，请及时归还。咨询电话：22808600,如果书已归还，请忽略些短信";
								smsText = "尊敬的读者，您于 " + loanDate + " 所借书刊 " + subOverBookList.get(0).getTitle() + " 将于 "
										+ returnTime + " 到期，请及时归还。咨询电话：22808600,如果书已归还，请忽略些短信";
								System.out.println("发送短信：" + smsText);
//							boolean check = WXUtils.sendTemplateMessage(openid, "XVxZjetXFR8C_Is8-N3TwxNjoamFByg0MnxXYCanKv4", subOverBookList.get(0).getName(),subOverBookList.get(0).getCardNo(), subOverBookList.get(0).getTitle(), returnTime);
//							System.out.println("发送模板消息："+check);
								ps3.setString(1, openid);
								ps3.setString(2, subOverBookList.get(0).getCardNo());
								ps3.setString(3, loanDate);
								ps3.setString(4, "");
								ps3.setString(5, returnTime);
								ps3.setString(6, subOverBookList.get(0).getTitle());
								int i = ps3.executeUpdate();
								System.out.printf("成功插入%d条", i);
							} else if (StringUtils.isNotEmpty(openid)) {
								smsText = "尊敬的读者，您于 " + loanDate + " 所借书刊 " + subOverBookList.get(0).getTitle() + "等" + subOverBookList.size() + "本 将于 "
										+ returnTime + " 到期，请及时归还。咨询电话：22808600,如果书已归还，请忽略些短信";
								System.out.println("11发送短信：" + smsText);
								String titleString;
								if(subOverBookList.size() == 0 || subOverBookList == null)
								{
									titleString = "";
								}
								else
								{
									String overBookString = subOverBookList.parallelStream().collect(() -> new StringBuffer(), (a, b) -> a.append(b + ","), (a, b) -> a.append(b)).toString();
									String subOverBookString = overBookString.substring(0, overBookString.lastIndexOf(","));
									titleString = subOverBookString;
								}

//							boolean check = WXUtils.sendTemplateMessage(openid, "XVxZjetXFR8C_Is8-N3TwxNjoamFByg0MnxXYCanKv4",subOverBookList.get(0).getName(), subOverBookList.get(0).getCardNo(), subOverBookList.get(0).getTitle()+"等"+subOverBookList.size()+"本书", returnTime);
//							System.out.println("发送模板消息："+check);
								ps3.setString(1, openid);
								ps3.setString(2, subOverBookList.get(0).getCardNo());
								ps3.setString(3, loanDate);
								ps3.setString(4, "");
								ps3.setString(5, returnTime);
								ps3.setString(6, titleString);
								int i = ps3.executeUpdate();
								System.out.printf("成功插入%d条", i);
							} else {
								System.out.println("没有openid" + overBook);
							}
						}
					}
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
					System.out.println("第二部分出现错误");

					StringWriter sw = new StringWriter();
					ex.printStackTrace( new PrintWriter(sw, true));

					ByteArrayInputStream txt = new ByteArrayInputStream(sw.getBuffer().toString().getBytes());
					ps_log_exception.setString(1,"第二部分出现错误");
					ps_log_exception.setString(2,ex.getMessage());
					ps_log_exception.setBlob(3, txt);

					int resultNumber = ps_log_exception.executeUpdate();
					if(resultNumber == 1) System.out.println("第二部分异常插入数据库 成功");
					else
						System.out.println("第二部分异常插入数据库 失败");
					return;
				}
			}

			try{
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
				connection_date_if_sended.close();
				connection_log_exception.close();
		} catch (Exception ex) {

				ex.printStackTrace();
				System.out.print("循环体出现空异常："+ex.toString());
				StringWriter sw = new StringWriter();
				ex.printStackTrace( new PrintWriter(sw, true));

				ByteArrayInputStream txt = new ByteArrayInputStream(sw.getBuffer().toString().getBytes());
				ps_log_exception.setString(1,"循环体出现空异常");
				ps_log_exception.setString(2,ex.getMessage());
				ps_log_exception.setBlob(3, txt);

				int resultNumber = ps_log_exception.executeUpdate();
				if(resultNumber == 1) System.out.println("第三部分异常插入数据库 成功");
				else
					System.out.println("第三部分异常插入数据库 失败");


			return;
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
package datatodata;

import java.sql.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.rmi.RemoteException;
//import com.ilas.entity.xsd.ReaderInfo;
import com.ilas.webservice.services.impl.ServiceServerStub;
import datatodata.entity.ilas.IlasOverBook;
import datatodata.entity.ilas.IlasUser;

import java.util.*;

public class Test3 {

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
		String phone = "";
		String sh_txtx="";
		String message = "未发送";
		ServiceServerStub serverStub = new ServiceServerStub(
				"http://202.105.30.27:6086/ilasWebservice/services/ServiceServer?wsdl");
		try {
			String[] libids = { "SD001", "SD002", "SD003", "SD005", "SD006", "SD007", "SD008", "SD009", "SD010",
					"SD011", "SD012", "DX001" }; // 图书馆代码
			int overdueDays = -5; // 过期天数参数
			int page = 0; // 页码
			int pageSize = 50; // 每页请求数量

			for (String libid : libids) {
				page = 0;
				List<IlasOverBook> tempBooks = null;
				List<IlasOverBook> finalBooks = new ArrayList<>();
				// 如果结果等于size，则继续查询
				do
				{
					tempBooks = IlasUitls.queryOverdueBooks(libid, overdueDays, page, pageSize);
					page++;
					finalBooks.addAll(tempBooks);
				}while (tempBooks.size() == pageSize);
				System.out.println(libid+" 的数量："+finalBooks.size());

				// 遍历finalBooks
				for (IlasOverBook book : finalBooks) {

					// 定义两个变量
					String loanDate = book.getLoanDate()==null?"":book.getLoanDate();
					String returnTime = book.getReturnTime()==null?"":book.getReturnTime();

					IlasUser ilasUser = IlasUitls.queryReaderInfo(book.getIdentifier(), "", "");
					if (ilasUser != null) {
						readerName = ilasUser.getPatronName();
						sh_txtx="尊敬的 "+readerName+" 读者，您于 "+loanDate+" 所借书刊 "+book.getTitle()+" 将于 " +returnTime+" 到期，请及时归还。咨询电话：22808600,如果书已归还，请忽略些短信";
						System.out.println(sh_txtx);
						phone = book.getPhone()==null?"":book.getPhone();
						if(phone.equals("")||phone.length()!=11)
						{
							System.out.println("出现不合格phone");
							// 保存到数据库
							pst.setString(1, book.getBarCode());
							pst.setString(2, book.getCallno());
							pst.setString(3, loanDate);
							pst.setString(4, book.getPhone());
							pst.setString(5, returnTime);
							pst.setString(6, book.getTitle());
							pst.setString(7, book.getLoanCount());
							pst.setString(8, book.getIdentifier());
							pst.setString(9, "");
							pst.setString(10, "");
							pst.setString(11, "出现不合格phone");// 标志出现不合格phone
							pst.setString(12, readerName);
							int i = pst.executeUpdate();
//							if (i > 0) {
//								System.out.println("插入成功！");
//							}
//							else
//							{
//								System.out.println("插入失败！");
//							}
						}
						else
						{
//							message = SendSms.SendSms(phone, sh_txtx);
//							System.out.println(phone+" : "+message);
							// 保存到数据库
							pst.setString(1, book.getBarCode());
							pst.setString(2, book.getCallno());
							pst.setString(3, loanDate);
							pst.setString(4, book.getPhone());
							pst.setString(5, returnTime);
							pst.setString(6, book.getTitle());
							pst.setString(7, book.getLoanCount());
							pst.setString(8, book.getIdentifier());
							pst.setString(9, "");
							pst.setString(10, "");
							if("Http Request is accept.".equals(message))
							{
								pst.setString(11, "发送成功");
							}
							else
							{
								pst.setString(11, "发送失败");
							}
							pst.setString(12, readerName);
							int i = pst.executeUpdate();
//							if (i > 0) {
//								System.out.println("插入成功！");
//							}
//							else
//							{
//								System.out.println("插入失败！");
//							}
						}
					}

				}

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
				URL url = Test3.class.getClassLoader().getResource("datasource.properties");
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
		Test3 test = new Test3();
		test.testneww();

	}
}
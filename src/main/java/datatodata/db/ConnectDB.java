package datatodata.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static Connection connection = null;

    // 连接数据库
    public static Connection getConnection(String ip, String databaseName, String user, String password) {

        // 连接数据库的url
        String url = "jdbc:mysql://"+ip+":3306/" + databaseName+"?useSSL=false&serverTimezone=UTC";

        // 驱动程序的加载
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("驱动程序加载失败，请检查驱动程序是否正常！");
            e.printStackTrace();
            return null;
        }

        // 尝试连接
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("数据库连接失败，请重新尝试");
            e.printStackTrace();
        }
        return connection;
    }

    // 关闭连接
    public static void closeConnect(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}

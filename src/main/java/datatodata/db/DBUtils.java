package datatodata.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {

    //向表中插入数据
    public static int insert(Connection connection, String tableName, Object... args) throws SQLException {
        int result = 0;
        PreparedStatement statement = null;
        String insert = "INSERT INTO " + tableName + " (openid, card_no, LoanDate, true_name, return_time, title, ) VALUES (?, ?, ?)";
        if (connection != null) {
            statement = connection.prepareStatement(insert);
            for (int i = 0; i < args.length; ++i) {
                statement.setObject(i + 1, args[i]);
            }
            result = statement.executeUpdate();
        }
        if (statement != null) {
            statement.close();
        }
        return result;
    }

    // 在表中删除指定数据
    public static int delete(Connection connection, String tableName, String condition, String conditionValue) throws SQLException {
        int result = 0;
        PreparedStatement statement = null;
        String delete = "DELETE FROM " + tableName + " WHERE " + condition + " = ?";
        if (connection != null) {
            statement = connection.prepareStatement(delete);
            statement.setString(1, conditionValue);
            result = statement.executeUpdate();
        }
        if (statement != null) {
            statement.close();
        }
        return result;
    }

    // 删除表中的所有数据
    public static int deleteAll(Connection connection, String tableName) throws SQLException {
        int result = 0;
        PreparedStatement statement = null;
        String delete = "DELETE FROM " + tableName;
        if (connection != null) {
            statement = connection.prepareStatement(delete);
            result = statement.executeUpdate();
        }
        if (statement != null) {
            statement.close();
        }
        return result;
    }

    // 对表中的数据修改（无条件）
    public static int updateWithoutCondition(Connection connection, String tableName, String column, String obj) throws SQLException {
        int result = 0;
        PreparedStatement statement = null;
        String update = "UPDATE " + tableName + " SET " + column + " = ?";
        if (connection != null) {
            statement = connection.prepareStatement(update);
            statement.setString(1, obj);
            result = statement.executeUpdate();
        }
        if (statement != null) {
            statement.close();
        }
        return result;
    }

    // 对表中的数据修改（含条件）
    public static int updateWithCondition(Connection connection, String tableName, String column, String obj, String condition, String conditionValue) throws SQLException {
        int result = 0;
        PreparedStatement statement = null;
        String update = "UPDATE " + tableName + " SET " + column + " = ? WHERE " + condition + " = ?";
        if (connection != null) {
            statement = connection.prepareStatement(update);
            statement.setString(1, obj);
            statement.setString(2, conditionValue);
            result = statement.executeUpdate();
        }
        if (statement != null) {
            statement.close();
        }
        return result;
    }

    // 对表中的数据进行查找
    public static ResultSet select(Connection connection, String tableName, String condition, String conditionValue) throws SQLException {
        PreparedStatement statement = null;
        String select = "SELECT * FROM " + tableName + " WHERE " + condition + " = ?";
        if (connection != null) {
            statement = connection.prepareStatement(select);
            statement.setString(1, conditionValue);
        }
        ResultSet resultset = null;
        if (statement != null) {
            resultset = statement.executeQuery();
        }
        return resultset;
    }

    // 对表中的数据进行查找
    public static ResultSet selectAll(Connection connection, String tableName) throws SQLException {
        PreparedStatement statement = null;
        String select = "SELECT * FROM " + tableName;
        if (connection != null) {
            statement = connection.prepareStatement(select);
        }
        ResultSet resultset = null;
        if (statement != null) {
            resultset = statement.executeQuery();
        }
        return resultset;
    }
}

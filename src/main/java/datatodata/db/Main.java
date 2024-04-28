package datatodata.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        int count = 0;
        Connection connection;
        System.out.println("请分别输入MySQL的ip地址（本机一般为localhost），用户名，密码，以及要使用的数据库名称：");
        do {
            ++count;
            if (count == 5) {
                System.out.println("多次尝试失败，已退出");
                return;
            }
            Scanner scanner = new Scanner(System.in);
            System.out.println("ip:");
            String ip = scanner.next();
            System.out.println("用户名：");
            String user = scanner.next();
            System.out.println("密码：");
            String password = scanner.next();
            System.out.println("要操作的数据库：");
            String databaseName = scanner.next();
            connection = ConnectDB.getConnection(ip, databaseName, user, password);
        } while (connection == null);
        Scanner scanner = new Scanner(System.in);
        System.out.println("在哪个表中操作？（后续可更改）");
        String tableName = scanner.next();
        while (true) {
            System.out.println("-------------------------------------");
            System.out.println("\t1.插入数据");
            System.out.println("\t2.删除指定数据");
            System.out.println("\t3.删除全部数据");
            System.out.println("\t4.改（无条件）");
            System.out.println("\t5.改（有条件）");
            System.out.println("\t6.查找数据");
            System.out.println("\t7.显示所有数据");
            System.out.println("\t8.修改操作的表");
            System.out.println("\t0.退出");
            System.out.println("-------------------------------------");
            System.out.println("请输入你的操作：");
            int flag;
            do {
                flag = scanner.nextInt();
                if (flag == 0) {
                    ConnectDB.closeConnect(connection);
                    return;
                }
                if (flag > 8) {
                    System.out.println("输入的指令有误，请重新输入：");
                }
            } while (flag > 8);
            switch (flag) {
                case 1 : {
                    System.out.println("要添加多少条信息？");
                    int temp = scanner.nextInt(), insert = 0;
                    for (int i = 0; i < temp; ++i) {
                        System.out.println("请输入第" + (i + 1) + "条信息（id, name, age）：");
                        int id = scanner.nextInt();
                        String name = scanner.next();
                        int age = scanner.nextInt();
                        insert += DBUtils.insert(connection, tableName, id, name, age);
                    }
                    System.out.println(insert + "条数据添加成功，" + (temp - insert) + "条数据添加失败");

                }
                case 2 : {
                    System.out.println("想通过什么删除数据？（id？name？age？）");
                    String condition = scanner.next();
                    System.out.println("它的值？");
                    String conditionValue = scanner.next();
                    int delete = DBUtils.delete(connection, tableName, condition, conditionValue);
                    if (delete != 0) {
                        System.out.println(delete + "条数据已成功删除");
                    } else {
                        System.out.println("数据删除失败");
                    }
                }
                case 3 : {
                    int delete = DBUtils.deleteAll(connection, tableName);
                    if (delete != 0) {
                        System.out.println("全部数据删除成功，共" + delete + "条");
                    } else {
                        System.out.println("数据删除失败");
                    }
                }
                case 4 : {
                    System.out.println("想修改哪一列？（id？name？age？）");
                    String column = scanner.next();
                    System.out.println("修改成什么？");
                    String obj = scanner.next();
                    int i = DBUtils.updateWithoutCondition(connection, tableName, column, obj);
                    if (i != 0) {
                        System.out.println("修改成功，" + i + "条数据受影响");
                    } else {
                        System.out.println("修改失败");
                    }
                }
                case 5 : {
                    System.out.println("想修改哪一列？（id？name？age？）");
                    String column = scanner.next();
                    System.out.println("修改成什么？");
                    String obj = scanner.next();
                    System.out.println("条件？（id？name？age？）");
                    String condition = scanner.next();
                    System.out.println("条件的值？");
                    String conditionValue = scanner.next();
                    int i = DBUtils.updateWithCondition(connection, tableName, column, obj, condition, conditionValue);
                    if (i == 1) {
                        System.out.println("修改成功，" + i + "条数据受影响");
                    } else {
                        System.out.println("修改失败");
                    }
                }
                case 6 : {
                    int countResult = 0;
                    System.out.println("查找条件？（id？name？age？）");
                    String condition = scanner.next();
                    System.out.println("查找条件的值？");
                    String conditionValue = scanner.next();
                    ResultSet select = DBUtils.select(connection, tableName, condition, conditionValue);
                    System.out.println("-------------------------------------");
                    countResult = getCountResult(countResult, select);
                    System.out.println("-------------------------------------");
                    System.out.println("查找完毕，共" + countResult + "条数据");
                }
                case 7 : {
                    int countResult = 0;
                    ResultSet selectAll = DBUtils.selectAll(connection, tableName);
                    System.out.println("-------------------------------------");
                    countResult = getCountResult(countResult, selectAll);
                    System.out.println("-------------------------------------");
                    System.out.println("查找完毕，共" + countResult + "条数据");
                }
                case 8 : {
                    System.out.println("修改成什么？");
                    tableName = scanner.next();
                    System.out.println("修改成功");
                }
            }
        }
    }

    private static int getCountResult(int countResult, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            System.out.println("id = " + id + ", name = " + name + ", age = " + age);
            ++countResult;
        }
        return countResult;
    }
}

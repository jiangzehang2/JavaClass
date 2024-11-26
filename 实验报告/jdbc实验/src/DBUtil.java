/**
 * @BelongsProject: test0924
 * @BelongsPackage: PACKAGE_NAME
 * @ClassName DBUtil
 * @Author: bill
 * @CreateTime: 2024-11-26  13:44
 * @Description: TODO
 * @Version: 1.0
 */

/**
 * 数据库连接工具类
 * @author 江浩
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    // 数据库连接的URL，格式：jdbc:mysql://主机:端口/数据库名
    private static final String URL = "jdbc:mysql://localhost:3306/DroneManagementSystem?serverTimezone=UTC";
    ;
    // 数据库的用户名
    private static final String USER = "root";
    // 数据库的密码
    private static final String PASSWORD = "@SZU1981027129AB";

    // 获取数据库连接的方法
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 使用DriverManager获取数据库连接
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}


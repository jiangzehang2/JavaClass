/**
 * @BelongsProject: test0924
 * @BelongsPackage: PACKAGE_NAME
 * @ClassName OrderDAO
 * @Author: bill
 * @CreateTime: 2024-11-26  13:54
 * @Description: TODO
 * @Version: 1.0
 */

/**
 * 订单和订单明细的增删改查操作类
 * @author 江浩
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    // 插入订单的方法
    public void addOrder(int userId, double totalAmount, List<OrderDetail> orderDetails) {
        // 订单插入SQL语句
        String insertOrderSQL = "INSERT INTO orders (user_id, total_amount) VALUES (?, ?)";
        // 订单明细插入SQL语句
        String insertOrderDetailsSQL = "INSERT INTO order_details (order_id, product_id, quantity, unit_price, subtotal) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             // 预处理SQL语句对象，用于插入订单
             PreparedStatement orderStmt = conn.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS);
             // 预处理SQL语句对象，用于插入订单明细
             PreparedStatement detailStmt = conn.prepareStatement(insertOrderDetailsSQL)) {

            // 设置订单表的参数
            orderStmt.setInt(1, userId);
            orderStmt.setDouble(2, totalAmount);
            // 执行插入操作
            orderStmt.executeUpdate();

            // 获取生成的订单ID（在插入订单后）
            try (ResultSet generatedKeys = orderStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // 获取插入的订单ID
                    int orderId = generatedKeys.getInt(1);

                    // 循环插入订单明细
                    for (OrderDetail detail : orderDetails) {
                        detailStmt.setInt(1, orderId);         // 订单ID
                        detailStmt.setInt(2, detail.getProductId()); // 商品ID
                        detailStmt.setInt(3, detail.getQuantity());  // 商品数量
                        detailStmt.setDouble(4, detail.getUnitPrice()); // 商品单价
                        detailStmt.setDouble(5, detail.getSubtotal());  // 商品小计
                        detailStmt.addBatch();  // 将当前插入明细的操作添加到批处理
                    }

                    // 执行批量插入
                    detailStmt.executeBatch();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();  // 捕获异常并打印栈信息
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // 获取所有订单的方法
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        // 查询所有订单的SQL语句
        String query = "SELECT * FROM orders";

        try (Connection conn = DBUtil.getConnection();
             // 创建Statement对象，用于执行SQL查询
             Statement stmt = conn.createStatement();
             // 执行查询并获取结果集
             ResultSet rs = stmt.executeQuery(query)) {

            // 遍历结果集，获取订单信息
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                int userId = rs.getInt("user_id");
                double totalAmount = rs.getDouble("total_amount");
                Timestamp createTime = rs.getTimestamp("create_time");
                // 创建一个Order对象并将其添加到列表
                Order order = new Order(orderId, userId, totalAmount, createTime);
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return orders;
    }

    // 获取某个订单的所有明细的方法
    public List<OrderDetail> getOrderDetails(int orderId) {
        List<OrderDetail> details = new ArrayList<>();
        // 查询某个订单的所有明细的SQL语句
        String query = "SELECT * FROM order_details WHERE order_id = ?";

        try (Connection conn = DBUtil.getConnection();
             // 创建PreparedStatement对象，防止SQL注入
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderId);  // 设置查询的订单ID
            // 执行查询并获取结果集
            try (ResultSet rs = stmt.executeQuery()) {
                // 遍历结果集，获取订单明细信息
                while (rs.next()) {
                    int detailId = rs.getInt("order_detail_id");
                    int productId = rs.getInt("product_id");
                    int quantity = rs.getInt("quantity");
                    double unitPrice = rs.getDouble("unit_price");
                    double subtotal = rs.getDouble("subtotal");
                    // 创建一个OrderDetail对象并将其添加到列表
                    OrderDetail detail = new OrderDetail(detailId, orderId, productId, quantity, unitPrice, subtotal);
                    details.add(detail);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return details;
    }

    // 删除订单及其明细的方法
    public void deleteOrder(int orderId) {
        // 删除订单明细的SQL语句
        String deleteDetailsSQL = "DELETE FROM order_details WHERE order_id = ?";
        // 删除订单的SQL语句
        String deleteOrderSQL = "DELETE FROM orders WHERE order_id = ?";

        try (Connection conn = DBUtil.getConnection();
             // 创建PreparedStatement对象用于删除订单明细
             PreparedStatement deleteDetailsStmt = conn.prepareStatement(deleteDetailsSQL);
             // 创建PreparedStatement对象用于删除订单
             PreparedStatement deleteOrderStmt = conn.prepareStatement(deleteOrderSQL)) {

            // 删除订单明细
            deleteDetailsStmt.setInt(1, orderId);
            deleteDetailsStmt.executeUpdate();

            // 删除订单
            deleteOrderStmt.setInt(1, orderId);
            deleteOrderStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}


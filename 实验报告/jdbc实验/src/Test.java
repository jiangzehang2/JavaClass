/**
 * @BelongsProject: test0924
 * @BelongsPackage: PACKAGE_NAME
 * @ClassName Test
 * @Author: bill
 * @CreateTime: 2024-11-26  13:55
 * @Description: TODO
 * @Version: 1.0
 */

/**
 * 测试类
 * @author 江浩
 */
import java.util.ArrayList;
import java.util.List;

public class Test {
    private static Object order;

    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();

        // 添加订单示例
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetail(0, 0, 1, 2, 10, 20));  // 添加一个商品
        orderDetails.add(new OrderDetail(0, 10, 12, 11, 15, 15));  // 添加另一个商品
        orderDAO.addOrder(1, 35, orderDetails);  // 假设user_id = 1

        // 获取所有订单示例
        List<Order> orders = orderDAO.getAllOrders();
        for (Order order : orders) {
            System.out.println(order.getOrderId() + ": " + order.getTotalAmount());
            List<OrderDetail> details = orderDAO.getOrderDetails(order.getOrderId());
            for (OrderDetail detail : details) {
                System.out.println("  Product: " + detail.getProductId() + ", Quantity: " + detail.getQuantity() + ", Subtotal: " + detail.getSubtotal());
            }
        }

        // 删除订单示例
        orderDAO.deleteOrder(6);
    }
}


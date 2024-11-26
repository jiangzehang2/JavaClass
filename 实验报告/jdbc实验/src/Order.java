/**
 * @BelongsProject: test0924
 * @BelongsPackage: PACKAGE_NAME
 * @ClassName Order
 * @Author: bill
 * @CreateTime: 2024-11-26  13:52
 * @Description: TODO
 * @Version: 1.0
 */

/**
 * 订单实体类
 * @author 江浩
 */
import java.sql.Timestamp;
import java.util.List;

public class Order {
    private int orderId;  // 订单ID
    private int userId;   // 用户ID
    private double totalAmount;  // 订单金额
    private Timestamp createTime;  // 订单创建时间
    private List<OrderDetail> details;  // 订单的明细列表

    // 构造方法、getter 和 setter
    public Order(int orderId, int userId, double totalAmount, Timestamp createTime) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.createTime = createTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public double getTotalAmount() {
        return  totalAmount;
    }

    // Getter 和 Setter 方法省略
}




/**
 * @BelongsProject: test0924
 * @BelongsPackage: PACKAGE_NAME
 * @ClassName OrderDetail
 * @Author: bill
 * @CreateTime: 2024-11-26  13:52
 * @Description: TODO
 * @Version: 1.0
 */

/**
 * 订单明细实体类
 * @author 江浩
 */
public class OrderDetail {
    private int orderDetailId;  // 订单明细ID
    private int orderId;        // 订单ID
    private int productId;      // 商品ID
    private int quantity;       // 商品数量
    private double unitPrice;   // 商品单价
    private double subtotal;    // 商品金额小计

    // 构造方法、getter 和 setter
    public OrderDetail(int orderDetailId, int orderId, int productId, int quantity, double unitPrice, double subtotal) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getSubtotal() {
        return subtotal;
    }

    // Getter 和 Setter 方法省略
}

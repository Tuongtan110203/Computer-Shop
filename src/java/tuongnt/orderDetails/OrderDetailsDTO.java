package tuongnt.orderDetails;

import java.sql.Date;
import tuongnt.products.ProductDTO;

public class OrderDetailsDTO {

    private int orderDetailId;
    private int orderId;
    private String productId; 
    private String name;
    private double price;
    private String image;
    private int quantity;
    private double totalMoney;
    private String orderDate;
    private String fullname;
    private String address;
    private String email;
    private String phone;
    private String notice;
    public OrderDetailsDTO() {
    }

    @Override
    public String toString() {
        return "OrderDetailsDTO{" + "orderDetailId=" + orderDetailId + ", orderId=" + orderId + ", productId=" + productId + ", name=" + name + ", price=" + price + ", image=" + image + ", quantity=" + quantity + ", totalMoney=" + totalMoney + ", orderDate=" + orderDate + ", fullname=" + fullname + ", address=" + address + ", email=" + email + ", phone=" + phone + ", notice=" + notice + '}';
    }
    
    public OrderDetailsDTO(int orderDetailId, int orderId, String productId, String name, double price, String image, int quantity, double totalMoney, String orderDate, String fullname, String address, String email, String phone, String notice) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.totalMoney = totalMoney;
        this.orderDate = orderDate;
        this.fullname = fullname;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.notice = notice;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    
    
}

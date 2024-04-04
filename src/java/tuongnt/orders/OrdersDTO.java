/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongnt.orders;

import java.sql.Date;

/**
 *
 * @author tuong
 */
public class OrdersDTO {

    public int OrderId;
    public int UserId;
    public String ProductId;
    public Date OrderDate;
    public boolean Status;
    public OrdersDTO() {
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int OrderId) {
        this.OrderId = OrderId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String ProductId) {
        this.ProductId = ProductId;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date OrderDate) {
        this.OrderDate = OrderDate;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "OrdersDTO{" + "OrderId=" + OrderId + ", UserId=" + UserId + ", ProductId=" + ProductId + ", OrderDate=" + OrderDate + ", Status=" + Status + '}';
    }

    public OrdersDTO(int OrderId, int UserId, String ProductId, Date OrderDate, boolean Status) {
        this.OrderId = OrderId;
        this.UserId = UserId;
        this.ProductId = ProductId;
        this.OrderDate = OrderDate;
        this.Status = Status;
    }

}

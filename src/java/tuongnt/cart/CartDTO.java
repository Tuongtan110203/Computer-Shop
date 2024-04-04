/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongnt.cart;

/**
 *
 * @author tuong
 */
public class CartDTO {
    public int CartId;
    public int UserId ;
    public String ProductId; 
    public int Quantity;

    public CartDTO(int CartId, int UserId, String ProductId, int Quantity) {
        this.CartId = CartId;
        this.UserId = UserId;
        this.ProductId = ProductId;
        this.Quantity = Quantity;
    }

    public CartDTO() {
    }

    @Override
    public String toString() {
        return "CartDTO{" + "CartId=" + CartId + ", UserId=" + UserId + ", ProductId=" + ProductId + ", Quantity=" + Quantity + '}';
    }

    public int getCartId() {
        return CartId;
    }

    public void setCartId(int CartId) {
        this.CartId = CartId;
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

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }
    
}

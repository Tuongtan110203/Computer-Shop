/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongnt.products;

import tuongnt.categories.CategoryDTO;

/**
 *
 * @author tuong
 */
public class ProductDTO {
     private String id,name;
    private int quantity;
    private double price;
    private String releaseDate,describe,image;
    private CategoryDTO category;

    public ProductDTO() {
    }

    @Override
    public String toString() {
        return "ProductDTO{" + "id=" + id + ", name=" + name + ", quantity=" + quantity + ", price=" + price + ", releaseDate=" + releaseDate + ", describe=" + describe + ", image=" + image + ", category=" + category + '}';
    }

    public ProductDTO(String id, String name, int quantity, double price, String releaseDate, String describe, String image, CategoryDTO category) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.releaseDate = releaseDate;
        this.describe = describe;
        this.image = image;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
    
}

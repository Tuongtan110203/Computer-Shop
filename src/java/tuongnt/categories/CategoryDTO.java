/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongnt.categories;

/**
 *
 * @author tuong
 */
public class CategoryDTO {
    private int id;
    private String name,describe;

    @Override
    public String toString() {
        return "CategoryDTO{" + "id=" + id + ", name=" + name + ", describe=" + describe + '}';
    }

    public CategoryDTO() {
    }

    public CategoryDTO(int id, String name, String describe) {
        this.id = id;
        this.name = name;
        this.describe = describe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
    
}

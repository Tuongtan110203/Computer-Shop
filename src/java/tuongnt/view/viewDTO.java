/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongnt.view;

/**
 *
 * @author tuong
 */
public class viewDTO {
    private int viewed;

    public viewDTO() {
    }

    public viewDTO(int viewed) {
        this.viewed = viewed;
    }

    public int getViewed() {
        return viewed;
    }

    public void setViewed(int viewed) {
        this.viewed = viewed;
    }

    @Override
    public String toString() {
        return "viewDTO{" + "viewed=" + viewed + '}';
    }
    
}

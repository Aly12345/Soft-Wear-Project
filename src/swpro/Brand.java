/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package swpro;

/**
 *
 * @author Ali Emad
 */
public class Brand {

    private String brandName;
    private String brandID;
    private String brandDesc;

    public Brand(String brandName, String brandID, String brandDesc) {
        this.brandName = brandName;
        this.brandID = brandID;
        this.brandDesc = brandDesc;
    }
    
    
    
    /**
     * @return the brandName
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * @param brandName the brandName to set
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * @return the brandID
     */
    public String getBrandID() {
        return brandID;
    }

    /**
     * @param brandID the brandID to set
     */
    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    /**
     * @return the brandDesc
     */
    public String getBrandDesc() {
        return brandDesc;
    }

    /**
     * @param brandDesc the brandDesc to set
     */
    public void setBrandDesc(String brandDesc) {
        this.brandDesc = brandDesc;
    }
    
  
    
}

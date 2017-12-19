/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package swpro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import static swpro.StoreOwner.stores;

/**
 *
 * @author Ali Emad
 */
public class Store {

    /**
     * @return the vouchers
     */
    public LinkedList<Voucher> getVouchers() {
        return vouchers;
    }

    /**
     * @param vouchers the vouchers to set
     */
    public void setVouchers(LinkedList<Voucher> vouchers) {
        this.vouchers = vouchers;
    }
    
    
    private String storeName;
    private String storeID;
    private String storePhysicalAddress;
    private String storeDesc;
    private String storeType;
    
    private Map<String , Product> products = new HashMap<>();
    
    private LinkedList<Voucher> vouchers = new LinkedList<>();
    

   
    
    

    public Map<String, Product> getProducts() {
        return products;
    }

    
   
    private StoreOwner storeOwner;

    /**
     * @return the storeName
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * @param storeName the storeName to set
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * @return the storeID
     */
    public String getStoreID() {
        return storeID;
    }

    /**
     * @param storeID the storeID to set
     */
    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    /**
     * @return the storePhysicalAddress
     */
    public String getStorePhysicalAddress() {
        return storePhysicalAddress;
    }

    /**
     * @param storePhysicalAddress the storePhysicalAddress to set
     */
    public void setStorePhysicalAddress(String storePhysicalAddress) {
        this.storePhysicalAddress = storePhysicalAddress;
    }

    /**
     * @return the storeDesc
     */
    public String getStoreDesc() {
        return storeDesc;
    }

    /**
     * @param storeDesc the storeDesc to set
     */
    public void setStoreDesc(String storeDesc) {
        this.storeDesc = storeDesc;
    }

    /**
     * @return the storeType
     */
    public String getStoreType() {
        return storeType;
    }

    /**
     * @param storeType the storeType to set
     */
    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

   

  
   

    

    /**
     * @return the storeOwner
     */
    public StoreOwner getStoreOwner() {
        return storeOwner;
    }

    /**
     * @param storeOwner the storeOwner to set
     */
    public void setStoreOwner(StoreOwner storeOwner) {
        this.storeOwner = storeOwner;
    }
    
    public boolean Like(String storeID , String productID){
        boolean flag = false;
            Store myStore = stores.get(storeID);
            if(myStore != null){
            Product myProduct = myStore.getProducts().get(productID);
            if(myProduct != null){
                myProduct.setLikeCounter();
                flag = true;
            }else{
                System.out.println("You entered Wrong Product ID.");
            }
            }else{
                System.out.println("You entered wrong Store ID.");
            }
            return flag;
        }
    
    
    
}

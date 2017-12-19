/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package swpro;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Ali Emad
 */
public interface UserController {
//==============================Common==========================================
    /**
     * @return the type
     */
    public String getUserType();

    /**
     * @param type the type to set
     */
    public void setUserType(String type);

    /**
     * @return the name
     */
    public String getName();

    /**
     * @param name the name to set
     */
    public void setName(String name);

    /**
     * @return the Password
     */
    public String getPassword();

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password);
    
    public void ExploreProduct();
    
    public void ViewSpecificProductDetails(String storeID , String productID);
    
    public void SuggestProductToSystem();
    
    public void Clear();
      
//============================Customer==========================================
    public void CustomerController(UserController user);
//==============================================================================
        
//============================StoreOwner========================================
    public void StoreOwnerController(UserController user);
//==============================================================================
    
//============================Admin=============================================
    public void AdminController(UserController user);
//==============================================================================
    
}

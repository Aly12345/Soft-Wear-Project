/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swpro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import static swpro.Admin.platFormProducts;
import static swpro.Admin.platFormBrands;

/**
 *
 * @author Ali Emad
 */
public class StoreOwner implements UserController {

    StoreOwner storeowner;
    String name;
    String password;
    String userType;
    private String storeOwnerType;
    public static Map<String, Store> stores = new HashMap<>();

    /**
     * @return the storeOwnerType
     */
    public String getStoreOwnerType() {
        return storeOwnerType;
    }

    /**
     * @param storeOwnerType the storeOwnerType to set
     */
    public void setStoreOwnerType(String storeOwnerType) {
        this.storeOwnerType = storeOwnerType;
    }

    public StoreOwner() {
    }

    public StoreOwner(String name, String password, String type, String storeOwnerType) {
        this.name = name;
        this.password = password;
        this.userType = type;
        this.storeOwnerType = storeOwnerType;
    }

//=====================================Common===================================
    @Override
    public String getUserType() {
        return userType;
    }

    @Override
    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String Password) {
        this.password = password;
    }

//=============================StoreHandelling==================================
    public void AddStore() {
        String storeName = "";
        String storeID = "";
        String storePhysicalAddress = "";
        String storeDesc = "";
        String storeType = "";
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Store Name : ");
        storeName = sc.next();
        System.out.println("Enter Store ID : ");
        storeID = sc.next();
        System.out.println("Enter Store Physical address if exist : ");
        storePhysicalAddress = sc.next();
        System.out.println("Enter Store Desc : ");
        storeDesc = sc.next();
        System.out.println("Enter Store Type : ");
        storeType = sc.next();

        Store newStore = new Store();
        if (stores.get(storeID) == null) {

            newStore.setStoreID(storeowner.getPassword() + storeID);
            newStore.setStoreName(storeName);
            newStore.setStoreOwner(storeowner);
            newStore.setStoreType(storeType);
            newStore.setStorePhysicalAddress(storePhysicalAddress);
            newStore.setStoreDesc(storeDesc);
            stores.put(newStore.getStoreID(), newStore);

        } else {
            System.out.println("This Store is already existed in the System.");
        }

    }

    public void AddProductToStore() {
        if (stores.size() == 0) {
            System.out.println("There is no stores");
            return;
        }

        if (platFormBrands.size() == 0) {
            System.out.println("There is no brands in the system...");
            return;
        }
        if (platFormProducts.size() == 0) {
            System.out.println("There is no Products in the system...");
            return;
        }

        System.out.println("=============Stores================");
        for (Map.Entry<String, Store> entry : stores.entrySet()) {
            String key = entry.getKey();
            Store value = entry.getValue();
            if (value.getStoreID().contains(storeowner.getPassword())) {
                System.out.println(value.getStoreID() + " : " + value.getStoreName());
            }

        }
        System.out.println("==============Products===============");

        for (Map.Entry<String, Product> entry : platFormProducts.entrySet()) {
            String key = entry.getKey();
            Product value = entry.getValue();
            if (value.GetProductStatus().equals("Approved")) {
                System.out.println(value.getProductID() + " : " + value.getProductName() + " | " + value.getBrand().getBrandID() + " : " + value.getBrand().getBrandName());
            }
        }
        String storeID = "";
        String productID = "";
        String brandID = "";
        String productName = "";
        Double productPrice;
        String productDesc = "";
        Integer quantity;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Store ID : ");
        storeID = sc.next();

        System.out.println("Enter the product ID : ");
        productID = sc.next();

        System.out.println("Enter the Brand ID : ");
        brandID = sc.next();

        Store myStore = stores.get(storeID);
        Brand myBrand = platFormBrands.get(brandID);
        Product myproduct = platFormProducts.get(productID);
        if (myStore == null) {
            System.out.println("There is no such Store.");
            return;
        } else {
            if (myBrand == null) {
                System.out.println("There is no such Brand.");
                return;
            } else {
                if (myproduct != null) {
                    if (!myproduct.GetProductStatus().equals("Approved")) {
                        System.out.println("This product is not approved.");
                        return;
                    } else {
                        if (CheckProductAvailability(storeID, myproduct) == false) {
                            Product newProduct = new Product();
                            newProduct.setBrand(myBrand);
                            newProduct.setProductDesc(myproduct.getProductDesc());
                            newProduct.setProductID(storeowner.getPassword() + stores.get(storeID).getStoreID() + productID);
                            newProduct.setProductName(myproduct.getProductName());
                            newProduct.setQuantity(myproduct.getQuantity());
                            newProduct.setProductStatus(myproduct.GetProductStatus());
                            newProduct.setProductPrice(myproduct.getProductPrice());
                            myStore.getProducts().put(newProduct.getProductID(), newProduct);
                        } else {
                            System.out.println("The product already exists.");
                        }
                    }

                } else {
                    System.out.println("This product is not in the System.");
                }
            }

        }

    }

    public boolean CheckProductAvailability(String storeID, Product product) {
        boolean flag = false;
        LinkedList<Product> myProducts = new LinkedList<>();
        for (Map.Entry<String, Product> entry : stores.get(storeID).getProducts().entrySet()) {
            String key = entry.getKey();
            Product value = entry.getValue();
            myProducts.add(value);
        }

        for (int i = 0; i < myProducts.size(); i++) {
            if(myProducts.get(i).getProductName().equals(product.getProductName())){
                flag = true;
                break;
            }
        }
        
        return flag;
    }
//==============================================================================

    @Override
    public void StoreOwnerController(UserController user) {
        Scanner sc = new Scanner(System.in);
        storeowner = (StoreOwner) user;

        while (true) {
            int choice;
            System.out.println("Welcome Mr. " + user.getName());
            System.out.println("Operations.");
            System.out.println("-----------");
            System.out.println("1.Explore Product");
            System.out.println("2.View Hits For Each Product");
            System.out.println("3.View Most Rated Product");
            System.out.println("4.Suggest Product To System");
            System.out.println("5.Add Store");
            System.out.println("6.Add Products");
            System.out.println("7.Log Out");
            System.out.println("------------------------");
            System.out.println("Enter number of the operation that you want");
            choice = sc.nextInt();
            Clear();
            switch (choice) {
                case 1: {
                    ExploreProduct();
                    Clear();
                    break;
                }
                case 2: {
                    ViewHitsForEachProduct();
                    Clear();
                    break;
                }
                case 3: {
                    ViewMostRatedProduct();
                    Clear();
                    break;
                }
                case 4: {
                    SuggestProductToSystem();
                    Clear();
                    break;
                }
                case 5: {
                    AddStore();
                    Clear();
                    break;
                }
                case 6: {
                    AddProductToStore();
                    Clear();
                    break;
                }
                case 7: {
                    LogIn login = new LogIn();

                    login.Classification();
                    Clear();
                    break;
                }
                default: {
                    System.out.println("You entered wrong input.");
                }
            }
        }
    }

    @Override
    public void ViewSpecificProductDetails(String storeID, String productID) {
        Store myStore = stores.get(storeID);
        if (myStore != null) {
            Product myProduct = myStore.getProducts().get(productID);
            if (myProduct != null) {
                System.out.println("Name : " + myProduct.getProductName() + " Brand : " + myProduct.getBrand().getBrandName() + " Price" + myProduct.getProductPrice());
                System.out.println("Desc : " + myProduct.getProductDesc());
            } else {
                System.out.println("You entered Wrong Product ID.");
            }
        } else {
            System.out.println("You entered wrong Store ID.");
        }

    }

    @Override
    public void ExploreProduct() {

        if (stores.size() == 0) {
            System.out.println("There is no Stores in the system.");
            return;
        }
        LinkedList<Boolean> Empty = new LinkedList<>();
        for (Map.Entry<String, Store> en : stores.entrySet()) {
            String key = en.getKey();
            Store value = en.getValue();
            System.out.println("For Store ID : " + key + " and Name : " + value.getStoreName() + " has products : ");
            System.out.println("ProductID   :   ProductName  :  Status :  #Likes");
            System.out.println("Size of products : " + stores.get(key).getProducts().size());
            for (Map.Entry<String, Product> entry : stores.get(key).getProducts().entrySet()) {
                String key1 = entry.getKey();
                Product value1 = entry.getValue();
                
                if (stores.get(key).getProducts().size() == 0) {
                    Empty.add(true);
                } else {
                    Empty.add(false);
                }
                if (key1.contains(storeowner.getPassword())) {
                    System.out.println(key1 + " : " + value1.getProductName() + " : " + value1.GetProductStatus() + " : " + value1.getLikeCounter());
                } else {
                    System.out.println(key1 + " : " + value1.getProductName() + " : " + "Not available" + " : " + value1.getLikeCounter());

                }
            }
        }

        boolean flag = false;
        for (int i = 0; i < Empty.size(); i++) {

            if (Empty.get(i) == false) {
                flag = true;
            }
        }
        if (flag == false) {
            System.out.println("There is no products in the system.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        String choice = "";
        String storeID = "";
        String productID = "";

        System.out.println("If you want to view specific details for a spesific product please enter 'ok'");
        choice = sc.next();
        if (choice.contains("ok")) {
            System.out.println("Enter the store ID : ");
            storeID = sc.next();
            System.out.println("Enter the product ID : ");
            productID = sc.next();
            ViewSpecificProductDetails(storeID, productID);
        }
        System.out.println("If you Liked Any of these products please enter 'like'");
        choice = sc.next();
        if (choice.contains("like")) {
            System.out.println("Enter the store ID : ");
            storeID = sc.next();
            System.out.println("Enter the product ID : ");
            productID = sc.next();
            stores.get(storeID).Like(storeID, productID);

            return;
        } else {
            return;
        }
    }

    public void ViewHitsForEachProduct() {
        if (stores.size() == 0) {
            System.out.println("There is no stores in the system...");
            return;
        }

        String storeID = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Store ID : ");
        storeID = sc.next();

        if (storeowner.getStoreOwnerType().equals("Premium")) {
            LinkedList<Product> myProducts = new LinkedList<>();
            if (stores.get(storeID) != null) {
                for (Map.Entry<String, Product> entry1 : stores.get(storeID).getProducts().entrySet()) {
                    if (stores.get(storeID).getProducts().size() != 0) {
                        String key1 = entry1.getKey();
                        Product value1 = entry1.getValue();

                        if (value1.GetProductStatus().equals("Approved")) {
                            myProducts.add(value1);
                        }
                    } else {
                        System.out.println("There is no products in the store...");
                        return;
                    }

                }

                Collections.sort(myProducts);
                Collections.reverse(myProducts);
                System.out.println("ProductID :  ProductName  :  Hits");
                for (int i = 0; i < myProducts.size(); i++) {
                    System.out.println(myProducts.get(i).getProductID() + " : " + myProducts.get(i).getProductName() + " : " + myProducts.get(i).getLikeCounter());

                }
            } else {
                System.out.println("Wrong storeID.");
                return;
            }
        } else {
            System.out.println("You are not Premium");
            return;
        }

    }

    public void ViewMostRatedProduct() {
        if (stores.size() == 0) {
            System.out.println("There is no stores in the system...");
            return;
        }
        String storeID = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Store ID : ");
        storeID = sc.next();

        if (storeowner.getStoreOwnerType().equals("Premium")) {
            LinkedList<Product> myProducts = new LinkedList<>();
            if (stores.get(storeID) != null) {
                for (Map.Entry<String, Product> entry1 : stores.get(storeID).getProducts().entrySet()) {
                    if (stores.get(storeID).getProducts().size() != 0) {
                        String key1 = entry1.getKey();
                        Product value1 = entry1.getValue();
                        if (value1.GetProductStatus().equals("Approved")) {
                            myProducts.add(value1);
                        }
                    } else {
                        System.out.println("There is no products...");
                        return;
                    }

                }

                Collections.sort(myProducts);
                Collections.reverse(myProducts);
                Product maxProduct = Collections.max(myProducts);
                System.out.println("ProductID  :  ProductName  :  Hits");
                System.out.println(maxProduct.getProductID() + " : " + maxProduct.getProductName() + " : " + maxProduct.getLikeCounter());
            } else {
                System.out.println("Wrong storeID.");
                return;
            }
        } else {
            System.out.println("You are not Premium");
            return;
        }

    }

    @Override
    public void SuggestProductToSystem() {
        if (platFormBrands.size() == 0) {
            System.out.println("There is no brands...");
            return;
        }
        for (Map.Entry<String, Brand> entry : platFormBrands.entrySet()) {
            String key = entry.getKey();
            Brand value = entry.getValue();
            System.out.println(value.getBrandID() + " : " + value.getBrandName());

        }
        System.out.println("==================================");
        Scanner sc = new Scanner(System.in);
        String productName = "";
        String productID = "";
        Double productPrice;
        String brandID = "";
        String productDesc = "";
        Integer quantity;
        System.out.println("Enter the product Name : ");
        productName = sc.next();
        System.out.println("Enter the product ID: ");
        productID = sc.next();
        System.out.println("Enter the product Price : ");
        productPrice = sc.nextDouble();
        System.out.println("Enter the Brand ID : ");
        brandID = sc.next();
        System.out.println("Enter the product Desc : ");
        productDesc = sc.next();
        System.out.println("Enter the product Quantity : ");
        quantity = sc.nextInt();
        if (platFormBrands.get(brandID) == null) {
            System.out.println("There is no such brand in the system.");
            return;
        } else {
            if (platFormProducts.get(productID) == null) {
                Product newProduct = new Product();
                Brand productBrand = platFormBrands.get(brandID);
                newProduct.setBrand(productBrand);
                newProduct.setProductDesc(productDesc);
                newProduct.setProductID(productID);
                newProduct.setProductName(productName);
                newProduct.setProductPrice(productPrice);
                newProduct.setProductStatus("Suggested");
                newProduct.setQuantity(quantity);
                platFormProducts.put(productID, newProduct);

            } else {
                System.out.println("This product is already exists in the System.");
            }
        }
    }

    @Override
    public void Clear() {
        for (int i = 0; i < 7; i++) {
            System.out.println();
        }
    }

    //--------------------------UnUsed------------------------------------------
    @Override
    public void AdminController(UserController user) {
    }

    @Override
    public void CustomerController(UserController user) {
    }

}

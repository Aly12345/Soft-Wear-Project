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
import java.util.Scanner;
import static swpro.StoreOwner.stores;

/**
 *
 * @author Ali Emad
 */
public class Admin implements UserController {

    public Admin() {
    }
    Admin myUser;

    String name;
    String password;
    String type;
    public static Map<String, Brand> platFormBrands = new HashMap<>();
    public static Map<String, Product> platFormProducts = new HashMap<>();

    public Admin(String name, String password, String type) {
        this.name = name;
        this.password = password;
        this.type = type;
    }

//=============================Common===========================================
    @Override
    public String getUserType() {
        return type;
    }

    @Override
    public void setUserType(String type) {
        this.type = type;
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
//==============================================================================  

    @Override
    public void AdminController(UserController user) {
        myUser = (Admin)user;
        Scanner sc = new Scanner(System.in);
        
        while(true){
        int choice;
        System.out.println("Welcome Admin. "+myUser.getName());
        System.out.println("Operations.");
        System.out.println("-----------");
        System.out.println("1.Explore Product");
        System.out.println("2.Add Brand To System");
        System.out.println("3.Add Product To System");
        System.out.println("4.Approval");
        System.out.println("5.Provide Vouchers To Stores");
        System.out.println("6.Log Out");
        System.out.println("------------------------");
        System.out.println("Enter number of the operation that you want");
        choice = sc.nextInt();
        Clear();
        switch(choice){
            case 1:{
                ExploreProduct();
                Clear();
                break;
            }
            case 2:{
                AddBrandToSystem();
                Clear();
                break;
            }
            case 3:{
                AddProdctToSystem();
                Clear();
                break;
            }
            case 4:{
                Approval();
                Clear();
                break;
            }
            case 5:{
                ProvideVouchersToStores();
                Clear();
                break;
            }
            case 6:{
                LogIn login = new LogIn();
                login.Classification();
                Clear();
                break;
            }
            default:{
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
        if(stores.size() == 0){
            System.out.println("There is no Stores in the system.");
            return;
        }
        LinkedList<Boolean> Empty = new LinkedList<>();
        for (Map.Entry<String, Store> en : stores.entrySet()) {
            String key = en.getKey();
            Store value = en.getValue();
            System.out.println("For Store ID : " + key + " and Name : " + value.getStoreName() + " has products : ");
            System.out.println("ProductID   :   ProductName  :  Status :  #Likes");
            for (Map.Entry<String, Product> entry : stores.get(key).getProducts().entrySet()) {
                
                String key1 = entry.getKey();
                Product value1 = entry.getValue();
                if(stores.get(key).getProducts().size() == 0){
                    Empty.add(true);
                }else{
                    Empty.add(false);
                }
                System.out.println(key1 + " : " + value1.getProductName() + " : " + value1.GetProductStatus() + " : " + value1.getLikeCounter());

            }
        }
        boolean flag = false;
        for (int i = 0; i < Empty.size(); i++) {
            
            if(Empty.get(i) == false){
                flag = true;
            }
        }
        if(flag == false){
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
            if(stores.get(storeID) != null){
            System.out.println("Enter the store ID : ");
            storeID = sc.next();
            System.out.println("Enter the product ID : ");
            productID = sc.next();
            stores.get(storeID).Like(storeID, productID);
            return;
            }else{
                System.out.println("There is no such product");
                return;
            }
        } else {
            return;
        }
    }

    public void AddBrandToSystem() {
        String brandName = ""; String brandID = ""; String brandDesc="";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the brand name : ");
        brandName = sc.next();
        System.out.println("Enter the brand ID : ");
        brandID = sc.next();
        System.out.println("Enter the brand Desc : ");
        brandDesc = sc.next();
        
        if (platFormBrands.get(brandID) == null) {
            Brand newBrand = new Brand(brandName, brandID, brandDesc);
            platFormBrands.put(brandID, newBrand);
        } else {
            System.out.println("This brand is already exists in the System.");
        }
    }

    public void AddProdctToSystem() {
        if(platFormBrands.size() == 0){
            System.out.println("There is no brand in the system...");
            return;
        }
        for (Map.Entry<String, Brand> entry : platFormBrands.entrySet()) {
            String key = entry.getKey();
            Brand value = entry.getValue();
            System.out.println(value.getBrandID()+" : "+value.getBrandName());
        }
        System.out.println("================");
        Scanner sc = new Scanner(System.in);
        String productName=""; String productID=""; Double productPrice; String brandID=""; String productDesc=""; Integer quantity;
        System.out.println("Enter the product Name : ");
        productName = sc.next();
        System.out.println("Enter the product ID : ");
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
        } 
        else 
        {
            if (platFormProducts.get(productID) == null) {
              Product newProduct = new Product();
              Brand productBrand = platFormBrands.get(brandID);
               newProduct.setBrand(productBrand);
               newProduct.setProductDesc(productDesc);
               newProduct.setProductID(productID);
               newProduct.setProductName(productName);
               newProduct.setProductPrice(productPrice);
               newProduct.setProductStatus("Approved");
               newProduct.setQuantity(quantity);
               platFormProducts.put(productID ,newProduct);
                
            } 
            else 
            {
                System.out.println("This product is already exists in the System.");
            }
        }
    }

    public void Approval(){
        if(platFormProducts.size() != 0){
            if(stores.size() == 0){
                System.out.println("There is no stores in the system...");
                return;
            }
        System.out.println("Platform Products : ");
        System.out.println("--------------------");
        for (Map.Entry<String, Product> entry : platFormProducts.entrySet()) {
            String key = entry.getKey();
            Product value = entry.getValue();
            if(value.GetProductStatus().equals("Suggested")){
            System.out.println(value.getProductID()+" : "+value.getProductName()+" : "+value.GetProductStatus());
            System.out.println("----------------------------------------------");
            }
        }
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the id of the product that you want to approve it : ");
        choice = sc.nextInt();
        if(platFormProducts.get(choice+"") != null){
            Product approvedProduct = platFormProducts.get(choice+"");
            if(approvedProduct.GetProductStatus().equals("Suggested")){
                approvedProduct.setProductStatus("Approved");
                System.out.println("Success !!!");
            }else{
                System.out.println("This product is alreaady approved.");
            }
        }else{
            System.out.println("There is no product like that.");
        }
    }else{
            System.out.println("There is no products in the system.");
            return;
        }
    }
    
    public void ProvideVouchersToStores(){
        if(stores.size() != 0){
        System.out.println("The stores in the platform : ");
        System.out.println("-----------------------------");
        System.out.println("ID   :   StoreName");
        for (Map.Entry<String, Store> entry : stores.entrySet()) {
            String key = entry.getKey();
            Store value = entry.getValue();
            System.out.println(value.getStoreID()+" : "+value.getStoreName());
        }
        String choice = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter The id of the store that you want tp provide it a voucger card.");
        choice = sc.next();
        Store myStore = stores.get(choice);
        if(myStore != null){
            String value = "";
            int numberOfVouchers;
            System.out.println("Enter the value of this voucher card : ");
            value = sc.next();
            System.out.println("Enter how many voucher cards from this value you want to provide for this store : ");
            numberOfVouchers = sc.nextInt();
            for (int i = 0; i < numberOfVouchers; i++) {
                Voucher voucher = new Voucher(myStore.getStoreID()+myStore.getVouchers().size(), value);
                myStore.getVouchers().add(voucher);
            }
            
        }else{
            System.out.println("There is no store with this id.");
        }
    }else{
            System.out.println("There is no stores in the system.");
            return;
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
    public void CustomerController(UserController user) {
    }

    @Override
    public void StoreOwnerController(UserController user) {
    }

    @Override
    public void SuggestProductToSystem() {
    }

}

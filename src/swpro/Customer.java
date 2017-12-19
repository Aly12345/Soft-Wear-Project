/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swpro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import static swpro.Admin.platFormBrands;
import static swpro.Admin.platFormProducts;
import static swpro.StoreOwner.stores;

/**
 *
 * @author Ali Emad
 */
public class Customer implements UserController {

    /**
     * @param myVouchers the myVouchers to set
     */
    public void setMyVouchers(LinkedList<Voucher> myVouchers) {
        this.myVouchers.clear();
        for (int i = 0; i < myVouchers.size(); i++) {
            this.myVouchers.add(myVouchers.get(i));
        }
        
    }
    Customer customer;
    private ArrayList<Product> products;
    private String brandDesc;
    private String productDesc;
    private Date orderDate;
    private Map<String, Integer> numberOfPoints = new HashMap<>();
    private LinkedList<Voucher> myVouchers = new LinkedList<>();
    private Integer PointsCounter;
    private LinkedList<Product> customerProduct = new LinkedList<>();

    String name;
    String password;
    String type;
    

    public Integer GetPointsCounter(){
        return this.PointsCounter;
    }
    
    public void SetPointsCounter(Integer pointsCount){
        this.PointsCounter = pointsCount;
    }
    /**
     * @return the numberOfPoints
     */
    public Map<String, Integer> getNumberOfPoints() {
        return numberOfPoints;
    }

    

    /**
     * @return the myVouchers
     */
    public LinkedList<Voucher> getMyVouchers() {
        return myVouchers;
    }

   

    public Customer() {
    }

    public Customer(String name, String password, String type) {
        this.name = name;
        this.password = password;
        this.type = type;
    }

    

//==================================Common======================================
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
    
//===========================Customer Controller================================

    @Override
    public void CustomerController(UserController user) {
        Scanner sc = new Scanner(System.in);
        customer = (Customer)user;
        while(true){
            int choice;
        System.out.println("Welcome Mr. "+customer.getName());
        System.out.println("Operations.");
        System.out.println("-----------");
        System.out.println("1.Explore Product");
        System.out.println("2.Suggest Product To System");
        System.out.println("3.Aquires Voucher");
        System.out.println("4.Buy Product");
        System.out.println("5.Show My Products.");
        System.out.println("6.Log Out.");
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
                SuggestProductToSystem();
                Clear();
                break;
            }
            case 3:{
                VoucherAquires();
                Clear();
                break;
            }
            case 4:{
                BuyProduct();
                Clear();
                break;
            }
            case 5:{
                ShowMyProducts();
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
                if (myProduct.GetProductStatus().equals("Approved")) {
                    System.out.println("Name : " + myProduct.getProductName() + " Brand : " + myProduct.getBrand().getBrandName() + " Price" + myProduct.getProductPrice());
                    System.out.println("Desc : " + myProduct.getProductDesc());
                } else {
                    System.out.println("This product is not approved from the Admin.");
                }
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
            System.out.println("ProductID   :   ProductName  :  #Likes : Quantity");
            for (Map.Entry<String, Product> entry : stores.get(key).getProducts().entrySet()) {
                String key1 = entry.getKey();
                Product value1 = entry.getValue();
                if(stores.get(key).getProducts().size() == 0){
                    Empty.add(true);
                }else{
                    Empty.add(false);
                }
                
                if (!value1.GetProductStatus().equals("Suggested")) {
                    System.out.println(key1 + " : " + value1.getProductName() + " : " + value1.getLikeCounter()+" : "+value1.getQuantity());
                }
            }
        }

         boolean flag_1 = false;
        for (int i = 0; i < Empty.size(); i++) {
            
            if(Empty.get(i) == false){
                flag_1 = true;
            }
        }
        if(flag_1 == false){
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
            boolean flag = stores.get(storeID).Like(storeID, productID);

            //*************************************************
            //-----------------provid points For Customer--------------------
            if (flag == true) {
                if (customer.getNumberOfPoints().get(storeID) == null) {
                    
                    customer.getNumberOfPoints().put(storeID, 1);
                } else {
                    int n = customer.getNumberOfPoints().get(storeID);
                    customer.getNumberOfPoints().remove(storeID);
                    customer.getNumberOfPoints().put(storeID, n+1);
                }
            }
            System.out.print("+++++++++++++++++++Number of Points : ");
            System.out.println(customer.getNumberOfPoints().get(storeID));
            //*************************************************

            return;
        } else {
            return;
        }
    }

    @Override
    public void SuggestProductToSystem() {
        if(platFormBrands.size() == 0){
            System.out.println("There is no brands...");
            return;
        }
        for (Map.Entry<String, Brand> entry : platFormBrands.entrySet()) {
            String key = entry.getKey();
            Brand value = entry.getValue();
            System.out.println(value.getBrandID()+" : "+value.getBrandName());
            
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

    public void VoucherAquires() {
       if(stores.size() == 0){
            System.out.println("There is no stores in the System...");
            return;
        }
       
        if(customer.getNumberOfPoints().size() == 0){
            System.out.println("There is no points for you...");
            return;
        }
        for (Map.Entry<String, Integer> entry : customer.getNumberOfPoints().entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            Store store = stores.get(key);
            if (store.getVouchers().size() != 0) {
                if (customer.getNumberOfPoints().get(key) >= 3) {
                  
                    Voucher voucher = stores.get(key).getVouchers().removeFirst();
                    customer.getMyVouchers().add(voucher);
                    System.out.println("You get a voucher from store : " + stores.get(key).getStoreName() + " has a value = " + voucher.getVoucherValue());
                    System.out.println("------------------------------");
                }
            } else {
                System.out.println("There is no vouchers in the store.");
            }

        }
    }

    public void BuyProduct() {
        if(stores.size() == 0){
            System.out.println("There is no Stores in the system.");
            return;
        }
        LinkedList<Boolean> Empty = new LinkedList<>();
        for (Map.Entry<String, Store> entry : stores.entrySet()) {
            String key = entry.getKey();
            Store value = entry.getValue();
            System.out.println("StoreID : "+value.getStoreID() +" storeName : "+ value.getStoreName() + " has : ");
            for (Map.Entry<String, Product> entry1 : value.getProducts().entrySet()) {
                String key1 = entry1.getKey();
                Product value1 = entry1.getValue();
                if(stores.get(key).getProducts().size() == 0){
                    Empty.add(true);
                }else{
                    Empty.add(false);
                }
                if (value1.GetProductStatus().equals("Approved")) {
                    System.out.println(value1.getProductID() + " : " + value1.getProductName() + " : " + value1.getProductPrice());
                }
            }
        }
         boolean flag_1 = false;
        for (int i = 0; i < Empty.size(); i++) {
            
            if(Empty.get(i) == false){
                flag_1 = true;
            }
        }
        if(flag_1 == false){
            System.out.println("There is no products in the system.");
            return;
        }
        System.out.println("------------------MyVouchers-------------------");
        for (int i = 0; i < customer.getMyVouchers().size(); i++) {
            System.out.println(customer.getMyVouchers().get(i).getVoucherValue());
        }
        System.out.println("-------------------------------------");
        String productId = "";
        String storeId = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the id of the Store that you want to purchase from : ");
        storeId = sc.next();

        System.out.println("Enter the id of the Product that you want purchase : ");
        productId = sc.next();

        if (stores.get(storeId) != null) {
            if (stores.get(storeId).getProducts().get(productId) != null) {
                if (stores.get(storeId).getProducts().get(productId).GetProductStatus().equals("Approved")) {
                    
                    Product myProduct = stores.get(storeId).getProducts().get(productId);
                    if(myProduct.getQuantity() <= 0){
                        System.out.println("there is no much available quantity...");
                        return;
                    }
                    boolean flag = CheckPurchaseAvailability(storeId, myProduct);

                    if (flag == true) {
                        int quantity = myProduct.getQuantity() - 1;
                        myProduct.setQuantity(quantity);
                        System.out.println("Success !!!");
                        customer.customerProduct.add(myProduct);
                    }else{
                        System.out.println("You dont have enough vouchers to buy this product.");
                    }
                }else{
            System.out.println("This Product is not available now.");
        }
            }else{
            System.out.println("There is no such Product.");
        }
        }else{
            System.out.println("There is no such Store.");
        }

    }

    public boolean CheckPurchaseAvailability(String storeID, Product product) {
        int sum = 0;
        boolean flag = false;
        LinkedList<Voucher> indeces = new LinkedList<>();
        if(customer.getMyVouchers().size() == 0){
            System.out.println("You dont have vouchers...");
            return false;
        }
        LinkedList<Voucher> myVouchers_2 = new LinkedList<>();
        
        for (int i = 0; i < customer.getMyVouchers().size(); i++) {
            myVouchers_2.add(customer.getMyVouchers().get(i));
        }
        
        Collections.sort(myVouchers_2);
        
        for (int i = 0; i < myVouchers_2.size(); i++) {
            if (myVouchers_2.get(i).getCode().contains(storeID)) {
                sum += Integer.parseInt(myVouchers_2.get(i).getVoucherValue());
                indeces.add(myVouchers_2.get(i));
                if (sum >=product.getProductPrice().intValue()) {
                    flag = true;
                    break;
                }
            }
        }
        if (flag == true) {
            for (int i = 0; i < indeces.size(); i++) {
                myVouchers_2.remove(indeces.get(i));
            }
        }
        customer.setMyVouchers(myVouchers_2);
        indeces.clear();

        return flag;
    }

    public void ShowMyProducts(){
        System.out.println("=========My Products=============");
        for (int i = 0; i < customer.customerProduct.size(); i++) {
            System.out.println(customer.customerProduct.get(i).getProductName());
        }
        System.out.println("=================================");
    }
    
    @Override
    public void Clear() {
        for (int i = 0; i < 7; i++) {
            System.out.println();
        }
    }
    
    //--------------------------UnUsed------------------------------------------
    @Override
    public void StoreOwnerController(UserController user) {
    }

    @Override
    public void AdminController(UserController user) {
    }
    
}

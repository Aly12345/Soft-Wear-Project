/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swpro;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Ali Emad
 */
public class LogIn {

    static Map<String, UserController> clients = new HashMap<>();

    public UserController LogIn_To_System() {
        System.out.println("Login...");
        String password = "";
        String name = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name : ");
        name = sc.next();
        System.out.println("Enter your password : ");
        password = sc.next();

        UserController user = clients.get(password);
        if (user == null) {
            
            System.out.println("The User IS NOT existing in the System, Try another Password or register.");
            String choice = "";
            System.out.println("if you want to regester enter 'y' else enter any other key.");

            choice = sc.next();
            if (choice.equals("y")) {
                String name_1 = "";
                String password_1 = "";
                String type_1 = "";
                System.out.println("Enter your name : ");
                name_1 = sc.next();
                System.out.println("Enter your password : ");
                password_1 = sc.next();
                System.out.println("Enter your Type : ");
                type_1 = sc.next();
                boolean flag = Regesteration(name_1, password_1, type_1);
                if(flag == true){
                    Classification();
                }
                else{
                    System.exit(0);
                }
                
            } else {
                Classification();
                
            }
        } 
        else {
            
            if(!user.getName().equals(name)){
                System.out.println("Access Denied");
                System.exit(0);
                
            }
            
            else
       System.out.println("Welcome Mr." + name);

        }
        return user;
    }

    public static boolean Regesteration(String name, String password, String type) {
        boolean flag = false;
        UserController user = clients.get(password);
        if (user == null) {

            if (type.equals("Admin")) {
                Admin newAdmin = new Admin(name, password, type);
                clients.put(password, newAdmin);
                flag = true;
            } else if (type.equals("Customer")) {
                Customer newCustomer = new Customer(name, password, type);
                clients.put(password, newCustomer);
                flag = true;
            } else if (type.equals("StoreOwner")) {

                Scanner sc = new Scanner(System.in);
                String storeOwnerType = "";
                System.out.println("Enter the Store Owner Type : ");
                storeOwnerType = sc.next();
                StoreOwner newStoreOwner = new StoreOwner(name, password, type, storeOwnerType);

                clients.put(password, newStoreOwner);
                flag = true;
            }
            System.out.println("DONE!!!!");
        } else {
            System.out.println("The user '" + name + "' is already existing in the system try another password.");
        }
        return flag;
    }

    public void Classification() {
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome To The System.");
        System.out.println("----------------------");
        System.out.println("1.Login.");
        System.out.println("2.Regesteration.");
        System.out.println("3.Exit.");
        System.out.println("----------------------");
        System.out.println("Enter number of your choice.");
        choice = sc.nextInt();
        switch (choice) {
            case 1: {
                UserController user = LogIn_To_System();
                String type = "";
                if (user != null) {
                    type = user.getUserType();
                    switch (type) {
                        case "Customer": {
                            Customer customer = new Customer();
                            customer.CustomerController(user);
                            break;
                        }
                        case "Admin": {
                            Admin admin = new Admin();
                            admin.AdminController(user);
                            break;
                        }
                        case "StoreOwner": {
                            StoreOwner storeOwner = new StoreOwner();
                            storeOwner.StoreOwnerController(user);
                            break;
                        }

                    }
                }
                break;
            }
            case 2: {
                String name = "";
                String password = "";
                String type = "";
                System.out.println("Enter Your name : ");
                name = sc.next();
                System.out.println("Enter Your Password : ");
                password = sc.next();
                System.out.println("Enter Type : ");
                type = sc.next();
                boolean flag = Regesteration(name, password, type);
                if(flag == true){
                    Classification();
                }
                else{
                    System.exit(0);
                }
                break;
            }
            case 3:{
                System.exit(0);
                break;
            }
            default:{
                System.out.println("Wrong Entry...");
            }
            
         

    }

}
}

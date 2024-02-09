import java.util.Objects;
import java.util.Random;
import java.util.Scanner;


interface customerFunctions{
    double DeliveryCharges(double orderPrice);
    double getDiscount();
    String DeliveryTime();
}
abstract class Customer implements customerFunctions{
    private String name;
    private String password;
    private int age;
    private String phoneNo;
    private String emailId;
    private double balance = 1000;
    boolean isRegistered = false;

    int[] coupons = new int[1000];
    Products[] cart = new Products[1000];
    int[] Quantity = new int[1000];

    int noOfProducts = 0;
    int noOfCoupons = 0;


    public void viewCart(){
        for(int i = 0; i < this.noOfProducts; i++){
            if(this.cart[i].getClass() == Products.class){

                System.out.println("Product name is " +  this.cart[i].getProductName());
                System.out.println("Product Id is " + this.cart[i].getProductId());
                System.out.println("Product price is " + Double.toString(this.cart[i].getProductPrice()));
                System.out.println("Quantity : " + Integer.toString(this.Quantity[i]));
                this.cart[i].productDesc();

                System.out.println("-----");
            }
            else{
                System.out.println("DealId is " +   Integer.toString(this.cart[i].getDealId()));
                System.out.println("Id of product1 is " + this.cart[i].getProductId1());
                System.out.println("Id of product2 is " + this.cart[i].getProductId2());

                System.out.println("Deal price for you is  " +Double.toString(this.cart[i].getDealPrice(this)));


                System.out.println("Quantity : " + Integer.toString(this.Quantity[i]));
                System.out.println("------");
            }

        }
    }





    public void ultimateSumCart(Categories[] plist, int plen){
        double totalCost = 0;
        double discountedCost = 0;
        int safetyFlag = 0;
        for(int i = 0; i < this.noOfProducts; i++){
            if(this.cart[i].getClass() == Products.class){
                if(this.cart[i].getProductQuantity() < this.Quantity[i]){
                    System.out.println(this.cart[i].getProductId());
                    System.out.println("Not available in the required quantity");
                    System.out.println("Empty your cart");
                    safetyFlag = 1;
                    break;
                }
            }
            if(this.cart[i].getClass() == giveAwayDeals.class){
                Products p1 = Admin.findProduct(plist, plen ,this.cart[i].getProductId1());
                Products p2 = Admin.findProduct(plist, plen ,this.cart[i].getProductId2());
                if(p1.getProductQuantity() >= this.Quantity[i] && p2.getProductQuantity() >= this.Quantity[i]){

                }
                else{
                    System.out.println(this.cart[i].getDealId());
                    System.out.println("Not available in the required quantity");
                    System.out.println("Empty your cart");
                    safetyFlag = 1;
                    break;
                }
            }
        }
        if(safetyFlag == 0){
            double maxCouponDiscount = 0;
            int maxCouponDiscountNumber = 0;

            for(int i = 0; i < this.noOfCoupons; i++){
                if(this.coupons[i] > maxCouponDiscount){
                    maxCouponDiscountNumber = i;
                    maxCouponDiscount = this.coupons[i];
                }
            }

            for(int i = 0; i < this.noOfProducts; i++){
                if(this.cart[i].getClass() == Products.class){
                    if(this.getClass() == NormalCustomer.class){
                        totalCost = totalCost + this.cart[i].getProductPrice()*this.Quantity[i];
                        discountedCost = discountedCost + this.cart[i].getProductPrice()*(100 - this.cart[i].discountArray[0])*0.01*this.Quantity[i];
                        this.cart[i].setProductQuantity(this.cart[i].getProductQuantity() - this.Quantity[i]);
//                        this.Quantity[i] = 0;
                    }
                    if(this.getClass() == PrimeCustomer.class){
                        if(maxCouponDiscount > 5){
                            if(this.cart[i].discountArray[1] >= maxCouponDiscount){
                                totalCost = totalCost + this.cart[i].getProductPrice()*this.Quantity[i];
                                discountedCost = discountedCost + this.cart[i].getProductPrice()*(100 - this.cart[i].discountArray[1])*0.01*this.Quantity[i];
                                this.cart[i].setProductQuantity(this.cart[i].getProductQuantity() - this.Quantity[i]);
//                                this.Quantity[i] = 0;
                            }
                            else{
                                totalCost = totalCost + this.cart[i].getProductPrice()*this.Quantity[i];
                                discountedCost = discountedCost + this.cart[i].getProductPrice()*(100 - maxCouponDiscount)*0.01*this.Quantity[i];
                                this.cart[i].setProductQuantity(this.cart[i].getProductQuantity() - this.Quantity[i]);
//                                this.Quantity[i] = 0;
                                this.coupons[maxCouponDiscountNumber] = 0;
                            }
                        }
                        else{
                            if(this.cart[i].discountArray[1] >= 5){
                                totalCost = totalCost + this.cart[i].getProductPrice()*this.Quantity[i];
                                discountedCost = discountedCost + this.cart[i].getProductPrice()*(100 - this.cart[i].discountArray[1])*0.01*this.Quantity[i];
                                this.cart[i].setProductQuantity(this.cart[i].getProductQuantity() - this.Quantity[i]);
//                                this.Quantity[i] = 0;
                            }
                            else{
                                totalCost = totalCost + this.cart[i].getProductPrice()*this.Quantity[i];
                                discountedCost = discountedCost + this.cart[i].getProductPrice()*(100 - 5)*0.01*this.Quantity[i];
                                this.cart[i].setProductQuantity(this.cart[i].getProductQuantity() - this.Quantity[i]);
//                                this.Quantity[i] = 0;

                            }
                        }
                    }
                    if(this.getClass() == EliteCustomer.class){
                        if(maxCouponDiscount > 10){
                            if(this.cart[i].discountArray[2] >= maxCouponDiscount){
                                totalCost = totalCost + this.cart[i].getProductPrice()*this.Quantity[i];
                                discountedCost = discountedCost + this.cart[i].getProductPrice()*(100 - this.cart[i].discountArray[2])*0.01*this.Quantity[i];
                                this.cart[i].setProductQuantity(this.cart[i].getProductQuantity() - this.Quantity[i]);
//                                this.Quantity[i] = 0;
                            }
                            else{
                                totalCost = totalCost + this.cart[i].getProductPrice()*this.Quantity[i];
                                discountedCost = discountedCost + this.cart[i].getProductPrice()*(100 - maxCouponDiscount)*0.01*this.Quantity[i];
                                this.cart[i].setProductQuantity(this.cart[i].getProductQuantity() - this.Quantity[i]);
//                                this.Quantity[i] = 0;
                                this.coupons[maxCouponDiscountNumber] = 0;
                            }
                        }
                        else{
                            if(this.cart[i].discountArray[2] >= 10){
                                totalCost = totalCost + this.cart[i].getProductPrice()*this.Quantity[i];
                                discountedCost = discountedCost + this.cart[i].getProductPrice()*(100 - this.cart[i].discountArray[2])*0.01*this.Quantity[i];
                                this.cart[i].setProductQuantity(this.cart[i].getProductQuantity() - this.Quantity[i]);
//                                this.Quantity[i] = 0;
                            }
                            else{
                                totalCost = totalCost + this.cart[i].getProductPrice()*this.Quantity[i];
                                discountedCost = discountedCost + this.cart[i].getProductPrice()*(100 - 10)*0.01*this.Quantity[i];
                                this.cart[i].setProductQuantity(this.cart[i].getProductQuantity() - this.Quantity[i]);
//                                this.Quantity[i] = 0;

                            }
                        }
                    }

                }

                if(this.cart[i].getClass() == giveAwayDeals.class){
                    Products p1 = Admin.findProduct(plist, plen ,this.cart[i].getProductId1());
                    Products p2 = Admin.findProduct(plist, plen ,this.cart[i].getProductId2());

                    totalCost = totalCost + (this.cart[i].getDealPrice(this))*(this.Quantity[i]);
                    discountedCost = discountedCost + (this.cart[i].getDealPrice(this))*(this.Quantity[i]);

                    p1.setProductQuantity(p1.getProductQuantity() - this.Quantity[i]);
                    p2.setProductQuantity(p2.getProductQuantity() - this.Quantity[i]);
                }
            }

            ///
            if(this.balance >= discountedCost){

                System.out.println("Delivery charges are : " + Double.toString(this.DeliveryCharges(totalCost)));
                System.out.println("Discount of "+ Double.toString(totalCost - discountedCost) +" Rs");
                System.out.println("Total cost is "+ Double.toString(discountedCost));
                System.out.println("Order placed . It will be delivered within "+ this.DeliveryTime());

                this.balance = this.balance - discountedCost;
                this.Quantity = new int[1000];
                this.cart = new Products[1000];
                this.noOfProducts = 0;


                if(discountedCost > 5000){
                    Random a = new Random();

                    if(this.getClass() == EliteCustomer.class){
                        int a3 = a.nextInt(3,5);
                        for(int i4 = 0; i4 < a3; i4++){
                            this.coupons[i4] = a.nextInt(5,16);
                            this.noOfCoupons++;
                        }

                    }

                    if(this.getClass() == PrimeCustomer.class){
                        int a3 = a.nextInt(1,3);
                        for(int i4 = 0; i4 < a3; i4++){
                            this.coupons[i4] = a.nextInt(5,16);
                            this.noOfCoupons++;
                        }
                    }

                }


            }

            else{

                System.out.println("Insufficient Balance");
                System.out.println("Empty your cart or add money in your wallet ");
                for(int i = 0; i < this.noOfProducts; i++){
                    if(this.cart[i].getClass() == Products.class){
                        this.cart[i].setProductQuantity(this.cart[i].getProductQuantity() + this.Quantity[i]);

                    }
                    if(this.cart[i].getClass() == giveAwayDeals.class){
                        Products p1 = Admin.findProduct(plist, plen ,this.cart[i].getProductId1());
                        Products p2 = Admin.findProduct(plist, plen ,this.cart[i].getProductId2());

                        p1.setProductQuantity(p1.getProductQuantity() + this.Quantity[i]);
                        p2.setProductQuantity(p2.getProductQuantity() + this.Quantity[i]);
                    }
                }
            }

        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean balanceDeducter(double ded){
        if(this.getBalance() - ded >= 0){
            return true;
        }
        return false;
    }
}


interface Category{
    //addcategory delete product

}



class Categories implements Category{
    int categoryId = 0;
    String categoryName;
    boolean isCategoryDeleted = false;
    int noOfProducts = 0;
    Products[] productList = new Products[1000];
    public static boolean checkId(Categories[] clist, int clen, int ci){
        for(int i = 0; i < clen; i++){
            if(clist[i].categoryId == ci && !clist[i].isCategoryDeleted){
                return true;
            }
        }
        return false;
    }


}
class Products {

    private String productId;
    private String productName;
    private double productPrice;
    boolean isProductDeleted = false;

    private int productQuantity = 0;
    int noOFlinDesc = 0;
    String[] descOfProduct;
    int[] discountArray = new int[3];

    Products(){

    }




    Products(String pid, String pname, double pprice){
        this.productId = pid;
        this.productName = pname;
        this.productPrice = pprice;
        this.descOfProduct = new String[1000];
    }
    public void productDesc(){
        for(int k = 0; k < this.noOFlinDesc; k++){
            System.out.println(this.descOfProduct[k]);
        }
    }
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
    public String getProductId1() {
        return "";
    }
    public String getProductId2() {
        return "";
    }
    public double getDealPrice(Customer c) {
        return 0;
    }
    public int getDealId(){return 0;}

}

class giveAwayDeals extends Products{

    private String productId1;
    private String productId2;

    private int dealId;
    double[] DealPrice = new double[3];

    giveAwayDeals(String p1, String p2,int did){

        this.productId1 = p1;
        this.productId2 = p2;

        this.dealId = did;

    }

    @Override
    public String getProductId1() {
        return productId1;
    }

    public void setProductId1(String productId1) {
        this.productId1 = productId1;
    }

    @Override
    public String getProductId2() {
        return productId2;
    }

    public void setProductId2(String productId2) {
        this.productId2 = productId2;
    }

    @Override
    public double getDealPrice(Customer c){
        if(c.getClass() == NormalCustomer.class){
            return DealPrice[0];
        }
        if(c.getClass() == PrimeCustomer.class){
            return DealPrice[1];
        }
        return DealPrice[2];

    }



    public int getDealId() {
        return dealId;
    }

    public void setDealId(int dealId) {
        this.dealId = dealId;
    }
}

class Admin{
    private static String[] AdminList = {"Beff Jezos","Gill Bates"};
    private static String[] PasswordList = {"Amazon", "Microsoft"};

    public static boolean checkAdmin(String name, String password){
        for(int i = 0; i < 2; i++){
            if(AdminList[i].equals(name) && PasswordList[i].equals(password)){
                return true;
            }
        }
        return false;
    }

    public static void DeleteCategory(Categories[] clist, int clen, int ci){
        int flag = 1;

        for(int i = 0; i < clen; i++){
            if(clist[i].categoryId == ci && !clist[i].isCategoryDeleted){
                clist[i].isCategoryDeleted = true;
                System.out.println("Category successfully deleted ");
                flag = 0;
                break;
            }
        }

        if(flag == 1){
            System.out.println("Category not found ");
        }
    }

    public static int addProduct(Categories[] clist, int clen, int ci){
        for(int i = 0; i < clen; i++){
            if(clist[i].categoryId == ci && !clist[i].isCategoryDeleted){
                return i;
            }
        }
        return -1;

    }

    public static boolean checkProductId(Categories ccc, String pid){
        for(int i = 0; i < ccc.noOfProducts; i++){
            if(Objects.equals(ccc.productList[i].getProductId(), pid) && !ccc.productList[i].isProductDeleted){
                return false;
            }
        }
        return true;
    }

    public static void deleteProduct(Categories[] clist, int clen, String cp){
        int i1 = Integer.parseInt(cp.substring(0,cp.indexOf('.')));
        for(int i = 0; i < clen; i++){
            if(clist[i].categoryId == i1 && !clist[i].isCategoryDeleted){
                for(int j = 0; j < clist[i].noOfProducts; j++){
                    if(Objects.equals(clist[i].productList[j].getProductId(), cp) && !clist[i].productList[j].isProductDeleted){
                        clist[i].productList[j].isProductDeleted = true;
                        System.out.println("Product successfully deleted ");
                        break;
                    }
                }
                break;
            }
        }

        //Deleting category
        int counter = 0;

        for(int i = 0; i < clen; i++){
            if(clist[i].categoryId == i1 && !clist[i].isCategoryDeleted){
                for(int j = 0; j < clist[i].noOfProducts; j++){
                    if(!clist[i].productList[j].isProductDeleted){
                        counter++;
                    }
                }

                if(counter == 0){
                    clist[i].isCategoryDeleted = true;
                }
                break;
            }
        }



    }

    public static Products findProduct(Categories[] clist, int clen, String cp){
        int i1 = Integer.parseInt(cp.substring(0,cp.indexOf('.')));
        for(int i = 0; i < clen; i++){
            if(clist[i].categoryId == i1 && !clist[i].isCategoryDeleted){
                for(int j = 0; j < clist[i].noOfProducts; j++){
                    if(Objects.equals(clist[i].productList[j].getProductId(), cp) && !clist[i].productList[j].isProductDeleted){
                        return clist[i].productList[j];
                    }
                }
                break;
            }
        }
        return clist[0].productList[0];
    }

    public static giveAwayDeals findDeal(giveAwayDeals[] clist, int clen, int cp){
        for(int i = 0; i < clen; i++){
            if(clist[i].getDealId() == cp){
                return clist[i];
            }
        }
        return clist[0];
    }


    public static double searchProduct(Categories[] clist, int clen, String cp){
        int i1 = Integer.parseInt(cp.substring(0,cp.indexOf('.')));
        for(int i = 0; i < clen; i++){
            if(clist[i].categoryId == i1 && !clist[i].isCategoryDeleted){
                for(int j = 0; j < clist[i].noOfProducts; j++){
                    if(Objects.equals(clist[i].productList[j].getProductId(), cp) && !clist[i].productList[j].isProductDeleted){
                        return clist[i].productList[j].getProductPrice();
                    }
                }
                break;
            }
        }
        return 0;
    }

    public static int checkCustomer(Customer[] cl, int clen, String cname, String cpass){
        for(int i = 0; i < clen; i++){
            if(Objects.equals(cl[i].getName(), cname) && Objects.equals(cl[i].getPassword(), cpass)){
                return i;
            }
        }
        return -1;
    }


}

class NormalCustomer extends Customer implements customerFunctions{

    public double getDiscount(){
        return 0;
    }
    public double DeliveryCharges(double orderPrice){
        return (double) (100 + 0.05*orderPrice);
    }
    public String DeliveryTime(){
        return " 7-10 days ";
    }
}


class PrimeCustomer extends Customer implements customerFunctions{

    PrimeCustomer(String n, int age, String email, String phoneNo, String p, double b){
        this.setAge(age);
        this.setName(n);
        this.setPassword(p);
        this.setPhoneNo(phoneNo);
        this.setEmailId(email);
        this.setBalance(b);
    }

    public double getDiscount(){
        return  5;
    }
    public double DeliveryCharges(double orderPrice){
        return (double) (100 + 0.02*orderPrice);
    }
    public String DeliveryTime(){
        return " 3-6 days ";
    }
}

class EliteCustomer extends Customer implements customerFunctions{
    EliteCustomer(String n, int age, String email, String phoneNo, String p, double b){
        this.setAge(age);
        this.setName(n);
        this.setPassword(p);
        this.setPhoneNo(phoneNo);
        this.setEmailId(email);
        this.setBalance(b);
    }
    public double getDiscount(){
        return 10;
    }
    public double DeliveryCharges(double orderPrice){
        return (double) (100);
    }
    public String DeliveryTime(){
        return " 2 days ";
    }
}

public class Flipzon{
    public static void main(String[] args) {
        boolean loop1 = true;
        Scanner sc = new Scanner(System.in);
        int noOfCategories = 0;

        int noOfCustomers = 0;
        int noOfGad = 0;

        giveAwayDeals[] Gads = new giveAwayDeals[1000];
        Customer[] CustomerList = new Customer[100];
        Categories[] CategoryList = new Categories[100];


         while(loop1){
            System.out.println("Welcome to Flipzon");
            System.out.println("Press [1] : Enter as admin");
            System.out.println("Press [2] : Explore the Product Catalog");
            System.out.println("Press [3] : Show Available Deals");
            System.out.println("Press [4] : Enter as Customer");
            System.out.println("Press [5] : Exit the Application");

            int c1 = sc.nextInt();
            if(c1 == 1){
                sc.nextLine();
                System.out.println("Enter your username : ");
                String Aname = sc.nextLine();

                System.out.println("Enter your password : ");
                String Apassword = sc.nextLine();

                if(Admin.checkAdmin(Aname, Apassword)){
                    System.out.println("Welcome " + Aname + "!!!");
                    boolean loop11 = true;
                    while(loop11){
                        System.out.println("Please choose any one of the following actions:");
                        System.out.println("Press [1] : Add category");
                        System.out.println("Press [2] : Delete category");
                        System.out.println("Press [3] : Add product");
                        System.out.println("Press [4] : Delete Product");
                        System.out.println("Press [5] : Set Discount on Product");
                        System.out.println("Press [6] : Add giveaway deal");
                        System.out.println("Press [7] : Back");
                        int c11 = sc.nextInt();
                        if (c11 == 1){
                            System.out.println("Enter Category Id : ");
                            int cid = sc.nextInt();
                            if(Categories.checkId(CategoryList,noOfCategories,cid)){
                                System.out.println("Category Id already exist");
                                System.out.println("Enter a different Id");
                            }
                            else{
                                sc.nextLine();
                                System.out.println("Enter name of the Category : ");
                                String ccname = sc.nextLine();
                                Categories categoryObject = new Categories();
                                categoryObject.categoryId = cid;
                                categoryObject.categoryName = ccname;
                                CategoryList[noOfCategories] = categoryObject;

                                System.out.println("Add a product : ");
                                System.out.println("Enter product name ");
                                String pname = sc.nextLine();
                                System.out.println("Enter product Id : ");
                                String pid = sc.nextLine();
//                                sc.nextLine();
                                System.out.println("Enter product price : ");
                                double pprice = sc.nextDouble();
                                System.out.println("Enter quantity : ");
                                int scq = sc.nextInt();

                                Products p = new Products(pid, pname, pprice);
                                p.setProductQuantity(scq);

                                System.out.println("Enter details of the product ");
                                sc.nextLine();
                                while(true){

                                    p.descOfProduct[p.noOFlinDesc] = sc.nextLine();
                                    p.noOFlinDesc++;
                                    System.out.println("Do you want to add more details Yes/No : ");
                                    String pd = sc.nextLine();
                                    System.out.println("Add details : ");
                                    if(Objects.equals(pd, "No")){
                                        break;
                                    }
                                }

                                CategoryList[noOfCategories].productList[CategoryList[noOfCategories].noOfProducts] = p;
                                CategoryList[noOfCategories].noOfProducts++;
                                noOfCategories++;


                            }


                        }
                        else if(c11 == 2){
                            System.out.println("Enter category id : ");
                            int cf = sc.nextInt();
                            Admin.DeleteCategory(CategoryList,noOfCategories,cf);

                        }
                        else if(c11 == 3){
                            System.out.println("Enter category id : ");
                            int cf = sc.nextInt();

                            int cindex = Admin.addProduct(CategoryList,noOfCategories,cf);
                            if(cindex == -1){
                                System.out.println("Entered category not found ");

                            }
                            else{

                                System.out.println("Add a product : ");
                                sc.nextLine();
                                System.out.println("Enter product name ");

                                String pname = sc.nextLine();

                                System.out.println("Enter product Id : ");
                                String pid = sc.nextLine();
//                                sc.nextLine();

                                System.out.println("Enter product price : ");
                                double pprice = sc.nextDouble();
                                System.out.println("Enter quantity : ");
                                int scq = sc.nextInt();

                                if(Admin.checkProductId(CategoryList[cindex], pid)){
                                    Products p = new Products(pid, pname, pprice);
                                    p.setProductQuantity(scq);

                                    System.out.println("Enter details of the product ");

                                    sc.nextLine();
                                    while(true){

                                        p.descOfProduct[p.noOFlinDesc] = sc.nextLine();
                                        p.noOFlinDesc++;
                                        System.out.println("Do you want to add more details Yes/No : ");
                                        String pd = sc.nextLine();
                                        System.out.println("Add details : ");
                                        if(Objects.equals(pd, "No")){
                                            break;
                                        }
                                    }

                                    CategoryList[cindex].productList[CategoryList[cindex].noOfProducts] = p;
                                    CategoryList[cindex].noOfProducts++;

                                    System.out.println("Product successfully added ");
                                }
                                else{
                                    System.out.println("Product Id already exists ");
                                    System.out.println("Enter another Id");
                                }
                            }


                        }
                        else if(c11 == 4){
                            sc.nextLine();
                            System.out.println("Enter product Id : ");

                            String pid = sc.nextLine();
                            Admin.deleteProduct(CategoryList, noOfCategories, pid);


                        }
                        else if(c11 == 5){
                            sc.nextLine();
                            System.out.println("Enter product Id : ");
                            String pid = sc.nextLine();
                            Products p = Admin.findProduct(CategoryList, noOfCategories, pid);
//                            sc.nextLine();

                            System.out.println("Enter discount for Normal customer");
                            p.discountArray[0] = sc.nextInt();
                            System.out.println("Enter discount for Prime customer");
                            p.discountArray[1] = sc.nextInt();
                            System.out.println("Enter discount for Elite customer");
                            p.discountArray[2] = sc.nextInt();

                        }
                        else if(c11 == 6){
                            sc.nextLine();
                            System.out.println("Enter product Id1 : ");
                            String pid1 = sc.nextLine();
                            System.out.println("Enter product Id2 : ");
                            String pid2 = sc.nextLine();

                            double[] gprice = new double[3];
                            System.out.println("Enter the deal price for normal user : ");
                            gprice[0] = sc.nextDouble();

                            Products p1 = Admin.findProduct(CategoryList, noOfCategories, pid1);
                            Products p2 = Admin.findProduct(CategoryList, noOfCategories, pid2);


                            int flag = 0;





                            if(p1.getProductPrice()*(100 - p1.discountArray[0])*0.01 + p2.getProductPrice()*(100 - p2.discountArray[0])*0.01 > gprice[0]){

                            }
                            else{
                                System.out.println(p1.getProductPrice()*(100 - p1.discountArray[0])*0.01 + p2.getProductPrice()*(100 - p2.discountArray[0])*0.01);
                                flag = 1;
                                System.out.println("Deal price should be strictly less than the combined price ");
                            }



                            if(flag != 1){
                                System.out.println("Enter the deal price for prime user : ");
                                gprice[1] = sc.nextDouble();
                            if(p1.getProductPrice()*(100 - p1.discountArray[1])*0.01 + p2.getProductPrice()*(100 - p2.discountArray[1])*0.01 > gprice[1]){

                            }
                            else{
                                System.out.println(p1.getProductPrice()*(100 - p1.discountArray[1])*0.01 + p2.getProductPrice()*(100 - p2.discountArray[1])*0.01 );
                                flag = 1;
                                System.out.println("Deal price should be strictly less than the combined price ");
                            }}


                            if(flag != 1){

                                System.out.println("Enter the deal price for elite user : ");
                                gprice[2] = sc.nextDouble();
                            if(p1.getProductPrice()*(100 - p1.discountArray[2])*0.01 + p2.getProductPrice()*(100 - p2.discountArray[2])*0.01 > gprice[2]){
                                System.out.println("Enter dealId : ");
                                int did = sc.nextInt();
                                Gads[noOfGad] = new giveAwayDeals(pid1, pid2, did);
                                Gads[noOfGad].DealPrice = gprice;
                                noOfGad++;

                            }
                            else{
                                System.out.println(p1.getProductPrice()*(100 - p1.discountArray[2])*0.01 + p2.getProductPrice()*(100 - p2.discountArray[2])*0.01);
                                System.out.println("Deal price should be strictly less than the combined price ");
                            }

                            }




                        }
                        else{
                            loop11 = false;
                        }

                    }

                }
                else{
                    System.out.println("Invalid Admin name and password");
                    System.out.println("Enter again");
                }

            }
            else if(c1 == 2){
                System.out.println("We have the following Categories and Products");
                for(int i = 0; i < noOfCategories; i++){
                    if(!CategoryList[i].isCategoryDeleted){
                        System.out.println("Category name is "+ CategoryList[i].categoryName);
                        System.out.println("Category ID is " + Integer.toString(CategoryList[i].categoryId));
                        System.out.println("Products under this category are ");
                        for(int j = 0; j < CategoryList[i].noOfProducts; j++){

                            if(!CategoryList[i].productList[j].isProductDeleted){
                                System.out.println("Product name is " +  CategoryList[i].productList[j].getProductName());
                                System.out.println("Product Id is " + CategoryList[i].productList[j].getProductId());
                                System.out.println("Product price is " + Double.toString(CategoryList[i].productList[j].getProductPrice()));
                                System.out.println("Product quantity is " + Integer.toString(CategoryList[i].productList[j].getProductQuantity()));

                                for(int k = 0; k < CategoryList[i].productList[j].noOFlinDesc; k++){
                                    System.out.println(CategoryList[i].productList[j].descOfProduct[k]);
                                }
                                System.out.println("-----");
                            }
                        }


                    }
                }
            }
            else if(c1 == 3){
                System.out.println("We have the following deals : ");
                for(int i = 0; i < noOfGad; i++){

                    System.out.println("DealId is " +   Integer.toString(Gads[i].getDealId()));
                    System.out.println("Id of product1 is " + Gads[i].getProductId1());
                    System.out.println("Id of product2 is " + Gads[i].getProductId2());
                    System.out.println("Deal price for normal user is  " + Gads[i].DealPrice[0]);
                    System.out.println("Deal price for prime user is  " + Gads[i].DealPrice[1]);
                    System.out.println("Deal price for elite user is  " + Gads[i].DealPrice[2]);

                    System.out.println("------");
                }

            }
            else if(c1 == 4){
                boolean loop14 = true;
                while(loop14){
                    System.out.println("Press [1] : Sign up");
                    System.out.println("Press [2] : Log in");
                    System.out.println("Press [3] : Back");
                    int c14 = sc.nextInt();
                    if(c14 == 1){
                        sc.nextLine();
                        System.out.println("Enter your name : ");
                        String cname = sc.nextLine();
                        System.out.println("Enter your email-id :  ");
                        String cemail = sc.nextLine();
                        System.out.println("Enter your phone number : ");
                        String cphone = sc.nextLine();
                        System.out.println("Enter your age : ");
                        int cage = sc.nextInt();

                        sc.nextLine();
                        System.out.println("Enter your password : ");
                        String cpassword = sc.nextLine();

                        CustomerList[noOfCustomers] = new NormalCustomer();

                        CustomerList[noOfCustomers].setAge(cage);
                        CustomerList[noOfCustomers].setName(cname);
                        CustomerList[noOfCustomers].setEmailId(cemail);
                        CustomerList[noOfCustomers].setPhoneNo(cphone);
                        CustomerList[noOfCustomers].setPassword(cpassword);
                        CustomerList[noOfCustomers].isRegistered = true;
                        noOfCustomers++;

                        System.out.println("Customer successfully registered");
                    }
                    else if(c14 == 2){
                        sc.nextLine();
                        System.out.println("Enter your name : ");
                        String cname = sc.nextLine();
                        System.out.println("Enter your password : ");
                        String cpassword = sc.nextLine();
                        int CustomerIndex = Admin.checkCustomer(CustomerList, noOfCustomers, cname, cpassword);
                        if(CustomerIndex != -1){
                            System.out.println("Welcome " + cname + "!!");
                            boolean loop141 = true;
                            while(loop141){
                                System.out.println("Press [1] : Browse products ");
                                System.out.println("Press [2] : Browse deals ");
                                System.out.println("Press [3] : Add a product to cart ");
                                System.out.println("Press [4] : Add products in deal to cart ");
                                System.out.println("Press [5] : View Coupons");
                                System.out.println("Press [6] : Check account balance");
                                System.out.println("Press [7] : View cart ");
                                System.out.println("Press [8] : Empty cart ");
                                System.out.println("Press [9] : Checkout cart ");
                                System.out.println("Press [10] : Upgrade customer status ");
                                System.out.println("Press [11] : Add amount to wallet ");
                                System.out.println("Press [12] : Back ");
                                int c141 = sc.nextInt();
                                if (c141 == 1){
                                    System.out.println("We have the following Categories and Products");
                                    for(int i = 0; i < noOfCategories; i++){
                                        if(!CategoryList[i].isCategoryDeleted){
                                            System.out.println("Category name is "+ CategoryList[i].categoryName);
                                            System.out.println("Category ID is " + Integer.toString(CategoryList[i].categoryId));
                                            System.out.println("Products under this category are ");
                                            for(int j = 0; j < CategoryList[i].noOfProducts; j++){

                                                if(!CategoryList[i].productList[j].isProductDeleted){
                                                    System.out.println("Product name is " +  CategoryList[i].productList[j].getProductName());
                                                    System.out.println("Product Id is " + CategoryList[i].productList[j].getProductId());
                                                    System.out.println("Product price is " + Double.toString(CategoryList[i].productList[j].getProductPrice()));
                                                    System.out.println("Product quantity is " + Integer.toString(CategoryList[i].productList[j].getProductQuantity()));

                                                    for(int k = 0; k < CategoryList[i].productList[j].noOFlinDesc; k++){
                                                        System.out.println(CategoryList[i].productList[j].descOfProduct[k]);
                                                    }
                                                    System.out.println("-----");
                                                }
                                            }


                                        }
                                    }

                                }
                                else if(c141 == 2){
                                    System.out.println("We have the following deals : ");
                                    for(int i = 0; i < noOfGad; i++){

                                        System.out.println("DealId is " +   Integer.toString(Gads[i].getDealId()));
                                        System.out.println("Id of product1 is " + Gads[i].getProductId1());
                                        System.out.println("Id of product2 is " + Gads[i].getProductId2());
                                        System.out.println("Deal price for normal user is  " + Gads[i].DealPrice[0]);
                                        System.out.println("Deal price for prime user is  " + Gads[i].DealPrice[1]);
                                        System.out.println("Deal price for elite user is  " + Gads[i].DealPrice[2]);

                                        System.out.println("------");
                                    }


                                }
                                else if(c141 == 3){
                                    sc.nextLine();
                                    System.out.println("Enter product Id : ");
                                    String pid = sc.nextLine();

                                    System.out.println("Enter quantity : ");
                                    int pquantity = sc.nextInt();
                                    Products p1 = Admin.findProduct(CategoryList, noOfCategories, pid);
                                    if(pquantity > p1.getProductQuantity() ){
                                        System.out.println("Item not present in the required quantity");
                                    }
                                    else{
                                        CustomerList[CustomerIndex].cart[CustomerList[CustomerIndex].noOfProducts] = p1;
                                        CustomerList[CustomerIndex].Quantity[CustomerList[CustomerIndex].noOfProducts] = pquantity;
                                        CustomerList[CustomerIndex].noOfProducts++;
                                        System.out.println("Item successfully added to the cart ");
                                    }



                                }
                                else if(c141 == 4){

                                    System.out.println("Enter Deal Id : ");
                                    int did = sc.nextInt();
                                    System.out.println("Enter quantity : ");
                                    int pquantity = sc.nextInt();


                                    giveAwayDeals d1  = Admin.findDeal(Gads,noOfGad, did);

                                    Products p1 =  Admin.findProduct(CategoryList, noOfCategories, d1.getProductId1());
                                    Products p2 = Admin.findProduct(CategoryList, noOfCategories, d1.getProductId2());

                                    if(p1.getProductQuantity() > pquantity && p2.getProductQuantity() > pquantity){
                                        CustomerList[CustomerIndex].cart[CustomerList[CustomerIndex].noOfProducts] = d1;
                                        CustomerList[CustomerIndex].Quantity[CustomerList[CustomerIndex].noOfProducts] = pquantity;
                                        CustomerList[CustomerIndex].noOfProducts++;
                                    }
                                    else{
                                        System.out.println("Deal out of stock");
                                    }




                                }
                                else if(c141 == 5){

                                    System.out.println("Available coupons are");
                                    for(int i32 = 0; i32 < CustomerList[CustomerIndex].noOfCoupons; i32++){
                                        if(CustomerList[CustomerIndex].coupons[i32] != 0){
                                            System.out.println(CustomerList[CustomerIndex].coupons[i32]);
                                        }
                                    }


                                }
                                else if(c141 == 6){
                                    System.out.println("Current account balance is : ");
                                    System.out.println(CustomerList[CustomerIndex].getBalance());

                                }
                                else if(c141 == 7){

                                    System.out.println("Your cart contains ");
                                    CustomerList[CustomerIndex].viewCart();


                                }
                                else if(c141 == 8){

                                    CustomerList[CustomerIndex].cart = new Products[1000];
                                    CustomerList[CustomerIndex].Quantity = new int[1000];
                                    CustomerList[CustomerIndex].noOfProducts = 0;
                                    System.out.println("Cart successfully emptied ");

                                }
                                else if(c141 == 9){
                                    System.out.println("Proceeding to checkout. Details:");
                                    CustomerList[CustomerIndex].viewCart();
                                    CustomerList[CustomerIndex].ultimateSumCart(CategoryList, noOfCategories);

                                }
                                else if(c141 == 10){
                                    System.out.println("Current status : ");
                                    if(CustomerList[CustomerIndex].getClass() == NormalCustomer.class){
                                        System.out.println("Normal");
                                    }
                                    else if(CustomerList[CustomerIndex].getClass() == PrimeCustomer.class){
                                        System.out.println("Prime");
                                    }
                                    else{
                                        System.out.println("Elite");
                                    }

                                    sc.nextLine();
                                    System.out.println("Choose a new status : ");
                                    String cstatus = sc.nextLine();

                                    System.out.println("Number of months : ");
                                    int cistatus = sc.nextInt();

                                    if(Objects.equals(cstatus, "Elite") && CustomerList[CustomerIndex].getClass() == PrimeCustomer.class){
                                        if(CustomerList[CustomerIndex].balanceDeducter(cistatus*100)){
                                            CustomerList[CustomerIndex].setBalance(CustomerList[CustomerIndex].getBalance() - cistatus*100 );
                                            System.out.println("Status updated to Elite ");
                                            EliteCustomer ecopy = new EliteCustomer(CustomerList[CustomerIndex].getName(), CustomerList[CustomerIndex].getAge(), CustomerList[CustomerIndex].getEmailId(), CustomerList[CustomerIndex].getPhoneNo(), CustomerList[CustomerIndex].getPassword(), CustomerList[CustomerIndex].getBalance());
                                            ecopy.coupons = CustomerList[CustomerIndex].coupons;
                                            ecopy.cart = CustomerList[CustomerIndex].cart;
                                            ecopy.Quantity = CustomerList[CustomerIndex].Quantity;
                                            ecopy.noOfProducts = CustomerList[CustomerIndex].noOfProducts;
                                            ecopy.noOfCoupons = CustomerList[CustomerIndex].noOfCoupons;
                                            CustomerList[CustomerIndex] = ecopy;
                                            CustomerList[CustomerIndex].isRegistered = true;
                                        }
                                        else{
                                            System.out.println("Insufficient Balance");
                                        }
                                    }

                                    if(Objects.equals(cstatus, "Elite") && CustomerList[CustomerIndex].getClass() != EliteCustomer.class){
                                        if(CustomerList[CustomerIndex].balanceDeducter(cistatus*300)){
                                            CustomerList[CustomerIndex].setBalance(CustomerList[CustomerIndex].getBalance() - cistatus*300 );
                                            System.out.println("Status updated to Elite ");
                                            EliteCustomer ecopy = new EliteCustomer(CustomerList[CustomerIndex].getName(), CustomerList[CustomerIndex].getAge(), CustomerList[CustomerIndex].getEmailId(), CustomerList[CustomerIndex].getPhoneNo(), CustomerList[CustomerIndex].getPassword(), CustomerList[CustomerIndex].getBalance());
                                            ecopy.coupons = CustomerList[CustomerIndex].coupons;
                                            ecopy.cart = CustomerList[CustomerIndex].cart;
                                            ecopy.Quantity = CustomerList[CustomerIndex].Quantity;
                                            ecopy.noOfProducts = CustomerList[CustomerIndex].noOfProducts;
                                            ecopy.noOfCoupons = CustomerList[CustomerIndex].noOfCoupons;
                                            CustomerList[CustomerIndex] = ecopy;
                                            CustomerList[CustomerIndex].isRegistered = true;
                                        }
                                        else{
                                            System.out.println("Insufficient Balance");
                                        }
                                    }



                                    if(Objects.equals(cstatus, "Prime") && CustomerList[CustomerIndex].getClass() != PrimeCustomer.class){
                                        if(CustomerList[CustomerIndex].balanceDeducter(cistatus*200)){
                                            CustomerList[CustomerIndex].setBalance(CustomerList[CustomerIndex].getBalance() - cistatus*200 );
                                            System.out.println("Status updated to Prime ");

                                            PrimeCustomer ecopy = new PrimeCustomer(CustomerList[CustomerIndex].getName(), CustomerList[CustomerIndex].getAge(), CustomerList[CustomerIndex].getEmailId(), CustomerList[CustomerIndex].getPhoneNo(), CustomerList[CustomerIndex].getPassword(), CustomerList[CustomerIndex].getBalance());

                                            ecopy.coupons = CustomerList[CustomerIndex].coupons;
                                            ecopy.cart = CustomerList[CustomerIndex].cart;
                                            ecopy.Quantity = CustomerList[CustomerIndex].Quantity;
                                            ecopy.noOfProducts = CustomerList[CustomerIndex].noOfProducts;
                                            ecopy.noOfCoupons = CustomerList[CustomerIndex].noOfCoupons;

                                            CustomerList[CustomerIndex] = ecopy;
                                            CustomerList[CustomerIndex].isRegistered = true;
                                        }
                                        else{
                                            System.out.println("Insufficient Balance");
                                        }
                                    }

                                }
                                else if(c141 == 11){

                                    System.out.println("Enter amount to add : ");
                                    float cadd = sc.nextFloat();
                                    CustomerList[CustomerIndex].setBalance(cadd + CustomerList[CustomerIndex].getBalance());
                                    System.out.println("Amount successfully added");

                                }
                                else{
                                    loop141 = false;
                                }

                            }

                        }
                        else{
                            System.out.println("Entered name and password didn't match");
                            System.out.println("Please try again ");
                        }

                    }
                    else{
                        loop14 = false;
                    }
                }

            }
            else{
                loop1 = false;
            }
        }

    }
}
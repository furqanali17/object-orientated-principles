import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

//Wardrobe Implemented class that implements from Wardrobe
public final class WardrobeImp implements Wardrobe {

    //fields
    private final ArrayList<Customer> customersList;
    private final ArrayList<Item> itemsList;
    private final int maxPurchasedItems;

    private static int nextID = 3;


    //constructor

    public WardrobeImp(int _maxPurchasedItems){
        //initializing fields in constructor
        this.maxPurchasedItems = _maxPurchasedItems;

        //storing both lists in ArrayList
        this.customersList = new ArrayList<>();
        this.itemsList = new ArrayList<>();
    }

    //method used to add customer to our store

    public int addCustomer(String _name){
        //initializing output variable
        int res = WardrobeImp.nextID;

        //creating a new customer object
        Customer newCustomer = new Customer(res, _name, this.maxPurchasedItems);

        //adding new customer into customer list
        this.customersList.add(newCustomer);

        //incrementing the static variable of the class
        WardrobeImp.nextID++;

        //returning output variable
        return res;
    }

    //method used to remove customer from our store

    public int removeCustomer(int _customerID){
        //initializing output variable
        int res = -1;

        //we use our checkCustomer method to find customerID in customers list
        int index = Customer.checkCustomer(_customerID, this.customersList);

        //setting index to -1 since it is unreachable
        //if index is not -1
        if (index != -1){
            //we check for any purchases made by customer
            Customer customer = this.customersList.get(index);
            int size = customer.getActivePurchases().size();

            //if no purchases are found the user is removed
            if (size == 0){
                res = 0;
                this.customersList.remove(index);
            }

            //if purchases are found the user is not removed
            else{
                res = -2;
            }
        }
        //returning output variable
        return res;
    }

    //method used to display a specific customers' information from our store

    public void displayCustomerInfo(int _customerID) {
        System.out.println("******************\n * Customer IDs *\n******************");

        //we use our checkCustomer method to find customerID in customers list
        int index = Customer.checkCustomer(_customerID, this.customersList);

        //setting index to -1 since it is unreachable
        //if index is not -1
        if (index != -1) {
            //we find the customer
            Customer customer = this.customersList.get(index);

            //we get customer's info and store it in a string
            String info = customer.toString();

            //we print the info using our toString method
            System.out.println(info);
        }
        //if customer is not found we print error message
        else
            System.out.println("No customer found with id = " + _customerID + " choose from the customer id's shown above.");
    }

    //method used to display all customers' information from our store

    public void displayAllCustomerIDs(){
        System.out.println("******************\n * All Customers IDs*\n******************");
        //loop that runs through customersList and gets all customerIDs
        for (Customer temp : this.customersList) {
            System.out.println(temp.getId());
        }
    }

    //method used to display all item's information from our store

    public void displayAllItemIDs(){
        System.out.println("******************\n * All Items IDs*\n******************");
        //loop that runs through itemsList and gets all ItemsIDs
        for (Item temp : this.itemsList) {
            System.out.println(temp.getId());
        }
    }

    //method used to add a clothes item to our store

    public int addClothes(String _color, String _quality, int _size, int _quantity){

        //initializing output variable
        int res = WardrobeImp.nextID;

        //creating a new clothes object
        Clothes newClothes = new Clothes(res, _color, _quality, _size, _quantity);

        //adding this object to the itemList
        this.itemsList.add(newClothes);

        //incrementing the static variable of the class
        WardrobeImp.nextID++;

        //returning output variable
        return res;
    }

    //method used to remove a clothes item from our store

    public boolean removeClothes(int _itemID){

        //initializing output boolean and setting it to false
        boolean res = false;

        //we use our checkItem method to find itemID in items list
        int index = Item.checkItem(_itemID, this.itemsList);

        //checking with index if item exists
        if (index >= 0){
            //output boolean returns true and the specific itemID is removed
            res = true;
            this.itemsList.remove(index);
        }

        //returning output variable
        return res;
    }

    //method used to display a specific item's information from our store

    public void displayClothesInfo(int _itemID){

        //we use our checkItem method to find itemID in items list
        int index = Item.checkItem(_itemID, this.itemsList);

        //setting index to -1 since it is unreachable
        //if index is not -1
        if (index != -1){
            //we find the itemID
            Item item = this.itemsList.get(index);

            //we get item's info and store it in a string
            String info = item.toString();

            //we print the info using our toString method
            System.out.println(info);
        }

        //if user is not found we print error message
        else
            System.out.println("No item is registered with id = " + _itemID + " so the remove operation cannot proceed.");
    }

    //method used to ensure items purchase

    private int isBought(Customer customer, Item item) {
        //initializing output variable
        int res = WardrobeImp.nextID;

        //incrementing the static variable of the class
        WardrobeImp.nextID++;

        //we create a new buy object
        Bought buy = new Bought(res, customer.getId(), item.getId());

        //we update users info
        customer.getActivePurchases().add(buy);

        //we set item to be purchased
        item.setCurrentlyBought(buy);

        //returning output variable
        return res;
    }

    //method used to buy clothes item from our store

    public int buyItem(int _customerID, int _itemID){

        //initializing output variable
        int res = -1;

        //we get indexes of customers in customers list
        int customerIndex = Customer.checkCustomer(_customerID, this.customersList);
        if (customerIndex == -1)
            System.out.println("There is no customer with " + _customerID + " in the CustomersList");

        //we get indexes of items in items list
        int itemIndex = Item.checkItem(_itemID, this.itemsList);
        if (itemIndex == -1)
            System.out.println("There is no item with " + _itemID + " in the ItemsList");

        //when both customer and item are found we check if the item is available to be purchased
        if ((customerIndex >= 0) && (itemIndex >= 0)){

        Customer customer = this.customersList.get(customerIndex);
        Item item = this.itemsList.get(itemIndex);

        //we get the active purchases each customer has
        int numPurchases = customer.getActivePurchases().size();
        int maxPurchases = customer.getMaxPurchases();

        //we ensure the item has not been bought already
        Bought itemBoughtObject = item.getCurrentlyBought();

        //if item has not been bought we buy the item
            if (numPurchases < maxPurchases)
               if (itemBoughtObject == null)
                   //bought item is stored in isBought
                   res = isBought(customer, item);

               else
                   System.out.println("The item with id " + _itemID + " has already been bought.");
            else
                System.out.println("The customer with id " + _customerID + " already has the maximum available purchases");
        }
        //returning output variable
        return res;
    }

    //method used to return clothes item from our store

    public boolean returnItem(int _customerID, int _itemID){
        //initializing output boolean and setting it to false
        boolean res = false;

        //we get indexes of customers in customers list
        int customerIndex = Customer.checkCustomer(_customerID, this.customersList);
        if (customerIndex == -1)
            System.out.println("There is no customer with " + _customerID + " in the CustomersList");

        //we get indexes of items in items list
        int itemIndex = Item.checkItem(_itemID, this.itemsList);
        if (itemIndex == -1)
            System.out.println("There is no item with " + _itemID + " in the ItemsList");

        //when both customer and item are found we check if the customer has purchased the item
        if ((customerIndex >= 0) && (itemIndex >= 0)){

            Customer customer = this.customersList.get(customerIndex);

            //we check for item in bought list
            ArrayList<Bought> boughtList = customer.getActivePurchases();
            int index = Bought.checkItemBought(_itemID, boughtList);

            //if purchase is found we return the item
            if (index != -1) {

                res = true;

                //we remove purchase from user
                boughtList.remove(index);
            }
            else
                System.out.println("The customer with id " + _customerID + " did not purchase the item with id " + _itemID);
        }

        //returning output variable
        return res;
    }

   //method used to load data from text file

    public void loadData(String customerFileName, String itemsFileName){

        if ((this.loadCustomerData(customerFileName))) {
            this.loadItemData(itemsFileName);
        }

    }

    //method used to load customer data from text file

    private boolean loadCustomerData(String customersFileName){
        //initializing output variable
        boolean res = false;

        try {
            //creating the file object
            File file = new File(customersFileName);
            Scanner sc = new Scanner(file);

            //checking if file is empty or not
            if (sc.hasNext()){
                //we get the number of customers
                int customerSize = sc.nextInt();
                //we loop through each of the customer objects
                for (int customerIndex = 0; customerIndex < customerSize; customerIndex++){
                    //we read their values
                    int _id = sc.nextInt();
                    String _name = sc.next();
                    int _maxPurchases = sc.nextInt();

                    //we create a new customer object
                    Customer customer = new Customer(_id, _name, _maxPurchases);

                    //we read the number of current purchases
                    int numPurchases = sc.nextInt();

                    //we update the id
                    if (_id >= WardrobeImp.nextID)
                        WardrobeImp.nextID = _id + 1;

                    //we loop through each of the purchases made
                    for (int purchaseIndex = 0; purchaseIndex < numPurchases; purchaseIndex++){
                        //we read their values
                        int _rentID = sc.nextInt();
                        int _customerID = sc.nextInt();
                        int _itemID = sc.nextInt();

                        //we create a new buy object
                        Bought buy = new Bought(_rentID, _customerID, _itemID);

                        //we add the buy object to the list
                        customer.getActivePurchases().add(buy);

                        //we update the id
                        if (_rentID >= WardrobeImp.nextID)
                            WardrobeImp.nextID = _rentID + 1;
                    }

                    //we add the buy object to the list
                    this.customersList.add(customer);
                }
            }

            //we close the reader
            sc.close();

            //setting our output variable to true
            res = true;
        }
        //if file is not found
        catch (Exception e) {
            System.out.println("NO FILE FOUND");
        }

        //returning output variable
        return res;
    }

    //method used to load items data from text file

    private void loadItemData(String itemsFileName){
        try {
            //creating the file object
            File file = new File(itemsFileName);
            Scanner sc = new Scanner(file);

            //checking if file is empty or not
            if (sc.hasNext()){
                //we get the number of items
                int itemsSize = sc.nextInt();

                //we loop through each of the items objects
                for (int itemIndex = 0; itemIndex < itemsSize; itemIndex++){

                    //we read their values
                    int _id = sc.nextInt();
                    String _color = sc.next();
                    String _quality = sc.next();
                    int _size = sc.nextInt();
                    int _quantity;

                    //we initialize item object
                    Item item;
                    if (sc.hasNextInt()){
                        //we read quantity provided
                        _quantity = sc.nextInt();

                        //we create a new item object
                        item = new Clothes(_id, _color, _quality, _size, _quantity);


                        //we update the id
                        if (_id >= WardrobeImp.nextID)
                            WardrobeImp.nextID = _id + 1;

                        //we read for purchases
                        if (sc.hasNextInt()){
                            //we read their values
                            sc.nextInt();
                            int _customerID = sc.nextInt();
                            int _itemID = sc.nextInt();
                            sc.nextInt();

                            //we find index of customers
                            int customerIndex = Customer.checkCustomer(_customerID, this.customersList);
                            Customer customer = this.customersList.get(customerIndex);

                            //we find index of purchases made
                            ArrayList<Bought> boughtList = customer.getActivePurchases();
                            int BoughtIndex = Bought.checkItemBought(_itemID, boughtList);

                            //we get the buy object
                            Bought buy = boughtList.get(BoughtIndex);

                            //we set the purchase to the item
                            item.setCurrentlyBought(buy);
                        }
                        else{
                            //we go to next after purchase was made
                            sc.next();
                        }

                        //we add item to items list
                        this.itemsList.add(item);
                    }
                }
            }
            //we close the reader
            sc.close();
        }
        //if file is not found
        catch (Exception e) {
            System.out.println("NO FILE FOUND");
        }
    }

    //method used to save data to text file

    public void saveData(String customerFileName, String itemsFileName) {

        //creating the print writer object
        PrintWriter customersWriter;
        PrintWriter itemsWriter;

        try {
            //we open files for writing
            customersWriter = new PrintWriter(customerFileName, StandardCharsets.UTF_8);
            itemsWriter = new PrintWriter(itemsFileName, StandardCharsets.UTF_8);

            //we print customers info
            int size = this.customersList.size();
            customersWriter.println(size);
            for (int index = 0; index < size; index++){
                Customer customer = this.customersList.get(index);
                String content = customer.toString();
                customersWriter.print(content);
            }

            //we print items info
            size = this.itemsList.size();
            itemsWriter.println(size);
            for (int index = 0; index < size; index++){
                Item item = this.itemsList.get(index);
                String content = item.toString();
                itemsWriter.print(content);
            }

            //we close the files
            customersWriter.close();
            itemsWriter.close();
        }
        //if file is not found
        catch (Exception e) {
            System.out.println("NO FILE FOUND");
        }
    }
}
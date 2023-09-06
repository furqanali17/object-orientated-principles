import java.util.ArrayList;

//Customer
public class Customer extends Parent {

    //fields

    private final String name;
    private final ArrayList<Bought> activeBuys;
    private final int maxPurchases;

    //constructor

    public Customer(int _id, String _name, int _maxPurchases){

        //calling super to the Parent constructor.
        super(_id);

        //initializing fields in constructor
        this.name = _name;
        this.maxPurchases = _maxPurchases;

        //storing the active buys of the new customer in array
        this.activeBuys = new ArrayList<>();
    }

    //get methods

    public ArrayList<Bought> getActivePurchases(){
        return this.activeBuys;
    }

    //gets the maximum amount a customer can buy an item
    public int getMaxPurchases(){
        return this.maxPurchases;
    }

    //additional methods

    //method used to check if custom exists in the customerList array

    public static int checkCustomer(int id, ArrayList<Customer> list){

        //initializing output variable
        int res = -1;

        //creating a parent and passing through id
        Parent baseline = new Parent(id);

        //we search for parent through the list
        int size = list.size();
        int index = 0;

        //when customer is found it is assigned to our output variable
        while ((res == -1) && (index < size)){
            if (baseline.equals(list.get(index)))
                res = index;
            else
                index++;
        }
        //returning output variable
        return res;
    }

    //to String method

    public String toString(){
        //initializing output variable
        StringBuilder res;

        //format of the toString method
        res = new StringBuilder("" + this.getId() + "\t" + this.name + "\t"+ this.maxPurchases + "\n");

        int size = this.activeBuys.size();
        res.append(size).append("\n");
        for (Bought activeBought : this.activeBuys) {
            res.append(activeBought.toString());
        }

        //returning output variable
        return res.toString();
    }
}

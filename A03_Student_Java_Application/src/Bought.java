import java.util.ArrayList;

//Class that ensures an Item has been bought by Customer
public class Bought {

    //fields

    private final int BuyID;
    private final int customerID;
    private final int itemID;

    //constructor

    public Bought(int _buyID, int _customerID, int _itemID){

        //initializing fields in constructor
        this.BuyID = _buyID;
        this.customerID = _customerID;
        this.itemID = _itemID;
    }

    //get methods

    public int getItemID(){
        return this.itemID;
    }

    //additional methods

    //method to check if an Item has been bought
    public static int checkItemBought(int id, ArrayList<Bought> list){
        //initializing output variable
        int res = -1;

        //going through the list to check
        int size = list.size();
        int index = 0;

        //when item is found it is assigned to our output variable
        while ((res == -1) && (index < size)){
            if (list.get(index).getItemID() == id)
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
        String res;

        //format of the toString method
        res = "" + this.BuyID + "\t" + this.customerID + "\t" + this.itemID + "\t" + "\n";

        //returning output variable
        return res;
    }
}

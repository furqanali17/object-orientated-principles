//Class that models clothes which is a subclass of Item
public class Clothes extends Item {

    //fields
    //quantity of clothes being purchased
    private final int quantity;

    //constructor

    public Clothes(int _id, String _color, String _quality, int _size, int _quantity) {
        super(_id, _color, _quality, _size);
        this.quantity = _quantity;
    }

    //get methods

    public int getQuantity(){
        return this.quantity;
    }

    //additional methods

    public int returnPeriod(){
        return this.quantity;
    }

    //to String method

    public String toString(){
        //initializing output variable
        String res;

        //format of the toString method
        res = "" +this.getId() + "\t" + this.getColor() + "\t" + this.getQuality() + "\t" + this.getSize() + "\t";

        //printing the field in clothes
        res = res + this.getQuantity() + "\n";

        if (this.getCurrentlyBought() == null)
            res = res + "Has not been bought";
        else
            res = res + "Purchase Receipt ID: " + this.getCurrentlyBought().toString();

        //returning output variable
        return res;
    }
}

import java.util.ArrayList;

//Abstract class of all items
public abstract class Item extends Parent {

    //fields

    private final String color;
    private final String quality;
    private final int size;
    private Bought currentlyBought;

    //constructor


    public Item(int _id, String _color, String _quality, int _size){

        //calling super to the Parent constructor.
        super(_id);

        //initializing fields in constructor
        this.color = _color;
        this.quality = _quality;
        this.size = _size;

        //setting currently bought to null
        this.currentlyBought = null;

    }

    //get methods

    public String getColor(){
        return this.color;
    }

    public String getQuality(){
        return this.quality;
    }

    public int getSize(){
        return this.size;
    }

    public Bought getCurrentlyBought(){ return this.currentlyBought; }

    //set methods

    public void setCurrentlyBought(Bought _newBought){ this.currentlyBought = _newBought; }

    //additional methods

    //time in which return can be made
    public abstract int returnPeriod();

    //method to find if item exists in Items Array
    public static int checkItem(int id, ArrayList<Item> list){
        //initializing output variable
        int res = -1;

        //creating a new parent and searching for it in the list
        Parent parent = new Parent(id);

        int size = list.size();
        int index = 0;

        //when ItemID is found it is stored in our output variable
        while ((res == -1) && (index < size)){
            if (parent.equals(list.get(index)))
                res = index;
            else
                index++;
        }
        //returning output variable
        return res;
    }
}
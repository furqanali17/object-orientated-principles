//Parent class
public class Parent {

    //fields

    private final int id;

    private static int nextID = 3;

    //constructor

    public Parent(int _id) {
        this.id = _id;
    }

    public static int getNextID() {
        return nextID;
    }

    public static void setNextID(int nextID) {
        Parent.nextID = nextID;
    }

    //get methods
    //final since we don't want it changed by any subclasses
    public final int getId(){
        return this.id;
    }

    //equals method used to compare the parent id to each CustomerID and ItemID respectively
    public boolean equals(Parent o) {
        return this.id == o.id;
    }
}

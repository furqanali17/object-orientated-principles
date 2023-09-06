//Interface class
public interface Wardrobe {

    //initializing all methods needed to be implemented

    int addCustomer(String _name);

    int removeCustomer(int _customerID);

    void displayCustomerInfo(int _customerID);

    void displayAllCustomerIDs();

    void displayAllItemIDs();

    int addClothes(String _color, String _quality, int _size, int _quantity);

    boolean removeClothes(int _itemID);

    void displayClothesInfo(int _itemID);

    int buyItem(int _customerID, int _itemID);

    boolean returnItem(int _customerID, int _itemID);

    void loadData(String customerFileName, String itemsFileName);

    void saveData(String customerFileName, String itemsFileName);
}

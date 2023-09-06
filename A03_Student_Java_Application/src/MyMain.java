import java.util.Scanner;

//main class which accesses all the other methods
public class MyMain {

	//our menu
	public static void printMenu(){
		System.out.print("*****************************************\n");
		System.out.print("  *    WELCOME TO OUR CLOTHES STORE   *  \n");
		System.out.print("*****************************************\n");
		System.out.print("Enter 0.  Exit\n");
		System.out.print("Enter 1.  Manage Customers\n");
		System.out.print("Enter 2.  Manage Clothes Item\n");
		System.out.print("Enter 3.  Buy Item\n");
		System.out.print("Enter 4.  Return Item Bought \n");
		System.out.println("\n");
	}

	//method to validate users input
	public static int validateInt(Scanner sc, int lb, int ub){

		//initializing output variable
		int res = -1;

		//initializing variable to use in the loop
		boolean validate = false;

		//we keep loop going until valid option is selected
		while (!validate) {
			System.out.println("Value must be in the range " + lb + " and " + ub );
			try {
				res = sc.nextInt();
				sc.nextLine();
				if ((res >= lb) && (res <= ub))
					validate = true;
				else
					System.out.println("Value must be in the range " + lb + " and " + ub);
			}
			catch (Exception e) {
				System.out.println("Enter an integer only.");
				sc.next();
			}
		}
		//returning output variable
		return res;
	}

	//method to validate users input
	public static int validateInt(Scanner sc){
		//initializing output variable
		int res = -1;

		//initializing variable to use in the loop
		boolean validate = false;

		//we keep loop going until valid option is selected
		while (!validate) {
			System.out.println("Enter an integer only");
			try {
				res = sc.nextInt();
				sc.nextLine();
				validate = true;
			}
			catch (Exception e) {
				System.out.println("Enter an integer only.");
				sc.next();
			}
		}
		//returning output variable
		return res;
	}

	//method to validate users input
	public static String validateString(Scanner sc){

		//initializing output variable
		String res = "";

		//initializing variable to use in the loop
		boolean validate = false;

		//we keep loop going until valid option is selected
		while (!validate) {
			System.out.println("Enter a String");
			try {
				res = sc.nextLine();
				if ((res.length() > 0) && (res.charAt(0) >= 'A') && (res.charAt(0) <= 'Z'))
					validate = true;
				else
					System.out.println("Entry must start with a capital letter and be non-empty.");
			}
			catch (Exception e) {
				System.out.println("Enter a String only.");
				sc.next();
			}
		}

		//returning output variable
		return res;
	}

	//session that the user interacts with
	public static void Session(String customersFileName, String itemsFileName, int maxPurchasedItems){

		//we create wardrobe object and load file
		Wardrobe l = new WardrobeImp(maxPurchasedItems);
		l.loadData(customersFileName,itemsFileName);

		//initializing variables to use
		boolean end = false;
		int choiceMenu;
		int choice;
		//scanner to take user's input
		Scanner sc = new Scanner(System.in);

		//while loop for when the session is running
		while (!end){
			//loop to print empty lines after every end case
			for (int i = 0; i < 10; i++)
				System.out.println("\n");

			//printing our menu
			printMenu();

			//setting choice for menu to upper bound and lower bound
			choiceMenu = validateInt(sc,0,4);

			//loop to print empty lines after every end case
			for (int i = 0; i < 10; i++)
				System.out.println("\n");

			//initializing variables to use
			String str;
			String str2;

			int num;
			int num2;
			int num3;

			boolean bool;

			//for when user exits program
			if(choiceMenu == 0){
				end = true;
			}

			if(choiceMenu == 1){

				//print user interacts with
				System.out.println("Enter 1. Add Customer\n");
				System.out.println("Enter 2. Remove Customer\n");
				System.out.println("Enter 3. Display Customer Info\n");

				//choice user makes determines case
				choice = validateInt(sc, 0, 3);

				switch (choice){

					case 1 -> {
						//print message
						System.out.println("******************\n1. Add Customer *\n******************");

						//we ask user's input
						System.out.println("Enter the name for the new customer");
						str = validateString(sc);

						//we add given user's input into customer
						num = l.addCustomer(str);

						//print for success
						System.out.println("New customer with ID " + num + " successfully created");

						//save changes to file
						l.saveData(customersFileName, itemsFileName);
					}

					case 2 -> {
						//print message
						System.out.println("******************\n2. Remove Customer *\n******************");

						//we display all customers ID's
						l.displayAllCustomerIDs();

						//we prompt user to select customers ID
						System.out.println("Enter the ID for the customer to be removed");

						//we validate user's input
						num = validateInt(sc);

						//we remove customer
						num = l.removeCustomer(num);

						//print for when no user found
						if (num == -1)
							System.out.println("No customer found with ID = " + num);
						else
							//print for when user has purchases
						if (num == -2)
							System.out.println("The customer with ID = " + num + " has purchases, cannot remove user.");
						else
							//print for success
							System.out.println("customer with ID = " + num + " successfully removed");

						//save changes to file
						l.saveData(customersFileName, itemsFileName);
					}

					case 3 -> {
						//print message
						System.out.println("******************\n3. Display Customer Info*\n******************");

						//we display all customers ID's
						l.displayAllCustomerIDs();

						//we prompt user to select customers ID for which to display info
						System.out.println("Enter the ID for the customer to be displayed");

						//we validate user's input
						num = validateInt(sc);

						//we display info of the customer selected by user
						l.displayCustomerInfo(num);
					}
				}
				//print at the end of operation
				if (choice != 0) {
					System.out.println("Press any key to continue");
					sc.nextLine();
				}
			}

			if(choiceMenu == 2){

				//print user interacts with
				System.out.println("Enter 1. Add Clothes Item\n");
				System.out.println("Enter 2. Remove Clothes Item\n");
				System.out.println("Enter 3. Display Clothes Info\n");

				//choice user makes determines case
				choice = validateInt(sc, 0, 3);

				switch (choice){

					case 1 -> {
						//print message
						System.out.println("******************\n1. Add Clothes Item *\n******************");

						//we prompt user to give details for the item
						System.out.println("Enter the color for the new clothes");

						//we validate user's input
						str = validateString(sc);

						System.out.println("Enter the quality for the new clothes");

						//we validate user's input
						str2 = validateString(sc);

						System.out.println("Enter the size for the new clothes\n");
						System.out.println("(Small 1-3) (Medium 3-6) (Large 6-10)");

						//we validate user's input
						num = validateInt(sc);

						System.out.println("Enter the quantity of clothes for the new clothes");

						//we validate user's input
						num2 = validateInt(sc);

						//we add clothes
						num = l.addClothes(str, str2, num, num2);

						//print for success
						System.out.println("New Clothes with ID " + num + " successfully created");

						//save changes to file
						l.saveData(customersFileName, itemsFileName);
					}

					case 2 -> {
						//print message
						System.out.println("******************\n2. Remove Clothes Item *\n******************");

						//we display all customers ID's
						l.displayAllItemIDs();

						//we prompt user to select items ID
						System.out.println("Enter the ID for the item to be removed");
						num = validateInt(sc);

						//for when clothes item is not found
						if (!l.removeClothes(num)) {
							System.out.println("No item found with ID = " + num);
						} else
							//print for success
							System.out.println("Clothes Item with ID = " + num + " has been removed");

						//save changes to file
						l.saveData(customersFileName, itemsFileName);
					}

					case 3 -> {
						//print message
						System.out.println("******************\n3. Display Clothes Info *\n******************");

						//we display all items ID's
						l.displayAllItemIDs();

						//we prompt user to select items ID for which to display info
						System.out.println("Enter the id for the Clothes Item to be displayed");

						//we validate user's input
						num = validateInt(sc);

						//we display info of the item selected by user
						l.displayClothesInfo(num);
					}
				}
				//print at the end of operation
				if (choice != 0) {
					System.out.println("Press any key to continue");
					sc.nextLine();
				}
			}

			if (choiceMenu == 3){
			//print message
			System.out.println("***************\n3. Buy Item *\n***************");

			//we prompt user to select a customer ID
			System.out.println("Enter the ID for the customer buying the item");

			//we display all customer ID's
			l.displayAllCustomerIDs();

			//we validate user's input
			num = validateInt(sc);

			//we prompt user to select a clothes item ID
			System.out.println("Enter the ID for the item to be bought");

			//we display all item ID's
			l.displayAllItemIDs();

			//we validate user's input
			num2 = validateInt(sc);

			//we assign the item selected by user to the customer selected by user
			num3 = l.buyItem(num, num2);

			//print for when item is already purchased
			if (num3 == -1) {
				System.out.println("The item is already purchased");
				System.out.println("Press any key to continue");
				sc.nextLine();
			}
			//print for success
			else
				System.out.println("Purchase with ID = " + num3 + " completed ");

			//save changes to file
			l.saveData(customersFileName, itemsFileName);
				System.out.println("Press any key to continue");
				sc.nextLine();
			}
			if (choiceMenu == 4){
				//print message
				System.out.println("******************\n4. Return Bought Item *\n******************");

				//we prompt user to select a customer ID
				System.out.println("Enter the ID for the customer returning the item");

				//we display all customers ID's
				l.displayAllCustomerIDs();

				//we validate user's input
				num = validateInt(sc);

				//we prompt user to select a clothes item ID
				System.out.println("Enter the ID for the item to be returned");

				//we display all items ID's
				l.displayAllItemIDs();

				//we validate user's input
				num2 = validateInt(sc);

				//we unassigned the item selected by user from the customer selected by user
				bool = l.returnItem(num, num2);

				//print for when item has not been purchased by given customer ID
				if (!bool) {
					System.out.println("Sorry, the item can not be returned");
					System.out.println("Press any key to continue");
					sc.nextLine();
				}
				//print for success
				else
					System.out.println("The item is returned successfully.");

				//save changes to file
				l.saveData(customersFileName, itemsFileName);
				System.out.println("Press any key to continue");
				sc.nextLine();
			}
		}
	}

	public static void main(String[] args) {
		//we read file
		String customersFileName = "./myDatabase/customers.txt";
		String itemsFileName = "./myDatabase/items.txt";
		int maxPurchasedItems = 3;

		//we call to the runtime method
		Session(customersFileName, itemsFileName, maxPurchasedItems);
	}
}

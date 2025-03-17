import java.util.Arrays;
import java.util.Scanner;

public class iCM111_Imsarie {
    public static Scanner scanner = new Scanner(System.in);

    public static String[] orderId = new String[0];
    public static String[] phone = new String[0];

    public static int[] orderStatus = new int[0];
    public static int[] qty = new int[0];

    public static double[] amount = new double[0];
    public static double[] size = new double[0];

    public static final int PROCESSING = 0;
    public static final int DELIVERING = 1;
    public static final int DELIVERED = 2;

    public static final double XS_PRICE = 600.00;
    public static final double S_PRICE = 800.00;
    public static final double M_PRICE = 900.00;
    public static final double L_PRICE = 1000.00;
    public static final double XL_PRICE = 1100.00;
    public static final double XXL_PRICE = 1200.00;

    public static int lastOrderId = 10000;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n   /$$$$$$$$                 /$$       /$$                            /$$$$$$  /$$  ");
			System.out.println("  | $$_____/                | $$      |__/                           /$$__  $$| $$  ");
			System.out.println("  | $$    /$$$$$$   /$$$$$$$| $$$$$$$  /$$  /$$$$$$  /$$$$$$$       | $$  \\__/| $$$$$$$   /$$$$$$   /$$$$$$  ");
			System.out.println("  | $$$$$|____  $$ /$$_____/| $$__  $$| $$ /$$__  $$| $$__  $$      |  $$$$$$ | $$__  $$ /$$__  $$ /$$__  $$  ");
			System.out.println("  | $$__/ /$$$$$$$|  $$$$$$ | $$  \\ $$| $$| $$  \\ $$| $$  \\ $$       \\____  $$| $$  \\ $$| $$  \\ $$| $$  \\ $$  ");
			System.out.println("  | $$   /$$__  $$ \\____  $$| $$  | $$| $$| $$  | $$| $$  | $$       /$$  \\ $$| $$  | $$| $$  | $$| $$  | $$  ");
			System.out.println("  | $$  |  $$$$$$$ /$$$$$$$/| $$  | $$| $$|  $$$$$$/| $$  | $$      |  $$$$$$/| $$  | $$|  $$$$$$/| $$$$$$$/  ");
			System.out.println("  |__/   \\_______/|_______/ |__/  |__/|__/ \\______/ |__/  |__/       \\______/ |__/  |__/ \\______/ | $$____/  ");
			System.out.println("                                                                                                  | $$  ");
			System.out.println("                                                                                                  | $$  ");
			System.out.println("                                                                                                  |__/  ");
			System.out.println("  _______________________________________________________________________________________________________________");
			System.out.println("\n\t\t [1] Place Order      \t\t\t\t [2] Search Customer ");
			System.out.println("\n\t\t [3] Search Order     \t\t\t\t [4] View Report ");
			System.out.println("\n\t\t [5] Set Order Status \t\t\t\t [6] Delete Order ");

            System.out.print("\n\t Input Option: ");

            // Input from user
            int option = scanner.nextInt();
            scanner.nextLine();

            clearConsole();

            // Switch case for menu options
            switch (option) {
                case 1:
                    placeOrder();
                    break;
                case 2:
                    searchCustomer();
                    break;
                case 3:
                    searchOrder();
                    break;
                case 4:
                    viewReports();
                    break;
                case 5:
                    changeOrderStatus();
                    break;
                case 6:
                    deleteOrder();
                    break;
                default:
                    System.out.println("\t Invalid option. Please try again.");

            }
        }
    }

    public static void placeOrder(){
        while(true){
            System.out.println("\t    _____  _                         ___           _                ");
			System.out.println("\t   |  __ \\| |                       / __ \\        | |              ");
			System.out.println("\t   | |__) | | __ _  ____ ___       | |  | |_ __ __| | ___ _ __      ");
			System.out.println("\t   | ____/| |/ _' |/ __ / _ \\      | |  | | '__/ _' |/ _ \\ '__|    ");
			System.out.println("\t   | |    | | (_| | (__|  __/      | |__| | | | (_| |  __/ |        ");
			System.out.println("\t   |_|    |_|\\__,_|\\____\\___|       \\____/|_|  \\__'_|\\___|_|  ");
			System.out.println("\t ________________________________________________________________\n");

            String orderID = generateOrderId();
            System.out.println("Enter Order ID : " + orderID);

            String phoneNumber = getValidContactNumber();
            double size = getTShirtSizeAndPrice();
            int qty = getValidQuantity();

            System.out.println("Amount : " + size * qty);

            System.out.print("Do you want to place this order (Y/N)? ");
            String placeOrder = scanner.next().toUpperCase();

            if (placeOrder.equals("Y")) {
                addOrder(orderID, phoneNumber, size, qty, size * qty, PROCESSING);
                System.out.println("\tOrder placed successfully under Order ID " + orderID);

            } else {
                System.out.println("Order not placed.");
                lastOrderId--;
            }

            System.out.print("\nDo you want to place another order? (Y/N)");
            String placeNewOrder = scanner.next().toUpperCase();

            if (!placeNewOrder.equals("Y")){
                clearConsole();
                break;
            }

            clearConsole();
        }
    }


    public static void searchCustomer(){
        while (true) {
            System.out.println("\t     _____                     _            ____           _                                     ");
			System.out.println("\t    / ____|                   | |          / ___|         | |                                    ");
			System.out.println("\t   | (___   ___  __ _ _ __ ___| |__       | |    _   _ ___| |_ ___  _ __ ___   ___ _ __          "); 
			System.out.println("\t    \\___ \\ / _ \\/ _ '| '__/ __| '_ \\      | |   | | | / __| __/ _ \\| '_  '_ \\ / _ \\ '__|         ");
			System.out.println("\t    ____) |  __/ (_| | | | (__| | | |     | |___| |_| \\__ \\ || (_) | | | | | |  __/ |            ");
			System.out.println("\t   |_____/ \\___|\\__,_|_|  \\___|_| |_|      \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|            ");
			System.out.println("\t _______________________________________________________________________________________         \n");

            String searchPhone = getValidContactNumber();

            boolean found = false;
            System.out.println("\n\t\t+---+----+----+----+----+----+---+");
            System.out.println("\t\t|\t Size \t| \tQty\t | \tAmount\t |");
            System.out.println("\t\t+---+----+----+----+----+----+---+");

            for (int i = 0; i < phone.length; i++) {
                if (phone[i].equals(searchPhone)) {
                    found = true;
                    System.out.println("\t\t|\t " + size[i] + " \t| \t" + qty[i] + "\t | \t" + amount[i] + "\t |");
                }
            }

            if (!found) {
                System.out.println("No orders found for this customer.\n");


            } else {
                System.out.println("\t\t+---+----+----+----+----+----+---+");
            }

            System.out.print("\nDo you want to search another customer report ? (Y/N)");
            String placeNewOrder = scanner.next().toUpperCase();

            if (!placeNewOrder.equals("Y")){
                clearConsole();
                break;
            }
            clearConsole();
            System.out.println();
        }
    }

    public static void searchOrder(){
        while(true) {
            System.out.println("\t     _____                     _            ____          _             ");
			System.out.println("\t    / ____|                   | |          / __ \\        | |                    ");
			System.out.println("\t   | (___   ___  __ _ _ __ ___| |__       | |  | |_ __ __| | ___ _ __          "); 
			System.out.println("\t    \\___ \\ / _ \\/ _ '| '__/ __| '_ \\      | |  | | '__/ _' |/ _ \\ '__|         ");
			System.out.println("\t    ____) |  __/ (_| | | | (__| | | |     | |__| | | | (_| |  __/ |            ");
			System.out.println("\t   |_____/ \\___|\\__,_|_|  \\___|_| |_|      \\____/|_|  \\__,_|\\___|_|            ");
			System.out.println("\t ______________________________________________________________________         \n");

            System.out.print("Enter the order ID to search: ");
            String searchOrderId = scanner.next();

            boolean found = false;

            System.out.println();

            for (int i = 0; i < orderId.length; i++) {
                if (orderId[i].equals(searchOrderId)) {
                    found = true;
                    System.out.println("Phone Number : " + phone[i]);
                    System.out.println("Size : " + size[i]);
                    System.out.println("QTY : " + getSizeLabel(size[i]));
                    System.out.println("Amount : " + amount[i]);
                    System.out.println("Status : " + (orderStatus[i] == 0 ? "Processing" : orderStatus[i] == 2 ? "Delivering" : "Delivered"));
                }
            }

            System.out.println();

            if (!found) {
                System.out.println("\tInvalid Order ID...");
            }

            System.out.print("Do you want to search another order? (Y/N) : ");
            String placeNewOrder = scanner.next().toUpperCase();

            if (!placeNewOrder.equals("Y")){
                clearConsole();
                break;
            }
            clearConsole();
            System.out.println();
        }
    }


    public static void changeOrderStatus() {
        while(true) {
            System.out.println("\t     ____          _                   ____ _        _                  ");
			System.out.println("\t    / __ \\        | |                 / ___| |      | |                 ");
			System.out.println("\t   | |  | |_ __ __| | ___ _ __       | (__ | |_ __ _| |_ _   _ ___      ");
			System.out.println("\t   | |  | | '__/ _' |/ _ \\ '__|       \\__ \\| __/ _' | __| | | / __|     ");
			System.out.println("\t   | |__| | | | (_| |  __/ |          ___) | || (_| | |_| |_| \\__ \\     ");
			System.out.println("\t    \\____/|_|  \\__'_|\\___|_|         |____/ \\__\\__,_|\\__|\\__,_|___/     ");
			System.out.println("\t _____________________________________________________________________  \n");

            System.out.print("Enter the order ID to change status: ");
            String searchOrderId = scanner.next();

            boolean found = false;

            for (int i = 0; i < orderId.length; i++) {
                if (orderId[i].equals(searchOrderId)) {
                    found = true;
                    System.out.println("\nPhone Number : " + phone[i]);
                    System.out.println("Size : " + size[i]);
                    System.out.println("QTY : " + getSizeLabel(size[i]));
                    System.out.println("Amount : " + amount[i]);
                    System.out.println("Status : " + (orderStatus[i] == 0 ? "Processing" : orderStatus[i] == 2 ? "Delivering" : "Delivered"));

                    if(orderStatus[i] == 0){
                        System.out.print("\nDo you want to change status of order? (Y/N) : ");
                        String change = scanner.next().toUpperCase();

                        if(change.equals("Y")) {
                            System.out.println("\t[1] Order Delivering");
                            System.out.println("\t[2] Order Delivered");

                            System.out.print("\n Enter option : ");
                            int choice = scanner.nextInt();

                            if (choice == 1) {
                                orderStatus[i] = DELIVERING;
                                System.out.println("\nOrder status changed.");
                            } else if (choice == 2) {
                                orderStatus[i] = DELIVERED;
                                System.out.println("\nOrder status changed.");
                            }
                        }
                    } else if (orderStatus[i] == 1) {
                        System.out.print("\nDo you want to change status of order? (Y/N) : ");
                        String change = scanner.next().toUpperCase();

                        if(change.equals("Y")){
                            System.out.println("\t[1] Order Delivered");

                            System.out.print("\n Enter option : ");
                            int choice = scanner.nextInt();

                            if(choice == 1){
                                orderStatus[i] = DELIVERED;
                                System.out.println("Order status changed to 'delivered'.");
                            }
                        }

                    } else {
                        System.out.println("Can't change the order status...\n");
                    }

                    // Ask for confirmation to change the status
                    System.out.print("Do you want to change another order status (Y/N)? ");
                    String confirmChange = scanner.next().toUpperCase();

                    if (!confirmChange.equals("Y")) {
                        clearConsole();
                        return;
                    }
                }
            }

            if (!found) {
                System.out.println("\tInvalid Order ID.");
            }
            clearConsole();

        }
    }


    public static void deleteOrder() {
        while (true) {
            System.out.println("\t    _____       _      _               ____          _             ");
			System.out.println("\t   |  __ \\     | |    | |             / __ \\        | |            ");
			System.out.println("\t   | |  | | ___| | ___| |_ ___       | |  | |_ __ __| | ___ _ __   ");
			System.out.println("\t   | |  | |/ _ \\ |/ _ \\ __/ _ \\      | |  | | '__/ _' |/ _ \\ '__|  ");
			System.out.println("\t   | |__| |  __/ |  __/ ||  __/      | |__| | | | (_| |  __/ |     ");
			System.out.println("\t   |_____/ \\___|_|\\___|\\__\\___|       \\____/|_|  \\__,_|\\___|_|     ");
			System.out.println("\t _________________________________________________________________  \n");

            System.out.print("Enter the order ID to delete: ");
            String deleteOrderId = scanner.next();

            boolean found = false;

            for (int i = 0; i < orderId.length; i++) {
                if (orderId[i].equals(deleteOrderId)) {
                    found = true;
                    System.out.println("\nPhone Number : " + phone[i]);
                    System.out.println("Size : " + size[i]);
                    System.out.println("QTY : " + getSizeLabel(size[i]));
                    System.out.println("Amount : " + amount[i]);
                    System.out.println("Status : " + (orderStatus[i] == 0 ? "Processing" : orderStatus[i] == 2 ? "Delivering" : "Delivered"));

                    // Ask for confirmation to delete the order
                    System.out.print("\nDo you really want to delete this order (Y/N)? ");
                    String confirmDelete = scanner.next().toUpperCase();

                    if (confirmDelete.equals("Y")) {
                        // Delete the order by shifting the remaining elements
                        for (int j = i; j < orderId.length - 1; j++) {
                            orderId[j] = orderId[j + 1];
                            phone[j] = phone[j + 1];
                            size[j] = size[j + 1];
                            qty[j] = qty[j + 1];
                            amount[j] = amount[j + 1];
                            orderStatus[j] = orderStatus[j + 1];
                        }
                        // Resize the arrays
                        orderId = resizeArray(orderId);
                        phone = resizeArray(phone);
                        size = resizeArray(size);
                        qty = resizeArray(qty);
                        amount = resizeArray(amount);
                        orderStatus = resizeArray(orderStatus);

                        System.out.println("Order " + deleteOrderId + " has been successfully deleted.");
                    } else {
                        System.out.println("Order deletion canceled.");
                    }

                    System.out.println("\nDo you want delete another order ?(Y/N) : ");
                    String choice = scanner.next().toUpperCase();

                    if (!choice.equals("Y")){
                        clearConsole();
                        return;
                    }
                }
            }

            if (!found) {
                System.out.println("No order found with the given Order ID.");
            }

            clearConsole();
        }
    }

    public static void viewReports(){
        System.out.println("\t    _____                       _            ");
		System.out.println("\t   |  __ \\                     | |           ");
		System.out.println("\t   | |__) |___ _ __   ___  _ __| |_ ___      ");
		System.out.println("\t   |  _  // _ \\ '_ \\ / _ \\| '__| __/ __|     ");
		System.out.println("\t   | | \\ \\  __/ |_) | (_) | |  | |_\\__ \\     ");
		System.out.println("\t   |_|  \\_\\___| .__/ \\___/|_|   \\__|___/     ");
		System.out.println("\t              | |                            ");
		System.out.println("\t              |_|                            ");
		System.out.println("\t _________________________________________ ");
		System.out.println("\n\t\t [1] Customer Reports");
		System.out.println("\n\t\t [2] Item Reports");
		System.out.println("\n\t\t [3] Order Reports\n");

        System.out.print("Enter Option: ");
        int reportOption = scanner.nextInt();
        scanner.nextLine();

        switch (reportOption) {
            case 1:
                customerReport();
                break;
            case 2:
                itemsReport();
                break;
//            case 3:
//                allCustomerReports();
//                break;
            default:
                System.out.println("Invalid option. Please try again.");

        }

        clearConsole();


    }

    public static void customerReport(){
        System.out.println("\t     ____           _                                   _____                       _          ");
		System.out.println("\t    / ___|         | |                                 |  __ \\                     | |         ");
		System.out.println("\t   | |    _   _ ___| |_ ___  _ __ ___   ___ _ __       | |__) |___ _ __   ___  _ __| |_ ___    "); 
		System.out.println("\t   | |   | | | / __| __/ _ \\| '_ ' _ \\ / _ \\ '__|      |  _  // _ \\ '  \\ /   \\| '__| __/ __|   ");
		System.out.println("\t   | |___| |_| \\__ \\ || (_) | | | | | |  __/ |         | | \\ \\  __/ |_) | (_) | |  | |_\\__ \\   ");
		System.out.println("\t    \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|         |_|  \\_\\___| .__/ \\___/|_|   \\__|___/   ");
		System.out.println("\t                                                                  | |                           ");
		System.out.println("\t                                                                  |_|                           ");
		System.out.println("\t _____________________________________________________________________________________________  ");
		System.out.println("\n\t\t [1] Best in Customers");
		System.out.println("\n\t\t [2] View Customers");
		System.out.println("\n\t\t [3] All Customer Report\n");

        System.out.print("Enter Option: ");
        int reportOption = scanner.nextInt();
        scanner.nextLine();

        switch (reportOption) {
            case 1:
                bestInCustomers();
                break;
            case 2:
                viewCustomers();
                break;
            case 3:
                allCustomerReports();
                break;
            default:
                System.out.println("Invalid option. Please try again.");

        }

        clearConsole();

    }


    public static void bestInCustomers() {
        System.out.println("\t    ____            _          _____               ____           _                                    ");
		System.out.println("\t   |  _ \\          | |        |_   _|             / ___|         | |                                   ");
		System.out.println("\t   | |_) | ___  ___| |_         | |  _ __        | |    _   _ ___| |_ ___  _ __ ___   ___ _ __ ___     ");
		System.out.println("\t   |  _ < / _ \\/ __| __/        | | | '_ \\       | |   | | | / __| __/ _ \\| '_ ' _ \\ / _ \\ '__/ __|    ");
		System.out.println("\t   | |_) |  __/\\__ \\ |_        _| |_| | | |      | |___| |_| \\__ \\ || (_) | | | | | |  __/ |  \\__ \\    ");
		System.out.println("\t   |____/ \\___||___/\\__|      |_____|_| |_|       \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|  |___/    ");
		System.out.println("\t ____________________________________________________________________________________________________  \n");

        String[] uniquePhones = getUniquePhones();
        int[] qtyTotal = new int[uniquePhones.length];
        double[] totalAmounts = new double[uniquePhones.length];

        for (int i = 0; i < uniquePhones.length; i++) {
            totalAmounts[i] = calculateTotalAmountByCustomer(uniquePhones[i]);
            qtyTotal[i] = calculateTotalQuantity(uniquePhones[i]);
        }


        for (int i = 0; i < totalAmounts.length - 1; i++) {
            for (int j = i + 1; j < totalAmounts.length; j++) {
                if (totalAmounts[i] < totalAmounts[j]) {
                    double tempAmount = totalAmounts[i];
                    totalAmounts[i] = totalAmounts[j];
                    totalAmounts[j] = tempAmount;

                    String tempPhone = uniquePhones[i];
                    uniquePhones[i] = uniquePhones[j];
                    uniquePhones[j] = tempPhone;

                    int tempQty = qtyTotal[i];
                    qtyTotal[i] = qtyTotal[j];
                    qtyTotal[j] = tempQty;
                }
            }
        }


        System.out.println("\n\t\t+---+----+----+----+----+----+---+----+----+----+----+---+");
        System.out.println("\t\t|\t Customer ID \t| \tAll Qty\t | \t\tTotal Amount\t |");
        System.out.println("\t\t+---+----+----+----+----+----+---+----+----+----+----+---+");

        for (int i = 0; i < uniquePhones.length; i++) {
            System.out.println("\t\t|\t " + uniquePhones[i] + " \t| \t" + qtyTotal[i] + "\t\t | \t\t" + totalAmounts[i] + "\t\t |");
        }

        System.out.println("\t\t+---+----+----+----+----+----+---+----+----+----+----+---+\n");

        System.out.print("To access Main Menu, please enter 0 : ");
        scanner.nextLine();
        clearConsole();
    }

    public static void viewCustomers() {
        System.out.println("\t    __      ___                         ____           _                                    ");
		System.out.println("\t    \\ \\    / (_)                       / ___|         | |                                   ");
		System.out.println("\t     \\ \\  / / _  _____        __      | |    _   _ ___| |_ ___  _ __ ___   ___ _ __ ___     ");
		System.out.println("\t      \\ \\/ / | |/ _ \\ \\  /\\  / /      | |   | | | / __| __/   \\| '_ ' _ \\ / _ \\ '__/ __|    ");
		System.out.println("\t       \\  /  | |  __/\\ \\/  \\/ /       | |___| |_| \\__ \\ || (_) | | | | | |  __/ |  \\__ \\    ");
		System.out.println("\t        \\/   |_|\\___| \\__/\\__/         \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|  |___/    ");
		System.out.println("\t __________________________________________________________________________________________  \n");

        String[] uniquePhones = getUniquePhones();
        int[] qtyTotal = new int[uniquePhones.length];
        double[] totalAmounts = new double[uniquePhones.length];

        for (int i = 0; i < uniquePhones.length; i++) {
            totalAmounts[i] = calculateTotalAmountByCustomer(uniquePhones[i]);
            qtyTotal[i] = calculateTotalQuantity(uniquePhones[i]);
        }

        System.out.println("\n\t\t+---+----+----+----+----+----+---+----+----+----+----+---+");
        System.out.println("\t\t|\t Customer ID \t| \tAll Qty\t | \t\tTotal Amount\t |");
        System.out.println("\t\t+---+----+----+----+----+----+---+----+----+----+----+---+");

        for (int i = 0; i < uniquePhones.length; i++) {
            System.out.println("\t\t|\t " + uniquePhones[i] + " \t| \t" + qtyTotal[i] + "\t\t | \t\t" + totalAmounts[i] + "\t\t |");
        }

        System.out.println("\t\t+---+----+----+----+----+----+---+----+----+----+----+---+\n");

        System.out.print("To access Main Menu, please enter 0 : ");
        scanner.nextLine();
        clearConsole();

    }

    public static void allCustomerReports() {
        System.out.println("\t             _ _         ____           _                                   _____                       _          ");
		System.out.println("\t       /\\   | | |       / ___|         | |                                 |  __ \\                     | |         ");
		System.out.println("\t      /  \\  | | |      | |    _   _ ___| |_ ___  _ __ ___   ___ _ __       | |__) |___ _ __   ___  _ __| |_ ___    ");
		System.out.println("\t     / /\\ \\ | | |      | |   | | | / __| __/   \\| '_ ' _ \\ / _ \\ '__/      |  _  // _ \\ '  \\ /   \\| '__| __/ __|   ");
		System.out.println("\t    / ____ \\| | |      | |___| |_| \\__ \\ || (_) | | | | | |  __/ |         | | \\ \\  __/ |_) | (_) | |  | |_\\__ \\   ");
		System.out.println("\t   /_/    \\_\\_|_|       \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|         |_|  \\_\\___| .__/ \\___/|_|   \\__|___/   ");
		System.out.println("\t                                                                                      | |                          ");
		System.out.println("\t                                                                                      |_|                          ");
		System.out.println("\t ________________________________________________________________________________________________________________ \n");

        String[] uniquePhones = getUniquePhones();
        double[] totalAmounts = new double[uniquePhones.length];
        int[][] quantities = new int[uniquePhones.length][6];

        for (int i = 0; i < uniquePhones.length; i++) {
            totalAmounts[i] = calculateTotalAmountByCustomer(uniquePhones[i]);
            quantities[i] = calculateQuantitiesWithSize(uniquePhones[i]);

        }

        System.out.println("\n\t\t+---+----+----+----+----+----+---+----+----+----+----+---+---+----+----+----+----+");
        System.out.println("\t\t|\t Customer ID \t| \tXS\t | \tS\t | \tM\t | \tL\t | \tXL\t | \tXXL\t | \tTotal\t |");
        System.out.println("\t\t+---+----+----+----+----+----+---+----+----+----+----+---+---+----+----+----+----+");

        for (int i = 0; i < uniquePhones.length; i++) {
            System.out.println("\t\t|\t " + uniquePhones[i] + " \t| \t" + quantities[i][0] + " \t | \t" + quantities[i][1] + "\t | \t" + quantities[i][2] + "\t | \t" + quantities[i][3] + "\t | \t" + quantities[i][4]+ "\t | \t" + quantities[i][5] +"\t | \t" +  totalAmounts[i] +" \t |");
//            System.out.println(uniquePhones[i] + " " + quantities[i][0]+ " " + quantities[i][1]+ " " + quantities[i][2]+ " " + quantities[i][3]+ " " + quantities[i][4]+ " " + quantities[i][5] + " " + totalAmounts[i]);
        }

        System.out.println("\t\t+---+----+----+----+----+----+---+----+----+----+----+---+---+----+----+----+----+");


        System.out.print("\nTo access Main Menu, please enter 0 : ");
        scanner.nextLine();
        clearConsole();
    }


    public static void itemsReport(){
        System.out.println("_____________________________________________________________");
        System.out.println("                         Items Report                       ");
        System.out.println("_____________________________________________________________\n");

        System.out.println("\n [1] All Orders");
        System.out.println(" [2] Orders by Amount\n");

        System.out.print("Enter Option: ");
        int reportOption = scanner.nextInt();
        scanner.nextLine();

        switch (reportOption) {
            case 1:
                viewAllOrders();
                break;
            case 2:
                viewAllOrdersSort();
                break;
            default:
                System.out.println("Invalid option. Please try again.");

        }

        clearConsole();
    }

    public static void viewAllOrders() {
        System.out.println("_____________________________________________________________");
        System.out.println("                            Orders                           ");
        System.out.println("_____________________________________________________________\n");

        System.out.println("+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+");
        System.out.println("|\tOrder ID|\tCustomer No|\tSize|\t|QTY\t|\tAmount\t|Status|");
        System.out.println("+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+");

        // Sort orders in descending order based on Order ID using Bubble Sort
        for (int i = 0; i < orderId.length - 1; i++) {
            for (int j = 0; j < orderId.length - 1 - i; j++) {
                if (orderId[j].compareTo(orderId[j + 1]) < 0) { // Descending order comparison
                    // Swap orderId
                    String tempOrderId = orderId[j];
                    orderId[j] = orderId[j + 1];
                    orderId[j + 1] = tempOrderId;

                    // Swap phone
                    String tempPhone = phone[j];
                    phone[j] = phone[j + 1];
                    phone[j + 1] = tempPhone;

                    // Swap size
                    double tempSize = size[j];
                    size[j] = size[j + 1];
                    size[j + 1] = tempSize;

                    // Swap qty
                    int tempQty = qty[j];
                    qty[j] = qty[j + 1];
                    qty[j + 1] = tempQty;

                    // Swap amount
                    double tempAmount = amount[j];
                    amount[j] = amount[j + 1];
                    amount[j + 1] = tempAmount;

                    // Swap orderStatus
                    int tempStatus = orderStatus[j];
                    orderStatus[j] = orderStatus[j + 1];
                    orderStatus[j + 1] = tempStatus;
                }
            }
        }

        // Print sorted orders
        for (int i = 0; i < orderId.length; i++) {
            System.out.println("|\t" + orderId[i] + "|\t" + phone[i] + "|\t" + getSizeLabel(size[i]) + " |\t|" + qty[i] + "\t|\t" + amount[i] * qty[i] + "\t|" +( orderStatus[i] == 0 ? "Processing" : orderStatus[i] == 1 ? "Deliveruing" : "Delivered" )+ "|");
        }

        System.out.println("+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+");

        System.out.print("\nTo access Main Menu, please enter 0 : ");
        scanner.nextLine();
        clearConsole();
    }

    public static void viewAllOrdersSort() {
        System.out.println("_____________________________________________________________");
        System.out.println("                  Orders By Amount                           ");
        System.out.println("_____________________________________________________________\n");

        System.out.println("+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+");
        System.out.println("|\tOrder ID|\tCustomer No|\tSize|\t|QTY\t|\tAmount\t|Status|");
        System.out.println("+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+");

        // Sort orders in descending order based on Amount using Bubble Sort
        for (int i = 0; i < amount.length - 1; i++) {
            for (int j = 0; j < amount.length - 1 - i; j++) {
                // Calculate the total amount for the current order
                double totalAmountJ = amount[j] * qty[j];
                double totalAmountJ1 = amount[j + 1] * qty[j + 1];

                if (totalAmountJ < totalAmountJ1) { // Descending order comparison
                    // Swap amount
                    double tempAmount = amount[j];
                    amount[j] = amount[j + 1];
                    amount[j + 1] = tempAmount;

                    // Swap qty
                    int tempQty = qty[j];
                    qty[j] = qty[j + 1];
                    qty[j + 1] = tempQty;

                    // Swap orderId
                    String tempOrderId = orderId[j];
                    orderId[j] = orderId[j + 1];
                    orderId[j + 1] = tempOrderId;

                    // Swap phone
                    String tempPhone = phone[j];
                    phone[j] = phone[j + 1];
                    phone[j + 1] = tempPhone;

                    // Swap size
                    double tempSize = size[j];
                    size[j] = size[j + 1];
                    size[j + 1] = tempSize;

                    // Swap orderStatus
                    int tempStatus = orderStatus[j];
                    orderStatus[j] = orderStatus[j + 1];
                    orderStatus[j + 1] = tempStatus;
                }
            }
        }

        // Print sorted orders
        for (int i = 0; i < orderId.length; i++) {
            System.out.println("|\t" + orderId[i] + "|\t" + phone[i] + "|\t" + getSizeLabel(size[i]) + " |\t|" + qty[i] + "\t|\t" + (amount[i] * qty[i]) + "\t|" + ( orderStatus[i] == 0 ? "Processing" : orderStatus[i] == 1 ? "Deliveruing" : "Delivered" ) + "|");
        }

        System.out.println("+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+");

        System.out.print("\nTo access Main Menu, please enter 0 : ");
        scanner.nextLine();
        clearConsole();
    }



    public static String generateOrderId() {
        lastOrderId++; // Increment the last order ID
        return "ODR#" + String.format("%05d", lastOrderId); // Format to 5 digits
    }

    // Calculate the total amount spent by a specific customer
    public static double calculateTotalAmountByCustomer(String phoneNumber) {
        double totalAmount = 0;

        for (int i = 0; i < phone.length; i++) {
            if (phone[i].equals(phoneNumber)) {
                totalAmount += amount[i];
            }
        }

        return totalAmount;
    }

    public static int calculateTotalQuantity(String phoneNumber){
        int quantityValue = 0;

        for (int i = 0; i < phone.length; i++) {
            if(phone[i].equals(phoneNumber)){
                quantityValue +=  qty[i];
            }

        }
        return quantityValue;
    }

    public static int[] calculateQuantitiesWithSize(String phoneNumber){
        int[] quantitiess = new int[6];

        for (int i = 0; i < phone.length; i++) {
            if(phone[i].equals(phoneNumber)){
                if(size[i] == XS_PRICE){
                    quantitiess[0] += 1;
                } else if (size[i] == S_PRICE) {
                    quantitiess[1] += 1;
                } else if (quantitiess[2] == M_PRICE) {
                    quantitiess[2] += 1;
                } else if (quantitiess[3] == L_PRICE) {
                    quantitiess[3] += 1;
                } else if (quantitiess[4] == XL_PRICE) {
                    quantitiess[4] += 1;
                } else {
                    quantitiess[5] += 1;
                }
            }
        }
        return quantitiess;
    }

    // Get unique phone numbers of customers
    public static String[] getUniquePhones() {
        String[] uniquePhones = new String[phone.length];
        int count = 0;

        for (int i = 0; i < phone.length; i++) {
            boolean isUnique = true;
            for (int j = 0; j < count; j++) {
                if (phone[i].equals(uniquePhones[j])) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                uniquePhones[count] = phone[i];
                count++;
            }
        }

        return Arrays.copyOf(uniquePhones, count); // Return only the populated portion of the array
    }

    // Method to get a valid customer contact number
    public static String getValidContactNumber() {
        String contact;
        while (true) {
            System.out.print("Enter Customer Contact Number  : ");
            contact = scanner.next();
            if (contact.length() == 10 && contact.startsWith("0")) {
                break; // Valid contact number
            } else {
                System.out.println("Invalid phone number. Please try again.");
            }
        }
        return contact;
    }

    // Method to get a valid T-shirt size and return the price
    public static double getTShirtSizeAndPrice() {
        String size;
        double price = 0.0;
        while (true) {
            System.out.print("Enter T-Shirt Size (XS/S/M/L/XL/XXL): ");
            size = scanner.next().toUpperCase();
            switch (size) {
                case "XS":
                    price = XS_PRICE;
                    break;
                case "S":
                    price = S_PRICE;
                    break;
                case "M":
                    price = M_PRICE;
                    break;
                case "L":
                    price = L_PRICE;
                    break;
                case "XL":
                    price = XL_PRICE;
                    break;
                case "XXL":
                    price = XXL_PRICE;
                    break;
                default:
                    System.out.println("Invalid size. Please enter a valid size.");
                    continue;
            }
            break; // Exit loop when valid size is entered
        }
        return price;
    }

    // Method to convert size price back to label
    public static String getSizeLabel(double sizePrice) {
        if (sizePrice == XS_PRICE) {
            return "XS";
        }
        else if (sizePrice == S_PRICE) {
            return "S";
        }
        else if (sizePrice == M_PRICE) {
            return "M";
        }
        else if (sizePrice == L_PRICE) {
            return "L";
        }
        else if (sizePrice == XL_PRICE) {
            return "XL";
        }
        else if (sizePrice == XXL_PRICE) {
            return "XXL";
        }

        return "Unknown";
    }

    // Method to get a valid quantity
    public static int getValidQuantity() {
        int quantity;
        while (true) {
            System.out.print("Enter Quantity: ");
            quantity = scanner.nextInt();
            if (quantity > 0) {
                break; // Valid quantity
            } else {
                System.out.println("Quantity must be greater than 0. Please try again.");
            }
        }
        return quantity;
    }

    public static void addOrder(String orderID, String phoneNumber, double shirtSize, int quantity, double totalAmount, int status) {
        // Resize orderId array
        orderId = addToArray(orderId, orderID);
        phone = addToArray(phone, phoneNumber);
        size = addToArray(size, shirtSize);
        qty = addToArray(qty, quantity);
        amount = addToArray(amount, totalAmount);
        orderStatus = addToArray(orderStatus, status);
    }

    public static String[] resizeArray(String[] array) {
        String[] newArray = new String[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, newArray.length);
        return newArray;
    }

    public static double[] resizeArray(double[] array) {
        double[] newArray = new double[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, newArray.length);
        return newArray;
    }

    public static int[] resizeArray(int[] array) {
        int[] newArray = new int[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, newArray.length);
        return newArray;
    }

    public static String[] addToArray(String[] array, String value) {
        String[] newArray = new String[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = value;
        return newArray;
    }

    public static double[] addToArray(double[] array, double value) {
        double[] newArray = new double[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = value;
        return newArray;
    }

    public static int[] addToArray(int[] array, int value) {
        int[] newArray = new int[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = value;
        return newArray;
    }

    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c",
                        "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
            // Handle any exceptions.
        }
    }

}

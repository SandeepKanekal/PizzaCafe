import java.util.Scanner;

/**
 * Main class
 */
class Main {
    /**
     * Checks and stops multiple orders in one execution of the program
     */
    static boolean check = false;
    /**
     * Stores the value of the choice
     */
    static int choice = 0;
    /**
     * Stores value of choice under menu
     */
    static int choice1 = 0;
    /**
     * Used to check if the user has entered the dates correctly
     */
    static int DateChecker = 0;
    /**
     * Stores initial date
     */
    static String FromDate = "";
    /**
     * Stores Quantities of the Items in the Inventory before an order in case it is
     * cancelled
     */
    static double[] InventoryValues = new double[5];
    /**
     * Stores number of margherita pizzas ordered
     */
    static int NumberOfMargherita = 0;
    /**
     * Stores the number of spicy pizzas ordered
     */
    static int NumberOfSpicy = 0;
    /**
     * Stores the number of veggie pizzas ordered
     */
    static int NumberOfVeggie = 0;
    /**
     * Stores the original number of margherita pizzas ordered
     */
    static int OriMar = 0;
    /**
     * Stores the original number of spicy pizzas ordered
     */
    static int OriSpi = 0;
    /**
     * Stores the original number of veggie pizzas ordered
     */
    static int OriVeg = 0;
    /**
     * Store value of profit
     */
    static double profit = 0.0;
    /**
     * Stores the value of choice1
     */
    static int StoreChoice1_1 = 0;
    /**
     * Stores the value of choice1
     */
    static int StoreChoice1_2 = 0;
    /**
     * Stores the value of choice1
     */
    static int StoreChoice1_3 = 0;
    /**
     * Stores final date
     */
    static String ToDate = "";
    /**
     * Stores the total amount of pizzas without taxes
     */
    static double TotalPizzaAmt = 0.0;

    /**
     * Main Method
     */
    public static void main(String[] args) {
        // print header
        System.out.println("\t \t \t \t \t \tNortham's Pizza ");
        System.out.println("\t \t \t  \t #5,27th Main,16th Cross,HSR Layout,Bengaluru.");

        // creation of objects
        Scanner input = new Scanner(System.in);
        Data ObjData = new Data();
        Profits ObjProfits = new Profits();
        InventoryData ObjInv = new InventoryData();
        AdminModuleDisplay ObjAdminModule = new AdminModuleDisplay();

        // Storing Values of the Inventory before ordering
        InventoryValues = ObjInv.storeInventoryData();

        // print choices
        System.out.println();
        System.out.println("\t1.Menu");
        System.out.println("\t2.Inventory");
        System.out.println("\t3.Profit/loss");
        System.out.println("\t4.Admin Module");
        System.out.println("\t5.Exit");
        System.out.println();

        // Repeat until the user enter 5
        first:
        while (choice < 5) {
            System.out.println();
            System.out.println("Enter your choice under main choices");
            choice = input.nextInt();

            // compute and display based on main choices
            switch (choice) {
                case 1 -> {
                    if (check) {
                        System.out.println("Please run the program again to order");
                        System.out.println("***** BYE!! ***** THANK YOU *****");
                        break first;
                    }
                    System.out.println("****** MENU ******");
                    System.out.println();
                    ObjData.displayMenu();
                    System.out.println();
                    choice1 = 0;

                    // Repeat until user enters 4 or 5
                    while (choice1 < 4) {
                        System.out.println("Enter your choice to order pizza");
                        choice1 = input.nextInt();
                        switch (choice1) {
                            case 1 -> {
                                System.out.println("Enter the number of pizzas");
                                NumberOfMargherita += input.nextInt();
                                TotalPizzaAmt += (100.0 * (NumberOfMargherita - OriMar));
                                OriMar = NumberOfMargherita;
                                ObjData.updateInventory(choice1, NumberOfMargherita);
                                if (ObjData.checkInventory())
                                    break;
                                StoreChoice1_1 = choice1;
                            }
                            case 2 -> {
                                System.out.println("Enter the number of pizzas");
                                NumberOfSpicy += input.nextInt();
                                TotalPizzaAmt += (150.0 * (NumberOfSpicy - OriSpi));
                                OriSpi = NumberOfSpicy;
                                ObjData.updateInventory(choice1, NumberOfSpicy);
                                if (ObjData.checkInventory())
                                    break;
                                StoreChoice1_2 = choice1;
                            }
                            case 3 -> {
                                System.out.println("Enter the number of pizzas");
                                NumberOfVeggie += input.nextInt();
                                TotalPizzaAmt += (200.0 * (NumberOfVeggie - OriVeg));
                                OriVeg = NumberOfVeggie;
                                ObjData.updateInventory(choice1, NumberOfVeggie);
                                if (ObjData.checkInventory())
                                    break;
                                StoreChoice1_3 = choice1;
                            }
                            case 4 -> {
                                ObjData.updateBillHeader(TotalPizzaAmt);
                                ObjData.updateBillItem(StoreChoice1_1, StoreChoice1_2, StoreChoice1_3,
                                        NumberOfMargherita, NumberOfSpicy, NumberOfVeggie);
                                ObjData.displayBill(StoreChoice1_1, StoreChoice1_2, StoreChoice1_3);
                            }
                            case 5 -> {
                                System.out.println("Your order has been cancelled.");
                                ObjData.orderCancelled(InventoryValues);
                            }
                            default -> {
                                System.out.println("Invalid input");
                                choice1 = 0;
                            }
                        }
                    }
                    if (StoreChoice1_1 > 0 || StoreChoice1_2 > 0 || StoreChoice1_3 > 0)
                        check = true;
                }
                case 2 -> {
                    System.out.println("\t**********INVENTORY**********");
                    ObjData.displayInventory();
                    System.out.println("****************************************************");
                    System.out.println();
                }
                case 3 -> {
                    System.out.println("\t**********PROFIT/LOSS**********");
                    System.out.println();
                    System.out.println("Please enter the date limit to see profits in the format 'YYYY-MM-DD'");
                    FromDate = input.next();
                    ToDate = input.next();
                    profit = ObjProfits.calculateProfit(FromDate, ToDate);
                    DateChecker = ObjProfits.numberOfDaysBetween(FromDate, ToDate);
                    if (DateChecker < 0)
                        System.out.println(
                                "The first entry must be the initial date and the second entry must be the final date. Please enter the dates properly.");
                    else {
                        if (profit < 0)
                            System.out.println("Loss = Rs." + Math.abs(profit));
                        else
                            System.out.println("Profit = Rs." + profit);
                        System.out.println("****************************************************");
                        System.out.println();
                    }
                }
                case 4 -> {
                    ObjAdminModule.display();
                    choice = 0;
                }
                case 5 -> System.out.println("***** BYE!! ***** THANK YOU *****");
                default -> {
                    System.out.println("Invalid input");
                    choice = 0;
                }
            }
        }
        input.close();
    }
}

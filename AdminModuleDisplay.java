import java.util.Scanner;

/**
 * This class defines the User Interface of the Admin Module
 */
class AdminModuleDisplay {
    final Scanner sc = new Scanner(System.in);

    /**
     * This method is for displaying the Admin Module
     */
    public void display() {
        int AdminUpdate = 0;
        int choice = 0;
        AdminModuleUpdate ObjAdminModuleUpdate = new AdminModuleUpdate();
        System.out.println("**********ADMIN MODULE**********");
        System.out.println("Enter Admin Password");
        String password = sc.next();
        boolean check = ObjAdminModuleUpdate.checkPassword(password);
        if (check) {
            System.out.println("1. Update Inventory");
            System.out.println("2. Update Menu");
            System.out.println("3. Update Consumption");
            System.out.println("4. Update Overheads");
            System.out.println("5. Exit");
            while (choice < 5) {
                System.out.println();
                System.out.println("Enter your choice under main choices of Admin module");
                choice = sc.nextInt();
                switch (choice) {
                    case 1 -> {
                        System.out.println("What would you like to update?");
                        System.out.println("1.Quantity");
                        System.out.println("2.Cost per Unit");
                        System.out.println("3.Exit");
                        while (AdminUpdate != 3) {
                            System.out.println();
                            System.out.println("Enter your choice under update Inventory");
                            AdminUpdate = sc.nextInt();
                            switch (AdminUpdate) {
                                case 1:
                                    ObjAdminModuleUpdate.inventoryQuantity();
                                    break;

                                case 2:
                                    ObjAdminModuleUpdate.inventoryCostPerUnit();
                                    break;

                                case 3:
                                    break;

                                default:
                                    System.out.println("Invalid input");
                                    AdminUpdate = 0;
                            }
                        }
                        AdminUpdate = 0;
                    }
                    case 2 -> {
                        System.out.println("What would you like to update?");
                        System.out.println("1.Price");
                        System.out.println("2.Exit");
                        while (AdminUpdate < 2) {
                            System.out.println();
                            System.out.println("Enter your choices under update Menu");
                            AdminUpdate = sc.nextInt();
                            switch (AdminUpdate) {
                                case 1:
                                    ObjAdminModuleUpdate.menuPrice();
                                    break;

                                case 2:
                                    break;

                                default:
                                    System.out.println("Invalid input");
                                    AdminUpdate = 0;
                            }
                        }
                        AdminUpdate = 0;
                    }
                    case 3 -> {
                        System.out.println("What would you like to update?");
                        System.out.println("1.Quantity Consumed");
                        System.out.println("2.Exit");
                        while (AdminUpdate != 2) {
                            System.out.println();
                            System.out.println("Enter your choice under update Consumption");
                            AdminUpdate = sc.nextInt();
                            switch (AdminUpdate) {
                                case 1:
                                    ObjAdminModuleUpdate.consumptionQuantityConsumed();
                                    break;

                                case 2:
                                    break;

                                default:
                                    System.out.println("Invalid input");
                                    AdminUpdate = 0;
                            }
                        }
                        AdminUpdate = 0;
                    }
                    case 4 -> {
                        System.out.println("What would you like to update");
                        System.out.println("1.Expenses per day");
                        System.out.println("2.Exit");
                        while (AdminUpdate != 2) {
                            System.out.println();
                            System.out.println("Enter your choice under update Overheads");
                            AdminUpdate = sc.nextInt();
                            switch (AdminUpdate) {
                                case 1:
                                    ObjAdminModuleUpdate.overheadsExpensesPerDay();
                                    break;

                                case 2:
                                    break;

                                default:
                                    System.out.println("Invalid input");
                                    AdminUpdate = 0;
                            }
                        }
                        AdminUpdate = 0;
                    }
                    case 5 -> System.out.println("***** The Database has been updated as per the choices inputted *****");
                    default -> System.out.println("Invalid input");
                }
            }
        } else
            System.out.println("Incorrect password");
    }
}

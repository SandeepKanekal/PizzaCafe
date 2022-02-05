import java.sql.*;
import java.util.Scanner;

/**
 * This class is required for updating the tables in the database by the Admin
 */
class AdminModuleUpdate {
    final Scanner sc = new Scanner(System.in);

    /**
     * This method updates the Quantities in the Inventory table
     */
    public void inventoryQuantity() {
        int choice = 0;
        double QTY;
        try {
            Connection Cnc = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzacafe", "root", "TraKat@123");
            Statement Stmt = Cnc.createStatement();
            ResultSet Rslt = Stmt.executeQuery("select ID, Name, Quantity, Unit from pizzacafe.inventory");
            System.out.println("Which item would you like to Update?");
            while (Rslt.next()) {
                System.out.println(Rslt.getString("ID") + ". " + Rslt.getString("Name") + " - "
                        + Rslt.getDouble("Quantity") + " " + Rslt.getString("Unit"));
            }
            System.out.println("6. Exit");
            while (choice < 6) {
                System.out.println();
                System.out.println("Enter your choice under update Quantities of items");
                choice = sc.nextInt();
                switch (choice) {
                    case 1, 2, 3, 4, 5 -> {
                        System.out.println("Enter the quantity to be set");
                        QTY = sc.nextDouble();
                        Stmt.executeUpdate("Update pizzacafe.inventory set Quantity=" + QTY + " where ID=" + choice);
                        System.out.println("Update successful");
                    }
                    case 6 -> System.out.println("Exited");
                    default -> {
                        System.out.println("Invalid input");
                        choice = 0;
                    }
                }
            }
            Cnc.close();
        } catch (Exception exc) {
            System.out.println("Runtime error detected");
            exc.printStackTrace();
        }

    }

    /**
     * This method Updates the Cost Per Unit in the Inventory table
     */
    public void inventoryCostPerUnit() {
        System.out.println("Updating Cost per Unit");
        int choice = 0;
        double CPU;
        try {
            Connection Cnc = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzacafe", "root", "TraKat@123");
            Statement Stmt = Cnc.createStatement();
            ResultSet Rslt = Stmt.executeQuery("select ID, Name, Unit, Cost_per_Unit from pizzacafe.inventory");
            System.out.println("Which item's cost would you like to Update?");
            while (Rslt.next()) {
                System.out.println(Rslt.getString("ID") + ". " + Rslt.getString("Name") + " - Rs."
                        + Rslt.getDouble("Cost_per_Unit") + " per " + Rslt.getString("Unit"));
            }
            System.out.println("6. Exit");
            while (choice < 6) {
                System.out.println();
                System.out.println("Enter your choice under update Cost Per Unit");
                choice = sc.nextInt();
                switch (choice) {
                    case 1, 2, 3, 4, 5 -> {
                        System.out.println("Enter the Cost per Unit that is to be set");
                        CPU = sc.nextDouble();
                        Stmt.executeUpdate(
                                "Update pizzacafe.inventory set Cost_per_Unit=" + CPU + " where ID=" + choice);
                        System.out.println("Update successful");
                    }
                    case 6 -> System.out.println("Exited");
                    default -> {
                        System.out.println("Invalid input");
                        choice = 0;
                    }
                }
            }
            Cnc.close();
        } catch (Exception exc) {
            System.out.println("Runtime error detected");
            exc.printStackTrace();
        }

    }

    /**
     * This method updates Price in the Menu table
     */
    public void menuPrice() {
        System.out.println("Updating Menu");
        int choice = 0;
        double price;
        try {
            Connection Cnc = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzacafe", "root", "TraKat@123");
            Statement Stmt = Cnc.createStatement();
            ResultSet Rslt = Stmt.executeQuery("select * from pizzacafe.menu");
            System.out.println("Which Item's Price would you like to update?");
            while (Rslt.next()) {
                System.out.println(Rslt.getString("ID") + ". " + Rslt.getString("Item") + "-"
                        + Rslt.getString("Currency") + Rslt.getDouble("Price"));
            }
            System.out.println("4. Exit");
            while (choice < 4) {
                System.out.println();
                System.out.println("Enter your choice under update update Price");
                choice = sc.nextInt();
                switch (choice) {
                    case 1, 2, 3 -> {
                        System.out.println("Enter the Price that is to be set");
                        price = sc.nextDouble();
                        Stmt.executeUpdate("Update pizzacafe.menu set Price=" + price + " where ID=" + choice);
                        System.out.println("Update successful\n");
                    }
                    case 4 -> System.out.println("Exited");
                    default -> {
                        System.out.println("Invalid input");
                        choice = 0;
                    }
                }
            }
            Cnc.close();
        } catch (Exception exc) {
            System.out.println("Runtime error detected");
            exc.printStackTrace();
        }

    }

    /**
     * This method Updates Quantity Consumed per piece in the Consumption table
     */
    public void consumptionQuantityConsumed() {
        int choice = 0;
        int choice1 = 0;
        double QTY;
        System.out.println("Updating Consumption");
        System.out.println();
        try {
            Connection Cnc = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzacafe", "root", "TraKat@123");
            Statement Stmt = Cnc.createStatement();
            System.out.println("What pizza's Quantities would you like to update?");
            System.out.println("1. Margherita");
            System.out.println("2. Spicy Pizza");
            System.out.println("3. Veggie Pizza");
            System.out.println("4. Exit");
            while (choice < 4) {
                System.out.println();
                System.out.println("Enter your choice under update Pizza's Quantities");
                choice = sc.nextInt();
                switch (choice) {
                    case 1 -> {
                        System.out.println("What would you like to update?");
                        ResultSet Rslt1 = Stmt.executeQuery(
                                ("select A.Inventory_ID ID2, B.Name, C.Item Pizza, A.QTY_Consumed, A.Unit from pizzacafe.consumption A, pizzacafe.inventory B, pizzacafe.menu C where A.Inventory_id=B.ID and A.menu_ID=C.ID and A.menu_ID="
                                        + choice));
                        while (Rslt1.next()) {
                            System.out.println(Rslt1.getString("ID2") + ". " + Rslt1.getString("Name") + "\t"
                                    + Rslt1.getString("Pizza") + "\t" + Rslt1.getString("QTY_Consumed") + " "
                                    + Rslt1.getString("Unit") + " per piece");
                        }
                        System.out.println("4. Exit");
                        while (choice1 < 4) {
                            System.out.println();
                            System.out
                                    .println("Enter your choice under update Quantities of items used for Margherita");
                            choice1 = sc.nextInt();
                            switch (choice1) {
                                case 1, 2, 3 -> {
                                    System.out.println("What is the Quantity to be set?");
                                    QTY = sc.nextDouble();
                                    Stmt.executeUpdate("Update pizzacafe.consumption set QTY_Consumed=" + QTY
                                            + " where Inventory_ID=" + choice1);
                                    System.out.println("Update successful\n");
                                }
                                case 4 -> System.out.println("Exited");
                                default -> {
                                    System.out.println("Invalid input");
                                    choice1 = 0;
                                }
                            }
                        }
                    }
                    case 2 -> {
                        System.out.println("What would you like to update?");
                        ResultSet Rslt2 = Stmt.executeQuery(
                                ("select A.Inventory_ID ID2, B.Name, C.Item Pizza, A.QTY_Consumed, A.Unit from pizzacafe.consumption A, pizzacafe.inventory B, pizzacafe.menu C where A.Inventory_id=B.ID and A.menu_ID=C.ID and A.menu_ID="
                                        + choice));
                        while (Rslt2.next()) {
                            System.out.println(Rslt2.getString("ID2") + ". " + Rslt2.getString("Name") + "\t"
                                    + Rslt2.getString("Pizza") + "\t" + Rslt2.getString("QTY_Consumed") + " "
                                    + Rslt2.getString("Unit") + " per piece");
                        }
                        System.out.println("5. Exit");
                        while (choice1 < 5) {
                            System.out.println();
                            System.out
                                    .println("Enter your choice under update Quantities of items used for Spicy Pizza");
                            choice1 = sc.nextInt();
                            switch (choice1) {
                                case 1, 2, 3, 4 -> {
                                    System.out.println("What is the Quantity to be set?");
                                    QTY = sc.nextDouble();
                                    Stmt.executeUpdate("Update pizzacafe.consumption set QTY_Consumed=" + QTY
                                            + " where Inventory_ID=" + choice1);
                                    System.out.println("Update successful\n");
                                }
                                case 5 -> System.out.println("Exited");
                                default -> {
                                    System.out.println("Invalid input");
                                    choice1 = 0;
                                }
                            }
                        }
                    }
                    case 3 -> {
                        System.out.println("What would you like to update?");
                        ResultSet Rslt3 = Stmt.executeQuery(
                                ("select A.Inventory_ID ID2, B.Name, C.Item Pizza, A.QTY_Consumed, A.Unit from pizzacafe.consumption A, pizzacafe.inventory B, pizzacafe.menu C where A.Inventory_id=B.ID and A.menu_ID=C.ID and A.menu_ID="
                                        + choice));
                        while (Rslt3.next()) {
                            System.out.println(Rslt3.getString("ID2") + ". " + Rslt3.getString("Name") + "\t"
                                    + Rslt3.getString("Pizza") + "\t" + Rslt3.getString("QTY_Consumed") + " "
                                    + Rslt3.getString("Unit") + " per piece");
                        }
                        System.out.println("6. Exit");
                        while (choice1 < 6) {
                            System.out.println();
                            System.out.println(
                                    "Enter your choice under update Quantities of items used for Veggie Pizza");
                            choice1 = sc.nextInt();
                            switch (choice1) {
                                case 1, 5, 4, 3, 2 -> {
                                    System.out.println("What is the Quantity to be set?");
                                    QTY = sc.nextDouble();
                                    Stmt.executeUpdate("Update pizzacafe.consumption set QTY_Consumed=" + QTY
                                            + " where Inventory_ID=" + choice1);
                                    System.out.println("Update successful\n");
                                }
                                case 6 -> System.out.println("Exited");
                                default -> {
                                    System.out.println("Invalid input");
                                    choice1 = 0;
                                }
                            }
                        }
                    }
                    case 4 -> System.out.println("Exited");
                    default -> {
                        System.out.println("Invalid input");
                        choice = 0;
                    }
                }
            }
            Cnc.close();
        } catch (Exception exc) {
            System.out.println("Runtime error detected");
            exc.printStackTrace();
        }

    }

    /**
     * This method updates the Expenses spent per day in the Overheads table
     */
    public void overheadsExpensesPerDay() {
        int choice = 0;
        double exp;
        try {
            Connection Cnc = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzacafe", "root", "TraKat@123");
            Statement Stmt = Cnc.createStatement();
            ResultSet Rslt = Stmt.executeQuery("select * from pizzacafe.overheads");
            System.out.println("What would you like to update");
            System.out.println();
            while (Rslt.next()) {
                System.out.println(
                        Rslt.getInt("ID") + ". " + Rslt.getString("Description") + " " + Rslt.getDouble("Expense_day"));
            }
            System.out.println("4. Exit");
            while (choice < 4) {
                System.out.println();
                System.out.println("Enter your choice under update Expenses per day");
                choice = sc.nextInt();
                switch (choice) {
                    case 1, 3, 2 -> {
                        System.out.println("What would you like to set the expense as?");
                        exp = sc.nextDouble();
                        Stmt.executeUpdate("Update pizzacafe.overheads set Expense_day=" + exp + " where ID=" + choice);
                        System.out.println("Update successful\n");
                    }
                    case 4 -> System.out.println("Exited");
                    default -> {
                        System.out.println("Invalid input");
                        choice = 0;
                    }
                }
            }
            Cnc.close();
        } catch (Exception exc) {
            System.out.println("Runtime error detected");
            exc.printStackTrace();
        }

    }

    /**
     * This method checks the entered password and then allows access to the Admin
     * module if it is correct
     */
    public boolean checkPassword(String Password) {
        int ID = 0;
        try {
            Connection Cnc = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzacafe", "root", "TraKat@123");
            Statement Stmt = Cnc.createStatement();
            ResultSet Rslt = Stmt
                    .executeQuery("select ID from pizzacafe.user_credentials where Password=sha1('" + Password + "')");
            while (Rslt.next()) {
                ID = Rslt.getInt("ID");
            }
            Cnc.close();
        } catch (Exception exc) {
            System.out.println("Runtime error detected");
            exc.printStackTrace();
        }
        return ID > 0;
    }
}

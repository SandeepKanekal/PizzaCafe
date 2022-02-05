import java.sql.*;
import java.util.Objects;

/**
 * This class contains various methods connecting to the Database, fetching the
 * necessary details and updating it
 */
public class Data {
    /**
     * This method updates the Inventory
     */
    public void updateInventory(int choice, int qty) {
        int id = 1;
        InventoryData ob = new InventoryData();
        double[] InventoryValues = ob.remainingQuantities(choice, qty);
        for (int i = 0; i < (choice + 2); i++) {
            if (InventoryValues[i] == 0)
                continue;
            String sqlstmt = "Update pizzacafe.inventory set Quantity=" + InventoryValues[i] + " where ID=" + id;
            id++;
            try {
                Connection Cnc = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzacafe", "root",
                        "TraKat@123");
                Statement Stmt = Cnc.createStatement();
                Stmt.executeUpdate(sqlstmt);
                Cnc.close();
            } catch (Exception exc) {
                System.out.println("Runtime error detected");
                exc.printStackTrace();
            }
        }
    }

    /**
     * This method displays the Menu
     */
    public void displayMenu() {
        try {
            Connection Cnc = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzacafe", "root", "TraKat@123");
            Statement Stmt = Cnc.createStatement();
            ResultSet Rslt = Stmt.executeQuery("select * from pizzacafe.menu");
            while (Rslt.next()) {
                System.out.println(Rslt.getString("ID") + ". " + Rslt.getString("Item") + "-"
                        + Rslt.getString("Currency") + Rslt.getDouble("Price"));
            }
            Cnc.close();
        } catch (Exception exc) {
            System.out.println("Runtime error detected");
            exc.printStackTrace();
        }
        System.out.println("4. Create Bill and go back to Home");
        System.out.println("5. Cancel Order");
    }

    /**
     * This method displays the Inventory
     */
    public void displayInventory() {
        try {
            Connection Cnc = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzacafe", "root", "TraKat@123");
            Statement Stmt = Cnc.createStatement();
            ResultSet Rslt = Stmt.executeQuery("select Name,Quantity,Unit from pizzacafe.inventory");
            while (Rslt.next()) {
                System.out.println("Remaining " + Rslt.getString("Name") + " : " + Rslt.getDouble("Quantity") + " "
                        + Rslt.getString("Unit"));
            }
            Cnc.close();
        } catch (Exception exc) {
            System.out.println("Runtime error detected");
            exc.printStackTrace();
        }
    }

    /**
     * This method checks the Inventory if it has enough materials
     */
    public boolean checkInventory() {
        boolean ItemsPresent = true;
        try {
            Connection Cnc = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzacafe", "root", "TraKat@123");
            Statement Stmt = Cnc.createStatement();
            ResultSet Rslt = Stmt.executeQuery("select Quantity from pizzacafe.inventory");
            while (Rslt.next()) {
                double check = Rslt.getDouble("Quantity");
                if (check <= 0) {
                    System.out.println("Out of items in the Inventory. Please order another item");
                    ItemsPresent = false;
                    break;
                }
            }
            Cnc.close();
        } catch (Exception exc) {
            System.out.println("Runtime error detected");
            exc.printStackTrace();
        }
        return !ItemsPresent;
    }

    /**
     * This method updates Bill_Header table in the Database
     */
    public void updateBillHeader(double Subtotal) {
        if (Subtotal > 0.0) {
            double tax = 0.1 * Subtotal;
            double total = Subtotal + tax;
            String sqlstmt = "Insert into pizzacafe.bill_header(Bill_date, Amount, Tax, Total) values(CURRENT_TIMESTAMP, "
                    + Subtotal + ", " + tax + ", " + total + ")";
            try {
                Connection Cnc = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzacafe", "root",
                        "TraKat@123");
                Statement Stmt = Cnc.createStatement();
                Stmt.executeUpdate(sqlstmt);
                Cnc.close();
            } catch (Exception exc) {
                System.out.println("Runtime error detected");
                exc.printStackTrace();
            }
        }
    }

    /**
     * This method updates Bill_Item table in the Database
     */
    public void updateBillItem(int ch1, int ch2, int ch3, int n1, int n2, int n3) {
        int[] inputAr = {ch1, n1, ch2, n2, ch3, n3};
        for (int q = 0; q < 3; q++) {
            if (inputAr[q * 2] == 0 && inputAr[q * 2 + 1] == 0)
                continue;
            String sqlstmt = "Insert into pizzacafe.bill_item(Bill_ID, Menu_ID, Quantity, Rate, Total) values((Select max(Bill_ID) from pizzacafe.bill_header), "
                    + inputAr[q * 2] + ", " + inputAr[(q * 2) + 1] + ", (Select Price from pizzacafe.menu where ID="
                    + inputAr[q * 2] + "), (Quantity*rate))";
            try {
                Connection Cnc = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzacafe", "root",
                        "TraKat@123");
                Statement Stmt = Cnc.createStatement();
                Stmt.executeUpdate(sqlstmt);
                Cnc.close();
            } catch (Exception exc) {
                System.out.println("Runtime error detected");
                exc.printStackTrace();
            }
        }
    }

    /**
     * This method changes the quantities in the Inventory if an order is cancelled
     */
    public void orderCancelled(double[] QTY) {
        try {
            Connection Cnc = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzacafe", "root", "TraKat@123");
            Statement Stmt = Cnc.createStatement();
            Stmt.executeUpdate("Update pizzacafe.inventory set Quantity=" + QTY[0] + " where ID=1");
            Stmt.executeUpdate("Update pizzacafe.inventory set Quantity=" + QTY[1] + " where ID=2");
            Stmt.executeUpdate("Update pizzacafe.inventory set Quantity=" + QTY[2] + " where ID=3");
            Stmt.executeUpdate("Update pizzacafe.inventory set Quantity=" + QTY[3] + " where ID=4");
            Stmt.executeUpdate("Update pizzacafe.inventory set Quantity=" + QTY[4] + " where ID=5");
            Cnc.close();
        } catch (Exception exc) {
            System.out.println("Runtime error detected");
            exc.printStackTrace();
        }
    }

    /**
     * This method displays the Bill
     */
    public void displayBill(int a, int b, int c) {
        if (a > 0 || b > 0 || c > 0) {
            int Bill_id = 0;
            String Bill_date = "";
            double Tax = 0.0;
            double Total = 0.0;
            int[] Quantity = new int[3];
            double Amount = 0.0;
            String[] Item = new String[3];
            double[] Price = new double[3];
            double[] PizzaTotal = new double[3];
            short index = 0;
            String sqlstmt = "select A.Bill_id, A.Bill_Date, A.Tax, A.Total, B.Quantity, B.Total Amount, C.Item, C.Price from pizzacafe.bill_header A, pizzacafe.bill_item B, pizzacafe.menu C where A.Bill_id = B.Bill_id and B.Menu_ID = C.ID and A.Bill_id = (select max(Bill_id) from bill_header)";
            try {
                Connection Cnc = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzacafe", "root",
                        "TraKat@123");
                Statement Stmt = Cnc.createStatement();
                ResultSet Rslt = Stmt.executeQuery(sqlstmt);
                while (Rslt.next()) {
                    Bill_id = Rslt.getInt("Bill_id");
                    Bill_date = Rslt.getString("Bill_date");
                    Tax = Rslt.getDouble("Tax");
                    Total = Rslt.getDouble("Total");
                    Quantity[index] = Rslt.getInt("Quantity");
                    Amount += Rslt.getDouble("Amount");
                    Item[index] = Rslt.getString("Item");
                    Price[index] = Rslt.getDouble("Price");
                    PizzaTotal[index] = Quantity[index] * Price[index];
                    index++;
                }
                Cnc.close();
            } catch (Exception exc) {
                System.out.println("Runtime error detected");
                exc.printStackTrace();
            }
            System.out.println("\t********** BILL **********");// creates bill
            System.out.println();
            System.out.println("Bill Number: " + Bill_id);
            System.out.println("Date and Time: " + Bill_date);
            System.out.println();
            System.out.println("QUANTITY" + "\t" + "PIZZA ORDERED" + "\t\t" + "RATE" + "\t\t" + "AMOUNT");
            for (int m = 0; m <= (index - 1); m++) {
                if (!Objects.equals(Item[m], "Veggie Pizza"))
                    System.out.println(
                            Quantity[m] + "\t\t\t" + Item[m] + "\t\t\tRs." + Price[m] + "\tRs." + PizzaTotal[m]);
                else
                    System.out
                            .println(Quantity[m] + "\t\t\t" + Item[m] + "\t\tRs." + Price[m] + "\tRs." + PizzaTotal[m]);
            }
            System.out.println();
            System.out.println("Subtotal: Rs." + Amount);
            System.out.println("Tax: Rs." + Tax);
            System.out.println("Total: Rs." + Total);
            System.out.println("***************************************************");
        } else
            System.out.println("No order placed yet");
    }
}
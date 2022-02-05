import java.sql.*;

/**
 * This class contains methods which are required for updating the Inventory
 */
public class InventoryData {
    /**
     * This method is used for updating the Inventory
     */
    public double[] remainingQuantities(int choice, int NumberOfPizza) {
        int i = 0;
        double[] RemainingQuantity = new double[choice + 2];
        try {
            String sqlstmt = "Select A.Inventory_id, A.QTY_Consumed, B.Quantity from pizzacafe.consumption A, pizzacafe.inventory B where A.Inventory_id = B.id and A.menu_id = "
                    + choice;
            Connection Cnc = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzacafe", "root", "TraKat@123");
            Statement Stmt = Cnc.createStatement();
            ResultSet Rslt = Stmt.executeQuery(sqlstmt);
            while (Rslt.next()) {
                double QuantityConsumed = Rslt.getDouble("QTY_Consumed") * NumberOfPizza;
                RemainingQuantity[i] = Rslt.getDouble("Quantity") - QuantityConsumed;
                i++;
            }
        } catch (Exception exc) {
            System.out.println("Runtime error detected");
            exc.printStackTrace();
        }
        return RemainingQuantity;
    }

    /**
     * This method is required for updating the Inventory in case an order is
     * cancelled
     */
    public double[] storeInventoryData() {
        int q = 0;
        double[] QTY = new double[5];
        try {
            Connection Cnc = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzacafe", "root", "TraKat@123");
            Statement Stmt = Cnc.createStatement();
            ResultSet Rslt = Stmt.executeQuery("select Quantity from pizzacafe.inventory");
            while (Rslt.next()) {
                QTY[q] = Rslt.getDouble("Quantity");
                q++;
            }
            Cnc.close();
        } catch (Exception exc) {
            System.out.println("Runtime error detected");
            exc.printStackTrace();
        }
        return QTY;
    }
}

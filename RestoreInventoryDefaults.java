import java.sql.*;

/**
 * This class resets the Inventory values in Database to defaults
 * Only for ease of usage
 */
class RestoreInventoryDefaults {
    /**
     * Main Method
     */
    public static void main(String[] args) {
        try {
            Connection Cnc = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzacafe", "root", "TraKat@123");
            Statement Stmt = Cnc.createStatement();
            Stmt.executeUpdate("Update pizzacafe.inventory set Quantity=100.00 where id=1");
            Stmt.executeUpdate("Update pizzacafe.inventory set Quantity=50.00 where id=2");
            Stmt.executeUpdate("Update pizzacafe.inventory set Quantity=50.00 where id=3");
            Stmt.executeUpdate("Update pizzacafe.inventory set Quantity=25.00 where id=4");
            Stmt.executeUpdate("Update pizzacafe.inventory set Quantity=50.00 where id=5");
            Cnc.close();
        } catch (Exception exc) {
            System.out.println("Runtime error detected");
            exc.printStackTrace();
        }
        System.out.println("The items in the inventory have been restored to their default quantities.");
    }
}

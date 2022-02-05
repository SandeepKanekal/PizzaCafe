import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * This class contains methods which helps in calculating Profits and Losses.
 */
class Profits {

    /**
     * This method is used to calculate sales
     */
    public double amount(String FromDate, String ToDate) {
        double Sales = 0;
        try {
            Connection Cnc = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzacafe", "root", "TraKat@123");
            Statement Stmt = Cnc.createStatement();
            ResultSet Rslt = Stmt.executeQuery("select sum(Amount) from pizzacafe.bill_header where Bill_Date>='"
                    + FromDate + " 00:00:00' and Bill_date<='" + ToDate + " 23:59:59'");
            while (Rslt.next()) {
                Sales += Rslt.getDouble("sum(Amount)");
            }
            Cnc.close();
        } catch (Exception exc) {
            System.out.println("Runtime error detected");
            exc.printStackTrace();
        }
        return Sales;
    }

    public int numberOfDaysBetween(String FromDate, String ToDate) {
        return (int) ChronoUnit.DAYS.between((LocalDate.parse(FromDate)), (LocalDate.parse(ToDate)));
    }

    /**
     * This method is used to calculate expenses between two dates
     */
    public double expenses(String FromDate, String ToDate) {
        Profits ob = new Profits();
        double ExpensesBetweenTwoDates = 0;
        int noOfDaysBetween = ob.numberOfDaysBetween(FromDate, ToDate);
        try {
            Connection Cnc = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzacafe", "root", "TraKat@123");
            Statement Stmt = Cnc.createStatement();
            ResultSet Rslt = Stmt
                    .executeQuery("select sum(Expense_day)*" + noOfDaysBetween + " from pizzacafe.overheads");
            while (Rslt.next()) {
                ExpensesBetweenTwoDates += Rslt.getDouble("sum(Expense_day)*" + noOfDaysBetween);
            }
            Cnc.close();
        } catch (Exception exc) {
            System.out.println("Runtime error detected");
            exc.printStackTrace();
        }
        return ExpensesBetweenTwoDates;
    }

    /**
     * This method is used to calculate Cost Per Unit in the inventory depending on
     * the choice of pizza ordered
     */
    public double expensesOfPizzas(String FromDate, String ToDate) {
        double PizzaCosts = 0;
        String sqlstmt = "select B.Inventory_id, Cost_per_Unit * sum(QTY_Consumed) from pizzacafe.bill_header A, pizzacafe.consumption B, pizzacafe.inventory C where B.Inventory_id = C.Id and A.Bill_Date>='"
                + FromDate + " 00:00:00' and A.Bill_date<='" + ToDate + " 23:59:59' group by B.Inventory_id";
        try {
            Connection Cnc = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzacafe", "root", "TraKat@123");
            Statement Stmt = Cnc.createStatement();
            ResultSet Rslt = Stmt.executeQuery(sqlstmt);
            while (Rslt.next()) {
                PizzaCosts += Rslt.getDouble("Cost_per_Unit * sum(QTY_Consumed)");
            }
            Cnc.close();
        } catch (Exception exc) {
            System.out.println("Runtime error detected");
            exc.printStackTrace();
        }
        return PizzaCosts;
    }

    /**
     * This method is used to calculate the total profits/losses made between two
     * given dates
     */
    public double calculateProfit(String FromDate, String ToDate) {
        Profits ob = new Profits();
        double Sales = ob.amount(FromDate, ToDate);
        double ExpensesBetweenTwoDates = ob.expenses(FromDate, ToDate);
        double PizzaCosts = ob.expensesOfPizzas(FromDate, ToDate);
        return Sales - (ExpensesBetweenTwoDates + PizzaCosts);
    }
}


import java.sql.*;

public class Connector {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Chyba driveru");

            System.out.println(e.toString());

        }

        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/objednavky", "root", "");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM doprava");
            System.out.println("Spojení navázáno");

        } catch (SQLException e) {
            System.out.println("CHYBA SQL");
            System.out.println(e.toString());
        }
    }
}

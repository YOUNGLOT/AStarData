package help;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Helper {

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlserver://192.168.219.101:1433;database=MYASTAR;user=redwoodYH;password=1234");
    }
}

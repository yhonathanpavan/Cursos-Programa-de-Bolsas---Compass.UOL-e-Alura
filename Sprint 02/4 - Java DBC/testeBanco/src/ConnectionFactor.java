import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactor {

    public Connection getConexao() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/teste?useTimezone=true&serverTimezone=UTC", "root", "toor123");
    }

}

import java.sql.*;

public class TesteConsulta {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/teste?useTimezone=true&serverTimezone=UTC",
        "root", "toor123");

        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM cliente");

        pstm.execute();

        ResultSet rs = pstm.getResultSet();

        while(rs.next()){

            Integer id = rs.getInt("id");
            String nome = rs.getString("nome");
            String endereco = rs.getString("endereco");

            System.out.println(id);
            System.out.println(nome);
            System.out.println(endereco);

        }

        connection.close();

    }

}
